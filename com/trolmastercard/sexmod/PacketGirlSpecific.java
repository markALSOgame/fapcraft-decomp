package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketGirlSpecific implements IMessage {
   boolean Loaded = false;
   EntityPlayer Player;
   HashMap<GirlRegistry, String> GirlDataMap = new HashMap<>();

   public PacketGirlSpecific() {
   }

   public PacketGirlSpecific(EntityPlayer player) {
      this.Player = player;
   }

   public void fromBytes(ByteBuf buf) {
      int i = buf.readInt();
      int i2 = 0;

      try {
         while (i2 < i) {
            this.GirlDataMap.put(GirlRegistry.valueOf(ByteBufUtils.readUTF8String(buf)), ByteBufUtils.readUTF8String(buf));
            i2++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      for (GirlRegistry girlType : GirlRegistry.values()) {
         try {
            if (!girlType.hasSpecifics) {
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         String string = this.Player.getEntityData().getString("sexmod:GirlSpecific" + girlType);

         try {
            if ("".equals(string)) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         this.GirlDataMap.put(girlType, string);
      }

      buf.writeInt(this.GirlDataMap.size());

      for (Entry entry : this.GirlDataMap.entrySet()) {
         ByteBufUtils.writeUTF8String(buf, ((GirlRegistry)entry.getKey()).toString());
         ByteBufUtils.writeUTF8String(buf, (String)entry.getValue());
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketGirlSpecific, IMessage> {

      public IMessage handle(PacketGirlSpecific packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.CLIENT) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketGirlSpecific.Handler.rethrow(runtimeException);
                    }
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketGirlSpecific.Handler.rethrow(runtimeException);
                }
            }
            this.handle(packet.GirlDataMap);
            return null;
        }

      @SideOnly(Side.CLIENT)
      public void handle(HashMap<GirlRegistry, String> map) {
         Minecraft mc = Minecraft.getMinecraft();
         mc.addScheduledTask(() -> mc.displayGuiScreen(new GuiGirlSelect(map)));
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
