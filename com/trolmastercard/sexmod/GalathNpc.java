/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockBanner
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockButton
 *  net.minecraft.block.BlockCarpet
 *  net.minecraft.block.BlockHorizontal
 *  net.minecraft.block.BlockLadder
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSign
 *  net.minecraft.block.BlockTorch
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityMultiPart
 *  net.minecraft.entity.MultiPartEntityPart
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntityWitherSkeleton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketEntityVelocity
 *  net.minecraft.network.play.server.SPacketParticles
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.BossInfo$Color
 *  net.minecraft.world.BossInfo$Overlay
 *  net.minecraft.world.BossInfoServer
 *  net.minecraft.world.DimensionType
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.event.entity.EntityMountEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingSpawnEvent$CheckSpawn
 *  net.minecraftforge.event.entity.player.PlayerWakeUpEvent
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBanner;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
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
public class GalathNpc
extends GirlEntity
implements IEntityMultiPart,
LeftBallActiveKey {
    public static final float f3 = 0.6f;
    public static final float f4 = 0.6f;
    public static final int bj = 10;
    public static final int an = 20;
    public static final float aU = 50.0f;
    public static final float ba = 40.0f;
    public static final int bM = 5;
    public static final int bs = 25;
    public static final float bJ = 30.0f;
    public static final float aA = 3.0f;
    public static final int i4 = 23;
    public static final int X = 45;
    public static final float ca = 0.3f;
    public static final float f5 = 9.0f;
    public static final float aX = 30.0f;
    public static final int bE = 24;
    public static final int aQ = 32;
    public static final int av = 5;
    public static final int bQ = 36;
    public static final int aR = 40;
    public static final int aB = 54;
    public static final int by = 10;
    public static final float b_ = 0.25f;
    public static final double ax = 3.0;
    public static final double bF = 1.0;
    public static final double bv = 1.5;
    public static final double az = (double)0.3f;
    public static final double ag = 40.0;
    public static final double au = 5.0;
    public static final double ae = 0.2;
    public static final double aV = 3.0;
    public static final double ar = (double)0.1f;
    public static final double ai = 6.0;
    public static final double ah = 50.0;
    public static final double bR = 39.0;
    public static final double bV = 58.0;
    public static final double aZ = 2.0;
    public static final double Q = 1.0;
    public static final float aJ = 0.5f;
    public static final Vec3f BodyColor = new Vec3f(0.83137256f, 0.6862745f, 0.21568628f);
    public static final Vec3d RightBallOffset = new Vec3d((double)-1.049342f, 2.0547213554382324, -0.05048239231109619);
    public static final Vec3d LeftBallOffset = new Vec3d(1.2522261142730713, 1.435773253440857, 0.23570987582206726);
    public static final int aN = 10;
    public static final float ak = 0.2f;
    public static final int am = 5;
    public static final float T = 15.0f;
    public static final int aM = 48;
    public static final float be = 0.05f;
    public static final float f6 = 0.65f;
    public static final float bh = 0.9f;
    public static final float K = 45.0f;
    public static final float f9 = 1.0f;
    public static final float bn = 1.5f;
    public static final float ao = 110.0f;
    public static final int aj = 15;
    public static final float aw = 6.0f;
    public static final float bp = 0.94f;
    public static final int R = 13;
    public static final int bW = 40;
    public static final int bl = 25;
    public static final int aY = 38;
    public static final int N = 95;
    static final int bB = 10;
    static final int aI = 30;
    static final int bf = 175;
    static final float as = 2.0f;
    public static final float bo = 0.25f;
    public static final float Y = 1000.0f;
    public static final float bX = 15.0f;
    public static final float f13 = 5.0f;
    public static final int aW = 8000;
    public static final float aK = 0.1f;
    public static final float ac = 5.0f;
    public static final float f14 = -10.0f;
    public static final int bk = 16;
    public static final int br = 7;
    public static final int cb = 4;
    public static final float M = 0.5f;
    public static final float bi = 0.55f;
    static final Class<?>[] aS = new Class[]{BlockAir.class, BlockCarpet.class, BlockBush.class, BlockButton.class, BlockLadder.class, BlockTorch.class, BlockSign.class, BlockBanner.class};
    public static final DataParameter<Integer> TargetEntityIdKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.VARINT).getSerializer().createKey(111);
    public static final DataParameter<Integer> CombatTargetIdKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.VARINT).getSerializer().createKey(112);
    public static final DataParameter<Boolean> RightBallActiveKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(113);
    public static final DataParameter<Boolean> LeftBallActiveKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(114);
    public static final DataParameter<Boolean> FlipSideKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(115);
    public static final DataParameter<Integer> bH = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.VARINT).getSerializer().createKey(116);
    public static final DataParameter<String> ManglePosKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(117);
    public static final DataParameter<Boolean> IsKnockedOutKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(118);
    public static final DataParameter<Float> bO = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.FLOAT).getSerializer().createKey(119);
    public static final DataParameter<Boolean> IsParalyzedKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(120);
    public static final DataParameter<String> ChildMangleUuidKey = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(121);
    public static final DataParameter<Boolean> bT = EntityDataManager.createKey(GalathNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(122);
    public static final double d2 = 0.2;
    public static final float bS = 5.0f;
    public static final int i5 = 60;
    BossInfoServer aO = new BossInfoServer((ITextComponent)new TextComponentString(this.getDisplayName()), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS);
    GalathBodyPart galathBodyPart = new GalathBodyPart(this, "energyBallHitBox", 0.75f, 0.75f);
    GalathBodyPart V = new GalathBodyPart(this, "energyBallHitBox", 0.75f, 0.75f);
    public DeadClass CurrentDeathEvent = null;
    public Vec3d O = null;
    public Vec3d bL = null;
    public int aF = 0;
    public Vec3d bd = null;
    public List<EntityWitherSkeleton> bI = new ArrayList<EntityWitherSkeleton>();
    public float aE = 0.0f;
    public long af = -1L;
    public long aH = -1L;
    public float bw = 0.0f;
    public float bm = 0.0f;
    boolean bU = false;
    public Vec3d aG = null;
    boolean bA = false;
    Vec3d bD;
    Vec3d W;
    Vec3d Z;
    float al = 0.0f;
    boolean U = false;
    public int ad = 0;
    double d4 = 0.0;
    double bg = 0.0;
    double d5 = 0.0;
    double a_ = 0.0;
    boolean bK = false;
    Path aq = null;
    BlockPos bG = null;
    int aC = 0;
    GirlAnimationState ab = null;
    int at = 0;
    int bY = 0;
    int i6 = 0;
    long bc = 0L;
    boolean S = false;
    boolean P = false;
    int i7 = 0;
    boolean aT = false;
    public boolean bx = false;
    public boolean flag = false;
    public boolean aD = false;
    public boolean bt = false;
    public boolean ap = false;
    public boolean bu = false;
    public boolean aL = true;
    public boolean bb = false;
    boolean flag2 = false;

    public GalathNpc(World world) {
        super(world);
    }

    public GalathNpc(World world, @Nonnull EntityPlayer entityPlayer, Vec3d vec3d, boolean flag) {
        this(world);
        UUID uUID = entityPlayer.getPersistentID();
        this.DataManager.set(MasterUuidKey, (Object)uUID.toString());
        this.aO.setVisible(false);
        this.bG = new BlockPos(this.getPositionVector());
        String string = GirlHomeWorldData.getCustomName(uUID, GirlRegistry.GALATH);
        try {
            if (string != null) {
                super.setCustomName(string);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (flag) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getRNG().nextFloat() > 0.1f) {
                this.setCurrentAction(GirlAnimationState.GALATH_SUMMON);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.setCurrentAction(GirlAnimationState.MASTERBATE);
        this.b(180.0f - (float)AngleMath.radToDegrees(Math.atan2(vec3d.x - entityPlayer.posX, vec3d.z - entityPlayer.posZ)));
        MathUtils.runAfterDelay(8000, () -> {
            EntityPlayer entityPlayer = this.getSexPlayer();
            try {
                if (entityPlayer == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                if (entityPlayer.isDead) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            this.setTargetPos(entityPlayer.getPositionVector());
            this.b(entityPlayer.rotationYaw + 180.0f);
            this.setCurrentAction(GirlAnimationState.RAPE_INTRO);
            this.e(entityPlayer.getPersistentID());
            this.getChildMangle(true);
        });
    }

    public GalathNpc(World world, @Nonnull EntityPlayer entityPlayer, Vec3d vec3d) {
        this(world, entityPlayer, vec3d, false);
    }

    @Override
    public void f(String string) {
        super.setCustomModel(string);
        CustomModelWorldData.removeGirl(this);
    }

    @Override
    public String getDisplayName() {
        return "Galath";
    }

    @Override
    public float i() {
        float f;
        try {
            f = this.getChildMangleUuid() == null ? 0.5f : 1.35f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return f;
    }

    public float getEyeHeight() {
        return 1.9f;
    }

    public boolean k() {
        return this.J();
    }

    public boolean isPushedByWater() {
        return false;
    }

    protected void handleJumpWater() {
        try {
            if (this.k()) {
                super.handleJumpWater();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    protected float getWaterSlowDown() {
        try {
            if (this.k()) {
                return super.getWaterSlowDown();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return 0.0f;
    }

    public boolean isInWater() {
        try {
            if (this.k()) {
                return super.isInWater();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return false;
    }

    public boolean handleWaterMovement() {
        try {
            if (this.k()) {
                return super.handleWaterMovement();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.DataManager.register(TargetEntityIdKey, (Object)-1);
        this.DataManager.register(CombatTargetIdKey, (Object)0);
        this.DataManager.register(RightBallActiveKey, (Object)true);
        this.DataManager.register(LeftBallActiveKey, (Object)true);
        this.DataManager.register(FlipSideKey, (Object)false);
        this.DataManager.register(ManglePosKey, (Object)"null");
        this.DataManager.register(bH, (Object)-1);
        this.DataManager.register(IsKnockedOutKey, (Object)false);
        this.DataManager.register(bO, (Object)Float.valueOf(0.0f));
        this.DataManager.register(IsParalyzedKey, (Object)false);
        this.DataManager.register(ChildMangleUuidKey, (Object)"");
        this.DataManager.register(bT, (Object)false);
    }

    @Override
    protected void applyEntityAttributes() {
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.MAX_HEALTH);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
        this.getAttributeMap().registerAttribute(SWIM_SPEED);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(110.0);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue((double)0.6f);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.6f);
    }

    @Override
    protected void initEntityAI() {
        this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.4, false, new HashSet<>(PaymentItems)));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this));
        this.tasks.addTask(5, (EntityAIBase)this.WatchPlayerAI);
    }

    public void addTrackingPlayer(EntityPlayerMP entityPlayerMP) {
        super.addTrackingPlayer(entityPlayerMP);
        this.aO.addPlayer(entityPlayerMP);
    }

    public void removeTrackingPlayer(EntityPlayerMP entityPlayerMP) {
        super.removeTrackingPlayer(entityPlayerMP);
        this.aO.removePlayer(entityPlayerMP);
    }

    @Override
    public Vec3d o() {
        block4: {
            try {
                try {
                    if (!this.world.isRemote || this.aG == null) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return this.aG;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        return super.getTargetPos();
    }

    @Nullable
    public UUID getChildMangleUuid() {
        String string = (String)this.DataManager.get(ChildMangleUuidKey);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (Exception exception) {
            throw GalathNpc.rethrow(exception);
        }
        try {
            return UUID.fromString(string);
        }
        catch (Exception exception) {
            return null;
        }
    }

    @Nullable
    public ManglelieNpc getChildMangle(boolean flag) {
        GirlEntity girl;
        UUID uUID = this.getChildMangleUuid();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            girl = flag ? GalathNpc.setChildMangleUuid(uUID) : GalathNpc.getClientSideByUuid(uUID);
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        GirlEntity girl2 = girl;
        try {
            if (girl2 instanceof ManglelieNpc) {
                return (ManglelieNpc)girl2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return null;
    }

    @Nullable
    public static ManglelieNpc getChildMangle(GirlEntity girl, boolean flag) {
        try {
            if (!(girl instanceof GalathNpc)) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return ((GalathNpc)girl).a(flag);
    }

    public void setChildMangleUuid(@Nullable UUID uUID) {
        String string;
        DataParameter<String> dataParameter;
        EntityDataManager entityDataManager;
        try {
            entityDataManager = this.DataManager;
            dataParameter = ChildMangleUuidKey;
            string = uUID == null ? "" : uUID.toString();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        entityDataManager.set(dataParameter, (Object)string);
    }

    public void aC() {
        this.bA = true;
        ManglelieNpc manglelie = this.getChildMangle(true);
        try {
            if (manglelie != null) {
                manglelie.setDespawned();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    public void w() {
        GirlAnimationState girlAnimationState = this.getCurrentAction();
        try {
            if (girlAnimationState != GirlAnimationState.RAPE_ON_GOING) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.CurrentDeathEvent = DeadClass.CHANGE_POSITION;
        this.CurrentDeathEvent.start(this);
        this.getChildMangle(false);
        this.setCurrentAction(GirlAnimationState.FLY);
        EntityPlayer entityPlayer = this.S();
        try {
            this.e((UUID)null);
            if (entityPlayer != null) {
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), (EntityPlayerMP)entityPlayer);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        GirlEntity.playSound((GirlEntity)this, ModSounds.GIRLS_GALATH_DIALOG[0]);
    }

    public Vec3d getManglePos() {
        String[] stringArray = ((String)this.DataManager.get(ManglePosKey)).split("\\|");
        return new Vec3d(Double.parseDouble(stringArray[0]), Double.parseDouble(stringArray[1]), Double.parseDouble(stringArray[2]));
    }

    public void setManglePos(@Nullable Vec3d vec3d) {
        this.DataManager.set(ManglePosKey, (Object)(vec3d.x + "|" + vec3d.y + "|" + vec3d.z));
    }

    public int getSpecialState() {
        return (Integer)this.DataManager.get(bH);
    }

    public void setSpecialState(int i) {
        this.DataManager.set(bH, (Object)i);
    }

    public boolean isNonBoss() {
        return false;
    }

    @Override
    public boolean b() {
        try {
            switch (this.getCurrentAction()) {
                default: {
                    return false;
                }
                case HUG_MANG: 
                case MORNING_BLOWJOB_SLOW: 
                case MORNING_BLOWJOB_FAST: 
                case MORNING_BLOWJOB_CUM: {
                    return true;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void aa() {
        this.Z = new Vec3d(this.motionX, this.motionY, this.motionZ);
        this.bD = this.getPositionVector();
        this.W = this.getPositionVector().add(this.Z);
        this.Z = this.Z.scale(0.9);
    }

    @Override
    public void onUpdate() {
        block10: {
            block9: {
                boolean flag;
                block8: {
                    block7: {
                        flag = this.k();
                        try {
                            if (!flag) break block7;
                            this.E();
                            break block8;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                    }
                    this.getDisplayName();
                }
                try {
                    this.getPlayerFrontPos();
                    super.onUpdate();
                    if (!flag) break block9;
                    this.au();
                    break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            this.R();
        }
        try {
            if (this.world.isRemote) {
                this.X();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    @Override
    public boolean getManglePos() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    void X() {
        block11: {
            try {
                if (this.getCurrentAction() != GirlAnimationState.GIVE_COIN) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            int i = GirlAnimationState.GIVE_COIN.ticksPlaying[1];
            try {
                if (i == 95) {
                    ItemGalathCoin.spawnDesummonParticles((EntityPlayer)Minecraft.getMinecraft().player, this);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                try {
                    if (i > 25 && i < 38) break block11;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        Vec3d vec3d = this.getPositionVector();
        Vec3d vec3d2 = this.getModelBone("weapon").add(vec3d);
        Vec3d vec3d3 = this.getModelBone("offhand").add(vec3d);
        DragonBreathParticles.ParticleScale = 0.5f;
        for (float f = 0.0f; f < 1.0f; f += 0.2f) {
            Vec3d vec3d4 = LerpMath.lerpVec3d(vec3d2, vec3d3, (double)f);
            Minecraft.getMinecraft().effectRenderer.addEffect((Particle)new DragonBreathParticles(this.world, vec3d4.x, vec3d4.y, vec3d4.z));
        }
    }

    void E() {
        boolean flag;
        GalathNpc f_2;
        try {
            f_2 = this;
            flag = this.getDisplayName() != null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        f_2.setNoGravity(flag);
    }

    void au() {
        block11: {
            try {
                try {
                    try {
                        try {
                            if (this.isInWater() || this.hasNoGravity()) break block11;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                        if (!(this.motionY < 0.0)) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    if (this.getCurrentAction() == GirlAnimationState.MASTERBATE) break block11;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                this.motionY *= (double)0.4f;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            this.aB();
            this.aj();
            this.aq();
            this.aw();
            this.C();
            this.Y();
            this.getTargetPos();
            if (this.getTargetEntity() == null) {
                this.ap = false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void o() {
        try {
            if (this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.RAPE_CUM) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (GirlAnimationState.RAPE_CUM.ticksPlaying[0] < 28) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.getChildMangle(false);
        this.setCurrentAction(GirlAnimationState.NULL);
        EntityPlayer entityPlayer = this.S();
        try {
            this.e((UUID)null);
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        entityPlayer.setPositionAndUpdate(entityPlayer.posX, Math.ceil(entityPlayer.posY) + 1.0, entityPlayer.posZ);
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), (EntityPlayerMP)entityPlayer);
    }

    void Y() {
        try {
            if (this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.CORRUPT_CUM) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (GirlAnimationState.CORRUPT_CUM.ticksPlaying[0] < 30) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.getChildMangle(false);
        this.setCurrentAction(GirlAnimationState.NULL);
        EntityPlayer entityPlayer = this.S();
        try {
            this.e((UUID)null);
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        entityPlayer.setPositionAndUpdate(entityPlayer.posX, Math.ceil(entityPlayer.posY) + 1.0, entityPlayer.posZ);
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), (EntityPlayerMP)entityPlayer);
    }

    static boolean isSpawnPositionFree(BlockPos blockPos, World world) {
        for (BlockPos object : TribeVillageData.GalathSpawnPositions) {
            try {
                if (!(Math.sqrt(blockPos.distanceSq((Vec3i)object)) < 1000.0)) continue;
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                try {
                    if (girl.world.isRemote) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (!(girl instanceof GalathNpc)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (girl.isDead) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                if (!(girl.getDistanceSq(blockPos) < 1000000.0)) continue;
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        int i = blockPos.getY();
        while (true) {
            block25: {
                try {
                    try {
                        if (!((float)i < 15.0f + (float)blockPos.getY())) break;
                        if (world.getBlockState(new BlockPos(blockPos.getX(), i, blockPos.getZ())).getBlock() == Blocks.AIR) break block25;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    return false;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            ++i;
        }
        i = blockPos.getY();
        while (true) {
            block26: {
                try {
                    try {
                        if (!((float)i > (float)blockPos.getY() - 5.0f)) break;
                        if (!(world.getBlockState(new BlockPos(blockPos.getX(), i, blockPos.getZ())).getBlock() instanceof BlockLiquid)) break block26;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    return false;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            --i;
        }
        return true;
    }

    void aw() {
        int i;
        EntityPlayer entityPlayer = this.getDisplayName();
        GirlAnimationState girlAnimationState = this.getCurrentAction();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (girlAnimationState != GirlAnimationState.BOOST) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            i = ServerThreadUtil.isServerThread() ? 0 : 1;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        int i2 = i;
        try {
            if (girlAnimationState.ticksPlaying[i2] < 13) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (girlAnimationState.ticksPlaying[i2] == 13) {
                this.al = 6.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = entityPlayer.getLook(0.0f).normalize();
        this.motionX = vec3d.x * (double)this.al;
        this.motionY = vec3d.y * (double)this.al;
        this.motionZ = vec3d.z * (double)this.al;
        this.al *= 0.94f;
    }

    void c() {
        this.isOwnedByLocalPlayer();
        this.getRenderPosition();
        this.getOutfitIndex();
    }

    void R() {
        try {
            GalathNpc.rotateToTarget(this, 0.0f);
            this.isTracked();
            this.aj();
            this.getSelf();
            this.L();
            this.F();
            this.C();
            this.u();
            if (this.world.isRemote) {
                this.H();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void u() {
        try {
            if (this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.CORRUPT_CUM) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (GirlAnimationState.CORRUPT_CUM.ticksPlaying[0] >= 30) {
                this.setCurrentAction(GirlAnimationState.GIVE_COIN);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void C() {
        if (((Boolean)this.DataManager.get(IsParalyzedKey)).booleanValue()) {
            this.bb = true;
            return;
        }
        switch (this.getCurrentAction()) {
            case RAPE_INTRO:
            case RAPE_ON_GOING:
            case RAPE_CUM:
            case RAPE_CHARGE:
            case RAPE_CUM_IDLE:
            case CORRUPT_SLOW:
            case CORRUPT_FAST:
            case CORRUPT_CUM:
            case MASTERBATE: {
                this.bb = true;
            }
            case RAPE_PREPARE: {
                return;
            }
        }
        this.bb = false;
    }

    @Override
    public boolean m() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.CORRUPT_INTRO) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return this.U;
    }

    void F() {
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() == GirlAnimationState.KNOCK_OUT_STAND_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.aL = true;
    }

    void j() {
        this.aO.setPercent(this.getHealth() / this.getMaxHealth());
    }

    void n() {
        boolean flag;
        GalathNpc f_2;
        try {
            if (((Boolean)this.DataManager.get(IsKnockedOutKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            f_2 = this;
            flag = this.getTargetEntity() != null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        f_2.setNoGravity(flag);
    }

    void L() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.ATTACK_SWORD) {
                this.ap = false;
                this.bu = false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    protected void collideWithNearbyEntities() {
    }

    public void addPotionEffect(PotionEffect potionEffect) {
    }

    void af() {
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (!this.bu) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = this.getPositionVector();
        Vec3d vec3d2 = this.getModelBone("weaponStart").add(vec3d);
        Vec3d vec3d3 = this.getModelBone("weaponEnd").add(vec3d);
        float f = 0.1f;
        Random random = this.getRNG();
        block22: for (float f2 = 0.0f; f2 < 1.0f; f2 += f) {
            Vec3d vec3d4 = LerpMath.lerpVec3d(vec3d2, vec3d3, (double)f2);
            int i = 0;
            while (true) {
                int i2;
                double d;
                double d2;
                double d3;
                int i3;
                double d4;
                double d5;
                double d6;
                int i4;
                double d7;
                double d8;
                EnumParticleTypes enumParticleTypes;
                World world;
                block28: {
                    block27: {
                        try {
                            try {
                                if (i >= 3) continue block22;
                                world = this.world;
                                enumParticleTypes = EnumParticleTypes.DRAGON_BREATH;
                                d8 = vec3d4.x;
                                d7 = random.nextDouble() * 0.25;
                                if (!random.nextBoolean()) break block27;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                            i4 = 1;
                            break block28;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                    }
                    i4 = -1;
                }
                try {
                    d6 = d8 + d7 * (double)i4;
                    d5 = vec3d4.y;
                    d4 = random.nextDouble() * 0.25;
                    i3 = random.nextBoolean() ? 1 : -1;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    d3 = d5 + d4 * (double)i3;
                    d2 = vec3d4.z;
                    d = random.nextDouble() * 0.25;
                    i2 = random.nextBoolean() ? 1 : -1;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                world.spawnParticle(enumParticleTypes, d6, d3, d2 + d * (double)i2, 0.0, 0.0, 0.0, new int[0]);
                ++i;
            }
        }
        int i5 = 0;
        while (true) {
            int i6;
            double d9;
            double d27;
            double d28;
            int i7;
            double d29;
            double d30;
            double d31;
            int i8;
            double d32;
            int i9;
            double d33;
            double d34;
            EnumParticleTypes enumParticleTypes;
            World world;
            block30: {
                block29: {
                    try {
                        try {
                            if (i5 >= 3) break;
                            world = this.world;
                            enumParticleTypes = EnumParticleTypes.DRAGON_BREATH;
                            d34 = vec3d3.x;
                            d33 = random.nextDouble() * 0.25;
                            if (!random.nextBoolean()) break block29;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                        i9 = 1;
                        break block30;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                }
                i9 = -1;
            }
            try {
                d32 = d33 * (double)i9;
                i8 = random.nextBoolean() ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                d31 = d34 + d32 * (double)i8;
                d30 = vec3d3.y;
                d29 = random.nextDouble() * 0.25;
                i7 = random.nextBoolean() ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                d28 = d30 + d29 * (double)i7;
                d27 = vec3d3.z;
                d9 = random.nextDouble() * 0.25;
                i6 = random.nextBoolean() ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            world.spawnParticle(enumParticleTypes, d31, d28, d27 + d9 * (double)i6, 0.0, 0.0, 0.0, new int[0]);
            ++i5;
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ag() {
        try {
            if (this.getCurrentAction() == GirlAnimationState.GALATH_DE_SUMMON) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.ActionController.tickOffset = 0.0;
    }

    @Override
    public String ab() {
        EntityPlayer entityPlayer = this.getSexPlayer();
        try {
            if (entityPlayer == null) {
                return super.getDisplayName();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return String.format("%s %s[%s]", super.getDisplayName(), TextFormatting.DARK_PURPLE, entityPlayer.getName());
    }

    void h() {
        Vec3d vec3d;
        Vec3d vec3d2;
        Vec3d vec3d3;
        Vec3d vec3d4;
        try {
            this.b2.Collidable = false;
            this.V.Collidable = false;
            if ((float)this.ad < 9.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if ((float)this.ad > 30.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.b2.Collidable = true;
        this.V.Collidable = true;
        boolean flag = (Boolean)this.DataManager.get(FlipSideKey);
        try {
            vec3d4 = this.getPositionVector();
            vec3d3 = flag ? VectorMath.mirrorXZ(RightBallOffset) : RightBallOffset;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d5 = vec3d4.add(VectorMath.rotateYaw(vec3d3, 180.0f + this.renderYawOffset));
        try {
            vec3d2 = this.getPositionVector();
            vec3d = flag ? VectorMath.mirrorXZ(LeftBallOffset) : LeftBallOffset;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d6 = vec3d2.add(VectorMath.rotateYaw(vec3d, 180.0f + this.renderYawOffset));
        this.b2.setLocationAndAngles(vec3d5.x, vec3d5.y, vec3d5.z, this.renderYawOffset, 0.0f);
        this.V.setLocationAndAngles(vec3d6.x, vec3d6.y, vec3d6.z, this.renderYawOffset, 0.0f);
        this.b2.onUpdate();
        this.V.onUpdate();
    }

    void ah() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.SUMMON_SKELETON) {
                this.ad = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.ad++ > 45) {
                this.ad = 0;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    @Override
    public Vec4d d() {
        return new Vec4d(this.a9, this.bg, this.b4, this.a_);
    }

    void aj() {
        this.b4 = this.a9;
        this.a_ = this.bg;
        Vec3d vec3d = this.W.subtract(this.bD);
        Vec3d vec3d2 = VectorMath.rotateYaw(vec3d, this.renderYawOffset + 180.0f);
        this.a9 = AngleMath.degToRadians(MathUtils.clamp(vec3d2.z * 40.0, -50.0, 50.0));
        this.bg = AngleMath.degToRadians(MathUtils.clamp(vec3d2.x * 40.0, -50.0, 50.0));
    }

    public void f(Vec3d vec3d) {
        Vec3d vec3d2;
        try {
            if (((Boolean)this.DataManager.get(IsKnockedOutKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            this.DataManager.set(IsKnockedOutKey, (Object)true);
            if (this.CurrentDeathEvent != null) {
                this.CurrentDeathEvent.tick(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.CurrentDeathEvent = null;
        Vec3d vec3d3 = this.getPositionVector();
        Random random = this.getRNG();
        try {
            vec3d2 = vec3d == null ? new Vec3d(random.nextDouble(), random.nextDouble(), random.nextDouble()).normalize() : vec3d3.subtract(vec3d).normalize();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d4 = vec3d2;
        this.setVelocity(vec3d4.x * 1.0, 1.0, vec3d4.z * 1.0);
        this.setCurrentAction(GirlAnimationState.KNOCK_OUT_FLY);
        this.setNoGravity(false);
        this.noClip = false;
        this.getNavigator().clearPath();
        GalathNpc.playSoundRandom((GirlEntity)this, ModSounds.GIRLS_GALATH_AAA, true);
    }

    void a(Entity entity) {
        GirlEntity.sendMessageToNearbyPlayers((GirlEntity)this, TextFormatting.YELLOW + "Galath is paralyzed! Now it's time to corrupt her");
        GirlEntity.sendMessageToNearbyPlayers((GirlEntity)this, TextFormatting.GRAY + "(Walk to her and right click her)");
        NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticles(this.getPositionVector(), true), (Entity)this);
        this.f((Vec3d)null);
        this.DataManager.set(IsParalyzedKey, (Object)true);
    }

    @Override
    public void updateAITasks() {
        block6: {
            block5: {
                try {
                    if (this.P) {
                        GalathOwnershipData.releaseOwnedGalath(this);
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    this.P();
                    super.updateAITasks();
                    this.WatchPlayerAI.Active = this.resetMasterAndWalkSpeed();
                    if (!this.k()) break block5;
                    this.getSexPlayerUuid();
                    break block6;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            this.an();
        }
    }

    void P() {
        try {
            if (this.bK) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.f(CustomModelWorldData.getCustomModelName(this));
        this.bK = true;
    }

    boolean x() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.NULL) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (Math.abs(this.motionX) + Math.abs(this.motionZ) > 0.01) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return true;
    }

    void aq() {
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getDisplayName() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.getSexPlayer();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.d(entityPlayer);
    }

    void d(EntityPlayer entityPlayer) {
        float f;
        double d;
        double d2;
        Vec3d vec3d;
        Vec3d vec3d2;
        PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(entityPlayer.getPersistentID());
        try {
            Vec3d vec3d3;
            vec3d2 = vec3d3;
            vec3d = vec3d3;
            d2 = entityPlayer.posX;
            d = entityPlayer.posY;
            f = playerGirl == null ? entityPlayer.eyeHeight : playerGirl.getEyeHeight();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        vec3d2(d2, d + (double)f, entityPlayer.posZ);
        Vec3d vec3d4 = vec3d;
        Vec3d vec3d5 = new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
        double d3 = vec3d5.distanceTo(vec3d4);
        double d4 = vec3d4.y - vec3d5.y;
        this.rotationPitch = (float)(-(Math.sin(d4 / d3) * 57.29577951308232));
    }

    void ae() {
        block9: {
            block8: {
                try {
                    this.aO.setVisible(false);
                    if (!GalathOwnershipData.isPlayerNearOwnedGalath(this)) {
                        GalathOwnershipData.releaseOwnedGalath(this);
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (this.getDisplayName() != null) {
                        this.getCurrentAction();
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    this.m();
                    if (this.getChildMangleUuid() != null) break block8;
                    this.aJ();
                    break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            this.am();
        }
    }

    void m() {
        try {
            if (!GalathOwnershipData.isOwnerOnline(GalathOwnershipData.getGalathOwnerUuidByEntity(this))) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        boolean flag = this.getScale();
        try {
            if (flag) {
                Main.LOGGER.warn("mommy thinks she got no daughter but she actually does have one. Failsafe called. Hopefully its fixed");
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void am() {
        try {
            if (this.ai()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.DataManager.set(bT, (Object)false);
        this.ao();
    }

    boolean ai() {
        double d;
        boolean flag;
        PathNavigate pathNavigate;
        float f;
        block40: {
            block39: {
                EntityPlayer entityPlayer;
                block38: {
                    block35: {
                        block37: {
                            BlockPos blockPos;
                            block36: {
                                UUID uUID = GalathOwnershipData.getGalathOwnerUuidByEntity(this);
                                try {
                                    if (uUID == null) {
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                entityPlayer = this.world.getPlayerEntityByUUID(uUID);
                                try {
                                    if (entityPlayer == null) {
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                blockPos = entityPlayer.getPosition();
                                try {
                                    if (!this.a(blockPos)) {
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                try {
                                    if (this.CurrentDeathEvent != null) {
                                        this.CurrentDeathEvent.tick(this);
                                        this.CurrentDeathEvent = null;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                f = this.getDistance((Entity)entityPlayer);
                                pathNavigate = this.getNavigator();
                                try {
                                    if (f < 4.0f) {
                                        pathNavigate.clearPath();
                                        return false;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                try {
                                    if (f > 16.0f) {
                                        pathNavigate.clearPath();
                                        this.canInteract(entityPlayer);
                                        return true;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                try {
                                    try {
                                        if (!(PathUtil.getPathEndPos(this.aq).distanceSq((Vec3i)blockPos) > 16.0)) break block35;
                                        if (this.onGround) break block36;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                    return true;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                this.aq = this.a(entityPlayer, blockPos);
                                if (this.aq != null) break block37;
                                this.canInteract(entityPlayer);
                                break block35;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                        }
                        pathNavigate.setPath(this.aq, 1.0);
                    }
                    try {
                        try {
                            if (this.aq != null && !this.aq.isFinished()) break block38;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                        return false;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                }
                try {
                    try {
                        if (!entityPlayer.isSprinting() && !(this.getDistance((Entity)entityPlayer) > 7.0f)) break block39;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    flag = true;
                    break block40;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            flag = false;
        }
        boolean flag2 = flag;
        try {
            d = flag2 ? (double)0.55f : 0.5;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        double d2 = d;
        double d3 = Math.floor(f / 5.0f) * 0.2;
        d2 += d3;
        if (this.isInWater()) {
            d2 *= 60.0;
        }
        pathNavigate.setSpeed(d2);
        this.DataManager.set(bT, (Object)flag2);
        this.setCurrentAction((GirlAnimationState)null);
        return true;
    }

    boolean a(BlockPos blockPos) {
        boolean flag;
        try {
            if (this.CurrentDeathEvent == null) {
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        BlockPos blockPos2 = this.getPosition();
        int i = Math.abs(blockPos.getX() - blockPos2.getX()) + Math.abs(blockPos.getX() - blockPos2.getX());
        try {
            flag = i > 16;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return flag;
    }

    protected void b(EntityPlayer entityPlayer) {
        BlockPos blockPos;
        int i = 0;
        do {
            blockPos = entityPlayer.getPosition().add(ModConstants.Random.nextInt(4), 0, ModConstants.Random.nextInt(4));
        } while (++i < 20 && !this.attemptTeleport(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
        try {
            if (i >= 20) {
                this.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
    }

    @Nullable
    Path a(EntityPlayer entityPlayer, BlockPos blockPos) {
        PathNavigate pathNavigate = this.getNavigator();
        return pathNavigate.getPathToEntityLiving((Entity)entityPlayer);
    }

    void aJ() {
        this.at();
        this.ay();
    }

    void y() {
        try {
            this.bG = null;
            this.aC = 0;
            if (this.CurrentDeathEvent != null) {
                this.CurrentDeathEvent.tick(this);
                this.CurrentDeathEvent = null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void at() {
        ManglelieNpc manglelie;
        ManglelieNpc manglelie2;
        block26: {
            block27: {
                try {
                    if (!this.onGround) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (this.getChildMangleUuid() != null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (this.getCurrentAction() == GirlAnimationState.HUG_MANG) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (GalathOwnershipData.isOwnerOnline(GalathOwnershipData.getOwnerUuid(this.getGirlUuid()))) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                BlockPos blockPos = this.getPosition();
                BlockPos blockPos2 = blockPos.add(-15.0, -15.0, -15.0);
                BlockPos blockPos3 = blockPos.add(15.0, 15.0, 15.0);
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos2, blockPos3);
                List list = this.world.getEntitiesWithinAABB(ManglelieNpc.class, axisAlignedBB);
                manglelie2 = null;
                for (ManglelieNpc manglelie3 : list) {
                    try {
                        if (manglelie3.isDead) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    try {
                        if (manglelie3.getMommy(true) != null) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    manglelie2 = manglelie3;
                    break;
                }
                try {
                    try {
                        if (manglelie2 != null) break block26;
                        if (this.getCurrentAction() != GirlAnimationState.RUN) break block27;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    this.setCurrentAction((GirlAnimationState)null);
                    this.getNavigator().clearPath();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            return;
        }
        try {
            this.Navigation = this.getNavigator();
            if (manglelie2.getDistance((Entity)this) <= 3.65f) {
                this.Navigation.clearPath();
                this.setCurrentAction(GirlAnimationState.HUG_MANG);
                this.motionX = 0.0;
                this.motionY = 0.0;
                this.motionZ = 0.0;
                this.setTargetPos(this.getPositionVector());
                this.getChildMangle(true);
                this.setChildMangleUuid(manglelie2.getGirlUuid());
                manglelie2.setMommyUuid(this.getGirlUuid());
                manglelie2.setAnimationState(GirlAnimationState.RIDE_MOMMY_HEAD);
                GalathOwnershipData.markOwnerOnline(this.getGirlUuid());
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = this.getPositionVector();
        manglelie3 = manglelie2.getPositionVector();
        Vec3d vec3d2 = manglelie3.subtract(vec3d);
        float f = (float)AngleMath.radToDegrees(Math.atan2(vec3d2.z, vec3d2.x)) - 90.0f;
        this.b(f);
        this.Navigation.clearPath();
        this.Navigation.tryMoveToEntityLiving((Entity)manglelie2, (double)0.65f);
        this.setCurrentAction(GirlAnimationState.RUN);
    }

    void ay() {
        block39: {
            block38: {
                block35: {
                    int i;
                    int i2;
                    int i3;
                    block37: {
                        block36: {
                            block33: {
                                block32: {
                                    GirlAnimationState girlAnimationState = this.getCurrentAction();
                                    try {
                                        if (girlAnimationState == GirlAnimationState.RUN) {
                                            return;
                                        }
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                    try {
                                        if (girlAnimationState == GirlAnimationState.HUG_MANG) {
                                            return;
                                        }
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                    try {
                                        try {
                                            if (!this.Q() && girlAnimationState != GirlAnimationState.MASTERBATE) break block32;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GalathNpc.rethrow(concurrentModificationException);
                                        }
                                        this.getNavigator().clearPath();
                                        return;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                }
                                EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 15.0);
                                try {
                                    try {
                                        try {
                                            try {
                                                if (!this.J() || entityPlayer == null) break block33;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GalathNpc.rethrow(concurrentModificationException);
                                            }
                                            if (!(entityPlayer.getDistance((Entity)this) < 2.0f)) break block33;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GalathNpc.rethrow(concurrentModificationException);
                                        }
                                        if (!entityPlayer.getPersistentID().equals(this.O())) break block33;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                    this.getNavigator().clearPath();
                                    return;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    block34: {
                                        try {
                                            try {
                                                if (this.bG == null || this.getDistance(this.bG.getX(), this.bG.getY(), this.bG.getZ()) > this.getRenderLabelOffset()) break block34;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GalathNpc.rethrow(concurrentModificationException);
                                            }
                                            if (this.aC <= 175) break block35;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GalathNpc.rethrow(concurrentModificationException);
                                        }
                                    }
                                    if (!this.getRNG().nextBoolean()) break block36;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                i3 = 1;
                                break block37;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                        }
                        i3 = -1;
                    }
                    int i4 = i3 * this.getRNG().nextInt(10);
                    try {
                        i2 = this.getRNG().nextBoolean() ? 1 : -1;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    int i5 = i2 * this.getRNG().nextInt(10);
                    try {
                        i = this.world.provider.getDimensionType() == DimensionType.NETHER ? (int)Math.ceil(this.posY) : BedLogic.countNearbyBeds(this.world, this.getPosition().getX() + i4, this.getPosition().getZ() + i5);
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    int i6 = i;
                    this.bG = new BlockPos(this.getPosition().getX() + i4, i6, this.getPosition().getZ() + i5);
                    this.aC = 0;
                }
                try {
                    if (!(Math.sqrt(this.bG.distanceSq((Vec3i)this.getPosition())) > 2.0)) break block38;
                    this.getNavigator().tryMoveToXYZ((double)this.bG.getX(), (double)this.bG.getY(), (double)this.bG.getZ(), (double)0.35f);
                    this.k();
                    break block39;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            ++this.aC;
        }
    }

    BlockPos av() {
        UUID uUID = GalathOwnershipData.getGalathOwnerUuidByEntity(this);
        try {
            if (uUID == null) {
                return BlockPos.ORIGIN;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return BlockPos.ORIGIN;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return entityPlayer.getPosition();
    }

    double i() {
        return Math.sqrt(1800.0);
    }

    @Nullable
    public EntityPlayer ab() {
        List list = this.getPassengers();
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (list.get(0) instanceof EntityPlayer) {
                return (EntityPlayer)list.get(0);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return null;
    }

    @Nullable
    public UUID getAttackPlayerUuid() {
        EntityPlayer entityPlayer = this.getDisplayName();
        try {
            if (entityPlayer == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return entityPlayer.getPersistentID();
    }

    @Override
    public void g(String string) {
        super.setCustomName(string);
        UUID uUID = this.O();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        GirlHomeWorldData.setCustomName(uUID, GirlRegistry.GALATH, string);
    }

    public void addVelocity(Vec3d vec3d) {
        this.motionX += vec3d.x;
        this.motionZ += vec3d.z;
        this.motionY = vec3d.y / 2.0;
    }

    public void resetMangle() {
        this.e((UUID)null);
        this.setCurrentAction((GirlAnimationState)null);
    }

    void aB() {
        EntityPlayer entityPlayer = this.getDisplayName();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.prevRenderYawOffset = entityPlayer.prevRotationYawHead;
        this.renderYawOffset = entityPlayer.rotationYawHead;
    }

    void an() {
        this.aO.setVisible(true);
        this.ao();
        this.as();
    }

    void ao() {
        try {
            if (GirlAnimationState.isGirlInAnimation((GirlEntity)this, GirlAnimationState.MASTERBATE, GirlAnimationState.HUG_MANG)) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.Q();
        this.I();
        this.D();
        this.getWalkState();
        this.J();
        this.T();
        this.S();
        this.b();
        this.getAllGirls();
        this.aG();
        this.aA();
        this.aD();
        this.O();
        this.Z();
    }

    void Q() {
        try {
            if (!this.k()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getTargetEntity() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        int i = (Integer)this.DataManager.get(TargetEntityIdKey);
        try {
            if (i == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.CurrentDeathEvent != null) {
                this.CurrentDeathEvent.tick(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.CurrentDeathEvent = null;
        this.setCurrentAction(GirlAnimationState.NULL);
    }

    void as() {
        try {
            if (this.getTargetEntity() != null) {
                this.bG = null;
                this.aC = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (((Boolean)this.DataManager.get(IsParalyzedKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (((Boolean)this.DataManager.get(IsKnockedOutKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.ay();
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block55: {
            GirlAnimationState girlAnimationState2;
            block54: {
                block53: {
                    block51: {
                        block52: {
                            block50: {
                                block48: {
                                    block47: {
                                        block45: {
                                            girlAnimationState2 = this.getCurrentAction();
                                            try {
                                                if (girlAnimationState2 == GirlAnimationState.GALATH_DE_SUMMON) {
                                                    return;
                                                }
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GalathNpc.rethrow(concurrentModificationException);
                                            }
                                            try {
                                                block46: {
                                                    try {
                                                        try {
                                                            if (girlAnimationState2 != GirlAnimationState.CORRUPT_CUM) break block45;
                                                            if (girlAnimationState == GirlAnimationState.CORRUPT_FAST) break block46;
                                                        }
                                                        catch (ConcurrentModificationException concurrentModificationException) {
                                                            throw GalathNpc.rethrow(concurrentModificationException);
                                                        }
                                                        if (girlAnimationState != GirlAnimationState.CORRUPT_SLOW) break block45;
                                                    }
                                                    catch (ConcurrentModificationException concurrentModificationException) {
                                                        throw GalathNpc.rethrow(concurrentModificationException);
                                                    }
                                                }
                                                return;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GalathNpc.rethrow(concurrentModificationException);
                                            }
                                        }
                                        try {
                                            try {
                                                if (girlAnimationState2 != GirlAnimationState.RAPE_CUM || girlAnimationState != GirlAnimationState.RAPE_ON_GOING) break block47;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GalathNpc.rethrow(concurrentModificationException);
                                            }
                                            return;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GalathNpc.rethrow(concurrentModificationException);
                                        }
                                    }
                                    try {
                                        block49: {
                                            try {
                                                try {
                                                    if (girlAnimationState2 != GirlAnimationState.MORNING_BLOWJOB_CUM) break block48;
                                                    if (girlAnimationState == GirlAnimationState.MORNING_BLOWJOB_SLOW) break block49;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw GalathNpc.rethrow(concurrentModificationException);
                                                }
                                                if (girlAnimationState != GirlAnimationState.MORNING_BLOWJOB_FAST) break block48;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GalathNpc.rethrow(concurrentModificationException);
                                            }
                                        }
                                        return;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                }
                                try {
                                    try {
                                        if (this.world.isRemote || !GirlAnimationState.isAnimationInList(girlAnimationState2, GirlAnimationState.CORRUPT_CUM, GirlAnimationState.RAPE_CUM, GirlAnimationState.MORNING_BLOWJOB_CUM)) break block50;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                    GalathOwnershipData.setLastSeenTime(this.getSexPlayerUuid(), this.world.getTotalWorldTime());
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    if (girlAnimationState != GirlAnimationState.CORRUPT_SLOW) break block51;
                                    this.aT = false;
                                    if (girlAnimationState2 != GirlAnimationState.CORRUPT_INTRO) break block52;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                this.d(false);
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                        }
                        try {
                            try {
                                if (!this.k() || girlAnimationState2 != GirlAnimationState.NULL) break block51;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                            this.d(true);
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.GIVE_COIN || girlAnimationState != GirlAnimationState.NULL) break block53;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                            if (this.world.isRemote) break block53;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                        this.ap();
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState2 != GirlAnimationState.HUG_MANG || girlAnimationState != GirlAnimationState.NULL) break block54;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    this.al();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            try {
                try {
                    if (girlAnimationState2 != GirlAnimationState.MORNING_BLOWJOB_CUM || girlAnimationState != GirlAnimationState.NULL) break block55;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                this.aE();
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    void aE() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer != null) {
                PacketResetGirl.Handler.a((EntityPlayerMP)entityPlayer);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        PacketResetGirl.Handler.openGui(this);
    }

    void al() {
        this.getChildMangle(false);
        ManglelieNpc manglelie = this.getChildMangle(true);
        try {
            if (manglelie == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        manglelie.setClaimed(true);
    }

    void ap() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        ItemStack itemStack = entityPlayer.getHeldItemMainhand();
        try {
            entityPlayer.setHeldItem(EnumHand.MAIN_HAND, new ItemStack((Item)ItemGalathCoin.Instance));
            if (!itemStack.isEmpty()) {
                entityPlayer.inventory.addItemStackToInventory(itemStack);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), (EntityPlayerMP)entityPlayer);
        this.e((UUID)null);
        this.setTargetEntity((EntityLivingBase)null);
        entityPlayer.sendMessage((ITextComponent)new TextComponentString(TextFormatting.GRAY + "Defeating a succubus makes her accept the victor as her master, granting him a coin to which her soul is bound. Using the coin summons her, offering services on demand. If her master uses the coin on her or goes too far, she returns to the coin"));
        GalathOwnershipData.releaseOwnedGalath(this);
        entityPlayer.setPositionAndUpdate(entityPlayer.posX, Math.ceil(entityPlayer.posY) + 1.0, entityPlayer.posZ);
    }

    @SideOnly(value=Side.CLIENT)
    void H() {
        float f;
        float f2;
        GirlAnimationState girlAnimationState;
        block9: {
            girlAnimationState = this.getCurrentAction();
            try {
                try {
                    if (this.ab != GirlAnimationState.CORRUPT_INTRO && girlAnimationState == GirlAnimationState.CORRUPT_INTRO) break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                this.ab = girlAnimationState;
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        try {
            if (!entityPlayerSP.getPersistentID().equals(this.getSexPlayerUuid())) {
                this.ab = girlAnimationState;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            f2 = this.k() ? 0.0f : this.I().floatValue() + 180.0f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        entityPlayerSP.rotationYaw = f = f2;
        entityPlayerSP.prevRotationYaw = f;
        entityPlayerSP.rotationPitch = 80.0f;
        entityPlayerSP.prevRotationPitch = 80.0f;
        this.ab = girlAnimationState;
    }

    void d(boolean flag) {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = flag ? new Vec3d(-0.5, (double)(0.5f - entityPlayer.getEyeHeight()), (double)0.4f).add(this.getTargetPos()) : VectorMath.rotateYaw(new Vec3d(0.5, (double)(0.5f - entityPlayer.getEyeHeight()), (double)0.4f), this.I().floatValue()).add(this.getTargetPos());
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public float v() {
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft.gameSettings.thirdPersonView != 0) {
            return 1.0f;
        }
        switch (this.getCurrentAction()) {
            case CORRUPT_INTRO: {
                if (!this.U) break;
            }
            case CORRUPT_SLOW:
            case CORRUPT_FAST:
            case CORRUPT_CUM: {
                return 0.5f;
            }
        }
        return 1.0f;
    }

    @Override
    protected boolean X() {
        return false;
    }

    public boolean v() {
        try {
            if (this.getChildMangle(true) != null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        ManglelieNpc manglelie = new ManglelieNpc(this.world);
        this.setChildMangleUuid(manglelie.getGirlUuid());
        manglelie.setMommyUuid(this.getGirlUuid());
        manglelie.setClaimed(true);
        manglelie.setAnimationState(GirlAnimationState.RIDE_MOMMY_HEAD);
        manglelie.setPositionAndUpdate(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)manglelie);
        return true;
    }

    void Z() {
        try {
            if (this.k()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        GirlAnimationState girlAnimationState = this.getCurrentAction();
        try {
            if (girlAnimationState != GirlAnimationState.RAPE_CUM) {
                this.at = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                this.at = 0;
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.at != 15) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        entityPlayer.attackEntityFrom((DamageSource)new GalathMeleeDamageSource(this), 2.14748365E9f);
    }

    void O() {
        EntityLivingBase entityLivingBase = this.getTargetEntity();
        try {
            if (entityLivingBase == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (entityWitherSkeleton.isDead) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                if (entityLivingBase.getDistance((Entity)entityWitherSkeleton) < 15.0f) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticles(entityWitherSkeleton.getPositionVector(), true), (Entity)this);
            entityWitherSkeleton.setDead();
            this.world.removeEntity((Entity)entityWitherSkeleton);
        }
    }

    void aD() {
        try {
            if (!((Boolean)this.DataManager.get(IsKnockedOutKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (entityWitherSkeleton.isDead) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticles(entityWitherSkeleton.getPositionVector(), true), (Entity)this);
            entityWitherSkeleton.setDead();
            this.world.removeEntity((Entity)entityWitherSkeleton);
        }
        this.bI.clear();
    }

    public static void c(EntityPlayer entityPlayer) {
        GirlEntity girl = GirlEntity.getServerSideByUuid(GalathOwnershipData.getGalathByPlayer(entityPlayer));
        try {
            if (girl == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (girl.equals(entityPlayer.getRidingEntity())) {
                girl.handleGirlUuidEvent(entityPlayer.getPersistentID());
                girl.setCurrentAction(GirlAnimationState.CONTROLLED_FLIGHT);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void aA() {
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (entityWitherSkeleton.isDead) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                if (entityWitherSkeleton.ticksExisted % 10 != 0) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            Set set = ((WorldServer)this.world).getEntityTracker().getTrackingPlayers((Entity)entityWitherSkeleton);
            for (EntityPlayer entityPlayer : set) {
                ((EntityPlayerMP)entityPlayer).connection.sendPacket((Packet)new SPacketParticles(EnumParticleTypes.DRAGON_BREATH, false, (float)entityWitherSkeleton.posX, (float)entityWitherSkeleton.posY, (float)entityWitherSkeleton.posZ, 0.2f * (float)MathUtils.randomSign(), entityWitherSkeleton.getEyeHeight() / 2.0f, 0.2f * (float)MathUtils.randomSign(), 0.0f, 5, new int[0]));
            }
        }
    }

    void aG() {
        ArrayList<EntityWitherSkeleton> arrayList = new ArrayList<EntityWitherSkeleton>();
        for (EntityWitherSkeleton entityWitherSkeleton : this.bI) {
            try {
                if (!entityWitherSkeleton.isDead) continue;
                arrayList.add(entityWitherSkeleton);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        for (EntityWitherSkeleton entityWitherSkeleton : arrayList) {
            this.bI.remove(entityWitherSkeleton);
        }
    }

    void ad() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.KNOCK_OUT_STAND_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        ++this.bY;
        if ((double)this.bY == 39.0) {
            this.setNoGravity(true);
            this.setVelocity(0.0, 0.6f, 0.0);
            Vec3d vec3d = this.getPositionVector();
            Vec3d vec3d2 = vec3d.subtract(2.0, 2.0, 2.0);
            Vec3d vec3d3 = vec3d.add(2.0, 2.0, 2.0);
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d2.x, vec3d2.y, vec3d2.z, vec3d3.x, vec3d3.y, vec3d3.z);
            List list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, axisAlignedBB);
            for (EntityLivingBase entityLivingBase : list) {
                try {
                    if (entityLivingBase instanceof GalathNpc) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                Vec3d vec3d4 = entityLivingBase.getPositionVector();
                Vec3d vec3d5 = vec3d4.subtract(vec3d).normalize();
                try {
                    entityLivingBase.motionX = vec3d5.x * 1.0;
                    entityLivingBase.motionZ = vec3d5.z * 1.0;
                    entityLivingBase.motionY = 1.0;
                    entityLivingBase.attackEntityFrom((DamageSource)new GalathProjectileDamageSource(this), 0.5f);
                    if (!(entityLivingBase instanceof EntityPlayerMP)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entityLivingBase;
                entityPlayerMP.connection.sendPacket((Packet)new SPacketEntityVelocity((Entity)entityPlayerMP));
            }
        }
        try {
            if ((double)this.bY < 58.0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.b(Vec3d.ZERO);
        this.DataManager.set(IsKnockedOutKey, (Object)false);
        this.bY = 0;
    }

    void b() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.KNOCK_OUT_GROUND) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (((Boolean)this.DataManager.get(IsParalyzedKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            int i;
            ++this.b3;
            if ((double)i < 50.0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.setCurrentAction(GirlAnimationState.KNOCK_OUT_STAND_UP);
        this.bY = 0;
        this.b3 = 0;
    }

    void S() {
        block7: {
            GirlAnimationState girlAnimationState = this.getCurrentAction();
            try {
                try {
                    if (girlAnimationState == GirlAnimationState.KNOCK_OUT_GROUND || girlAnimationState == GirlAnimationState.KNOCK_OUT_STAND_UP) break block7;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            this.motionX = 0.0;
            this.motionZ = 0.0;
            if (((Boolean)this.DataManager.get(IsParalyzedKey)).booleanValue()) {
                this.motionY = 0.0;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void T() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.KNOCK_OUT_FLY) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        BlockPos blockPos = this.getPosition();
        if (this.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
            BlockPos blockPos2 = blockPos;
            while (this.world.getBlockState(blockPos2.up()).getBlock() instanceof BlockLiquid) {
                blockPos2 = blockPos2.up();
            }
            for (int i = -1; i < 2; ++i) {
                try {
                    for (int i4 = -1; i4 < 2; ++i4) {
                        this.world.setBlockState(blockPos2.add(i, 0, i4), Blocks.OBSIDIAN.getDefaultState());
                    }
                    continue;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            blockPos2 = blockPos2.up();
            this.setPositionAndUpdate(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ());
            this.setTargetPos(new Vec3d((Vec3i)blockPos2));
            NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticles(new Vec3d((Vec3i)blockPos2), true), (Entity)this);
            for (EntityPlayer entityPlayer : ((WorldServer)this.world).getEntityTracker().getTrackingPlayers((Entity)this)) {
                ((EntityPlayerMP)entityPlayer).connection.sendPacket((Packet)new SPacketSoundEffect(SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.AMBIENT, this.posX, this.posY, this.posZ, 1.0f, 1.0f));
            }
            this.setCurrentAction(GirlAnimationState.KNOCK_OUT_GROUND);
            return;
        }
        try {
            if (!this.onGround) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.setCurrentAction(GirlAnimationState.KNOCK_OUT_GROUND);
    }

    void J() {
        boolean flag;
        try {
            if (this.CurrentDeathEvent != DeadClass.CHANGE_POSITION) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        int i = this.getCombatTargetId();
        try {
            GalathNpc f_2 = this;
            flag = i == 0;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            f_2.noClip = flag;
            if (!this.world.isAirBlock(this.getPosition())) {
                this.noClip = true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void q() {
        try {
            if (this.CurrentDeathEvent == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.CurrentDeathEvent.apply(this);
    }

    void D() {
        try {
            if (this.getTargetEntity() == null) {
                this.aH();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.CurrentDeathEvent == null) {
                this.getSexPlayer();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.CurrentDeathEvent.isDone(this)) {
                this.getSexPlayer();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    void z() {
        DeadClass deadClass;
        if (((Boolean)this.DataManager.get(IsKnockedOutKey)).booleanValue()) {
            return;
        }
        DeadClass deadClass2 = this.CurrentDeathEvent;
        if (this.getSexPlayerUuid() != null) {
            if (deadClass2 != null) {
                deadClass2.tick(this);
            }
            this.CurrentDeathEvent = null;
            return;
        }
        if (deadClass2 != null && deadClass2.applyAttackCoolDown) {
            deadClass2.tick(this);
            this.CurrentDeathEvent = DeadClass.CHANGE_POSITION;
            this.CurrentDeathEvent.start(this);
            return;
        }
        DeadClass[] deadClassArray = DeadClass.values();
        while (!this.a(deadClass = deadClassArray[this.getRNG().nextInt(deadClassArray.length)])) {
        }
        this.CurrentDeathEvent = deadClass;
        if (deadClass2 != null) {
            deadClass2.tick(this);
        }
        this.CurrentDeathEvent.start(this);
    }

    boolean a(DeadClass dead) {
        block4: {
            try {
                try {
                    if (!dead.onlyDoThisOnPlayers || this.getTargetEntity() instanceof EntityPlayer) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        return dead.canDo(this);
    }

    void aH() {
        this.CurrentDeathEvent = null;
    }

    void I() {
        Object object;
        float f;
        try {
            if (this.getGirlUuid()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        boolean flag = this.k();
        try {
            f = flag ? 7.0f : 20.0f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        float f2 = f;
        Vec3d vec3d = new Vec3d((double)f2, (double)f2, (double)f2);
        Vec3d vec3d2 = this.getPositionVector();
        Vec3d vec3d3 = vec3d2.subtract(vec3d);
        Vec3d vec3d4 = vec3d2.add(vec3d);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d3.x, vec3d3.y, vec3d3.z, vec3d4.x, vec3d4.y, vec3d4.z);
        try {
            object = flag ? this.a(axisAlignedBB) : this.b(axisAlignedBB);
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        EntityMob entityMob = object;
        try {
            if (entityMob == null) {
                this.aI();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            this.setTargetEntity((EntityLivingBase)entityMob);
            GirlEntity.playSound((GirlEntity)this, ModSounds.GIRLS_GALATH_DIALOG[1], true);
            if (this.CurrentDeathEvent != null) {
                this.CurrentDeathEvent.tick(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.CurrentDeathEvent = DeadClass.CHANGE_POSITION;
        this.CurrentDeathEvent.start(this);
    }

    EntityPlayer b(AxisAlignedBB axisAlignedBB) {
        List list = this.world.getEntitiesWithinAABB(EntityPlayer.class, axisAlignedBB, entityPlayer -> {
            boolean flag;
            block7: {
                block6: {
                    try {
                        try {
                            try {
                                if (PlayerGirlEntity.isPlayerGirl(entityPlayer) || entityPlayer.isCreative()) break block6;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                            if (entityPlayer.isSpectator()) break block6;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                        flag = true;
                        break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                }
                flag = false;
            }
            return flag;
        });
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return (EntityPlayer)list.get(0);
    }

    EntityMob a(AxisAlignedBB axisAlignedBB) {
        List list = this.world.getEntitiesWithinAABB(EntityMob.class, axisAlignedBB);
        try {
            if (list.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        ArrayList<EntityMob> arrayList = new ArrayList<EntityMob>();
        for (Object object : list) {
            try {
                if (!LineOfSight.canSeeEntity((Entity)object)) continue;
                arrayList.add((EntityMob)object);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        Vec3d vec3d = this.getPositionVector().add(0.0, (double)this.getEyeHeight(), 0.0);
        for (EntityMob entityMob : arrayList) {
            try {
                if (!LineOfSight.hasLineOfSight(this.world, vec3d, (Entity)entityMob)) continue;
                return entityMob;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        return null;
    }

    void aI() {
        try {
            if (this.getTargetEntity() == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            this.setTargetEntity((EntityLivingBase)null);
            if (this.CurrentDeathEvent != null) {
                this.CurrentDeathEvent.tick(this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            this.CurrentDeathEvent = null;
            if (((Boolean)this.DataManager.get(IsKnockedOutKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.setCurrentAction(GirlAnimationState.NULL);
    }

    boolean f() {
        float f;
        EntityLivingBase entityLivingBase = this.getTargetEntity();
        try {
            if (entityLivingBase == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (entityLivingBase.isDead) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (entityLivingBase.dimension != this.dimension) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        float f2 = this.getDistance((Entity)entityLivingBase);
        try {
            f = this.k() ? 16.0f : 30.0f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        float f3 = f;
        try {
            if (f2 > f3) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (!(entityLivingBase instanceof EntityPlayer)) {
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
        try {
            if (GirlEntity.getByUuid(entityPlayer.getPersistentID()) != null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (entityPlayer.isCreative()) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (entityPlayer.isSpectator()) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public GirlEntity E() {
        ManglelieNpc manglelie = this.getChildMangle(false);
        try {
            if (manglelie == null) {
                return super.E();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
        try {
            if (entityPlayerSP.isSneaking()) {
                return manglelie;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        entityPlayerSP.sendStatusMessage((ITextComponent)new TextComponentString(TextFormatting.GRAY + "[sneak] + [right click] if you want to edit Manglelie instead"), true);
        return super.E();
    }

    protected boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        try {
            if (this.k()) {
                return this.a(entityPlayer, enumHand);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return this.b(entityPlayer, enumHand);
    }

    boolean a(EntityPlayer entityPlayer, EnumHand enumHand) {
        block13: {
            try {
                if (!entityPlayer.getPersistentID().equals(this.O())) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                if (GirlAnimationState.isGirlInAnimation((GirlEntity)this, GirlAnimationState.HUG_MANG, GirlAnimationState.RUN, GirlAnimationState.GALATH_SUMMON, GirlAnimationState.GALATH_DE_SUMMON, GirlAnimationState.MASTERBATE)) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            try {
                try {
                    if (!ItemGalathCoin.Instance.equals(entityPlayer.getHeldItem(EnumHand.OFF_HAND).getItem()) && !ItemGalathCoin.Instance.equals(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem())) break block13;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        this.a(ModSounds.GIRLS_GALATH_HUH, new int[0]);
        String[] stringArray = !entityPlayer.onGround ? new String[]{"ride"} : (this.getChildMangle(false) == null ? new String[]{"cowgirl", "anal", "ride"} : new String[]{"cowgirl", "anal", "threesome", "ride"});
        try {
            if (this.world.isRemote) {
                GalathNpc.openActionMenu(entityPlayer, this.getSelf(), stringArray, false);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onInteractionCommand(String string, UUID uUID) {
        try {
            if ("ride".equals(string)) {
                GuiGalathFlight.startFlight();
                NetworkHandler.channel.sendToServer((IMessage)new PacketRequestRiding());
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if ("anal".equals(string)) {
                GuiTransitionScreen.startTransition();
                AnimationInputLock.setAnimationLocked(false);
                MathUtils.runAfterDelay(1200, () -> {
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    this.setTargetPos(entityPlayerSP.getPositionVector());
                    this.b(0.0f);
                    this.e(entityPlayerSP.getPersistentID());
                    this.getChildMangle(true);
                    this.setCurrentAction(GirlAnimationState.CORRUPT_SLOW);
                });
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if ("cowgirl".equals(string)) {
                GuiTransitionScreen.startTransition();
                AnimationInputLock.setAnimationLocked(false);
                MathUtils.runAfterDelay(1200, () -> {
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    this.setTargetPos(entityPlayerSP.getPositionVector());
                    this.b(entityPlayerSP.rotationYaw + 180.0f);
                    this.setCurrentAction(GirlAnimationState.RAPE_INTRO);
                    this.e(entityPlayerSP.getPersistentID());
                    this.getChildMangle(true);
                });
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        if ("threesome".equals(string)) {
            ManglelieNpc manglelie = this.getChildMangle(false);
            try {
                if (manglelie == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
            GuiTransitionScreen.startTransition();
            AnimationInputLock.setAnimationLocked(false);
            MathUtils.runAfterDelay(1200, () -> {
                Minecraft minecraft = Minecraft.getMinecraft();
                EntityPlayerSP entityPlayerSP = minecraft.player;
                minecraft.gameSettings.thirdPersonView = 1;
                manglelie.c(entityPlayerSP.getPositionVector());
                this.setTargetPos(entityPlayerSP.getPositionVector());
                manglelie.b(entityPlayerSP.rotationYaw + 180.0f);
                this.b(entityPlayerSP.rotationYaw);
                manglelie.setAnimationState(GirlAnimationState.THREESOME_SLOW);
                this.setCurrentAction(GirlAnimationState.PUSSY_LICKING);
                manglelie.handleGirlUuidEvent(entityPlayerSP.getPersistentID());
                this.e(entityPlayerSP.getPersistentID());
                manglelie.getMommy(true);
                this.getChildMangle(true);
            });
        }
    }

    boolean b(EntityPlayer entityPlayer, EnumHand enumHand) {
        try {
            if (!((Boolean)this.DataManager.get(IsKnockedOutKey)).booleanValue()) {
                return super.processInteract(entityPlayer, enumHand);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.KNOCK_OUT_GROUND) {
                return super.processInteract(entityPlayer, enumHand);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.world.isRemote) {
                entityPlayer.rotationYaw -= -128.0f;
                entityPlayer.rotationPitch = 19.0f;
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.setCurrentAction(GirlAnimationState.CORRUPT_INTRO);
        this.e(entityPlayer.getPersistentID());
        this.getChildMangle(true);
        this.setTargetPos(this.getPositionVector());
        this.b(entityPlayer.rotationYaw);
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
        entityPlayer.setPositionAndUpdate(this.posX, this.posY, this.posZ);
        return true;
    }

    @Nullable
    public Entity[] getParts() {
        return new Entity[]{this.V, this.b2};
    }

    public void setTargetEntity(@Nullable EntityLivingBase entityLivingBase) {
        try {
            if (entityLivingBase == null) {
                this.DataManager.set(TargetEntityIdKey, (Object)-1);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.DataManager.set(TargetEntityIdKey, (Object)entityLivingBase.getEntityId());
    }

    public int getCombatTargetId() {
        return (Integer)this.DataManager.get(CombatTargetIdKey);
    }

    public void b(int i) {
        this.DataManager.set(CombatTargetIdKey, (Object)i);
    }

    public EntityLivingBase getTargetEntity() {
        int i = (Integer)this.DataManager.get(TargetEntityIdKey);
        try {
            if (-1 == i) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return (EntityLivingBase)this.world.getEntityByID(i);
    }

    public static Float rotateToTarget(GalathNpc f_2, float f) {
        float f2;
        block9: {
            GirlAnimationState girlAnimationState = f_2.getCurrentAction();
            try {
                try {
                    try {
                        if (girlAnimationState == GirlAnimationState.FLY || girlAnimationState == GirlAnimationState.SUMMON_SKELETON) break block9;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    if (girlAnimationState == GirlAnimationState.RAPE_PREPARE) break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return null;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        EntityLivingBase entityLivingBase = f_2.getTargetEntity();
        try {
            if (entityLivingBase == null) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = LerpMath.lerpVec3d(new Vec3d(entityLivingBase.lastTickPosX, entityLivingBase.lastTickPosY, entityLivingBase.lastTickPosZ), entityLivingBase.getPositionVector(), (double)f);
        Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(f_2.lastTickPosX, f_2.lastTickPosY, f_2.lastTickPosZ), f_2.getPositionVector(), (double)f);
        Vec3d vec3d3 = vec3d.subtract(vec3d2);
        f_2.renderYawOffset = f2 = (float)AngleMath.radToDegrees(Math.atan2(vec3d3.z, vec3d3.x)) - 90.0f;
        f_2.prevRenderYawOffset = f2;
        return Float.valueOf(f2);
    }

    void c(float f) {
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getHealth() - f <= 0.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        long l = System.currentTimeMillis();
        try {
            if (l < this.bc + 1000L) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.a(ModSounds.GIRLS_GALATH_UUH, new int[0]);
        this.bc = l;
    }

    public boolean attackEntityFrom(DamageSource damageSource, float f) {
        try {
            if (damageSource.isFireDamage()) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (DamageSource.DROWN.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (DamageSource.CACTUS.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (DamageSource.FALL.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (DamageSource.FLY_INTO_WALL.equals(damageSource)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.c(f);
        return super.attackEntityFrom(damageSource, f);
    }

    public boolean attackEntityFromPart(MultiPartEntityPart multiPartEntityPart, DamageSource damageSource, float f) {
        try {
            if (this.world.isRemote) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (!(damageSource.getTrueSource() instanceof EntityPlayer)) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (multiPartEntityPart == this.V) {
                this.DataManager.set(LeftBallActiveKey, (Object)false);
                NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticles(this.V.getPositionVector(), false), (Entity)this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (multiPartEntityPart == this.b2) {
                this.DataManager.set(RightBallActiveKey, (Object)false);
                NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticles(this.b2.getPositionVector(), false), (Entity)this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return true;
    }

    @Override
    public void g() {
        this.setTargetEntity((EntityLivingBase)null);
        this.aH();
    }

    public World getWorld() {
        return this.world;
    }

    public void setFire(int i) {
    }

    public void fall(float f, float f2) {
    }

    @Override
    @Nullable
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block10: {
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CORRUPT_FAST && girlAnimationState != GirlAnimationState.CORRUPT_SLOW) break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return GirlAnimationState.CORRUPT_CUM;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            if (girlAnimationState == GirlAnimationState.RAPE_ON_GOING) {
                return GirlAnimationState.RAPE_CUM;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (GirlAnimationState.isAnimationInList(girlAnimationState, GirlAnimationState.MORNING_BLOWJOB_SLOW, GirlAnimationState.MORNING_BLOWJOB_FAST)) {
                this.S = true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return null;
    }

    @Override
    public boolean c() {
        return this.bb;
    }

    @Override
    public boolean a() {
        try {
            switch (this.getCurrentAction()) {
                case CORRUPT_SLOW: 
                case CORRUPT_FAST: 
                case CORRUPT_CUM: 
                case COWGIRLCUM: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    public void c(boolean flag) {
        block16: {
            GirlAnimationState girlAnimationState = this.getCurrentAction();
            try {
                try {
                    if (girlAnimationState == GirlAnimationState.RAPE_ON_GOING || girlAnimationState == GirlAnimationState.RAPE_INTRO) break block16;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (0.0f >= entityPlayer.getHealth() - 1.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            if (entityPlayer.capabilities.isCreativeMode) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        try {
            entityPlayer.attackEntityFrom((DamageSource)new GalathMeleeDamageSource(this), 1.0f);
            if (flag) {
                this.heal(1.5f);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        try {
            super.writeEntityToNBT(nBTTagCompound);
            nBTTagCompound.setString("sexmod:master", (String)this.DataManager.get(MasterUuidKey));
            if (this.bA) {
                nBTTagCompound.setBoolean("sexmod:despawned", true);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        try {
            super.readEntityFromNBT(nBTTagCompound);
            this.DataManager.set(MasterUuidKey, (Object)nBTTagCompound.getString("sexmod:master"));
            if (nBTTagCompound.getBoolean("sexmod:despawned")) {
                this.P = true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        UUID uUID = this.O();
        if (uUID != null) {
            String string = GirlHomeWorldData.getCustomName(uUID, GirlRegistry.GALATH);
            try {
                if (string != null) {
                    this.setCustomName(string);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
    }

    public void ak() {
        try {
            if (this.getCurrentAction() == GirlAnimationState.MASTERBATE_SITTING) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        this.bx = true;
        this.setCurrentAction(GirlAnimationState.MASTERBATE_SITTING);
    }

    public void a() {
        this.a5 = true;
        this.setCurrentAction(GirlAnimationState.PUSSY_LICKING);
    }

    @Override
    protected boolean shouldHoldAnimation(GirlAnimationState girlAnimationState, String string, boolean flag, AnimationEvent animationEvent) {
        block40: {
            block39: {
                block37: {
                    block36: {
                        block35: {
                            block34: {
                                block33: {
                                    try {
                                        try {
                                            if (girlAnimationState != GirlAnimationState.MASTERBATE_SITTING || !this.bx) break block33;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GalathNpc.rethrow(concurrentModificationException);
                                        }
                                        this.bx = false;
                                        this.createAnimation("animation.galath.masterbating_sitting", true, animationEvent, true);
                                        return true;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                }
                                try {
                                    try {
                                        if (girlAnimationState != GirlAnimationState.MORNING_BLOWJOB_FAST || !this.S) break block34;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GalathNpc.rethrow(concurrentModificationException);
                                    }
                                    this.setCurrentAction(GirlAnimationState.MORNING_BLOWJOB_CUM);
                                    return true;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    if (girlAnimationState != GirlAnimationState.MORNING_BLOWJOB_FAST || !this.aD) break block35;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                this.createAnimation("animation.shared.bed_fast", true, animationEvent, true);
                                this.aD = false;
                                return true;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                        }
                        try {
                            if (girlAnimationState == GirlAnimationState.MORNING_BLOWJOB_CUM) {
                                this.setCurrentAction((GirlAnimationState)null);
                                return true;
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                        try {
                            try {
                                if (girlAnimationState != GirlAnimationState.PUSSY_LICKING || !this.a5) break block36;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                            this.a5 = false;
                            this.createAnimation("animation.galath.pussy_licking", true, animationEvent, true);
                            return true;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GalathNpc.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        block38: {
                            try {
                                try {
                                    if (girlAnimationState != GirlAnimationState.MORNING_BLOWJOB_SLOW) break block37;
                                    if (this.S) break block38;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GalathNpc.rethrow(concurrentModificationException);
                                }
                                if (!AnimationInputLock.SneakPressed) break block37;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GalathNpc.rethrow(concurrentModificationException);
                            }
                        }
                        this.aD = true;
                        this.setCurrentAction(GirlAnimationState.MORNING_BLOWJOB_FAST);
                        this.createAnimation("animation.shared.bed_soft", true, animationEvent, true);
                        return true;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.MORNING_BLOWJOB_SLOW || !this.bt) break block39;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    this.bt = false;
                    this.createAnimation("animation.shared.bed_slow", true, animationEvent, true);
                    return true;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.MORNING_BLOWJOB_FAST || AnimationInputLock.SneakPressed) break block40;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                this.setCurrentAction(GirlAnimationState.MORNING_BLOWJOB_SLOW);
                this.bt = true;
                this.createAnimation("animation.shared.bed_back", true, animationEvent, true);
                return true;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        return false;
    }

    public float b(float f) {
        GirlAnimationState girlAnimationState;
        block12: {
            block11: {
                girlAnimationState = this.getCurrentAction();
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.PUSSY_LICKING || this.a5) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.rethrow(concurrentModificationException);
                    }
                    return 0.0f;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.MASTERBATE_SITTING || this.bx) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.rethrow(concurrentModificationException);
                }
                return 1.0f;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.rethrow(concurrentModificationException);
            }
        }
        float f2 = GirlAnimationState.getAnimationNormalized01(this, f);
        try {
            if (girlAnimationState == GirlAnimationState.MASTERBATE_SITTING) {
                return f2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GalathNpc.rethrow(concurrentModificationException);
        }
        return 1.0f - f2;
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.isTracked()) {
            this.createAnimationOnce("animation.galath.idle", true, animEvent);
            return PlayState.CONTINUE;
        }
        GirlAnimationState girlAnimationState = this.getCurrentAction();
        AnimationController animationController = animEvent.getController();
        animationController.setAnimationSpeed(1.0);
        if (animationController.equals(this.EyesController)) {
            if (!girlAnimationState.autoBlink || girlAnimationState == GirlAnimationState.GALATH_DE_SUMMON) {
                return PlayState.STOP;
            }
            this.createAnimationOnce("animation.galath.blink", true, animEvent);
            return PlayState.CONTINUE;
        }
        if (animationController.equals(this.MovementController)) {
            if (girlAnimationState != GirlAnimationState.NULL) {
                return PlayState.STOP;
            }
            if (!this.onGround) {
                this.createAnimationOnce("animation.galath.controlled_flight", true, animEvent);
                return PlayState.CONTINUE;
            }
            Vec3d vec3d = this.getPositionVector().subtract(new Vec3d(this.lastTickPosX, this.lastTickPosY, this.lastTickPosZ));
            if (vec3d.equals(Vec3d.ZERO)) {
                this.createAnimationOnce("animation.galath.idle", true, animEvent);
                return PlayState.CONTINUE;
            }
            this.rotationYaw = this.rotationYawHead;
            this.createAnimationOnce("animation.galath." + ((Boolean)this.DataManager.get(bT) != false ? "run" : "walk"), true, animEvent);
            return PlayState.CONTINUE;
        }
        switch (this.getCurrentAction()) {
            case NULL: {
                return PlayState.STOP;
            }
            case FLY: {
                this.createAnimationOnce("animation.galath.idle_flying", true, animEvent);
                break;
            }
            case SUMMON_SKELETON: {
                this.createAnimationOnce("animation.galath.summon_skeleton" + ((Boolean)this.DataManager.get(FlipSideKey) != false ? "Mirrored" : ""), true, animEvent);
                break;
            }
            case ATTACK_SWORD: {
                this.createAnimationOnce("animation.galath.attack", true, animEvent);
                break;
            }
            case KNOCK_OUT_FLY: {
                animationController.setAnimationSpeed(1.5);
                this.createAnimationOnce("animation.galath.knockout_air", true, animEvent);
                break;
            }
            case KNOCK_OUT_GROUND: {
                this.createAnimationOnce("animation.galath.knocked_out", true, animEvent);
                break;
            }
            case KNOCK_OUT_STAND_UP: {
                this.createAnimationOnce("animation.galath.knocked_out_stand_up", true, animEvent);
                break;
            }
            case RAPE_PREPARE: {
                this.createAnimationOnce("animation.galath.rape_prepare", true, animEvent);
                break;
            }
            case RAPE_CHARGE: {
                this.createAnimationOnce("animation.galath.rape_charge", true, animEvent);
                break;
            }
            case RAPE_INTRO: {
                this.createAnimationOnce("animation.galath.rape_intro", true, animEvent);
                break;
            }
            case RAPE_ON_GOING: {
                this.createAnimationOnce("animation.galath.rape" + this.i7, true, animEvent);
                break;
            }
            case RAPE_CUM: {
                this.createAnimationOnce("animation.galath.rape_cum", true, animEvent);
                break;
            }
            case RAPE_CUM_IDLE: {
                this.createAnimationOnce("animation.galath.rape_cum_idle", true, animEvent);
                break;
            }
            case CORRUPT_FAST: {
                this.createAnimationOnce("animation.galath.corrupt_" + (this.aT ? "hard" : "soft"), true, animEvent);
                break;
            }
            case CORRUPT_SLOW: {
                this.createAnimationOnce("animation.galath.corrupt_slow", true, animEvent);
                break;
            }
            case CORRUPT_INTRO: {
                this.createAnimationOnce("animation.galath.corrupt_intro", true, animEvent);
                break;
            }
            case CORRUPT_CUM: {
                this.createAnimationOnce("animation.galath.corrupt_cum", true, animEvent);
                break;
            }
            case CONTROLLED_FLIGHT: {
                this.createAnimationOnce("animation.galath.controlled_flight", true, animEvent);
                break;
            }
            case BOOST: {
                this.createAnimationOnce("animation.galath.boost", true, animEvent);
                break;
            }
            case GALATH_SUMMON: {
                this.createAnimationOnce("animation.galath.summon", false, animEvent);
                break;
            }
            case GALATH_DE_SUMMON: {
                this.createAnimationOnce("animation.galath.desummon" + (this.onGround ? "_standing" : ""), true, animEvent);
                break;
            }
            case GIVE_COIN: {
                this.createAnimationOnce("animation.galath.give_coin", true, animEvent);
                break;
            }
            case MASTERBATE: {
                this.createAnimationOnce("animation.galath.masterbate", true, animEvent);
                break;
            }
            case RUN: {
                animationController.setAnimationSpeed(0.7);
                this.createAnimationOnce("animation.galath.running", true, animEvent);
                break;
            }
            case HUG_MANG: {
                this.createAnimationOnce("animation.galath.hug_mang", true, animEvent);
                break;
            }
            case PUSSY_LICKING: {
                this.a(this.flag ? "animation.galath.pussy_licking_forward" : "animation.galath.pussy_licking", true, animEvent);
                break;
            }
            case MASTERBATE_SITTING: {
                this.a(this.bx ? "animation.galath.pussy_licking_back" : "animation.galath.masterbating_sitting", true, animEvent);
                break;
            }
            case MASTERBATE_SITTING_CUM: {
                this.createAnimationOnce("animation.galath.masterbating_sitting_cum", true, animEvent);
                break;
            }
            case MORNING_BLOWJOB_SLOW: {
                this.a(this.bt ? "animation.shared.bed_back" : "animation.shared.bed_slow", true, animEvent);
                break;
            }
            case MORNING_BLOWJOB_FAST: {
                if (this.aD) {
                    this.createAnimationOnce("animation.shared.bed_soft", true, animEvent);
                    break;
                }
                this.createRangeAnimationOnce("animation.shared.bed_fast", 4, 0.75f, animEvent);
                break;
            }
            case MORNING_BLOWJOB_CUM: {
                this.createAnimationOnce("animation.shared.bed_cum", true, animEvent);
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        this.ActionController = new CustomAnimationController<GalathNpc>(this, "action", 0.0f, this::predicate);
        this.MovementController = new AnimationController<GalathNpc>(this, "movement", 5.0f, this::predicate);
        this.EyesController = new AnimationController<GalathNpc>(this, "eyes", 10.0f, this::predicate);
        this.ActionController.registerSoundListener(arg1 -> {
            switch (arg1.sound) {
                case "goodTiming": {
                    this.a(ModSounds.GIRLS_GALATH_DIALOG[4]);
                    this.a("Good timing boy~");
                    break;
                }
                case "huh": {
                    this.a(ModSounds.GIRLS_GALATH_HUH, new int[0]);
                    break;
                }
                case "giggle": {
                    Vec3d vec3d = this.A();
                    this.world.playSound(vec3d.x, vec3d.y, vec3d.z, ModSounds.pickRandomSound(ModSounds.GIRLS_GALATH_GIGGLE), SoundCategory.HOSTILE, 1.0f, 1.0f, false);
                    break;
                }
                case "dialog1": {
                    this.a(ModSounds.GIRLS_GALATH_DIALOG[1]);
                    break;
                }
                case "moan": {
                    this.a(ModSounds.GIRLS_GALATH_MOAN, new int[0]);
                    break;
                }
                case "breath": {
                    this.a(ModSounds.GIRLS_GALATH_BREATHING, new int[0]);
                    break;
                }
                case "dialog5": {
                    this.a(ModSounds.GIRLS_GALATH_DIALOG[5]);
                    break;
                }
                case "switchmoan": {
                    if (this.flag2) {
                        this.a(ModSounds.GIRLS_GALATH_BREATHING, new int[0]);
                    } else {
                        this.a(this.getRNG().nextBoolean() ? ModSounds.GIRLS_GALATH_MOAN : ModSounds.GIRLS_GALATH_AHH, new int[0]);
                    }
                    this.flag2 = !this.flag2;
                    break;
                }
                case "lightcharge": {
                    Vec3d vec3d = this.A();
                    this.world.playSound(vec3d.x, vec3d.y, vec3d.z, ModSounds.pickRandomSound(ModSounds.GIRLS_GALATH_LIGHTCHARGE), SoundCategory.HOSTILE, 1.0f, 1.0f, false);
                    break;
                }
                case "strongcharge": {
                    this.a(ModSounds.GIRLS_GALATH_STRONGCHARGE, new int[0]);
                    break;
                }
                case "hmph": {
                    this.a(ModSounds.GIRLS_GALATH_HMPH, new int[0]);
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_SMALLINSERTS, 2.0f);
                    break;
                }
                case "giggle0": {
                    this.a(ModSounds.GIRLS_GALATH_GIGGLE[0]);
                    break;
                }
                case "orgasm": {
                    this.a(ModSounds.GIRLS_GALATH_ORGASM, new int[0]);
                    break;
                }
                case "pound": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    break;
                }
                case "flap": {
                    Vec3d vec3d = this.A();
                    this.world.playSound(vec3d.x, vec3d.y, vec3d.z, ModSounds.pickRandomSound(ModSounds.MISC_FLAP), SoundCategory.HOSTILE, 1.0f, 1.0f, false);
                    break;
                }
                case "startRenderSword": {
                    this.ap = true;
                    this.bu = true;
                    break;
                }
                case "stopFadeInParticles": {
                    this.bu = false;
                    break;
                }
                case "stopRenderSword": {
                    this.ap = false;
                    this.bu = false;
                    break;
                }
                case "dontDrawStars": {
                    this.aL = false;
                    break;
                }
                case "setNude": {
                    this.bb = true;
                    Vec3d vec3d = this.getPositionVector();
                    Vec3d vec3d2 = this.getModelBone("slipR").add(vec3d);
                    Vec3d vec3d3 = this.getModelBone("slipL").add(vec3d);
                    Vec3d vec3d4 = this.getModelBone("turnable").add(vec3d);
                    this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, vec3d2.x, vec3d2.y, vec3d2.z, 0.0, 0.0, 0.0, new int[0]);
                    this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, vec3d3.x, vec3d3.y, vec3d3.z, 0.0, 0.0, 0.0, new int[0]);
                    this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, vec3d4.x, vec3d4.y, vec3d4.z, 0.0, 0.0, 0.0, new int[0]);
                    break;
                }
                case "rapeIntroDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.setCurrentAction(GirlAnimationState.RAPE_ON_GOING);
                    break;
                }
                case "rape_switch": {
                    EntityPlayerSP entityPlayerSP;
                    Random random = this.getRNG();
                    int i = this.i7;
                    do {
                        this.i7 = random.nextInt(3);
                    } while (this.i7 == i);
                    if (this.J() || !this.isOwnedByLocalPlayer() || !(0.0f >= (entityPlayerSP = Minecraft.getMinecraft().player).getHealth() - 1.0f)) break;
                    this.setCurrentAction(GirlAnimationState.RAPE_CUM);
                    break;
                }
                case "poundRape": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    if (this.J()) {
                        GuiHud.addProgress(0.03f);
                        break;
                    }
                    NetworkHandler.channel.sendToServer((IMessage)new PacketGalathRapePounce(true));
                    break;
                }
                case "rapeHurt": {
                    if (this.J() || !this.isOwnedByLocalPlayer()) break;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketGalathRapePounce(false));
                    break;
                }
                case "enableRapeUI": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    if (this.J()) {
                        GuiHud.showHudWithForce(false);
                        break;
                    }
                    GuiEscapeMinigame.a();
                    break;
                }
                case "removeUI": {
                    if (!this.isOwnedByLocalPlayer() || this.J()) break;
                    GuiEscapeMinigame.d();
                    break;
                }
                case "reloadRenderer": {
                    if (!this.isOwnedByLocalPlayer()) {
                        return;
                    }
                    Minecraft minecraft = Minecraft.getMinecraft();
                    if (minecraft.gameSettings.thirdPersonView == 0) break;
                    minecraft.renderGlobal.loadRenderers();
                    break;
                }
                case "corruptSwitch": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.CORRUPT_FAST);
                    break;
                }
                case "corrupt_hard": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.aT = true;
                    this.N();
                    break;
                }
                case "corrupt_hard_end": {
                    this.setCurrentAction(GirlAnimationState.CORRUPT_SLOW);
                    this.aT = false;
                    break;
                }
                case "addCum": {
                    GuiHud.addProgress(0.03);
                    break;
                }
                case "clearcum": {
                    GuiCumOverlay.removeParticlesForGirl(this);
                    break;
                }
                case "setCamCorrupt": {
                    if (!this.isOwnedByLocalPlayer()) {
                        return;
                    }
                    this.U = true;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    float f = this.I().floatValue() + 220.0f;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.5, 0.5f - entityPlayerSP.getEyeHeight(), 0.4f), this.I().floatValue()).add(this.getTargetPos());
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(entityPlayerSP.getPersistentID().toString(), vec3d, f, 15.0f));
                    GuiHud.showHud();
                    break;
                }
                case "enableBoyCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.U = false;
                    break;
                }
                case "masterbateCumming": {
                    if (!CommandFuta.Enabled) break;
                    GuiCumOverlay.addParticles(new ParticleEmitter(90, girl -> {
                        Vec3d vec3d = girl.d("futaCockTip");
                        Vec3d vec3d2 = girl.d("futaCockTipDirHelp");
                        return vec3d.subtract(vec3d2).normalize();
                    }, girl -> girl.getModelBone("futaCockTip").add(girl.getTargetPos()), (GirlEntity)this, 0.3f, 0.3f));
                    break;
                }
                case "creampie": {
                    GuiCumOverlay.addParticles(new ParticleEmitter(100, girl -> VectorMath.rotateYaw(new Vec3d(0.0, 0.0, (double)0.6f), this.I().floatValue()), girl -> girl.getModelBone("creampiePos").add(girl.getTargetPos()), (GirlEntity)this, 0.6f, 0.5f));
                }
                case "creampieGalath": {
                    if (CommandFuta.Enabled) {
                        GuiCumOverlay.addParticles(new ParticleEmitter(130, girl -> {
                            Vec3d vec3d = girl.d("futaCockTip");
                            Vec3d vec3d2 = girl.d("futaCockTipDirHelp");
                            return vec3d.subtract(vec3d2).normalize();
                        }, girl -> girl.getModelBone("futaCockTip").add(girl.getTargetPos()), (GirlEntity)this, 0.3f, 0.3f));
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_SMALLINSERTS), 3.0f);
                    break;
                }
                case "blackScreenTamed": {
                    if (!this.J()) break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "blackScreenMaster": {
                    if (!Minecraft.getMinecraft().player.getPersistentID().equals(this.O())) break;
                    GuiTransitionScreen.startTransition();
                    AnimationInputLock.setAnimationLocked(false);
                    break;
                }
                case "flapControlled": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiGalathFlight.startFlight();
                    this.a(ModSounds.MISC_FLAP, new int[0]);
                    Minecraft minecraft = Minecraft.getMinecraft();
                    EntityPlayerSP entityPlayerSP = minecraft.player;
                    MovementInput movementInput = entityPlayerSP.movementInput;
                    Vec2f vec2f = movementInput.getMoveVector();
                    if (vec2f.x == 0.0f && vec2f.y == 0.0f) break;
                    Vec3d vec3d = VectorMath.rotatePitchYaw(new Vec3d(-vec2f.x, 0.0, vec2f.y), LerpMath.lerp(entityPlayerSP.prevRotationPitch, entityPlayerSP.rotationPitch, minecraft.getRenderPartialTicks()), LerpMath.lerp(entityPlayerSP.prevRotationYawHead, entityPlayerSP.rotationYawHead, minecraft.getRenderPartialTicks()));
                    NetworkHandler.channel.sendToServer((IMessage)new PacketUpdateVelocity(vec3d, this.getGirlUuid()));
                    break;
                }
                case "clap": {
                    this.a(ModSounds.MISC_CLAP, new int[0]);
                    break;
                }
                case "energysound": {
                    this.a(ModSounds.MISC_BEEW[1]);
                    break;
                }
                case "energy2": {
                    this.a(ModSounds.MISC_BEEW[2]);
                    break;
                }
                case "tpSound": {
                    this.a(ModSounds.MISC_WEOWEO[2]);
                    break;
                }
                case "lick": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_ALLIE_LIPSOUND));
                    break;
                }
                case "setCoinLook": {
                    float f;
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    entityPlayerSP.rotationYaw = f = this.I().floatValue() + 180.0f;
                    entityPlayerSP.prevRotationYaw = f;
                    entityPlayerSP.rotationPitch = 0.0f;
                    entityPlayerSP.prevRotationPitch = 0.0f;
                    break;
                }
                case "sexui": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "boostSound": {
                    Minecraft.getMinecraft().player.playSound(ModSounds.pickRandomSound(ModSounds.GIRLS_GALATH_LIGHTCHARGE), 1.0f, 1.0f);
                    Minecraft.getMinecraft().player.playSound(ModSounds.pickRandomSound(ModSounds.MISC_FLAP), 1.0f, 1.0f);
                }
            }
        });
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.EyesController);
        animationData.addAnimationController(this.MovementController);
    }

    private static /* synthetic */ Vec3d lambda$null$10(GirlEntity girl) {
        return girl.getModelBone("futaCockTip").add(girl.getTargetPos());
    }

    private static /* synthetic */ Vec3d lambda$null$9(GirlEntity girl) {
        Vec3d vec3d = girl.d("futaCockTip");
        Vec3d vec3d2 = girl.d("futaCockTipDirHelp");
        return vec3d.subtract(vec3d2).normalize();
    }

    private static /* synthetic */ Vec3d lambda$null$8(GirlEntity girl) {
        return girl.getModelBone("creampiePos").add(girl.getTargetPos());
    }

    private /* synthetic */ Vec3d lambda$null$7(GirlEntity girl) {
        return VectorMath.rotateYaw(new Vec3d(0.0, 0.0, (double)0.6f), this.I().floatValue());
    }

    private static /* synthetic */ Vec3d lambda$null$6(GirlEntity girl) {
        return girl.getModelBone("futaCockTip").add(girl.getTargetPos());
    }

    private static /* synthetic */ Vec3d lambda$null$5(GirlEntity girl) {
        Vec3d vec3d = girl.d("futaCockTip");
        Vec3d vec3d2 = girl.d("futaCockTipDirHelp");
        return vec3d.subtract(vec3d2).normalize();
    }

    private static Exception rethrow(Exception exception) {
        return exception;
    }

    public static class EventHandler {
        boolean a(GalathNpc f_2) {
            boolean flag;
            try {
                flag = f_2.getDisplayName() != null;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            return flag;
        }

        @SubscribeEvent(priority=EventPriority.LOWEST)
        public void onCheckSpawn(LivingSpawnEvent.CheckSpawn checkSpawn) {
            Entity entity;
            block13: {
                Event.Result result = checkSpawn.getResult();
                try {
                    if (result == Event.Result.DENY) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (checkSpawn.isSpawner()) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                entity = checkSpawn.getEntity();
                try {
                    try {
                        if (entity instanceof EntityWitherSkeleton || entity instanceof EntityBlaze) break block13;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
            }
            BlockPos blockPos = entity.getPosition();
            World world = entity.world;
            try {
                if (!GalathNpc.isSpawnPositionFree(blockPos, world)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            checkSpawn.setResult(Event.Result.DENY);
            TribeVillageData.addSpawnPosition(blockPos, TribeVillageData.GalathSpawnPositions);
            GalathNpc f_2 = new GalathNpc(world);
            f_2.setPositionAndUpdate(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            world.spawnEntity((Entity)f_2);
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void onKeyInput(InputEvent.KeyInputEvent keyInputEvent) {
            Minecraft minecraft = Minecraft.getMinecraft();
            try {
                if (!minecraft.gameSettings.keyBindJump.isKeyDown()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            try {
                if (!GuiGalathFlight.canStartFlight()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            try {
                for (GirlEntity girl : GirlEntity.getAllGirls()) {
                    try {
                        if (!girl.world.isRemote) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (!(girl instanceof GalathNpc)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (!minecraft.player.getPersistentID().equals(((GalathNpc)girl).ax())) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    GuiGalathFlight.consumeFlightAttempt();
                    girl.setCurrentAction(GirlAnimationState.BOOST);
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }

        @SubscribeEvent
        public void onMount(EntityMountEvent entityMountEvent) {
            try {
                if (entityMountEvent.isMounting()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            Entity entity = entityMountEvent.getEntityBeingMounted();
            try {
                if (!(entity instanceof GalathNpc)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            try {
                if (entity.world.isRemote) {
                    GuiGalathFlight.recordTouchTime();
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            ((GalathNpc)entity).t();
        }

        @SubscribeEvent(priority=EventPriority.HIGH)
        public void onLivingDeath(LivingDeathEvent livingDeathEvent) {
            GalathNpc f_2;
            block15: {
                block14: {
                    Entity entity = livingDeathEvent.getEntity();
                    try {
                        if (!(entity instanceof GalathNpc)) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (livingDeathEvent.getSource().equals(DamageSource.OUT_OF_WORLD)) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    f_2 = (GalathNpc)entity;
                    try {
                        if (f_2.bU) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (entity.world.isRemote) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (f_2.k()) break block14;
                        f_2.a((Entity)f_2.getCombatTracker().getFighter());
                        break block15;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                }
                ItemGalathCoin.desummonGalath(f_2);
                NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticle(f_2.getGirlUuid(), GalathOwnershipData.getGalathOwnerUuidByEntity(f_2)), (Entity)f_2);
                MathUtils.runAfterDelay(900, () -> GalathOwnershipData.releaseOwnedGalath(f_2));
                f_2.bU = true;
            }
            f_2.setHealth(1.0f);
            livingDeathEvent.setCanceled(true);
        }

        @SubscribeEvent
        public void onRespawn(PlayerEvent.PlayerRespawnEvent playerRespawnEvent) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)playerRespawnEvent.player;
            GirlEntity girl = GirlEntity.getByUuidForSide(entityPlayerMP.getPersistentID(), true);
            try {
                if (!(girl instanceof GalathNpc)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            GalathNpc f_2 = (GalathNpc)girl;
            try {
                f_2.setTargetEntity((EntityLivingBase)null);
                PacketResetGirl.Handler.openGui(girl);
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), entityPlayerMP);
                girl.setCurrentAction((GirlAnimationState)null);
                if (f_2.CurrentDeathEvent == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            f_2.CurrentDeathEvent.tick(f_2);
            f_2.CurrentDeathEvent = null;
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void onRenderWorldLast(RenderWorldLastEvent renderWorldLastEvent) {
            Minecraft minecraft = Minecraft.getMinecraft();
            RenderManager renderManager = minecraft.getRenderManager();
            float f = minecraft.getRenderPartialTicks();
            try {
                for (GirlEntity girl : GirlEntity.getAllGirls()) {
                    EnergyBallEntity energyBall;
                    Vec3d vec3d;
                    Vec3d vec3d2;
                    try {
                        if (!(girl instanceof GalathNpc)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (!girl.world.isRemote) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (girl.getCurrentAction() != GirlAnimationState.SUMMON_SKELETON) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    double d = ((GalathNpc)girl).ad;
                    try {
                        if (d < 9.0) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (d > 30.0) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    Vec3d vec3d3 = LerpMath.lerpVec3d(new Vec3d(girl.lastTickPosX, girl.lastTickPosY, girl.lastTickPosZ), girl.getPositionVector(), (double)f);
                    double d2 = (d - 9.0) / 21.0;
                    if (((Boolean)girl.getDataManager().get(RightBallActiveKey)).booleanValue()) {
                        vec3d2 = girl.getModelBone("energyBallR");
                        vec3d = vec3d3.add(vec3d2);
                        energyBall = new EnergyBallEntity(girl.world, (GalathNpc)girl);
                        energyBall.g = d2;
                        energyBall.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
                        renderManager.renderEntity((Entity)energyBall, 0.0, 0.0, 0.0, 0.0f, f, true);
                        energyBall.setPosition(0.0, -500.0, 0.0);
                        energyBall.setDead();
                    }
                    if (!((Boolean)girl.getDataManager().get(b7)).booleanValue()) continue;
                    vec3d2 = girl.getModelBone("energyBallL");
                    vec3d = vec3d3.add(vec3d2);
                    energyBall = new EnergyBallEntity(girl.world, (GalathNpc)girl);
                    energyBall.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
                    energyBall.g = d2;
                    renderManager.renderEntity((Entity)energyBall, 0.0, 0.0, 0.0, 0.0f, f, true);
                    energyBall.setPosition(0.0, -500.0, 0.0);
                    energyBall.setDead();
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            GlStateManager.enableAlpha();
        }

        boolean a(World world, BlockPos blockPos, EnumFacing enumFacing) {
            if (enumFacing == EnumFacing.NORTH) {
                blockPos = blockPos.west();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.south())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.south().up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                return true;
            }
            if (enumFacing == EnumFacing.WEST) {
                blockPos = blockPos.south();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.east())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.east().up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                return true;
            }
            if (enumFacing == EnumFacing.SOUTH) {
                blockPos = blockPos.east();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.north())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.north().up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                return true;
            }
            if (enumFacing == EnumFacing.EAST) {
                blockPos = blockPos.north();
                try {
                    if (this.a(world, blockPos)) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.west())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (this.a(world, blockPos.west().up())) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
                return true;
            }
            Main.LOGGER.error("Weird bed orientation, when checking for space next to bed, on galaths morning blowjob animation: " + enumFacing.getName());
            return false;
        }

        boolean a(World world, BlockPos blockPos) {
            Block block = world.getBlockState(blockPos).getBlock();
            for (Class<?> clazz : aS) {
                try {
                    if (!clazz.isInstance(block)) continue;
                    return false;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
                }
            }
            return true;
        }

        @SubscribeEvent
        public void onWakeUp(PlayerWakeUpEvent playerWakeUpEvent) {
            float f;
            EntityPlayer entityPlayer = playerWakeUpEvent.getEntityPlayer();
            try {
                if (entityPlayer.world.isRemote) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            try {
                if (!GalathOwnershipData.isGalathRecent(entityPlayer.getPersistentID(), entityPlayer.world)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            Vec3d vec3d = entityPlayer.getPositionVector();
            BlockPos blockPos = new BlockPos(vec3d);
            EnumFacing enumFacing = (EnumFacing)entityPlayer.world.getBlockState(blockPos).getValue((IProperty)BlockHorizontal.FACING);
            try {
                if (!this.a(entityPlayer.world, blockPos, enumFacing)) {
                    entityPlayer.sendMessage((ITextComponent)new TextComponentString(String.format("%sFor Galath and Manglelie to %swake you up with a blowjob%s, you have to provide enough space to the %sright side%s of your bed. This includes the %stop and bottom half%s of the bed.", TextFormatting.GRAY, TextFormatting.DARK_RED, TextFormatting.GRAY, TextFormatting.DARK_RED, TextFormatting.GRAY, TextFormatting.DARK_RED, TextFormatting.GRAY)));
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            switch ((EnumFacing)entityPlayer.world.getBlockState(blockPos).getValue((IProperty)BlockHorizontal.FACING)) {
                default: {
                    f = 0.0f;
                    break;
                }
                case NORTH: {
                    f = 180.0f;
                    break;
                }
                case EAST: {
                    f = -90.0f;
                    break;
                }
                case WEST: {
                    f = 90.0f;
                }
            }
            Vec3d vec3d2 = new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5);
            UUID uUID = GalathOwnershipData.getGalathByPlayer(entityPlayer);
            try {
                if (uUID != null) {
                    GalathOwnershipData.releaseOwnedGalath((GalathNpc)GirlEntity.getServerSideByUuid(uUID));
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GalathNpc.EventHandler.rethrow(concurrentModificationException);
            }
            GalathNpc f_2 = new GalathNpc(entityPlayer.world, entityPlayer, vec3d, true);
            f_2.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
            entityPlayer.world.spawnEntity((Entity)f_2);
            GalathOwnershipData.setOwnershipByPlayer(entityPlayer, f_2);
            f_2.getScale();
            f_2.setTargetPos(vec3d2);
            f_2.b(f);
            f_2.getChildMangle(true);
            f_2.e(entityPlayer.getPersistentID());
            f_2.setCurrentAction(GirlAnimationState.MORNING_BLOWJOB_SLOW);
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
            MathUtils.runAfterDelay(500, () -> {
                entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerCam(-10.0f, f + 180.0f + 5.0f, 0), (EntityPlayerMP)entityPlayer);
            });
        }

        private static ConcurrentModificationException rethrow(ConcurrentModificationException concurrentModificationException) {
            return concurrentModificationException;
        }
    }
}

