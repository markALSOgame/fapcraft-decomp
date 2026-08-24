package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGalathFlight extends Gui {
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/galath_flight_ui.png");
   static final IntRect4 BarRect = new IntRect4(0, 77, 128, 41);
   static final IntRect4 SpriteRect1 = new IntRect4(0, 0, 23, 36);
   static final IntRect4 SpriteRect2 = new IntRect4(0, 36, 23, 36);
   static final IntRect4 SpriteRect3 = new IntRect4(23, 2, 20, 31);
   static long MinAttemptIntervalMs = 3000L;
   static long FlightCooldownMs = 5000L;
   static final long TouchWindowMs = 500L;
   static final float d = 150.0F;
   static final float m = 0.075F;
   static final float b = -11.25F;
   static final float[] StageOffsetX = new float[]{-14.25F, -15.5F, -16.875F};
   static final float h = 500.0F;
   static final float o = -0.15F;
   static final float r = 37.5F;
   static final float[] StageOffsetY = new float[]{37.5F, 43.0F, 45.0F};
   static final int v = 70;
   static final int a = 70;
   static boolean Active = false;
   static Minecraft Mc = Minecraft.getMinecraft();
   static int AttemptsLeft = 3;
   static long LastAttemptMs = 0L;
   static long LastFlightEndMs = 0L;
   static long LastTouchStartMs = 0L;
   static long LastTouchTimeMs = 9223372036854775307L;

   public static boolean canStartFlight() {
      try {
         if (CultistRenderer <= 0) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (System.currentTimeMillis() - LastAttemptMs > MinAttemptIntervalMs) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return false;
   }

   public static void consumeFlightAttempt() {
      AttemptsLeft--;
      LastAttemptMs = System.currentTimeMillis();
   }

   void b() {
      try {
         if (AttemptsLeft == 3) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      long l = System.currentTimeMillis();

      try {
         if (l - Math.max(LastAttemptMs, LastFlightEndMs) < FlightCooldownMs) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      AttemptsLeft++;
      LastFlightEndMs = l;
   }

   @SubscribeEvent
   public void onRenderGameOverlay(RenderGameOverlayEvent renderGameOverlayEvent) {
      try {
         this.b();
         if (!Active) {
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      ScaledResolution scaledResolution = renderGameOverlayEvent.getResolution();
      int i = scaledResolution.getScaledWidth();
      int i2 = scaledResolution.getScaledHeight();
      int i3 = i / 2;
      long l2 = System.currentTimeMillis();

      try {
         if (l2 - LastTouchTimeMs > 500L) {
            endFlight();
            return;
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      Mc.getTextureManager().bindTexture(Texture);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      GlStateManager.enableAlpha();
      float f;
      if (l2 < LastTouchStartMs + 500L) {
         f = (float)(l2 - LastTouchStartMs) / 500.0F;
      } else if (l2 < LastTouchTimeMs + 500L) {
         f = 1.0F + (float)(LastTouchTimeMs - l2) / 500.0F;
      } else {
         f = 1.0F;
      }

      f = MathUtils.clamp(f, 0.0F, 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, f);
      this.a(BarRect, i3 - BarRect.EggAgeKey / 2, i2 - 70);
      this.a(SpriteRect3, (int)(i3 - 1.5F * SpriteRect1.c + 1.0F), i2 - 70 + 3);
      this.a(SpriteRect3, i3 - SpriteRect1.c / 2 + 1, i2 - 70 + 3);
      this.a(SpriteRect3, i3 + SpriteRect1.c / 2 + 1, i2 - 70 + 3);
      float f2 = (float)LerpMath.easeOutSine(Math.min(1.0F, (float)(l2 - LastAttemptMs) / 150.0F));

      float f3;
      label34: {
         try {
            if (f2 == 1.0F) {
               f3 = MathUtils.clamp(1.0F - (float)(l2 - LastFlightEndMs) / 500.0F, 0.0F, 1.0F);
               break label34;
            }
         } catch (RuntimeException error7) {
            throw rethrow(error7);
         }

         f3 = 0.0F;
      }

      float f4 = f3;
      this.a(1, -1.5F * SpriteRect1.c, f4, f2, i3, i2, f);
      this.a(2, -SpriteRect1.c / 2.0F, f4, f2, i3, i2, f);
      this.a(3, SpriteRect1.c / 2.0F, f4, f2, i3, i2, f);
   }

   void a(int i4, float f5, float f6, float f7, int i5, int i6, float f8) {
      float f9;
      if (AttemptsLeft >= i4) {
         f9 = 0.0F;
      } else if (CultistRenderer < i4 - 1) {
         f9 = 1.0F;
      } else {
         f9 = f7;
      }

      float f10;
      if (AttemptsLeft == i4) {
         f10 = f6;
      } else {
         f10 = 0.0F;
      }

      float f11 = 1.0F + f9 * 0.075F + f10 * -0.15F;
      GlStateManager.pushMatrix();
      GlStateManager.scale(f11, f11, f11);
      GlStateManager.translate(f9 * NullNetworkManager[i4 - 1] + f10 * PacketRemoveItems[i4 - 1], f9 * -11.25F + f10 * 37.5F, 0.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, f8 - f9 - f10);
      this.a(SpriteRect1, (int)(i5 + f5), i6 - 70);
      GlStateManager.resetColor();
      GlStateManager.color(1.0F, 1.0F, 1.0F, (float)Math.sin(Math.PI * f9) * 0.5F);
      this.a(SpriteRect2, (int)(i5 + f5), i6 - 70);
      GlStateManager.popMatrix();
      GlStateManager.resetColor();
   }

   public static void startFlight() {
      try {
         if (Active) {
            return;
         }
      } catch (RuntimeException error8) {
         throw rethrow(error8);
      }

      Active = true;
      LastTouchStartMs = System.currentTimeMillis();
      LastTouchTimeMs = 9223372036854775307L;
   }

   public static void recordTouchTime() {
      LastTouchTimeMs = System.currentTimeMillis();
   }

   public static void endFlight() {
      Active = false;
      LastTouchTimeMs = 9223372036854775307L;
      LastTouchStartMs = 0L;
   }

   void a(IntRect4 intRect4, int i7, int i8) {
      this.drawTexturedModalRect(i7, i8, intRect4.X, intRect4.Height, intRect4.Width, intRect4.Y);
   }

   private static RuntimeException rethrow(RuntimeException error9) {
      return error9;
   }
}
