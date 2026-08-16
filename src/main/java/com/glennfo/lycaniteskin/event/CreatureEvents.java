package com.glennfo.lycaniteskin.event;

import com.glennfo.lycaniteskin.init.ModItems;
import com.lycanitesmobs.core.entity.creature.aberration.EntityKrake;
import com.lycanitesmobs.core.entity.creature.aberration.EntityYale;
import com.lycanitesmobs.core.entity.creature.amphibian.EntitySalamander;
import com.lycanitesmobs.core.entity.creature.aquatic.EntityIoray;
import com.lycanitesmobs.core.entity.creature.arachnid.EntityFrostweaver;
import com.lycanitesmobs.core.entity.creature.avian.EntityRaiko;
import com.lycanitesmobs.core.entity.creature.avian.EntityRoc;
import com.lycanitesmobs.core.entity.creature.beast.EntityBarghest;
import com.lycanitesmobs.core.entity.creature.beast.EntityDawon;
import com.lycanitesmobs.core.entity.creature.beast.EntityMaka;
import com.lycanitesmobs.core.entity.creature.beast.EntityMakaAlpha;
import com.lycanitesmobs.core.entity.creature.beast.EntityWarg;
import com.lycanitesmobs.core.entity.creature.dragon.EntityRemobra;
import com.lycanitesmobs.core.entity.creature.elemental.EntityArgus;
import com.lycanitesmobs.core.entity.creature.elemental.EntitySpriggan;
import com.lycanitesmobs.core.entity.creature.insect.EntityJouste;
import com.lycanitesmobs.core.entity.creature.insect.EntityVespid;
import com.lycanitesmobs.core.entity.creature.undead.EntityReaper;
import com.lycanitesmobs.core.manager.CreatureManager;
import com.lycanitesmobs.core.data.info.creature.CreatureInfo;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;


@Mod.EventBusSubscriber(modid = "lycaniteskin", bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreatureEvents {

    // -------------------------------------------------------------------------
    // Patch summonable flags after all mod loading is complete
    // -------------------------------------------------------------------------

    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        // Ensure replaced creatures are marked tameable (their registered entityClass is the
        // original non-tameable class, so CreatureUnlockHandler won't catch them automatically)
        patchTameable("krake",    true);
        patchTameable("yale",     true);
        patchTameable("maka",     true);
        patchTameable("jouste",   true);
        patchTameable("vespid",   true);
        patchTameable("warg",     true);
        patchTameable("barghest", true);
        patchTameable("dawon",    true);

        patchSummonable("warg",          true);
        patchSummonable("chupacabra",    true);
        patchSummonable("roc",           true);
        patchSummonable("uvaraptor",     true);
        patchSummonable("ventoraptor",   true);
        patchSummonable("raiko",         true);
        patchSummonable("barghest",      true);
        patchSummonable("balayang",      true);
        patchSummonable("bobeko",        true);
        patchSummonable("brucha",        true);
        patchSummonable("dawon",         true);
        patchSummonable("epion",         true);
        patchSummonable("feradon",       true);
        patchSummonable("kobold",        true);
        patchSummonable("maug",          true);
        patchSummonable("wraamon",       true);
        patchSummonable("abaia",         true);
        patchSummonable("abtu",          true);
        patchSummonable("cephignis",     true);
        patchSummonable("herma",         true);
        patchSummonable("ioray",         true);
        patchSummonable("roa",           true);
        patchSummonable("silex",         true);
        patchSummonable("skylus",        true);
        patchSummonable("stryder",       true);
        patchSummonable("aglebemu",      true);
        patchSummonable("ningen",        true);
        patchSummonable("salamander",    true);
        patchSummonable("frostweaver",   true);
        patchSummonable("lycosa",        true);
        patchSummonable("sutiramu",      true);
        patchSummonable("ettin",         true);
        patchSummonable("troll",         true);
        patchSummonable("wildkin",       true);
        patchSummonable("arisaur",       true);
        patchSummonable("aspid",         true);
        patchSummonable("geken",         true);
        patchSummonable("khalk",         true);
        patchSummonable("thresher",      true);
        patchSummonable("jouste",        true);
        patchSummonable("vespid",        true);
        patchSummonable("yale",          true);
        patchSummonable("cryptkeeper",   true);
        patchSummonable("geist",         true);
        patchSummonable("ghoul",         true);
        patchSummonable("necrovore",     true);
        patchSummonable("crusk",         true);
        patchSummonable("ika",           true);
        patchSummonable("serpix",        true);
        patchSummonable("umibas",        true);
        patchSummonable("krake",         true);
        patchSummonable("spriggan",      true);
        patchSummonable("argus",         true);
    }

    private static final java.lang.reflect.Field TAMEABLE_FIELD;
    private static final java.lang.reflect.Field SUMMONABLE_FIELD;

    static {
        try {
            TAMEABLE_FIELD = CreatureInfo.class.getDeclaredField("tameable");
            TAMEABLE_FIELD.setAccessible(true);
            SUMMONABLE_FIELD = CreatureInfo.class.getDeclaredField("summonable");
            SUMMONABLE_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Lycanites Kin: CreatureInfo field lookup failed", e);
        }
    }

    private static void patchTameable(String name, boolean tameable) {
        CreatureManager mgr = CreatureManager.getInstance();
        if (mgr == null) return;
        CreatureInfo info = mgr.getCreature(name);
        if (info == null) return;
        try {
            TAMEABLE_FIELD.setBoolean(info, tameable);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Lycanites Kin: failed to set tameable on " + name, e);
        }
    }

    private static void patchSummonable(String name, boolean summonable) {
        CreatureManager mgr = CreatureManager.getInstance();
        if (mgr == null) return;
        CreatureInfo info = mgr.getCreature(name);
        if (info == null) return;
        try {
            SUMMONABLE_FIELD.setBoolean(info, summonable);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Lycanites Kin: failed to set summonable on " + name, e);
        }
    }

    // -------------------------------------------------------------------------
    // Inject creature material drops on death
    // -------------------------------------------------------------------------

    @Mod.EventBusSubscriber(modid = "lycaniteskin")
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onLivingDrops(LivingDropsEvent event) {
            var entity = event.getEntity();
            var level = entity.level();
            if (level.isClientSide()) return;
            var drops = event.getDrops();
            int lootingLevel = event.getLootingLevel();

            if (entity instanceof EntityWarg || entity instanceof com.glennfo.lycaniteskin.entity.TameableWarg) {
                addDrop(drops, level, entity, ModItems.WARG_PELT.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityVespid || entity instanceof com.glennfo.lycaniteskin.entity.TameableVespid) {
                addDrop(drops, level, entity, ModItems.VESPID_CHITIN.get(), 1, 1, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityMaka || entity instanceof EntityMakaAlpha || entity instanceof com.glennfo.lycaniteskin.entity.TameableMaka) {
                addDrop(drops, level, entity, ModItems.MAKA_HIDE.get(), 1, 3, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityRoc) {
                addDrop(drops, level, entity, ModItems.ROC_FEATHER.get(), 2, 4, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityReaper) {
                addDrop(drops, level, entity, ModItems.REAPER_BONE.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityFrostweaver) {
                addDrop(drops, level, entity, ModItems.FROSTWEAVER_SILK.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityRemobra) {
                addDrop(drops, level, entity, ModItems.REMOBRA_SCALE.get(), 1, 3, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityJouste || entity instanceof com.glennfo.lycaniteskin.entity.TameableJouste) {
                addDrop(drops, level, entity, ModItems.JOUSTE_SHELL.get(), 1, 1, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityKrake || entity instanceof com.glennfo.lycaniteskin.entity.TameableKrake) {
                addDrop(drops, level, entity, ModItems.KRAKE_SHELL.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntitySpriggan) {
                addDrop(drops, level, entity, ModItems.SPRIGGAN_BARK.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityArgus) {
                addDrop(drops, level, entity, ModItems.ARGUS_SCALE.get(), 1, 1, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityBarghest || entity instanceof com.glennfo.lycaniteskin.entity.TameableBarghest) {
                addDrop(drops, level, entity, ModItems.BARGHEST_PELT.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityDawon || entity instanceof com.glennfo.lycaniteskin.entity.TameableDawon) {
                addDrop(drops, level, entity, ModItems.DAWON_PELT.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntitySalamander) {
                addDrop(drops, level, entity, ModItems.SALAMANDER_SCALE.get(), 1, 2, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityYale || entity instanceof com.glennfo.lycaniteskin.entity.TameableYale) {
                addDrop(drops, level, entity, ModItems.YALE_HIDE.get(), 1, 3, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityRaiko) {
                addDrop(drops, level, entity, ModItems.RAIKO_FEATHER.get(), 2, 4, lootingLevel, 0.5f);
            }
            else if (entity instanceof EntityIoray) {
                addDrop(drops, level, entity, ModItems.IORAY_MEMBRANE.get(), 1, 2, lootingLevel, 0.5f);
            }
        }

        private static void addDrop(
                java.util.Collection<ItemEntity> drops,
                net.minecraft.world.level.Level level,
                net.minecraft.world.entity.LivingEntity entity,
                net.minecraft.world.item.Item item,
                int min, int max, int lootingBonus, float chance) {

            if (entity.getRandom().nextFloat() > chance) return;
            int count = min + entity.getRandom().nextInt(Math.max(1, max - min + 1));
            count = Math.min(count + lootingBonus, item.getMaxStackSize());
            drops.add(new ItemEntity(level,
                    entity.getX(), entity.getY(), entity.getZ(),
                    new ItemStack(item, count)));
        }
    }
}
