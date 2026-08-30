package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;

public class ModelLuna extends GirlGeoModel<GirlEntity> {
   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/cat/cat.geo.json"), new ResourceLocation("sexmod", "geo/cat/cat.geo.json")};
   }

   @Override
   public ResourceLocation getSkinLocation() {
      return new ResourceLocation("sexmod", "textures/entity/cat/cat.png");
   }

   @Override
   public ResourceLocation getAnimationFileLocation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/cat/cat.animation.json");
   }

   @Override
   public String[] getHelmetBones() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getChestArmorBones() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   @Override
   public String[] getFleshTorsoBones() {
      return new String[]{"boobsFlesh", "cloth"};
   }

   @Override
   public String[] getPantsArmorBones() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   @Override
   public String[] getFleshLegsBones() {
      return new String[]{"fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR", "cloth"};
   }

   @Override
   public String[] getBootsArmorBones() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }
}
