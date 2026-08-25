package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSpawnEnergyBallParticle implements IMessage {
   boolean Loaded = false;
   UUID ShooterUuid;
   UUID TargetUuid;

   public PacketSpawnEnergyBallParticle() {
   }

   public PacketSpawnEnergyBallParticle(UUID uuid, UUID uuid2) {
      this.ShooterUuid = uuid;
      this.TargetUuid = uuid2;
   }

   public void fromBytes(ByteBuf buf) {
      try {
         this.ShooterUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      } catch (Exception error) {
         this.ShooterUuid = null;
      }

      try {
         this.TargetUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      } catch (Exception error2) {
         this.TargetUuid = null;
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBuf buf2;
      String string;
      label28: {
         try {
            buf2 = buf;
            if (this.ShooterUuid == null) {
               string = "trol was here";
               break label28;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         string = this.ShooterUuid.toString();
      }

      label21: {
         try {
            ByteBufUtils.writeUTF8String(buf2, string);
            buf2 = buf;
            if (this.TargetUuid == null) {
               string = "trol was here";
               break label21;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         string = this.TargetUuid.toString();
      }

      ByteBufUtils.writeUTF8String(buf2, string);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketSpawnEnergyBallParticle, IMessage> {

      public IMessage onMessage(PacketSpawnEnergyBallParticle packet, MessageContext ctx) {
            block7: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.CLIENT)) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSpawnEnergyBallParticle.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @SpawnEnergyBallParticles :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSpawnEnergyBallParticle.Handler.rethrow(runtimeException);
                }
            }
            GirlEntity girl = GirlEntity.getClientSideByUuid(packet.ShooterUuid);
            try {
                if (!(girl instanceof GalathNpc)) {
                    System.out.println("doesnt exit");
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw PacketSpawnEnergyBallParticle.Handler.rethrow(runtimeException);
            }
            ItemGalathCoin.spawnDesummonParticles(packet.TargetUuid, (GalathNpc)girl);
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
