package com.glennfo.lycaniteskin.client;

import com.glennfo.lycaniteskin.init.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "lycaniteskin", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            registerUsing(ModItems.CORRUPTED_SUMMONING_STAFF.get());
            registerUsing(ModItems.CORRUPTED_STURDY_SUMMONING_STAFF.get());
            registerUsing(ModItems.CORRUPTED_STABLE_SUMMONING_STAFF.get());
            registerUsing(ModItems.CORRUPTED_SAVAGE_SUMMONING_STAFF.get());
            registerUsing(ModItems.CORRUPTED_BLOOD_SUMMONING_STAFF.get());
        });
    }

    private static void registerUsing(Item item) {
        ItemProperties.register(item, ResourceLocation.parse("using"),
            (stack, level, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getUseItem().getItem() == item ? 1.0F : 0.0F);
    }
}
