package com.glennfo.lycaniteskin.event;

import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

@Mod.EventBusSubscriber(modid = "lycaniteskin")
public class MobLevelGateHandler {

    private static final Logger LOGGER = LogManager.getLogger("lycaniteskin");

    private static Class<?> BASE_CREATURE_CLASS = null;
    private static Method SET_LEVEL = null;
    private static Method GET_LEVEL = null;
    private static boolean resolved = false;

    private static boolean resolve() {
        if (resolved) return BASE_CREATURE_CLASS != null;
        resolved = true;
        try {
            BASE_CREATURE_CLASS = Class.forName("com.lycanitesmobs.core.entity.base.BaseCreatureEntity");
            SET_LEVEL = BASE_CREATURE_CLASS.getMethod("setLevel", int.class);
            GET_LEVEL = BASE_CREATURE_CLASS.getMethod("getMobLevel");
            return true;
        } catch (Exception e) {
            LOGGER.warn("[LycanitesKin] Lycanites not found — mob level gate disabled.");
            return false;
        }
    }

    private static int maxLevelForDay(long day) {
        if (day < 7)   return 3;
        if (day < 21)  return 8;
        if (day < 51)  return 15;
        if (day < 100) return 25;
        return 100;
    }

    @SubscribeEvent
    public static void onMobSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (!resolve()) return;
        if (event.getEntity().level().isClientSide()) return;
        if (!BASE_CREATURE_CLASS.isInstance(event.getEntity())) return;

        Level world = event.getEntity().level();
        long day = world.getGameTime() / 24000L;
        int cap = maxLevelForDay(day);

        try {
            int current = (int) GET_LEVEL.invoke(event.getEntity());
            if (current > cap) {
                SET_LEVEL.invoke(event.getEntity(), cap);
            }
        } catch (Exception e) {
            LOGGER.debug("[LycanitesKin] Failed to gate mob level: {}", e.getMessage());
        }
    }
}
