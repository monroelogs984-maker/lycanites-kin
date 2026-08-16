package com.glennfo.lycaniteskin.init;

import com.glennfo.lycaniteskin.LycanitesKin;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    // Early — beast tier, between leather and iron
    WARG_PELT("warg_pelt",
            new int[]{2, 4, 5, 2}, 15, 12, 0.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.WARG_PELT.get())),

    // Early — insect chitin, lighter than chain
    VESPID_CHITIN("vespid_chitin",
            new int[]{1, 3, 4, 1}, 10, 14, 0.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.VESPID_CHITIN.get())),

    // Mid — dino hide, iron equivalent with extra durability
    MAKA_HIDE("maka_hide",
            new int[]{2, 5, 6, 2}, 18, 9, 1.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.MAKA_HIDE.get())),

    // Mid — avian, light as leather but with unique abilities
    ROC_FEATHER("roc_feather",
            new int[]{1, 2, 3, 1}, 8, 15, 0.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.ROC_FEATHER.get())),

    // Mid/Late — undead bone plates, above iron
    REAPER_BONE("reaper_bone",
            new int[]{2, 5, 6, 2}, 20, 8, 1.0f, 0.1f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.REAPER_BONE.get())),

    // Mid — spider silk weave, high enchantability
    FROSTWEAVER_SILK("frostweaver_silk",
            new int[]{1, 4, 5, 2}, 14, 16, 0.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.FROSTWEAVER_SILK.get())),

    // Late — dragon scales, near diamond tier
    REMOBRA_SCALE("remobra_scale",
            new int[]{3, 6, 7, 3}, 28, 10, 2.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.REMOBRA_SCALE.get())),

    // Low — desert crab shell, below leather
    JOUSTE_SHELL("jouste_shell",
            new int[]{1, 2, 3, 1}, 8, 10, 0.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.JOUSTE_SHELL.get())),

    // Mid — aquatic crustacean, iron-adjacent with slight toughness
    KRAKE_SHELL("krake_shell",
            new int[]{2, 4, 5, 2}, 16, 11, 0.5f, 0.0f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.KRAKE_SHELL.get())),

    // Mid — nature elemental bark, high enchantability
    SPRIGGAN_BARK("spriggan_bark",
            new int[]{2, 4, 5, 2}, 15, 15, 0.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.SPRIGGAN_BARK.get())),

    // High — chaos elemental scales, tank/rogue hybrid
    ARGUS_SCALE("argus_scale",
            new int[]{3, 5, 7, 3}, 24, 12, 1.5f, 0.1f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.ARGUS_SCALE.get())),

    // Mid-high — fae shadow wolf pelt, magical hunter
    BARGHEST_PELT("barghest_pelt",
            new int[]{2, 5, 6, 2}, 20, 13, 1.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.BARGHEST_PELT.get())),

    // Mid — tiger predator pelt, agile and fast
    DAWON_PELT("dawon_pelt",
            new int[]{2, 4, 5, 2}, 16, 14, 0.5f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.DAWON_PELT.get())),

    // Mid — fire-hardened lava salamander scales
    SALAMANDER_SCALE("salamander_scale",
            new int[]{2, 4, 5, 2}, 17, 10, 0.5f, 0.0f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.SALAMANDER_SCALE.get())),

    // Mid — tough mountain beast hide, knockback resistant
    YALE_HIDE("yale_hide",
            new int[]{2, 5, 6, 2}, 18, 9, 1.0f, 0.1f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.YALE_HIDE.get())),

    // Mid-high — storm bird feather, extra helmet protection
    RAIKO_FEATHER("raiko_feather",
            new int[]{2, 5, 6, 3}, 20, 12, 1.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.RAIKO_FEATHER.get())),

    // Mid — aquatic ray membrane, arcane-infused
    IORAY_MEMBRANE("ioray_membrane",
            new int[]{1, 4, 5, 2}, 15, 14, 0.0f, 0.0f,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            () -> Ingredient.of(ModItems.IORAY_MEMBRANE.get()));

    // Indexed by slot.getIndex(): FEET=0, LEGS=1, CHEST=2, HEAD=3
    private static final int[] DURABILITY_TEMPLATE = {13, 15, 16, 11};

    private final String name;
    private final int[] protection; // [FEET/BOOTS=0, LEGS/LEGGINGS=1, CHEST/CHESTPLATE=2, HEAD/HELMET=3]
    private final int durabilityMultiplier;
    private final int enchantability;
    private final float toughness;
    private final float knockbackResistance;
    private final SoundEvent equipSound;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int[] protection, int durabilityMultiplier,
                      int enchantability, float toughness, float knockbackResistance,
                      SoundEvent equipSound, Supplier<Ingredient> repairIngredient) {
        this.name = LycanitesKin.MOD_ID + ":" + name;
        this.protection = protection;
        this.durabilityMultiplier = durabilityMultiplier;
        this.enchantability = enchantability;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.equipSound = equipSound;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return DURABILITY_TEMPLATE[type.getSlot().getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        // protection array is [BOOTS, LEGGINGS, CHESTPLATE, HELMET] — same as slot index order
        return this.protection[type.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
