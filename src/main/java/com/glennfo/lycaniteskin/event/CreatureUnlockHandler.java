package com.glennfo.lycaniteskin.event;

import com.glennfo.lycaniteskin.entity.*;
import com.lycanitesmobs.core.data.info.creature.CreatureInfo;
import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.creature.aberration.EntityKrake;
import com.lycanitesmobs.core.entity.creature.aberration.EntityYale;
import com.lycanitesmobs.core.entity.creature.beast.EntityBarghest;
import com.lycanitesmobs.core.entity.creature.beast.EntityDawon;
import com.lycanitesmobs.core.entity.creature.beast.EntityMaka;
import com.lycanitesmobs.core.entity.creature.beast.EntityWarg;
import com.lycanitesmobs.core.entity.creature.insect.EntityJouste;
import com.lycanitesmobs.core.entity.creature.insect.EntityVespid;
import com.lycanitesmobs.core.manager.CreatureManager;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

@Mod.EventBusSubscriber(modid = "lycaniteskin", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CreatureUnlockHandler {

    private static final Logger LOGGER = LogManager.getLogger("lycaniteskin");

    private static final Set<String> BOSS_BLACKLIST = Set.of("amalgalich", "asmodeus", "rahovart");

    // Maps original non-tameable Lycanites class → our TameableCreatureEntity subclass.
    // The entityClass replacement makes CreatureInfo.isTameable() pass its isAssignableFrom check.
    private static final Map<Class<?>, Class<?>> TAMEABLE_REPLACEMENTS = new HashMap<>();
    static {
        TAMEABLE_REPLACEMENTS.put(EntityKrake.class,    TameableKrake.class);
        TAMEABLE_REPLACEMENTS.put(EntityYale.class,     TameableYale.class);
        TAMEABLE_REPLACEMENTS.put(EntityBarghest.class, TameableBarghest.class);
        TAMEABLE_REPLACEMENTS.put(EntityDawon.class,    TameableDawon.class);
        TAMEABLE_REPLACEMENTS.put(EntityMaka.class,     TameableMaka.class);
        TAMEABLE_REPLACEMENTS.put(EntityWarg.class,     TameableWarg.class);
        TAMEABLE_REPLACEMENTS.put(EntityJouste.class,   TameableJouste.class);
        TAMEABLE_REPLACEMENTS.put(EntityVespid.class,   TameableVespid.class);
    }

    private static final Field TAMEABLE_FIELD;
    private static final Field SUMMONABLE_FIELD;
    private static final Field ENTITY_CLASS_FIELD;
    private static final Field CREATURE_CLASS_MAP_FIELD;

    static {
        try {
            TAMEABLE_FIELD = CreatureInfo.class.getDeclaredField("tameable");
            TAMEABLE_FIELD.setAccessible(true);
            SUMMONABLE_FIELD = CreatureInfo.class.getDeclaredField("summonable");
            SUMMONABLE_FIELD.setAccessible(true);
            ENTITY_CLASS_FIELD = CreatureInfo.class.getDeclaredField("entityClass");
            ENTITY_CLASS_FIELD.setAccessible(true);
            CREATURE_CLASS_MAP_FIELD = CreatureManager.class.getDeclaredField("creatureClassMap");
            CREATURE_CLASS_MAP_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("[LycanitesKin] CreatureInfo field lookup failed", e);
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        try {
            CreatureManager mgr = CreatureManager.getInstance();
            if (mgr == null) {
                LOGGER.warn("[LycanitesKin] CreatureManager not available — skipping creature unlock.");
                return;
            }

            @SuppressWarnings("unchecked")
            Map<Class<?>, CreatureInfo> classMap = (Map<Class<?>, CreatureInfo>) CREATURE_CLASS_MAP_FIELD.get(mgr);

            int tamed = 0, summoned = 0;
            for (CreatureInfo info : mgr.getCreatures()) {
                String name = info.getName();
                Class<?> entityClass = (Class<?>) ENTITY_CLASS_FIELD.get(info);

                boolean canBeTameable = entityClass != null
                        && TameableCreatureEntity.class.isAssignableFrom(entityClass);

                if (canBeTameable && !BOSS_BLACKLIST.contains(name)) {
                    TAMEABLE_FIELD.setBoolean(info, true);
                    tamed++;
                }

                // For creatures we've replaced with a Tameable* subclass:
                // 1. Patch entityClass so CreatureInfo.isTameable() passes its isAssignableFrom check.
                // 2. Register the Tameable* class in creatureClassMap so BaseCreatureEntity.defineSynchedData()
                //    can find the CreatureInfo via getCreature(this.getClass()) — without this it returns null
                //    and NPEs immediately on creatureInfo.getWidth().
                Class<?> replacement = TAMEABLE_REPLACEMENTS.get(entityClass);
                if (replacement != null) {
                    // Keep the original class in classMap so Lycanites staff summons (which spawn
                    // the original EntityWarg etc.) can still resolve their CreatureInfo via
                    // getCreature(this.getClass()). Only ADD the Tameable class as a second entry.
                    ENTITY_CLASS_FIELD.set(info, replacement);
                    classMap.put(replacement, info);
                    TAMEABLE_FIELD.setBoolean(info, true);
                    tamed++;
                }

                if (!BOSS_BLACKLIST.contains(name)) {
                    SUMMONABLE_FIELD.setBoolean(info, true);
                    summoned++;
                }
            }

            LOGGER.info("[LycanitesKin] Unlocked tameable on {} and summonable on {} Lycanites creatures.", tamed, summoned);

        } catch (Exception e) {
            LOGGER.error("[LycanitesKin] Failed to unlock Lycanites creatures: {}", e.getMessage(), e);
        }
    }
}
