package com.trolmastercard.sexmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCube6 extends ModelBase implements ModelPartProvider {
   private final ModelRenderer Root;
   private final ModelRenderer ChildPart;
   private final ModelRenderer SparePart;

   public ModelCube6() {
      this.textureWidth = 16;
      this.textureHeight = 16;
      this.Root = new ModelRenderer(this);
      this.Root.setRotationPoint(-5.0F, 1.5708F, 0.0F);
      this.ChildPart = new ModelRenderer(this);
      this.ChildPart.setRotationPoint(-1.0F, -3.0F, 1.0F);
      this.Root.addChild(this.ChildPart);
      this.setRotationAngles(this.ChildPart, 0.0F, 1.5708F, 0.0F);
      this.ChildPart.cubeList.add(new ModelBox(this.ChildPart, 0, 0, -1.0F, -3.0F, -1.0F, 2, 6, 2, 0.0F, false));
      this.SparePart = new ModelRenderer(this);
      this.SparePart.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      this.Root.render(f6);
      this.SparePart.render(f6);
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
