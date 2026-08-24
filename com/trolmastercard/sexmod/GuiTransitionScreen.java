package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTransitionScreen extends GuiScreen {
   public static final int Duration = 1200;
   private static boolean TransitionActive = false;
   private static double TransitionProgress = 0.0;
   static ResourceLocation OverlayTexture = new ResourceLocation("sexmod", "textures/gui/transitionscreen.png");
   static ResourceLocation MirroredOverlayTexture = new ResourceLocation("sexmod", "textures/gui/mirroredtransitionscreen.png");
   static ResourceLocation BlackTexture = new ResourceLocation("sexmod", "textures/gui/blackscreen.png");

   public static boolean isTransitionActive() {
      return TransitionActive;
   }

   public static void startTransition() {
      TransitionActive = true;
   }

   public static void a(Runnable runnable) {
      TransitionActive = true;
      MathUtils.runAfterDelay(1200, runnable);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   @SubscribeEvent
   public void renderOverlay(RenderGameOverlayEvent renderGameOverlayEvent) {
      try {
         if (!TransitionActive) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (renderGameOverlayEvent.getType() != ElementType.TEXT) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Minecraft mc = Minecraft.getMinecraft();
      TransitionProgress = TransitionProgress + mc.getTickLength() * 0.75F;
      int i = mc.gameSettings.guiScale;
      float f;
      if (i == 1) {
         f = (float)LerpMath.lerp(-1800.0, 1000.0, 0.5 * Math.cos(TransitionProgress / 25.0) + 0.5);
      } else if (i == 2) {
         f = (float)LerpMath.lerp(-900.0, 750.0, 0.5 * Math.cos(TransitionProgress / 25.0) + 0.5);
      } else {
         f = (float)LerpMath.lerp(-900.0, 600.0, 0.5 * Math.cos(TransitionProgress / 25.0) + 0.5);
      }

      try {
         GlStateManager.pushMatrix();
         if (i == 1) {
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (i == 2) {
            GlStateManager.scale(1.5, 1.5, 1.5);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         mc.renderEngine.bindTexture(OverlayTexture);
         this.drawTexturedModalRect(f, 0.0F, 0, (int)(TransitionProgress * 1.5), 256, 256);
         this.drawTexturedModalRect(f, 256.0F, 0, (int)(TransitionProgress * 1.5), 256, 256);
         this.drawTexturedModalRect(f, 512.0F, 0, (int)(TransitionProgress * 1.5), 256, 256);
         mc.renderEngine.bindTexture(MirroredOverlayTexture);
         this.drawTexturedModalRect(f + 600.0F, 0.0F, 0, (int)(TransitionProgress * 1.5), 256, 256);
         this.drawTexturedModalRect(f + 600.0F, 256.0F, 0, (int)(TransitionProgress * 1.5), 256, 256);
         this.drawTexturedModalRect(f + 600.0F, 512.0F, 0, (int)(TransitionProgress * 1.5), 256, 256);
         mc.renderEngine.bindTexture(BlackTexture);
         this.drawTexturedModalRect(f + 200.0F, 0.0F, 0, 0, 400, 256);
         this.drawTexturedModalRect(f + 200.0F, 256.0F, 0, 0, 400, 256);
         this.drawTexturedModalRect(f + 200.0F, 512.0F, 0, 0, 400, 256);
         if (TransitionProgress > 30.0) {
            GuiHud.forceShowHud();
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      try {
         if (TransitionProgress > 69.0) {
            TransitionProgress = 0.0;
            TransitionActive = false;
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      GlStateManager.popMatrix();
   }

   private static RuntimeException rethrow(RuntimeException error7) {
      return error7;
   }
}
