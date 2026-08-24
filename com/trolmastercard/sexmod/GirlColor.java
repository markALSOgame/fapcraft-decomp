package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;

public enum GirlColor {
   LIGHT_GREEN(213, 239, 150),
   MEDIUM_GREEN(189, 165, 91),
   DARK_GREEN(160, 183, 135),
   LIGHT_YELLOW(234, 176, 102),
   LIGHT_BLUE(187, 203, 252);

   private final Vec3i Color;

   GirlColor(int i, int i2, int i3) {
      this.Color = new Vec3i(i, i2, i3);
   }

   public Vec3i getColor() {
      return this.Color;
   }

   public static int getColorIndex(GirlColor girlColor) {
      int i = 0;

      for (GirlColor girlColor2 : values()) {
         try {
            if (girlColor == girlColor2) {
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
