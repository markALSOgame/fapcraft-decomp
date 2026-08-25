package com.trolmastercard.sexmod;

import java.util.Objects;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;

public class GirlPlayerRenderer extends GeoGirlRenderer<GirlEntity> {
   public static boolean RenderTrigger = false;
   ItemStack MainHandStack = ItemStack.EMPTY;
   ItemStack OffHandStack = ItemStack.EMPTY;
   boolean r = false;
   boolean u = false;
   protected PlayerGirlEntity CurrentGirl;
   protected float RenderTick;
   float t = 0.0F;

   public GirlPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel, 0.0);
   }

   public void doRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2) {
   }

   boolean shouldRender(GirlEntity girl) {
      try {
         if (girl.isTracked()) {
            return true;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      boolean flag = RenderTrigger;
      RenderTrigger = false;
      return flag;
   }

   @Override
   public void doRender(GirlEntity girl, double d, double d2, double d3, float f, float f2) {
      try {
         if (!this.shouldRender(girl)) {
            return;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity playerGirl = (PlayerGirlEntity)girl;

      try {
         if (playerGirl.getBoundPlayerUuid() == null) {
            return;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player2 = Minecraft.getMinecraft().player.world.getPlayerEntityByUUID(playerGirl.getBoundPlayerUuid());

      try {
         if (player2 == null) {
            return;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      try {
         this.MainHandStack = player2.getHeldItemMainhand();
         this.OffHandStack = player2.getHeldItemOffhand();
         this.u = playerGirl.ah;
         this.r = playerGirl.ad;
         this.CurrentGirl = (PlayerGirlEntity)girl;
         this.RenderTick = f2;
         playerGirl.syncEquipment(player2);
         if (this.a(player2, girl)) {
            this.renderLivingLabel(girl, player2.getName(), d, d2 + playerGirl.getRenderLabelOffset(), d3, 300);
         }
      } catch (IllegalStateException error4) {
         throw rethrow(error4);
      }

      super.doRender(girl, d, d2, d3, f, f2);
   }

   @Override
   public Entity c(GirlEntity girl) {
      try {
         if (!(girl instanceof PlayerGirlEntity)) {
            return girl;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity playerGirl = (PlayerGirlEntity)girl;
      EntityPlayer player = playerGirl.getBoundPlayer();

      try {
         return (Entity)(player == null ? girl : player);
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }
   }

   boolean a(EntityPlayer player2, GirlEntity girl) {
      try {
         if (player2.getPersistentID().equals(Minecraft.getMinecraft().player.getPersistentID())) {
            return false;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      GirlAnimationState girlAnimationState = girl.getCurrentAction();

      try {
         if (girlAnimationState == null) {
            return true;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if (!girlAnimationState.hideNameTag) {
            return true;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      return false;
   }

   protected void applyBoneState(String string, GeoBone bone) {
   }

   protected void a(String string, GeoBone bone, PlayerGirlEntity playerGirl, BufferBuilder bufferBuilder) {
   }

   @Override

   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
      String string = bone.getName();
      if (this.r) {
         if (string.equals("upperBody")) {
            bone.setRotationX(bone.getRotationX() - 0.5F);
         }

         if (string.equals("head")) {
            bone.setRotationX(bone.getRotationX() + 0.5F);
         }
      }

      if (string.equals("head")) {
         this.a(bufferBuilder, bone, Color.ofRGB(f, f2, f3));
      }

      this.applyBoneState(string, bone);
      this.a(string, bone, this.CurrentGirl, bufferBuilder);
      if (this.u && (this.MainHandStack.getItem() instanceof ItemBow || this.OffHandStack.getItem() instanceof ItemBow)) {
         if (string.equals("armR")) {
            bone.setRotationX(bone.getRotationX() - this.RenderEntity.rotationPitch / 50.0F);
         }

         if (string.equals("armL")) {
            bone.setRotationY(bone.getRotationY() - this.RenderEntity.rotationPitch / 50.0F);
         }

         if (this.OffHandStack.getItem() instanceof ItemBow) {
            ItemStack stack = this.OffHandStack;
            this.OffHandStack = this.MainHandStack;
            this.MainHandStack = stack;
         }
      }

      if (this.u && this.MainHandStack.getItem() instanceof ItemShield) {
         if (string.equals("armR")) {
            bone.setRotationZ(0.0F);
            bone.setRotationX(0.5F);
         } else if (this.OffHandStack.getItem() instanceof ItemShield && string.equals("armL")) {
            bone.setRotationZ(0.0F);
            bone.setRotationX(0.5F);
         }
      }

      if (string.equals("weapon") && !this.MainHandStack.isEmpty()) {
         this.a(bufferBuilder, bone, false);
      }

      if (string.equals("offhand") && !this.OffHandStack.isEmpty()) {
         this.a(bufferBuilder, bone, true);
      }

      GirlPlayerRenderer.MATRIX_STACK.push();
      GirlPlayerRenderer.MATRIX_STACK.translate(bone);
      GirlPlayerRenderer.MATRIX_STACK.moveToPivot(bone);
      GirlPlayerRenderer.MATRIX_STACK.rotate(bone);
      GirlPlayerRenderer.MATRIX_STACK.scale(bone);
      GirlPlayerRenderer.MATRIX_STACK.moveBackFromPivot(bone);
      if ("Head2".equals(string) && !this.c()) {
         GirlPlayerRenderer.MATRIX_STACK.pop();
         return;
      }

      if (("neck".equals(string) || "head".equals(string)) && !this.boolean_a()) {
         GirlPlayerRenderer.MATRIX_STACK.pop();
         return;
      }

      if (!bone.isHidden) {
         Vector4f vector4f = this.a(string, f, f2, f3);
         f = vector4f.x;
         f2 = vector4f.y;
         f3 = vector4f.z;
         double d = vector4f.w;
         if (!this.ProcessedBones.contains(string)) {
            for (GeoCube cube : bone.childCubes) {
               GirlPlayerRenderer.MATRIX_STACK.push();
               GlStateManager.pushMatrix();
               this.CachedBone = bone;
               this.a(bufferBuilder, cube, f, f2, f3, f4, d);
               GlStateManager.popMatrix();
               GirlPlayerRenderer.MATRIX_STACK.pop();
            }
         }

         for (GeoBone bone2 : bone.childBones) {
            if (d == 0.0) {
               this.renderRecursively(bufferBuilder, bone2, f, f2, f3, f4);
               continue;
            }

            this.a(bufferBuilder, bone2, f, f2, f3, f4, d);
         }
      }

      try {
         GirlPlayerRenderer.MATRIX_STACK.pop();
      }
      catch (IllegalStateException illegalStateException) {
         // empty catch block
      }
   }


   boolean boolean_a() {
        boolean flag;
        block11: {
            block10: {
                try {
                    if (!((PlayerGirlEntity)this.RenderEntity).isBoundToLocalPlayer()) {
                        return true;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
                try {
                    if (GirlPlayerRenderer.Mc.gameSettings.thirdPersonView != 0) {
                        return true;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
                try {
                    try {
                        if (!(GirlPlayerRenderer.Mc.currentScreen instanceof GuiInventory) && !(GirlPlayerRenderer.Mc.currentScreen instanceof GuiContainerCreative)) break block10;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GirlPlayerRenderer.rethrow(illegalStateException);
                    }
                    flag = true;
                    break block11;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
            }
            flag = false;
        }
        return flag;
    }

   void a(BufferBuilder bufferBuilder, GeoBone bone, Color color) {
      GlStateManager.pushMatrix();
      Tessellator.getInstance().draw();
      MatrixUtil.applyGeoBoneTransform(IGeoRenderer.MATRIX_STACK, bone);
      GL11.glEnable(2896);
      this.applyScaleOffset();
      new GirlElytraRenderer(this).render(this.RenderEntity, this.RenderEntity.limbSwing, this.RenderEntity.limbSwingAmount, this.RenderTick, 0.0F, 0.0F, 0.0F, color);
      this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      GL11.glDisable(2896);
      GlStateManager.popMatrix();
   }

   protected void applyScaleOffset() {
   }


   void a(BufferBuilder bufferBuilder, GeoBone bone, boolean flag) {
      ItemRenderer itemRenderer = Minecraft.getMinecraft().getItemRenderer();
      GlStateManager.pushMatrix();
      Tessellator.getInstance().draw();
      MatrixUtil.applyGeoBoneTransform(IGeoRenderer.MATRIX_STACK, bone);
      GL11.glEnable(2896);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      ItemStack stack = flag ? this.OffHandStack : this.MainHandStack;
      switch (stack.getItem().getItemUseAction(stack)) {
         case BOW:
            this.applyHandOffset(flag);
            break;
         case BLOCK:
            this.a(flag, this.u);
      }

      if (this.u && !flag && stack.getItem() instanceof ItemBow) {
          this.t += 0.015F;
          this.RenderEntity.d(Math.round(-this.t * 20.0F + (float)stack.getMaxItemUseDuration()));
          this.RenderEntity.setActiveItemStack(stack);
         this.RenderEntity.setActiveHand(EnumHand.MAIN_HAND);
         this.RenderEntity.W();
      } else {
          this.t = 0.0F;
          this.RenderEntity.d(0);
          this.RenderEntity.setActiveItemStack(ItemStack.EMPTY);
         this.RenderEntity.W();
      }

      this.applyHeldItemTransform(flag, stack);
      GlStateManager.scale(0.75F, 0.75F, 0.75F);
      itemRenderer.renderItem(this.RenderEntity, stack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
      GL11.glDisable(2896);
      GlStateManager.popMatrix();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
   }

   protected void applyHeldItemTransform(boolean flag, ItemStack stack) {
      float f;
      label16: {
         try {
            if (flag) {
               f = 200.0F;
               break label16;
            }
         } catch (IllegalStateException error) {
            throw rethrow(error);
         }

         f = 90.0F;
      }

      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
   }

   protected void applyHandOffset(boolean flag) {
      GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
   }


   protected void applyDualHandOffset(boolean flag, boolean flag2) {
        block8: {
            block7: {
                try {
                    try {
                        if (!flag) break block7;
                        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        if (!flag2) break block8;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GirlPlayerRenderer.rethrow(illegalStateException);
                    }
                    GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.rotate((float)35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.rotate((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.228f);
                    break block8;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
            }
            try {
                if (flag2) {
                    GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.translate((float)0.0f, (float)0.165f, (float)0.0f);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw GirlPlayerRenderer.rethrow(illegalStateException);
            }
        }
    }

   private static IllegalStateException rethrow(IllegalStateException error) {
      return error;
   }
}
