/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying
 *  net.minecraft.entity.ai.EntityFlyHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketParticles
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathNavigateFlying
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
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
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
public class BeeNpc
extends ChestGirlEntity {
    public float WildTimer = 3200.0f;
    int ParticlePhaseTicks = 0;
    static final float WildDespawnThreshold = 4800.0f;
    static final float Q = 10.0f;
    public static final DataParameter<Boolean> IsTamedKey = EntityDataManager.createKey(BeeNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(112);

    public BeeNpc(World world) {
        super(world);
        this.moveHelper = new EntityFlyHelper((EntityLiving)this);
        this.setSize(0.3f, 1.5f);
    }

    @Override
    public String getDisplayName() {
        return "Bee";
    }

    @Override
    public float getRenderLabelOffset() {
        return -0.1f;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.DataManager.register(IsTamedKey, (Object)false);
    }

    protected PathNavigate createNavigator(World world) {
        PathNavigateFlying pathNavigateFlying = new PathNavigateFlying((EntityLiving)this, world);
        pathNavigateFlying.setCanOpenDoors(false);
        pathNavigateFlying.setCanFloat(true);
        pathNavigateFlying.setCanEnterDoors(true);
        this.Navigation = pathNavigateFlying;
        return pathNavigateFlying;
    }

    @Override
    protected void applyEntityAttributes() {
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.MAX_HEALTH);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
        this.getAttributeMap().registerAttribute(SWIM_SPEED);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue((double)0.4f);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.2f);
    }

    @Override
    protected void initEntityAI() {
        this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.tasks.addTask(0, (EntityAIBase)new GirlFollowAi(this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.25));
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)this.WatchPlayerAI);
        this.tasks.addTask(3, (EntityAIBase)new EntityAIWanderAvoidWaterFlying((EntityCreature)this, 1.0));
    }

    @Override
    public void updateAITasks() {
        block9: {
            try {
                try {
                    try {
                        super.updateAITasks();
                        if (!this.isPotionActive(PotionHandler.b) || !(this.WildTimer < 4800.0f)) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                    if (this.getSexPlayerUuid() != null) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw BeeNpc.rethrow(runtimeException);
                }
                this.removePotionEffect(PotionHandler.b);
                this.WildTimer = 6.9420184E7f;
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
        }
        try {
            this.getDisplayName();
            if (this.getCurrentAction().equals((Object)GirlAnimationState.CITIZEN_CUM)) {
                this.ParticlePhaseTicks = Math.max(1, this.ParticlePhaseTicks);
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        this.a_();
        this.b_15();
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block6: {
            try {
                block7: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.CITIZEN_CUM) break block6;
                            if (girlAnimationState == GirlAnimationState.CITIZEN_FAST) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BeeNpc.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.COWGIRLSLOW) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    void c() {
        try {
            if (this.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        try {
            if (this.J()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        try {
            this.WildTimer += 1.0f;
            if (this.WildTimer < 4800.0f) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 10.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        try {
            if (BeeNpc.getByPlayerUuid(entityPlayer) != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        try {
            if (PlayerGirlEntity.isPlayerGirl(entityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        if (entityPlayer.getDistance((Entity)this) < 1.5f) {
            this.WildTimer = 0.0f;
            this.handleGirlUuidEvent(entityPlayer.getPersistentID());
            this.DataManager.set(BusyKey, (Object)true);
            this.setTargetPos(this.getPlayerFrontPos());
            this.b(entityPlayer.rotationYaw - 180.0f);
            this.Navigation.clearPath();
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
            this.setCurrentAction(GirlAnimationState.CITIZEN_START);
            Vec3d vec3d = this.a(0.2);
            entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
        } else {
            this.Navigation.clearPath();
            this.Navigation.tryMoveToEntityLiving((Entity)entityPlayer, 1.0);
        }
    }

    void b_15() {
        block7: {
            RayTraceResult rayTraceResult = this.world.rayTraceBlocks(this.getPositionVector(), new Vec3d(this.posX, 0.0, this.posZ));
            try {
                if (rayTraceResult == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
            BlockPos blockPos = rayTraceResult.getBlockPos();
            double d = this.posY - (double)blockPos.getY();
            try {
                try {
                    if (!(d > 3.0) || !(this.motionY > 0.0)) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw BeeNpc.rethrow(runtimeException);
                }
                this.motionY = 0.0;
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
        }
    }

    void a_() {
        block28: {
            block25: {
                block26: {
                    try {
                        if (this.ParticlePhaseTicks == 0) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                    try {
                        ++this.ParticlePhaseTicks;
                        if (!((Boolean)this.DataManager.get(IsTamedKey)).booleanValue()) break block25;
                        if (this.ParticlePhaseTicks >= 40) break block26;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                    for (EntityPlayer entityPlayer : this.world.playerEntities) {
                        try {
                            if (!(entityPlayer.getDistance((Entity)this) < 15.0f)) continue;
                            ((EntityPlayerMP)entityPlayer).connection.sendPacket((Packet)new SPacketParticles(EnumParticleTypes.HEART, true, (float)this.posX, (float)this.posY + 0.3f, (float)this.posZ, 0.2f, 0.3f, 0.2f, 0.25f, 1, new int[0]));
                        }
                        catch (RuntimeException runtimeException) {
                            throw BeeNpc.rethrow(runtimeException);
                        }
                    }
                    break block28;
                }
                this.ParticlePhaseTicks = 0;
                break block28;
            }
            if (this.ParticlePhaseTicks < 200) {
                for (EntityPlayer entityPlayer : this.world.playerEntities) {
                    try {
                        if (!(entityPlayer.getDistance((Entity)this) < 15.0f)) continue;
                        ((EntityPlayerMP)entityPlayer).connection.sendPacket((Packet)new SPacketParticles(EnumParticleTypes.SPELL, true, (float)this.posX, (float)this.posY + 0.3f, (float)this.posZ, 0.2f, 0.3f, 0.2f, 0.25f, 1, new int[0]));
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                }
            } else {
                block27: {
                    try {
                        if (this.ParticlePhaseTicks != 200) break block27;
                        this.DataManager.set(IsTamedKey, (Object)this.getRNG().nextBoolean());
                        break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                }
                if (this.ParticlePhaseTicks < 250) {
                    for (EntityPlayer entityPlayer : this.world.playerEntities) {
                        EnumParticleTypes enumParticleTypes;
                        SPacketParticles sPacketParticles;
                        SPacketParticles sPacketParticles2;
                        NetHandlerPlayServer netHandlerPlayServer;
                        block30: {
                            block29: {
                                try {
                                    try {
                                        SPacketParticles sPacketParticles3;
                                        if (!(entityPlayer.getDistance((Entity)this) < 15.0f)) continue;
                                        netHandlerPlayServer = ((EntityPlayerMP)entityPlayer).connection;
                                        sPacketParticles2 = sPacketParticles3;
                                        sPacketParticles = sPacketParticles3;
                                        if (!((Boolean)this.DataManager.get(IsTamedKey)).booleanValue()) break block29;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BeeNpc.rethrow(runtimeException);
                                    }
                                    enumParticleTypes = EnumParticleTypes.HEART;
                                    break block30;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw BeeNpc.rethrow(runtimeException);
                                }
                            }
                            enumParticleTypes = EnumParticleTypes.VILLAGER_ANGRY;
                        }
                        sPacketParticles2(enumParticleTypes, true, (float)this.posX, (float)this.posY + 0.3f, (float)this.posZ, 0.2f, 0.3f, 0.2f, 0.25f, 3, new int[0]);
                        netHandlerPlayServer.sendPacket((Packet)sPacketParticles);
                    }
                } else {
                    this.ParticlePhaseTicks = 0;
                }
            }
        }
        for (EntityPlayer entityPlayer : this.world.playerEntities) {
            try {
                if (!(entityPlayer.getDistance((Entity)this) < 15.0f)) continue;
                ((EntityPlayerMP)entityPlayer).connection.sendPacket((Packet)new SPacketParticles(EnumParticleTypes.SPELL, true, (float)this.posX, (float)this.posY + 0.3f, (float)this.posZ, 0.2f, 0.3f, 0.2f, 0.25f, 10, new int[0]));
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
        }
    }

    @Override
    public void onUpdate() {
        block6: {
            try {
                try {
                    try {
                        super.onUpdate();
                        if (!(this.WildTimer < 4800.0f) || this.onGround) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                    if (!(this.motionY < 0.0)) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw BeeNpc.rethrow(runtimeException);
                }
                this.motionY *= 0.4;
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
        }
    }

    public void fall(float f, float f2) {
    }

    protected boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        block11: {
            block10: {
                try {
                    try {
                        try {
                            if (!((Boolean)this.DataManager.get(IsTamedKey)).booleanValue() || ((Boolean)this.DataManager.get(K)).booleanValue()) break block10;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BeeNpc.rethrow(runtimeException);
                        }
                        if (entityPlayer.getHeldItem(enumHand).getItem() != Item.getItemFromBlock((Block)Blocks.CHEST)) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeeNpc.rethrow(runtimeException);
                    }
                    this.DataManager.set(K, (Object)true);
                    entityPlayer.getHeldItem(enumHand).shrink(1);
                    return super.processInteract(entityPlayer, enumHand);
                }
                catch (RuntimeException runtimeException) {
                    throw BeeNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (!this.world.isRemote || !((Boolean)this.DataManager.get(IsTamedKey)).booleanValue()) break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw BeeNpc.rethrow(runtimeException);
                }
                this.void_b(entityPlayer);
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
        }
        return super.processInteract(entityPlayer, enumHand);
    }

    @SideOnly(value=Side.CLIENT)
    void void_b(EntityPlayer entityPlayer) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlDialogue(this, entityPlayer));
    }

    @Override
    public boolean canInteract(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public void performAction(String string, UUID uUID) {
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.CITIZEN_SLOW) {
                return GirlAnimationState.CITIZEN_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block4: {
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CITIZEN_FAST && girlAnimationState != GirlAnimationState.CITIZEN_SLOW) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw BeeNpc.rethrow(runtimeException);
                }
                return GirlAnimationState.CITIZEN_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw BeeNpc.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected void U() {
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        super.writeEntityToNBT(nBTTagCompound);
        nBTTagCompound.setBoolean("isTamed", ((Boolean)this.DataManager.get(IsTamedKey)).booleanValue());
        nBTTagCompound.setBoolean("hasChest", ((Boolean)this.DataManager.get(K)).booleanValue());
        nBTTagCompound.setTag("inventory", (NBTBase)this.Inventory.serializeNBT());
    }

    public void readFromNBT(NBTTagCompound nBTTagCompound) {
        try {
            super.readFromNBT(nBTTagCompound);
            if (nBTTagCompound.hasKey("isTamed")) {
                this.DataManager.set(IsTamedKey, (Object)nBTTagCompound.getBoolean("isTamed"));
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        this.DataManager.set(K, (Object)nBTTagCompound.getBoolean("hasChest"));
        this.Inventory.deserializeNBT(nBTTagCompound.getCompoundTag("inventory"));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        block4: switch (animEvent.getController().getName()) {
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.bee.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.bee." + (((Boolean)this.DataManager.get(K)).booleanValue() ? "idle_has_chest" : "idle"), true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case CITIZEN_START: {
                        this.createAnimationOnce("animation.bee.sex_start", false, animEvent);
                        break block4;
                    }
                    case CITIZEN_SLOW: {
                        this.createAnimationOnce("animation.bee.sex_slow", true, animEvent);
                        break block4;
                    }
                    case CITIZEN_FAST: {
                        this.createAnimationOnce("animation.bee.sex_fast", true, animEvent);
                        break block4;
                    }
                    case CITIZEN_CUM: {
                        this.createAnimationOnce("animation.bee.sex_cum", false, animEvent);
                        break block4;
                    }
                    case THROW_PEARL: {
                        this.createAnimationOnce("animation.bee.throw_pearl", true, animEvent);
                    }
                }
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.initAnimationControllers();
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeeNpc.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "pearl": {
                    if (!this.isLocalPlayerNearby() || this.getCurrentAction() != GirlAnimationState.THROW_PEARL) break;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "resetCumPercentage": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "sex_fastMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "sex_startMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "sex_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                }
                case "sex_startDone": {
                    this.setCurrentAction(GirlAnimationState.CITIZEN_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "sex_cumMSG1": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_CUMINFLATION), 2.0f);
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    break;
                }
                case "blackscreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "sex_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                    break;
                }
                case "sex_fastReady": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
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

