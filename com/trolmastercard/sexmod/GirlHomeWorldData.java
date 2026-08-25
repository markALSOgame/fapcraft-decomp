package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Map;

public class GirlHomeWorldData extends WorldSavedData {
   static final String SaveKey = "sexmod:customstaticgirlnames";
   static final HashMap<UUID, HashMap<GirlRegistry, String>> CustomNameData = new HashMap<>();

   public GirlHomeWorldData() {
      super("sexmod:customstaticgirlnames");
   }

   public GirlHomeWorldData(String string) {
      super("sexmod:customstaticgirlnames");
   }

   @SubscribeEvent
   public void onWorldSave(Save save) {
      World world = save.getWorld();
      world.getMapStorage().setData("sexmod:customstaticgirlnames", this);
      this.markDirty();
   }

   @SubscribeEvent
   public void onWorldLoad(Load load) {
      World world = load.getWorld();
      world.getMapStorage().getOrLoadData(GirlHomeWorldData.class, "sexmod:customstaticgirlnames");
   }

   public static void setCustomName(UUID uuid, GirlRegistry girlType, String string) {
      HashMap map = CustomNameData.get(uuid);
      if (map == null) {
         map = new HashMap();
      }

      map.put(girlType, string);
      CustomNameData.put(uuid, map);
   }

   @Nullable
   public static String getCustomName(UUID uuid, GirlRegistry girlType) {
      HashMap map = CustomNameData.get(uuid);

      try {
         if (map == null) {
            return null;
         }
      } catch (IllegalArgumentException error) {
         throw rethrow(error);
      }

      return (String)map.get(girlType);
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
      for (String string : tagCompound.getKeySet()) {
         UUID uuid;
         try {
            uuid = UUID.fromString(string);
         } catch (IllegalArgumentException error) {
            continue;
         }

         CustomNameData.put(uuid, this.a(tagCompound.getCompoundTag(string)));
      }
   }

   public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
      for (Entry entry : CustomNameData.entrySet()) {
         UUID uuid = (UUID)entry.getKey();
         tagCompound.setTag(uuid.toString(), this.a((HashMap<GirlRegistry, String>)entry.getValue()));
      }

      return tagCompound;
   }

   private NBTTagCompound writeNamesToNbt(HashMap<GirlRegistry, String> map) {
      NBTTagCompound tagCompound = new NBTTagCompound();

      for (Entry entry : map.entrySet()) {
         tagCompound.setString(((GirlRegistry)entry.getKey()).name(), (String)entry.getValue());
      }

      return tagCompound;
   }

   private HashMap<GirlRegistry, String> readNamesFromNbt(NBTTagCompound tagCompound) {
      HashMap map = new HashMap();

      for (GirlRegistry girlType : GirlRegistry.values()) {
         String string = tagCompound.getString(girlType.name());

         try {
            if (!"".equals(string)) {
               map.put(girlType, string);
            }
         } catch (IllegalArgumentException error) {
            throw rethrow(error);
         }
      }

      return map;
   }

   private static IllegalArgumentException rethrow(IllegalArgumentException error) {
      return error;
   }
}
