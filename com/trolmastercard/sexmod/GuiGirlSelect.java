package com.trolmastercard.sexmod;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiGirlSelect extends GuiScreen {
   List<EntityLivingBase> Girls = new ArrayList<>();
   int Tick = 0;
   static float ScrollOffset = 0.0F;

   public GuiGirlSelect(HashMap<GirlRegistry, String> map) {
      this.mc = Minecraft.getMinecraft();

      for (GirlRegistry girlType : GirlRegistry.values()) {
         try {
            if (girlType.isNpcOnly) {
               continue;
            }
         } catch (Exception error) {
            throw rethrow(error);
         }

         try {
            Constructor constructor = girlType.npcClass.getConstructor(World.class);
            GirlEntity girl = (GirlEntity)constructor.newInstance(this.mc.world);
            girl.setTracked(true);
            this.Girls.add(girl);
            String string = (String)map.get(girlType);
            if (string != null) {
               girl.a(GirlEntity.stringToColors(string));
            }
         } catch (Exception error2) {
            error2.printStackTrace();
         }
      }

      this.Girls.add(this.mc.player);
   }

   public void drawScreen(int i, int i2, float f) {
      super.drawScreen(i, i2, f);
      this.buttonList.clear();
      a(this.width / 2, this.height / 2 + 20, 30, this.Girls.get(this.Tick));
      this.buttonList.add(new GuiButton(1, this.width / 2 + 30, this.height / 2 - 10, 20, 20, ">"));
      this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height / 2 - 10, 20, 20, "<"));
      this.buttonList.add(new GuiButton(0, this.width / 2 - 30, this.height / 2 + 30, 60, 20, "pick"));
   }


   protected void actionPerformed(GuiButton gui) {
        block13: {
            block12: {
                try {
                    try {
                        if (!">".equals(gui.displayString) || ++this.Tick < this.Girls.size()) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiGirlSelect.rethrow(runtimeException);
                    }
                    this.Tick = 0;
                }
                catch (RuntimeException runtimeException) {
                    throw GuiGirlSelect.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (!"<".equals(gui.displayString) || --this.Tick >= 0) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw GuiGirlSelect.rethrow(runtimeException);
                }
                this.Tick = this.Girls.size() - 1;
            }
            catch (RuntimeException runtimeException) {
                throw GuiGirlSelect.rethrow(runtimeException);
            }
        }
        if (gui.id == 0) {
            NetworkHandler.channel.sendToServer((IMessage)new PacketUpdatePlayerModel(GirlRegistry.getByEntity((Entity)this.Girls.get(this.Tick))));
            EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                entityPlayerSP.closeScreen();
                entityPlayerSP.eyeHeight = entityPlayerSP.getDefaultEyeHeight();
                if (!entityPlayerSP.capabilities.allowFlying) {
                    entityPlayerSP.capabilities.allowFlying = entityPlayerSP.capabilities.isCreativeMode;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GuiGirlSelect.rethrow(runtimeException);
            }
        }
    }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public static void a(int i, int i2, int i3, EntityLivingBase livingBase) {
      float f = livingBase.renderYawOffset;
      float f2 = livingBase.rotationYaw;
      float f3 = livingBase.rotationPitch;
      float f4 = livingBase.prevRotationYawHead;
      float f5 = livingBase.rotationYawHead;

      try {
         if (!(livingBase instanceof EntityPlayer)) {
            livingBase.posX = 0.0;
            livingBase.posY = 0.0;
            livingBase.posZ = 0.0;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      livingBase.renderYawOffset = 0.0F;
      livingBase.rotationYaw = 0.0F;
      livingBase.rotationPitch = 0.0F;
      livingBase.prevRotationYawHead = 0.0F;
      livingBase.rotationYawHead = 0.0F;
      float f6 = Minecraft.getDebugFPS();
      if (f6 == 0.0F) {
         f6 = 0.1F;
      }

      ScrollOffset += 60.0F / f6;
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate(i, i2, 50.0F);
      GlStateManager.scale(-i3, i3, i3);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(ScrollOffset, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      renderManager.setPlayerViewY(180.0F);
      renderManager.setRenderShadow(false);
      renderManager.renderEntity(livingBase, 0.0, 0.0, 0.0, 0.0F, 1.2345679F, false);
      renderManager.setRenderShadow(true);
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      livingBase.renderYawOffset = f;
      livingBase.rotationYaw = f2;
      livingBase.rotationPitch = f3;
      livingBase.prevRotationYawHead = f4;
      livingBase.rotationYawHead = f5;
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
