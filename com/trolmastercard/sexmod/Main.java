package com.trolmastercard.sexmod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(modid = "sexmod", name = "Fapcraft", version = "1.1.0", dependencies = "after:geckolib")
public class Main {
   @Instance
   public static Main instance;
   @SidedProxy(clientSide = "ClientProxy", serverSide = "CommonProxy")
   public static CommonProxy proxy;
   public static final Logger LOGGER = LogManager.getLogger("sexmod");

   @EventHandler
   public void preInit(FMLPreInitializationEvent fMLPreInitializationEvent) {
      GeckoLib.initialize();
      proxy.preInitRegistries(fMLPreInitializationEvent);
   }

   @EventHandler
   public void init(FMLInitializationEvent fMLInitializationEvent) throws IOException {
      proxy.initRegistries(fMLInitializationEvent);
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent fMLPostInitializationEvent) throws IOException {
      proxy.postInit(fMLPostInitializationEvent);
   }

   @EventHandler
   public static void onWorldClosed(FMLServerStoppedEvent fMLServerStoppedEvent) {
      try {
         GirlEntity.getAllGirls().clear();
         GirlHomeBuilder.clearAll();
         KoboldNpc.MemberData.clear();
         GalathOwnershipData.clearOwnershipData();
         KoboldVillageGenerator.getInstance().a();
         GirlTracker.clearAll();
         FilePersistence.ClientActive = false;
         CustomModelWorldData.clearAll();
         if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            clientReset();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @EventHandler
   public static void onWorldStart(FMLServerStartingEvent fMLServerStartingEvent) {
      fMLServerStartingEvent.registerServerCommand(CommandLocateGoblinLair.Instance);
      fMLServerStartingEvent.registerServerCommand(CommandReloadCustomModels.Instance);
   }

   @SideOnly(Side.CLIENT)
   static void clientReset() {
      GuiMark.clearMarks();
      ScaledGirlGeoRenderer.clearColorCache();
   }

   @SideOnly(Side.CLIENT)
   @EventHandler
   public void registerReplacedRenderers(FMLInitializationEvent fMLInitializationEvent2) {
      GeckoLib.initialize();
   }

   public static void setConfigs() throws IOException {
      File file = new File("config");
      file.mkdir();
      File file2 = new File("config/sexmod.json");
      if (!file2.exists()) {
         file2.createNewFile();
         FileWriter fileWriter = new FileWriter(file2);
         fileWriter.write("{\"shouldGenBuildings\":true,\"shouldLoadOtherSkins\":false,\"allowFlying\":true}");
         fileWriter.close();
      }

      StringBuilder sb = new StringBuilder();
      BufferedReader reader = new BufferedReader(new FileReader(file2));
      Throwable error2 = null;

      try {
         while (true) {
            String string;
            String string2 = string = reader.readLine();

               if (string2 == null) {
                  break;
               }

               sb.append(string);
         }
      } catch (Throwable error4) {
         error2 = error4;
         throw error4;
      } finally {
         label130: {
            label129: {
                  if (reader == null) {
                     break label130;
                  }

                  if (error2 == null) {
                     break label129;
                  }

               try {
                  reader.close();
               } catch (Throwable error6) {
                  error2.addSuppressed(error6);
               }
               break label130;
            }

            reader.close();
         }
      }

      String string3 = sb.toString();
      if (!string3.contains("shouldGenBuildings")) {
         file2.delete();
         file2 = new File("config/sexmod.json");
         file2.createNewFile();
         FileWriter fileWriter2 = new FileWriter(file2);
         fileWriter2.write("{\"shouldGenBuildings\":true,\"shouldLoadOtherSkins\":false,\"allowFlying\":true}");
         fileWriter2.close();
         KoboldVillageGenerator.ShouldGenBuildings = true;
         GirlGeoModel.RenderBraStrings = false;
         PlayerGirlEntity.FeatureEnabled = true;
      } else {
         int i = string3.indexOf("shouldGenBuildings");
         int i2 = string3.indexOf("shouldLoadOtherSkins");
         int i3 = string3.indexOf("allowFlying");

         boolean flag;
         label152: {
               if ('t' == string3.charAt(i + 20)) {
                  flag = true;
                  break label152;
               }

            flag = false;
         }

         label145: {
               KoboldVillageGenerator.ShouldGenBuildings = flag;
               if ('t' == string3.charAt(i2 + 22)) {
                  flag = true;
                  break label145;
               }

            flag = false;
         }

         label138: {
               GirlGeoModel.RenderBraStrings = flag;
               if ('t' == string3.charAt(i3 + 13)) {
                  flag = true;
                  break label138;
               }

            flag = false;
         }

         PlayerGirlEntity.FeatureEnabled = flag;
      }
   }
 static RuntimeException rethrow(RuntimeException error) {

      return error;

   }


   private static RuntimeException rethrow(Throwable error10) {
      return new RuntimeException(error10);
   }
}
