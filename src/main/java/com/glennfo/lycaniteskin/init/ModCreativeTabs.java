package com.glennfo.lycaniteskin.init;

import com.glennfo.lycaniteskin.LycanitesKin;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LycanitesKin.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LYCANITES_ADDON = TABS.register(
            "lycanites_addon_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.literal("Lycanites Kin"))
                    .icon(() -> new ItemStack(ModItems.ROC_FEATHER.get()))
                    .displayItems((params, output) -> {})
                    .build()
    );

    public static void addContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() != LYCANITES_ADDON.get()) return;

        // Drop materials
        event.accept(ModItems.WARG_PELT.get());
        event.accept(ModItems.VESPID_CHITIN.get());
        event.accept(ModItems.MAKA_HIDE.get());
        event.accept(ModItems.ROC_FEATHER.get());
        event.accept(ModItems.REAPER_BONE.get());
        event.accept(ModItems.FROSTWEAVER_SILK.get());
        event.accept(ModItems.REMOBRA_SCALE.get());
        event.accept(ModItems.JOUSTE_SHELL.get());
        event.accept(ModItems.KRAKE_SHELL.get());
        event.accept(ModItems.SPRIGGAN_BARK.get());
        event.accept(ModItems.ARGUS_SCALE.get());
        event.accept(ModItems.BARGHEST_PELT.get());
        event.accept(ModItems.DAWON_PELT.get());
        event.accept(ModItems.SALAMANDER_SCALE.get());
        event.accept(ModItems.YALE_HIDE.get());
        event.accept(ModItems.RAIKO_FEATHER.get());
        event.accept(ModItems.IORAY_MEMBRANE.get());

        // Warg Pelt armor
        event.accept(ModItems.WARG_PELT_HELMET.get());
        event.accept(ModItems.WARG_PELT_CHESTPLATE.get());
        event.accept(ModItems.WARG_PELT_LEGGINGS.get());
        event.accept(ModItems.WARG_PELT_BOOTS.get());

        // Vespid Chitin armor
        event.accept(ModItems.VESPID_CHITIN_HELMET.get());
        event.accept(ModItems.VESPID_CHITIN_CHESTPLATE.get());
        event.accept(ModItems.VESPID_CHITIN_LEGGINGS.get());
        event.accept(ModItems.VESPID_CHITIN_BOOTS.get());

        // Maka Hide armor
        event.accept(ModItems.MAKA_HIDE_HELMET.get());
        event.accept(ModItems.MAKA_HIDE_CHESTPLATE.get());
        event.accept(ModItems.MAKA_HIDE_LEGGINGS.get());
        event.accept(ModItems.MAKA_HIDE_BOOTS.get());

        // Roc Feather armor
        event.accept(ModItems.ROC_FEATHER_HELMET.get());
        event.accept(ModItems.ROC_FEATHER_CHESTPLATE.get());
        event.accept(ModItems.ROC_FEATHER_LEGGINGS.get());
        event.accept(ModItems.ROC_FEATHER_BOOTS.get());

        // Reaper Bone armor
        event.accept(ModItems.REAPER_BONE_HELMET.get());
        event.accept(ModItems.REAPER_BONE_CHESTPLATE.get());
        event.accept(ModItems.REAPER_BONE_LEGGINGS.get());
        event.accept(ModItems.REAPER_BONE_BOOTS.get());

        // Frostweaver Silk armor
        event.accept(ModItems.FROSTWEAVER_SILK_HELMET.get());
        event.accept(ModItems.FROSTWEAVER_SILK_CHESTPLATE.get());
        event.accept(ModItems.FROSTWEAVER_SILK_LEGGINGS.get());
        event.accept(ModItems.FROSTWEAVER_SILK_BOOTS.get());

        // Remobra Scale armor
        event.accept(ModItems.REMOBRA_SCALE_HELMET.get());
        event.accept(ModItems.REMOBRA_SCALE_CHESTPLATE.get());
        event.accept(ModItems.REMOBRA_SCALE_LEGGINGS.get());
        event.accept(ModItems.REMOBRA_SCALE_BOOTS.get());

        // Jouste Shell armor
        event.accept(ModItems.JOUSTE_SHELL_HELMET.get());
        event.accept(ModItems.JOUSTE_SHELL_CHESTPLATE.get());
        event.accept(ModItems.JOUSTE_SHELL_LEGGINGS.get());
        event.accept(ModItems.JOUSTE_SHELL_BOOTS.get());

        // Krake Shell armor
        event.accept(ModItems.KRAKE_SHELL_HELMET.get());
        event.accept(ModItems.KRAKE_SHELL_CHESTPLATE.get());
        event.accept(ModItems.KRAKE_SHELL_LEGGINGS.get());
        event.accept(ModItems.KRAKE_SHELL_BOOTS.get());

        // Spriggan Bark armor
        event.accept(ModItems.SPRIGGAN_BARK_HELMET.get());
        event.accept(ModItems.SPRIGGAN_BARK_CHESTPLATE.get());
        event.accept(ModItems.SPRIGGAN_BARK_LEGGINGS.get());
        event.accept(ModItems.SPRIGGAN_BARK_BOOTS.get());

        // Argus Scale armor
        event.accept(ModItems.ARGUS_SCALE_HELMET.get());
        event.accept(ModItems.ARGUS_SCALE_CHESTPLATE.get());
        event.accept(ModItems.ARGUS_SCALE_LEGGINGS.get());
        event.accept(ModItems.ARGUS_SCALE_BOOTS.get());

        // Barghest Pelt armor
        event.accept(ModItems.BARGHEST_PELT_HELMET.get());
        event.accept(ModItems.BARGHEST_PELT_CHESTPLATE.get());
        event.accept(ModItems.BARGHEST_PELT_LEGGINGS.get());
        event.accept(ModItems.BARGHEST_PELT_BOOTS.get());

        // Dawon Pelt armor
        event.accept(ModItems.DAWON_PELT_HELMET.get());
        event.accept(ModItems.DAWON_PELT_CHESTPLATE.get());
        event.accept(ModItems.DAWON_PELT_LEGGINGS.get());
        event.accept(ModItems.DAWON_PELT_BOOTS.get());

        // Salamander Scale armor
        event.accept(ModItems.SALAMANDER_SCALE_HELMET.get());
        event.accept(ModItems.SALAMANDER_SCALE_CHESTPLATE.get());
        event.accept(ModItems.SALAMANDER_SCALE_LEGGINGS.get());
        event.accept(ModItems.SALAMANDER_SCALE_BOOTS.get());

        // Yale Hide armor
        event.accept(ModItems.YALE_HIDE_HELMET.get());
        event.accept(ModItems.YALE_HIDE_CHESTPLATE.get());
        event.accept(ModItems.YALE_HIDE_LEGGINGS.get());
        event.accept(ModItems.YALE_HIDE_BOOTS.get());

        // Raiko Feather armor
        event.accept(ModItems.RAIKO_FEATHER_HELMET.get());
        event.accept(ModItems.RAIKO_FEATHER_CHESTPLATE.get());
        event.accept(ModItems.RAIKO_FEATHER_LEGGINGS.get());
        event.accept(ModItems.RAIKO_FEATHER_BOOTS.get());

        // Ioray Membrane armor
        event.accept(ModItems.IORAY_MEMBRANE_HELMET.get());
        event.accept(ModItems.IORAY_MEMBRANE_CHESTPLATE.get());
        event.accept(ModItems.IORAY_MEMBRANE_LEGGINGS.get());
        event.accept(ModItems.IORAY_MEMBRANE_BOOTS.get());

        // Netherite Soulgazer
        event.accept(ModItems.NETHERITE_SOULGAZER.get());

        // Corrupted Summoning Staffs
        event.accept(ModItems.CORRUPTED_SUMMONING_STAFF.get());
        event.accept(ModItems.CORRUPTED_STURDY_SUMMONING_STAFF.get());
        event.accept(ModItems.CORRUPTED_STABLE_SUMMONING_STAFF.get());
        event.accept(ModItems.CORRUPTED_SAVAGE_SUMMONING_STAFF.get());
        event.accept(ModItems.CORRUPTED_BLOOD_SUMMONING_STAFF.get());

        // Elemental Treat
        event.accept(ModItems.TREAT_ELEMENTAL.get());

        // Summoning Scrolls — Beast
        event.accept(ModItems.SCROLL_WARG.get());
        event.accept(ModItems.SCROLL_MAKA.get());
        event.accept(ModItems.SCROLL_BARGHEST.get());
        event.accept(ModItems.SCROLL_DAWON.get());
        event.accept(ModItems.SCROLL_CHUPACABRA.get());
        event.accept(ModItems.SCROLL_UVARAPTOR.get());
        event.accept(ModItems.SCROLL_VENTORAPTOR.get());
        // Avian
        event.accept(ModItems.SCROLL_ROC.get());
        event.accept(ModItems.SCROLL_RAIKO.get());
        event.accept(ModItems.SCROLL_BALAYANG.get());
        event.accept(ModItems.SCROLL_BOBEKO.get());
        event.accept(ModItems.SCROLL_MAUG.get());
        event.accept(ModItems.SCROLL_WRAAMON.get());
        // Insect / Arthropod
        event.accept(ModItems.SCROLL_JOUSTE.get());
        event.accept(ModItems.SCROLL_VESPID.get());
        event.accept(ModItems.SCROLL_BRUCHA.get());
        event.accept(ModItems.SCROLL_EPION.get());
        event.accept(ModItems.SCROLL_FERADON.get());
        event.accept(ModItems.SCROLL_ARISAUR.get());
        event.accept(ModItems.SCROLL_ASPID.get());
        event.accept(ModItems.SCROLL_GEKEN.get());
        event.accept(ModItems.SCROLL_KHALK.get());
        event.accept(ModItems.SCROLL_THRESHER.get());
        // Arachnid
        event.accept(ModItems.SCROLL_FROSTWEAVER.get());
        event.accept(ModItems.SCROLL_LYCOSA.get());
        event.accept(ModItems.SCROLL_SUTIRAMU.get());
        // Aquatic
        event.accept(ModItems.SCROLL_IORAY.get());
        event.accept(ModItems.SCROLL_KRAKE.get());
        event.accept(ModItems.SCROLL_ABAIA.get());
        event.accept(ModItems.SCROLL_ABTU.get());
        event.accept(ModItems.SCROLL_CEPHIGNIS.get());
        event.accept(ModItems.SCROLL_HERMA.get());
        event.accept(ModItems.SCROLL_ROA.get());
        event.accept(ModItems.SCROLL_SILEX.get());
        event.accept(ModItems.SCROLL_SKYLUS.get());
        event.accept(ModItems.SCROLL_STRYDER.get());
        // Amphibian
        event.accept(ModItems.SCROLL_SALAMANDER.get());
        event.accept(ModItems.SCROLL_AGLEBEMU.get());
        event.accept(ModItems.SCROLL_NINGEN.get());
        // Aberration
        event.accept(ModItems.SCROLL_YALE.get());
        event.accept(ModItems.SCROLL_WILDKIN.get());
        // Elemental
        event.accept(ModItems.SCROLL_SPRIGGAN.get());
        event.accept(ModItems.SCROLL_ARGUS.get());
        // Dragon
        event.accept(ModItems.SCROLL_REMOBRA.get());
        // Demon
        event.accept(ModItems.SCROLL_KOBOLD.get());
        event.accept(ModItems.SCROLL_ETTIN.get());
        event.accept(ModItems.SCROLL_TROLL.get());
        event.accept(ModItems.SCROLL_CRUSK.get());
        event.accept(ModItems.SCROLL_IKA.get());
        event.accept(ModItems.SCROLL_SERPIX.get());
        event.accept(ModItems.SCROLL_UMIBAS.get());
        // Undead
        event.accept(ModItems.SCROLL_REAPER.get());
        event.accept(ModItems.SCROLL_CRYPTKEEPER.get());
        event.accept(ModItems.SCROLL_GEIST.get());
        event.accept(ModItems.SCROLL_GHOUL.get());
        event.accept(ModItems.SCROLL_NECROVORE.get());
    }
}
