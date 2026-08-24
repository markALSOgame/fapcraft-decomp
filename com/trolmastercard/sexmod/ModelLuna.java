package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;

public class ModelLuna extends GirlGeoModel {
   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/cat/cat.geo.json"), new ResourceLocation("sexmod", "geo/cat/cat.geo.json")};
   }

   @Override
   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/cat/cat.png");
   }

   @Override
   public ResourceLocation getAnimation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/cat/cat.animation.json");
   }

   @Override
   public String[] HelmetBones() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] ChestBones() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   @Override
   public String[] a() {
      return new String[]{"boobsFlesh", "cloth"};
   }

   @Override
   public String[] PantsBones() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   @Override
   public String[] FleshBones() {
      return new String[]{"fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR", "cloth"};
   }

   @Override
   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }
}
