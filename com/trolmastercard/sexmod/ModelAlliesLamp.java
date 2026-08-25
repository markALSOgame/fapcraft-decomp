package com.trolmastercard.sexmod;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelAlliesLamp extends AnimatedGeoModel<ItemAlliesLamp> {
   ResourceLocation Texture = null;

   public ResourceLocation getModelLocation(ItemAlliesLamp item) {
      return new ResourceLocation("sexmod", "geo/allie/lamp.geo.json");
   }

   public ResourceLocation getTextureLocation(ItemAlliesLamp item) {
         if (this.Texture != null) {
            return this.Texture;
         }

      try {
         Minecraft mc = Minecraft.getMinecraft();
         BufferedImage bufferedImage = SkinFetcher.fetchSkinImage(mc.player.getPersistentID());
         Graphics graphics = bufferedImage.getGraphics();
         graphics.setColor(new Color(185, 254, 255));
         graphics.fillRect(0, 0, 2, 2);
         graphics.setColor(new Color(255, 255, 255));
         graphics.fillRect(2, 0, 1, 2);
         graphics.setColor(new Color(0, 0, 0));
         graphics.fillRect(3, 0, 1, 2);
         this.Texture = mc.renderEngine.getDynamicTextureLocation("alliesLamp", new DynamicTexture(bufferedImage));
      } catch (IOException error2) {
         error2.printStackTrace();
         this.Texture = new ResourceLocation("sexmod", "textures/entity/allie/lamp.png");
      }

      return this.Texture;
   }

   public ResourceLocation getAnimationFileLocation(ItemAlliesLamp item) {
      return new ResourceLocation("sexmod", "animations/allie/lamp.animation.json");
   }

   private static IOException rethrow(IOException error) {
      return error;
   }
}
