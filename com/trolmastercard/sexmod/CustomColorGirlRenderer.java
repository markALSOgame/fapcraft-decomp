package com.trolmastercard.sexmod;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.model.AnimatedGeoModel;

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

   protected float a() {
      return 1.0F;
   }

   protected Vec3d a(ItemStack stack) {
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
        block72: {
            block84: {
                block71: {
                    block70: {
                        block69: {
                            block67: {
                                block68: {
                                    block78: {
                                        block65: {
                                            block66: {
                                                block76: {
                                                    block75: {
                                                        block63: {
                                                            block64: {
                                                                string = bone.getName();
                                                                if (!this.r) break block63;
                                                                try {
                                                                    block73: {
                                                                        if (!string.equals("upperBody")) break block64;
                                                                        break block73;
                                                                        catch (IllegalStateException error) {
                                                                            throw CustomColorGirlRenderer.rethrow(error);
                                                                        }
                                                                    }
                                                                    bone.setRotationX(bone.getRotationX() - 0.5f);
                                                                }
                                                                catch (IllegalStateException error2) {
                                                                    throw CustomColorGirlRenderer.rethrow(error2);
                                                                }
                                                            }
                                                            try {
                                                                if (string.equals("head")) {
                                                                    bone.setRotationX(bone.getRotationX() + 0.5f);
                                                                }
                                                            }
                                                            catch (IllegalStateException error3) {
                                                                throw CustomColorGirlRenderer.rethrow(error3);
                                                            }
                                                            if (string.equals("legL")) ** GOTO lbl28
                                                            try {
                                                                block74: {
                                                                    if (!string.equals("legR")) break block63;
                                                                    break block74;
                                                                    catch (IllegalStateException error4) {
                                                                        throw CustomColorGirlRenderer.rethrow(error4);
                                                                    }
                                                                }
                                                                bone.setPositionZ(bone.getPositionZ() + 1.0f);
                                                            }
                                                            catch (IllegalStateException error5) {
                                                                throw CustomColorGirlRenderer.rethrow(error5);
                                                            }
                                                        }
                                                        try {
                                                            if (string.equals("head")) {
                                                                this.a(bufferBuilder, bone, Color.ofRGB(f, f2, f3));
                                                            }
                                                        }
                                                        catch (IllegalStateException error6) {
                                                            throw CustomColorGirlRenderer.rethrow(error6);
                                                        }
                                                        this.a(string, bone);
                                                        this.a(string, bone, this.CurrentGirl, bufferBuilder);
                                                        if (!this.u) break block65;
                                                        if (this.MainHandStack.getItem() instanceof ItemBow) ** GOTO lbl55
                                                        break block75;
                                                        catch (IllegalStateException error7) {
                                                            throw CustomColorGirlRenderer.rethrow(error7);
                                                        }
                                                    }
                                                    if (!(this.OffHandStack.getItem() instanceof ItemBow)) break block65;
                                                    break block76;
                                                    catch (IllegalStateException error8) {
                                                        throw CustomColorGirlRenderer.rethrow(error8);
                                                    }
                                                }
                                                try {
                                                    block77: {
                                                        if (!string.equals("armR")) break block66;
                                                        break block77;
                                                        catch (IllegalStateException error9) {
                                                            throw CustomColorGirlRenderer.rethrow(error9);
                                                        }
                                                    }
                                                    bone.setRotationX(bone.getRotationX() - this.RenderEntity.rotationPitch / 50.0f);
                                                }
                                                catch (IllegalStateException error10) {
                                                    throw CustomColorGirlRenderer.rethrow(error10);
                                                }
                                            }
                                            try {
                                                if (string.equals("armL")) {
                                                    bone.setRotationY(bone.getRotationY() - this.RenderEntity.rotationPitch / 50.0f);
                                                }
                                            }
                                            catch (IllegalStateException error11) {
                                                throw CustomColorGirlRenderer.rethrow(error11);
                                            }
                                            if (this.OffHandStack.getItem() instanceof ItemBow) {
                                                stack = this.OffHandStack;
                                                this.OffHandStack = this.MainHandStack;
                                                this.MainHandStack = stack;
                                            }
                                        }
                                        if (!this.u) break block67;
                                        if (!(this.MainHandStack.getItem() instanceof ItemShield)) break block67;
                                        break block78;
                                        catch (IllegalStateException error12) {
                                            throw CustomColorGirlRenderer.rethrow(error12);
                                        }
                                    }
                                    try {
                                        block79: {
                                            if (!string.equals("armR")) break block68;
                                            break block79;
                                            catch (IllegalStateException error13) {
                                                throw CustomColorGirlRenderer.rethrow(error13);
                                            }
                                        }
                                        bone.setRotationZ(0.0f);
                                        bone.setRotationX(0.5f);
                                        break block67;
                                    }
                                    catch (IllegalStateException error14) {
                                        throw CustomColorGirlRenderer.rethrow(error14);
                                    }
                                }
                                if (!(this.OffHandStack.getItem() instanceof ItemShield)) break block67;
                                try {
                                    block80: {
                                        if (!string.equals("armL")) break block67;
                                        break block80;
                                        catch (IllegalStateException error15) {
                                            throw CustomColorGirlRenderer.rethrow(error15);
                                        }
                                    }
                                    bone.setRotationZ(0.0f);
                                    bone.setRotationX(0.5f);
                                }
                                catch (IllegalStateException error16) {
                                    throw CustomColorGirlRenderer.rethrow(error16);
                                }
                            }
                            if (!string.equals("weapon")) break block69;
                            try {
                                block81: {
                                    if (this.MainHandStack.isEmpty()) break block69;
                                    break block81;
                                    catch (IllegalStateException error17) {
                                        throw CustomColorGirlRenderer.rethrow(error17);
                                    }
                                }
                                this.a(bufferBuilder, bone, false);
                            }
                            catch (IllegalStateException error18) {
                                throw CustomColorGirlRenderer.rethrow(error18);
                            }
                        }
                        if (!string.equals("offhand")) break block70;
                        try {
                            block82: {
                                if (this.OffHandStack.isEmpty()) break block70;
                                break block82;
                                catch (IllegalStateException error19) {
                                    throw CustomColorGirlRenderer.rethrow(error19);
                                }
                            }
                            this.a(bufferBuilder, bone, true);
                        }
                        catch (IllegalStateException error20) {
                            throw CustomColorGirlRenderer.rethrow(error20);
                        }
                    }
                    CustomColorGirlRenderer.MATRIX_STACK.push();
                    CustomColorGirlRenderer.MATRIX_STACK.translate(bone);
                    CustomColorGirlRenderer.MATRIX_STACK.moveToPivot(bone);
                    CustomColorGirlRenderer.MATRIX_STACK.rotate(bone);
                    CustomColorGirlRenderer.MATRIX_STACK.scale(bone);
                    CustomColorGirlRenderer.MATRIX_STACK.moveBackFromPivot(bone);
                    if (!"Head2".equals(string)) break block71;
                    try {
                        block83: {
                            if (this.c()) break block71;
                            break block83;
                            catch (IllegalStateException error21) {
                                throw CustomColorGirlRenderer.rethrow(error21);
                            }
                        }
                        CustomColorGirlRenderer.MATRIX_STACK.pop();
                        return;
                    }
                    catch (IllegalStateException error22) {
                        throw CustomColorGirlRenderer.rethrow(error22);
                    }
                }
                if ("neck".equals(string)) ** GOTO lbl164
                if (!"head".equals(string)) break block72;
                break block84;
                catch (IllegalStateException error23) {
                    throw CustomColorGirlRenderer.rethrow(error23);
                }
            }
            try {
                block85: {
                    if (this.a()) break block72;
                    break block85;
                    catch (IllegalStateException error24) {
                        throw CustomColorGirlRenderer.rethrow(error24);
                    }
                }
                CustomColorGirlRenderer.MATRIX_STACK.pop();
                return;
            }
            catch (IllegalStateException error25) {
                throw CustomColorGirlRenderer.rethrow(error25);
            }
        }
        if (!bone.isHidden) {
            stack = this.a(string, f, f2, f3);
            f = stack.x;
            f2 = stack.y;
            f3 = stack.z;
            d = stack.w;
            if (!this.ProcessedBones.contains(string)) {
                for (Object obj : bone.childCubes) {
                    CustomColorGirlRenderer.MATRIX_STACK.push();
                    GlStateManager.pushMatrix();
                    this.CachedBone = bone;
                    this.a(bufferBuilder, (GeoCube)obj, bone, f, f2, f3, f4, d);
                    GlStateManager.popMatrix();
                    CustomColorGirlRenderer.MATRIX_STACK.pop();
                }
            }
            for (Object obj2 : bone.childBones) {
                try {
                    if (d == 0.0) {
                        this.renderRecursively(bufferBuilder, (GeoBone)obj2, f, f2, f3, f4);
                        continue;
                    }
                }
                catch (IllegalStateException error26) {
                    throw CustomColorGirlRenderer.rethrow(error26);
                }
                this.a(bufferBuilder, (GeoBone)obj2, f, f2, f3, f4, d);
            }
        }
        try {
            CustomColorGirlRenderer.MATRIX_STACK.pop();
        }
        catch (IllegalStateException error27) {
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
                GeoVertex[] geoVertexArray = this.a(bone);
                geoVertexArray = this.a((Vec3i)geoVertexArray);
                vec3d = BoneColorHelper.applyBoneColor(this, bone, new Vec3d((double)((float)geoVertexArray.getX() / 255.0f), (double)((float)geoVertexArray.getY() / 255.0f), (double)((float)geoVertexArray.getZ() / 255.0f)), vector3f);
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
