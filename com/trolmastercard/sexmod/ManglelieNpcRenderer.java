package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class ManglelieNpcRenderer extends GeoGirlRenderer<ManglelieNpc> {
   static final RgbaColor4 C = new RgbaColor4(115, 108, 188, 255);
   static final Vec3f D = new Vec3f(0.05F, 0.04F, 0.0F);
   static final Vec3f v = new Vec3f(0.0F, 0.065F, 0.0F);
   static final Vec3f z = new Vec3f(0.0F, 0.03F, 0.03F);
   static final RgbaColor4 r = new RgbaColor4(63, 59, 150, 255);
   static final RgbaColor4 x = new RgbaColor4(79, 74, 188, 255);
   static final float A = 0.5F;
   static final float w = 0.5F;
   static final int s = 40;
   static final float y = 0.01F;
   static final float t = 0.03F;
   public static final HashSet<String> B = new HashSet<String>() {
      {
         this.add("boobs2");
         this.add("booty2");
         this.add("vagina2");
         this.add("fuckhole2");
      }
   };
   boolean Initialized = false;

   public ManglelieNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
   }

   @Override
   public HashSet<String> a() {
      try {
         if (!this.Initialized) {
            B.addAll(BoneColorHelper.AdultParts);
            this.Initialized = true;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      return B;
   }

   public void doRender(ManglelieNpc manglelie, double d2, double d3, double d4, float f, float f2) {
      try {
         if (this.d(manglelie)) {
            return;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      try {
         if (this.isInThreesomeAnimation(manglelie)) {
            return;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      try {
         if (c(manglelie, 0.5F)) {
            return;
         }
      } catch (NumberFormatException error3) {
         throw rethrow(error3);
      }

      try {
         if (this.c(manglelie)) {
            return;
         }
      } catch (NumberFormatException error4) {
         throw rethrow(error4);
      }

      super.doRender(manglelie, d2, d3, d4, f, f2);
      renderGirl(manglelie, f2);
   }

   boolean c(ManglelieNpc manglelie) {
      GalathNpc galath = manglelie.getMommy(false);

      try {
         if (galath == null) {
            return false;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      try {
         switch (galath.getCurrentAction()) {
            case CONTROLLED_FLIGHT:
            case BOOST:
               return true;
            default:
               return false;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }
   }

   boolean a(ManglelieNpc manglelie) {
      try {
         if (manglelie.getCurrentAction() != GirlAnimationState.RIDE_MOMMY_HEAD) {
            return false;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      try {
         if (manglelie.getMommy(false) == null) {
            return true;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      return false;
   }

   boolean d(ManglelieNpc manglelie) {
      GalathNpc galath = manglelie.getMommy(false);

      try {
         if (galath == null) {
            return false;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      try {
         if (galath.isDead) {
            manglelie.setMommyUuid((UUID)null);
            return false;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      return galath.b();
   }

   public void doRenderShadowAndFire(Entity entity, double d2, double d3, double d4, float f, float f2) {
      try {
         if (!(entity instanceof ManglelieNpc)) {
            super.doRenderShadowAndFire(entity, d2, d3, d4, f, f2);
            return;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      ManglelieNpc manglelie = (ManglelieNpc)entity;

      try {
         if (this.d(manglelie)) {
            return;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      try {
         if (manglelie.isClaimed()) {
            return;
         }
      } catch (NumberFormatException error3) {
         throw rethrow(error3);
      }

      super.doRenderShadowAndFire(entity, d2, d3, d4, f, f2);
   }

   static boolean c(GirlEntity girl, float f) {
      try {
         if (!(girl instanceof ManglelieNpc)) {
            return false;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      GalathNpc galath = ((ManglelieNpc)girl).a(false);

      try {
         if (galath == null) {
            return false;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      try {
         if (galath.bm < f) {
            return true;
         }
      } catch (NumberFormatException error3) {
         throw rethrow(error3);
      }

      return false;
   }

   public static void renderGirl(GirlEntity girl, float f) {
      EntityPlayerSP mcPlayer = KoboldEggEntity.player;

      try {
         if (mcPlayer == null) {
            return;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      try {
         if (c(girl, 0.5F)) {
            return;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();

      label29: {
         try {
            GlStateManager.pushMatrix();
            if (girl.isTracked()) {
               GlStateManager.translate(0.0, 0.01, 0.0);
               break label29;
            }
         } catch (NumberFormatException error3) {
            throw rethrow(error3);
         }

         VectorUtil.drawGirlBones(i, girl, f);
         rotateHeadToTwin(girl, f);
      }

      KoboldEggEntity.getTextureManager().bindTexture(e);
      GlStateManager.disableCull();
      GlStateManager.disableLighting();
      renderGirlGeometryWithAlpha(girl, bufferBuilder, tessellator, renderGirl((GirlEntity)girl, f));
      renderGirlGeometry(girl, bufferBuilder, tessellator);
      GlStateManager.popMatrix();
      GlStateManager.enableCull();
      GlStateManager.enableLighting();
   }

   static void rotateHeadToTwin(GirlEntity girl, float f) {
      try {
         if (!(girl instanceof ManglelieNpc)) {
            return;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      ManglelieNpc manglelie = (ManglelieNpc)girl;

      try {
         if (!manglelie.isClaimed()) {
            return;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      try {
         if (ModelManglelie.isInThreesomeAnimation(manglelie)) {
            return;
         }
      } catch (NumberFormatException error3) {
         throw rethrow(error3);
      }

      GalathNpc galath = manglelie.getMommy(false);

      try {
         if (galath == null) {
            return;
         }
      } catch (NumberFormatException error4) {
         throw rethrow(error4);
      }

      GlStateManager.rotate(-LerpMath.lerpAngleDegrees(girl.prevRenderYawOffset, girl.renderYawOffset, f), 0.0F, 1.0F, 0.0F);
   }

   static boolean isInThreesomeAnimation(GirlEntity girl) {
      if (girl instanceof GalathNpc) {
         girl = ((GalathNpc)girl).a(false);
      }

      try {
         if (girl == null) {
            return false;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      try {
         if (!GirlAnimationState.isGirlInAnimation(girl, GirlAnimationState.THREESOME_SLOW, GirlAnimationState.THREESOME_FAST, GirlAnimationState.THREESOME_CUM)) {
            return true;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      return false;
   }

   static void renderGirlGeometry(GirlEntity girl, BufferBuilder bufferBuilder, Tessellator tessellator) {
      try {
         if (!isInThreesomeAnimation(girl)) {
            return;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
      int i = 0;

      try {
         while (i < 39) {
            drawGirlShadow(girl, bufferBuilder, i, i + 1);
            i++;
         }
      } catch (NumberFormatException error2) {
         throw rethrow(error2);
      }

      drawGirlShadow(girl, bufferBuilder, 39, 0);
      tessellator.draw();
   }

   static void drawGirlShadow(GirlEntity girl, BufferBuilder bufferBuilder, int i, int i2) {
      Vec3d vec3d = girl.getModelBone("skirt_" + i + "_0");
      Vec3d vec3d2 = girl.getModelBone("skirt_" + i + "_1");
      Vec3d vec3d3 = girl.getModelBone("skirt_" + i + "_2");
      Vec3d vec3d4 = girl.getModelBone("skirt_" + i2 + "_0");
      Vec3d vec3d5 = girl.getModelBone("skirt_" + i2 + "_1");
      Vec3d vec3d6 = girl.getModelBone("skirt_" + i2 + "_2");

      RgbaColor4 rgbaColor4;
      label17: {
         try {
            if (i % 2 == 0) {
               rgbaColor4 = x;
               break label17;
            }
         } catch (NumberFormatException error) {
            throw rethrow(error);
         }

         rgbaColor4 = r;
      }

      RgbaColor4 rgbaColor42 = rgbaColor4;
      bufferBuilder.pos(vec3d.x, vec3d.y, vec3d.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
      bufferBuilder.pos(vec3d2.x, vec3d2.y, vec3d2.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
      bufferBuilder.pos(vec3d5.x, vec3d5.y, vec3d5.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
      bufferBuilder.pos(vec3d4.x, vec3d4.y, vec3d4.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
      bufferBuilder.pos(vec3d2.x, vec3d2.y, vec3d2.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
      bufferBuilder.pos(vec3d5.x, vec3d5.y, vec3d5.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
      bufferBuilder.pos(vec3d6.x, vec3d6.y, vec3d6.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
      bufferBuilder.pos(vec3d3.x, vec3d3.y, vec3d3.z).color(rgbaColor42.R, rgbaColor42.G, rgbaColor42.B, rgbaColor42.A).endVertex();
   }

   @Override

   protected void a(BufferBuilder bufferBuilder, String string, GeoBone bone) {
        block12: {
            Entity entity;
            block11: {
                ManglelieNpcRenderer.applyModelPartColor(this.RenderEntity, string, bone, false);
                entity = ((ManglelieNpc)this.RenderEntity).b();
                try {
                    if (entity == null) {
                        return;
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    throw ManglelieNpcRenderer.rethrow(numberFormatException);
                }
                try {
                    try {
                        if (!"weapon".equals(string) || !((ManglelieNpc)this.RenderEntity).a(entity, KoboldEggEntity.getRenderPartialTicks())) break block11;
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw ManglelieNpcRenderer.rethrow(numberFormatException);
                    }
                    this.a(bufferBuilder, bone, true);
                }
                catch (NumberFormatException numberFormatException) {
                    throw ManglelieNpcRenderer.rethrow(numberFormatException);
                }
            }
            try {
                try {
                    if (!"offhand".equals(string) || ((ManglelieNpc)this.RenderEntity).a(entity, KoboldEggEntity.getRenderPartialTicks())) break block12;
                }
                catch (NumberFormatException numberFormatException) {
                    throw ManglelieNpcRenderer.rethrow(numberFormatException);
                }
                this.a(bufferBuilder, bone, false);
            }
            catch (NumberFormatException numberFormatException) {
                throw ManglelieNpcRenderer.rethrow(numberFormatException);
            }
        }
    }

   void a(BufferBuilder bufferBuilder, GeoBone bone, boolean flag) {
      ItemRenderer item = Minecraft.getMinecraft().getItemRenderer();

      label22: {
         try {
            GlStateManager.pushMatrix();
            Tessellator.getInstance().draw();
            MatrixUtil.applyGeoBoneTransform(IGeoRenderer.MATRIX_STACK, bone);
            GL11.glEnable(2896);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            if (flag) {
               GlStateManager.translate(-0.01, 0.0, 0.0);
               GlStateManager.rotate(120.0F, 1.0F, 0.0F, 0.0F);
               break label22;
            }
         } catch (NumberFormatException error) {
            throw rethrow(error);
         }

         GlStateManager.translate(0.15, 0.0, -0.05);
         GlStateManager.rotate(-140.0F, 1.0F, 0.0F, 0.0F);
      }

      GlStateManager.scale(0.7, 0.7, 0.7);
      ItemStack stack = new ItemStack(Items.BOW);
      float f = this.RenderEntity.b(KoboldEggEntity.getRenderPartialTicks());
      if (f < 1.0F) {
         float f2 = (float)LerpMath.EaseOutQuart(f);
         this.RenderEntity.d((int)(11.0F * (1.0F - f2) + 71980.0F));
         this.RenderEntity.a(stack);
         this.RenderEntity.setActiveHand(EnumHand.MAIN_HAND);
         this.RenderEntity.W();
      } else {
         this.RenderEntity.a(ItemStack.EMPTY);
         this.RenderEntity.K();
      }

      item.renderItem(this.RenderEntity, stack, TransformType.THIRD_PERSON_RIGHT_HAND);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
      GL11.glDisable(2896);
      GlStateManager.popMatrix();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
   }


   public static void applyModelPartColor(GirlEntity girl, String string, GeoBone bone, boolean flag) {
        block25: {
            float f;
            String string2;
            int i;
            block26: {
                block23: {
                    block24: {
                        try {
                            if (!string.contains("skirt_")) {
                                return;
                            }
                        }
                        catch (NumberFormatException numberFormatException) {
                            throw ManglelieNpcRenderer.rethrow(numberFormatException);
                        }
                        i = ManglelieNpcRenderer.parseSkirtBoneIndex(string);
                        try {
                            try {
                                if (!MathUtils.isInRange((double)i, 17.0, 35.0)) break block23;
                                if (!KoboldEggEntity.isGamePaused()) break block24;
                            }
                            catch (NumberFormatException numberFormatException) {
                                throw ManglelieNpcRenderer.rethrow(numberFormatException);
                            }
                            return;
                        }
                        catch (NumberFormatException numberFormatException) {
                            throw ManglelieNpcRenderer.rethrow(numberFormatException);
                        }
                    }
                    try {
                        String string3 = string2 = i < 26 ? "cheekL" : "cheekR";
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw ManglelieNpcRenderer.rethrow(numberFormatException);
                    }
                    if (flag) {
                        string2 = string2 + "2";
                    }
                    f = AngleMath.d(girl.b().getBone(string2).getRotationX());
                    try {
                        if (f < 0.0f) {
                            return;
                        }
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw ManglelieNpcRenderer.rethrow(numberFormatException);
                    }
                    bone.setPositionY(bone.getPositionY() + f * 0.01f);
                }
                try {
                    try {
                        if (!MathUtils.isInRange((double)i, 1.0, 11.0)) break block25;
                        if (string.endsWith("1")) break block26;
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw ManglelieNpcRenderer.rethrow(numberFormatException);
                    }
                    return;
                }
                catch (NumberFormatException numberFormatException) {
                    throw ManglelieNpcRenderer.rethrow(numberFormatException);
                }
            }
            try {
                String string4 = string2 = i < 6 ? "legR" : "legL";
            }
            catch (NumberFormatException numberFormatException) {
                throw ManglelieNpcRenderer.rethrow(numberFormatException);
            }
            if (flag) {
                string2 = string2 + "2";
            }
            f = AngleMath.d(girl.b().getBone(string2).getRotationX());
            try {
                if (f < 0.0f) {
                    return;
                }
            }
            catch (NumberFormatException numberFormatException) {
                throw ManglelieNpcRenderer.rethrow(numberFormatException);
            }
            bone.setRotationX(AngleMath.degToRadians(f));
            bone.setPositionY(AngleMath.degToRadians(f * 0.03f));
        }
    }

   static int parseSkirtBoneIndex(String string) {
      int i = string.indexOf(95);
      int i2 = string.indexOf(95, i + 1);

      try {
         if (i == -1 || i2 == -1) {
            return -1;
         }
      } catch (NumberFormatException error) {
         throw rethrow(error);
      }

      String string2 = string.substring(i + 1, i2);

      try {
         return Integer.parseInt(string2);
      } catch (NumberFormatException error2) {
         return -1;
      }
   }

   protected void a(GeoModel model, BufferBuilder bufferBuilder, ManglelieNpc manglelie, float f, float f2, float f3, float f4, float f5) {
      try {
         if (!ModelManglelie.isInThreesomeAnimation(manglelie)) {
            super.a(model, bufferBuilder, manglelie, f, f2, f3, f4, f5);
            return;
         }
      } catch (IOException error) {
         throw rethrow(error);
      }

      GeoBone bone = (GeoBone)model.topLevelBones.get(0);
      GeoBone bone2 = null;
      GeoBone bone3 = null;

      for (GeoBone bone4 : bone.childBones) {
         String string = bone4.getName();
         byte bv = -1;

         label48: {
            label47: {
               try {
                  switch (string.hashCode()) {
                     case 93911760:
                        break;
                     case 109761491:
                        if (!string.equals("steve")) {
                           break label48;
                        }
                        break label47;
                     default:
                        break label48;
                  }
               } catch (IOException error2) {
                  throw rethrow(error2);
               }

               if (string.equals("body2")) {
                  bv = 1;
               }
               break label48;
            }

            bv = 0;
         }

         switch (bv) {
            case 0:
               bone3 = bone4;
               break;
            case 1:
               bone2 = bone4;
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
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

      try {
         Minecraft.getMinecraft().renderEngine.bindTexture(this.d(this.RenderEntity));
      } catch (IOException error3) {
         error3.printStackTrace();
      }

      this.renderRecursively(bufferBuilder, bone3, f, f2, f3, this.RenderEntity.getMommyUuid());
      Tessellator.getInstance().draw();
      MATRIX_STACK.pop();
   }

   static void renderGirlGeometryWithAlpha(GirlEntity girl, BufferBuilder bufferBuilder, Tessellator tessellator, float f) {
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      Vec3d[][] vec3dArray = VectorUtil.getGirlBonePositionsFiltered(girl, f, "clothBoobLconStart", "clothBoobLconEnd", D, v);
      Vec3d[][] vec3dArray2 = VectorUtil.getGirlBonePositionsFiltered(girl, f, "clothBoobRconStart", "clothBoobRconEnd", D, v);
      Vec3d[][] vec3dArray3 = VectorUtil.getGirlBonePositionsFiltered(girl, f, "clothBoobMidconStart", "clothBoobMidconEnd", z, z);
      VectorUtil.drawBoneQuads(bufferBuilder, vec3dArray, C);
      VectorUtil.drawBoneQuads(bufferBuilder, vec3dArray2, C);
      VectorUtil.drawBoneQuads(bufferBuilder, vec3dArray3, C);
      tessellator.draw();
   }

   @Override
   public boolean a(HashSet set, GeoBone bone) {
      while (bone.parent != null) {
         String string = bone.getName();

         try {
            if (string.contains("clothBoob")) {
               return true;
            }
         } catch (NumberFormatException error) {
            throw rethrow(error);
         }

         try {
            if (set.contains(string)) {
               return false;
            }
         } catch (NumberFormatException error2) {
            throw rethrow(error2);
         }

         try {
            if (string.startsWith("armor")) {
               return false;
            }
         } catch (NumberFormatException error3) {
            throw rethrow(error3);
         }

         bone = bone.parent;
      }

      return true;
   }

   protected Vec3d a(ManglelieNpc manglelie, float f, Vec3d vec3d) {
      if (manglelie.getCurrentAction() == GirlAnimationState.RUN) {
         float f2 = manglelie.I();
         manglelie.rotationYaw = f2;
         manglelie.prevRenderYawOffset = f2;
         manglelie.renderYawOffset = f2;
         manglelie.prevRotationYawHead = f2;
         manglelie.rotationYawHead = f2;
         return vec3d;
      }

      if (isManglelieNpc(manglelie)) {
         GalathNpc galath = manglelie.getMommy(false);

         try {
            if (galath != null) {
               renderNpcWithLiving(galath, f, manglelie);
               return rotateHeadToTwin(galath, f);
            }
         } catch (NumberFormatException error) {
            throw rethrow(error);
         }
      }

      return vec3d;
   }

   public static void renderNpcWithLiving(GalathNpc galath, float f, EntityLivingBase livingBase) {
      boolean flag = galath.Q();

      float f2;
      label35: {
         try {
            if (flag) {
               f2 = galath.I();
               break label35;
            }
         } catch (NumberFormatException error) {
            throw rethrow(error);
         }

         f2 = galath.rotationYawHead;
      }

      float f3 = f2;

      label27: {
         try {
            if (flag) {
               f2 = galath.I();
               break label27;
            }
         } catch (NumberFormatException error2) {
            throw rethrow(error2);
         }

         f2 = galath.prevRotationYawHead;
      }

      float f4 = f2;
      Float f5 = GalathNpc.rotateToTarget(galath, f);
      if (f5 != null) {
         f3 = f5;
         f4 = f5;
      }

      livingBase.rotationYaw = f3;
      livingBase.prevRenderYawOffset = f4;
      livingBase.renderYawOffset = f3;
      livingBase.prevRotationYawHead = f4;
      livingBase.rotationYawHead = f3;
   }


   public static boolean isManglelieNpc(ManglelieNpc manglelie) {
        boolean flag;
        block5: {
            block4: {
                try {
                    try {
                        if (!manglelie.isClaimed() || ModelManglelie.isInThreesomeAnimation(manglelie)) break block4;
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw ManglelieNpcRenderer.rethrow(numberFormatException);
                    }
                    flag = true;
                    break block5;
                }
                catch (NumberFormatException numberFormatException) {
                    throw ManglelieNpcRenderer.rethrow(numberFormatException);
                }
            }
            flag = false;
        }
        return flag;
    }

   public static Vec3d b(GalathNpc galath, float f) {
      return VecMath.a(galath, KoboldEggEntity.player, f).add(galath.getModelBone("mangPos"));
   }

   public static Vec3d a(GalathNpc galath, float f) {
      return VecMath.getPositionOffset(galath, f).add(galath.getModelBone("mangPos"));
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
