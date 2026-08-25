package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class GirlElytraRenderer extends GeoLayerRenderer {
   private static final ResourceLocation ElytraTexture = new ResourceLocation("textures/entity/elytra.png");
   private final ModelElytra Model = new ModelElytra();

   public GirlElytraRenderer(IGeoRenderer renderer) {
      super(renderer);
   }

   public void render(EntityLivingBase livingBase, float f, float f2, float f3, float f4, float f5, float f6, Color color2) {
      try {
         if (!(livingBase instanceof InventoryGirlEntity)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      InventoryGirlEntity inventoryGirl = (InventoryGirlEntity)livingBase;
      ItemStack stack = (ItemStack)inventoryGirl.getDataManager().get(InventoryGirlEntity.ChestKey);
      EntityPlayer player = null;
      if (inventoryGirl instanceof PlayerGirlEntity) {
         UUID uuid = ((PlayerGirlEntity)inventoryGirl).getBoundPlayerUuid();
         if (uuid != null) {
            player = livingBase.world.getPlayerEntityByUUID(uuid);
         }
      }

      try {
         if (stack.getItem() != Items.ELYTRA) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ZERO);
      Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(ElytraTexture);
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 0.0F, 0.125F);
      float f7 = this.getScaleFactor();

      ModelElytra model = this.Model;
      float f8 = f;
      float f9 = f2;
      float f10 = f4;
      float f11 = f5;
      float f12 = f6;
      float f13 = f7;
      Entity renderEntity = player == null ? (Entity)livingBase : player;

      model.setRotationAngles(f8, f9, f10, f11, f12, f13, renderEntity);
      model.render(renderEntity, f, f2, f4, f5, f6, f7);
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
   }

   public float getScaleFactor() {
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      GlStateManager.translate(0.0F, -1.501F, 0.0F);
      return 0.0625F;
   }

   public void doRenderLayer(EntityLivingBase livingBase, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
   }

   public boolean shouldCombineTextures() {
      return false;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
