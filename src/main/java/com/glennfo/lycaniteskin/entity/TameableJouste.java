package com.glennfo.lycaniteskin.entity;

import com.lycanitesmobs.core.block.Material;
import com.lycanitesmobs.core.data.info.creature.CreatureInfo;
import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import com.lycanitesmobs.core.entity.goals.actions.TemptGoal;
import com.lycanitesmobs.core.entity.goals.targeting.CopyMasterAttackTargetGoal;
import com.lycanitesmobs.core.entity.goals.targeting.FindMasterGoal;
import com.lycanitesmobs.core.manager.CreatureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class TameableJouste extends TameableCreatureEntity {

    public TameableJouste(EntityType<? extends TameableJouste> type, Level level) {
        super(type, level);
        this.attribute = MobType.UNDEFINED;
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
            if (mgr != null) this.creatureInfo = mgr.getCreature("jouste");
        }
    }

    @Override
    protected void m_8099_() {
        super.m_8099_();
        this.goalSelector.addGoal(nextDistractionGoalIndex++, new TemptGoal(this).setIncludeDiet(true));
        this.goalSelector.addGoal(nextCombatGoalIndex++, new AttackMeleeGoal(this).setLongMemory(false));
        this.targetSelector.addGoal(nextFindTargetIndex++,
            new FindMasterGoal(this).setTargetClass(com.lycanitesmobs.core.entity.creature.insect.EntityJousteAlpha.class).setSightCheck(false));
        this.targetSelector.addGoal(nextFindTargetIndex++, new CopyMasterAttackTargetGoal(this));
    }

    @Override
    public void onFirstSpawn() {
        CreatureInfo alphaInfo = CreatureManager.getInstance() != null ? CreatureManager.getInstance().getCreature("joustealpha") : null;
        if (alphaInfo != null && this.creatureInfo != null) {
            float ratio = alphaInfo.getCreatureSpawn().getSpawnWeight() / (float) Math.max(this.creatureInfo.getCreatureSpawn().getSpawnWeight(), 1);
            if (this.getRandom().nextFloat() <= ratio) {
                LivingEntity alpha = alphaInfo.createEntity(this.level());
                if (alpha != null) {
                    alpha.startRiding(this);
                    this.level().addFreshEntity(alpha);
                }
            }
        }
        super.onFirstSpawn();
    }

    @Override
    public int getNoBagSize() { return 0; }

    @Override
    public int getBagSize() { return this.creatureInfo != null ? this.creatureInfo.getBagSize() : 0; }

    @Override
    public float getBlockPathWeight(int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y - 1, z);
        BlockState below = this.level().getBlockState(pos);
        if (below.getBlock() == Blocks.SAND || Material.SAND.contains(below.getBlock())) return 10.0f;
        if (Material.CLAY != null && Material.CLAY.contains(below.getBlock())) return 7.0f;
        if (Material.STONE != null && Material.STONE.contains(below.getBlock())) return 5.0f;
        return super.getBlockPathWeight(x, y, z);
    }
}
