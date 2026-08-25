package com.trolmastercard.sexmod;

import java.util.HashSet;
import software.bernie.geckolib3.geo.render.built.GeoBone;

public interface GirlBoneFilter {
   default HashSet<String> getFilteredBoneNames() {
      return BoneColorHelper.AdultParts;
   }

   default boolean isBoneIncluded(HashSet<String> set, GeoBone bone) {
      while (bone.parent != null) {
         String string = bone.getName();

         try {
            if (set.contains(string)) {
               return false;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (string.startsWith("armor")) {
               return false;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         bone = bone.parent;
      }

      return true;
   }

   static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
