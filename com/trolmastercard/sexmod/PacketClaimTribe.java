package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketClaimTribe implements IMessage {
   boolean Loaded = false;
   UUID GirlUuid;
   UUID PlayerUuid;
   String TribeName;

   public PacketClaimTribe() {
   }

   public PacketClaimTribe(UUID uuid, UUID uuid2, String string) {
      this.GirlUuid = uuid;
      this.PlayerUuid = uuid2;
      this.TribeName = string;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.PlayerUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.TribeName = ByteBufUtils.readUTF8String(buf);
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.PlayerUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.TribeName);
   }

   public static class Handler implements IMessageHandler<PacketClaimTribe, IMessage> {

      public IMessage handle(PacketClaimTribe packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketClaimTribe.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @ClaimTribe :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketClaimTribe.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                List<KoboldNpc> list = GirlHomeBuilder.getKobolds(packet.GirlUuid);
                EyeAndKoboldColor eyeAndKoboldColor = null;
                for (KoboldNpc object2 : list) {
                    try {
                        if (object2.J()) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketClaimTribe.Handler.rethrow(runtimeException);
                    }
                    EntityDataManager entityDataManager = object2.getDataManager();
                    entityDataManager.set(GirlEntity.MasterUuidKey, (Object)packet.PlayerUuid.toString());
                    entityDataManager.set(KoboldNpc.TribeNameKey, (Object)packet.TribeName);
                    eyeAndKoboldColor = EyeAndKoboldColor.valueOf((String)entityDataManager.get(KoboldNpc.BodyColorKey));
                }
                try {
                    if (eyeAndKoboldColor == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketClaimTribe.Handler.rethrow(runtimeException);
                }
                PlayerList playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
                String string = ctx.getServerHandler().player.getName();
                for (EntityPlayer entityPlayer : playerList.getPlayers()) {
                    entityPlayer.sendMessage((ITextComponent)new TextComponentString(String.format("%s formed the " + eyeAndKoboldColor.getTextColor() + "%s " + TextFormatting.WHITE + "Tribe", string, packet.TribeName)));
                }
                GirlHomeBuilder.setHasTribe(packet.GirlUuid, true);
                GirlHomeBuilder.setOwnerUuid(packet.GirlUuid, ctx.getServerHandler().player.getPersistentID());
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
