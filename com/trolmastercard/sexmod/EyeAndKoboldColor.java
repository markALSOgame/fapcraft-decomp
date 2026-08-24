package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;

public enum EyeAndKoboldColor {
   GREEN(69, 141, 113, 91, 167, 128, 9, TextFormatting.DARK_GREEN),
   YELLOW(241, 177, 77, 255, 226, 170, 4, TextFormatting.YELLOW),
   RED(230, 27, 57, 253, 232, 239, 14, TextFormatting.RED),
   PURPLE(196, 148, 207, 246, 188, 96, 10, TextFormatting.DARK_PURPLE),
   LIGHT_GREEN(170, 208, 47, 230, 214, 104, 5, TextFormatting.GREEN),
   OLD_BLUE(173, 138, 128, 118, 151, 180, 2, TextFormatting.LIGHT_PURPLE),
   DARK_GREY(92, 92, 110, 198, 193, 165, 7, TextFormatting.DARK_GRAY),
   BROWN(200, 145, 112, 253, 228, 198, 12, TextFormatting.GOLD),
   DARK_BLUE(65, 84, 116, 104, 137, 146, 11, TextFormatting.DARK_BLUE),
   LIGHT_BLUE(100, 163, 206, 138, 235, 242, 3, TextFormatting.DARK_AQUA),
   SILVER(136, 136, 134, 255, 255, 255, 0, TextFormatting.GRAY);

   private final Vec3i mainColor;
   private final Vec3i secondaryColor;
   private final int woolMeta;
   private final TextFormatting textColor;

   EyeAndKoboldColor(int i, int i2, int i3, int i4, int i5, int i6, int i7, TextFormatting textFormatting) {
      this.mainColor = new Vec3i(i, i2, i3);
      this.secondaryColor = new Vec3i(i4, i5, i6);
      this.woolMeta = i7;
      this.textColor = textFormatting;
   }

   public static int indexOf(EyeAndKoboldColor eyeColor) {
      int i = 0;

      for (EyeAndKoboldColor eyeColor2 : values()) {
         try {
            if (eyeColor == eyeColor2) {
               return i;
            }
         } catch (IllegalArgumentException error) {
            throw rethrow(error);
         }

         i++;
      }

      return i;
   }

   public static EyeAndKoboldColor safeValueOf(String string) {
      try {
         return valueOf(string);
      } catch (IllegalArgumentException error) {
         return KoboldNpc.DefaultTribeColor;
      }
   }

   public static EyeAndKoboldColor safeValueOf(Vec3i vec3i) {
      for (EyeAndKoboldColor eyeColor : values()) {
         try {
            if (vec3i.equals(eyeColor.getMainColor())) {
               return eyeColor;
            }
         } catch (IllegalArgumentException error) {
            throw rethrow(error);
         }
      }

      return KoboldNpc.DefaultTribeColor;
   }

   public static EyeAndKoboldColor getColorByWoolId(int i) {
      for (EyeAndKoboldColor eyeColor : values()) {
         try {
            if (eyeColor.getWoolMeta() == i) {
               return eyeColor;
            }
         } catch (IllegalArgumentException error) {
            throw rethrow(error);
         }
      }

      return KoboldNpc.DefaultTribeColor;
   }

   public Vec3i getMainColor() {
      return this.mainColor;
   }

   public Vec3i getSecondaryColor() {
      return this.secondaryColor;
   }

   public int getWoolMeta() {
      return this.woolMeta;
   }

   public TextFormatting getTextColor() {
      return this.textColor;
   }

   private static IllegalArgumentException rethrow(IllegalArgumentException error) {
      return error;
   }
}
