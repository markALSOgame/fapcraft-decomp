package software.bernie.geckolib3.renderers.geo;

import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.util.AnimationUtils;

public class GeoProjectilesRenderer<T extends Entity & IAnimatable> extends Render<T> implements IGeoRenderer<T> {
   private final AnimatedGeoModel<T> modelProvider;

   public GeoProjectilesRenderer(RenderManager renderManager, AnimatedGeoModel<T> modelProvider) {
      super(renderManager);
      this.modelProvider = modelProvider;
   }

   public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.func_179094_E();
      GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(entity));
      GlStateManager.func_179114_b(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks, 0.0F, 0.0F, 1.0F);
      float lastLimbDistance = 0.0F;
      float limbSwing = 0.0F;
      EntityModelData entityModelData = new EntityModelData();
      AnimationEvent<T> predicate = new AnimationEvent<>(
         entity,
         limbSwing,
         lastLimbDistance,
         partialTicks,
         !(lastLimbDistance > -0.15F) || !(lastLimbDistance < 0.15F),
         Collections.singletonList(entityModelData)
      );
      if (this.modelProvider instanceof IAnimatableModel) {
         this.modelProvider.setLivingAnimations(entity, this.getUniqueID(entity), predicate);
      }

      GlStateManager.func_179094_E();
      Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(entity));
      Color renderColor = this.getRenderColor(entity, partialTicks);
      if (!entity.func_98034_c(Minecraft.func_71410_x().field_71439_g)) {
         this.render(
            model,
            entity,
            partialTicks,
            renderColor.getRed() / 255.0F,
            renderColor.getBlue() / 255.0F,
            renderColor.getGreen() / 255.0F,
            renderColor.getAlpha() / 255.0F
         );
      }

      GlStateManager.func_179121_F();
      GlStateManager.func_179121_F();
   }

   @Override
   public GeoModelProvider<T> getGeoModelProvider() {
      return this.modelProvider;
   }

   public ResourceLocation func_110775_a(T instance) {
      return this.getTextureLocation(instance);
   }

   public Integer getUniqueID(T animatable) {
      return animatable.func_110124_au().hashCode();
   }

   public ResourceLocation getTextureLocation(T instance) {
      return this.modelProvider.getTextureLocation(instance);
   }

   static {
      AnimationController.addModelFetcher(object -> object instanceof Entity ? (IAnimatableModel)AnimationUtils.getGeoModelForEntity((T)object) : null);
   }
}
