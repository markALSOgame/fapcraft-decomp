package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketGalathRapePounce implements IMessage {
   boolean Loaded = false;
   boolean Active;

   public PacketGalathRapePounce() {
   }

   public PacketGalathRapePounce(boolean flag) {
      this.Active = flag;
   }

   public void fromBytes(ByteBuf buf) {
      this.Active = buf.readBoolean();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeBoolean(this.Active);
   }

   public static class Handler implements IMessageHandler<PacketGalathRapePounce, IMessage> {

      public IMessage onMessage(PacketGalathRapePounce packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketGalathRapePounce.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @GalathRapePounce :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketGalathRapePounce.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                GirlEntity girl = GirlEntity.getByUuid(ctx.getServerHandler().player.getPersistentID());
                try {
                    if (girl instanceof GalathNpc) {
                        ((GalathNpc)girl).c(packet.Active);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketGalathRapePounce.Handler.rethrow(runtimeException);
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
