package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ElliePlayerRenderer extends GirlPlayerRenderer {
   public ElliePlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override
   protected void applyScaleOffset() {
      GlStateManager.translate(0.0F, -1.5F, 0.0F);
   }

   @Override
   protected void applyHeldItemTransform(boolean flag, ItemStack stack) {
      try {
         super.applyHeldItemTransform(flag, stack);
         switch (stack.getItem().getItemUseAction(stack)) {
            case BLOCK:
            case BOW:
               return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      float f;
      label31: {
         try {
            if (flag) {
               f = 90.0F;
               break label31;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         f = 180.0F;
      }

      try {
         GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
         if (flag) {
            GlStateManager.translate(0.0, 0.23900000452995301, -0.1F);
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      GlStateManager.translate(0.0, 0.1, -0.07);
   }

   @Override
   protected void applyHandOffset(boolean flag) {
      float f;
      label24: {
         try {
            if (flag) {
               f = 90.0F;
               break label24;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         f = 180.0F;
      }

      try {
         GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
         if (flag) {
            GlStateManager.translate(0.2, -0.2, 0.0);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   @Override

   protected void applyDualHandOffset(boolean flag, boolean flag2) {
        block7: {
            block8: {
                block6: {
                    try {
                        try {
                            if (!flag) break block6;
                            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                            if (!flag2) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayerRenderer.rethrow(runtimeException);
                        }
                        GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        GlStateManager.rotate((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                        GlStateManager.rotate((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        GlStateManager.translate((float)0.4f, (float)0.0f, (float)0.228f);
                        break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayerRenderer.rethrow(runtimeException);
                    }
                }
                try {
                    GlStateManager.translate((float)0.0f, (float)0.282f, (float)0.141f);
                    if (!flag2) break block8;
                    GlStateManager.translate((double)0.165, (double)-0.45f, (double)0.0);
                    GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.rotate((float)-27.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayerRenderer.rethrow(runtimeException);
                }
            }
            GlStateManager.translate((double)0.0, (double)0.0, (double)-0.05);
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
