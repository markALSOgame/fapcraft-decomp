package com.trolmastercard.sexmod;

import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class ModelManglelie extends GirlGeoModel {
   public static final float h = 7.0F;
   public static final float k = 0.75F;
   static final float l = AngleMath.degToRadians(140.0F);
   static final float m = AngleMath.degToRadians(35.0F);
   static final float i = 90.0F;
   static final float g = AngleMath.degToRadians(45.0F);
   static final float f = AngleMath.degToRadians(-45.0F);
   public static final ResourceLocation SkinTexture = new ResourceLocation("sexmod", "textures/entity/manglelie/manglelie.png");

   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{
         new ResourceLocation("sexmod", "geo/manglelie/manglelie.geo.json"),
         new ResourceLocation("sexmod", "geo/manglelie/manglelie.geo.json"),
         new ResourceLocation("sexmod", "geo/galath/galath_con_mang.geo.json")
      };
   }

   @Override
   public ResourceLocation a(GirlEntity girl) {
      try {
         if (girl.world instanceof PreviewWorld) {
            return this.TextureLayers[0];
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (isInThreesomeAnimation(girl)) {
            return this.TextureLayers[2];
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return this.TextureLayers[girl.getDataManager().get(GirlEntity.OutfitIndexKey)];
   }

   public static boolean isInThreesomeAnimation(GirlEntity girl) {
      return GirlAnimationState.isGirlInAnimation(girl, GirlAnimationState.THREESOME_SLOW, GirlAnimationState.THREESOME_FAST, GirlAnimationState.THREESOME_CUM);
   }

   @Override
   public ResourceLocation b() {
      return SkinTexture;
   }

   @Override
   public ResourceLocation b(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/manglelie/manglelie.animation.json");
   }

   @Override
   public void onAnimationEvent(GirlEntity girl, Integer i, AnimationEvent animEvent) {
      super.onAnimationEvent(girl, i, animEvent);
      applyAnimationTransforms(girl, this.getAnimationProcessor(), animEvent.getPartialTick());
      this.void_b(girl);
      this.void_d(girl);
      this.a(girl);
      this.void_e(girl);
   }

   void void_e(GirlEntity girl) {
      try {
         if (this.Mc.isGamePaused()) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (isInThreesomeAnimation(girl)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      GalathNpc galath = ManglelieNpc.getMommy(girl, false);

      try {
         if (galath == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (!GirlAnimationState.isAnimationInList(galath.getCurrentAction(), GirlAnimationState.CORRUPT_CUM, GirlAnimationState.CARRY_FAST, GirlAnimationState.CORRUPT_INTRO, GirlAnimationState.CORRUPT_SLOW)) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      AnimationProcessor animationProcessor = this.getAnimationProcessor();
      IBone iBone = animationProcessor.getBone("legR");
      iBone.setRotationY(iBone.getRotationY() + f);
      IBone iBone2 = animationProcessor.getBone("lowerArmR");
      IBone iBone3 = animationProcessor.getBone("lowerArmL");
      iBone2.setRotationX(iBone2.getRotationX() + f);
      iBone3.setRotationX(iBone3.getRotationX() + f);
   }

   void a(GirlEntity girl) {
      try {
         if (!(girl instanceof ManglelieNpc)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (isInThreesomeAnimation(girl)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      ManglelieNpc manglelie = (ManglelieNpc)girl;
      GalathNpc galath = manglelie.getMommy(false);

      try {
         if (galath == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      IBone iBone = this.getAnimationProcessor().getBone("body");

      IBone iBone2;
      float f;
      float f2;
      label32: {
         try {
            iBone2 = iBone;
            f = galath.bw;
            if (this.Mc.isGamePaused()) {
               f2 = 0.0F;
               break label32;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         f2 = iBone.getRotationY();
      }

      iBone2.setRotationY(f + f2);
      iBone.setScaleX(galath.bm);
      iBone.setScaleY(galath.bm);
      iBone.setScaleZ(galath.bm);
   }

   Vec3d a(@Nonnull Entity entity) {
      return VecMath.getPositionOffset(entity, this.Mc.getRenderPartialTicks()).add(0.0, entity.getEyeHeight(), 0.0);
   }


   void void_d(GirlEntity girl) {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (isInThreesomeAnimation(girl)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (this.Mc.isGamePaused()) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      ManglelieNpc manglelie = (ManglelieNpc)girl;

      try {
         if (!manglelie.isClaimed()) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      GalathNpc galath = manglelie.getMommy(false);

      try {
         if (galath == null) {
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      AnimationProcessor animationProcessor = this.getAnimationProcessor();
      IBone armL = animationProcessor.getBone("armL");
      IBone armR = animationProcessor.getBone("armR");
      IBone lowerArmL = animationProcessor.getBone("lowerArmL");
      IBone lowerArmR = animationProcessor.getBone("lowerArmR");
      IBone elbowR = animationProcessor.getBone("elbowR");
      IBone elbowL = animationProcessor.getBone("elbowL");
      Entity entity = manglelie.getTargetEntity();
      boolean flag = entity == null;

      try {
         if (!flag) {
            manglelie.R = this.a(entity);
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      float fps = Minecraft.getDebugFPS();
      if (fps == 0.0F) {
         fps = 1.0F;
      }

      try {
         if (manglelie.aj != flag) {
            manglelie.V += 1.5F / fps;
         } else {
            manglelie.V = 0.0F;
         }

         if (manglelie.V >= 1.0F) {
            manglelie.V = 0.0F;
            manglelie.aj = flag;
         }
      } catch (RuntimeException error7) {
         throw rethrow(error7);
      }

      a pose;
      if (manglelie.V != 0.0F) {
         pose = a.a(
            this.a(galath, armR, armL, lowerArmL, lowerArmR),
            this.a(manglelie, galath, lowerArmR, lowerArmL, animationProcessor),
            (float)(manglelie.aj ? LerpMath.EaseOutBack(manglelie.V) : 1.0 - LerpMath.EaseOutBack(manglelie.V))
         );
      } else {
         try {
            if (flag) {
               pose = this.a(galath, armR, armL, lowerArmL, lowerArmR);
            } else {
               pose = this.a(manglelie, galath, lowerArmR, lowerArmL, animationProcessor);
            }
         } catch (RuntimeException error8) {
            throw rethrow(error8);
         }
      }

      armR.setRotationX(pose.c.X);
      armR.setRotationY(pose.c.Y);
      armR.setRotationZ(pose.c.Z);
      armL.setRotationX(pose.g.X);
      armL.setRotationY(pose.g.Y);
      armL.setRotationZ(pose.g.Z);
      lowerArmL.setRotationX(pose.b.X);
      lowerArmL.setRotationY(pose.b.Y);
      lowerArmL.setRotationZ(pose.b.Z);
      lowerArmR.setRotationX(pose.h.X);
      lowerArmR.setRotationY(pose.h.Y);
      lowerArmR.setRotationZ(pose.h.Z);
      armL.setScaleY(pose.a);
      armR.setScaleY(pose.f);
      elbowR.setRotationY(pose.d);
      elbowL.setRotationY(pose.e);
   }


   a a(@Nonnull ManglelieNpc manglelie, @Nonnull GalathNpc galath, IBone iBone, IBone iBone2, AnimationProcessor animationProcessor) {
      a gui = new a();
      gui.b = new Vec3f(m, 0.0F, iBone.getRotationZ());
      gui.h = new Vec3f(l, 0.0F, iBone2.getRotationZ());
      float f = galath.aE + animationProcessor.getBone("upperBody").getRotationX();
      float f2 = this.Mc.getRenderPartialTicks();
      Vec3d vec3d = ManglelieNpcRenderer.a(galath, f2);
      Vec3d vec3d2 = manglelie.getModelBone("armR").add(vec3d);
      Vec3d vec3d3 = manglelie.getModelBone("armL").add(vec3d);
      Vec2f vec2f = MathUtils.rotationBetween(vec3d2, manglelie.R);
      Vec2f vec2f2 = MathUtils.rotationBetween(vec3d3, manglelie.R);
      Float f3 = GalathNpc.rotateToTarget(galath, f2);

      float i2;
      try {
         i2 = f3 == null ? LerpMath.lerpAngleDegrees(galath.prevRotationYawHead, galath.rotationYawHead, (double)f2) : f3.floatValue();
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      float f4 = AngleMath.degToRadians(i2);
      float f5 = manglelie.b(f2);
      float f6 = (float)LerpMath.EaseOutQuart(Math.min(1.0F, f5));
      float f7;
      if (f6 != 1.0F) {
         f7 = 0.0F;
      } else {
         f7 = (f5 * 28.0F - 28.0F) / 32.0F;
         f7 = Math.max(0.0F, f7 - 0.5F) * 2.0F;
      }

      float f8 = (float)LerpMath.EaseInOutSine(f7);
      float f9 = AngleMath.degToRadians(LerpMath.lerp(0.0F, 90.0F, f6));

      try {
         if (manglelie.boolean_a(manglelie.R, f2)) {
            gui.c = new Vec3f(-f + vec2f.X + AngleMath.degToRadians(90.0F), vec2f.Y, 0.0F);
            gui.g = new Vec3f(
               -f + vec2f2.X + AngleMath.degToRadians(90.0F),
               (float)((double)vec2f2.Y + (double)AngleMath.degToRadians(-20.0F) * Math.cos(vec2f.Y + f4 * 1.0F) + (double)LerpMath.lerp(f9 / 2.0F, 0.0F, f8)),
               0.0F
            );
            gui.a = 1.0F + Math.abs(Math.abs(vec2f.Y) - Math.abs(f4)) * 0.1909F;
            gui.e = AngleMath.degToRadians(90.0F);
            gui.b.Z = LerpMath.lerp(f9, 0.0F, f8);
            if ((double)f7 > 0.5) {
               gui.b.X = m + (float)LerpMath.lerp((double)g, 0.0, LerpMath.EaseInOutSine((f7 - 0.5F) * 2.0F));
            } else if (f7 != 0.0F && (double)f7 < 0.5) {
               gui.b.X = m + (float)LerpMath.lerp(0.0, (double)g, LerpMath.EaseInOutSine(f7 * 2.0F));
            }
         } else {
            gui.g = new Vec3f(-f + vec2f2.X + AngleMath.degToRadians(90.0F), vec2f2.Y, 0.0F);
            gui.c = new Vec3f(
               -f + vec2f.X + AngleMath.degToRadians(90.0F),
               (float)((double)vec2f.Y + (double)AngleMath.degToRadians(20.0F) * Math.cos(vec2f2.Y + f4 * 1.0F)) - LerpMath.lerp(f9 / 2.0F, 0.0F, f8),
               0.0F
            );
            gui.f = 1.0F + Math.abs(Math.abs(vec2f2.Y) - Math.abs(f4)) * 0.1909F;
            gui.d = AngleMath.degToRadians(90.0F);
            gui.h.Z = -LerpMath.lerp(f9, 0.0F, f8);
            if ((double)f7 > 0.5) {
               gui.h.X = l + (float)LerpMath.lerp((double)g, 0.0, LerpMath.EaseInOutSine((f7 - 0.5F) * 2.0F));
            } else if (f7 != 0.0F && (double)f7 < 0.5) {
               gui.h.X = l + (float)LerpMath.lerp(0.0, (double)g, LerpMath.EaseInOutSine(f7 * 2.0F));
            }
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      gui.c.Y += f4;
      gui.g.Y += f4;
      return gui;
   }

   a a(GalathNpc galath, IBone iBone, IBone iBone2, IBone iBone3, IBone iBone4) {
      float f = galath.aE;
      a mc = new a();

      try {
         if (f > 0.0F) {
            mc.c = new Vec3f(iBone.getRotationX() - f, iBone.getRotationY() - f * -25.0F / 45.0F, iBone.getRotationZ() + f * 12.5F / 45.0F);
            mc.g = new Vec3f(iBone2.getRotationX() - f, iBone2.getRotationY() + f * 15.0F / 45.0F, iBone2.getRotationZ());
            mc.b = new Vec3f(iBone3.getRotationX(), iBone3.getRotationY(), iBone3.getRotationZ());
            mc.h = new Vec3f(iBone4.getRotationX(), iBone4.getRotationY(), iBone4.getRotationZ());
            return mc;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      mc.h = new Vec3f(iBone4.getRotationX() + 2.0F * f, iBone4.getRotationY(), iBone4.getRotationZ());
      mc.b = new Vec3f(iBone3.getRotationX() + 2.2222223F * f, iBone3.getRotationY(), iBone3.getRotationZ());
      mc.c = new Vec3f(iBone.getRotationX() - f, iBone.getRotationY(), iBone.getRotationZ() + f * 5.0F / 45.0F);
      mc.g = new Vec3f(iBone2.getRotationX() - f, iBone2.getRotationY(), iBone2.getRotationZ() - f * 5.0F / 45.0F);
      return mc;
   }

   void void_b(GirlEntity girl) {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (this.Mc.isGamePaused()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      ManglelieNpc manglelie = (ManglelieNpc)girl;

      try {
         if (!ManglelieNpcRenderer.isManglelieNpc(manglelie)) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      GalathNpc galath = manglelie.getMommy(false);

      try {
         if (galath == null) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      AnimationProcessor animationProcessor = this.getAnimationProcessor();
      float f = galath.aE;
      animationProcessor.getBone("rotationTool").setRotationX(f);
      IBone iBone = animationProcessor.getBone("head");
      IBone iBone2 = animationProcessor.getBone("upperBody");
      IBone iBone3 = animationProcessor.getBone("boobs");

      label87: {
         try {
            if (f > 0.0F) {
               iBone2.setRotationX(-1.1111112F * f);
               iBone.setRotationX(0.1333F * f);
               iBone3.setRotationX(f * 22.5F / 45.0F);
               break label87;
            }
         } catch (RuntimeException error5) {
            throw rethrow(error5);
         }

         iBone2.setRotationX(-1.6666666F * f);
         iBone.setRotationX(f * 0.666F);
      }

      float f2 = MathUtils.angleDifference(manglelie.T, manglelie.VerticalImpulse);
      float f3 = MathUtils.angleDifference(manglelie.ai, manglelie.W);
      float f4 = Minecraft.getDebugFPS();
      if (f4 == 0.0F) {
         f4 = 1.0F;
      }

      float f5;
      float f6;
      label112: {
         try {
            f5 = 7.0F;
            if (Math.abs(f2) < 7.0F) {
               f6 = f2;
               break label112;
            }
         } catch (RuntimeException error6) {
            throw rethrow(error6);
         }

         try {
            if (f2 > 0.0F) {
               f6 = 7.0F;
               break label112;
            }
         } catch (RuntimeException error7) {
            throw rethrow(error7);
         }

         f6 = -7.0F;
      }

      float f7 = f5 * f6 * (1.0F / f4);

      label113: {
         try {
            f5 = 7.0F;
            if (Math.abs(f3) < 7.0F) {
               f6 = f3;
               break label113;
            }
         } catch (RuntimeException error8) {
            throw rethrow(error8);
         }

         try {
            if (f3 > 0.0F) {
               f6 = 7.0F;
               break label113;
            }
         } catch (RuntimeException error9) {
            throw rethrow(error9);
         }

         f6 = -7.0F;
      }

      float f8 = f5 * f6 * (1.0F / f4);
      float f9 = manglelie.T + f7;
      float f10 = manglelie.ai + f8;
      iBone.setRotationY(iBone.getRotationY() + f9);
      iBone.setRotationX(iBone.getRotationX() + f10);
      manglelie.T = f9;
      manglelie.ai = f10;
   }

   public static void applyAnimationTransforms(GirlEntity girl, AnimationProcessor animationProcessor, float f2) {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      boolean flag = ManglelieNpcRenderer.isInThreesomeAnimation(girl);
      e(animationProcessor, flag);
      f(animationProcessor, flag);
      b(girl, animationProcessor, f2);
   }


   static void b(GirlEntity girl, AnimationProcessor animationProcessor, float f) {
        try {
            if (!(girl instanceof ManglelieNpc)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelManglelie.rethrow(runtimeException);
        }
        for (int i = 0; i < 3; ++i) {
            boolean flag;
            IBone iBone;
            block9: {
                block8: {
                    IBone iBone2 = animationProcessor.getBone("cockStage" + i);
                    try {
                        try {
                            if (iBone2 == null) continue;
                            iBone = iBone2;
                            if (i <= ((ManglelieNpc)girl).an) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelManglelie.rethrow(runtimeException);
                        }
                        flag = true;
                        break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ModelManglelie.rethrow(runtimeException);
                    }
                }
                flag = false;
            }
            iBone.setHidden(flag);
        }
    }

   static void f(AnimationProcessor animationProcessor, boolean flag) {
      IBone iBone;
      boolean flag2;
      label16: {
         try {
            iBone = animationProcessor.getBone("skirt");
            if (!flag) {
               flag2 = true;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag2 = false;
      }

      iBone.setHidden(flag2);
   }

   static void e(AnimationProcessor animationProcessor, boolean flag) {
      IBone iBone;
      boolean flag2;
      label28: {
         try {
            animationProcessor.getBone("cheekRBelowSkirt").setHidden(flag);
            animationProcessor.getBone("cheekLBelowSkirt").setHidden(flag);
            animationProcessor.getBone("sideRNoSkirt").setHidden(flag);
            iBone = animationProcessor.getBone("sideRSkirt");
            if (!flag) {
               flag2 = true;
               break label28;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag2 = false;
      }

      label21: {
         try {
            iBone.setHidden(flag2);
            animationProcessor.getBone("sideLNoSkirt").setHidden(flag);
            iBone = animationProcessor.getBone("sideLSkirt");
            if (!flag) {
               flag2 = true;
               break label21;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         flag2 = false;
      }

      iBone.setHidden(flag2);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   private static class a {
      private Vec3f c;
      private Vec3f g;
      private Vec3f h;
      private Vec3f b;
      private float f = 1.0F;
      private float a = 1.0F;
      private float e = 0.0F;
      private float d = 0.0F;

      private a() {
      }

      static a a(a mc, a mc2, float f2) {
         a mc3 = new a();
         mc3.c = LerpMath.lerpVec3f(mc.c, mc2.c, (double)f2);
         mc3.g = LerpMath.lerpVec3f(mc.g, mc2.g, (double)f2);
         mc3.h = LerpMath.lerpVec3f(mc.h, mc2.h, (double)f2);
         mc3.b = LerpMath.lerpVec3f(mc.b, mc2.b, (double)f2);
         mc3.f = LerpMath.lerp(mc.f, mc2.f, f2);
         mc3.a = LerpMath.lerp(mc.a, mc2.a, f2);
         mc3.e = LerpMath.lerp(mc.e, mc2.e, f2);
         mc3.d = LerpMath.lerp(mc.d, mc2.d, f2);
         return mc3;
      }
   }
}
