package com.glennfo.lycaniteskin.entity;

import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import com.lycanitesmobs.core.entity.goals.actions.TemptGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class TameableKrake extends TameableCreatureEntity {

    public TameableKrake(EntityType<? extends TameableKrake> type, Level level) {
        super(type, level);
        this.attribute = MobType.UNDEFINED;
        this.spawnsOnLand = true;
        this.spawnsInWater = true;
        this.hasAttackSound = true;
        this.babySpawnChance = 0.1;
        this.canGrow = true;
        this.attackCooldownMax = 10;
        this.setupMob();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (this.creatureInfo == null) {
            com.lycanitesmobs.core.manager.CreatureManager mgr = com.lycanitesmobs.core.manager.CreatureManager.getInstance();
            if (mgr != null) this.creatureInfo = mgr.getCreature("krake");
        }
    }

    @Override
    protected void m_8099_() {
        super.m_8099_();
        this.goalSelector.addGoal(nextDistractionGoalIndex++, new TemptGoal(this).setIncludeDiet(true));
        this.goalSelector.addGoal(nextCombatGoalIndex++, new AttackMeleeGoal(this).setLongMemory(false).setRange(1.0));
    }

    @Override
    public float getAISpeedModifier() {
        if (this.isUnderWater()) return 1.5f;
        if (this.waterContact()) return 1.25f;
        return super.getAISpeedModifier();
    }

    @Override
    public float getBlockPathWeight(int x, int y, int z) {
        int bonus = 10;
        BlockPos pos = new BlockPos(x, y, z);
        BlockState state = this.level().getBlockState(pos);
        if (state.getBlock() == Blocks.WATER) {
            return (super.getBlockPathWeight(x, y, z) + 1) * (bonus + 1);
        }
        if (this.level().isRaining() && this.level().isRainingAt(pos)) {
            return (super.getBlockPathWeight(x, y, z) + 1) * (bonus + 1);
        }
        return super.getBlockPathWeight(x, y, z);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    @Override
    public int getNoBagSize() { return 0; }

    @Override
    public int getBagSize() { return this.creatureInfo != null ? this.creatureInfo.getBagSize() : 0; }

    @Override
    public BlockPos getWanderPosition(BlockPos pos) {
        BlockPos below = pos.below();
        while (below.getY() > 0 && this.level().getFluidState(below).isEmpty()) {
            below = below.below();
        }
        return below.above();
    }

    @Override
    public boolean isPushedByFluid() {
        return true;
    }
}
