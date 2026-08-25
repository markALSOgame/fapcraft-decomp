package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.UUID;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PacketSetTribeFollowMode implements IMessage {
   boolean Loaded = false;
   boolean FollowMode;

   public PacketSetTribeFollowMode() {
   }

   public PacketSetTribeFollowMode(boolean flag) {
      this.FollowMode = flag;
   }

   public void fromBytes(ByteBuf buf) {
      this.FollowMode = buf.readBoolean();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeBoolean(this.FollowMode);
   }

   public static class Handler implements IMessageHandler<PacketSetTribeFollowMode, IMessage> {

      public IMessage onMessage(PacketSetTribeFollowMode packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && !ctx.side.isClient()) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSetTribeFollowMode.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @SetTribeFollowMode :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSetTribeFollowMode.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                UUID uUID = GirlHomeBuilder.findTribeUuid(ctx.getServerHandler().player.getPersistentID());
                try {
                    if (uUID == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSetTribeFollowMode.Handler.rethrow(runtimeException);
                }
                GirlHomeBuilder.setHasTribe(uUID, packet.FollowMode);
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
