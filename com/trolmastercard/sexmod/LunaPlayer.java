/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
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

public class LunaPlayer
extends PlayerGirlEntity {
    int WaitTicks = 0;
    boolean aq = false;
    boolean ap = false;
    boolean as = false;

    protected LunaPlayer(World world) {
        super(world);
    }

    public LunaPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.6f;
    }

    public float getEyeHeight() {
        return 1.34f;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube4();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/cat/hand.png";
    }

    @Override
    public void performAction(String string, UUID uUID) {
        try {
            if ("action.names.touchboobs".equals(string)) {
                this.a(0, GirlAnimationState.TOUCH_BOOBS_INTRO);
                this.b(GirlAnimationState.TOUCH_BOOBS_INTRO);
                this.DataManager.set(D, (Object)0);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
        try {
            if ("action.names.headpat".equals(string)) {
                this.b(GirlAnimationState.HEAD_PAT);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void setWaitState() {
        this.b(GirlAnimationState.WAIT_CAT);
    }

    @Override
    public boolean allowsBedInteraction() {
        return true;
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        LunaPlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.touchboobs", "action.names.headpat"}, false);
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
                                if (this.getCurrentAction() != GirlAnimationState.COWGIRL_SITTING_CUM) break block12;
                                if (girlAnimationState == GirlAnimationState.COWGIRL_SITTING_SLOW) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.COWGIRL_SITTING_FAST) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.TOUCH_BOOBS_CUM) break block14;
                            if (girlAnimationState == GirlAnimationState.TOUCH_BOOBS_FAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.TOUCH_BOOBS_SLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    public void onUpdate() {
        block3: {
            block2: {
                try {
                    super.onUpdate();
                    if (!GirlAnimationState.WAIT_CAT.equals((Object)this.getCurrentAction())) break block2;
                    this.a();
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            this.WaitTicks = 0;
        }
    }

    void a() {
        block12: {
            EntityPlayer entityPlayer;
            block11: {
                entityPlayer = this.getRenderPosition();
                try {
                    if (entityPlayer == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
                try {
                    if (entityPlayer.getDistance(this.posX, this.getCustomName().y, this.posZ) > 1.25) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
                try {
                    if (!this.world.isRemote) break block11;
                    this.a(entityPlayer, this.WaitTicks);
                    break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            try {
                if (this.WaitTicks == 25) {
                    this.hasGirl(entityPlayer.getPersistentID());
                    entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
                    entityPlayer.setPositionAndUpdate(this.getPositionVector().x, this.getCustomName().y, this.getPositionVector().z);
                    this.b(GirlAnimationState.COWGIRL_SITTING_INTRO);
                    entityPlayer.setRotationYawHead(this.I().floatValue() + 180.0f);
                    entityPlayer.rotationYaw = this.I().floatValue() + 180.0f;
                    entityPlayer.prevRotationYaw = this.I().floatValue() + 180.0f;
                    this.AimYaw = this.I().floatValue() + 180.0f;
                    this.a(0.0, -0.075f, -0.7109375, 0.0f, 0.0f);
                    this.DataManager.set(D, (Object)0);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        ++this.WaitTicks;
    }

    @SideOnly(value=Side.CLIENT)
    void a(EntityPlayer entityPlayer, int i) {
        EntityPlayerSP entityPlayerSP;
        if (i == 0) {
            entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    GuiTransitionScreen.startTransition();
                    entityPlayerSP.setVelocity(0.0, 0.0, 0.0);
                    AnimationInputLock.setAnimationLocked(false);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        if (i == 25) {
            entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    Minecraft.getMinecraft().gameSettings.thirdPersonView = 2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.TOUCH_BOOBS_SLOW) {
                return GirlAnimationState.TOUCH_BOOBS_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.COWGIRL_SITTING_SLOW) {
                return GirlAnimationState.COWGIRL_SITTING_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.TOUCH_BOOBS_SLOW && girlAnimationState != GirlAnimationState.TOUCH_BOOBS_FAST) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.TOUCH_BOOBS_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.COWGIRL_SITTING_FAST && girlAnimationState != GirlAnimationState.COWGIRL_SITTING_SLOW) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.COWGIRL_SITTING_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.a("animation.cat.null", true, animEvent);
                    break;
                }
                this.a("animation.cat.blink", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.a("animation.cat.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.a("animation.cat.sit", true, animEvent);
                    break;
                }
                if (this.MovementController.getCurrentAnimation() != null && this.MovementController.getCurrentAnimation().animationName.contains("fly") && this.af) {
                    boolean bl = this.aq = !this.aq;
                }
                if (!this.af) {
                    this.a("animation.cat.fly" + (this.aq ? "2" : ""), true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) > 0.0f) {
                    if (this.aj) {
                        this.MovementController.setAnimationSpeed(1.5);
                        this.a("animation.cat.run", true, animEvent);
                        break;
                    }
                    if (this.ao.y >= -0.1f) {
                        this.MovementController.setAnimationSpeed(2.0);
                        this.a("animation.cat.fastwalk", true, animEvent);
                        break;
                    }
                    this.MovementController.setAnimationSpeed(2.0);
                    this.a("animation.cat.backwards_walk", true, animEvent);
                    break;
                }
                this.a("animation.cat.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.a("animation.cat.null", true, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.a("animation.cat.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case RIDE:
                    case SIT: {
                        this.a("animation.cat.sit", true, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.a("animation.cat.bowcharge", false, animEvent);
                        break block5;
                    }
                    case THROW_PEARL: {
                        this.a("animation.cat.throwpearl", true, animEvent);
                        break block5;
                    }
                    case DOWNED: {
                        this.a("animation.cat.downed", true, animEvent);
                        break block5;
                    }
                    case FISHING_START: {
                        this.a("animation.cat.start_fishing", false, animEvent);
                        break block5;
                    }
                    case FISHING_IDLE: {
                        this.a("animation.cat.idle_fishing", true, animEvent);
                        break block5;
                    }
                    case FISHING_EAT: {
                        this.a("animation.cat.eat_fishing", false, animEvent);
                        break block5;
                    }
                    case FISHING_THROW_AWAY: {
                        this.a("animation.cat.throw_away", false, animEvent);
                        break block5;
                    }
                    case PAYMENT: {
                        this.a("animation.cat.payment", false, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_INTRO: {
                        this.a("animation.cat.touch_boobs_intro", false, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_SLOW: {
                        this.a("animation.cat.touch_boobs_slow" + (this.ap ? "1" : ""), true, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_FAST: {
                        this.a("animation.cat.touch_boobs_fast", true, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_CUM: {
                        this.a("animation.cat.touch_boobs_cum", false, animEvent);
                        break block5;
                    }
                    case WAIT_CAT: {
                        this.a("animation.cat.wait", false, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_INTRO: {
                        this.a("animation.cat.sitting_intro", false, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_SLOW: {
                        this.a("animation.cat.sitting_slow", true, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_FAST: {
                        this.a("animation.cat.sitting_fast", true, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_CUM: {
                        this.a("animation.cat.sitting_cum", true, animEvent);
                        break block5;
                    }
                    case HEAD_PAT: {
                        this.a("animation.cat.head_pat", true, animEvent);
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
            throw LunaPlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "idleDone": {
                    this.as = this.getRNG().nextInt(10) == 0;
                    break;
                }
                case "idle2Done": {
                    this.as = false;
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "paymentMSG1": {
                    this.a(this.getSexPlayerUuid(), "Here, I know u like fish and yea.. these are for you");
                    this.a(ModSounds.MISC_PLOB[0]);
                    break;
                }
                case "paymentMSG2": {
                    this.a("huh~?");
                    this.a(ModSounds.GIRLS_LUNA_HUH, new int[0]);
                    break;
                }
                case "paymentMSG3": {
                    this.a("nyyyaaaa~ :D");
                    int[] nArray = new int[]{1, 7, 10, 11};
                    int n = nArray[this.getRNG().nextInt(nArray.length)];
                    this.a(ModSounds.GIRLS_LUNA_CUTENYA[n]);
                    break;
                }
                case "paymentMSG4": {
                    this.a("tankuuuu owowowo");
                    this.a(ModSounds.GIRLS_LUNA_OWO, new int[0]);
                    break;
                }
                case "paymentDone": {
                    if (this.isLocalPlayerNearby()) {
                        this.U();
                    }
                    this.n = 1.0f;
                    break;
                }
                case "breath":
                case "rod_breath": {
                    this.a(ModSounds.GIRLS_LUNA_LIGHTBREATHING, new int[0]);
                    break;
                }
                case "happyOh": {
                    this.a(ModSounds.GIRLS_LUNA_HAPPYOH, new int[0]);
                    break;
                }
                case "cutenya3": {
                    this.a(ModSounds.GIRLS_LUNA_CUTENYA[3]);
                    break;
                }
                case "cutenya2": {
                    this.a(ModSounds.GIRLS_LUNA_CUTENYA[2]);
                    break;
                }
                case "huh": {
                    this.a(ModSounds.GIRLS_LUNA_HUH, new int[0]);
                    break;
                }
                case "hmph": {
                    this.a(ModSounds.GIRLS_LUNA_HMPH, new int[0]);
                    break;
                }
                case "hehe":
                case "giggle": {
                    this.a(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    break;
                }
                case "singing": {
                    this.a(ModSounds.GIRLS_LUNA_SINGING, new int[0]);
                    break;
                }
                case "touch_boobsMSG1": {
                    this.a("comon~ touch me hihi~");
                    this.a(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    break;
                }
                case "touch": {
                    this.a(ModSounds.MISC_TOUCH, new int[0]);
                    break;
                }
                case "jump": {
                    this.a(ModSounds.MISC_JUMP[0], 0.2f);
                    break;
                }
                case "horninya": {
                    this.a(ModSounds.GIRLS_LUNA_HORNINYA, new int[0]);
                    break;
                }
                case "horninya2":
                case "touch_boobs_cumMSG3":
                case "sitting_cumMSG1": {
                    this.a(ModSounds.GIRLS_LUNA_HORNINYA[1]);
                    this.a(ModSounds.MISC_CUMINFLATION[0], 5.0f);
                    break;
                }
                case "moan": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                    break;
                }
                case "touch_boobs_introDone": {
                    this.b(GirlAnimationState.TOUCH_BOOBS_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    GuiHud.showHud();
                    AnimationInputLock.setAnimationLocked(false);
                    break;
                }
                case "touch_boobs_slowDone": {
                    if (this.ap) {
                        this.ap = false;
                        break;
                    }
                    this.ap = Math.random() < 0.5;
                    break;
                }
                case "addCumSlow": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "addCumFast": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.TOUCH_BOOBS_SLOW);
                    break;
                }
                case "moanOrNya": {
                    if (Math.random() > 0.5) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                        break;
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_HORNINYA));
                    break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "touch_boobs_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                    break;
                }
                case "resetGirl": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    break;
                }
                case "touch_boobs_cumMSG1": {
                    this.a(ModSounds.GIRLS_LUNA_HORNINYA[3]);
                    break;
                }
                case "touch_boobs_cumMSG2": {
                    this.a(ModSounds.GIRLS_LUNA_HORNINYA[9]);
                    break;
                }
                case "call_playerMSG1": {
                    this.a(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    this.a("come here - big guy hehe~");
                    break;
                }
                case "pounding": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    break;
                }
                case "sitting_introMSG1": {
                    this.a(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    this.a("hehe~");
                    break;
                }
                case "sitting_introDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.b(GirlAnimationState.COWGIRL_SITTING_SLOW);
                    GuiHud.resetProgress();
                    GuiHud.showHud();
                    break;
                }
                case "sitting_slowMSG1": {
                    if (this.getRNG().nextBoolean()) {
                        if (this.getRNG().nextBoolean()) {
                            this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_HORNINYA));
                            break;
                        }
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_LIGHTBREATHING));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "sitting_fastMSG1": {
                    if (this.getRNG().nextBoolean()) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_HORNINYA));
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "sitting_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.COWGIRL_SITTING_SLOW);
                    Vec3d vec3d = new Vec3d(0.0, -0.075f, -0.7109375);
                    Vec3d vec3d2 = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    Minecraft.getMinecraft().player.setPosition(this.getPositionVector().x + vec3d2.x, this.getPositionVector().y - 0.0 + vec3d2.y, this.getPositionVector().z + vec3d2.z);
                    break;
                }
                case "sitting_fastTp": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    Vec3d vec3d = new Vec3d(0.0, -0.160625, -0.9925);
                    Vec3d vec3d3 = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    Minecraft.getMinecraft().player.setPosition(this.getPositionVector().x + vec3d3.x, this.getPositionVector().y - 0.0 + vec3d3.y, this.getPositionVector().z + vec3d3.z);
                    break;
                }
                case "headpatMSG1": {
                    this.a("huh?~");
                    this.a(ModSounds.GIRLS_LUNA_HUH, new int[0]);
                    break;
                }
                case "headpatMSG2": {
                    this.a(ModSounds.GIRLS_LUNA_MMM, new int[0]);
                    break;
                }
                case "headpatMSG3": {
                    this.a("nya~");
                    this.a(ModSounds.GIRLS_LUNA_HORNINYA[0]);
                }
            }
        };
        this.MovementController.transitionLengthTicks = 10.0;
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

