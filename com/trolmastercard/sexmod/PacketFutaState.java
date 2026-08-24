package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFutaState implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   NBTTagCompound Data;

   public PacketFutaState() {
   }

   public PacketFutaState(UUID uuid, NBTTagCompound tagCompound) {
      this.GirlUuid = uuid;
      this.Data = tagCompound;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Data = ByteBufUtils.readTag(buf);
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeTag(buf, this.Data);
   }

   public static class Handler implements IMessageHandler<PacketFutaState, IMessage> {
      public IMessage handle(PacketFutaState packet, MessageContext ctx) {
         try {
            if (!packet.Loaded) {
               System.out.println("received an invalid message @UpdateEquipment :(");
               return null;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
            for (GirlEntity girl : GirlEntity.getGirlsByOwner(packet.GirlUuid)) {
               try {
                  if (girl instanceof InventoryGirlEntity) {
                     ((InventoryGirlEntity)girl).Q.deserializeNBT(packet.Data);
                  }
               } catch (RuntimeException error2) {
                  throw rethrow(error2);
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
