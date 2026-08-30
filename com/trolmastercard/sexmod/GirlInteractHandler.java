package com.trolmastercard.sexmod;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

public class GirlInteractHandler {
   static final int DressUpButtonId = 284453;

   @SubscribeEvent
   public void onPlayerSleepAttempt(PlayerSleepInBedEvent playerSleepInBedEvent) {
      EntityPlayer player = playerSleepInBedEvent.getEntityPlayer();
      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer(player);

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!player.isSneaking()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      playerSleepInBedEvent.setResult(SleepResult.OTHER_PROBLEM);
   }

   @SubscribeEvent
   public void onGetCollisionBoxes(GetCollisionBoxesEvent getCollisionBoxesEvent) {
   }

   @SubscribeEvent
   public void onRightClickBlock(RightClickBlock rightClickBlock) {
      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(rightClickBlock.getEntityPlayer().getPersistentID());
      BlockPos pos = rightClickBlock.getPos();
      World world2 = rightClickBlock.getEntityPlayer().world;
      EntityPlayer player = rightClickBlock.getEntityPlayer();

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!playerGirl.v()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (!BedLogic.getBedRespawnPos(world2, pos, rightClickBlock.getHitVec(), rightClickBlock.getFace(), player)) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if ((Boolean)playerGirl.getDataManager().get(GirlEntity.BusyKey)) {
            rightClickBlock.setCanceled(true);
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if (!player.isSneaking()) {
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      ArrayList<BlockPos> list = new ArrayList<BlockPos>();

      try {
         if (world2.getBlockState(pos.north()).getBlock() == Blocks.AIR) {
            list.add(pos.north());
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      try {
         if (world2.getBlockState(pos.east()).getBlock() == Blocks.AIR) {
            list.add(pos.east());
         }
      } catch (RuntimeException error7) {
         throw rethrow(error7);
      }

      try {
         if (world2.getBlockState(pos.south()).getBlock() == Blocks.AIR) {
            list.add(pos.south());
         }
      } catch (RuntimeException error8) {
         throw rethrow(error8);
      }

      try {
         if (world2.getBlockState(pos.west()).getBlock() == Blocks.AIR) {
            list.add(pos.west());
         }
      } catch (RuntimeException error9) {
         throw rethrow(error9);
      }

      BlockPos pos2 = null;

      for (BlockPos pos3 : list) {
         if (pos2 == null) {
            pos2 = pos3;
         } else {
            Vec3d vec3d = player.getPositionVector();
            double d = this.distanceTo(
               pos3.getX(), pos3.getY(), pos3.getZ(), vec3d.x, vec3d.y, vec3d.z
            );
            double d2 = this.distanceTo(
               pos2.getX(), pos2.getY(), pos2.getZ(), vec3d.x, vec3d.y, vec3d.z
            );
            if (d < d2) {
               pos2 = pos3;
            }
         }
      }

      try {
         if (pos2 == null) {
            player.sendMessage(new TextComponentString("Bed is obscured"));
            return;
         }
      } catch (RuntimeException error10) {
         throw rethrow(error10);
      }

      try {
         player.setPosition(pos2.getX() + 0.5, pos2.getY(), pos2.getZ() + 0.5);
         if (pos.north().equals(pos2)) {
            player.rotationYaw = 0.0F;
         }
      } catch (RuntimeException error11) {
         throw rethrow(error11);
      }

      try {
         if (pos.east().equals(pos2)) {
            player.rotationYaw = 90.0F;
         }
      } catch (RuntimeException error12) {
         throw rethrow(error12);
      }

      try {
         if (pos.south().equals(pos2)) {
            player.rotationYaw = 180.0F;
         }
      } catch (RuntimeException error13) {
         throw rethrow(error13);
      }

      try {
         if (pos.west().equals(pos2)) {
            player.rotationYaw = -90.0F;
         }
      } catch (RuntimeException error14) {
         throw rethrow(error14);
      }

      try {
         if (rightClickBlock.getWorld().isRemote) {
            AnimationInputLock.setAnimationLocked(false);
            playerGirl.H_();
            return;
         }
      } catch (RuntimeException error15) {
         throw rethrow(error15);
      }

      playerGirl.setTargetPos(new Vec3d(pos2.getX() + 0.5, pos2.getY() + 0.0F, pos2.getZ() + 0.5));
      playerGirl.b(player.rotationYaw);
      playerGirl.getDataManager().set(GirlEntity.BusyKey, true);
      playerGirl.u_();
   }

   double distanceTo(double d, double d2, double d3, double d4, double d5, double d6) {
      double d7 = d - d4;
      double d8 = d2 - d5;
      double d9 = d3 - d6;
      return Math.sqrt(d7 * d7 + d8 * d8 + d9 * d9);
   }

   @SubscribeEvent
   public void onPlayerRespawn(PlayerRespawnEvent playerRespawnEvent) {
      EntityPlayer player2 = playerRespawnEvent.player;

      try {
         if (player2 == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity playerGirl = PlayerGirlEntity.getServerSideByUuid(player2.getPersistentID());

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Vec3d vec3d = player2.getPositionVector();
      playerGirl.dimension = player2.dimension;
      playerGirl.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
      playerGirl.updateAITasks();
      System.out.println(player2.world.isAreaLoaded(playerGirl.getPosition(), 2));
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onPlayerInteractWithGirl(EntityInteract entityInteract) {
      try {
         if (!(entityInteract.getTarget() instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (entityInteract.getEntityPlayer().isSneaking()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (!entityInteract.getEntityPlayer().getPersistentID().equals(Minecraft.getMinecraft().player.getPersistentID())) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(mcPlayer.getPersistentID());
      EntityPlayer player2 = (EntityPlayer)entityInteract.getTarget();
      PlayerGirlEntity playerGirl2 = PlayerGirlEntity.getByPlayer(player2);

      try {
         if (playerGirl2 == null) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if (playerGirl != null) {
            mcPlayer.sendStatusMessage(new TextComponentString("no lesbo yet owo"), true);
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      try {
         if (!playerGirl2.isInteractionAllowed()) {
            return;
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      try {
         if (playerGirl2.canStartInteraction()) {
            playerGirl2.startInteraction(Minecraft.getMinecraft().player);
         }
      } catch (RuntimeException error7) {
         throw rethrow(error7);
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onPlayerInteract(EntityInteract entityInteract) {
      try {
         if (!(entityInteract.getTarget() instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!entityInteract.getEntityPlayer().getPersistentID().equals(Minecraft.getMinecraft().player.getPersistentID())) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(mcPlayer.getPersistentID());

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      EntityPlayer player2 = (EntityPlayer)entityInteract.getTarget();
      PlayerGirlEntity playerGirl2 = PlayerGirlEntity.getByUuid(player2.getPersistentID());

      try {
         if (playerGirl2 != null) {
            player2.sendStatusMessage(new TextComponentString("no lesbo yet owo"), true);
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if (playerGirl.canStartInteraction()) {
            playerGirl.Accept = false;
            playerGirl.startInteraction(player2);
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }
   }

   @SubscribeEvent

   public void onRightClickStartDoggy(RightClickBlock rightClickBlock) {
        block28: {
            EntityPlayer entityPlayer = rightClickBlock.getEntityPlayer();
            PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer(entityPlayer);
            try {
                if (playerGirl == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                if (!(playerGirl instanceof SlimePlayer)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                if (!entityPlayer.isSneaking()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                if (!entityPlayer.getHeldItemMainhand().equals(ItemStack.EMPTY)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                if (((Boolean)playerGirl.getDataManager().get(GirlEntity.BusyKey)).booleanValue()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                if (entityPlayer.rotationPitch < 20.0f) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            Vec3d vec3d = rightClickBlock.getHitVec();
            try {
                if (vec3d == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            Vec3d vec3d2 = new Vec3d(vec3d.x, Math.floor(vec3d.y) + 0.0, vec3d.z);
            try {
                if (vec3d.distanceTo(entityPlayer.getPositionVector()) > 3.0) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                try {
                    entityPlayer.setPosition(vec3d2.x, Math.floor(vec3d.y), vec3d2.z);
                    playerGirl.setTargetPos(vec3d2);
                    playerGirl.b(entityPlayer.rotationYaw);
                    playerGirl.getDataManager().set(GirlEntity.BusyKey, true);
                    playerGirl.getDataManager().set(GirlEntity.OutfitIndexKey, 0);
                    playerGirl.setCurrentAction(GirlAnimationState.STARTDOGGY);
                    if (!rightClickBlock.getWorld().isRemote || !Minecraft.getMinecraft().player.getPersistentID().equals(entityPlayer.getPersistentID())) break block28;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlInteractHandler.rethrow(runtimeException);
                }
                AnimationInputLock.setAnimationLocked(false);
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
        }
    }

   @SubscribeEvent

   public void onLivingHurt(LivingHurtEvent livingHurtEvent) {
        block13: {
            try {
                if (!(livingHurtEvent.getEntityLiving() instanceof EntityPlayer)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                if (livingHurtEvent.getSource() != DamageSource.FALL) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            EntityPlayer entityPlayer = (EntityPlayer)livingHurtEvent.getEntityLiving();
            PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer(entityPlayer);
            try {
                if (playerGirl == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
            try {
                try {
                    if (!(playerGirl instanceof AlliePlayer) && !(playerGirl instanceof BeePlayer)) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlInteractHandler.rethrow(runtimeException);
                }
                livingHurtEvent.setCanceled(true);
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
        }
    }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onGuiInit(InitGuiEvent initGuiEvent) {
        String string;
        GuiScreen guiScreen;
        block15: {
            guiScreen = initGuiEvent.getGui();
            try {
                try {
                    if (guiScreen instanceof GuiInventory || guiScreen instanceof GuiContainerCreative) break block15;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlInteractHandler.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
        }
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        try {
            if (entityPlayerSP == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer((EntityPlayer)entityPlayerSP);
        try {
            if (playerGirl == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        try {
            if (playerGirl.A_()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        List list = initGuiEvent.getButtonList();
        try {
            string = playerGirl.getOutfitIndex() == 0 ? "action.names.dressup" : "action.names.strip";
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        String string2 = I18n.format((String)string, (Object[])new Object[0]);
        list.add(new GuiButton(284453, (int)((double)guiScreen.width * 0.5 - 35.0), (int)((double)guiScreen.height * 0.87), 70, 20, string2));
        initGuiEvent.setButtonList(list);
    }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onActionPerformed(ActionPerformedEvent actionPerformedEvent) {
        block19: {
            GuiScreen guiScreen = actionPerformedEvent.getGui();
            try {
                try {
                    if (guiScreen instanceof GuiInventory || guiScreen instanceof GuiContainerCreative) break block19;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlInteractHandler.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GirlInteractHandler.rethrow(runtimeException);
            }
        }
        try {
            if (actionPerformedEvent.getButton().id != 284453) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(minecraft.player.getPersistentID());
        try {
            if (playerGirl == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        try {
            if (playerGirl.A_()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        try {
            if (playerGirl.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        try {
            if (playerGirl.getCurrentAction() != GirlAnimationState.NULL) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlInteractHandler.rethrow(runtimeException);
        }
        minecraft.gameSettings.thirdPersonView = 2;
        minecraft.entityRenderer.loadEntityShader(null);
        playerGirl.setCurrentAction(GirlAnimationState.STRIP);
        AnimationInputLock.setAnimationLocked(false);
        minecraft.player.closeScreen();
    }

   @SubscribeEvent
   public void onLivingDamage(LivingDamageEvent livingDamageEvent) {
      try {
         if (livingDamageEvent.getSource() != DamageSource.FALL) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      EntityLivingBase livingBase = livingDamageEvent.getEntityLiving();

      try {
         if (!(livingBase instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(livingBase.getPersistentID());

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (playerGirl instanceof SlimePlayer) {
            livingDamageEvent.setResult(Result.DENY);
            livingDamageEvent.setAmount(0.0F);
            livingDamageEvent.setCanceled(true);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
