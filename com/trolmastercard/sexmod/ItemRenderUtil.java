package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import javax.vecmath.Tuple3f;
import net.minecraft.entity.Entity;

public class ItemRenderUtil extends GeoItemRenderer<ItemDragonStaff> {
   private static final ResourceLocation CrystalTexture = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
   private final ModelLamp CrystalModel = new ModelLamp();
   static final float p = 10.0F;
   static final float f = 1.5F;
   static final float m = 0.175F;
   static final float r = 0.1F;
   static final float g = 0.04F;
   static final float d = 8.0F;
   static final float i = 6.0F;
   static final float a = 1.3F;
   static final Vector2f[] l = new Vector2f[]{
      new Vector2f(1.0F, 0.0F),
      new Vector2f(0.0F, 1.0F),
      new Vector2f(0.0F, 0.0F),
      new Vector2f(0.5F, 0.5F),
      new Vector2f(0.75F, 0.25F),
      new Vector2f(0.25F, 0.75F),
      new Vector2f(0.25F, 0.75F)
   };
   static boolean IsAnimated = false;
   Minecraft Mc;
   Vector2f Offset2f;
   double AnimationTime = 0.0;
   EntityPlayer OwnerPlayer;
   ItemStack HeldItem;
   static HashMap<ItemStack, Vector3f> RotationCache = new HashMap<>();

   public ItemRenderUtil() {
      super(new ModelDragonStaff());
      this.Mc = Minecraft.getMinecraft();
   }

   public static boolean isAnimated() {
      return IsAnimated;
   }

   public static void toggleAnimated() {
      boolean flag;
      label16: {
         try {
            if (!IsAnimated) {
               flag = true;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag = false;
      }

      IsAnimated = flag;
   }

   public void a(ItemDragonStaff item, ItemStack stack) {
      EntityPlayer player2 = null;

      for (EntityPlayer player3 : this.Mc.world.playerEntities) {
         if (player3.inventory.mainInventory.contains(stack)) {
            player2 = player3;
            break;
         }

         if (player3.inventory.offHandInventory.contains(stack)) {
            player2 = player3;
            break;
         }
      }

      if (player2 != null) {
         double d = player2.posX - player2.lastTickPosX;
         double d2 = player2.posZ - player2.lastTickPosZ;
         double d3 = (Math.PI / 180.0) * player2.rotationYaw;
         this.Offset2f = new Vector2f((float)(d * Math.cos(d3) + d2 * Math.sin(d3)), (float)(-d * Math.sin(d3) + d2 * Math.cos(d3)));
      } else {
         this.Offset2f = new Vector2f(0.0F, 0.0F);
      }

      try {
         if (!Minecraft.getMinecraft().isGamePaused()) {
            this.AnimationTime = Minecraft.getMinecraft().player.ticksExisted + this.Mc.getRenderPartialTicks();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.HeldItem = stack;
      this.OwnerPlayer = player2;
      super.render(item, stack);
   }


   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
        if ("staff".equals(bone.getName())) {
            float f5;
            float f6;
            Vector3f vector3f;
            Vector3f vector3f2;
            Vector3f vector3f3;
            GlStateManager.pushMatrix();
            Tessellator.getInstance().draw();
            MatrixUtil.applyGeoBoneTransform(IGeoRenderer.MATRIX_STACK, bone);
            GlStateManager.translate((double)0.0, (double)(1.5 + 0.001 * Math.sin(0.005 * this.AnimationTime) + 0.001), (double)0.0);
            Vector3f vector3f4 = RotationCache.get(this.HeldItem);
            GlStateManager.scale((double)this.getStaffScale(), (double)this.getStaffScale(), (double)this.getStaffScale());
            if (vector3f4 == null) {
                vector3f4 = new Vector3f(0.0f, 0.0f, 0.0f);
            }
            try {
                Vector3f vector3f5;
                vector3f3 = vector3f4;
                vector3f2 = vector3f5;
                vector3f = vector3f5;
                f6 = this.Offset2f.x;
                f5 = this.OwnerPlayer == null ? 0.0f : (float)(this.OwnerPlayer.posY - this.OwnerPlayer.lastTickPosY);
            }
            catch (RuntimeException runtimeException) {
                throw ItemRenderUtil.rethrow(runtimeException);
            }
            vector3f2(f6, f5, this.Offset2f.y);
            vector3f3.add((Tuple3f)vector3f);
            GlStateManager.rotate((float)(vector3f4.z * 10.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)(vector3f4.x * 10.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)(-vector3f4.y * 10.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.rotate((float)((float)(this.AnimationTime * (double)0.1f)), (float)1.0f, (float)1.0f, (float)1.0f);
            RotationCache.put(this.HeldItem, vector3f4);
            this.Mc.getTextureManager().bindTexture(CrystalTexture);
            this.CrystalModel.render((Entity)Minecraft.getMinecraft().player, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GlStateManager.popMatrix();
            if (this.OwnerPlayer != null) {
                this.computeStaffRotation();
            }
            this.Mc.getTextureManager().bindTexture(new ModelDragonStaff().a(null));
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
        }
        super.renderRecursively(bufferBuilder, bone, f, f2, f3, f4);
    }

   void computeStaffRotation() {
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();

      for (Vector4d vector4d : KoboldNpc.MemberData) {
         list.add((int)vector4d.getW());
         list2.add(new Vec3d(vector4d.getX(), vector4d.getY(), vector4d.getZ()));
      }

      try {
         if (list.size() == 0) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (IsAnimated) {
            this.a(list, list2);
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      this.a(list);
   }

   void a(List<Integer> list, List<Vec3d> list2) {
      for (int i = 0; i < list.size(); i++) {
         float f = LerpMath.lerp(this.OwnerPlayer.prevRotationYawHead, this.OwnerPlayer.rotationYawHead, this.Mc.getRenderPartialTicks());
         float f2 = LerpMath.lerp(this.OwnerPlayer.prevRotationPitch, this.OwnerPlayer.rotationPitch, this.Mc.getRenderPartialTicks());
         Vec3d vec3d = LerpMath.lerpVec3d(
            new Vec3d(this.OwnerPlayer.prevPosX, this.OwnerPlayer.prevPosY + this.OwnerPlayer.getEyeHeight(), this.OwnerPlayer.prevPosZ),
            this.OwnerPlayer.getPositionVector().add(0.0, this.OwnerPlayer.getEyeHeight(), 0.0),
            this.Mc.getRenderPartialTicks()
         );
         Vec3d vec3d2 = vec3d.subtract((Vec3d)list2.get(i));
         vec3d2 = VectorMath.rotatePitchYaw(vec3d2, -f2, f);
         double d = Math.abs(vec3d2.x) + Math.abs(vec3d2.z) + Math.abs(vec3d2.y);
         double d2 = -vec3d2.x / d;
         double d3 = -vec3d2.y / d;
         double d4 = vec3d2.z / d;
         d2 = this.a(d2);
         d3 = this.a(d3);
         d4 = this.a(d4);
         d2 *= 1.3F;
         d3 *= 1.3F;
         d4 *= 1.3F;
         this.b((Integer)list.get(i), (float)d2, (float)d3, (float)d4);
      }
   }

   void a(List<Integer> list) {
      float f = 1.0F / list.size();
      float f2 = 0.0F;

      for (int i = 0; i < list.size(); i++) {
         f2 += f;
         this.a((Integer)list.get(i), 1.0F - f2, 0.0F + f2, (float)LerpMath.lerpAngleDegrees(0.8F, 1.2F, (double)i / list.size()));
      }
   }

   double a(double d) {
      return d * Math.sqrt(1.0 - d * d / 2.0);
   }

   double getStaffScale() {
      return 0.175F + 0.025 * Math.sin(0.005 * this.AnimationTime) + 0.025;
   }

   void a(int i, float f, float f2, float f3) {
      this.a(new ItemStack(Blocks.WOOL, 1, i), f, f2, f3);
   }

   void b(int i, float f, float f2, float f3) {
      this.b(new ItemStack(Blocks.WOOL, 1, i), f, f2, f3);
   }

   void b(ItemStack stack, float f, float f2, float f3) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0, 1.5 + 0.001 * Math.sin(0.005 * this.AnimationTime) + 0.001, 0.0);
      GlStateManager.scale(0.04F, 0.04F, 0.04F);
      GlStateManager.translate(f * 6.0F, f2 * 6.0F, f3 * 6.0F);
      this.Mc.getItemRenderer().renderItem(Minecraft.getMinecraft().player, stack, TransformType.NONE);
      GlStateManager.popMatrix();
   }

   void a(ItemStack stack, float f, float f2, float f3) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0, 1.5 + 0.001 * Math.sin(0.005 * this.AnimationTime) + 0.001, 0.0);
      GlStateManager.scale(0.04F, 0.04F, 0.04F);
      GlStateManager.rotate((float)(this.AnimationTime * 8.0 * f3), 0.0F, f, f2);
      GlStateManager.translate(6.0F, 0.0F, 0.0F);
      this.Mc.getItemRenderer().renderItem(Minecraft.getMinecraft().player, stack, TransformType.NONE);
      GlStateManager.popMatrix();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
