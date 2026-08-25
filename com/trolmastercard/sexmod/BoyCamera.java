package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;

public class BoyCamera {
   Vec3d PlayerPos = null;
   Vec3d LastTickPos = null;

   @SubscribeEvent
   public void onRenderPlayerPre(Pre pre) {
      try {
         for (GirlEntity girl : GirlEntity.getAllGirls()) {
            if (girl.isDead || girl.getSexPlayerUuid() == null || girl.getCurrentAction() == GirlAnimationState.NULL) continue;
            EntityPlayer entityPlayer = pre.getEntityPlayer();
            if (!girl.getCurrentAction().hasPlayer || !girl.getSexPlayerUuid().equals(entityPlayer.getPersistentID()) && !girl.getSexPlayerUuid().equals(entityPlayer.getUniqueID())) continue;
            pre.setCanceled(true);
            return;
         }
      }
      catch (ConcurrentModificationException concurrentModificationException) {
         // empty catch block
      }
   }

   @SubscribeEvent
   public void onRenderHand(RenderHandEvent renderHandEvent) {
      Minecraft minecraft = Minecraft.getMinecraft();
      EntityPlayerSP entityPlayerSP = minecraft.player;
      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer((EntityPlayer)entityPlayerSP);
      if (playerGirl != null && playerGirl.Q()) {
         renderHandEvent.setCanceled(true);
         return;
      }
      try {
         for (GirlEntity girl : GirlEntity.getAllGirls()) {
            UUID uUID = girl.getSexPlayerUuid();
            GirlAnimationState girlAnimationState = girl.getCurrentAction();
            if (girl.isDead || uUID == null || girlAnimationState == null || !girlAnimationState.hasPlayer || !uUID.equals(entityPlayerSP.getUniqueID()) && !uUID.equals(entityPlayerSP.getPersistentID())) continue;
            renderHandEvent.setCanceled(true);
            return;
         }
      }
      catch (ConcurrentModificationException concurrentModificationException) {
         // empty catch block
      }
   }

   @SideOnly(value=Side.CLIENT)
   @SubscribeEvent
   public void onRenderTick(RenderTickEvent renderTickEvent) {
      Minecraft minecraft = Minecraft.getMinecraft();
      if (minecraft.player == null) {
         return;
      }
      if (renderTickEvent.phase != TickEvent.Phase.END) {
         if (minecraft.gameSettings.thirdPersonView != 0) {
            return;
         }
         GirlEntity girl = GirlEntity.getByUuidForSide(minecraft.player.getPersistentID(), false);
         if (girl == null) {
            return;
         }
         if (!girl.getCurrentAction().useBoyCam) {
            return;
         }
         if (girl.m()) {
            return;
         }
         this.PlayerPos = minecraft.player.getPositionVector();
         this.LastTickPos = new Vec3d(minecraft.player.lastTickPosX, minecraft.player.lastTickPosY, minecraft.player.lastTickPosZ);
         Vec3d vec3d = girl.Q() ? girl.getModelBone("boyCam").add(girl.getTargetPos()) : girl.getModelBone("boyCam").add(LerpMath.lerpVec3d(new Vec3d(girl.lastTickPosX, girl.lastTickPosY, girl.lastTickPosZ), girl.getPositionVector(), (double)renderTickEvent.renderTickTime));
         minecraft.player.posX = vec3d.x;
         minecraft.player.posY = vec3d.y - (double)minecraft.player.getEyeHeight();
         minecraft.player.posZ = vec3d.z;
         minecraft.player.lastTickPosX = vec3d.x;
         minecraft.player.lastTickPosY = vec3d.y - (double)minecraft.player.getEyeHeight();
         minecraft.player.lastTickPosZ = vec3d.z;
         return;
      }
      if (this.PlayerPos != null) {
         minecraft.player.setPosition(this.PlayerPos.x, this.PlayerPos.y, this.PlayerPos.z);
         minecraft.player.lastTickPosX = this.LastTickPos.x;
         minecraft.player.lastTickPosY = this.LastTickPos.y;
         minecraft.player.lastTickPosZ = this.LastTickPos.z;
         this.PlayerPos = null;
         this.LastTickPos = null;
      }
   }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
