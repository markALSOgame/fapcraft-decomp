package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.ArrayList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSpawnParticle implements IMessage {
   boolean Loaded = false;
   UUID GirlUuid;
   String Name;
   int Count;

   public PacketSpawnParticle() {
   }

   public PacketSpawnParticle(UUID uuid, String string) {
      this.GirlUuid = uuid;
      this.Name = string;
      this.Count = 1;
   }

   public PacketSpawnParticle(UUID uuid, String string, int i) {
      this.GirlUuid = uuid;
      this.Name = string;
      this.Count = i;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Name = ByteBufUtils.readUTF8String(buf);
      this.Count = buf.readInt();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.Name);
      buf.writeInt(this.Count);
   }

   public static class Handler implements IMessageHandler<PacketSpawnParticle, IMessage> {

      public IMessage onMessage(PacketSpawnParticle packet, MessageContext ctx) {
            block11: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.CLIENT)) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSpawnParticle.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @SpawnParticle :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSpawnParticle.Handler.rethrow(runtimeException);
                }
            }
            ArrayList<GirlEntity> arrayList = GirlEntity.getGirlsByOwner(packet.GirlUuid);
            for (GirlEntity girl : arrayList) {
                try {
                    if (!girl.world.isRemote) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSpawnParticle.Handler.rethrow(runtimeException);
                }
                try {
                    for (int i = 0; i < packet.Count; ++i) {
                        GirlEntity.spawnParticles(EnumParticleTypes.getByName((String)packet.Name), girl);
                    }
                    break;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSpawnParticle.Handler.rethrow(runtimeException);
                }
            }
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
