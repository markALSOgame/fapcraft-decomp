package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGalathCoin extends AnimatedGeoModel<ItemGalathCoin> {
   public ResourceLocation getModelLocation(ItemGalathCoin item) {
      return new ResourceLocation("sexmod", "geo/galath/galath_coin.geo.json");
   }

   public ResourceLocation getTextureLocation(ItemGalathCoin item) {
      return new ResourceLocation("sexmod", "textures/items/galath_coin/galath_coin.png");
   }

   public ResourceLocation getAnimationFileLocation(ItemGalathCoin item) {
      return new ResourceLocation("sexmod", "animations/galath/galath_coin.animation.json");
   }
}
