package com.trolmastercard.sexmod;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
   public static SimpleNetworkWrapper channel;
   private static int NextIdCounter = 0;

   private static int nextId() {
      return NextIdCounter++;
   }

   public static void init() {
      channel = NetworkRegistry.INSTANCE.newSimpleChannel("sexmodchannel");
      channel.registerMessage(PacketSendChatMessage.Handler.class, PacketSendChatMessage.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketSendChatMessage.Handler.class, PacketSendChatMessage.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSetPlayerMovement.Handler.class, PacketSetPlayerMovement.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketTeleportPlayer.Handler.class, PacketTeleportPlayer.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSendGirlToSex.Handler.class, PacketSendGirlToSex.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSetPlayerForGirl.Handler.class, PacketSetPlayerForGirl.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSexPromptReply.Handler.class, PacketSexPromptReply.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketResetController.Handler.class, PacketResetController.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketResetController.Handler.class, PacketResetController.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketResetGirl.Handler.class, PacketResetGirl.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketUpdateGirl.Handler.class, PacketUpdateGirl.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketOpenEquipment.Handler.class, PacketOpenEquipment.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSendCompanionHome.Handler.class, PacketSendCompanionHome.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSetNewHome.Handler.class, PacketSetNewHome.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketUploadInventoryToServer.Handler.class, PacketUploadInventoryToServer.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketRemoveItems.Handler.class, PacketRemoveItems.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSummonAllie.Handler.class, PacketSummonAllie.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketUploadInventory.Handler.class, PacketUploadInventory.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketMakeRichWish.Handler.class, PacketMakeRichWish.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketUpdatePlayerModel.Handler.class, PacketUpdatePlayerModel.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSexPrompt.Handler.class, PacketSexPrompt.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSexPrompt.Handler.class, PacketSexPrompt.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketStartStandingSexAnimation.Handler.class, PacketStartStandingSexAnimation.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketBeeOpenChest.Handler.class, PacketBeeOpenChest.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketCatActivateFishing.Handler.class, PacketCatActivateFishing.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketCatEatingDone.Handler.class, PacketCatEatingDone.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketCatThrowAwayItem.Handler.class, PacketCatThrowAwayItem.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketClaimTribe.Handler.class, PacketClaimTribe.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketGetTribeUIValues.Handler.class, PacketGetTribeUIValues.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketGetTribeUIValues.Handler.class, PacketGetTribeUIValues.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketSetTribeFollowMode.Handler.class, PacketSetTribeFollowMode.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketFallTree.Handler.class, PacketFallTree.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSendBlocks.Handler.class, PacketSendBlocks.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketSendBlocks.Handler.class, PacketSendBlocks.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketCancelTask.Handler.class, PacketCancelTask.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSpawnParticle.Handler.class, PacketSpawnParticle.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketSendEgg.Handler.class, PacketSendEgg.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketMine.Handler.class, PacketMine.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketGirlSpecific.Handler.class, PacketGirlSpecific.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketForcePlayerGirlUpdate.Handler.class, PacketForcePlayerGirlUpdate.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketUploadModelString.Handler.class, PacketUploadModelString.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketGalathRapePounce.Handler.class, PacketGalathRapePounce.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketUpdateVelocity.Handler.class, PacketUpdateVelocity.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketRequestServerModelAvailability.Handler.class, PacketRequestServerModelAvailability.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketRequestServerModelAvailability.Handler.class, PacketRequestServerModelAvailability.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketDownloadServerModel.Handler.class, PacketDownloadServerModel.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketDownloadServerModel.Handler.class, PacketDownloadServerModel.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketSpawnEnergyBallParticles.Handler.class, PacketSpawnEnergyBallParticles.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketGalathBackOffRape.Handler.class, PacketGalathBackOffRape.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketInformOfOwnership.Handler.class, PacketInformOfOwnership.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketRequestRiding.Handler.class, PacketRequestRiding.class, nextId(), Side.SERVER);
      channel.registerMessage(PacketSpawnEnergyBallParticle.Handler.class, PacketSpawnEnergyBallParticle.class, nextId(), Side.CLIENT);
      channel.registerMessage(PacketSetPlayerCam.Handler.class, PacketSetPlayerCam.class, nextId(), Side.CLIENT);
   }
}
