package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GuiGirlDialogue extends GuiScreen {
   ChestGirlEntity Dialogue;
   EntityPlayer Player;
   boolean HasUnlocked;
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
   double Progress = 0.0;

   public GuiGirlDialogue(ChestGirlEntity chestGirl, EntityPlayer player) {
      this.Dialogue = chestGirl;
      this.Player = player;
      this.HasUnlocked = !"".equals(chestGirl.getDataManager().get(GirlEntity.MasterUuidKey));
   }

   public boolean doesGuiPauseGame() {
      return false;
   }


   public void drawScreen(int i, int i2, float f) {
        String string;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        GuiButton guiButton;
        GuiButton guiButton2;
        List list;
        super.drawScreen(i, i2, f);
        this.buttonList.clear();
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        int i8 = scaledResolution.getScaledWidth();
        try {
            GuiButton guiButton3;
            this.Progress = Math.min(1.0, this.Progress + (double)(this.mc.getTickLength() / 5.0f));
            list = this.buttonList;
            i7 = 0;
            i6 = i8 / 2 - 119 + (int)(100.0 - 100.0 * this.Progress);
            i5 = 30;
            i4 = (int)(this.Progress * 100.0);
            i3 = 20;
            string = this.HasUnlocked ? I18n.format((String)"action.names.stopfollowme", (Object[])new Object[0]) : I18n.format((String)"action.names.followme", (Object[])new Object[0]);
        }
        catch (RuntimeException runtimeException) {
            throw GuiGirlDialogue.rethrow(runtimeException);
        }
        guiButton = new GuiButton(i7, i6, i5, i4, i3, string);
        list.add(guiButton);
        this.buttonList.add(new GuiButton(1, i8 / 2 + 19, 30, (int)(this.Progress * 100.0), 20, I18n.format((String)"action.names.gohome", (Object[])new Object[0])));
        this.mc.renderEngine.bindTexture(Texture);
        this.drawTexturedModalRect(i8 / 2 - 7, 61 - (int)(15.0 - this.Progress * 15.0), 32, 0, 15, 15);
        this.buttonList.add(new GuiButton(2, i8 / 2 - 10, 59 - (int)(15.0 - this.Progress * 15.0), 20, 20, ""));
        this.drawTexturedModalRect(i8 / 2 - 20, 20, (Boolean)this.Dialogue.getDataManager().get(ChestGirlEntity.K) != false ? 0 : 40, 130, 40, 40);
    }


   protected void mouseClicked(int i, int i2, int i3) throws IOException {
        block10: {
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            int i4 = scaledResolution.getScaledWidth();
            if (!((Boolean)this.Dialogue.getDataManager().get(ChestGirlEntity.K)).booleanValue() || i < i4 / 2 - 20) break block10;
            if (i > i4 / 2 + 20) break block10;
            if (i2 < 20) break block10;
            if (i2 > 60) break block10;
            NetworkHandler.channel.sendToServer((IMessage)new PacketBeeOpenChest(this.Dialogue.getGirlUuid(), this.Player.getPersistentID()));
            this.onGuiClosed();
        }
        super.mouseClicked(i, i2, i3);
    }


   protected void actionPerformed(GuiButton gui) throws IOException {
        block12: {
            boolean flag;
            block14: {
                block13: {
                        try {
                            super.actionPerformed(gui);
                            if (gui.id != 0) break block12;
                            if (!this.HasUnlocked) break block13;
                        }
                        catch (IOException iOException) {
                            throw GuiGirlDialogue.rethrow(iOException);
                        }
                        NetworkHandler.channel.sendToServer((IMessage)new PacketUpdateGirl(this.Dialogue.getGirlUuid(), "master", ""));
                        this.Player.sendMessage((ITextComponent)new TextComponentString(I18n.format((String)"bee.dialogue.sad", (Object[])new Object[0])));
                        break block14;
                }
                NetworkHandler.channel.sendToServer((IMessage)new PacketUpdateGirl(this.Dialogue.getGirlUuid(), "master", this.Player.getPersistentID().toString()));
                this.Player.sendMessage((ITextComponent)new TextComponentString(I18n.format((String)"bee.dialogue.exited", (Object[])new Object[0])));
            }
                this.HasUnlocked = !this.HasUnlocked;
            this.Player.closeScreen();
        }
            if (gui.id == 1) {
                NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.Dialogue.getGirlUuid()));
                this.Player.closeScreen();
            }
            if (gui.id == 2) {
                NetworkHandler.channel.sendToServer((IMessage)new PacketSetNewHome(this.Dialogue.getGirlUuid(), new Vec3d(this.Dialogue.posX, this.Dialogue.posY, this.Dialogue.posZ)));
                this.Player.closeScreen();
                this.Player.sendMessage((ITextComponent)new TextComponentString(I18n.format((String)"bee.dialogue.home", (Object[])new Object[0])));
            }
    }
 static RuntimeException rethrow(RuntimeException error) {

      return error;

   }


   private static RuntimeException rethrow(Exception error) {
      return new RuntimeException(error);
   }
}
