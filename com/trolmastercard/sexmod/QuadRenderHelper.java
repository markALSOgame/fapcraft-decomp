package com.trolmastercard.sexmod;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class QuadRenderHelper {
   public static void a(BufferBuilder bufferBuilder, Tessellator tessellator, Minecraft mc, QuadRenderHelper.QuadConfig quadConfig) {
      Vec3d[] vec3dArray = new Vec3d[]{
         new Vec3d(-quadConfig.HalfWidth, -quadConfig.HalfHeight, 0.0), new Vec3d(-quadConfig.HalfWidth, quadConfig.HalfHeight, 0.0), new Vec3d(quadConfig.HalfWidth, quadConfig.HalfHeight, 0.0), new Vec3d(quadConfig.HalfWidth, -quadConfig.HalfHeight, 0.0)
      };
      Vec3d vec3d = new Vec3d(0.0, 0.0, -quadConfig.SegmentLength);
      Vec3d vec3d2 = VectorMath.scale(vec3d.normalize(), (double)quadConfig.RotationAngle);
      Vec3d[] vec3dArray2 = new Vec3d[4];
      System.arraycopy(vec3dArray, 0, vec3dArray2, 0, 4);
      ArrayList list = new ArrayList();
      float f = mc.player.ticksExisted + mc.getRenderPartialTicks();

      for (int i = 0; i <= quadConfig.SegmentCount; i++) {
         Vec3d[] vec3dArray3 = new Vec3d[4];
         float f2 = 1.0F - (float)i / quadConfig.SegmentCount;

         for (int i2 = 0; i2 < 4; i2++) {
            Vec3d vec3d3 = vec3dArray[i2];
            vec3dArray3[i2] = new Vec3d(vec3d3.x * f2, vec3d3.y, vec3d3.z).add(vec3d2);
         }

         list.add(vec3dArray3);
         vec3d = VectorMath.rotateEuler(vec3d, quadConfig.AngleX.a(i, f), quadConfig.AngleY.a(i, f), quadConfig.AngleZ.a(i, f));
         vec3d2 = vec3d2.add(vec3d);
      }

      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
      a(bufferBuilder, vec3dArray2, (Vec3d[])list.get(0), quadConfig.Color);

      for (int i3 = 0; i3 < quadConfig.SegmentCount - 1; i3++) {
         Vec3d[] vec3dArray4 = (Vec3d[])list.get(i3);
         Vec3d[] vec3dArray5 = (Vec3d[])list.get(i3 + 1);
         a(bufferBuilder, vec3dArray4, vec3dArray5, quadConfig.Color);
      }

      tessellator.draw();
   }

   static float waveFunction(float f, float f2, float f3, int i, float f4) {
      return (float)(Math.sin(f * f2 + f3 * i) * f4);
   }

   static void drawFace(BufferBuilder bufferBuilder, Vec3d[] vec3dArray, Vec3d[] vec3dArray2, RgbaColor4 rgbaColor4) {
      bufferBuilder.pos(vec3dArray[1].x, vec3dArray[1].y, vec3dArray[1].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray[2].x, vec3dArray[2].y, vec3dArray[2].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[2].x, vec3dArray2[2].y, vec3dArray2[2].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[1].x, vec3dArray2[1].y, vec3dArray2[1].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray[0].x, vec3dArray[0].y, vec3dArray[0].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray[1].x, vec3dArray[1].y, vec3dArray[1].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[1].x, vec3dArray2[1].y, vec3dArray2[1].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[0].x, vec3dArray2[0].y, vec3dArray2[0].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray[2].x, vec3dArray[2].y, vec3dArray[2].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray[3].x, vec3dArray[3].y, vec3dArray[3].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[3].x, vec3dArray2[3].y, vec3dArray2[3].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[2].x, vec3dArray2[2].y, vec3dArray2[2].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray[0].x, vec3dArray[0].y, vec3dArray[0].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray[3].x, vec3dArray[3].y, vec3dArray[3].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[3].x, vec3dArray2[3].y, vec3dArray2[3].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(vec3dArray2[0].x, vec3dArray2[0].y, vec3dArray2[0].z).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
   }

   @FunctionalInterface
   public interface FloatFunction {
      float apply(int i, float f);
   }

   public static class QuadConfig {
      public RgbaColor4 Color;
      public float RotationAngle;
      public int SegmentCount;
      public float SegmentLength;
      public QuadRenderHelper.FloatFunction AngleX;
      public QuadRenderHelper.FloatFunction AngleY;
      public QuadRenderHelper.FloatFunction AngleZ;
      public float HalfWidth;
      public float HalfHeight;

      public QuadConfig(RgbaColor4 rgbaColor4, float f, int i, float f2, QuadRenderHelper.FloatFunction floatFunction, QuadRenderHelper.FloatFunction floatFunction2, QuadRenderHelper.FloatFunction floatFunction3, float f3, float f4) {
         this.Color = rgbaColor4;
         this.RotationAngle = f;
         this.SegmentCount = i;
         this.SegmentLength = f2;
         this.AngleX = floatFunction;
         this.AngleY = floatFunction2;
         this.AngleZ = floatFunction3;
         this.HalfWidth = f3;
         this.HalfHeight = f4;
      }

      public QuadRenderHelper.QuadConfig copy() {
         return new QuadRenderHelper.QuadConfig(this.Color, this.RotationAngle, this.SegmentCount, this.SegmentLength, this.AngleX, this.AngleY, this.AngleZ, this.HalfWidth, this.HalfHeight);
      }
   }
}
