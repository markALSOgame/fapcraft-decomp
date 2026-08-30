package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDragonStaff extends AnimatedGeoModel<ItemDragonStaff> {
   public ResourceLocation getModelLocation(ItemDragonStaff item) {
      return new ResourceLocation("sexmod", "geo/kobold/staff.geo.json");
   }

   public ResourceLocation getTextureLocation(ItemDragonStaff item) {
      return new ResourceLocation("sexmod", "textures/entity/kobold/staff.png");
   }

   public ResourceLocation getAnimationFileLocation(ItemDragonStaff item) {
      return new ResourceLocation("sexmod", "animations/kobold/staff.animation.json");
   }
}
