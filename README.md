# Fapcraft (com.trolmastercard.sexmod)

Decompiled and recompiled copy of the Minecraft Forge 1.12.2 mod **Fapcraft** (`com.trolmastercard.sexmod`, TMC 1.1.0). The decompiled sources were fixed up so the mod compiles cleanly against the real Minecraft/Forge/GeckoLib classpath and produces a working mod jar.

## Contents

| Path | Description |
|------|-------------|
| `com/trolmastercard/sexmod/` | Compiled mod sources (~298 Java files) |
| `assets/` | Mod resources: Geo models, textures, animations |
| `_meta/` | Decompilation/renaming pipeline: `classes.json`, `members.json` mappings + `apply_renames.py` |
| `forge/` | ForgeGradle build pipeline (`build.gradle`, wrapper, `gradle.properties`) |
| `fapcraft-1.1.0.jar` | Pre-built mod jar (43 MB, GeckoLib + Apache shaded in) |
| `mcmod.info`, `logo.png`, `pack.mcmeta` | Mod metadata |

## Requirements

- Java 8 (Temurin 8 recommended), e.g. `C:\Program Files\Eclipse Adoptium\jdk-8.0.502.7-hotspot`
- Forge 1.12.2-14.23.5.2847 + MCP `stable_39` (resolved by ForgeGradle automatically)

## Build

```powershell
cd forge
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-8.0.502.7-hotspot"
gradlew.bat compileJava --no-daemon   # verify sources compile
gradlew.bat fatJar --no-daemon        # build jar with shaded deps
```

The `fatJar` task produces `forge/build/libs/fapcraft-1.1.0-all.jar` which includes the mod classes, resources, and the shaded `software.bernie.geckolib` + `org.apache.commons` runtime dependencies (needed because they are not present in the base Forge runtime).

## Install

Copy `fapcraft-1.1.0.jar` into the `mods/` folder of a Minecraft 1.12.2 + Forge 14.23.5.2847 installation.

## Notes

- The build source set deliberately excludes `org/**` and `software/**` (GeckoLib + Apache commons), pulled in via `deobfCompile` and shaded at package time.
- `META-INF/` signatures are excluded so Forge can load the jar.
- Original jar size ~46 MB; rebuilt fat jar ~45 MB.
