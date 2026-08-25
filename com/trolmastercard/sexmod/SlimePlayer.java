/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class SlimePlayer
extends PlayerGirlEntity {
    boolean ap = false;
    int aq = 0;

    protected SlimePlayer(World world) {
        super(world);
    }

    public SlimePlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.6f;
    }

    public float getEyeHeight() {
        return 1.64f;
    }

    @Override
    public boolean allowsBedInteraction() {
        return false;
    }

    @Override
    public boolean suppressInventoryStripButton() {
        return false;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube5();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/slime/hand.png";
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("action.names.blowjob".equals(string)) {
                this.a(0, GirlAnimationState.SUCKBLOWJOB);
                this.setCurrentAction(GirlAnimationState.SUCKBLOWJOB);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        SlimePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.blowjob"}, false);
        return true;
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block14: {
            block12: {
                try {
                    block13: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.CUMBLOWJOB) break block12;
                                if (girlAnimationState == GirlAnimationState.THRUSTBLOWJOB) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimePlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.DOGGYCUM) break block14;
                            if (girlAnimationState == GirlAnimationState.DOGGYFAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.DOGGYSLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw SlimePlayer.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    @Override
    protected GirlAnimationState getFollowUpAction(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB) {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.DOGGYSLOW) {
                return GirlAnimationState.DOGGYFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState nextAnimationState(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB && girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimePlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.CUMBLOWJOB;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.DOGGYSLOW && girlAnimationState != GirlAnimationState.DOGGYFAST) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.DOGGYCUM;
            }
            catch (RuntimeException runtimeException) {
                throw SlimePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void updateAITasks() {
        try {
            super.updateAITasks();
            if (this.getCurrentAction() != GirlAnimationState.WAITDOGGY) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.getRenderPosition();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        try {
            if (entityPlayer.getPositionVector().distanceTo(this.getCustomName()) > 1.0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
        this.hasGirl(entityPlayer.getPersistentID());
        entityPlayer.rotationYaw = this.I().floatValue();
        this.AimYaw = this.I().floatValue();
        entityPlayer.setPosition(this.getCustomName().x, this.getCustomName().y, this.getCustomName().z);
        entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
        this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
        this.setCurrentAction(GirlAnimationState.DOGGYSTART);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        EntityPlayer entityPlayer2 = this.world.getPlayerEntityByUUID(this.getBoundPlayerUuid());
        entityPlayer2.setNoGravity(true);
        entityPlayer.noClip = true;
        entityPlayer.capabilities.isFlying = true;
        entityPlayer2.capabilities.isFlying = true;
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() == GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.createAnimationOnce("animation.slime.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.slime.fhappy", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.slime.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.createAnimationOnce("animation.slime.sit", true, animEvent);
                    break;
                }
                if (this.MovementController.getCurrentAnimation() != null && this.MovementController.getCurrentAnimation().animationName.contains("fly") && this.af) {
                    boolean bl = this.ap = !this.ap;
                }
                if (!this.af) {
                    this.createAnimationOnce("animation.slime.fly" + (this.ap ? "2" : ""), true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) > 0.0f) {
                    if (this.aj) {
                        this.createAnimationOnce("animation.slime.run", true, animEvent);
                        break;
                    }
                    if (this.ao.y >= -0.1f) {
                        this.createAnimationOnce("animation.slime.walk", true, animEvent);
                        break;
                    }
                    this.createAnimationOnce("animation.slime.backwards_walk", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.slime.idle", true, animEvent);
                break;
            }
            case "action": {
                if (this.getCurrentAction() == GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.slime.null", true, animEvent);
                    break;
                }
                switch (this.getCurrentAction()) {
                    case UNDRESS: {
                        this.createAnimationOnce("animation.slime.undress", false, animEvent);
                        break block5;
                    }
                    case DRESS: {
                        this.createAnimationOnce("animation.slime.dress", false, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.createAnimationOnce("animation.slime.strip", false, animEvent);
                        break block5;
                    }
                    case SUCKBLOWJOB: {
                        this.createAnimationOnce("animation.slime.blowjobsuck", true, animEvent);
                        break block5;
                    }
                    case THRUSTBLOWJOB: {
                        this.createAnimationOnce("animation.slime.blowjobthrust", true, animEvent);
                        break block5;
                    }
                    case CUMBLOWJOB: {
                        this.createAnimationOnce("animation.slime.blowjobcum", false, animEvent);
                        break block5;
                    }
                    case STARTDOGGY: {
                        this.createAnimationOnce("animation.slime.doggygoonbed", false, animEvent);
                        break block5;
                    }
                    case WAITDOGGY: {
                        this.createAnimationOnce("animation.slime.doggywait", true, animEvent);
                        break block5;
                    }
                    case DOGGYSTART: {
                        this.createAnimationOnce("animation.slime.doggystart", false, animEvent);
                        break block5;
                    }
                    case DOGGYSLOW: {
                        this.createAnimationOnce("animation.slime.doggyslow", true, animEvent);
                        break block5;
                    }
                    case DOGGYFAST: {
                        this.createAnimationOnce("animation.slime.doggyfast", true, animEvent);
                        break block5;
                    }
                    case DOGGYCUM: {
                        this.createAnimationOnce("animation.slime.doggycum", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.createAnimationOnce("animation.slime.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.createAnimationOnce("animation.slime.bowcharge", false, animEvent);
                        break block5;
                    }
                    case RIDE: {
                        this.createAnimationOnce("animation.slime.ride", true, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.createAnimationOnce("animation.slime.sit", true, animEvent);
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
            throw SlimePlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            String string;
            switch (string = arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "undress": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.DataManager.set(GirlEntity.OutfitIndexKey, (Object)0);
                    this.resetAimTarget();
                    break;
                }
                case "dress": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.DataManager.set(GirlEntity.OutfitIndexKey, (Object)1);
                    this.setCurrentAction((GirlAnimationState)null);
                    this.resetAimTarget();
                    break;
                }
                case "sexUiOn": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "bjiMSG10": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.a(-0.4, -0.8, -0.2, 60.0f, -3.0f);
                    break;
                }
                case "bjiMSG11": {
                    this.a(SoundEvents.ENTITY_SLIME_SQUISH, 0.5f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "bjiMSG12": {
                    if (ModConstants.Random.nextInt(5) == 0) {
                        this.a(SoundEvents.ENTITY_SLIME_JUMP, 0.5f);
                    }
                    this.a(SoundEvents.ENTITY_SLIME_SQUISH, 0.5f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "bjtMSG1": {
                    this.a(SoundEvents.BLOCK_SLIME_HIT);
                    this.a(SoundEvents.ENTITY_SLIME_DEATH);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "bjiDone": {
                    this.setCurrentAction(GirlAnimationState.SUCKBLOWJOB);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "bjtDone": {
                    this.setCurrentAction(GirlAnimationState.SUCKBLOWJOB);
                    break;
                }
                case "doggyfastReady": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "bjtReady": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "bjcMSG1": {
                    this.a(SoundEvents.ENTITY_SLIME_JUMP);
                    break;
                }
                case "bjcMSG2": {
                    this.a(SoundEvents.ENTITY_SLIME_JUMP);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.forceShowHud();
                    break;
                }
                case "doggyslowMSG2": {
                    this.a(SoundEvents.BLOCK_SLIME_HIT);
                    break;
                }
                case "bjcBlackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "bjcDone":
                case "doggyCumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                    break;
                }
                case "doggyGoOnBedMSG1": {
                    this.a(SoundEvents.ENTITY_SLIME_SQUISH);
                    this.AimYaw = this.rotationYaw;
                    break;
                }
                case "doggyGoOnBedDone": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSetPlayerForGirl(this.getGirlUuid(), Minecraft.getMinecraft().player.getPersistentID()));
                    this.setCurrentAction(GirlAnimationState.WAITDOGGY);
                    break;
                }
                case "doggystartMSG1": {
                    this.a(ModSounds.MISC_TOUCH[0]);
                    break;
                }
                case "doggystartMSG2": {
                    this.a(ModSounds.MISC_TOUCH[1]);
                    break;
                }
                case "doggystartMSG3": {
                    this.a(SoundEvents.ENTITY_SLIME_SQUISH, 0.25f);
                    break;
                }
                case "doggystartMSG4": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_SMALLINSERTS), 1.5f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "doggystartMSG5": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.33f);
                    this.a(SoundEvents.BLOCK_SLIME_HIT);
                    break;
                }
                case "doggystartDone": {
                    this.setCurrentAction(GirlAnimationState.DOGGYSLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "doggyslowMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.33f);
                    int n = ModConstants.Random.nextInt(4);
                    if (n == 0) {
                        n = ModConstants.Random.nextInt(2);
                        if (n == 0) {
                            this.a(SoundEvents.ENTITY_SLIME_JUMP);
                        } else {
                            this.a(SoundEvents.ENTITY_SLIME_SQUISH);
                        }
                    } else {
                        this.a(SoundEvents.BLOCK_SLIME_HIT);
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.00666);
                    break;
                }
                case "doggyfastMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                    ++this.aq;
                    if (this.aq % 2 == 0) {
                        int n = ModConstants.Random.nextInt(2);
                        if (n == 0) {
                            this.a(SoundEvents.ENTITY_SLIME_JUMP);
                            break;
                        }
                        this.a(SoundEvents.ENTITY_SLIME_SQUISH);
                        break;
                    }
                    this.a(SoundEvents.BLOCK_SLIME_HIT);
                    break;
                }
                case "doggyfastDone": {
                    this.setCurrentAction(GirlAnimationState.DOGGYSLOW);
                    break;
                }
                case "doggycumMSG1": {
                    this.a(ModSounds.MISC_CUMINFLATION[0], 4.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 2.0f);
                    this.a(SoundEvents.ENTITY_SLIME_DEATH);
                }
            }
        };
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.EyesController);
        animationData.addAnimationController(this.MovementController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

