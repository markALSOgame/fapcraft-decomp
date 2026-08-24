/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class BiaPlayer
extends PlayerGirlEntity {
    int ActionCountdown = -1;
    boolean ap = false;
    int MotionVariant = 1;

    public BiaPlayer(World world) {
        super(world);
    }

    public BiaPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.5f;
    }

    public float getEyeHeight() {
        return 1.5f;
    }

    @Override
    public void u() {
    }

    @Override
    public boolean startActionByKey(String string) {
        try {
            if ("anal".equals(string)) {
                this.b(GirlAnimationState.ANAL_PREPARE);
                this.f(0);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        try {
            if ("doggy".equals(string)) {
                this.b(GirlAnimationState.SITDOWN);
                this.f(0);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void openActionMenu() {
        GirlEntity.openActionMenu((EntityPlayer)Minecraft.getMinecraft().player, this, new String[]{"anal", "doggy"}, false);
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("action.names.headpat".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.HEAD_PAT);
                this.a(this.getOutfitIndex(), GirlAnimationState.HEAD_PAT);
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube9();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/bia/hand.png";
    }

    @Override
    public float getCameraPitch() {
        return 35.0f;
    }

    @Override
    public float getCameraYaw() {
        return 140.0f;
    }

    @Override
    public boolean isMenuOpenable() {
        return false;
    }

    @Override
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        GirlEntity.openActionMenu(entityPlayer, this, new String[]{"action.names.headpat"}, false);
        return true;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block14: {
            block12: {
                try {
                    block13: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.ANAL_CUM) break block12;
                                if (girlAnimationState == GirlAnimationState.ANAL_FAST) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw BiaPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.ANAL_SLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BiaPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.PRONE_DOGGY_CUM) break block14;
                            if (girlAnimationState == GirlAnimationState.PRONE_DOGGY_HARD) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BiaPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.PRONE_DOGGY_SOFT) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw BiaPlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.ANAL_SLOW) {
                return GirlAnimationState.ANAL_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.PRONE_DOGGY_INTRO) {
                return GirlAnimationState.PRONE_DOGGY_INSERT;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.ANAL_SLOW && girlAnimationState != GirlAnimationState.ANAL_FAST) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.ANAL_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.PRONE_DOGGY_SOFT && girlAnimationState != GirlAnimationState.PRONE_DOGGY_HARD) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.PRONE_DOGGY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw BiaPlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.a();
    }

    @Override
    protected void resetAction() {
        super.resetAction();
        this.ActionCountdown = -1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isLocalPlayer(EntityPlayer entityPlayer) {
        return Minecraft.getMinecraft().player.getPersistentID().equals(entityPlayer.getPersistentID());
    }

    void a() {
        float f2;
        EntityPlayer entityPlayer;
        block35: {
            block37: {
                block36: {
                    GirlAnimationState girlAnimationState;
                    block32: {
                        block34: {
                            block33: {
                                block31: {
                                    block30: {
                                        girlAnimationState = this.getCurrentAction();
                                        try {
                                            try {
                                                if (girlAnimationState == GirlAnimationState.ANAL_WAIT || girlAnimationState == GirlAnimationState.SITDOWNIDLE) break block30;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw BiaPlayer.rethrow(runtimeException);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw BiaPlayer.rethrow(runtimeException);
                                        }
                                    }
                                    entityPlayer = this.getRenderPosition();
                                    try {
                                        if (entityPlayer == null) {
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                    try {
                                        if (entityPlayer.getDistance((Entity)this) > 1.0f) {
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                    try {
                                        try {
                                            if (!this.world.isRemote || this.isLocalPlayer(entityPlayer)) break block31;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw BiaPlayer.rethrow(runtimeException);
                                        }
                                        return;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (this.ActionCountdown != -1) break block32;
                                        if (!this.world.isRemote) break block33;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                    GuiTransitionScreen.startTransition();
                                    AnimationInputLock.setAnimationLocked(false);
                                    break block34;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw BiaPlayer.rethrow(runtimeException);
                                }
                            }
                            this.hasGirl(entityPlayer.getPersistentID());
                        }
                        this.ActionCountdown = GirlEntity.j;
                        return;
                    }
                    try {
                        if (--this.ActionCountdown > 0) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                    try {
                        this.ActionCountdown = -1;
                        entityPlayer.noClip = true;
                        entityPlayer.setNoGravity(true);
                        if (girlAnimationState != GirlAnimationState.ANAL_WAIT) break block35;
                        if (this.world.isRemote) break block36;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                    this.b(GirlAnimationState.ANAL_START);
                    Vec3d vec3d = this.getTargetPos().add(VectorMath.rotatePitch(-0.3, -1.0, -0.5, this.I().floatValue()));
                    entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
                    break block37;
                }
                try {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.showHud();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
            }
            return;
        }
        entityPlayer.rotationYaw = f2 = this.I().floatValue();
        entityPlayer.rotationPitch = 60.0f;
        if (!this.world.isRemote) {
            this.f(0);
            this.b(GirlAnimationState.PRONE_DOGGY_INTRO);
            Vec3d vec3d = this.getTargetPos();
            Vec3d vec3d2 = vec3d.add(VectorMath.rotatePitch(0.0, 0.0, 1.0, f2));
            this.c(vec3d2);
            EntityPlayer entityPlayer2 = this.getBoundPlayer();
            try {
                if (entityPlayer2 != null) {
                    entityPlayer2.setPositionAndUpdate(vec3d2.x, vec3d2.y, vec3d2.z);
                }
            }
            catch (RuntimeException runtimeException) {
                throw BiaPlayer.rethrow(runtimeException);
            }
            Vec3d vec3d3 = vec3d.add(VectorMath.rotatePitch(0.0, 1.1875 - (double)entityPlayer.getEyeHeight(), 0.5, f2));
            entityPlayer.setPositionAndUpdate(vec3d3.x, vec3d3.y, vec3d3.z);
            this.a(true);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ag() {
        try {
            super.resetTickOffset();
            if (this.getCurrentAction() != GirlAnimationState.PRONE_DOGGY_HARD) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        int i = this.MotionVariant;
        try {
            do {
                this.MotionVariant = this.getRNG().nextInt(3) + 1;
            } while (i == this.MotionVariant);
            return;
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.a("animation.bia.null", true, animEvent);
                    break;
                }
                this.a("animation.bia.fhappy", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.a("animation.bia.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.a("animation.bia.sit", true, animEvent);
                    break;
                }
                if (this.MovementController.getCurrentAnimation() != null && this.MovementController.getCurrentAnimation().animationName.contains("fly") && this.af) {
                    boolean bl = this.ap = !this.ap;
                }
                if (!this.af) {
                    this.a("animation.bia.fly" + (this.ap ? "2" : ""), true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) > 0.0f) {
                    if (this.aj) {
                        this.MovementController.setAnimationSpeed(1.2);
                        this.a("animation.bia.run", true, animEvent);
                        break;
                    }
                    if (this.ao.y >= -0.1f) {
                        this.MovementController.setAnimationSpeed(1.2);
                        this.a("animation.bia.fastwalk", true, animEvent);
                        break;
                    }
                    this.MovementController.setAnimationSpeed(1.2);
                    this.a("animation.bia.backwards_walk", true, animEvent);
                    break;
                }
                this.a("animation.bia.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.a("animation.bia.null", true, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.a("animation.bia.strip", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.a("animation.bia.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.a("animation.bia.bowcharge", false, animEvent);
                        break block5;
                    }
                    case RIDE: {
                        this.a("animation.bia.ride", true, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.a("animation.bia.sit", true, animEvent);
                        break block5;
                    }
                    case THROW_PEARL: {
                        this.a("animation.bia.throwpearl", false, animEvent);
                        break block5;
                    }
                    case DOWNED: {
                        this.a("animation.bia.downed", true, animEvent);
                        break block5;
                    }
                    case TALK_HORNY: {
                        this.a("animation.bia.talk_horny", false, animEvent);
                        break block5;
                    }
                    case TALK_IDLE: {
                        this.a("animation.bia.talk_idle", true, animEvent);
                        break block5;
                    }
                    case TALK_RESPONSE: {
                        this.a("animation.bia.talk_response", true, animEvent);
                        break block5;
                    }
                    case ANAL_PREPARE: {
                        this.a("animation.bia.anal_prepare", false, animEvent);
                        break block5;
                    }
                    case ANAL_WAIT: {
                        this.a("animation.bia.anal_wait", true, animEvent);
                        break block5;
                    }
                    case ANAL_START: {
                        this.a("animation.bia.anal_start", true, animEvent);
                        break block5;
                    }
                    case ANAL_SLOW: {
                        this.a("animation.bia.anal_slow", true, animEvent);
                        break block5;
                    }
                    case ANAL_FAST: {
                        this.a("animation.bia.anal_fast", true, animEvent);
                        break block5;
                    }
                    case ANAL_CUM: {
                        this.a("animation.bia.anal_cum", false, animEvent);
                        break block5;
                    }
                    case HEAD_PAT: {
                        this.a("animation.bia.headpat", false, animEvent);
                        break block5;
                    }
                    case SITDOWN: {
                        this.a("animation.bia.sitdown", false, animEvent);
                        break block5;
                    }
                    case SITDOWNIDLE: {
                        this.a("animation.bia.sitdownidle", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_INTRO: {
                        this.a("animation.bia.prone_doggy_intro", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_INSERT: {
                        this.a("animation.bia.prone_doggy_insert", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_SOFT: {
                        this.a("animation.bia.prone_doggy_soft", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_HARD: {
                        this.a("animation.bia.prone_doggy_hard" + this.MotionVariant, true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_CUM: {
                        this.a("animation.bia.prone_doggy_cum", true, animEvent);
                    }
                }
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.canStartInteraction();
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "stripMSG1": {
                    this.h("Hihi~");
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_BIA_GIGGLE));
                    break;
                }
                case "sexUiOn": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "talk_hornyMSG1": {
                    this.a("Heyaaa~");
                    this.a(ModSounds.GIRLS_BIA_HEY[3]);
                    break;
                }
                case "talk_hornyMSG2": {
                    this.a("I am Hornyyyyy~");
                    this.a(ModSounds.GIRLS_BIA_GIGGLE[2]);
                    break;
                }
                case "talk_hornyMSG3": {
                    this.a("So...");
                    this.a(ModSounds.GIRLS_BIA_BREATH[0]);
                    break;
                }
                case "talk_hornyMSG4": {
                    this.a("Are we gonna have some fun nyaa?");
                    this.a(ModSounds.GIRLS_BIA_HUH[0]);
                    break;
                }
                case "talk_responseMSG1": {
                    this.a("Huh?!...");
                    this.a(ModSounds.GIRLS_BIA_HUH[2]);
                    break;
                }
                case "talk_responseMSG2": {
                    this.a("I... uhm...");
                    this.a(ModSounds.GIRLS_BIA_BREATH[1]);
                    break;
                }
                case "talk_responseMSG3": {
                    this.a("yes~");
                    this.a(ModSounds.GIRLS_BIA_GIGGLE[0]);
                    break;
                }
                case "talk_responseDone": {
                    this.s();
                    if (this.getOutfitIndex() != 0) {
                        this.b(GirlAnimationState.STRIP);
                        break;
                    }
                    this.U();
                    break;
                }
                case "anal_prepareMSG1": {
                    this.a(ModSounds.MISC_PLOB[0]);
                    break;
                }
                case "anal_prepareMSG2": {
                    this.a(ModSounds.MISC_BEDRUSTLE[0]);
                    break;
                }
                case "anal_prepareDone": {
                    this.b(GirlAnimationState.ANAL_WAIT);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "anal_startMSG1": {
                    this.a(ModSounds.GIRLS_BIA_MMM[3]);
                    this.a(ModSounds.MISC_POUNDING[34]);
                    break;
                }
                case "anal_fastMSG1": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.5f);
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_BIA_AHH));
                    break;
                }
                case "anal_slowMSG1":
                case "anal_startMSG2": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.5f);
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_BIA_AHH));
                    break;
                }
                case "anal_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                }
                case "anal_startDone": {
                    this.b(GirlAnimationState.ANAL_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "anal_cumMSG2": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_BIA_AHH));
                    break;
                }
                case "anal_cumBlackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "doggy_cumDone":
                case "anal_cumDone": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.resetProgress();
                    }
                    this.resetAimTarget();
                    break;
                }
                case "headpatMSG1": {
                    this.a("Ooh headpats!");
                    this.a(ModSounds.GIRLS_BIA_BREATH[0]);
                    break;
                }
                case "headpatMSG2": {
                    this.a("Hmmm.... :D");
                    this.a(ModSounds.GIRLS_BIA_MMM[0]);
                    break;
                }
                case "headpatMSG3": {
                    this.a("huh...?");
                    this.a(ModSounds.GIRLS_BIA_HUH[0]);
                    break;
                }
                case "headpatMSG4": {
                    this.a("Tanku hehe");
                    this.a(ModSounds.GIRLS_BIA_GIGGLE[1]);
                    break;
                }
                case "headpatDone": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.resetAimTarget();
                    break;
                }
                case "sitdownMSG1": {
                    this.a("come here big boy~");
                    this.a(ModSounds.GIRLS_BIA_BREATH);
                    break;
                }
                case "sitdownDone": {
                    this.b(GirlAnimationState.SITDOWNIDLE);
                    break;
                }
                case "slide": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_SLIDE));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.005);
                    break;
                }
                case "pound": {
                    this.a(ModSounds.MISC_POUNDING);
                    break;
                }
                case "doggyMoan": {
                    this.a(ModSounds.pickRandomSound(this.getRNG().nextBoolean() ? ModSounds.GIRLS_BIA_AHH : ModSounds.GIRLS_BIA_MMM));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "doggySwitch": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.PRONE_DOGGY_HARD);
                    break;
                }
                case "doggyReset": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_INSERTS, 6.0f);
                    break;
                }
                case "orgasm1": {
                    this.a(ModSounds.GIRLS_BIA_MMM[6]);
                    break;
                }
                case "orgasm2": {
                    this.a(ModSounds.GIRLS_BIA_MMM[7]);
                    break;
                }
                case "openSexUI": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                }
            }
        };
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
        animationData.addAnimationController(this.ActionController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

