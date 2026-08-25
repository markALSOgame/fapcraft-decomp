/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class BeePlayer
extends PlayerGirlEntity {
    protected BeePlayer(World world) {
        super(world);
    }

    public BeePlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public void B_() {
        this.c(true);
    }

    @Override
    public void y() {
        this.c(false);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.4f;
    }

    public float getEyeHeight() {
        return 1.3f;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube3();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/bee/hand.png";
    }

    @Override
    public void startAction(String string, UUID uUID) {
        this.a(0, GirlAnimationState.CITIZEN_START);
        this.setOutfitIndex(0);
        this.setCurrentAction(GirlAnimationState.CITIZEN_START);
        this.b(uUID);
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeePlayer.rethrow(runtimeException);
        }
        Vec3d vec3d = this.a(-0.2);
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public boolean canInteract(EntityPlayer entityPlayer) {
        BeePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.sex"}, false);
        return true;
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block6: {
            try {
                block7: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.CITIZEN_CUM) break block6;
                            if (girlAnimationState == GirlAnimationState.CITIZEN_FAST) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BeePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.COWGIRLSLOW) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw BeePlayer.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public boolean v() {
        return false;
    }

    @Override
    protected GirlAnimationState getFollowUpAction(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.CITIZEN_SLOW) {
                return GirlAnimationState.CITIZEN_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeePlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState nextAnimationState(GirlAnimationState girlAnimationState) {
        block4: {
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CITIZEN_FAST && girlAnimationState != GirlAnimationState.CITIZEN_SLOW) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw BeePlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.CITIZEN_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw BeePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void noop() {
        super.noop();
        this.setOutfitIndex(1);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        block4 : switch (animEvent.getController().getName()) {
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.bee.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.bee.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.createAnimationOnce("animation.bee.null", false, animEvent);
                        break block4;
                    }
                    case CITIZEN_START: {
                        this.createAnimationOnce("animation.bee.sex_start", false, animEvent);
                        break block4;
                    }
                    case CITIZEN_SLOW: {
                        this.createAnimationOnce("animation.bee.sex_slow", true, animEvent);
                        break block4;
                    }
                    case CITIZEN_FAST: {
                        this.createAnimationOnce("animation.bee.sex_fast", true, animEvent);
                        break block4;
                    }
                    case CITIZEN_CUM: {
                        this.createAnimationOnce("animation.bee.sex_cum", false, animEvent);
                        break block4;
                    }
                    case THROW_PEARL: {
                        this.createAnimationOnce("animation.bee.throw_pearl", true, animEvent);
                        break block4;
                    }
                    case ATTACK: {
                        this.createAnimationOnce("animation.bee.attack" + this.S, false, animEvent);
                        break block4;
                    }
                    case BOW: {
                        this.createAnimationOnce("animation.bee.bowcharge", false, animEvent);
                        break block4;
                    }
                    case RIDE: {
                        this.createAnimationOnce("animation.bee.ride", true, animEvent);
                    }
                }
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.canStartInteraction();
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeePlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "pearl": {
                    if (!this.isLocalPlayerNearby() || this.getCurrentAction() != GirlAnimationState.THROW_PEARL) break;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "resetCumPercentage": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "sex_fastMSG1": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "sex_startMSG1": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "sex_fastReady": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "sex_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                }
                case "sex_startDone": {
                    this.setCurrentAction(GirlAnimationState.CITIZEN_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "sex_cumMSG1": {
                    this.playSoundAtVolume(ModSounds.pickRandomSound(ModSounds.MISC_CUMINFLATION), 2.0f);
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    break;
                }
                case "blackscreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "sex_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                }
            }
        };
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

