package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GalathNpcRenderer extends GeoGirlRenderer<GalathNpc> implements GirlBoneFilter {
   public static final int D = 14;
   public static final HashSet<String> E = new HashSet<String>() {
      {
         this.add("static");
         this.add("turnable");
         this.add("slip");
         this.add("boobs");
         this.add("booty");
         this.add("vagina");
         this.add("fuckhole");
         this.add("futaBallLR");
         this.add("futaBallLL");
         this.add("coin");
         this.add("pentagram");
      }
   };
   public static final Vec3f y = new Vec3f(0.0F, 0.0F, 0.0F);
   static final RgbaColor4 H = new RgbaColor4(152, 45, 62, 255);
   static final RgbaColor4 I = new RgbaColor4(84, 66, 88, 255);
   static final Vec2f C = new Vec2f(0.25F, 0.125F);
   static final Vec2f x = new Vec2f(0.375F, 0.125F);
   static final float F = 0.125F;
   static final ResourceLocation BodyTexture = new ResourceLocation("sexmod", "textures/star.png");
   static final int v = 105;
   static final int A = 125;
   static final float B = 0.0296875F;
   static final float J = 0.06484375F;
   static final float z = 0.026124999F;
   static final float u = 0.0570625F;
   static final QuadRenderHelper.QuadConfig G = new QuadRenderHelper.QuadConfig(
      H,
      0.1F,
      12,
      0.035F,
      (arg1, arg2) -> (float)(Math.sin(arg2 * 0.3 + -0.2 * arg1) * 15.0),
      (arg1b, arg2b) -> (float)(Math.sin(arg2b * -0.15 + -0.2 * arg1b) * 3.0),
      (arg1c, arg2c) -> 0.0F,
      0.03F,
      0.005F
   );
   static final QuadRenderHelper.QuadConfig t = new QuadRenderHelper.QuadConfig(
      H,
      0.0F,
      12,
      0.0F,
      (arg1d, arg2d) -> (float)(Math.sin(arg2d * 0.3 + -0.2 * arg1d) * 15.0),
      (arg1e, arg2e) -> (float)(Math.sin(arg2e * -0.15 + -0.2 * arg1e) * 3.0),
      (arg1f, arg2f) -> 0.0F,
      0.03F,
      0.005F
   );
   boolean Initialized = false;
   float SavedHeadYaw = 0.0F;

   public GalathNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
   }

   @Override
   @Nullable
   protected Vec3f e(GalathNpc galath) {
      try {
         if (galath.world instanceof PreviewWorld) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (galath.bb) {
            return null;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return y;
   }

   @Override
   public HashSet<String> getFilteredBoneNames() {
      try {
         if (!this.Initialized) {
            E.addAll(BoneColorHelper.AdultParts);
            E.addAll(ManglelieNpcRenderer.B);
            this.Initialized = true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return E;
   }

   @Override
   protected void b(Tessellator tessellator, BufferBuilder bufferBuilder, GirlEntity girl, Vec3f vec3f, float f) {
      a(tessellator, bufferBuilder, girl, vec3f, f);
   }

   protected void preRender(GalathNpc galath) {
      try {
         if (galath.getCurrentAction() != GirlAnimationState.MASTERBATE) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      float f = galath.I();
      galath.rotationYaw = f;
      galath.prevRenderYawOffset = f;
      galath.renderYawOffset = f;
      galath.prevRotationYawHead = f;
      galath.rotationYawHead = f;
   }

   public void doRender(GalathNpc galath, double d2, double d3, double d4, float f, float f2) {
      Vec3d vec3d = a(galath, f2);

      try {
         if (vec3d != null) {
            galath.setTargetPosUnsafe(vec3d);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         galath.aG = vec3d;
         GalathNpc.rotateToTarget(galath, f2);
         this.applyKnockedOutPose(galath);
         this.c(galath);
         super.doRender(galath, d2, d3, d4, f, f2);
         renderGirl((GirlEntity)galath, f2);
         if (galath.hasMangleCompanion()) {
             ManglelieNpcRenderer.renderGirl((GirlEntity)galath, f2);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   void c(GalathNpc galath) {
      try {
         if (galath.getCurrentAction() != GirlAnimationState.RAPE_CHARGE) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      galath.renderYawOffset = galath.I();
      galath.prevRenderYawOffset = galath.renderYawOffset;
   }

   void applyKnockedOutPose(GalathNpc galath) {
      try {
         if (!(Boolean)galath.getDataManager().get(GalathNpc.IsKnockedOutKey)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Vec3d vec3d = new Vec3d(galath.lastTickPosX, galath.lastTickPosY, galath.lastTickPosZ);
      Vec3d vec3d2 = galath.getPositionVector().subtract(vec3d);

      boolean flag;
      label35: {
         try {
            if (Math.abs(vec3d2.x) + Math.abs(vec3d2.z) < 0.05F) {
               flag = true;
               break label35;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         flag = false;
      }

      boolean flag2 = flag;

      try {
         if (flag2) {
            galath.renderYawOffset = this.SavedHeadYaw;
            galath.prevRenderYawOffset = this.SavedHeadYaw;
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      float f = (float)(AngleMath.radToDegrees(Math.atan2(vec3d2.z, vec3d2.x)) - 90.0);
      galath.renderYawOffset = f;
      galath.prevRenderYawOffset = f;
      this.SavedHeadYaw = f;
   }

   @Nullable

   public static Vec3d a(GalathNpc galath, float f) {
        Vec3d vec3d;
        EntityLivingBase entityLivingBase;
        float f2;
        block12: {
            f2 = galath.getSpecialState();
            try {
                if (f2 == -1.0f) {
                    galath.af = -1L;
                    galath.aH = -1L;
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GalathNpcRenderer.rethrow(runtimeException);
            }
            entityLivingBase = galath.getTargetEntity();
            try {
                if (entityLivingBase == null) {
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GalathNpcRenderer.rethrow(runtimeException);
            }
            vec3d = LerpMath.lerpVec3d(new Vec3d(entityLivingBase.prevPosX, entityLivingBase.prevPosY, entityLivingBase.prevPosZ), entityLivingBase.getPositionVector(), (double)f);
            try {
                try {
                    if (f2 != 24.0f || galath.af != -1L) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathNpcRenderer.rethrow(runtimeException);
                }
                galath.af = GalathNpcRenderer.Mc.world.getTotalWorldTime();
                galath.aH = galath.af + 8L;
            }
            catch (RuntimeException runtimeException) {
                throw GalathNpcRenderer.rethrow(runtimeException);
            }
        }
        if (MathUtils.isInRange((double)f2, 24.0, 32.0)) {
            Vec3d vec3d2 = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 3.0), galath.I().floatValue() + 180.0f);
            Vec3d vec3d3 = galath.getManglePos();
            Vec3d vec3d4 = vec3d.add(0.0, (double)entityLivingBase.getEyeHeight(), 0.0).add(vec3d2);
            float f3 = ((float)GalathNpcRenderer.Mc.world.getTotalWorldTime() + f - (float)galath.af) / (float)(galath.aH - galath.af);
            return LerpMath.lerpVec3d(vec3d3, vec3d4, (double)f3);
        }
        if (MathUtils.isInRange((double)f2, 32.0, 54.0)) {
            Vec3d vec3d5 = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 1.5), galath.I().floatValue() + 180.0f);
            return vec3d.add(vec3d5);
        }
        return null;
    }

   public static void renderGirl(GirlEntity girl, float f) {
      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;

      try {
         if (mcPlayer == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      GlStateManager.pushMatrix();
      VectorUtil.drawGirlBones(Mc, girl, f);
      Minecraft.getMinecraft().getTextureManager().bindTexture(LineTexture);
      GlStateManager.disableCull();
      GlStateManager.disableLighting();
      a(girl, bufferBuilder, tessellator, LerpMath.lerp(girl.prevRenderYawOffset, girl.renderYawOffset, f));
      b(girl, bufferBuilder, tessellator, f);
      a(girl, bufferBuilder, tessellator);
      GlStateManager.popMatrix();
      GlStateManager.enableCull();
      GlStateManager.enableLighting();
   }

   static void b(GirlEntity girl, BufferBuilder bufferBuilder, Tessellator tessellator, float f) {
      try {
         if (!(girl instanceof GalathNpc)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!(Boolean)girl.getDataManager().get(GalathNpc.IsKnockedOutKey)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if ((Boolean)girl.getDataManager().get(GalathNpc.IsParalyzedKey)) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      GlStateManager.pushMatrix();
      Vec3d vec3d = girl.getModelBone("stars");
      GlStateManager.translate(vec3d.x, vec3d.y, vec3d.z);
      float f2 = (float)Minecraft.getMinecraft().world.getTotalWorldTime() + f;
      float f3 = (float)(Math.sin(f2 * 0.2) * 5.0);
      float f4 = (float)(Math.cos(f2 * 0.2) * 5.0);
      float f5 = (float)(f2 * 3.0);
      GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f5, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f4, 0.0F, 0.0F, 1.0F);
      float f6 = AngleMath.degToRadians(9.0F);
      Vec3f vec3f = GalathNpc.BodyColor;
      Minecraft.getMinecraft().getTextureManager().bindTexture(LineTexture);
      bufferBuilder.begin(3, DefaultVertexFormats.POSITION_TEX_COLOR);
      GlStateManager.glLineWidth(a(girl, f, 1.0F, 3.0F));

      for (float f7 = 0.0F; f7 < Math.PI * 2; f7 += f6) {
         double d = Math.sin(f7) * 0.3F;
         double d2 = Math.cos(f7) * 0.3F;
         bufferBuilder.pos(d, 0.0, d2).tex(0.0, 0.0).color(vec3f.X, vec3f.Y, vec3f.Z, 1.0F).endVertex();
      }

      tessellator.draw();
      Minecraft.getMinecraft().getTextureManager().bindTexture(BodyTexture);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      f6 = AngleMath.degToRadians(60.0F);

      for (float f8 = 0.0F; f8 < Math.PI * 2; f8 += f6) {
         double d3 = Math.sin(f8) * 0.3F;
         double d4 = Math.cos(f8) * 0.3F;
         bufferBuilder.pos(d3 - 0.1F, 0.1F, d4).tex(0.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
         bufferBuilder.pos(d3 + 0.1F, 0.1F, d4).tex(1.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
         bufferBuilder.pos(d3 + 0.1F, -0.1F, d4).tex(1.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
         bufferBuilder.pos(d3 - 0.1F, -0.1F, d4).tex(0.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      }

      tessellator.draw();
      GlStateManager.popMatrix();
   }


   static void a(GirlEntity girl, BufferBuilder bufferBuilder, Tessellator tessellator, float f) {
        block4: {
            try {
                try {
                    if (girl.getCurrentAction() != GirlAnimationState.GIVE_COIN || GirlAnimationState.GIVE_COIN.ticksPlaying[1] <= 100) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathNpcRenderer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GalathNpcRenderer.rethrow(runtimeException);
            }
        }
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        Vec3d[][] vec3dArray = VectorUtil.getGirlBonePositions(girl, f, "hairStrandStartR", "hairStrandMidR", "hairStrandEndR", 0.0296875f, 0.06484375f, 0.026124999f, 0.0570625f, "head");
        Vec3d[][] vec3dArray2 = VectorUtil.getGirlBonePositions(girl, f, "hairStrandStartL", "hairStrandMidL", "hairStrandEndL", 0.0296875f, 0.06484375f, 0.026124999f, 0.0570625f, "head");
        VectorUtil.drawBoneQuads(bufferBuilder, vec3dArray, I);
        VectorUtil.drawBoneQuads(bufferBuilder, vec3dArray2, I);
        tessellator.draw();
    }

   static void a(GirlEntity girl, BufferBuilder bufferBuilder, Tessellator tessellator) {
      try {
         if (!((BoxSource)girl).isVisible()) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Minecraft.getMinecraft().getTextureManager().bindTexture(ModelGalath.GalathTexture);
      Vec3d[] vec3dArray = new Vec3d[14];
      Vec3d[] vec3dArray2 = new Vec3d[14];
      int i = 0;

      try {
         while (i < 14) {
            vec3dArray[i] = girl.getModelBone("wingRV" + i);
            vec3dArray2[i] = girl.getModelBone("wingLV" + i);
            i++;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      a(bufferBuilder, tessellator, vec3dArray);
      a(bufferBuilder, tessellator, vec3dArray2);
   }

   static void a(BufferBuilder bufferBuilder, Tessellator tessellator, Vec3d[] vec3dArray) {
      bufferBuilder.begin(4, DefaultVertexFormats.POSITION_TEX_COLOR);
      bufferBuilder.pos(vec3dArray[0].x, vec3dArray[0].y, vec3dArray[0].z)
          .tex(C.X, C.Y)
          .color(255, 255, 255, 255)
          .endVertex();
       bufferBuilder.pos(vec3dArray[1].x, vec3dArray[1].y, vec3dArray[1].z)
          .tex(C.X + 0.125F, C.Y)
          .color(255, 255, 255, 255)
          .endVertex();
       bufferBuilder.pos(vec3dArray[2].x, vec3dArray[2].y, vec3dArray[2].z)
          .tex(C.X + 0.125F, C.Y + 0.125F)
          .color(255, 255, 255, 255)
          .endVertex();
       bufferBuilder.pos(vec3dArray[11].x, vec3dArray[11].y, vec3dArray[11].z)
          .tex(C.X, C.Y)
          .color(255, 255, 255, 255)
          .endVertex();
       bufferBuilder.pos(vec3dArray[12].x, vec3dArray[12].y, vec3dArray[12].z)
          .tex(C.X + 0.125F, C.Y)
          .color(255, 255, 255, 255)
          .endVertex();
       bufferBuilder.pos(vec3dArray[13].x, vec3dArray[13].y, vec3dArray[13].z)
          .tex(C.X + 0.125F, C.Y + 0.125F)
         .color(255, 255, 255, 255)
         .endVertex();
      tessellator.draw();
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      bufferBuilder.pos(vec3dArray[3].x, vec3dArray[3].y, vec3dArray[3].z)
         .tex(x.X, x.Y + 0.125F)
         .color(255, 255, 255, 255)
         .endVertex();
      bufferBuilder.pos(vec3dArray[4].x, vec3dArray[4].y, vec3dArray[4].z)
         .tex(x.X, x.Y)
         .color(255, 255, 255, 255)
         .endVertex();
      bufferBuilder.pos(vec3dArray[5].x, vec3dArray[5].y, vec3dArray[5].z)
         .tex(x.X + 0.125F, x.Y)
         .color(255, 255, 255, 255)
         .endVertex();
      bufferBuilder.pos(vec3dArray[6].x, vec3dArray[6].y, vec3dArray[6].z)
         .tex(x.X + 0.125F, x.Y + 0.125F)
         .color(255, 255, 255, 255)
         .endVertex();
      bufferBuilder.pos(vec3dArray[7].x, vec3dArray[7].y, vec3dArray[7].z)
         .tex(x.X, x.Y + 0.125F)
         .color(255, 255, 255, 255)
         .endVertex();
      bufferBuilder.pos(vec3dArray[8].x, vec3dArray[8].y, vec3dArray[8].z)
         .tex(x.X, x.Y)
         .color(255, 255, 255, 255)
         .endVertex();
      bufferBuilder.pos(vec3dArray[9].x, vec3dArray[9].y, vec3dArray[9].z)
         .tex(x.X + 0.125F, x.Y)
         .color(255, 255, 255, 255)
         .endVertex();
      bufferBuilder.pos(vec3dArray[10].x, vec3dArray[10].y, vec3dArray[10].z)
         .tex(x.X + 0.125F, x.Y + 0.125F)
         .color(255, 255, 255, 255)
         .endVertex();
      tessellator.draw();
   }

   protected void a(GeoModel model, BufferBuilder bufferBuilder, GalathNpc galath, float f, float f2, float f3, float f4, float f5) {
      GeoBone bone = (GeoBone)model.topLevelBones.get(0);
      GeoBone bone2 = null;
      GeoBone bone3 = null;
      GeoBone bone4 = null;
      GeoBone bone5 = null;

      for (GeoBone bone6 : bone.childBones) {
         String string = bone6.getName();
         byte bv = -1;

         label68: {
            label67: {
               label66: {
                  label65: {
                  switch (string.hashCode()) {
                     case 3029410:
                        break;
                     case 3059345:
                        break label65;
                     case 93911760:
                        break label66;
                     case 109761491:
                        if (!string.equals("steve")) {
                           break label68;
                        }
                        break label67;
                     default:
                        break label68;
                  }

                  if (string.equals("body")) {
                        bv = 1;
                     }
                     break label68;
                  }

                  if (string.equals("coin")) {
                     bv = 2;
                  }
                  break label68;
               }

               if (string.equals("body2")) {
                  bv = 3;
               }
               break label68;
            }

            bv = 0;
         }

         switch (bv) {
            case 0:
               bone4 = bone6;
               break;
            case 1:
               bone2 = bone6;
               break;
            case 2:
               bone3 = bone6;
               break;
            case 3:
               bone5 = bone6;
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
      this.a(bufferBuilder, bone3, galath, f5);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

      try {
         Minecraft.getMinecraft().renderEngine.bindTexture(this.getEntityTexture(this.RenderEntity));
      } catch (Exception error2) {
         error2.printStackTrace();
      }

      this.renderRecursively(bufferBuilder, bone4, f, f2, f3, this.RenderEntity.getScale());
      Tessellator.getInstance().draw();
      if (bone5 != null) {
         bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
         Minecraft.getMinecraft().renderEngine.bindTexture(ModelManglelie.SkinTexture);
         this.renderRecursively(bufferBuilder, bone5, f, f2, f3, this.RenderEntity.getScale());
         Tessellator.getInstance().draw();
      }

      MATRIX_STACK.pop();
   }

   @Override

   protected void applyBoneState(BufferBuilder bufferBuilder, String string, GeoBone bone) {
      switch (string) {
         case "hairBack": {
            if (Mc.isGamePaused()) break;
            IBone head = ((GirlEntity)this.RenderEntity).b().getBone("head");
            float f = AngleMath.radToDegrees(head.getRotationX());
            if (f < 0.0F) {
               bone.setRotationX(AngleMath.degToRadians(-f));
               break;
            }

            float f2 = Math.min(1.0F, f / 45.0F);
            bone.setRotationX(AngleMath.degToRadians(-f));
            bone.setPositionY(bone.getPositionY() + f2 * 1.5F);
            break;
         }
         case "hairDownSideL":
         case "hairDownSideR": {
            if (Mc.isGamePaused()) break;
            IBone head = ((GirlEntity)this.RenderEntity).b().getBone("head");
            float f = AngleMath.radToDegrees(head.getRotationX());
            if (f < 0.0F) {
               bone.setRotationX(AngleMath.degToRadians(-f / 2.0F));
               break;
            }

            float f3 = Math.min(1.0F, f / 45.0F);
            bone.setRotationX(AngleMath.degToRadians(-f));
            bone.setPositionY(bone.getPositionY() + f3);
            break;
         }
         case "head": {
            this.c(bone);
            GirlAnimationState girlAnimationState = ((GalathNpc)this.RenderEntity).getCurrentAction();
            EntityLivingBase target = null;
            if (girlAnimationState == GirlAnimationState.FLY || girlAnimationState == GirlAnimationState.ATTACK_SWORD) {
               target = ((GalathNpc)this.RenderEntity).getTargetEntity();
            }

            if (target == null) break;
            float f4 = Mc.getRenderPartialTicks();
            Vec3d vec3d = LerpMath.lerpVec3d(new Vec3d(((GalathNpc)this.RenderEntity).lastTickPosX, ((GalathNpc)this.RenderEntity).lastTickPosY, ((GalathNpc)this.RenderEntity).lastTickPosZ), ((GalathNpc)this.RenderEntity).getPositionVector(), (double)f4);
            Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(target.lastTickPosX, target.lastTickPosY, target.lastTickPosZ), ((GalathNpc)this.RenderEntity).getPositionVector(), (double)f4);
            Vec3d vec3d3 = vec3d.subtract(vec3d2);
            float f5 = (float)VectorMath.rotateYaw(vec3d3, (float)((GalathNpc)this.RenderEntity).renderYawOffset).z;
            float f6 = (float)Math.atan2(vec3d3.y, f5);
            break;
         }
         case "weapon": {
            if (!((GalathNpc)this.RenderEntity).ap) break;
            GlStateManager.pushMatrix();
            Tessellator.getInstance().draw();
            MatrixUtil.applyGeoBoneTransform(MATRIX_STACK, bone);
            GL11.glEnable(2896);
            GlStateManager.scale(1.5, 1.0, 2.0);
            GlStateManager.translate(0.0, 0.0, 0.05);
            GlStateManager.rotate(110.0F, 1.0F, 0.0F, 0.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItem(this.RenderEntity, new ItemStack(Items.IRON_SWORD), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
            GL11.glDisable(2896);
            GlStateManager.popMatrix();
            break;
         }
         case "tongue":
            this.e(bufferBuilder, bone);
            break;
         case "mangTongue":
            this.c(bufferBuilder, bone);
            break;
         case "head3":
            this.d(bone);
            break;
         case "irisL":
         case "irisR":
            this.a(bone);
            break;
         case "irsisFaceR2":
         case "irsisFaceR3":
            this.b(bone);
            break;
         case "armL":
         case "armR": {
            EntityLivingBase target = ((GalathNpc)this.RenderEntity).getTargetEntity();
            if (((GalathNpc)this.RenderEntity).getCurrentAction() != GirlAnimationState.RAPE_CHARGE || target == null) break;
            float f7 = (float)((GalathNpc)this.RenderEntity).renderYawOffset;
            Vec3d vec3d4 = target.getPositionVector().subtract(((GalathNpc)this.RenderEntity).getPositionVector());
            vec3d4 = VectorMath.rotateYaw(vec3d4, f7);
            double d = -MathUtils.clamp(vec3d4.x, -1.0, 1.0);
            bone.setRotationZ(bone.getRotationZ() + AngleMath.degToRadians((double)(45.0F * (float)d)));
         }
      }

      if (((GalathNpc)this.RenderEntity).hasMangleCompanion()) {
         ManglelieNpcRenderer.applyModelPartColor(this.RenderEntity, string, bone, true);
      }
   }

   void e(BufferBuilder bufferBuilder, GeoBone bone) {
      try {
         if (GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.PUSSY_LICKING, GirlAnimationState.MASTERBATE_SITTING)) {
            this.f(bufferBuilder, bone);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW)) {
            this.d(bufferBuilder, bone);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }


   void c(BufferBuilder bufferBuilder, GeoBone bone) {
        float f;
        block6: {
            try {
                try {
                    if (GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW) || ((GalathNpc)this.RenderEntity).aD) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathNpcRenderer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GalathNpcRenderer.rethrow(runtimeException);
            }
        }
        try {
            f = ((GalathNpc)this.RenderEntity).aD ? 1.0f - Math.min(0.29f, GirlAnimationState.getAnimationProgress(this.RenderEntity, Minecraft.getMinecraft().getRenderPartialTicks())) / 0.29f : 1.0f;
        }
        catch (RuntimeException runtimeException) {
            throw GalathNpcRenderer.rethrow(runtimeException);
        }
        float f2 = f;
        this.a(bufferBuilder, bone, f2);
        this.bindTexture(ModelManglelie.SkinTexture);
    }

   void d(GeoBone bone) {
      try {
         if (!GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW, GirlAnimationState.MORNING_BLOWJOB_FAST)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (Minecraft.getMinecraft().isGamePaused()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      float f = Minecraft.getMinecraft().player.ticksExisted + Minecraft.getMinecraft().getRenderPartialTicks();
      float f2 = (float)(Math.sin(f * 0.1F) * 0.1F) + 0.2F;
      float f3 = (float)Math.sin(f * 0.1F) * 0.1F;

      try {
         if (GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW)) {
            bone.setRotationY(bone.getRotationY() + f2);
            bone.setRotationZ(bone.getRotationZ() + f3);
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (!this.RenderEntity.aD) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      float f4 = 1.0F - Math.min(0.5F, GirlAnimationState.getAnimationProgress(this.RenderEntity, Minecraft.getMinecraft().getRenderPartialTicks())) / 0.5F;
      bone.setRotationY(bone.getRotationY() + f2 * f4);
      bone.setRotationZ(bone.getRotationZ() + f3 * f4);
   }

   void c(GeoBone bone) {
      try {
         if (!GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW, GirlAnimationState.MORNING_BLOWJOB_FAST)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (Minecraft.getMinecraft().isGamePaused()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      float f = Minecraft.getMinecraft().player.ticksExisted + Minecraft.getMinecraft().getRenderPartialTicks();
      float f2 = (float)Math.sin(f * -0.1F) * 0.1F;
      float f3 = (float)Math.sin(f * 0.1F) * 0.1F;

      try {
         if (GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW)) {
            bone.setRotationY(bone.getRotationY() + f2);
            bone.setRotationZ(bone.getRotationZ() + f3);
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (!this.RenderEntity.aD) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      float f4 = Math.min(0.5F, GirlAnimationState.getAnimationProgress(this.RenderEntity, Minecraft.getMinecraft().getRenderPartialTicks())) / 0.5F;
      bone.setRotationY(bone.getRotationY() + f2 * f4);
      bone.setRotationZ(bone.getRotationZ() + f3 * f4);
   }

   void a(GeoBone bone) {
      try {
         if (!GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (Minecraft.getMinecraft().isGamePaused()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      float f = Minecraft.getMinecraft().player.ticksExisted + Minecraft.getMinecraft().getRenderPartialTicks();
      bone.setPositionX((float)(bone.getPositionX() + Math.sin(f * 0.1F) * -0.1F));
   }

   void b(GeoBone bone) {
      try {
         if (!GirlAnimationState.isGirlInAnimation(this.RenderEntity, GirlAnimationState.MORNING_BLOWJOB_SLOW)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (Minecraft.getMinecraft().isGamePaused()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      float f = Minecraft.getMinecraft().player.ticksExisted + Minecraft.getMinecraft().getRenderPartialTicks();
      bone.setPositionX((float)(bone.getPositionX() + Math.sin(f * 0.1F) * -0.15F));
   }

   void a(BufferBuilder bufferBuilder, GeoBone bone, float f) {
      float f2 = GirlAnimationState.getAnimationNormalized01(this.RenderEntity, Minecraft.getMinecraft().getRenderPartialTicks());
      float f3 = f * (float)(0.02F * (-0.4F * Math.cos((Math.PI * 2) * f2 + 1.05) + 0.6F));
      QuadRenderHelper.QuadConfig quadConfig = new QuadRenderHelper.QuadConfig(
         H,
         0.0F,
         12,
         f3,
         (arg1, arg2) -> f * (float)(Math.cos((Math.PI * 2) * f2 + 0.35F + -0.2F * arg1) * -10.0),
         (arg1b, arg2b) -> 0.0F,
         (arg1c, arg2c) -> f * (float)(Math.cos((Math.PI * 2) * f2 + 1.25 + -0.1F * arg1c) * -5.0),
         0.03F,
         0.005F
      );
      this.a(bufferBuilder, bone, quadConfig);
   }

   void d(BufferBuilder bufferBuilder, GeoBone bone) {
      float f = GirlAnimationState.getAnimationNormalized01(this.RenderEntity, Minecraft.getMinecraft().getRenderPartialTicks());
      QuadRenderHelper.QuadConfig quadConfig = new QuadRenderHelper.QuadConfig(
         H,
         0.0F,
         12,
         0.02F,
         (arg1, arg2) -> (float)(Math.cos((Math.PI * 2) * f + -0.2F * arg1) * 15.0),
         (arg1b, arg2b) -> (float)(Math.cos((Math.PI * 2) * f + -0.2F * arg1b) * 5.0),
         (arg1c, arg2c) -> 0.0F,
         0.03F,
         0.005F
      );
      this.a(bufferBuilder, bone, quadConfig);
   }

   void f(BufferBuilder bufferBuilder, GeoBone bone) {
      float f = this.RenderEntity.float_b(Minecraft.getMinecraft().getRenderPartialTicks());

      try {
         if (f == 0.0F) {
            this.a(bufferBuilder, bone, G);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (f == 1.0F) {
            this.a(bufferBuilder, bone, t);
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      QuadRenderHelper.QuadConfig quadConfig = G.copy();
      quadConfig.SegmentLength = LerpMath.lerp(G.SegmentLength, 0.0F, f);
      quadConfig.RotationAngle = LerpMath.lerp(G.RotationAngle, 0.0F, f);
      this.a(bufferBuilder, bone, quadConfig);
   }

   void a(BufferBuilder bufferBuilder, GeoBone bone, QuadRenderHelper.QuadConfig quadConfig) {
      GlStateManager.pushMatrix();
      Tessellator.getInstance().draw();
      MatrixUtil.applyGeoBoneTransform(MATRIX_STACK, bone);
      GlStateManager.disableCull();
      this.bindTexture(LineTexture);
      QuadRenderHelper.drawQuad(bufferBuilder, Tessellator.getInstance(), Mc, quadConfig);
      this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      GlStateManager.enableCull();
      GlStateManager.popMatrix();
   }

   void a(BufferBuilder bufferBuilder, GeoBone bone, GalathNpc galath, float f) {
      try {
         if (galath.getCurrentAction() != GirlAnimationState.GIVE_COIN) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      n = bufferBuilder;
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      MATRIX_STACK.push();
      MATRIX_STACK.translate(bone);
      MATRIX_STACK.moveToPivot(bone);
      MATRIX_STACK.rotate(bone);
      MATRIX_STACK.scale(bone);
      MATRIX_STACK.moveBackFromPivot(bone);
      if (!this.ProcessedBones.contains(bone.getName())) {
         for (GeoCube geoCube : bone.childCubes) {
            MATRIX_STACK.push();
            GlStateManager.pushMatrix();
            this.CachedBone = bone;
            this.a(bufferBuilder, geoCube, 1.0F, 1.0F, 1.0F, 1.0F, (double)0.0);
            GlStateManager.popMatrix();
            MATRIX_STACK.pop();
         }
      }

      Tessellator.getInstance().draw();
      GeoBone bone2 = (GeoBone)bone.childBones.get(0);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      GL11.glDisable(2896);
      float f2 = MathUtils.clamp(GirlAnimationState.GIVE_COIN.ticksPlaying[1] + f, 105.0F, 125.0F);
      float f3 = (f2 - 105.0F) / 20.0F;
      float f4 = LerpMath.lerp(120.0F, 240.0F, f3);
      Vec3f vec3f = LerpMath.lerpVec3f(GeoModelDrawer.DimColor, GeoModelDrawer.GlowColor, f3);
      float f5 = OpenGlHelper.lastBrightnessX;
      float f6 = OpenGlHelper.lastBrightnessY;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f4, f4);
      MATRIX_STACK.push();
      MATRIX_STACK.translate(bone2);
      MATRIX_STACK.moveToPivot(bone2);
      MATRIX_STACK.rotate(bone2);
      MATRIX_STACK.scale(bone2);
      MATRIX_STACK.moveBackFromPivot(bone2);
      if (!this.ProcessedBones.contains(bone2.getName())) {
         for (GeoCube geoCube2 : bone2.childCubes) {
            MATRIX_STACK.push();
            GlStateManager.pushMatrix();
            this.CachedBone = bone2;
            this.a(bufferBuilder, geoCube2, vec3f.X, vec3f.Y, vec3f.Z, 1.0F, (double)0.0);
            GlStateManager.popMatrix();
            MATRIX_STACK.pop();
         }
      }

      MATRIX_STACK.pop();
      MATRIX_STACK.pop();
      Tessellator.getInstance().draw();
      GL11.glEnable(2896);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f5, f6);
   }

   protected Vec3d a(GalathNpc galath, float f, Vec3d vec3d) {
      if (galath.getCurrentAction() == GirlAnimationState.RUN) {
         float f2 = galath.I();
         galath.rotationYaw = f2;
         galath.prevRenderYawOffset = f2;
         galath.renderYawOffset = f2;
         galath.prevRotationYawHead = f2;
         galath.rotationYawHead = f2;
      }

      return vec3d;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   private static IllegalStateException rethrow(IllegalStateException error) {
      return error;
   }

   private static IOException rethrow(IOException error) {
      return error;
   }
}
