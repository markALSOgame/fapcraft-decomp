package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map.Entry;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.ArrayList;
import java.util.Map;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PacketRequestServerModelAvailability implements IMessage {
   boolean Loaded = false;
   HashMap<String, Float> ModelChecksums = new HashMap<>();

   public PacketRequestServerModelAvailability() {
   }

   public PacketRequestServerModelAvailability(HashMap<String, Float> map) {
      this.ModelChecksums = map;
   }

   public void fromBytes(ByteBuf buf) {
      try {
         if (!(Main.proxy instanceof ClientProxy)) {
            this.Loaded = true;
            return;
         }
      } catch (IndexOutOfBoundsException error) {
         throw rethrow(error);
      }

      try {
         if (!FilePersistence.isServerWhitelisted()) {
            return;
         }
      } catch (IndexOutOfBoundsException error2) {
         throw rethrow(error2);
      }

      int i;
      try {
         i = buf.readInt();
      } catch (IndexOutOfBoundsException error3) {
         this.Loaded = true;
         return;
      }

      int i2 = 0;

      try {
         while (i2 < i) {
            this.ModelChecksums.put(ByteBufUtils.readUTF8String(buf), buf.readFloat());
            i2++;
         }
      } catch (IndexOutOfBoundsException error4) {
         throw rethrow(error4);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      try {
         if (Main.proxy instanceof ClientProxy) {
            return;
         }
      } catch (IndexOutOfBoundsException error) {
         throw rethrow(error);
      }

      buf.writeInt(this.ModelChecksums.size());

      for (Entry entry : this.ModelChecksums.entrySet()) {
         ByteBufUtils.writeUTF8String(buf, (String)entry.getKey());
         buf.writeFloat((Float)entry.getValue());
      }
   }

   private static IndexOutOfBoundsException rethrow(IndexOutOfBoundsException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketRequestServerModelAvailability, IMessage> {

      public IMessage onMessage(PacketRequestServerModelAvailability packet, MessageContext ctx) {
            block13: {
                block14: {
                    try {
                        if (!packet.Loaded) {
                            System.out.println("received an invalid Message @RequestServerModelAvailability :(");
                            return null;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketRequestServerModelAvailability.Handler.rethrow(runtimeException);
                    }
                    try {
                        try {
                            if (!ctx.side.isClient()) break block13;
                            if (FilePersistence.isServerWhitelisted()) break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw PacketRequestServerModelAvailability.Handler.rethrow(runtimeException);
                        }
                        return null;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketRequestServerModelAvailability.Handler.rethrow(runtimeException);
                    }
                }
                ArrayList<String> arrayList = new ArrayList<String>();
                for (Map.Entry<String, Float> entry : packet.ModelChecksums.entrySet()) {
                    String string = entry.getKey();
                    try {
                        if (!FilePersistence.isModelRegistered(string)) {
                            arrayList.add(string);
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketRequestServerModelAvailability.Handler.rethrow(runtimeException);
                    }
                    float f = FilePersistence.getBaseScale(string);
                    float f2 = entry.getValue().floatValue();
                    try {
                        if (!(f2 > f)) continue;
                        arrayList.add(string);
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketRequestServerModelAvailability.Handler.rethrow(runtimeException);
                    }
                }
                return new PacketDownloadServerModel(arrayList);
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> NetworkHandler.channel.sendTo((IMessage)new PacketRequestServerModelAvailability(FilePersistence.getModelChecksums()), ctx.getServerHandler().player));
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
