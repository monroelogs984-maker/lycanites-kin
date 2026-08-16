#!/usr/bin/env python3
"""Generate all 28 lycancompanions armor crafting recipes."""
import json, os

OUT = "src/main/resources/data/lycancompanions/recipes"
MOD = "lycancompanions"

SETS = {
    "warg_pelt":        {"vanilla": "minecraft:iron_block",      "charge": "lycanitesmobs:mudshotcharge"},
    "vespid_chitin":    {"vanilla": "minecraft:lapis_block",     "charge": "lycanitesmobs:poisonraycharge"},
    "maka_hide":        {"vanilla": "minecraft:iron_block",      "charge": "lycanitesmobs:lightballcharge"},
    "roc_feather":      {"vanilla": "minecraft:gold_block",      "charge": "lycanitesmobs:whirlwindcharge"},
    "reaper_bone":      {"vanilla": "minecraft:diamond",         "charge": "lycanitesmobs:spectralboltcharge"},
    "frostweaver_silk": {"vanilla": "minecraft:lapis_block",     "charge": "lycanitesmobs:frostwebcharge"},
    "remobra_scale":    {"vanilla": "minecraft:netherite_scrap", "charge": "lycanitesmobs:venomshotcharge"},
    "jouste_shell":     {"vanilla": "minecraft:iron_block",      "charge": "lycanitesmobs:boulderblastcharge"},
    "krake_shell":      {"vanilla": "minecraft:gold_block",      "charge": "lycanitesmobs:aquapulsecharge"},
    "spriggan_bark":    {"vanilla": "minecraft:lapis_block",     "charge": "lycanitesmobs:lifedraincharge"},
    "argus_scale":      {"vanilla": "minecraft:diamond",         "charge": "lycanitesmobs:chaosorbcharge"},
    "barghest_pelt":    {"vanilla": "minecraft:lapis_block",     "charge": "lycanitesmobs:faeboltcharge"},
    "dawon_pelt":       {"vanilla": "minecraft:gold_block",      "charge": "lycanitesmobs:arcanelaserstormcharge"},
    "salamander_scale": {"vanilla": "minecraft:iron_block",      "charge": "lycanitesmobs:magmacharge"},
    "yale_hide":        {"vanilla": "minecraft:iron_block",      "charge": "lycanitesmobs:mudshotcharge"},
    "raiko_feather":    {"vanilla": "minecraft:gold_block",      "charge": "lycanitesmobs:aetherwavecharge"},
    "ioray_membrane":   {"vanilla": "minecraft:lapis_block",     "charge": "lycanitesmobs:waterjetcharge"},
}

# X = creature drop, C = charge, V = vanilla material
PATTERNS = {
    "helmet":     (["VXV", "XCX", "   "], "helmet"),
    "chestplate": (["XVX", "XCX", "XXX"], "chestplate"),
    "leggings":   (["XXX", "VCV", "X X"], "leggings"),
    "boots":      (["   ", "XCX", "V V"], "boots"),
}

os.makedirs(OUT, exist_ok=True)

for mat, mdata in SETS.items():
    for piece, (pattern, _) in PATTERNS.items():
        recipe = {
            "type": "minecraft:crafting_shaped",
            "pattern": pattern,
            "key": {
                "X": {"item": f"{MOD}:{mat}"},
                "C": {"item": mdata["charge"]},
                "V": {"item": mdata["vanilla"]},
            },
            "result": {
                "item": f"{MOD}:{mat}_{piece}",
                "count": 1
            }
        }
        path = f"{OUT}/{mat}_{piece}.json"
        with open(path, "w") as f:
            json.dump(recipe, f, indent=2)
        print(f"  {path}")

print(f"\nDone. {len(SETS) * len(PATTERNS)} recipes written.")
