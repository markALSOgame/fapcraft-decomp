package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class WinchesterItemRenderer extends GeoItemRenderer<ItemWinchester> {
   static final Vec3d DefaultColor = new Vec3d(0.0, 1.0, 0.0);

   public WinchesterItemRenderer() {
      super(new ModelWinchester());
   }

   public void render(ItemWinchester item, ItemStack stack) {
      try {
         if (DevConsole.DevFloats[0] == 0.0F) {
            GL11.glDisable(2896);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      super.render(item, stack);
      GL11.glEnable(2896);
   }


   public void renderCube(BufferBuilder bufferBuilder, GeoCube geoCube, float f, float f2, float f3, float f4) {
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
                        catch (RuntimeException runtimeException) {
                            throw WinchesterItemRenderer.rethrow(runtimeException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.getX(), (float)geoQuad.normal.getY(), (float)geoQuad.normal.getZ());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block25;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw WinchesterItemRenderer.rethrow(runtimeException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block25;
                            }
                            catch (RuntimeException runtimeException) {
                                throw WinchesterItemRenderer.rethrow(runtimeException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (RuntimeException runtimeException) {
                            throw WinchesterItemRenderer.rethrow(runtimeException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block26;
                            }
                            catch (RuntimeException runtimeException) {
                                throw WinchesterItemRenderer.rethrow(runtimeException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block26;
                        }
                        catch (RuntimeException runtimeException) {
                            throw WinchesterItemRenderer.rethrow(runtimeException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw WinchesterItemRenderer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw WinchesterItemRenderer.rethrow(runtimeException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block27;
                    }
                    catch (RuntimeException runtimeException) {
                        throw WinchesterItemRenderer.rethrow(runtimeException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw WinchesterItemRenderer.rethrow(runtimeException);
                }
            }
            try {
                vec3d = DevConsole.DevFloats[0] == 0.0f ? BoneColorHelper.tintColor(new Vec3d((double)f, (double)f2, (double)f3), vector3f, DefaultColor) : new Vec3d((double)f, (double)f2, (double)f3);
            }
            catch (RuntimeException runtimeException) {
                throw WinchesterItemRenderer.rethrow(runtimeException);
            }
            Vec3d vec3d2 = vec3d;
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.pos((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).tex((double)geoVertex.textureU, (double)geoVertex.textureV).color((float)vec3d2.x, (float)vec3d2.y, (float)vec3d2.z, f4).normal(vector3f.getX(), vector3f.getY(), vector3f.getZ()).endVertex();
            }
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
