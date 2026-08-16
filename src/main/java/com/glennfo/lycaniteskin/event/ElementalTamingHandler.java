package com.glennfo.lycaniteskin.event;

import com.glennfo.lycaniteskin.init.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Mod.EventBusSubscriber(modid = "lycaniteskin")
public class ElementalTamingHandler {

    private static final Logger LOGGER = LogManager.getLogger("lycaniteskin");

    private static Class<?> TAMEABLE_CLASS = null;
    private static Method TAME = null;
    private static Field CREATURE_INFO_FIELD = null;
    private static Field CREATURE_TYPE_FIELD = null;
    private static Method GET_TYPE_NAME = null;
    private static boolean resolved = false;

    private static boolean resolve() {
        if (resolved) return TAMEABLE_CLASS != null;
        resolved = true;
        try {
            TAMEABLE_CLASS     = Class.forName("com.lycanitesmobs.core.entity.base.TameableCreatureEntity");
            TAME               = TAMEABLE_CLASS.getMethod("tame", Player.class);
            Class<?> baseClass = Class.forName("com.lycanitesmobs.core.entity.base.BaseCreatureEntity");
            CREATURE_INFO_FIELD = baseClass.getField("creatureInfo");
            Class<?> infoClass = Class.forName("com.lycanitesmobs.core.data.info.creature.CreatureInfo");
            CREATURE_TYPE_FIELD = infoClass.getField("creatureType");
            Class<?> typeClass  = Class.forName("com.lycanitesmobs.core.data.info.creature.CreatureType");
            GET_TYPE_NAME       = typeClass.getMethod("getName");
            return true;
        } catch (Exception e) {
            LOGGER.warn("[LycanitesKin] Could not resolve Lycanites methods for elemental taming: {}", e.getMessage());
            return false;
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        Player player = event.getEntity();
        ItemStack held = player.getMainHandItem();
        if (held.getItem() != ModItems.TREAT_ELEMENTAL.get()) return;

        Entity target = event.getTarget();
        if (!resolve()) return;
        if (!TAMEABLE_CLASS.isInstance(target)) return;

        try {
            Object creatureInfo = CREATURE_INFO_FIELD.get(target);
            if (creatureInfo == null) return;

            Object creatureType = CREATURE_TYPE_FIELD.get(creatureInfo);
            if (creatureType == null) return;

            String typeName = (String) GET_TYPE_NAME.invoke(creatureType);
            if (!"elemental".equals(typeName)) return;

            // Mirrors the Lycanites treat taming flow — tame() handles knowledge/RNG internally
            boolean tamed = (boolean) TAME.invoke(target, player);

            if (!player.getAbilities().instabuild) {
                held.shrink(1);
            }

            event.setCanceled(true);
            LOGGER.debug("[LycanitesKin] Elemental tame attempt on {} — result: {}", target.getClass().getSimpleName(), tamed);

        } catch (Exception e) {
            LOGGER.error("[LycanitesKin] Error during elemental taming: {}", e.getMessage());
        }
    }
}
