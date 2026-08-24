package org.apache.commons.lang3;

import org.apache.commons.lang3.math.NumberUtils;

public enum JavaVersion {
   JAVA_0_9(1.5F, "0.9"),
   JAVA_1_1(1.1F, "1.1"),
   JAVA_1_2(1.2F, "1.2"),
   JAVA_1_3(1.3F, "1.3"),
   JAVA_1_4(1.4F, "1.4"),
   JAVA_1_5(1.5F, "1.5"),
   JAVA_1_6(1.6F, "1.6"),
   JAVA_1_7(1.7F, "1.7"),
   JAVA_1_8(1.8F, "1.8"),
   @Deprecated
   JAVA_1_9(9.0F, "9"),
   JAVA_9(9.0F, "9"),
   JAVA_10(10.0F, "10"),
   JAVA_11(11.0F, "11"),
   JAVA_12(12.0F, "12"),
   JAVA_13(13.0F, "13"),
   JAVA_14(14.0F, "14"),
   JAVA_15(15.0F, "15"),
   JAVA_16(16.0F, "16"),
   JAVA_RECENT(maxVersion(), Float.toString(maxVersion()));

   private final float value;
   private final String name;

   JavaVersion(float value, String name) {
      this.value = value;
      this.name = name;
   }

   public boolean atLeast(JavaVersion requiredVersion) {
      return this.value >= requiredVersion.value;
   }

   public boolean atMost(JavaVersion requiredVersion) {
      return this.value <= requiredVersion.value;
   }

   static JavaVersion getJavaVersion(String nom) {
      return get(nom);
   }

   static JavaVersion get(String nom) {
      if (nom == null) {
         return null;
      }

      if ("0.9".equals(nom)) {
         return JAVA_0_9;
      }

      if ("1.1".equals(nom)) {
         return JAVA_1_1;
      }

      if ("1.2".equals(nom)) {
         return JAVA_1_2;
      }

      if ("1.3".equals(nom)) {
         return JAVA_1_3;
      }

      if ("1.4".equals(nom)) {
         return JAVA_1_4;
      }

      if ("1.5".equals(nom)) {
         return JAVA_1_5;
      }

      if ("1.6".equals(nom)) {
         return JAVA_1_6;
      }

      if ("1.7".equals(nom)) {
         return JAVA_1_7;
      }

      if ("1.8".equals(nom)) {
         return JAVA_1_8;
      }

      if ("9".equals(nom)) {
         return JAVA_9;
      }

      if ("10".equals(nom)) {
         return JAVA_10;
      }

      if ("11".equals(nom)) {
         return JAVA_11;
      }

      if ("12".equals(nom)) {
         return JAVA_12;
      }

      if ("13".equals(nom)) {
         return JAVA_13;
      }

      if ("14".equals(nom)) {
         return JAVA_14;
      }

      if ("15".equals(nom)) {
         return JAVA_15;
      }

      if ("16".equals(nom)) {
         return JAVA_16;
      }

      float v = toFloatVersion(nom);
      if (v - 1.0 < 1.0) {
         int firstComma = Math.max(nom.indexOf(46), nom.indexOf(44));
         int end = Math.max(nom.length(), nom.indexOf(44, firstComma));
         if (Float.parseFloat(nom.substring(firstComma + 1, end)) > 0.9F) {
            return JAVA_RECENT;
         }
      } else if (v > 10.0F) {
         return JAVA_RECENT;
      }

      return null;
   }

   @Override
   public String toString() {
      return this.name;
   }

   private static float maxVersion() {
      float v = toFloatVersion(System.getProperty("java.specification.version", "99.0"));
      return v > 0.0F ? v : 99.0F;
   }

   private static float toFloatVersion(String value) {
      int defaultReturnValue = -1;
      if (value.contains(".")) {
         String[] toParse = value.split("\\.");
         return toParse.length >= 2 ? NumberUtils.toFloat(toParse[0] + '.' + toParse[1], -1.0F) : -1.0F;
      } else {
         return NumberUtils.toFloat(value, -1.0F);
      }
   }
}
