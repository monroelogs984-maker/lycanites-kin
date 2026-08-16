#!/usr/bin/env python3
"""
Lycancompanions texture generator.
Drop icons are shaped after the actual Lycanites creature model textures.
Armor layers use dithered creature-accurate colors on the vanilla UV layout.
"""
from PIL import Image
import os

ITEM_DIR  = "src/main/resources/assets/lycancompanions/textures/item"
ARMOR_DIR = "src/main/resources/assets/lycancompanions/textures/models/armor"

T = (0, 0, 0, 0)

def c(h):
    h = h.lstrip('#')
    return tuple(int(h[i:i+2], 16) for i in (0, 2, 4)) + (255,)

def px(img, x, y, col):
    if 0 <= x < img.width and 0 <= y < img.height and col != T:
        img.putpixel((x, y), col)

def canvas16():
    return Image.new("RGBA", (16, 16), T)

def outline_pass(img, O):
    """1px dark outline around all opaque pixels."""
    snap = img.copy()
    for y in range(16):
        for x in range(16):
            if snap.getpixel((x, y))[3] == 0:
                for dx, dy in ((-1,0),(1,0),(0,-1),(0,1)):
                    nx, ny = x+dx, y+dy
                    if 0 <= nx < 16 and 0 <= ny < 16 and snap.getpixel((nx, ny))[3] > 128:
                        img.putpixel((x, y), O)
                        break

def save(img, path):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    img.save(path)

# ── Creature palettes sampled from actual Lycanites textures ──────────────────
# [outline, shadow, mid, light, highlight]
# Corrected where auto-sampler got fooled by backgrounds/wing transparency.

PAL = {
    # Dark chocolate-mahogany matted quill-fur
    "warg_pelt":        [c("140a03"), c("211004"), c("3d2015"), c("613a28"), c("8a5840")],
    # Yellow-black wasp chitin (corrected: sampler grabbed white wings)
    "vespid_chitin":    [c("0a0800"), c("3a2000"), c("8a6000"), c("c89000"), c("e8b808")],
    # Warm orange-brown maka hide
    "maka_hide":        [c("200808"), c("683811"), c("885020"), c("b06428"), c("d48040")],
    # Very dark earth-brown roc feathers
    "roc_feather":      [c("180a00"), c("3a1f01"), c("592f01"), c("7a4510"), c("9a6030")],
    # Cream bone with dark reaper tan (sampler found bone correctly)
    "reaper_bone":      [c("181008"), c("4a3010"), c("9b6c43"), c("c09060"), c("e8d0a0")],
    # Icy pale blue — crystal silk
    "frostweaver_silk": [c("182838"), c("6090b0"), c("a1c8d8"), c("d1e8ef"), c("f0f8ff")],
    # Deep purple remobra scales — NOT green
    "remobra_scale":    [c("0e021a"), c("190529"), c("280e3a"), c("4a2060"), c("6030a0")],
    # Golden amber jouste shell segments
    "jouste_shell":     [c("1a1000"), c("382b16"), c("a66423"), c("c27426"), c("e8a030")],
    # Crimson red krake with navy accent stored separately
    "krake_shell":      [c("0a0408"), c("62180d"), c("a51d1e"), c("d02828"), c("f06040")],
    # Living green wood with brown bark grain — spriggan body IS green
    "spriggan_bark":    [c("051005"), c("0a3a08"), c("116917"), c("1e8522"), c("2aad30")],
    # Neon green argus scales — NOT purple
    "argus_scale":      [c("081008"), c("0c3808"), c("075302"), c("0d8004"), c("20d010")],
    # Tawny orange-amber barghest wolf fur
    "barghest_pelt":    [c("080400"), c("3c1800"), c("8c3c10"), c("b36522"), c("d88030")],
    # Forest green with dark rosette/spot jaguar pattern
    "dawon_pelt":       [c("080c04"), c("1a3008"), c("2e5514"), c("4a8020"), c("6ab830")],
    # Vivid red-orange lava salamander
    "salamander_scale": [c("0a0400"), c("450203"), c("8e1c05"), c("c04010"), c("e86020")],
    # Grey-brown woolly yale hide
    "yale_hide":        [c("100a08"), c("3a2820"), c("77685a"), c("9a8878"), c("c0b0a0")],
    # Grey-white raiko feathers
    "raiko_feather":    [c("181818"), c("31302e"), c("565656"), c("989698"), c("dad6d5")],
    # Deep navy + bright blue ioray membrane
    "ioray_membrane":   [c("060810"), c("1f3053"), c("3c30bf"), c("6070d0"), c("a3b6dd")],
}

# Accent colors for two-toned creatures
ACCENT = {
    "vespid_chitin":    c("0a0800"),   # black chitin veins/stripes
    "roc_feather":      c("703090"),   # purple flower protrusions
    "krake_shell":      c("101d2f"),   # navy blue fins
    "argus_scale":      c("d020c0"),   # magenta glowing spots
    "barghest_pelt":    c("0a0600"),   # dark stripe markings
    "dawon_pelt":       c("0c0800"),   # black tiger stripes
    "yale_hide":        c("901010"),   # red horn/joint accent
    "raiko_feather":    c("d88010"),   # orange head/beak marking
    "reaper_bone":      c("30c0b0"),   # spectral teal crack
}

# Crop rectangles (left,top,right,bottom) into the actual creature PNGs
# Warg: dark chocolate fur band at bottom of 512x512 sheet
# Barghest: clean orange+black tiger stripe body section
# Dawon: green+dark rosette jaguar body section from 256x256 sheet
CREATURE_CROPS = {
    # Pelts
    "warg_pelt":        ("/tmp/lycan_textures/warg.png",        (5,   390, 90,  430)),
    "barghest_pelt":    ("/tmp/lycan_textures/barghest.png",    (5,   155, 190, 220)),
    "dawon_pelt":       ("/tmp/lycan_textures/dawon.png",       (15,  95,  115, 168)),
    # Hides
    "maka_hide":        ("/tmp/lycan_textures/maka.png",        (0,   320, 512, 400)),
    "yale_hide":        ("/tmp/lycan_textures/yale.png",        (0,   200, 256, 256)),
    # Scales
    "remobra_scale":    ("/tmp/lycan_textures/remobra.png",     (0,   0,   64,  32)),
    "salamander_scale": ("/tmp/lycan_textures/salamander.png",  (0,   144, 256, 200)),
    # argus_scale: excluded — texture has solid green background contamination;
    # procedural approach gives correct neon-green + magenta appearance
    # Feathers
    "roc_feather":      ("/tmp/lycan_textures/roc.png",         (0,   96,  256, 128)),  # y=96-112 is 85% brown feather
    "raiko_feather":    ("/tmp/lycan_textures/raiko.png",       (0,   200, 256, 256)),
    # Shells
    "jouste_shell":     ("/tmp/lycan_textures/jouste.png",      (0,   192, 256, 256)),
    "krake_shell":      ("/tmp/lycan_textures/krake.png",       (20,  80,  200, 150)),
    # Aquatic/membrane
    "ioray_membrane":   ("/tmp/lycan_textures/ioray.png",       (0,   220, 256, 256)),
    # Specials
    "reaper_bone":      ("/tmp/lycan_textures/reaper.png",      (0,   16,  32,  64)),  # bone-dense x=0-32 strip (84-90% bone)
    "frostweaver_silk": ("/tmp/lycan_textures/frostweaver.png", (0,   0,   128, 48)),
    "vespid_chitin":    ("/tmp/lycan_textures/vespid.png",      (20,  144, 236, 192)),
    "spriggan_bark":    ("/tmp/lycan_textures/spriggan.png",    (0,   80,  96,  180)),  # left body section 85-92% green
}

# ── Drop icon draw functions ──────────────────────────────────────────────────

def draw_fur_tuft(img, mat):
    """Spiky-tipped fur mass — warg / barghest."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    # Three fur spike tips
    for sx, sy in [(5, 2), (8, 1), (11, 2)]:
        px(img, sx, sy, L); px(img, sx, sy+1, M); px(img, sx, sy+2, Sh)

    # Body mass rows
    rows = {4:(4,11), 5:(3,12), 6:(3,12), 7:(3,12), 8:(4,11), 9:(5,10), 10:(6,9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Dithered fur texture — alternating columns of shadow and light
    for y in range(4, 11):
        for x in range(3, 13):
            if img.getpixel((x, y)) == M:
                if x % 3 == 0:
                    px(img, x, y, Sh)
                elif x % 3 == 1 and y % 2 == 0:
                    px(img, x, y, L)

    # Dark stripe marks for barghest if accented
    if A:
        for y in [5, 7, 9]:
            for x in [4, 7, 10]:
                if img.getpixel((x, y))[3] > 0:
                    px(img, x, y, A)

    # Highlight cluster
    px(img, 5, 5, Hi); px(img, 6, 5, L); px(img, 5, 6, L)

    # Shadow right edge
    for y in range(5, 11):
        if img.getpixel((11, y))[3] > 0: px(img, 11, y, Sh)

def draw_tiger_pelt(img, mat):
    """Orange pelt patch with bold black stripes — dawon."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat, c("0c0800"))

    # Pelt patch shape (slightly angled)
    rows = {3:(4,12), 4:(3,12), 5:(3,13), 6:(3,13), 7:(3,12),
            8:(4,12), 9:(4,11), 10:(5,10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Fur grain texture (vertical streaks)
    for y in range(3, 11):
        for x in range(3, 13):
            if img.getpixel((x, y)) == M:
                if y % 3 == 0:
                    px(img, x, y, L)
                elif y % 3 == 2:
                    px(img, x, y, Sh)

    # Bold tiger stripes (diagonal bands)
    for dy in [0, 4, 8]:
        for i in range(5):
            x, y = 3 + i, 5 + dy - i
            if 0 <= x < 16 and 0 <= y < 16 and img.getpixel((x, y))[3] > 0:
                px(img, x, y, A)
                if x+1 < 16 and img.getpixel((x+1, y))[3] > 0:
                    px(img, x+1, y, A)

    px(img, 5, 4, Hi); px(img, 6, 4, L)

def draw_chitin_shard(img, mat):
    """Curved insect chitin plate — vespid. Yellow with dark vein lines."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    # Main plate shape (curved hexagonal)
    rows = {2:(6,9), 3:(5,10), 4:(4,11), 5:(4,11), 6:(4,11),
            7:(4,11), 8:(5,10), 9:(6,9), 10:(7,8)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Chitin surface sheen (left bright, right shadowed)
    for y in range(3, 10):
        if img.getpixel((5, y))[3] > 0: px(img, 5, y, L)
        if img.getpixel((10, y))[3] > 0: px(img, 10, y, Sh)

    # Central ridge
    for y in range(3, 10): px(img, 7, y, L); px(img, 8, y, Sh)

    # Black vein lines (characteristic wasp pattern)
    for y, xs in {4:[4,7,10], 6:[4,7,10], 8:[5,8]}.items():
        for x in xs:
            if img.getpixel((x, y))[3] > 0:
                px(img, x, y, A if A else Sh)

    # Highlight
    px(img, 6, 3, Hi); px(img, 7, 3, Hi)

def draw_hide_patch(img, mat):
    """Leather/hide piece with scale or wrinkle texture — maka, yale."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    # Main hide shape (slightly irregular rectangle)
    rows = {3:(4,12), 4:(3,12), 5:(3,13), 6:(3,13), 7:(3,12),
            8:(3,12), 9:(4,11), 10:(5,10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Scale/wrinkle texture grid
    for y in range(3, 11):
        for x in range(3, 13):
            if img.getpixel((x, y))[3] > 0:
                if (x + y*2) % 4 == 0:
                    px(img, x, y, Sh)
                elif (x*2 + y) % 5 == 0:
                    px(img, x, y, L)

    # Rolled left edge (folded hide)
    for y in range(4, 10): px(img, 3, y, Sh)

    # Red accent dots for yale (horn/joint marks)
    if A:
        for pos in [(5, 4), (9, 6), (7, 9)]:
            px(img, pos[0], pos[1], A)

    px(img, 5, 4, Hi); px(img, 6, 4, L)

def draw_feather(img, mat):
    """Full feather with shaft and barbs — roc, raiko."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    # Shaft — diagonal from top-right to bottom-left
    shaft = [(10,2),(9,3),(8,4),(7,5),(6,6),(5,7),(4,8),(3,9),(2,10),(3,11)]
    for (x, y) in shaft:
        px(img, x, y, Sh)

    # Barbs right side of shaft (lighter, wider)
    right_barbs = [(11,2),(12,3),(11,3),(12,4),(11,4),(11,5),(10,5),
                   (10,6),(9,6),(9,7),(8,7),(8,8),(7,8),(7,9),(6,9)]
    for (x, y) in right_barbs:
        px(img, x, y, L)

    # Fill barbs
    for sx, sy in shaft[:8]:
        for bx in range(sx+1, min(sx+4, 14)):
            if img.getpixel((bx, sy))[3] == 0:
                px(img, bx, sy, M)
        for bx in range(max(sx-3, 1), sx):
            if img.getpixel((bx, sy))[3] == 0:
                px(img, bx, sy, M)

    # Orange tip for raiko
    if A:
        px(img, 11, 2, A); px(img, 12, 2, A); px(img, 12, 3, A)

    # Purple accent for roc
    if A and mat == "roc_feather":
        px(img, 12, 3, A); px(img, 13, 4, A); px(img, 12, 5, A)

    # Quill highlight
    px(img, 10, 2, Hi)

def draw_bone_piece(img, mat):
    """Curved rib bone — reaper. Cream/tan with spectral teal crack."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    # Bone shaft — slightly curved
    shaft_rows = {
        3:(6,9), 4:(5,10), 5:(5,10), 6:(5,10),
        7:(5,10), 8:(5,10), 9:(5,10), 10:(6,10), 11:(7,9)
    }
    for y, (x0, x1) in shaft_rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Knobbed ends
    for x in range(5, 11):
        px(img, x, 2, M); px(img, x, 12, M)
    for x in range(4, 12):
        px(img, x, 3, M); px(img, x, 11, M)

    # Highlight (left face of bone)
    for y in range(4, 11): px(img, 5, y, L); px(img, 6, y, L)
    for x in range(6, 10): px(img, x, 3, L)

    # Shadow (right face)
    for y in range(4, 11): px(img, 9, y, Sh); px(img, 10, y, Sh)

    # Top/bottom end caps highlight
    px(img, 7, 2, Hi); px(img, 8, 2, Hi)

    # Spectral teal crack
    if A:
        crack = [(7,5),(8,6),(7,7),(6,8),(7,9)]
        for (x, y) in crack: px(img, x, y, A)

def draw_silk_clump(img, mat):
    """Icy silk web clump — frostweaver. Like Lycanites' frostyfur reference."""
    O, Sh, M, L, Hi = PAL[mat]

    # Roughly circular clump
    rows = {3:(6,10), 4:(5,11), 5:(4,12), 6:(4,12), 7:(4,12),
            8:(4,12), 9:(5,11), 10:(6,10), 11:(7,9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Icy crystal texture — bright spots scattered
    for y in range(4, 11):
        for x in range(5, 12):
            if img.getpixel((x, y))[3] > 0:
                if (x*3 + y*2) % 7 == 0:
                    px(img, x, y, Hi)
                elif (x + y*3) % 5 == 0:
                    px(img, x, y, Sh)

    # Web strand lines (cross pattern)
    for x in range(5, 12): px(img, x, 7, L)
    for y in range(4, 11): px(img, 8, y, L)

    # Bright ice highlights
    px(img, 6, 5, Hi); px(img, 7, 4, Hi); px(img, 10, 6, Hi)

    # Shadow underside
    for x in range(5, 12):
        if img.getpixel((x, 10))[3] > 0: px(img, x, 10, Sh)

def draw_scale(img, mat):
    """Single large creature scale — remobra, argus, salamander."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    # Scale shape — teardrop / rhombus, pointed at bottom
    rows = {2:(7,9), 3:(6,10), 4:(5,11), 5:(5,11), 6:(5,11),
            7:(5,11), 8:(5,11), 9:(6,10), 10:(7,9), 11:(8,8)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Highlight curved top ridge
    for x in range(6, 10): px(img, x, 3, L); px(img, x, 4, L)
    for x in range(7, 9):  px(img, x, 2, Hi)

    # Shadow underside
    for x in range(6, 10):
        if img.getpixel((x, 10))[3] > 0: px(img, x, 10, Sh)
    for x in range(7, 9):
        if img.getpixel((x, 11))[3] > 0: px(img, x, 11, Sh)

    # Scale surface texture (concave lines)
    for x in range(5, 12):
        if img.getpixel((x, 6))[3] > 0: px(img, x, 6, Sh)
    for x in range(6, 11):
        if img.getpixel((x, 8))[3] > 0: px(img, x, 8, Sh)

    # Shadow right side
    for y in range(3, 11):
        if img.getpixel((10, y))[3] > 0: px(img, 10, y, Sh)

    # Magenta glow spots for argus
    if A and mat == "argus_scale":
        px(img, 7, 5, A); px(img, 9, 7, A); px(img, 7, 9, A)

def draw_crustacean_shell(img, mat):
    """Curved shell piece — jouste (amber), krake (crimson + navy)."""
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    # Shell dome shape — crescent/cap
    rows = {2:(5,11), 3:(4,12), 4:(3,13), 5:(3,13), 6:(3,13),
            7:(4,12), 8:(5,11), 9:(6,10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Shell ridge lines (segmented crustacean look)
    for x in range(4, 12):
        if img.getpixel((x, 4))[3] > 0: px(img, x, 4, Sh)
        if img.getpixel((x, 6))[3] > 0: px(img, x, 6, Sh)
        if img.getpixel((x, 8))[3] > 0: px(img, x, 8, Sh)

    # Highlight across top ridge
    for x in range(5, 11): px(img, x, 3, L)
    for x in range(6, 10): px(img, x, 2, Hi)

    # Shadow underside
    for x in range(5, 11):
        if img.getpixel((x, 8))[3] > 0: px(img, x, 8, Sh)

    # Navy blue fin/edge accent for krake
    if A:
        for y in range(3, 9):
            px(img, 3, y, A); px(img, 12, y, A)
        px(img, 4, 2, A); px(img, 11, 2, A)

def draw_bark_chunk(img, mat):
    """Rough bark piece — spriggan. Dark brown with visible wood grain."""
    O, Sh, M, L, Hi = PAL[mat]

    # Irregular chunk shape
    rows = {3:(5,11), 4:(4,12), 5:(3,12), 6:(3,13), 7:(3,12),
            8:(4,12), 9:(4,11), 10:(5,10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Wood grain — horizontal lines
    for y in [4, 6, 8, 10]:
        for x in range(3, 13):
            if img.getpixel((x, y))[3] > 0:
                px(img, x, y, Sh)

    # Bark highlight cracks (lighter lines)
    for y in [5, 7, 9]:
        for x in range(4, 12, 3):
            if img.getpixel((x, y))[3] > 0:
                px(img, x, y, L)

    # Rough edge notches
    for pos in [(3,5),(3,8),(12,4),(12,7),(5,3),(10,3)]:
        px(img, pos[0], pos[1], Sh)

    px(img, 5, 4, Hi); px(img, 6, 5, L)

def draw_membrane(img, mat):
    """Ray wing membrane — ioray. Deep navy with bright blue cell pattern."""
    O, Sh, M, L, Hi = PAL[mat]

    # Wing membrane — wide, thin, pointed tips
    rows = {4:(3,13), 5:(2,14), 6:(2,14), 7:(3,13), 8:(4,12),
            9:(5,11), 10:(6,10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1+1):
            px(img, x, y, M)

    # Cell scale pattern (hexagonal dots) — the ioray's distinctive look
    cell_positions = [
        (4,5),(7,5),(10,5),(13,5),
        (3,7),(6,7),(9,7),(12,7),
        (5,9),(8,9),(11,9),
    ]
    for (cx, cy) in cell_positions:
        if img.getpixel((cx, cy))[3] > 0:
            px(img, cx, cy, Hi)
            for dx, dy in ((-1,0),(1,0),(0,-1),(0,1)):
                nx, ny = cx+dx, cy+dy
                if 0<=nx<16 and 0<=ny<16 and img.getpixel((nx,ny))[3]>0:
                    px(img, nx, ny, L)

    # Membrane sheen — top edge brighter
    for x in range(3, 13):
        if img.getpixel((x, 4))[3] > 0: px(img, x, 4, L)
    for x in range(4, 12):
        if img.getpixel((x, 5))[3] > 0: px(img, x, 5, L)

    # Shadow underside
    for x in range(4, 12):
        if img.getpixel((x, 9))[3] > 0: px(img, x, 9, Sh)

# ── Dispatch table ────────────────────────────────────────────────────────────
DROP_FN = {
    "warg_pelt":        draw_fur_tuft,
    "barghest_pelt":    draw_fur_tuft,
    "dawon_pelt":       draw_tiger_pelt,
    "vespid_chitin":    draw_chitin_shard,
    "maka_hide":        draw_hide_patch,
    "yale_hide":        draw_hide_patch,
    "roc_feather":      draw_feather,
    "raiko_feather":    draw_feather,
    "reaper_bone":      draw_bone_piece,
    "frostweaver_silk": draw_silk_clump,
    "remobra_scale":    draw_scale,
    "argus_scale":      draw_scale,
    "salamander_scale": draw_scale,
    "jouste_shell":     draw_crustacean_shell,
    "krake_shell":      draw_crustacean_shell,
    "spriggan_bark":    draw_bark_chunk,
    "ioray_membrane":   draw_membrane,
}

# ── Armor icon shapes ─────────────────────────────────────────────────────────

def armor_fill(img, rows_map, O, Sh, M, L, Hi, A=None, stripe=False):
    """Fill armor shape with dithered creature-texture look."""
    for y, (x0, x1) in rows_map.items():
        for x in range(x0, x1+1):
            # Dither pattern based on creature surface type
            t = (x + y) % 4
            if t == 0: px(img, x, y, Sh)
            elif t == 2: px(img, x, y, L)
            else: px(img, x, y, M)
    # Highlight along top-left interior
    for y, (x0, x1) in rows_map.items():
        px(img, x0+1, y, L)
    # Shadow right edge
    for y, (x0, x1) in rows_map.items():
        if x1-x0 > 2: px(img, x1-1, y, Sh)
    # Accent stripe if specified
    if A and stripe:
        for y in rows_map:
            x0, x1 = rows_map[y]
            mid = (x0+x1)//2
            if img.getpixel((mid, y))[3] > 0:
                px(img, mid, y, A)

def make_helmet(mat):
    img = canvas16()
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)
    rows = {2:(5,10), 3:(4,11), 4:(3,12), 5:(3,12),
            6:(3,12), 7:(3,12), 8:(3,12), 9:(3,12), 10:(3,12)}
    armor_fill(img, rows, O, Sh, M, L, Hi, A)
    # Visor slot
    for x in range(4, 12): px(img, x, 8, Sh); px(img, x, 9, Sh)
    for x in range(5, 10): px(img, x, 7, Sh)
    # Crown highlight
    for x in range(6, 10): px(img, x, 3, L)
    px(img, 7, 2, Hi); px(img, 8, 2, Hi)
    if A: px(img, 7, 4, A); px(img, 8, 4, A)
    outline_pass(img, O)
    return img

def make_chestplate(mat):
    img = canvas16()
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)
    rows = {1:(5,10), 2:(3,12), 3:(2,13), 4:(2,13), 5:(2,13),
            6:(2,13), 7:(2,13), 8:(2,13), 9:(2,13), 10:(2,13), 11:(2,13), 12:(3,12)}
    armor_fill(img, rows, O, Sh, M, L, Hi, A)
    # Neck collar notch
    for x in [5,6,7,8,9]: px(img, x, 1, O)
    # Center line
    for y in range(2, 13): px(img, 7, y, Sh); px(img, 8, y, Sh)
    # Shoulder highlight
    for y in range(2, 7): px(img, 3, y, L)
    px(img, 4, 2, Hi)
    if A:
        for y in [4, 7, 10]: px(img, 7, y, A); px(img, 8, y, A)
    outline_pass(img, O)
    return img

def make_leggings(mat):
    img = canvas16()
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)
    # Waist band
    for y in range(1, 4):
        for x in range(2, 14): px(img, x, y, M)
    # Left leg
    for y in range(4, 14):
        for x in range(2, 8): px(img, x, y, M)
    # Right leg
    for y in range(4, 14):
        for x in range(8, 14): px(img, x, y, M)
    # Apply dither
    for y in range(1, 14):
        for x in range(2, 14):
            if img.getpixel((x, y)) == M:
                t = (x + y) % 4
                if t == 0: px(img, x, y, Sh)
                elif t == 2: px(img, x, y, L)
    # Gap between legs
    for y in range(4, 14): px(img, 7, y, T); px(img, 8, y, T)
    # Highlights
    for y in range(2, 14):
        if img.getpixel((3, y))[3] > 0: px(img, 3, y, L)
        if img.getpixel((9, y))[3] > 0: px(img, 9, y, L)
    if A:
        for y in [3, 6, 9, 12]:
            for x in [4, 10]:
                if img.getpixel((x, y))[3] > 0: px(img, x, y, A)
    outline_pass(img, O)
    return img

def make_boots(mat):
    img = canvas16()
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)
    # Shaft
    for y in range(2, 9):
        for x in range(3, 10): px(img, x, y, M)
    # Foot
    for y in range(8, 13):
        for x in range(3, 13): px(img, x, y, M)
    # Apply dither
    for y in range(2, 13):
        for x in range(3, 13):
            if img.getpixel((x, y)) == M:
                t = (x + y) % 4
                if t == 0: px(img, x, y, Sh)
                elif t == 2: px(img, x, y, L)
    # Sole shadow
    for x in range(3, 13):
        if img.getpixel((x, 12))[3] > 0: px(img, x, 12, Sh)
    # Ankle highlight
    for y in range(3, 8): px(img, 4, y, L)
    px(img, 5, 3, Hi); px(img, 4, 3, Hi)
    if A:
        for y in [4, 7]: px(img, 6, y, A); px(img, 7, y, A)
    outline_pass(img, O)
    return img

# ── Armor layer textures (64x32) ──────────────────────────────────────────────

def armor_layer(mat, layer):
    img = Image.new("RGBA", (64, 32), T)
    O, Sh, M, L, Hi = PAL[mat]
    A = ACCENT.get(mat)

    def fill_region(x0, y0, x1, y1):
        for y in range(y0, y1):
            for x in range(x0, x1):
                t = (x + y) % 4
                if t == 0: col = Sh
                elif t == 2: col = L
                else: col = M
                img.putpixel((x, y), col)
        # Top highlight row
        for x in range(x0, x1): img.putpixel((x, y0), L)
        # Left highlight col
        for y in range(y0, y1): img.putpixel((x0, y), L)
        # Right shadow col
        for y in range(y0, y1): img.putpixel((x1-1, y), Sh)
        # Bottom shadow row
        for x in range(x0, x1): img.putpixel((x, y1-1), Sh)
        # Outline
        for x in range(x0, x1):
            img.putpixel((x, y0), O); img.putpixel((x, y1-1), O)
        for y in range(y0, y1):
            img.putpixel((x0, y), O); img.putpixel((x1-1, y), O)
        # Accent stripe
        if A:
            mx = (x0+x1)//2
            for y in range(y0+1, y1-1): img.putpixel((mx, y), A)

    if layer == 1:
        # Head
        fill_region(0,0,8,8); fill_region(8,0,24,8)
        fill_region(16,0,24,8); fill_region(24,0,32,8)
        fill_region(8,8,24,16); fill_region(0,8,8,16)
        # Body
        fill_region(16,16,28,20); fill_region(20,20,28,32)
        fill_region(16,20,20,32); fill_region(28,20,32,32); fill_region(32,20,40,32)
        # Right leg
        fill_region(0,16,4,20); fill_region(4,16,8,20)
        fill_region(0,20,4,32); fill_region(4,20,8,32)
        fill_region(8,20,12,32); fill_region(12,20,16,32)
        # Right arm
        fill_region(40,16,44,20); fill_region(44,16,48,20)
        fill_region(40,20,44,32); fill_region(44,20,48,32)
        fill_region(48,20,52,32); fill_region(52,20,56,32)
    else:
        # Leggings
        fill_region(0,16,4,20); fill_region(4,16,8,20)
        fill_region(0,20,4,32); fill_region(4,20,8,32)
        fill_region(8,20,12,32); fill_region(12,20,16,32)
        fill_region(16,16,20,20); fill_region(20,16,24,20)
        fill_region(16,20,20,32); fill_region(20,20,24,32)
        fill_region(24,20,28,32); fill_region(28,20,32,32)
        fill_region(0,16,28,20)

    return img

# ── Crop-based armor layers (samples actual creature textures) ────────────────

def _shade_tile(tile, factor):
    out = Image.new("RGBA", tile.size)
    for y in range(tile.height):
        for x in range(tile.width):
            r, g, b, a = tile.getpixel((x, y))
            r = min(255, int(r * factor))
            g = min(255, int(g * factor))
            b = min(255, int(b * factor))
            out.putpixel((x, y), (r, g, b, a))
    return out

def _paste_region(sheet, base_tile, x0, y0, x1, y1, shade, outline_col):
    w, h = x1 - x0, y1 - y0
    if w <= 0 or h <= 0:
        return
    tile = base_tile.resize((w, h), Image.LANCZOS)
    if abs(shade - 1.0) > 0.01:
        tile = _shade_tile(tile, shade)
    sheet.paste(tile, (x0, y0))
    for x in range(x0, x1):
        sheet.putpixel((x, y0),     outline_col)
        sheet.putpixel((x, y1 - 1), outline_col)
    for y in range(y0, y1):
        sheet.putpixel((x0, y),     outline_col)
        sheet.putpixel((x1 - 1, y), outline_col)

def armor_layer_from_crop(mat, layer):
    """Build a 64x32 armor sheet by tiling a cropped creature texture patch."""
    path, crop_box = CREATURE_CROPS[mat]
    creature = Image.open(path).convert("RGBA")
    base_tile = creature.crop(crop_box)
    O = PAL[mat][0]

    sheet = Image.new("RGBA", (64, 32), T)
    pr = lambda x0, y0, x1, y1, shade: _paste_region(sheet, base_tile, x0, y0, x1, y1, shade, O)

    if layer == 1:
        # Head top/bottom caps
        pr(8,  0, 16,  8, 1.20)   # head top
        pr(16, 0, 24,  8, 0.70)   # head bottom
        pr(0,  0,  8,  8, 0.85)   # (hat overlay slot — fill neutral)
        pr(24, 0, 32,  8, 0.75)   # (filler)
        # Head faces
        pr(0,  8,  8, 16, 0.85)   # head right
        pr(8,  8, 16, 16, 1.00)   # head front
        pr(16, 8, 24, 16, 0.85)   # head left
        pr(24, 8, 32, 16, 0.70)   # head back
        # Body
        pr(20, 16, 28, 20, 1.15)  # body top
        pr(28, 16, 36, 20, 0.70)  # body bottom
        pr(16, 20, 20, 32, 0.85)  # body right
        pr(20, 20, 28, 32, 1.00)  # body front
        pr(28, 20, 32, 32, 0.85)  # body left
        pr(32, 20, 40, 32, 0.70)  # body back
        # Right leg
        pr(4,  16,  8, 20, 1.15)  # right leg top
        pr(8,  16, 12, 20, 0.70)  # right leg bottom
        pr(0,  20,  4, 32, 0.85)  # right leg right
        pr(4,  20,  8, 32, 1.00)  # right leg front
        pr(8,  20, 12, 32, 0.85)  # right leg left
        pr(12, 20, 16, 32, 0.70)  # right leg back
        # Right arm
        pr(44, 16, 48, 20, 1.15)  # right arm top
        pr(48, 16, 52, 20, 0.70)  # right arm bottom
        pr(40, 20, 44, 32, 0.85)  # right arm right
        pr(44, 20, 48, 32, 1.00)  # right arm front
        pr(48, 20, 52, 32, 0.85)  # right arm left
        pr(52, 20, 56, 32, 0.70)  # right arm back
    else:
        # Leggings — waist band + both legs
        pr(0,  16, 28, 20, 1.00)  # waist band
        # Right leg
        pr(0,  20,  4, 32, 0.85)
        pr(4,  20,  8, 32, 1.00)
        pr(8,  20, 12, 32, 0.85)
        pr(12, 20, 16, 32, 0.70)
        # Left leg
        pr(16, 20, 20, 32, 0.85)
        pr(20, 20, 24, 32, 1.00)
        pr(24, 20, 28, 32, 0.85)
        pr(28, 20, 32, 32, 0.70)

    return sheet

# ── Generate everything ───────────────────────────────────────────────────────

ALL_MATS = list(PAL.keys())

print("=== Drop icons ===")
for mat, fn in DROP_FN.items():
    img = canvas16()
    fn(img, mat)
    outline_pass(img, PAL[mat][0])
    save(img, f"{ITEM_DIR}/{mat}.png")
    print(f"  {mat}.png")

print("\n=== Armor item icons ===")
PIECES = {"helmet": make_helmet, "chestplate": make_chestplate,
          "leggings": make_leggings, "boots": make_boots}
for mat in ALL_MATS:
    for piece, fn in PIECES.items():
        img = fn(mat)
        save(img, f"{ITEM_DIR}/{mat}_{piece}.png")
    print(f"  {mat}_*.png")

print("\n=== Armor layers ===")
for mat in ALL_MATS:
    for layer in [1, 2]:
        if mat in CREATURE_CROPS:
            img = armor_layer_from_crop(mat, layer)
        else:
            img = armor_layer(mat, layer)
        save(img, f"{ARMOR_DIR}/{mat}_layer_{layer}.png")
    print(f"  {mat}_layer_*.png")

print(f"\nDone. {len(DROP_FN)} drop icons, {len(ALL_MATS)*4} armor icons, {len(ALL_MATS)*2} armor layers.")
