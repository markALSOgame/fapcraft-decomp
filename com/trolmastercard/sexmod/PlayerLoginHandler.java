package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class PlayerLoginHandler {
   static final UUID BiaPlayerOwnerUuid = UUID.fromString("b91e6484-8911-4def-ab04-9fa3452fca5f");
   static final UUID ElliePlayerOwnerUuid = UUID.fromString("adf20149-2adc-4a9d-9af5-8e9aeda019d6");

   @SubscribeEvent

   public void onPlayerLoggedIn(PlayerLoggedInEvent playerLoggedInEvent) {
        Object object2;
        EntityPlayerMP entityPlayerMP;
        block19: {
            entityPlayerMP = playerLoggedInEvent.player.world.getMinecraftServer().getPlayerList().getPlayerByUUID(playerLoggedInEvent.player.getPersistentID());
            try {
                try {
                    entityPlayerMP.setInvisible(false);
                    entityPlayerMP.setNoGravity(false);
                    entityPlayerMP.noClip = false;
                    if (entityPlayerMP.capabilities.isCreativeMode || !entityPlayerMP.capabilities.isFlying) break block19;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PlayerLoginHandler.rethrow(concurrentModificationException);
                }
                entityPlayerMP.capabilities.isFlying = false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw PlayerLoginHandler.rethrow(concurrentModificationException);
            }
        }
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), entityPlayerMP);
        NetworkHandler.channel.sendTo((IMessage)new PacketInformOfOwnership(GalathOwnershipData.hasOwnershipData(entityPlayerMP.getPersistentID())), entityPlayerMP);
        for (Object object2 : entityPlayerMP.inventory.mainInventory) {
            try {
                try {
                    if (object2.getItem() != ItemAlliesLamp.Instance || !object2.hasTagCompound()) continue;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PlayerLoginHandler.rethrow(concurrentModificationException);
                }
                object2.getTagCompound().setUniqueId("user", UUID.randomUUID());
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw PlayerLoginHandler.rethrow(concurrentModificationException);
            }
        }
        UUID uUID = GirlHomeBuilder.findTribeUuid(entityPlayerMP.getPersistentID());
        if (uUID != null) {
            object2 = GirlHomeBuilder.getTribeAreaPositions(uUID);
            NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks((HashSet<BlockPos>)object2, true), entityPlayerMP);
        }
        PlayerGirlEntity.C();
        object2 = PlayerGirlEntity.getByUuid(playerLoggedInEvent.player.getPersistentID());
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
        try {
            this.removePlayerGirls(world, (EntityPlayer)entityPlayerMP, (PlayerGirlEntity)object2);
            if (object2 != null) {
                ((GirlEntity)object2).a(false);
                ((PlayerGirlEntity)object2).b(GirlAnimationState.NULL);
                PacketResetGirl.Handler.openGui((GirlEntity)object2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw PlayerLoginHandler.rethrow(concurrentModificationException);
        }
        UUID uUID2 = playerLoggedInEvent.player.getPersistentID();
        try {
            if (uUID2.equals(BiaPlayerOwnerUuid)) {
                this.spawnBiaPlayer(world, (EntityPlayer)entityPlayerMP, uUID2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw PlayerLoginHandler.rethrow(concurrentModificationException);
        }
        try {
            if (uUID2.equals(ElliePlayerOwnerUuid)) {
                this.spawnElliePlayer(world, (EntityPlayer)entityPlayerMP, uUID2);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw PlayerLoginHandler.rethrow(concurrentModificationException);
        }
        GalathNpc.getByPlayer((EntityPlayer)entityPlayerMP);
    }

   void spawnBiaPlayer(World world, EntityPlayer player, UUID uuid) {
      BiaPlayer bia = new BiaPlayer(world, uuid);
      bia.setNoGravity(true);
      bia.noClip = true;
      bia.motionX = 0.0;
      bia.motionY = 0.0;
      bia.motionZ = 0.0;
      bia.setPosition(player.posX, player.posY + 69.0, player.posZ);
      world.spawnEntity(bia);
      bia.B();
   }

   void spawnElliePlayer(World world, EntityPlayer player, UUID uuid) {
      ElliePlayer ellie = new ElliePlayer(world, uuid);
      ellie.setNoGravity(true);
      ellie.noClip = true;
      ellie.motionX = 0.0;
      ellie.motionY = 0.0;
      ellie.motionZ = 0.0;
      ellie.setPosition(player.posX, player.posY + 69.0, player.posZ);
      world.spawnEntity(ellie);
      ellie.B();
   }


   void removePlayerGirls(World world, EntityPlayer player, PlayerGirlEntity playerGirl) {
        Predicate<PlayerGirlEntity> predicate = arg1 -> true;
        List list = world.getEntities(PlayerGirlEntity.class, predicate::test);
        for (PlayerGirlEntity playerGirl2 : list) {
            try {
                if (!playerGirl2.getBoundPlayerUuid().equals(player.getPersistentID())) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw PlayerLoginHandler.rethrow(concurrentModificationException);
            }
            try {
                try {
                    if (playerGirl != null && playerGirl2.getEntityId() == playerGirl.getEntityId()) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PlayerLoginHandler.rethrow(concurrentModificationException);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw PlayerLoginHandler.rethrow(concurrentModificationException);
            }
            world.removeEntity((Entity)playerGirl2);
        }
    }

   @SubscribeEvent

   public void onPlayerLoggedOut(PlayerLoggedOutEvent playerLoggedOutEvent) {
        serverPlayer = playerLoggedOutEvent.player;
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                block17: {
                    try {
                        if (girl instanceof PlayerGirlEntity) {
                            ((PlayerGirlEntity)girl).b(serverPlayer);
                        }
                    }
                    catch (ConcurrentModificationException error) {
                        throw PlayerLoginHandler.rethrow(error);
                    }
                    try {
                        if (girl.getSexPlayerUuid() == null) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException error2) {
                        throw PlayerLoginHandler.rethrow(error2);
                    }
                    if (girl.getSexPlayerUuid().equals(serverPlayer.getPersistentID())) ** GOTO lbl23
                    try {
                        block18: {
                            if (!girl.getSexPlayerUuid().equals(serverPlayer.getUniqueID())) break block17;
                            break block18;
                            catch (ConcurrentModificationException error3) {
                                throw PlayerLoginHandler.rethrow(error3);
                            }
                        }
                        PacketResetGirl.Handler.openGui(girl);
                        girl.setShouldBeAtTargetPos(false);
                        girl.setCurrentAction(GirlAnimationState.NULL);
                    }
                    catch (ConcurrentModificationException error4) {
                        throw PlayerLoginHandler.rethrow(error4);
                    }
                }
                if (!(girl instanceof PlayerGirlEntity)) continue;
                try {
                    block19: {
                        if (!((PlayerGirlEntity)girl).m().equals(serverPlayer.getPersistentID())) continue;
                        break block19;
                        catch (ConcurrentModificationException error5) {
                            throw PlayerLoginHandler.rethrow(error5);
                        }
                    }
                    if (girl.getSexPlayerUuid() == null) continue;
                }
                catch (ConcurrentModificationException error6) {
                    throw PlayerLoginHandler.rethrow(error6);
                }
                serverPlayer2 = (EntityPlayerMP)playerLoggedOutEvent.player.world.getPlayerEntityByUUID(girl.getSexPlayerUuid());
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), serverPlayer2);
                PacketResetGirl.Handler.openGui(serverPlayer2);
                serverPlayer.setInvisible(false);
                girl.handleGirlUuidEvent((UUID)null);
            }
        }
        catch (ConcurrentModificationException error7) {
            // empty catch block
        }
    }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
