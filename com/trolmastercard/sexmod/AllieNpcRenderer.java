package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AllieNpcRenderer extends GeoGirlRenderer {
   public AllieNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
   }

   @Override

   public void render(GeoModel model, GirlEntity girl, float f, float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        AllieNpc allie;
        block8: {
            allie = (AllieNpc)girl;
            try {
                try {
                    if (girl.getCurrentAction() != GirlAnimationState.NULL || girl.isTracked()) break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpcRenderer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw AllieNpcRenderer.rethrow(runtimeException);
            }
        }
        try {
            allie.ConversionTimer = allie.ConversionTimer == 1.0f ? allie.ConversionTimer : allie.ConversionTimer - 0.01f;
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpcRenderer.rethrow(runtimeException);
        }
        f5 = allie.ConversionTimer;
        try {
            GlStateManager.scale((float)f5, (float)f5, (float)f5);
            f7 = 0.0f;
            f6 = f5 == 1.0f ? 0.0f : 3.0f - f5 * 3.0f;
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpcRenderer.rethrow(runtimeException);
        }
        GlStateManager.translate((float)f7, (float)f6, (float)0.0f);
        super.render(model, girl, f, f2, f3, f4, f5);
    }

   @Override
   protected void renderNameLabel(double d, double d2, double d3) {
      try {
         if (this.RenderEntity.getCurrentAction() == GirlAnimationState.NULL) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (this.RenderEntity.isTracked()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (this.RenderEntity.getCurrentAction().hideNameTag) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (Minecraft.getMinecraft().getRenderManager().renderViewEntity == null) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      this.renderLivingLabel(this.RenderEntity, this.RenderEntity.getDisplayName(), d, d2 + this.RenderEntity.getRenderLabelOffset(), d3, 300);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
