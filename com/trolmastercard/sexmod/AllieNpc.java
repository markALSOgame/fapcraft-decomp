/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class AllieNpc
extends GirlEntity {
    public static final int PortalParticleCount = 300;
    public static final int TailBoneCount = 8;
    public static final Vec3d EntitySize = new Vec3d(0.5, 1.0, 0.0);
    float ConversionTimer = 1.0f;
    public boolean PlayIntroAnim = false;
    public static final DataParameter<ItemStack> SpawnItemStack = EntityDataManager.createKey(AllieNpc.class, (DataSerializer)DataSerializers.ITEM_STACK).getSerializer().createKey(111);
    boolean SpawnPortalsPending = true;
    int T = 1;
    int L = 1;
    boolean M = false;
    boolean MenuOpen = false;

    public AllieNpc(World world) {
        super(world);
        this.setSize((float)AllieNpc.EntitySize.x, (float)AllieNpc.EntitySize.y);
    }

    public AllieNpc(World world, ItemStack itemStack) {
        this(world);
        this.DataManager.set(SpawnItemStack, (Object)itemStack);
    }

    @Override
    public String getDisplayName() {
        return "Allie";
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.0f;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.DataManager.register(SpawnItemStack, (Object)ItemStack.EMPTY);
    }

    public boolean isFirstTimeWithItem() {
        boolean flag;
        NBTTagCompound nBTTagCompound = ((ItemStack)this.DataManager.get(SpawnItemStack)).getTagCompound();
        try {
            if (nBTTagCompound == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            flag = nBTTagCompound.getInteger("sexmodUses") == 1;
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    public void updateAITasks() {
        try {
            super.updateAITasks();
            if (this.getCurrentAction() == GirlAnimationState.NULL) {
                this.world.removeEntity((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                this.world.removeEntity((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onActionGuiClosed() {
        try {
            if (!this.MenuOpen) {
                this.PlayIntroAnim = true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
    }

    @Override
    public void onUpdate() {
        block17: {
            block18: {
                try {
                    try {
                        try {
                            try {
                                super.onUpdate();
                                if (this.ConversionTimer == 1.0f || this.ConversionTimer == -69.0f) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AllieNpc.rethrow(runtimeException);
                            }
                            if (!(this.ConversionTimer <= 0.0f)) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                        if (!this.isOwnedByLocalPlayer()) break block18;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                    NetworkHandler.channel.sendToServer((IMessage)new PacketUploadInventory(this.isFirstTimeWithItem()));
                    AnimationInputLock.setAnimationLocked(true);
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
            }
            this.ConversionTimer = -69.0f;
        }
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.PlayIntroAnim) {
                this.getDisplayName();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.SpawnPortalsPending) {
                this.d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        this.spawnTailParticles();
    }

    void spawnTailParticles() {
        try {
            if (this.ticksExisted % 10 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        int i = this.getRNG().nextInt(8);
        Vec3d vec3d = this.b("tail" + i).add(this.getPositionVector());
        this.world.spawnParticle(EnumParticleTypes.PORTAL, vec3d.x, vec3d.y, vec3d.z, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, new int[0]);
    }

    @SideOnly(value=Side.CLIENT)
    void d() {
        this.SpawnPortalsPending = false;
        BedLogic.spawnParticles(this.world, EnumParticleTypes.PORTAL, this.getPositionVector(), 300, 0.75, 1.5);
    }

    @SideOnly(value=Side.CLIENT)
    void c() {
        this.openActionMenu((EntityPlayer)Minecraft.getMinecraft().player);
        this.PlayIntroAnim = false;
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        this.MenuOpen = false;
        String[] stringArray = new String[]{"action.names.makemerichallie", "action.names.deepthroat", "Reverse cowgirl"};
        AllieNpc.openActionMenu(entityPlayer, this, stringArray, false);
        return true;
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.DEEPTHROAT_SLOW) {
                return GirlAnimationState.DEEPTHROAT_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) {
                return GirlAnimationState.REVERSE_COWGIRL_FAST_START;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
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
                        throw AllieNpc.rethrow(runtimeException);
                    }
                    return GirlAnimationState.DEEPTHROAT_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
            }
            try {
                block11: {
                    try {
                        try {
                            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW || girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                }
                return GirlAnimationState.REVERSE_COWGIRL_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw AllieNpc.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block22: {
            block20: {
                block18: {
                    try {
                        block19: {
                            try {
                                try {
                                    if (this.getCurrentAction() != GirlAnimationState.DEEPTHROAT_CUM) break block18;
                                    if (girlAnimationState == GirlAnimationState.DEEPTHROAT_FAST) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw AllieNpc.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.DEEPTHROAT_SLOW) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AllieNpc.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                try {
                                    if (this.getCurrentAction() != GirlAnimationState.REVERSE_COWGIRL_CUM) break block20;
                                    if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) break block21;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw AllieNpc.rethrow(runtimeException);
                                }
                                if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AllieNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (this.world.isRemote || girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_START) break block22;
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
                this.a();
            }
            catch (RuntimeException runtimeException) {
                throw AllieNpc.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    void a() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = this.getTargetPos();
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        block5: switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() == GirlAnimationState.NULL && this.getCurrentAction().autoBlink) break;
                this.createAnimationOnce("animation.allie.null", true, animEvent);
                break;
            }
            case "movement": {
                this.createAnimationOnce("animation.allie.tail", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
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
                    case REVERSE_COWGIRL_START: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_start", true, animEvent);
                        break block5;
                    }
                    case REVERSE_COWGIRL_SLOW: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_slow" + this.T, true, animEvent);
                        break block5;
                    }
                    case REVERSE_COWGIRL_FAST_CONTINUES: {
                        this.createAnimationOnce("animation.allie.reverse_cowgirl_fastc" + this.L, true, animEvent);
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

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.initAnimationControllers();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "summonMSG1": {
                    this.a(I18n.format("allie.dialogue.summon1", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_SCAWY[0], 0.5f);
                    break;
                }
                case "summonMSG2": {
                    this.a(I18n.format("allie.dialogue.summon2", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_GIGGLE[this.getRNG().nextInt(4)]);
                    break;
                }
                case "summonMSG3": {
                    this.a(I18n.format("allie.dialogue.summon3", new Object[0]));
                    break;
                }
                case "summonMSG4": {
                    this.a(I18n.format("allie.dialogue.summon4", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_LIGHTBREATHING[2]);
                    break;
                }
                case "summonMSG5": {
                    this.a(I18n.format("allie.dialogue.summon5", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_HMPH[4]);
                    break;
                }
                case "summonMSG6": {
                    this.a(I18n.format("allie.dialogue.summon6", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_GIGGLE[3]);
                    break;
                }
                case "summonMSG7": {
                    this.a(I18n.format("allie.dialogue.summon7", new Object[0]));
                    break;
                }
                case "summonMSG8": {
                    this.a(I18n.format("allie.dialogue.summon8", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_HUH, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.openActionMenu(this.world.getPlayerEntityByUUID(this.getSexPlayerUuid()));
                    break;
                }
                case "summonDone": {
                    this.setCurrentAction(GirlAnimationState.SUMMON_WAIT);
                    break;
                }
                case "deepthroat_prepareMSG1": {
                    this.a(I18n.format("allie.dialogue.hihi", new Object[0]));
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_GIGGLE));
                    break;
                }
                case "deepthroat_prepareMSG2": {
                    this.a(I18n.format("allie.dialogue.boys", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_SIGH[0]);
                    break;
                }
                case "scream": {
                    this.a(ModSounds.MISC_SCREAM, new int[0]);
                    break;
                }
                case "blackscreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "deepthroat_prepareDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    if ("reverse_cowgirl".equals(this.DataManager.get(BlowjobStageKey))) {
                        this.rotationPitch = 30.0f;
                        this.setCurrentAction(GirlAnimationState.REVERSE_COWGIRL_START);
                        break;
                    }
                    this.setCurrentAction(GirlAnimationState.DEEPTHROAT_START);
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSexPromptReply(this.getGirlUuid(), this.getSexPlayerUuid(), false, true));
                    this.AimYaw = this.rotationYaw + 180.0f;
                    this.a(0.0, 0.0, (double)1.35f, 0.0f, 30.0f);
                    GuiHud.resetProgress();
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
                case "deepthroat_fastMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_BJMOAN));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "deepthroat_slowMSG1": {
                    if (this.getRNG().nextFloat() > 0.33f) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_LIPSOUND));
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_BJMOAN));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "deepthroat_cumMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_MOAN));
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_LIPSOUND));
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_CUMINFLATION), 1.5f);
                    break;
                }
                case "cowgirl_cumDone": 
                case "deepthroat_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    NetworkHandler.channel.sendToServer((IMessage)new PacketUploadInventory(this.getGirlUuid()));
                    break;
                }
                case "summon_normalMSG1": {
                    this.a(I18n.format("allie.dialogue.sup", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_GIGGLE[this.getRNG().nextInt(4)]);
                    break;
                }
                case "summon_normalMSG2": {
                    this.a(I18n.format("allie.dialogue.youhave", new Object[0]));
                    break;
                }
                case "summon_normalMSG3": {
                    if (this.DataManager.get(SpawnItemStack).getTagCompound().getInteger("sexmodUses") == 2) {
                        this.a(I18n.format("allie.dialogue.2wishes", new Object[0]));
                    } else {
                        this.a(I18n.format("allie.dialogue.1wish", new Object[0]));
                    }
                    this.a(ModSounds.GIRLS_ALLIE_HMPH[4]);
                    break;
                }
                case "summon_normalMSG4": {
                    this.a("So...");
                    break;
                }
                case "summon_normalMSG5": {
                    this.a(I18n.format("allie.dialogue.tellme", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_HUH, new int[0]);
                    break;
                }
                case "summon_normalDone": {
                    this.setCurrentAction(GirlAnimationState.SUMMON_NORMAL_WAIT);
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.openActionMenu(Minecraft.getMinecraft().player);
                    break;
                }
                case "deepthroat_normal_prepareMSG1": {
                    this.a(I18n.format("allie.dialogue.alright", new Object[0]));
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_GIGGLE));
                    break;
                }
                case "rich_MSG1": {
                    this.a(I18n.format("allie.dialogue.wishgranted", new Object[0]));
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_PLOB));
                    if (!this.isOwnedByLocalPlayer()) break;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketMakeRichWish(this.getPositionVector()));
                    break;
                }
                case "disappear": {
                    this.ConversionTimer = 0.99f;
                    break;
                }
                case "summon_sandMSG1": {
                    this.a(I18n.format("allie.dialogue.nooo", new Object[0]));
                    this.a(ModSounds.GIRLS_ALLIE_SCAWY[2]);
                    break;
                }
                case "summon_sandMSG2": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.b(I18n.format("allie.dialogue.phobia", new Object[0]), true);
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
                    int n = this.T;
                    do {
                        this.T = this.getRNG().nextInt(3) + 1;
                    } while (this.T == n);
                    break;
                }
                case "fastMoan": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.04f);
                    }
                    if (!this.M) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_MOAN));
                        this.M = true;
                        break;
                    }
                    this.M = false;
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
                    int n = this.L;
                    do {
                        this.L = this.getRNG().nextInt(3) + 1;
                    } while (this.L == n);
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
        animationData.addAnimationController(this.EyesController);
    }

    @Override
    public void performAction(String string, UUID uUID) {
        GirlAnimationState girlAnimationState;
        AllieNpc allie;
        String string2;
        String string3;
        AllieNpc allie2;
        block8: {
            GirlAnimationState girlAnimationState2;
            AllieNpc allie3;
            block10: {
                block9: {
                    try {
                        try {
                            this.MenuOpen = true;
                            if (!"action.names.makemerichallie".equals(string)) break block8;
                            allie3 = this;
                            if (!this.isFirstTimeWithItem()) break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                        girlAnimationState2 = GirlAnimationState.RICH_FIRST_TIME;
                        break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                }
                girlAnimationState2 = GirlAnimationState.RICH_NORMAL;
            }
            allie3.setCurrentAction(girlAnimationState2);
            return;
        }
        try {
            allie2 = this;
            string3 = "animationFollowUp";
            string2 = "action.names.deepthroat".equals(string) ? "deepthroat" : "reverse_cowgirl";
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            allie2.a(string3, string2);
            allie = this;
            girlAnimationState = this.isFirstTimeWithItem() ? GirlAnimationState.ALLIE_PREPARE_FIRST_TIME : GirlAnimationState.ALLIE_PREPARE_NORMAL;
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
            allie.setCurrentAction(girlAnimationState);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

