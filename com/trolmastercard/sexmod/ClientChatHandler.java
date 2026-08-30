package com.trolmastercard.sexmod;

import java.util.UUID;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ClientChatHandler {
   public static ClientChatHandler Instance;
   private ClientChatHandler.Entry PendingEntry;

   public void a() {
      try {
         if (Instance.PendingEntry == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (--Instance.PendingEntry.e <= 0.0F) {
            Minecraft.getMinecraft()
               .player
               .sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.format("genderswap.sexpromt.timeout", new Object[0])));
            this.c();
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   public ClientChatHandler.Entry b() {
      return Instance.PendingEntry;
   }

   void c() {
      Instance.PendingEntry = null;
   }


    public void a(@Nonnull ClientChatHandler.Entry entry) {
        World world = Minecraft.getMinecraft().player.world;
        EntityPlayer entityPlayer2 = world.getPlayerEntityByUUID(entry.d);
        EntityPlayer entityPlayer = world.getPlayerEntityByUUID(entry.c);
        if (entityPlayer != null && entityPlayer2 != null) {
            String string = entry.PendingEntry ? entityPlayer.getName() : entityPlayer2.getName();
            TextComponentString textComponentString4 = new TextComponentString(TextFormatting.LIGHT_PURPLE + string + " " + TextFormatting.DARK_PURPLE + I18n.format((String)"genderswap.sexpromt.playerxaskedfory", new Object[0]) + " " + TextFormatting.LIGHT_PURPLE + I18n.format((String)entry.Instance, new Object[0]));
            TextComponentString textComponentString5 = new TextComponentString(TextFormatting.DARK_PURPLE + I18n.format((String)"genderswap.sexpromt.autodeletion", new Object[0]));
            TextComponentString textComponentString6 = new TextComponentString(TextFormatting.DARK_PURPLE + "[ " + TextFormatting.LIGHT_PURPLE + I18n.format((String)"genderswap.sexpromt.accept", new Object[0]) + TextFormatting.DARK_PURPLE + " | " + TextFormatting.LIGHT_PURPLE + I18n.format((String)"genderswap.sexpromt.decline", new Object[0]) + TextFormatting.DARK_PURPLE + " ]");
            entityPlayer2.sendMessage(textComponentString4);
            entityPlayer2.sendMessage(textComponentString5);
            entityPlayer2.sendMessage(textComponentString6);
            this.PendingEntry = entry;
        }
    }

   @SubscribeEvent
   public void a(ClientChatEvent clientChatEvent) {
      try {
         if (Instance.b() == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String string = clientChatEvent.getMessage().toLowerCase();
      if (string.equals(I18n.format("genderswap.sexpromt.accept", new Object[0]).toLowerCase())) {
         ClientChatHandler.Entry entry = Instance.b();
         this.a(entry.Instance, entry.d, entry.c);
         this.c();
         clientChatEvent.setCanceled(true);
      }

      try {
         if (string.equals(I18n.format("genderswap.sexpromt.decline", new Object[0]).toLowerCase())) {
            Minecraft.getMinecraft()
               .player
               .sendMessage(
                  new TextComponentString(TextFormatting.DARK_PURPLE + I18n.format("genderswap.sexpromt.declineconformation", new Object[0]))
               );
            this.c();
            clientChatEvent.setCanceled(true);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   void a(String string, UUID uuid, UUID uuid2) {
      NetworkHandler.channel.sendToServer(new PacketStartStandingSexAnimation(uuid, uuid2, string));
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Entry {
      public String Instance;
      public UUID c;
      public UUID d;
      public float e;
      boolean PendingEntry;

      public Entry(String string, UUID uuid, UUID uuid2, boolean flag) {
         this.Instance = string;
         this.c = uuid;
         this.d = uuid2;
         this.e = 1200.0F;
         this.PendingEntry = flag;
      }
   }
}
