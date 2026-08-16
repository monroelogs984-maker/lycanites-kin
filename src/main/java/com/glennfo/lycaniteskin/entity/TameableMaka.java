package com.glennfo.lycaniteskin.entity;

import com.lycanitesmobs.core.data.info.creature.CreatureInfo;
import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import com.lycanitesmobs.core.entity.goals.actions.TemptGoal;
import com.lycanitesmobs.core.entity.goals.targeting.CopyMasterAttackTargetGoal;
import com.lycanitesmobs.core.entity.goals.targeting.FindMasterGoal;
import com.lycanitesmobs.core.manager.CreatureManager;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;

public class TameableMaka extends TameableCreatureEntity {

    public TameableMaka(EntityType<? extends TameableMaka> type, Level level) {
        super(type, level);
        this.attribute = MobType.UNDEFINED;
        this.hasAttackSound = true;
        this.babySpawnChance = 0.1;
        this.canGrow = true;
        this.attackCooldownMax = 10;
        this.fleeHealthPercent = 1.0f;
        this.isAggressiveByDefault = false;
        this.setupMob();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (this.creatureInfo == null) {
            com.lycanitesmobs.core.manager.CreatureManager mgr = com.lycanitesmobs.core.manager.CreatureManager.getInstance();
            if (mgr != null) this.creatureInfo = mgr.getCreature("maka");
        }
    }

    @Override
    protected void m_8099_() {
        super.m_8099_();
        this.goalSelector.addGoal(nextDistractionGoalIndex++, new TemptGoal(this).setIncludeDiet(true));
        this.goalSelector.addGoal(nextCombatGoalIndex++, new AttackMeleeGoal(this).setLongMemory(false));
        this.targetSelector.addGoal(nextFindTargetIndex++,
            new FindMasterGoal(this).setTargetClass(com.lycanitesmobs.core.entity.creature.beast.EntityMakaAlpha.class).setSightCheck(false));
        this.targetSelector.addGoal(nextFindTargetIndex++, new CopyMasterAttackTargetGoal(this));
    }

    @Override
    public int getNoBagSize() { return 0; }

    @Override
    public int getBagSize() { return this.creatureInfo != null ? this.creatureInfo.getBagSize() : 0; }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (target instanceof TameableMaka) return false;
        if (target instanceof com.lycanitesmobs.core.entity.creature.beast.EntityMaka) return false;
        if (target instanceof com.lycanitesmobs.core.entity.creature.beast.EntityMakaAlpha) return false;
        return super.canAttack(target);
    }

    @Override
    public void onFirstSpawn() {
        CreatureInfo alphaInfo = CreatureManager.getInstance() != null ? CreatureManager.getInstance().getCreature("makaalpha") : null;
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
    public void setGrowingAge(int age) {
        if (age == 0 && this.getAge() < 0 && this.getRandom().nextFloat() >= 0.9f) {
            CreatureInfo alphaInfo = CreatureManager.getInstance() != null ? CreatureManager.getInstance().getCreature("makaalpha") : null;
            if (alphaInfo != null) {
                LivingEntity alpha = alphaInfo.createEntity(this.level());
                if (alpha != null) {
                    alpha.copyPosition(this);
                    this.level().addFreshEntity(alpha);
                    this.remove(RemovalReason.DISCARDED);
                    return;
                }
            }
        }
        super.setGrowingAge(age);
    }
}
