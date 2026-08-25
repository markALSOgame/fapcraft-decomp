package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketForcePlayerGirlUpdate implements IMessage {
   boolean Loaded = false;
   UUID GirlUuid;
   int Index;
   GirlAnimationState Animation;

   public PacketForcePlayerGirlUpdate() {
   }

   public PacketForcePlayerGirlUpdate(UUID uuid, int i, GirlAnimationState girlAnimationState) {
      this.GirlUuid = uuid;
      this.Index = i;
      this.Animation = girlAnimationState;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Index = buf.readInt();
      this.Animation = GirlAnimationState.valueOf(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      buf.writeInt(this.Index);
      ByteBufUtils.writeUTF8String(buf, this.Animation.toString());
   }

   public static class Handler implements IMessageHandler<PacketForcePlayerGirlUpdate, IMessage> {

      public IMessage onMessage(PacketForcePlayerGirlUpdate packet, MessageContext ctx) {
            block7: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.CLIENT)) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketForcePlayerGirlUpdate.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @ForcePlayerGirlUpdate :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketForcePlayerGirlUpdate.Handler.rethrow(runtimeException);
                }
            }
            PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(packet.GirlUuid);
            try {
                if (playerGirl == null) {
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw PacketForcePlayerGirlUpdate.Handler.rethrow(runtimeException);
            }
            playerGirl.getDataManager().set(GirlEntity.CurrentActionKey, packet.Animation.toString());
            playerGirl.getDataManager().set(GirlEntity.OutfitIndexKey, packet.Index);
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
