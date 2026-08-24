package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GirlRenderHandler {
   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderWorldLast(RenderWorldLastEvent renderWorldLastEvent) {
      Minecraft mc = Minecraft.getMinecraft();

      try {
         if (mc.gameSettings.thirdPersonView != 0) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      UUID uuid = mc.player.getPersistentID();
      GirlEntity girl = null;

      try {
         for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
            try {
               if (girl2 == null) {
                  continue;
               }
            } catch (ConcurrentModificationException error2) {
               throw rethrow(error2);
            }

            try {
               if (girl2.isDead) {
                  continue;
               }
            } catch (ConcurrentModificationException error3) {
               throw rethrow(error3);
            }

            try {
               if (!girl2.world.isRemote) {
                  continue;
               }
            } catch (ConcurrentModificationException error4) {
               throw rethrow(error4);
            }

            try {
               if (!(girl2 instanceof GirlMaster)) {
                  continue;
               }
            } catch (ConcurrentModificationException error5) {
               throw rethrow(error5);
            }

            GirlMaster girlMaster = (GirlMaster)girl2;
            if (uuid.equals(girlMaster.getGirlUuid())) {
               girl = girl2;
               break;
            }
         }
      } catch (ConcurrentModificationException error6) {
      }

      try {
         if (girl == null) {
            return;
         }
      } catch (ConcurrentModificationException error7) {
         throw rethrow(error7);
      }

      Render render = mc.getRenderManager().getEntityRenderObject(girl);

      try {
         if (render == null) {
            return;
         }
      } catch (ConcurrentModificationException error8) {
         throw rethrow(error8);
      }

      float f = mc.player.rotationYaw;
      GoblinNpcRenderer.N = (float)(mc.player.movementInput.moveStrafe * GoblinNpcRenderer.G.x);
      GoblinNpcRenderer.N = GoblinNpcRenderer.N + -(f - GoblinNpcRenderer.H) * 3.0F;
      GoblinNpcRenderer.N = LerpMath.lerp(GoblinNpcRenderer.I, GoblinNpcRenderer.N, 0.1F);
      float f2 = -mc.player.rotationPitch;
      GoblinNpcRenderer.x = (float)(mc.player.movementInput.moveForward * GoblinNpcRenderer.G.z + (float)mc.player.motionY * GoblinNpcRenderer.G.y);
      GoblinNpcRenderer.x = GoblinNpcRenderer.x + -(f2 - GoblinNpcRenderer.t) * 3.0F;
      GoblinNpcRenderer.x = LerpMath.lerp(GoblinNpcRenderer.E, GoblinNpcRenderer.x, 0.1F);
      GoblinNpcRenderer.renderGirlBox(girl, renderWorldLastEvent.getPartialTicks());
      GoblinNpcRenderer.H = f;
      GoblinNpcRenderer.I = GoblinNpcRenderer.N;
      GoblinNpcRenderer.t = f2;
      GoblinNpcRenderer.E = GoblinNpcRenderer.x;
      GlStateManager.enableLighting();
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderThrowingGirl(RenderWorldLastEvent renderWorldLastEvent) {
        Minecraft minecraft = Minecraft.getMinecraft();
        try {
            if (minecraft.player == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GirlRenderHandler.rethrow(concurrentModificationException);
        }
        UUID uUID = minecraft.player.getPersistentID();
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                float f;
                double d;
                double d2;
                double d3;
                GirlEntity girl2;
                RenderManager renderManager;
                block20: {
                    block19: {
                        try {
                            if (!girl.world.isRemote) {
                                continue;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlRenderHandler.rethrow(concurrentModificationException);
                        }
                        try {
                            if (girl.isDead) {
                                continue;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlRenderHandler.rethrow(concurrentModificationException);
                        }
                        try {
                            if (!(girl instanceof GirlMaster)) {
                                continue;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlRenderHandler.rethrow(concurrentModificationException);
                        }
                        GirlMaster girlMaster = (GirlMaster)((Object)girl);
                        if (girl.getCurrentAction() != GirlAnimationState.START_THROWING) continue;
                        try {
                            block21: {
                                girl.setTracked(true);
                                renderManager = minecraft.getRenderManager();
                                girl2 = girl;
                                d3 = 0.0;
                                d2 = 0.0;
                                d = 0.0;
                                if (!uUID.equals(girlMaster.getGirlUuid())) break block19;
                                break block21;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GirlRenderHandler.rethrow(concurrentModificationException);
                                }
                            }
                            f = -420.69f;
                            break block20;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlRenderHandler.rethrow(concurrentModificationException);
                        }
                    }
                    f = 0.0f;
                }
                renderManager.renderEntity((Entity)girl2, d3, d2, d, f, minecraft.getRenderPartialTicks(), false);
                girl.setTracked(false);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
    }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderHand(RenderHandEvent renderHandEvent) {
        Minecraft minecraft = Minecraft.getMinecraft();
        UUID uUID = minecraft.player.getPersistentID();
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                block13: {
                    try {
                        if (!(girl instanceof GirlMaster)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlRenderHandler.rethrow(concurrentModificationException);
                    }
                    GirlAnimationState girlAnimationState = girl.getCurrentAction();
                    if (girlAnimationState == GirlAnimationState.PICK_UP) break block13;
                    try {
                        if (girlAnimationState != GirlAnimationState.START_THROWING) {
                            continue;
                        }
                        break block13;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlRenderHandler.rethrow(concurrentModificationException);
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlRenderHandler.rethrow(concurrentModificationException);
                    }
                }
                GirlMaster girlMaster = (GirlMaster)((Object)girl);
                UUID uUID2 = girlMaster.getGirlUuid();
                try {
                    if (!uUID.equals(uUID2)) continue;
                    renderHandEvent.setCanceled(true);
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlRenderHandler.rethrow(concurrentModificationException);
                    return;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderPlayerPre(Pre pre) {
        UUID uUID = pre.getEntityPlayer().getPersistentID();
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                GirlMaster girlMaster;
                block13: {
                    try {
                        if (!(girl instanceof GirlMaster)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlRenderHandler.rethrow(concurrentModificationException);
                    }
                    girlMaster = (GirlMaster)((Object)girl);
                    GirlAnimationState girlAnimationState = girl.getCurrentAction();
                    if (girlAnimationState == GirlAnimationState.PICK_UP) break block13;
                    try {
                        if (girlAnimationState != GirlAnimationState.START_THROWING) {
                            continue;
                        }
                        break block13;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlRenderHandler.rethrow(concurrentModificationException);
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlRenderHandler.rethrow(concurrentModificationException);
                    }
                }
                try {
                    if (!uUID.equals(girlMaster.getGirlUuid())) continue;
                    pre.setCanceled(true);
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlRenderHandler.rethrow(concurrentModificationException);
                    return;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
