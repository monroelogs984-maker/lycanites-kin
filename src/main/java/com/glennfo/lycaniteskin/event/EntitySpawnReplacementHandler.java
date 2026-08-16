package com.glennfo.lycaniteskin.event;

import com.glennfo.lycaniteskin.entity.*;
import com.glennfo.lycaniteskin.item.SummoningScroll;
import com.lycanitesmobs.core.entity.base.BaseCreatureEntity;
import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.creature.aberration.EntityKrake;
import com.lycanitesmobs.core.entity.creature.aberration.EntityYale;
import com.lycanitesmobs.core.entity.creature.beast.EntityBarghest;
import com.lycanitesmobs.core.entity.creature.beast.EntityDawon;
import com.lycanitesmobs.core.entity.creature.beast.EntityMaka;
import com.lycanitesmobs.core.entity.creature.beast.EntityMakaAlpha;
import com.lycanitesmobs.core.entity.creature.beast.EntityWarg;
import com.lycanitesmobs.core.entity.creature.insect.EntityJouste;
import com.lycanitesmobs.core.entity.creature.insect.EntityJousteAlpha;
import com.lycanitesmobs.core.entity.creature.insect.EntityVespid;
import com.lycanitesmobs.core.entity.creature.insect.EntityVespidQueen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = "lycaniteskin")
public class EntitySpawnReplacementHandler {

    private static final Logger LOGGER = LogManager.getLogger("lycaniteskin");

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;

        Entity entity = event.getEntity();
        ServerLevel level = (ServerLevel) event.getLevel();

        // Skip alphas and queens — they ride on top of the tameable, not replaced
        if (entity instanceof EntityMakaAlpha) return;
        if (entity instanceof EntityJousteAlpha) return;
        if (entity instanceof EntityVespidQueen) return;

        // Skip entities already configured as minions — these are Lycanites staff summons.
        // Staff summons set isMinion=true before adding the entity to the world; our scroll
        // summons do not (config is applied post-spawn), so this correctly distinguishes them.
        if (entity instanceof BaseCreatureEntity bce && bce.isMinion()) return;

        String creatureName = null;
        if      (entity instanceof EntityKrake    && !(entity instanceof TameableKrake))    creatureName = "krake";
        else if (entity instanceof EntityYale     && !(entity instanceof TameableYale))     creatureName = "yale";
        else if (entity instanceof EntityMaka     && !(entity instanceof TameableMaka))     creatureName = "maka";
        else if (entity instanceof EntityJouste   && !(entity instanceof TameableJouste))   creatureName = "jouste";
        else if (entity instanceof EntityVespid   && !(entity instanceof TameableVespid))   creatureName = "vespid";
        else if (entity instanceof EntityWarg     && !(entity instanceof TameableWarg))     creatureName = "warg";
        else if (entity instanceof EntityBarghest && !(entity instanceof TameableBarghest)) creatureName = "barghest";
        else if (entity instanceof EntityDawon    && !(entity instanceof TameableDawon))    creatureName = "dawon";

        if (creatureName == null) return;

        boolean loadedFromDisk = event.loadedFromDisk();

        TameableCreatureEntity replacement;
        try {
            replacement = switch (creatureName) {
                case "krake"    -> new TameableKrake(cast(entity.getType()),    level);
                case "yale"     -> new TameableYale(cast(entity.getType()),     level);
                case "maka"     -> new TameableMaka(cast(entity.getType()),     level);
                case "jouste"   -> new TameableJouste(cast(entity.getType()),   level);
                case "vespid"   -> new TameableVespid(cast(entity.getType()),   level);
                case "warg"     -> new TameableWarg(cast(entity.getType()),     level);
                case "barghest" -> new TameableBarghest(cast(entity.getType()), level);
                case "dawon"    -> new TameableDawon(cast(entity.getType()),    level);
                default -> throw new IllegalStateException("Unhandled creature: " + creatureName);
            };
        } catch (Exception e) {
            // Don't cancel — let the original through rather than silently deleting it
            LOGGER.error("[LycanitesKin] Failed to construct replacement for '{}': {}", creatureName, e.getMessage());
            return;
        }

        // Transfer full entity state so taming, health, and custom names survive world reload
        try {
            CompoundTag nbt = new CompoundTag();
            entity.saveWithoutId(nbt);
            replacement.load(nbt);
        } catch (Exception e) {
            LOGGER.warn("[LycanitesKin] Could not copy NBT for '{}': {}", creatureName, e.getMessage());
        }

        // load() restores position from NBT — always sync from the live entity instead
        replacement.setPos(entity.getX(), entity.getY(), entity.getZ());
        replacement.setYRot(entity.getYRot());
        replacement.setXRot(entity.getXRot());

        // If this entity was spawned by SummoningScroll, apply its config to the replacement
        java.util.function.Consumer<net.minecraft.world.entity.Entity> summonConfig =
                SummoningScroll.PENDING_SUMMONS.remove(entity.getUUID());
        if (summonConfig != null) {
            summonConfig.accept(replacement);
        }

        event.setCanceled(true);
        level.addFreshEntity(replacement);

        // Skip onFirstSpawn on reloads — the entity's state is already captured in the copied NBT.
        // Also skip for Maka/Jouste on fresh spawns: Lycanites calls onFirstSpawn on the canceled
        // original (spawning the alpha rider), so our call would double it.
        if (!loadedFromDisk
                && !(replacement instanceof TameableMaka)
                && !(replacement instanceof TameableJouste)) {
            replacement.onFirstSpawn();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends TameableCreatureEntity> EntityType<T> cast(EntityType<?> type) {
        return (EntityType<T>) type;
    }
}
