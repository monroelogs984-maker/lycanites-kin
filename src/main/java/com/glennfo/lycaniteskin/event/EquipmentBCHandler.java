package com.glennfo.lycaniteskin.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = "lycaniteskin")
public class EquipmentBCHandler {

    private static final Logger LOGGER = LogManager.getLogger("lycaniteskin");
    private static final String BC_NBT_KEY = "weapon_attributes";
    private static final ResourceLocation EQUIPMENT_ID = new ResourceLocation("lycanitesmobs", "equipment");

    // Priority order: first match in this list wins when multiple parts are present
    private static final List<String> SLOT_PRIORITY = List.of("pike", "axe", "blade", "base", "head", "pommel", "jewel");

    // All 59 Lycanites equipment parts mapped to their slot category
    private static final Map<String, String> PART_SLOT = Map.ofEntries(
        // axe slot
        Map.entry("clinkscythe",     "axe"),
        Map.entry("epionwing",       "axe"),
        Map.entry("geonachfist",     "axe"),
        Map.entry("reaperclaw",      "axe"),
        Map.entry("remobrawing",     "axe"),
        Map.entry("serpixmandible",  "axe"),
        Map.entry("vapulacrystal",   "axe"),
        // blade slot
        Map.entry("cinderblade",     "blade"),
        Map.entry("frostweaverleg",  "blade"),
        Map.entry("grueclaw",        "blade"),
        Map.entry("ironpaxel",       "blade"),
        Map.entry("raidrablade",     "blade"),
        Map.entry("woodenpaxel",     "blade"),
        // base slot
        Map.entry("argustail",       "base"),
        Map.entry("belphegorarm",    "base"),
        Map.entry("eechetikarm",     "base"),
        Map.entry("entarm",          "base"),
        Map.entry("ironrod",         "base"),
        Map.entry("spectretendril",  "base"),
        Map.entry("sprigganroot",    "base"),
        Map.entry("sylphwing",       "base"),
        Map.entry("wendigoantler",   "base"),
        Map.entry("woodenrod",       "base"),
        Map.entry("xaphanspine",     "base"),
        // head slot
        Map.entry("apollyonclaw",    "head"),
        Map.entry("astarothclaw",    "head"),
        Map.entry("bansheeeye",      "head"),
        Map.entry("behemophethand",  "head"),
        Map.entry("darklingskull",   "head"),
        Map.entry("goldscepterhead", "head"),
        Map.entry("ironaxehead",     "head"),
        Map.entry("ironguard",       "head"),
        Map.entry("ironpikejoint",   "head"),
        Map.entry("uvaraptorskull",  "head"),
        Map.entry("ventoraptorskull","head"),
        Map.entry("wargskull",       "head"),
        Map.entry("wildkinarm",      "head"),
        Map.entry("woodenguard",     "head"),
        Map.entry("wraithskull",     "head"),
        // jewel slot
        Map.entry("afritlung",       "jewel"),
        Map.entry("arixbrain",       "jewel"),
        Map.entry("cherufecore",     "jewel"),
        Map.entry("conbabutt",       "jewel"),
        Map.entry("eyewigeye",       "jewel"),
        Map.entry("gammasphere",     "jewel"),
        Map.entry("malwratheye",     "jewel"),
        Map.entry("naxiriseye",      "jewel"),
        Map.entry("sprigganheart",   "jewel"),
        Map.entry("stryderheart",    "jewel"),
        // pike slot
        Map.entry("bruchaquill",     "pike"),
        Map.entry("erepededrill",    "pike"),
        Map.entry("ettinclub",       "pike"),
        Map.entry("geonachspear",    "pike"),
        Map.entry("reiverhorns",     "pike"),
        // pommel slot
        Map.entry("ioraystinger",    "pommel"),
        Map.entry("lacedonhead",     "pommel"),
        Map.entry("sutiramustinger", "pommel"),
        Map.entry("vespidstinger",   "pommel"),
        Map.entry("zephyrcloud",     "pommel")
    );

    // BC reads weapon_attributes NBT as AttributesContainer JSON: {"attributes":{...}}
    private static final Map<String, String> SLOT_ATTRS = Map.of(
        "pike",   "{\"attributes\":{\"attack_range\":3.5,\"two_handed\":true,\"category\":\"spear\",\"attacks\":[{\"hitbox\":\"FORWARD_BOX\",\"damage_multiplier\":1.0,\"angle\":0,\"upswing\":0.3,\"animation\":\"bettercombat:two_handed_stab_left\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\"}},{\"hitbox\":\"FORWARD_BOX\",\"damage_multiplier\":1.0,\"angle\":0,\"upswing\":0.3,\"animation\":\"bettercombat:two_handed_stab_right\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\"}}]}}",
        "axe",    "{\"attributes\":{\"attack_range\":2.5,\"category\":\"axe\",\"attacks\":[{\"hitbox\":\"HORIZONTAL_PLANE\",\"angle\":90,\"upswing\":0.5,\"animation\":\"bettercombat:one_handed_slash_horizontal_right\",\"swing_sound\":{\"id\":\"bettercombat:axe_slash\"}},{\"hitbox\":\"HORIZONTAL_PLANE\",\"angle\":90,\"upswing\":0.5,\"animation\":\"bettercombat:one_handed_slash_horizontal_left\",\"swing_sound\":{\"id\":\"bettercombat:axe_slash\"}}]}}",
        "blade",  "{\"attributes\":{\"attack_range\":2.5,\"category\":\"sword\",\"attacks\":[{\"hitbox\":\"HORIZONTAL_PLANE\",\"damage_multiplier\":1.0,\"angle\":120,\"upswing\":0.5,\"animation\":\"bettercombat:one_handed_slash_horizontal_right\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\"}},{\"hitbox\":\"HORIZONTAL_PLANE\",\"damage_multiplier\":1.0,\"angle\":120,\"upswing\":0.5,\"animation\":\"bettercombat:one_handed_slash_horizontal_left\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\"}},{\"hitbox\":\"FORWARD_BOX\",\"damage_multiplier\":1.0,\"angle\":0,\"upswing\":0.5,\"animation\":\"bettercombat:one_handed_stab\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\",\"pitch\":1.2}}]}}",
        "base",   "{\"attributes\":{\"attack_range\":3.0,\"two_handed\":true,\"category\":\"staff\",\"attacks\":[{\"hitbox\":\"HORIZONTAL_PLANE\",\"damage_multiplier\":1.0,\"angle\":180,\"upswing\":0.4,\"animation\":\"bettercombat:two_handed_slash_horizontal_right\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\"}},{\"hitbox\":\"HORIZONTAL_PLANE\",\"damage_multiplier\":1.0,\"angle\":180,\"upswing\":0.4,\"animation\":\"bettercombat:two_handed_slash_horizontal_left\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\"}}]}}",
        "head",   "{\"attributes\":{\"attack_range\":2.5,\"category\":\"hammer\",\"attacks\":[{\"hitbox\":\"HORIZONTAL_PLANE\",\"damage_multiplier\":1.0,\"angle\":90,\"upswing\":0.6,\"animation\":\"bettercombat:one_handed_slam\",\"swing_sound\":{\"id\":\"bettercombat:axe_slash\"}}]}}",
        "pommel", "{\"attributes\":{\"attack_range\":2.0,\"category\":\"dagger\",\"attacks\":[{\"hitbox\":\"HORIZONTAL_PLANE\",\"damage_multiplier\":0.9,\"angle\":150,\"upswing\":0.4,\"animation\":\"bettercombat:one_handed_slash_horizontal_right\",\"swing_sound\":{\"id\":\"bettercombat:dagger_slash\"}},{\"hitbox\":\"HORIZONTAL_PLANE\",\"damage_multiplier\":0.9,\"angle\":150,\"upswing\":0.4,\"animation\":\"bettercombat:one_handed_slash_horizontal_left\",\"swing_sound\":{\"id\":\"bettercombat:dagger_slash\"}}]}}",
        "jewel",  "{\"attributes\":{\"attack_range\":2.5,\"category\":\"wand\",\"attacks\":[{\"hitbox\":\"FORWARD_BOX\",\"damage_multiplier\":1.0,\"angle\":0,\"upswing\":0.5,\"animation\":\"bettercombat:one_handed_stab\",\"swing_sound\":{\"id\":\"bettercombat:sword_slash\"}}]}}"
    );

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        ItemStack stack = event.getTo();
        if (stack.isEmpty()) return;

        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(stack.getItem());
        if (!EQUIPMENT_ID.equals(itemId)) return;

        CompoundTag tag = stack.getOrCreateTag();
        // Re-inject if the stored value uses the old unwrapped format (missing "attributes" wrapper)
        if (tag.contains(BC_NBT_KEY)) {
            String existing = tag.getString(BC_NBT_KEY);
            if (existing.startsWith("{\"attributes\"")) return;
            tag.remove(BC_NBT_KEY);
        }

        String slotCategory = detectSlotCategory(tag);
        if (slotCategory == null) return;

        String attrsJson = SLOT_ATTRS.get(slotCategory);
        if (attrsJson == null) return;

        tag.putString(BC_NBT_KEY, attrsJson);
        LOGGER.debug("[LycanitesKin] Injected BC '{}' animation into crafted equipment.", slotCategory);
    }

    private static String detectSlotCategory(CompoundTag equipmentTag) {
        Set<String> found = new HashSet<>();
        collectCategories(equipmentTag, found);
        // Return highest priority category present
        for (String category : SLOT_PRIORITY) {
            if (found.contains(category)) return category;
        }
        return null;
    }

    private static void collectCategories(CompoundTag compound, Set<String> found) {
        if (compound.contains("id")) {
            String partName = compound.getString("id").replace("lycanitesmobs:", "");
            String category = PART_SLOT.get(partName);
            if (category != null) found.add(category);
        }
        for (String key : compound.getAllKeys()) {
            Tag sub = compound.get(key);
            if (sub instanceof CompoundTag nested) {
                collectCategories(nested, found);
            } else if (sub instanceof ListTag list) {
                for (Tag entry : list) {
                    if (entry instanceof CompoundTag nestedEntry) {
                        collectCategories(nestedEntry, found);
                    }
                }
            }
        }
    }
}
