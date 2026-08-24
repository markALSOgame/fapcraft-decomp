package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomModelWorldData extends WorldSavedData {
   static final String a = "sexmod:static_custom_model_manager";
   static final String d = "sexmod:static_custom_model_manager";
   public static HashMap<UUID, String> c = new HashMap<>();
   public static HashMap<UUID, String> b = new HashMap<>();

   public CustomModelWorldData() {
      super("sexmod:static_custom_model_manager");
   }

   public CustomModelWorldData(String string) {
      super("sexmod:static_custom_model_manager");
   }

   public static String getCustomModelName(GirlEntity girl) {
      String string = b(girl);

      try {
         return string == null ? "" : string;
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   private static String b(GirlEntity girl) {
      if (girl instanceof GalathNpc) {
         UUID uuid = girl.getGirlUuid();
         UUID uuid2 = GalathOwnershipData.getOwnerUuid(uuid);
         if (uuid2 == null) {
            uuid2 = uuid;
         }

         return c.get(uuid2);
      } else if (girl instanceof ManglelieNpc) {
         UUID uuid3 = GalathOwnershipData.getOwnerUuid(((ManglelieNpc)girl).v());

         HashMap map;
         try {
            map = b;
            if (uuid3 == null) {
               return b.get(girl.getGirlUuid());
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         return (String)map.get(uuid3);
      } else {
         return null;
      }
   }

   public static void removeGirl(GirlEntity girl) {
      if (girl instanceof GalathNpc) {
         UUID uuid = girl.getGirlUuid();
         UUID uuid2 = GalathOwnershipData.getOwnerUuid(uuid);
         if (uuid2 == null) {
            uuid2 = uuid;
         }

         c.put(uuid2, girl.C());
      } else {
         if (girl instanceof ManglelieNpc) {
            UUID uuid3 = GalathOwnershipData.getOwnerUuid(((ManglelieNpc)girl).v());

            HashMap map;
            UUID uuid4;
            label26: {
               try {
                  map = b;
                  if (uuid3 == null) {
                     uuid4 = girl.getGirlUuid();
                     break label26;
                  }
               } catch (RuntimeException error) {
                  throw rethrow(error);
               }

               uuid4 = uuid3;
            }

            map.put(uuid4, girl.C());
         }
      }
   }

   @SubscribeEvent
   public void a(Save save) {
      World world = save.getWorld();
      world.getMapStorage().setData("sexmod:static_custom_model_manager", this);
      this.markDirty();
   }

   @SubscribeEvent
   public void a(Load load) {
      World world = load.getWorld();
      world.getMapStorage().getOrLoadData(CustomModelWorldData.class, "sexmod:static_custom_model_manager");
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = tagCompound.getCompoundTag("sexmod:static_custom_model_manager");
      this.a(tagCompound2.getCompoundTag("galath"), c);
      this.a(tagCompound2.getCompoundTag("mang"), b);
   }

   public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      tagCompound2.setTag("galath", this.a(c));
      tagCompound2.setTag("mang", this.a(b));
      tagCompound.setTag("sexmod:static_custom_model_manager", tagCompound2);
      return tagCompound;
   }

   NBTTagCompound a(HashMap<UUID, String> map) {
      NBTTagCompound tagCompound = new NBTTagCompound();
      int i = 0;

      for (Entry entry : map.entrySet()) {
         UUID uuid = (UUID)entry.getKey();
         tagCompound.setString("UUID" + i, uuid.toString());
         tagCompound.setString("MODEL" + i, (String)entry.getValue());
         i++;
      }

      return tagCompound;
   }

   void a(NBTTagCompound tagCompound, HashMap<UUID, String> map) {
      int i = 0;

      while (true) {
         String string = tagCompound.getString("UUID" + i);

         try {
            if ("".equals(string)) {
               return;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         map.put(UUID.fromString(string), tagCompound.getString("MODEL" + i));
         i++;
      }
   }

   public static void clearAll() {
      c.clear();
      b.clear();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
