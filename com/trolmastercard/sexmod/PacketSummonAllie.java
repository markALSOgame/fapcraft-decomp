package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSummonAllie implements IMessage {
   boolean Loaded = false;

   public void fromBytes(ByteBuf buf) {
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
   }

   public static class Handler implements IMessageHandler<PacketSummonAllie, IMessage> {

      public IMessage handle(PacketSummonAllie packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSummonAllie.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @SummonAllie :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSummonAllie.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                block5: {
                    GirlAnimationState girlAnimationState;
                    AllieNpc allie;
                    AllieNpc allie2;
                    block4: {
                        EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
                        Vec3d vec3d = entityPlayerMP.getPositionVector().add(-Math.sin((double)entityPlayerMP.rotationYawHead * (Math.PI / 180)) * 2.0, 0.0, Math.cos((double)entityPlayerMP.rotationYawHead * (Math.PI / 180)) * 2.0);
                        allie2 = new AllieNpc(entityPlayerMP.world, entityPlayerMP.getHeldItemMainhand());
                        allie2.handleGirlUuidEvent(entityPlayerMP.getPersistentID());
                        allie2.setPositionAndRotation(vec3d.x, vec3d.y, vec3d.z, entityPlayerMP.rotationYawHead + 180.0f, entityPlayerMP.rotationPitch);
                        allie2.setTargetPos(allie2.getPositionVector());
                        allie2.b(entityPlayerMP.rotationYawHead + 180.0f);
                        allie2.setNoGravity(true);
                        allie2.noClip = true;
                        entityPlayerMP.world.spawnEntity((Entity)allie2);
                        BlockPos blockPos = allie2.getPosition().add(0, -1, 0);
                        try {
                            if (!allie2.world.getBlockState(blockPos).getBlock().equals(Blocks.SAND)) break block4;
                            allie2.b(GirlAnimationState.SUMMON_SAND);
                            break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw PacketSummonAllie.Handler.rethrow(runtimeException);
                        }
                    }
                    try {
                        allie = allie2;
                        girlAnimationState = allie2.isFirstTimeWithItem() ? GirlAnimationState.SUMMON : GirlAnimationState.SUMMON_NORMAL;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSummonAllie.Handler.rethrow(runtimeException);
                    }
                    allie.b(girlAnimationState);
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
