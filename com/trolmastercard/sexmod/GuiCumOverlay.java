package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCumOverlay {
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/cummy.png");
   static Minecraft Mc = Minecraft.getMinecraft();
   static List<ParticleEmitter> Particles = new ArrayList<>();

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(RenderWorldLastEvent renderWorldLastEvent) {
      Mc.renderEngine.bindTexture(Texture);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      float f2 = renderWorldLastEvent.getPartialTicks();

      try {
         GlStateManager.disableLighting();
         GlStateManager.enableAlpha();
         if (Mc.player == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      for (ParticleEmitter particleEmitter : Particles) {
         particleEmitter.draw(Mc, tessellator, bufferBuilder, f2);
      }

      GlStateManager.enableDepth();
      GlStateManager.enableLighting();
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(ClientTickEvent clientTickEvent) {
      try {
         if (clientTickEvent.phase == Phase.END) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      for (ParticleEmitter particleEmitter2 : Particles) {
         particleEmitter2.a();
      }
   }

   public static void addParticles(ParticleEmitter particleEmitter3) {
      Particles.add(particleEmitter3);
   }

   public static void a(int i, GirlAnchor girlAnchor, GirlAnchor2 girlAnchor2, GirlEntity girl, float f3, float f4) {
      Particles.add(new ParticleEmitter(i, girlAnchor, girlAnchor2, girl, f3, f4));
   }

   public static void removeParticlesForGirl(@Nonnull GirlEntity girl2) {
      ArrayList list = new ArrayList();

      for (ParticleEmitter particleEmitter4 : Particles) {
         try {
            if (particleEmitter4.Girl.f().equals(girl2.getGirlUuid())) {
               list.add(particleEmitter4);
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }
      }

      Particles.removeAll(list);
   }

   private static RuntimeException rethrow(RuntimeException error4) {
      return error4;
   }
}
