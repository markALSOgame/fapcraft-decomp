package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSendChatMessage implements IMessage {
   boolean Loaded;
   String Message;
   int DimensionId;
   UUID SenderUuid;

   public PacketSendChatMessage(String string, int i, UUID uuid) {
      this.Message = string;
      this.DimensionId = i;
      this.SenderUuid = uuid;
      this.Loaded = true;
   }

   public PacketSendChatMessage() {
      this.Loaded = false;
   }

   public void fromBytes(ByteBuf buf) {
      try {
         int i = buf.readInt();
         byte[] bytes = new byte[i];
         int i2 = 0;

         try {
            while (i2 < i) {
               bytes[i2] = buf.readByte();
               i2++;
            }
         } catch (IndexOutOfBoundsException error) {
            throw rethrow(error);
         }

         this.Message = new String(bytes);
         this.DimensionId = buf.readInt();
         this.SenderUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
         this.Loaded = true;
      } catch (IndexOutOfBoundsException error2) {
         this.Loaded = false;
         System.out.println("couldn't read bytes @SendChatMessage :(");
      }
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.Message.getBytes().length);
      buf.writeBytes(this.Message.getBytes());
      buf.writeInt(this.DimensionId);
      ByteBufUtils.writeUTF8String(buf, this.SenderUuid.toString());
   }

   private static IndexOutOfBoundsException rethrow(IndexOutOfBoundsException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketSendChatMessage, IMessage> {
      public IMessage handle(PacketSendChatMessage packet, MessageContext ctx) {
         try {
            if (!packet.Loaded) {
               System.out.println("recieved an unvalid message @SendChatMessage :(");
               return null;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (ctx.side.isClient()) {
               Minecraft.getMinecraft().player.sendMessage(new TextComponentString(packet.Message));
               return null;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
            Vec3d vec3d = GirlEntity.getGirlsByOwner(packet.SenderUuid).get(0).M();
            NetworkHandler.channel.sendToAllAround(new PacketSendChatMessage(packet.Message, packet.DimensionId, packet.SenderUuid), new TargetPoint(packet.DimensionId, vec3d.x, vec3d.y, vec3d.z, 40.0));
         });
         return null;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
