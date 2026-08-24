package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BeePlayerRenderer extends GirlPlayerRenderer {
   public BeePlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override
   protected void applyItemHoldRotation(boolean flag, ItemStack stack) {
      float f;
      label16: {
         try {
            if (flag) {
               f = 290.0F;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         f = 90.0F;
      }

      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
   }

   @Override
   protected void applyRenderOffset() {
      GlStateManager.translate(0.0F, -0.6F, 0.0F);
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
   }

   @Override
   protected void applyBodyTilt(boolean flag) {
      try {
         super.applyBodyTilt(flag);
         if (flag) {
            GlStateManager.translate(0.1, 0.0, 0.0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @Override

   protected void applyPostureTransform(boolean flag, boolean flag2) {
        block8: {
            block7: {
                try {
                    try {
                        if (!flag) break block7;
                        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        GlStateManager.translate((float)0.0f, (float)-0.14f, (float)-0.17f);
                        if (!flag2) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeePlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.rotate((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.translate((double)0.067, (double)0.0, (double)0.0);
                    break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw BeePlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                if (flag2) {
                    GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.translate((float)0.0f, (float)0.165f, (float)0.0f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw BeePlayerRenderer.rethrow(runtimeException);
            }
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
