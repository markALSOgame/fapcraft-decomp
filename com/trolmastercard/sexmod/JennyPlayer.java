/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import java.util.Random;

public class JennyPlayer
extends PlayerGirlEntity {
    boolean ap = false;
    boolean ar = false;
    int aq = 0;
    boolean as = false;

    protected JennyPlayer(World world) {
        super(world);
    }

    public JennyPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.75f;
    }

    @Override
    public float T() {
        return 35.0f;
    }

    @Override
    public float ai() {
        return 140.0f;
    }

    public float getEyeHeight() {
        return 1.64f;
    }

    @Override
    public void u_() {
        this.setCurrentAction(GirlAnimationState.STARTDOGGY);
        this.DataManager.set(GirlEntity.OutfitIndexKey, 0);
        this.AimYaw = ((Float)this.DataManager.get(GirlEntity.RotationYawKey)).floatValue();
    }

    @Override
    public boolean A_() {
        return false;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube1();
    }

    @Override
    public String getHandTexture(int i) {
        try {
            if (i == 0) {
                return "textures/entity/jenny/hand_nude.png";
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyPlayer.rethrow(runtimeException);
        }
        return "textures/entity/jenny/hand.png";
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("action.names.boobjob".equals(string)) {
                this.DataManager.set(GirlEntity.OutfitIndexKey, 0);
                this.setCurrentAction(GirlAnimationState.PAIZURI_START);
                this.a(0, GirlAnimationState.PAIZURI_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyPlayer.rethrow(runtimeException);
        }
        try {
            if ("action.names.blowjob".equals(string)) {
                this.setCurrentAction(GirlAnimationState.STARTBLOWJOB);
                this.a(this.getOutfitIndex(), GirlAnimationState.PAIZURI_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void updateAITasks() {
        block7: {
            super.updateAITasks();
            if (this.getCurrentAction() == GirlAnimationState.WAITDOGGY) {
                EntityPlayer entityPlayer;
                block8: {
                    entityPlayer = this.j();
                    try {
                        try {
                            try {
                                if (entityPlayer == null || !(entityPlayer.getDistance(this.w().x, this.w().y, this.w().z) < 1.0)) break block7;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyPlayer.rethrow(runtimeException);
                            }
                            if (!this.c(entityPlayer.getPersistentID())) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                        entityPlayer.sendMessage((ITextComponent)new TextComponentString(TextFormatting.DARK_PURPLE + "sowy no lesbo action yet uwu"));
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                this.hasGirl(entityPlayer.getPersistentID());
                entityPlayer.setPositionAndUpdate(this.getPositionVector().x, this.w().y, this.getPositionVector().z);
                this.teleportServerPlayerInFront((EntityPlayerMP)entityPlayer, false);
                entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
                entityPlayer.capabilities.isFlying = true;
                this.world.getPlayerEntityByUUID((UUID)this.getBoundPlayerUuid()).capabilities.isFlying = true;
                this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
                this.AimTarget = null;
                this.setCurrentAction(GirlAnimationState.DOGGYSTART);
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
            }
        }
    }

    @Override
    public boolean canInteract(EntityPlayer entityPlayer) {
        GirlEntity.openActionMenu(entityPlayer, this, new String[]{"action.names.blowjob", "action.names.boobjob"}, false);
        return true;
    }

    @Override
    protected GirlAnimationState getFollowUpAction(GirlAnimationState girlAnimationState) {
        switch (girlAnimationState) {
            case SUCKBLOWJOB: {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
            case DOGGYSLOW: {
                return GirlAnimationState.DOGGYFAST;
            }
            case PAIZURI_SLOW: {
                if (this.as) {
                    this.as = false;
                    this.a(0.0, 0.0, 0.0, 0.0f, 70.0f);
                }
                return GirlAnimationState.PAIZURI_FAST;
            }
        }
        return null;
    }

    @Override
    protected GirlAnimationState nextAnimationState(GirlAnimationState girlAnimationState) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB && girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                        this.a(0.0, 0.0, 0.0, 0.0f, 70.0f);
                        return GirlAnimationState.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.DOGGYSLOW && girlAnimationState != GirlAnimationState.DOGGYFAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.DOGGYCUM;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.PAIZURI_FAST && girlAnimationState != GirlAnimationState.PAIZURI_SLOW) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.PAIZURI_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw JennyPlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block22: {
            GirlAnimationState girlAnimationState2;
            block20: {
                block18: {
                    girlAnimationState2 = this.getCurrentAction();
                    try {
                        block19: {
                            try {
                                try {
                                    if (girlAnimationState2 != GirlAnimationState.DOGGYCUM) break block18;
                                    if (girlAnimationState == GirlAnimationState.DOGGYSLOW) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw JennyPlayer.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.DOGGYFAST) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyPlayer.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.CUMBLOWJOB) break block20;
                                if (girlAnimationState == GirlAnimationState.THRUSTBLOWJOB) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayer.rethrow(runtimeException);
                }
            }
            try {
                block23: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.PAIZURI_CUM) break block22;
                            if (girlAnimationState == GirlAnimationState.PAIZURI_SLOW) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.PAIZURI_FAST) break block22;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw JennyPlayer.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.createAnimationOnce("animation.jenny.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.jenny.fhappy", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.jenny.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.createAnimationOnce("animation.jenny.sit", true, animEvent);
                    break;
                }
                if (this.MovementController.getCurrentAnimation() != null && this.MovementController.getCurrentAnimation().animationName.contains("fly") && this.af) {
                    boolean bl = this.ap = !this.ap;
                }
                if (!this.af) {
                    this.createAnimationOnce("animation.jenny.fly" + (this.ap ? "2" : ""), true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) > 0.0f) {
                    if (this.aj) {
                        this.MovementController.setAnimationSpeed(1.2f);
                        this.createAnimationOnce("animation.jenny.run", true, animEvent);
                        break;
                    }
                    if (this.ao.y >= -0.1f) {
                        this.MovementController.setAnimationSpeed(1.5);
                        this.createAnimationOnce("animation.jenny.fastwalk", true, animEvent);
                        break;
                    }
                    this.MovementController.setAnimationSpeed(1.2f);
                    this.createAnimationOnce("animation.jenny.backwards_walk", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.jenny.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.createAnimationOnce("animation.jenny.null", true, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.createAnimationOnce("animation.jenny.strip", false, animEvent);
                        break block5;
                    }
                    case PAYMENT: {
                        this.createAnimationOnce("animation.jenny.payment", false, animEvent);
                        break block5;
                    }
                    case STARTBLOWJOB: {
                        this.createAnimationOnce("animation.jenny.blowjobintro", false, animEvent);
                        break block5;
                    }
                    case SUCKBLOWJOB: {
                        this.createAnimationOnce("animation.jenny.blowjobsuck", true, animEvent);
                        break block5;
                    }
                    case THRUSTBLOWJOB: {
                        this.createAnimationOnce("animation.jenny.blowjobthrust", true, animEvent);
                        break block5;
                    }
                    case CUMBLOWJOB: {
                        this.createAnimationOnce("animation.jenny.blowjobcum", false, animEvent);
                        break block5;
                    }
                    case STARTDOGGY: {
                        this.createAnimationOnce("animation.jenny.doggygoonbed", false, animEvent);
                        break block5;
                    }
                    case WAITDOGGY: {
                        this.createAnimationOnce("animation.jenny.doggywait", true, animEvent);
                        break block5;
                    }
                    case DOGGYSTART: {
                        this.createAnimationOnce("animation.jenny.doggystart", false, animEvent);
                        break block5;
                    }
                    case DOGGYSLOW: {
                        this.createAnimationOnce("animation.jenny.doggyslow", true, animEvent);
                        break block5;
                    }
                    case DOGGYFAST: {
                        this.createAnimationOnce("animation.jenny.doggyfast_" + (this.ar ? "hard" : "soft"), true, animEvent);
                        break block5;
                    }
                    case DOGGYCUM: {
                        this.createAnimationOnce("animation.jenny.doggycum", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.createAnimationOnce("animation.jenny.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.createAnimationOnce("animation.jenny.bowcharge", false, animEvent);
                        break block5;
                    }
                    case RIDE: {
                        this.createAnimationOnce("animation.jenny.ride", true, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.createAnimationOnce("animation.jenny.sit", true, animEvent);
                        break block5;
                    }
                    case THROW_PEARL: {
                        this.createAnimationOnce("animation.jenny.throwpearl", false, animEvent);
                        break block5;
                    }
                    case DOWNED: {
                        this.createAnimationOnce("animation.jenny.downed", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_START: {
                        this.createAnimationOnce("animation.jenny.paizuri_start", false, animEvent);
                        break block5;
                    }
                    case PAIZURI_SLOW: {
                        this.createAnimationOnce("animation.jenny.paizuri_slow", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_FAST: {
                        this.createAnimationOnce("animation.jenny.paizuri_fast", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_CUM: {
                        this.createAnimationOnce("animation.jenny.paizuri_cum", false, animEvent);
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
            throw JennyPlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            block68 : switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "stripMSG1": {
                    this.h("Hihi~");
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_GIGGLE));
                    break;
                }
                case "paymentMSG1": {
                    this.h("Huh?");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_HUH[1]);
                    break;
                }
                case "paymentMSG2": {
                    this.playSoundAtVolume(ModSounds.MISC_PLOB[0], 0.5f);
                    String string = "<" + Minecraft.getMinecraft().player.getName() + "> ";
                    switch (this.DataManager.get(GirlEntity.BlowjobStageKey)) {
                        case "strip": {
                            this.b(string + "show Bobs and vegana pls", true);
                            break block68;
                        }
                        case "blowjob": {
                            this.b(string + "Give me the sucky sucky and these are yours", true);
                            break block68;
                        }
                        case "doggy": {
                            this.b(string + "Give me the sex pls :)", true);
                            break block68;
                        }
                        case "boobjob": {
                            this.b(string + "gib boba OwO", true);
                            break block68;
                        }
                    }
                    this.b(string + "sex pls", true);
                    break;
                }
                case "paymentMSG3": {
                    this.h("Hehe~");
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_GIGGLE));
                    break;
                }
                case "sexUiOn": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "paymentMSG4": {
                    this.playSoundAtVolume(ModSounds.MISC_PLOB[0], 0.25f);
                    break;
                }
                case "paymentDone": {
                    this.U();
                    break;
                }
                case "bjiMSG1": {
                    this.h("What are you...");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_MMM[8]);
                    this.AimYaw = 180.0f;
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "bjiMSG2": {
                    this.h("eh... boys...");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_LIGHTBREATHING[8]);
                    break;
                }
                case "bjiMSG3": {
                    this.h("OHOhh...!");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_AFTERSESSIONMOAN[0]);
                    break;
                }
                case "bjiMSG4": {
                    this.playSoundEvent(ModSounds.MISC_BELLJINGLE[0]);
                    break;
                }
                case "bjiMSG5": {
                    this.h("Was this really necessary?!");
                    this.playSoundAtVolume(ModSounds.GIRLS_JENNY_HMPH[1], 0.5f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "bjiMSG6": {
                    this.h("Oh~");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_LIGHTBREATHING[8]);
                    break;
                }
                case "bjiMSG7": {
                    this.h("You like it?~");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_GIGGLE[4]);
                    break;
                }
                case "bjiMSG8": {
                    this.b("<" + Minecraft.getMinecraft().player.getName() + "> Yee", true);
                    this.playSoundAtVolume(ModSounds.MISC_PLOB[0], 0.5f);
                    break;
                }
                case "bjiMSG9": {
                    this.h("Hihihi~");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_GIGGLE[2]);
                    break;
                }
                case "bjiMSG10": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.a(-0.4, -0.8, -0.2, 60.0f, -3.0f);
                    break;
                }
                case "bjiMSG11": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIPSOUND));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "bjiMSG12": {
                    if (ModConstants.Random.nextInt(5) == 0) {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_BJMOAN));
                    }
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIPSOUND));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "bjtMSG1": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MMM));
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIPSOUND));
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
                    this.ar = true;
                    break;
                }
                case "bjtReady":
                case "paizuriReady": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "bjcMSG1": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_BJMOAN[1]);
                    break;
                }
                case "bjcMSG2": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_BJMOAN[7]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.forceShowHud();
                    break;
                }
                case "bjcMSG3": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_AFTERSESSIONMOAN[1]);
                    break;
                }
                case "bjcMSG4": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_LIGHTBREATHING[0]);
                    break;
                }
                case "bjcMSG5": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_LIGHTBREATHING[1]);
                    break;
                }
                case "bjcMSG6": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_LIGHTBREATHING[2]);
                    break;
                }
                case "bjcMSG7": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_LIGHTBREATHING[3]);
                    break;
                }
                case "bjcBlackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "bjcDone":
                case "paizuri_cumDone":
                case "doggyCumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                    break;
                }
                case "doggyGoOnBedMSG1": {
                    this.playSoundEvent(ModSounds.MISC_BEDRUSTLE[0]);
                    this.AimYaw = this.rotationYaw;
                    break;
                }
                case "doggyGoOnBedMSG2": {
                    this.a("what are you waiting for?~");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_LIGHTBREATHING[9]);
                    break;
                }
                case "doggyGoOnBedMSG3": {
                    this.a("this ass ain't gonna fuck itself...");
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_GIGGLE[0]);
                    break;
                }
                case "doggyGoOnBedMSG4": {
                    this.playSoundAtVolume(ModSounds.MISC_SLAP[0], 0.75f);
                    break;
                }
                case "doggyGoOnBedDone": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSetPlayerForGirl(this.getGirlUuid(), Minecraft.getMinecraft().player.getPersistentID()));
                    this.setCurrentAction(GirlAnimationState.WAITDOGGY);
                    break;
                }
                case "doggystartMSG1": {
                    this.playSoundEvent(ModSounds.MISC_TOUCH[0]);
                    break;
                }
                case "doggystartMSG2": {
                    this.playSoundEvent(ModSounds.MISC_TOUCH[1]);
                    break;
                }
                case "doggystartMSG3": {
                    this.playSoundAtVolume(ModSounds.MISC_BEDRUSTLE[1], 0.5f);
                    break;
                }
                case "doggystartMSG4": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.MISC_SMALLINSERTS));
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_MMM[1]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "doggystartMSG5": {
                    this.playSoundAtVolume(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.33f);
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                    break;
                }
                case "doggystartDone": {
                    this.setCurrentAction(GirlAnimationState.DOGGYSLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "doggyslowMSG1": {
                    this.ar = false;
                    this.playSoundAtVolume(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.33f);
                    int n = ModConstants.Random.nextInt(4);
                    if (n == 0) {
                        n = ModConstants.Random.nextInt(2);
                        if (n == 0) {
                            this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MMM));
                        } else {
                            this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                        }
                    } else {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_HEAVYBREATHING));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.00666);
                    break;
                }
                case "doggyslowMSG2": {
                    this.playSoundAtVolume(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIGHTBREATHING), 0.5f);
                    break;
                }
                case "doggyfastMSG1": {
                    this.playSoundAtVolume(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                    ++this.aq;
                    if (this.aq % 2 == 0) {
                        int n = ModConstants.Random.nextInt(2);
                        if (n == 0) {
                            this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                            break;
                        }
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_HEAVYBREATHING));
                        break;
                    }
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_AHH));
                    break;
                }
                case "doggyfastDone": {
                    this.ar = false;
                    this.setCurrentAction(GirlAnimationState.DOGGYSLOW);
                    break;
                }
                case "doggycumMSG1": {
                    this.playSoundAtVolume(ModSounds.MISC_CUMINFLATION[0], 2.0f);
                    this.playSoundAtVolume(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 2.0f);
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                    break;
                }
                case "doggycumMSG2": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_HEAVYBREATHING[4]);
                    break;
                }
                case "doggycumMSG3": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_HEAVYBREATHING[5]);
                    break;
                }
                case "doggycumMSG4": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_HEAVYBREATHING[6]);
                    break;
                }
                case "doggycumMSG5": {
                    this.playSoundEvent(ModSounds.GIRLS_JENNY_HEAVYBREATHING[7]);
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "boobjob_camera": {
                    if (!this.isOwnedByLocalPlayer() || this.as) break;
                    this.as = true;
                    this.AimYaw = 180.0f;
                    this.a(-0.7, -0.6, -0.2, 60.0f, -3.0f);
                    break;
                }
                case "paizuri_startDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.setCurrentAction(GirlAnimationState.PAIZURI_SLOW);
                    GuiHud.resetProgress();
                    GuiHud.showHud();
                    break;
                }
                case "paizuriFastMSG1": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (this.getRNG().nextBoolean()) {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MMM));
                    } else {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_AHH));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "paizuriSlowMSG1":
                case "paizuriStartMSG1": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "paizuri_fastDone": {
                    this.setCurrentAction(GirlAnimationState.PAIZURI_SLOW);
                    if (!this.isOwnedByLocalPlayer() || this.as) break;
                    this.as = true;
                    this.a(-0.7, -0.6, -0.2, 60.0f, -3.0f);
                    break;
                }
                case "paizuri_startStep": {
                    IBlockState iBlockState = this.world.getBlockState(this.getPosition().subtract(new Vec3i(0, 1, 0)));
                    this.playSoundEvent(iBlockState.getBlock().getSoundType(iBlockState, this.world, this.getPosition(), this).getStepSound());
                    break;
                }
                case "paizuri_cumStart": {
                    if (!this.isOwnedByLocalPlayer() || this.as) break;
                    this.a(-0.7, -0.6, -0.2, 60.0f, -3.0f);
                }
            }
        };
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

