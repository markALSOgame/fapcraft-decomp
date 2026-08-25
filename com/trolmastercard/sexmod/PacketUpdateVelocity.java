package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketUpdateVelocity implements IMessage {
   boolean Loaded = false;
   Vec3d Velocity;
   UUID GirlUuid;

   public PacketUpdateVelocity(Vec3d vec3d, UUID uuid) {
      this.Velocity = vec3d;
      this.GirlUuid = uuid;
   }

   public PacketUpdateVelocity() {
   }

   public void fromBytes(ByteBuf buf) {
      this.Velocity = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeDouble(this.Velocity.x);
      buf.writeDouble(this.Velocity.y);
      buf.writeDouble(this.Velocity.z);
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketUpdateVelocity, IMessage> {

      public IMessage onMessage(PacketUpdateVelocity packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketUpdateVelocity.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @UpdateVelocity :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketUpdateVelocity.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                GirlEntity girl = GirlEntity.getServerSideByUuid(packet.GirlUuid);
                try {
                    if (!(girl instanceof GalathNpc)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketUpdateVelocity.Handler.rethrow(runtimeException);
                }
                GalathNpc f_2 = (GalathNpc)girl;
                try {
                    if (ctx.getServerHandler().player.equals((Object)f_2.getDisplayName())) {
                        f_2.addVelocity(packet.Velocity);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketUpdateVelocity.Handler.rethrow(runtimeException);
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
