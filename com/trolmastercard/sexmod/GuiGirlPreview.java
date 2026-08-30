package com.trolmastercard.sexmod;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiGirlPreview extends GuiScreen {
   static final float j = 100.0F;
   static final float c = 15.0F;
   static final float k = 5.0F;
   static final float l = 0.5F;
   static final float b = 0.5F;
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/command.png");
   float OpenProgress = 0.0F;
   float g = 0.0F;
   float LerpFactor = 0.0F;
   float d = 0.0F;
   float m = 0.0F;
   GirlEntity Girl;
   boolean IsGoblin = false;

   public GuiGirlPreview(GirlEntity girl) {
      this.Girl = girl;
      this.IsGoblin = girl instanceof GoblinNpc;
   }


   public void onGuiClosed() {
        block16: {
            block15: {
                block14: {
                    try {
                        try {
                            try {
                                super.onGuiClosed();
                                if (this.d != 0.0f || this.m != 0.0f) break block14;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw GuiGirlPreview.rethrow(nullPointerException);
                            }
                            if (this.g != 0.0f) break block14;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw GuiGirlPreview.rethrow(nullPointerException);
                        }
                        return;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw GuiGirlPreview.rethrow(nullPointerException);
                    }
                }
                try {
                    if (this.g > 0.0f) {
                        this.c();
                        return;
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw GuiGirlPreview.rethrow(nullPointerException);
                }
                try {
                    if (!this.IsGoblin) {
                        return;
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw GuiGirlPreview.rethrow(nullPointerException);
                }
                try {
                    if (!(this.d > this.m)) break block15;
                    this.a();
                    break block16;
                }
                catch (NullPointerException nullPointerException) {
                    throw GuiGirlPreview.rethrow(nullPointerException);
                }
            }
            this.b();
        }
    }

   void a() {
      try {
         if (this.IsGoblin) {
            ((GoblinNpc)this.Girl).c(Minecraft.getMinecraft().player.getPersistentID());
         }
      } catch (NullPointerException error) {
         throw rethrow(error);
      }
   }

   void b() {
      ((GoblinNpc)this.Girl).b(Minecraft.getMinecraft().player.getPersistentID());
   }

   void c() {
      try {
         if (this.Girl.getSexPlayerUuid() != null) {
            return;
         }
      } catch (NullPointerException error) {
         throw rethrow(error);
      }

      this.Girl.setCurrentAction(GirlAnimationState.START_THROWING);
   }


   public void handleKeyboardInput() throws IOException {
        block4: {
            if (ClientProxy.keyBindings[0].getKeyCode() != Keyboard.getEventKey() || Keyboard.getEventKeyState()) break block4;
            Minecraft.getMinecraft().player.closeScreen();
            return;
        }
        super.handleKeyboardInput();
    }

   public void drawScreen(int i, int i2, float f) {
      super.drawScreen(i, i2, f);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glBlendFunc(770, 771);

      try {
         this.OpenProgress = Math.min(1.0F, this.OpenProgress + this.mc.getTickLength() / 5.0F);
      } catch (NullPointerException error) {
      }

      float f2 = (float)this.a(this.OpenProgress);
      float f3 = (1.0F - f2) * 100.0F;

      GuiGirlPreview gui;
      float f4;
      byte bv;
      label78: {
         try {
            gui = this;
            f4 = this.g;
            if (i < this.width / 2) {
               bv = 1;
               break label78;
            }
         } catch (NullPointerException error2) {
            throw rethrow(error2);
         }

         bv = -1;
      }

      label71: {
         try {
            gui.g = f4 + bv * this.mc.getTickLength();
            gui = this;
            f4 = this.LerpFactor;
            if (i > this.width / 2) {
               bv = 1;
               break label71;
            }
         } catch (NullPointerException error3) {
            throw rethrow(error3);
         }

         bv = -1;
      }

      label64: {
         try {
            gui.LerpFactor = f4 + bv * this.mc.getTickLength();
            gui = this;
            f4 = this.d;
            if (i2 < this.height / 2 - 1) {
               bv = 1;
               break label64;
            }
         } catch (NullPointerException error4) {
            throw rethrow(error4);
         }

         bv = -1;
      }

      label57: {
         try {
            gui.d = f4 + bv * this.mc.getTickLength();
            gui = this;
            f4 = this.m;
            if (i2 > this.height / 2) {
               bv = 1;
               break label57;
            }
         } catch (NullPointerException error5) {
            throw rethrow(error5);
         }

         bv = -1;
      }

      try {
         gui.m = f4 + bv * this.mc.getTickLength();
         this.g = MathUtils.clamp(this.g, 0.0F, 1.0F);
         this.LerpFactor = MathUtils.clamp(this.LerpFactor, 0.0F, 1.0F);
         this.d = MathUtils.clamp(this.d, 0.0F, 1.0F);
         this.m = MathUtils.clamp(this.m, 0.0F, 1.0F);
         GlStateManager.pushMatrix();
         GlStateManager.translate(this.width / 2.0F, this.height / 2.0F, 0.0F);
         GlStateManager.scale(f2, f2, f2);
         this.mc.renderEngine.bindTexture(Texture);
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F + this.g * 0.5F, 1.0F + this.g * 0.5F, 1.0F);
         this.drawTexturedModalRect(-62.0F + f3 - this.g * 15.0F, f3 - 32.0F, 0, 0, 64, 64);
         this.drawTexturedModalRect(-62.0F + f3 - this.g * 15.0F, f3 - 32.0F, 64, 128, 64, 64);
         GlStateManager.popMatrix();
         if (!this.IsGoblin) {
            GlStateManager.popMatrix();
            GL11.glDisable(3042);
            return;
         }
      } catch (NullPointerException error6) {
         throw rethrow(error6);
      }

      try {
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F - this.LerpFactor, 1.0F - this.LerpFactor, 1.0F);
         this.drawTexturedModalRect(-2.0F - f3 + this.LerpFactor * 32.0F, -f3 - 32.0F, 0, 0, 64, 64);
         this.drawTexturedModalRect(-2.0F - f3 + this.LerpFactor * 32.0F, -f3 - 32.0F, 0, 128, 64, 64);
         GlStateManager.popMatrix();
         if (this.LerpFactor > 0.0F) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(-1.0F + this.LerpFactor + 1.0F + this.d * 0.5F, -1.0F + this.LerpFactor + 1.0F + this.d * 0.5F, 1.0F);
            this.drawTexturedModalRect(-2.0F - f3 + this.d * 5.0F, -f3 - 64.0F - this.d * 5.0F / 2.0F, 0, 0, 64, 64);
            this.drawTexturedModalRect(-2.0F - f3 + this.d * 5.0F, -f3 - 64.0F - this.d * 5.0F / 2.0F, 128, 128, 64, 64);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(-1.0F + this.LerpFactor + 1.0F + this.m * 0.5F, -1.0F + this.LerpFactor + 1.0F + this.m * 0.5F, 1.0F);
            this.drawTexturedModalRect(-2.0F - f3 + this.m * 5.0F, -f3 + this.m * 5.0F / 2.0F, 0, 0, 64, 64);
            this.drawTexturedModalRect(-2.0F - f3 + this.m * 5.0F, -f3 + this.m * 5.0F / 2.0F, 192, 128, 64, 64);
            GlStateManager.popMatrix();
         }
      } catch (NullPointerException error7) {
         throw rethrow(error7);
      }

      GlStateManager.popMatrix();
      GL11.glDisable(3042);
   }

   double a(double d) {
      double d2 = 1.70158;
      double d3 = d2 + 1.0;
      return 1.0 + d3 * Math.pow(d - 1.0, 3.0) + d2 * Math.pow(d - 1.0, 2.0);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }
 static RuntimeException rethrow(RuntimeException error) {

      return error;

   }


   private static RuntimeException rethrow(Exception error) {
      return new RuntimeException(error);
   }
}
