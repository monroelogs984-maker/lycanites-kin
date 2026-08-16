package com.glennfo.lycaniteskin.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SummoningScroll extends Item {

    private static final Logger LOGGER = LogManager.getLogger("lycaniteskin");
    private static final int SUMMON_DURATION = 6000; // 5 minutes in ticks

    // setMinion and setTemporary are on BaseCreatureEntity, not TameableCreatureEntity
    private static Class<?> BASE_CLASS = null;
    private static Class<?> TAMEABLE_CLASS = null;
    private static Method SET_MINION = null;
    private static Method SET_TEMPORARY = null;
    private static Method SET_PLAYER_OWNER = null;
    private static Method SET_FOLLOWING = null;
    private static Method SET_ASSIST = null;
    private static Method SET_SITTING = null;
    private static Method SET_PASSIVE = null;
    private static boolean resolved = false;

    // Summon configs waiting for EntityJoinLevelEvent. Keyed by the original entity's UUID.
    // When EntitySpawnReplacementHandler replaces a creature it consumes the entry and applies
    // it to the replacement. For non-replaced creatures the entry is cleaned up after spawn.
    public static final Map<UUID, Consumer<Entity>> PENDING_SUMMONS = new ConcurrentHashMap<>();

    private final String creatureName;

    public SummoningScroll(String creatureName) {
        super(new Item.Properties().stacksTo(16));
        this.creatureName = creatureName;
    }

    private static boolean resolve() {
        if (resolved) return BASE_CLASS != null;
        resolved = true;
        try {
            BASE_CLASS     = Class.forName("com.lycanitesmobs.core.entity.base.BaseCreatureEntity");
            TAMEABLE_CLASS = Class.forName("com.lycanitesmobs.core.entity.base.TameableCreatureEntity");
            SET_MINION     = BASE_CLASS.getMethod("setMinion", boolean.class);
            SET_TEMPORARY  = BASE_CLASS.getMethod("setTemporary", int.class);
            SET_PLAYER_OWNER = TAMEABLE_CLASS.getMethod("setPlayerOwner", Player.class);
            SET_FOLLOWING  = TAMEABLE_CLASS.getMethod("setFollowing", boolean.class);
            SET_ASSIST     = TAMEABLE_CLASS.getMethod("setAssist", boolean.class);
            SET_SITTING    = TAMEABLE_CLASS.getMethod("setSitting", boolean.class);
            SET_PASSIVE    = TAMEABLE_CLASS.getMethod("setPassive", boolean.class);
            return true;
        } catch (Exception e) {
            LOGGER.warn("[LycanitesKin] Could not resolve Lycanites creature methods: {}", e.getMessage());
            return false;
        }
    }

    static void applySummonConfig(Entity entity, Player player) {
        if (!resolve()) return;
        try {
            SET_MINION.invoke(entity, true);
            if (TAMEABLE_CLASS.isInstance(entity)) {
                SET_PLAYER_OWNER.invoke(entity, player);
                SET_SITTING.invoke(entity, false);
                SET_FOLLOWING.invoke(entity, true);
                SET_PASSIVE.invoke(entity, false);
                SET_ASSIST.invoke(entity, true);
            }
            SET_TEMPORARY.invoke(entity, SUMMON_DURATION);
        } catch (Exception e) {
            LOGGER.error("[LycanitesKin] Failed to apply summon config to {}: {}", entity.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) return InteractionResultHolder.success(stack);
        if (!resolve()) return InteractionResultHolder.fail(stack);

        @SuppressWarnings("deprecation")
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.fromNamespaceAndPath("lycanitesmobs", creatureName));
        if (type == null) {
            LOGGER.warn("[LycanitesKin] Entity type not found for creature: {}", creatureName);
            return InteractionResultHolder.fail(stack);
        }

        Entity entity = type.create(level);
        if (entity == null) return InteractionResultHolder.fail(stack);

        // Spawn 2 blocks in front of the player
        double angle = Math.toRadians(player.getYRot());
        entity.setPos(
                player.getX() - Math.sin(angle) * 2,
                player.getY(),
                player.getZ() + Math.cos(angle) * 2
        );

        if (!BASE_CLASS.isInstance(entity)) {
            LOGGER.warn("[LycanitesKin] {} is not a BaseCreatureEntity.", creatureName);
            return InteractionResultHolder.fail(stack);
        }

        // Register in PENDING_SUMMONS so EntitySpawnReplacementHandler can apply config to
        // the replacement entity if this creature gets swapped out.
        PENDING_SUMMONS.put(entity.getUUID(), e -> applySummonConfig(e, player));

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.addFreshEntityWithPassengers(entity);
        }

        // If the entry is still present, this entity was NOT replaced — apply config now,
        // after addFreshEntityWithPassengers so we run after any Lycanites onFirstSpawn calls.
        Consumer<Entity> pending = PENDING_SUMMONS.remove(entity.getUUID());
        if (pending != null) {
            pending.accept(entity);
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResultHolder.consume(stack);
    }

    public String getCreatureName() {
        return creatureName;
    }
}
