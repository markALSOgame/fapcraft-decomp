package com.trolmastercard.sexmod;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiHud extends Gui {
   static ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/buttons.png");
   static ResourceLocation OverlayTexture = new ResourceLocation("sexmod", "textures/gui/hornymeter.png");
   public static boolean d = false;
   public static double c = 0.0;
   static double Progress = c;
   static float f = 0.0F;
   static float g = 0.0F;
   static boolean i = false;
   static boolean h = true;

   public static void showHud() {
      try {
         if (d) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      resetProgress();
      d = true;
      h = true;
   }

   public static void showHudWithForce(boolean flag) {
      try {
         if (d) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      resetProgress();
      d = true;
      h = flag;
   }

   public static void forceShowHud() {
      resetProgress();
      d = false;
      h = true;
   }

   public static boolean isHudVisible() {
      return d;
   }

   @SubscribeEvent

   public void onRenderGameOverlay(RenderGameOverlayEvent renderGameOverlayEvent) {
        block18: {
            block27: {
                Minecraft minecraft;
                block26: {
                    int i2;
                    block23: {
                        int i3;
                        block25: {
                            block24: {
                                block21: {
                                    int i4;
                                    block22: {
                                        block20: {
                                            block19: {
                                                try {
                                                    if (!d || renderGameOverlayEvent.getType() != RenderGameOverlayEvent.ElementType.TEXT) break block18;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw GuiHud.rethrow(runtimeException);
                                                }
                                                minecraft = Minecraft.getMinecraft();
                                                try {
                                                    if (!(f < 1.0f)) break block19;
                                                    f += minecraft.getTickLength() / 25.0f;
                                                    break block20;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw GuiHud.rethrow(runtimeException);
                                                }
                                            }
                                            f = 1.0f;
                                        }
                                        try {
                                            try {
                                                GL11.glPushMatrix();
                                                minecraft.renderEngine.bindTexture(Texture);
                                                GL11.glScalef((float)0.35f, (float)0.35f, (float)0.35f);
                                                if (!(c >= 1.0)) break block21;
                                                if (!AnimationInputLock.JumpPressed) break block22;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw GuiHud.rethrow(runtimeException);
                                            }
                                            i = true;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GuiHud.rethrow(runtimeException);
                                        }
                                    }
                                    try {
                                        i4 = i ? 54 : 0;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GuiHud.rethrow(runtimeException);
                                    }
                                    i2 = i4;
                                    this.drawTexturedModalRect(240, 160, 0, 108 + i2, 256, 52);
                                }
                                try {
                                    try {
                                        try {
                                            if (!h || i) break block23;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GuiHud.rethrow(runtimeException);
                                        }
                                        if (!AnimationInputLock.SneakPressed) break block24;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GuiHud.rethrow(runtimeException);
                                    }
                                    i3 = 54;
                                    break block25;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiHud.rethrow(runtimeException);
                                }
                            }
                            i3 = 0;
                        }
                        i2 = i3;
                        this.drawTexturedModalRect((int)LerpMath.lerp(-200.0f, 98.0f, f), 405, 0, i2, 158, 54);
                    }
                    GL11.glScalef((float)2.857143f, (float)2.857143f, (float)2.857143f);
                    minecraft.renderEngine.bindTexture(OverlayTexture);
                    GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
                    this.drawTexturedModalRect(10, (int)LerpMath.lerp(-200.0f, 10.0f, f), 0, 0, 146, 175);
                    Progress = LerpMath.lerp(Progress, c, (double)minecraft.getTickLength());
                    i2 = (int)LerpMath.lerp(0.0, 160.0, Progress);
                    int i5 = (int)LerpMath.lerp(167.0, 8.0, Progress);
                    double d2 = LerpMath.lerp(178.0, 18.0, Progress);
                    try {
                        if (i) break block26;
                        this.drawTexturedModalRect(67, (int)LerpMath.lerp(-45.0, d2, (double)f), 159, i5, 32, i2);
                        this.drawTexturedModalRect(120, (int)LerpMath.lerp(-58.0, LerpMath.lerp(178.0, 149.0, 1.0 - Progress), (double)f), 212, (int)LerpMath.lerp(169.0, 141.0, 1.0 - Progress), 28, (int)LerpMath.lerp(1.0, 29.0, 1.0 - Progress));
                        this.drawTexturedModalRect(18, (int)LerpMath.lerp(-58.0, LerpMath.lerp(178.0, 149.0, 1.0 - Progress), (double)f), 212, (int)LerpMath.lerp(169.0, 141.0, 1.0 - Progress), 28, (int)LerpMath.lerp(1.0, 29.0, 1.0 - Progress));
                        break block27;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiHud.rethrow(runtimeException);
                    }
                }
                this.drawTexturedModalRect(67, (int)LerpMath.lerp(18.0f, -300.0f, g += minecraft.getTickLength() / 15.0f), 159, 8, 32, 160);
            }
            GL11.glPopMatrix();
        }
    }

   public static void addProgress(double d3) {
      double d4;
      label16: {
         try {
            c += d3;
            if (c > 1.0) {
               d4 = 1.0;
               break label16;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         d4 = c;
      }

      c = d4;
   }

   public static void resetProgress() {
      c = 0.0;
      i = false;
   }

   private static RuntimeException rethrow(RuntimeException error4) {
      return error4;
   }
}
