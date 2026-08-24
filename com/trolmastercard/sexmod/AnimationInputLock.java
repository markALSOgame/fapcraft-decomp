package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MovementInput;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AnimationInputLock {
   private static boolean AnimationLocked = true;
   public static boolean SneakPressed = false;
   public static boolean JumpPressed = false;
   public static MovementInput MovementInput;

   @SubscribeEvent

   public void onInputUpdate(InputUpdateEvent inputUpdateEvent) {
        block13: {
            try {
                MovementInput = inputUpdateEvent.getMovementInput();
                SneakPressed = AnimationInputLock.MovementInput.sneak;
                JumpPressed = AnimationInputLock.MovementInput.jump;
                if (AnimationLocked) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw AnimationInputLock.rethrow(runtimeException);
            }
            try {
                if (AnimationInputLock.MovementInput.jump) {
                    PlayerGirlEntity.getRenderLabelOffset();
                }
            }
            catch (RuntimeException runtimeException) {
                throw AnimationInputLock.rethrow(runtimeException);
            }
            try {
                if (AnimationInputLock.MovementInput.sneak) {
                    GirlEntity.advanceAnimationState(Minecraft.getMinecraft().player.getPersistentID());
                }
            }
            catch (RuntimeException runtimeException) {
                throw AnimationInputLock.rethrow(runtimeException);
            }
            try {
                try {
                    if (!AnimationInputLock.MovementInput.jump || !(GuiHud.c >= 1.0)) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw AnimationInputLock.rethrow(runtimeException);
                }
                GirlEntity.unregisterRemote(Minecraft.getMinecraft().player.getPersistentID());
            }
            catch (RuntimeException runtimeException) {
                throw AnimationInputLock.rethrow(runtimeException);
            }
        }
        AnimationInputLock.MovementInput.backKeyDown = false;
        AnimationInputLock.MovementInput.forwardKeyDown = false;
        AnimationInputLock.MovementInput.leftKeyDown = false;
        AnimationInputLock.MovementInput.rightKeyDown = false;
        AnimationInputLock.MovementInput.sneak = false;
        AnimationInputLock.MovementInput.jump = false;
        AnimationInputLock.MovementInput.moveForward = 0.0f;
        AnimationInputLock.MovementInput.moveStrafe = 0.0f;
        Minecraft.getMinecraft().player.setVelocity(0.0, 0.0, 0.0);
    }

   public static boolean isAnimationLocked() {
      return AnimationLocked;
   }

   public static void setAnimationLocked(boolean flag) {
      try {
         AnimationLocked = flag;
         if (!flag) {
            resetAnimationLock();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @SideOnly(Side.CLIENT)
   static void resetAnimationLock() {
      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;

      try {
         if (!PlayerGirlEntity.isPlayerGirl(mcPlayer)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      mcPlayer.sendStatusMessage(new TextComponentString("Jump to get out of the animation"), true);
   }

   @SubscribeEvent

   public void onMouseEvent(MouseEvent mouseEvent) {
        block4: {
            try {
                try {
                    if (AnimationLocked || !mouseEvent.isButtonstate()) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw AnimationInputLock.rethrow(runtimeException);
                }
                mouseEvent.setCanceled(true);
            }
            catch (RuntimeException runtimeException) {
                throw AnimationInputLock.rethrow(runtimeException);
            }
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
