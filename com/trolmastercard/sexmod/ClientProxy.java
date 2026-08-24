package com.trolmastercard.sexmod;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {
   public static boolean IS_PRELOADING = false;
   public static KeyBinding[] keyBindings;

   @Override
   public void postInit(FMLPostInitializationEvent fMLPostInitializationEvent) throws IOException {
   }

   @Override
   public void preInitRegistries(FMLPreInitializationEvent fMLPreInitializationEvent) {
      super.preInitRegistries(fMLPreInitializationEvent);
      RendererRegistration.registerEntityRenderers();
   }

   @Override
   public void initRegistries(FMLInitializationEvent fMLInitializationEvent) throws IOException {
      keyBindings = new KeyBinding[2];
      keyBindings[0] = new KeyBinding("Interact with your goblin", 34, "Sex mod");
      keyBindings[1] = new KeyBinding("open character customisation menu", 76, "Sex mod");

      for (KeyBinding keyBinding : keyBindings) {
         ClientRegistry.registerKeyBinding(keyBinding);
      }

      Main.setConfigs();
      ModSounds.registerAllSounds();
      NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler(true));
      EventManager.registerEventHandlers(true);
      NetworkHandler.init();
      Minecraft mc = Minecraft.getMinecraft();
      RenderManager renderManager = mc.getRenderManager();
      PreviewWorld previewWorld = new PreviewWorld();
      IS_PRELOADING = true;

      try {
         for (GirlRegistry girlType : GirlRegistry.values()) {
            renderManager.renderEntity((Entity)girlType.npcClass.getDeclaredConstructor(World.class).newInstance(previewWorld), 0.0, 0.0, 0.0, 0.0F, 0.0F, false);
         }
      } catch (Exception error) {
         System.out.println("error while preloading:");
         error.printStackTrace();
      }

      IS_PRELOADING = false;
      ClientChatHandler.Entry = new ClientChatHandler();
      ClientCommandHandler.instance.registerCommand(CommandWhitelistServer.Instance);
      ClientCommandHandler.instance.registerCommand(CommandSetModelCode.Instance);
      ClientCommandHandler.instance.registerCommand(CommandFuta.Instance);
      Minecraft.getMinecraft()
         .effectRenderer
         .registerParticle(625115, (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9) -> new DragonBreathParticles(arg2, arg3, arg4, arg5));
   }
}
