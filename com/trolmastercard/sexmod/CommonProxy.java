package com.trolmastercard.sexmod;

import java.io.IOException;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
   public void preInitRegistries(FMLPreInitializationEvent fMLPreInitializationEvent) {
      GameRegistry.registerWorldGenerator(KoboldVillageGenerator.getInstance(), 0);
      EntityRegistration.registerEntitiesAndSpawning();
      ItemRegistration.registerItems();
   }

   public void initRegistries(FMLInitializationEvent fMLInitializationEvent) throws IOException {
      Main.setConfigs();
      ModSounds.registerAllSounds();
      NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
      EventManager.registerEventHandlers(false);
      NetworkHandler.init();
   }

   public void postInit(FMLPostInitializationEvent fMLPostInitializationEvent) throws IOException {
      this.setUpCustomModelsOnServer();
   }

   void setUpCustomModelsOnServer() {
      try {
         if (!FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer()) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      FilePersistence.loadModels(false);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
