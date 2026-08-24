package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerGirlEquipment extends Container {
   GirlEntity Girl;
   public Slot[] Slots;
   public UUID GirlUuid;
   public static List<ContainerGirlEquipment> OpenContainers = new ArrayList<>();

   public ContainerGirlEquipment(GirlEntity girl, InventoryPlayer inventory, UUID uuid) {
      this.GirlUuid = uuid;
      OpenContainers.add(this);
      if (girl.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
         IItemHandler handler = (IItemHandler)girl.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
         this.Girl = girl;
         this.Slots = new Slot[]{
            new EquipmentSlot(EquipmentSlot.SlotType.WEAPON, handler, EquipmentSlot.SlotType.WEAPON.SlotIndex, 31, 60),
            new EquipmentSlot(EquipmentSlot.SlotType.BOW, handler, EquipmentSlot.SlotType.BOW.SlotIndex, 50, 60),
            new EquipmentSlot(EquipmentSlot.SlotType.HELMET, handler, EquipmentSlot.SlotType.HELMET.SlotIndex, 72, 60),
            new EquipmentSlot(EquipmentSlot.SlotType.CHEST_PLATE, handler, EquipmentSlot.SlotType.CHEST_PLATE.SlotIndex, 91, 60),
            new EquipmentSlot(EquipmentSlot.SlotType.PANTS, handler, EquipmentSlot.SlotType.PANTS.SlotIndex, 110, 60),
            new EquipmentSlot(EquipmentSlot.SlotType.SHOES, handler, EquipmentSlot.SlotType.SHOES.SlotIndex, 129, 60)
         };
         ArrayList list = new ArrayList();

         for (int i = 0; i < 3; i++) {
            int i2 = 0;

            try {
               while (i2 < 9) {
                  list.add(new Slot(inventory, i2 + i * 9 + 9, 8 + i2 * 18, 84 + i * 18));
                  i2++;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }
         }

         int i3 = 0;

         try {
            while (i3 < 9) {
               list.add(new Slot(inventory, i3, 8 + i3 * 18, 142));
               i3++;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         for (Slot slot : this.Slots) {
            this.addSlotToContainer(slot);
         }

         for (Slot slot2 : list) {
            this.addSlotToContainer(slot2);
         }
      }
   }


   public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        ItemStack itemStack;
        block11: {
            ItemStack itemStack2;
            Slot slot;
            block15: {
                block14: {
                    block13: {
                        int i2;
                        block12: {
                            itemStack = ItemStack.EMPTY;
                            slot = (Slot)this.inventorySlots.get(i);
                            try {
                                if (slot == null || !slot.getHasStack()) break block11;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ContainerGirlEquipment.rethrow(runtimeException);
                            }
                            itemStack2 = slot.getStack();
                            itemStack = itemStack2.copy();
                            i2 = this.inventorySlots.size() - player.inventory.mainInventory.size();
                            try {
                                try {
                                    if (i >= i2) break block12;
                                    if (this.mergeItemStack(itemStack2, i2, this.inventorySlots.size(), true)) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ContainerGirlEquipment.rethrow(runtimeException);
                                }
                                return ItemStack.EMPTY;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ContainerGirlEquipment.rethrow(runtimeException);
                            }
                        }
                        try {
                            if (!this.mergeItemStack(itemStack2, 0, i2, false)) {
                                return ItemStack.EMPTY;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ContainerGirlEquipment.rethrow(runtimeException);
                        }
                    }
                    try {
                        if (itemStack2.getCount() != 0) break block14;
                        slot.putStack(ItemStack.EMPTY);
                        break block15;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ContainerGirlEquipment.rethrow(runtimeException);
                    }
                }
                slot.onSlotChanged();
            }
            slot.onTake(player, itemStack2);
        }
        return itemStack;
    }

   public void putStackInSlot(int i, ItemStack stack) {
      super.putStackInSlot(i, stack);
   }

   public boolean canInteractWith(EntityPlayer player) {
      return true;
   }

   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
