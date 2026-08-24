package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRemoveItems implements IMessage {
   boolean Loaded = false;
   UUID GirlUuid;
   ItemStack Stack;

   public PacketRemoveItems() {
   }

   public PacketRemoveItems(UUID uuid, ItemStack stack) {
      this.GirlUuid = uuid;
      this.Stack = stack;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Stack = ByteBufUtils.readItemStack(buf);
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeItemStack(buf, this.Stack);
   }

   public static class Handler implements IMessageHandler<PacketRemoveItems, IMessage> {

      public IMessage handle(PacketRemoveItems packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketRemoveItems.Handler.rethrow(runtimeException);
                    }
                    System.out.println("recieved an unvalid message @RemoveItems :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketRemoveItems.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                InventoryPlayer inventoryPlayer = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID((UUID)packet.GirlUuid).inventory;
                for (int i = 0; i < inventoryPlayer.getSizeInventory(); ++i) {
                    ItemStack itemStack = inventoryPlayer.getStackInSlot(i);
                    try {
                        if (!itemStack.getItem().equals(packet.Stack.getItem())) continue;
                        itemStack.shrink(packet.Stack.getCount());
                        break;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketRemoveItems.Handler.rethrow(runtimeException);
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
