package com.glennfo.lycaniteskin.entity;

import com.lycanitesmobs.core.data.info.item.ItemDrop;
import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import com.lycanitesmobs.core.entity.goals.actions.EatBlockGoal;
import com.lycanitesmobs.core.entity.goals.actions.TemptGoal;
import com.lycanitesmobs.core.block.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IForgeShearable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class TameableYale extends TameableCreatureEntity implements IForgeShearable {

    private static final EntityDataAccessor<Byte> FUR =
        SynchedEntityData.defineId(TameableYale.class, EntityDataSerializers.BYTE);

    private static final Map<DyeColor, ItemLike> WOOL_BY_COLOR = new EnumMap<>(DyeColor.class);

    static {
        WOOL_BY_COLOR.put(DyeColor.WHITE,      Blocks.WHITE_WOOL);
        WOOL_BY_COLOR.put(DyeColor.ORANGE,     Blocks.ORANGE_WOOL);
        WOOL_BY_COLOR.put(DyeColor.MAGENTA,    Blocks.MAGENTA_WOOL);
        WOOL_BY_COLOR.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
        WOOL_BY_COLOR.put(DyeColor.YELLOW,     Blocks.YELLOW_WOOL);
        WOOL_BY_COLOR.put(DyeColor.LIME,       Blocks.LIME_WOOL);
        WOOL_BY_COLOR.put(DyeColor.PINK,       Blocks.PINK_WOOL);
        WOOL_BY_COLOR.put(DyeColor.GRAY,       Blocks.GRAY_WOOL);
        WOOL_BY_COLOR.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
        WOOL_BY_COLOR.put(DyeColor.CYAN,       Blocks.CYAN_WOOL);
        WOOL_BY_COLOR.put(DyeColor.PURPLE,     Blocks.PURPLE_WOOL);
        WOOL_BY_COLOR.put(DyeColor.BLUE,       Blocks.BLUE_WOOL);
        WOOL_BY_COLOR.put(DyeColor.BROWN,      Blocks.BROWN_WOOL);
        WOOL_BY_COLOR.put(DyeColor.GREEN,      Blocks.GREEN_WOOL);
        WOOL_BY_COLOR.put(DyeColor.RED,        Blocks.RED_WOOL);
        WOOL_BY_COLOR.put(DyeColor.BLACK,      Blocks.BLACK_WOOL);
    }

    protected ItemDrop woolDrop;

    public TameableYale(EntityType<? extends TameableYale> type, Level level) {
        super(type, level);
        this.attribute = MobType.UNDEFINED;
        this.hasAttackSound = false;
        this.canGrow = true;
        this.babySpawnChance = 0.1;
        this.fleeHealthPercent = 1.0f;
        this.isAggressiveByDefault = false;
        this.setupMob();
        this.woolDrop = new ItemDrop(
            Blocks.WHITE_WOOL.builtInRegistryHolder().key().location().toString(), 1.0f)
            .setMinAmount(1).setMaxAmount(3);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (this.creatureInfo == null) {
            com.lycanitesmobs.core.manager.CreatureManager mgr = com.lycanitesmobs.core.manager.CreatureManager.getInstance();
            if (mgr != null) this.creatureInfo = mgr.getCreature("yale");
        }
        this.entityData.define(FUR, (byte) 1);
    }

    @Override
    protected void m_8099_() {
        this.goalSelector.addGoal(nextIdleGoalIndex++,
            new EatBlockGoal(this)
                .setBlocks(new net.minecraft.world.level.block.Block[]{ Blocks.GRASS_BLOCK })
                .setReplaceBlock(Blocks.DIRT));
        super.m_8099_();
        this.goalSelector.addGoal(nextDistractionGoalIndex++, new TemptGoal(this).setIncludeDiet(true));
        this.goalSelector.addGoal(nextCombatGoalIndex++, new AttackMeleeGoal(this).setLongMemory(false));
    }

    @Override
    public void onFirstSpawn() {
        if (!this.isBaby()) {
            this.setColor(this.getRandomFurColor(this.getRandom()));
        }
        super.onFirstSpawn();
    }

    @Override
    public boolean isShearable(ItemStack stack, Level level, BlockPos pos) {
        return this.hasFur() && !this.isBaby();
    }

    @Override
    public ArrayList<ItemStack> onSheared(Player player, ItemStack stack, Level level, BlockPos pos, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<>();
        if (this.woolDrop == null) return drops;
        this.setFur(false);
        this.playSound(SoundEvents.SHEEP_SHEAR, 1.0f, 1.0f);
        int quantity = this.woolDrop.getQuantity(this.getRandom(), fortune, 1);
        ItemStack drop = this.woolDrop.getEntityDropItemStack(this, quantity);
        if (drop != null && drop.getItem() instanceof BlockItem bi) {
            String blockName = bi.getBlock().builtInRegistryHolder().key().location().toString();
            if (blockName.contains("_wool")) {
                drop = new ItemStack((ItemLike) WOOL_BY_COLOR.get(this.getColor()), drop.getCount());
            }
        }
        if (drop != null) drops.add(drop);
        return drops;
    }

    public boolean hasFur() {
        if (this.entityData == null) return true;
        return this.entityData.get(FUR) > 0;
    }

    public void setFur(boolean hasFur) {
        if (this.level().isClientSide()) return;
        this.entityData.set(FUR, hasFur ? (byte) 1 : (byte) 0);
    }

    @Override
    public void onEat() {
        if (!this.level().isClientSide()) {
            this.setFur(true);
        }
    }

    public boolean canBeColored(Player player) {
        return true;
    }

    @Override
    public void setColor(DyeColor color) {
        ItemLike woolItem = WOOL_BY_COLOR.get(color);
        if (woolItem == null) woolItem = Blocks.WHITE_WOOL;
        if (this.woolDrop == null) {
            this.woolDrop = new ItemDrop(
                ((net.minecraft.world.level.block.Block) woolItem).builtInRegistryHolder().key().location().toString(), 1.0f)
                .setMinAmount(1).setMaxAmount(3);
        } else if (this.woolDrop.getItemStack().getItem() != woolItem.asItem()) {
            this.woolDrop.setDrop(new ItemStack(woolItem, 1));
        }
        super.setColor(color);
    }

    public DyeColor getRandomFurColor(net.minecraft.util.RandomSource random) {
        int roll = random.nextInt(100);
        if (roll < 5)  return DyeColor.BLACK;
        if (roll < 10) return DyeColor.GREEN;
        if (roll < 15) return DyeColor.RED;
        if (roll < 18) return DyeColor.BROWN;
        return random.nextInt(500) == 0 ? DyeColor.PINK : DyeColor.WHITE;
    }

    @Override
    public int getNoBagSize() { return 0; }

    @Override
    public int getBagSize() { return this.creatureInfo != null ? this.creatureInfo.getBagSize() : 0; }

    @Override
    public float getBlockPathWeight(int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y - 1, z);
        BlockState below = this.level().getBlockState(pos);
        if (Material.GRASS != null && Material.GRASS.contains(below.getBlock())) return 10.0f;
        if (Material.DIRT != null && Material.DIRT.contains(below.getBlock())) return 7.0f;
        return super.getBlockPathWeight(x, y, z);
    }
}
