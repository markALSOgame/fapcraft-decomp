package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.entity.item.EntityBoat;
import javax.vecmath.Vector2f;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

public class GirlCameraController {
   public static final float SentinelPartialTick = 1.2345679F;
   Vec3d StartPos = null;
   Vec3d PrevPos = null;
   PlayerGirlEntity CurrentGirl = null;
   boolean CameraActive = false;

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderPlayer(Pre pre) {
      try {
         if (pre.getPartialRenderTick() == 1.2345679F) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity.C_();
      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(pre.getEntityPlayer().getPersistentID());

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      pre.setCanceled(true);
      renderGirlAsPlayer(playerGirl, pre.getEntityPlayer(), pre.getX(), pre.getY(), pre.getZ(), pre.getPartialRenderTick());
   }

   @SideOnly(Side.CLIENT)

   public static void renderGirlAsPlayer(PlayerGirlEntity playerGirl, EntityPlayer player2, double d, double d2, double d3, float f) {
        float f2;
        boolean flag;
        PlayerGirlEntity playerGirl2;
        Minecraft minecraft;
        block8: {
            minecraft = Minecraft.getMinecraft();
            player2 = playerGirl.asPlayer(player2);
            try {
                try {
                    if (!player2.isInvisibleToPlayer((EntityPlayer)minecraft.player) || playerGirl.E_()) break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlCameraController.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GirlCameraController.rethrow(runtimeException);
            }
        }
        RenderManager renderManager = minecraft.getRenderManager();
        try {
            playerGirl.rotationYaw = player2.rotationYaw;
            playerGirl.prevRotationYawHead = player2.prevRotationYawHead;
            playerGirl.rotationYawHead = player2.rotationYawHead;
            playerGirl.prevRotationPitch = player2.prevRotationPitch;
            playerGirl.rotationPitch = player2.rotationPitch;
            playerGirl.prevRotationYaw = player2.prevRotationYaw;
            playerGirl.prevPosX = player2.prevPosX;
            playerGirl.prevPosY = player2.prevPosY;
            playerGirl.prevPosZ = player2.prevPosZ;
            playerGirl.lastTickPosX = player2.lastTickPosX;
            playerGirl.lastTickPosY = player2.lastTickPosY;
            playerGirl.lastTickPosZ = player2.lastTickPosZ;
            playerGirl.renderYawOffset = player2.renderYawOffset;
            playerGirl.prevRenderYawOffset = player2.prevRenderYawOffset;
            playerGirl.ad = player2.isSneaking();
            playerGirl.aj = player2.isSprinting();
            playerGirl.ak = player2.isRiding();
            playerGirl.af = player2.onGround;
            playerGirl2 = playerGirl;
            flag = player2.getItemInUseCount() != 0;
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        playerGirl2.ah = flag;
        double d4 = player2.lastTickPosX - player2.posX;
        double d5 = player2.posZ - player2.lastTickPosZ;
        double d6 = Math.PI / 180 * (double)player2.rotationYaw;
        try {
            playerGirl.ao = new Vector2f((float)(d4 * Math.cos(d6) + d5 * Math.sin(d6)), (float)(d4 * Math.sin(d6) + d5 * Math.cos(d6)));
            f2 = playerGirl.z() ? GirlCameraController.getRenderYOffset(playerGirl, player2) : 0.0f;
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        float f3 = f2;
        GirlPlayerRenderer.RenderTrigger = true;
        renderManager.renderEntity((Entity)playerGirl, d, d2 + (double)f3, d3, 90.0f, f, false);
    }


   static float getRenderYOffset(PlayerGirlEntity playerGirl, EntityPlayer player) {
        block22: {
            float f;
            block24: {
                block23: {
                    block21: {
                        block20: {
                            try {
                                if (((Boolean)playerGirl.getDataManager().get(GirlEntity.BusyKey)).booleanValue()) {
                                    return 0.0f;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCameraController.rethrow(runtimeException);
                            }
                            try {
                                try {
                                    try {
                                        if (!(player.getHeldItemMainhand().getItem() instanceof ItemBow) && !(player.getHeldItemOffhand().getItem() instanceof ItemBow)) break block20;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GirlCameraController.rethrow(runtimeException);
                                    }
                                    if (!playerGirl.ah) break block20;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GirlCameraController.rethrow(runtimeException);
                                }
                                playerGirl.setCurrentAction(GirlAnimationState.BOW);
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCameraController.rethrow(runtimeException);
                            }
                        }
                        try {
                            try {
                                if (playerGirl.getCurrentAction() != GirlAnimationState.BOW || playerGirl.ah) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCameraController.rethrow(runtimeException);
                            }
                            playerGirl.setCurrentAction(GirlAnimationState.NULL);
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCameraController.rethrow(runtimeException);
                        }
                    }
                    try {
                        if (playerGirl.getCurrentAction() == GirlAnimationState.BOW) {
                            playerGirl.rotationYaw = playerGirl.rotationYawHead;
                            playerGirl.renderYawOffset = playerGirl.rotationYawHead;
                            playerGirl.prevRenderYawOffset = playerGirl.prevRotationYawHead;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCameraController.rethrow(runtimeException);
                    }
                    try {
                        try {
                            if (!playerGirl.ak) break block22;
                            if (!(player.getRidingEntity() instanceof EntityBoat)) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCameraController.rethrow(runtimeException);
                        }
                        f = 0.4f;
                        break block24;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCameraController.rethrow(runtimeException);
                    }
                }
                f = 0.2f;
            }
            return f;
        }
        return 0.0f;
    }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderTick(RenderTickEvent renderTickEvent) {
        Minecraft minecraft;
        block32: {
            block33: {
                minecraft = Minecraft.getMinecraft();
                try {
                    if (minecraft.player == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GirlCameraController.rethrow(runtimeException);
                }
                try {
                    try {
                        if (renderTickEvent.phase != TickEvent.Phase.END) break block32;
                        if (this.StartPos == null) break block33;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCameraController.rethrow(runtimeException);
                    }
                    minecraft.player.setPosition(this.StartPos.x, this.StartPos.y, this.StartPos.z);
                    minecraft.player.lastTickPosX = this.PrevPos.x;
                    minecraft.player.lastTickPosY = this.PrevPos.y;
                    minecraft.player.lastTickPosZ = this.PrevPos.z;
                    this.StartPos = null;
                    this.PrevPos = null;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlCameraController.rethrow(runtimeException);
                }
            }
            return;
        }
        try {
            if (minecraft.gameSettings.thirdPersonView != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(minecraft.player.getPersistentID());
        try {
            if (playerGirl == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        try {
            if (!playerGirl.Q()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        this.StartPos = minecraft.player.getPositionVector();
        this.PrevPos = new Vec3d(minecraft.player.lastTickPosX, minecraft.player.lastTickPosY, minecraft.player.lastTickPosZ);
        Vec3d vec3d = playerGirl.getModelBone("girlCam");
        vec3d = playerGirl.b(vec3d, renderTickEvent.renderTickTime);
        vec3d = vec3d.add(LerpMath.lerpVec3d(this.PrevPos, this.StartPos, (double)renderTickEvent.renderTickTime));
        minecraft.player.posX = vec3d.x;
        minecraft.player.posY = vec3d.y - (double)minecraft.player.getEyeHeight();
        minecraft.player.posZ = vec3d.z;
        minecraft.player.lastTickPosX = vec3d.x;
        minecraft.player.lastTickPosY = vec3d.y - (double)minecraft.player.getEyeHeight();
        minecraft.player.lastTickPosZ = vec3d.z;
        GirlAnimationState girlAnimationState = playerGirl.getCurrentAction();
        float f = playerGirl.I().floatValue();
        try {
            if (playerGirl.canDoAction(girlAnimationState, (EntityPlayer)minecraft.player)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        if (girlAnimationState.flipGirlYaw) {
            f += 180.0f;
        }
        try {
            if (minecraft.player.rotationPitch > girlAnimationState.maxGirlPitch) {
                minecraft.player.rotationPitch = girlAnimationState.maxGirlPitch;
                minecraft.player.prevRotationPitch = girlAnimationState.maxGirlPitch;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        try {
            if (minecraft.player.rotationPitch < girlAnimationState.minGirlPitch) {
                minecraft.player.rotationPitch = girlAnimationState.minGirlPitch;
                minecraft.player.prevRotationPitch = girlAnimationState.minGirlPitch;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        try {
            if (minecraft.player.rotationYaw > f + 90.0f) {
                minecraft.player.rotationYaw = f + 90.0f;
                minecraft.player.prevRotationYaw = f + 90.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        try {
            if (minecraft.player.rotationYaw < f - 90.0f) {
                minecraft.player.rotationYaw = f - 90.0f;
                minecraft.player.prevRotationYaw = f - 90.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
    }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onCameraSetup(CameraSetup cameraSetup) {
      Minecraft mc = Minecraft.getMinecraft();

      try {
         if (mc.player == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(mc.player.getPersistentID());

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (!playerGirl.F_()) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (!playerGirl.Q()) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      cameraSetup.setRoll(180.0F);
      cameraSetup.setPitch(-cameraSetup.getPitch());
      cameraSetup.setYaw(-cameraSetup.getYaw());
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderWorldLast(RenderWorldLastEvent renderWorldLastEvent) {
      Minecraft mc = Minecraft.getMinecraft();

      try {
         if (this.StartPos == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (mc.gameSettings.thirdPersonView != 0) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(mc.player.getPersistentID());

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      Vec3d vec3d = mc.player.getPositionVector();
      Vec3d vec3d2 = LerpMath.lerpVec3d(this.PrevPos, this.StartPos, renderWorldLastEvent.getPartialTicks());
      Vec3d vec3d3 = vec3d2.subtract(vec3d);
      renderGirlAsPlayer(playerGirl, mc.player, vec3d3.x, vec3d3.y, vec3d3.z, renderWorldLastEvent.getPartialTicks());
      GlStateManager.enableLighting();
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderTickStart(RenderTickEvent renderTickEvent) {
        PlayerGirlEntity playerGirl;
        Minecraft minecraft;
        block19: {
            block20: {
                block17: {
                    block18: {
                        minecraft = Minecraft.getMinecraft();
                        try {
                            if (minecraft.player == null) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCameraController.rethrow(runtimeException);
                        }
                        try {
                            if (renderTickEvent.phase == TickEvent.Phase.END) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCameraController.rethrow(runtimeException);
                        }
                        playerGirl = PlayerGirlEntity.getByUuid(minecraft.player.getPersistentID());
                        try {
                            try {
                                if (playerGirl != null) break block17;
                                if (!this.CameraActive) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCameraController.rethrow(runtimeException);
                            }
                            this.CameraActive = false;
                            minecraft.player.eyeHeight = minecraft.player.getDefaultEyeHeight();
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCameraController.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                try {
                    try {
                        if (!playerGirl.Q()) break block19;
                        if (!this.CameraActive) break block20;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCameraController.rethrow(runtimeException);
                    }
                    this.CameraActive = false;
                    minecraft.player.eyeHeight = minecraft.player.getDefaultEyeHeight();
                }
                catch (RuntimeException runtimeException) {
                    throw GirlCameraController.rethrow(runtimeException);
                }
            }
            return;
        }
        try {
            if (this.CurrentGirl != playerGirl) {
                GirlCameraController.renderGirlAsPlayer(playerGirl, (EntityPlayer)minecraft.player, 0.0, 500.0, 0.0, renderTickEvent.renderTickTime);
                this.CurrentGirl = playerGirl;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlCameraController.rethrow(runtimeException);
        }
        minecraft.player.eyeHeight = playerGirl.R();
        this.CameraActive = true;
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
