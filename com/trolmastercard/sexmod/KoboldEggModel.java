package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KoboldEggModel extends AnimatedGeoModel<KoboldEggEntity> {
   public ResourceLocation getModelLocation(KoboldEggEntity egg) {
      return new ResourceLocation("sexmod", "geo/kobold/koboldegg.geo.json");
   }

   public ResourceLocation getTextureLocation(KoboldEggEntity egg) {
      return new ResourceLocation("sexmod", "textures/entity/kobold/koboldegg.png");
   }

   public ResourceLocation getAnimationLocation(KoboldEggEntity egg) {
      return new ResourceLocation("sexmod", "animations/kobold/egg.animation.json");
   }
}
