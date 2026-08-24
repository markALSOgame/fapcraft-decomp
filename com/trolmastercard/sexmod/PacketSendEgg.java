package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSendEgg implements IMessage {
   boolean Value;

   public void fromBytes(ByteBuf buf) {
      this.Value = true;
   }

   public void toBytes(ByteBuf buf) {
   }

   public static class Handler implements IMessageHandler<PacketSendEgg, IMessage> {

      public IMessage handle(PacketSendEgg packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Value && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSendEgg.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid Message @SendEgg :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSendEgg.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
                UUID uUID = GirlHomeBuilder.findTribeUuid(entityPlayerMP.getPersistentID());
                try {
                    if (uUID == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSendEgg.Handler.rethrow(runtimeException);
                }
                EyeAndKoboldColor eyeAndKoboldColor = GirlHomeBuilder.getTribeColor(uUID);
                ItemStack itemStack = new ItemStack((Item)ItemKoboldEgg.Instance, 1, eyeAndKoboldColor.getWoolMeta());
                NBTTagCompound nBTTagCompound = itemStack.getTagCompound();
                if (nBTTagCompound == null) {
                    nBTTagCompound = new NBTTagCompound();
                }
                nBTTagCompound.setString("tribeID", uUID.toString());
                itemStack.setTagCompound(nBTTagCompound);
                entityPlayerMP.inventory.addItemStackToInventory(itemStack);
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
