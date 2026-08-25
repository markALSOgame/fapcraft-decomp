package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketCatActivateFishing implements IMessage {
   boolean Loaded = false;
   UUID GirlUuid;

   public PacketCatActivateFishing() {
   }

   public PacketCatActivateFishing(UUID uuid) {
      this.GirlUuid = uuid;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketCatActivateFishing, IMessage> {

      public IMessage onMessage(PacketCatActivateFishing packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketCatActivateFishing.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @CatActivateFishing :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketCatActivateFishing.Handler.rethrow(runtimeException);
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
                        throw PacketCatActivateFishing.Handler.rethrow(runtimeException);
                    }
                    try {
                        if (!(girl instanceof LunaNpc)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketCatActivateFishing.Handler.rethrow(runtimeException);
                    }
                    LunaNpc luna = (LunaNpc)girl;
                    ItemStack itemStack = luna.HeldRodStack;
                    ItemLunaRod item = (ItemLunaRod)itemStack.getItem();
                    item.a(ctx.getServerHandler().player.world, luna, EnumHand.MAIN_HAND);
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
