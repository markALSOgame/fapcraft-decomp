package com.trolmastercard.sexmod;

import java.lang.reflect.Field;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.exception.GeoModelException;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class GirlAnimatedGeoModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
   protected GirlAnimatedGeoModel() {
      try {
         Field field = Class.forName("software.bernie.geckolib3.model.AnimatedGeoModel").getDeclaredField("animationProcessor");
         field.setAccessible(true);
         field.set(this, new BoneAnimationProcessor(this));
      } catch (Exception error) {
         error.printStackTrace();
      }
   }

   public GeoModel getModel(ResourceLocation location) {
      GeoModel model = super.getModel(location);

      try {
         if (model == null) {
            throw new GeoModelException(location, "Could not find model.");
         }
      } catch (GeoModelException error) {
         throw rethrow(error);
      }

      this.getAnimationProcessor().clearModelRendererList();

      for (GeoBone bone : model.topLevelBones) {
         this.registerBone(bone);
      }

      return model;
   }

   private static GeoModelException rethrow(GeoModelException error) {
      return error;
   }
}
