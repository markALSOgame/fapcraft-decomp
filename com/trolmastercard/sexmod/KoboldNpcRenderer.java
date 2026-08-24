package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.HashSet;
import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KoboldNpcRenderer extends ScaledGirlGeoRenderer<KoboldNpc> {
   static final HashSet<String> MainColorBones = new HashSet<>(
      Arrays.asList(
         "colorSpots",
         "neck",
         "head",
         "snout",
         "midSectionR",
         "midSectionL",
         "innerCheekLR",
         "innerCheekRR",
         "gayL",
         "gayR",
         "legR",
         "legL",
         "shinL",
         "toesL",
         "kneeL",
         "curvesL",
         "shinR",
         "toesR",
         "kneeR",
         "curvesR",
         "sideL",
         "sideR",
         "hip",
         "torsoL",
         "torsoR",
         "armR",
         "lowerArmR",
         "ellbowR",
         "armL",
         "lowerArmL",
         "ellbowL",
         "hornUL",
         "hornUR",
         "tail",
         "tail2",
         "tail3",
         "tail4",
         "tail5",
         "hornDL2",
         "hornDR2",
         "hornDR3M",
         "hornDL3M",
         "frecklesAL1",
         "frecklesAL2",
         "frecklesAR1",
         "frecklesAR2",
         "frecklesHL1",
         "frecklesHL2",
         "frecklesHR1",
         "frecklesHR2"
      )
   );
   static final HashSet<String> SecondaryColorBones = new HashSet<>(
      Arrays.asList(
         "boobR",
         "boobL",
         "frontNeck",
         "Rside",
         "Lside",
         "frontAndInside",
         "innerCheekLL",
         "innerCheekRL",
         "layer",
         "layer2",
         "down",
         "down2",
         "down3",
         "down4",
         "down5",
         "fuckhole",
         "hornDR3S",
         "hornDL3S",
         "assholeCoverUp",
         "assholeCoverUp2"
      )
   );
   Minecraft Mc = Minecraft.getMinecraft();
   Vector3f RenderPosition;

   public KoboldNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
   }

   @Override

   protected Vec3i getBoneColor(String string) {
        block10: {
            EntityDataManager entityDataManager = ((KoboldNpc)this.RenderEntity).getDataManager();
            EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.valueOf((String)entityDataManager.get(KoboldNpc.BodyColorKey));
            BlockPos blockPos = (BlockPos)entityDataManager.get(KoboldNpc.EyeColorKey);
            try {
                if (MainColorBones.contains(string)) {
                    return eyeAndKoboldColor.getMainColor();
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldNpcRenderer.rethrow(runtimeException);
            }
            try {
                if (SecondaryColorBones.contains(string)) {
                    return eyeAndKoboldColor.getSecondaryColor();
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldNpcRenderer.rethrow(runtimeException);
            }
            try {
                try {
                    if (!"irisR".equals(string) && !"irisL".equals(string)) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldNpcRenderer.rethrow(runtimeException);
                }
                return blockPos;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldNpcRenderer.rethrow(runtimeException);
            }
        }
        return r;
    }

   @Override

   protected ItemStack a(@Nullable ItemStack stack) {
      label31: {
         switch (((KoboldNpc)this.RenderEntity).getCurrentAction()) {
            case MINE:
               if ((Boolean)this.RenderEntity.getDataManager().get(KoboldNpc.at)) {
                  return new ItemStack(Items.IRON_AXE);
               }

               return new ItemStack(Items.IRON_PICKAXE);
            case NULL:
               if (!(Boolean)this.RenderEntity.getDataManager().get(KoboldNpc.aC)) break label31;
               return new ItemStack(Items.IRON_SWORD);
            case ATTACK:
               return new ItemStack(Items.IRON_SWORD);
         }
      }

      return stack;
   }

   @Override
   public void renderBone(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4, double d) {
      try {
         if (this.RenderEntity.world instanceof PreviewWorld) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String string = bone.getName();
      if ("blowOpening".equals(string)) {
         d = 0.0;
      }

      if ("mouth".equals(string)) {
         String[] stringArray = GirlEffectEntity.getAttributeStrings(this.RenderEntity);
         int i = Integer.parseInt(stringArray[7]);
         if (i == 1) {
            d = -0.078125;
         }
      }

      super.renderBone(bufferBuilder, bone, f, f2, f3, f4, d);
   }

   @Override
   protected void applyBodyScale() {
      float f = 0.25F - (Float)this.RenderEntity.getDataManager().get(KoboldPlayer.BodySizeKey);
      GlStateManager.scale(1.0F - f, 1.0F - f, 1.0F - f);
   }

   @Override
   protected void undoBodyScale() {
      float f = 0.25F - (Float)this.RenderEntity.getDataManager().get(KoboldPlayer.BodySizeKey);
      double d = 1.0 / (1.0 - f);
      GlStateManager.scale(d, d, d);
   }

   @Override
   protected ItemStack getHeldItem() {
      String string = (String)this.RenderEntity.getDataManager().get(GirlEntity.BlowjobStageKey);

      try {
         if ("STARTBLOWJOB".equals(string)) {
            return new ItemStack(Items.IRON_PICKAXE);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         return "ANAL_START".equals(string) ? new ItemStack(Items.GOLD_INGOT, 3) : null;
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   public void render(KoboldNpc kobold, double d, double d2, double d3, float f, float f2) {
      String string = (String)kobold.getDataManager().get(GirlEffectEntity.TribeColorKey);

      try {
         if (kobold.as == null) {
            kobold.as = string;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!kobold.as.equals(string)) {
            c();
            kobold.as = string;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      this.RenderPosition = new Vector3f((float)d, (float)d2, (float)d3);
      super.render(kobold, d, d2, d3, f, f2);
   }

   @Override
   protected void renderNameTag(double d, double d2, double d3) {
      EntityDataManager entityDataManager = this.RenderEntity.getDataManager();
      String string = (String)entityDataManager.get(KoboldNpc.TribeNameKey);

      try {
         if ("null".equals(string)) {
            super.renderNameTag(d, d2, d3);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      EyeAndKoboldColor eyeColor = EyeAndKoboldColor.valueOf((String)entityDataManager.get(KoboldNpc.BodyColorKey));
      string = eyeColor.getTextColor() + " -" + string + "-";
      this.renderLivingLabel(this.RenderEntity, this.RenderEntity.getDisplayName() + string, d, d2 + this.RenderEntity.getScaleOffset(), d3, 300);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
