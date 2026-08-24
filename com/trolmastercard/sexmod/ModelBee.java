package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;

public class ModelBee extends GirlGeoModel {
   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/bee/bee.geo.json"), new ResourceLocation("sexmod", "geo/bee/armored.geo.json")};
   }

   @Override
   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/bee/bee.png");
   }

   @Override
   public ResourceLocation getAnimationLocation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/bee/bee.animation.json");
   }

   @Override

   public void onAnimationEvent(GirlEntity girl, Integer i, AnimationEvent animEvent) {
        boolean flag;
        IBone iBone;
        block11: {
            block10: {
                try {
                    super.onAnimationEvent(girl, i, animEvent);
                    if (girl.world instanceof PreviewWorld) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ModelBee.rethrow(runtimeException);
                }
                AnimationProcessor animationProcessor = this.getAnimationProcessor();
                IBone iBone2 = animationProcessor.getBone("chest");
                try {
                    if (iBone2 == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ModelBee.rethrow(runtimeException);
                }
                try {
                    try {
                        iBone = iBone2;
                        if (girl.MovementController.getCurrentAnimation() != null && girl.MovementController.getCurrentAnimation().animationName.contains("chest")) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ModelBee.rethrow(runtimeException);
                    }
                    flag = true;
                    break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw ModelBee.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        iBone.setHidden(flag);
    }

   @Override

   protected void applyAnimationTransforms(GirlEntity girl, AnimationProcessor animationProcessor, AnimationEvent animEvent) {
        block8: {
            IBone iBone;
            block9: {
                try {
                    try {
                        try {
                            if (girl.world instanceof PreviewWorld) break block8;
                            if (girl.getCurrentAction() == GirlAnimationState.NULL) break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelBee.rethrow(runtimeException);
                        }
                        if (girl.getCurrentAction() == GirlAnimationState.ATTACK) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ModelBee.rethrow(runtimeException);
                    }
                    if (girl.getCurrentAction() != GirlAnimationState.BOW) break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw ModelBee.rethrow(runtimeException);
                }
            }
            EntityModelData entityModelData = animEvent.getExtraDataOfType(EntityModelData.class).get(0);
            IBone iBone2 = animationProcessor.getBone("neck");
            iBone2.setRotationY(entityModelData.netHeadYaw * 0.5f * ((float)Math.PI / 180));
            IBone iBone3 = animationProcessor.getBone("head");
            try {
                iBone3.setRotationY(entityModelData.netHeadYaw * ((float)Math.PI / 180));
                iBone3.setRotationX(1.0f + entityModelData.headPitch * ((float)Math.PI / 180));
                iBone = animationProcessor.getBone("body") == null ? animationProcessor.getBone("dd") : animationProcessor.getBone("body");
            }
            catch (RuntimeException runtimeException) {
                throw ModelBee.rethrow(runtimeException);
            }
            IBone iBone4 = iBone;
            iBone4.setRotationY(0.0f);
        }
    }

   @Override
   public String[] getHelmetBones() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getHeadAccessoryBones() {
      return new String[]{"band", "feeler", "feeler2", "brow", "brow2", "brow3", "brow4"};
   }

   @Override
   public String[] getChestBones() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   @Override
   public String[] a() {
      return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR"};
   }

   @Override
   public String[] getPantsBones() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   @Override
   public String[] getLegBones() {
      return new String[]{"sideL", "sideR", "fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   @Override
   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
