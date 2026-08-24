package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class GirlNameTagHandler {
   @SubscribeEvent
   public void onEntityInteract(EntityInteractSpecific entityInteractSpecific) {
      Entity entity = entityInteractSpecific.getTarget();

      try {
         if (!(entity instanceof GirlEntity)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      EntityPlayer player = entityInteractSpecific.getEntityPlayer();
      ItemStack stack;
      if (player.getHeldItemMainhand().getItem() == Items.NAME_TAG) {
         stack = player.getHeldItemMainhand();
      } else {
         if (player.getHeldItemOffhand().getItem() != Items.NAME_TAG) {
            return;
         }

         stack = player.getHeldItemOffhand();
      }

      String string = stack.getDisplayName();

      try {
         if ("".equals(string)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         ((GirlEntity)entity).g(string);
         if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      entityInteractSpecific.setCanceled(true);
      entityInteractSpecific.setResult(Result.DENY);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
