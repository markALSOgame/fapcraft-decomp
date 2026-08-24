package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCatEatingDone implements IMessage {
   boolean Loaded = false;
   UUID GirlUuid;

   public PacketCatEatingDone() {
   }

   public PacketCatEatingDone(UUID uuid) {
      this.GirlUuid = uuid;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketCatEatingDone, IMessage> {

      public IMessage handle(PacketCatEatingDone packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketCatEatingDone.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @CatEatingDone :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketCatEatingDone.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                ArrayList<GirlEntity> arrayList = GirlEntity.getGirlsByOwner(packet.GirlUuid);
                for (GirlEntity girl : arrayList) {
                    try {
                        if (girl.world.isRemote) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketCatEatingDone.Handler.rethrow(runtimeException);
                    }
                    try {
                        if (!(girl instanceof LunaNpc)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketCatEatingDone.Handler.rethrow(runtimeException);
                    }
                    LunaNpc luna = (LunaNpc)girl;
                    luna.isTracked();
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
