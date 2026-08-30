package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;

public enum MarkColor {
   PURPLE(103, 39, 123),
   ORANGE(251, 153, 56),
   BLACK(30, 33, 38),
   BLUE(88, 83, 186),
   BROWN(63, 35, 34),
   PINK(247, 102, 109),
   RED(241, 69, 49),
   GREEN(75, 143, 106);

   private final Vec3i Color;

   MarkColor(int i, int i2, int i3) {
      this.Color = new Vec3i(i, i2, i3);
   }

   public Vec3i getColor() {
      return this.Color;
   }

   public static int getIndex(MarkColor markColor) {
      int i = 0;

      for (MarkColor markColor2 : values()) {
         try {
            if (markColor == markColor2) {
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
