package com.trolmastercard.sexmod;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiCloseResetHandler {
   @SubscribeEvent

   public void onGuiOpen(GuiOpenEvent gui) {
        block4: {
            try {
                try {
                    if (!(gui.getGui() instanceof GuiMainMenu) && !(gui.getGui() instanceof GuiMultiplayer)) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw GuiCloseResetHandler.rethrow(runtimeException);
                }
                PlayerGirlEntity.AllPlayerGirls.clear();
                PlayerGirlEntity.PlayerGirls.clear();
            }
            catch (RuntimeException runtimeException) {
                throw GuiCloseResetHandler.rethrow(runtimeException);
            }
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
