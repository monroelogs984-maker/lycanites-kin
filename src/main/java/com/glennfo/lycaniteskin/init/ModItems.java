package com.glennfo.lycaniteskin.init;

import com.glennfo.lycaniteskin.LycanitesKin;
import com.glennfo.lycaniteskin.item.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LycanitesKin.MOD_ID);

    // -------------------------------------------------------------------------
    // Creature material drops
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> WARG_PELT = ITEMS.register("warg_pelt",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> VESPID_CHITIN = ITEMS.register("vespid_chitin",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> MAKA_HIDE = ITEMS.register("maka_hide",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> ROC_FEATHER = ITEMS.register("roc_feather",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> REAPER_BONE = ITEMS.register("reaper_bone",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> FROSTWEAVER_SILK = ITEMS.register("frostweaver_silk",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> REMOBRA_SCALE = ITEMS.register("remobra_scale",
            () -> new Item(new Item.Properties().stacksTo(64)));

    // -------------------------------------------------------------------------
    // Warg Pelt armor set
    // -------------------------------------------------------------------------

    public static final RegistryObject<ArmorItem> WARG_PELT_HELMET = ITEMS.register("warg_pelt_helmet",
            () -> new ArmorItem(ModArmorMaterials.WARG_PELT, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> WARG_PELT_CHESTPLATE = ITEMS.register("warg_pelt_chestplate",
            () -> new ArmorItem(ModArmorMaterials.WARG_PELT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> WARG_PELT_LEGGINGS = ITEMS.register("warg_pelt_leggings",
            () -> new ArmorItem(ModArmorMaterials.WARG_PELT, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> WARG_PELT_BOOTS = ITEMS.register("warg_pelt_boots",
            () -> new ArmorItem(ModArmorMaterials.WARG_PELT, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Vespid Chitin armor set
    // -------------------------------------------------------------------------

    public static final RegistryObject<ArmorItem> VESPID_CHITIN_HELMET = ITEMS.register("vespid_chitin_helmet",
            () -> new ArmorItem(ModArmorMaterials.VESPID_CHITIN, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> VESPID_CHITIN_CHESTPLATE = ITEMS.register("vespid_chitin_chestplate",
            () -> new ArmorItem(ModArmorMaterials.VESPID_CHITIN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> VESPID_CHITIN_LEGGINGS = ITEMS.register("vespid_chitin_leggings",
            () -> new ArmorItem(ModArmorMaterials.VESPID_CHITIN, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> VESPID_CHITIN_BOOTS = ITEMS.register("vespid_chitin_boots",
            () -> new ArmorItem(ModArmorMaterials.VESPID_CHITIN, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Maka Hide armor set
    // -------------------------------------------------------------------------

    public static final RegistryObject<ArmorItem> MAKA_HIDE_HELMET = ITEMS.register("maka_hide_helmet",
            () -> new ArmorItem(ModArmorMaterials.MAKA_HIDE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> MAKA_HIDE_CHESTPLATE = ITEMS.register("maka_hide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.MAKA_HIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> MAKA_HIDE_LEGGINGS = ITEMS.register("maka_hide_leggings",
            () -> new ArmorItem(ModArmorMaterials.MAKA_HIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> MAKA_HIDE_BOOTS = ITEMS.register("maka_hide_boots",
            () -> new ArmorItem(ModArmorMaterials.MAKA_HIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Roc Feather armor set
    // -------------------------------------------------------------------------

    public static final RegistryObject<ArmorItem> ROC_FEATHER_HELMET = ITEMS.register("roc_feather_helmet",
            () -> new ArmorItem(ModArmorMaterials.ROC_FEATHER, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ROC_FEATHER_CHESTPLATE = ITEMS.register("roc_feather_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ROC_FEATHER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ROC_FEATHER_LEGGINGS = ITEMS.register("roc_feather_leggings",
            () -> new ArmorItem(ModArmorMaterials.ROC_FEATHER, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ROC_FEATHER_BOOTS = ITEMS.register("roc_feather_boots",
            () -> new ArmorItem(ModArmorMaterials.ROC_FEATHER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Reaper Bone armor set
    // -------------------------------------------------------------------------

    public static final RegistryObject<ArmorItem> REAPER_BONE_HELMET = ITEMS.register("reaper_bone_helmet",
            () -> new ArmorItem(ModArmorMaterials.REAPER_BONE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> REAPER_BONE_CHESTPLATE = ITEMS.register("reaper_bone_chestplate",
            () -> new ArmorItem(ModArmorMaterials.REAPER_BONE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> REAPER_BONE_LEGGINGS = ITEMS.register("reaper_bone_leggings",
            () -> new ArmorItem(ModArmorMaterials.REAPER_BONE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> REAPER_BONE_BOOTS = ITEMS.register("reaper_bone_boots",
            () -> new ArmorItem(ModArmorMaterials.REAPER_BONE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Frostweaver Silk armor set
    // -------------------------------------------------------------------------

    public static final RegistryObject<ArmorItem> FROSTWEAVER_SILK_HELMET = ITEMS.register("frostweaver_silk_helmet",
            () -> new ArmorItem(ModArmorMaterials.FROSTWEAVER_SILK, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> FROSTWEAVER_SILK_CHESTPLATE = ITEMS.register("frostweaver_silk_chestplate",
            () -> new ArmorItem(ModArmorMaterials.FROSTWEAVER_SILK, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> FROSTWEAVER_SILK_LEGGINGS = ITEMS.register("frostweaver_silk_leggings",
            () -> new ArmorItem(ModArmorMaterials.FROSTWEAVER_SILK, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> FROSTWEAVER_SILK_BOOTS = ITEMS.register("frostweaver_silk_boots",
            () -> new ArmorItem(ModArmorMaterials.FROSTWEAVER_SILK, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Remobra Scale armor set
    // -------------------------------------------------------------------------

    public static final RegistryObject<ArmorItem> REMOBRA_SCALE_HELMET = ITEMS.register("remobra_scale_helmet",
            () -> new ArmorItem(ModArmorMaterials.REMOBRA_SCALE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> REMOBRA_SCALE_CHESTPLATE = ITEMS.register("remobra_scale_chestplate",
            () -> new ArmorItem(ModArmorMaterials.REMOBRA_SCALE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> REMOBRA_SCALE_LEGGINGS = ITEMS.register("remobra_scale_leggings",
            () -> new ArmorItem(ModArmorMaterials.REMOBRA_SCALE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> REMOBRA_SCALE_BOOTS = ITEMS.register("remobra_scale_boots",
            () -> new ArmorItem(ModArmorMaterials.REMOBRA_SCALE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Jouste Shell armor set  (low-tier, desert crab)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> JOUSTE_SHELL = ITEMS.register("jouste_shell",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> JOUSTE_SHELL_HELMET = ITEMS.register("jouste_shell_helmet",
            () -> new ArmorItem(ModArmorMaterials.JOUSTE_SHELL, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> JOUSTE_SHELL_CHESTPLATE = ITEMS.register("jouste_shell_chestplate",
            () -> new ArmorItem(ModArmorMaterials.JOUSTE_SHELL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> JOUSTE_SHELL_LEGGINGS = ITEMS.register("jouste_shell_leggings",
            () -> new ArmorItem(ModArmorMaterials.JOUSTE_SHELL, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> JOUSTE_SHELL_BOOTS = ITEMS.register("jouste_shell_boots",
            () -> new ArmorItem(ModArmorMaterials.JOUSTE_SHELL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Krake Shell armor set  (mid-tier, aquatic crustacean)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> KRAKE_SHELL = ITEMS.register("krake_shell",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> KRAKE_SHELL_HELMET = ITEMS.register("krake_shell_helmet",
            () -> new ArmorItem(ModArmorMaterials.KRAKE_SHELL, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> KRAKE_SHELL_CHESTPLATE = ITEMS.register("krake_shell_chestplate",
            () -> new ArmorItem(ModArmorMaterials.KRAKE_SHELL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> KRAKE_SHELL_LEGGINGS = ITEMS.register("krake_shell_leggings",
            () -> new ArmorItem(ModArmorMaterials.KRAKE_SHELL, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> KRAKE_SHELL_BOOTS = ITEMS.register("krake_shell_boots",
            () -> new ArmorItem(ModArmorMaterials.KRAKE_SHELL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Spriggan Bark armor set  (mid-tier, nature elemental)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> SPRIGGAN_BARK = ITEMS.register("spriggan_bark",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> SPRIGGAN_BARK_HELMET = ITEMS.register("spriggan_bark_helmet",
            () -> new ArmorItem(ModArmorMaterials.SPRIGGAN_BARK, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> SPRIGGAN_BARK_CHESTPLATE = ITEMS.register("spriggan_bark_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SPRIGGAN_BARK, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> SPRIGGAN_BARK_LEGGINGS = ITEMS.register("spriggan_bark_leggings",
            () -> new ArmorItem(ModArmorMaterials.SPRIGGAN_BARK, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> SPRIGGAN_BARK_BOOTS = ITEMS.register("spriggan_bark_boots",
            () -> new ArmorItem(ModArmorMaterials.SPRIGGAN_BARK, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Argus Scale armor set  (high-tier, chaos elemental)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> ARGUS_SCALE = ITEMS.register("argus_scale",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> ARGUS_SCALE_HELMET = ITEMS.register("argus_scale_helmet",
            () -> new ArmorItem(ModArmorMaterials.ARGUS_SCALE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ARGUS_SCALE_CHESTPLATE = ITEMS.register("argus_scale_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ARGUS_SCALE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ARGUS_SCALE_LEGGINGS = ITEMS.register("argus_scale_leggings",
            () -> new ArmorItem(ModArmorMaterials.ARGUS_SCALE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> ARGUS_SCALE_BOOTS = ITEMS.register("argus_scale_boots",
            () -> new ArmorItem(ModArmorMaterials.ARGUS_SCALE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Barghest Pelt armor set  (mid-high, fae shadow wolf)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> BARGHEST_PELT = ITEMS.register("barghest_pelt",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> BARGHEST_PELT_HELMET = ITEMS.register("barghest_pelt_helmet",
            () -> new ArmorItem(ModArmorMaterials.BARGHEST_PELT, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> BARGHEST_PELT_CHESTPLATE = ITEMS.register("barghest_pelt_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BARGHEST_PELT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> BARGHEST_PELT_LEGGINGS = ITEMS.register("barghest_pelt_leggings",
            () -> new ArmorItem(ModArmorMaterials.BARGHEST_PELT, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> BARGHEST_PELT_BOOTS = ITEMS.register("barghest_pelt_boots",
            () -> new ArmorItem(ModArmorMaterials.BARGHEST_PELT, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Dawon Pelt armor set  (mid-tier, tiger predator)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> DAWON_PELT = ITEMS.register("dawon_pelt",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> DAWON_PELT_HELMET = ITEMS.register("dawon_pelt_helmet",
            () -> new ArmorItem(ModArmorMaterials.DAWON_PELT, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> DAWON_PELT_CHESTPLATE = ITEMS.register("dawon_pelt_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DAWON_PELT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> DAWON_PELT_LEGGINGS = ITEMS.register("dawon_pelt_leggings",
            () -> new ArmorItem(ModArmorMaterials.DAWON_PELT, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> DAWON_PELT_BOOTS = ITEMS.register("dawon_pelt_boots",
            () -> new ArmorItem(ModArmorMaterials.DAWON_PELT, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Salamander Scale armor set  (mid-tier, fire amphibian)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> SALAMANDER_SCALE = ITEMS.register("salamander_scale",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> SALAMANDER_SCALE_HELMET = ITEMS.register("salamander_scale_helmet",
            () -> new ArmorItem(ModArmorMaterials.SALAMANDER_SCALE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> SALAMANDER_SCALE_CHESTPLATE = ITEMS.register("salamander_scale_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SALAMANDER_SCALE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> SALAMANDER_SCALE_LEGGINGS = ITEMS.register("salamander_scale_leggings",
            () -> new ArmorItem(ModArmorMaterials.SALAMANDER_SCALE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> SALAMANDER_SCALE_BOOTS = ITEMS.register("salamander_scale_boots",
            () -> new ArmorItem(ModArmorMaterials.SALAMANDER_SCALE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Yale Hide armor set  (mid-tier, mountain beast)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> YALE_HIDE = ITEMS.register("yale_hide",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> YALE_HIDE_HELMET = ITEMS.register("yale_hide_helmet",
            () -> new ArmorItem(ModArmorMaterials.YALE_HIDE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> YALE_HIDE_CHESTPLATE = ITEMS.register("yale_hide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.YALE_HIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> YALE_HIDE_LEGGINGS = ITEMS.register("yale_hide_leggings",
            () -> new ArmorItem(ModArmorMaterials.YALE_HIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> YALE_HIDE_BOOTS = ITEMS.register("yale_hide_boots",
            () -> new ArmorItem(ModArmorMaterials.YALE_HIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Raiko Feather armor set  (mid-high, storm bird)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> RAIKO_FEATHER = ITEMS.register("raiko_feather",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> RAIKO_FEATHER_HELMET = ITEMS.register("raiko_feather_helmet",
            () -> new ArmorItem(ModArmorMaterials.RAIKO_FEATHER, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> RAIKO_FEATHER_CHESTPLATE = ITEMS.register("raiko_feather_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RAIKO_FEATHER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> RAIKO_FEATHER_LEGGINGS = ITEMS.register("raiko_feather_leggings",
            () -> new ArmorItem(ModArmorMaterials.RAIKO_FEATHER, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> RAIKO_FEATHER_BOOTS = ITEMS.register("raiko_feather_boots",
            () -> new ArmorItem(ModArmorMaterials.RAIKO_FEATHER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Ioray Membrane armor set  (mid-tier, aquatic ray)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> IORAY_MEMBRANE = ITEMS.register("ioray_membrane",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ArmorItem> IORAY_MEMBRANE_HELMET = ITEMS.register("ioray_membrane_helmet",
            () -> new ArmorItem(ModArmorMaterials.IORAY_MEMBRANE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> IORAY_MEMBRANE_CHESTPLATE = ITEMS.register("ioray_membrane_chestplate",
            () -> new ArmorItem(ModArmorMaterials.IORAY_MEMBRANE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<ArmorItem> IORAY_MEMBRANE_LEGGINGS = ITEMS.register("ioray_membrane_leggings",
            () -> new ArmorItem(ModArmorMaterials.IORAY_MEMBRANE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<ArmorItem> IORAY_MEMBRANE_BOOTS = ITEMS.register("ioray_membrane_boots",
            () -> new ArmorItem(ModArmorMaterials.IORAY_MEMBRANE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // -------------------------------------------------------------------------
    // Summoning Scrolls
    // -------------------------------------------------------------------------

    // Beast
    public static final RegistryObject<SummoningScroll> SCROLL_WARG         = ITEMS.register("scroll_warg",         () -> new SummoningScroll("warg"));
    public static final RegistryObject<SummoningScroll> SCROLL_MAKA         = ITEMS.register("scroll_maka",         () -> new SummoningScroll("maka"));
    public static final RegistryObject<SummoningScroll> SCROLL_BARGHEST     = ITEMS.register("scroll_barghest",     () -> new SummoningScroll("barghest"));
    public static final RegistryObject<SummoningScroll> SCROLL_DAWON        = ITEMS.register("scroll_dawon",        () -> new SummoningScroll("dawon"));
    public static final RegistryObject<SummoningScroll> SCROLL_CHUPACABRA   = ITEMS.register("scroll_chupacabra",   () -> new SummoningScroll("chupacabra"));
    public static final RegistryObject<SummoningScroll> SCROLL_UVARAPTOR    = ITEMS.register("scroll_uvaraptor",    () -> new SummoningScroll("uvaraptor"));
    public static final RegistryObject<SummoningScroll> SCROLL_VENTORAPTOR  = ITEMS.register("scroll_ventoraptor",  () -> new SummoningScroll("ventoraptor"));
    // Avian
    public static final RegistryObject<SummoningScroll> SCROLL_ROC          = ITEMS.register("scroll_roc",          () -> new SummoningScroll("roc"));
    public static final RegistryObject<SummoningScroll> SCROLL_RAIKO        = ITEMS.register("scroll_raiko",        () -> new SummoningScroll("raiko"));
    public static final RegistryObject<SummoningScroll> SCROLL_BALAYANG     = ITEMS.register("scroll_balayang",     () -> new SummoningScroll("balayang"));
    public static final RegistryObject<SummoningScroll> SCROLL_BOBEKO       = ITEMS.register("scroll_bobeko",       () -> new SummoningScroll("bobeko"));
    public static final RegistryObject<SummoningScroll> SCROLL_MAUG         = ITEMS.register("scroll_maug",         () -> new SummoningScroll("maug"));
    public static final RegistryObject<SummoningScroll> SCROLL_WRAAMON      = ITEMS.register("scroll_wraamon",      () -> new SummoningScroll("wraamon"));
    // Insect / Arthropod
    public static final RegistryObject<SummoningScroll> SCROLL_JOUSTE       = ITEMS.register("scroll_jouste",       () -> new SummoningScroll("jouste"));
    public static final RegistryObject<SummoningScroll> SCROLL_VESPID       = ITEMS.register("scroll_vespid",       () -> new SummoningScroll("vespid"));
    public static final RegistryObject<SummoningScroll> SCROLL_BRUCHA       = ITEMS.register("scroll_brucha",       () -> new SummoningScroll("brucha"));
    public static final RegistryObject<SummoningScroll> SCROLL_EPION        = ITEMS.register("scroll_epion",        () -> new SummoningScroll("epion"));
    public static final RegistryObject<SummoningScroll> SCROLL_FERADON      = ITEMS.register("scroll_feradon",      () -> new SummoningScroll("feradon"));
    public static final RegistryObject<SummoningScroll> SCROLL_ARISAUR      = ITEMS.register("scroll_arisaur",      () -> new SummoningScroll("arisaur"));
    public static final RegistryObject<SummoningScroll> SCROLL_ASPID        = ITEMS.register("scroll_aspid",        () -> new SummoningScroll("aspid"));
    public static final RegistryObject<SummoningScroll> SCROLL_GEKEN        = ITEMS.register("scroll_geken",        () -> new SummoningScroll("geken"));
    public static final RegistryObject<SummoningScroll> SCROLL_KHALK        = ITEMS.register("scroll_khalk",        () -> new SummoningScroll("khalk"));
    public static final RegistryObject<SummoningScroll> SCROLL_THRESHER     = ITEMS.register("scroll_thresher",     () -> new SummoningScroll("thresher"));
    // Arachnid
    public static final RegistryObject<SummoningScroll> SCROLL_FROSTWEAVER  = ITEMS.register("scroll_frostweaver",  () -> new SummoningScroll("frostweaver"));
    public static final RegistryObject<SummoningScroll> SCROLL_LYCOSA       = ITEMS.register("scroll_lycosa",       () -> new SummoningScroll("lycosa"));
    public static final RegistryObject<SummoningScroll> SCROLL_SUTIRAMU     = ITEMS.register("scroll_sutiramu",     () -> new SummoningScroll("sutiramu"));
    // Aquatic
    public static final RegistryObject<SummoningScroll> SCROLL_IORAY        = ITEMS.register("scroll_ioray",        () -> new SummoningScroll("ioray"));
    public static final RegistryObject<SummoningScroll> SCROLL_KRAKE        = ITEMS.register("scroll_krake",        () -> new SummoningScroll("krake"));
    public static final RegistryObject<SummoningScroll> SCROLL_ABAIA        = ITEMS.register("scroll_abaia",        () -> new SummoningScroll("abaia"));
    public static final RegistryObject<SummoningScroll> SCROLL_ABTU         = ITEMS.register("scroll_abtu",         () -> new SummoningScroll("abtu"));
    public static final RegistryObject<SummoningScroll> SCROLL_CEPHIGNIS    = ITEMS.register("scroll_cephignis",    () -> new SummoningScroll("cephignis"));
    public static final RegistryObject<SummoningScroll> SCROLL_HERMA        = ITEMS.register("scroll_herma",        () -> new SummoningScroll("herma"));
    public static final RegistryObject<SummoningScroll> SCROLL_ROA          = ITEMS.register("scroll_roa",          () -> new SummoningScroll("roa"));
    public static final RegistryObject<SummoningScroll> SCROLL_SILEX        = ITEMS.register("scroll_silex",        () -> new SummoningScroll("silex"));
    public static final RegistryObject<SummoningScroll> SCROLL_SKYLUS       = ITEMS.register("scroll_skylus",       () -> new SummoningScroll("skylus"));
    public static final RegistryObject<SummoningScroll> SCROLL_STRYDER      = ITEMS.register("scroll_stryder",      () -> new SummoningScroll("stryder"));
    // Amphibian
    public static final RegistryObject<SummoningScroll> SCROLL_SALAMANDER   = ITEMS.register("scroll_salamander",   () -> new SummoningScroll("salamander"));
    public static final RegistryObject<SummoningScroll> SCROLL_AGLEBEMU     = ITEMS.register("scroll_aglebemu",     () -> new SummoningScroll("aglebemu"));
    public static final RegistryObject<SummoningScroll> SCROLL_NINGEN       = ITEMS.register("scroll_ningen",       () -> new SummoningScroll("ningen"));
    // Aberration
    public static final RegistryObject<SummoningScroll> SCROLL_YALE         = ITEMS.register("scroll_yale",         () -> new SummoningScroll("yale"));
    public static final RegistryObject<SummoningScroll> SCROLL_WILDKIN      = ITEMS.register("scroll_wildkin",      () -> new SummoningScroll("wildkin"));
    // Elemental
    public static final RegistryObject<SummoningScroll> SCROLL_SPRIGGAN     = ITEMS.register("scroll_spriggan",     () -> new SummoningScroll("spriggan"));
    public static final RegistryObject<SummoningScroll> SCROLL_ARGUS        = ITEMS.register("scroll_argus",        () -> new SummoningScroll("argus"));
    // Dragon
    public static final RegistryObject<SummoningScroll> SCROLL_REMOBRA      = ITEMS.register("scroll_remobra",      () -> new SummoningScroll("remobra"));
    // Demon
    public static final RegistryObject<SummoningScroll> SCROLL_KOBOLD       = ITEMS.register("scroll_kobold",       () -> new SummoningScroll("kobold"));
    public static final RegistryObject<SummoningScroll> SCROLL_ETTIN        = ITEMS.register("scroll_ettin",        () -> new SummoningScroll("ettin"));
    public static final RegistryObject<SummoningScroll> SCROLL_TROLL        = ITEMS.register("scroll_troll",        () -> new SummoningScroll("troll"));
    public static final RegistryObject<SummoningScroll> SCROLL_CRUSK        = ITEMS.register("scroll_crusk",        () -> new SummoningScroll("crusk"));
    public static final RegistryObject<SummoningScroll> SCROLL_IKA          = ITEMS.register("scroll_ika",          () -> new SummoningScroll("ika"));
    public static final RegistryObject<SummoningScroll> SCROLL_SERPIX       = ITEMS.register("scroll_serpix",       () -> new SummoningScroll("serpix"));
    public static final RegistryObject<SummoningScroll> SCROLL_UMIBAS       = ITEMS.register("scroll_umibas",       () -> new SummoningScroll("umibas"));
    // Undead
    public static final RegistryObject<SummoningScroll> SCROLL_REAPER       = ITEMS.register("scroll_reaper",       () -> new SummoningScroll("reaper"));
    public static final RegistryObject<SummoningScroll> SCROLL_CRYPTKEEPER  = ITEMS.register("scroll_cryptkeeper",  () -> new SummoningScroll("cryptkeeper"));
    public static final RegistryObject<SummoningScroll> SCROLL_GEIST        = ITEMS.register("scroll_geist",        () -> new SummoningScroll("geist"));
    public static final RegistryObject<SummoningScroll> SCROLL_GHOUL        = ITEMS.register("scroll_ghoul",        () -> new SummoningScroll("ghoul"));
    public static final RegistryObject<SummoningScroll> SCROLL_NECROVORE    = ITEMS.register("scroll_necrovore",    () -> new SummoningScroll("necrovore"));

    // -------------------------------------------------------------------------
    // Corrupted Summoning Staffs (netherite tier)
    // -------------------------------------------------------------------------

    public static final RegistryObject<CorruptedSummoningStaff>       CORRUPTED_SUMMONING_STAFF        = ITEMS.register("corrupted_summoning_staff",        CorruptedSummoningStaff::new);
    public static final RegistryObject<CorruptedSturdySummoningStaff> CORRUPTED_STURDY_SUMMONING_STAFF = ITEMS.register("corrupted_sturdy_summoning_staff",  CorruptedSturdySummoningStaff::new);
    public static final RegistryObject<CorruptedStableSummoningStaff> CORRUPTED_STABLE_SUMMONING_STAFF = ITEMS.register("corrupted_stable_summoning_staff",  CorruptedStableSummoningStaff::new);
    public static final RegistryObject<CorruptedSavageSummoningStaff> CORRUPTED_SAVAGE_SUMMONING_STAFF = ITEMS.register("corrupted_savage_summoning_staff",  CorruptedSavageSummoningStaff::new);
    public static final RegistryObject<CorruptedBloodSummoningStaff>  CORRUPTED_BLOOD_SUMMONING_STAFF  = ITEMS.register("corrupted_blood_summoning_staff",   CorruptedBloodSummoningStaff::new);

    // -------------------------------------------------------------------------
    // Netherite Soulgazer
    // -------------------------------------------------------------------------

    public static final RegistryObject<NetheriteSoulgazer> NETHERITE_SOULGAZER = ITEMS.register("netherite_soulgazer", NetheriteSoulgazer::new);

    // -------------------------------------------------------------------------
    // Elemental treat (no treat_elemental exists in this Lycanites build)
    // -------------------------------------------------------------------------

    public static final RegistryObject<Item> TREAT_ELEMENTAL = ITEMS.register("treat_elemental",
            () -> new Item(new Item.Properties().stacksTo(16)));
}
