package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumParticleTypes;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSendCompanionHome implements IMessage {
   boolean Loaded;
   UUID GirlUuid;

   public PacketSendCompanionHome() {
   }

   public PacketSendCompanionHome(UUID uuid) {
      this.GirlUuid = uuid;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
   }

   public static class Handler implements IMessageHandler<PacketSendCompanionHome, IMessage> {

      public IMessage onMessage(PacketSendCompanionHome packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSendCompanionHome.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @SendCompanionHome :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSendCompanionHome.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                ArrayList<GirlEntity> arrayList = GirlEntity.getGirlsByOwner(packet.GirlUuid);
                for (GirlEntity girl : arrayList) {
                    try {
                        if (girl.world.isRemote) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSendCompanionHome.Handler.rethrow(runtimeException);
                    }
                    try {
                        if (girl.getCurrentAction() != GirlAnimationState.THROW_PEARL) {
                            girl.setCurrentAction(GirlAnimationState.THROW_PEARL);
                            girl.b((float)Math.atan2(girl.posZ - girl.HomePos.z, girl.posX - girl.HomePos.x) * 57.29578f + 90.0f);
                            girl.setTargetPos(girl.getPositionVector());
                            girl.getDataManager().set(GirlEntity.BusyKey, (Object)true);
                            girl.EnderPearl = null;
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSendCompanionHome.Handler.rethrow(runtimeException);
                    }
                    if (girl.EnderPearl == null) {
                        float f = (float)girl.getPositionVector().distanceTo(girl.HomePos);
                        girl.EnderPearl = new CustomEnderPearl(girl.world, (EntityLivingBase)girl);
                        girl.EnderPearl.shoot(girl.HomePos.x - girl.posX, girl.HomePos.y - girl.posY, girl.HomePos.z - girl.posZ, Math.min(4.0f, f * 0.1f), 0.0f);
                        girl.world.spawnEntity((Entity)girl.EnderPearl);
                        continue;
                    }
                    WorldServer worldServer = (WorldServer)girl.world;
                    try {
                        for (int i = 0; i < 32; ++i) {
                            worldServer.spawnParticle(EnumParticleTypes.PORTAL, false, girl.posX, girl.posY + ModConstants.Random.nextDouble() * 2.0, girl.posZ, 32, 0.2, 0.2, 0.2, ModConstants.Random.nextGaussian(), new int[0]);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSendCompanionHome.Handler.rethrow(runtimeException);
                    }
                    girl.setPosition(girl.HomePos.x, girl.HomePos.y, girl.HomePos.z);
                    girl.EnderPearl = null;
                    girl.setCurrentAction(GirlAnimationState.NULL);
                    girl.getDataManager().set(GirlEntity.BusyKey, (Object)false);
                    girl.resetMasterAndWalkSpeed();
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
