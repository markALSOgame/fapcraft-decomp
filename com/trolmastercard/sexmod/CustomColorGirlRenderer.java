package com.trolmastercard.sexmod;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;

public abstract class CustomColorGirlRenderer extends GirlPlayerRenderer {
   protected static final Vec3i DefaultColor = new Vec3i(255, 255, 255);
   static HashMap<Integer, Vec3i> ColorCache = new HashMap<>();

   public CustomColorGirlRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   public static void clearColorCache() {
      ColorCache.clear();
   }

   protected Vec3i a(GeoBone bone) {
      String string = bone.getName();
      int i = string.hashCode() + this.RenderEntity.getPersistentID().hashCode();
      Vec3i vec3i = ColorCache.get(i);

      try {
         if (vec3i != null) {
            return vec3i;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      vec3i = this.a(string);
      ColorCache.put(i, vec3i);
      return vec3i;
   }

   protected abstract Vec3i a(String string);

   protected void b(GeoBone bone, int i) {
      List list = bone.childBones;

      for (int i2 = 0; i2 < list.size(); i2++) {
         GeoBone bone2 = (GeoBone)list.get(i2);
         if (i == i2) {
            GeoBone bone3 = bone2;
            bone3.setHidden(false);
            return;
         }
      }
   }

   protected float float_a() {
      return 1.0F;
   }

   protected Vec3d getItemRenderRotation(ItemStack stack) {
      return new Vec3d(-90.0, 0.0, 0.0);
   }

   protected GeoBone a(GeoBone bone, int i) {
      List list = bone.childBones;
      GeoBone bone2 = null;
      list.sort(Comparator.comparingDouble(GeoBone::getPivotY));

      for (int i2 = 0; i2 < list.size(); i2++) {
         GeoBone bone3 = (GeoBone)list.get(i2);
         if (i == i2) {
            bone2 = bone3;
            bone2.setHidden(false);
         } else {
            bone3.setHidden(true);
         }
      }

      return bone2;
   }

   protected Vec3i a(Vec3i vec3i) {
      return vec3i;
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

         if (string.equals("legL") || string.equals("legR")) {
            bone.setPositionZ(bone.getPositionZ() + 1.0F);
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

      CustomColorGirlRenderer.MATRIX_STACK.push();
      CustomColorGirlRenderer.MATRIX_STACK.translate(bone);
      CustomColorGirlRenderer.MATRIX_STACK.moveToPivot(bone);
      CustomColorGirlRenderer.MATRIX_STACK.rotate(bone);
      CustomColorGirlRenderer.MATRIX_STACK.scale(bone);
      CustomColorGirlRenderer.MATRIX_STACK.moveBackFromPivot(bone);
      if ("Head2".equals(string) && !this.c()) {
         CustomColorGirlRenderer.MATRIX_STACK.pop();
         return;
      }

      if (("neck".equals(string) || "head".equals(string)) && !super.boolean_a()) {
         CustomColorGirlRenderer.MATRIX_STACK.pop();
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
               CustomColorGirlRenderer.MATRIX_STACK.push();
               GlStateManager.pushMatrix();
               this.CachedBone = bone;
               this.a(bufferBuilder, cube, bone, f, f2, f3, f4, d);
               GlStateManager.popMatrix();
               CustomColorGirlRenderer.MATRIX_STACK.pop();
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
         CustomColorGirlRenderer.MATRIX_STACK.pop();
      }
      catch (IllegalStateException illegalStateException) {
         // empty catch block
      }
   }


   public void a(BufferBuilder bufferBuilder, GeoCube geoCube, GeoBone bone, float f, float f2, float f3, float f4, double d) {
        MATRIX_STACK.moveToPivot(geoCube);
        MATRIX_STACK.rotate(geoCube);
        MATRIX_STACK.moveBackFromPivot(geoCube);
        for (GeoQuad geoQuad : geoCube.quads) {
            Vec3d vec3d;
            Vector3f vector3f;
            block27: {
                block26: {
                    block25: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw CustomColorGirlRenderer.rethrow(illegalStateException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.getX(), (float)geoQuad.normal.getY(), (float)geoQuad.normal.getZ());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block25;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw CustomColorGirlRenderer.rethrow(illegalStateException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block25;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw CustomColorGirlRenderer.rethrow(illegalStateException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw CustomColorGirlRenderer.rethrow(illegalStateException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block26;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw CustomColorGirlRenderer.rethrow(illegalStateException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block26;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw CustomColorGirlRenderer.rethrow(illegalStateException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw CustomColorGirlRenderer.rethrow(illegalStateException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block27;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw CustomColorGirlRenderer.rethrow(illegalStateException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block27;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw CustomColorGirlRenderer.rethrow(illegalStateException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (IllegalStateException illegalStateException) {
                    throw CustomColorGirlRenderer.rethrow(illegalStateException);
                }
            }
            if (this.c(bone.getName())) {
                vec3d = new Vec3d((double)f, (double)f2, (double)f3);
            } else {
                Vec3i vec3i = this.a((Vec3i)this.a(bone));
                vec3d = BoneColorHelper.applyBoneColor(this, bone, new Vec3d((double)((float)vec3i.getX() / 255.0f), (double)((float)vec3i.getY() / 255.0f), (double)((float)vec3i.getZ() / 255.0f)), vector3f);
            }
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.pos((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).tex((double)geoVertex.textureU + d, (double)geoVertex.textureV).color((float)vec3d.x, (float)vec3d.y, (float)vec3d.z, f4).normal(vector3f.getX(), vector3f.getY(), vector3f.getZ()).endVertex();
            }
        }
    }

   protected boolean c(String string) {
      return string.startsWith("armor");
   }

   private static IllegalStateException rethrow(IllegalStateException error) {
      return error;
   }
}
