package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Constructor;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketUpdatePlayerModel implements IMessage {
   boolean Loaded = false;
   GirlRegistry GirlType;

   public PacketUpdatePlayerModel() {
   }

   public PacketUpdatePlayerModel(GirlRegistry girlType) {
      this.GirlType = girlType;
   }

   public void fromBytes(ByteBuf buf) {
      String string = ByteBufUtils.readUTF8String(buf);

      label17: {
         try {
            if ("player".equals(string)) {
               this.GirlType = null;
               break label17;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         this.GirlType = GirlRegistry.valueOf(string);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      try {
         if (this.GirlType == null) {
            ByteBufUtils.writeUTF8String(buf, "player");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      ByteBufUtils.writeUTF8String(buf, this.GirlType.toString());
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketUpdatePlayerModel, IMessage> {

      public IMessage handle(PacketUpdatePlayerModel packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PacketUpdatePlayerModel.Handler.rethrow(concurrentModificationException);
                    }
                    System.out.println("received an invalid message @UpdatePlayerModel :(");
                    return null;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PacketUpdatePlayerModel.Handler.rethrow(concurrentModificationException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
                World world = serverPlayer.world;
                UUID uuid = serverPlayer.getPersistentID();
                PlayerGirlEntity existing = PlayerGirlEntity.getByUuid(uuid);
                if (existing != null) {
                    try {
                        for (GirlEntity girl : GirlEntity.getAllGirls()) {
                            if (girl.world.isRemote || !girl.getGirlUuid().equals(existing.getGirlUuid())) continue;
                            world.removeEntity(girl);
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        // empty catch block
                    }
                    existing.y();
                    PlayerGirlEntity.PlayerGirls.remove(uuid);
                    GirlEntity.getAllGirls().remove(existing);
                    existing.a(Optional.absent());
                }
                GirlRegistry girlType = packet.GirlType;
                if (girlType == null) {
                    return;
                }
                PlayerGirlEntity spawned;
                try {
                    Constructor<? extends PlayerGirlEntity> constructor = girlType.playerClass.getConstructor(World.class, UUID.class);
                    spawned = constructor.newInstance(world, uuid);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    return;
                }
                spawned.setNoGravity(true);
                spawned.noClip = true;
                spawned.motionX = 0.0;
                spawned.motionY = 0.0;
                spawned.motionZ = 0.0;
                spawned.setPosition(serverPlayer.posX, serverPlayer.posY + 69.0, serverPlayer.posZ);
                world.spawnEntity(spawned);
                spawned.B();
            });
            return null;
        }

      private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
         return error;
      }
   }
}
