package com.glennfo.lycaniteskin;

import com.glennfo.lycaniteskin.init.ModCreativeTabs;
import com.glennfo.lycaniteskin.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LycanitesKin.MOD_ID)
public class LycanitesKin {

    public static final String MOD_ID = "lycaniteskin";

    public LycanitesKin(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();
        ModItems.ITEMS.register(bus);
        ModCreativeTabs.TABS.register(bus);
        bus.addListener(ModCreativeTabs::addContents);
    }
}
