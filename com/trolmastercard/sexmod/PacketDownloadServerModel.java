package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.FileUtils;

public class PacketDownloadServerModel implements IMessage {
   boolean Loaded;
   List<String> ModelNames = new ArrayList<>();
   byte[] Data;
   PacketDownloadServerModel.Type FileType;
   String ModelName;
   int Id = 0;

   public PacketDownloadServerModel() {
   }

   public PacketDownloadServerModel(List<String> list) {
      this.ModelNames = list;
   }

   public PacketDownloadServerModel(byte[] bytes, PacketDownloadServerModel.Type type, String string) {
      this.Data = bytes;
      this.FileType = type;
      this.ModelName = string;
   }

   public int handle() {
      return this.Id;
   }

   public void handle(int i) {
      this.Id = i;
   }


   public void fromBytes(ByteBuf buf) {
      if (Main.proxy instanceof ClientProxy) {
         if (!FilePersistence.isServerWhitelisted()) {
            return;
         }
         this.ModelName = ByteBufUtils.readUTF8String(buf);
         this.FileType = PacketDownloadServerModel.Type.valueOf(ByteBufUtils.readUTF8String(buf));
         this.Id = buf.readInt();
         int i = buf.readInt();
         this.Data = new byte[i];
         for (int i2 = 0; i2 < i; ++i2) {
            this.Data[i2] = buf.readByte();
         }
         this.Loaded = true;
         return;
      }
      int i3 = buf.readInt();
      for (int i4 = 0; i4 < i3; ++i4) {
         this.ModelNames.add(ByteBufUtils.readUTF8String(buf));
      }
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      if (Main.proxy instanceof ClientProxy) {
         buf.writeInt(this.ModelNames.size());

         for (String string : this.ModelNames) {
            ByteBufUtils.writeUTF8String(buf, string);
         }
      } else {
         ByteBufUtils.writeUTF8String(buf, this.ModelName);
         ByteBufUtils.writeUTF8String(buf, this.FileType.toString());
         buf.writeInt(this.Id);
         buf.writeInt(this.Data.length);

         for (byte bv : this.Data) {
            buf.writeByte(bv);
         }
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketDownloadServerModel, IMessage> {
      static int Id = 0;

      @SideOnly(Side.CLIENT)
      void handle(String string) {
         Minecraft.getMinecraft().player.sendMessage(new TextComponentString(string));
      }

      @SideOnly(Side.CLIENT)
      void handle() {
         Minecraft.getMinecraft().addScheduledTask(() -> FilePersistence.reloadModels(true));
      }


      public IMessage onMessage(PacketDownloadServerModel packet, MessageContext ctx) {
            if (!packet.Loaded) {
                System.out.println("received an invalid Message @DownloadServerModel :(");
                return null;
            }
            if (ctx.side.isClient()) {
                if (!FilePersistence.isServerWhitelisted()) {
                    return null;
                }
                String string = packet.ModelName;
                Type type = packet.FileType;
                byte[] byArray = packet.Data;
                String string2 = FilePersistence.getModelsPath() + "/" + string;
                File file = new File(string2);
                file.mkdirs();
                File file2 = new File(string2 + "/" + string + type.ending);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    Throwable throwable = null;
                    try {
                        fileOutputStream.write(byArray);
                    }
                    catch (Throwable throwable2) {
                        throwable = throwable2;
                        throw throwable2;
                    }
                    finally {
                        if (fileOutputStream != null) {
                            if (throwable != null) {
                                try {
                                    fileOutputStream.close();
                                }
                                catch (Throwable throwable3) {
                                    throwable.addSuppressed(throwable3);
                                }
                            } else {
                                fileOutputStream.close();
                            }
                        }
                    }
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
                int i = 0;
                int i2 = PacketDownloadServerModel.Type.values().length;
                for (Type type2 : PacketDownloadServerModel.Type.values()) {
                    if (!new File(string2 + "/" + string + type2.ending).exists()) continue;
                    ++i;
                }
                if (i == i2) {
                    this.handle(String.format("%sSuccessfully downloaded the custom model '%s%s%s'!", TextFormatting.GREEN, TextFormatting.YELLOW, string, TextFormatting.GREEN));
                } else {
                    this.handle(String.format("%sdownloading custom model '%s%s%s' (%s/%s)...", TextFormatting.GRAY, TextFormatting.YELLOW, string, TextFormatting.GRAY, i, i2));
                }
                if (++Id < packet.Id) {
                    return null;
                }
                Id = 0;
                this.handle();
                return null;
            }
            MinecraftServer minecraftServer = FMLCommonHandler.instance().getMinecraftServerInstance();
            minecraftServer.addScheduledTask(() -> {
                List<String> list = packet.ModelNames;
                ArrayList<PacketDownloadServerModel> arrayList = new ArrayList<PacketDownloadServerModel>();
                for (String object : list) {
                    String string = "sexmod_custom_models/" + object;
                    for (Type type3 : PacketDownloadServerModel.Type.values()) {
                        File file = new File(string + "/" + object + type3.ending);
                        if (!file.exists()) {
                            System.out.println(file.getAbsolutePath() + " doesnt exist lol");
                            continue;
                        }
                        byte[] byArray = null;
                        try {
                            byArray = FileUtils.readFileToByteArray(file);
                        }
                        catch (IOException iOException) {
                            throw new RuntimeException(iOException);
                        }
                        if (byArray == null) continue;
                        arrayList.add(new PacketDownloadServerModel(byArray, type3, object));
                    }
                }
                int i3 = arrayList.size();
                for (PacketDownloadServerModel packet2 : arrayList) {
                    packet2.handle(i3);
                    minecraftServer.addScheduledTask(() -> NetworkHandler.channel.sendTo((IMessage)packet2, ctx.getServerHandler().player));
                }
            });
            return null;
        }

      private static Throwable rethrow(Throwable error) {
         return error;
      }
   }

   public enum Type {
      CFG(".cfg"),
      PNG(".png"),
      GEO(".geo.json");

      public String ending;

      Type(String string) {
         this.ending = string;
      }
   }
}
