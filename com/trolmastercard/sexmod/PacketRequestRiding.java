package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;

public class PacketRequestRiding implements IMessage {
   boolean Loaded = false;

   public void fromBytes(ByteBuf buf) {
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
   }

   public static class Handler implements IMessageHandler<PacketRequestRiding, IMessage> {

      public IMessage onMessage(PacketRequestRiding packet, MessageContext ctx) {
            block7: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.SERVER)) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketRequestRiding.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @RequestRiding :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketRequestRiding.Handler.rethrow(runtimeException);
                }
            }
            EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
            UUID uUID = GalathOwnershipData.getGalathByPlayer((EntityPlayer)entityPlayerMP);
            GirlEntity girl = GirlEntity.getServerSideByUuid(uUID);
            try {
                if (girl == null) {
                    return null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw PacketRequestRiding.Handler.rethrow(runtimeException);
            }
            entityPlayerMP.startRiding((Entity)girl, true);
            girl.setCurrentAction(GirlAnimationState.CONTROLLED_FLIGHT);
            girl.isGirlEntity((EntityPlayer)entityPlayerMP);
            girl.motionY = 0.25;
            entityPlayerMP.world.getChunk(girl.getPosition()).removeEntity((Entity)girl);
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
