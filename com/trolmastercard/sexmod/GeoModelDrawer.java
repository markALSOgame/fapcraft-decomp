package com.trolmastercard.sexmod;

import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;
import net.minecraft.nbt.NBTTagCompound;

public class GeoModelDrawer extends GeoItemRenderer<ItemGalathCoin> {
   public static final Vec3f GlowColor = new Vec3f(0.84705883F, 0.11764706F, 0.35686275F);
   public static final Vec3f DimColor = new Vec3f(0.44705883F, 0.44705883F, 0.44705883F);
   public static final float FullRotation = 240.0F;
   public static final float IdleRotation = 120.0F;
   static final float AnimationSpeed = 0.05F;
   static final Minecraft Mc = Minecraft.getMinecraft();
   boolean GlowPassActive = false;
   Vec3f CurrentColor;

   public GeoModelDrawer() {
      super(new ModelGalathCoin());
   }

   public void render(GeoModel model, ItemGalathCoin item, float f2, float f3, float f4, float f5, float f6) {
      GlStateManager.disableCull();
      GlStateManager.enableRescaleNormal();
      BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      GeoBone bone = null;
      this.GlowPassActive = false;
      GeoBone bone2 = (GeoBone)model.topLevelBones.get(0);
      MATRIX_STACK.push();
      MATRIX_STACK.translate(bone2);
      MATRIX_STACK.moveToPivot(bone2);
      MATRIX_STACK.rotate(bone2);
      MATRIX_STACK.scale(bone2);
      MATRIX_STACK.moveBackFromPivot(bone2);

      for (GeoBone bone3 : bone2.childBones) {
         if ("pentagram".equals(bone3.getName())) {
            bone = bone3;
         } else {
            this.renderRecursively(bufferBuilder, bone3, f3, f4, f5, f6);
         }
      }

      Tessellator.getInstance().draw();
      float f7 = this.getSpinAngle(f2);

      try {
         this.CurrentColor = this.getCurrentColor();
         if (!GalathOwnershipData.f) {
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f7, f7);
            GL11.glDisable(2896);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      this.GlowPassActive = true;
      this.renderRecursively(bufferBuilder, bone, f3, f4, f5, f6);
      Tessellator.getInstance().draw();
      GL11.glEnable(2896);
      MATRIX_STACK.pop();
      GlStateManager.disableRescaleNormal();
      GlStateManager.enableCull();
      GlStateManager.resetColor();
   }


   float getSpinAngle(float f2) {
        block13: {
            try {
                try {
                    if (GeoModelDrawer.Mc.player.getHeldItemMainhand() == this.currentItemStack || GeoModelDrawer.Mc.player.getHeldItemOffhand() == this.currentItemStack) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw GeoModelDrawer.rethrow(runtimeException);
                }
                return this.computeSpinAngle(f2);
            }
            catch (RuntimeException runtimeException) {
                throw GeoModelDrawer.rethrow(runtimeException);
            }
        }
        long l = System.currentTimeMillis();
        NBTTagCompound nBTTagCompound = GeoModelDrawer.Mc.player.getEntityData();
        long l5 = nBTTagCompound.getLong("sexmod:galath_coin_activation_time");
        long l6 = nBTTagCompound.getLong("sexmod:galath_coin_deactivation_time");
        try {
            if (l5 != 0L) {
                return this.lerpFullToIdleRotation(l, l5, f2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GeoModelDrawer.rethrow(runtimeException);
        }
        try {
            if (l6 != 0L) {
                return this.lerpIdleToFullRotation(l, l6, f2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GeoModelDrawer.rethrow(runtimeException);
        }
        try {
            if (GalathOwnershipData.f) {
                return 120.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GeoModelDrawer.rethrow(runtimeException);
        }
        return this.computeSpinAngle(f2);
    }

   float lerpIdleToFullRotation(long l, long l2, float f) {
      float f2 = (float)(l - l2);

      try {
         if (f2 < 1000.0F) {
            return 120.0F;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         return f2 <= 3000.0F ? LerpMath.lerp(120.0F, 240.0F, (f2 - 1000.0F) / 2000.0F) : 240.0F;
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   float lerpFullToIdleRotation(long l, long l2, float f) {
      float f2 = (float)(l - l2);

      try {
         if (f2 < 1000.0F) {
            return 240.0F;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         return f2 <= 3000.0F ? LerpMath.lerp(240.0F, 120.0F, (f2 - 1000.0F) / 2000.0F) : 120.0F;
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }


   Vec3f getCurrentColor() {
        block13: {
            try {
                try {
                    if (GeoModelDrawer.Mc.player.getHeldItemMainhand() == this.currentItemStack || GeoModelDrawer.Mc.player.getHeldItemOffhand() == this.currentItemStack) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw GeoModelDrawer.rethrow(runtimeException);
                }
                return GlowColor;
            }
            catch (RuntimeException runtimeException) {
                throw GeoModelDrawer.rethrow(runtimeException);
            }
        }
        long l = System.currentTimeMillis();
        NBTTagCompound nBTTagCompound = GeoModelDrawer.Mc.player.getEntityData();
        long l5 = nBTTagCompound.getLong("sexmod:galath_coin_activation_time");
        long l6 = nBTTagCompound.getLong("sexmod:galath_coin_deactivation_time");
        try {
            if (l5 != 0L) {
                return this.lerpGlowToDimColor(l5, l);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GeoModelDrawer.rethrow(runtimeException);
        }
        try {
            if (l6 != 0L) {
                return this.lerpDimToGlowColor(l6, l);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GeoModelDrawer.rethrow(runtimeException);
        }
        try {
            if (GalathOwnershipData.f) {
                return DimColor;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GeoModelDrawer.rethrow(runtimeException);
        }
        return GlowColor;
    }

   Vec3f lerpDimToGlowColor(long l, long l2) {
      float f = (float)(l2 - l);

      try {
         if (f < 1000.0F) {
            return DimColor;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (f <= 3000.0F) {
            return LerpMath.lerpVec3f(DimColor, GlowColor, (f - 1000.0F) / 2000.0F);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return GlowColor;
   }

   Vec3f lerpGlowToDimColor(long l, long l2) {
      float f = (float)(l2 - l);

      try {
         if (f < 1000.0F) {
            return GlowColor;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (f <= 3000.0F) {
            return LerpMath.lerpVec3f(GlowColor, DimColor, (f - 1000.0F) / 2000.0F);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return DimColor;
   }

   float computeSpinAngle(float f) {
      return (float)(60.0 * Math.sin((Mc.player.ticksExisted + f) * 0.05F) + 180.0);
   }

   void drawCube(BufferBuilder bufferBuilder, GeoCube geoCube) {
      for (GeoQuad geoQuad : geoCube.quads) {
         try {
            if (geoQuad == null) {
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         for (GeoVertex geoVertex : geoQuad.vertices) {
            Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0F);
            MATRIX_STACK.getModelMatrix().transform(vector4f);
            bufferBuilder.pos(vector4f.getX(), vector4f.getY(), vector4f.getZ())
               .tex(geoVertex.textureU, geoVertex.textureV)
               .color(this.CurrentColor.X, this.CurrentColor.Y, this.CurrentColor.Z, 1.0F)
               .endVertex();
         }
      }
   }


   public void renderCube(BufferBuilder bufferBuilder, GeoCube geoCube, float f, float f2, float f3, float f4) {
        try {
            MATRIX_STACK.moveToPivot(geoCube);
            MATRIX_STACK.rotate(geoCube);
            MATRIX_STACK.moveBackFromPivot(geoCube);
            if (this.GlowPassActive) {
                this.drawCube(bufferBuilder, geoCube);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GeoModelDrawer.rethrow(runtimeException);
        }
        for (GeoQuad geoQuad : geoCube.quads) {
            Vector3f vector3f;
            block28: {
                block27: {
                    block26: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw GeoModelDrawer.rethrow(runtimeException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.getX(), (float)geoQuad.normal.getY(), (float)geoQuad.normal.getZ());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block26;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GeoModelDrawer.rethrow(runtimeException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block26;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GeoModelDrawer.rethrow(runtimeException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GeoModelDrawer.rethrow(runtimeException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block27;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GeoModelDrawer.rethrow(runtimeException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GeoModelDrawer.rethrow(runtimeException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GeoModelDrawer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block28;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GeoModelDrawer.rethrow(runtimeException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GeoModelDrawer.rethrow(runtimeException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw GeoModelDrawer.rethrow(runtimeException);
                }
            }
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.pos((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).tex((double)geoVertex.textureU, (double)geoVertex.textureV).color(f, f2, f3, f4).normal(vector3f.getX(), vector3f.getY(), vector3f.getZ()).endVertex();
            }
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
