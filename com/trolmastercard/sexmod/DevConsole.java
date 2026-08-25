package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Map;

public class DevConsole {
   static final int DevFloatCount = 60;
   public static float[] DevFloats;

   public DevConsole() {
      if (isDeobfuscatedEnvironment()) {
         DevFloats = new float[60];
      }
   }

   public static boolean isDeobfuscatedEnvironment() {
      return (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onResetColorCommand(ClientChatEvent clientChatEvent) {
      try {
         if (!isDeobfuscatedEnvironment()) {
            return;
         }
      } catch (NullPointerException error) {
         throw rethrow(error);
      }

      try {
         if (!"resetcolor".equalsIgnoreCase(clientChatEvent.getMessage())) {
            return;
         }
      } catch (NullPointerException error2) {
         throw rethrow(error2);
      }

      KoboldNpcRenderer.clearColorCache();
      KoboldPlayerRenderer.clearColorCache();
      GoblinNpcRenderer.clearColorCache();
      GoblinPlayerRenderer.clearColorCache();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onSetDevFloatCommand(ClientChatEvent clientChatEvent) {
         if (!isDeobfuscatedEnvironment()) {
            return;
         }

      String string = clientChatEvent.getOriginalMessage();
      String[] stringArray = string.split(" ");

         if (stringArray.length != 3) {
            return;
         }

         if (!"set".equalsIgnoreCase(stringArray[0])) {
            return;
         }

      int i;
      float f;
      try {
         i = Integer.parseInt(stringArray[1]);
         f = Float.parseFloat(stringArray[2]);
         if (DevFloats.length - 1 < i) {
            return;
         }
      } catch (Exception error4) {
         return;
      }

      Minecraft.getMinecraft()
         .player
         .sendMessage(new TextComponentString(String.format("%sSet dev float N.%s from %s to %s", TextFormatting.GRAY, i, DevFloats[i], f)));
      DevFloats[i] = f;
      clientChatEvent.setCanceled(true);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onGetDevFloatCommand(ClientChatEvent clientChatEvent) {
         if (!isDeobfuscatedEnvironment()) {
            return;
         }

      String string = clientChatEvent.getOriginalMessage();
      String[] stringArray = string.split(" ");

         if (stringArray.length != 2) {
            return;
         }

         if (!"get".equalsIgnoreCase(stringArray[0])) {
            return;
         }

      int i;
      try {
         i = Integer.parseInt(stringArray[1]);
         if (DevFloats.length - 1 < i) {
            return;
         }
      } catch (Exception error4) {
         return;
      }

      Minecraft.getMinecraft()
         .player
         .sendMessage(new TextComponentString(String.format("%sdev float N.%s is %s", TextFormatting.YELLOW, i, DevFloats[i])));
      clientChatEvent.setCanceled(true);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onKoboldHurtDebug(LivingHurtEvent livingHurtEvent) {
      try {
         if (!isDeobfuscatedEnvironment()) {
            return;
         }
      } catch (NullPointerException error) {
         throw rethrow(error);
      }

      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
      EntityLivingBase livingBase = livingHurtEvent.getEntityLiving();

      try {
         if (!(livingBase instanceof KoboldNpc)) {
            return;
         }
      } catch (NullPointerException error2) {
         throw rethrow(error2);
      }

      KoboldNpc kobold = (KoboldNpc)livingBase;
      UUID uuid = GirlHomeBuilder.findTribeUuid(mcPlayer.getPersistentID());

      for (TreeCluster treeCluster : GirlHomeBuilder.getTreeClusters(uuid)) {
         this.sendChatMessage("task: " + treeCluster.getTaskType().name());
         this.sendChatMessage("workers involved: ");

         for (KoboldNpc kobold2 : treeCluster.getAssignees()) {
            this.sendChatMessage(kobold2.getDisplayName() + " " + kobold2.getGirlUuid());
         }
      }

      this.sendChatMessage("tribe contains my exact reference: " + GirlHomeBuilder.getKobolds(uuid).contains(kobold));
      this.sendChatMessage("tribe contains my ID: ");
      boolean flag = false;

      for (KoboldNpc kobold3 : GirlHomeBuilder.getKobolds(uuid)) {
         if (kobold3.getGirlUuid().equals(kobold.getGirlUuid())) {
            flag = true;
         }
      }

      boolean flag2 = false;

      for (Entry entry : GirlHomeBuilder.getLoadedHomes(uuid, mcPlayer.world).entrySet()) {
         if (((UUID)entry.getKey()).equals(kobold.getGirlUuid())) {
            flag2 = true;
         }
      }

      this.sendChatMessage("loaded : " + flag);
      this.sendChatMessage("saved : " + flag2);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onDevCommand(ClientChatEvent clientChatEvent) {
      try {
         if (!isDeobfuscatedEnvironment()) {
            return;
         }
      } catch (NullPointerException error) {
         throw rethrow(error);
      }

      String string = clientChatEvent.getOriginalMessage().toLowerCase();
      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;

      try {
         if ("time".equals(string)) {
            mcPlayer.sendMessage(new TextComponentString(String.valueOf(mcPlayer.world.getTotalWorldTime())));
         }
      } catch (NullPointerException error2) {
         throw rethrow(error2);
      }

      if ("girls".equals(string)) {
         List<GirlEntity> list = mcPlayer.world.getEntities(GirlEntity.class, arg1 -> true);
         mcPlayer.sendMessage(new TextComponentString(String.valueOf(list.size())));

         for (GirlEntity girl : list) {
            System.out.printf("%s at %s %s %s\n", girl, girl.posX, girl.posY, girl.posZ);
         }
      }

      if ("kobs".equals(string)) {
         UUID uuid = GirlHomeBuilder.findTribeUuid(mcPlayer.getPersistentID());
         int i = GirlHomeBuilder.getKoboldCount(uuid);

         for (KoboldNpc kobold : GirlHomeBuilder.getKobolds(uuid)) {
            DevConsole devConsole;
            String string2;
            label61: {
               try {
                  this.sendChatMessage(
                     String.format(
                        "alive member %s at %s world.isremote? %s isdead %s girlID %s entityID %s",
                        kobold.getDisplayName(),
                        kobold.getPosition(),
                        kobold.world.isRemote,
                        kobold.isDead,
                        kobold.getGirlUuid(),
                        kobold.getEntityId()
                     )
                  );
                  devConsole = this;
                  if (mcPlayer.world.getEntitiesWithinAABB(KoboldNpc.class, new AxisAlignedBB(kobold.getPosition())).isEmpty()) {
                     string2 = "couldn't be located";
                     break label61;
                  }
               } catch (NullPointerException error3) {
                  throw rethrow(error3);
               }

               string2 = "appears to actually exist";
            }

            devConsole.sendChatMessage(string2);
         }

         HashMap<UUID, BlockPos> map = GirlHomeBuilder.getLoadedHomes(uuid, mcPlayer.world);

         for (Entry entry : map.entrySet()) {
            this.sendChatMessage(String.format("saved pos of %s at %s", ((UUID)entry.getKey()).toString(), ((BlockPos)entry.getValue()).toString()));
         }

         this.sendChatMessage("total amount members: " + i);
      }

      if (string.startsWith("setcumtime ")) {
         String[] stringArray = string.split(" ");

         long l;
         try {
            l = Long.parseLong(stringArray[1]);
         } catch (NullPointerException error4) {
            System.out.println("long: " + stringArray[1]);
            error4.printStackTrace();
            return;
         }

         GalathOwnershipData.setLastSeenTime(mcPlayer.getPersistentID(), l);
         mcPlayer.sendMessage(new TextComponentString("set to: " + l));
      }
   }

   @SideOnly(Side.CLIENT)
   void sendChatMessage(String string) {
      Minecraft.getMinecraft().player.sendMessage(new TextComponentString(string));
   }
 static RuntimeException rethrow(RuntimeException error) {

      return error;

   }


   private static RuntimeException rethrow(Exception error) {
      return new RuntimeException(error);
   }
}
