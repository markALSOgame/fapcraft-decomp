package com.trolmastercard.sexmod;

public class Vec2d {
   public double X;
   public double Y;

   public Vec2d(double d, double d2) {
      this.X = d;
      this.Y = d2;
   }

   public Vec2d subtract(Vec2d vec2d) {
      return new Vec2d(this.X - vec2d.X, this.Y - vec2d.Y);
   }
}
