package com.trolmastercard.sexmod;

import java.util.HashSet;
import javax.vecmath.Vector3f;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SlimePlayerRenderer extends GirlPlayerRenderer {
   Vector3f SlimeScale = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f SlimePosition = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f SlimeRotation = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f TorsoRotation = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f BoobsRotation = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f UpperBodyRotation = new Vector3f(0.0F, 0.0F, 0.0F);
   Vector3f HeadRotation = new Vector3f(0.0F, 0.0F, 0.0F);

   public SlimePlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override
   protected void applyBaseTransform() {
      GlStateManager.translate(0.0F, -1.25F, 0.0F);
      GlStateManager.scale(0.8F, 0.8F, 0.8F);
   }

   @Override
   protected void applyBoneState(String string, GeoBone bone) {
      try {
         if ("slime".equals(string)) {
            this.SlimeRotation = new Vector3f(bone.getRotationX(), bone.getRotationY(), bone.getRotationZ());
            this.SlimeScale = new Vector3f(bone.getScaleX(), bone.getScaleY(), bone.getScaleZ());
            this.SlimePosition = new Vector3f(bone.getPositionX(), bone.getPositionY(), bone.getPositionZ());
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if ("upperBody".equals(string)) {
            this.UpperBodyRotation = new Vector3f(bone.getRotationX(), bone.getRotationY(), bone.getRotationZ());
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if ("torso".equals(string)) {
            this.TorsoRotation = new Vector3f(bone.getRotationX(), bone.getRotationY(), bone.getRotationZ());
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if ("head".equals(string)) {
            this.HeadRotation = new Vector3f(bone.getRotationX(), bone.getRotationY(), bone.getRotationZ());
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if ("boobs".equals(string)) {
            this.BoobsRotation = new Vector3f(bone.getRotationX(), bone.getRotationY(), bone.getRotationZ());
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      try {
         if ("figure".equals(string)) {
            bone.setRotationX(this.SlimeRotation.x);
            bone.setRotationY(this.SlimeRotation.y);
            bone.setRotationZ(this.SlimeRotation.z);
            bone.setScaleX(this.SlimeScale.x);
            bone.setScaleY(this.SlimeScale.y);
            bone.setScaleZ(this.SlimeScale.z);
            bone.setPositionX(this.SlimePosition.x);
            bone.setPositionY(this.SlimePosition.y);
            bone.setPositionZ(this.SlimePosition.z);
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      try {
         if ("dress".equals(string)) {
            bone.setRotationX(this.UpperBodyRotation.x);
            bone.setRotationY(this.UpperBodyRotation.y);
            bone.setRotationZ(this.UpperBodyRotation.z);
         }
      } catch (RuntimeException error7) {
         throw rethrow(error7);
      }

      try {
         if ("hat".equals(string)) {
            bone.setRotationX(this.HeadRotation.x);
            bone.setRotationY(this.HeadRotation.y);
            bone.setRotationZ(this.HeadRotation.z);
         }
      } catch (RuntimeException error8) {
         throw rethrow(error8);
      }

      try {
         if ("boobsSlime".equals(string)) {
            bone.setRotationX(this.BoobsRotation.x);
            bone.setRotationY(this.BoobsRotation.y);
            bone.setRotationZ(this.BoobsRotation.z);
         }
      } catch (RuntimeException error9) {
         throw rethrow(error9);
      }
   }

   @Override
   protected void applyItemOffset(boolean flag) {
      try {
         super.applyItemOffset(flag);
         if (flag) {
            GlStateManager.translate(0.15F, 0.0F, 0.0F);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GlStateManager.translate(-0.02, 0.0, 0.0);
      GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
   }

   @Override
   public HashSet<String> getTrackedBones() {
      HashSet set = super.getTrackedBones();
      set.add("figure");
      return set;
   }

   @Override

   protected void applyArmPose(boolean flag, boolean flag2) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            super.applyArmPose(flag, flag2);
                            if (!flag || flag2) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimePlayerRenderer.rethrow(runtimeException);
                        }
                        GlStateManager.translate((double)-0.025, (double)-0.025, (double)0.0);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimePlayerRenderer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (flag || !flag2) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimePlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.rotate((float)120.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (flag || flag2) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayerRenderer.rethrow(runtimeException);
                }
                GlStateManager.translate((double)0.0, (double)0.4, (double)-0.1);
                GlStateManager.rotate((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            }
            catch (RuntimeException runtimeException) {
                throw SlimePlayerRenderer.rethrow(runtimeException);
            }
        }
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
      label21: {
         try {
            if (flag) {
               f = 30.0F;
               break label21;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         f = 135.0F;
      }

      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(0.0, 0.05, -0.05);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
