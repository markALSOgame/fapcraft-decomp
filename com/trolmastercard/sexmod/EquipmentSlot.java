package com.trolmastercard.sexmod;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class EquipmentSlot extends SlotItemHandler {
   EquipmentSlot.SlotType Kind;

   public EquipmentSlot(EquipmentSlot.SlotType slotType, IItemHandler handler, int i, int i2, int i3) {
      super(handler, i, i2, i3);
      this.Kind = slotType;
   }

   public static boolean a(ItemStack stack, int i) {
      return a(stack, EquipmentSlot.SlotType.getBySlotIndex(i));
   }

   public boolean isItemValid(ItemStack stack) {
      return a(stack, this.Kind);
   }


   static boolean a(ItemStack stack, EquipmentSlot.SlotType slotType) {
      Item item = stack.getItem();
      switch (slotType) {
         case WEAPON:
            return item instanceof ItemSword || item instanceof ItemTool;
         case BOW:
            return item instanceof ItemBow;
         case HELMET:
            return item instanceof ItemArmor && ((ItemArmor)item).armorType == EntityEquipmentSlot.HEAD;
         case CHEST_PLATE:
            return item instanceof ItemArmor && ((ItemArmor)item).armorType == EntityEquipmentSlot.CHEST;
         case PANTS:
            return item instanceof ItemArmor && ((ItemArmor)item).armorType == EntityEquipmentSlot.LEGS;
         case SHOES:
            return item instanceof ItemArmor && ((ItemArmor)item).armorType == EntityEquipmentSlot.FEET;
         case ROD:
            return item instanceof ItemFishingRod;
      }

      return false;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public enum SlotType {
      WEAPON(0),
      BOW(1),
      HELMET(2),
      CHEST_PLATE(3),
      PANTS(4),
      SHOES(5),
      ROD(6);

      public int SlotIndex;

      public static EquipmentSlot.SlotType getBySlotIndex(int i) {
         try {
            switch (i) {
               case 0:
                  return WEAPON;
               case 1:
                  return BOW;
               case 2:
                  return HELMET;
               case 3:
                  return CHEST_PLATE;
               case 4:
                  return PANTS;
               case 5:
                  return SHOES;
               case 6:
                  return ROD;
            }
         } catch (NullPointerException error) {
            throw rethrow(error);
         }

         throw new NullPointerException("Girls don't have a slot nr. " + i);
      }

      SlotType(int i) {
         this.SlotIndex = i;
      }

      private static NullPointerException rethrow(NullPointerException error) {
         return error;
      }
   }
}
