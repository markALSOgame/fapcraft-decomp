package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiGirlEquipment extends GuiContainer {
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/girlinventory.png");
   UUID GirlUuid;
   GirlEntity Girl;
   UUID PlayerUuid;

   public GuiGirlEquipment(GirlEntity girl, InventoryPlayer inventory, UUID uuid) {
      super(new ContainerGirlEquipment(girl, inventory, uuid));
      this.GirlUuid = uuid;
      this.Girl = girl;
      this.PlayerUuid = inventory.player.getPersistentID();
   }

   public void drawScreen(int i, int i2, float f) {
      this.drawDefaultBackground();
      super.drawScreen(i, i2, f);
      this.renderHoveredToolTip(i, i2);
   }

   public void onGuiClosed() {
      super.onGuiClosed();

      for (ContainerGirlEquipment container : ContainerGirlEquipment.OpenContainers) {
         if (container.GirlUuid.equals(this.GirlUuid)) {
            ItemStack[] stackArray = new ItemStack[42];
            Minecraft.getMinecraft().player.inventory.mainInventory.toArray(stackArray);
            stackArray[36] = container.getSlot(0).getStack();
            stackArray[37] = container.getSlot(1).getStack();
            stackArray[38] = container.getSlot(2).getStack();
            stackArray[39] = container.getSlot(3).getStack();
            stackArray[40] = container.getSlot(4).getStack();
            stackArray[41] = container.getSlot(5).getStack();
            NetworkHandler.channel.sendToServer(new PacketUploadInventoryToServer(this.Girl.getGirlUuid(), this.PlayerUuid, stackArray));
         }
      }
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int i2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.renderEngine.bindTexture(Texture);
      this.drawTexturedModalRect(this.width / 2 - 88, this.height / 2 - 7 - 24, 33, 16, 176, 114);
   }
}
