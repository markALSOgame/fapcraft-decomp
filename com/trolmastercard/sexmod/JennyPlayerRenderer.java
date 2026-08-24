package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JennyPlayerRenderer extends GirlPlayerRenderer {
   public JennyPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override
   protected void c() {
      GlStateManager.translate(0.0F, -1.25F, 0.0F);
      GlStateManager.scale(0.8F, 0.8F, 0.8F);
   }

   @Override

   protected void a(boolean flag, boolean flag2) {
        block9: {
            block8: {
                try {
                    try {
                        super.a(flag, flag2);
                        if (flag || flag2) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.translate((double)0.0, (double)-0.1, (double)0.05);
                    GlStateManager.rotate((float)40.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.rotate((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (!flag || flag2) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayerRenderer.rethrow(runtimeException);
                }
                GlStateManager.translate((double)-0.025, (double)-0.1, (double)0.0);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw JennyPlayerRenderer.rethrow(runtimeException);
            }
        }
    }

   @Override
   protected void a(boolean flag) {
      try {
         super.a(flag);
         if (flag) {
            GlStateManager.translate(0.15, 0.0, 0.0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
