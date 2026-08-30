package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelWinchester extends AnimatedGeoModel<ItemWinchester> {
   public ResourceLocation getModelLocation(ItemWinchester item) {
      return new ResourceLocation("sexmod", "geo/west/winchester.geo.json");
   }

   public ResourceLocation getTextureLocation(ItemWinchester item) {
      return new ResourceLocation("sexmod", "textures/items/winchester/winchester.png");
   }

   public ResourceLocation getAnimationFileLocation(ItemWinchester item) {
      return new ResourceLocation("sexmod", "animations/west/winchester.animation.json");
   }
}
