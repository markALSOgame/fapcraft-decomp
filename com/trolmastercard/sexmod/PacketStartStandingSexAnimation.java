package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketStartStandingSexAnimation implements IMessage {
   boolean Loaded;
   UUID PlayerUuid;
   UUID GirlUuid;
   String AnimationName;

   public PacketStartStandingSexAnimation() {
   }

   public PacketStartStandingSexAnimation(UUID uuid, UUID uuid2, String string) {
      this.PlayerUuid = uuid;
      this.GirlUuid = uuid2;
      this.AnimationName = string;
   }

   public void fromBytes(ByteBuf buf) {
      this.PlayerUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.AnimationName = ByteBufUtils.readUTF8String(buf);
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.PlayerUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.AnimationName);
   }

   public static class Handler implements IMessageHandler<PacketStartStandingSexAnimation, IMessage> {

      public IMessage onMessage(PacketStartStandingSexAnimation packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketStartStandingSexAnimation.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @StartStandingSexAnimation :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketStartStandingSexAnimation.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(packet.PlayerUuid);
                if (playerGirl == null) {
                    return;
                }
                if (!FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer()) {
                    try {
                        for (GirlEntity girl : GirlEntity.getAllGirls()) {
                            if (!(girl instanceof PlayerGirlEntity)) continue;
                            playerGirl = (PlayerGirlEntity)girl;
                            if (playerGirl.world.isRemote || !playerGirl.getBoundPlayerUuid().equals(packet.PlayerUuid)) continue;
                            break;
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                playerGirl.startAction(packet.AnimationName, packet.GirlUuid);
            });
            return null;
        }
 static RuntimeException rethrow(RuntimeException error) {

         return error;

      }


      private static RuntimeException rethrow(Exception error) {
         return new RuntimeException(error);
      }
   }
}
