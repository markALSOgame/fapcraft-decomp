package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChest extends Container {
   private final IInventory Inventory;
   private final int SlotCount;
   public static List<ContainerChest> OpenContainers = new ArrayList<>();
   public UUID GirlUuid;

   public ContainerChest(IInventory iInventory, IInventory iInventory2, EntityPlayer player, UUID uuid) {
      this.GirlUuid = uuid;
      OpenContainers.add(this);
      this.Inventory = iInventory2;
      iInventory2.openInventory(player);
      this.SlotCount = 3;
      byte bv = -18;

      for (int i = 0; i < 3; i++) {
         int i2 = 0;

         try {
            while (i2 < 9) {
               this.addSlotToContainer(new Slot(iInventory2, i2 + i * 9, 8 + i2 * 18, 18 + i * 18));
               i2++;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }

      for (int i3 = 0; i3 < 3; i3++) {
         int i4 = 0;

         try {
            while (i4 < 9) {
               this.addSlotToContainer(new Slot(iInventory, i4 + i3 * 9 + 9, 8 + i4 * 18, 103 + i3 * 18 + bv));
               i4++;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }
      }

      int i5 = 0;

      try {
         while (i5 < 9) {
            this.addSlotToContainer(new Slot(iInventory, i5, 8 + i5 * 18, 161 + bv));
            i5++;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.Inventory.isUsableByPlayer(player);
   }


   public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        ItemStack itemStack;
        block11: {
            Slot slot;
            block14: {
                ItemStack itemStack2;
                block13: {
                    block12: {
                        itemStack = ItemStack.EMPTY;
                        slot = (Slot)this.inventorySlots.get(i);
                        try {
                            if (slot == null || !slot.getHasStack()) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ContainerChest.rethrow(runtimeException);
                        }
                        itemStack2 = slot.getStack();
                        itemStack = itemStack2.copy();
                        try {
                            try {
                                if (i >= this.SlotCount * 9) break block12;
                                if (this.mergeItemStack(itemStack2, this.SlotCount * 9, this.inventorySlots.size(), true)) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ContainerChest.rethrow(runtimeException);
                            }
                            return ItemStack.EMPTY;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ContainerChest.rethrow(runtimeException);
                        }
                    }
                    try {
                        if (!this.mergeItemStack(itemStack2, 0, this.SlotCount * 9, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ContainerChest.rethrow(runtimeException);
                    }
                }
                try {
                    if (!itemStack2.isEmpty()) break block14;
                    slot.putStack(ItemStack.EMPTY);
                    break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw ContainerChest.rethrow(runtimeException);
                }
            }
            slot.onSlotChanged();
        }
        return itemStack;
    }

   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      this.Inventory.closeInventory(player);
   }

   public IInventory a() {
      return this.Inventory;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
