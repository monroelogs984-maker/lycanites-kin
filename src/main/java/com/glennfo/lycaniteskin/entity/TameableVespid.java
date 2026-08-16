package com.glennfo.lycaniteskin.entity;

import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import com.lycanitesmobs.core.entity.goals.actions.abilities.PlaceBlockGoal;
import com.lycanitesmobs.core.entity.goals.targeting.CopyMasterAttackTargetGoal;
import com.lycanitesmobs.core.entity.goals.targeting.FindMasterGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;

public class TameableVespid extends TameableCreatureEntity {

    public PlaceBlockGoal aiPlaceBlock;
    private boolean hiveBuilding = true;

    public TameableVespid(EntityType<? extends TameableVespid> type, Level level) {
        super(type, level);
        this.attribute = MobType.ARTHROPOD;
        this.hasAttackSound = true;
        this.canGrow = true;
        this.babySpawnChance = 0.1;
        this.setMaxUpStep(1.0f);
        this.attackCooldownMax = 10;
        this.setupMob();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (this.creatureInfo == null) {
            com.lycanitesmobs.core.manager.CreatureManager mgr = com.lycanitesmobs.core.manager.CreatureManager.getInstance();
            if (mgr != null) this.creatureInfo = mgr.getCreature("vespid");
        }
    }

    @Override
    protected void m_8099_() {
        super.m_8099_();
        this.goalSelector.addGoal(nextCombatGoalIndex++, new AttackMeleeGoal(this).setLongMemory(true));
        this.aiPlaceBlock = new PlaceBlockGoal(this)
            .setMaxDistance(128.0)
            .setSpeed(3.0)
            .setReplaceLiquid(true)
            .setReplaceSolid(false);
        this.goalSelector.addGoal(nextIdleGoalIndex++, this.aiPlaceBlock);
        this.targetSelector.addGoal(nextFindTargetIndex++,
            new FindMasterGoal(this).setTargetClass(com.lycanitesmobs.core.entity.creature.insect.EntityVespidQueen.class).setRange(64.0));
        this.targetSelector.addGoal(nextFindTargetIndex++, new CopyMasterAttackTargetGoal(this));
    }

    @Override
    public void loadCreatureFlags() {
        if (this.creatureInfo == null) return;
        this.hiveBuilding = this.creatureInfo.getFlag("hiveBuilding", this.hiveBuilding);
    }

    @Override
    public int getNoBagSize() { return 0; }

    @Override
    public int getBagSize() { return this.creatureInfo != null ? this.creatureInfo.getBagSize() : 0; }

    @Override
    public boolean isPersistant() {
        if (this.getMasterTarget() instanceof com.lycanitesmobs.core.entity.base.BaseCreatureEntity master) {
            return master.isPersistant();
        }
        return super.isPersistant();
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (target instanceof TameableVespid) return false;
        if (target instanceof com.lycanitesmobs.core.entity.creature.insect.EntityVespid) return false;
        if (target instanceof com.lycanitesmobs.core.entity.creature.insect.EntityVespidQueen) return false;
        return super.canAttack(target);
    }
}
