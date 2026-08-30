package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketOpenEquipment implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   UUID PlayerUuid;

   public PacketOpenEquipment() {
   }

   public PacketOpenEquipment(UUID uuid, UUID uuid2) {
      this.GirlUuid = uuid;
      this.PlayerUuid = uuid2;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.PlayerUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.PlayerUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketOpenEquipment, IMessage> {

      public IMessage onMessage(PacketOpenEquipment packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketOpenEquipment.Handler.rethrow(runtimeException);
                    }
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketOpenEquipment.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                for (GirlEntity girl : GirlEntity.getAllGirls()) {
                    try {
                        try {
                            if (girl.world.isRemote || !girl.getGirlUuid().equals(packet.GirlUuid)) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw PacketOpenEquipment.Handler.rethrow(runtimeException);
                        }
                        ((EntityPlayerMP)girl.world.getPlayerEntityByUUID(packet.PlayerUuid)).openGui((Object)Main.instance, 0, girl.world, girl.getPosition().getX(), girl.getPosition().getY(), girl.getPosition().getZ());
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketOpenEquipment.Handler.rethrow(runtimeException);
                    }
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
