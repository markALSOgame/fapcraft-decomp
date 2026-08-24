package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class SlimeRainRenderer extends RenderLiving<SlimeRainEntity> {
   private static final ResourceLocation SlimeTexture = new ResourceLocation("textures/entity/slime/slime.png");

   public SlimeRainRenderer(RenderManager renderManager) {
      super(renderManager, new ModelSlimeRain(), 0.25F);
      this.addLayer(new SlimeRainLayer(this));
   }

   public void render(SlimeRainEntity slimeRainEntity, double d, double d2, double d3, float f, float f2) {
      this.shadowSize = 0.25F * slimeRainEntity.getSlimeSize();
      super.doRender(slimeRainEntity, d, d2, d3, f, f2);
   }

   protected void preRenderCallback(SlimeRainEntity slimeRainEntity, float f) {
      float f2 = 0.999F;
      GlStateManager.scale(0.999F, 0.999F, 0.999F);
      float f3 = slimeRainEntity.getSlimeSize();
      float f4 = (slimeRainEntity.PrevSquishFactor + (slimeRainEntity.SquishFactor - slimeRainEntity.PrevSquishFactor) * f) / (f3 * 0.5F + 1.0F);
      float f5 = 1.0F / (f4 + 1.0F);
      GlStateManager.scale(f5 * f3, 1.0F / f5 * f3, f5 * f3);
   }

   protected ResourceLocation getEntityTexture(SlimeRainEntity slimeRainEntity) {
      return SlimeTexture;
   }
}
