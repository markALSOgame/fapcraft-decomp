package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class EnergyBallRenderer extends Render<EnergyBallEntity> {
   public static EnergyBallRenderer Instance;
   static final RgbaColor4 ChargedColorA = new RgbaColor4(0, 255, 251, 255);
   static final RgbaColor4 ChargedColorB = new RgbaColor4(255, 0, 236, 255);
   static final RgbaColor4 UnchargedColor = new RgbaColor4(255, 255, 255, 0);
   Minecraft Mc = Minecraft.getMinecraft();

   public EnergyBallRenderer(RenderManager renderManager) {
      super(renderManager);
      Instance = this;
   }

   @Nullable
   protected ResourceLocation getEntityTexture(EnergyBallEntity energyBall) {
      return new ResourceLocation("sexmod", "textures/entity/galath/energy_ball.png");
   }

   public void doRender(EnergyBallEntity energyBall, double d, double d2, double d3, float f, float f2) {
      GL11.glDisable(2896);
      GlStateManager.enableAlpha();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
      EntityPlayerSP mcPlayer = this.Mc.player;
      Vec3d vec3d = LerpMath.lerpVec3d(new Vec3d(energyBall.lastTickPosX, energyBall.lastTickPosY, energyBall.lastTickPosZ), energyBall.getPositionVector(), f2);
      Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f2);
      Vec3d vec3d3 = vec3d.subtract(vec3d2);

      byte bv;
      label22: {
         try {
            GlStateManager.pushMatrix();
            GlStateManager.translate(vec3d3.x, vec3d3.y, vec3d3.z);
            GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            if (this.renderManager.options.thirdPersonView == 2) {
               bv = -1;
               break label22;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         bv = 1;
      }

      GlStateManager.rotate(bv * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(energyBall.g, energyBall.g, energyBall.g);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      this.Mc.renderEngine.bindTexture(this.getEntityTexture(energyBall));
      RgbaColor4 rgbaColor4;
      RgbaColor4 rgbaColor42;
      if (energyBall.g == 1.0) {
         float f3 = (float)this.Mc.world.getTotalWorldTime() + this.Mc.getRenderPartialTicks();
         double d4 = 0.5 * Math.sin(f3 * 0.5) + 0.5;
         rgbaColor4 = LerpMath.lerpColor(ChargedColorA, ChargedColorB, d4);
         rgbaColor42 = LerpMath.lerpColor(ChargedColorB, ChargedColorA, d4);
      } else {
         rgbaColor4 = LerpMath.lerpColor(UnchargedColor, ChargedColorA, energyBall.g);
         rgbaColor42 = LerpMath.lerpColor(UnchargedColor, ChargedColorA, energyBall.g);
      }

      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      this.drawQuad(bufferBuilder, rgbaColor4, 0.0F);
      tessellator.draw();
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      GlStateManager.scale(0.75F, 0.75F, 0.75F);
      GlStateManager.translate(0.0F, 0.075F, 0.0F);
      this.drawQuad(bufferBuilder, rgbaColor42, 0.001F);
      tessellator.draw();
      GlStateManager.popMatrix();
      GlStateManager.disableAlpha();
      GL11.glEnable(2896);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, OpenGlHelper.lastBrightnessX, OpenGlHelper.lastBrightnessY);
   }

   void drawQuad(BufferBuilder bufferBuilder, RgbaColor4 rgbaColor4, float f) {
      bufferBuilder.pos(-0.25, 0.0, f).tex(0.0, 0.0).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(0.25, 0.0, f).tex(1.0, 0.0).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(0.25, 0.5, f).tex(1.0, 1.0).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
      bufferBuilder.pos(-0.25, 0.5, f).tex(0.0, 1.0).color(rgbaColor4.R, rgbaColor4.G, rgbaColor4.B, rgbaColor4.A).endVertex();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
