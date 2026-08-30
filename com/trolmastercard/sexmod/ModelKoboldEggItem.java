package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelKoboldEggItem extends AnimatedGeoModel<ItemKoboldEgg> {
   public ResourceLocation getModelLocation(ItemKoboldEgg item) {
      return new ResourceLocation("sexmod", "geo/kobold/koboldegg.geo.json");
   }

   public ResourceLocation getTextureLocation(ItemKoboldEgg item) {
      return new ResourceLocation("sexmod", "textures/entity/kobold/koboldegg.png");
   }

   public ResourceLocation getAnimationFileLocation(ItemKoboldEgg item) {
      return new ResourceLocation("sexmod", "animations/kobold/egg.animation.json");
   }
}
