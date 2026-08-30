package com.trolmastercard.sexmod;

public class Vec2i {
   public static final Vec2i ZERO = new Vec2i(0, 0);
   public int X;
   public int Y;

   public Vec2i(int i, int i2) {
      this.X = i;
      this.Y = i2;
   }

   public float distanceTo(int i, int i2) {
      float f = i - this.X;
      float f2 = i2 - this.Y;
      return (float)Math.sqrt(f * f + f2 * f2);
   }

   @Override
   public String toString() {
      return String.format("(%s, %s)", this.X, this.Y);
   }
}
