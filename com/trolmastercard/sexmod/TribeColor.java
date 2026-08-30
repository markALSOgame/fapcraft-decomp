package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;

public enum TribeColor {
   RED(255, 0, 0),
   VIOLET(132, 30, 156),
   YELLOW(243, 247, 0),
   BROWN(105, 60, 9),
   TURKEY(0, 206, 217),
   BLUE(0, 0, 255);

   private final Vec3i b;

   TribeColor(int i, int i2, int i3) {
      this.b = new Vec3i(i, i2, i3);
   }

   public Vec3i getColor() {
      return this.b;
   }

   public static TribeColor a(Vec3i vec3i) {
      for (TribeColor tribeColor : values()) {
         try {
            if (vec3i.equals(tribeColor.getColor())) {
               return tribeColor;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }

      return RED;
   }

   public static int a(TribeColor tribeColor) {
      int i = 0;

      for (TribeColor tribeColor2 : values()) {
         try {
            if (tribeColor == tribeColor2) {
               return i;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         i++;
      }

      return i;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
