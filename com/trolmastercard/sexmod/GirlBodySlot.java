package com.trolmastercard.sexmod;

public enum GirlBodySlot {
   GIRL_SPECIFIC,
   HEAD(0, "customHead"),
   FOOT_L(60, "customShoeL"),
   FOOT_R(80, "customShoeR"),
   HAND_L(100, "customHandL"),
   HAND_R(120, "customHandR"),
   CUSTOM_BONE(140);

   public static final String SEPARATOR = "#";
   public int buttonIDPlus;
   public int buttonIDMinus;
   public String boneName = null;
   public int iconXPos = 0;

   GirlBodySlot() {
   }

   GirlBodySlot(int i) {
      this.iconXPos = i;
   }

   GirlBodySlot(int i, String string) {
      this.iconXPos = i;
      this.boneName = string;
      this.buttonIDPlus = ++ModConstants.AuxCounter;
      this.buttonIDMinus = ++ModConstants.AuxCounter;
   }

   public static int getClothingSlotCount() {
      return values().length - 2;
   }
}
