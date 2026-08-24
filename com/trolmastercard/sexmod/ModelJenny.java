package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;

public class ModelJenny extends GirlGeoModel {
   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{
         new ResourceLocation("sexmod", "geo/jenny/jennynude.geo.json"), new ResourceLocation("sexmod", "geo/jenny/jennydressed.geo.json")
      };
   }

   @Override
   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/jenny/jenny.png");
   }

   @Override
   public ResourceLocation getAnimationFileLocation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/jenny/jenny.animation.json");
   }

   @Override
   public String[] getHelmetBoneGroup() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getChestBoneGroup() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   @Override
   public String[] a() {
      return new String[]{"boobsFlesh", "upperBodyL", "upperBodyR"};
   }

   @Override
   public String[] getPantsBoneGroup() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   @Override
   public String[] getBodyFleshBoneGroup() {
      return new String[]{"fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   @Override
   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }
}
