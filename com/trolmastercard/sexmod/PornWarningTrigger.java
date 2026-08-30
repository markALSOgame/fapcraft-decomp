package com.trolmastercard.sexmod;

import javax.swing.JFrame;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class PornWarningTrigger extends JFrame {
   public boolean HasShown = false;

   @SubscribeEvent
   public void onClientTick(ClientTickEvent clientTickEvent) {
      try {
         if (this.HasShown) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.HasShown = true;
      PornWarningHelper.showWarningWindow();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
