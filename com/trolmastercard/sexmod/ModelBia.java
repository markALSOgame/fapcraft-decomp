package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;

public class ModelBia extends GirlGeoModel {
   public ModelBia() {
      this.TextureLayers = this.a();
   }

   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{new ResourceLocation("sexmod", "geo/bia/bianude.geo.json"), new ResourceLocation("sexmod", "geo/bia/biadressed.geo.json")};
   }

   @Override
   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/bia/bia.png");
   }

   @Override
   public ResourceLocation getAnimationFile(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/bia/bia.animation.json");
   }

   @Override
   public String[] getHelmetBones() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getLeafBones() {
      return new String[]{"leaf7", "leaf8"};
   }

   @Override
   public String[] getChestBones() {
      return new String[]{"armorChest", "armorBoobs", "armorShoulderR", "armorShoulderL"};
   }

   @Override
   public String[] a() {
      return new String[]{"bra", "upperBodyR", "upperBodyL"};
   }

   @Override
   public String[] getPantsBones() {
      return new String[]{"armorBootyR", "armorBootyL", "armorPantsLowL", "armorPantsLowR", "armorPantsLowR", "armorPantsUpR", "armorPantsUpL", "armorHip"};
   }

   @Override
   public String[] getSkinBones() {
      return new String[]{"slip", "fleshL", "fleshR", "vagina", "curvesL", "curvesR", "kneeL", "kneeR"};
   }

   @Override
   public String[] b() {
      return new String[]{"armorShoesL", "armorShoesR"};
   }
}
