package com.trolmastercard.sexmod;

import java.util.HashSet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BiaPlayerRenderer extends GirlPlayerRenderer {
   public BiaPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override
   protected void applyScaleOffset() {
      GlStateManager.translate(0.0, -1.0, -0.05);
      GlStateManager.scale(0.65F, 0.65F, 0.65F);
   }

   @Override
   protected void applyHandOffset(boolean flag) {
      try {
         super.applyHandOffset(flag);
         if (flag) {
            GlStateManager.translate(0.15, 0.0, 0.0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @Override

   protected void applyDualHandOffset(boolean flag, boolean flag2) {
        block9: {
            block8: {
                try {
                    try {
                        super.applyDualHandOffset(flag, flag2);
                        if (flag || flag2) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.translate((double)0.0, (double)-0.1, (double)0.05);
                    GlStateManager.rotate((float)40.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.rotate((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (!flag || flag2) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayerRenderer.rethrow(runtimeException);
                }
                GlStateManager.translate((double)-0.025, (double)-0.1, (double)0.0);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw BiaPlayerRenderer.rethrow(runtimeException);
            }
        }
    }

   @Override
   public HashSet<String> getFilteredBoneNames() {
      return new HashSet<String>() {
         {
            this.add("boobs");
            this.add("booty");
            this.add("vagina");
            this.add("fuckhole");
            this.add("leaf7");
            this.add("leaf8");
         }
      };
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
