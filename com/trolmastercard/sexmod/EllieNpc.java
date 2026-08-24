/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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
public class EllieNpc
extends InventoryGirlEntity
implements VoidCallback {
    static final float SoundVolume = 10.0f;
    static final int ao = 16;
    static final int ap = 79;
    static final int ag = 109;
    static final int as = 150;
    static final int ar = 20;
    static final int ab = 110;
    static final int an = 4;
    int CarryIntroTicks = -1;
    boolean aq = false;
    boolean PendingMenu = false;
    boolean PhysicsInit = false;
    int StartSexCountdown = -1;
    int HugCountdown = -1;
    int SitCountdown = -1;
    int HugSitCountdown = -1;
    boolean IsSitting = false;
    Object[] BedTarget;
    int DashCountdown = -1;
    int aa = 1;
    boolean aj = false;

    public EllieNpc(World world) {
        super(world);
        this.P = -85;
        this.O = -175;
        this.K = -85;
        this.V = new Vec3d(-0.1, 0.05, 0.0);
    }

    @Override
    public void c() {
        this.a("Okay, I will be residing here then..");
        this.a(ModSounds.GIRLS_ELLIE_HUH[0], 6.0f);
    }

    @Override
    public String c() {
        return "Ellie";
    }

    @Override
    protected ResourceLocation getLootTable() {
        return GirlLootTables.EllieLootTable;
    }

    boolean i() {
        boolean flag;
        try {
            if (this.updateCarryHud()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            flag = this.world.getBlockState(this.getPosition().add(0, 2, 0)).getBlock() != Blocks.AIR;
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        return flag;
    }

    public float getEyeHeight() {
        float f;
        try {
            f = this.getRenderLabelOffset() ? 1.53f : 1.9f;
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        return f;
    }

    @Override
    public float i() {
        return 0.4f;
    }

    @Override
    public void b() {
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                this.getGirlUuid();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                this.getGirlUuid();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        float f = entityPlayer.rotationYaw - 180.0f;
        this.b(f);
        this.setCurrentAction(GirlAnimationState.CARRY_INTRO);
        this.a(true);
    }

    @Override
    public boolean t() {
        try {
            if (this.getCurrentAction() == GirlAnimationState.CARRY_INTRO) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        return true;
    }

    public boolean a(EntityPlayer entityPlayer, boolean flag) {
        try {
            if (flag) {
                EllieNpc.openActionMenu(entityPlayer, this, new String[]{"action.names.cowgirl", "action.names.missionary"}, false);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if ((Integer)this.DataManager.get(D) == 0) {
                EllieNpc.openActionMenu(entityPlayer, this, new String[]{"action.names.dressup"}, true);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        EllieNpc.openActionMenu(entityPlayer, this, new String[]{"Face fuck"}, true);
        return true;
    }

    @Override
    public void x() {
        super.resetMasterAndWalkSpeed();
        this.a("stay safe darling~");
        this.a(ModSounds.GIRLS_ELLIE_SIGH[1], 6.0f);
    }

    @Override
    public void a(String string, UUID uuid) {
        super.a(string, uuid);
        this.aq = true;
        switch (string) {
            case "action.names.missionary": {
                this.setCurrentAction(GirlAnimationState.HUGSELECTED);
                this.a("animationFollowUp", "Missionary");
                break;
            }
            case "action.names.cowgirl": {
                this.setCurrentAction(GirlAnimationState.HUGSELECTED);
                this.a("animationFollowUp", "cowgirl");
                break;
            }
            case "action.names.dressup": 
            case "action.names.strip": {
                this.setCurrentAction(GirlAnimationState.STRIP);
                this.a("animationFollowUp", "");
                break;
            }
            case "Face fuck": {
                this.a(true, true, uuid);
                AnimationInputLock.setAnimationLocked(false);
            }
        }
    }

    @Override
    protected void a(EntityPlayerMP entityPlayerMP, boolean flag) {
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block30: {
            GirlAnimationState girlAnimationState2;
            block28: {
                block26: {
                    block25: {
                        girlAnimationState2 = this.getCurrentAction();
                        try {
                            try {
                                if (girlAnimationState != GirlAnimationState.HUGSELECTED || this.world.isRemote) break block25;
                            }
                            catch (RuntimeException runtimeException) {
                                throw EllieNpc.rethrow(runtimeException);
                            }
                            this.HugSitCountdown = 79;
                        }
                        catch (RuntimeException runtimeException) {
                            throw EllieNpc.rethrow(runtimeException);
                        }
                    }
                    try {
                        block27: {
                            try {
                                try {
                                    if (girlAnimationState2 != GirlAnimationState.MISSIONARY_CUM) break block26;
                                    if (girlAnimationState == GirlAnimationState.MISSIONARY_FAST) break block27;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw EllieNpc.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.MISSIONARY_SLOW) break block26;
                            }
                            catch (RuntimeException runtimeException) {
                                throw EllieNpc.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw EllieNpc.rethrow(runtimeException);
                    }
                }
                try {
                    block29: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.COWGIRLCUM) break block28;
                                if (girlAnimationState == GirlAnimationState.COWGIRLSLOW) break block29;
                            }
                            catch (RuntimeException runtimeException) {
                                throw EllieNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.COWGIRLFAST) break block28;
                        }
                        catch (RuntimeException runtimeException) {
                            throw EllieNpc.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw EllieNpc.rethrow(runtimeException);
                }
            }
            try {
                block31: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.CARRY_CUM) break block30;
                            if (girlAnimationState == GirlAnimationState.CARRY_SLOW) break block31;
                        }
                        catch (RuntimeException runtimeException) {
                            throw EllieNpc.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.CARRY_FAST) break block30;
                    }
                    catch (RuntimeException runtimeException) {
                        throw EllieNpc.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
        }
        try {
            if (girlAnimationState == GirlAnimationState.CARRY_INTRO) {
                this.CarryIntroTicks = 0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        super.setCurrentAction(girlAnimationState);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        try {
            super.onUpdate();
            if (this.PendingMenu) {
                this.a((EntityPlayer)Minecraft.getMinecraft().player, true);
                this.PendingMenu = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.m();
        this.updateCarryHud();
    }

    void updateCarryHud() {
        try {
            if (GuiHud.isHudVisible()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.CARRY_SLOW) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        GuiHud.showHud();
    }

    void updateCarryIntro() {
        try {
            if (this.CarryIntroTicks == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (++this.CarryIntroTicks < 110) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            this.CarryIntroTicks = -1;
            if (this.getCurrentAction() != GirlAnimationState.CARRY_INTRO) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        float f = this.I().floatValue();
        Vec3d vec3d = this.initPhysics().add(VectorMath.rotateYaw(new Vec3d(0.0, (double)(2.5625f - entityPlayer.getEyeHeight()), -0.3125), 180.0f + f));
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    void m() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.SITDOWNIDLE) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.getDistance((Entity)entityPlayer) > 1.5f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (entityPlayer.getPersistentID().equals(Minecraft.getMinecraft().player.getPersistentID())) {
                GuiTransitionScreen.startTransition();
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        this.initPhysics();
        this.d();
        this.isOwnedByLocalPlayer();
        this.getWalkState();
        this.getRenderPosition();
        this.a();
        this.t();
        this.u();
    }

    void initPhysics() {
        try {
            if (this.PhysicsInit) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.PhysicsInit = true;
        this.noClip = false;
        this.setNoGravity(false);
    }

    @Override
    protected void U() {
        Vec3d vec3d;
        Vec3d vec3d2;
        EntityPlayer entityPlayer;
        UUID uUID;
        String string = (String)this.DataManager.get(h);
        if ("Missionary".equals(string)) {
            this.DataManager.set(D, (Object)0);
            this.setCurrentAction(GirlAnimationState.MISSIONARY_START);
            uUID = this.getSexPlayerUuid();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
            entityPlayer = this.world.getPlayerEntityByUUID(uUID);
            try {
                if (entityPlayer == null) {
                    this.resetAimTarget();
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
            entityPlayer.setNoGravity(true);
            entityPlayer.noClip = true;
            vec3d2 = this.initPhysics();
            entityPlayer.rotationYaw = this.I().floatValue();
            vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 0.1), entityPlayer.rotationYaw);
            vec3d2 = vec3d2.add(vec3d);
            entityPlayer.setPositionAndUpdate(vec3d2.x, vec3d2.y, vec3d2.z);
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
        }
        if ("cowgirl".equals(string)) {
            this.DataManager.set(D, (Object)0);
            this.setCurrentAction(GirlAnimationState.COWGIRLSTART);
            uUID = this.getSexPlayerUuid();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
            entityPlayer = this.world.getPlayerEntityByUUID(uUID);
            try {
                if (entityPlayer == null) {
                    this.resetAimTarget();
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
            entityPlayer.setNoGravity(true);
            entityPlayer.noClip = true;
            vec3d2 = this.initPhysics();
            entityPlayer.rotationYaw = this.I().floatValue() + 180.0f;
            vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 1.0 - (double)entityPlayer.eyeHeight, -1.8125), entityPlayer.rotationYaw);
            vec3d2 = vec3d2.add(vec3d);
            entityPlayer.setPositionAndUpdate(vec3d2.x, vec3d2.y, vec3d2.z);
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
        }
    }

    void u() {
        try {
            if (--this.StartSexCountdown != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.U();
    }

    void t() {
        block10: {
            try {
                try {
                    if (this.getCurrentAction() == GirlAnimationState.SITDOWNIDLE && this.StartSexCountdown < 0) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw EllieNpc.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
        }
        EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.getDistance((Entity)entityPlayer) > 1.5f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.StartSexCountdown = 20;
        this.updateCarryIntro(entityPlayer.getPersistentID());
    }

    void a() {
        try {
            if (--this.HugCountdown != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.setCurrentAction(GirlAnimationState.HUGIDLE);
    }

    void j() {
        try {
            if (--this.SitCountdown != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.setCurrentAction(GirlAnimationState.SITDOWNIDLE);
    }

    void q() {
        block16: {
            try {
                try {
                    if (--this.HugSitCountdown == 0 || this.IsSitting) break block16;
                }
                catch (RuntimeException runtimeException) {
                    throw EllieNpc.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
        }
        try {
            this.IsSitting = true;
            this.DataManager.set(G, (Object)false);
            this.setCurrentAction(GirlAnimationState.NULL);
            this.noClip = false;
            this.setNoGravity(false);
            if (this.BedTarget == null) {
                this.BedTarget = this.noop();
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.BedTarget == null) {
                this.updateCarryHud("no bed in sight...");
                this.world.playSound(null, this.getPosition(), ModSounds.GIRLS_ELLIE_SIGH[0], SoundCategory.NEUTRAL, 6.0f, 1.0f);
                this.s();
                this.getGirlUuid();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer != null) {
                entityPlayer.setNoGravity(false);
                entityPlayer.noClip = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = (Vec3d)this.BedTarget[0];
        int i = (Integer)this.BedTarget[1];
        try {
            if (vec3d.distanceTo(this.getPositionVector()) > 1.0) {
                this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, (double)0.35f);
                this.k();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.setTargetPos(vec3d);
        this.b(i);
        this.setCurrentAction(GirlAnimationState.SITDOWN);
        this.DataManager.set(G, (Object)true);
        this.SitCountdown = 109;
        this.noClip = true;
        this.setNoGravity(true);
        this.IsSitting = false;
        this.BedTarget = null;
    }

    @Override
    public void g() {
        super.noop();
        this.HugCountdown = -1;
    }

    Object[] g() {
        Vec3d vec3d;
        BlockPos blockPos;
        int i = -1;
        int i3 = 0;
        Vec3d[][] vec3dArrayArray = new Vec3d[][]{{new Vec3d(0.5, 0.0, -0.18), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0)}, {new Vec3d(0.5, 0.0, 1.18), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, 0.0, -1.0)}, {new Vec3d(-0.18, 0.0, 0.5), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0)}, {new Vec3d(1.18, 0.0, 0.5), new Vec3d(1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 0.0)}};
        int[] nArray = new int[]{0, 180, -90, 90};
        do {
            if ((blockPos = this.a(this.getPosition(), ++i3)) == null) {
                return null;
            }
            vec3d = new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
            for (int i4 = 0; i4 < vec3dArrayArray.length; ++i4) {
                block7: {
                    Vec3d vec3d2 = vec3d.add(vec3dArrayArray[i4][1]);
                    Block block = this.world.getBlockState(new BlockPos(vec3d2.x, vec3d2.y, vec3d2.z)).getBlock();
                    Vec3d vec3d3 = vec3d.add(vec3dArrayArray[i4][2]);
                    Block block2 = this.world.getBlockState(new BlockPos(vec3d3.x, vec3d3.y, vec3d3.z)).getBlock();
                    try {
                        try {
                            if (block != Blocks.AIR || block2 != Blocks.BED) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw EllieNpc.rethrow(runtimeException);
                        }
                        if (i != -1) break block7;
                    }
                    catch (RuntimeException runtimeException) {
                        throw EllieNpc.rethrow(runtimeException);
                    }
                    i = i4;
                    continue;
                }
                double d = this.getPosition().distanceSq(vec3d.add((Vec3d)vec3dArrayArray[i][0]).x, vec3d.add((Vec3d)vec3dArrayArray[i][0]).y, vec3d.add((Vec3d)vec3dArrayArray[i][0]).z);
                double d2 = this.getPosition().distanceSq(vec3d.add((Vec3d)vec3dArrayArray[i4][0]).x, vec3d.add((Vec3d)vec3dArrayArray[i4][0]).y, vec3d.add((Vec3d)vec3dArrayArray[i4][0]).z);
                if (!(d2 < d)) continue;
                i = i4;
            }
        } while (i == -1);
        blockPos = vec3d.add(vec3dArrayArray[i][0]);
        return new Object[]{blockPos, nArray[i]};
    }

    void d() {
        try {
            if (this.getActivePotionEffect(PotionHandler.b) == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        this.removeActivePotionEffect(PotionHandler.b);
        this.updateCarryIntro(entityPlayer.getPersistentID());
        float f = (float)(Math.atan2(this.posZ - entityPlayer.posZ, this.posX - entityPlayer.posX) * 57.29577951308232);
        this.b(f);
        this.setTargetPos(this.getPositionVector());
        this.DataManager.set(G, (Object)true);
        this.setCurrentAction(GirlAnimationState.DASH);
        this.DashCountdown = 16;
        this.setNoGravity(true);
        this.noClip = true;
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
        this.tasks.removeTask((EntityAIBase)this.WanderAI);
        this.tasks.removeTask((EntityAIBase)this.WatchPlayerAI);
    }

    void n() {
        try {
            if (--this.DashCountdown != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                this.getGirlUuid();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                this.getGirlUuid();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, -0.5), entityPlayer.rotationYaw);
        Vec3d vec3d2 = vec3d.add(entityPlayer.getPositionVector());
        this.setTargetPos(vec3d2);
        this.b(entityPlayer.rotationYaw);
        this.setCurrentAction(GirlAnimationState.HUG);
        this.HugCountdown = 150;
    }

    void f() {
        this.DataManager.set(G, (Object)false);
        this.setCurrentAction(GirlAnimationState.NULL);
        this.updateCarryIntro((UUID)null);
        this.noClip = false;
        this.setNoGravity(false);
        this.IsSitting = false;
        this.HugCountdown = -1;
        this.DashCountdown = -1;
        this.HugSitCountdown = -1;
        this.BedTarget = null;
    }

    protected boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        try {
            if (EllieNpc.getByPlayerUuid(entityPlayer) != null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.getSexPlayerUuid() != null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.world.isRemote) {
                this.a(entityPlayer, false);
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        return true;
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
                            throw EllieNpc.rethrow(runtimeException);
                        }
                        return GirlAnimationState.COWGIRLCUM;
                    }
                    catch (RuntimeException runtimeException) {
                        throw EllieNpc.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.MISSIONARY_FAST && girlAnimationState != GirlAnimationState.MISSIONARY_SLOW) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw EllieNpc.rethrow(runtimeException);
                    }
                    return GirlAnimationState.MISSIONARY_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw EllieNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CARRY_SLOW && girlAnimationState != GirlAnimationState.CARRY_FAST) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw EllieNpc.rethrow(runtimeException);
                }
                return GirlAnimationState.CARRY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw EllieNpc.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.COWGIRLSLOW) {
                return GirlAnimationState.COWGIRLFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.MISSIONARY_SLOW) {
                return GirlAnimationState.MISSIONARY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.CARRY_SLOW) {
                return GirlAnimationState.CARRY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return null;
        }
        block5: switch (animEvent.getController().getName()) {
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
                double d = Math.abs(this.prevPosX - this.posX) + Math.abs(this.prevPosZ - this.posZ);
                if (d == 0.0) {
                    this.a(this.i() ? "animation.ellie.crouchidle" : "animation.ellie.idle", true, animEvent);
                    break;
                }
                if (this.i()) {
                    this.a("animation.ellie.crouchwalk", true, animEvent);
                    break;
                }
                switch (this.getWalkState()) {
                    case RUN: {
                        this.a("animation.ellie.run", true, animEvent);
                        break;
                    }
                    case FAST_WALK: {
                        this.a("animation.ellie.fastwalk", true, animEvent);
                        break;
                    }
                    case WALK: {
                        this.a("animation.ellie.walk", true, animEvent);
                    }
                }
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
                        this.a("animation.ellie.carry_slow" + this.aa, true, animEvent);
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
                this.initAnimationControllers();
            }
        }
        catch (RuntimeException runtimeException) {
            throw EllieNpc.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "becomeNude": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.a("currentModel", (Integer)this.DataManager.get(OutfitIndexKey) == 1 ? "0" : "1");
                    break;
                }
                case "stripDone": {
                    this.setCurrentAction((GirlAnimationState)null);
                    this.resetAimTarget();
                    this.U();
                    break;
                }
                case "hugMSG2": {
                    this.h("Hmm...");
                    this.a(ModSounds.GIRLS_ELLIE_HMPH[3], 6.0f);
                    break;
                }
                case "hugMSG3": {
                    this.h("Hey!");
                    this.a(ModSounds.GIRLS_ELLIE_HUH[1], 1.0f);
                    break;
                }
                case "hugMSG4": {
                    this.h(I18n.format("ellie.dialogue.mommyhorny", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_MOMMYHORNY, 0.5f);
                    break;
                }
                case "hugMSG5": {
                    this.h(I18n.format("ellie.dialogue.whattodo", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_HUH[1], 6.0f);
                    break;
                }
                case "hugDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.a(Minecraft.getMinecraft().player, true);
                    break;
                }
                case "hugselectedMSG1": {
                    this.h(I18n.format("ellie.dialogue.iknow", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_HMPH[3], 6.0f);
                    break;
                }
                case "hugselectedMSG2": {
                    this.h(I18n.format("ellie.dialogue.followmedarling", new Object[0]));
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[3], 6.0f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    AnimationInputLock.setAnimationLocked(true);
                    break;
                }
                case "sitdownMSG1": {
                    this.a(ModSounds.GIRLS_ELLIE_COMETOMOMMY, 0.5f);
                    if (!this.isLocalPlayerNearby()) break;
                    this.h(I18n.format("ellie.dialogue.cometomommy", new Object[0]));
                    break;
                }
                case "cowgirlStartMSG0": {
                    this.a(ModSounds.GIRLS_ELLIE_GIGGLE[4], 6.0f);
                    break;
                }
                case "cowgirlStartMSG1": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.a(I18n.format("ellie.dialogue.like", new Object[0]));
                    GuiHud.resetProgress();
                    break;
                }
                case "cowgirlStartMSG2": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 6.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "cowgirlStartDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.setCurrentAction(GirlAnimationState.COWGIRLSLOW);
                    GuiHud.showHud();
                    break;
                }
                case "cowgirlfastMSG1": {
                    if (this.aj) {
                        this.aj = false;
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 6.0f);
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "cowgirlfastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.COWGIRLSLOW);
                    break;
                }
                case "cowgirlfastdomMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.2);
                    break;
                }
                case "cowgirlcumMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 6.0f);
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
                    this.a(ModSounds.GIRLS_ELLIE_GOODBOY, 0.5f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.a(I18n.format("ellie.dialogue.goodboy", new Object[0]));
                    break;
                }
                case "cowgirlcumMSG6": 
                case "blackScreen": {
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
                case "attackSound": {
                    this.a(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG);
                    break;
                }
                case "attackDone": {
                    this.setCurrentAction(GirlAnimationState.NULL);
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "openSexUi": {
                    if (!this.isLocalPlayerNearby()) break;
                    GuiHud.showHud();
                    break;
                }
                case "missionary_slowMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (this.getRNG().nextBoolean() && this.getRNG().nextBoolean()) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_MOAN), 6.0f);
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 6.0f);
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "missionary_fastMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (this.getRNG().nextBoolean() || this.getRNG().nextBoolean()) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_MOAN), 6.0f);
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 6.0f);
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.05);
                    break;
                }
                case "missionary_startDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.setCurrentAction(GirlAnimationState.MISSIONARY_SLOW);
                    GuiHud.showHud();
                    break;
                }
                case "missionary_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.MISSIONARY_SLOW);
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
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ELLIE_AHH), 6.0f);
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
                    int n = this.aa;
                    do {
                        this.aa = this.getRNG().nextInt(4) + 1;
                    } while (this.aa == n);
                    break;
                }
                case "carry_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.CARRY_SLOW);
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

