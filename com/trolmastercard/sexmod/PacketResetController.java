package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketResetController implements IMessage {
   public static final int Range = 100;
   boolean Loaded;
   UUID GirlUuid;
   UUID UnusedUuid;

   public PacketResetController() {
      this.Loaded = false;
   }

   public PacketResetController(UUID uuid) {
      this.GirlUuid = uuid;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketResetController, IMessage> {

      public IMessage handle(PacketResetController packet, MessageContext ctx) {
            try {
                if (!packet.Loaded) {
                    System.out.println("received an invalid message @ResetController :(");
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw PacketResetController.Handler.rethrow(runtimeException);
            }
            if (ctx.side.isServer()) {
                GirlEntity girl = GirlEntity.getServerSideByUuid(packet.GirlUuid);
                try {
                    if (girl == null) {
                        return null;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketResetController.Handler.rethrow(runtimeException);
                }
                UUID uUID = ctx.getServerHandler().player.getPersistentID();
                girl.getCurrentAction().ticksPlaying = new int[]{0, 0};
                for (EntityPlayerMP entityPlayerMP : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers()) {
                    try {
                        try {
                            if (uUID.equals(entityPlayerMP.getPersistentID()) || !(entityPlayerMP.getDistance((Entity)girl) < 100.0f)) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw PacketResetController.Handler.rethrow(runtimeException);
                        }
                        NetworkHandler.channel.sendTo((IMessage)new PacketResetController(packet.GirlUuid), entityPlayerMP);
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketResetController.Handler.rethrow(runtimeException);
                    }
                }
                return null;
            }
            GirlEntity girl2 = GirlEntity.getClientSideByUuid(packet.GirlUuid);
            try {
                if (girl2 != null) {
                    girl2.resetTickOffset();
                }
            }
            catch (RuntimeException runtimeException) {
                throw PacketResetController.Handler.rethrow(runtimeException);
            }
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
