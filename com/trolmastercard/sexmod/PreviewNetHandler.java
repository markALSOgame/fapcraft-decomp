package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.EnumPacketDirection;

public class PreviewNetHandler extends NetHandlerPlayClient {
   public PreviewNetHandler(Minecraft mc) {
      super(mc, mc.currentScreen, new NullNetworkManager(EnumPacketDirection.CLIENTBOUND), mc.getSession().getProfile());
   }
}
