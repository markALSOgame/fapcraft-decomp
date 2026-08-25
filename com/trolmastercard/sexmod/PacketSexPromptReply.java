package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSexPromptReply implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   boolean Accept;
   boolean Reject;
   UUID PlayerUuid = null;

   public PacketSexPromptReply() {
      this.Loaded = false;
   }

   public PacketSexPromptReply(UUID uuid, UUID uuid2, boolean flag, boolean flag2) {
      this.GirlUuid = uuid;
      this.Accept = flag;
      this.PlayerUuid = uuid2;
      this.Reject = flag2;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Accept = buf.readBoolean();
      this.Reject = buf.readBoolean();
      String string = ByteBufUtils.readUTF8String(buf);

      PacketSexPromptReply packet;
      UUID uuid;
      label17: {
         try {
            packet = this;
            if (string.equals("null")) {
               uuid = null;
               break label17;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         uuid = UUID.fromString(string);
      }

      packet.PlayerUuid = uuid;
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBuf buf2;
      String string;
      label16: {
         try {
            ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
            buf.writeBoolean(this.Accept);
            buf.writeBoolean(this.Reject);
            buf2 = buf;
            if (this.PlayerUuid == null) {
               string = "null";
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         string = this.PlayerUuid.toString();
      }

      ByteBufUtils.writeUTF8String(buf2, string);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketSexPromptReply, IMessage> {

      public static void handle(UUID uuid, UUID uuid2, boolean flag, boolean flag2) {
         try {
            for (GirlEntity girl : GirlEntity.getGirlsByOwner(uuid)) {
               if (girl.world.isRemote) continue;
               if (girl instanceof JennyNpc || girl instanceof EllieNpc || girl instanceof LunaNpc) {
                  girl.tasks.removeTask(girl.WatchPlayerAI);
                  girl.tasks.removeTask(girl.WanderAI);
               }

               girl.getNavigator().clearPath();
               girl.motionX = 0.0;
               girl.motionY = 0.0;
               girl.motionZ = 0.0;
               if (girl.getSexPlayerUuid() == null) {
                  girl.handleGirlUuidEvent(uuid2);
               }

               if (flag2) {
                  girl.setTargetPos(girl.getPlayerFrontPos());
               }

               girl.teleportPlayerInFront(girl.getSexPlayerUuid());
               if (!flag) {
                  return;
               }

               if (!(girl instanceof VoidCallback)) {
                  return;
               }

               VoidCallback callback = (VoidCallback)girl;
               callback.void_b();
            }
         } catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
         }
      }


      public IMessage handle(PacketSexPromptReply packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (!packet.Loaded || ctx.side != Side.SERVER) break block4;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PacketSexPromptReply.Handler.rethrow(concurrentModificationException);
                    }
                    FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> PacketSexPromptReply.Handler.handle(packet.GirlUuid, packet.PlayerUuid, packet.Accept, packet.Reject));
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PacketSexPromptReply.Handler.rethrow(concurrentModificationException);
                }
            }
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
