package com.trolmastercard.sexmod;

import java.awt.Color;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KoboldEggRenderer extends GeoEntityRenderer<KoboldEggEntity> {
   public static final Color ShellColor = new Color(223, 206, 155);
   KoboldEggEntity RenderEntity;

   public KoboldEggRenderer(RenderManager renderManager, AnimatedGeoModel<KoboldEggEntity> animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   public void render(GeoModel model, KoboldEggEntity egg, float f, float f2, float f3, float f4, float f5) {
      this.RenderEntity = egg;
      super.render(model, egg, f, f2, f3, f4, f5);
   }

   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
      String string = bone.getName();
      if ("shell".equals(string)) {
         f = ShellColor.getRed() / 255.0F;
         f2 = ShellColor.getGreen() / 255.0F;
         f3 = ShellColor.getBlue() / 255.0F;
      }

      if ("colorSpots".equals(string)) {
         Vec3i vec3i = EyeAndKoboldColor.safeValueOf((String)this.RenderEntity.getDataManager().get(KoboldEggEntity.EggColorKey)).getMainColor();
         f = vec3i.getX() / 255.0F;
         f2 = vec3i.getY() / 255.0F;
         f3 = vec3i.getZ() / 255.0F;
      }

      super.renderRecursively(bufferBuilder, bone, f, f2, f3, f4);
   }
}
