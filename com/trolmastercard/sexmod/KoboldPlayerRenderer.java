package com.trolmastercard.sexmod;

import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KoboldPlayerRenderer extends CustomColorGirlRenderer {
   public KoboldPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override

   protected Vec3i getBoneColor(String string) {
        block10: {
            EntityDataManager entityDataManager = this.RenderEntity.getDataManager();
            EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.valueOf((String)entityDataManager.get(KoboldNpc.BodyColorKey));
            BlockPos blockPos = (BlockPos)entityDataManager.get(KoboldNpc.EyeColorKey);
            try {
                if (KoboldNpcRenderer.MainColorBones.contains(string)) {
                    return eyeAndKoboldColor.getMainColor();
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayerRenderer.rethrow(runtimeException);
            }
            try {
                if (KoboldNpcRenderer.SecondaryColorBones.contains(string)) {
                    return eyeAndKoboldColor.getSecondaryColor();
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayerRenderer.rethrow(runtimeException);
            }
            try {
                try {
                    if (!"irisR".equals(string) && !"irisL".equals(string)) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayerRenderer.rethrow(runtimeException);
                }
                return blockPos;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayerRenderer.rethrow(runtimeException);
            }
        }
        return z;
    }

   @Override
   protected Vector4f getBoneColorRGBA(String string, float f, float f2, float f3) {
      if ("mouth".equals(string)) {
         String[] stringArray = GirlEffectEntity.getAttributeStrings(this.RenderEntity);
         int i = Integer.parseInt(stringArray[7]);

         try {
            if (i == 1) {
               return new Vector4f(f, f2, f3, -0.078125F);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }

      return super.getBoneColorRGBA(string, f, f2, f3);
   }

   @Override
   protected void applyBodySizeScale() {
      float f = 0.25F - (Float)this.RenderEntity.getDataManager().get(KoboldPlayer.BodySizeKey);
      GlStateManager.scale(1.0F - f, 1.0F - f, 1.0F - f);
   }

   @Override
   protected void undoBodySizeScale() {
      float f = 0.25F - (Float)this.RenderEntity.getDataManager().get(KoboldPlayer.BodySizeKey);
      double d = 1.0 / (1.0 - f);
      GlStateManager.scale(d, d, d);
   }

   @Override
   protected void applyHeadRenderTransform() {
      GlStateManager.translate(0.0, -0.8F, 0.05);
      GlStateManager.scale(0.5, 0.5, 0.5);
   }

   @Override

   protected void applyHeldItemPose(boolean flag, ItemStack stack) {
        float f;
        block9: {
            block10: {
                try {
                    try {
                        super.applyHeldItemPose(flag, stack);
                        if (stack.getItem().getItemUseAction(stack) != EnumAction.BOW) break block9;
                        if (flag) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.rotate((float)170.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                if (flag) {
                    GlStateManager.translate((float)0.1f, (float)0.0f, (float)0.0f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayerRenderer.rethrow(runtimeException);
            }
            return;
        }
        try {
            f = flag ? 80.0f : 180.0f;
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayerRenderer.rethrow(runtimeException);
        }
        GlStateManager.rotate((float)f, (float)1.0f, (float)0.0f, (float)0.0f);
    }

   @Override

   protected void applyArmPose(boolean flag, boolean flag2) {
        block8: {
            block9: {
                block6: {
                    block7: {
                        try {
                            try {
                                super.applyArmPose(flag, flag2);
                                if (!flag) break block6;
                                if (!flag2) break block7;
                            }
                            catch (RuntimeException runtimeException) {
                                throw KoboldPlayerRenderer.rethrow(runtimeException);
                            }
                            GlStateManager.translate((double)0.06, (double)0.0, (double)-0.13);
                            GlStateManager.rotate((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            GlStateManager.rotate((float)38.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                            GlStateManager.rotate((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldPlayerRenderer.rethrow(runtimeException);
                        }
                    }
                    GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.translate((double)0.0, (double)-0.3f, (double)-0.13);
                    break block8;
                }
                try {
                    if (!flag2) break block9;
                    GlStateManager.rotate((float)150.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.translate((double)0.0, (double)-0.35, (double)0.0);
                    break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayerRenderer.rethrow(runtimeException);
                }
            }
            GlStateManager.translate((double)0.0, (double)-0.1, (double)-0.083f);
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
