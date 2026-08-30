package com.trolmastercard.sexmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public abstract class ChestGirlEntity extends GirlEntity implements IInventory {
   public static final DataParameter<Boolean> K = EntityDataManager.createKey(GirlEntity.class, DataSerializers.BOOLEAN).getSerializer().createKey(111);
   public ItemStackHandler Inventory = new ItemStackHandler(27);

   protected ChestGirlEntity(World world) {
      super(world);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.DataManager.register(K, false);
   }

   public int getSizeInventory() {
      return 27;
   }

   public boolean isEmpty() {
      return false;
   }

   public ItemStack getStackInSlot(int i) {
      try {
         if (i >= this.Inventory.getSlots()) {
            return ItemStack.EMPTY;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return this.Inventory.getStackInSlot(i);
   }

   public ItemStack decrStackSize(int i, int i2) {
      return this.Inventory.extractItem(i, i2, false);
   }

   public ItemStack removeStackFromSlot(int i) {
      return this.Inventory.extractItem(i, this.Inventory.getStackInSlot(i).getCount(), false);
   }

   public void setInventorySlotContents(int i, ItemStack stack) {
      this.Inventory.setStackInSlot(i, stack);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void markDirty() {
   }

   public boolean isUsableByPlayer(EntityPlayer player) {
      return true;
   }

   public void openInventory(EntityPlayer player) {
   }

   public void closeInventory(EntityPlayer player) {
   }

   public boolean isItemValidForSlot(int i, ItemStack stack) {
      return true;
   }

   public int getField(int i) {
      return i;
   }

   public void setField(int i, int i2) {
   }

   public int getFieldCount() {
      return 0;
   }

   public void clear() {
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
