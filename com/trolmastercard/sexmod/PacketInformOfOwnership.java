package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketInformOfOwnership implements IMessage {
   boolean Loaded = false;
   boolean Owns;

   public PacketInformOfOwnership() {
   }

   public PacketInformOfOwnership(boolean flag) {
      this.Owns = flag;
   }

   public void fromBytes(ByteBuf buf) {
      this.Owns = buf.readBoolean();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeBoolean(this.Owns);
   }

   public static class Handler implements IMessageHandler<PacketInformOfOwnership, IMessage> {

      public IMessage handle(PacketInformOfOwnership packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.CLIENT)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketInformOfOwnership.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @InformOfOwnership :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketInformOfOwnership.Handler.rethrow(runtimeException);
                }
            }
            GalathOwnershipData.f = packet.Owns;
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
