package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetPlayerCam implements IMessage {
   boolean Loaded = false;
   float Yaw;
   float Pitch;
   int Mode;

   public PacketSetPlayerCam() {
   }

   public PacketSetPlayerCam(float f, float f2, int i) {
      this.Yaw = f;
      this.Pitch = f2;
      this.Mode = i;
   }

   public void fromBytes(ByteBuf buf) {
      this.Yaw = buf.readFloat();
      this.Pitch = buf.readFloat();
      this.Mode = buf.readInt();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeFloat(this.Yaw);
      buf.writeFloat(this.Pitch);
      buf.writeInt(this.Mode);
   }

   public static class Handler implements IMessageHandler<PacketSetPlayerCam, IMessage> {

      public IMessage handle(PacketSetPlayerCam packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.CLIENT) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSetPlayerCam.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @SetPlayerCam :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSetPlayerCam.Handler.rethrow(runtimeException);
                }
            }
            System.out.println(Thread.currentThread().getName());
            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> {
                minecraft.gameSettings.thirdPersonView = packet.Mode;
                EntityPlayerSP entityPlayerSP = minecraft.player;
                entityPlayerSP.rotationYaw = packet.Pitch;
                entityPlayerSP.prevRotationYaw = packet.Pitch;
                entityPlayerSP.prevRotationYawHead = packet.Pitch;
                entityPlayerSP.rotationYawHead = packet.Pitch;
                entityPlayerSP.renderYawOffset = packet.Pitch;
                entityPlayerSP.rotationPitch = packet.Yaw;
                entityPlayerSP.prevRotationPitch = packet.Yaw;
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
