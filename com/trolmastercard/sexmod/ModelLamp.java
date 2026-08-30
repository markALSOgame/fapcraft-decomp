package com.trolmastercard.sexmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelLamp extends ModelBase {
   private final ModelRenderer Cube;
   private final ModelRenderer Glass = new ModelRenderer(this, "glass");

   public ModelLamp() {
      this.Glass.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
      this.Cube = new ModelRenderer(this, "cube");
      this.Cube.setTextureOffset(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
   }

   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      this.Glass.render(f6);
      GlStateManager.scale(0.875F, 0.875F, 0.875F);
      GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
      this.Glass.render(f6);
      GlStateManager.scale(0.875F, 0.875F, 0.875F);
      GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
      GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
      this.Cube.render(f6);
      GlStateManager.popMatrix();
   }
}
