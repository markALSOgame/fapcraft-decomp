package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetNewHome implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   Vec3d HomePos;

   public PacketSetNewHome() {
   }

   public PacketSetNewHome(UUID uuid, Vec3d vec3d) {
      this.GirlUuid = uuid;
      this.HomePos = vec3d;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.HomePos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      buf.writeDouble(this.HomePos.x);
      buf.writeDouble(this.HomePos.y);
      buf.writeDouble(this.HomePos.z);
   }

   public static class Handler implements IMessageHandler<PacketSetNewHome, IMessage> {
      public IMessage onMessage(PacketSetNewHome packet, MessageContext ctx) {
         try {
            if (!packet.Loaded) {
               System.out.println("received an invalid message @SetNewHome :(");
               return null;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
            ArrayList list = GirlEntity.getGirlsByOwner(packet.GirlUuid);

            try {
               if (list.isEmpty()) {
                  return;
               }
            } catch (RuntimeException error2) {
               throw rethrow(error2);
            }

            for (GirlEntity girl : list) {
               girl.HomePos = new Vec3d(packet.HomePos.x, Math.floor(packet.HomePos.y), packet.HomePos.z);
            }
         });
         return null;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
