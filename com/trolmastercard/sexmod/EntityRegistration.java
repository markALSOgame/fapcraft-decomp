package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityRegistration {
   public static void registerEntitiesAndSpawning() {
      registerNpcEntity("jenny", JennyNpc.class, GirlRegistry.JENNY.npcID, 3286592, 12655237);
      registerNpcEntity("ellie", EllieNpc.class, GirlRegistry.ELLIE.npcID, 1447446, 9961472);
      registerNpcEntity("slime", SlimeNpc.class, GirlRegistry.SLIME.npcID, 13167780, 8244330);
      registerNpcEntity("bia", BiaNpc.class, GirlRegistry.BIA.npcID, 7488816, 7254603);
      registerNpcEntity("bee", BeeNpc.class, GirlRegistry.BEE.npcID, 16701032, 4400155);
      registerNpcEntity("luna", LunaNpc.class, GirlRegistry.LUNA.npcID, 7881787, 7940422);
      registerSimpleEntity("allie", AllieNpc.class, GirlRegistry.ALLIE.npcID);
      registerSimpleEntity("kobold", KoboldNpc.class, GirlRegistry.KOBOLD.npcID);
      registerSimpleEntity("kobold_egg", KoboldEggEntity.class, 4674237);
      registerNpcEntity("goblin", GoblinNpc.class, GirlRegistry.GOBLIN.npcID, 39424, 19456);
      registerNpcEntity("galath", GalathNpc.class, GirlRegistry.GALATH.npcID, 16711680, 16711680);
      registerNpcEntity("manglelie", ManglelieNpc.class, GirlRegistry.MANGLELIE.npcID, 16382457, 8485574);
      registerSimpleEntity("custom_model", PreviewEntity.class, 6281823);
      registerPlayerEntity("player_jenny", JennyPlayer.class, GirlRegistry.JENNY.playerID);
      registerPlayerEntity("player_ellie", ElliePlayer.class, GirlRegistry.ELLIE.playerID);
      registerPlayerEntity("player_slime", SlimePlayer.class, GirlRegistry.SLIME.playerID);
      registerPlayerEntity("player_bia", BiaPlayer.class, GirlRegistry.BIA.playerID);
      registerPlayerEntity("player_bee", BeePlayer.class, GirlRegistry.BEE.playerID);
      registerPlayerEntity("player_allie", AlliePlayer.class, GirlRegistry.ALLIE.playerID);
      registerPlayerEntity("player_kobold", KoboldPlayer.class, GirlRegistry.KOBOLD.playerID);
      registerPlayerEntity("player_goblin", GoblinPlayer.class, GirlRegistry.GOBLIN.playerID);
      registerPlayerEntity("player_luna", LunaPlayer.class, GirlRegistry.LUNA.playerID);
      registerPlayerEntity("player_galath", GalathPlayer.class, GirlRegistry.GALATH.playerID);
      registerSimpleEntity("friendly_slime", SlimeRainEntity.class, 5548484);
      registerSimpleEntity("luna_hook", LunaFamiliarEntity.class, 4768742);
      registerSimpleEntity("energy_ball", EnergyBallEntity.class, 2565153);
      registerSimpleEntity("pyrocinical", CultistEntity.class, 515153);
      EntityRegistry.addSpawn(SlimeNpc.class, 10, 1, 1, EnumCreatureType.CREATURE, new Biome[]{Biomes.SWAMPLAND, Biomes.MUTATED_SWAMPLAND});
      EntityRegistry.addSpawn(BeeNpc.class, 5, 1, 1, EnumCreatureType.CREATURE, new Biome[]{Biomes.FOREST, Biomes.FOREST_HILLS});
      EntityRegistry.addSpawn(CultistEntity.class, 3, 1, 1, EnumCreatureType.AMBIENT, new Biome[]{Biomes.HELL});
      EntityRegistry.addSpawn(ManglelieNpc.class, 5, 1, 1, EnumCreatureType.AMBIENT, new Biome[]{Biomes.HELL});
   }

   private static void registerPlayerEntity(String string, Class<? extends Entity> cls, int i) {
      EntityRegistry.registerModEntity(new ResourceLocation("sexmod:" + string), cls, string, i, Main.instance, 100, 1, false);
   }

   private static void registerNpcEntity(String string, Class<? extends Entity> cls, int i, int i2, int i3) {
      EntityRegistry.registerModEntity(new ResourceLocation("sexmod:" + string), cls, string, i, Main.instance, 50, 1, true, i2, i3);
   }

   private static void registerSimpleEntity(String string, Class<? extends Entity> cls, int i) {
      EntityRegistry.registerModEntity(new ResourceLocation("sexmod:" + string), cls, string, i, Main.instance, 50, 1, true);
   }
}
