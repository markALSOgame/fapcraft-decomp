package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3d;

public class AngleMath {
   public static double angleBetween(Vec3d vec3d, Vec3d vec3d2) {
      double d = vec3d2.x - vec3d.x;
      double d2 = vec3d2.y - vec3d.y;
      double d3 = vec3d2.z - vec3d.z;
      return Math.atan2(d3, Math.sqrt(d * d + d2 * d2));
   }

   public static float normalizeDegrees(float f) {
      f %= 360.0F;
      if (f < 0.0F) {
         f += 360.0F;
      }

      return f;
   }

   public static float wrapPositiveDegrees(float f) {
      float f2;
      float f3 = f2 = f % 360.0F;

      try {
         if (f3 >= 0.0F) {
            return f2;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return f2 + 360.0F;
   }

   public static double wrapPositiveDegreesDouble(double d) {
      double d2;
      double d3 = d2 = d % 360.0;

      try {
         if (d3 >= 0.0) {
            return d2;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return d2 + 360.0;
   }

   public static float degToRadians(float f) {
      return (float)((Math.PI * 2) / (360.0 / f));
   }

   public static float degToRadians(double d) {
      return (float)((Math.PI * 2) / (360.0 / d));
   }

   public static float radToDegrees(float f) {
      return (float)((180.0 / Math.PI) * f);
   }

   public static double radToDegrees(double d) {
      return (180.0 / Math.PI) * d;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
