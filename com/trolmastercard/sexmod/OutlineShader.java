package com.trolmastercard.sexmod;

import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class OutlineShader {
   public static ShaderGroup ShaderGroup;
   static final ResourceLocation OutlineShaderLocation = new ResourceLocation("sexmod", "shaders/post/outline.json");
   static Framebuffer FinalFramebuffer;

   public static void initOutlineShader() {
      Minecraft mc = Minecraft.getMinecraft();

         if (!OpenGlHelper.shadersSupported) {
            Main.LOGGER.warn("Shaders not supported");
            return;
         }

         if (ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
            ShaderLinkHelper.setNewStaticShaderLinkHelper();
         }

      try {
         ShaderGroup = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), mc.getFramebuffer(), OutlineShaderLocation);
         ShaderGroup.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
         FinalFramebuffer = ShaderGroup.getFramebufferRaw("final");
         ClientRegistry.registerEntityShader(GirlEntity.class, OutlineShaderLocation);
         System.out.println("succ registered the outline shader :)");
      } catch (IOException error3) {
         Main.LOGGER.warn("Failed to load shader: {}", OutlineShaderLocation, error3);
      } catch (JsonSyntaxException error4) {
         Main.LOGGER.warn("Failed to load shader: {}", OutlineShaderLocation, error4);
      }
   }

   private static IOException rethrow(IOException error) {
      return error;
   }
}
