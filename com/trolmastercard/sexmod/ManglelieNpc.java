/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityTippedArrow
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.ProjectileImpactEvent$Arrow
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
public class ManglelieNpc
extends GirlEntity {
    public static final String MommyTagKey = "sexmod:mommy";
    public static final float am = 60.0f;
    public static final float ag = 4.0f;
    public static final float P = 3.5f;
    public static final float ah = 28.0f;
    public static final float ae = 15.0f;
    public static final float K = 15.0f;
    public static final float L = 0.65f;
    public static final float ao = 3.65f;
    public static final float O = 6.0f;
    public static final float ak = 80.0f;
    public static final float X = 700.0f;
    public static final DataParameter<String> MommyUuidKey = EntityDataManager.createKey(ManglelieNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(111);
    public static final DataParameter<Boolean> ClaimedKey = EntityDataManager.createKey(ManglelieNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(112);
    public static final DataParameter<Integer> TargetEntityIdKey = EntityDataManager.createKey(ManglelieNpc.class, (DataSerializer)DataSerializers.VARINT).getSerializer().createKey(113);
    public static final DataParameter<String> AttackTimeKey = EntityDataManager.createKey(ManglelieNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(114);
    public static final DataParameter<Boolean> FleeingKey = EntityDataManager.createKey(ManglelieNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(115);
    private UUID PendingMommyUuid = null;
    public boolean aj = true;
    public Vec3d R = Vec3d.ZERO;
    public float V = 0.0f;
    boolean IsWild = true;
    boolean Despawned = false;
    boolean ArrowFired = false;
    public float VerticalImpulse = 0.0f;
    public float W = 0.0f;
    public float T = 0.0f;
    public float ai = 0.0f;
    boolean DespawnPending = false;
    boolean NameInitialized = false;
    boolean N = false;
    boolean Y = false;
    boolean M = false;
    public int an = 2;

    public ManglelieNpc(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.DataManager.register(MommyUuidKey, "");
        this.DataManager.register(ClaimedKey, false);
        this.DataManager.register(TargetEntityIdKey, -1);
        this.DataManager.register(AttackTimeKey, "");
        this.DataManager.register(FleeingKey, false);
    }

    @Override
    public String getGirlName() {
        return "Manglelie";
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, (EntityAIBase)new ManglelieFleeAi(this, 20.0f, 1.0, 1.2));
    }

    @Override
    public float getRenderLabelOffset() {
        return 0.0f;
    }

    public void setClaimed(boolean flag) {
        this.DataManager.set(ClaimedKey, flag);
    }

    public boolean isClaimed() {
        return (Boolean)this.DataManager.get(ClaimedKey);
    }

    @Nullable
    public UUID getMommyUuid() {
        String string = (String)this.DataManager.get(MommyUuidKey);
            if ("".equals(string)) {
                return null;
            }
        try {
            return UUID.fromString(string);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean isUnclaimed() {
        boolean flag;
        try {
            flag = !this.isClaimed();
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return flag;
    }

    @Nullable
    public GalathNpc getMommy(boolean flag) {
        GirlEntity girl;
        UUID uUID = this.getMommyUuid();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            girl = flag ? GirlEntity.getServerSideByUuid(uUID) : GirlEntity.getClientSideByUuid(uUID);
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GirlEntity girl2 = girl;
        try {
            if (!(girl2 instanceof GalathNpc)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return (GalathNpc)girl2;
    }

    public void setMommyUuid(UUID uUID) {
        try {
            if (uUID == null) {
                this.DataManager.set(MommyUuidKey, "");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        this.DataManager.set(MommyUuidKey, uUID.toString());
    }

    @Override
    public Float I() {
        float f = super.I().floatValue();
        if (ModelManglelie.isInThreesomeAnimation(this)) {
            f += 180.0f;
        }
        return Float.valueOf(f);
    }

    public void setDespawned() {
        this.Despawned = true;
    }

    @Override
    public void updateAITasks() {
        try {
            if (this.DespawnPending) {
                this.world.removeEntity((Entity)this);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        this.f();
        this.w();
        super.updateAITasks();
        this.bindPendingMommy();
        this.void_c();
        this.void_d();
        this.i();
        this.void_n();
        this.u_();
        this.void_h();
        this.a_();
        this.void_t();
    }

    void void_t() {
        try {
            if (this.getMommyUuid() != null) {
                this.IsWild = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (this.IsWild) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (this.getMommy(true) == null) {
                System.out.println("removed non-wild mang for lack of mommy");
                this.world.removeEntity((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
    }

    void a_() {
        GalathNpc f_2 = this.getMommy(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (f_2.getChildMangleUuid() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (this.getGirlUuid().equals(f_2.getChildMangleUuid())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        System.out.println("removed non-wild mang cuz her mommy disowned her and got another mang");
        this.world.removeEntity((Entity)this);
    }

    public static GalathNpc getMommy(GirlEntity girl, boolean flag) {
        try {
            if (!(girl instanceof ManglelieNpc)) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return ((ManglelieNpc)girl).getMommy(flag);
    }

    public long getAttackTime() {
        String string = (String)this.DataManager.get(AttackTimeKey);
            if ("".equals(string)) {
                return -1L;
            }
        try {
            return Long.parseLong(string);
        }
        catch (Exception exception) {
            return -1L;
        }
    }

    public void setAttackTime(long l) {
        this.DataManager.set(AttackTimeKey, Long.toString(l));
        this.ArrowFired = false;
    }

    void void_h() {
        long l = this.getAttackTime();
        try {
            if (l == -1L) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        long l4 = this.world.getTotalWorldTime();
        try {
            if ((float)l4 < 28.0f + (float)l) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (this.ArrowFired) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        Entity entity = this.getTargetEntity();
        try {
            if (entity == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        EntityTippedArrow entityTippedArrow = new EntityTippedArrow(this.world, (EntityLivingBase)this);
        Vec3d vec3d = f_2.getPositionVector().add(0.0, 3.5, 0.0);
        entityTippedArrow.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
        Vec3d vec3d2 = entity.getPositionVector();
        Vec3d vec3d3 = vec3d2.subtract(vec3d).normalize();
        entityTippedArrow.motionX = vec3d3.x * 4.0;
        entityTippedArrow.motionY = vec3d3.y * 4.0;
        entityTippedArrow.motionZ = vec3d3.z * 4.0;
        GirlEntity.playSound((GirlEntity)f_2, SoundEvents.ENTITY_ARROW_SHOOT, true);
        this.world.spawnEntity((Entity)entityTippedArrow);
        this.ArrowFired = true;
    }

    public void addPotionEffect(PotionEffect potionEffect) {
    }

    void u_() {
        boolean flag;
        try {
            flag = this.getMommyUuid() != null;
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        boolean flag2 = flag;
        this.setNoGravity(flag2);
        this.noClip = flag2;
    }

    public boolean canBeCollidedWith() {
        boolean flag;
        try {
            flag = this.getMommyUuid() == null;
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public Vec3d getRenderPosition(Minecraft minecraft, PreviewEntity previewEntity, EntityLivingBase entityLivingBase, float f) {
        try {
            if (this.isTracked()) {
                return super.getRenderPosition(minecraft, previewEntity, entityLivingBase, f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (!this.isClaimed()) {
                return super.getRenderPosition(minecraft, previewEntity, entityLivingBase, f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(false);
        try {
            if (f_2 == null) {
                return super.getRenderPosition(minecraft, previewEntity, entityLivingBase, f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        ManglelieNpcRenderer.renderNpcWithLiving(f_2, f, previewEntity);
        return ManglelieNpcRenderer.b(f_2, f);
    }

    public float float_b(float f) {
        long l = this.getAttackTime();
        try {
            if (l == -1L) {
                return 0.0f;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        long l4 = this.world.getTotalWorldTime();
        float f2 = l4 - l;
        return (f2 + f) / 28.0f;
    }

    @Nullable
    public Entity getTargetEntity() {
        int i = (Integer)this.DataManager.get(TargetEntityIdKey);
        try {
            if (i == -1) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return this.world.getEntityByID(i);
    }

    void a(int i) {
        long l;
        ManglelieNpc manglelie;
        try {
            this.DataManager.set(TargetEntityIdKey, i);
            manglelie = this;
            l = i == -1 ? -1L : this.world.getTotalWorldTime();
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        manglelie.setAttackTime(l);
    }

    void void_d() {
        Entity entity = this.getTargetEntity();
        try {
            if (entity == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(true);
        try {
            if (f_2 == null) {
                this.setAttackTime(-1);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (!this.isClaimed()) {
                this.setAttackTime(-1);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (ManglelieNpc.isTargetBlocked(entity, f_2)) {
                this.setAttackTime(-1);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
    }

    public static boolean isTargetBlocked(Entity entity, GalathNpc f_2) {
        float f;
        try {
            if (entity.isDead) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (entity.dimension != f_2.dimension) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (!LineOfSight.canSeeEntity(entity)) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (!LineOfSight.hasLineOfSight(f_2.world, f_2.getTargetPos().add(0.0, (double)f_2.getEyeHeight(), 0.0), entity)) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = entity.getPositionVector().subtract(f_2.getPositionVector());
        try {
            if (vec3d.x * vec3d.x + vec3d.z * vec3d.z > 225.0) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        Float f2 = GalathNpc.rotateToTarget(f_2, 0.0f);
        try {
            f = f2 == null ? f_2.rotationYawHead : f2.floatValue();
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        float f3 = f;
        Vec3d vec3d2 = VectorMath.rotateYaw(vec3d, f3);
        try {
            if (vec3d2.z < 0.0) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return false;
    }

    void void_n() {
        try {
            if (this.getTargetEntity() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (!this.isClaimed()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (f_2.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (f_2.getCurrentAction() == GirlAnimationState.MASTERBATE) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        BlockPos blockPos = f_2.getPosition();
        BlockPos blockPos2 = new BlockPos(15.0, 15.0, 15.0);
        List<EntityMob> list = this.world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(blockPos.add((Vec3i)blockPos2), blockPos.subtract((Vec3i)blockPos2)));
        for (EntityMob entityMob : list) {
            try {
                if (ManglelieNpc.isTargetBlocked((Entity)entityMob, f_2)) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
            this.a(entityMob.getEntityId());
            return;
        }
    }

    void i() {
        Entity entity = this.getTargetEntity();
        try {
            if (entity == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(true);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        long l = this.getAttackTime();
        try {
            if (l == -1L) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        long l5 = this.world.getTotalWorldTime();
        long l6 = l5 - this.getAttackTime();
        try {
            if ((float)l6 < 60.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        this.ArrowFired = false;
        this.setAttackTime(-1);
    }

    void bindPendingMommy() {
        try {
            if (this.PendingMommyUuid == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GirlEntity girl = GirlEntity.getServerSideByUuid(this.PendingMommyUuid);
        try {
            if (!(girl instanceof GalathNpc)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = (GalathNpc)girl;
        try {
            this.setMommyUuid(this.PendingMommyUuid);
            f_2.setChildMangleUuid(this.getGirlUuid());
            this.setClaimed(true);
            this.setCurrentAction(GirlAnimationState.RIDE_MOMMY_HEAD);
            this.PendingMommyUuid = null;
            if (f_2.getCurrentAction() == GirlAnimationState.HUG_MANG) {
                f_2.getChildMangle(false);
                f_2.setCurrentAction((GirlAnimationState)null);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (this.getCurrentAction() != GirlAnimationState.THREESOME_CUM || !GirlAnimationState.isAnimationInList(girlAnimationState, GirlAnimationState.THREESOME_FAST, GirlAnimationState.THREESOME_SLOW)) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (this.world.isRemote || girlAnimationState != GirlAnimationState.THREESOME_CUM) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
                GalathOwnershipData.setLastSeenTime(this.getSexPlayerUuid(), this.world.getTotalWorldTime());
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    void w() {
        GalathNpc f_2;
        block12: {
            block11: {
                try {
                    try {
                        if (this.isClaimed() && !GirlAnimationState.isGirlInAnimation((GirlEntity)this, GirlAnimationState.THREESOME_SLOW, GirlAnimationState.THREESOME_CUM, GirlAnimationState.THREESOME_FAST)) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
            }
            f_2 = this.getMommy(true);
            try {
                if (f_2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
            try {
                try {
                    if (!f_2.isDead && this.getGirlUuid().equals(f_2.getChildMangleUuid())) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
                Main.LOGGER.warn("A dead mommy has been saved onto a mang. Deleting her and creating a new one");
                this.world.removeEntity((Entity)this);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
        }
        this.b(0.0f);
        this.setTargetPos(f_2.getPositionVector());
        this.getMommy(true);
    }

    @Override
    public void b(float f) {
        super.b(f);
    }

    @Override
    public Vec3d transformRenderPos(Vec3d vec3d, float f) {
        try {
            if (!this.isClaimed()) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (ModelManglelie.isInThreesomeAnimation(this)) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(false);
        try {
            if (f_2 == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return ManglelieNpcRenderer.b(f_2, f);
    }

    void void_c() {
        GalathNpc f_3;
        block23: {
            block24: {
                try {
                    if (this.isClaimed()) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
                try {
                    if (this.getMommyUuid() != null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
                BlockPos blockPos = this.getPosition();
                BlockPos blockPos2 = blockPos.add(-15.0, -15.0, -15.0);
                BlockPos blockPos3 = blockPos.add(15.0, 15.0, 15.0);
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos2, blockPos3);
                List<GalathNpc> list = this.world.getEntitiesWithinAABB(GalathNpc.class, axisAlignedBB);
                f_3 = null;
                for (GalathNpc galath : list) {
                    try {
                        if (galath.isDead) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                    try {
                        if (galath.getChildMangle(true) != null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                    try {
                        if (!galath.onGround) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                    f_3 = galath;
                    break;
                }
                try {
                    try {
                        if (f_3 != null) break block23;
                        if (this.getCurrentAction() != GirlAnimationState.RUN) break block24;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                    this.setCurrentAction((GirlAnimationState)null);
                    this.getNavigator().clearPath();
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
            }
            return;
        }
        try {
            if (this.getCurrentAction() == GirlAnimationState.RIDE_MOMMY_HEAD) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        this.setCurrentAction(GirlAnimationState.RUN);
        Vec3d vec3d = this.getPositionVector();
        Vec3d vec3d4 = f_3.getPositionVector();
        Vec3d vec3d2 = vec3d4.subtract(vec3d);
        float f = (float)AngleMath.radToDegrees(Math.atan2(vec3d2.z, vec3d2.x)) - 90.0f;
        this.b(f);
        this.Navigation = this.getNavigator();
        this.Navigation.clearPath();
        this.Navigation.tryMoveToEntityLiving((Entity)f_3, (double)0.65f);
    }

    boolean a(Entity entity, float f) {
        boolean flag;
        ManglelieNpc manglelie;
        try {
            manglelie = this;
            flag = f == 1.0f;
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = manglelie.getMommy(flag);
        try {
            if (f_2 == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = VecMath.getPositionOffset((Entity)this, f);
        return this.a(VecMath.getPositionOffset(entity, f).subtract(vec3d), f_2, f);
    }

    boolean boolean_a(Vec3d vec3d, float f) {
        boolean flag;
        ManglelieNpc manglelie;
        try {
            manglelie = this;
            flag = f == 1.0f;
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = manglelie.getMommy(flag);
        try {
            if (f_2 == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        Vec3d vec3d2 = VecMath.getPositionOffset((Entity)this, f);
        return this.a(vec3d.subtract(vec3d2), f_2, f);
    }

    boolean a(Vec3d vec3d, GalathNpc f_2, float f) {
        boolean flag;
        Vec3d vec3d2 = VectorMath.rotateYaw(vec3d, LerpMath.lerpAngleDegrees(f_2.prevRotationYawHead, f_2.rotationYawHead, (double)f));
        try {
            flag = vec3d2.x > 0.35;
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    public void onUpdate() {
        try {
            super.onUpdate();
            if (this.world.isRemote) {
                this.void_m();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void void_m() {
        float f;
        float f2;
        try {
            if ((float)Minecraft.getMinecraft().player.ticksExisted % 7.0f != 0.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (!ManglelieNpcRenderer.isManglelieNpc(this)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(false);
        try {
            if (f_2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        Entity entity = this.getSexPlayer();
        try {
            if (entity == null) {
                this.VerticalImpulse = 0.0f;
                this.W = 0.0f;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = entity.getPositionVector().add(0.0, (double)entity.getEyeHeight(), 0.0);
        Vec3d vec3d2 = f_2.getPositionVector().add(f_2.getModelBone("mangPos")).add(this.getModelBone("head"));
        Vec3d vec3d3 = vec3d2.subtract(vec3d);
        float f3 = (float)(AngleMath.radToDegrees(Math.atan2(vec3d3.z, vec3d3.x)) + 90.0);
        Float f4 = GalathNpc.rotateToTarget(f_2, 0.0f);
        f3 -= f_2.rotationYawHead;
        if (f4 != null) {
            f3 -= f4.floatValue();
        }
        try {
            f2 = Math.abs(BedLogic.angleDifference(0.0f, f3)) < 80.0f ? -AngleMath.degToRadians(f3) : 0.0f;
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            this.VerticalImpulse = f2;
            f = this.VerticalImpulse == 0.0f ? 0.0f : (float)MathUtils.clamp(-vec3d3.y / 2.0, -0.75, 0.75);
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        this.W = f;
    }

    public boolean attackEntityFrom(DamageSource damageSource, float f) {
        try {
            if (damageSource == DamageSource.OUT_OF_WORLD) {
                return super.attackEntityFrom(damageSource, f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        GalathNpc f_2 = this.getMommy(true);
        try {
            if (f_2 == null) {
                return super.attackEntityFrom(damageSource, f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        f_2.attackEntityFrom(damageSource, f);
        return false;
    }

    @Nullable
    Entity net_minecraft_entity_Entity_o() {
        Entity entity = this.getTargetEntity();
        try {
            if (entity != null) {
                return entity;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        for (EntityPlayer entityPlayer : this.world.playerEntities) {
            float f = entityPlayer.getDistance((Entity)this);
            try {
                if (f > 6.0f) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
            try {
                if (entity != null && !(entity.getDistance((Entity)this) > f)) continue;
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
            entity = entityPlayer;
        }
        return entity;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        String string;
        String string2;
        NBTTagCompound nBTTagCompound2;
        super.writeEntityToNBT(nBTTagCompound);
        UUID uUID = this.getMommyUuid();
        try {
            nBTTagCompound2 = nBTTagCompound;
            string2 = MommyTagKey;
            string = uUID == null ? "" : uUID.toString();
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            nBTTagCompound2.setString(string2, string);
            nBTTagCompound.setBoolean("sexmod:iswild", this.IsWild);
            if (this.Despawned) {
                nBTTagCompound.setBoolean("sexmod:despawned", true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
    }

    public void readFromNBT(NBTTagCompound nBTTagCompound) {
        super.readFromNBT(nBTTagCompound);
        String string = nBTTagCompound.getString(MommyTagKey);
        try {
            if (!"".equals(string)) {
                this.PendingMommyUuid = UUID.fromString(string);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        try {
            if (nBTTagCompound.getBoolean("sexmod:despawned")) {
                this.DespawnPending = true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        this.IsWild = nBTTagCompound.getBoolean("sexmod:iswild");
    }

    @Override
    protected boolean X() {
        return false;
    }

    @Override
    public void setCustomModel(String string) {
        super.setCustomModel(string);
        CustomModelWorldData.removeGirl(this);
    }

    void f() {
        try {
            if (this.NameInitialized) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        this.setCustomModel(CustomModelWorldData.getCustomModelName(this));
        this.NameInitialized = true;
    }

    @Override
    @Nullable
    protected GirlAnimationState getFollowUpAction(GirlAnimationState girlAnimationState) {
        return null;
    }

    @Override
    protected GirlAnimationState nextAnimationState(GirlAnimationState girlAnimationState) {
        try {
            if (GirlAnimationState.isAnimationInList(girlAnimationState, GirlAnimationState.THREESOME_FAST, GirlAnimationState.THREESOME_SLOW)) {
                this.N = true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    public void noop() {
        try {
            if (this.isClaimed()) {
                this.setCurrentAction(GirlAnimationState.RIDE_MOMMY_HEAD);
                this.b(0.0f);
                this.DataManager.setDirty(RotationYawKey);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
    }

    public boolean getCanSpawnHere() {
        try {
            if (!super.getCanSpawnHere()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ManglelieNpc.rethrow(runtimeException);
        }
        BlockPos blockPos = this.getPosition();
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        arrayList.addAll(TribeVillageData.GalathSpawnPositions);
        arrayList.addAll(TribeVillageData.ManglelieSpawnPositions);
        for (BlockPos blockPos2 : arrayList) {
            try {
                if (!(Math.sqrt(blockPos.distanceSq((Vec3i)blockPos2)) < 700.0)) continue;
                return false;
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
        }
        TribeVillageData.addSpawnPosition(blockPos, TribeVillageData.ManglelieSpawnPositions);
        return true;
    }

    @Override
    protected boolean shouldHoldAnimation(GirlAnimationState girlAnimationState, String string, boolean flag, AnimationEvent animationEvent) {
        block38: {
            block37: {
                block36: {
                    block35: {
                        block34: {
                            if (girlAnimationState == GirlAnimationState.THREESOME_CUM) {
                                this.N = false;
                                this.Y = false;
                                this.M = false;
                                this.an = 2;
                                this.isClaimed();
                                GalathNpc f_2 = this.getMommy(false);
                                try {
                                    if (f_2 != null) {
                                        f_2.resetAimTarget();
                                        GuiCumOverlay.removeParticlesForGirl(f_2);
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ManglelieNpc.rethrow(runtimeException);
                                }
                                GuiCumOverlay.removeParticlesForGirl(this);
                                return true;
                            }
                            try {
                                if (!this.N || girlAnimationState != GirlAnimationState.THREESOME_FAST) break block34;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ManglelieNpc.rethrow(runtimeException);
                            }
                            this.setCurrentAction(GirlAnimationState.THREESOME_CUM);
                            this.createAnimation("animation.shared.double_holding_cum", true, animationEvent, true);
                            GalathNpc f_3 = this.getMommy(false);
                            try {
                                if (f_3 != null) {
                                    f_3.setCurrentAction(GirlAnimationState.MASTERBATE_SITTING_CUM);
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw ManglelieNpc.rethrow(runtimeException);
                            }
                            return true;
                        }
                        try {
                            try {
                                if (!this.N && !flag) break block35;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ManglelieNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.THREESOME_SLOW) break block35;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ManglelieNpc.rethrow(runtimeException);
                        }
                        this.Y = false;
                        this.setCurrentAction(GirlAnimationState.THREESOME_FAST);
                        this.createAnimation("animation.shared.double_holding_soft", true, animationEvent, true);
                        GalathNpc f_4 = this.getMommy(false);
                        try {
                            if (f_4 != null) {
                                f_4.ak();
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ManglelieNpc.rethrow(runtimeException);
                        }
                        return true;
                    }
                    try {
                        if (this.N) {
                            return false;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                    try {
                        try {
                            try {
                                if (!flag || this.Y) break block36;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ManglelieNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.THREESOME_FAST) break block36;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ManglelieNpc.rethrow(runtimeException);
                        }
                        this.Y = true;
                        this.createAnimation("animation.shared.double_holding_hard", true, animationEvent, true);
                        return true;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ManglelieNpc.rethrow(runtimeException);
                    }
                }
                try {
                    if (flag || girlAnimationState != GirlAnimationState.THREESOME_FAST) break block37;
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
                this.M = true;
                this.setCurrentAction(GirlAnimationState.THREESOME_SLOW);
                this.createAnimation("animation.shared.double_holding_back", true, animationEvent, true);
                GalathNpc f_5 = this.getMommy(false);
                try {
                    if (f_5 != null) {
                        f_5.void_a();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
                return true;
            }
            try {
                try {
                    if (!this.M || girlAnimationState != GirlAnimationState.THREESOME_SLOW) break block38;
                }
                catch (RuntimeException runtimeException) {
                    throw ManglelieNpc.rethrow(runtimeException);
                }
                this.M = false;
                this.createAnimation("animation.shared.double_holding_slow", true, animationEvent, true);
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.rethrow(runtimeException);
            }
        }
        return false;
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        AnimationController animationController = animEvent.getController();
        if (this.EyesController == animationController) {
            if (this.getTargetEntity() == null) {
                return PlayState.STOP;
            }
            this.createAnimationOnce("animation.manglelie.angry_face", true, animEvent);
            return PlayState.CONTINUE;
        }
        if (this.MovementController == animationController) {
            if (this.getCurrentAction() != GirlAnimationState.NULL || this.isClaimed()) {
                return PlayState.STOP;
            }
            if (Math.abs(this.prevPosX - this.posX) + Math.abs(this.prevPosZ - this.posZ) > 0.0) {
                if ((Boolean)this.DataManager.get(FleeingKey)) {
                    this.createAnimationOnce("animation.manglelie.scared_run", true, animEvent);
                } else {
                    this.createAnimationOnce("animation.manglelie.walk", true, animEvent);
                }
                this.rotationYaw = this.rotationYawHead;
                return PlayState.CONTINUE;
            }
            this.createAnimationOnce("animation.manglelie.idle", true, animEvent);
            return PlayState.CONTINUE;
        }
        switch (this.getCurrentAction()) {
            default: {
                return PlayState.STOP;
            }
            case RUN: {
                this.createAnimationOnce("animation.manglelie.running", true, animEvent);
                break;
            }
            case RIDE_MOMMY_HEAD: {
                this.createAnimationOnce("animation.manglelie.sit_on_galath", true, animEvent);
                break;
            }
            case THREESOME_SLOW: {
                if (this.M) {
                    this.createAnimationOnce("animation.shared.double_holding_back", true, animEvent);
                    break;
                }
                this.createRangeAnimationOnce("animation.shared.double_holding_slow", 4, 0.33f, animEvent);
                break;
            }
            case THREESOME_FAST: {
                if (this.Y) {
                    this.createRangeAnimationOnce("animation.shared.double_holding_hard", 3, 0.33f, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.shared.double_holding_soft", true, animEvent);
                break;
            }
            case THREESOME_CUM: {
                this.createAnimationOnce("animation.shared.double_holding_cum", true, animEvent);
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
        this.ActionController.registerSoundListener(arg1 -> {
            switch (arg1.sound) {
                case "pound": {
                    this.playRandomSound(ModSounds.MISC_POUNDING, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "cs0": {
                    this.an = 0;
                    break;
                }
                case "cs1": {
                    this.an = 1;
                    break;
                }
                case "cs2": {
                    this.an = 2;
                    break;
                }
                case "sexui": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "doubleSemen0": {
                    this.playRandomSoundWithChance(ModSounds.MISC_INSERTS, 6.0f);
                    this.playRandomSound(ModSounds.MISC_POUNDING, new int[0]);
                }
                case "doubleSemen": {
                    GuiCumOverlay.addParticles(new ParticleEmitter(10, girl -> {
                        Vec3d vec3d = girl.d("semenEmitter");
                        Vec3d vec3d2 = girl.d("semenDir");
                        return vec3d.subtract(vec3d2).normalize();
                    }, girl -> girl.getModelBone("semenEmitter").add(girl.getTargetPos()), (GirlEntity)this, 0.3f, 0.3f));
                    break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                }
            }
        });
        animationData.addAnimationController(this.ActionController);
    }

    private static RuntimeException rethrow(Exception exception) {
        return new RuntimeException(exception);
    }

    public static class ArrowImpactHandler {
        @SubscribeEvent
        public void onArrowImpact(ProjectileImpactEvent.Arrow arrow) {
            RayTraceResult rayTraceResult = arrow.getRayTraceResult();
            EntityArrow entityArrow = arrow.getArrow();
            try {
                if (!(entityArrow.shootingEntity instanceof ManglelieNpc)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.ArrowImpactHandler.rethrow(runtimeException);
            }
            try {
                if (rayTraceResult.entityHit instanceof GirlEntity) {
                    arrow.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw ManglelieNpc.ArrowImpactHandler.rethrow(runtimeException);
            }
        }

        private static RuntimeException rethrow(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

