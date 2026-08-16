package com.glennfo.lycaniteskin.item;

import com.lycanitesmobs.core.capabilities.entity.ExtendedPlayer;
import com.lycanitesmobs.core.item.special.ItemSoulgazer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class NetheriteSoulgazer extends ItemSoulgazer {

    public NetheriteSoulgazer() {
        super(new Item.Properties().stacksTo(1));
        this.itemName = "netherite_soulgazer";
        this.setup();
    }

    @Override
    public void setup() {
        setRegistryName("lycanitesmobs", this.itemName != null ? this.itemName : "netherite_soulgazer");
    }

    @Override
    public String getDescriptionId() { return "item.lycaniteskin.netherite_soulgazer"; }

    @Override
    public Component getDescription(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) { return Component.empty(); }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        InteractionResult result = super.interactLivingEntity(stack, player, target, hand);
        if (result == InteractionResult.SUCCESS && !player.level().isClientSide()) {
            ExtendedPlayer ext = ExtendedPlayer.getForPlayer(player);
            if (ext != null) {
                int reducedCooldown = (int)(ext.getCreatureStudyCooldown() * 0.75f);
                ext.applyNetworkStats(ext.getSpirit(), ext.getSummonFocus(), reducedCooldown);
            }
        }
        return result;
    }
}
