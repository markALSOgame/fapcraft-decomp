#!/usr/bin/env python3
"""Regenerate mod/ from pristine decompiled_src/ applying classes.json.

Token-aware class renamer: skips string/char literals and comments, and only
renames an identifier when it is used in a *type position* (declaration,
generics, cast, static qualifier, `new`, extends/implements/instanceof/throws,
constructor/class decl in the owning file). Member accesses like `this.o`,
`var.o` are left untouched (members are renamed in a separate members.json pass).

Idempotent: always reads from decompiled_src/.
"""
import json
import os
import re
import sys

MODIFIER_WORDS = frozenset(
    (
        "public", "protected", "private", "static", "final", "abstract",
        "synchronized", "native", "transient", "volatile", "strictfp",
        "default",
    )
)

# MC/Forge parent chain for classes the mod's entities extend (1.12.2);
# used to resolve signature-dict overloads by ancestor type (e.g. a call
# `GirlRegistry.a(this)` where `this` is an em -> the `Entity` overload).
MC_SUPERS = {
    "Entity": None,
    "EntityLivingBase": "Entity",
    "EntityLiving": "EntityLivingBase",
    "EntityCreature": "EntityLiving",
    "EntityMob": "EntityCreature",
    "EntityAgeable": "EntityCreature",
    "EntityAnimal": "EntityAgeable",
    "EntityPlayer": "EntityLivingBase",
    "EntityPlayerMP": "EntityPlayer",
    "EntityPlayerSP": "EntityPlayer",
    "EntityOtherPlayerMP": "EntityPlayer",
    "EntityTameable": "EntityAnimal",
    "EntityNPC": "EntityCreature",
    "EntityFlying": "EntityLiving",
    "EntitySlime": "EntityLiving",
    "EntityRideable": "EntityLiving",
}

# Well-known MC field types for type-inference fallback (when the owner is a
# vanilla class that isn't in decompiled_src).  Key = (owner-simple, field).
MC_FIELD_TYPES = {
    ("RayTraceResult", "field_72308_g"): "Entity",  # entityHit
    ("ItemArmor", "field_77881_a"): "EntityEquipmentSlot",  # armorType
    ("Minecraft", "player"): "EntityPlayerSP",
    ("Entity", "rand"): "Random",
    ("Minecraft", "world"): "WorldClient",
    ("PlayerLoggedOutEvent", "player"): "EntityPlayerMP",
    ("Vec3d", "x"): "double",
    ("Vec3d", "y"): "double",
    ("Vec3d", "z"): "double",
    ("Minecraft", "objectMouseOver"): "RayTraceResult",
    ("EntityPlayerSP", "capabilities"): "PlayerCapabilities",
    ("Vec3d", "field_186680_a"): "Vec3d",  # ZERO
    ("EntityLivingBase", "field_70760_ar"): "float",  # renderYawOffset
    ("EntityLivingBase", "field_70761_aq"): "float",  # rotationYawHead
    ("RayTraceResult", "field_72307_f"): "Vec3d",   # hitVec
    ("Entity", "field_70170_p"): "World",           # world
    ("Minecraft", "field_71439_g"): "EntityPlayerSP",  # thePlayer
    ("EntityLivingBase", "field_70758_at"): "float",   # rotationYawHead-ish
    ("EntityLivingBase", "field_70759_as"): "float",   # rotationYawHead
    ("EntityLivingBase", "field_70125_A"): "float",    # rotationPitch
    ("EntityLivingBase", "field_70127_C"): "float",    # prevRotationPitch
    ("Entity", "field_70165_t"): "double",          # posX
    ("Entity", "field_70163_u"): "double",          # posY
    ("Entity", "field_70173_aa"): "int",            # ticksExisted
    ("Entity", "field_70161_v"): "double",          # posZ
    ("Entity", "field_70142_S"): "double",          # prevPosX
    ("Entity", "field_70137_T"): "double",          # prevPosY
    ("Entity", "field_70136_U"): "double",          # prevPosZ
    ("Entity", "field_70169_q"): "double",          # lastTickPosX
    ("Entity", "field_70167_r"): "double",          # lastTickPosY
    ("Entity", "field_70166_s"): "double",          # lastTickPosZ
    ("Entity", "field_70177_z"): "float",           # rotationYaw
    ("Entity", "field_70125_A"): "float",           # rotationPitch
    ("Entity", "field_70126_B"): "float",           # prevRotationPitch
    ("Entity", "field_70127_C"): "float",           # prevRotationYaw
    ("EntityLivingBase", "field_70761_aq"): "float",  # rotationYawHead
    ("EntityLivingBase", "field_70760_ar"): "float",  # prevRotationYawHead
("EntityLivingBase", "field_70721_aZ"): "float",  # swingProgress
    ("EntityLivingBase", "field_184618_aE"): "float", # limbSwingAmount
    ("EntityLivingBase", "field_184619_aG"): "float", # prevLimbSwingAmount
    ("Vec3d", "field_72450_a"): "double",   # xCoord
    ("Vec3d", "field_72448_b"): "double",   # yCoord
    ("Vec3d", "field_72449_c"): "double",   # zCoord
    ("IGeoRenderer", "MATRIX_STACK"): "MatrixStack",
    ("RenderWorldLastEvent", "renderTickTime"): "float",
}

# Well-known MC method return types for type-inference fallback.
MC_METHOD_RETURNS = {
    ("PlayerSleepInBedEvent", "getPos"): "BlockPos",
    ("Entity", "getPersistentID"): "UUID",
    ("Entity", "func_110124_au"): "UUID",  # getPersistentID obf
    ("Entity", "func_174791_d"): "Vec3d",  # getPositionVector
    ("Entity", "func_174824_e"): "Vec3d",  # getPositionEyes
    ("Entity", "func_70040_Z"): "Vec3d",   # getLook
    ("EntityLivingBase", "func_70694_bT"): "ItemStack",  # getHeldItemMainhand
    ("Entity", "func_184187_bx"): "Entity",  # getRidingEntity
    ("Entity", "func_130014_f_"): "World",   # getEntityWorld
    ("World", "func_82737_E"): "long",      # getTotalWorldTime
    ("Minecraft", "func_184121_ak"): "float",   # getPartialTicks
    ("Entity", "func_184121_ak"): "int",        # generic Entity tick counter
    ("Entity", "getPartialTicks"): "float",
    ("RenderWorldLastEvent", "getPartialTicks"): "float",
    ("Entity", "func_70047_e"): "float",        # getEyeHeight
    ("Vec3d", "func_72441_c"): "Vec3d",         # add
    ("Vec3d", "func_72432_b"): "Vec3d",         # getYawVector
    ("Minecraft", "func_71410_x"): "Minecraft",  # getMinecraft
    ("PlayerSPPushOutOfBlocksEvent", "getEntityPlayer"): "EntityPlayer",
    ("RightClickBlock", "getHitVec"): "Vec3d",
    ("RightClickBlock", "getFace"): "EnumFacing",
    ("EntityInteract", "getHitVec"): "Vec3d",
    ("EntityInteract", "getFace"): "EnumFacing",
    ("GeoBone", "getRotationX"): "float",
    ("GeoBone", "getRotationY"): "float",
    ("GeoBone", "getRotationZ"): "float",
    ("ItemArmor", "func_82812_d"): "ArmorMaterial",  # getArmorMaterial
    # MCP-name era entries (late inference sees post-MCP text)
    ("Minecraft", "getRenderPartialTicks"): "float",
    ("World", "getPlayerEntityByUUID"): "EntityPlayer",
    ("EntityPlayer", "getPlayerEntityByUUID"): "EntityPlayer",
    ("EntityPlayerSP", "getLookVec"): "Vec3d",
    ("Entity", "getLookVec"): "Vec3d",
    ("Vec3d", "subtract"): "Vec3d",
    ("String", "split"): "String[]",
    ("EntityLivingBase", "getDistance"): "float",
    ("Entity", "getDistance"): "float",
    ("ArrayList", "size"): "int",
    ("List", "size"): "int",
    ("Map", "size"): "int",
    ("Set", "size"): "int",
    ("Collection", "size"): "int",
    ("Tessellator", "func_178181_a"): "Tessellator",  # getInstance
    ("Properties", "getProperty"): "String",
    ("GeoBone", "getName"): "String",
    ("GeoModel", "getName"): "String",
    ("IBone", "getName"): "String",
    ("File", "getAbsolutePath"): "String",
    ("File", "getName"): "String",
    ("File", "getPath"): "String",
    ("Object", "toString"): "String",
    ("String", "trim"): "String",
    ("String", "replace"): "String",
    ("String", "substring"): "String",
    ("String", "toLowerCase"): "String",
    ("String", "toUpperCase"): "String",
    ("AnimationEvent", "getPartialTick"): "float",
    ("GirlGeoModel", "getAnimationProcessor"): "AnimationProcessor",
    ("GirlGeoModel", "func_188138_a"): "AnimationProcessor",
    ("Float", "floatValue"): "float",
    ("Double", "doubleValue"): "double",
    ("Integer", "intValue"): "int",
    ("Long", "longValue"): "long",
    ("Boolean", "booleanValue"): "boolean",
    ("Byte", "byteValue"): "byte",
    ("Short", "shortValue"): "short",
}
_STATIC_OWNERS = {("Math", n): t for n, t in
    [("atan2", "double"), ("sin", "double"), ("cos", "double"), ("tan", "double"),
     ("sqrt", "double"), ("abs", "double"), ("min", "double"), ("max", "double"),
     ("pow", "double"), ("floor", "double"), ("ceil", "double"), ("toRadians", "double"),
     ("toDegrees", "double"), ("random", "double"), ("log", "double")]}
_STATIC_OWNERS[("String", "format")] = "String"
_STATIC_OWNERS[("String", "valueOf")] = "String"
_STATIC_OWNERS[("EnumParticleTypes", "func_186831_a")] = "EnumParticleTypes"
for _rm in ("nextFloat",):
    _STATIC_OWNERS[("Random", _rm)] = "float"
for _ri in ("nextInt",):
    _STATIC_OWNERS[("Random", _ri)] = "int"
for _mh, _mt in (("sin", "float"), ("cos", "float"), ("sqrt", "double"),
                 ("clamp_float", "float")):
    _STATIC_OWNERS[("MathHelper", _mh)] = _mt
for _intm in ("parseInt", "parseLong", "parseFloat", "parseDouble", "valueOf"):
    _STATIC_OWNERS[("Integer", _intm)] = "int"
    _STATIC_OWNERS[("Long", _intm)] = "long"
_STATIC_OWNERS[("Float", "parseFloat")] = "float"
_STATIC_OWNERS[("Float", "valueOf")] = "float"
_STATIC_OWNERS[("Double", "parseDouble")] = "double"
_STATIC_OWNERS[("Double", "valueOf")] = "double"

BASE = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(BASE, "decompiled_src")
OUT = os.path.join(BASE, "mod")
META = os.path.join(BASE, "_meta")

CLS = json.load(open(os.path.join(META, "classes.json")))
MEM = {}
for _sect in json.load(open(os.path.join(META, "members.json"))).values():
    if isinstance(_sect, dict):
        for _owner, _entry in _sect.items():
            dst = MEM.setdefault(_owner, {})
            for _map, _vals in _entry.items():
                dst.setdefault(_map, {}).update(_vals)
for _om in MEM.values():
    _om.setdefault("types", {})
    _om.setdefault("methods", {})
    _om.setdefault("fields", {})
NEW2OLD = {v: k for k, v in CLS.items() if v != k}

# `static T a(T v){return v;}` exception-rethrow helpers across ALL sexmod
# classes (mapped or not). Cross-file `throw Owner.a(exc)` calls are renamed to
# `Owner.rethrow(exc)` when the receiver's own source declares such a helper.
_RTH_RE = re.compile(
    r"static\s+([A-Za-z_$][\w$]*)\s+([a-z][\w$]*)\s*\(\s*\1\s+([A-Za-z_$][\w$]*)\s*\)\s*\{\s*return\s+\3\s*;\s*\}"
)
_RTH_CACHE = {}


def rethrow_helper_names(owner_token):
    old = NEW2OLD.get(owner_token, owner_token)
    if old not in _RTH_CACHE:
        names = set()
        p = os.path.join(_PKG, old + ".java")
        if os.path.exists(p):
            t = open(p, encoding="utf-8", errors="replace").read()
            names = {m.group(2) for m in _RTH_RE.finditer(t)}
        _RTH_CACHE[old] = names
    return _RTH_CACHE[old]

# Inheritance table: new-name -> immediate parent new-name (only for mapped
# sexmod classes; MC parents like EntityCreature resolve via MC_SUPERS).
SUPERS = {}
_PKG = os.path.join(SRC, "com", "trolmastercard", "sexmod")
IMPLEMENTS = {}
for _old, _new in CLS.items():
    _p = os.path.join(_PKG, _old + ".java")
    if not os.path.exists(_p):
        continue
    _t = open(_p, encoding="utf-8", errors="replace").read()
    _m = re.search(
        r"\bclass\s+" + _old + r"\b(?:<[^>]*>)?\s*[^{]*?\bextends\s+([\w.]+)", _t
    )
    if _m:
        _parent_old = _m.group(1).split(".")[-1]
        SUPERS[_new] = CLS.get(_parent_old, _parent_old)
    _im = re.search(r"\bclass\s+" + _old + r"\b[^{]*?\bimplements\s+([\w.$,\s]+?)(?:\{|$)", _t)
    if _im:
        _ifaces = [i.strip().split(".")[-1] for i in _im.group(1).split(",") if i.strip()]
        _ifaces = [CLS.get(i, i) for i in _ifaces]
        _ifaces = [i for i in _ifaces if i in CLS.values() or i in CLS]
        if _ifaces:
            IMPLEMENTS[_new] = _ifaces

for _n in ("ManglelieNpc", "GalathNpc", "KoboldNpc", "GoblinNpc"):
    if _n not in SUPERS:
        SUPERS[_n] = "GirlEntity"

ID = re.compile(r"[A-Za-z_$][A-Za-z0-9_$]*")
# decompiled local/param tokens (`varN`, `varN_8`, `param2`, `var12_14`...).
DECOMP_LOCAL = re.compile(r"^(?:var|param)\d+\w*$")
KW = {"new", "extends", "implements", "instanceof", "throws", "class", "interface", "enum"}
MODS = {"public", "protected", "private"}


def scan(text):
    """Yield (start, end, word) for identifiers outside strings/comments."""
    i, n = 0, len(text)
    while i < n:
        ch = text[i]
        if ch in " \t\r\n":
            i += 1
            continue
        if ch == "/" and i + 1 < n and text[i + 1] == "/":
            j = text.find("\n", i)
            i = n if j == -1 else j + 1
            continue
        if ch == "/" and i + 1 < n and text[i + 1] == "*":
            j = text.find("*/", i + 2)
            i = n if j == -1 else j + 2
            continue
        if ch == '"':
            i += 1
            while i < n:
                if text[i] == "\\":
                    i += 2
                    continue
                if text[i] == '"':
                    i += 1
                    break
                i += 1
            continue
        if ch == "'":
            i += 1
            while i < n:
                if text[i] == "\\":
                    i += 2
                    continue
                if text[i] == "'":
                    i += 1
                    break
                i += 1
            continue
        m = ID.match(text, i)
        if m:
            # skip Java numeric-literal suffixes (0.0f, 1.5F, 100L, 0x1F): a
            # single suffix letter directly after a digit must not be treated
            # as an identifier (else `0.0f` -> `0.0StartAction`).
            if (
                m.group() in ("f", "F", "d", "D", "l", "L")
                and m.start() > 0
                and text[m.start() - 1].isdigit()
            ):
                i = m.end()
                continue
            yield (m.start(), m.end(), m.group())
            i = m.end()
            continue
        i += 1


def prev_word(before):
    m = None
    for m in ID.finditer(before):
        pass
    if not m:
        return None, ""
    return m.group(), before[m.start() - 1] if m.start() > 0 else ""


def is_keyword(pw, pwch):
    """pw is a keyword directly qualifying the token (ignore Foo.class literal)."""
    if pw in ("new", "extends", "implements", "instanceof", "throws"):
        return True
    if pw in ("class", "interface", "enum") and pwch != ".":
        return True
    return False


def last_sig(before):
    s = before.rstrip()
    return s[-1] if s else ""


def first_sig(after):
    m = re.match(r"[ \t\r\n]*(\S)", after)
    return m.group(1) if m else ""


def after_paren_is_brace(after):
    """True if the text after token starts with '(' whose matching ')' is
    directly followed (after whitespace) by '{' — i.e. a declaration (ctor /
    method), not a call. Used to stop "types" from renaming method calls."""
    if not after or after[0] != "(":
        return False
    depth = 0
    i = 0
    n = len(after)
    while i < n:
        ch = after[i]
        if ch == "(":
            depth += 1
        elif ch == ")":
            depth -= 1
            if depth == 0:
                i += 1
                while i < n and after[i] in " \t\r\n":
                    i += 1
                return i < n and after[i] == "{"
        elif ch in ('"', "'"):
            q = ch
            i += 1
            while i < n and after[i] != q:
                if after[i] == "\\":
                    i += 1
                i += 1
        i += 1
    return False


def member_names(text):
    """Field/method names declared in the class body (brace depth 1). Decompiled
    member names are lowercase; names like `ff`/`fy` are classes, so multi-letter
    lowercase matches are still only accepted from the class-body patterns."""
    body = re.sub(r'"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'|//[^\n]*|/\*.*?\*/', "", text, flags=re.S)
    names = set()
    depth = 0
    mods = r"(?:(?:public|private|protected|static|final|transient|volatile|synchronized|abstract|native|default)\s+)+"
    fld = re.compile(r"^\s*(?:" + mods + r")?[A-Za-z_$][\w$<>\[\], .]*?\s+([a-z][\w$]*)\s*(?==|;|,)")
    mth = re.compile(r"^\s*(?:" + mods + r")(?:[A-Za-z_$][\w$<>\[\], .]*?\s+)?([a-z][\w$]*)\s*\(")
    for line in body.splitlines():
        start_depth = depth
        depth += line.count("{") - line.count("}")
        if start_depth != 1:
            continue
        ls = line.lstrip()
        if not ls or ls[0] in "*{}" or ls.startswith(("//", "class ", "interface ", "enum ")):
            continue
        m = mth.match(line)
        if m:
            names.add(m.group(1))
            continue
        m = fld.match(line)
        if m:
            names.add(m.group(1))
    return names


def owner_map(token):
    """Member map for a class-token, matching both obfuscated and post-rename names
    (incl. inner classes like `GirlHomeBuilder.HomeData` -> MEM['ax.a'])."""
    # enclosing-instance refs (`GuiClothingList.this`) are NOT class tokens —
    # they must never resolve to the outer class's own member map.
    if any(p == "this" for p in token.split(".")):
        return None
    if token.startswith("com.trolmastercard.sexmod."):
        token = token[len("com.trolmastercard.sexmod."):]
    if token in MEM:
        return MEM[token]
    if "$" in token:
        # CFR emits FQCN inner refs in bytecode form (`com.trolmastercard.sexmod.f_$a`
        # = GalathNpc$EventHandler); MEM keys use the dot form `f_.a`.
        r = owner_map(token.replace("$", "."))
        if r is not None:
            return r
    o = NEW2OLD.get(token)
    if o and o in MEM:
        return MEM[o]
    if "." in token:
        base = token.split(".")[0]
        bo = NEW2OLD.get(base) or base
        # inner class: reverse-lookup MemberBase.<inner> via types maps
        if bo in MEM:
            inner = ".".join(token.split(".")[1:])
            parts = inner.split(".")
            cur = bo
            hit = MEM[bo]
            for p in parts:
                found = None
                if "types" in hit:
                    for k, v in hit["types"].items():
                        if v == p or k == p:
                            found = k
                            break
                if found is None:
                    break
                cur += "." + found
                if cur not in MEM:
                    break
                hit = MEM[cur]
            if cur != bo and cur in MEM:
                return MEM[cur]
        om = owner_map(base)
        if om is not None:
            return om
    return None


def receiver_map(t):
    """Member map for a receiver type token, walking the SUPERS chain until a
    map is found (fall back to MC_SUPERS for minecraft types). Used for
    `varN.member`/`this.field.member` lookups where the member may be declared
    on a superclass (e.g. ei calls em's abstract float i())."""
    seen = set()
    while t and t not in seen:
        seen.add(t)
        om = owner_map(t)
        if om is not None:
            return om
        t = SUPERS.get(t) or MC_SUPERS.get(t)
    return None


def receiver_has(rmap_list, w, key):
    """Given a list of (map, type) pairs ordered from most-derived to base,
    return the map that declares `w` under section `key`, or None."""
    for m, _t in rmap_list:
        if w in m.get(key, {}):
            return m
    return None


def dot_prev(text, s):
    m = re.search(r"([A-Za-z_$][A-Za-z0-9_$]*)\s*\.\s*$", text[:s])
    return m.group(1) if m else None
    m = re.search(r"([A-Za-z_$][A-Za-z0-9_$]*)\s*\.\s*$", text[:s])
    return m.group(1) if m else None


def word_after(text, e, skip):
    m = re.match(r"[ \t\r\n]*" + re.escape(skip) + r"[ \t\r\n]*([A-Za-z_$][A-Za-z0-9_$]*)", text[e:])
    return m.group(1) if m else ""


def inner_map(own_old):
    """Return dict inner-name -> member-map for inner classes of own_old."""
    prefix = own_old + "."
    out = {}
    for k in MEM:
        if k.startswith(prefix):
            out.setdefault(k[len(prefix):], {}).update(MEM[k])
    return out


def class_scopes(text):
    """Scan text (outside strings/comments) returning inner class declarations
    at brace depth 1 (directly inside the top-level class body):
    (name_start, body_open, body_close, name) where body_open/body_close bound
    the class body (body_open = first char after the body `{`)."""
    depth = 0
    pending = None
    bodies = []
    i = 0
    n = len(text)
    while i < n:
        ch = text[i]
        if ch == "/" and i + 1 < n and text[i + 1] == "/":
            j = text.find("\n", i)
            i = n if j == -1 else j + 1
            continue
        if ch == "/" and i + 1 < n and text[i + 1] == "*":
            j = text.find("*/", i + 2)
            i = n if j == -1 else j + 2
            continue
        if ch in ('"', "'"):
            q = ch
            i += 1
            while i < n and text[i] != q:
                if text[i] == "\\":
                    i += 1
                i += 1
            i += 1
            continue
        if ch == "{":
            depth += 1
            if pending is not None and depth == 2:
                bodies.append([pending[0], i + 1, None, pending[1]])
            pending = None
        elif ch == "}":
            depth -= 1
            for b in bodies:
                if b[2] is None and depth == 1:
                    b[2] = i
        elif ch.isalpha() or ch == "_":
            m = ID.match(text, i)
            word = m.group()
            j = m.end()
            before = text[:i].rstrip()
            if depth == 1 and before.endswith(("class", "interface", "enum")):
                pending = (i, word)
            i = j
            continue
        i += 1
    return [tuple(b) for b in bodies if b[2] is not None]


def decl_params(after):
    """Parse a declaration/call parameter list `(....)` starting at after[0]=='('.
    Returns list of param *type* strings for a declaration (types before the
    trailing varN name), or the raw tokens for a call.  None if malformed."""
    if not after or after[0] != "(":
        return None
    depth = 0
    i = 0
    n = len(after)
    while i < n:
        c = after[i]
        if c == "(":
            depth += 1
        elif c == ")":
            depth -= 1
            if depth == 0:
                break
        elif c in ('"', "'"):
            q = c
            i += 1
            while i < n and after[i] != q:
                if after[i] == "\\":
                    i += 1
                i += 1
        i += 1
    if depth != 0:
        return None
    inner = after[1:i]
    parts, cur, d = [], [], 0
    for c in inner:
        if c in "(<[{":
            d += 1
        elif c in ")>]}":
            d -= 1
        if c == "," and d == 0:
            parts.append("".join(cur).strip())
            cur = []
        else:
            cur.append(c)
    if "".join(cur).strip():
        parts.append("".join(cur).strip())
    return parts


def decl_types(after):
    """Param *types* of a method declaration `(Type varN, ...)` — None if it is
    a call (args look like varN, not Type varN)."""
    parts = decl_params(after)
    if parts is None:
        return None
    out = []
    for p in parts:
        p = re.sub(r"@\w+(\([^)]*\))?", "", p).strip()
        m = list(ID.finditer(p))
        if not m:
            return None
        name = m[-1].group()
        if re.match(r"var\d+\w*|param\d+\w*", name):
            typ = p[: m[-1].start()].strip()
            if not typ:
                return None
            if "(" in typ or ")" in typ or re.search(r"[+\-*/%]", typ):
                # `(Cast)varN` — a CALL arg with a reference cast, not a
                # `Type varN` declaration.  Arithmetic junk (`60.0F * var8 *`
                # before a trailing var6) likewise.  Bail so resolve_method
                # falls through to the parts-based (call-arg) resolution.
                return None
            out.append(typ)
        else:
            # CFR-style parameter names (not varN/paramN): `int n2` / `fp fp2` —
            # a `Type Name` two-token *declaration*, never a call arg (a call
            # arg is a single expression).  Resolving the type here stops the
            # argc-only fallback in resolve_method from mis-renaming a
            # declaration whose actual type isn't in the members map.
            dm = re.match(r"^([A-Za-z_$][\w$.<>\[\],]*)\s+([A-Za-z_$][\w]*)$", p)
            if dm is None:
                return None
            out.append(dm.group(1))
    return out


_FIELD_TYPES_CACHE = {}


def resolve_field_type(owner, field):
    """Return the declared type of `field` on mapped class `owner` (by source
    scan), walking up the SUPERS chain when inherited.  Matching both
    obfuscated and post-rename names.  None if unknown."""
    o = (NEW2OLD.get(owner) or owner).rsplit(".", 1)[-1]
    key = (o, field)
    if key in _FIELD_TYPES_CACHE:
        return _FIELD_TYPES_CACHE[key]
    # the caller may pass a post-rename field name (`I`) while the source uses
    # the obfuscated one (`I`'s old name) — reverse-look it via the owner's
    # members.json fields map.
    if field not in ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"):
        mf = MEM.get(o) or MEM.get(owner)
        if mf and "fields" in mf:
            for _f_old, _f_new in mf["fields"].items():
                if _f_new == field:
                    field = _f_old
                    break
    out = None
    cur = CLS.get(o) or o
    seen = set()
    while cur and cur not in seen and out is None:
        seen.add(cur)
        old = NEW2OLD.get(cur) or cur
        # `field` may be the RENAMED name while THIS ancestor's raw source uses
        # its own obfuscated spelling (Mc declared as y on GoblinNpcRenderer,
        # queried from subclass GoblinPlayerRenderer) — convert per ancestor.
        fld_scan = field
        mf = MEM.get(old)
        if mf:
            for _fo, _fn in mf.get("fields", {}).items():
                if _fn == fld_scan:
                    fld_scan = _fo
                    break
        p = os.path.join(_PKG, old + ".java")
        if os.path.exists(p):
            t = open(p, encoding="utf-8", errors="replace").read()
            m = re.search(
                r"\b([A-Za-z_$][\w$.<>\[\], ]*?)\s+" + re.escape(fld_scan) + r"\s*(?:=|;)", t
            )
            if m:
                nm = m.group(1)
                if not (nm.startswith("var") or nm in KW or nm in MODS):
                    out = nm.split()[-1] if nm.split() else nm
                    break
        cur = SUPERS.get(cur, MC_SUPERS.get(cur))
    if out is not None and re.fullmatch(r"[A-Z]", out):
        # generic type param inherited from a parent (`protected T j` in
        # d_<T> renderer): bind via the extends clause chain (`class dj
        # extends d6<ff>` -> T=G=ff).
        out = bind_generic(CLS.get(owner) or owner, out)
    if out is None:
        # MC-class field (EntityPlayer.rotationYawHead etc.): walk the combined
        # mod-SUPERS (mapped sexmod class -> MC parent) then MC-SUPERS chain, so
        # `this.field_70170_p` on a mapped entity reaches Entity.world/EntityLiving.
        cur = CLS.get(owner) or owner
        seen = set()
        while cur and cur not in seen:
            seen.add(cur)
            out = MC_FIELD_TYPES.get((cur.rsplit(".", 1)[-1], field))
            if out is not None:
                break
            cur = SUPERS.get(cur, MC_SUPERS.get(cur))
    _FIELD_TYPES_CACHE[key] = out
    return out


def local_var_types(text):
    """Map decompiled local/param name -> declared type, SCOPED by position:
    the nearest preceding *method* signature wins (decompiled varN/paramN names
    are reused across methods; control-flow blocks like `if (...) {` are NOT
    signatures).  Returns a callable pos->{name:type}."""
    FLOW = {"if", "for", "while", "switch", "catch", "synchronized", "new"}
    sigs = []  # (signature_open_paren_pos, signature_end_pos, params)
    for m in re.finditer(r"\(([^()]*)\)\s*(?:throws\s+[\w.,<>\[\] ]+\s*)?\{", text):
        before = text[: m.start()].rstrip()
        wm = re.search(r"([A-Za-z_$][\w$]*)\s*$", before)
        if wm and wm.group(1) in FLOW:
            continue
        params = {}
        for p in m.group(1).split(","):
            pm = re.match(
                r"(?:@\w+(?:\([^)]*\))?\s*)?([A-Za-z_$][\w$.<>\[\]]*?)\s+([A-Za-z_$][\w]*)\s*$",
                p.strip(),
            )
            if pm:
                params[pm.group(2)] = pm.group(1)
        sigs.append((m.start(), m.end(), params))
    # field types: `Type fieldName;` declared anywhere in the file (outside
    # methods).  Used to resolve `this.field` argument types.
    field_types = {}
    for fm in re.finditer(
        r"([A-Za-z_$][\w$.<>\[\], ]*?)\s+([A-Za-z_$][\w]*)\s*(?:=|;)", text
    ):
        nm = fm.group(2)
        if nm in ("this", "super") or nm.startswith("var") or nm.startswith("param"):
            continue
        if nm in KW or nm in MODS or nm == "new":
            continue
        if nm in ("true", "false", "null", "return", "void", "int", "float", "double", "boolean", "long", "short", "byte", "char", "String", "for", "while", "if", "else", "switch", "case", "break"):
            continue
        typ = fm.group(1).strip()
        # the regex may have swallowed modifiers (`public static final Vec3d c`)
        # as part of group(1); strip them to leave the actual type.
        while typ.split():
            head = typ.split()[0]
            if head.endswith(",") or head in MODIFIER_WORDS or head in KW:
                sp = typ.split(maxsplit=1)
                if len(sp) == 1:
                    break
                typ = sp[1]
            else:
                break
        field_types.setdefault(nm, typ)
    import bisect
    ends = [e for _, e, _ in sigs]

    def get(pos):
        idx = bisect.bisect_right(ends, pos) - 1
        if idx < 0:
            return {}
        start, e, params = sigs[idx]
        vt = dict(params)
        seg = text[e:pos]
        for lm in re.finditer(
            r"\b([A-Za-z_$][\w$.<>\[\], ]*?)\s+(var\d+\w*)\s*(?:=(?!=)|;)", seg
        ):
            if lm.group(2) not in vt:
                vt[lm.group(2)] = lm.group(1).strip()
        # `varN = new Type(...)` — assignment-inferred local type (CFR-merged
        # bodies often reuse a var without re-declaring it).
        for lm in re.finditer(r"\b(var\d+\w*)\s*=\s*new\s+([A-Za-z_$][\w$.<>]*)\s*\(", seg):
            if lm.group(1) not in vt:
                vt[lm.group(1)] = lm.group(2).split("<")[0]
        # for-each loop vars: `for (Type varN : ...)` / `for (Type name : ...)`
        for lm in re.finditer(r"\bfor\s*\(\s*([A-Za-z_$][\w$.<>\[\]]*?)\s+([A-Za-z_$][\w]*)\s*:", seg):
            if lm.group(2) not in vt and lm.group(2) not in ("this", "super"):
                vt[lm.group(2)] = lm.group(1).strip()
        # bare field receivers: `Type field;` anywhere in the file -> vt[field].
        # Only when the simple name isn't already a param/local in scope, and
        # only lowercase single-token field names (mapped mod obfuscation).
        for fn, ft in field_types.items():
            if fn not in vt and fn[0].islower() and re.fullmatch(r"[a-z]\w*", fn):
                vt[fn] = ft
        # field-access args: `this.field` -> the field's declared type
        for fm in re.finditer(r"\bthis\.([A-Za-z_$][\w]*)", seg):
            fn = fm.group(1)
            if fn in field_types and ("this." + fn) not in vt:
                vt["this." + fn] = field_types[fn]
        # chained fields `this.X.Y...`: resolve left-to-right via field_types
        for fm in re.finditer(r"\bthis\.[A-Za-z_$][\w$.]*", seg):
            chain = fm.group(0)[5:].split(".")
            cur = None
            ok = True
            for c in chain:
                if cur is None:
                    t = field_types.get(c)
                else:
                    t = resolve_field_type(cur, c)
                if t is None:
                    ok = False
                    break
                cur = t
            if ok and cur is not None and fm.group(0) not in vt:
                vt[fm.group(0)] = cur
        return vt

    return get


def gen_keys(chains):
    """Cross-product of per-arg candidate lists -> comma-joined keys, dedup'd,
    shortest-chain-first so exact matches win over ancestor matches."""
    import itertools
    out, seen = [], set()
    collected = sorted(itertools.product(*chains), key=lambda tup: sum(tup.index(x) for x in tup))
    for tup in collected:
        k = ",".join(tup)
        if k not in seen:
            seen.add(k)
            out.append(k)
    return out


_GENERIC_BOUNDS = {}


def generic_bound(own_old, letter):
    """Resolve a generic type-parameter letter (T/G/E...) to its declared bound
    from own_old's class header (`class d_<T extends em & IAnimatable>`) — a
    param declared `T param2` must match a sig key typed as the bound."""
    if not re.fullmatch(r"[A-Z]", letter):
        return None
    key = (own_old, letter)
    if key in _GENERIC_BOUNDS:
        return _GENERIC_BOUNDS[key]
    out = None
    p = os.path.join(_PKG, own_old + ".java")
    if os.path.exists(p):
        src = open(p, encoding="utf-8", errors="replace").read()
        m = re.search(
            r"<(?:[A-Z],\s*)*" + re.escape(letter) + r"\s+extends\s+([A-Za-z_$][\w$.]*)(?:\s*&|\s*>)",
            src,
        )
        if m:
            b = m.group(1).split(".")[-1]
            out = CLS.get(b, b)
    _GENERIC_BOUNDS[key] = out
    return out


_GENERIC_BINDS_CACHE = {}


def bind_generic(subclass_new, letter):
    """Chain-resolve a generic param letter through the SUPERS hierarchy:
    `dj extends d6<ff>` + `d6<G> extends d_<G>` + `d_<T extends em>` gives
    T=G=ff for `dj` (the renderer's `protected T j` field)."""
    if not re.fullmatch(r"[A-Z]", letter):
        return None
    current = {}
    cur = subclass_new
    seen = set()
    while cur and cur not in seen:
        seen.add(cur)
        binds = generic_bindings(cur)
        parent = SUPERS.get(cur)
        nxt = {}
        for p, v in binds.items():
            if re.fullmatch(r"[A-Z]", v) and v in current:
                v = current[v]
            nxt[p] = v
        if parent and letter in nxt:
            return nxt[letter]
        current = nxt
        cur = parent
    # no extends-arg binding: fall back to the declared bound `T extends em`
    # anywhere on the SUPERS chain (dm extends d_<T extends em> -> GirlEntity).
    cur2 = subclass_new
    seen2 = set()
    while cur2 and cur2 not in seen2:
        seen2.add(cur2)
        b = generic_bound(NEW2OLD.get(cur2) or cur2, letter)
        if b:
            return b
        cur2 = SUPERS.get(cur2)
    return None


def generic_bindings(subclass_new):
    """Map generic param letters -> type args from `class Sub extends Base<P1,P2>`
    (zip Sub's extends-args against Base's declared params), so an inherited
    generic field (`protected T j` on d6<ff>) binds T=ff for `Sub`."""
    key = subclass_new
    if key in _GENERIC_BINDS_CACHE:
        return _GENERIC_BINDS_CACHE[key]
    out = {}
    o = NEW2OLD.get(subclass_new) or subclass_new
    p = os.path.join(_PKG, o + ".java")
    if os.path.exists(p):
        t = open(p, encoding="utf-8", errors="replace").read()
        m = re.search(
            r"\bclass\s+[A-Za-z_$][\w$]*(?:<[^>]*>)?\s+extends\s+([\w.]+)\s*(?:<([^>]*)>)?",
            t,
        )
        if m:
            base = m.group(1).split(".")[-1]
            args = [a.strip() for a in m.group(2).split(",") if a.strip()] if m.group(2) else []
            bo = NEW2OLD.get(base) or base
            params = []
            bp = os.path.join(_PKG, bo + ".java")
            if os.path.exists(bp):
                bt = open(bp, encoding="utf-8", errors="replace").read()
                bm = re.search(r"\bclass\s+[^{]*?<([^>]*)>", bt)
                if bm:
                    params = [p_.strip().split()[0] for p_ in bm.group(1).split(",") if p_.strip()]
            for pn, arg in zip(params, args):
                out[pn] = CLS.get(arg, arg)
    _GENERIC_BINDS_CACHE[key] = out
    return out


def method_return_type(owner, method, argc=0, arg_types=None):
    """Return declared return-type of `method` on mapped class `owner` (or any
    ancestor in the SUPERS/MC_SUPERS chain) by scanning decompiled sources.
    Matches signatures by argc, and when arg_types are given also by their
    declared param types (allowing the obfuscated-name and ancestor-chain
    fallbacks)."""
    o = (NEW2OLD.get(owner) or owner).rsplit(".", 1)[-1]
    # `method` may be the RENAMED member while decompiled_src still holds the
    # obfuscated one — reverse-look it up via each ancestor's members map as
    # the SUPERS walk proceeds (the declaring ancestor usually holds it).
    names = [method]
    first_void = None
    seen = set()
    while o and o not in seen:
        seen.add(o)
        # MEM is keyed by OBFUSCATED names — `o` may be a NEW name here.
        mf = MEM.get(o) or MEM.get(NEW2OLD.get(o) or o)
        if mf:
            for _m_old, _m_val in mf.get("methods", {}).items():
                vals = _m_val.values() if isinstance(_m_val, dict) else [_m_val]
                if method in vals and _m_old not in names:
                    names.append(_m_old)
        cur_new = CLS.get(o) or o
        cur_old = NEW2OLD.get(cur_new) or cur_new
        p = os.path.join(_PKG, cur_old + ".java")
        if os.path.exists(p):
            for ln in open(p, encoding="utf-8", errors="replace"):
                ls = ln.lstrip()
                if ls.startswith("throw ") or ls.startswith("//"):
                    continue
                m = None
                for _nm in names:
                    m = re.match(
                        r"^\s*(?:(?:public|private|protected|static|final|synchronized|abstract|native)\s+)*([A-Za-z_$][\w$.<>\[\], ]*?)\s+"
                        + re.escape(_nm)
                        + r"\(([^)]*)\)",
                        ln,
                    )
                    if m:
                        break
                if not m:
                    continue
                args = m.group(2).strip()
                n = 0 if not args else args.count(",") + 1
                if n != argc:
                    continue
                if arg_types is not None:
                    pd = []
                    for pa in args.split(","):
                        if not pa.strip():
                            continue
                        ptoks = pa.strip().split()
                        # `Type varN` or annotated `@Ann Type varN`
                        pt = ptoks[-2] if len(ptoks) >= 2 else (ptoks[-1] if ptoks else "")
                        if pt in ("var", "param") or re.fullmatch(r"var\d+\w*|param\d+\w*", pt):
                            pt = ptoks[-2] if len(ptoks) >= 2 else ""
                        pd.append(pt)
                    if len(pd) != len(arg_types):
                        continue
                    matched = True
                    for i, want in enumerate(arg_types):
                        if want is None:
                            # unresolvable arg = wildcard (partial match)
                            continue
                        got = pd[i]
                        if got == want:
                            continue
                        # obfuscated<->new equivalence (one maps to the other)
                        ok = False
                        if (NEW2OLD.get(got) or got) == want or (NEW2OLD.get(want) or want) == got:
                            ok = True
                        if not ok:
                            # walk `want` (the ARG type) up its ancestors
                            # looking for `got` (the DECLARED param type):
                            # an arg of a subtype (GoblinNpc) satisfies a
                            # param declared as its base (GirlEffectEntity).
                            cur = want
                            got_new = CLS.get(got, got)
                            seen2 = set()
                            while cur and cur not in seen2:
                                seen2.add(cur)
                                nxt = SUPERS.get(cur) or MC_SUPERS.get(cur)
                                if nxt is None:
                                    break
                                cur = nxt
                                if cur == got or cur == got_new:
                                    ok = True
                                    break
                        if not ok:
                            matched = False
                            break
                    if not matched:
                        continue
                ret = m.group(1).strip()
                if not ret or "." in ret or not ret.split():
                    continue
                last = ret.split()[-1]
                if last in MODS or last in KW:
                    continue
                if last in ("void", "boolean", "int", "long", "float",
                            "double", "short", "byte", "char"):
                    # bytecode may hold TWO same-name methods differing only
                    # by return type (decompilers print one name for both) —
                    # a primitive match is weak evidence for ARG-type
                    # inference, so keep scanning (same file + ancestors) for
                    # a reference-typed overload; fall back to the primitive.
                    if first_void is None:
                        first_void = last
                    continue
                return last
        nxt = SUPERS.get(cur_new) or MC_SUPERS.get(cur_new)
        o = nxt
    return first_void


def _depth_at(s, idx):
    """Paren/bracket depth at character position idx (for operator scans)."""
    d = 0
    for k, c in enumerate(s):
        if k == idx:
            return d
        if c in "([":
            d += 1
        elif c in ")]":
            d -= 1
    return d


def _split_toplevel(s):
    """Split on commas at paren/bracket depth 0."""
    parts, cur, d = [], [], 0
    for c in s:
        if c in "([":
            d += 1
        elif c in ")]":
            d -= 1
        if c == "," and d == 0:
            parts.append("".join(cur))
            cur = []
        else:
            cur.append(c)
    if "".join(cur).strip():
        parts.append("".join(cur))
    return parts


def resolve_expr_type(expr, vt, own_old=None):
    """Best-effort type of an argument expression:
      - varN                     -> vt
      - varN.member()            -> return type of `member` on varN's class
      - varN.member              -> field type on varN's class
      - this.X.Y[...]            -> chained field types (own file via own_old)
      - Owner.member(args)       -> return type of static call on mapped Owner
      - Owner.member             -> field type on mapped Owner
    Returns new-type string or None."""
    e = expr
    # CFR emits fully-qualified refs: `com.trolmastercard.sexmod.v.b(...)`.
    # Strip the package so the call/field branches see `v.b(...)`.
    if e.startswith("com.trolmastercard.sexmod."):
        e = e[len("com.trolmastercard.sexmod.") :]
    # `Outer.this.field` — enclosing-instance access (`GuiClothingList.this.Girl`).
    if re.match(r"^[A-Za-z_$][\w$]*\.this\.", e):
        e = "this." + e.split(".this.", 1)[1]
    if e in ("this", "super") and own_old:
        return CLS.get(own_old, own_old)
    if DECOMP_LOCAL.match(e) and e in vt:
        return vt[e]
    if e in vt:
        # any known-local name (CFR numbered primitives like `v0` included)
        return vt[e]
    # bare numeric literal -> its primitive type
    nm_lit = re.match(r"^[-+]?\d*\.?\d+[fFdD]?$", e.strip())
    if nm_lit:
        s = e.strip()
        if s.endswith(("f", "F")):
            return "float"
        if s.endswith(("d", "D")):
            return "double"
        if s.endswith(("l", "L")):
            return "long"
        if "." in s:
            return "double"
        return "int"
    # `true`/`false` -> boolean
    if e.strip() in ("true", "false"):
        return "boolean"
    # bare capitalized identifier naming a KNOWN type (cast remnant after a
    # decl_params mis-split of `(Entity)param0`, Class literal, etc.) — only
    # trusted for mapped classes and well-known MC types; unknown lowercase
    # locals must NOT resolve (they are variables with undeclared types).
    if re.match(r"^[A-Z][\w$]*$", e) and (
        e in NEW2OLD
        or e in CLS.values()
        or e in MC_SUPERS
        or e in ("String", "UUID", "Object", "Class", "Integer", "Float",
                 "Double", "Long", "Boolean", "Byte", "Short", "Number",
                 "BlockPos", "Vec3d", "Vec3i", "ItemStack", "EnumFacing",
                 "EnumHand", "World", "DimensionType", "IBlockState",
                 "EnumParticleTypes", "TextComponentString")
    ):
        return e
    # `varN[...]` / `paramN[...]` / `arrName[...]` — array element -> element type
    am = re.match(r"^([A-Za-z_$][\w$]*)\s*\[[^\]]*\]$", e)
    if am and (DECOMP_LOCAL.match(am.group(1)) or am.group(1) in vt):
        bt = vt.get(am.group(1))
        if bt:
            return bt.split("[")[0]
    # `expr.member()[idx]` — array element of a call result (e.g. `GirlEntity.
    # getModelColors(g).get(index)`, `handlePickUp(this)[9]` -> String).
    aidx = re.match(r"^(.*)\s*\[[^\]]*\]$", e)
    if aidx:
        bt = resolve_expr_type(aidx.group(1).strip(), vt, own_old)
        if bt:
            return bt.split("[")[0]
    # simple numeric arithmetic on a known var (`var8 * 0.5F`) -> that var's type
    am = re.match(
        r"^\s*(var\d+\w*|param\d+\w*)\s*[+\-*/]\s*[-+]?\d*\.?\d+[fFdD]?[ \t]*$", e
    )
    if am and am.group(1) in vt:
        return vt[am.group(1)]
    # `new Type(...)` / `new Type` -> the Type itself (checked before the
    # operator guard below — the ctor args may contain `+`).
    nm = re.match(r"^new\s+([A-Za-z_$][\w$]*)(?:<[^>]*>)?\s*(?:\(.*\))?$", e)
    if nm:
        return nm.group(1)
    # `new Type[]{...}` / `new Type[n]` array creation -> `Type[]`
    # (checked before the operator guard — `[` is a guard trigger).
    nma = re.match(
        r"^new\s+([A-Za-z_$][\w$]*)\s*(?:<[^>]*>)?\s*\[[^\]]*\]\s*(?:\{[^{}]*\})?\s*$",
        e,
    )
    if nma:
        return nma.group(1) + "[]"
    # primitive cast wrapper: `(double)expr`, `(float)expr` -> the cast type
    cm = re.match(r"^\((double|float|int|long|short|byte)\)\s*(.+)$", e)
    if cm:
        return cm.group(1)
    # reference cast: `(GirlEntity)entity` / `((GirlEntity)entity)` -> the cast
    # type (feeds chained calls like `((GirlEntity)entity).E()`).
    rc = re.match(r"^\(+([A-Za-z_$][\w$]*(?:\.[\w$]+)*)\)\s*(.+)$", e)
    if rc and rc.group(1) not in vt:
        rest = rc.group(2)
        # the value expression must be paren-balanced — `((X)y).z()` would
        # otherwise mis-parse as a cast of the garbage `y).z()`.
        _rd = 0
        _ok = True
        for _ch in rest:
            if _ch in "([":
                _rd += 1
            elif _ch in ")]":
                _rd -= 1
                if _rd < 0:
                    _ok = False
                    break
        if _ok and _rd == 0:
            t = rc.group(1)
            if t in CLS or t in NEW2OLD or t.rsplit(".", 1)[-1] in MC_SUPERS or t.rsplit(".", 1)[-1] in ("Entity", "World", "EntityLivingBase", "EntityLiving", "EntityPlayer", "EntityPlayerMP", "EntityPlayerSP", "EnumHand", "EnumFacing", "ItemStack", "BlockPos", "Vec3d", "UUID", "String", "List", "Map", "Set", "HashSet", "ArrayList"):
                return t
    # numeric literal arithmetic on a resolvable operand (`180.0f + this.
    # field_70761_aq`, `0.5f - entityPlayer.func_70047_e()`) -> operand's
    # numeric type.  Checked before the operator guard below.
    nmp = re.match(r"^[-+]?\d*\.?\d+[fFdD]?\s*[+\-]\s*(.+)$", e)
    if nmp:
        t = resolve_expr_type(nmp.group(1).strip(), vt, own_old)
        if t in ("float", "double", "int", "long", "short", "byte"):
            return t
        return None
    nmp2 = re.match(r"^(.+?)\s*[+\-]\s*[-+]?\d*\.?\d+[fFdD]?$", e)
    if nmp2:
        t = resolve_expr_type(nmp2.group(1).strip(), vt, own_old)
        if t in ("float", "double", "int", "long", "short", "byte"):
            return t
        return None
    # numeric-literal multiply/divide (`vec3d2.field_72449_c * 40.0`) -> the
    # operand's numeric type (`*`/`/` would otherwise trip the operator guard).
    nmp3 = re.match(r"^(.+?)\s*[*/]\s*[-+]?\d*\.?\d+[fFdD]?$", e)
    if nmp3:
        t = resolve_expr_type(nmp3.group(1).strip(), vt, own_old)
        if t in ("float", "double", "int", "long", "short", "byte"):
            return t
        return None
    # top-level arithmetic/comparison guard: bail only when an operator sits
    # OUTSIDE any parens/brackets — `Math.atan2(a - b, c)` is a call whose
    # ARGS contain operators and must still resolve via the call branch.
    _NUMERIC = ("double", "float", "long", "int", "short", "byte", "char")
    _RANK = {"double": 6, "float": 5, "long": 4, "int": 3, "short": 2,
             "byte": 1, "char": 0}
    _d = 0
    _bail = False
    _i = 0
    while _i < len(e):
        _c = e[_i]
        if _c in "([":
            _d += 1
        elif _c in ")]":
            _d -= 1
        elif _d == 0 and (_c in "+-*/" or e.startswith("==", _i)):
            # top-level `+` with a string literal operand is String concat
            # (`TextFormatting.YELLOW + "..."`), not arithmetic.
            if _c == "+" and '"' in e:
                return "String"
            # numeric arithmetic on resolvable operands (`f11 + f12`,
            # `60.0F * var8 * var6`) -> the widest operand type; chained
            # operators split at the FIRST top-level one and recurse.
            if not e.startswith("==", _i):
                lt = resolve_expr_type(e[:_i].strip(), vt, own_old)
                rt = resolve_expr_type(e[_i + 1:].strip(), vt, own_old)
                if lt in _NUMERIC and rt in _NUMERIC:
                    return lt if _RANK[lt] >= _RANK[rt] else rt
            _bail = True
            break
        _i += 1
    if _bail:
        return None
    # chained receiver-call: `a.b.M(...)` / `a.M1().M2(...)` — resolve the
    # receiver expression's type first, then M's return type on it.  Match the
    # OUTERMOST call: find the '(' that balances the final ')' of the string.
    if e.strip().endswith(")"):
        est = e.strip()
        depth = 0
        outer = -1
        for k in range(len(est) - 1, -1, -1):
            if est[k] == ")":
                depth += 1
            elif est[k] == "(":
                depth -= 1
                if depth == 0:
                    outer = k
                    break
        if outer > 0:
            mrecv = est[:outer].rstrip()
            mmch = re.search(r"\.([A-Za-z_$][\w]*)\s*$", mrecv)
            if mmch:
                cc = (mrecv[: mmch.start()], mmch.group(1), est[outer + 1 : -1])
                rt = resolve_expr_type(cc[0], vt, own_old)
                # `this.method(...)` — resolve return type by walking the own
                # class (and MC) SUPERS chain (GeckoLib getAnimationProcessor etc.)
                if rt is None and cc[0] == "this" and own_old:
                    rt = CLS.get(own_old, own_old)
                if rt:
                    mec_args = cc[2]
                    _margs = _split_toplevel(mec_args) if mec_args else []
                    argc = len(_margs)
                    at = [resolve_expr_type(pa, vt, own_old) for pa in _margs]
                    t = method_return_type(rt, cc[1], argc, at)
                    if t is None:
                        cur = rt.rsplit(".", 1)[-1]
                        seen = set()
                        while cur and cur not in seen:
                            seen.add(cur)
                            t = MC_METHOD_RETURNS.get((cur, cc[1]))
                            if t is not None:
                                break
                            cur = SUPERS.get(cur, MC_SUPERS.get(cur))
                    if t is None:
                        # static well-known owners (`Random.nextFloat`)
                        t = _STATIC_OWNERS.get((rt.rsplit(".", 1)[-1], mmch.group(1)))
                    if t is None:
                        # MC method-return walk (`this.girl.getDistance(...)`
                        # -> EntityLivingBase.getDistance -> float)
                        _mc = CLS.get(rt.rsplit(".", 1)[-1], rt.rsplit(".", 1)[-1])
                        _seenm = set()
                        while _mc and _mc not in _seenm:
                            _seenm.add(_mc)
                            t = MC_METHOD_RETURNS.get((_mc, mmch.group(1)))
                            if t is not None:
                                break
                            _mc = SUPERS.get(_mc, MC_SUPERS.get(_mc))
                    if t is not None:
                        return t
            else:
                rt = resolve_expr_type(mrecv, vt, own_old)
                if rt and rt.rsplit(".", 1)[-1] in ("Vec3d", "Vec3f", "Vec3i", "Vec2f"):
                    return rt
    # trailing `.toString()` (any receiver) -> String
    if e.endswith(".toString()"):
        return "String"
    # trailing `.equals(...)`/`contains(...)` etc. -> boolean
    if re.search(r"\.(equals|contains|startsWith|endsWith|isEmpty|hasNext|hasMoreElements)\([^()]*\)$", e):
        return "boolean"
    # `Owner.member(args)` / `varN.member(args)` — return type of the call
    call = re.match(r"^([A-Za-z_$][\w$]*)\.([A-Za-z_$][\w]*)\((.*)\)$", e)
    if call:
        recv, meth, args = call.group(1), call.group(2), call.group(3)
        argc = 0 if not args else args.count(",") + 1
        at = []
        if args:
            for pa in args.split(","):
                at.append(resolve_expr_type(pa, vt, own_old))
        if DECOMP_LOCAL.match(recv) or recv in vt:
            base = vt.get(recv)
            if base is None:
                return None
            owner = base
        else:
            owner = recv
        ob_meth = meth
        if owner in ("UUID", "java.util.UUID") and ob_meth == "fromString":
            return "UUID"
        t = method_return_type(owner, ob_meth, argc, at)
        if t is None and any(a is None for a in at):
            # an unresolvable arg (untyped CFR local) must not poison the
            # whole match — retry with argc-only disambiguation.
            t = method_return_type(owner, ob_meth, argc, None)
        if t is None:
            # static well-known owners (Math.atan2 -> double, ...)
            t = _STATIC_OWNERS.get((owner.rsplit(".", 1)[-1], ob_meth))
        if t is None and ob_meth == "valueOf":
            # `EnumType.valueOf(String)` returns the enum type itself.
            o_tok = owner.rsplit(".", 1)[-1]
            o_old = NEW2OLD.get(o_tok) or (o_tok if o_tok in CLS else None)
            if o_old:
                pv = os.path.join(_PKG, o_old + ".java")
                if os.path.exists(pv):
                    sv = open(pv, encoding="utf-8", errors="replace").read()
                    if re.search(r"\benum\s+" + re.escape(o_old) + r"\b", sv):
                        t = o_tok
        if t is None and ob_meth == "values":
            # `EnumType.values()` returns `EnumType[]`.
            o_tok = owner.rsplit(".", 1)[-1]
            o_old = NEW2OLD.get(o_tok) or (o_tok if o_tok in CLS else None)
            if o_old:
                pv = os.path.join(_PKG, o_old + ".java")
                if os.path.exists(pv):
                    sv = open(pv, encoding="utf-8", errors="replace").read()
                    if re.search(r"\benum\s+" + re.escape(o_old) + r"\b", sv):
                        t = o_tok + "[]"
        if t is None:
            # MC method return fallback: walk the mod supers chain first, then
            # the MC chain (a mod entity is-a ...Entity).
            cur = owner.rsplit(".", 1)[-1]
            seen = set()
            while cur and cur not in seen:
                seen.add(cur)
                t = MC_METHOD_RETURNS.get((cur, ob_meth))
                if t is not None:
                    break
                cur = SUPERS.get(cur, MC_SUPERS.get(cur))
        if t is not None:
            return t
        # degenerate multi-dot chain matched in parts — fall through to the
        # trailing-method-name fallback below.
    # `expr.method().field` — chained call followed by a field access
    # (e.g. `Minecraft.func_71410_x().field_71439_g` -> EntityPlayerSP).
    cfm = re.match(r"^(.*)\.([A-Za-z_$][\w]*)$", e)
    if cfm and re.search(r"\(\)", cfm.group(1)) and not e.endswith(")"):
        recv_t = resolve_expr_type(cfm.group(1), vt, own_old)
        if recv_t:
            if cfm.group(2) == "length" and recv_t.endswith("[]"):
                return "int"
            rt2 = resolve_field_type(recv_t, cfm.group(2))
            if rt2:
                return rt2
            cur = recv_t.rsplit(".", 1)[-1]
            seen = set()
            while cur and cur not in seen:
                seen.add(cur)
                rt2 = MC_FIELD_TYPES.get((cur, cfm.group(2)))
                if rt2 is not None:
                    return rt2
                cur = SUPERS.get(cur, MC_SUPERS.get(cur))
            return None
    mm = re.match(r"^([A-Za-z_$][\w$]*)\.([A-Za-z_$][\w]*)$", e)
    if mm:
        recv_f, fld = mm.group(1), mm.group(2)
        # UPPERCASE trailing field (`c.MISC_FART`, `IGeoRenderer.MATRIX_STACK`)
        # is a static class constant — prefer class-static resolution over a
        # local variable that happens to share the receiver name (`vt['c']`).
        if fld[:1].isupper() and (recv_f in CLS or recv_f in NEW2OLD):
            return resolve_field_type(CLS.get(recv_f, recv_f), fld)
        if recv_f == "this":
            # `this.field` — resolve via own-file field_types/cm below.
            pass
        elif DECOMP_LOCAL.match(recv_f) or recv_f in vt:
            base = vt.get(recv_f)
            if base is None:
                return None
            if fld == "length" and (base.endswith("[]") or base in ("String", "byte[]", "int[]", "float[]", "double[]", "Object[]")):
                return "int"
            t = resolve_field_type(base, fld)
            if t is None:
                # well-known MC fields (type inference fallback): walk the mod SUPERS
                # chain first (a GirlEntity is-a ...Entity) then the MC chain.
                cur = base.rsplit(".", 1)[-1]
                seen = set()
                while cur and cur not in seen:
                    seen.add(cur)
                    t = MC_FIELD_TYPES.get((cur, fld))
                    if t is not None:
                        break
                    cur = SUPERS.get(cur, MC_SUPERS.get(cur))
            return t
        # cross-file static field on a class (e.g. ModConstants.EndPos) — resolve
        # the field type from that class's own source when it is a mapped class.
        # The receiver may be the OLD name (member pass runs on raw source,
        # e.g. `dy.y` before the class pass turns dy -> GoblinNpcRenderer) or
        # the NEW name — try both.
        elif recv_f in CLS or recv_f in NEW2OLD:
            return resolve_field_type(CLS.get(recv_f, recv_f), fld)
        else:
            # external/interface receiver (GeckoLib IGeoRenderer.MATRIX_STACK) —
            # well-known MC/GeckoLib fields keyed directly by (owner, field).
            if recv_f in ("SoundEvents", "SoundEventHolder", "BlockSounds"):
                # MC static sound constants (`SoundEvents.field_187737_v`)
                return "SoundEvent"
            return MC_FIELD_TYPES.get((recv_f, fld))
    # field-only chains with >2 components (`A.B.C.D`, no parens): resolve the
    # receiver prefix recursively, then apply the trailing field (e.g.
    # `dy.y.field_71439_g.field_70177_z` -> Minecraft -> EntityPlayerSP -> float).
    fd = re.match(r"^(.*)\.([A-Za-z_$][\w]*)$", e)
    if fd and "." in fd.group(1):
        recv_t = resolve_expr_type(fd.group(1), vt, own_old)
        if recv_t is None:
            return None
        return resolve_field_type(recv_t, fd.group(2))
    # bare own-file static field arg (e.g. `b6.a(var0, c, var5)` where `c` is a
    # field of the current class declared `static Vec3d c`) — resolve from source.
    if re.match(r"^[a-z][\w$]*$", e) and own_old and not DECOMP_LOCAL.match(e) and e not in ("this", "super"):
        return resolve_field_type(own_old, e)
    # bare known static constant (GeckoLib `MATRIX_STACK` static-import).
    if e in ("MATRIX_STACK",):
        return "MatrixStack"
    cm = re.match(r"^this\.([A-Za-z_$][\w]*)(?:\.([A-Za-z_$][\w]*))?$", e)
    if cm:
        if cm.group(1) in vt:
            t = vt[cm.group(1)]
        elif "this." + cm.group(1) in vt:
            t = vt["this." + cm.group(1)]
        elif own_old:
            t = resolve_field_type(own_old, cm.group(1))
        else:
            return None
        if cm.group(2) is None:
            return t
        return resolve_field_type(t, cm.group(2))
    # chained call args: `a.b().c.d.Method()` — resolve by trailing method name
    # when it has a blanket known return (e.g. any Entity.getPersistentID()).
    tail = re.search(r"\.([A-Za-z_$][\w]*)\(\s*\)\s*$", e)
    if tail:
        name = tail.group(1)
        if name in ("getPersistentID", "func_110124_au"):
            return "UUID"
    return None


def resolve_method(mval, after, var_types=None, own_old=None):
    """Given a methods-map value (str or sig-dict) and the text after the token,
    return the replacement name or None."""
    if isinstance(mval, str):
        return mval
    ts = decl_types(after)
    if ts is not None:
        cands = [ts]
        alt = [CLS.get(t) or (NEW2OLD.get(t) or t) for t in ts]
        if alt != ts:
            cands.append(alt)
        for cand in cands:
            key = ",".join(cand)
            if key in mval:
                return mval[key]
            # raw-generic erasure both ways: decl `Map<String,Integer>` matches a
            # raw `Map` key, and a `HashSet<String>` key matches a raw arg.
            ekey = re.sub(r"<.*>", "", key)
            erasures = {mval[k] for k in mval if re.sub(r"<.*>", "", k) == ekey}
            if len(erasures) == 1:
                return next(iter(erasures))
        return None
# unresolved arg types: a single-entry sig-dict that is the no-arg "" is
    # unambiguous (getter-like calls); defer typed single-keys to the
    # var_types/argc resolution below.
    if len(mval) == 1:
        only_key = next(iter(mval))
        if only_key == "":
            parts = decl_params(after)
            argc = len([p for p in (parts or []) if p.strip()])
            if argc == 0:
                return next(iter(mval.values()))
    parts = decl_params(after)
    if parts is not None and var_types is not None:
        ts = []
        for part in parts:
            p = part.strip()
            # unary +/- on an arg (`-var5`) — resolve the bare operand
            if p.startswith(("-", "+")) and len(p) > 1:
                p = p[1:].strip()
            # literals FIRST: a `return true;` mis-parses a local `true` var
            if p in ("true", "false"):
                ts.append("boolean")
            elif re.match(r"^-?\d+[lL]?$", p):
                ts.append("int")
            elif re.match(r"^-?\d+\.\d+[fFdD]?$", p):
                # Java: a decimal literal is `double` unless it carries an
                # f/F suffix (d/D suffix is also double).
                ts.append("float" if p[-1] in "fF" else "double")
            elif re.match(r"^-?\d+[fF]?$", p):
                ts.append("int")
            elif re.match(r"^[\"']", p):
                ts.append("String")
            elif p in var_types:
                ts.append(var_types[p])
            elif re.search(r"\)\s*->|^\s*[A-Za-z_$][\w$]*\s*->", p):
                ts.append("Runnable")
            else:
                m = re.match(r"\(([A-Za-z_$][\w$.]*)\)", p)
                if m:
                    ts.append(m.group(1))
                else:
                    # `EnumType.CONSTANT` — a static enum-constant access whose
                    # runtime type is the enum itself.  For MAPPED classes
                    # verify it is a real enum; MC enums (EnumParticleTypes,
                    # EnumHand, ...) are never mapped so their constants are
                    # typed by the leading identifier directly.
                    em = re.match(r"^([A-Za-z_$][\w$]*)\.([A-Z][\w$]*)$", p)
                    o_f = em.group(1) if em else None
                    if em and (o_f in NEW2OLD or o_f in CLS):
                        newf = CLS.get(o_f, o_f)
                        oldf = NEW2OLD.get(newf, o_f)
                        p2 = os.path.join(_PKG, oldf + ".java")
                        if os.path.exists(p2):
                            src = open(p2, encoding="utf-8", errors="replace").read()
                            if re.search(r"\benum\s+" + re.escape(newf) + r"\b", src) or re.search(r"\benum\s+" + re.escape(oldf) + r"\b", src):
                                ts.append(newf)
                            else:
                                rt = resolve_expr_type(p, var_types, own_old)
                                ts.append(rt)
                        else:
                            rt = resolve_expr_type(p, var_types, own_old)
                            ts.append(rt)
                    elif em and em.group(1) not in ("this", "super") and not DECOMP_LOCAL.match(em.group(1)):
                        # MC/GeckoLib constant (EnumParticleTypes.xxx,
                        # IGeoRenderer.MATRIX_STACK): typed by the leading
                        # identifier — unless it is a known field (MATRIX_STACK).
                        rt = resolve_expr_type(p, var_types, own_old)
                        ts.append(rt if rt else em.group(1))
                    else:
                        # `this.C`, `var5.CONST` — a field access, not an enum
                        # constant: fall back to field-type resolution.
                        rt = resolve_expr_type(p, var_types, own_old)
                        ts.append(rt)
        # Java primitive widening: float arg matches a `double` param,
        # int matches `long`/`double`/`float` (common in LerpMath overloads).
        # Boxed counterparts match too (`false` arg vs a `Boolean` key).
        WIDEN = {"float": ["float", "double", "Float", "Double"],
                 "int": ["int", "long", "float", "double", "Integer", "Long"],
                 "double": ["double", "Double"], "long": ["long", "Long"],
                 "boolean": ["boolean", "Boolean"]}

        if ts and all(t is not None for t in ts):
            # methods-dict keys may use OBFUSCATED type names (pre-class-pass),
            # while var_types yields post-rename names — try both, plus the
            # whole ancestor chain (a `GirlRegistry.a(this)` call where `this`
            # is an em must match the `Entity` overload).
            combos = [ts]
            olds = [NEW2OLD.get(t) or t for t in ts]
            if olds != ts:
                combos.append(olds)
            news = [CLS.get(t) or t for t in ts]
            if news != ts:
                combos.append(news)
            # Java primitive widening: float arg matches a `double` param,
            # int matches `long`/`double`/`float` (common in LerpMath overloads).
            widened = []
            for t in ts:
                widened.append(WIDEN.get(t, [t]))
            try:
                from itertools import product
                for combo in product(*widened):
                    if list(combo) != ts:
                        combos.append(list(combo))
            except Exception:
                pass
            # walk up SUPERS (mod) + MC_SUPERS (minecraft) for every arg type
            chains = []
            for t in ts:
                # t may be an OBFUSCATED name (CFR locals like `ex ex2;` from
                # field_types) or a post-rename one — seed both spellings and
                # start the walk from the NEW name so SUPERS (keyed by new
                # names) resolves.
                c = [t, NEW2OLD.get(t) or t, CLS.get(t) or t]
                # a generic type-param (`T` in `class d_<T extends em...>`) —
                # substitute its declared bound (girl entity etc.).
                gb = generic_bound(own_old, t)
                if gb and gb not in c:
                    c.insert(0, gb)
                    gb_old = NEW2OLD.get(gb)
                    if gb_old and gb_old not in c:
                        c.append(gb_old)
                c = list(dict.fromkeys(c))
                cur = gb or (CLS.get(t) or t)
                seen = set()
                while True:
                    nxt = None
                    if cur in SUPERS:
                        nxt = SUPERS[cur]
                    elif cur in MC_SUPERS:
                        nxt = MC_SUPERS[cur]
                    elif NEW2OLD.get(cur) in SUPERS:
                        nxt = SUPERS[NEW2OLD.get(cur)]
                    elif NEW2OLD.get(cur) in MC_SUPERS:
                        nxt = MC_SUPERS[NEW2OLD.get(cur)]
                    if nxt is None or nxt in seen:
                        break
                    seen.add(nxt)
                    c.append(nxt)
                    # ancestors are looked up by NEW name in SUPERS, but members
                    # dicts key by OBFUSCATED type names (e.g. `em`) — add the
                    # old name of each ancestor so the chain can match it.
                    o_anc = NEW2OLD.get(nxt)
                    if o_anc and o_anc not in c:
                        c.append(o_anc)
                    cur = nxt
                # implemented interfaces (mapped sexmod ones, e.g. d_ implements
                # c3=GirlBoneFilter): a `this` arg typed as GeoGirlRenderer must
                # also match a sig key typed as the interface.
                iface_cur = CLS.get(t) or t
                iface_seen = set()
                while iface_cur and iface_cur not in iface_seen:
                    iface_seen.add(iface_cur)
                    for _if in IMPLEMENTS.get(iface_cur, []):
                        if _if not in c:
                            c.append(_if)
                        o_if = NEW2OLD.get(_if)
                        if o_if and o_if not in c:
                            c.append(o_if)
                    iface_cur = SUPERS.get(iface_cur) or MC_SUPERS.get(iface_cur, NEW2OLD.get(iface_cur))
                chains.append(c)
            for combo in combos:
                import itertools as _it

                for picked in _it.product(*[
                    [t] + ctx for t, ctx in zip(combo, chains)
                ]):
                    # test each product immediately: exact ts is combos[0], and
                    # `_it.product` iterates shortest-chain-first, so the exact
                    # match (when present) is returned before any ancestor form.
                    joined2 = ",".join(picked)
                    if joined2 in mval:
                        return mval[joined2]
            for combo in combos:
                joined = ",".join(combo)
                if joined in mval:
                    return mval[joined]
                # raw-generic erasure: `HashSet<String>` keys match a raw
                # `HashSet` arg (Vineflower drops generics on raw locals).
                # Compared by LAST dotted component so a `java.util.HashSet`
                # key matches a `HashSet` arg and vice versa.
                jk = re.sub(r"<.*>", "", joined)
                erasures = set()
                for k in mval:
                    ek = re.sub(r"<.*>", "", k)
                    if ek == joined or ek == jk or (
                        ek.rsplit(".", 1)[-1] == jk.rsplit(".", 1)[-1]
                        and ("." in ek or "." in jk)
                    ):
                        erasures.add(mval[k])
                if len(erasures) == 1:
                    return next(iter(erasures))
            # varargs: key last part `Foo[]` matches any trailing run of
            # Foo-typed args (key parts == fixed params + array element type).
            for k in mval:
                kp = k.split(",")
                if not kp or not kp[-1].endswith("[]"):
                    continue
                elem = kp[-1][:-2]
                fixed = kp[:-1]
                if len(ts) < len(fixed):
                    continue
                # try each combo (old/new spellings, widened, ancestors)
                for combo in combos:
                    if combo[: len(fixed)] != fixed:
                        continue
                    if all(t == elem for t in combo[len(fixed) :]):
                        return mval[k]
                # chain-walked picks too: a GirlEntity-subclass arg matches the
                # fixed `GirlEntity` varargs param (GalathNpc -> GirlEntity).
                for picked in _it.product(*[
                    [t] + ctx for t, ctx in zip(ts, chains)
                ]):
                    if list(picked[: len(fixed)]) != fixed:
                        continue
                    if all(t == elem for t in picked[len(fixed) :]):
                        return mval[k]

            # Fully-resolved ts with no matching key: do NOT fall through to the
            # argc fallback — a typed multi-overload dict (e.g. em.b /
            # ev.b having BOTH GirlAnimationState->openActionMenu and
            # String->getModelBone) must not pick the single arity-matched name
            # when the resolved arg types don't match (see AllieNpc
            # `this.b("tail"+n2)`), it would mis-name the call.
            return None

        # partial-arg match: untyped CFR locals (None args) must not poison
        # the whole call — match keys where every RESOLVED arg matches its
        # component (exact > widened).  Only fire on a UNIQUE best score.
        if any(t is None for t in ts):
            hits = {}
            for k in mval:
                kp = k.split(",")
                if len(kp) != len(ts):
                    continue
                score = 0
                ok = True
                for want, got in zip(ts, kp):
                    if want is None:
                        continue
                    if want == got:
                        score += 2
                        continue
                    wv = {NEW2OLD.get(want) or want, CLS.get(want) or want}
                    wv.update(WIDEN.get(want, [want]))
                    if got in wv:
                        score += 1
                        continue
                    ok = False
                    break
                if ok:
                    hits[k] = score
            if hits:
                best = max(hits.values())
                winners = {mval[k] for k, sc in hits.items() if sc == best}
                if len(winners) == 1:
                    return next(iter(winners))

    # argc-based same-name fallback (only for UNRESOLVED arg types — a fully
    # typed call that matched nothing must stay unmapped): if every signature
    # with this arity maps to the SAME name, resolve without the arg types.
    # Only sound when the dict is unambiguous (a typed single-entry dict must
    # NOT auto-resolve: the arg may be an untyped MC field access, see
    # `var2.b(var5.field_70177_z)`).
    if parts is not None and not (len(mval) == 1 and next(iter(mval)) != ""):
        argc = len([p for p in parts if p.strip()])
        # "" means a no-arg method (getter/action); a 1-arg call must not match
        # it (`"".split(",")` is `[""]`, length 1, which previously let a no-arg
        # key mis-name a 1-arg `updateJump` call).
        hits = {}
        for k, v in mval.items():
            ka = 0 if k == "" else len(k.split(","))
            if ka == argc:
                hits[v] = k
        if len(hits) == 1:
            # Only fire the argc fallback for an UNTYPED single hit ("" key, a
            # getter/action). A single TYPED arity-match (e.g. f8.b has both
            # `fp`:setAnimationState and ``:getTargetEntity — a 1-arg call with
            # a float arg) must not be auto-named: the arg types decide.
            if next(iter(hits.values())) == "":
                return next(iter(hits))
            return None
    return None


def rename_members(text, own_old):
    """Rename fields/methods (values) and inner classes (types) per members.json.

    Rules (own file = file whose obfuscated stem is own_old):
      - bare token / this.x / super.x / var.x inside the OWN file: rename if it
        is one of the owner's fields/methods/types (type-position first).
      - Owner.x anywhere (Owner = any mapped class): rename x per Owner's maps
        (covers `PacketSendChatMessage.a.class` in NetworkHandler).
      - cross-instance obj.x in OTHER files is left (needs type inference).
    """
    own_map = MEM.get(own_old)
    inner = inner_map(own_old)
    scopes = class_scopes(text)

    def scope_at(pos):
        """Return (scope_name, inner_map) for a position inside an inner class
        body, else (None, None)."""
        for name_start, body_open, body_close, name in scopes:
            if body_open <= pos < body_close:
                im = inner.get(name)
                if im is not None and (im["types"] or im["methods"] or im["fields"]):
                    return name, im
        return None, None

    # `static T a(T varN) { return varN; }` exception rethrow helpers -> rethrow.
    # Param name may be any identifier (decompilers vary: varN / paramN / string /
    # runtimeException / fp2), not just var\d+.
    RH = re.compile(
        r"static\s+([A-Za-z_$][\w$]*)\s+([a-z][\w$]*)\s*\(\s*\1\s+([A-Za-z_$][\w$]*)\s*\)\s*\{\s*return\s+\3\s*;\s*\}"
    )
    rethrow_pos = {(m.start(2), m.end(2)) for m in RH.finditer(text)}
    base_var_types = local_var_types(text)
    self_name = CLS.get(own_old, own_old)

    def var_types(pos):
        vt = dict(base_var_types(pos))
        vt.setdefault("this", self_name)
        vt.setdefault("super", self_name)
        return vt

    subs = []
    for s, e, w in scan(text):
        pch = text[s - 1] if s > 0 else ""
        after = text[e:]
        vt = var_types(s)
        scope_name, inner_map_ = scope_at(s)
        cur_map = inner_map_ if inner_map_ is not None else own_map
        if (s, e) in rethrow_pos:
            subs.append((s, e, "rethrow"))
            continue
        if text[s - 2 : s] == "::":
            prev = prev_word(text[:s])[0]
            if prev in ("this", "super") or (prev or "").startswith("var") or DECOMP_LOCAL.match(prev or ""):
                if cur_map is not None and w in cur_map["methods"]:
                    nm = resolve_method(cur_map["methods"][w], after, vt, own_old)
                    if nm is not None:
                        subs.append((s, e, nm))
                continue
            om = owner_map(prev)
            if om is not None and w in om["methods"]:
                nm = resolve_method(om["methods"][w], after, vt, own_old)
                if nm is not None:
                    subs.append((s, e, nm))
            continue
        if pch == ".":
            prev = dot_prev(text, s)
            if not prev:
                continue
            # `throw Cls.a(exc)` — the rethrow-helper call pattern with an
            # own-class prefix (decompilers emit `throw SlimeNpc.a(exc)` for
            # CFR bodies, sometimes fully qualified:
            # `throw com.trolmastercard.sexmod.g.a(runtimeException)`). A
            # `throw <call>` requires the method to return a Throwable, so
            # renaming the token to `rethrow` is always safe. prev must be a
            # MAPPED class — otherwise a package segment like
            # `com.trolmastercard.sexmod.g.a(...)` would match at
            # `trolmastercard` (prev=`com`, two words back=`throw`) and be
            # mangled to rethrow.
            wb = re.findall(r"[A-Za-z_$][\w$]*", text[:s])
            # inner-class receivers appear in bytecode form (`ay$b`); the
            # rethrow helper may live in the OUTER class source (inner classes
            # have no own .java file), so also check the outer name.
            prev_outer = prev.replace("$", ".").split(".")[0]
            if (
                owner_map(prev) is not None
                or w in rethrow_helper_names(prev)
                or (prev_outer != prev and w in rethrow_helper_names(prev_outer))
            ) and (
                (len(wb) >= 2 and wb[-1] == prev and wb[-2] == "throw")
                or re.search(
                    r"\bthrow\s+([A-Za-z_$][\w$]*\s*\.\s*)*"
                    + re.escape(prev)
                    + r"\s*\.\s*$",
                    text[:s],
                )
            ):
                subs.append((s, e, "rethrow"))
                continue
            # `OwnClass.inner.member(...)` — prev may be an INNER class of the
            # current class (its obfuscated key, e.g. `a`).  A same-named
            # top-level class (`a`=GuiCustomizeGirl) would otherwise win via
            # owner_map; the qualified own-class prefix disambiguates.
            om = None
            qm = re.search(
                r"([A-Za-z_$][\w$]*)\s*\.\s*" + re.escape(prev) + r"\s*\.\s*$",
                text[:s],
            )
            if (
                qm
                and cur_map is not None
                and qm.group(1) in (self_name, own_old)
            ):
                for _ic_k, _ic_v in cur_map["types"].items():
                    if prev in (_ic_k, _ic_v):
                        _ic = MEM.get(own_old + "." + _ic_k)
                        if _ic is not None:
                            om = _ic
                            break
            if om is None:
                om = owner_map(prev)
            if om is not None:
                in_type_pos = (
                    first_sig(after) != "("
                    or after_paren_is_brace(after)
                    or re.search(r"\bnew\s+" + re.escape(prev) + r"\.$", text[:s])
                )
                if w in om["types"] and in_type_pos:
                    subs.append((s, e, om["types"][w]))
                    continue
                if w in om["methods"] and first_sig(after) == "(":
                    nm = resolve_method(om["methods"][w], after, vt, own_old)
                    if nm is not None:
                        subs.append((s, e, nm))
                        continue
                    # nm is None (no overload matches): fall through so the
                    # static-inherited SUPERS walk below can try the parent's
                    # map (e.g. `AlliePlayer.a(player, this, String[], bool)` is
                    # GirlEntity's static openActionMenu).
                if w in om["fields"] and first_sig(after) != "(":
                    subs.append((s, e, om["fields"][w]))
                    continue
                # static inherited member: `KoboldNpcRenderer.c()` where c() is
                # declared on a mapped SUPERclass (d6/d9 color-cache clears).
                # Note: when the FIRST ancestor declaring `w` has no overload
                # matching the args (e.g. `AlliePlayer.a(player, this, Str[], b)`
                # hits PlayerGirlEntity's a(UUID) first), keep walking up so the
                # true declaring class (GirlEntity's openActionMenu) is found.
                cur = CLS.get(prev) or prev
                seen = set()
                om2 = None
                is_call = first_sig(after) == "("
                sec = "methods" if is_call else "fields"
                nm2 = None
                while cur and cur not in seen:
                    seen.add(cur)
                    nxt = SUPERS.get(cur) or SUPERS.get(NEW2OLD.get(cur) or cur)
                    if nxt is None:
                        break
                    cur = nxt
                    om2 = owner_map(cur)
                    if om2 is not None and w in om2.get(sec, {}):
                        if sec == "methods":
                            nm2 = resolve_method(om2["methods"][w], after, vt, own_old)
                            if nm2 is not None:
                                break
                        else:
                            nm2 = om2["fields"][w]
                            break
                if nm2 is not None:
                    subs.append((s, e, nm2))
                    continue
                # `this.c.a` — prev (e.g. `c`) resolved to a members map because
                # its name collides with a class name, but it is ALSO a field of
                # the current class whose actual type holds `w` (TreeCluster's
                # TaskType-enum Cost). Fall through to the this-field receiver
                # chain (which resolves via resolve_field_type) instead of
                # stopping here.
                if not re.search(r"\bthis\.\s*" + re.escape(prev) + r"\s*\.\s*$", text[:s]):
                    continue
            # Receiver is a mapped class WITHOUT an own members map — a static
            # member may be inherited from a mapped SUPERclass (color-cache
            # clears etc.), e.g. `KoboldNpcRenderer.c()` -> ScaledGirlGeoRenderer.
            cur = CLS.get(prev) or prev
            seen_sup = set()
            om2 = owner_map(prev)
            if om2 is None:
                while cur and cur not in seen_sup:
                    seen_sup.add(cur)
                    nxt = SUPERS.get(cur) or SUPERS.get(NEW2OLD.get(cur) or cur)
                    if nxt is None:
                        break
                    cur = nxt
                    om2 = owner_map(cur)
                    if om2 is not None:
                        break
            this_recv = bool(re.search(r"\bthis\.\s*" + re.escape(prev) + r"\s*\.\s*$", text[:s]))
            if om2 is not None and first_sig(after) == "(" and w in om2.get("methods", {}):
                nm = resolve_method(om2["methods"][w], after, vt, own_old)
                if nm is not None:
                    subs.append((s, e, nm))
                    continue
                # prev may ALSO be a field of `this` whose real type holds `w`
                # (mapped class name colliding with a field, e.g. `this.f.b(...)`
                # in GuiGirlPreview where f is both GirlAiBase and the em field)
                # — fall through to the this-field receiver chain.
                if not this_recv:
                    continue
            # enum-constant receiver: `EquipmentSlot.SlotType.WEAPON.id` — the
            # receiver chain ends in an enum constant whose type is the inner
            # enum; drop the constant and resolve the qualified inner map.
            chain = re.search(
                r"([A-Za-z_$][A-Za-z0-9_$]*(?:\.[A-Za-z_$][A-Za-z0-9_$]*)*)\.\s*$",
                text[:s],
            )
            if (
                chain is not None
                and first_sig(after) != "("
                and ".".join(chain.group(1).split(".")[:-1])
            ):
                # enum-constant receiver (`EquipmentSlot.SlotType.WEAPON.id`):
                # every remaining component must be a TYPE/constant name.
                # Enclosing-instance chains (`Outer.this.field`) have lowercase
                # field / `this` components and must NOT take this path.
                _chain_parts = chain.group(1).split(".")[:-1]
                _chain_ok = all(
                    p != "this" and p[:1].isupper() for p in _chain_parts[1:]
                )
                qm = (
                    owner_map(".".join(_chain_parts))
                    if _chain_ok
                    else None
                )
                if qm is not None and w in qm["fields"]:
                    subs.append((s, e, qm["fields"][w]))
                    continue
            this_prev = bool(re.search(r"\bthis\.\s*" + re.escape(prev) + r"\s*\.\s*$", text[:s]))

            def own_related(t):
                if t in (own_old, self_name):
                    return True
                if "." in t:
                    bo = NEW2OLD.get(t.split(".")[0]) or t.split(".")[0]
                    return bo == own_old
                return False

            if DECOMP_LOCAL.match(prev) and prev in vt:
                # build receiver map chain (most-derived -> base) and find the
                # map declaring `w` (members may be on a superclass, e.g. an ei
                # instance calling em's abstract float i()).
                rchain = []
                t = vt[prev]
                seen = set()
                is_call = first_sig(after) == "("
                sec = "methods" if is_call else "fields"
                while t and t not in seen:
                    seen.add(t)
                    om = owner_map(t)
                    if om is not None:
                        rchain.append((om, t))
                        if w in om.get(sec, {}):
                            break
                    t = SUPERS.get(CLS.get(t, t)) or MC_SUPERS.get(CLS.get(t, t))
                rmap = None
                for om, _t in rchain:
                    if w in om.get(sec, {}):
                        rmap = om
                        break
                if rmap is None and cur_map is not None and own_related(vt[prev]):
                    rmap = cur_map
                if rmap is not None:
                    if w in rmap["methods"] and is_call:
                        nm = resolve_method(rmap["methods"][w], after, vt, own_old)
                        if nm is not None:
                            subs.append((s, e, nm))
                        continue
                    if w in rmap["fields"] and not is_call:
                        subs.append((s, e, rmap["fields"][w]))
                continue
            if cur_map is not None and (prev in ("this", "super") or prev in vt or this_prev):
                # inside an INNER class body `this` is the inner instance —
                # its OWN map outranks the enclosing class's map (`this.e`
                # in TribeConfig must hit g3.b's fields, not outer g3's).
                if (
                    cur_map is not own_map
                    and prev in ("this", "super")
                    and w in (
                        cur_map["methods"]
                        if first_sig(after) == "("
                        else cur_map["fields"]
                    )
                ):
                    if first_sig(after) == "(":
                        nm = resolve_method(cur_map["methods"][w], after, vt, own_old)
                        if nm is not None:
                            subs.append((s, e, nm))
                            continue
                    else:
                        subs.append((s, e, cur_map["fields"][w]))
                        continue
                if prev in vt and prev != "this" and prev != "super":
                    t = vt[prev]
                elif this_prev:
                    t = vt.get("this." + prev) or resolve_field_type(own_old, prev)
                else:
                    t = own_old if prev in ("this", "super") else None
                if t is not None:
                    # walk the receiver-type chain (most-derived -> base) and
                    # use the FIRST map declaring `w` — members may live on a
                    # superclass (e.g. this.q is an InventoryGirlEntity but
                    # `b(fp)` is declared on GirlEntity).
                    is_call = first_sig(after) == "("
                    sec = "methods" if is_call else "fields"
                    rmap = None
                    seen = set()
                    t0 = t
                    while t and t not in seen:
                        seen.add(t)
                        om = owner_map(t)
                        if om is not None and w in om.get(sec, {}):
                            rmap = om
                            break
                        t = SUPERS.get(CLS.get(t, t)) or MC_SUPERS.get(CLS.get(t, t))
                    if rmap is None and own_related(t0):
                        rmap = cur_map
                    if rmap is not None:
                        if w in rmap["methods"] and is_call:
                            nm = resolve_method(rmap["methods"][w], after, vt, own_old)
                            if nm is not None:
                                subs.append((s, e, nm))
                            continue
                        if w in rmap["fields"] and not is_call:
                            subs.append((s, e, rmap["fields"][w]))
            continue
        if cur_map is None:
            if prev_word(text[:s])[0] == "throw" and first_sig(after) == "(":
                subs.append((s, e, "rethrow"))
            continue
        pw, pwch = prev_word(text[:s])
        if pw == "throw" and first_sig(after) == "(":
            subs.append((s, e, "rethrow"))
            continue
        psig = last_sig(text[:s])
        nsig = first_sig(after)
        if is_keyword(pw, pwch) and pw in ("class", "interface", "enum"):
            if w in cur_map["types"]:
                subs.append((s, e, cur_map["types"][w]))
            continue
        if pw in ("new", "extends", "implements", "instanceof", "throws"):
            if w in cur_map["types"]:
                subs.append((s, e, cur_map["types"][w]))
            continue
        if w in cur_map["types"] and nsig == "." and word_after(after, 1, ".") == "class":
            subs.append((s, e, cur_map["types"][w]))
            continue
        # bare type-position token: `a S = ...` (field/var decl of an inner
        # class/enum type, e.g. SlimeNpc's `a S = ...SQL string...`).
        # Reader-safe guards: next char is an identifier (a declared var name)
        # and prev word is a type-position signal (modifier, `;;`, `{`, `,`,
        # `(`, `=`, `return`, `new`, nothing) — NOT a method call `a(`.
        if (
            w in cur_map["types"]
            and nsig
            and (nsig.isalpha() or nsig == "_")
            and (pw in MODIFIER_WORDS or pw in (None, "returns") or psig in ("{", ";", ",", "(", "="))
        ):
            subs.append((s, e, cur_map["types"][w]))
            continue
        if nsig == "(":
            # inner-class/enum CONSTRUCTOR decl inside its own body FIRST:
            # the token equals the scope's obfuscated key (`b(int i)` for enum
            # b, `a(UUID,...)` for HomeData) and the NEW name lives on the
            # OUTER class's types map.  Must outrank the methods map — a ctor
            # arg list can accidentally match a setter overload.  Uses raw
            # class_scopes (works even when the inner class has no dotted MEM
            # entry, e.g. SlimeRainEntity$RandomHopAi).
            _rk = None
            for _ns, _bo, _bc, _nm in scopes:
                if _bo <= s < _bc:
                    _rk = _nm
                    break
            if (
                _rk is not None
                and w == _rk
                and after_paren_is_brace(after)
                and (
                    prev_word(text[:s])[0] in MODIFIER_WORDS
                    or last_sig(text[:s]) in ";}{(),"
                )
                and own_map is not None
                and w in own_map.get("types", {})
            ):
                subs.append((s, e, own_map["types"][w]))
                continue
            if w in cur_map["methods"]:
                nm = resolve_method(cur_map["methods"][w], after, vt, own_old)
                if nm is not None:
                    subs.append((s, e, nm))
                continue
            if (
                w in cur_map["types"]
                and after_paren_is_brace(after)
                and (
                    pw in MODIFIER_WORDS
                    or last_sig(text[:s]) in ";}{(),"
                )
            ):
                # inner-class/enum constructor declaration (e.g. enum ctor
                # `b(String)` for class b) — NOT a method `void a(...)`
                # (which has a return type before its name).
                subs.append((s, e, cur_map["types"][w]))
            continue
        if w in cur_map["fields"]:
            subs.append((s, e, cur_map["fields"][w]))
    if not subs:
        return text
    out = []
    pos = 0
    for s, e, new in sorted(subs):
        out.append(text[pos:s])
        out.append(new)
        pos = e
    out.append(text[pos:])
    return "".join(out)


def inherited_members(own_old):
    """Field/method names (obfuscated keys + renamed values) declared on MAPPED
    superclasses of own_old. The class pass's `w not in members` guards must
    also skip these, otherwise a bare inherited single-letter field ref (e.g.
    `i`=Minecraft in a renderer subclass) is mis-renamed to the class name
    (`i` -> KoboldEggEntity) before the member pass can see it."""
    names = set()
    cur = CLS.get(own_old, own_old)
    seen = set()
    while cur and cur not in seen:
        seen.add(cur)
        om = owner_map(cur)
        if om is not None:
            for sec in ("fields", "methods"):
                for k, v in om.get(sec, {}).items():
                    names.add(k)
                    if isinstance(v, str):
                        names.add(v)
        cur = SUPERS.get(cur)
    return names


def rename_source(text, own_old):
    members = member_names(text)
    members.discard(own_old)
    # Inner-class names declared in THIS file (class/interface/enum at brace
    # depth 1). Bare references to them must NOT be renamed by the class pass
    # (they are members.json "types" territory, renamed by the member pass),
    # otherwise `a S = ...` becomes `GuiCustomizeGirl S = ...` instead of
    # `JumpState S = ...` (em avoided this because its type refs are
    # qualified `em.a` / `com...em$a`, but CFR files use bare `a`).
    inner_names = {n for _cs, _bo, _bc, n in class_scopes(text)}
    subs = []
    for s, e, w in scan(text):
        new = CLS.get(w)
        if not new or new == w:
            continue
        before = text[:s]
        after = text[e:]
        pch = text[s - 1] if s > 0 else ""
        nsig = first_sig(after)
        pw, pwch = prev_word(before)
        psig = last_sig(before)

        if pch == ".":
            continue
        if w in inner_names:
            # inner class of this file (e.g. fn's `enum a`): leave for the
            # member pass's types map (jump to member-pass handling).
            # `class X`/`enum X` decls below still check w == own_old.
            if not (is_keyword(pw, pwch) and pw in ("class", "interface", "enum") and w == own_old):
                continue
        if is_keyword(pw, pwch) and pw in ("class", "interface", "enum"):
            # `class X`/`interface X`/`enum X`: only the file's own top-level
            # class decl is a classes.json entry; any other X is an inner class,
            # renamed later by the members.json "types" pass.
            if w == own_old:
                subs.append((s, e, new))
            continue
        if w == own_old and nsig == "(" and (pw in MODS or pw is None or psig in ("{", ";", "}")):
            subs.append((s, e, new))
            continue
        if nsig == ".":
            if w not in members:
                subs.append((s, e, new))
            continue
        if nsig == "<" or nsig == "[":
            subs.append((s, e, new))
            continue
        if nsig == "(":
            if is_keyword(pw, pwch):
                subs.append((s, e, new))
            continue
        if nsig and (nsig.isalpha() or nsig == "_"):
            subs.append((s, e, new))
            continue
        if is_keyword(pw, pwch):
            subs.append((s, e, new))
            continue
        if psig == "(" and nsig == ")" and re.match(r"[ \t\r\n]*[A-Za-z0-9_(]", after[1:]):
            if w not in members:
                subs.append((s, e, new))
            continue
        if psig == "<" and nsig in (">", ","):
            if w not in members:
                subs.append((s, e, new))
            continue
        if psig == "," and nsig == ">":
            if w not in members:
                subs.append((s, e, new))
            continue
        if psig == "," and nsig == ",":
            # pure value-argument position (`, i,`): a bare single-letter
            # INHERITED field here (e.g. `i`=Minecraft passed as an arg in a
            # renderer subclass) must not be mis-renamed to the class name —
            # the class pass cannot tell it from a genuine class reference, so
            # extend the guard with members inherited from mapped superclasses.
            if w not in members and w not in inherited_members(own_old):
                subs.append((s, e, new))
            continue
    if not subs:
        return rename_members(text, own_old)
    out = []
    pos = 0
    for s, e, new in sorted(subs):
        out.append(text[pos:s])
        out.append(new)
        pos = e
    out.append(text[pos:])
    return rename_members("".join(out), own_old)


def copy_tree(src, dst):
    count = 0
    for root, dirs, files in os.walk(src):
        rel = os.path.relpath(root, src)
        if rel == ".":
            rel = ""
        rel_dir = os.path.join(dst, rel) if rel else dst
        os.makedirs(rel_dir, exist_ok=True)
        for f in files:
            fsrc = os.path.join(root, f)
            stem, ext = os.path.splitext(f)
            if ext == ".java":
                text = open(fsrc, encoding="utf-8", errors="replace").read()
                if stem in CLS:
                    text = rename_source(text, stem)
                    text = apply_mcp(text)
                    text = localize_locals(text, stem)
                    text = _fix_inner_refs(text)
                    if CLS[stem] != stem:
                        stem = CLS[stem]
                        count += 1
                fdst = os.path.join(rel_dir, stem + ext)
                open(fdst, "w", encoding="utf-8").write(text)
            else:
                shutil.copy2(fsrc, os.path.join(rel_dir, f))
    return count


# ---------------------------------------------------------------------------
# Local-variable pass: rename decompiler locals (varN/paramN from Vineflower,
# n2/f10/bl2-style numbered tokens from CFR) to semantic names derived from
# their declared types.  Purely file-local identifier substitution.
# ---------------------------------------------------------------------------

_LOCAL_TOKEN = r"(?:var\d+\w*|param\d+\w*|[a-z]{1,2}\d+(?:_\d+)*)"
_DECL_RE = re.compile(
    r"(?P<type>[A-Za-z_$][\w$]*(?:\.[A-Za-z_$][\w$]*)*"
    r"(?:<[^<>(){};]*(?:<[^<>(){};]*>[^<>(){};]*)*>)*(?:\[\])*(?:\.\.\.)?)"
    r"\s+(?P<tok>" + _LOCAL_TOKEN + r")\s*(?=[=;:,)\])])"
)
_DECL2_RE = re.compile(r",\s*(" + _LOCAL_TOKEN + r")\s*(?=[=;:])")
_TYPE_OK = re.compile(r"^[A-Z_<\[]")
_PRIMS = {"int", "long", "float", "double", "boolean", "byte", "short", "char"}
_KW_TYPES = {"return", "new", "case", "else", "throw", "throws", "instanceof",
             "break", "continue", "assert", "this", "super", "null", "true",
             "false", "do", "try", "catch", "finally", "switch", "while",
             "for", "if", "synchronized", "final", "static", "public",
             "private", "protected", "abstract", "native", "transient",
             "volatile", "strictfp", "default"}
_JAVA_KW = {"abstract", "assert", "boolean", "break", "byte", "case", "catch",
            "char", "class", "const", "continue", "default", "do", "double",
            "else", "enum", "extends", "final", "finally", "float", "for",
            "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "true", "false",
            "null"}
_LOCAL_NAMES = {
    "int": "i", "long": "l", "float": "f", "double": "d", "boolean": "flag",
    "byte": "bv", "short": "sh", "char": "ch",
    "Integer": "i", "Long": "l", "Float": "f", "Double": "d",
    "Boolean": "flag", "Short": "sh", "Byte": "bv", "Character": "ch",
    "Number": "num", "Object": "obj", "String": "string",
    "CharSequence": "string", "Class": "cls",
    "World": "world", "WorldServer": "world", "BlockPos": "pos",
    "Vec3d": "vec3d", "Vec3i": "vec3i", "Vec3f": "vec3f", "Vec4d": "vec4d",
    "Vec2f": "vec2f", "Vec2i": "vec2i", "Vec2d": "vec2d",
    "ItemStack": "stack", "EntityPlayer": "player", "EntityPlayerMP":
    "serverPlayer", "EntityPlayerSP": "mcPlayer", "EntityOtherPlayerMP":
    "otherPlayer", "EntityLivingBase": "livingBase", "EntityLiving": "living",
    "EntityCreature": "creature", "Entity": "entity",
    "EntityArmorStand": "armorStand", "EnumFacing": "facing", "EnumHand":
    "hand", "IBlockState": "state", "Block": "block", "Item": "item",
    "ItemArmor": "armor", "NBTTagCompound": "tagCompound", "DataParameter":
    "dataParam", "Random": "random", "ThreadLocalRandom": "random",
    "List": "list", "ArrayList": "list", "LinkedList": "list", "Map": "map",
    "HashMap": "map", "TreeMap": "map", "Set": "set", "HashSet": "set",
    "Collection": "collection", "Iterator": "iterator", "ListIterator":
    "iterator", "Iterable": "iterable", "Queue": "queue", "Deque": "deque",
    "Minecraft": "mc", "GuiScreen": "gui", "GuiContainer": "gui",
    "Container": "container", "InventoryPlayer": "inventory",
    "Optional": "optional", "Throwable": "error", "Exception": "error",
    "RuntimeException": "error", "Error": "error", "UUID": "uuid",
    "ResourceLocation": "location", "SoundEvent": "sound",
    "EnumParticleTypes": "particle", "DamageSource": "damage",
    "AxisAlignedBB": "bbox", "RayTraceResult": "hit", "TextComponentString":
    "text", "ITextComponent": "text", "MinecraftServer": "server",
    "ICommandSender": "sender", "MessageContext": "ctx", "IMessage":
    "message", "NetworkManager": "networkManager", "Template": "template",
    "Chunk": "chunk", "PotionEffect": "effect", "AttributeModifier":
    "modifier", "IAttributeInstance": "attribute", "AnimationEvent":
    "animEvent", "AnimationController": "controller", "GeoModel": "model",
    "GeoBone": "bone", "EntityAIBase": "aiTask", "PathNavigate": "navigator",
    "Path": "path", "BiMap": "bimap", "ByteBuffer": "buffer", "ByteBuf":
    "buf", "ByteArrayOutputStream": "bytes", "InputStream": "inStream",
    "OutputStream": "outStream", "File": "file", "BufferedReader": "reader",
    "PrintWriter": "writer", "GZIPOutputStream": "gzip", "JSONObject": "json",
    "JSONArray": "jsonArr", "URL": "url", "HttpURLConnection": "conn",
    "Scanner": "scanner", "StringBuilder": "sb", "StringBuffer": "sb",
}
_MOD_LOCAL_OVERRIDES = {
    "GirlEntity": "girl", "PlayerGirlEntity": "playerGirl",
    "InventoryGirlEntity": "inventoryGirl", "ChestGirlEntity": "chestGirl",
    "GirlEffectEntity": "effect", "ManglelieNpc": "manglelie",
    "KoboldNpc": "kobold", "GalathNpc": "galath", "GoblinNpc": "goblin",
    "LunaNpc": "luna", "EllieNpc": "ellie", "BiaNpc": "bia",
    "JennyNpc": "jenny", "AllieNpc": "allie", "SlimeNpc": "slime",
    "SummonerNpc": "summoner", "ElliePlayer": "ellie", "BiaPlayer": "bia",
    "AlliePlayer": "allie", "JennyPlayer": "jenny", "LunaPlayer": "luna",
    "GalathPlayer": "galath", "SlimePlayer": "slime", "BeePlayer": "bee",
    "GoblinPlayer": "goblin", "KoboldEggEntity": "egg", "EnergyBallEntity":
    "energyBall", "LunaFamiliarEntity": "familiar", "DeadClass": "dead",
    "CustomEnderPearl": "enderPearl", "GirlRegistry": "girlType",
    "EyeAndKoboldColor": "eyeColor", "TribeColor": "tribeColor",
    "GirlColor": "girlColor", "MarkColor": "markColor", "WalkState":
    "walkState", "ActivityState": "activityState", "SlotType": "slotType",
    "HomeData": "homeData", "WhitelistFile": "whitelist",
    "ArmorReductionTable": "reductionTable", "CultistNpc": "cultist",
}


def _mask_code(text):
    """Blank out string/char literals and comments, preserving length and
    newlines, so identifier scans never hit their contents."""
    out = list(text)
    i = 0
    n = len(text)
    while i < n:
        c = text[i]
        if c == '"':
            j = i + 1
            while j < n:
                if text[j] == "\\":
                    j += 2
                    continue
                if text[j] == '"':
                    break
                j += 1
            for k in range(i + 1, min(j, n)):
                out[k] = " "
            i = j + 1
        elif c == "'":
            j = i + 1
            while j < n:
                if text[j] == "\\":
                    j += 2
                    continue
                if text[j] == "'":
                    break
                j += 1
            for k in range(i + 1, min(j, n)):
                out[k] = " "
            i = j + 1
        elif c == "/" and i + 1 < n and text[i + 1] == "/":
            j = text.find("\n", i)
            j = n if j < 0 else j
            for k in range(i, j):
                out[k] = " "
            i = j
        elif c == "/" and i + 1 < n and text[i + 1] == "*":
            j = text.find("*/", i + 2)
            j = n if j < 0 else j + 2
            for k in range(i, j):
                if out[k] != "\n":
                    out[k] = " "
            i = j
        else:
            i += 1
    return "".join(out)


def _method_regions(m):
    """Spans of method/constructor headers+bodies: starts at the '(' of a
    parameter list whose close is directly followed by '{' at class-member
    depth (i.e. NOT inside another method region).  Control-flow blocks
    inside methods are suppressed."""
    regions = []
    stack = []
    depth = 0
    pend = False
    hdr_start = None
    for i, c in enumerate(m):
        if c == "(":
            stack.append(["(", depth, i])
            pend = False
            hdr_start = None
        elif c == ")":
            if stack and stack[-1][0] == "(":
                ent = stack.pop()
                od, idx = ent[1], ent[2]
                if od == depth:
                    pend = True
                    hdr_start = idx
                else:
                    pend = False
                    hdr_start = None
            else:
                pend = False
                hdr_start = None
        elif c in ";=":
            pend = False
            hdr_start = None
        elif c == "{":
            inside = any(r[1] is None for r in regions)
            idx = None
            if pend and hdr_start is not None and not inside:
                idx = len(regions)
                regions.append([hdr_start, None])
            stack.append(["{", depth, idx])
            depth += 1
            pend = False
            hdr_start = None
        elif c == "}":
            depth -= 1
            if stack and stack[-1][0] == "{":
                ent = stack.pop()
                if ent[2] is not None:
                    regions[ent[2]][1] = i
    return [(a, b) for a, b in regions if b is not None]


def _local_base_name(t):
    """Semantic base name for a declared type string."""
    t = t.strip()
    is_arr = "[]" in t or "..." in t
    core = t.replace("...", "").strip()
    # strip trailing generics (`List<BlockPos>` -> `List`)
    core = re.sub(r"<[^<>]*(?:<[^<>]*>[^<>]*)*>", "", core).strip()
    while core.endswith("[]"):
        core = core[:-2].strip()
    simple = core.split(".")[-1].split("$")[-1]
    base = _LOCAL_NAMES.get(simple)
    if base is None and simple in _PRIMS:
        base = simple[0]
    if base is None:
        new = CLS.get(simple, simple)
        base = _MOD_LOCAL_OVERRIDES.get(new)
        if base is None:
            if new.startswith("Packet"):
                base = "packet"
            elif new.startswith("Gui"):
                base = "gui"
            elif new.startswith("Model"):
                base = "model"
            elif new.startswith("Item"):
                base = "item"
            elif new.startswith("Container"):
                base = "container"
            elif new.startswith("Command"):
                base = "command"
            elif new.endswith("Renderer"):
                base = "renderer"
            elif new.endswith("Ai"):
                base = "ai"
            elif new.endswith("Handler"):
                base = "handler"
            elif new.endswith("Exception"):
                base = "error"
            else:
                base = new[0].lower() + new[1:]
    if is_arr:
        prim_pl = {"byte": "bytes", "int": "ints", "long": "longs",
                   "float": "floats", "double": "doubles", "boolean": "flags",
                   "short": "shorts", "char": "chars"}
        base = prim_pl.get(simple, base + "Array")
    return base



_INNER_REF_RE = re.compile(r"\b([A-Za-z_]\w*)\$([A-Za-z_][\w$]*)\b")


def _fix_inner_refs(text):
    """Rewrite bytecode-form inner-class refs (`h6$a`, `com.trolmastercard.
    sexmod.f_$a`) to renamed dotted form (`PacketSendBlocks.Handler`,
    `GalathNpc.EventHandler`).  Lambda synthetics (`lambda$null`) untouched."""
    def rep(mm):
        outer, inner = mm.group(1), mm.group(2)
        new = CLS.get(outer)
        if not new:
            return mm.group(0)
        if inner.startswith("lambda$"):
            return mm.group(0)
        om = MEM.get(new) or MEM.get(outer) or {}
        tmap = om.get("types", {})
        inn = tmap.get(inner)
        if inn is None:
            im = MEM.get(new + "." + inner) or MEM.get(outer + "." + inner)
            if im is not None:
                # dotted owner exists but no types rename — keep letter
                inn = inner
            else:
                inn = inner
        return new + "." + inn
    text = _INNER_REF_RE.sub(rep, text)
    # drop redundant same-package imports (`import com.trolmastercard.sexmod.ai;`)
    # — after renaming they would shorten to invalid single-name imports
    text = re.sub(
        r"^import\s+com\.trolmastercard\.sexmod\.[A-Za-z_$][\w$]*\s*;\s*\n?",
        "",
        text,
        flags=re.M,
    )
    # plain fully-qualified refs: `com.trolmastercard.sexmod.c.GIRLS_...` ->
    # `ModSounds.GIRLS_...` (class pass skips tokens after dots)
    fq = re.compile(r"\bcom\.trolmastercard\.sexmod\.([A-Za-z_$][\w$]*)")
    _subpkgs = {"packets", "interfaces", "world", "preloading"}
    def rep2(mm):
        tok = mm.group(1)
        if tok in _subpkgs:
            return mm.group(0)
        # same-package classes: shorten to the bare (possibly renamed) name
        return CLS.get(tok, tok)
    return fq.sub(rep2, text)

# ---------------------------------------------------------------------------
# MCP mapping pass: restore official MCP stable_39 names for Minecraft SRG
# members (func_71410_x -> getMinecraft, field_70170_p -> world, ...).  The
# original mod source used exactly these names, so restoring them cannot
# introduce name conflicts that were not already resolved upstream.
# ---------------------------------------------------------------------------

_MCP = json.load(open(os.path.join(META, "mcp_stable39.json")))
_SRG_RE = re.compile(r"\b(?:func|field)_[0-9]+_[a-zA-Z_]+\b")


# Late passes (localize_locals) see POST-MCP text — auto-register MCP-name
# aliases for every table entry keyed by an SRG name so inference keeps working.
def _add_mcp_aliases(table):
    extra = []
    for (cls_key, name), val in table.items():
        if re.match(r"^(?:func|field)_\d+_[a-zA-Z_]+$", name):
            mcp = _MCP["methods"].get(name) or _MCP["fields"].get(name)
            if mcp:
                extra.append(((cls_key, mcp), val))
    for k, v in extra:
        table.setdefault(k, v)


_add_mcp_aliases(MC_METHOD_RETURNS)
_add_mcp_aliases(MC_FIELD_TYPES)
for _k, _v in list(_STATIC_OWNERS.items()):
    _mcp_n = _MCP["methods"].get(_k[1])
    if _mcp_n:
        _STATIC_OWNERS.setdefault((_k[0], _mcp_n), _v)


def apply_mcp(text):
    """Replace every known SRG member token with its official MCP name."""
    m = _MCP["methods"]
    f = _MCP["fields"]

    def rep(mm):
        tok = mm.group(0)
        return m.get(tok) or f.get(tok) or tok

    return _SRG_RE.sub(rep, text)

def localize_locals(text, own_old=None):
    """Rename decompiler local variables to type-derived semantic names."""
    masked = _mask_code(text)
    regions = _method_regions(masked)

    def scope_of(pos):
        best = None
        for a, b in regions:
            if a <= pos <= b and (best is None or (b - a) < (best[1] - best[0])):
                best = (a, b)
        return best

    decls = []  # (pos, tok, type)
    for mm in _DECL_RE.finditer(masked):
        typ = mm.group("type")
        tok = mm.group("tok")
        first = typ.split(".")[0].split("<")[0]
        if first in _KW_TYPES:
            continue
        # judge "looks like a TYPE" by the LAST dotted component — fully
        # qualified refs (`com.trolmastercard.sexmod.a`) start lowercase but
        # end in a class name.
        tcheck = typ.replace("[]", "").replace("...", "").strip()
        if "." in tcheck:
            # qualified refs are always types (`com.trolmastercard.sexmod.a`)
            pass
        else:
            tlast = tcheck.split(".")[-1].split("<")[0]
            if not (_TYPE_OK.match(tlast) or tlast in _PRIMS):
                continue
        decls.append((mm.start("tok"), tok, typ))
    # untyped lambda parameters: `(var0, var1) ->` / `var1 ->` — no declared
    # type; name them arg1/arg2/... by position in the parameter list.
    for mm in re.finditer(r"\(((?:\s*" + _LOCAL_TOKEN + r"\s*,?)*)\)\s*->", masked):
        seg = mm.group(1)
        base_off = mm.start(1)
        k = 0
        for tm in re.finditer(_LOCAL_TOKEN, seg):
            k += 1
            decls.append((base_off + tm.start(), tm.group(0), "\x00lambda" + str(k)))
    for mm in re.finditer(r"(?<![\w$.])(" + _LOCAL_TOKEN + r")\s*->", masked):
        decls.append((mm.start(1), mm.group(1), "\x00lambda1"))
    # multi-declarators: `int var5 = 0, var6 = 0;` — inherit the type
    for mm in _DECL2_RE.finditer(masked):
        p = mm.start(1)
        seg = masked.rfind(";", 0, p)
        seg_end = masked.find("=", p)
        header = masked[max(seg, 0):p]
        last = None
        for d in _DECL_RE.finditer(header):
            last = d
        if last is not None:
            decls.append((p, mm.group(1), last.group("type")))
    # tokens USED but never declared (CFR GOTO-broken regions): infer the
    # type from the first plain assignment's RHS (`var3_3 = new Properties();`
    # -> Properties).  Iterated to a fixpoint so chains resolve
    # (var13_21 = var3_3.getProperty(...) needs var3_3 typed first).
    for _fix_round in range(5):
        changed = False
        _seen_toks = {tok for _, tok, _ in decls}
        for tok in sorted(set(re.findall(r"(?<![\w$.])(" + _LOCAL_TOKEN + r")(?![\w$])", masked))):
            if tok in _seen_toks:
                continue
            mm = re.search(r"(?<![\w$.])" + re.escape(tok) + r"\s*=(?![=])", masked)
            if not mm:
                continue
            i = mm.end()
            depth = 0
            j = i
            n = len(masked)
            while j < n:
                c = masked[j]
                if c in "([{":
                    depth += 1
                elif c in ")]}":
                    if depth == 0:
                        break
                    depth -= 1
                elif c in ";," and depth == 0:
                    break
                j += 1
            expr = masked[i:j].strip()
            if not expr or expr == "null":
                continue
            # locals already typed by earlier declarations seed the resolver
            mini_vt = {}
            for p2, tok2, typ2 in sorted(decls):
                if p2 >= mm.start():
                    break
                mini_vt[tok2] = typ2  # last decl before pos wins (per-method scope)
            try:
                t = resolve_expr_type(expr, mini_vt, own_old)
            except Exception:
                t = None
            if t and not t.startswith("("):
                decls.append((mm.start(), tok, t))
                changed = True
                continue
            # compare-with-literal fallback (`var9_10 == 0.0`) -> primitive
            mc_ = re.search(
                re.escape(tok) + r"\s*[=!<>]=?\s*([-+]?[0-9][\w.]*)"
                r"|([-+]?[0-9][\w.]*)\s*[=!<>]=?\s*" + re.escape(tok),
                masked,
            )
            if mc_:
                lit = mc_.group(1) or mc_.group(2)
                if lit and re.match(r"^-?\d", lit):
                    if "." in lit:
                        t = "float" if lit[-1] in "fF" else "double"
                    elif lit[-1] in "lL":
                        t = "long"
                    else:
                        t = "int"
                    decls.append((mm.start(), tok, t))
                    changed = True
        if not changed:
            break

    if not decls:
        return text
    decls.sort()

    by_tok = {}
    for pos, tok, typ in decls:
        by_tok.setdefault(tok, []).append((pos, typ))

    scope_words = {}
    scope_used = {}

    def words_for(scope):
        if scope not in scope_words:
            if scope is None:
                scope_words[scope] = set(re.findall(r"\b[A-Za-z_]\w*\b", masked))
            else:
                scope_words[scope] = set(
                    re.findall(r"\b[A-Za-z_]\w*\b", masked[scope[0]:scope[1]]))
        return scope_words[scope]

    name_of = {}  # decl index -> new name
    for di, (pos, tok, typ) in enumerate(decls):
        if typ.startswith("\x00lambda"):
            base = "arg" + typ[len("\x00lambda"):]
        else:
            base = _local_base_name(typ)
        if not base or base == tok:
            name_of[di] = None
            continue
        sc = scope_of(pos)
        used = scope_used.setdefault(sc, set())
        if typ.startswith("\x00lambda"):
            cands = [base] + [base + chr(ord("b") + j) for j in range(8)]
            cand = next((c for c in cands
                         if c not in used and c not in _JAVA_KW
                         and c not in words_for(sc)), base + "_" + tok)
        else:
            cand = base
            k = 2
            while cand in used or cand in _JAVA_KW or cand in words_for(sc):
                cand = base + str(k)
                k += 1
        used.add(cand)
        name_of[di] = cand

    subs = []
    pos_to_name = {pos: name_of[di] for di, (pos, tok, typ) in enumerate(decls)}
    for tok, ds in by_tok.items():
        pat = re.compile(r"(?<![\w$.])" + re.escape(tok) + r"(?![\w$])")
        positions = [dm.start() for dm in pat.finditer(masked)]
        dpos = [d[0] for d in ds]
        for p in positions:
            # owner = last declaration at or before this occurrence
            lo, hi = 0, len(dpos) - 1
            owner = -1
            while lo <= hi:
                mid = (lo + hi) // 2
                if dpos[mid] <= p:
                    owner = mid
                    lo = mid + 1
                else:
                    hi = mid - 1
            if owner < 0:
                continue
            subs.append((p, p + len(tok), dpos[owner]))
    out = []
    pos_last = 0
    for s, e, dpos_owner in sorted(subs):
        nm = pos_to_name.get(dpos_owner)
        if not nm:
            continue
        out.append(text[pos_last:s])
        out.append(nm)
        pos_last = e
    out.append(text[pos_last:])
    return "".join(out)


if __name__ == "__main__":
    import shutil
    if os.path.exists(OUT):
        shutil.rmtree(OUT)
    renamed = copy_tree(SRC, OUT)
    print(f"renamed {renamed} files into {OUT}")
