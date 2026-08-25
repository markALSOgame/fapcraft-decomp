package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.ArrayList;
import net.minecraftforge.fml.relauncher.Side;

public class PacketResetGirl implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   boolean Teleport;

   public PacketResetGirl() {
      this.Loaded = false;
   }

   public PacketResetGirl(UUID uuid) {
      this.GirlUuid = uuid;
      this.Teleport = false;
      this.Loaded = true;
   }

   public PacketResetGirl(UUID uuid, boolean flag) {
      this.GirlUuid = uuid;
      this.Teleport = flag;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Teleport = buf.readBoolean();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      buf.writeBoolean(this.Teleport);
      this.Loaded = true;
   }

   public static class Handler implements IMessageHandler<PacketResetGirl, IMessage> {
      public static void handle(GirlEntity girl) {
         label36: {
            try {
               girl.noop();
               if (!(girl instanceof PlayerGirlEntity) || girl.world.getPlayerEntityByUUID(((PlayerGirlEntity)girl).getBoundPlayerUuid()) == null) {
                  break label36;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            NetworkHandler.channel
               .sendTo(
                  new PacketSetPlayerMovement(true),
                  (EntityPlayerMP)FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(girl.dimension).getPlayerEntityByUUID(((PlayerGirlEntity)girl).getBoundPlayerUuid())
               );
            girl.getDataManager().set(GirlEntity.OutfitIndexKey, 1);
            EntityPlayer player = girl.world.getPlayerEntityByUUID(((PlayerGirlEntity)girl).getBoundPlayerUuid());
            player.capabilities.isFlying = false;
            player.setNoGravity(false);
            player.noClip = false;
            girl.setShouldBeAtTargetPos(false);
            girl.setCurrentAction(GirlAnimationState.NULL);
            if (girl.getSexPlayerUuid() != null) {
               EntityPlayer player2 = girl.world.getPlayerEntityByUUID(girl.getSexPlayerUuid());

               try {
                  if (player2 != null) {
                     player2.capabilities.isFlying = false;
                     player2.setNoGravity(false);
                     player2.noClip = false;
                  }
               } catch (RuntimeException error2) {
                  throw rethrow(error2);
               }
            }
         }

         girl.setShouldBeAtTargetPos(false);
         girl.handleGirlUuidEvent((UUID)null);
         girl.AimTarget = null;
         girl.setNoGravity(false);
         girl.noClip = false;
         World world2 = girl.world;
         Vec3d vec3d = girl.getPositionVector();

         while (world2.getBlockState(new BlockPos(vec3d.x, vec3d.y, vec3d.z)).getBlock() != Blocks.AIR) {
            vec3d = vec3d.add(0.0, 1.0, 0.0);
         }

         girl.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
      }

      public static void handle(EntityPlayerMP serverPlayer) {
         try {
            if (serverPlayer == null) {
               return;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         World world2 = serverPlayer.world;
         Vec3d vec3d = serverPlayer.getPositionVector();

         while (world2.getBlockState(new BlockPos(vec3d.x, vec3d.y, vec3d.z)).getBlock() != Blocks.AIR) {
            vec3d = vec3d.add(0.0, 1.0, 0.0);
         }

         serverPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
         serverPlayer.setInvisible(false);
         serverPlayer.noClip = false;
         serverPlayer.setNoGravity(false);
         serverPlayer.capabilities.isFlying = false;
         NetworkHandler.channel.sendTo(new PacketSetPlayerMovement(true), serverPlayer);
      }


      public IMessage onMessage(PacketResetGirl packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketResetGirl.Handler.rethrow(runtimeException);
                    }
                    System.out.println("recieved an unvalid message @ResetGirl :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketResetGirl.Handler.rethrow(runtimeException);
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
                        throw PacketResetGirl.Handler.rethrow(runtimeException);
                    }
                    try {
                        if (girl.getSexPlayerUuid() != null) {
                            PacketResetGirl.Handler.handle(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(girl.getSexPlayerUuid()));
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketResetGirl.Handler.rethrow(runtimeException);
                    }
                    try {
                        if (packet.Teleport) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketResetGirl.Handler.rethrow(runtimeException);
                    }
                    PacketResetGirl.Handler.handle(girl);
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
