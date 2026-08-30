package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LunaPlayerRenderer extends GirlPlayerRenderer {
   float HairFlipRotation = 0.0F;

   public LunaPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override
   protected void applyBodyScale() {
      GlStateManager.translate(0.0F, -1.0F, 0.0F);
      GlStateManager.scale(0.65F, 0.65F, 0.65F);
   }

   @Override
   protected ItemStack a(@Nullable ItemStack stack) {
      switch (this.RenderEntity.getCurrentAction()) {
         case FISHING_IDLE:
         case FISHING_START:
            ItemStack stack2 = ((LunaNpc)this.RenderEntity).HeldRodStack;
            this.RenderEntity.setHeldItem(EnumHand.MAIN_HAND, stack2);
            return stack2;
         default:
            return stack;
      }
   }

   boolean isBusy() {
      return (Boolean)this.RenderEntity.getDataManager().get(GirlEntity.BusyKey);
   }

   @Override
   protected void applyBoneState(String string, GeoBone bone) {
      try {
         if (Minecraft.getMinecraft().isGamePaused()) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String string2 = string;
      byte bv = -1;

      label76: {
         label75: {
            label74: {
               label73: {
                  try {
                     switch (string2.hashCode()) {
                        case -345841663:
                           break label73;
                        case -345841657:
                           break label74;
                        case 3198432:
                           if (!string2.equals("head")) {
                              break label76;
                           }
                           break label75;
                        case 2120576361:
                           break;
                        default:
                           break label76;
                     }
                  } catch (RuntimeException error2) {
                     throw rethrow(error2);
                  }

                  if (string2.equals("backHair")) {
                     bv = 1;
                  }
                  break label76;
               }

               if (string2.equals("frontHairL")) {
                  bv = 2;
               }
               break label76;
            }

            if (string2.equals("frontHairR")) {
               bv = 3;
            }
            break label76;
         }

         bv = 0;
      }

      label96: {
         try {
            switch (bv) {
               case 0:
                  this.HairFlipRotation = bone.getRotationX();
                  return;
               case 1:
                  break;
               case 2:
               case 3:
                  break label96;
               default:
                  return;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         try {
            if (this.isBusy()) {
               return;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         if (this.HairFlipRotation > 0.0F) {
            double d = this.HairFlipRotation / AngleMath.degToRadians(45.0F);
            float f = (float)LerpMath.lerp(0.0, 0.75, d);
            bone.setPositionZ(f);
            bone.setPositionY(f);
            bone.setRotationX(-this.HairFlipRotation);
         }

         return;
      }

      try {
         if (this.isBusy()) {
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      bone.setRotationX(-this.HairFlipRotation);
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
               f = 60.0F;
               break label21;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         f = 150.0F;
      }

      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(0.0, 0.08, -0.05);
   }

   @Override
   protected void applyHandOffset(boolean flag) {
      float f;
      label24: {
         try {
            if (flag) {
               f = 60.0F;
               break label24;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         f = 150.0F;
      }

      try {
         GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
         if (flag) {
            GlStateManager.translate(0.12, 0.0, 0.0);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   @Override

   protected void applyDualHandOffset(boolean flag, boolean flag2) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            super.applyDualHandOffset(flag, flag2);
                            if (flag || !flag2) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaPlayerRenderer.rethrow(runtimeException);
                        }
                        GlStateManager.rotate((float)120.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaPlayerRenderer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (flag || flag2) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaPlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.translate((double)0.0, (double)0.3, (double)-0.15);
                    GlStateManager.rotate((float)-45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (!flag || flag2) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayerRenderer.rethrow(runtimeException);
                }
                GlStateManager.translate((double)-0.025, (double)-0.05, (double)0.0);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayerRenderer.rethrow(runtimeException);
            }
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
