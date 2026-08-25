package com.trolmastercard.sexmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class SlimeRainLayer implements LayerRenderer<SlimeRainEntity> {
   private final SlimeRainRenderer Renderer;
   private final ModelBase Model = new ModelSlime(0);

   public SlimeRainLayer(SlimeRainRenderer renderer) {
      this.Renderer = renderer;
   }

   @Override
   public void doRenderLayer(SlimeRainEntity slimeRainEntity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      try {
         if (!slimeRainEntity.isInvisible()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            this.Model.setModelAttributes(this.Renderer.getMainModel());
            this.Model.render(slimeRainEntity, f, f2, f4, f5, f6, f7);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   public boolean shouldCombineTextures() {
      return true;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
