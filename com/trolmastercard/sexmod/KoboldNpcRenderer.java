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
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 3[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
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
