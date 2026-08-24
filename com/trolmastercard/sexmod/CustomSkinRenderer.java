package com.trolmastercard.sexmod;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CustomSkinRenderer extends GeoItemRenderer<ItemAlliesLamp> {
   Minecraft Mc = Minecraft.getMinecraft();
   static ResourceLocation SkinTexture = null;

   public CustomSkinRenderer() {
      super(new ModelAlliesLamp());
   }

   ResourceLocation getSkinTexture() {
      if (SkinTexture == null) {
         try {
            URL url = new URL(
               "https://sessionserver.mojang.com/session/minecraft/profile/"
                  + Minecraft.getMinecraft().player.getPersistentID().toString().replace("-", "")
            );
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String string = reader.lines().collect(Collectors.joining());
            int i = string.indexOf("\"value\" : ");
            int i2 = i + 11;
            StringBuilder sb = new StringBuilder();
            int i3 = 0;

            try {
               while (string.charAt(i2 + i3) != '"') {
                  sb.append(string.charAt(i2 + i3));
                  i3++;
               }
            } catch (Exception error) {
               throw rethrow(error);
            }

            String string2 = new String(Base64.getDecoder().decode(sb.toString()));
            int i4 = string2.indexOf("\"url\" : ");
            int i5 = i4 + 9;
            StringBuilder sb2 = new StringBuilder();
            int i6 = 0;

            try {
               while (string2.charAt(i5 + i6) != '"') {
                  sb2.append(string2.charAt(i5 + i6));
                  i6++;
               }
            } catch (Exception error2) {
               throw rethrow(error2);
            }

            URL url2 = new URL(sb2.toString());
            BufferedImage bufferedImage = ImageIO.read(url2);
            BufferedImage bufferedImage2 = ImageIO.read(this.Mc.getResourceManager().getResource(new ModelAlliesLamp().c(new ItemAlliesLamp())).getInputStream());

            for (int i7 = 0; i7 < bufferedImage2.getWidth(); i7++) {
               for (int i8 = 0; i8 < bufferedImage2.getHeight(); i8++) {
                  int i9 = bufferedImage.getRGB(i7, i8);

                  try {
                     if (i9 != 0) {
                        bufferedImage2.setRGB(i7, i8, i9);
                     }
                  } catch (Exception error3) {
                     throw rethrow(error3);
                  }
               }
            }

            SkinTexture = Minecraft.getMinecraft().getRenderManager().renderEngine.getDynamicTextureLocation("lamptex", new DynamicTexture(bufferedImage2));
         } catch (Exception error4) {
            SkinTexture = new ModelAlliesLamp().c(new ItemAlliesLamp());
         }
      }

      return SkinTexture;
   }

   public void render(GeoModel model, ItemAlliesLamp item, float f, float f2, float f3, float f4, float f5) {
      GlStateManager.disableCull();
      GlStateManager.enableRescaleNormal();
      this.renderEarly(item, f, f2, f3, f4, f5);
      this.renderLate(item, f, f2, f3, f4, f5);
      BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

      for (GeoBone bone : model.topLevelBones) {
         this.renderBone(bufferBuilder, item, bone, f2, f3, f4, f5);
      }

      Tessellator.getInstance().draw();
      this.renderAfter(item, f, f2, f3, f4, f5);
      GlStateManager.disableRescaleNormal();
      GlStateManager.enableCull();
   }

   public void renderBone(BufferBuilder bufferBuilder, ItemAlliesLamp item, GeoBone bone, float f, float f2, float f3, float f4) {
      try {
         MATRIX_STACK.push();
         MATRIX_STACK.translate(bone);
         MATRIX_STACK.moveToPivot(bone);
         MATRIX_STACK.rotate(bone);
         MATRIX_STACK.scale(bone);
         MATRIX_STACK.moveBackFromPivot(bone);
         this.Mc.renderEngine.bindTexture(this.getSkinTexture());
         if (this.shouldRenderBone(bone.getName())) {
            this.b(bufferBuilder, item, bone, f, f2, f3, f4);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      MATRIX_STACK.pop();
   }


   boolean shouldRenderBone(String string) {
        boolean flag;
        block10: {
            block9: {
                block8: {
                    try {
                        try {
                            if (string.equals("leftArm") || string.equals("rightArm")) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw CustomSkinRenderer.rethrow(runtimeException);
                        }
                        return true;
                    }
                    catch (RuntimeException runtimeException) {
                        throw CustomSkinRenderer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (!this.Mc.player.getEntityData().getBoolean("sexmodAllieInUse") || this.Mc.gameSettings.thirdPersonView != 0) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw CustomSkinRenderer.rethrow(runtimeException);
                    }
                    flag = true;
                    break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw CustomSkinRenderer.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

   void b(BufferBuilder bufferBuilder, ItemAlliesLamp item, GeoBone bone, float f, float f2, float f3, float f4) {
      if (!bone.isHidden) {
         for (GeoCube geoCube : bone.childCubes) {
            MATRIX_STACK.push();
            GlStateManager.pushMatrix();
            this.renderCube(bufferBuilder, geoCube, f, f2, f3, f4);
            GlStateManager.popMatrix();
            MATRIX_STACK.pop();
         }

         for (GeoBone bone2 : bone.childBones) {
            this.renderBone(bufferBuilder, item, bone2, f, f2, f3, f4);
         }
      }
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
