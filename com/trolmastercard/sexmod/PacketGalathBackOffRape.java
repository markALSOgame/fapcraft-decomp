package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGalathBackOffRape implements IMessage {
   boolean Loaded = false;

   public void fromBytes(ByteBuf buf) {
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
   }

   public static class Handler implements IMessageHandler<PacketGalathBackOffRape, IMessage> {

      public IMessage handle(PacketGalathBackOffRape packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketGalathBackOffRape.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid Message @GalathBackOffRape :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketGalathBackOffRape.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                GirlEntity girl = GirlEntity.getByUuidForSide(ctx.getServerHandler().player.getPersistentID(), true);
                try {
                    if (girl instanceof GalathNpc) {
                        ((GalathNpc)girl).w();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketGalathBackOffRape.Handler.rethrow(runtimeException);
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
