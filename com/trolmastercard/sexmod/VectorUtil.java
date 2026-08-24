package com.trolmastercard.sexmod;

import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.processor.IBone;

public class VectorUtil {
   public static Vec3d[][] getGirlBonePositions(GirlEntity girl, float f, String string, String string2, String string3, float f2, float f3, float f4, float f5, String string4) {
      Vec3d[] vec3dArray = b(girl, f, string, string2, string3, f2, f3, f4, f5, string4);
      return rearrangeVec3dArray(vec3dArray);
   }

   public static Vec3d[][] getGirlBonePositionsFiltered(GirlEntity girl, float f, String string, String string2, Vec3f vec3f, Vec3f vec3f2) {
      Vec3d[] vec3dArray = b(girl, f, string, string2, vec3f, vec3f2);
      return b(vec3dArray);
   }


   static Vec3d[] b(GirlEntity girl, float f, String string, String string2, Vec3f vec3f, Vec3f vec3f2) {
        int i;
        Vec3d[] vec3dArray;
        Vec3d vec3d;
        Vec3d vec3d2;
        block14: {
            block13: {
                vec3d2 = girl.getModelBone(string);
                vec3d = girl.getModelBone(string2);
                vec3dArray = new Vec3d[8];
                try {
                    try {
                        if (vec3f.X != 0.0f || vec3f2.X != 0.0f) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw VectorUtil.rethrow(runtimeException);
                    }
                    vec3dArray[0] = new Vec3d(0.0, (double)vec3f.Y, (double)vec3f.Z);
                    vec3dArray[1] = new Vec3d(0.0, (double)(-vec3f.Y), (double)vec3f.Z);
                    vec3dArray[2] = new Vec3d(0.0, (double)(-vec3f.Y), (double)(-vec3f.Z));
                    vec3dArray[3] = new Vec3d(0.0, (double)vec3f.Y, (double)(-vec3f.Z));
                    vec3dArray[4] = new Vec3d(0.0, (double)vec3f2.Y, (double)vec3f2.Z);
                    vec3dArray[5] = new Vec3d(0.0, (double)(-vec3f2.Y), (double)vec3f2.Z);
                    vec3dArray[6] = new Vec3d(0.0, (double)(-vec3f2.Y), (double)(-vec3f2.Z));
                    vec3dArray[7] = new Vec3d(0.0, (double)vec3f2.Y, (double)(-vec3f2.Z));
                    break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw VectorUtil.rethrow(runtimeException);
                }
            }
            vec3dArray[0] = new Vec3d((double)vec3f.X, (double)vec3f.Y, 0.0);
            vec3dArray[1] = new Vec3d((double)(-vec3f.X), (double)vec3f.Y, 0.0);
            vec3dArray[2] = new Vec3d((double)(-vec3f.X), (double)(-vec3f.Y), 0.0);
            vec3dArray[3] = new Vec3d((double)vec3f.X, (double)(-vec3f.Y), 0.0);
            vec3dArray[4] = new Vec3d((double)vec3f2.X, (double)vec3f2.Y, 0.0);
            vec3dArray[5] = new Vec3d((double)(-vec3f2.X), (double)vec3f2.Y, 0.0);
            vec3dArray[6] = new Vec3d((double)(-vec3f2.X), (double)(-vec3f2.Y), 0.0);
            vec3dArray[7] = new Vec3d((double)vec3f2.X, (double)(-vec3f2.Y), 0.0);
        }
        try {
            for (i = 0; i < vec3dArray.length; ++i) {
                vec3dArray[i] = VectorMath.rotateYaw(vec3dArray[i], f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw VectorUtil.rethrow(runtimeException);
        }
        try {
            for (i = 0; i < 4; ++i) {
                vec3dArray[i] = vec3dArray[i].add(vec3d2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw VectorUtil.rethrow(runtimeException);
        }
        try {
            for (i = 4; i < 8; ++i) {
                vec3dArray[i] = vec3dArray[i].add(vec3d);
            }
        }
        catch (RuntimeException runtimeException) {
            throw VectorUtil.rethrow(runtimeException);
        }
        return vec3dArray;
    }

   static Vec3d[][] b(Vec3d[] vec3dArray) {
      Vec3d[][] vec3dArray2 = new Vec3d[6][4];
      vec3dArray2[0][0] = vec3dArray[0];
      vec3dArray2[0][1] = vec3dArray[1];
      vec3dArray2[0][2] = vec3dArray[2];
      vec3dArray2[0][3] = vec3dArray[3];
      vec3dArray2[1][0] = vec3dArray[4];
      vec3dArray2[1][1] = vec3dArray[5];
      vec3dArray2[1][2] = vec3dArray[6];
      vec3dArray2[1][3] = vec3dArray[7];
      vec3dArray2[2][0] = vec3dArray[1];
      vec3dArray2[2][1] = vec3dArray[2];
      vec3dArray2[2][2] = vec3dArray[6];
      vec3dArray2[2][3] = vec3dArray[5];
      vec3dArray2[3][0] = vec3dArray[3];
      vec3dArray2[3][1] = vec3dArray[7];
      vec3dArray2[3][2] = vec3dArray[4];
      vec3dArray2[3][3] = vec3dArray[0];
      vec3dArray2[4][0] = vec3dArray[1];
      vec3dArray2[4][1] = vec3dArray[0];
      vec3dArray2[4][2] = vec3dArray[4];
      vec3dArray2[4][3] = vec3dArray[5];
      vec3dArray2[5][0] = vec3dArray[2];
      vec3dArray2[5][1] = vec3dArray[3];
      vec3dArray2[5][2] = vec3dArray[7];
      vec3dArray2[5][3] = vec3dArray[6];
      return vec3dArray2;
   }

   static Vec3d[] b(GirlEntity girl, float f, String string, String string2, String string3, float f2, float f3, float f4, float f5, String string4) {
      IBone iBone = girl.b().getBone(string4);
      if (iBone == null) {
         Vec3d[] vec3dArray = new Vec3d[12];
         Arrays.fill(vec3dArray, Vec3d.ZERO);
         return vec3dArray;
      }

      float f6 = AngleMath.radToDegrees(iBone.getRotationY());
      float f7 = AngleMath.radToDegrees(iBone.getRotationZ());
      Vec3d vec3d = girl.getModelBone(string);
      Vec3d vec3d2 = girl.getModelBone(string2);
      Vec3d vec3d3 = girl.getModelBone(string3);
      Vec3d[] vec3dArray2 = new Vec3d[]{
         new Vec3d(f2, 0.0, -f3),
         new Vec3d(-f2, 0.0, -f3),
         new Vec3d(-f2, 0.0, f3),
         new Vec3d(f2, 0.0, f3),
         new Vec3d(f2, f3, 0.0),
         new Vec3d(-f2, f3, 0.0),
         new Vec3d(-f2, -f3, 0.0),
         new Vec3d(f2, -f3, 0.0),
         new Vec3d(f4, 0.0, -f5),
         new Vec3d(-f4, 0.0, -f5),
         new Vec3d(-f4, 0.0, f5),
         new Vec3d(f4, 0.0, f5)
      };
      int i = 0;

      try {
         while (i < vec3dArray2.length) {
            vec3dArray2[i] = VectorMath.rotateYaw(vec3dArray2[i], f);
            i++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      i = 0;

      try {
         while (i < 4) {
            vec3dArray2[i] = VectorMath.rotateEuler(vec3dArray2[i], 0.0F, f6, f7);
            i++;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      i = 0;

      try {
         while (i < 4) {
            vec3dArray2[i] = vec3dArray2[i].add(vec3d);
            i++;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      i = 4;

      try {
         while (i < 8) {
            vec3dArray2[i] = vec3dArray2[i].add(vec3d2);
            i++;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      i = 8;

      try {
         while (i < 12) {
            vec3dArray2[i] = vec3dArray2[i].add(vec3d3);
            i++;
         }

         return vec3dArray2;
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }
   }

   static Vec3d[][] rearrangeVec3dArray(Vec3d[] vec3dArray) {
      Vec3d[][] vec3dArray2 = new Vec3d[10][4];
      vec3dArray2[0][0] = vec3dArray[0];
      vec3dArray2[0][1] = vec3dArray[1];
      vec3dArray2[0][2] = vec3dArray[5];
      vec3dArray2[0][3] = vec3dArray[4];
      vec3dArray2[1][0] = vec3dArray[1];
      vec3dArray2[1][1] = vec3dArray[2];
      vec3dArray2[1][2] = vec3dArray[6];
      vec3dArray2[1][3] = vec3dArray[5];
      vec3dArray2[2][0] = vec3dArray[3];
      vec3dArray2[2][1] = vec3dArray[2];
      vec3dArray2[2][2] = vec3dArray[6];
      vec3dArray2[2][3] = vec3dArray[7];
      vec3dArray2[3][0] = vec3dArray[0];
      vec3dArray2[3][1] = vec3dArray[4];
      vec3dArray2[3][2] = vec3dArray[7];
      vec3dArray2[3][3] = vec3dArray[3];
      vec3dArray2[4][0] = vec3dArray[0];
      vec3dArray2[4][1] = vec3dArray[1];
      vec3dArray2[4][2] = vec3dArray[2];
      vec3dArray2[4][3] = vec3dArray[3];
      vec3dArray2[5][0] = vec3dArray[4];
      vec3dArray2[5][1] = vec3dArray[5];
      vec3dArray2[5][2] = vec3dArray[9];
      vec3dArray2[5][3] = vec3dArray[8];
      vec3dArray2[6][0] = vec3dArray[9];
      vec3dArray2[6][1] = vec3dArray[10];
      vec3dArray2[6][2] = vec3dArray[6];
      vec3dArray2[6][3] = vec3dArray[5];
      vec3dArray2[7][0] = vec3dArray[10];
      vec3dArray2[7][1] = vec3dArray[11];
      vec3dArray2[7][2] = vec3dArray[7];
      vec3dArray2[7][3] = vec3dArray[6];
      vec3dArray2[8][0] = vec3dArray[4];
      vec3dArray2[8][1] = vec3dArray[7];
      vec3dArray2[8][2] = vec3dArray[11];
      vec3dArray2[8][3] = vec3dArray[8];
      vec3dArray2[9][0] = vec3dArray[8];
      vec3dArray2[9][1] = vec3dArray[9];
      vec3dArray2[9][2] = vec3dArray[10];
      vec3dArray2[9][3] = vec3dArray[11];
      return vec3dArray2;
   }

   public static void drawBoneQuads(BufferBuilder bufferBuilder, Vec3d[][] vec3dArray, RgbaColor4 rgbaColor4) {
      for (Vec3d[] vec3dArray2 : vec3dArray) {
         for (Vec3d vec3d : vec3dArray2) {
            bufferBuilder.pos(vec3d.x, vec3d.y, vec3d.z)
               .tex(0.0, 0.0)
               .color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A)
               .endVertex();
         }
      }
   }

   public static void drawGirlBones(Minecraft mc, GirlEntity girl, float f) {
      EntityPlayerSP mcPlayer = mc.player;

      try {
         if (mcPlayer == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GlStateManager.translate(0.0, 0.01, 0.0);
      Entity entity = ((GeoGirlRenderer)mc.getRenderManager().getEntityRenderObject(girl)).c(girl);

      Vec3d vec3d;
      label22: {
         try {
            if (girl.Q()) {
               vec3d = girl.getTargetPos();
               break label22;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         vec3d = LerpMath.lerpVec3d(new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ), entity.getPositionVector(), f);
      }

      Vec3d vec3d2 = vec3d;
      Vec3d vec3d3 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f);
      Vec3d vec3d4 = vec3d2.subtract(vec3d3);
      vec3d4 = girl.a(vec3d4, f);
      GlStateManager.translate(vec3d4.x, vec3d4.y, vec3d4.z);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
