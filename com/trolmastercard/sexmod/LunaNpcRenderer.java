package com.trolmastercard.sexmod;

import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class LunaNpcRenderer extends GeoGirlRenderer {
   float HeadRotationX;

   public LunaNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
   }

   @Override
   protected ItemStack getActionItem(@Nullable ItemStack stack) {
      switch (this.RenderEntity.getCurrentAction()) {
         case FISHING_IDLE:
         case FISHING_START:
            ItemStack stack2 = ((LunaNpc)this.RenderEntity).ao;
            ItemStack stack3 = (ItemStack)this.RenderEntity.getDataManager().get(LunaNpc.ActiveItemStackKey);

            try {
               if (stack3.equals(ItemStack.EMPTY)) {
                  return stack2;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            Map map = EnchantmentHelper.getEnchantments(stack3);
            EnchantmentHelper.setEnchantments(map, stack2);
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
   protected void applyBoneState(BufferBuilder bufferBuilder, String string, GeoBone bone) {
      try {
         if (Minecraft.getMinecraft().isGamePaused()) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String string2 = string;
      byte bv = -1;

      label125: {
         label124: {
            label123: {
               label122: {
                  label121: {
                     label120: {
                        label119: {
                           try {
                              switch (string2.hashCode()) {
                                 case -1870254701:
                                    break label120;
                                 case -1870254695:
                                    break label119;
                                 case -1548738978:
                                    break label123;
                                 case -345841663:
                                    break label121;
                                 case -345841657:
                                    break label122;
                                 case 3198432:
                                    if (!string2.equals("head")) {
                                       break label125;
                                    }
                                    break label124;
                                 case 2120576361:
                                    break;
                                 default:
                                    break label125;
                              }
                           } catch (RuntimeException error2) {
                              throw rethrow(error2);
                           }

                           if (string2.equals("backHair")) {
                              bv = 1;
                           }
                           break label125;
                        }

                        if (string2.equals("sideHairR")) {
                           bv = 2;
                        }
                        break label125;
                     }

                     if (string2.equals("sideHairL")) {
                        bv = 3;
                     }
                     break label125;
                  }

                  if (string2.equals("frontHairL")) {
                     bv = 4;
                  }
                  break label125;
               }

               if (string2.equals("frontHairR")) {
                  bv = 5;
               }
               break label125;
            }

            if (string2.equals("offhand")) {
               bv = 6;
            }
            break label125;
         }

         bv = 0;
      }

      label152: {
         label153: {
            label137: {
               try {
                  switch (bv) {
                     case 0:
                        this.HeadRotationX = bone.getRotationX();
                        return;
                     case 1:
                        break label137;
                     case 2:
                     case 3:
                        break;
                     case 4:
                     case 5:
                        break label153;
                     case 6:
                        break label152;
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

               double d = this.HeadRotationX / AngleMath.degToRadians(45.0F);
               float f = (float)LerpMath.lerp(0.0, 1.3F, d);
               bone.setPositionZ(-f);
               bone.setPositionY(f);
               break label153;
            }

            try {
               if (this.isBusy()) {
                  return;
               }
            } catch (RuntimeException error5) {
               throw rethrow(error5);
            }

            double d2 = this.HeadRotationX / AngleMath.degToRadians(45.0F);
            float f2 = (float)LerpMath.lerp(0.0, 0.75, d2);
            bone.setPositionZ(f2);
            bone.setPositionY(f2);
            bone.setRotationX(-this.HeadRotationX);
            return;
         }

         try {
            if (this.isBusy()) {
               return;
            }
         } catch (RuntimeException error6) {
            throw rethrow(error6);
         }

         bone.setRotationX(-this.HeadRotationX);
         return;
      }

      LunaNpc luna = (LunaNpc)this.RenderEntity;
      ItemStack stack = (ItemStack)this.RenderEntity.getDataManager().get(LunaNpc.HeldItemStackKey);

      try {
         if (stack.equals(ItemStack.EMPTY)) {
            return;
         }
      } catch (RuntimeException error7) {
         throw rethrow(error7);
      }

      try {
         if (luna.Z != 1.0F) {
            return;
         }
      } catch (RuntimeException error8) {
         throw rethrow(error8);
      }

      GlStateManager.pushMatrix();
      Tessellator.getInstance().draw();
      MatrixUtil.applyGeoBoneTransform(IGeoRenderer.MATRIX_STACK, bone);
      GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(luna.aa, luna.aa, luna.aa);
      Minecraft.getMinecraft().getItemRenderer().renderItem(this.RenderEntity, stack, TransformType.THIRD_PERSON_RIGHT_HAND);
      GeoGirlRenderer.n.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
      GlStateManager.popMatrix();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
