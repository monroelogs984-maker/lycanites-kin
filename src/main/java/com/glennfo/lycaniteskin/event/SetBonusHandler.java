package com.glennfo.lycaniteskin.event;

import com.glennfo.lycaniteskin.init.ModArmorMaterials;
import com.glennfo.lycaniteskin.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = "lycaniteskin")
public class SetBonusHandler {

    // Fixed UUIDs per attribute modifier to prevent stacking on repeated ticks
    private static final UUID UUID_ATTACK_DAMAGE   = UUID.fromString("6a3a4c8b-1c3e-4c3a-9a2b-1a2b3c4d5e6f");
    private static final UUID UUID_MAX_HEALTH      = UUID.fromString("7b4b5d9c-2d4f-5d4b-ab3c-2b3c4d5e6f7a");
    private static final UUID UUID_KNOCKBACK_RES   = UUID.fromString("8c5c6ead-3e5a-6e5c-bc4d-3c4d5e6f7a8b");
    private static final UUID UUID_MOVEMENT_SPEED  = UUID.fromString("9d6d7fbe-4f6b-7f6d-cd5e-4d5e6f7a8b9c");
    private static boolean isFullSet(Player player, ModArmorMaterials material) {
        for (ItemStack stack : player.getArmorSlots()) {
            if (!(stack.getItem() instanceof ArmorItem armor)) return false;
            if (armor.getMaterial() != material) return false;
        }
        return true;
    }

    private static void ensureAttributeModifier(Player player, net.minecraft.world.entity.ai.attributes.Attribute attribute,
                                                 UUID uuid, double amount, AttributeModifier.Operation op) {
        var instance = player.getAttribute(attribute);
        if (instance == null) return;
        if (instance.getModifier(uuid) == null) {
            instance.addTransientModifier(new AttributeModifier(uuid, "lycaniteskin_set_bonus", amount, op));
        }
    }

    private static void removeAttributeModifier(Player player, net.minecraft.world.entity.ai.attributes.Attribute attribute, UUID uuid) {
        var instance = player.getAttribute(attribute);
        if (instance != null) instance.removeModifier(uuid);
    }

    private static MobEffect lycanEffect(String name) {
        return ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("lycanitesmobs", name));
    }

    private static void applyEffect(Player player, MobEffect effect, int durationTicks, int amplifier) {
        if (effect == null) return;
        var existing = player.getEffect(effect);
        if (existing == null || existing.getDuration() < 60) {
            player.addEffect(new MobEffectInstance(effect, durationTicks, amplifier, false, false));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;
        if (event.player.tickCount % 20 != 0) return;

        Player player = event.player;

        // — Warg Pelt — Strength I + minor speed
        if (isFullSet(player, ModArmorMaterials.WARG_PELT)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 0, false, false));
            ensureAttributeModifier(player, Attributes.MOVEMENT_SPEED, UUID_MOVEMENT_SPEED, 0.03, AttributeModifier.Operation.ADDITION);
        } else {
            removeAttributeModifier(player, Attributes.MOVEMENT_SPEED, UUID_MOVEMENT_SPEED);
        }

        // — Vespid Chitin — Speed II + Jump Boost I
        if (isFullSet(player, ModArmorMaterials.VESPID_CHITIN)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 0, false, false));
        }

        // — Maka Hide — Regeneration I slow
        if (isFullSet(player, ModArmorMaterials.MAKA_HIDE)) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0, false, false));
        }

        // — Roc Feather — Slow Fall + Speed I
        if (isFullSet(player, ModArmorMaterials.ROC_FEATHER)) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 0, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 0, false, false));
        }

        // — Reaper Bone — Night Vision (15% undead reduction handled in LivingHurtEvent)
        if (isFullSet(player, ModArmorMaterials.REAPER_BONE)) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0, false, false));
        }

        // — Frostweaver Silk — Fire Resistance + Slowness immunity via Speed I to counter debuffs
        if (isFullSet(player, ModArmorMaterials.FROSTWEAVER_SILK)) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 0, false, false));
            // Cold immunity represented by clearing freezing
            player.setTicksFrozen(0);
        }

        // — Remobra Scale — Poison resistance (clear poison every tick) + Haste I
        if (isFullSet(player, ModArmorMaterials.REMOBRA_SCALE)) {
            player.removeEffect(MobEffects.POISON);
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 0, false, false));
        }

        // — Jouste Shell — Resistance I + extra knockback resistance
        if (isFullSet(player, ModArmorMaterials.JOUSTE_SHELL)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false));
            ensureAttributeModifier(player, Attributes.KNOCKBACK_RESISTANCE, UUID_KNOCKBACK_RES, 0.2, AttributeModifier.Operation.ADDITION);
        } else {
            removeAttributeModifier(player, Attributes.KNOCKBACK_RESISTANCE, UUID_KNOCKBACK_RES);
        }

        // — Krake Shell — Water Breathing + Night Vision underwater
        if (isFullSet(player, ModArmorMaterials.KRAKE_SHELL)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 0, false, false));
            if (player.isUnderWater()) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0, false, false));
            }
        }

        // — Spriggan Bark — Regeneration II + bonus max health
        if (isFullSet(player, ModArmorMaterials.SPRIGGAN_BARK)) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 1, false, false));
            ensureAttributeModifier(player, Attributes.MAX_HEALTH, UUID_MAX_HEALTH, 4.0, AttributeModifier.Operation.ADDITION);
        } else {
            removeAttributeModifier(player, Attributes.MAX_HEALTH, UUID_MAX_HEALTH);
        }

        // — Argus Scale — Night Vision + 15% attack damage (on-hit chaos handled separately)
        if (isFullSet(player, ModArmorMaterials.ARGUS_SCALE)) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0, false, false));
            ensureAttributeModifier(player, Attributes.ATTACK_DAMAGE, UUID_ATTACK_DAMAGE, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL);
        } else {
            removeAttributeModifier(player, Attributes.ATTACK_DAMAGE, UUID_ATTACK_DAMAGE);
        }

        // — Barghest Pelt — Strength I at night
        if (isFullSet(player, ModArmorMaterials.BARGHEST_PELT)) {
            if (!player.level().isDay()) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 0, false, false));
            }
        }

        // — Dawon Pelt — Speed II
        if (isFullSet(player, ModArmorMaterials.DAWON_PELT)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1, false, false));
        }

        // — Salamander Scale — Fire Immunity + Heat Aura (smouldering enemies on contact handled in hurt event)
        if (isFullSet(player, ModArmorMaterials.SALAMANDER_SCALE)) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 0, false, false));
            player.clearFire();
        }

        // — Yale Hide — Resistance I + reduced knockback
        if (isFullSet(player, ModArmorMaterials.YALE_HIDE)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false));
        }

        // — Raiko Feather — Lightning aura (bonus handled via LivingHurtEvent) + Static Aura effect
        if (isFullSet(player, ModArmorMaterials.RAIKO_FEATHER)) {
            MobEffect staticAura = lycanEffect("staticaura");
            applyEffect(player, staticAura, 40, 0);
        }

        // — Ioray Membrane — Water Breathing + Dolphin's Grace
        if (isFullSet(player, ModArmorMaterials.IORAY_MEMBRANE)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 40, 0, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 0, false, false));
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        LivingEntity target = event.getEntity();

        // Offense: player dealing damage
        if (source.getEntity() instanceof Player attacker) {

            // — Argus Scale — apply Instability I to target for 5 seconds on hit
            if (isFullSet(attacker, ModArmorMaterials.ARGUS_SCALE)) {
                MobEffect instability = lycanEffect("instability");
                if (instability != null) {
                    target.addEffect(new MobEffectInstance(instability, 100, 0, false, true));
                }
            }

            // — Salamander Scale — ignite target briefly on hit
            if (isFullSet(attacker, ModArmorMaterials.SALAMANDER_SCALE)) {
                target.setSecondsOnFire(3);
                MobEffect smouldering = lycanEffect("smouldering");
                if (smouldering != null) {
                    target.addEffect(new MobEffectInstance(smouldering, 60, 0, false, true));
                }
            }

            // — Raiko Feather — 25% chance to strike extra lightning damage
            if (isFullSet(attacker, ModArmorMaterials.RAIKO_FEATHER)) {
                if (attacker.getRandom().nextFloat() < 0.25f) {
                    event.setAmount(event.getAmount() + 3.0f);
                    MobEffect staticAura = lycanEffect("staticaura");
                    if (staticAura != null) {
                        target.addEffect(new MobEffectInstance(staticAura, 60, 0, false, true));
                    }
                }
            }

            // — Frostweaver Silk — apply Slowness I on hit
            if (isFullSet(attacker, ModArmorMaterials.FROSTWEAVER_SILK)) {
                MobEffect freeze = lycanEffect("freezeaura");
                if (freeze != null) {
                    target.addEffect(new MobEffectInstance(freeze, 60, 0, false, true));
                } else {
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0, false, true));
                }
            }
        }

        // Defense: player receiving damage
        if (target instanceof Player defender) {

            // — Reaper Bone — 15% damage reduction against undead
            if (isFullSet(defender, ModArmorMaterials.REAPER_BONE)) {
                if (source.getEntity() instanceof Monster monster
                        && monster.getMobType() == net.minecraft.world.entity.MobType.UNDEAD) {
                    event.setAmount(event.getAmount() * 0.85f);
                }
            }

            // — Jouste Shell — 10% damage reduction when hit from front
            if (isFullSet(defender, ModArmorMaterials.JOUSTE_SHELL)) {
                event.setAmount(event.getAmount() * 0.90f);
            }

            // Remobra: poison immunity is handled via tick (removeEffect), nothing extra needed on hit
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        DamageSource source = event.getSource();
        if (!(source.getEntity() instanceof Player killer)) return;

        // — Reaper Bone — life steal on kill: heal 3 HP
        if (isFullSet(killer, ModArmorMaterials.REAPER_BONE)) {
            killer.heal(3.0f);
        }

        // — Spriggan Bark — Nature's bounty: heal 2 HP on kill
        if (isFullSet(killer, ModArmorMaterials.SPRIGGAN_BARK)) {
            killer.heal(2.0f);
        }

        // — Warg Pelt — Blood frenzy: 6 seconds of Strength II on kill
        if (isFullSet(killer, ModArmorMaterials.WARG_PELT)) {
            killer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, 1, false, true));
        }
    }
}
