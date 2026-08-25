package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSexPrompt implements IMessage {
   boolean Loaded = false;
   String Message;
   UUID PlayerUuid;
   UUID GirlUuid;
   boolean Accept;

   public PacketSexPrompt() {
   }

   public PacketSexPrompt(String string, UUID uuid, UUID uuid2, boolean flag) {
      this.Message = string;
      this.PlayerUuid = uuid;
      this.GirlUuid = uuid2;
      this.Accept = flag;
   }

   public void fromBytes(ByteBuf buf) {
      this.Message = ByteBufUtils.readUTF8String(buf);
      this.PlayerUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Accept = buf.readBoolean();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.Message);
      ByteBufUtils.writeUTF8String(buf, this.PlayerUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      buf.writeBoolean(this.Accept);
   }

   public static class Handler implements IMessageHandler<PacketSexPrompt, IMessage> {
      public IMessage onMessage(PacketSexPrompt packet, MessageContext ctx) {
         try {
            if (!packet.Loaded) {
               System.out.println("received an invalid message @SexPrompt :(");
               return null;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (ctx.side.equals(Side.CLIENT)) {
               ClientChatHandler.Entry.openGui(new ClientChatHandler.Entry(packet.Message, packet.PlayerUuid, packet.GirlUuid, packet.Accept));
               return null;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
            World world2 = ctx.getServerHandler().player.world;
            EntityPlayer player2 = world2.getPlayerEntityByUUID(packet.GirlUuid);
            EntityPlayer player3 = world2.getPlayerEntityByUUID(packet.PlayerUuid);

            try {
               if (player2 == null) {
                  System.out.println("Sex prompt invalid -> female player not found");
                  return;
               }
            } catch (RuntimeException error3) {
               throw rethrow(error3);
            }

            try {
               if (player3 == null) {
                  System.out.println("Sex prompt invalid -> male player not found");
                  return;
               }
            } catch (RuntimeException error4) {
               throw rethrow(error4);
            }

            SimpleNetworkWrapper simpleNetworkWrapper;
            PacketSexPrompt packet2;
            EntityPlayer player4;
            label27: {
               try {
                  simpleNetworkWrapper = NetworkHandler.channel;
                  packet2 = new PacketSexPrompt(packet.Message, packet.PlayerUuid, packet.GirlUuid, packet.Accept);
                  if (packet.Accept) {
                     player4 = player2;
                     break label27;
                  }
               } catch (RuntimeException error5) {
                  throw rethrow(error5);
               }

               player4 = player3;
            }

            simpleNetworkWrapper.sendTo(packet2, (EntityPlayerMP & EntityPlayerMP)player4);
         });
         return null;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
