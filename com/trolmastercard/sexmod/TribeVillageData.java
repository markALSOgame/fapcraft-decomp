package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TribeVillageData extends WorldSavedData {
   public static final List<BlockPos> GalathSpawnPositions = new ArrayList<>();
   public static final List<BlockPos> ManglelieSpawnPositions = new ArrayList<>();
   static final String SaveKey = "sexmod:galath_spawn_list";

   public TribeVillageData() {
      super("sexmod:galath_spawn_list");
   }

   public TribeVillageData(String string) {
      super("sexmod:galath_spawn_list");
   }

   public static void addSpawnPosition(BlockPos pos, List<BlockPos> list) {
      list.add(pos);
   }

   @SubscribeEvent
   public void onWorldSave(Save save) {
      World world = save.getWorld();
      world.getMapStorage().setData("sexmod:galath_spawn_list", this);
      this.markDirty();
   }

   @SubscribeEvent
   public void onWorldLoad(Load load) {
      World world = load.getWorld();
      world.getMapStorage().getOrLoadData(TribeVillageData.class, "sexmod:galath_spawn_list");
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = tagCompound.getCompoundTag("sexmod:galath_spawn_list");
      this.readPositions(tagCompound2, "", GalathSpawnPositions);
      this.readPositions(tagCompound2, "mang", ManglelieSpawnPositions);
   }

   public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      this.writePositions(tagCompound2, "", GalathSpawnPositions);
      this.writePositions(tagCompound2, "mang", ManglelieSpawnPositions);
      tagCompound.setTag("sexmod:galath_spawn_list", tagCompound2);
      return tagCompound;
   }

   void writePositions(NBTTagCompound tagCompound, String string, List<BlockPos> list) {
      tagCompound.setInteger("sexmod:pos_amount" + string, list.size());
      int i = 0;

      for (BlockPos pos : list) {
         tagCompound.setInteger("sexmod:x" + string + i, pos.getX());
         tagCompound.setInteger("sexmod:y" + string + i, pos.getY());
         tagCompound.setInteger("sexmod:z" + string + i, pos.getZ());
         i++;
      }
   }

   void readPositions(NBTTagCompound tagCompound, String string, List<BlockPos> list) {
      list.clear();
      int i = tagCompound.getInteger("sexmod:pos_amount" + string);
      int i2 = 0;

      try {
         while (i2 < i) {
            list.add(
               new BlockPos(
                  tagCompound.getInteger("sexmod:x" + string + i2), tagCompound.getInteger("sexmod:y" + string + i2), tagCompound.getInteger("sexmod:z" + string + i2)
               )
            );
            i2++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
