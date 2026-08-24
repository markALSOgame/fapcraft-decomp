package com.trolmastercard.sexmod;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ServerThreadUtil {
   public static boolean isServerThread() {
      String string = Thread.currentThread().getName().toLowerCase();

      try {
         if (string.contains("server")) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (string.contains("client")) {
            return false;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

      try {
         if (server == null) {
            return false;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      boolean flag = server.isCallingFromMinecraftThread();
      Main.LOGGER.warn("couldn't clarify if is running on a server or client thread. Came to the solution onServer=" + flag);
      return flag;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
