package com.trolmastercard.sexmod;

import java.util.UUID;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
        String string;
        StringBuilder stringBuilder;
        TextComponentString textComponentString;
        TextComponentString textComponentString2;
        EntityPlayer entityPlayer;
        EntityPlayer entityPlayer2;
        block6: {
            World world = Minecraft.getMinecraft().player.world;
            entityPlayer2 = world.getPlayerEntityByUUID(entry.d);
            entityPlayer = world.getPlayerEntityByUUID(entry.c);
            try {
                try {
                    if (entityPlayer != null && entityPlayer2 != null) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw ClientChatHandler.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ClientChatHandler.rethrow(runtimeException);
            }
        }
        try {
            TextComponentString textComponentString3;
            textComponentString2 = textComponentString3;
            textComponentString = textComponentString3;
            stringBuilder = new StringBuilder().append(TextFormatting.LIGHT_PURPLE);
            string = entry.PendingEntry ? entityPlayer.getName() : entityPlayer2.getName();
        }
        catch (RuntimeException runtimeException) {
            throw ClientChatHandler.rethrow(runtimeException);
        }
        textComponentString2(stringBuilder.append(string).append(" ").append(TextFormatting.DARK_PURPLE).append(I18n.format((String)"genderswap.sexpromt.playerxaskedfory", (Object[])new Object[0])).append(" ").append(TextFormatting.LIGHT_PURPLE).append(I18n.format((String)entry.Instance, (Object[])new Object[0])).toString());
        TextComponentString textComponentString4 = textComponentString;
        TextComponentString textComponentString5 = new TextComponentString(TextFormatting.DARK_PURPLE + I18n.format((String)"genderswap.sexpromt.autodeletion", (Object[])new Object[0]));
        TextComponentString textComponentString6 = new TextComponentString(TextFormatting.DARK_PURPLE + "[ " + TextFormatting.LIGHT_PURPLE + I18n.format((String)"genderswap.sexpromt.accept", (Object[])new Object[0]) + TextFormatting.DARK_PURPLE + " | " + TextFormatting.LIGHT_PURPLE + I18n.format((String)"genderswap.sexpromt.decline", (Object[])new Object[0]) + TextFormatting.DARK_PURPLE + " ]");
        entityPlayer2.sendMessage((ITextComponent)textComponentString4);
        entityPlayer2.sendMessage((ITextComponent)textComponentString5);
        entityPlayer2.sendMessage((ITextComponent)textComponentString6);
        this.PendingEntry = entry;
    }

   @SubscribeEvent
   public void a(ClientChatEvent clientChatEvent) {
      try {
         if (Instance.getSelectedClothingOptions() == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String string = clientChatEvent.getMessage().toLowerCase();
      if (string.equals(I18n.format("genderswap.sexpromt.accept", new Object[0]).toLowerCase())) {
         ClientChatHandler.Entry entry = Instance.getSelectedClothingOptions();
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
