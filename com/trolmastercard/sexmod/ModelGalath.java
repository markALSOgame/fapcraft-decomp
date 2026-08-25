package com.trolmastercard.sexmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class ModelGalath extends GirlGeoModel {
   public static ResourceLocation GalathTexture = new ResourceLocation("sexmod", "textures/entity/galath/galath.png");
   float LickPulse = 0.0F;
   long AttackStartTick = -1L;
   long AttackEndTick = -1L;

   public ModelGalath() {
      this.TextureLayers = this.a();
   }

   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{
         new ResourceLocation("sexmod", "geo/galath/galath.geo.json"),
         new ResourceLocation("sexmod", "geo/galath/galath.geo.json"),
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
         if (((BoxSource)girl).b()) {
            return this.TextureLayers[2];
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return this.TextureLayers[girl.getDataManager().get(GirlEntity.OutfitIndexKey)];
   }

   @Override
   public ResourceLocation b() {
      return GalathTexture;
   }

   @Override
   public ResourceLocation b(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/galath/galath.animation.json");
   }

   @Override
   protected boolean e(GirlEntity girl) {
      try {
         if (!(girl instanceof GalathNpc)) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GalathNpc galath = (GalathNpc)girl;

      try {
         if (galath.k()) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         return galath.getTargetEntity() == null;
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }

   @Override
   public void a(GirlEntity girl, Integer i, AnimationEvent animEvent) {
      try {
         this.applyMasturbationAngles(girl);
         super.a(girl, i, animEvent);
         this.a(girl);
         this.applyRapeChargePose(girl);
         this.applySwordAttackPose(girl);
         this.b(girl);
         this.e(girl);
         this.updateWingsVisibility(girl);
         this.hideCoinBone(girl);
         this.a();
         this.syncBodyPose(girl);
         this.animatePussyLicking(girl);
         this.applyHugPose(girl);
         if (!(girl instanceof GalathNpc)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GalathNpc galath = (GalathNpc)girl;

      try {
         galath.aE = this.getAnimationProcessor().getBone("head").getRotationX();
         if (galath.b()) {
            ModelManglelie.applyAnimationTransforms(galath, this.getAnimationProcessor(), animEvent.getPartialTick());
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }


   void animatePussyLicking(GirlEntity girl) {
        float f;
        block23: {
            float f2;
            block21: {
                try {
                    if (!GirlAnimationState.isGirlInAnimation(girl, GirlAnimationState.PUSSY_LICKING)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGalath.rethrow(runtimeException);
                }
                try {
                    if (!(girl instanceof GalathNpc)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGalath.rethrow(runtimeException);
                }
                try {
                    if (this.Mc.isGamePaused()) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGalath.rethrow(runtimeException);
                }
                AnimationProcessor animationProcessor = this.getAnimationProcessor();
                IBone iBone = animationProcessor.getBone("head");
                f2 = this.Mc.getRenderPartialTicks() + (float)this.Mc.player.ticksExisted;
                Vec3f vec3f = this.getHeadSwayOffset((GalathNpc)girl, f2);
                try {
                    try {
                        iBone.setRotationX(iBone.getRotationX() + vec3f.X);
                        iBone.setRotationY(iBone.getRotationY() + vec3f.Y);
                        iBone.setRotationZ(iBone.getRotationZ() + vec3f.Z);
                        if (girl.getCurrentAction() == GirlAnimationState.PUSSY_LICKING && !((GalathNpc)girl).a5) break block21;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ModelGalath.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGalath.rethrow(runtimeException);
                }
            }
            f = (float)(Math.sin(f2 * 0.3f) * 10.0);
            try {
                block22: {
                    try {
                        try {
                            try {
                                if (f > 0.0f && this.LickPulse < 0.0f) break block22;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ModelGalath.rethrow(runtimeException);
                            }
                            if (!(f < 0.0f)) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelGalath.rethrow(runtimeException);
                        }
                        if (!(this.LickPulse > 0.0f)) break block23;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ModelGalath.rethrow(runtimeException);
                    }
                }
                girl.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_LIPSOUND));
            }
            catch (RuntimeException runtimeException) {
                throw ModelGalath.rethrow(runtimeException);
            }
        }
        this.LickPulse = f;
    }

   Vec3f getHeadSwayOffset(GalathNpc galath, float f) {
      return LerpMath.lerpVec3f(this.computeHeadSway(f), Vec3f.ZERO, galath.b(this.Mc.getRenderPartialTicks()));
   }

   Vec3f computeHeadSway(float f) {
      return new Vec3f((float)Math.sin(f * 0.3F) * AngleMath.degToRadians(10.0F), (float)Math.sin(f * 0.15F) * AngleMath.degToRadians(7.0F), (float)Math.sin(f * -0.15) * AngleMath.degToRadians(7.0F));
   }

   void syncBodyPose(GirlEntity girl) {
      try {
         if (!(girl instanceof GalathNpc)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GalathNpc galath = (GalathNpc)girl;
      AnimationProcessor animationProcessor = this.getAnimationProcessor();
      IBone iBone = animationProcessor.getBone("body");
      galath.bw = iBone.getRotationY();
      galath.bm = iBone.getScaleY();
   }

   void applyHugPose(GirlEntity girl) {
      try {
         if (girl.ActionController.getAnimationState() != AnimationState.Transitioning) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      AnimationProcessor animationProcessor = this.getAnimationProcessor();
      GirlAnimationState girlAnimationState = girl.getCurrentAction();
      if (girlAnimationState == GirlAnimationState.HUG_MANG) {
         IBone iBone = animationProcessor.getBone("body2");

         try {
            if (iBone == null) {
               return;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         iBone.setPositionX(0.0F);
         iBone.setPositionY(-0.53F);
         iBone.setPositionZ(-40.05F);
      }
   }

   void applyMasturbationAngles(GirlEntity girl) {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (girl.getCurrentAction() != GirlAnimationState.MASTERBATE) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Object obj = girl.getSexPlayer();
      if (obj == null) {
         obj = this.Mc.player;
      }

      MolangParser molangParser = GeckoLibCache.getInstance().parser;
      Vec3d vec3d = VecMath.getPlayerRelativeOffsetWithEyeHeight(girl, (EntityPlayer)obj, this.Mc.getRenderPartialTicks()).add(girl.getModelBone("head"));
      float f = (float)AngleMath.radToDegrees(Math.atan2(vec3d.z, vec3d.x)) - girl.I();
      float f2 = (float)AngleMath.radToDegrees(Math.atan2(vec3d.y, Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z)));
      double d = Math.abs(vec3d.x) + Math.abs(vec3d.y) + Math.abs(vec3d.z);
      double d2 = d * 7.0 + -20.0;
      double d3 = d * 5.0 + -20.0;
      molangParser.setValue("pitch", d2 + f2 - 80.0);
      molangParser.setValue("armpitch", d3 + f2 + -110.0);
      molangParser.setValue("armyaw", f + 80.0F);
      molangParser.setValue("yaw", f + 90.0F);
   }

   void a() {
      try {
         if (ClientProxy.IS_PRELOADING) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      IBone iBone;
      boolean flag;
      label46: {
         try {
            iBone = this.getAnimationProcessor().getBone("futaCock");
            if (!CommandFuta.Enabled) {
               flag = true;
               break label46;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         flag = false;
      }

      label39: {
         try {
            iBone.setHidden(flag);
            iBone = this.getAnimationProcessor().getBone("futaBallLL");
            if (!CommandFuta.Enabled) {
               flag = true;
               break label39;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         flag = false;
      }

      label32: {
         try {
            iBone.setHidden(flag);
            iBone = this.getAnimationProcessor().getBone("futaBallLR");
            if (!CommandFuta.Enabled) {
               flag = true;
               break label32;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         flag = false;
      }

      iBone.setHidden(flag);
   }

   void hideCoinBone(GirlEntity girl) {
      try {
         if (!(girl instanceof PlayerGirlEntity)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.getAnimationProcessor().getBone("coin").setHidden(true);
   }

   void updateWingsVisibility(GirlEntity girl) {
      IBone iBone;
      boolean flag;
      label16: {
         try {
            iBone = this.getAnimationProcessor().getBone("wings");
            if (!((BoxSource)girl).a()) {
               flag = true;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag = false;
      }

      iBone.setHidden(flag);
   }


   void e(GirlEntity girl) {
        try {
            if (!(girl instanceof GalathNpc)) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGalath.rethrow(runtimeException);
        }
        GalathNpc f_2 = (GalathNpc)girl;
        try {
            if (f_2.k()) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGalath.rethrow(runtimeException);
        }
        try {
            if (f_2.getTargetEntity() == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGalath.rethrow(runtimeException);
        }
        return false;
    }

   void b(GirlEntity girl) {
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
         if (girl.getCurrentAction() != GirlAnimationState.KNOCK_OUT_FLY) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      IBone iBone = this.getAnimationProcessor().getBone("body");
      Vec3d vec3d = new Vec3d(girl.lastTickPosX, girl.lastTickPosY, girl.lastTickPosZ);
      Vec3d vec3d2 = girl.getPositionVector().subtract(vec3d);

      boolean flag;
      label45: {
         try {
            if (Math.abs(vec3d2.x) + Math.abs(vec3d2.z) < 0.01F) {
               flag = true;
               break label45;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         flag = false;
      }

      boolean flag2 = flag;

      try {
         if (flag2) {
            iBone.setRotationX(AngleMath.degToRadians(-90.0F));
            iBone.setPositionY(0.0F);
            iBone.setPositionZ(0.0F);
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      Vec3d vec3d3 = applyHugPose(girl);
      iBone.setRotationX(-((float)vec3d3.x));
      iBone.setPositionY((float)vec3d3.y);
      iBone.setPositionZ((float)vec3d3.z);
   }

   void applyRapeChargePose(GirlEntity girl) {
      try {
         if (!(girl instanceof GalathNpc)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (girl.getCurrentAction() != GirlAnimationState.RAPE_CHARGE) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Vec3d vec3d = applyHugPose(girl);
      IBone iBone = this.getAnimationProcessor().getBone("body");
      IBone iBone2 = this.getAnimationProcessor().getBone("rotationTool");
      iBone2.setRotationX((float)vec3d.x);
      iBone.setPositionY((float)vec3d.y);
      iBone.setPositionZ((float)vec3d.z);
      float f = (Float)girl.getDataManager().get(GalathNpc.bO);
      iBone.setRotationY(AngleMath.degToRadians(f * 180.0F));
   }


   void applySwordAttackPose(GirlEntity girl) {
        int i;
        GalathNpc f_2;
        block13: {
            try {
                if (!(girl instanceof GalathNpc)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ModelGalath.rethrow(runtimeException);
            }
            f_2 = (GalathNpc)girl;
            try {
                if (f_2.getCurrentAction() != GirlAnimationState.ATTACK_SWORD) {
                    this.AttackStartTick = -1L;
                    this.AttackEndTick = -1L;
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ModelGalath.rethrow(runtimeException);
            }
            i = f_2.getSpecialState();
            try {
                try {
                    if (i != 24 || this.AttackStartTick != -1L) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGalath.rethrow(runtimeException);
                }
                this.AttackStartTick = this.Mc.world.getTotalWorldTime();
                this.AttackEndTick = this.AttackStartTick + 8L;
            }
            catch (RuntimeException runtimeException) {
                throw ModelGalath.rethrow(runtimeException);
            }
        }
        try {
            if (!MathUtils.isInRange((double)i, 24.0, 32.0)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGalath.rethrow(runtimeException);
        }
        IBone iBone = this.getAnimationProcessor().getBone("body");
        Vec3d vec3d = ModelGalath.a(f_2, f_2.getManglePos());
        float f = ((float)Minecraft.getMinecraft().world.getTotalWorldTime() + this.Mc.getRenderPartialTicks() - (float)this.AttackStartTick) / (float)(this.AttackEndTick - this.AttackStartTick);
        vec3d = LerpMath.lerpVec3d(vec3d, Vec3d.ZERO, (double)f);
        iBone.setRotationX((float)vec3d.x);
        iBone.setPositionY((float)vec3d.y);
        iBone.setPositionZ((float)vec3d.z);
    }


   void a(GirlEntity girl) {
        try {
            if (girl.world instanceof PreviewWorld) {
                return this.TextureLayers[0];
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGalath.rethrow(runtimeException);
        }
        try {
            if (((BoxSource)((Object)girl)).b()) {
                return this.TextureLayers[2];
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGalath.rethrow(runtimeException);
        }
        return this.TextureLayers[(Integer)girl.getDataManager().get(GirlEntity.OutfitIndexKey)];
    }

   @Override
   public String[] c() {
      return new String[]{"armorHelmet"};
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
