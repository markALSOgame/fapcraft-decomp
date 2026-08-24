package software.bernie.example.client.model.entity;

import net.minecraft.util.ResourceLocation;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ExampleEntityModel extends AnimatedTickingGeoModel<GeoExampleEntity> {
   public ResourceLocation getAnimationFileLocation(GeoExampleEntity entity) {
      return new ResourceLocation("geckolib3", "animations/bat.animation.json");
   }

   public ResourceLocation getModelLocation(GeoExampleEntity entity) {
      return new ResourceLocation("geckolib3", "geo/bat.geo.json");
   }

   public ResourceLocation getTextureLocation(GeoExampleEntity entity) {
      return new ResourceLocation("geckolib3", "textures/model/entity/bat.png");
   }

   public void setLivingAnimations(GeoExampleEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
      super.setLivingAnimations(entity, uniqueID, customPredicate);
      IBone head = this.getAnimationProcessor().getBone("head");
      EntityModelData extraData = customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
      head.setRotationX(extraData.headPitch * (float) (Math.PI / 180.0));
      head.setRotationY(extraData.netHeadYaw * (float) (Math.PI / 180.0));
   }
}
