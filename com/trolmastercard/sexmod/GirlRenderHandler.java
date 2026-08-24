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
      Minecraft mc = Minecraft.getMinecraft();

      try {
         if (mc.player == null) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      UUID uuid = mc.player.getPersistentID();

      try {
         for (GirlEntity girl : GirlEntity.getAllGirls()) {
            if (!girl.world.isRemote || girl.isDead || !(girl instanceof GirlMaster)) {
               continue;
            }

            GirlMaster girlMaster = (GirlMaster)girl;

            if (girl.getCurrentAction() != GirlAnimationState.START_THROWING) {
               continue;
            }

            girl.setTracked(true);
            mc.getRenderManager()
               .renderEntity(
                  girl,
                  0.0,
                  0.0,
                  0.0,
                  uuid.equals(girlMaster.getGirlUuid()) ? -420.69F : 0.0F,
                  mc.getRenderPartialTicks(),
                  false
               );
            girl.setTracked(false);
            return;
         }
      } catch (ConcurrentModificationException error2) {
      }

      GlStateManager.enableLighting();
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderHand(RenderHandEvent renderHandEvent) {
      Minecraft mc = Minecraft.getMinecraft();
      UUID uuid = mc.player.getPersistentID();

      try {
         for (GirlEntity girl : GirlEntity.getAllGirls()) {
            if (!(girl instanceof GirlMaster)) {
               continue;
            }

            GirlAnimationState girlAnimationState = girl.getCurrentAction();

            if (girlAnimationState != GirlAnimationState.PICK_UP && girlAnimationState != GirlAnimationState.START_THROWING) {
               continue;
            }

            UUID girlUuid = ((GirlMaster)girl).getGirlUuid();

            if (!uuid.equals(girlUuid)) {
               continue;
            }

            renderHandEvent.setCanceled(true);
            break;
         }
      } catch (ConcurrentModificationException error) {
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderPlayerPre(Pre pre) {
      UUID uuid = pre.getEntityPlayer().getPersistentID();

      try {
         for (GirlEntity girl : GirlEntity.getAllGirls()) {
            if (!(girl instanceof GirlMaster)) {
               continue;
            }

            GirlMaster girlMaster = (GirlMaster)girl;
            GirlAnimationState girlAnimationState = girl.getCurrentAction();

            if (girlAnimationState != GirlAnimationState.PICK_UP && girlAnimationState != GirlAnimationState.START_THROWING) {
               continue;
            }

            if (!uuid.equals(girlMaster.getGirlUuid())) {
               continue;
            }

            pre.setCanceled(true);
            break;
         }
      } catch (ConcurrentModificationException error) {
      }
   }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
