package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiChest extends GuiContainer {
   private static final ResourceLocation Texture = new ResourceLocation("textures/gui/container/generic_54.png");
   private final IInventory PlayerInventory;
   private final IInventory GirlInventory;
   private final int Rows;
   UUID GirlUuid;
   GirlEntity Girl;
   UUID PlayerUuid;

   public GuiChest(EntityPlayer player, GirlEntity girl, UUID uuid) {
      super(new ContainerChest(player.inventory, (IInventory)girl, player, uuid));
      this.GirlUuid = uuid;
      this.Girl = girl;
      this.PlayerUuid = player.getPersistentID();
      this.PlayerInventory = player.inventory;
      this.GirlInventory = (IInventory)girl;
      this.allowUserInput = false;
      this.Rows = ((IInventory)girl).getSizeInventory() / 9;
      this.ySize = 114 + this.Rows * 18;
   }

   public void drawScreen(int i, int i2, float f) {
      this.drawDefaultBackground();
      super.drawScreen(i, i2, f);
      this.renderHoveredToolTip(i, i2);
   }

   protected void drawGuiContainerForegroundLayer(int i, int i2) {
      this.fontRenderer.drawString(this.Girl.getDisplayName(), 8, 6, 4210752);
      this.fontRenderer.drawString(this.PlayerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int i2) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(Texture);
      int i3 = (this.width - this.xSize) / 2;
      int i4 = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(i3, i4, 0, 0, this.xSize, this.Rows * 18 + 17);
      this.drawTexturedModalRect(i3, i4 + this.Rows * 18 + 17, 0, 126, this.xSize, 96);
   }

   public void onGuiClosed() {
      super.onGuiClosed();

      for (ContainerGirlEquipment container : ContainerGirlEquipment.OpenContainers) {
         if (container.GirlUuid.equals(this.GirlUuid)) {
            ItemStack[] stackArray = new ItemStack[63];
            Minecraft.getMinecraft().player.inventory.mainInventory.toArray(stackArray);
            int i = 0;

            try {
               while (i < 27) {
                  stackArray[i + 36] = container.getSlot(i).getStack();
                  i++;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            NetworkHandler.channel.sendToServer(new PacketUploadInventoryToServer(this.Girl.getGirlUuid(), this.PlayerUuid, stackArray));
         }
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
