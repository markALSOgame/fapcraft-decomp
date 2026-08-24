/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
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
public class SlimeNpc
extends GirlEntity {
    static final double Q = (double)0.7f;
    static final float W = 0.9f;
    static final double M = 100.0;
    static final float L = 0.1f;
    static final int O = 2400;
    JumpState JumpPhase = SlimeNpc.JumpState.IDLE;
    public static DataParameter<Integer> TicksUntilBirthKey = EntityDataManager.createKey(SlimeNpc.class, (DataSerializer)DataSerializers.VARINT).getSerializer().createKey(113);
    public static DataParameter<Float> JumpYawKey = EntityDataManager.createKey(SlimeNpc.class, (DataSerializer)DataSerializers.FLOAT).getSerializer().createKey(112);
    public static DataParameter<Integer> HornyLevelKey = EntityDataManager.createKey(SlimeNpc.class, (DataSerializer)DataSerializers.VARINT).getSerializer().createKey(111);
    int JumpTicks = 0;
    boolean LastOnGround = true;
    boolean ShouldJump = false;
    int P = 0;

    public SlimeNpc(World world) {
        super(world);
    }

    @Override
    public String c() {
        return "Slime";
    }

    @Override
    public float i() {
        return 1.6f;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block14: {
            block12: {
                try {
                    block13: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.CUMBLOWJOB) break block12;
                                if (girlAnimationState == GirlAnimationState.THRUSTBLOWJOB) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimeNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeNpc.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.DOGGYCUM) break block14;
                            if (girlAnimationState == GirlAnimationState.DOGGYFAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.DOGGYSLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimeNpc.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw SlimeNpc.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean t() {
        return false;
    }

    @Override
    protected void initEntityAI() {
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(HornyLevelKey, (Object)0);
        this.getDataManager().register(JumpYawKey, (Object)Float.valueOf(0.0f));
        this.getDataManager().register(TicksUntilBirthKey, (Object)-1);
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB && girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimeNpc.rethrow(runtimeException);
                    }
                    return GirlAnimationState.CUMBLOWJOB;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.DOGGYSLOW && girlAnimationState != GirlAnimationState.DOGGYFAST) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeNpc.rethrow(runtimeException);
                }
                return GirlAnimationState.DOGGYCUM;
            }
            catch (RuntimeException runtimeException) {
                throw SlimeNpc.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB) {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.DOGGYSLOW) {
                return GirlAnimationState.DOGGYFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        return null;
    }

    protected float getJumpUpwardsMotion() {
        return 0.9f;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        super.writeEntityToNBT(nBTTagCompound);
        nBTTagCompound.setInteger("hornyLevel", ((Integer)this.DataManager.get(HornyLevelKey)).intValue());
        nBTTagCompound.setInteger("ticksUntilBirth", ((Integer)this.DataManager.get(TicksUntilBirthKey)).intValue());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        try {
            super.readEntityFromNBT(nBTTagCompound);
            this.DataManager.set(HornyLevelKey, (Object)nBTTagCompound.getInteger("hornyLevel"));
            this.DataManager.set(TicksUntilBirthKey, (Object)nBTTagCompound.getInteger("ticksUntilBirth"));
            if ((Integer)this.DataManager.get(HornyLevelKey) != 0) {
                this.DataManager.set(D, (Object)0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        this.noClip = false;
        this.setNoGravity(false);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return GirlLootTables.SlimeLootTable;
    }

    @Override
    public void onBirth() {
        this.DataManager.set(HornyLevelKey, (Object)0);
        this.DataManager.set(D, (Object)1);
    }

    @Override
    public void updateAITasks() {
        block8: {
            block9: {
                try {
                    try {
                        try {
                            try {
                                super.updateAITasks();
                                this.tryMate();
                                this.getDisplayName();
                                if (!this.isPotionActive(PotionHandler.b) || this.JumpPhase != SlimeNpc.JumpState.IDLE) break block8;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimeNpc.rethrow(runtimeException);
                            }
                            if ((Integer)this.DataManager.get(TicksUntilBirthKey) != -1) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                        this.DataManager.set(HornyLevelKey, (Object)2);
                        if ((Integer)this.DataManager.get(D) != 1) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimeNpc.rethrow(runtimeException);
                    }
                    this.b(GirlAnimationState.UNDRESS);
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeNpc.rethrow(runtimeException);
                }
            }
            this.removePotionEffect(PotionHandler.b);
        }
    }

    @Override
    public void onUpdate() {
        block10: {
            try {
                super.onUpdate();
                if (this.getCurrentAction() == GirlAnimationState.NULL) {
                    this.updateJump();
                }
            }
            catch (RuntimeException runtimeException) {
                throw SlimeNpc.rethrow(runtimeException);
            }
            try {
                try {
                    if ((Integer)this.DataManager.get(HornyLevelKey) < 2 || this.ticksExisted % 10 != 0) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeNpc.rethrow(runtimeException);
                }
                SlimeNpc.spawnParticles(EnumParticleTypes.HEART, (GirlEntity)this);
            }
            catch (RuntimeException runtimeException) {
                throw SlimeNpc.rethrow(runtimeException);
            }
        }
        try {
            if (this.world.isRemote) {
                this.spawnPregnancyParticles();
                this.getRenderLabelOffset();
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void i() {
        try {
            if (this.getSexPlayerUuid() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        try {
            if (!this.getSexPlayerUuid().equals(entityPlayerSP.getPersistentID())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = this.getPositionVector();
        Vec3d vec3d2 = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, (double)0.65f), this.I().floatValue());
        vec3d = vec3d.add(vec3d2);
        entityPlayerSP.setPosition(vec3d.x, vec3d.y, vec3d.z);
        entityPlayerSP.setVelocity(0.0, 0.0, 0.0);
    }

    void spawnPregnancyParticles() {
        int i = (Integer)this.DataManager.get(TicksUntilBirthKey);
        try {
            if (i == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        try {
            SlimeNpc.spawnParticles(EnumParticleTypes.SPELL_WITCH, (GirlEntity)this);
            if (i == 0) {
                this.a(ModSounds.MISC_PLOB[0]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
    }

    void c() {
        int i = (Integer)this.DataManager.get(TicksUntilBirthKey);
        try {
            if (i == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        try {
            this.DataManager.set(TicksUntilBirthKey, (Object)(i - 1));
            if (--i >= 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        SlimeRainEntity slimeRainEntity = new SlimeRainEntity(this.world);
        slimeRainEntity.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)slimeRainEntity);
        this.DataManager.set(TicksUntilBirthKey, (Object)-1);
    }

    void tryMate() {
        block21: {
            block20: {
                EntityPlayer entityPlayer;
                block19: {
                    block17: {
                        int i = (Integer)this.DataManager.get(HornyLevelKey);
                        try {
                            if (i < 2) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                        try {
                            try {
                                try {
                                    if (i < 4 || !this.onGround) break block17;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw SlimeNpc.rethrow(runtimeException);
                                }
                                if (this.getCurrentAction() != GirlAnimationState.NULL) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimeNpc.rethrow(runtimeException);
                            }
                            this.setTargetPos(this.getPositionVector());
                            this.b(this.rotationYaw);
                            this.DataManager.set(G, (Object)true);
                            this.setNoGravity(true);
                            this.noClip = true;
                            this.b(GirlAnimationState.STARTDOGGY);
                            return;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                    }
                    entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 1.0);
                    try {
                        block18: {
                            try {
                                try {
                                    if (entityPlayer == null || !entityPlayer.onGround) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw SlimeNpc.rethrow(runtimeException);
                                }
                                if (SlimeNpc.getByPlayerUuid(entityPlayer) == null) break block19;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimeNpc.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimeNpc.rethrow(runtimeException);
                    }
                }
                this.setTargetPos(this.getPositionVector());
                this.b(this.rotationYaw);
                this.DataManager.set(G, (Object)true);
                this.setNoGravity(true);
                this.noClip = true;
                entityPlayer.setNoGravity(true);
                entityPlayer.noClip = true;
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
                this.getTargetYaw(entityPlayer.getPersistentID());
                entityPlayer.rotationYaw = this.I().floatValue();
                Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, (double)0.65f), this.I().floatValue());
                try {
                    entityPlayer.setPosition(this.posX + vec3d.x, this.posY, this.posZ + vec3d.z);
                    if (this.getCurrentAction() != GirlAnimationState.WAITDOGGY) break block20;
                    this.b(GirlAnimationState.DOGGYSTART);
                    break block21;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeNpc.rethrow(runtimeException);
                }
            }
            this.b(GirlAnimationState.SUCKBLOWJOB);
        }
    }

    void updateJump() {
        block36: {
            block33: {
                boolean flag;
                block35: {
                    block34: {
                        block30: {
                            float f;
                            block32: {
                                block31: {
                                    try {
                                        try {
                                            if (!this.world.isRemote) break block30;
                                            if ((double)this.JumpTicks != 90.0) break block31;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw SlimeNpc.rethrow(runtimeException);
                                        }
                                        this.JumpPhase = SlimeNpc.JumpState.JUMP_START;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw SlimeNpc.rethrow(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (this.LastOnGround || !this.onGround) break block32;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw SlimeNpc.rethrow(runtimeException);
                                    }
                                    this.JumpPhase = SlimeNpc.JumpState.JUMP_END;
                                    this.JumpTicks = 0;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw SlimeNpc.rethrow(runtimeException);
                                }
                            }
                            this.rotationYaw = f = ((Float)this.DataManager.get(JumpYawKey)).floatValue();
                            this.rotationYawHead = f;
                            this.renderYawOffset = f;
                            break block36;
                        }
                        try {
                            if ((double)this.JumpTicks == 85.0) {
                                this.DataManager.set(JumpYawKey, (Object)Float.valueOf(this.getTargetYaw()));
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                        try {
                            if ((double)this.JumpTicks == 100.0) {
                                this.performJump();
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                        try {
                            try {
                                try {
                                    try {
                                        if (this.LastOnGround || !this.onGround) break block33;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw SlimeNpc.rethrow(runtimeException);
                                    }
                                    SlimeNpc slime = this;
                                    if ((Integer)this.DataManager.get(TicksUntilBirthKey) != -1) break block34;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw SlimeNpc.rethrow(runtimeException);
                                }
                                if (!(this.getRNG().nextFloat() < 0.1f)) break block34;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimeNpc.rethrow(runtimeException);
                            }
                            flag = true;
                            break block35;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeNpc.rethrow(runtimeException);
                        }
                    }
                    flag = false;
                }
                slime.ShouldJump = flag;
            }
            try {
                if (!this.ShouldJump || this.JumpTicks != 50) break block36;
            }
            catch (RuntimeException runtimeException) {
                throw SlimeNpc.rethrow(runtimeException);
            }
            int i = (Integer)this.DataManager.get(HornyLevelKey);
            int i2 = i + 1;
            try {
                this.DataManager.set(HornyLevelKey, (Object)i2);
                if (i2 == 1) {
                    this.b(GirlAnimationState.UNDRESS);
                }
            }
            catch (RuntimeException runtimeException) {
                throw SlimeNpc.rethrow(runtimeException);
            }
        }
        try {
            if (this.onGround) {
                ++this.JumpTicks;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        this.LastOnGround = this.onGround;
    }

    void performJump() {
        float f;
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        this.jump();
        this.rotationYaw = f = ((Float)this.DataManager.get(JumpYawKey)).floatValue();
        this.prevRotationYaw = f;
        Vec3d vec3d = new Vec3d(0.0, 0.0, (double)0.7f);
        vec3d = VectorMath.rotateYaw(vec3d, f);
        this.motionX = vec3d.x;
        this.motionZ = vec3d.z;
        this.JumpTicks = 0;
    }

    float getTargetYaw() {
        int i = (Integer)this.DataManager.get(HornyLevelKey);
        try {
            if ((Integer)this.DataManager.get(TicksUntilBirthKey) != -1) {
                return this.getRandomYaw();
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        try {
            if (i < 2) {
                return this.getRandomYaw();
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 30.0);
        try {
            if (entityPlayer == null) {
                return this.getRandomYaw();
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        try {
            if (SlimeNpc.getByPlayerUuid(entityPlayer) != null) {
                return this.getRandomYaw();
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimeNpc.rethrow(runtimeException);
        }
        return (float)Math.atan2(this.posZ - entityPlayer.posZ, this.posX - entityPlayer.posX) * 57.29578f + 90.0f;
    }

    float getRandomYaw() {
        return ModConstants.Random.nextFloat() * 360.0f;
    }

    public void fall(float f, float f2) {
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 7[SWITCH]
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
    public void registerControllers(AnimationData animationData) {
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 22[SWITCH]
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
        animationData.addAnimationController(this.EyesController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }

    static enum JumpState {
        IDLE("animation.slime.idle"),
        JUMP_START("animation.slime.jumpstart"),
        JUMP_AIR("animation.slime.jumpair"),
        JUMP_END("animation.slime.jumpend");

        String AnimationId;

        public String getAnimationId() {
            return this.AnimationId;
        }

        private JumpState(String string2) {
            this.AnimationId = string2;
        }
    }
}

