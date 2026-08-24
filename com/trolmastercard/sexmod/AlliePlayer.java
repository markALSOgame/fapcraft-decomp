/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
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

public class AlliePlayer
extends PlayerGirlEntity {
    static final double au = 4.0;
    static final double at = 4.0;
    public float HeightOffset = 0.0f;
    EntityPlayer BoundPlayer = null;
    boolean ap = false;
    int ar = 1;
    int av = 1;

    protected AlliePlayer(World world) {
        super(world);
    }

    public AlliePlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.9f + this.HeightOffset;
    }

    public float getEyeHeight() {
        return 1.63f;
    }

    @Override
    public boolean v() {
        return false;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube7();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/allie/hand.png";
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("action.names.deepthroat".equals(string)) {
                this.setCurrentAction(GirlAnimationState.DEEPTHROAT_START);
                this.a(this.getOutfitIndex(), GirlAnimationState.DEEPTHROAT_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        try {
            if ("Reverse cowgirl".equals(string)) {
                this.setCurrentAction(GirlAnimationState.REVERSE_COWGIRL_START);
                this.a(0, GirlAnimationState.REVERSE_COWGIRL_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        AlliePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.deepthroat", "Reverse cowgirl"}, false);
        return true;
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block16: {
            block14: {
                try {
                    block15: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.DEEPTHROAT_CUM) break block14;
                                if (girlAnimationState == GirlAnimationState.DEEPTHROAT_FAST) break block15;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AlliePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.DEEPTHROAT_SLOW) break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AlliePlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayer.rethrow(runtimeException);
                }
            }
            try {
                block17: {
                    try {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.REVERSE_COWGIRL_CUM) break block16;
                                if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AlliePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AlliePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AlliePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    @Override
    public boolean isInOralState() {
        try {
            switch (this.getCurrentAction()) {
                case ALLIE_PREPARE_NORMAL: 
                case DEEPTHROAT_START: 
                case DEEPTHROAT_CUM: 
                case DEEPTHROAT_FAST: 
                case ALLIE_PREPARE_FIRST_TIME: 
                case DEEPTHROAT_SLOW: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void updateAITasks() {
        EntityPlayer entityPlayer;
        block7: {
            try {
                super.updateAITasks();
                if (this.getBoundPlayerUuid() == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
            entityPlayer = this.world.getPlayerEntityByUUID(this.getBoundPlayerUuid());
            try {
                try {
                    if (entityPlayer == null || this.BoundPlayer != null) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayer.rethrow(runtimeException);
                }
                this.c(true);
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
        }
        this.BoundPlayer = entityPlayer;
    }

    @Override
    public void onUpdate() {
        try {
            super.onUpdate();
            if (this.world.isRemote) {
                this.a();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void a() {
        try {
            if (this.ticksExisted % 10 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        int i = this.getRNG().nextInt(8);
        Vec3d vec3d = this.b("tail" + i).add(this.getPositionVector());
        this.world.spawnParticle(EnumParticleTypes.PORTAL, vec3d.x, vec3d.y, vec3d.z, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, new int[0]);
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
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.DEEPTHROAT_SLOW) {
                return GirlAnimationState.DEEPTHROAT_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) {
                return GirlAnimationState.REVERSE_COWGIRL_FAST_START;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block12: {
            block10: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.DEEPTHROAT_FAST && girlAnimationState != GirlAnimationState.DEEPTHROAT_SLOW) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AlliePlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.DEEPTHROAT_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayer.rethrow(runtimeException);
                }
            }
            try {
                block11: {
                    try {
                        try {
                            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW || girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AlliePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AlliePlayer.rethrow(runtimeException);
                    }
                }
                return GirlAnimationState.REVERSE_COWGIRL_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.canStartInteraction();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "deepthroat_prepareMSG1": {
                    this.a(I18n.format("allie.dialogue.hihi", new Object[0]));
                    this.a(ModSounds.MISC_PLOB[0]);
                    break;
                }
                case "deepthroat_prepareMSG2": {
                    this.a(I18n.format("allie.dialogue.boys", new Object[0]));
                    this.a(ModSounds.MISC_PLOB[0]);
                    break;
                }
                case "blackscreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "deepthroat_prepareDone": {
                    this.setCurrentAction(GirlAnimationState.DEEPTHROAT_START);
                    if (!this.isOwnedByLocalPlayer()) break;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSexPromptReply(this.getGirlUuid(), this.getSexPlayerUuid(), false, true));
                    this.AimYaw = this.rotationYaw + 180.0f;
                    this.a(0.0, 0.0, (double)1.35f, 0.0f, 30.0f);
                    GuiHud.resetProgress();
                    break;
                }
                case "deepthroat_fastMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_BJMOAN));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "deepthroat_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.DEEPTHROAT_SLOW);
                    break;
                }
                case "deepthroat_startDone": {
                    this.setCurrentAction(GirlAnimationState.DEEPTHROAT_SLOW);
                    break;
                }
                case "deepthroat_slowMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_LIPSOUND));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "deepthroat_cumMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_LIPSOUND));
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_CUMINFLATION), 1.5f);
                    break;
                }
                case "cowgirl_cumDone":
                case "deepthroat_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    break;
                }
                case "deepthroat_normal_prepareMSG1": {
                    this.a(I18n.format("allie.dialogue.alright", new Object[0]));
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_PLOB));
                    break;
                }
                case "giggle": {
                    this.a(ModSounds.GIRLS_ALLIE_GIGGLE, new int[0]);
                    break;
                }
                case "pounding": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    break;
                }
                case "moan": {
                    this.a(ModSounds.GIRLS_ALLIE_MOAN, new int[0]);
                    break;
                }
                case "mmm": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_MMM));
                    break;
                }
                case "slide": {
                    this.a(ModSounds.MISC_SLIDE, 0, 1, 4, 6);
                    break;
                }
                case "slowMoan": {
                    if (this.getRNG().nextBoolean()) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_AHH));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "cowgirlSlowDone": {
                    int n = this.ar;
                    do {
                        this.ar = this.getRNG().nextInt(3) + 1;
                    } while (this.ar == n);
                    break;
                }
                case "fastMoan": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.04f);
                    }
                    if (!this.ap) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_MOAN));
                        this.ap = true;
                        break;
                    }
                    this.ap = false;
                    break;
                }
                case "fastSwitch": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    GirlAnimationState girlAnimationState = this.getCurrentAction();
                    if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) {
                        this.setCurrentAction(GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES);
                        break;
                    }
                    this.N();
                    int n = this.av;
                    do {
                        this.av = this.getRNG().nextInt(3) + 1;
                    } while (this.av == n);
                    break;
                }
                case "openSexUi": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_INSERTS, 6.0f);
                    break;
                }
                case "aftermoan": {
                    this.a(ModSounds.GIRLS_ALLIE_AFTERSESSIONMOAN, new int[0]);
                }
            }
        };
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.createAnimationOnce("animation.allie.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.bia.blink", true, animEvent);
                break;
            }
            case "movement": {
                double d = 4.0 * (Math.abs(this.posX - this.lastTickPosX) + Math.abs(this.posY - this.lastTickPosY) + Math.abs(this.posZ - this.lastTickPosZ));
                d = Math.min(1.0 + d, 4.0);
                this.MovementController.setAnimationSpeed(d);
                this.createAnimationOnce("animation.allie.tail", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.createAnimationOnce("animation.allie.null", true, animEvent);
                        break block5;
                    }
                    case SUMMON: {
                        this.createAnimationOnce("animation.allie.summon", false, animEvent);
                        break block5;
                    }
                    case SUMMON_NORMAL: {
                        this.createAnimationOnce("animation.allie.summon_normal", false, animEvent);
                        break block5;
                    }
                    case SUMMON_NORMAL_WAIT: {
                        this.createAnimationOnce("animation.allie.summon_normal_wait", true, animEvent);
                        break block5;
                    }
                    case SUMMON_WAIT: {
                        this.createAnimationOnce("animation.allie.summon_wait", true, animEvent);
                        break block5;
                    }
                    case ALLIE_PREPARE_FIRST_TIME: {
                        this.createAnimationOnce("animation.allie.deepthroat_prepare", false, animEvent);
                        break block5;
                    }
                    case ALLIE_PREPARE_NORMAL: {
                        this.createAnimationOnce("animation.allie.deepthroat_normal_prepare", false, animEvent);
                        break block5;
                    }
                    case DEEPTHROAT_START: {
                        this.createAnimationOnce("animation.allie.deepthroat_start", false, animEvent);
                        break block5;
                    }
                    case DEEPTHROAT_SLOW: {
                        this.createAnimationOnce("animation.allie.deepthroat_slow", true, animEvent);
                        break block5;
                    }
                    case DEEPTHROAT_FAST: {
                        this.createAnimationOnce("animation.allie.deepthroat_fast", true, animEvent);
                        break block5;
                    }
                    case DEEPTHROAT_CUM: {
                        this.createAnimationOnce("animation.allie.deepthroat_cum", false, animEvent);
                        break block5;
                    }
                    case RICH_FIRST_TIME: {
                        this.createAnimationOnce("animation.allie.rich", false, animEvent);
                        break block5;
                    }
                    case RICH_NORMAL: {
                        this.createAnimationOnce("animation.allie.rich_normal", false, animEvent);
                        break block5;
                    }
                    case SUMMON_SAND: {
                        this.createAnimationOnce("animation.allie.summon_sand", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.createAnimationOnce("animation.allie.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.createAnimationOnce("animation.allie.bowcharge", false, animEvent);
                        break block5;
                    }
                    case REVERSE_COWGIRL_START: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_start", true, animEvent);
                        break block5;
                    }
                    case REVERSE_COWGIRL_SLOW: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_slow" + this.ar, true, animEvent);
                        break block5;
                    }
                    case REVERSE_COWGIRL_FAST_CONTINUES: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_fastc" + this.av, true, animEvent);
                        break block5;
                    }
                    case REVERSE_COWGIRL_FAST_START: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_fasts", true, animEvent);
                        break block5;
                    }
                    case REVERSE_COWGIRL_CUM: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_cum", true, animEvent);
                    }
                }
            }
        }
        return PlayState.CONTINUE;
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

