package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        block10: {
            block11: {
                try {
                    try {
                        if (!(Main.proxy instanceof ClientProxy)) break block10;
                        if (FilePersistence.isServerWhitelisted()) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketDownloadServerModel.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketDownloadServerModel.rethrow(runtimeException);
                }
            }
            this.ModelName = ByteBufUtils.readUTF8String((ByteBuf)buf);
            this.FileType = PacketDownloadServerModel.Type.valueOf(ByteBufUtils.readUTF8String((ByteBuf)buf));
            this.Id = buf.readInt();
            int i = buf.readInt();
            this.Data = new byte[i];
            try {
                for (int i4 = 0; i4 < i; ++i4) {
                    this.Data[i4] = buf.readByte();
                }
            }
            catch (RuntimeException runtimeException) {
                throw PacketDownloadServerModel.rethrow(runtimeException);
            }
            this.Loaded = true;
            return;
        }
        int i5 = buf.readInt();
        try {
            for (int i6 = 0; i6 < i5; ++i6) {
                this.ModelNames.add(ByteBufUtils.readUTF8String((ByteBuf)buf));
            }
        }
        catch (RuntimeException runtimeException) {
            throw PacketDownloadServerModel.rethrow(runtimeException);
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


      public IMessage handle(PacketDownloadServerModel packet, MessageContext ctx) {
            block27: {
                block34: {
                    int i;
                    int i2;
                    String string;
                    block33: {
                        String string2;
                        block29: {
                            block28: {
                                try {
                                    if (!packet.Loaded) {
                                        System.out.println("received an invalid Message @DownloadServerModel :(");
                                        return null;
                                    }
                                }
                                catch (Throwable throwable) {
                                    throw PacketDownloadServerModel.Handler.rethrow(throwable);
                                }
                                if (!ctx.side.isClient()) break block27;
                                try {
                                    block35: {
                                        if (FilePersistence.isServerWhitelisted()) break block28;
                                        break block35;
                                        catch (Throwable throwable) {
                                            throw PacketDownloadServerModel.Handler.rethrow(throwable);
                                        }
                                    }
                                    return null;
                                }
                                catch (Throwable throwable) {
                                    throw PacketDownloadServerModel.Handler.rethrow(throwable);
                                }
                            }
                            string = packet.ModelName;
                            Type type = packet.FileType;
                            byte[] byArray = packet.Data;
                            string2 = FilePersistence.getModelsPath() + "/" + string;
                            File file = new File(string2);
                            file.mkdirs();
                            File file2 = new File(string2 + "/" + string + type.ending);
                            try {
                                Throwable throwable;
                                FileOutputStream fileOutputStream;
                                block30: {
                                    fileOutputStream = new FileOutputStream(file2);
                                    throwable = null;
                                    fileOutputStream.write(byArray);
                                    if (fileOutputStream == null) break block29;
                                    if (throwable == null) break block30;
                                    try {
                                        fileOutputStream.close();
                                    }
                                    catch (Throwable throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    break block29;
                                }
                                fileOutputStream.close();
                                break block29;
                                catch (Throwable throwable3) {
                                    try {
                                        throwable = throwable3;
                                        throw throwable3;
                                    }
                                    catch (Throwable throwable4) {
                                        block31: {
                                            block32: {
                                                try {
                                                    if (fileOutputStream == null) break block31;
                                                    if (throwable == null) break block32;
                                                }
                                                catch (Throwable throwable5) {
                                                    throw PacketDownloadServerModel.Handler.rethrow(throwable5);
                                                }
                                                try {
                                                    fileOutputStream.close();
                                                }
                                                catch (Throwable throwable6) {
                                                    throwable.addSuppressed(throwable6);
                                                }
                                                break block31;
                                            }
                                            fileOutputStream.close();
                                        }
                                        throw throwable4;
                                    }
                                }
                            }
                            catch (IOException iOException) {
                                iOException.printStackTrace();
                            }
                        }
                        i2 = 0;
                        i = PacketDownloadServerModel.Type.values().length;
                        for (Type type2 : PacketDownloadServerModel.Type.values()) {
                            try {
                                if (!new File(string2 + "/" + string + type2.ending).exists()) continue;
                                ++i2;
                            }
                            catch (Throwable throwable) {
                                throw PacketDownloadServerModel.Handler.rethrow(throwable);
                            }
                        }
                        try {
                            if (i2 != i) break block33;
                            this.handle(String.format("%sSuccessfully downloaded the custom model '%s%s%s'!", TextFormatting.GREEN, TextFormatting.YELLOW, string, TextFormatting.GREEN));
                            break block34;
                        }
                        catch (Throwable throwable) {
                            throw PacketDownloadServerModel.Handler.rethrow(throwable);
                        }
                    }
                    this.handle(String.format("%sdownloading custom model '%s%s%s' (%s/%s)...", TextFormatting.GRAY, TextFormatting.YELLOW, string, TextFormatting.GRAY, i2, i));
                }
                try {
                    if (++Id < packet.Id) {
                        return null;
                    }
                }
                catch (Throwable throwable) {
                    throw PacketDownloadServerModel.Handler.rethrow(throwable);
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
                        try {
                            if (!file.exists()) {
                                System.out.println(file.getAbsolutePath() + " doesnt exist lol");
                                continue;
                            }
                        }
                        catch (IOException iOException) {
                            throw PacketDownloadServerModel.Handler.rethrow(iOException);
                        }
                        byte[] byArray = null;
                        try {
                            byArray = FileUtils.readFileToByteArray((File)file);
                        }
                        catch (IOException iOException) {
                            throw new RuntimeException(iOException);
                        }
                        try {
                            if (byArray == null) {
                                continue;
                            }
                        }
                        catch (IOException iOException) {
                            throw PacketDownloadServerModel.Handler.rethrow(iOException);
                        }
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
