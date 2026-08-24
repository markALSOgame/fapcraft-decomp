package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.vecmath.Vector3f;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;

public class BoneColorHelper {
   public static final Vec3d BlushColor = new Vec3d(0.95, 0.65, 0.85);
   public static final Vec3d DarkShadeColor = new Vec3d(0.0, 0.2, 0.3);
   public static final float TintStrength = 0.1F;
   public static final HashSet<String> AdultParts = new HashSet<String>() {
      {
         this.add("boobs");
         this.add("booty");
         this.add("vagina");
         this.add("fuckhole");
      }
   };
   protected static HashMap<GirlBoneFilter, HashMap<String, Boolean>> BoneInclusionCache = new HashMap<>();
   public static Vec3d CurrentSkinColor;

   static boolean isBoneIncluded(GirlBoneFilter girlBoneFilter, GeoBone bone) {
      HashMap map = BoneInclusionCache.get(girlBoneFilter);
      if (map == null) {
         map = new HashMap();
         boolean flag = girlBoneFilter.isBoneIncluded(girlBoneFilter.getFilteredBoneNames(), bone);
         map.put(bone.getName(), flag);
         BoneInclusionCache.put(girlBoneFilter, map);
         return flag;
      } else {
         Boolean flag2 = (Boolean)map.get(bone.getName());
         if (flag2 == null) {
            flag2 = girlBoneFilter.isBoneIncluded(girlBoneFilter.getFilteredBoneNames(), bone);
            map.put(bone.getName(), flag2);
            BoneInclusionCache.put(girlBoneFilter, map);
            return flag2;
         } else {
            return flag2;
         }
      }
   }

   public static Vec3d applyBoneColor(GirlBoneFilter girlBoneFilter, GeoBone bone, Vec3d vec3d, Vector3f vector3f) {
      try {
         if (!isBoneIncluded(girlBoneFilter, bone)) {
            return vec3d;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return tintColor(vec3d, vector3f, CurrentSkinColor);
   }

   public static Vec3d tintColor(Vec3d vec3d, Vector3f vector3f, Vec3d vec3d2) {
      double d = VectorMath.dotProduct3f(vector3f, vec3d2);
      double d2 = LerpMath.EaseOutQuart(Math.abs(d));
      d2 *= 0.1F;

      Vec3d vec3d3;
      try {
         vec3d3 = vec3d;
         if (d > 0.0) {
            return LerpMath.lerpVec3d(vec3d, BlushColor, d2);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return LerpMath.lerpVec3d(vec3d3, DarkShadeColor, d2);
   }

   public static void setSkinColor(EntityLivingBase livingBase, float f) {
      CurrentSkinColor = BedLogic.getBedHeadPos(livingBase, f);
   }

   public static void cacheBoneColors(List<IBone> list, HashSet<String> set, GirlBoneFilter girlBoneFilter) {
      try {
         if (BoneInclusionCache.get(girlBoneFilter) != null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      HashMap map = new HashMap();

      for (IBone iBone : list) {
         map.put(iBone.getName(), girlBoneFilter.isBoneIncluded(set, (GeoBone)iBone));
      }

      BoneInclusionCache.put(girlBoneFilter, map);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
