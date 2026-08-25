package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSpawnEnergyBallParticles implements IMessage {
   Vec3d Pos;
   boolean Dark;
   boolean Loaded = false;

   public PacketSpawnEnergyBallParticles() {
   }

   public PacketSpawnEnergyBallParticles(Vec3d vec3d, boolean flag) {
      this.Pos = vec3d;
      this.Dark = flag;
   }

   public void fromBytes(ByteBuf buf) {
      this.Pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      this.Dark = buf.readBoolean();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeDouble(this.Pos.x);
      buf.writeDouble(this.Pos.y);
      buf.writeDouble(this.Pos.z);
      buf.writeBoolean(this.Dark);
   }

   public static class Handler implements IMessageHandler<PacketSpawnEnergyBallParticles, IMessage> {

      public IMessage onMessage(PacketSpawnEnergyBallParticles packet, MessageContext ctx) {
            block8: {
                block7: {
                    block6: {
                        try {
                            try {
                                if (packet.Loaded && ctx.side.equals((Object)Side.CLIENT)) break block6;
                            }
                            catch (RuntimeException runtimeException) {
                                throw PacketSpawnEnergyBallParticles.Handler.rethrow(runtimeException);
                            }
                            System.out.println("received an invalid message @SpawnEnergyBallParticles :(");
                            return null;
                        }
                        catch (RuntimeException runtimeException) {
                            throw PacketSpawnEnergyBallParticles.Handler.rethrow(runtimeException);
                        }
                    }
                    try {
                        if (!packet.Dark) break block7;
                        EnergyBallEntity.spawnSmokeRingParticles(packet.Pos);
                        break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSpawnEnergyBallParticles.Handler.rethrow(runtimeException);
                    }
                }
                EnergyBallEntity.spawnDragonBreathParticles(packet.Pos);
            }
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
