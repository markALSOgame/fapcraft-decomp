package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketUploadInventoryToServer implements IMessage {
   boolean Loaded = false;
   ItemStack[] Items;
   UUID PlayerUuid;
   UUID GirlUuid;

   public PacketUploadInventoryToServer() {
   }

   public PacketUploadInventoryToServer(UUID uuid, UUID uuid2, ItemStack[] stackArray) {
      this.PlayerUuid = uuid;
      this.Items = stackArray;
      this.GirlUuid = uuid2;
   }

   public void fromBytes(ByteBuf buf) {
      this.PlayerUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      int i = buf.readInt();
      this.Items = new ItemStack[i];
      int i2 = 0;

      try {
         while (i2 < i) {
            this.Items[i2] = ByteBufUtils.readItemStack(buf);
            i2++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.PlayerUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      buf.writeInt(this.Items.length);

      for (ItemStack stack : this.Items) {
         ByteBufUtils.writeItemStack(buf, stack);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketUploadInventoryToServer, IMessage> {

      public IMessage onMessage(PacketUploadInventoryToServer packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketUploadInventoryToServer.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @UploadInventoryToServer :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketUploadInventoryToServer.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                ArrayList<GirlEntity> arrayList = GirlEntity.getGirlsByOwner(packet.PlayerUuid);
                for (GirlEntity girl : arrayList) {
                    try {
                        if (girl.world.isRemote) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketUploadInventoryToServer.Handler.rethrow(runtimeException);
                    }
                    EntityPlayer entityPlayer = girl.world.getPlayerEntityByUUID(packet.GirlUuid);
                    try {
                        if (entityPlayer == null) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketUploadInventoryToServer.Handler.rethrow(runtimeException);
                    }
                    InventoryPlayer inventoryPlayer = entityPlayer.inventory;
                    try {
                        for (int i = 0; i < 36; ++i) {
                            inventoryPlayer.setInventorySlotContents(i, packet.Items[i]);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketUploadInventoryToServer.Handler.rethrow(runtimeException);
                    }
                    if (girl instanceof LunaNpc) {
                        InventoryGirlEntity inventoryGirl = (InventoryGirlEntity)girl;
                        inventoryGirl.Inventory.setStackInSlot(0, packet.Items[36]);
                        inventoryGirl.Inventory.setStackInSlot(1, packet.Items[37]);
                        inventoryGirl.Inventory.setStackInSlot(2, packet.Items[38]);
                        inventoryGirl.Inventory.setStackInSlot(3, packet.Items[39]);
                        inventoryGirl.Inventory.setStackInSlot(4, packet.Items[40]);
                        inventoryGirl.Inventory.setStackInSlot(5, packet.Items[41]);
                        inventoryGirl.Inventory.setStackInSlot(6, packet.Items[42]);
                    } else if (girl instanceof InventoryGirlEntity) {
                        InventoryGirlEntity inventoryGirl2 = (InventoryGirlEntity)girl;
                        inventoryGirl2.Inventory.setStackInSlot(0, packet.Items[36]);
                        inventoryGirl2.Inventory.setStackInSlot(1, packet.Items[37]);
                        inventoryGirl2.Inventory.setStackInSlot(2, packet.Items[38]);
                        inventoryGirl2.Inventory.setStackInSlot(3, packet.Items[39]);
                        inventoryGirl2.Inventory.setStackInSlot(4, packet.Items[40]);
                        inventoryGirl2.Inventory.setStackInSlot(5, packet.Items[41]);
                    }
                    if (!(girl instanceof ChestGirlEntity)) continue;
                    ChestGirlEntity chestGirl = (ChestGirlEntity)girl;
                    try {
                        for (int i4 = 0; i4 < 27; ++i4) {
                            chestGirl.Inventory.setStackInSlot(i4, packet.Items[i4 + 36]);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketUploadInventoryToServer.Handler.rethrow(runtimeException);
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
