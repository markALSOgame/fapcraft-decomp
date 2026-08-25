package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;

public class GirlDamageHandler {
   public GirlDamageHandler() {
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.HEAD, ArmorMaterial.LEATHER, 1, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.HEAD, ArmorMaterial.GOLD, 2, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.HEAD, ArmorMaterial.CHAIN, 2, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.HEAD, ArmorMaterial.IRON, 2, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.HEAD, ArmorMaterial.DIAMOND, 3, 3);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.CHEST, ArmorMaterial.LEATHER, 3, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.CHEST, ArmorMaterial.GOLD, 5, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.CHEST, ArmorMaterial.CHAIN, 5, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.CHEST, ArmorMaterial.IRON, 6, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.CHEST, ArmorMaterial.DIAMOND, 8, 3);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.LEGS, ArmorMaterial.LEATHER, 2, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.LEGS, ArmorMaterial.GOLD, 3, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.LEGS, ArmorMaterial.CHAIN, 4, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.LEGS, ArmorMaterial.IRON, 5, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.LEGS, ArmorMaterial.DIAMOND, 6, 3);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.FEET, ArmorMaterial.LEATHER, 1, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.FEET, ArmorMaterial.GOLD, 1, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.FEET, ArmorMaterial.CHAIN, 1, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.FEET, ArmorMaterial.IRON, 2, 0);
      GirlDamageHandler.ArmorReductionTable.registerReduction(EntityEquipmentSlot.FEET, ArmorMaterial.DIAMOND, 3, 3);
   }

   @SubscribeEvent
   public void onLivingDamage(LivingDamageEvent livingDamageEvent) {
      try {
         if (!(livingDamageEvent.getEntity() instanceof InventoryGirlEntity)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      InventoryGirlEntity inventoryGirl = (InventoryGirlEntity)livingDamageEvent.getEntity();
      ItemStack[] stackArray = new ItemStack[]{inventoryGirl.Inventory.getStackInSlot(2), inventoryGirl.Inventory.getStackInSlot(3), inventoryGirl.Inventory.getStackInSlot(4), inventoryGirl.Inventory.getStackInSlot(5)};
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();

      for (ItemStack stack : stackArray) {
         try {
            if (stack.getItem() instanceof ItemArmor) {
               list.add((ItemArmor)stack.getItem());
               list2.add(stack);
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }
      }

      try {
         if (list.size() == 0) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      DamageSource damage = livingDamageEvent.getSource();
      int i = 0;
      int i2 = 0;
      if (!damage.isUnblockable()) {
         for (ItemArmor armor : list) {
            i += GirlDamageHandler.ArmorReductionTable.getReduction(armor.armorType, armor.getArmorMaterial());
            i2 += GirlDamageHandler.ArmorReductionTable.getToughness(armor.armorType, armor.getArmorMaterial());
         }
      }

      float f = livingDamageEvent.getAmount();
      f *= 1.0F - Math.min(20.0F, Math.max(i / 5.0F, i - 4.0F * f / (i2 + 8.0F))) / 25.0F;
      float f2 = 0.0F;

      for (ItemStack stack2 : list2) {
         int i3 = EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack2);
         f -= i3 * 0.04F * f;
         int i4 = EnchantmentHelper.getEnchantmentLevel(Enchantments.THORNS, stack2);

         float f3;
         float f4;
         label82: {
            try {
               f3 = f2;
               if (ModConstants.Random.nextFloat() < 0.15F * i4) {
                  f4 = ModConstants.Random.nextFloat() * 4.0F + 1.0F;
                  break label82;
               }
            } catch (RuntimeException error4) {
               throw rethrow(error4);
            }

            f4 = 0.0F;
         }

         f2 = f3 + f4;
         f2 = Math.min(4.0F, f2);
         if (damage.isFireDamage()) {
            int i5 = EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_PROTECTION, stack2);
            f -= i5 * 0.08F * f;
         }

         if (damage.isExplosion()) {
            int i6 = EnchantmentHelper.getEnchantmentLevel(Enchantments.BLAST_PROTECTION, stack2);
            f -= i6 * 0.08F * f;
         }

         if (damage.damageType.equals("fall")) {
            int i7 = EnchantmentHelper.getEnchantmentLevel(Enchantments.FEATHER_FALLING, stack2);
            f -= i7 * 0.12F * f;
         }

         if (damage.isProjectile()) {
            int i8 = EnchantmentHelper.getEnchantmentLevel(Enchantments.PROJECTILE_PROTECTION, stack2);
            f -= i8 * 0.08F * f;
         }
      }

      label116: {
         try {
            if (!(f2 > 0.0F) || !(damage instanceof EntityDamageSource)) {
               break label116;
            }
         } catch (RuntimeException error5) {
            throw rethrow(error5);
         }

         EntityDamageSource entityDamageSource = (EntityDamageSource)damage;

         try {
            if (entityDamageSource.getTrueSource() != null) {
               entityDamageSource.getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(inventoryGirl), f2);
            }
         } catch (RuntimeException error6) {
            throw rethrow(error6);
         }
      }

      livingDamageEvent.setAmount(f);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   static class ArmorReductionTable {
      public static HashMap<String, Integer[]> ReductionTable = new HashMap<>();

      public static int getReduction(EntityEquipmentSlot entityEquipmentSlot, ArmorMaterial armorMaterial) {
         try {
            return ReductionTable.get(entityEquipmentSlot.toString() + armorMaterial.toString())[0];
         } catch (NullPointerException error) {
            return 3;
         }
      }

      public static int getToughness(EntityEquipmentSlot entityEquipmentSlot, ArmorMaterial armorMaterial) {
         try {
            return ReductionTable.get(entityEquipmentSlot.toString() + armorMaterial.toString())[1];
         } catch (NullPointerException error) {
            return 0;
         }
      }

      public static void registerReduction(EntityEquipmentSlot entityEquipmentSlot, ArmorMaterial armorMaterial, int i, int i2) {
         ReductionTable.put(entityEquipmentSlot.toString() + armorMaterial.toString(), new Integer[]{i, i2});
      }
   }
}
