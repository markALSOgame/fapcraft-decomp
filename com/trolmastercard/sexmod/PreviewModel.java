package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PreviewModel extends AnimatedGeoModel<PreviewEntity> {
   public ResourceLocation getModelLocation(PreviewEntity previewEntity) {
      try {
         if (previewEntity.f) {
            return new ResourceLocation("sexmod", "geo/cross.geo.json");
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return FilePersistence.getModelResourceLocation(previewEntity.getModelName());
   }

   public ResourceLocation getTextureLocation(PreviewEntity previewEntity) {
      try {
         if (previewEntity.f) {
            return new ResourceLocation("sexmod", "textures/cross.png");
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return FilePersistence.getModelTexture(previewEntity.getModelName());
   }

   public ResourceLocation getAnimationFileLocation(PreviewEntity previewEntity) {
      return new ResourceLocation("sexmod", "animations/slime/slime.animation.json");
   }

   public void setLivingAnimations(PreviewEntity previewEntity, Integer i, @Nullable AnimationEvent animEvent) {
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
