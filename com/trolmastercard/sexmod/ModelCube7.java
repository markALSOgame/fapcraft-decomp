package com.trolmastercard.sexmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCube7 extends ModelBase implements ModelPartProvider {
   private final ModelRenderer Root;

   public ModelCube7() {
      this.textureWidth = 16;
      this.textureHeight = 16;
      this.Root = new ModelRenderer(this);
      this.Root.setRotationPoint(-5.0F, 2.5F, 0.0F);
      this.Root.cubeList.add(new ModelBox(this.Root, 0, 0, -2.0F, -6.0F, 0.0F, 2, 6, 2, 0.0F, false));
   }

   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      this.Root.render(f6);
   }

   public void setRotationAngles(ModelRenderer model, float f, float f2, float f3) {
      model.rotateAngleX = f;
      model.rotateAngleY = f2;
      model.rotateAngleZ = f3;
   }

   @Override
   public ModelRenderer getRootModel() {
      return this.Root;
   }
}
