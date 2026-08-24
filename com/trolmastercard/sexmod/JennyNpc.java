/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWater
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
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
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
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
public class JennyNpc
extends InventoryGirlEntity
implements VoidCallback,
fg {
    public boolean SeekingDoggyPos = false;
    public boolean Teleporting = false;
    public boolean StartDoggyPending = false;
    public static final DataParameter<Boolean> HornyPotionActive = EntityDataManager.createKey(GirlEntity.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(118);
    int TeleportTicks = 0;
    int SeekTicks = 0;
    boolean aa = false;
    int ag = 0;
    boolean ae = false;

    public JennyNpc(World world) {
        super(world);
        this.setSize(0.49f, 1.95f);
        this.P = 140;
        this.O = 50;
        this.K = 140;
        this.V = new Vec3d(0.0, -0.029999997854232782, -0.2);
    }

    public static JennyNpc create(World world) {
        JennyNpc jenny = new JennyNpc(world);
        jenny.F = true;
        return jenny;
    }

    @Override
    public String c() {
        return "Jenny";
    }

    @Override
    public float getRenderLabelOffset() {
        return -0.2f;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.DataManager.register(HornyPotionActive, (Object)false);
    }

    @Override
    public void c() {
        this.performAction("Alright, this is my new Home~");
        this.a(ModSounds.GIRLS_JENNY_HAPPYOH[1]);
    }

    public float getEyeHeight() {
        return 1.64f;
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_SIGH);
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    public void updateAITasks() {
        block26: {
            EntityPlayerMP entityPlayerMP;
            block28: {
                block29: {
                    block23: {
                        block25: {
                            block22: {
                                super.updateAITasks();
                                EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 15.0);
                                try {
                                    try {
                                        if (!this.StartDoggyPending || entityPlayer == null) break block22;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw JennyNpc.rethrow(runtimeException);
                                    }
                                    if (!(entityPlayer.getPositionVector().distanceTo(this.getPositionVector()) < 0.5)) break block22;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw JennyNpc.rethrow(runtimeException);
                                }
                                this.StartDoggyPending = false;
                                this.DataManager.set(GirlEntity.SexPlayerUuidKey, (Object)this.world.getClosestPlayerToEntity((Entity)this, 15.0).getPersistentID().toString());
                                entityPlayerMP = this.getServer().getPlayerList().getPlayerByUUID(this.getSexPlayerUuid());
                                this.DataManager.set(GirlEntity.SexPlayerUuidKey, (Object)entityPlayerMP.getPersistentID().toString());
                                entityPlayerMP.setPositionAndUpdate(this.getPositionVector().x, this.getPositionVector().y, this.getPositionVector().z);
                                this.a(entityPlayerMP, false);
                                entityPlayerMP.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
                                this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
                                this.AimTarget = null;
                                this.b(GirlAnimationState.DOGGYSTART);
                                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), entityPlayerMP);
                            }
                            try {
                                block24: {
                                    try {
                                        try {
                                            if (!this.SeekingDoggyPos) break block23;
                                            if (this.getPositionVector().distanceTo(this.getTargetPos()) < 0.6) break block24;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw JennyNpc.rethrow(runtimeException);
                                        }
                                        if (this.SeekTicks <= 200) break block25;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw JennyNpc.rethrow(runtimeException);
                                    }
                                }
                                this.SeekingDoggyPos = false;
                                this.DataManager.set(GirlEntity.BusyKey, (Object)true);
                                this.SeekTicks = 0;
                                this.noClip = true;
                                this.setNoGravity(true);
                                this.motionX = 0.0;
                                this.motionY = 0.0;
                                this.motionZ = 0.0;
                                this.b(GirlAnimationState.STARTDOGGY);
                                break block23;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyNpc.rethrow(runtimeException);
                            }
                        }
                        try {
                            try {
                                ++this.SeekTicks;
                                if (this.SeekTicks != 60 && this.SeekTicks != 120) break block23;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyNpc.rethrow(runtimeException);
                            }
                            this.getNavigator().clearPath();
                            this.getNavigator().tryMoveToXYZ(this.getTargetPos().x, this.getTargetPos().y, this.getTargetPos().z, 0.35);
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyNpc.rethrow(runtimeException);
                        }
                    }
                    try {
                        try {
                            block27: {
                                try {
                                    try {
                                        if (!this.Teleporting) break block26;
                                        ++this.TeleportTicks;
                                        if (this.getPositionVector().equals(GirlEntity.TargetPosKey)) break block27;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw JennyNpc.rethrow(runtimeException);
                                    }
                                    if (this.TeleportTicks <= 40) break block28;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw JennyNpc.rethrow(runtimeException);
                                }
                            }
                            this.Teleporting = false;
                            this.TeleportTicks = 0;
                            this.openActionMenu(this.world.getMinecraftServer().getPlayerList().getPlayerByUUID((UUID)this.getSexPlayerUuid()).rotationYaw + 180.0f);
                            this.DataManager.set(GirlEntity.BusyKey, (Object)true);
                            this.getNavigator().clearPath();
                            if (!((Boolean)this.DataManager.get(HornyPotionActive)).booleanValue()) break block29;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyNpc.rethrow(runtimeException);
                        }
                        this.U();
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyNpc.rethrow(runtimeException);
                    }
                }
                this.b(GirlAnimationState.PAYMENT);
                break block26;
            }
            this.rotationYaw = this.I().floatValue();
            this.setTargetPos(this.getPlayerFrontPos());
            this.setNoGravity(false);
            entityPlayerMP = LerpMath.stepTowards(this.getPositionVector(), this.getTargetPos(), 40 - this.TeleportTicks);
            this.setPosition(entityPlayerMP.x, entityPlayerMP.y, entityPlayerMP.z);
        }
    }

    public boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        block7: {
            try {
                if (super.processInteract(entityPlayer, enumHand)) {
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
            try {
                try {
                    if (!this.world.isRemote || this.openActionMenu(entityPlayer)) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyNpc.rethrow(runtimeException);
                }
                this.create(I18n.format((String)"jenny.dialogue.busy", (Object[])new Object[0]));
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
        }
        return true;
    }

    @Override
    public void onUpdate() {
        try {
            super.onUpdate();
            if (!this.world.isRemote) {
                this.DataManager.set(HornyPotionActive, (Object)this.isPotionActive(PotionHandler.b));
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyNpc.rethrow(runtimeException);
        }
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        block13: {
            ItemStack itemStack;
            ItemStack[] itemStackArray;
            String[] stringArray;
            JennyNpc jenny;
            EntityPlayer entityPlayer2;
            String string;
            String[] stringArray2;
            block16: {
                block15: {
                    try {
                        try {
                            block14: {
                                try {
                                    try {
                                        if (this.getSexPlayerUuid() != null) break block13;
                                        if (!this.J()) break block14;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw JennyNpc.rethrow(runtimeException);
                                    }
                                    if (!((String)this.DataManager.get(GirlEntity.MasterUuidKey)).equals(Minecraft.getMinecraft().player.getPersistentID().toString())) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw JennyNpc.rethrow(runtimeException);
                                }
                            }
                            String[] stringArray3 = new String[4];
                            stringArray3[0] = "action.names.blowjob";
                            stringArray3[1] = "action.names.boobjob";
                            stringArray3[2] = "action.names.doggy";
                            String[] stringArray4 = stringArray3;
                            stringArray2 = stringArray3;
                            int i = 3;
                            if ((Integer)this.DataManager.get(GirlEntity.OutfitIndexKey) != 1) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyNpc.rethrow(runtimeException);
                        }
                        string = "action.names.strip";
                        break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyNpc.rethrow(runtimeException);
                    }
                }
                string = "action.names.dressup";
            }
            stringArray4[i] = string;
            String[] stringArray5 = stringArray2;
            try {
                if (((Boolean)this.DataManager.get(HornyPotionActive)).booleanValue()) {
                    GirlEntity.openActionMenu(entityPlayer, this, stringArray5, true);
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
            try {
                entityPlayer2 = entityPlayer;
                jenny = this;
                stringArray = stringArray5;
                ItemStack[] itemStackArray2 = new ItemStack[4];
                itemStackArray2[0] = new ItemStack(Items.EMERALD, 3);
                itemStackArray2[1] = new ItemStack(Items.ENDER_PEARL, 2);
                itemStackArray2[2] = new ItemStack(Items.DIAMOND, 2);
                ItemStack[] itemStackArray3 = itemStackArray2;
                itemStackArray = itemStackArray2;
                int i2 = 3;
                itemStack = (Integer)this.DataManager.get(GirlEntity.OutfitIndexKey) == 1 ? new ItemStack(Items.GOLD_INGOT, 1) : new ItemStack(Items.AIR, 0);
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
            itemStackArray3[i2] = itemStack;
            GirlEntity.openActionMenuWithItems(entityPlayer2, jenny, stringArray, itemStackArray, true);
            return true;
        }
        return false;
    }

    @Override
    public void performAction(String string, UUID uUID) {
        block12: {
            block15: {
                block14: {
                    block13: {
                        block11: {
                            try {
                                super.performAction(string, uUID);
                                if (!"action.names.blowjob".equals(string)) break block11;
                                this.a("animationFollowUp", "blowjob");
                                this.a(true, uUID);
                                break block12;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyNpc.rethrow(runtimeException);
                            }
                        }
                        try {
                            if (!"action.names.boobjob".equals(string)) break block13;
                            this.a("animationFollowUp", "boobjob");
                            this.a(true, uUID);
                            break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyNpc.rethrow(runtimeException);
                        }
                    }
                    try {
                        if (!"action.names.doggy".equals(string)) break block14;
                        this.a("animationFollowUp", "doggy");
                        this.a(true, uUID);
                        break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyNpc.rethrow(runtimeException);
                    }
                }
                try {
                    if (!"action.names.strip".equals(string)) break block15;
                    this.a("animationFollowUp", "strip");
                    this.a(true, uUID);
                    break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyNpc.rethrow(runtimeException);
                }
            }
            try {
                if ("action.names.dressup".equals(string)) {
                    this.b(GirlAnimationState.STRIP);
                }
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
        }
    }

    protected void a(boolean flag, UUID uUID) {
        super.a(flag, true, uUID);
        AnimationInputLock.setAnimationLocked(false);
    }

    @Override
    public void a() {
        block9: {
            BlockPos blockPos;
            block8: {
                blockPos = this.create(this.getPosition());
                try {
                    if (blockPos != null) break block8;
                    this.a(ModSounds.GIRLS_JENNY_HMPH[2]);
                    this.create(I18n.format((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                    break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyNpc.rethrow(runtimeException);
                }
            }
            this.tasks.removeTask((EntityAIBase)this.WanderAI);
            this.tasks.removeTask((EntityAIBase)this.WatchPlayerAI);
            Vec3d vec3d = new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
            int[] nArray = new int[]{0, 180, -90, 90};
            Vec3d[][] vec3dArrayArray = new Vec3d[][]{{new Vec3d(0.5, 0.0, -0.5), new Vec3d(0.0, 0.0, -1.0)}, {new Vec3d(0.5, 0.0, 1.5), new Vec3d(0.0, 0.0, 1.0)}, {new Vec3d(-0.5, 0.0, 0.5), new Vec3d(-1.0, 0.0, 0.0)}, {new Vec3d(1.5, 0.0, 0.5), new Vec3d(1.0, 0.0, 0.0)}};
            int i = -1;
            for (int i3 = 0; i3 < vec3dArrayArray.length; ++i3) {
                block10: {
                    Vec3d vec3d2 = vec3d.add(vec3dArrayArray[i3][1]);
                    try {
                        if (this.world.getBlockState(new BlockPos(vec3d2.x, vec3d2.y, vec3d2.z)).getBlock() != Blocks.AIR) continue;
                        if (i != -1) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyNpc.rethrow(runtimeException);
                    }
                    i = i3;
                    continue;
                }
                double d = this.getPosition().distanceSq(vec3d.add((Vec3d)vec3dArrayArray[i][0]).x, vec3d.add((Vec3d)vec3dArrayArray[i][0]).y, vec3d.add((Vec3d)vec3dArrayArray[i][0]).z);
                double d2 = this.getPosition().distanceSq(vec3d.add((Vec3d)vec3dArrayArray[i3][0]).x, vec3d.add((Vec3d)vec3dArrayArray[i3][0]).y, vec3d.add((Vec3d)vec3dArrayArray[i3][0]).z);
                if (!(d2 < d)) continue;
                i = i3;
            }
            try {
                if (i == -1) {
                    this.a(ModSounds.GIRLS_JENNY_HMPH[2]);
                    this.create(I18n.format((String)"jenny.dialogue.bedobscured", (Object[])new Object[0]));
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
            Vec3d vec3d3 = vec3d.add(vec3dArrayArray[i][0]);
            this.a(false);
            this.b(nArray[i]);
            this.setTargetPos(new Vec3d(vec3d3.x, vec3d3.y, vec3d3.z));
            this.AimYaw = this.I().floatValue();
            this.getNavigator().clearPath();
            this.getNavigator().tryMoveToXYZ(vec3d3.x, vec3d3.y, vec3d3.z, 0.35);
            this.SeekingDoggyPos = true;
            this.SeekTicks = 0;
        }
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block34: {
            GirlAnimationState girlAnimationState2;
            block32: {
                block30: {
                    block28: {
                        girlAnimationState2 = this.getCurrentAction();
                        try {
                            block29: {
                                try {
                                    try {
                                        if (girlAnimationState2 != GirlAnimationState.DOGGYCUM) break block28;
                                        if (girlAnimationState == GirlAnimationState.DOGGYSLOW) break block29;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw JennyNpc.rethrow(runtimeException);
                                    }
                                    if (girlAnimationState != GirlAnimationState.DOGGYFAST) break block28;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw JennyNpc.rethrow(runtimeException);
                                }
                            }
                            return;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyNpc.rethrow(runtimeException);
                        }
                    }
                    try {
                        block31: {
                            try {
                                try {
                                    if (girlAnimationState2 != GirlAnimationState.CUMBLOWJOB) break block30;
                                    if (girlAnimationState == GirlAnimationState.THRUSTBLOWJOB) break block31;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw JennyNpc.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB) break block30;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyNpc.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyNpc.rethrow(runtimeException);
                    }
                }
                try {
                    block33: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.PAIZURI_CUM) break block32;
                                if (girlAnimationState == GirlAnimationState.PAIZURI_SLOW) break block33;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.PAIZURI_FAST) break block32;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyNpc.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    super.b(girlAnimationState);
                    if (girlAnimationState2 == GirlAnimationState.STARTBLOWJOB || girlAnimationState2 == GirlAnimationState.PAIZURI_START) break block34;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyNpc.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
        }
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 0.2), this.I().floatValue() + 180.0f);
        entityPlayer.setPositionAndUpdate(entityPlayer.posX + vec3d.x, entityPlayer.posY, entityPlayer.posZ + vec3d.z);
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB && girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyNpc.rethrow(runtimeException);
                        }
                        this.a(0.0, 0.0, 0.0, 0.0f, 70.0f);
                        return GirlAnimationState.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyNpc.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.DOGGYSLOW && girlAnimationState != GirlAnimationState.DOGGYFAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyNpc.rethrow(runtimeException);
                    }
                    return GirlAnimationState.DOGGYCUM;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.PAIZURI_FAST && girlAnimationState != GirlAnimationState.PAIZURI_SLOW) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyNpc.rethrow(runtimeException);
                }
                return GirlAnimationState.PAIZURI_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw JennyNpc.rethrow(runtimeException);
            }
        }
        return null;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public void b() {
        this.Teleporting = true;
    }

    @Override
    public void g() {
        this.WanderAI = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.tasks.addTask(5, (EntityAIBase)this.WatchPlayerAI);
        this.tasks.addTask(5, (EntityAIBase)this.WanderAI);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void U() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 6[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 12[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
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
            throw JennyNpc.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 42[SWITCH]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
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

