package com.trolmastercard.sexmod;

import javax.vecmath.Vector3f;
import net.minecraft.util.math.Vec3d;

public class VectorMath {
   public static Vec3d scale(Vec3d vec3d, double d) {
      return new Vec3d(vec3d.x * d, vec3d.y * d, vec3d.z * d);
   }

   public static double dotProduct3f(Vector3f vector3f, Vec3d vec3d) {
      return vector3f.x * vec3d.x + vector3f.y * vec3d.y + vector3f.z * vec3d.z;
   }

   public static double dotProduct(Vec3d vec3d, Vec3d vec3d2) {
      return vec3d.x * vec3d2.x + vec3d.y * vec3d2.y + vec3d.z * vec3d2.z;
   }

   public static Vec3d crossProduct(Vec3d vec3d, Vec3d vec3d2) {
      return new Vec3d(
         vec3d.y * vec3d2.z - vec3d.z * vec3d2.y,
         vec3d.z * vec3d2.x - vec3d.x * vec3d2.z,
         vec3d.x * vec3d2.y - vec3d.y * vec3d2.x
      );
   }

   public static Vec3d rotatePitch(double d, double d2, double d3, float f) {
      return rotateYaw(new Vec3d(d, d2, d3), f);
   }

   public static Vec3d rotateYaw(Vec3d vec3d, float f) {
      return rotatePitchYaw(vec3d, 0.0F, f);
   }

   public static Vec3d rotatePitchYaw(Vec3d vec3d, float f, float f2) {
      Vec3d vec3d2 = new Vec3d(
         vec3d.x,
         vec3d.y * Math.cos(f * (Math.PI / 180.0)) - vec3d.z * Math.sin(f * (Math.PI / 180.0)),
         vec3d.y * Math.sin(f * (Math.PI / 180.0)) + vec3d.z * Math.cos(f * (Math.PI / 180.0))
      );
      return new Vec3d(
         -Math.sin((f2 + 90.0F) * (Math.PI / 180.0)) * vec3d2.x - Math.sin(f2 * (Math.PI / 180.0)) * vec3d2.z,
         vec3d2.y,
         Math.cos((f2 + 90.0F) * (Math.PI / 180.0)) * vec3d2.x + Math.cos(f2 * (Math.PI / 180.0)) * vec3d2.z
      );
   }

   public static Vec3d rotatePitchYawCoords(double d, double d2, double d3, float f, float f2) {
      return rotatePitchYaw(new Vec3d(d, d2, d3), f, f2);
   }

   public static Vec3d rotateEuler(Vec3d vec3d, float f, float f2, float f3) {
      f = AngleMath.degToRadians(f);
      f2 = AngleMath.degToRadians(f2);
      f3 = AngleMath.degToRadians(f3);
      double d = (float)Math.sin(f);
      double d2 = (float)Math.cos(f);
      double d3 = (float)Math.sin(f2);
      double d4 = (float)Math.cos(f2);
      double d5 = (float)Math.sin(f3);
      double d6 = (float)Math.cos(f3);
      double d7 = vec3d.y * d2 - vec3d.z * d;
      double d8 = vec3d.y * d + vec3d.z * d2;
      vec3d = new Vec3d(vec3d.x, d7, d8);
      double d9 = vec3d.x * d4 + vec3d.z * d3;
      d8 = -vec3d.x * d3 + vec3d.z * d4;
      vec3d = new Vec3d(d9, vec3d.y, d8);
      d9 = vec3d.x * d6 - vec3d.y * d5;
      d7 = vec3d.x * d5 + vec3d.y * d6;
      return new Vec3d(d9, d7, vec3d.z);
   }

   public static Vec3d mirrorXZ(Vec3d vec3d) {
      return new Vec3d(-vec3d.x, vec3d.y, -vec3d.z);
   }

   public static Vec3d mirrorX(Vec3d vec3d) {
      return new Vec3d(-vec3d.x, -vec3d.y, vec3d.z);
   }

   public static Vec3d mirrorY(Vec3d vec3d) {
      return new Vec3d(vec3d.x, -vec3d.y, -vec3d.z);
   }

   static double inverseLerp(double d, double d2, double d3) {
      return (d3 - d) / (d2 - d);
   }

   public static double inverseLerpComponent(Vec3d vec3d, Vec3d vec3d2, Vec3d vec3d3) {
      return inverseLerp(vec3d.x, vec3d2.x, vec3d3.x);
   }
}
