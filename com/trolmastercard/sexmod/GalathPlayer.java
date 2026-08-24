/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class GalathPlayer
extends PlayerGirlEntity
implements BoxSource {
    boolean BusyFlag = false;
    int ar = 0;
    boolean WasCorrupting = false;
    boolean aq = false;

    public GalathPlayer(World world) {
        super(world);
    }

    public GalathPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube2();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/galath/hand.png";
    }

    @Override
    @Nullable
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block7: {
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CORRUPT_FAST && girlAnimationState != GirlAnimationState.CORRUPT_SLOW) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.CORRUPT_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw GalathPlayer.rethrow(runtimeException);
            }
        }
        try {
            if (girlAnimationState == GirlAnimationState.RAPE_ON_GOING) {
                return GirlAnimationState.RAPE_CUM;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    public float getRenderLabelOffset() {
        return 2.3f;
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("cowgirl".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.RAPE_INTRO);
                this.a(this.getOutfitIndex(), GirlAnimationState.RAPE_INTRO);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        try {
            if ("mating press".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.CORRUPT_SLOW);
                this.a(this.getOutfitIndex(), GirlAnimationState.CORRUPT_SLOW);
                this.a();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block20: {
            GirlAnimationState girlAnimationState2;
            block19: {
                block17: {
                    girlAnimationState2 = this.getCurrentAction();
                    try {
                        block18: {
                            try {
                                try {
                                    if (girlAnimationState2 != GirlAnimationState.CORRUPT_CUM) break block17;
                                    if (girlAnimationState == GirlAnimationState.CORRUPT_FAST) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GalathPlayer.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.CORRUPT_SLOW) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GalathPlayer.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState2 != GirlAnimationState.RAPE_CUM || girlAnimationState != GirlAnimationState.RAPE_ON_GOING) break block19;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayer.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState2 != GirlAnimationState.RAPE_CUM || girlAnimationState != GirlAnimationState.RAPE_CUM_IDLE) break block20;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GalathPlayer.rethrow(runtimeException);
            }
        }
        try {
            if (girlAnimationState == GirlAnimationState.CORRUPT_SLOW) {
                this.WasCorrupting = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        super.b(girlAnimationState);
    }

    void a() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.5, (double)(0.5f - entityPlayer.getEyeHeight()), (double)0.4f), this.I().floatValue()).add(this.getTargetPos());
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        GalathPlayer.openActionMenu(entityPlayer, this, new String[]{"cowgirl", "mating press", "ride"}, false);
        return true;
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
    public Vec4d d() {
        return new Vec4d(0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean c() {
        boolean flag;
        block5: {
            block4: {
                try {
                    try {
                        if (this.getOutfitIndex() != 0 && !this.BusyFlag) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayer.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean a() {
        try {
            switch (this.getCurrentAction()) {
                case CORRUPT_CUM: 
                case CORRUPT_FAST: 
                case CORRUPT_SLOW: 
                case COWGIRLCUM: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void B() {
        this.c(true);
    }

    @Override
    public void onUpdate() {
        try {
            super.onUpdate();
            this.b();
            if (this.world.isRemote) {
                this.d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void d() {
        try {
            if (!this.isOwnedByLocalPlayer()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.RAPE_INTRO) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        GuiHud.showHudWithForce(false);
    }

    void b() {
        switch (this.getCurrentAction()) {
            case CORRUPT_CUM: 
            case CORRUPT_FAST: 
            case CORRUPT_SLOW: 
            case RAPE_INTRO: 
            case RAPE_ON_GOING: 
            case RAPE_CUM: 
            case RAPE_CHARGE: 
            case RAPE_CUM_IDLE: {
                this.BusyFlag = true;
                return;
            }
        }
        this.BusyFlag = false;
    }

    boolean g() {
        boolean flag;
        EntityPlayer entityPlayer = this.getBoundPlayer();
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        try {
            flag = this.world.getBlockState(entityPlayer.getPosition().up().up()).getBlock() != Blocks.AIR;
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.a("animation.galath.null", true, animEvent);
                    break;
                }
                this.a("animation.galath.blink", true, animEvent);
                break;
            }
            case "movement": {
                this.MovementController.setAnimationSpeed(1.0);
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.a("animation.galath.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.a("animation.galath.sit", true, animEvent);
                    break;
                }
                if (!this.af) {
                    this.a("animation.galath.controlled_flight", true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) == 0.0f) {
                    this.a(this.g() ? "animation.galath.crouchidle" : "animation.galath.idle", true, animEvent);
                    break;
                }
                if (this.aj) {
                    this.MovementController.setAnimationSpeed(1.5);
                    this.a(this.g() ? "animation.galath.crouchwalk" : "animation.galath.run", true, animEvent);
                    break;
                }
                if (this.ao.y >= -0.1f) {
                    this.MovementController.setAnimationSpeed(2.0);
                    this.a(this.g() ? "animation.galath.crouchwalk" : "animation.galath.walk", true, animEvent);
                    break;
                }
                this.MovementController.setAnimationSpeed(1.5);
                this.a(this.g() ? "animation.galath.crouchwalk" : "animation.galath.backwards_walk", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        return PlayState.STOP;
                    }
                    case STRIP: {
                        this.a("animation.galath.strip", true, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.a("animation.galath.attack" + this.S, true, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.a("animation.galath.bowcharge", true, animEvent);
                        break block5;
                    }
                    case RIDE: 
                    case SIT: {
                        this.a("animation.galath.sit", true, animEvent);
                        break block5;
                    }
                    case RAPE_INTRO: {
                        this.a("animation.galath.rape_intro", true, animEvent);
                        break block5;
                    }
                    case RAPE_ON_GOING: {
                        this.a("animation.galath.rape" + this.ar, true, animEvent);
                        break block5;
                    }
                    case RAPE_CUM: {
                        this.a("animation.galath.rape_cum", true, animEvent);
                        break block5;
                    }
                    case RAPE_CUM_IDLE: {
                        this.a("animation.galath.rape_cum_idle", true, animEvent);
                        break block5;
                    }
                    case CORRUPT_FAST: {
                        this.a("animation.galath.corrupt_" + (this.WasCorrupting ? "hard" : "soft"), true, animEvent);
                        break block5;
                    }
                    case CORRUPT_SLOW: {
                        this.a("animation.galath.corrupt_slow", true, animEvent);
                        break block5;
                    }
                    case CORRUPT_INTRO: {
                        this.a("animation.galath.corrupt_intro", true, animEvent);
                        break block5;
                    }
                    case CORRUPT_CUM: {
                        this.a("animation.galath.corrupt_cum", true, animEvent);
                        break block5;
                    }
                    case CONTROLLED_FLIGHT: {
                        this.a("animation.galath.controlled_flight", true, animEvent);
                    }
                }
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        this.canStartInteraction();
        this.ActionController.registerSoundListener(arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_SMALLINSERTS, 2.0f);
                    break;
                }
                case "pound": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    break;
                }
                case "flap": {
                    this.a(ModSounds.MISC_FLAP, new int[0]);
                    break;
                }
                case "setNude": {
                    this.BusyFlag = true;
                    Vec3d vec3d = this.getPositionVector();
                    Vec3d vec3d2 = this.b("slipR").add(vec3d);
                    Vec3d vec3d3 = this.b("slipL").add(vec3d);
                    Vec3d vec3d4 = this.b("turnable").add(vec3d);
                    this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, vec3d2.x, vec3d2.y, vec3d2.z, 0.0, 0.0, 0.0, new int[0]);
                    this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, vec3d3.x, vec3d3.y, vec3d3.z, 0.0, 0.0, 0.0, new int[0]);
                    this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, vec3d4.x, vec3d4.y, vec3d4.z, 0.0, 0.0, 0.0, new int[0]);
                    break;
                }
                case "rapeIntroDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.b(GirlAnimationState.RAPE_ON_GOING);
                    break;
                }
                case "rape_switch": {
                    Random random = this.getRNG();
                    int n = this.ar;
                    do {
                        this.ar = random.nextInt(3);
                    } while (this.ar == n);
                    break;
                }
                case "poundRape": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.03f);
                    break;
                }
                case "enableRapeUI": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHudWithForce(false);
                    break;
                }
                case "reloadRenderer": {
                    if (!this.isOwnedByLocalPlayer()) {
                        return;
                    }
                    Minecraft minecraft = Minecraft.getMinecraft();
                    if (minecraft.gameSettings.thirdPersonView == 0) break;
                    minecraft.renderGlobal.loadRenderers();
                    break;
                }
                case "corruptSwitch": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.CORRUPT_FAST);
                    break;
                }
                case "corrupt_hard": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.WasCorrupting = true;
                    this.N();
                    break;
                }
                case "corrupt_hard_end": {
                    this.b(GirlAnimationState.CORRUPT_SLOW);
                    this.WasCorrupting = false;
                    break;
                }
                case "addCum": {
                    GuiHud.addProgress(0.03);
                    break;
                }
                case "clearcum": {
                    GuiCumOverlay.removeParticlesForGirl(this);
                }
                case "reset": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    break;
                }
                case "setCamCorrupt": {
                    if (!this.isOwnedByLocalPlayer()) {
                        return;
                    }
                    this.aq = true;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    float f = this.I().floatValue() + 220.0f;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.5, 0.5f - entityPlayerSP.getEyeHeight(), 0.4f), this.I().floatValue()).add(this.getPositionVector());
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(entityPlayerSP.getPersistentID().toString(), vec3d, f, 15.0f));
                    GuiHud.showHud();
                    break;
                }
                case "enableBoyCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.aq = false;
                    break;
                }
                case "creampie": {
                    GuiCumOverlay.addParticles(new ParticleEmitter(130, girl -> {
                        Vec3d vec3d = girl.d("futaCockTip");
                        Vec3d vec3d2 = girl.d("futaCockTipDirHelp");
                        return vec3d.subtract(vec3d2).normalize();
                    }, girl -> girl.b("futaCockTip").add(girl.getPositionVector()), this, 0.3f, 0.3f));
                    GuiCumOverlay.addParticles(new ParticleEmitter(100, girl -> VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 0.6f), this.I().floatValue()), girl -> girl.b("creampiePos").add(girl.getPositionVector()), this, 0.6f, 0.5f));
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_SMALLINSERTS), 3.0f);
                    break;
                }
                case "blackScreenTamed":
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "flapControlled": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiGalathFlight.startFlight();
                    this.a(ModSounds.MISC_FLAP, new int[0]);
                    Minecraft minecraft = Minecraft.getMinecraft();
                    EntityPlayerSP entityPlayerSP = minecraft.player;
                    MovementInput movementInput = entityPlayerSP.movementInput;
                    Vec2f vec2f = movementInput.getMoveVector();
                    if (vec2f.x == 0.0f && vec2f.y == 0.0f) break;
                    Vec3d vec3d = VectorMath.rotatePitchYaw(new Vec3d(-vec2f.x, 0.0, vec2f.y), LerpMath.lerp(entityPlayerSP.prevRotationPitch, entityPlayerSP.rotationPitch, minecraft.getRenderPartialTicks()), LerpMath.lerp(entityPlayerSP.prevRotationYawHead, entityPlayerSP.rotationYawHead, minecraft.getRenderPartialTicks()));
                    NetworkHandler.channel.sendToServer((IMessage)new PacketUpdateVelocity(vec3d, this.getGirlUuid()));
                    break;
                }
                case "clap": {
                    this.a(ModSounds.MISC_CLAP, new int[0]);
                    break;
                }
                case "energysound": {
                    this.a(ModSounds.MISC_BEEW[1]);
                    break;
                }
                case "energy2": {
                    this.a(ModSounds.MISC_BEEW[2]);
                    break;
                }
                case "tpSound": {
                    this.a(ModSounds.MISC_WEOWEO[2]);
                    break;
                }
                case "sexui": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                }
            }
        });
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.EyesController);
        animationData.addAnimationController(this.MovementController);
    }

    private static /* synthetic */ Vec3d lambda$null$3(GirlEntity girl) {
        return girl.getModelBone("creampiePos").add(girl.getTargetPos());
    }

    private /* synthetic */ Vec3d lambda$null$2(GirlEntity girl) {
        return VectorMath.rotateYaw(new Vec3d(0.0, 0.0, (double)0.6f), this.I().floatValue());
    }

    private static /* synthetic */ Vec3d lambda$null$1(GirlEntity girl) {
        return girl.getModelBone("futaCockTip").add(girl.getTargetPos());
    }

    private static /* synthetic */ Vec3d lambda$null$0(GirlEntity girl) {
        Vec3d vec3d = girl.d("futaCockTip");
        Vec3d vec3d2 = girl.d("futaCockTipDirHelp");
        return vec3d.subtract(vec3d2).normalize();
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

