package com.trolmastercard.sexmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSlimeRain extends ModelBase {
   private final ModelRenderer Body;
   private final ModelRenderer RightEye;
   private final ModelRenderer LeftEye;
   private final ModelRenderer Mouth;
   private final ModelRenderer Tentacles;

   public ModelSlimeRain() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Body = new ModelRenderer(this);
      this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Body.cubeList.add(new ModelBox(this.Body, 0, 16, -3.0F, 17.0F, -3.0F, 6, 6, 6, 0.0F, true));
      this.RightEye = new ModelRenderer(this);
      this.RightEye.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.RightEye.cubeList.add(new ModelBox(this.RightEye, 32, 0, 1.3F, 18.0F, -3.5F, 2, 2, 2, 0.0F, true));
      this.LeftEye = new ModelRenderer(this);
      this.LeftEye.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.LeftEye.cubeList.add(new ModelBox(this.LeftEye, 32, 4, -3.3F, 18.0F, -3.5F, 2, 2, 2, 0.0F, true));
      this.Mouth = new ModelRenderer(this);
      this.Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Mouth.cubeList.add(new ModelBox(this.Mouth, 32, 8, -1.0F, 21.0F, -3.5F, 1, 1, 1, 0.0F, true));
      this.Tentacles = new ModelRenderer(this);
      this.Tentacles.setRotationPoint(-0.5F, 0.0F, 0.1F);
      ModelRenderer model = new ModelRenderer(this);
      model.setRotationPoint(2.0F, 20.7406F, 4.0504F);
      this.Tentacles.addChild(model);
      this.setRotationAngles(model, 1.0908F, 0.0F, 0.0F);
      model.cubeList.add(new ModelBox(model, 10, 11, -2.5F, 0.0F, 0.0F, 2, 2, 1, 0.0F, false));
      ModelRenderer model2 = new ModelRenderer(this);
      model2.setRotationPoint(2.0F, 19.9214F, 3.4768F);
      this.Tentacles.addChild(model2);
      this.setRotationAngles(model2, 0.6109F, 0.0F, 0.0F);
      model2.cubeList.add(new ModelBox(model2, 10, 11, -3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F, false));
      ModelRenderer model3 = new ModelRenderer(this);
      model3.setRotationPoint(2.0F, 19.0074F, 3.0643F);
      this.Tentacles.addChild(model3);
      this.setRotationAngles(model3, 0.3491F, 0.0F, 0.0F);
      model3.cubeList.add(new ModelBox(model3, 10, 11, -4.0F, 0.0F, 0.075F, 5, 1, 1, 0.0F, false));
      ModelRenderer model4 = new ModelRenderer(this);
      model4.setRotationPoint(0.0F, 17.925F, 3.5F);
      this.Tentacles.addChild(model4);
      this.setRotationAngles(model4, 0.1309F, 0.0F, 0.0F);
      model4.cubeList.add(new ModelBox(model4, 10, 11, -3.0F, -1.0F, -0.5F, 7, 2, 1, 0.0F, false));
   }

   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      this.Body.render(f6);
      this.RightEye.render(f6);
      this.LeftEye.render(f6);
      this.Mouth.render(f6);
      this.Tentacles.render(f6);
   }

   public void setRotationAngles(ModelRenderer model, float f, float f2, float f3) {
      model.rotateAngleX = f;
      model.rotateAngleY = f2;
      model.rotateAngleZ = f3;
   }
}
