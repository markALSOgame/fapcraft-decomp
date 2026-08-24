package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class LerpMath {
   public static Vec3d stepTowards(Vec3d vec3d, Vec3d vec3d2, int i) {
      try {
         if (i == 0) {
            return vec3d2;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Vec3d vec3d3 = vec3d2.subtract(vec3d);
      return vec3d.add(vec3d3.x / i, vec3d3.y / i, vec3d3.z / i);
   }

   public static double lerp(double d, double d2, double d3) {
      return d + (d2 - d) * d3;
   }

   public static float lerp(float f, float f2, float f3) {
      return f + (f2 - f) * f3;
   }

   public static float lerpAngleRadians(float f, float f2, double d) {
      float f3 = f2 - f;

      while (f3 < -Math.PI) {
         f3 = (float)(f3 + (Math.PI * 2));
      }

      while (f3 >= Math.PI) {
         f3 = (float)(f3 - (Math.PI * 2));
      }

      return (float)(f + f3 * d);
   }

   public static float lerpAngleDegrees(float f, float f2, double d) {
      double d2 = Math.toRadians(f);
      double d3 = Math.toRadians(f2);
      return (float)Math.toDegrees(lerpAngleRadians((float)d2, (float)d3, d));
   }

   public static Vec3d lerpVec3d(Vec3d vec3d, Vec3d vec3d2, double d) {
      Vec3d vec3d3 = vec3d2.subtract(vec3d);
      return vec3d.add(new Vec3d(vec3d3.x * d, vec3d3.y * d, vec3d3.z * d));
   }

   public static Vec3f lerpVec3f(Vec3f vec3f, Vec3f vec3f2, double d) {
      Vec3f vec3f3 = vec3f2.subtract(vec3f);
      return vec3f.add(vec3f3.scale((float)d));
   }

   public static Vec3i lerpVec3i(Vec3i vec3i, Vec3i vec3i2, double d) {
      Vec3d vec3d = new Vec3d(
         vec3i2.getX() - vec3i.getX(), vec3i2.getY() - vec3i.getY(), vec3i2.getZ() - vec3i.getZ()
      );
      return new Vec3i(
         vec3i.getX() + vec3d.x * d, vec3i.getY() + vec3d.y * d, vec3i.getZ() + vec3d.z * d
      );
   }

   public static RgbaColor4 lerpColor(RgbaColor4 rgbaColor4, RgbaColor4 rgbaColor42, double d) {
      RgbaColor4 rgbaColor43 = new RgbaColor4(rgbaColor42.R - rgbaColor4.R, rgbaColor42.G - rgbaColor4.G, rgbaColor42.B - rgbaColor4.B, rgbaColor42.A - rgbaColor4.A);
      return new RgbaColor4((int)(rgbaColor4.R + rgbaColor43.R * d), (int)(rgbaColor4.G + rgbaColor43.G * d), (int)(rgbaColor4.B + rgbaColor43.B * d), (int)(rgbaColor4.A + rgbaColor43.A * d));
   }

   public static double EaseOutQuart(double d) {
      return 1.0 - Math.pow(1.0 - d, 4.0);
   }

   public static double EaseOutCubic(double d) {
      return 1.0 - Math.pow(1.0 - d, 3.0);
   }

   public static double EaseOutBack(double d) {
      double d2 = 1.70158;
      double d3 = d2 + 1.0;
      return 1.0 + d3 * Math.pow(d - 1.0, 3.0) + d2 * Math.pow(d - 1.0, 2.0);
   }

   public static double EaseInBack(double d) {
      double d2 = 1.70158;
      double d3 = d2 + 1.0;
      return d3 * d * d * d - d2 * d * d;
   }

   public static double easeOutSine(double d) {
      return Math.sin(d * Math.PI / 2.0);
   }

   public static double easeInCubic(double d) {
      return d * d * d;
   }

   public static double EaseInOutSine(double d) {
      return -(Math.cos(Math.PI * d) - 1.0) / 2.0;
   }

   public static double EaseOutSine(double d) {
      return 1.0 - Math.cos(Math.PI * d / 2.0);
   }

   public static double cosineLerp(double d, double d2, double d3) {
      double d4 = (1.0 - Math.cos(d3 * Math.PI)) / 2.0;
      return d * (1.0 - d4) + d2 * d4;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
