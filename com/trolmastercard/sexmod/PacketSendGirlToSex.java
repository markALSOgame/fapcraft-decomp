package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSendGirlToSex implements IMessage {
   boolean Loaded;
   UUID GirlUuid;

   public PacketSendGirlToSex() {
      this.Loaded = false;
   }

   public PacketSendGirlToSex(UUID uuid) {
      this.GirlUuid = uuid;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketSendGirlToSex, IMessage> {
      public IMessage handle(PacketSendGirlToSex packet, MessageContext ctx) {
         try {
            if (!packet.Loaded) {
               System.out.println("received an invalid message @SendGirlToSex :(");
               return null;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
            for (GirlEntity girl : GirlEntity.getGirlsByOwner(packet.GirlUuid)) {
               try {
                  if (girl.world.isRemote) {
                     continue;
                  }
               } catch (RuntimeException error2) {
                  throw rethrow(error2);
               }

               try {
                  if (girl instanceof EmptyAction) {
                     ((EmptyAction)girl).a();
                  }
               } catch (RuntimeException error3) {
                  throw rethrow(error3);
               }
            }
         });
         return null;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
