package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BoyCamera {
   Vec3d PlayerPos = null;
   Vec3d LastTickPos = null;

   @SubscribeEvent

   public void onRenderPlayerPre(Pre pre) {
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                block10: {
                    if (girl.isDead || girl.getSexPlayerUuid() == null) continue;
                    try {
                        if (girl.getCurrentAction() == GirlAnimationState.NULL) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw BoyCamera.rethrow(concurrentModificationException);
                    }
                    EntityPlayer entityPlayer = pre.getEntityPlayer();
                    if (!girl.getCurrentAction().hasPlayer) continue;
                    try {
                        block11: {
                            if (girl.getSexPlayerUuid().equals(entityPlayer.getPersistentID())) break block10;
                            break block11;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw BoyCamera.rethrow(concurrentModificationException);
                            }
                        }
                        if (!girl.getSexPlayerUuid().equals(entityPlayer.getUniqueID())) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw BoyCamera.rethrow(concurrentModificationException);
                    }
                }
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
        EntityPlayerSP entityPlayerSP;
        block14: {
            Minecraft minecraft = Minecraft.getMinecraft();
            entityPlayerSP = minecraft.player;
            PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer((EntityPlayer)entityPlayerSP);
            if (playerGirl == null) break block14;
            try {
                block16: {
                    if (!playerGirl.Q()) break block14;
                    break block16;
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw BoyCamera.rethrow(concurrentModificationException);
                    }
                }
                renderHandEvent.setCanceled(true);
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw BoyCamera.rethrow(concurrentModificationException);
            }
        }
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                block15: {
                    UUID uUID = girl.getSexPlayerUuid();
                    GirlAnimationState girlAnimationState = girl.getCurrentAction();
                    if (girl.isDead || uUID == null) continue;
                    try {
                        if (girlAnimationState == null) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw BoyCamera.rethrow(concurrentModificationException);
                    }
                    if (!girlAnimationState.hasPlayer) continue;
                    try {
                        block17: {
                            if (uUID.equals(entityPlayerSP.getUniqueID())) break block15;
                            break block17;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw BoyCamera.rethrow(concurrentModificationException);
                            }
                        }
                        if (!uUID.equals(entityPlayerSP.getPersistentID())) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw BoyCamera.rethrow(concurrentModificationException);
                    }
                }
                renderHandEvent.setCanceled(true);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
    }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent

   public void onRenderTick(RenderTickEvent renderTickEvent) {
        Vec3d vec3d;
        Minecraft minecraft;
        block21: {
            block22: {
                minecraft = Minecraft.getMinecraft();
                try {
                    if (minecraft.player == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw BoyCamera.rethrow(concurrentModificationException);
                }
                try {
                    try {
                        if (renderTickEvent.phase != TickEvent.Phase.END) break block21;
                        if (this.PlayerPos == null) break block22;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw BoyCamera.rethrow(concurrentModificationException);
                    }
                    minecraft.player.setPosition(this.PlayerPos.x, this.PlayerPos.y, this.PlayerPos.z);
                    minecraft.player.lastTickPosX = this.LastTickPos.x;
                    minecraft.player.lastTickPosY = this.LastTickPos.y;
                    minecraft.player.lastTickPosZ = this.LastTickPos.z;
                    this.PlayerPos = null;
                    this.LastTickPos = null;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw BoyCamera.rethrow(concurrentModificationException);
                }
            }
            return;
        }
        try {
            if (minecraft.gameSettings.thirdPersonView != 0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw BoyCamera.rethrow(concurrentModificationException);
        }
        GirlEntity girl = GirlEntity.getByUuidForSide(minecraft.player.getPersistentID(), false);
        try {
            if (girl == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw BoyCamera.rethrow(concurrentModificationException);
        }
        try {
            if (!girl.getCurrentAction().useBoyCam) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw BoyCamera.rethrow(concurrentModificationException);
        }
        try {
            if (girl.m()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw BoyCamera.rethrow(concurrentModificationException);
        }
        try {
            this.PlayerPos = minecraft.player.getPositionVector();
            this.LastTickPos = new Vec3d(minecraft.player.lastTickPosX, minecraft.player.lastTickPosY, minecraft.player.lastTickPosZ);
            vec3d = girl.Q() ? girl.getModelBone("boyCam").add(girl.getTargetPos()) : girl.getModelBone("boyCam").add(LerpMath.lerpVec3d(new Vec3d(girl.lastTickPosX, girl.lastTickPosY, girl.lastTickPosZ), girl.getPositionVector(), (double)renderTickEvent.renderTickTime));
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw BoyCamera.rethrow(concurrentModificationException);
        }
        Vec3d vec3d2 = vec3d;
        minecraft.player.posX = vec3d2.x;
        minecraft.player.posY = vec3d2.y - (double)minecraft.player.getEyeHeight();
        minecraft.player.posZ = vec3d2.z;
        minecraft.player.lastTickPosX = vec3d2.x;
        minecraft.player.lastTickPosY = vec3d2.y - (double)minecraft.player.getEyeHeight();
        minecraft.player.lastTickPosZ = vec3d2.z;
    }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
