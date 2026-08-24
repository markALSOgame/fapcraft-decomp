package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBeeOpenChest implements IMessage {
   boolean Loaded = false;
   UUID GirlUuid;
   UUID PlayerUuid;

   public PacketBeeOpenChest() {
   }

   public PacketBeeOpenChest(UUID uuid, UUID uuid2) {
      this.GirlUuid = uuid;
      this.PlayerUuid = uuid2;
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

   public static class Handler implements IMessageHandler<PacketBeeOpenChest, IMessage> {
      public IMessage handle(PacketBeeOpenChest packet, MessageContext ctx) {
         try {
            if (!packet.Loaded) {
               System.out.println("received an invalid message @BeeOpenChest :(");
               return null;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         FMLCommonHandler.instance()
            .getMinecraftServerInstance()
            .addScheduledTask(
               () -> {
                  for (GirlEntity girl : GirlEntity.getGirlsByOwner(packet.GirlUuid)) {
                     try {
                        if (girl.world.isRemote) {
                           continue;
                        }
                     } catch (RuntimeException error2) {
                        throw rethrow(error2);
                     }

                     try {
                        if (!(girl instanceof BeeNpc)) {
                           continue;
                        }
                     } catch (RuntimeException error3) {
                        throw rethrow(error3);
                     }

                     BeeNpc beeNpc = (BeeNpc)girl;

                     try {
                        if (!(Boolean)beeNpc.getDataManager().get(BeeNpc.IsTamedKey)) {
                           continue;
                        }
                     } catch (RuntimeException error4) {
                        throw rethrow(error4);
                     }

                     EntityPlayerMP serverPlayer = (EntityPlayerMP)beeNpc.world.getPlayerEntityByUUID(packet.PlayerUuid);

                     try {
                        if (serverPlayer == null) {
                           continue;
                        }
                     } catch (RuntimeException error5) {
                        throw rethrow(error5);
                     }

                     serverPlayer.openGui(
                        Main.instance,
                        1,
                        girl.world,
                        girl.getPosition().getX(),
                        girl.getPosition().getY(),
                        girl.getPosition().getZ()
                     );
                     return;
                  }
               }
            );
         return null;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
