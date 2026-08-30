package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiNameTribe extends GuiScreen {
   static final int b = 15;
   static final int a = 100;
   static final int c = 20;
   UUID GirlUuid;
   GuiTextField NameField;

   public GuiNameTribe(UUID uuid) {
      this.GirlUuid = uuid;
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void initGui() {
      super.initGui();
      this.NameField = new GuiTextField(0, this.mc.fontRenderer, this.width / 2 - 50, this.height / 2 - 10, 100, 20);
      this.NameField.setFocused(true);
      this.buttonList.add(new GuiButton(0, this.width / 2 - 25, this.height / 2 + 20, 50, 20, "set"));
   }

   public void updateScreen() {
      this.NameField.updateCursorCounter();
      super.updateScreen();
   }

   public void drawScreen(int i, int i2, float f) {
      this.drawHoveringText("Name Tribe", this.width / 2 - 39, this.height / 2 - 10);
      this.NameField.drawTextBox();
      super.drawScreen(i, i2, f);
   }

   protected void keyTyped(char ch, int i) throws IOException {
      this.NameField.textboxKeyTyped(ch, i);
      String string = this.NameField.getText();

         if (string.length() > 15) {
            this.NameField.setText(string.substring(0, 15));
         }

      super.keyTyped(ch, i);
   }

   protected void actionPerformed(GuiButton gui) throws IOException {
      super.actionPerformed(gui);
      String string = this.NameField.getText().trim();

         if (string.length() == 0) {
            return;
         }

      NetworkHandler.channel.sendToServer(new PacketClaimTribe(this.GirlUuid, Minecraft.getMinecraft().player.getPersistentID(), string));
      Minecraft.getMinecraft().player.closeScreen();
   }

   private static IOException rethrow(IOException error) {
      return error;
   }
}
