package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PlayerLoginHandler {
   static final UUID BiaPlayerOwnerUuid = UUID.fromString("b91e6484-8911-4def-ab04-9fa3452fca5f");
   static final UUID ElliePlayerOwnerUuid = UUID.fromString("adf20149-2adc-4a9d-9af5-8e9aeda019d6");

   @SubscribeEvent

   public void onPlayerLoggedIn(PlayerLoggedInEvent playerLoggedInEvent) {
        EntityPlayerMP serverPlayer = playerLoggedInEvent.player.world.getMinecraftServer().getPlayerList().getPlayerByUUID(playerLoggedInEvent.player.getPersistentID());
        serverPlayer.setInvisible(false);
        serverPlayer.setNoGravity(false);
        serverPlayer.noClip = false;
        if (!serverPlayer.capabilities.isCreativeMode && serverPlayer.capabilities.isFlying) {
            serverPlayer.capabilities.isFlying = false;
        }
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), serverPlayer);
        NetworkHandler.channel.sendTo((IMessage)new PacketInformOfOwnership(GalathOwnershipData.hasOwnershipData(serverPlayer.getPersistentID())), serverPlayer);
        for (ItemStack itemStack : serverPlayer.inventory.mainInventory) {
            if (itemStack.getItem() != ItemAlliesLamp.Instance || !itemStack.hasTagCompound()) continue;
            itemStack.getTagCompound().setUniqueId("user", UUID.randomUUID());
        }
        UUID tribeUuid = GirlHomeBuilder.findTribeUuid(serverPlayer.getPersistentID());
        if (tribeUuid != null) {
            HashSet<BlockPos> tribePositions = GirlHomeBuilder.getTribeAreaPositions(tribeUuid);
            NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(tribePositions, true), serverPlayer);
        }
        PlayerGirlEntity.C_();
        PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(playerLoggedInEvent.player.getPersistentID());
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
        this.removePlayerGirls(world, (EntityPlayer)serverPlayer, playerGirl);
        if (playerGirl != null) {
            playerGirl.setShouldBeAtTargetPos(false);
            playerGirl.setCurrentAction(GirlAnimationState.NULL);
            PacketResetGirl.Handler.handle(playerGirl);
        }
        UUID uuid = playerLoggedInEvent.player.getPersistentID();
        if (uuid.equals(BiaPlayerOwnerUuid)) {
            this.spawnBiaPlayer(world, (EntityPlayer)serverPlayer, uuid);
        }
        if (uuid.equals(ElliePlayerOwnerUuid)) {
            this.spawnElliePlayer(world, (EntityPlayer)serverPlayer, uuid);
        }
        GalathNpc.getByPlayer((EntityPlayer)serverPlayer);
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
      bia.B_();
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
      ellie.B_();
   }


   void removePlayerGirls(World world, EntityPlayer player, PlayerGirlEntity playerGirl) {
        Predicate<PlayerGirlEntity> predicate = arg1 -> true;
        List list = world.getEntities(PlayerGirlEntity.class, predicate::test);
        for (PlayerGirlEntity playerGirl2 : list) {
            if (!playerGirl2.getBoundPlayerUuid().equals(player.getPersistentID()) || playerGirl != null && playerGirl2.getEntityId() == playerGirl.getEntityId()) continue;
            world.removeEntity(playerGirl2);
        }
    }

   @SubscribeEvent

   public void onPlayerLoggedOut(PlayerLoggedOutEvent playerLoggedOutEvent) {
        EntityPlayer serverPlayer = playerLoggedOutEvent.player;
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                if (girl instanceof PlayerGirlEntity) {
                    ((PlayerGirlEntity)girl).startInteraction(serverPlayer);
                }
                if (girl.getSexPlayerUuid() == null) continue;
                if (girl.getSexPlayerUuid().equals(serverPlayer.getPersistentID()) || girl.getSexPlayerUuid().equals(serverPlayer.getUniqueID())) {
                    PacketResetGirl.Handler.handle(girl);
                    girl.setShouldBeAtTargetPos(false);
                    girl.setCurrentAction(GirlAnimationState.NULL);
                }
                if (!(girl instanceof PlayerGirlEntity) || !((PlayerGirlEntity)girl).getBoundPlayerUuid().equals(serverPlayer.getPersistentID()) || girl.getSexPlayerUuid() == null) continue;
                EntityPlayerMP serverPlayer2 = (EntityPlayerMP)playerLoggedOutEvent.player.world.getPlayerEntityByUUID(girl.getSexPlayerUuid());
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), serverPlayer2);
                PacketResetGirl.Handler.handle(serverPlayer2);
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
