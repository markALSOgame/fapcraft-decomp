package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GalathPlayerRenderer extends GirlPlayerRenderer {
   static final HashSet<String> ArmorTintedBones = new HashSet<>(
      Arrays.asList(
         "kneeL",
         "kneeR",
         "shinL",
         "shinR",
         "armorHelmet",
         "sockL",
         "sockR",
         "braBoobL",
         "braBoobR",
         "armorNippleR",
         "armorNippleL",
         "slip",
         "turnable",
         "static"
      )
   );

   public GalathPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Nullable
   @Override
   protected Vec3f getRenderOffset(GirlEntity girl) {
      try {
         if (girl.world instanceof PreviewWorld) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (((BoxSource)girl).c()) {
            return null;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return GalathNpcRenderer.y;
   }

   @Override
   public HashSet<String> getAdultBoneParts() {
      HashSet set = GalathNpcRenderer.E;
      GalathNpcRenderer.E.addAll(BoneColorHelper.AdultParts);
      return GalathNpcRenderer.E;
   }

   @Override
   protected void renderWithTexture(Tessellator tessellator, BufferBuilder bufferBuilder, GirlEntity girl, Vec3f vec3f, float f) {
      a(tessellator, bufferBuilder, girl, vec3f, f);
   }

   @Override

   public void render(GirlEntity girl, double d, double d2, double d3, float f, float f2) {
        block6: {
            try {
                try {
                    try {
                        super.render(girl, d, d2, d3, f, f2);
                        if (GalathPlayerRenderer.Mc.gameSettings.thirdPersonView != 0 || !GalathPlayerRenderer.Mc.player.getPersistentID().equals(((PlayerGirlEntity)girl).m())) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayerRenderer.rethrow(runtimeException);
                    }
                    if (girl.Q()) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayerRenderer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GalathPlayerRenderer.rethrow(runtimeException);
            }
        }
        GalathNpcRenderer.renderGirl(girl, f2);
    }

   @Override
   protected void applyRenderOffset(boolean flag) {
      try {
         super.applyRenderOffset(flag);
         if (flag) {
            GlStateManager.translate(0.15, 0.0, 0.0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @Override

   protected void applyPoseTransform(boolean flag, boolean flag2) {
        block8: {
            block7: {
                try {
                    try {
                        super.applyPoseTransform(flag, flag2);
                        if (!flag) break block7;
                        GlStateManager.translate((double)0.0, (double)-0.05, (double)-0.05);
                        GlStateManager.rotate((float)15.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        if (!flag2) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.translate((double)0.3, (double)0.2, (double)0.0);
                    GlStateManager.rotate((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)15.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                GlStateManager.translate((double)0.0, (double)0.0, (double)0.1);
                GlStateManager.rotate((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                if (flag2) {
                    GlStateManager.rotate((float)-29.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw GalathPlayerRenderer.rethrow(runtimeException);
            }
        }
    }

   @Override
   protected Vector4f getBoneColor(String string, float f, float f2, float f3) {
      try {
         if (!ArmorTintedBones.contains(string)) {
            return this.a(f, f2, f3);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if ("armorHelmet".equals(string)) {
            return super.getBoneColor(string, f, f2, f3);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      ItemStack stack = ItemStack.EMPTY;
      String string2 = string;
      int i = -1;

      label138: {
         label137: {
            label136: {
               label135: {
                  label134: {
                     label133: {
                        label132: {
                           label131: {
                              label130: {
                                 label129: {
                                    label128: {
                                       label127: {
                                          label126: {
                                             try {
                                                switch (string2.hashCode()) {
                                                   case -1626323751:
                                                      break label127;
                                                   case -1626323745:
                                                      break label126;
                                                   case -892481938:
                                                      break label129;
                                                   case -65677861:
                                                      if (!string2.equals("braBoobL")) {
                                                         break label138;
                                                      }
                                                      break label137;
                                                   case -65677855:
                                                      break;
                                                   case 3533120:
                                                      break label130;
                                                   case 102194025:
                                                      break label135;
                                                   case 102194031:
                                                      break label136;
                                                   case 109407570:
                                                      break label131;
                                                   case 109407576:
                                                      break label132;
                                                   case 109610248:
                                                      break label133;
                                                   case 109610254:
                                                      break label134;
                                                   case 134582103:
                                                      break label128;
                                                   default:
                                                      break label138;
                                                }
                                             } catch (RuntimeException error3) {
                                                throw rethrow(error3);
                                             }

                                             if (string2.equals("braBoobR")) {
                                                i = 1;
                                             }
                                             break label138;
                                          }

                                          if (string2.equals("armorNippleR")) {
                                             i = 2;
                                          }
                                          break label138;
                                       }

                                       if (string2.equals("armorNippleL")) {
                                          i = 3;
                                       }
                                       break label138;
                                    }

                                    if (string2.equals("turnable")) {
                                       i = 4;
                                    }
                                    break label138;
                                 }

                                 if (string2.equals("static")) {
                                    i = 5;
                                 }
                                 break label138;
                              }

                              if (string2.equals("slip")) {
                                 i = 6;
                              }
                              break label138;
                           }

                           if (string2.equals("shinL")) {
                              i = 7;
                           }
                           break label138;
                        }

                        if (string2.equals("shinR")) {
                           i = 8;
                        }
                        break label138;
                     }

                     if (string2.equals("sockL")) {
                        i = 9;
                     }
                     break label138;
                  }

                  if (string2.equals("sockR")) {
                     i = 10;
                  }
                  break label138;
               }

               if (string2.equals("kneeL")) {
                  i = 11;
               }
               break label138;
            }

            if (string2.equals("kneeR")) {
               i = 12;
            }
            break label138;
         }

         i = 0;
      }

      switch (i) {
         case 0:
         case 1:
         case 2:
         case 3:
            stack = (ItemStack)this.RenderEntity.getDataManager().get(InventoryGirlEntity.ChestKey);
            break;
         case 4:
         case 5:
         case 6:
            stack = (ItemStack)this.RenderEntity.getDataManager().get(InventoryGirlEntity.PantsKey);
            break;
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
            stack = (ItemStack)this.RenderEntity.getDataManager().get(InventoryGirlEntity.BootsKey);
      }

      try {
         if (!(stack.getItem() instanceof ItemArmor)) {
            return this.a(f, f2, f3);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      ItemArmor armor = (ItemArmor)stack.getItem();

      try {
         switch (armor.getArmorMaterial()) {
            case GOLD:
               return new Vector4f(f, f2, f3, -0.15625F);
            case IRON:
            case CHAIN:
               return new Vector4f(f, f2, f3, -0.125F);
            case LEATHER:
               break;
            default:
               return new Vector4f(f, f2, f3, -0.1875F);
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      i = armor.getColor(stack);
      float f4 = (i >> 16 & 0xFF) / 255.0F;
      float f5 = (i >> 8 & 0xFF) / 255.0F;
      float f6 = (i & 0xFF) / 255.0F;
      f *= f4;
      f2 *= f5;
      f3 *= f6;
      return new Vector4f(f, f2, f3, -0.09375F);
   }

   @Override
   protected void renderModel(GeoModel model, BufferBuilder bufferBuilder, GirlEntity girl, float f, float f2, float f3, float f4, float f5) {
      GeoBone bone = (GeoBone)model.topLevelBones.get(0);
      GeoBone bone2 = null;
      GeoBone bone3 = null;

      for (GeoBone bone4 : bone.childBones) {
         String string = bone4.getName();
         byte bv = -1;

         label44: {
            label43: {
               try {
                  switch (string.hashCode()) {
                     case 3029410:
                        break;
                     case 109761491:
                        if (!string.equals("steve")) {
                           break label44;
                        }
                        break label43;
                     default:
                        break label44;
                  }
               } catch (IOException error) {
                  throw rethrow(error);
               }

               if (string.equals("body")) {
                  bv = 1;
               }
               break label44;
            }

            bv = 0;
         }

         switch (bv) {
            case 0:
               bone3 = bone4;
               break;
            case 1:
               bone2 = bone4;
         }
      }

      MATRIX_STACK.push();
      MATRIX_STACK.translate(bone);
      MATRIX_STACK.moveToPivot(bone);
      MATRIX_STACK.rotate(bone);
      MATRIX_STACK.scale(bone);
      MATRIX_STACK.moveBackFromPivot(bone);
      this.renderRecursively(bufferBuilder, bone2, f, f2, f3, f4);
      Tessellator.getInstance().draw();
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

      try {
         Minecraft.getMinecraft().renderEngine.bindTexture(this.d(this.RenderEntity));
      } catch (IOException error2) {
         error2.printStackTrace();
      }

      this.renderRecursively(bufferBuilder, bone3, f, f2, f3, this.RenderEntity.getScale());
      Tessellator.getInstance().draw();
      MATRIX_STACK.pop();
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
