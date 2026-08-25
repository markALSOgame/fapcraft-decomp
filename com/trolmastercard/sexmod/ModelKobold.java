package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ModelKobold extends GirlGeoModel {
   static final float g = 1.2F;
   static final float f = 1.0F;

   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{
         new ResourceLocation("sexmod", "geo/kobold/kobold.geo.json"), new ResourceLocation("sexmod", "geo/kobold/armored.geo.json")
      };
   }

   @Override
   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/kobold/kobold.png");
   }

   @Override
   public ResourceLocation getAnimationFileLocation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/kobold/kobold.animation.json");
   }

   @Override

   public void a(GirlEntity girl, Integer i, AnimationEvent animEvent) {
      super.a(girl, i, animEvent);
      if (girl.world instanceof PreviewWorld) {
         return;
      }

      AnimationProcessor animationProcessor = this.getAnimationProcessor();
      if (!girl.isTracked() && girl instanceof KoboldNpc) {
         animationProcessor.getBone("crown").setHidden(girl.getDataManager().get(KoboldNpc.aZ) == false);
         animationProcessor.getBone("egg").setHidden(!((KoboldNpc)girl).Q);
      } else {
         animationProcessor.getBone("crown").setHidden(true);
         animationProcessor.getBone("egg").setHidden(true);
      }

      String[] stringArray = GirlEffectEntity.getAttributeStrings(girl);
      this.setUpperHorns(animationProcessor, stringArray[0]);
      this.setLowerHorns(animationProcessor, stringArray[1]);
      this.applyBoneScale(animationProcessor, stringArray[2], 0.75F, 1.35F, "boobL", "boobR", "armorBoobs");
      this.applyBoneScale(animationProcessor, stringArray[3], 1.0F, 1.2F, "eyeL", "eyeR");
      this.applyEyeBlink(animationProcessor, stringArray[3], 1.0F, 1.2F);
      this.applyArmFreckles(animationProcessor, stringArray[4]);
      this.applyHeadFreckles(animationProcessor, stringArray[5]);
      this.applyBackpackState(girl, animationProcessor, stringArray[6]);

      switch (girl.getCurrentAction()) {
         case STARTBLOWJOB:
         case SUCKBLOWJOB_BLINK:
         case THRUSTBLOWJOB:
         case CUMBLOWJOB:
            animationProcessor.getBone("tounge").setHidden(false);
            break;
         default:
            animationProcessor.getBone("tounge").setHidden(true);
      }

      this.applyBodyPositioning(girl, animationProcessor);
   }

   void applyBodyPositioning(GirlEntity girl, AnimationProcessor animationProcessor) {
      try {
         if (girl.ActionController.getAnimationState() != AnimationState.Transitioning) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      float f = (Float)girl.getDataManager().get(KoboldNpc.BodySizeKey);
      f = 0.25F - f;
      switch (girl.getCurrentAction()) {
         case SUCKBLOWJOB_BLINK:
         case THRUSTBLOWJOB:
         case CUMBLOWJOB:
            IBone iBone = animationProcessor.getBone("body");
            iBone.setPositionZ(11.43F + f * -7.0F);
            return;
         case KOBOLD_ANAL_SLOW:
         case ANAL_FAST:
         case ANAL_CUM:
         case ANAL_START:
            IBone iBone2 = animationProcessor.getBone("body");
            iBone2.setPositionX(1.78F + f * -1.5F);
            iBone2.setPositionY(13.07F + f * -11.0F);
            iBone2.setPositionZ(2.05F + f * -8.0F);
            return;
         case MATING_PRESS_CUM:
         case MATING_PRESS_HARD:
         case MATING_PRESS_SOFT:
         case MATING_PRESS_START:
            IBone iBone3 = animationProcessor.getBone("body");
            iBone3.setPositionX(0.0F);
            iBone3.setPositionY(2.85F);
            iBone3.setPositionZ(-7.0F + f * 4.7F);
            return;
      }
   }

   void applyBackpackState(GirlEntity girl, AnimationProcessor animationProcessor, String string) {
      int i = Integer.parseInt(string);
      IBone iBone = animationProcessor.getBone("backpack");
      IBone iBone2 = animationProcessor.getBone("tailpack");

      label34: {
         label33: {
            label32: {
               try {
                  switch (i) {
                     case 0:
                        iBone.setHidden(false);
                        iBone2.setHidden(true);
                        break label34;
                     case 1:
                        break label33;
                     case 2:
                        break label32;
                     case 3:
                        break;
                     default:
                        break label34;
                  }
               } catch (RuntimeException error) {
                  throw rethrow(error);
               }

               iBone.setHidden(true);
               iBone2.setHidden(true);
               break label34;
            }

            iBone.setHidden(true);
            iBone2.setHidden(false);
            break label34;
         }

         iBone.setHidden(false);
         iBone2.setHidden(false);
      }

      try {
         if (girl.getCurrentAction() == GirlAnimationState.PAYMENT) {
            iBone.setHidden(false);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   void applyHeadFreckles(AnimationProcessor animationProcessor, String string) {
      int i = Integer.parseInt(string);
      IBone iBone = animationProcessor.getBone("frecklesHR1");
      IBone iBone2 = animationProcessor.getBone("frecklesHR2");
      IBone iBone3 = animationProcessor.getBone("frecklesHL1");
      IBone iBone4 = animationProcessor.getBone("frecklesHL2");

      IBone iBone5;
      boolean flag;
      label53: {
         try {
            iBone5 = iBone3;
            if (i != 1) {
               flag = true;
               break label53;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag = false;
      }

      label46: {
         try {
            iBone5.setHidden(flag);
            iBone5 = iBone;
            if (i != 1) {
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
            iBone5.setHidden(flag);
            iBone5 = iBone4;
            if (i != 2) {
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
            iBone5.setHidden(flag);
            iBone5 = iBone2;
            if (i != 2) {
               flag = true;
               break label32;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         flag = false;
      }

      iBone5.setHidden(flag);
   }

   void applyArmFreckles(AnimationProcessor animationProcessor, String string) {
      int i = Integer.parseInt(string);
      IBone iBone = animationProcessor.getBone("frecklesAR1");
      IBone iBone2 = animationProcessor.getBone("frecklesAR2");
      IBone iBone3 = animationProcessor.getBone("frecklesAL1");
      IBone iBone4 = animationProcessor.getBone("frecklesAL2");

      IBone iBone5;
      boolean flag;
      label53: {
         try {
            iBone5 = iBone3;
            if (i != 1) {
               flag = true;
               break label53;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag = false;
      }

      label46: {
         try {
            iBone5.setHidden(flag);
            iBone5 = iBone;
            if (i != 1) {
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
            iBone5.setHidden(flag);
            iBone5 = iBone4;
            if (i != 2) {
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
            iBone5.setHidden(flag);
            iBone5 = iBone2;
            if (i != 2) {
               flag = true;
               break label32;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         flag = false;
      }

      iBone5.setHidden(flag);
   }

   void applyEyeBlink(AnimationProcessor animationProcessor, String string, float f, float f2) {
      try {
         if (Minecraft.getMinecraft().isGamePaused()) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      float f3 = Float.parseFloat(string);
      f3 /= 100.0F;
      f3 = f + (f2 - f) * f3 - 1.0F;
      IBone iBone = animationProcessor.getBone("eyeL");
      iBone.setPositionX(iBone.getPositionX() + f3);
      IBone iBone2 = animationProcessor.getBone("eyeR");
      iBone2.setPositionX(iBone2.getPositionX() - f3);
   }

   void applyBoneScale(AnimationProcessor animationProcessor, String string, float f, float f2, String... stringArray) {
      float f3 = Float.parseFloat(string);
      f3 /= 100.0F;
      f3 = f + (f2 - f) * f3;

      for (String string2 : stringArray) {
         IBone iBone = animationProcessor.getBone(string2);

         try {
            if (iBone == null) {
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         iBone.setScaleX(f3);
         iBone.setScaleY(f3);
         iBone.setScaleZ(f3);
      }
   }

   void setLowerHorns(AnimationProcessor animationProcessor, String string) {
      List list = this.getNumberedBones(animationProcessor, "hornDL");
      List list2 = this.getNumberedBones(animationProcessor, "hornDR");
      this.hideBones(list);
      this.hideBones(list2);
      int i = new Integer(string);
      animationProcessor.getBone("hornDL" + i).setHidden(false);
      animationProcessor.getBone("hornDR" + i).setHidden(false);
   }

   void setUpperHorns(AnimationProcessor animationProcessor, String string) {
      List list = this.getNumberedBones(animationProcessor, "hornUL");
      List list2 = this.getNumberedBones(animationProcessor, "hornUR");
      this.hideBones(list);
      this.hideBones(list2);
      int i = new Integer(string);
      animationProcessor.getBone("hornUL" + i).setHidden(false);
      animationProcessor.getBone("hornUR" + i).setHidden(false);
   }

   List<IBone> getNumberedBones(AnimationProcessor animationProcessor, String string) {
      ArrayList list = new ArrayList();
      int i = 0;

      while (true) {
         IBone iBone = animationProcessor.getBone(string + i);

         try {
            if (iBone == null) {
               return list;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         list.add(iBone);
         i++;
      }
   }

   void hideBones(List<IBone> list) {
      for (IBone iBone : list) {
         iBone.setHidden(true);
      }
   }

   @Override

   protected void a(GirlEntity girl, AnimationProcessor animationProcessor, AnimationEvent animEvent) {
      if (girl.world instanceof PreviewWorld) {
         return;
      }

      label28: {
         switch (girl.getCurrentAction()) {
            case NULL:
                if (Math.abs(girl.prevPosX - girl.posX) + Math.abs(girl.prevPosZ - girl.posZ) < 0.0 || girl.onGround && Math.abs(Math.abs(girl.prevPosY) - Math.abs(girl.posY)) > (double)0.1F || !((BooleanCheck)girl).boolean_a()) {
                  break label28;
               }
            default:
               return;
         }
      }

      EntityModelData entityModelData = (EntityModelData)animEvent.getExtraDataOfType(EntityModelData.class).get(0);
      IBone head = animationProcessor.getBone("head");
      head.setRotationY(entityModelData.netHeadYaw * ((float)Math.PI / 180));
      head.setRotationX(entityModelData.headPitch * ((float)Math.PI / 180));
      IBone body = animationProcessor.getBone("body") == null ? animationProcessor.getBone("dd") : animationProcessor.getBone("body");
      body.setRotationY(0.0F);
   }

   @Override
   public String[] getHelmetBones() {
      return new String[]{"armorHelmet"};
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
      return new String[]{
         "armorBootyR",
         "armorBootyL",
         "armorPantsLowL",
         "armorPantsLowR",
         "armorPantsLowR",
         "armorPantsUpR",
         "armorPantsUpL",
         "armorHip",
         "armorKneeR",
         "armorKneeL"
      };
   }

   @Override
   public String[] getLowerFleshBones() {
      return new String[]{"fleshL", "fleshR", "vagina", "fuckhole", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   @Override
   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }

   @Override
   public String[] getToeBones() {
      return new String[]{"toesR", "toesL"};
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
