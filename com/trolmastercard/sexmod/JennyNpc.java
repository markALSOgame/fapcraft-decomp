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
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.init.SoundEvents;
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
import net.minecraft.util.math.Vec3i;
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

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        switch (girlAnimationState) {
            case SUCKBLOWJOB: {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
            case DOGGYSLOW: {
                return GirlAnimationState.DOGGYFAST;
            }
            case PAIZURI_SLOW: {
                if (this.ae) {
                    this.ae = false;
                    this.a(0.0, 0.0, (double)0.2f, 0.0f, 70.0f);
                }
                return GirlAnimationState.PAIZURI_FAST;
            }
        }
        return null;
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

    @Override
    protected void U() {
        switch ((String)this.DataManager.get(BlowjobStageKey)) {
            case "strip": {
                this.s();
                this.setCurrentAction(GirlAnimationState.STRIP);
                break;
            }
            case "blowjob": {
                this.setCurrentAction(GirlAnimationState.STARTBLOWJOB);
                break;
            }
            case "boobjob": {
                if ((Integer)this.DataManager.get(OutfitIndexKey) != 0) {
                    this.setCurrentAction(GirlAnimationState.STRIP);
                    return;
                }
                this.setCurrentAction(GirlAnimationState.PAIZURI_START);
                break;
            }
            case "doggy": {
                if ((Integer)this.DataManager.get(OutfitIndexKey) != 0) {
                    this.setCurrentAction(GirlAnimationState.STRIP);
                    this.s();
                    return;
                }
                this.resetAimTarget();
                if (this.world.isRemote) {
                    NetworkHandler.channel.sendToServer(new PacketSendGirlToSex(this.getGirlUuid()));
                    break;
                }
                this.s();
                this.a();
            }
        }
        if (this.world.isRemote) {
            this.a("animationFollowUp", "");
        } else {
            this.DataManager.set(BlowjobStageKey, (Object)"");
        }
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return null;
        }
        block5: switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.a("animation.jenny.null", true, animEvent);
                    break;
                }
                this.a("animation.jenny.fhappy", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL && this.getCurrentAction() != null) {
                    this.a("animation.jenny.null", true, animEvent);
                    break;
                }
                if (this.isRiding()) {
                    this.a("animation.jenny.sit", true, animEvent);
                    break;
                }
                if (Math.abs(this.prevPosX - this.posX) + Math.abs(this.prevPosZ - this.posZ) > 0.0) {
                    switch (this.getWalkState()) {
                        case RUN: {
                            this.a("animation.jenny.run", true, animEvent);
                            break;
                        }
                        case FAST_WALK: {
                            this.a("animation.jenny.fastwalk", true, animEvent);
                            break;
                        }
                        case WALK: {
                            this.a("animation.jenny.walk", true, animEvent);
                        }
                    }
                    this.rotationYaw = this.rotationYawHead;
                    break;
                }
                this.a("animation.jenny.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.a("animation.jenny.null", true, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.a("animation.jenny.strip", false, animEvent);
                        break block5;
                    }
                    case PAYMENT: {
                        this.a("animation.jenny.payment", false, animEvent);
                        break block5;
                    }
                    case STARTBLOWJOB: {
                        this.a("animation.jenny.blowjobintro", false, animEvent);
                        break block5;
                    }
                    case SUCKBLOWJOB: {
                        this.a("animation.jenny.blowjobsuck", true, animEvent);
                        break block5;
                    }
                    case THRUSTBLOWJOB: {
                        this.a("animation.jenny.blowjobthrust", true, animEvent);
                        break block5;
                    }
                    case CUMBLOWJOB: {
                        this.a("animation.jenny.blowjobcum", false, animEvent);
                        break block5;
                    }
                    case STARTDOGGY: {
                        this.a("animation.jenny.doggygoonbed", false, animEvent);
                        break block5;
                    }
                    case WAITDOGGY: {
                        this.a("animation.jenny.doggywait", true, animEvent);
                        break block5;
                    }
                    case DOGGYSTART: {
                        this.a("animation.jenny.doggystart", false, animEvent);
                        break block5;
                    }
                    case DOGGYSLOW: {
                        this.a("animation.jenny.doggyslow", true, animEvent);
                        break block5;
                    }
                    case DOGGYFAST: {
                        this.a("animation.jenny.doggyfast_" + (this.aa ? "hard" : "soft"), true, animEvent);
                        break block5;
                    }
                    case DOGGYCUM: {
                        this.a("animation.jenny.doggycum", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.a("animation.jenny.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.a("animation.jenny.bowcharge", false, animEvent);
                        break block5;
                    }
                    case RIDE: {
                        this.a("animation.jenny.ride", true, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.a("animation.jenny.sit", true, animEvent);
                        break block5;
                    }
                    case THROW_PEARL: {
                        this.a("animation.jenny.throwpearl", false, animEvent);
                        break block5;
                    }
                    case DOWNED: {
                        this.a("animation.jenny.downed", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_START: {
                        this.a("animation.jenny.paizuri_start", false, animEvent);
                        break block5;
                    }
                    case PAIZURI_SLOW: {
                        this.a("animation.jenny.paizuri_slow", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_FAST: {
                        this.a("animation.jenny.paizuri_fast", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_CUM: {
                        this.a("animation.jenny.paizuri_cum", false, animEvent);
                        break block5;
                    }
                    case WAVE: {
                        this.a("animation.jenny.wave", true, animEvent);
                        break block5;
                    }
                    case WAVE_IDLE: {
                        this.a("animation.jenny.wave_idle", true, animEvent);
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
            throw JennyNpc.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            block71: switch (arg1.sound) {
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
                case "becomeNude": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.a("currentModel", (Integer)this.DataManager.get(OutfitIndexKey) == 1 ? "0" : "1");
                    break;
                }
                case "stripDone": {
                    if (!((String)this.DataManager.get(BlowjobStageKey)).equals("boobjob")) {
                        this.resetAimTarget();
                    }
                    this.U();
                    break;
                }
                case "stripMSG1": {
                    this.h(I18n.format("jenny.dialogue.hihi", new Object[0]));
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_GIGGLE));
                    break;
                }
                case "paymentMSG1": {
                    this.h(I18n.format("jenny.dialogue.huh", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_HUH[1]);
                    break;
                }
                case "paymentMSG2": {
                    this.a(ModSounds.MISC_PLOB[0], 0.5f);
                    String string = "<" + Minecraft.getMinecraft().player.getName() + "> ";
                    switch ((String)this.DataManager.get(BlowjobStageKey)) {
                        case "strip": {
                            this.b(string + I18n.format("jenny.dialogue.showBobsandveganapls", new Object[0]), true);
                            break block71;
                        }
                        case "blowjob": {
                            this.b(string + I18n.format("jenny.dialogue.giveblowjob", new Object[0]), true);
                            break block71;
                        }
                        case "doggy": {
                            this.b(string + I18n.format("jenny.dialogue.givesex", new Object[0]), true);
                            break block71;
                        }
                        case "boobjob": {
                            this.b(string + I18n.format("jenny.dialogue.givebooba", new Object[0]), true);
                            break block71;
                        }
                    }
                    this.b(string + "sex pls", true);
                    break;
                }
                case "paymentMSG3": {
                    this.h(I18n.format("jenny.dialogue.hehe", new Object[0]));
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_GIGGLE));
                    break;
                }
                case "sexUiOn": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "paymentMSG4": {
                    this.a(ModSounds.MISC_PLOB[0], 0.25f);
                    break;
                }
                case "paymentDone": {
                    this.U();
                    break;
                }
                case "bjiMSG1": {
                    this.h(I18n.format("jenny.dialogue.blowjobtext1", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_MMM[8]);
                    this.AimYaw = this.rotationYaw + 180.0f;
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "bjiMSG2": {
                    this.h(I18n.format("jenny.dialogue.blowjobtext2", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_LIGHTBREATHING[8]);
                    break;
                }
                case "bjiMSG3": {
                    this.h(I18n.format("jenny.dialogue.blowjobtext3", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_AFTERSESSIONMOAN[0]);
                    break;
                }
                case "bjiMSG4": {
                    this.a(ModSounds.MISC_BELLJINGLE[0]);
                    break;
                }
                case "bjiMSG5": {
                    this.h(I18n.format("jenny.dialogue.blowjobtext4", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_HMPH[1], 0.5f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "bjiMSG6": {
                    this.h(I18n.format("jenny.dialogue.blowjobtext5", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_LIGHTBREATHING[8]);
                    break;
                }
                case "bjiMSG7": {
                    this.h(I18n.format("jenny.dialogue.blowjobtext6", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_GIGGLE[4]);
                    break;
                }
                case "bjiMSG8": {
                    this.b("<" + Minecraft.getMinecraft().player.getName() + "> " + I18n.format("jenny.dialogue.blowjobtext7", new Object[0]), true);
                    this.a(ModSounds.MISC_PLOB[0], 0.5f);
                    break;
                }
                case "bjiMSG9": {
                    this.h(I18n.format("jenny.dialogue.blowjobtext8", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_GIGGLE[2]);
                    break;
                }
                case "bjiMSG10": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.a(-0.65, -0.8, -0.25, 60.0f, -3.0f);
                    break;
                }
                case "bjiMSG11": {
                    if (this.isOwnedByLocalPlayer() && AnimationInputLock.SneakPressed) {
                        this.N();
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIPSOUND));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "bjiMSG12": {
                    if (ModConstants.Random.nextInt(5) == 0) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_BJMOAN));
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIPSOUND));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "bjtMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MMM));
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIPSOUND));
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
                    this.aa = true;
                    break;
                }
                case "bjtReady": 
                case "paizuriReady": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "bjcMSG1": {
                    this.a(ModSounds.GIRLS_JENNY_BJMOAN[1]);
                    break;
                }
                case "bjcMSG2": {
                    this.a(ModSounds.GIRLS_JENNY_BJMOAN[7]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.forceShowHud();
                    break;
                }
                case "bjcMSG3": {
                    this.a(ModSounds.GIRLS_JENNY_AFTERSESSIONMOAN[1]);
                    break;
                }
                case "bjcMSG4": {
                    this.a(ModSounds.GIRLS_JENNY_LIGHTBREATHING[0]);
                    break;
                }
                case "bjcMSG5": {
                    this.a(ModSounds.GIRLS_JENNY_LIGHTBREATHING[1]);
                    break;
                }
                case "bjcMSG6": {
                    this.a(ModSounds.GIRLS_JENNY_LIGHTBREATHING[2]);
                    break;
                }
                case "bjcMSG7": {
                    this.a(ModSounds.GIRLS_JENNY_LIGHTBREATHING[3]);
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
                    this.a(ModSounds.MISC_BEDRUSTLE[0]);
                    this.AimYaw = this.rotationYaw;
                    break;
                }
                case "doggyGoOnBedMSG2": {
                    this.a(I18n.format("jenny.dialogue.doggytext1", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_LIGHTBREATHING[9]);
                    break;
                }
                case "doggyGoOnBedMSG3": {
                    this.a(I18n.format("jenny.dialogue.doggytext2", new Object[0]));
                    this.a(ModSounds.GIRLS_JENNY_GIGGLE[0]);
                    break;
                }
                case "doggyGoOnBedMSG4": {
                    this.a(ModSounds.MISC_SLAP[0], 0.75f);
                    break;
                }
                case "doggyGoOnBedDone": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSetPlayerForGirl(this.getGirlUuid(), Minecraft.getMinecraft().player.getPersistentID()));
                    this.setCurrentAction(GirlAnimationState.WAITDOGGY);
                    break;
                }
                case "doggystartMSG1": {
                    this.a(ModSounds.MISC_TOUCH[0]);
                    break;
                }
                case "doggystartMSG2": {
                    this.a(ModSounds.MISC_TOUCH[1]);
                    break;
                }
                case "doggystartMSG3": {
                    this.a(ModSounds.MISC_BEDRUSTLE[1], 0.5f);
                    break;
                }
                case "doggystartMSG4": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_SMALLINSERTS));
                    this.a(ModSounds.GIRLS_JENNY_MMM[1]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "doggystartMSG5": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.33f);
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                    break;
                }
                case "doggystartDone": {
                    this.setCurrentAction(GirlAnimationState.DOGGYSLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "doggyslowMSG1": {
                    this.aa = false;
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.33f);
                    int n = ModConstants.Random.nextInt(4);
                    if (n == 0) {
                        n = ModConstants.Random.nextInt(2);
                        if (n == 0) {
                            this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MMM));
                        } else {
                            this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                        }
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_HEAVYBREATHING));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.00666);
                    break;
                }
                case "doggyslowMSG2": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_LIGHTBREATHING), 0.5f);
                    break;
                }
                case "doggyfastMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.75f);
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                    ++this.ag;
                    if (this.ag % 2 == 0) {
                        int n = ModConstants.Random.nextInt(2);
                        if (n == 0) {
                            this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                            break;
                        }
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_HEAVYBREATHING));
                        break;
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_AHH));
                    break;
                }
                case "doggyfastDone": {
                    this.aa = false;
                    this.setCurrentAction(GirlAnimationState.DOGGYSLOW);
                    break;
                }
                case "doggycumMSG1": {
                    this.a(ModSounds.MISC_CUMINFLATION[0], 2.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 2.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MOAN));
                    break;
                }
                case "doggycumMSG2": {
                    this.a(ModSounds.GIRLS_JENNY_HEAVYBREATHING[4]);
                    break;
                }
                case "doggycumMSG3": {
                    this.a(ModSounds.GIRLS_JENNY_HEAVYBREATHING[5]);
                    break;
                }
                case "doggycumMSG4": {
                    this.a(ModSounds.GIRLS_JENNY_HEAVYBREATHING[6]);
                    break;
                }
                case "doggycumMSG5": {
                    this.a(ModSounds.GIRLS_JENNY_HEAVYBREATHING[7]);
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "boobjob_camera": {
                    UUID uUID = Minecraft.getMinecraft().player.getPersistentID();
                    if (!uUID.equals(this.world.getClosestPlayerToEntity((Entity)this, 2.0).getPersistentID())) break;
                    this.AimYaw = this.world.getPlayerEntityByUUID(uUID).rotationYaw;
                    this.handleGirlUuidEvent(uUID);
                    if (this.ae) break;
                    this.ae = true;
                    this.a(-0.7, -0.6, 0.2, 60.0f, -3.0f);
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
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (this.getRNG().nextBoolean()) {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_MMM));
                    } else {
                        this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_JENNY_AHH));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "paizuriSlowMSG1": 
                case "paizuriStartMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "paizuri_fastDone": {
                    this.setCurrentAction(GirlAnimationState.PAIZURI_SLOW);
                    if (!this.isOwnedByLocalPlayer() || this.ae) break;
                    this.ae = true;
                    this.a(-0.7, -0.6, 0.2, 60.0f, -3.0f);
                    break;
                }
                case "paizuri_startStep": {
                    IBlockState iBlockState = this.world.getBlockState(this.getPosition().subtract(new Vec3i(0, 1, 0)));
                    this.a(iBlockState.getBlock().getSoundType(iBlockState, this.world, this.getPosition(), this).getStepSound());
                    break;
                }
                case "paizuri_cumStart": {
                    if (!this.isOwnedByLocalPlayer() || this.ae) break;
                    this.a(-0.7, -0.6, 0.2, 60.0f, -3.0f);
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

