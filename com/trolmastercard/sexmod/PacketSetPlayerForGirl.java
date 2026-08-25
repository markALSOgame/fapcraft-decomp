package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSetPlayerForGirl implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   UUID PlayerUuid;

   public PacketSetPlayerForGirl() {
      this.Loaded = false;
   }

   public PacketSetPlayerForGirl(UUID uuid, UUID uuid2) {
      this.GirlUuid = uuid;
      this.PlayerUuid = uuid2;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.PlayerUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.PlayerUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketSetPlayerForGirl, IMessage> {

      public IMessage onMessage(PacketSetPlayerForGirl packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw PacketSetPlayerForGirl.Handler.rethrow(nullPointerException);
                    }
                    System.out.println("received an invalid message @SetPlayerForGirl :(");
                    return null;
                }
                catch (NullPointerException nullPointerException) {
                    throw PacketSetPlayerForGirl.Handler.rethrow(nullPointerException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                ArrayList<GirlEntity> arrayList = GirlEntity.getGirlsByOwner(packet.GirlUuid);
                for (GirlEntity girl : arrayList) {
                    PlayerList playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
                    try {
                        playerList.getPlayerByUUID(packet.PlayerUuid).getName();
                    }
                    catch (NullPointerException nullPointerException) {
                        System.out.println("couldn't find player with UUID: " + packet.PlayerUuid);
                        System.out.println("could only find players with thsese UUID's:");
                        for (EntityPlayerMP entityPlayerMP : playerList.getPlayers()) {
                            System.out.println(entityPlayerMP.getName() + " " + entityPlayerMP.getUniqueID());
                        }
                        continue;
                    }
                    try {
                        if (girl instanceof JennyNpc) {
                            ((JennyNpc)girl).af = true;
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw PacketSetPlayerForGirl.Handler.rethrow(nullPointerException);
                    }
                    girl.handleGirlUuidEvent(packet.PlayerUuid);
                }
            });
            return null;
        }

      private static NullPointerException rethrow(NullPointerException error) {
         return error;
      }
   }
}
