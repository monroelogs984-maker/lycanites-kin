package com.glennfo.lycaniteskin.entity;

import com.lycanitesmobs.core.entity.base.TameableCreatureEntity;
import com.lycanitesmobs.core.entity.goals.actions.AttackMeleeGoal;
import com.lycanitesmobs.core.entity.goals.actions.TemptGoal;
import com.lycanitesmobs.core.entity.goals.targeting.CopyMasterAttackTargetGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.Level;

public class TameableWarg extends TameableCreatureEntity {

    public TameableWarg(EntityType<? extends TameableWarg> type, Level level) {
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
            if (mgr != null) this.creatureInfo = mgr.getCreature("warg");
        }
    }

    @Override
    protected void m_8099_() {
        super.m_8099_();
        this.goalSelector.addGoal(nextDistractionGoalIndex++, new TemptGoal(this).setIncludeDiet(true));
        this.goalSelector.addGoal(nextCombatGoalIndex++, new AttackMeleeGoal(this).setLongMemory(true));
        this.targetSelector.addGoal(nextFindTargetIndex++, new CopyMasterAttackTargetGoal(this));
    }

    @Override
    public int getNoBagSize() { return 0; }

    @Override
    public int getBagSize() { return this.creatureInfo != null ? this.creatureInfo.getBagSize() : 0; }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (target instanceof TameableWarg) return false;
        if (target instanceof com.lycanitesmobs.core.entity.creature.beast.EntityWarg) return false;
        return super.canAttack(target);
    }
}
