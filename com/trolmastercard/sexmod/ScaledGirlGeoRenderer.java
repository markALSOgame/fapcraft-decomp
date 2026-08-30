package com.trolmastercard.sexmod;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;

public abstract class ScaledGirlGeoRenderer<G extends GirlEffectEntity> extends GeoGirlRenderer<G> {
   protected static final Vec3i DefaultColor = new Vec3i(255, 255, 255);
   static HashMap<Integer, Vec3i> ColorCache = new HashMap<>();

   public ScaledGirlGeoRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
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
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      vec3i = this.getBoneColor(string);
      ColorCache.put(i, vec3i);
      return vec3i;
   }

   protected abstract Vec3i getBoneColor(String string2);

   protected static void b(GeoBone bone, int i) {
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

   @Override
   protected void a(BufferBuilder bufferBuilder, GeoBone bone) {
      ItemStack stack = this.a((ItemStack)null);
      float f = this.float_a();
      Vec3d vec3d = this.getItemRenderRotation(stack);

      try {
         if (stack == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GlStateManager.pushMatrix();
      Tessellator.getInstance().draw();
      MatrixUtil.applyGeoBoneTransform(IGeoRenderer.MATRIX_STACK, bone);
      GL11.glEnable(2896);
      GlStateManager.scale(f, f, f);
      GlStateManager.rotate((float)vec3d.x, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate((float)vec3d.y, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate((float)vec3d.z, 0.0F, 0.0F, 1.0F);
      Minecraft.getMinecraft().getItemRenderer().renderItem(this.RenderEntity, stack, TransformType.THIRD_PERSON_RIGHT_HAND);
      this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      GL11.glDisable(2896);
      GlStateManager.popMatrix();
   }

   protected float float_a() {
      return 1.0F;
   }

   protected Vec3d getItemRenderRotation(ItemStack stack) {
      return new Vec3d(-90.0, 0.0, 0.0);
   }

   protected static GeoBone a(GeoBone bone, int i) {
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

   public void renderBone(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4, double d) {
        block13: {
            try {
                if (((GirlEffectEntity)this.RenderEntity).world instanceof PreviewWorld) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ScaledGirlGeoRenderer.rethrow(runtimeException);
            }
            String string = bone.getName();
            try {
                if (string.equals("weapon")) {
                    this.a(bufferBuilder, bone);
                }
            }
            catch (RuntimeException runtimeException) {
                throw ScaledGirlGeoRenderer.rethrow(runtimeException);
            }
            try {
                try {
                    if (!string.equals("itemRenderer") || ((GirlEffectEntity)this.RenderEntity).getCurrentAction() != GirlAnimationState.PAYMENT) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                }
                this.b(bufferBuilder, bone);
            }
            catch (RuntimeException runtimeException) {
                throw ScaledGirlGeoRenderer.rethrow(runtimeException);
            }
        }
        this.applyBoneState(bufferBuilder, bone.getName(), bone);
        MATRIX_STACK.push();
        MATRIX_STACK.translate(bone);
        MATRIX_STACK.moveToPivot(bone);
        MATRIX_STACK.rotate(bone);
        MATRIX_STACK.scale(bone);
        MATRIX_STACK.moveBackFromPivot(bone);
        if (!bone.isHidden) {
            for (GeoCube object : bone.childCubes) {
                MATRIX_STACK.push();
                GlStateManager.pushMatrix();
                this.CachedBone = bone;
                this.a(bufferBuilder, object, bone, f, f2, f3, f4, d);
                GlStateManager.popMatrix();
                MATRIX_STACK.pop();
            }
            for (GeoBone geoBone2 : bone.childBones) {
                this.renderBone(bufferBuilder, geoBone2, f, f2, f3, f4, d);
            }
        }
        MATRIX_STACK.pop();
    }

   @Override
   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
      this.renderBone(bufferBuilder, bone, f, f2, f3, f4, 0.0);
   }


   public void a(BufferBuilder bufferBuilder, GeoCube geoCube, GeoBone bone, float f, float f2, float f3, float f4, double d) {
        MATRIX_STACK.moveToPivot(geoCube);
        MATRIX_STACK.rotate(geoCube);
        MATRIX_STACK.moveBackFromPivot(geoCube);
        for (GeoQuad geoQuad : geoCube.quads) {
            Vector3f vector3f;
            block25: {
                block24: {
                    block23: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.getX(), (float)geoQuad.normal.getY(), (float)geoQuad.normal.getZ());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block23;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block23;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block24;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block24;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block25;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block25;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw ScaledGirlGeoRenderer.rethrow(runtimeException);
                }
            }
            Vec3i vec3i = this.a(bone);
            vec3i = this.a(vec3i);
            Vec3d vec3d = BoneColorHelper.applyBoneColor(this, bone, new Vec3d((double)((float)vec3i.getX() / 255.0f), (double)((float)vec3i.getY() / 255.0f), (double)((float)vec3i.getZ() / 255.0f)), vector3f);
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.pos((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).tex((double)geoVertex.textureU + d, (double)geoVertex.textureV).color((float)vec3d.x, (float)vec3d.y, (float)vec3d.z, f4).normal(vector3f.getX(), vector3f.getY(), vector3f.getZ()).endVertex();
            }
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
