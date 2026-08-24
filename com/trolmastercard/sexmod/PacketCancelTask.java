package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCancelTask implements IMessage {
   boolean Loaded = false;
   BlockPos Pos;

   public PacketCancelTask() {
   }

   public PacketCancelTask(BlockPos pos) {
      this.Pos = pos;
   }

   public void fromBytes(ByteBuf buf) {
      this.Pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.Pos.getX());
      buf.writeInt(this.Pos.getY());
      buf.writeInt(this.Pos.getZ());
   }

   public static class Handler implements IMessageHandler<PacketCancelTask, IMessage> {

      public IMessage handle(PacketCancelTask packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketCancelTask.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid Message @CancelTask :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketCancelTask.Handler.rethrow(runtimeException);
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
                    throw PacketCancelTask.Handler.rethrow(runtimeException);
                }
                HashSet<BlockPos> hashSet = GirlHomeBuilder.findAndRemoveAnchorAt(uUID, packet.Pos);
                try {
                    if (hashSet.isEmpty()) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketCancelTask.Handler.rethrow(runtimeException);
                }
                NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet, false), ctx.getServerHandler().player);
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
