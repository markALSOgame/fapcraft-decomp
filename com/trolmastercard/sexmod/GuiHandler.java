package com.trolmastercard.sexmod;

import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {
   File b;
   File c;
   boolean a = false;

   public GuiHandler() {
   }

   public GuiHandler(boolean flag) {
      this.preloadContainers();
   }

   @SideOnly(Side.CLIENT)
   void preloadContainers() {
      int i = 2;
      if (i == 0) {
         try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
               if (girl.world.isRemote || girl.getPosition().getX() != 5 || girl.getPosition().getY() != 7 || girl.getPosition().getZ() != 5) continue;
               if (girl instanceof LunaNpc) {
                  new ContainerLunaEquipment((LunaNpc)girl, Minecraft.getMinecraft().player.inventory, UUID.randomUUID());
               }
               new ContainerGirlEquipment(girl, Minecraft.getMinecraft().player.inventory, UUID.randomUUID());
            }
         }
         catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
         }
      }
      if (i == 1) {
         try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
               if (girl.world.isRemote || !(girl instanceof IInventory) || girl.getPosition().getX() != 3 || girl.getPosition().getY() != 1 || girl.getPosition().getZ() != 7) continue;
               IInventory iInventory = (IInventory)girl;
               new ContainerChest((IInventory)Minecraft.getMinecraft().player.inventory, iInventory, (EntityPlayer)Minecraft.getMinecraft().player, UUID.randomUUID());
            }
         }
         catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
         }
      }
   }

   public Object getServerGuiElement(int i, EntityPlayer player, World world2, int i2, int i3, int i4) {
      if (i == 0) {
         try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
               if (girl.world.isRemote || girl.getPosition().getX() != i2 || girl.getPosition().getY() != i3 || girl.getPosition().getZ() != i4) continue;
               if (girl instanceof LunaNpc) {
                  return new ContainerLunaEquipment((LunaNpc)girl, player.inventory, UUID.randomUUID());
               }
               return new ContainerGirlEquipment(girl, player.inventory, UUID.randomUUID());
            }
         }
         catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
         }
      }
      if (i == 1) {
         try {
            for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
               if (girl2.world.isRemote || !(girl2 instanceof IInventory) || girl2.getPosition().getX() != i2 || girl2.getPosition().getY() != i3 || girl2.getPosition().getZ() != i4) continue;
               IInventory iInventory = (IInventory)girl2;
               return new ContainerChest((IInventory)player.inventory, iInventory, player, UUID.randomUUID());
            }
         }
         catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
         }
      }
      return null;
   }

   public Object getClientGuiElement(int i, EntityPlayer player, World world2, int i2, int i3, int i4) {
      if (i == 0) {
         try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
               if (!girl.world.isRemote || girl.getPosition().getX() != i2 || girl.getPosition().getY() != i3 || girl.getPosition().getZ() != i4) continue;
               if (girl instanceof LunaNpc) {
                  return new GuiLunaEquipment((LunaNpc)girl, player.inventory, UUID.randomUUID());
               }
               return new GuiGirlEquipment(girl, player.inventory, UUID.randomUUID());
            }
         }
         catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
         }
      }
      if (i == 1) {
         try {
            for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
               if (!girl2.world.isRemote || !(girl2 instanceof IInventory) || girl2.getPosition().getX() != i2 || girl2.getPosition().getY() != i3 || girl2.getPosition().getZ() != i4) continue;
               return new GuiChest(player, girl2, UUID.randomUUID());
            }
         }
         catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
         }
      }
      return null;
   }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
