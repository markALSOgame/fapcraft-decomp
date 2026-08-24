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
      this.b(girl);
      this.d(girl);
      this.a(girl);
      this.e(girl);
   }

   void e(GirlEntity girl) {
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


   void d(GirlEntity girl) {
        a a10;
        IBone iBone;
        IBone iBone2;
        IBone iBone3;
        IBone iBone4;
        IBone iBone5;
        IBone iBone6;
        block37: {
            double d;
            a a11;
            a a12;
            AnimationProcessor animationProcessor;
            GalathNpc f_2;
            ManglelieNpc manglelie;
            block34: {
                a a13;
                block36: {
                    block35: {
                        boolean flag;
                        block33: {
                            float f;
                            block32: {
                                boolean flag2;
                                try {
                                    if (ClientProxy.IS_PRELOADING) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                                try {
                                    if (ModelManglelie.isInThreesomeAnimation(girl)) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                                try {
                                    if (this.Mc.isGamePaused()) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                                manglelie = (ManglelieNpc)girl;
                                try {
                                    if (!manglelie.isClaimed()) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                                f_2 = manglelie.getMommy(false);
                                try {
                                    if (f_2 == null) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                                animationProcessor = this.getAnimationProcessor();
                                iBone6 = animationProcessor.getBone("armL");
                                iBone5 = animationProcessor.getBone("armR");
                                iBone4 = animationProcessor.getBone("lowerArmL");
                                iBone3 = animationProcessor.getBone("lowerArmR");
                                iBone2 = animationProcessor.getBone("elbowR");
                                iBone = animationProcessor.getBone("elbowL");
                                Entity entity = manglelie.getTargetEntity();
                                try {
                                    flag2 = entity == null;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                                flag = flag2;
                                try {
                                    if (!flag) {
                                        manglelie.R = this.a(entity);
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                                f = Minecraft.getDebugFPS();
                                if (f == 0.0f) {
                                    f = 1.0f;
                                }
                                try {
                                    if (manglelie.aj != flag) break block32;
                                    manglelie.V = 0.0f;
                                    break block33;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelManglelie.rethrow(runtimeException);
                                }
                            }
                            manglelie.V += 1.5f / f;
                        }
                        try {
                            if (manglelie.V >= 1.0f) {
                                manglelie.V = 0.0f;
                                manglelie.aj = flag;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelManglelie.rethrow(runtimeException);
                        }
                        try {
                            try {
                                if (manglelie.V != 0.0f) break block34;
                                if (!flag) break block35;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ModelManglelie.rethrow(runtimeException);
                            }
                            i = this.a(f_2, iBone5, iBone6, iBone4, iBone3);
                            break block36;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelManglelie.rethrow(runtimeException);
                        }
                    }
                    i = this.a(manglelie, f_2, iBone3, iBone4, animationProcessor);
                }
                gui = i;
                break block37;
            }
            try {
                a12 = this.a(f_2, iBone5, iBone6, iBone4, iBone3);
                a11 = this.a(manglelie, f_2, iBone3, iBone4, animationProcessor);
                d = manglelie.aj ? LerpMath.EaseOutBack(manglelie.V) : 1.0 - LerpMath.EaseOutBack(manglelie.V);
            }
            catch (RuntimeException runtimeException) {
                throw ModelManglelie.rethrow(runtimeException);
            }
            gui = ModelManglelie.a.a(a12, a11, (float)d);
        }
        iBone5.setRotationX(((a)gui).c.a);
        iBone5.setRotationY(((a)gui).c.c);
        iBone5.setRotationZ(((a)gui).c.b);
        iBone6.setRotationX(((a)gui).g.EventHandler);
        iBone6.setRotationY(((a)gui).g.Navigation);
        iBone6.setRotationZ(((a)gui).g.FollowDistance);
        iBone4.setRotationX(((a)gui).b.Mc);
        iBone4.setRotationY(((a)gui).b.PreviewEntity);
        iBone4.setRotationZ(((a)gui).b.WhitelistEntry);
        iBone3.setRotationX(((a)gui).h.Player);
        iBone3.setRotationY(((a)gui).h.Navigation);
        iBone3.setRotationZ(((a)gui).h.FollowDistance);
        iBone6.setScaleY(gui.a);
        iBone5.setScaleY(gui.MagicSlotsDirty);
        iBone2.setRotationY(gui.SelectedSlot);
        iBone.setRotationY(gui.e);
    }


   ModelManglelie.Mc a(@Nonnull ManglelieNpc manglelie, @Nonnull GalathNpc galath, IBone iBone, IBone iBone2, AnimationProcessor animationProcessor) {
        block20: {
            block21: {
                block18: {
                    block19: {
                        gui = new a();
                        ModelManglelie.a.access$202(gui, new Vec3f(ModelManglelie.m, 0.0f, iBone.getRotationZ()));
                        ModelManglelie.a.access$302(gui, new Vec3f(ModelManglelie.l, 0.0f, iBone2.getRotationZ()));
                        string = galath.aE + animationProcessor.getBone("upperBody").getRotationX();
                        f = this.Mc.getRenderPartialTicks();
                        vec3d = ManglelieNpcRenderer.a(galath, f);
                        girl = manglelie.b("armR").add(vec3d);
                        girl2 = manglelie.b("armL").add(vec3d);
                        vec2f = MathUtils.rotationBetween(girl, manglelie.R);
                        vec2f2 = MathUtils.rotationBetween(girl2, manglelie.R);
                        manglelie2 = GalathNpc.rotateToTarget(galath, f);
                        try {
                            i = manglelie2 == null ? LerpMath.lerpAngleDegrees(galath.prevRotationYawHead, galath.rotationYawHead, (double)f) : manglelie2.floatValue();
                        }
                        catch (RuntimeException error) {
                            throw ModelManglelie.rethrow(error);
                        }
                        i2 = i;
                        var16_16 = AngleMath.degToRadians(i2);
                        girl3 = manglelie.b(f);
                        f2 = (float)LerpMath.EaseOutQuart(Math.min(1.0f, girl3));
                        if (f2 != 1.0f) {
                            f3 = 0.0f;
                        } else {
                            f3 = (girl3 * 28.0f - 28.0f) / 32.0f;
                            f3 = Math.max(0.0f, f3 - 0.5f) * 2.0f;
                        }
                        f4 = (float)LerpMath.EaseInOutSine(f3);
                        f5 = AngleMath.degToRadians(LerpMath.a(0.0f, 90.0f, f2));
                        vec3d2 = manglelie.a(manglelie.R, f);
                        try {
                            try {
                                if (!vec3d2) break block18;
                                ModelManglelie.a.access$002(gui, new Vec3f(-string + vec2f.a + AngleMath.degToRadians(90.0f), vec2f.c, 0.0f));
                                ModelManglelie.a.access$102(gui, new Vec3f(-string + vec2f2.a + AngleMath.degToRadians(90.0f), (float)((double)vec2f2.c + (double)AngleMath.degToRadians(-20.0f) * Math.cos(vec2f.c + var16_16 * 1.0f) + (double)LerpMath.a(f5 / 2.0f, 0.0f, f4)), 0.0f));
                                ModelManglelie.a.access$402(gui, 1.0f + Math.abs(Math.abs(vec2f.c) - Math.abs(var16_16)) * 0.1909f);
                                ModelManglelie.a.access$702(gui, AngleMath.degToRadians(90.0f));
                                ModelManglelie.a.access$200((a)gui).b = LerpMath.a(f5, 0.0f, f4);
                                if (!((double)f3 > 0.5)) break block19;
                            }
                            catch (RuntimeException error2) {
                                throw ModelManglelie.rethrow(error2);
                            }
                            ModelManglelie.a.access$200((a)gui).a = ModelManglelie.m + (float)LerpMath.lerp((double)ModelManglelie.g, 0.0, LerpMath.EaseInOutSine((f3 - 0.5f) * 2.0f));
                            break block20;
                        }
                        catch (RuntimeException error3) {
                            throw ModelManglelie.rethrow(error3);
                        }
                    }
                    try {
                        try {
                            if (f3 == 0.0f || !((double)f3 < 0.5)) ** GOTO lbl88
                        }
                        catch (RuntimeException error4) {
                            throw ModelManglelie.rethrow(error4);
                        }
                        ModelManglelie.a.access$200((a)gui).a = ModelManglelie.m + (float)LerpMath.lerp(0.0, (double)ModelManglelie.g, LerpMath.EaseInOutSine(f3 * 2.0f));
                    }
                    catch (RuntimeException error5) {
                        throw ModelManglelie.rethrow(error5);
                    }
                }
                try {
                    ModelManglelie.a.access$102(gui, new Vec3f(-string + vec2f2.a + AngleMath.degToRadians(90.0f), vec2f2.c, 0.0f));
                    ModelManglelie.a.access$002(gui, new Vec3f(-string + vec2f.a + AngleMath.degToRadians(90.0f), (float)((double)vec2f.c + (double)AngleMath.degToRadians(20.0f) * Math.cos(vec2f2.c + var16_16 * 1.0f)) - LerpMath.a(f5 / 2.0f, 0.0f, f4), 0.0f));
                    ModelManglelie.a.access$502(gui, 1.0f + Math.abs(Math.abs(vec2f2.c) - Math.abs(var16_16)) * 0.1909f);
                    ModelManglelie.a.access$602(gui, AngleMath.degToRadians(90.0f));
                    ModelManglelie.a.access$300((a)gui).b = -LerpMath.a(f5, 0.0f, f4);
                    if (!((double)f3 > 0.5)) break block21;
                    ModelManglelie.a.access$300((a)gui).a = ModelManglelie.l + (float)LerpMath.lerp((double)ModelManglelie.g, 0.0, LerpMath.EaseInOutSine((f3 - 0.5f) * 2.0f));
                    break block20;
                }
                catch (RuntimeException error6) {
                    throw ModelManglelie.rethrow(error6);
                }
            }
            try {
                try {
                    if (f3 == 0.0f || !((double)f3 < 0.5)) break block20;
                }
                catch (RuntimeException error7) {
                    throw ModelManglelie.rethrow(error7);
                }
                ModelManglelie.a.access$300((a)gui).a = ModelManglelie.l + (float)LerpMath.lerp(0.0, (double)ModelManglelie.g, LerpMath.EaseInOutSine(f3 * 2.0f));
            }
            catch (RuntimeException error8) {
                throw ModelManglelie.rethrow(error8);
            }
        }
        ModelManglelie.a.access$000((a)gui).c += var16_16;
        ModelManglelie.a.access$100((a)gui).c += var16_16;
        return gui;
    }

   ModelManglelie.Mc a(GalathNpc galath, IBone iBone, IBone iBone2, IBone iBone3, IBone iBone4) {
      float f = galath.aE;
      ModelManglelie.Mc mc = new ModelManglelie.a();

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

   void b(GirlEntity girl) {
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

      static ModelManglelie.Mc a(ModelManglelie.Mc mc, ModelManglelie.Mc mc2, float f2) {
         ModelManglelie.Mc mc3 = new ModelManglelie.a();
         mc3.c = LerpMath.a(mc.c, mc2.c, f2);
         mc3.g = LerpMath.a(mc.g, mc2.g, f2);
         mc3.h = LerpMath.lerp(mc.h, mc2.h, f2);
         mc3.b = LerpMath.lerp(mc.b, mc2.b, f2);
         mc3.f = LerpMath.a(mc.f, mc2.f, f2);
         mc3.a = LerpMath.a(mc.a, mc2.a, f2);
         mc3.e = LerpMath.lerp(mc.e, mc2.e, f2);
         mc3.d = LerpMath.lerp(mc.d, mc2.d, f2);
         return mc3;
      }
   }
}
