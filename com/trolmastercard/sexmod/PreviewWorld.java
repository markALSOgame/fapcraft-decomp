package com.trolmastercard.sexmod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PreviewWorld extends WorldClient {
   public Biome getBiomeForCoordsBody(BlockPos pos) {
      return new BiomePlains(false, new BiomeProperties("Plains").setBaseHeight(0.125F).setHeightVariation(0.05F).setHeightVariation(0.8F).setRainfall(0.4F));
   }

   public void notifyNeighborsOfStateChange(BlockPos pos, Block block, boolean flag) {
      super.notifyNeighborsOfStateChange(pos, block, flag);
   }

   public void markAndNotifyBlock(BlockPos pos, Chunk chunk, IBlockState state, IBlockState state2, int i) {
   }

   public float getSunBrightnessFactor(float f) {
      return 1.0F;
   }

   @SideOnly(Side.CLIENT)
   public float getSunBrightnessBody(float f) {
      return 1.0F;
   }

   public void updateWeatherBody() {
   }

   public boolean canBlockFreezeBody(BlockPos pos, boolean flag) {
      return false;
   }

   public boolean canSnowAtBody(BlockPos pos, boolean flag) {
      return false;
   }

   public PreviewWorld() {
      super(
         new PreviewNetHandler(Minecraft.getMinecraft()),
         new WorldSettings(0L, GameType.SURVIVAL, false, false, WorldType.FLAT),
         0,
         EnumDifficulty.HARD,
         new Profiler()
      );
      this.provider.setWorld(this);
   }

   public boolean canMineBlockBody(EntityPlayer player, BlockPos pos) {
      return false;
   }

   public boolean isSideSolid(BlockPos pos, EnumFacing facing) {
      try {
         if (pos.getY() <= 63) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   public boolean isSideSolid(BlockPos pos, EnumFacing facing, boolean flag) {
      try {
         if (pos.getY() <= 63) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   public int countEntities(EnumCreatureType enumCreatureType, boolean flag) {
      return 0;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
