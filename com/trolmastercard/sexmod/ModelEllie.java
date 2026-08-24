package com.trolmastercard.sexmod;

import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class ModelEllie extends GirlGeoModel {
   HashMap<Integer, float[]> HeadYawRotations = new HashMap<Integer, float[]>() {
      {
         this.put(0, new float[]{0.0F, -1.2F, 1.2F});
         this.put(-90, new float[]{2.0F, -71.56F, -68.0F});
         this.put(90, new float[]{-2.0F, 68.0F, 70.5F});
      }
   };

   public ModelEllie() {
      this.TextureLayers = this.a();
   }

   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/ellie/nude.geo.json"), new ResourceLocation("sexmod", "geo/ellie/dressed.geo.json")};
   }

   @Override
   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/ellie/ellie.png");
   }

   @Override
   public ResourceLocation getAnimationFileLocation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/ellie/ellie.animation.json");
   }

   @Override

   public void animateHeadTracking(GirlEntity girl, Integer i, AnimationEvent animEvent) {
        float f;
        float f2;
        float f3;
        IBone iBone;
        EntityPlayer entityPlayer;
        block24: {
            int i2;
            Vec3d vec3d;
            block25: {
                block23: {
                    block22: {
                        try {
                            super.animateHeadTracking(girl, i, animEvent);
                            if (girl.world instanceof PreviewWorld) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelEllie.rethrow(runtimeException);
                        }
                        try {
                            if (girl instanceof PlayerGirlEntity) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelEllie.rethrow(runtimeException);
                        }
                        try {
                            if (girl.getCurrentAction() != GirlAnimationState.SITDOWNIDLE) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelEllie.rethrow(runtimeException);
                        }
                        entityPlayer = girl.world.getClosestPlayerToEntity((Entity)girl, 15.0);
                        try {
                            if (entityPlayer == null) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelEllie.rethrow(runtimeException);
                        }
                        iBone = this.getAnimationProcessor().getBone("head");
                        vec3d = girl.getPositionVector().subtract(entityPlayer.getPositionVector());
                        i2 = Math.round(girl.I().floatValue());
                        if (i2 != 180) break block25;
                        f3 = (float)Math.atan2(vec3d.x, vec3d.z) * 1.2f;
                        f3 = f3 > 0.0f ? Math.max(1.5f, Math.min(3.14f, f3)) : Math.max(-3.14f, Math.min(-1.5f, f3));
                        try {
                            try {
                                try {
                                    if (f3 == 1.5f || f3 == 3.14f) break block22;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelEllie.rethrow(runtimeException);
                                }
                                if (f3 == -3.14f) break block22;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ModelEllie.rethrow(runtimeException);
                            }
                            if (f3 != -1.5f) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelEllie.rethrow(runtimeException);
                        }
                    }
                    f3 = 0.0f;
                    break block24;
                }
                f3 += 3.0f;
                break block24;
            }
            f2 = this.HeadYawRotations.get(i2)[1];
            float f4 = this.HeadYawRotations.get(i2)[2];
            f3 = ((float)(Math.atan2(vec3d.x, vec3d.z) + (double)this.HeadYawRotations.get(i2)[0]) + girl.I().floatValue()) * 0.8f;
            f3 = MathUtils.clamp(f3, f2, f4);
            try {
                if (f3 != f2 && f3 != f4) break block24;
            }
            catch (RuntimeException runtimeException) {
                throw ModelEllie.rethrow(runtimeException);
            }
            f3 = 0.0f;
        }
        try {
            f = f3 == 0.0f ? 0.0f : MathUtils.clamp((float)((entityPlayer.posY - girl.posY) * 0.5), -0.75f, 0.75f);
        }
        catch (RuntimeException runtimeException) {
            throw ModelEllie.rethrow(runtimeException);
        }
        f2 = f;
        iBone.setRotationY(f3);
        iBone.setRotationX(f2);
    }

   @Override
   public String[] getArmorHelmetBones() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getHeadbandBones() {
      return new String[]{"headband"};
   }

   @Override
   public String[] getArmorShoulderBones() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   @Override
   public String[] a() {
      return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR"};
   }

   @Override
   public String[] getArmorHipAndPantsBones() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   @Override
   public String[] getFleshBones() {
      return new String[]{"fleshL", "fleshR", "vagina", "hotpants", "slip", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   @Override
   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
