package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class KoboldEggItemRenderer extends GeoItemRenderer<ItemKoboldEgg> {
   ItemStack CurrentStack = null;

   public KoboldEggItemRenderer() {
      super(new ModelKoboldEggItem());
   }

   public void render(ItemKoboldEgg item, ItemStack stack) {
      this.CurrentStack = stack;
      super.render(item, stack);
   }

   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
      String string = bone.getName();
      if ("shell".equals(string)) {
         f = KoboldEggRenderer.ShellColor.getRed() / 255.0F;
         f2 = KoboldEggRenderer.ShellColor.getGreen() / 255.0F;
         f3 = KoboldEggRenderer.ShellColor.getBlue() / 255.0F;
      }

      if ("colorSpots".equals(string)) {
         Vec3i vec3i = this.getEggColor(this.CurrentStack).getMainColor();
         f = vec3i.getX() / 255.0F;
         f2 = vec3i.getY() / 255.0F;
         f3 = vec3i.getZ() / 255.0F;
      }

      super.renderRecursively(bufferBuilder, bone, f, f2, f3, f4);
   }

   EyeAndKoboldColor getEggColor(ItemStack stack) {
      return EyeAndKoboldColor.getColorByWoolId(stack.getMetadata());
   }
}
