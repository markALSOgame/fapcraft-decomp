/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.geckolib3.util.MatrixStack;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class KoboldPlayer
extends GirlPlayerHomeEntity
implements BooleanCheck {
    public static final EyeAndKoboldColor DefaultTribeColor = EyeAndKoboldColor.PURPLE;
    public static final DataParameter<Float> BodySizeKey = EntityDataManager.createKey(KoboldPlayer.class, (DataSerializer)DataSerializers.FLOAT).getSerializer().createKey(122);
    boolean aB = false;
    boolean az = true;
    boolean ay = false;
    int ax = 0;

    protected KoboldPlayer(World world) {
        super(world);
    }

    public KoboldPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.values()[this.getRNG().nextInt(EyeAndKoboldColor.values().length)];
        this.DataManager.register(au, (Object)new BlockPos(eyeAndKoboldColor.getMainColor()));
        this.DataManager.register(as, (Object)DefaultTribeColor.name());
        this.DataManager.register(BodySizeKey, (Object)Float.valueOf(0.0f));
    }

    @Override
    public AxisAlignedBB getInteractBox(EntityPlayer entityPlayer) {
        float f = 0.6f;
        float f2 = 0.9f;
        float f3 = f / 2.0f;
        return new AxisAlignedBB(entityPlayer.posX - (double)f3, entityPlayer.posY, entityPlayer.posZ - (double)f3, entityPlayer.posX + (double)f3, entityPlayer.posY + (double)f2, entityPlayer.posZ + (double)f3);
    }

    @Override
    public void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        block5: for (int i = 0; i < list.size(); ++i) {
            int n = list.get(i);
            switch (i) {
                case 0: {
                    this.DataManager.set(BodySizeKey, (Object)Float.valueOf((float)n / 100.0f * 0.25f));
                    continue block5;
                }
                case 1: {
                    this.DataManager.set(TribeColorKey, (Object)EyeAndKoboldColor.values()[n].toString());
                    continue block5;
                }
                case 2: {
                    this.DataManager.set(HomePosKey, (Object)new BlockPos(EyeAndKoboldColor.values()[n].getMainColor()));
                    continue block5;
                }
                default: {
                    GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, n);
                }
            }
        }
        this.DataManager.set(AttributeStringKey, (Object)stringBuilder.toString());
        if (this.world.isRemote) {
            KoboldPlayerRenderer.clearColorCache();
        }
    }

    @Override
    public ArrayList<Integer> getOutfitData() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(Math.round(((Float)this.DataManager.get(BodySizeKey)).floatValue() * 100.0f / 0.25f));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((String)this.DataManager.get(as))));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((Vec3i)this.DataManager.get(au))));
        return arrayList;
    }

    @Override
    protected String serializeGirlSpecificData(StringBuilder stringBuilder) {
        GirlEffectEntity.appendRandomBelow(stringBuilder, 8);
        GirlEffectEntity.appendRandomBelow(stringBuilder, 3);
        GirlEffectEntity.appendRandomGauss(stringBuilder);
        GirlEffectEntity.appendRandomGauss(stringBuilder);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 1);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> getCustomizationSlots() {
        return new ArrayList<Integer>(){
            {
                this.add(101);
                this.add(EyeAndKoboldColor.values().length);
                this.add(EyeAndKoboldColor.values().length);
                this.add(8);
                this.add(3);
                this.add(101);
                this.add(101);
                this.add(3);
                this.add(3);
                this.add(4);
                this.add(2);
            }
        };
    }

    @Override
    protected void a() {
        KoboldPlayerRenderer.clearColorCache();
        KoboldNpcRenderer.clearColorCache();
    }

    @Override
    public float getRenderLabelOffset() {
        float f = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        return 1.4f - f;
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("anal".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.KOBOLD_ANAL_START);
                this.a(this.getOutfitIndex(), GirlAnimationState.KOBOLD_ANAL_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        try {
            if ("oral".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.STARTBLOWJOB);
                this.a(this.getOutfitIndex(), GirlAnimationState.STARTBLOWJOB);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        try {
            if ("mating".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.MATING_PRESS_START);
                this.a(this.getOutfitIndex(), GirlAnimationState.MATING_PRESS_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlCommandMenu(this, entityPlayer, new String[]{"anal", "oral", "mating"}, null, false));
        return true;
    }

    @Override
    public boolean a() {
        boolean flag;
        Block block = this.world.getBlockState(this.getPosition().add(0, 1, 0)).getBlock();
        try {
            flag = !block.isPassable((IBlockAccess)this.world, this.getPosition().add(0, 1, 0));
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    protected MatrixStack a(MatrixStack matrixStack) {
        float f = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        matrixStack.scale(1.0f - f, 1.0f - f, 1.0f - f);
        return matrixStack;
    }

    @Override
    protected float a(float f) {
        float f2 = 1.0f - (0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue());
        return f * f2;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube8();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/kobold/hand.png";
    }

    @Override
    public Vec3i getTribeColor(int i) {
        try {
            return EyeAndKoboldColor.valueOf((String)this.DataManager.get(as)).getMainColor();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return super.getTribeColor(i);
        }
    }

    @Override
    @Nullable
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB_BLINK) {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.KOBOLD_ANAL_SLOW) {
                return GirlAnimationState.KOBOLD_ANAL_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.THRUSTBLOWJOB && girlAnimationState != GirlAnimationState.SUCKBLOWJOB_BLINK) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldPlayer.rethrow(runtimeException);
                        }
                        return GirlAnimationState.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.KOBOLD_ANAL_SLOW && girlAnimationState != GirlAnimationState.KOBOLD_ANAL_FAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.KOBOLD_ANAL_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.MATING_PRESS_HARD && girlAnimationState != GirlAnimationState.MATING_PRESS_SOFT) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.MATING_PRESS_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block22: {
            GirlAnimationState girlAnimationState2;
            block20: {
                block18: {
                    girlAnimationState2 = this.getCurrentAction();
                    try {
                        block19: {
                            try {
                                try {
                                    if (girlAnimationState2 != GirlAnimationState.MATING_PRESS_CUM) break block18;
                                    if (girlAnimationState == GirlAnimationState.MATING_PRESS_SOFT) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw KoboldPlayer.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.MATING_PRESS_HARD) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw KoboldPlayer.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.KOBOLD_ANAL_CUM) break block20;
                                if (girlAnimationState == GirlAnimationState.KOBOLD_ANAL_SLOW) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw KoboldPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.KOBOLD_ANAL_FAST) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayer.rethrow(runtimeException);
                }
            }
            try {
                block23: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.CUMBLOWJOB) break block22;
                            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block22;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        float f = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        GeckoLibCache.getInstance().parser.setValue("size", f);
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.a("animation.kobold.null", true, animEvent);
                    break;
                }
                this.a("animation.kobold.blink", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.a("animation.kobold.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.a("animation.kobold.sit", true, animEvent);
                    break;
                }
                if (this.MovementController.getCurrentAnimation() != null && this.MovementController.getCurrentAnimation().animationName.contains("fly") && this.af) {
                    boolean bl = this.aB = !this.aB;
                }
                if (!this.af) {
                    this.a("animation.kobold.fly" + (this.aB ? "2" : ""), true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) > 0.0f) {
                    if (this.aj) {
                        this.MovementController.setAnimationSpeed(1.2f);
                        this.a("animation.kobold.run", true, animEvent);
                        break;
                    }
                    if (this.ao.y >= -0.1f) {
                        this.MovementController.setAnimationSpeed(2.0);
                        this.a("animation.kobold.walk", true, animEvent);
                        break;
                    }
                    this.MovementController.setAnimationSpeed(1.75);
                    this.a("animation.kobold.backwards_walk", true, animEvent);
                    break;
                }
                this.a("animation.kobold.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.a("animation.kobold.null", true, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.a("animation.kobold.strip", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.a("animation.kobold.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.a("animation.kobold.bowcharge", false, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.a("animation.kobold.sit", true, animEvent);
                        break block5;
                    }
                    case MINE: {
                        this.a("animation.kobold.fall_tree", true, animEvent);
                        break block5;
                    }
                    case PAYMENT: {
                        this.a("animation.kobold.paymentBackpack", true, animEvent);
                        break block5;
                    }
                    case STARTBLOWJOB: {
                        this.a("animation.kobold.blowjobStart", false, animEvent);
                        break block5;
                    }
                    case SUCKBLOWJOB_BLINK: {
                        String string = this.az ? "R" : "L";
                        String string2 = this.ay ? "Switch" : "";
                        this.a("animation.kobold.blowjobSlow" + string + string2, true, animEvent);
                        break block5;
                    }
                    case THRUSTBLOWJOB: {
                        this.a("animation.kobold.blowjobFast", true, animEvent);
                        break block5;
                    }
                    case CUMBLOWJOB: {
                        this.a("animation.kobold.blowjobCum", false, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_START: {
                        this.a("animation.kobold.analStart", false, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_SLOW: {
                        this.a("animation.kobold.analSoft", true, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_FAST: {
                        this.a("animation.kobold.analHard", true, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_CUM: {
                        this.a("animation.kobold.analCum", true, animEvent);
                        break block5;
                    }
                    case SLEEP: {
                        this.a("animation.kobold.sleep", true, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_START: {
                        this.a("animation.kobold.mating_press_start", false, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_SOFT: {
                        this.a("animation.kobold.mating_press_soft", true, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_HARD: {
                        this.a("animation.kobold.mating_press_hard", true, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_CUM: {
                        this.a("animation.kobold.mating_press_cum", true, animEvent);
                    }
                }
            }
        }
        return PlayState.CONTINUE;
    }

    void b(SoundEvent soundEvent) {
        this.b(soundEvent, 1.0f);
    }

    void b(SoundEvent[] soundEventArray) {
        this.b(soundEventArray, 1.0f);
    }

    void b(SoundEvent[] soundEventArray, float f) {
        this.b(soundEventArray[this.getRNG().nextInt(soundEventArray.length)], f);
    }

    void b(SoundEvent soundEvent, float f) {
        float f2 = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        double d = f2 / 0.25f;
        float f3 = (float)LerpMath.lerp((double)0.9f, (double)1.1f, d);
        this.a(soundEvent, f, f3);
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
            throw KoboldPlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "paymentMSG1": {
                    this.a(this.getSexPlayerUuid(), "I'd like to use ur services owo");
                    this.b(ModSounds.MISC_PLOB);
                    break;
                }
                case "plob": {
                    this.b(ModSounds.MISC_PLOB);
                    break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "paymentDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.U();
                    break;
                }
                case "blowjobStartMSG1": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.625 - (double)entityPlayerSP.getEyeHeight(), -1.0), this.I().floatValue() + 180.0f);
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(this.getSexPlayerUuid().toString(), this.getPositionVector().add(vec3d), this.I().floatValue() + 180.0f, 0.0f));
                    break;
                }
                case "blowjobStartMSG2": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.5, 0.5 - (double)entityPlayerSP.getEyeHeight(), -0.6875), this.I().floatValue() + 180.0f);
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(this.getSexPlayerUuid().toString(), this.getPositionVector().add(vec3d), this.I().floatValue() + 180.0f - 40.0f, 0.0f));
                    break;
                }
                case "lipsound": {
                    if (this.getRNG().nextBoolean()) {
                        this.a(ModSounds.GIRLS_ALLIE_LIPSOUND, 1.5f);
                    } else {
                        this.a(ModSounds.GIRLS_JENNY_LIPSOUND, 1.5f);
                    }
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "touch": {
                    this.b(ModSounds.MISC_TOUCH);
                    break;
                }
                case "blowjobStartDone": {
                    this.b(GirlAnimationState.SUCKBLOWJOB_BLINK);
                    this.ay = false;
                    this.az = true;
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "switch": {
                    this.ay = this.getRNG().nextBoolean();
                    this.ActionController.clearAnimationCache();
                    break;
                }
                case "endSwitch": {
                    this.ay = false;
                    this.az = !this.az;
                    this.ActionController.clearAnimationCache();
                    break;
                }
                case "blowjobFastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.SUCKBLOWJOB_BLINK);
                    break;
                }
                case "cumLoud": {
                    this.a(ModSounds.MISC_SMALLINSERTS, 3.0f);
                    break;
                }
                case "cumQuiet": {
                    this.a(ModSounds.MISC_SMALLINSERTS, 1.5f);
                    break;
                }
                case "analCumDone":
                case "blowjobCumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    GuiHud.forceShowHud();
                    break;
                }
                case "analStartDone": {
                    this.b(GirlAnimationState.KOBOLD_ANAL_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "analStartCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.5625 - (double)entityPlayerSP.getEyeHeight(), 0.5625), this.I().floatValue() + 180.0f);
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(this.getSexPlayerUuid().toString(), this.getPositionVector().add(vec3d), this.I().floatValue(), 0.0f));
                    break;
                }
                case "pounding": {
                    this.b(ModSounds.MISC_POUNDING);
                    break;
                }
                case "analFastRapid": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    if (this.getCurrentAction() == GirlAnimationState.KOBOLD_ANAL_FAST) {
                        this.N();
                        break;
                    }
                    this.b(GirlAnimationState.KOBOLD_ANAL_FAST);
                    break;
                }
                case "analDone": {
                    if (this.getCurrentAction() != GirlAnimationState.KOBOLD_ANAL_FAST) break;
                    this.b(GirlAnimationState.KOBOLD_ANAL_SLOW);
                    break;
                }
                case "analHard": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "analSoft": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_SMALLINSERTS, 2.0f);
                    break;
                }
                case "giggle": {
                    this.b(ModSounds.GIRLS_KOBOLD_GIGGLE);
                    break;
                }
                case "moan": {
                    this.b(ModSounds.GIRLS_KOBOLD_MOAN);
                    break;
                }
                case "moanMating": {
                    --this.ax;
                    if (this.ax > 0) break;
                    this.ax = 3;
                    this.b(ModSounds.GIRLS_KOBOLD_MOAN);
                    break;
                }
                case "analHardMSG1": {
                    --this.ax;
                    if (this.ax > 0) break;
                    this.ax = 4;
                    this.b(ModSounds.GIRLS_KOBOLD_MOAN);
                    break;
                }
                case "orgasm": {
                    this.b(ModSounds.GIRLS_KOBOLD_ORGASM);
                    break;
                }
                case "breath": {
                    this.b(ModSounds.GIRLS_KOBOLD_LIGHTBREATHING, 0.5f);
                    break;
                }
                case "haa": {
                    this.b(ModSounds.GIRLS_KOBOLD_HAA, 0.7f);
                    break;
                }
                case "interested": {
                    this.b(ModSounds.GIRLS_KOBOLD_INTERESTED);
                    break;
                }
                case "yep": {
                    this.b(ModSounds.GIRLS_KOBOLD_YEP);
                    break;
                }
                case "bjmoan": {
                    this.b(ModSounds.pickRandomSound(ModSounds.GIRLS_KOBOLD_BJMOAN));
                    break;
                }
                case "blowjobStartbreath": {
                    int n = this.getRNG().nextInt(3);
                    this.b(ModSounds.GIRLS_KOBOLD_LIGHTBREATHING[n]);
                    break;
                }
                case "matingCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = new Vec3d(0.0, 0.4375 - (double)entityPlayerSP.eyeHeight, -0.6875);
                    vec3d = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    vec3d = vec3d.add(this.getPositionVector());
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(entityPlayerSP.getPersistentID().toString(), vec3d, this.I().floatValue() + 180.0f, 10.0f));
                    break;
                }
                case "mating_press_startDone": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.showHud();
                    }
                }
                case "mating_press_hardDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.b(GirlAnimationState.MATING_PRESS_SOFT);
                    break;
                }
                case "mating_press_softReady": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.04f);
                    }
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.MATING_PRESS_HARD);
                    break;
                }
                case "mating_press_hardReady": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.04f);
                    }
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "mating_cum_cam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = new Vec3d(0.0, 1.1875 - (double)entityPlayerSP.eyeHeight, 0.125);
                    vec3d = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    vec3d = vec3d.add(this.getPositionVector());
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(entityPlayerSP.getPersistentID().toString(), vec3d, this.I().floatValue() + 180.0f, 70.0f));
                    break;
                }
                case "cumMsg": {
                    this.a("I.. hope I am satisfying you sir");
                    this.b(ModSounds.GIRLS_KOBOLD_SAD[this.getRNG().nextInt(1)]);
                    break;
                }
                case "mating_press_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                }
            }
        };
        this.MovementController.transitionLengthTicks = 3.0;
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

