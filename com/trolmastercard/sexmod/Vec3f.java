package com.trolmastercard.sexmod;

public class Vec3f {
   public static final Vec3f ZERO = new Vec3f(0.0F, 0.0F, 0.0F);
   public float X;
   public float Y;
   public float Z;

   public Vec3f(float f, float f2, float f3) {
      this.X = f;
      this.Y = f2;
      this.Z = f3;
   }

   public Vec3f subtract(Vec3f vec3f) {
      return new Vec3f(this.X - vec3f.X, this.Y - vec3f.Y, this.Z - vec3f.Z);
   }

   public Vec3f add(Vec3f vec3f) {
      return new Vec3f(this.X + vec3f.X, this.Y + vec3f.Y, this.Z + vec3f.Z);
   }

   public Vec3f scale(float f) {
      return new Vec3f(this.X * f, this.Y * f, this.Z * f);
   }
}
