package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GuiGirlCommandMenu extends GuiScreen {
   final GirlEntity Girl;
   final EntityPlayer Player;
   final String[] CommandLabels;
   @Nullable
   final ItemStack[] Items;
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
   EntityDataManager DataManager;
   final boolean IsPremium;
   float m = 0.0F;
   float n = 0.0F;
   String[] Labels = new String[]{"action.names.followme", "action.names.stopfollowme", "action.names.gohome", "action.names.setnewhome", "action.names.equipment"};
   int[] d = new int[]{0, 0, 0, 0, 0};
   int[] j = new int[]{64, 80, 47, 32, 96};
   int[] b = new int[]{4, 4, 5, 5, 4};
   int[] e = new int[]{50, 90, 50, 80, 60};

   public GuiGirlCommandMenu(GirlEntity girl, EntityPlayer player) {
      this.Girl = girl;
      this.Player = player;
      this.CommandLabels = new String[0];
      this.Items = new ItemStack[0];
      this.IsPremium = true;
      this.DataManager = girl.getDataManager();
   }

   public GuiGirlCommandMenu(GirlEntity girl, EntityPlayer player, String[] stringArray, @Nullable ItemStack[] stackArray, boolean flag) {
      this.Girl = girl;
      this.Player = player;
      this.CommandLabels = stringArray;
      this.Items = stackArray;
      this.IsPremium = flag;
      this.DataManager = girl.getDataManager();
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void onGuiClosed() {
      super.onGuiClosed();
      this.Girl.ac();
   }


   protected void actionPerformed(GuiButton gui) {
        block16: {
            try {
                block15: {
                    try {
                        try {
                            try {
                                if (gui.id < 5 || this.Items == null) break block15;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GuiGirlCommandMenu.rethrow(runtimeException);
                            }
                            if (this.Items[gui.id - 5] == null) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiGirlCommandMenu.rethrow(runtimeException);
                        }
                        if (!this.Player.capabilities.isCreativeMode) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiGirlCommandMenu.rethrow(runtimeException);
                    }
                }
                this.a(gui);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GuiGirlCommandMenu.rethrow(runtimeException);
            }
        }
        for (ItemStack itemStack : this.Player.inventory.mainInventory) {
            try {
                try {
                    try {
                        if (!itemStack.getItem().equals(this.Items[gui.id - 5].getItem()) || itemStack.getCount() < this.Items[gui.id - 5].getCount()) continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiGirlCommandMenu.rethrow(runtimeException);
                    }
                    if (itemStack.getMetadata() != this.Items[gui.id - 5].getMetadata()) continue;
                }
                catch (RuntimeException runtimeException) {
                    throw GuiGirlCommandMenu.rethrow(runtimeException);
                }
                NetworkHandler.channel.sendToServer((IMessage)new PacketRemoveItems(this.Player.getPersistentID(), this.Items[gui.id - 5]));
                this.a(gui);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GuiGirlCommandMenu.rethrow(runtimeException);
            }
        }
        this.Player.sendMessage((ITextComponent)new TextComponentString("<" + this.Girl.getName() + "> you cannot afford that..."));
        this.Girl.playSoundEvent(ModSounds.GIRLS_JENNY_SADOH[1]);
    }

   void a(GuiButton gui) {
      String string;
      if (gui.id < 5) {
         string = this.Labels[gui.id];
      } else {
         string = this.CommandLabels[gui.id - 5];
      }

      this.Girl.a(string, this.Player.getPersistentID());
      Minecraft.getMinecraft().player.closeScreen();
   }


   public void drawScreen(int i, int i2, float f) {
        super.drawScreen(i, i2, f);
        this.buttonList.clear();
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        int i3 = scaledResolution.getScaledWidth();
        int i4 = scaledResolution.getScaledHeight();
        try {
            this.m = Math.min(1.0f, this.m + this.mc.getTickLength() / 5.0f);
            if (this.m == 1.0f) {
                this.n = Math.min(1.0f, this.n + this.mc.getTickLength() / 5.0f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GuiGirlCommandMenu.rethrow(runtimeException);
        }
        int i5 = (int)LerpMath.lerp(115.0f, 161.0f, this.n);
        int i6 = (int)LerpMath.lerp(91.0f, 137.0f, this.n);
        int i7 = (int)LerpMath.lerp(-30.0f, 120.0f, this.m);
        int i8 = 70;
        int i9 = 52;
        int i10 = 68;
        int i11 = 5;
        while (true) {
            block17: {
                try {
                    try {
                        try {
                            try {
                                try {
                                    if (i11 >= this.CommandLabels.length + 5) break;
                                    if (!(this.n > 0.0f)) break block17;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiGirlCommandMenu.rethrow(runtimeException);
                                }
                                if (this.Items == null) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GuiGirlCommandMenu.rethrow(runtimeException);
                            }
                            if (this.Items[i11 - 5] == null) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiGirlCommandMenu.rethrow(runtimeException);
                        }
                        if (this.Items[i11 - 5].getCount() == 0) break block17;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiGirlCommandMenu.rethrow(runtimeException);
                    }
                    this.zLevel = -300.0f;
                    this.itemRender.zLevel = -300.0f;
                    this.a(Arrays.asList(this.Items[i11 - 5].getCount() + "x    "), i3 - i5, i4 - i9, this.fontRenderer);
                    this.itemRender.renderItemIntoGUI(this.Items[i11 - 5], i3 - i6, i4 - i10);
                    this.zLevel = 0.0f;
                    this.itemRender.zLevel = 0.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw GuiGirlCommandMenu.rethrow(runtimeException);
                }
            }
            this.buttonList.add(new GuiButton(i11, i3 - i7, i4 - i8, 100, 20, I18n.format((String)this.CommandLabels[i11 - 5], (Object[])new Object[0])));
            i8 += 30;
            i9 += 30;
            i10 += 30;
            ++i11;
        }
        try {
            if (this.IsPremium) {
                this.a(i, i2);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GuiGirlCommandMenu.rethrow(runtimeException);
        }
    }


   void a(int i, int i3) {
        boolean flag;
        int i4 = (int)LerpMath.lerp(-30.0f, 120.0f, this.m);
        try {
            this.itemRender.renderItemIntoGUI((ItemStack)this.DataManager.get(InventoryGirlEntity.L), i4 - 105, 68);
            this.itemRender.renderItemIntoGUI((ItemStack)this.DataManager.get(InventoryGirlEntity.R), i4 - 105, 87);
            this.itemRender.renderItemIntoGUI((ItemStack)this.DataManager.get(InventoryGirlEntity.HelmetKey), i4 - 105, 109);
            this.itemRender.renderItemIntoGUI((ItemStack)this.DataManager.get(InventoryGirlEntity.ChestKey), i4 - 105, 127);
            this.itemRender.renderItemIntoGUI((ItemStack)this.DataManager.get(InventoryGirlEntity.PantsKey), i4 - 105, 146);
            this.itemRender.renderItemIntoGUI((ItemStack)this.DataManager.get(InventoryGirlEntity.BootsKey), i4 - 105, 166);
            if (this.n == 0.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GuiGirlCommandMenu.rethrow(runtimeException);
        }
        try {
            flag = !((String)this.DataManager.get(GirlEntity.MasterUuidKey)).equals("");
        }
        catch (RuntimeException runtimeException) {
            throw GuiGirlCommandMenu.rethrow(runtimeException);
        }
        boolean flag2 = flag;
        int i5 = 35;
        int i6 = 70;
        int i7 = 0;
        while (true) {
            String string;
            int i8;
            int i9;
            int i10;
            int i11;
            int i12;
            GuiButton guiButton;
            GuiButton guiButton2;
            List list;
            block28: {
                block27: {
                    block26: {
                        block25: {
                            try {
                                try {
                                    if (i7 >= 5) break;
                                    if (i7 != 0) break block25;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiGirlCommandMenu.rethrow(runtimeException);
                                }
                                if (!flag2) break block25;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GuiGirlCommandMenu.rethrow(runtimeException);
                            }
                            i7 = 1;
                            break block26;
                        }
                        try {
                            if (i7 != 1 || flag2) break block26;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiGirlCommandMenu.rethrow(runtimeException);
                        }
                        i7 = 2;
                    }
                    try {
                        try {
                            try {
                                try {
                                    if (i < i5 || i > i5 + 23 + this.d[i7]) break block27;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GuiGirlCommandMenu.rethrow(runtimeException);
                                }
                                if (i3 < i6) break block27;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GuiGirlCommandMenu.rethrow(runtimeException);
                            }
                            if (i3 > i6 + 20) break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiGirlCommandMenu.rethrow(runtimeException);
                        }
                        this.d[i7] = Math.min(this.e[i7], this.d[i7] + 7);
                        break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiGirlCommandMenu.rethrow(runtimeException);
                    }
                }
                this.d[i7] = Math.max(0, this.d[i7] - 7);
            }
            StringBuilder stringBuilder = new StringBuilder(I18n.format((String)this.Labels[i7], (Object[])new Object[0]));
            try {
                for (int i13 = 0; i13 < this.b[i7]; ++i13) {
                    stringBuilder.append(" ");
                }
            }
            catch (RuntimeException runtimeException) {
                throw GuiGirlCommandMenu.rethrow(runtimeException);
            }
            try {
                GuiButton guiButton3;
                this.mc.renderEngine.bindTexture(Texture);
                this.drawTexturedModalRect(this.d[i7] + i5 - 18 + (int)LerpMath.lerp(0.0f, 23.0f, this.n), i6 + 2, this.j[i7], 0, 16, 16);
                list = this.buttonList;
                i12 = i7;
                i11 = i5 + 1;
                i10 = i6;
                i9 = (int)(LerpMath.lerp(0.0f, 23.0f, this.n) + (float)this.d[i7]);
                i8 = 20;
                string = this.d[i7] <= 14 ? "" : stringBuilder.toString();
            }
            catch (RuntimeException runtimeException) {
                throw GuiGirlCommandMenu.rethrow(runtimeException);
            }
            guiButton = new GuiButton(i12, i11, i10, i9, i8, string);
            list.add(guiButton);
            i6 += 30;
            ++i7;
        }
        this.mc.renderEngine.bindTexture(Texture);
        this.drawTexturedModalRect(i4 - 113, 60, 0, 0, 32, 130);
    }

   void a(List<String> list, int i, int i2, FontRenderer renderer) {
      GlStateManager.disableRescaleNormal();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableLighting();
      int i3 = 0;

      for (String string : list) {
         int i4 = this.fontRenderer.getStringWidth(string);
         if (i4 > i3) {
            i3 = i4;
         }
      }

      int i5 = i + 12;
      int i6 = i2 - 12;
      int i7 = 8;
      if (list.size() > 1) {
         i7 += 2 + (list.size() - 1) * 10;
      }

      if (i5 + i3 > this.width) {
         i5 -= 28 + i3;
      }

      if (i6 + i7 + 6 > this.height) {
         i6 = this.height - i7 - 6;
      }

      this.drawGradientRect(i5 - 3, i6 - 4, i5 + i3 + 3, i6 - 3, -267386864, -267386864);
      this.drawGradientRect(i5 - 3, i6 + i7 + 3, i5 + i3 + 3, i6 + i7 + 4, -267386864, -267386864);
      this.drawGradientRect(i5 - 3, i6 - 3, i5 + i3 + 3, i6 + i7 + 3, -267386864, -267386864);
      this.drawGradientRect(i5 - 4, i6 - 3, i5 - 3, i6 + i7 + 3, -267386864, -267386864);
      this.drawGradientRect(i5 + i3 + 3, i6 - 3, i5 + i3 + 4, i6 + i7 + 3, -267386864, -267386864);
      this.drawGradientRect(i5 - 3, i6 - 3 + 1, i5 - 3 + 1, i6 + i7 + 3 - 1, 1347420415, 1344798847);
      this.drawGradientRect(i5 + i3 + 2, i6 - 3 + 1, i5 + i3 + 3, i6 + i7 + 3 - 1, 1347420415, 1344798847);
      this.drawGradientRect(i5 - 3, i6 - 3, i5 + i3 + 3, i6 - 3 + 1, 1347420415, 1347420415);
      this.drawGradientRect(i5 - 3, i6 + i7 + 2, i5 + i3 + 3, i6 + i7 + 3, 1344798847, 1344798847);

      for (int i8 = 0; i8 < list.size(); i8++) {
         String string2 = (String)list.get(i8);

         try {
            this.fontRenderer.drawStringWithShadow(string2, i5, i6, -1);
            if (i8 == 0) {
               i6 += 2;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         i6 += 10;
      }

      GlStateManager.enableLighting();
      RenderHelper.enableStandardItemLighting();
      GlStateManager.enableRescaleNormal();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
