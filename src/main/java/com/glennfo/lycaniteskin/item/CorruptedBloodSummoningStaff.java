package com.glennfo.lycaniteskin.item;

import com.lycanitesmobs.core.item.summoningstaff.ItemStaffBlood;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class CorruptedBloodSummoningStaff extends ItemStaffBlood {

    public CorruptedBloodSummoningStaff() {
        super(new Item.Properties().stacksTo(1).durability(1000),
                "corrupted_blood_summoning_staff", "corrupted_blood_summoning_staff");
        this.damageScale = 1.5f;
    }

    @Override
    public String getDescriptionId() { return "item.lycaniteskin.corrupted_blood_summoning_staff"; }

    @Override
    public Component getDescription(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) { return Component.empty(); }

    @Override
    public int getSummonDuration() { return 1800; }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repair) {
        return repair.is(Items.NETHERITE_INGOT) || super.isValidRepairItem(stack, repair);
    }
}
