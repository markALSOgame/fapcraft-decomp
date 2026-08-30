package com.trolmastercard.sexmod;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FireSpreadHandler {
   static final int Radius = 3;

   @SubscribeEvent
   public void onBreakEvent(BreakEvent breakEvent) {
      Block block = breakEvent.getState().getBlock();

      try {
         if (block != Blocks.BED) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      BlockPos pos = breakEvent.getPos();
      AxisAlignedBB bbox = new AxisAlignedBB(
         pos.getX() - 3,
         pos.getY() - 3,
         pos.getZ() - 3,
         pos.getX() + 3,
         pos.getY() + 3,
         pos.getZ() + 3
      );
      List<GirlEntity> list = breakEvent.getWorld().getEntitiesWithinAABB(GirlEntity.class, bbox);
      boolean flag = false;

      for (GirlEntity girl : list) {
         try {
            if (girl.isDead || !(Boolean)girl.getDataManager().get(GirlEntity.BusyKey)) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         flag = true;
         break;
      }

      try {
         if (!flag) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      breakEvent.getPlayer().sendStatusMessage(new TextComponentString("this bed is currently used by a girl.. pls don't disturb okay? ... you are kinda mean rn"), true);
      breakEvent.setCanceled(true);
   }

   @SubscribeEvent
   @SideOnly(Side.CLIENT)
   public void onPlayerPushOutOfBlocks(PlayerSPPushOutOfBlocksEvent playerSPPushOutOfBlocksEvent) {
      try {
         if (GirlEntity.getByPlayerUuid(playerSPPushOutOfBlocksEvent.getEntityPlayer()) != null) {
            playerSPPushOutOfBlocksEvent.setCanceled(true);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
