package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.EnumSet;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketTeleportPlayer implements IMessage {
   boolean Loaded;
   String PlayerName;
   Vec3d Pos;
   float Yaw;
   float Pitch;

   public PacketTeleportPlayer() {
      this.Loaded = false;
   }

   public PacketTeleportPlayer(String string, Vec3d vec3d) {
      this.PlayerName = string;
      this.Pos = vec3d;
      this.Yaw = 0.0F;
      this.Pitch = 0.0F;
      this.Loaded = true;
   }

   public PacketTeleportPlayer(String string, Vec3d vec3d, float f, float f2) {
      this.PlayerName = string;
      this.Pos = vec3d;
      this.Yaw = f;
      this.Pitch = f2;
      this.Loaded = true;
   }

   public PacketTeleportPlayer(String string, double d, double d2, double d3, float f, float f2) {
      this.PlayerName = string;
      this.Pos = new Vec3d(d, d2, d3);
      this.Yaw = f;
      this.Pitch = f2;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.PlayerName = ByteBufUtils.readUTF8String(buf);
      this.Pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      this.Yaw = buf.readFloat();
      this.Pitch = buf.readFloat();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.PlayerName);
      buf.writeDouble(this.Pos.x);
      buf.writeDouble(this.Pos.y);
      buf.writeDouble(this.Pos.z);
      buf.writeFloat(this.Yaw);
      buf.writeFloat(this.Pitch);
      this.Loaded = true;
   }

   public static class Handler implements IMessageHandler<PacketTeleportPlayer, IMessage> {

      public IMessage onMessage(PacketTeleportPlayer packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketTeleportPlayer.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @TeleportPlayer :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketTeleportPlayer.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                try {
                    System.out.println("teleporting player " + packet.PlayerName + " to " + packet.Pos);
                    EntityPlayerMP entityPlayerMP = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(UUID.fromString(packet.PlayerName));
                    packet.Yaw = MathHelper.wrapDegrees((float)packet.Yaw);
                    packet.Pitch = MathHelper.wrapDegrees((float)packet.Pitch);
                    entityPlayerMP.setLocationAndAngles(packet.Pos.x, packet.Pos.y, packet.Pos.z, packet.Yaw, packet.Pitch);
                    entityPlayerMP.setRotationYawHead(packet.Yaw);
                    entityPlayerMP.motionX = 0.0;
                    entityPlayerMP.motionY = 0.0;
                    entityPlayerMP.motionZ = 0.0;
                    entityPlayerMP.connection.setPlayerLocation(packet.Pos.x, packet.Pos.y, packet.Pos.z, packet.Yaw, packet.Pitch, EnumSet.noneOf(SPacketPlayerPosLook.EnumFlags.class));
                }
                catch (Exception exception) {
                    System.out.println("couldn't find player with UUID: " + packet.PlayerName);
                    System.out.println("could only find the following players:");
                    System.out.println(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getFormattedListOfPlayers(true));
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
