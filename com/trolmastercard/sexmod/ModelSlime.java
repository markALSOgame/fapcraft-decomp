package com.trolmastercard.sexmod;

import java.util.Arrays;
import javax.vecmath.Vector3f;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class ModelSlime extends GirlGeoModel<GirlEntity> {
   GirlAnimationState[] BedDoggyAnimations = new GirlAnimationState[]{GirlAnimationState.STARTDOGGY, GirlAnimationState.DOGGYCUM, GirlAnimationState.DOGGYSLOW, GirlAnimationState.DOGGYFAST, GirlAnimationState.DOGGYCUM, GirlAnimationState.DOGGYSTART, GirlAnimationState.WAITDOGGY};

   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{
         new ResourceLocation("sexmod", "geo/slime/nude.geo.json"),
         new ResourceLocation("sexmod", "geo/slime/armored.geo.json"),
         new ResourceLocation("sexmod", "geo/slime/dressed.geo.json")
      };
   }

   @Override
   public ResourceLocation getTextureLocation(GirlEntity girl) {
      try {
         if (girl.world instanceof PreviewWorld) {
            return this.TextureLayers[0];
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if ((Integer)girl.getDataManager().get(GirlEntity.OutfitIndexKey) > this.TextureLayers.length) {
            System.out.println("Girl doesn't have an outfit Nr." + girl.getDataManager().get(GirlEntity.OutfitIndexKey) + " so im just making her nude lol");
            return this.TextureLayers[0];
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (girl instanceof SlimePlayer) {
            return this.TextureLayers[girl.getDataManager().get(GirlEntity.OutfitIndexKey)];
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if ((Integer)girl.getDataManager().get(GirlEntity.OutfitIndexKey) == 1) {
            return this.TextureLayers[2];
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      return this.TextureLayers[0];
   }

   @Override
   public ResourceLocation getSkinLocation() {
      return new ResourceLocation("sexmod", "textures/entity/slime/slime.png");
   }

   @Override
   public ResourceLocation getAnimationFileLocation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/slime/slime.animation.json");
   }

   @Override

   public void setLivingAnimations(GirlEntity girl, Integer i, AnimationEvent animEvent) {
        block13: {
            boolean flag;
            IBone iBone;
            boolean flag2;
            IBone iBone2;
            AnimationProcessor animationProcessor;
            block15: {
                block14: {
                    super.setLivingAnimations(girl, i, animEvent);
                    animationProcessor = this.getAnimationProcessor();
                    try {
                        try {
                            try {
                                try {
                                    if (girl.world instanceof PreviewWorld || animationProcessor.getBone("bedSlime") == null) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelSlime.rethrow(runtimeException);
                                }
                                if (animationProcessor.getBone("bedSlimeLayer") == null) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ModelSlime.rethrow(runtimeException);
                            }
                            iBone2 = animationProcessor.getBone("bedSlime");
                            if (Arrays.asList(this.BedDoggyAnimations).contains((Object)girl.getCurrentAction())) break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelSlime.rethrow(runtimeException);
                        }
                        flag2 = true;
                        break block15;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ModelSlime.rethrow(runtimeException);
                    }
                }
                flag2 = false;
            }
            try {
                iBone2.setHidden(flag2);
                iBone = animationProcessor.getBone("bedSlimeLayer");
                flag = !Arrays.asList(this.BedDoggyAnimations).contains((Object)girl.getCurrentAction());
            }
            catch (RuntimeException runtimeException) {
                throw ModelSlime.rethrow(runtimeException);
            }
            iBone.setHidden(flag);
        }
        try {
            if (girl instanceof PlayerGirlEntity) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelSlime.rethrow(runtimeException);
        }
        this.copyBoneTransforms(new String[]{"head"}, "hat");
    }

   void copyBoneTransforms(String[] stringArray, String string) {
      AnimationProcessor animationProcessor = this.getAnimationProcessor();
      IBone iBone = animationProcessor.getBone(string);
      IBone[] iBoneArray = new IBone[stringArray.length];
      int i = 0;

      try {
         while (i < iBoneArray.length) {
            iBoneArray[i] = animationProcessor.getBone(stringArray[i]);
            i++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Vector3f vector3f = new Vector3f(0.0F, 0.0F, 0.0F);
      Vector3f vector3f2 = new Vector3f(0.0F, 0.0F, 0.0F);

      for (IBone iBone2 : iBoneArray) {
         vector3f.add(new Vector3f(iBone2.getRotationX(), iBone2.getRotationY(), iBone2.getRotationZ()));
         vector3f2.add(new Vector3f(iBone2.getPositionX(), iBone2.getPositionY(), iBone2.getPositionZ()));
      }

      iBone.setRotationX(vector3f.x);
      iBone.setRotationY(vector3f.y);
      iBone.setRotationZ(vector3f.z);
      iBone.setPositionX(vector3f2.x);
      iBone.setPositionY(vector3f2.y);
      iBone.setPositionZ(vector3f2.z);
      iBone.setPositionZ(vector3f2.z);
   }

   @Override
   public String[] getHelmetBones() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getHeadAccessoryBones() {
      return new String[]{"bigblob"};
   }

   @Override
   public String[] getChestArmorBones() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   @Override
   public String[] getFleshTorsoBones() {
      return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR", "cloth"};
   }

   @Override
   public String[] getPantsArmorBones() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   @Override
   public String[] getFleshLegsBones() {
      return new String[]{"fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   @Override
   public String[] getBootsArmorBones() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
