package com.trolmastercard.sexmod;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.geo.raw.pojo.Converter;
import software.bernie.geckolib3.geo.raw.pojo.RawGeoModel;
import software.bernie.geckolib3.geo.raw.tree.RawGeometryTree;
import software.bernie.geckolib3.geo.render.GeoBuilder;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;
import java.util.ArrayList;
import net.minecraft.entity.Entity;

public class FilePersistence {
   public static final String ModelsFolder = "sexmod/custom_models";
   static final String WhitelistFile = "sexmod/custom_models/whitelisted_servers.txt";
   public static final String ClientModelsDir = "sexmod_custom_models";
   static Map<String, FilePersistence.WhitelistFile> ModelCache = new HashMap<>();
   public static boolean ServerActive = false;
   public static boolean ClientActive = false;

   public static Map<String, FilePersistence.WhitelistFile> getModelCache() {
      return ModelCache;
   }

   public static boolean isModelRegistered(String string) {
      try {
         if (ModelCache.get(string) != null) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   public static int reloadModels(boolean flag) {
      clearModelCache(flag);
      return loadModels(flag);
   }

   static void log(Level level, String string) {
      try {
         if (Main.proxy instanceof ClientProxy) {
            a(level, string);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Main.LOGGER.log(level, string);
   }

   public static void clearModelCache(boolean flag) {
      try {
         if (flag) {
            clearGeoModelCache();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      ModelCache.clear();
   }

   public static void requestModelChecksums() {
      NetworkHandler.channel.sendToServer(new PacketRequestServerModelAvailability());
   }

   @SideOnly(Side.CLIENT)
   public static boolean isServerWhitelisted() {
      String string = getServerAddress();

      try {
         if (string == null) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return isWhitelistedByFile(string);
   }

   public static void whitelistServer(String string) {
      File file = new File("sexmod/custom_models/whitelisted_servers.txt");
      file.mkdirs();
      HashSet set = new HashSet();
      if (file.exists()) {
         set = loadWhitelistFile();
      }

      set.add(string);
      file.delete();
      file = new File("sexmod/custom_models/whitelisted_servers.txt");

      try {
         FileWriter fileWriter = new FileWriter(file);
         Throwable error = null;

         try {
            for (String string2 : set) {
               fileWriter.write(string2 + "\n");
            }
         } catch (Throwable error2) {
            error = error2;
            throw error2;
         } finally {
            label90: {
               label89: {
                  try {
                     if (fileWriter == null) {
                        break label90;
                     }

                     if (error == null) {
                        break label89;
                     }
                  } catch (Throwable error3) {
                     throw rethrow(error3);
                  }

                  try {
                     fileWriter.close();
                  } catch (Throwable error4) {
                     error.addSuppressed(error4);
                  }
                  break label90;
               }

               fileWriter.close();
            }
         }
      } catch (IOException error5) {
         error5.printStackTrace();
      }
   }

   public static boolean isWhitelistedByFile(String string) {
      return loadWhitelistFile().contains(string);
   }

   static HashSet<String> loadWhitelistFile() {
      File file = new File("sexmod/custom_models/whitelisted_servers.txt");

      try {
         file.createNewFile();
      } catch (Exception error) {
         error.printStackTrace();
      }

      HashSet set = new HashSet();

      try {
         BufferedReader reader = new BufferedReader(new FileReader(file));
         Throwable error2 = null;

         try {
            while (true) {
               String string;
               String string2 = string = reader.readLine();

               try {
                  if (string2 == null) {
                     return set;
                  }

                  set.add(string);
               } catch (Exception error3) {
                  throw rethrow(error3);
               }
            }
         } catch (Throwable error4) {
            error2 = error4;
            throw error4;
         } finally {
            label100: {
               label99: {
                  try {
                     if (reader == null) {
                        break label100;
                     }

                     if (error2 == null) {
                        break label99;
                     }
                  } catch (Exception error5) {
                     throw rethrow(error5);
                  }

                  try {
                     reader.close();
                  } catch (Throwable error6) {
                     error2.addSuppressed(error6);
                  }
                  break label100;
               }

               reader.close();
            }
         }
      } catch (IOException error7) {
         error7.printStackTrace();
         return new HashSet<>();
      }
   }

   public static float getBaseScale(String string) {
      FilePersistence.WhitelistFile whitelist = ModelCache.get(string);

      try {
         if (whitelist == null) {
            return 0.0F;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return whitelist.loadWhitelistFile();
   }

   @SideOnly(Side.CLIENT)
   static void clearGeoModelCache() {
      for (Entry entry : ModelCache.entrySet()) {
         FilePersistence.WhitelistFile whitelist = (FilePersistence.WhitelistFile)entry.getValue();

         try {
            if (whitelist == null) {
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         ResourceLocation location = whitelist.clearGeoModelCache();
         ResourceLocation location2 = whitelist.getModelResourceLocation();

         try {
            if (location != null) {
               GeckoLibCache.getInstance().getGeoModels().remove(location);
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         try {
            if (location2 != null) {
               Minecraft.getMinecraft().renderEngine.deleteTexture(location2);
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }
      }
   }

   @SideOnly(Side.CLIENT)
   static void a(Level level, String string) {
      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;

      try {
         if (mcPlayer == null) {
            Main.LOGGER.log(level, string);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      TextFormatting textFormatting;
      if (Level.DEBUG.equals(level)) {
         textFormatting = TextFormatting.DARK_GREEN;
      } else if (Level.ERROR.equals(level)) {
         textFormatting = TextFormatting.RED;
      } else {
         textFormatting = TextFormatting.WHITE;
      }

      mcPlayer.sendMessage(new TextComponentString(textFormatting.toString() + string));
   }

   public static String getModelsPath() {
      try {
         return Main.proxy instanceof ClientProxy ? getClientModelsPath() : "sexmod_custom_models";
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @SideOnly(Side.CLIENT)
   public static String getClientModelsPath() {
      String string = getServerAddress();

      try {
         if (string == null) {
            return "sexmod/custom_models/singleplayer";
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return "sexmod/custom_models/" + string;
   }

   @SideOnly(Side.CLIENT)
   @Nullable
   public static String getServerAddress() {
      Minecraft mc = Minecraft.getMinecraft();
      ServerData serverData = mc.getCurrentServerData();

      try {
         if (serverData == null) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String string = serverData.serverIP;
      int i = string.indexOf(":");
      if (i != -1) {
         string = string.substring(0, i);
      }

      return string;
   }

   public static int loadModels(boolean flag) {
      log(Level.INFO, "loading up custom models...");
      String string = getModelsPath();
      File file = new File(string);
      file.mkdirs();
      String[] stringArray = file.list((arg1, arg2) -> new File(arg1, arg2).isDirectory());

      try {
         if (stringArray == null) {
            log(
               Level.ERROR,
               String.format(
                  "Something is wrong with the custom models folder at '%s'. Check if it exists, if not - make the directory yourself because Minecraft cannot do it itself for some reason",
                  file.getAbsolutePath()
               )
            );
            return -1;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      log(Level.INFO, String.format("found %s custom model(s)", stringArray.length));
      int i = 0;

      for (String string2 : stringArray) {
         String string3 = validateModelFiles(string2, string);

         try {
            if (!"".equals(string3)) {
               log(Level.ERROR, string3);
               return -1;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         string3 = registerModel(string2, string, flag);

         try {
            if (!"".equals(string3)) {
               log(Level.ERROR, string3);
               return -1;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         i++;
      }

      log(Level.DEBUG, String.format("successfully registered %s custom models", i));
      ClientActive = true;
      return 0;
   }

   public static String validateModelFiles(String string, String string2) {
      String string3 = String.format("%s/%s", string2, string);
      File file = new File(String.format("%s/%s.geo.json", string3, string));
      File file2 = new File(String.format("%s/%s.png", string3, string));
      File file3 = new File(String.format("%s/%s.cfg", string3, string));

      try {
         if (!file.exists()) {
            return String.format("couldn't find model File for '%s'. It should have been at '%s'. Are you sure it exists?", string, file.getAbsolutePath());
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!file2.exists()) {
            return String.format("couldn't find texture File for '%s'. It should have been at '%s'. Are you sure it exists?", string, file2.getAbsolutePath());
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         return !file3.exists()
            ? String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", string, file3.getAbsolutePath())
            : "";
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }

   @SideOnly(Side.CLIENT)
   static ResourceLocation registerTexture(String string, File file) throws Exception {
      BufferedImage bufferedImage = ImageIO.read(file);
      return Minecraft.getMinecraft().renderEngine.getDynamicTextureLocation(string, new DynamicTexture(bufferedImage));
   }

   @SideOnly(Side.CLIENT)
   static RawGeoModel readGeoModel(File file) throws IOException {
      StringBuilder sb = new StringBuilder();
      BufferedReader reader = new BufferedReader(new FileReader(file));
      Throwable error = null;

      try {
         while (true) {
            String string;
            String string2 = string = reader.readLine();

            try {
               if (string2 == null) {
                  break;
               }

               sb.append(string);
            } catch (Throwable error2) {
               throw rethrow(error2);
            }
         }
      } catch (Throwable error3) {
         error = error3;
         throw error3;
      } finally {
         label82: {
            label81: {
               try {
                  if (reader == null) {
                     break label82;
                  }

                  if (error == null) {
                     break label81;
                  }
               } catch (Throwable error4) {
                  throw rethrow(error4);
               }

               try {
                  reader.close();
               } catch (Throwable error5) {
                  error.addSuppressed(error5);
               }
               break label82;
            }

            reader.close();
         }
      }

      String string3 = sb.toString();
      return Converter.fromJsonString(string3);
   }

   public static String registerModel(String string, String string2, boolean flag) {
      try {
         if (ModelCache.get(string) != null) {
            return String.format("already registered '%s'... honestly, unsure how this could happen lol", string);
         }
      } catch (IOException error) {
         throw rethrow(error);
      }

      String string3 = String.format("%s/%s/", string2, string);
      String string4 = string3 + string + ".cfg";
      File file = new File(string4);

      try {
         if (!file.exists()) {
            return String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", string, string4);
         }
      } catch (IOException error2) {
         throw rethrow(error2);
      }

      FilePersistence.WhitelistFile whitelist = new FilePersistence.WhitelistFile(file, string);

      try {
         if (whitelist.Error != null) {
            return whitelist.Error;
         }
      } catch (IOException error3) {
         throw rethrow(error3);
      }

      String string5 = string3 + string + ".png";
      File file2 = new File(string5);

      try {
         if (!file2.exists()) {
            return String.format("The texture for the custom model '%s' couldn't be found at '%s' are you sure it exists?", string, string5);
         }
      } catch (IOException error4) {
         throw rethrow(error4);
      }

      ResourceLocation location = null;
      if (flag) {
         try {
            location = registerTexture(string, file2);
         } catch (IOException error5) {
            return String.format("The texture for the custom model '%s' at '%s' appears to be corrupted. Try making a new one", string, string5);
         } catch (Exception error6) {
            return String.format(
               "Couldn't load the texture for the custom model '%s' at '%s'. Maybe try increasing the amount of RAM of ur Minecraft client", string, file2
            );
         }
      }

      ResourceLocation location2 = new ResourceLocation("sexmod", string + "Model");
      String string6 = string3 + string + ".geo.json";
      File file3 = new File(string6);

      try {
         if (!file3.exists()) {
            return String.format("The geo model for the custom model '%s' couldn't be found at '%s' are you sure it exists?", string, string6);
         }
      } catch (IOException error7) {
         throw rethrow(error7);
      }

      if (flag) {
         RawGeoModel rawGeoModel;
         try {
            rawGeoModel = readGeoModel(file3);
         } catch (IOException error8) {
            return String.format("The geo model for the custom model '%s' at '%s' appears to be corrupted. Try replacing it.", string, string6);
         }

         try {
            RawGeometryTree rawGeometryTree = RawGeometryTree.parseHierarchy(rawGeoModel, location2);
            GeoModel model = GeoBuilder.getGeoBuilder(location2.getNamespace()).constructGeoModel(rawGeometryTree);
            GeckoLibCache.getInstance().getGeoModels().put(location2, model);
         } catch (Exception error9) {
            return String.format("The geo model for the custom model '%s' at '%s' appears to be corrupted. Try replacing it.", string, string6);
         }
      }

      try {
         if (flag) {
            whitelist.b(location2);
            whitelist.a(location);
         }
      } catch (IOException error10) {
         throw rethrow(error10);
      }

      ModelCache.put(string, whitelist);
      log(Level.DEBUG, String.format("successfully registered custom model '%s'", string));
      return "";
   }


   public static ResourceLocation getModelResourceLocation(String string) {
        WhitelistFile whitelist;
        block4: {
            block5: {
                whitelist = ModelCache.get(string);
                try {
                    try {
                        if (whitelist != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw FilePersistence.rethrow(runtimeException);
                    }
                    System.out.printf("The custom model for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw FilePersistence.rethrow(runtimeException);
                }
            }
            return null;
        }
        return whitelist.c();
    }


   public static ResourceLocation getModelTexture(String string) {
        WhitelistFile whitelist;
        block4: {
            block5: {
                whitelist = ModelCache.get(string);
                try {
                    try {
                        if (whitelist != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw FilePersistence.rethrow(runtimeException);
                    }
                    System.out.printf("The custom texture for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw FilePersistence.rethrow(runtimeException);
                }
            }
            return null;
        }
        return whitelist.k();
    }

   public static GeoModel getGeoModel(String string) {
      return (GeoModel)GeckoLibCache.getInstance().getGeoModels().get(getModelResourceLocation(string));
   }


   public static GirlBodySlot getBodySlot(String string) {
        WhitelistFile whitelist;
        block4: {
            block5: {
                whitelist = ModelCache.get(string);
                try {
                    try {
                        if (whitelist != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw FilePersistence.rethrow(runtimeException);
                    }
                    System.out.printf("The ClothingType for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw FilePersistence.rethrow(runtimeException);
                }
            }
            return GirlBodySlot.HEAD;
        }
        return whitelist.ModelScale;
    }


   public static HashSet<GirlRegistry> getAllowedGirls(String string) {
        WhitelistFile whitelist;
        block4: {
            block5: {
                whitelist = ModelCache.get(string);
                try {
                    try {
                        if (whitelist != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw FilePersistence.rethrow(runtimeException);
                    }
                    System.out.printf("The HashSet<GirlType> for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw FilePersistence.rethrow(runtimeException);
                }
            }
            return null;
        }
        return whitelist.AllowedGirls;
    }


   public static HashSet<String> getModelSkinTexture(String string) {
        WhitelistFile whitelist;
        block4: {
            block5: {
                whitelist = ModelCache.get(string);
                try {
                    try {
                        if (whitelist != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw FilePersistence.rethrow(runtimeException);
                    }
                    System.out.printf("The HashSet<String> for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw FilePersistence.rethrow(runtimeException);
                }
            }
            return new HashSet<String>();
        }
        return whitelist.WhitelistEntry;
    }


   public static String d(String string) {
        WhitelistFile whitelist;
        block4: {
            block5: {
                whitelist = ModelCache.get(string);
                try {
                    try {
                        if (whitelist != null) break block4;
                        if (string.equals("cross")) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw FilePersistence.rethrow(runtimeException);
                    }
                    System.out.printf("The author for '%s', hasn't been registered, but gamers tried to use it anyways. Crash is imminent%n", string);
                }
                catch (RuntimeException runtimeException) {
                    throw FilePersistence.rethrow(runtimeException);
                }
            }
            return "";
        }
        return whitelist.IsRendering;
    }

   @Nullable
   public static FilePersistence.WhitelistFile getModelData(String string) {
      return ModelCache.get(string);
   }


   public static HashMap<GirlBodySlot, List<String>> getModelNames(GirlEntity girl) {
        HashMap<GirlBodySlot, List<String>> hashMap = new HashMap<GirlBodySlot, List<String>>();
        for (Object object : GirlBodySlot.values()) {
            hashMap.put((GirlBodySlot)((Object)object), new ArrayList());
        }
        for (Map.Entry entry : ModelCache.entrySet()) {
            Object object;
            String string = (String)entry.getKey();
            object = (WhitelistFile)entry.getValue();
            GirlBodySlot girlBodySlot = ((WhitelistFile)object).BodySlot;
            List<String> list = hashMap.get((Object)girlBodySlot);
            try {
                try {
                    if (!((WhitelistFile)object).AllowedGirls.isEmpty() && !((WhitelistFile)object).AllowedGirls.contains((Object)GirlRegistry.getByEntity((Entity)girl))) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw FilePersistence.rethrow(runtimeException);
                }
            }
            catch (RuntimeException runtimeException) {
                throw FilePersistence.rethrow(runtimeException);
            }
            list.add(string);
            hashMap.put(girlBodySlot, list);
        }
        return hashMap;
    }

   public static HashMap<String, Float> getModelChecksums() {
      HashMap map = new HashMap();

      for (Entry entry : getModelCache().entrySet()) {
         map.put(entry.getKey(), ((FilePersistence.WhitelistFile)entry.getValue()).f());
      }

      return map;
   }

   private static Throwable rethrow(Throwable error) {
      return error;
   }

   @SideOnly(Side.CLIENT)
   public static class ChatListener {
      boolean ModelsFolder = false;

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(ClientChatEvent clientChatEvent) {
         String string = clientChatEvent.getOriginalMessage();

         try {
            if (!"id".equals(string)) {
               return;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
         List list = mcPlayer.world.getEntitiesWithinAABB(GirlEntity.class, mcPlayer.getEntityBoundingBox().grow(10.0));
         GirlEntity girl = null;

         for (GirlEntity girl2 : list) {
            if (girl == null) {
               girl = girl2;
            } else if (mcPlayer.getDistance(girl2) < mcPlayer.getDistance(girl)) {
               girl = girl2;
            }
         }

         try {
            if (girl == null) {
               return;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         mcPlayer.sendStatusMessage(new TextComponentString(girl.getGirlUuid().toString()), false);
         clientChatEvent.setCanceled(true);
      }

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(ClientConnectedToServerEvent clientConnectedToServerEvent) {
         Minecraft mc = Minecraft.getMinecraft();
         mc.addScheduledTask(() -> FilePersistence.loadModels(true));
         this.ModelsFolder = false;
      }

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(EntityJoinWorldEvent entityJoinWorldEvent) {
         try {
            if (!entityJoinWorldEvent.getEntity().equals(Minecraft.getMinecraft().player)) {
               return;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         try {
            if (this.ModelsFolder) {
               return;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         try {
            this.ModelsFolder = true;
            if (FilePersistence.isServerWhitelisted()) {
               FilePersistence.requestModelChecksums();
            }
         } catch (RuntimeException error5) {
            throw rethrow(error5);
         }
      }

      @SideOnly(Side.CLIENT)
      @SubscribeEvent
      public void a(ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
         Minecraft.getMinecraft().addScheduledTask(() -> FilePersistence.clearModelCache(true));
         this.ModelsFolder = false;
      }

      private static RuntimeException rethrow(RuntimeException error6) {
         return error6;
      }
   }

   public static class WhitelistFile {
      GirlBodySlot BodySlot;
      HashSet<GirlRegistry> AllowedGirls;
      HashSet<String> AllowedNames;
      String ModelName;
      String FileName;
      boolean IsValid;
      RenderMode RenderMode;
      float BaseScale;
      float HeightOffset;
      ResourceLocation ModelTexture;
      ResourceLocation SkinTexture;
      public String Error;
      float AspectRatio;


      public WhitelistFile(File file, String string) {
         this.AllowedGirls = new HashSet();
         this.AllowedNames = new HashSet();
         this.BaseScale = 1.0f;
         this.HeightOffset = 0.0f;
         this.Error = null;
         if (string.contains(" ") || string.contains("#") || string.contains("$")) {
            this.Error = String.format("You cannot call your custom model '%s'. '#', '$' and spaces are illegal characters", new Object[]{string});
            return;
         }
         if ("cross".equalsIgnoreCase(string)) {
            this.Error = "You cannot call your custom model 'cross'. Im sorry, but I need that specific name for internal stuff";
            return;
         }
         Properties properties = new Properties();
         FileInputStream fileInputStream;
         try {
            fileInputStream = new FileInputStream(file);
         }
         catch (FileNotFoundException error) {
            this.Error = String.format("couldn't find cfg File for '%s'. It should have been at '%s'. Are you sure it exists?", new Object[]{string, file.getAbsolutePath()});
            return;
         }
         try {
            properties.load(fileInputStream);
         }
         catch (IOException error5) {
            this.Error = String.format("couldn't read the cfg File for '%s' at '%s'. It appears to be corrupted. Try making a new one", new Object[]{string, file.getAbsolutePath()});
            return;
         }
         String wearType = properties.getProperty("wear_type");
         try {
            if (wearType == null) {
               this.Error = String.format("The cfg File for the model '%s' at '%s' is missing the 'wear_type'. Go to the bottom of the cfg File and write 'wear_type=HEAD'. Check the cfg files of my examples to see what values for 'wear_type' are possible", new Object[]{string, file.getAbsolutePath()});
               return;
            }
         }
         catch (FileNotFoundException error6) {
            throw FilePersistence.WhitelistFile.rethrow(error6);
         }
         try {
            wearType = wearType.replace(" ", "");
            this.BodySlot = GirlBodySlot.valueOf(wearType);
         }
         catch (IllegalArgumentException error7) {
            this.Error = String.format("you entered '%s' into the 'wear_type' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'wear_type", new Object[]{wearType, string, file.getAbsolutePath()});
            return;
         }
         if (GirlBodySlot.CUSTOM_BONE.equals((Object)this.BodySlot)) {
            this.FileName = properties.getProperty("custom_bone");
            if ("".equals(this.FileName)) {
               this.Error = String.format("You selected CUSTOM_BONE as the 'wear_type' in the cfg file for '%s' at '%s', yet you left the 'custom_bone' field right underneath it empty. If you want ur model to be parented to a specific bone, you have to enter the name of that bone at the field 'custom_bone'.", new Object[]{string, file.getAbsolutePath()});
               return;
            }
         }
         String[] girls = properties.getProperty("which_girls").replace(" ", "").split(",");
         for (String girl : girls) {
            try {
               if ("".equals(girl)) continue;
               this.AllowedGirls.add(GirlRegistry.valueOf(girl));
            }
            catch (IllegalArgumentException error11) {
               this.Error = String.format("you entered '%s' as one of the girls, you put into the 'which_girls' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'which_girls'.", new Object[]{girl, string, file.getAbsolutePath()});
               return;
            }
         }
         String lighting = properties.getProperty("which_lighting");
         try {
            if (lighting == null) {
               this.Error = String.format("The %s's cfg file at '%s' doesn't contain the field 'which_lighting'. Go to the bottom of the cfg file and write either 'which_lighting=DEFAULT', 'which_lighting=SEXMOD', or 'which_lighting=NONE'.", new Object[]{string, file.getAbsolutePath()});
               return;
            }
         }
         catch (FileNotFoundException error12) {
            throw FilePersistence.WhitelistFile.rethrow(error12);
         }
         lighting = lighting.replace(" ", "");
         try {
            this.RenderMode = RenderMode.valueOf(lighting);
         }
         catch (IllegalArgumentException error13) {
            this.Error = String.format("you entered '%s' into the 'which_lighting' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'which_lighting'.", new Object[]{lighting, string, file.getAbsolutePath()});
         }
         String author = properties.getProperty("author");
         this.ModelName = author == null || "".equals(author) ? "anon" : author;
         String bonesToHide = properties.getProperty("bones_to_hide");
         if (bonesToHide != null && !"".equals(bonesToHide)) {
            bonesToHide = bonesToHide.replace(" ", "");
            this.AllowedNames.addAll(Arrays.asList(bonesToHide.split(",")));
         }
         String enableWhenNude = properties.getProperty("enable_when_nude");
         if (enableWhenNude == null) {
            this.IsValid = false;
         } else {
            enableWhenNude = enableWhenNude.replace(" ", "");
            this.IsValid = enableWhenNude.equalsIgnoreCase("yes");
         }
         String sizeFactor = properties.getProperty("gui_size_factor");
         if (sizeFactor != null && !"".equals(sizeFactor)) {
            sizeFactor = sizeFactor.replace(" ", "");
            sizeFactor = sizeFactor.replace(",", ".");
            try {
               this.BaseScale = Float.parseFloat(sizeFactor);
            }
            catch (NumberFormatException error19) {
               this.Error = String.format("you entered '%s' into the 'gui_size_factor' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'gui_size_factor'.", new Object[]{sizeFactor, string, file.getAbsolutePath()});
            }
         }
         String verticalPositioning = properties.getProperty("gui_vertical_positioning");
         if (verticalPositioning != null && !"".equals(verticalPositioning)) {
            verticalPositioning = verticalPositioning.replace(" ", "");
            verticalPositioning = verticalPositioning.replace(",", ".");
            try {
               this.HeightOffset = Float.parseFloat(verticalPositioning);
            }
            catch (NumberFormatException error21) {
               this.Error = String.format("you entered '%s' into the 'gui_vertical_positioning' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'gui_vertical_positioning'.", new Object[]{verticalPositioning, string, file.getAbsolutePath()});
            }
         }
         String version = properties.getProperty("version");
         version = version.replace(" ", "");
         version = version.replace(",", ".");
         try {
            this.AspectRatio = Float.parseFloat(version);
         }
         catch (NumberFormatException error22) {
            this.Error = String.format("you entered '%s' into the 'versionString' field of the %s's cfg file at '%s'. This is not a valid value. Check my examples on what valid values are to enter into the field 'versionString'.", new Object[]{version, string, file.getAbsolutePath()});
         }
      }

      public String b() {
         return this.FileName;
      }

      public RenderMode i() {
         return this.RenderMode;
      }

      public float g() {
         return this.HeightOffset;
      }

      public float d() {
         return this.BaseScale;
      }

      public GirlBodySlot j() {
         return this.BodySlot;
      }

      public HashSet<GirlRegistry> l() {
         return this.AllowedGirls;
      }

      public String e() {
         return this.ModelName;
      }

      public boolean a() {
         return this.IsValid;
      }

      public HashSet<String> h() {
         return this.AllowedNames;
      }

      public ResourceLocation k() {
         return this.ModelTexture;
      }

      public void a(ResourceLocation location) {
         this.ModelTexture = location;
      }

      public ResourceLocation c() {
         return this.SkinTexture;
      }

      public void b(ResourceLocation location) {
         this.SkinTexture = location;
      }

      public float f() {
         return this.AspectRatio;
      }

      private static FileNotFoundException rethrow(FileNotFoundException error) {
         return error;
      }
   }
}
