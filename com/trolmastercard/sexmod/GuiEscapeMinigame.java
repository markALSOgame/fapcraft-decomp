package com.trolmastercard.sexmod;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@SideOnly(Side.CLIENT)
public class GuiEscapeMinigame extends Gui {
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/escape_minigame_ui.png");
   static final int f = 52;
   static final float FallTicksMax = 20.0F;
   static final int KeySpawnIntervalTicks = 35;
   static final float n = 0.08F;
   static final float KeyDecayPerTick = 0.006F;
   static final int KeyMoveIntervalTicks = 2;
   static final float i = 0.33F;
   static boolean MinigameActive = false;
   static ControlKeys Keys = null;
   static float k = 0.0F;
   static float SpawnTimerTicks = 0.0F;
   static boolean b = true;
   static float FallTimerTicks = 0.0F;
   static boolean c = false;
   static Minecraft Mc = Minecraft.getMinecraft();
   static boolean EscapeRequested = false;


   public static void e() {
        block26: {
            block23: {
                boolean flag;
                block25: {
                    block24: {
                        block21: {
                            block22: {
                                try {
                                    if (!MinigameActive) {
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiEscapeMinigame.rethrow(runtimeException);
                                }
                                try {
                                    if (GuiEscapeMinigame.Mc.world == null) {
                                        MinigameActive = false;
                                        EscapeRequested = false;
                                        SpawnTimerTicks = 0.0f;
                                        k = 0.0f;
                                        FallTimerTicks = 0.0f;
                                        c = false;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiEscapeMinigame.rethrow(runtimeException);
                                }
                                try {
                                    try {
                                        if (!c) break block21;
                                        b = false;
                                        if (!((FallTimerTicks += 1.0f) >= 20.0f)) break block22;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GuiEscapeMinigame.rethrow(runtimeException);
                                    }
                                    MinigameActive = false;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiEscapeMinigame.rethrow(runtimeException);
                                }
                            }
                            return;
                        }
                        try {
                            try {
                                if ((SpawnTimerTicks += 1.0f) % (float)Math.max(1, 2) != 0.0f) break block23;
                                if (b) break block24;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GuiEscapeMinigame.rethrow(runtimeException);
                            }
                            flag = true;
                            break block25;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiEscapeMinigame.rethrow(runtimeException);
                        }
                    }
                    flag = false;
                }
                b = flag;
            }
            try {
                k = Math.max(0.0f, k - 0.006f);
                if (SpawnTimerTicks < 20.0f) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GuiEscapeMinigame.rethrow(runtimeException);
            }
            try {
                try {
                    if (SpawnTimerTicks % 35.0f != 0.0f && Keys != null) break block26;
                }
                catch (RuntimeException runtimeException) {
                    throw GuiEscapeMinigame.rethrow(runtimeException);
                }
                GuiEscapeMinigame.randomizeKey();
            }
            catch (RuntimeException runtimeException) {
                throw GuiEscapeMinigame.rethrow(runtimeException);
            }
        }
    }

   static void randomizeKey() {
      ControlKeys controlKeys = Keys;
      Random random = new Random();

      try {
         do {
            Keys = ControlKeys.values()[random.nextInt(ControlKeys.values().length)];
         } while (controlKeys == Keys);
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   static void sendEscapeRequest() {
      try {
         if (!MinigameActive) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (EscapeRequested) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      EscapeRequested = true;
      NetworkHandler.channel.sendToServer(new PacketGalathBackOffRape());
      d();
   }

   public static void a() {
      MinigameActive = true;
      EscapeRequested = false;
      SpawnTimerTicks = 0.0F;
      k = 0.0F;
      FallTimerTicks = 0.0F;
      c = false;
   }

   public static void d() {
      c = true;
      FallTimerTicks = 0.0F;
   }

   @SubscribeEvent

   public void a(RenderGameOverlayEvent renderGameOverlayEvent) {
        int i2;
        int i3;
        int i4;
        int i5;
        GuiEscapeMinigame gui;
        int i6;
        int i7;
        double d2;
        int i8;
        block29: {
            block28: {
                int i9;
                int i10;
                int i11;
                int i12;
                GuiEscapeMinigame gui2;
                block27: {
                    block26: {
                        int i13;
                        int i14;
                        int i15;
                        int i16;
                        GuiEscapeMinigame gui3;
                        block25: {
                            block24: {
                                int i17;
                                int i18;
                                int i19;
                                int i20;
                                GuiEscapeMinigame gui4;
                                block23: {
                                    block22: {
                                        try {
                                            if (!MinigameActive) {
                                                return;
                                            }
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GuiEscapeMinigame.rethrow(runtimeException);
                                        }
                                        try {
                                            if (renderGameOverlayEvent.getType() != RenderGameOverlayEvent.ElementType.TEXT) {
                                                return;
                                            }
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GuiEscapeMinigame.rethrow(runtimeException);
                                        }
                                        i8 = renderGameOverlayEvent.getResolution().getScaledWidth();
                                        int i21 = renderGameOverlayEvent.getResolution().getScaledHeight();
                                        float f2 = renderGameOverlayEvent.getPartialTicks();
                                        Mc.getTextureManager().bindTexture(Texture);
                                        d2 = c ? 1.0 - LerpMath.EaseInBack((FallTimerTicks + f2) / 20.0f) : Math.min(1.0, LerpMath.EaseOutBack((SpawnTimerTicks + f2) / 20.0f));
                                        i7 = i21 + 385;
                                        GlStateManager.pushMatrix();
                                        GlStateManager.scale((float)0.33f, (float)0.33f, (float)0.33f);
                                        GlStateManager.translate((float)485.0f, (float)0.0f, (float)0.0f);
                                        i6 = 4 * i21;
                                        try {
                                            try {
                                                this.drawTexturedModalRect(i8 / 2 - 87, (int)LerpMath.lerp((double)i6, (double)i7, d2), 0, 104, 174, 48);
                                                gui4 = this;
                                                i20 = (int)((float)i8 / 2.0f - 78.0f);
                                                i19 = (int)LerpMath.lerp((double)i6, (double)(i7 - 52), d2);
                                                i18 = 52;
                                                if (!b || Keys != ControlKeys.A) break block22;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw GuiEscapeMinigame.rethrow(runtimeException);
                                            }
                                            i17 = 52;
                                            break block23;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GuiEscapeMinigame.rethrow(runtimeException);
                                        }
                                    }
                                    i17 = 0;
                                }
                                try {
                                    try {
                                        gui4.drawTexturedModalRect(i20, i19, i18, i17, 52, 52);
                                        gui3 = this;
                                        i16 = (int)((float)i8 / 2.0f - 26.0f);
                                        i15 = (int)LerpMath.lerp((double)i6, (double)(i7 - 52), d2);
                                        i14 = 104;
                                        if (!b || Keys != ControlKeys.S) break block24;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GuiEscapeMinigame.rethrow(runtimeException);
                                    }
                                    i13 = 52;
                                    break block25;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiEscapeMinigame.rethrow(runtimeException);
                                }
                            }
                            i13 = 0;
                        }
                        try {
                            try {
                                gui3.drawTexturedModalRect(i16, i15, i14, i13, 52, 52);
                                gui2 = this;
                                i12 = (int)((float)i8 / 2.0f + 26.0f);
                                i11 = (int)LerpMath.lerp((double)i6, (double)(i7 - 52), d2);
                                i10 = 156;
                                if (!b || Keys != ControlKeys.D) break block26;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GuiEscapeMinigame.rethrow(runtimeException);
                            }
                            i9 = 52;
                            break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiEscapeMinigame.rethrow(runtimeException);
                        }
                    }
                    i9 = 0;
                }
                try {
                    try {
                        gui2.drawTexturedModalRect(i12, i11, i10, i9, 52, 52);
                        gui = this;
                        i5 = (int)((float)i8 / 2.0f - 26.0f);
                        i4 = (int)LerpMath.lerp((double)i6, (double)(i7 - 104), d2);
                        i3 = 0;
                        if (!b || Keys != ControlKeys.W) break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiEscapeMinigame.rethrow(runtimeException);
                    }
                    i2 = 52;
                    break block29;
                }
                catch (RuntimeException runtimeException) {
                    throw GuiEscapeMinigame.rethrow(runtimeException);
                }
            }
            i2 = 0;
        }
        gui.drawTexturedModalRect(i5, i4, i3, i2, 52, 52);
        this.drawTexturedModalRect(i8 / 2 - 87 + 8, (int)LerpMath.lerp((double)(i6 - 8), (double)(i7 + 8), d2), 8, 152, (int)(158.0f * k), 32);
        GlStateManager.popMatrix();
    }

   @SubscribeEvent
   public void a(ClientTickEvent clientTickEvent) {
      try {
         if (clientTickEvent.phase == Phase.END) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      e();
   }

   @SubscribeEvent

   public void a(KeyInputEvent keyInputEvent) {
        block28: {
            block30: {
                block29: {
                    GameSettings gameSettings;
                    block25: {
                        block27: {
                            block26: {
                                block22: {
                                    block24: {
                                        block23: {
                                            block19: {
                                                block21: {
                                                    block20: {
                                                        gameSettings = Minecraft.getMinecraft().gameSettings;
                                                        try {
                                                            try {
                                                                if (!GameSettings.isKeyDown((KeyBinding)gameSettings.keyBindLeft)) break block19;
                                                                if (Keys != ControlKeys.A) break block20;
                                                            }
                                                            catch (RuntimeException runtimeException) {
                                                                throw GuiEscapeMinigame.rethrow(runtimeException);
                                                            }
                                                            k += 0.08f;
                                                            break block21;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw GuiEscapeMinigame.rethrow(runtimeException);
                                                        }
                                                    }
                                                    k -= 0.04f;
                                                }
                                                return;
                                            }
                                            try {
                                                try {
                                                    if (!GameSettings.isKeyDown((KeyBinding)gameSettings.keyBindRight)) break block22;
                                                    if (Keys != ControlKeys.D) break block23;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw GuiEscapeMinigame.rethrow(runtimeException);
                                                }
                                                k += 0.08f;
                                                break block24;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw GuiEscapeMinigame.rethrow(runtimeException);
                                            }
                                        }
                                        k -= 0.04f;
                                    }
                                    return;
                                }
                                try {
                                    try {
                                        if (!GameSettings.isKeyDown((KeyBinding)gameSettings.keyBindForward)) break block25;
                                        if (Keys != ControlKeys.W) break block26;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GuiEscapeMinigame.rethrow(runtimeException);
                                    }
                                    k += 0.08f;
                                    break block27;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiEscapeMinigame.rethrow(runtimeException);
                                }
                            }
                            k -= 0.04f;
                        }
                        return;
                    }
                    try {
                        try {
                            if (!GameSettings.isKeyDown((KeyBinding)gameSettings.keyBindBack)) break block28;
                            if (Keys != ControlKeys.S) break block29;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiEscapeMinigame.rethrow(runtimeException);
                        }
                        k += 0.08f;
                        break block30;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiEscapeMinigame.rethrow(runtimeException);
                    }
                }
                k -= 0.04f;
            }
            return;
        }
        try {
            if (k >= 1.0f) {
                GuiEscapeMinigame.sendEscapeRequest();
            }
        }
        catch (RuntimeException runtimeException) {
            throw GuiEscapeMinigame.rethrow(runtimeException);
        }
    }

   private static RuntimeException rethrow(RuntimeException error5) {
      return error5;
   }
}
