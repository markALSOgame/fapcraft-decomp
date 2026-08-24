/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
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

public class ElliePlayer
extends PlayerGirlEntity {
    boolean ar = false;
    boolean aq = false;
    int ap = 1;

    protected ElliePlayer(World world) {
        super(world);
    }

    public ElliePlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 2.05f;
    }

    public float getEyeHeight() {
        float f;
        try {
            f = this.a() ? 1.53f : 1.9f;
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        return f;
    }

    @Override
    public void sitDown() {
        this.b(GirlAnimationState.SITDOWN);
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("Face fuck".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.CARRY_INTRO);
                this.a(this.getOutfitIndex(), GirlAnimationState.CARRY_INTRO);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube6();
    }

    @Override
    public String getHandTexture(int i) {
        try {
            if (i == 0) {
                return "textures/entity/ellie/hand_nude.png";
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        return "textures/entity/ellie/hand.png";
    }

    @Override
    public boolean isCustomAnimatable() {
        return true;
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            if ("action.names.cowgirl".equals(string)) {
                this.a("animationFollowUp", "Cowgirl");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if ("action.names.missionary".equals(string)) {
                this.a("animationFollowUp", "Missionary");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if (!((Optional)this.DataManager.get(BoundPlayerKey)).isPresent()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        NetworkHandler.channel.sendToServer((IMessage)new PacketSexPrompt(string, uUID, (UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get(), this.Accept));
        this.Accept = true;
    }

    @Override
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        ElliePlayer.openActionMenu(entityPlayer, this, new String[]{"Face fuck"}, false);
        return true;
    }

    void openActionMenu(EntityPlayer entityPlayer) {
        ElliePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.cowgirl", "action.names.missionary"}, false);
    }

    @Override
    public boolean isMenuOpenable() {
        return false;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block14: {
            GirlAnimationState girlAnimationState2;
            block12: {
                girlAnimationState2 = this.getCurrentAction();
                try {
                    block13: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.MISSIONARY_CUM) break block12;
                                if (girlAnimationState == GirlAnimationState.MISSIONARY_FAST) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ElliePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.MISSIONARY_SLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.COWGIRLCUM) break block14;
                            if (girlAnimationState == GirlAnimationState.COWGIRLSLOW) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.COWGIRLFAST) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ElliePlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.COWGIRLSLOW) {
                return GirlAnimationState.COWGIRLFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.MISSIONARY_SLOW) {
                return GirlAnimationState.MISSIONARY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.CARRY_SLOW) {
                return GirlAnimationState.CARRY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
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
                            if (girlAnimationState != GirlAnimationState.COWGIRLFAST && girlAnimationState != GirlAnimationState.COWGIRLSLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                        return GirlAnimationState.COWGIRLCUM;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.MISSIONARY_FAST && girlAnimationState != GirlAnimationState.MISSIONARY_SLOW) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.MISSIONARY_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CARRY_SLOW && girlAnimationState != GirlAnimationState.CARRY_FAST) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.CARRY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw ElliePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        if (this.getCurrentAction() == GirlAnimationState.SITDOWNIDLE) {
            EntityPlayer entityPlayer;
            String string;
            block12: {
                block11: {
                    string = (String)this.DataManager.get(GirlEntity.BlowjobStageKey);
                    try {
                        try {
                            if ("Missionary".equals(string) || "Cowgirl".equals(string)) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                }
                entityPlayer = this.getRenderPosition();
                try {
                    try {
                        if (entityPlayer != null && !(entityPlayer.getDistance(this.getPositionVector().x, this.getPositionVector().y, this.getPositionVector().z) > 1.0)) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
            }
            this.DataManager.set(GirlEntity.BlowjobStageKey, (Object)"");
            this.DataManager.set(GirlEntity.OutfitIndexKey, (Object)0);
            this.hasGirl(entityPlayer.getPersistentID());
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.world.getPlayerEntityByUUID((UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get());
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), entityPlayerMP);
            entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
            entityPlayerMP.capabilities.isFlying = true;
            entityPlayer.capabilities.isFlying = true;
            entityPlayerMP.noClip = true;
            entityPlayer.noClip = true;
            entityPlayerMP.setNoGravity(true);
            entityPlayer.setNoGravity(true);
            if ("Missionary".equals(string)) {
                this.b(GirlAnimationState.MISSIONARY_START);
                Vec3d vec3d = this.getCustomName().subtract(0.0, 0.1, 0.0);
                entityPlayer.setPositionAndRotation(vec3d.x, vec3d.y, vec3d.z, this.I().floatValue(), 60.0f);
                entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
            } else {
                this.b(GirlAnimationState.COWGIRLSTART);
                Vec3d vec3d = this.getCustomName().add(new Vec3d(-Math.sin((double)this.I().floatValue() * (Math.PI / 180)) * 1.8, -0.65, Math.cos((double)this.I().floatValue() * (Math.PI / 180)) * 1.8));
                entityPlayer.setPositionAndRotation(vec3d.x, vec3d.y, vec3d.z, 180.0f + this.I().floatValue(), -30.0f);
                entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
            }
        }
    }

    boolean a() {
        boolean flag;
        EntityPlayer entityPlayer = this.getBoundPlayer();
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            flag = this.world.getBlockState(entityPlayer.getPosition().up().up()).getBlock() != Blocks.AIR;
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.a("animation.ellie.null", true, animEvent);
                    break;
                }
                this.a("animation.ellie.eyes", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.a("animation.ellie.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.a("animation.ellie.ride", true, animEvent);
                    break;
                }
                if (this.MovementController.getCurrentAnimation() != null && this.MovementController.getCurrentAnimation().animationName.contains("fly") && this.af) {
                    boolean bl = this.ar = !this.ar;
                }
                if (!this.af) {
                    this.a("animation.ellie.fly" + (this.ar ? "2" : ""), true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) > 0.0f) {
                    if (this.aj) {
                        this.MovementController.setAnimationSpeed(1.5);
                        this.a(this.a() ? "animation.ellie.crouchwalk" : "animation.ellie.run", true, animEvent);
                        break;
                    }
                    if (this.ao.y >= -0.1f) {
                        this.MovementController.setAnimationSpeed(2.0);
                        this.a(this.a() ? "animation.ellie.crouchwalk" : "animation.ellie.fastwalk", true, animEvent);
                        break;
                    }
                    this.MovementController.setAnimationSpeed(1.5);
                    this.a(this.a() ? "animation.ellie.crouchwalk" : "animation.ellie.backwards_walk", true, animEvent);
                    break;
                }
                this.a(this.a() ? "animation.ellie.crouchidle" : "animation.ellie.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.a("animation.ellie.null", true, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.a("animation.ellie.strip", false, animEvent);
                        break block5;
                    }
                    case DASH: {
                        this.a("animation.ellie.dash", false, animEvent);
                        break block5;
                    }
                    case HUG: {
                        this.a("animation.ellie.hug", false, animEvent);
                        break block5;
                    }
                    case HUGIDLE: {
                        this.a("animation.ellie.hugidle", true, animEvent);
                        break block5;
                    }
                    case HUGSELECTED: {
                        this.a("animation.ellie.hugselected", false, animEvent);
                        break block5;
                    }
                    case SITDOWN: {
                        this.a("animation.ellie.sitdown", false, animEvent);
                        break block5;
                    }
                    case SITDOWNIDLE: {
                        this.a("animation.ellie.sitdownidle", true, animEvent);
                        break block5;
                    }
                    case COWGIRLSTART: {
                        this.a("animation.ellie.cowgirlstart", false, animEvent);
                        break block5;
                    }
                    case COWGIRLSLOW: {
                        this.a("animation.ellie.cowgirlslow2", true, animEvent);
                        break block5;
                    }
                    case COWGIRLFAST: {
                        this.a("animation.ellie.cowgirlfast", true, animEvent);
                        break block5;
                    }
                    case COWGIRLCUM: {
                        this.a("animation.ellie.cowgirlcum", true, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.a("animation.ellie.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.a("animation.ellie.bowcharge", false, animEvent);
                        break block5;
                    }
                    case RIDE: {
                        this.a("animation.ellie.ride", true, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.a("animation.ellie.sit", true, animEvent);
                        break block5;
                    }
                    case THROW_PEARL: {
                        this.a("animation.ellie.throwpearl", false, animEvent);
                        break block5;
                    }
                    case DOWNED: {
                        this.a("animation.ellie.downed", true, animEvent);
                        break block5;
                    }
                    case MISSIONARY_START: {
                        this.a("animation.ellie.missionary_start", false, animEvent);
                        break block5;
                    }
                    case MISSIONARY_SLOW: {
                        this.a("animation.ellie.missionary_slow", true, animEvent);
                        break block5;
                    }
                    case MISSIONARY_FAST: {
                        this.a("animation.ellie.missionary_fast", true, animEvent);
                        break block5;
                    }
                    case MISSIONARY_CUM: {
                        this.a("animation.ellie.missionary_cum", false, animEvent);
                        break block5;
                    }
                    case CARRY_INTRO: {
                        this.a("animation.ellie.carry_intro", false, animEvent);
                        break block5;
                    }
                    case CARRY_SLOW: {
                        this.a("animation.ellie.carry_slow" + this.ap, true, animEvent);
                        break block5;
                    }
                    case CARRY_FAST: {
                        this.a("animation.ellie.carry_fast", true, animEvent);
                        break block5;
                    }
                    case CARRY_CUM: {
                        this.a("animation.ellie.carry_cum", true, animEvent);
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
                this.isCustomAnimatable();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "dashMSG1": {
                    float f;
                    EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity(this, 15.0);
                    if (entityPlayer == null) break;
                    Vec3d vec3d = this.getPositionVector().subtract(entityPlayer.getPositionVector());
                    this.rotationYaw = f = (float)Math.atan2(vec3d.z, vec3d.x) * 57.29578f;
                    this.rotationYawHead = f;
                    this.renderYawOffset = f;
                    break;
                }
                case "dashReady": {
                    if (!this.isLocalPlayerNearby()) break;
                    break;
                }
                case "dashDone": {
                    float f;
                    this.b(GirlAnimationState.HUG);
                    EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity(this, 15.0);
                    if (entityPlayer == null) break;
                    this.rotationYaw = f = entityPlayer.rotationYaw;
                    this.rotationYawHead = f;
                    this.renderYawOffset = f;
                    break;
                }
                case "hugMSG1": {
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    if (!entityPlayerSP.getPersistentID().equals(this.getSexPlayerUuid()) && !entityPlayerSP.getUniqueID().equals(this.getSexPlayerUuid())) break;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(entityPlayerSP.getUniqueID().toString(), entityPlayerSP.getPositionVector(), entityPlayerSP.rotationYaw - 80.0f, entityPlayerSP.rotationPitch));
                    break;
                }
                case "hugMSG2": {
                    this.h("Hmm...");
                    this.a(ModSounds.GIRLS_ELLIE_HMPH[3], 3.0f);
                    break;
                }
                case "hugMSG3": {
                    this.h("Hey!");
                    this.a(ModSounds.GIRLS_ELLIE_AHH[2], 3.0f);
                    break;
                }
                case "hugMSG4": {
                    this.h(I18n.format("ellie.dialogue.mommyhorny", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[0], 3.0f);
                    break;
                }
                case "hugMSG5": {
                    this.h(I18n.format("ellie.dialogue.whattodo", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_HUH[1], 3.0f);
                    break;
                }
                case "hugDone": {
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    if (!entityPlayerSP.getPersistentID().equals(this.getSexPlayerUuid())) break;
                    this.b(GirlAnimationState.HUGIDLE);
                    this.openActionMenu((EntityPlayer)entityPlayerSP);
                    break;
                }
                case "hugselectedMSG1": {
                    this.h(I18n.format("ellie.dialogue.iknow", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_MMM[0], 3.0f);
                    break;
                }
                case "hugselectedMSG2": {
                    this.h(I18n.format("ellie.dialogue.followmedarling", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[3], 3.0f);
                    break;
                }
                case "hugselectedDone": {
                    if (!this.isLocalPlayerNearby()) break;
                    Vec3d vec3d = this.getPositionVector();
                    vec3d = vec3d.add(-Math.sin((double)(this.rotationYaw + 90.0f) * (Math.PI / 180)) * -0.7803124785423279, 0.0, Math.cos((double)(this.rotationYaw + 90.0f) * (Math.PI / 180)) * -0.7803124785423279);
                    vec3d = vec3d.add(-Math.sin((double)this.rotationYaw * (Math.PI / 180)) * 0.5296875238418579, 0.0, Math.cos((double)this.rotationYaw * (Math.PI / 180)) * 0.5296875238418579);
                    String string = vec3d.x + "f" + vec3d.y + "f" + vec3d.z + "f";
                    NetworkHandler.channel.sendToServer((IMessage)new PacketUpdateGirl(this.getGirlUuid(), "targetPos", string));
                    this.resetAimTarget();
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendGirlToSex(this.getGirlUuid()));
                    this.b(GirlAnimationState.NULL);
                    break;
                }
                case "sitdownMSG1": {
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[3], 3.0f);
                    if (!this.isLocalPlayerNearby()) break;
                    this.h(I18n.format("ellie.dialogue.cometomommy", new Object[0]));
                    break;
                }
                case "sitdownDone": {
                    if (!this.isBoundToLocalPlayer()) break;
                    this.b(GirlAnimationState.SITDOWNIDLE);
                    this.openActionMenu(this.world.getPlayerEntityByUUID(this.getBoundPlayerUuid()));
                    break;
                }
                case "missionary_startDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.b(GirlAnimationState.MISSIONARY_SLOW);
                    GuiHud.showHud();
                    break;
                }
                case "cowgirlStartMSG0": {
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[4], 3.0f);
                    break;
                }
                case "cowgirlStartMSG1": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.a(I18n.format("ellie.dialogue.like", new Object[0]));
                    GuiHud.resetProgress();
                    break;
                }
                case "cowgirlStartMSG2": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 3.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "cowgirlStartDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.b(GirlAnimationState.COWGIRLSLOW);
                    GuiHud.showHud();
                    break;
                }
                case "cowgirlfastMSG1": {
                    if (this.aq) {
                        this.aq = false;
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 3.0f);
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "cowgirlfastReady": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    if (!AnimationInputLock.SneakPressed) {
                        this.b(GirlAnimationState.COWGIRLSLOW);
                        break;
                    }
                    if (ModConstants.Random.nextInt(4) == 1) break;
                    this.ActionController.clearAnimationCache();
                    break;
                }
                case "cowgirlfastdomMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.2);
                    break;
                }
                case "cowgirlcumMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 3.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    break;
                }
                case "cowgirlcumMSG2": {
                    this.a(ModSounds.GIRLS_ELLIE_MOAN[5], 3.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    break;
                }
                case "cowgirlcumMSG3": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    break;
                }
                case "cowgirlcumMSG4": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.forceShowHud();
                    break;
                }
                case "cowgirlcumMSG5":
                case "missionary_cumMSG2": {
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[4], 3.0f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.a(I18n.format("ellie.dialogue.goodboy", new Object[0]));
                    break;
                }
                case "cowgirlcumMSG6": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "missionary_cumDone":
                case "cowgirlcumDone":
                case "carry_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                    break;
                }
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "openSexUi": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "missionary_slowMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (this.getRNG().nextBoolean() && this.getRNG().nextBoolean()) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_MOAN), 3.0f);
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 3.0f);
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "missionary_fastMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (this.getRNG().nextBoolean() || this.getRNG().nextBoolean()) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_MOAN), 3.0f);
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 3.0f);
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.05);
                    break;
                }
                case "missionary_fastDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    if (AnimationInputLock.SneakPressed) {
                        this.b(GirlAnimationState.MISSIONARY_FAST);
                        break;
                    }
                    this.b(GirlAnimationState.MISSIONARY_SLOW);
                    break;
                }
                case "bedRustle": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    this.a(ModSounds.MISC_BEDRUSTLE[0]);
                    break;
                }
                case "bedRustle1": {
                    this.a(ModSounds.MISC_BEDRUSTLE[1]);
                    break;
                }
                case "missionary_cumMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 3.0f);
                    break;
                }
                case "carry_introMSG1": {
                    this.a("I'm hungry..");
                    this.a(ModSounds.GIRLS_ELLIE_HMPH, 6.0f);
                    break;
                }
                case "carry_introMSG2": {
                    this.a("heh~");
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[3], 6.0f);
                    break;
                }
                case "lipsound": {
                    this.a(ModSounds.GIRLS_ALLIE_LIPSOUND, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_INSERTS, 6.0f);
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    break;
                }
                case "pound": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "carry_slowDone": {
                    int n = this.ap;
                    do {
                        this.ap = this.getRNG().nextInt(4) + 1;
                    } while (this.ap == n);
                    break;
                }
                case "carry_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.CARRY_SLOW);
                    break;
                }
                case "sexUI": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
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

