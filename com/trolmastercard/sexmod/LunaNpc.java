/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWater
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Biomes
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
public class LunaNpc
extends InventoryGirlEntity
implements VoidCallback,
EmptyAction {
    public static double FamiliarSpawnScale = 0.01;
    public ItemStack HeldRodStack = new ItemStack((Item)ItemLunaRod.Instance);
    public static final DataParameter<Float> Y = EntityDataManager.createKey(LunaNpc.class, (DataSerializer)DataSerializers.FLOAT).getSerializer().createKey(121);
    public static final DataParameter<ItemStack> ActiveItemStackKey = EntityDataManager.createKey(LunaNpc.class, (DataSerializer)DataSerializers.ITEM_STACK).getSerializer().createKey(120);
    public static final DataParameter<Boolean> IsBoundKey = EntityDataManager.createKey(LunaNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(119);
    public static final DataParameter<ItemStack> HeldItemStackKey = EntityDataManager.createKey(LunaNpc.class, (DataSerializer)DataSerializers.ITEM_STACK).getSerializer().createKey(118);
    static final float ah = 3.0f;
    static final float ax = 1200.0f;
    @Nullable
    public LunaFamiliarEntity Familiar;
    public float aa = 1.0f;
    public float Z = 0.0f;
    int FamiliarAgeTicks = 8000;
    public boolean ac = false;
    int aw = 0;
    boolean ay = false;
    int ak = 0;
    int ab = 0;
    public BlockPos MoveTargetPos;
    int at = 0;
    int as = 0;
    boolean am;
    long FamiliarCheckTime = 0L;
    boolean ar = false;
    Path CurrentPath = null;
    int aq = 0;
    HashSet<BlockPos> VisitedPositions = new HashSet();
    boolean ae = false;
    boolean ad = false;

    public LunaNpc(World world) {
        super(world);
        this.P = 230;
        this.O = 150;
        this.K = 320;
        this.V = new Vec3d(0.0, -0.05999999718368053, 0.10000001192092894);
        if (this.Inventory.getStackInSlot(0) == ItemStack.EMPTY) {
            this.Inventory.setStackInSlot(0, new ItemStack(Items.IRON_AXE));
        }
        try {
            if (this.Inventory.getStackInSlot(6) == ItemStack.EMPTY) {
                this.Inventory.setStackInSlot(6, new ItemStack((Item)Items.FISHING_ROD));
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
    }

    @Override
    public String getGirlName() {
        return "Luna";
    }

    @Override
    public float getRenderLabelOffset() {
        return -0.2f;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.DataManager.register(Y, Float.valueOf(0.0f));
        this.DataManager.register(ActiveItemStackKey, ItemStack.EMPTY);
        this.DataManager.register(IsBoundKey, false);
        this.DataManager.register(HeldItemStackKey, ItemStack.EMPTY);
    }

    @Override
    public void c() {
        this.a("Love it here owo");
        this.playRandomSound(ModSounds.GIRLS_LUNA_OWO, new int[0]);
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block14: {
            block12: {
                try {
                    block13: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.COWGIRL_SITTING_CUM) break block12;
                                if (girlAnimationState == GirlAnimationState.COWGIRL_SITTING_SLOW) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.COWGIRL_SITTING_FAST) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.TOUCH_BOOBS_CUM) break block14;
                            if (girlAnimationState == GirlAnimationState.TOUCH_BOOBS_FAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.TOUCH_BOOBS_SLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaNpc.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    @Override
    public void void_b() {
        this.ac = true;
    }

    public float getEyeHeight() {
        return 1.34f;
    }

    public boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        block12: {
            boolean flag;
            try {
                if (super.processInteract(entityPlayer, enumHand)) {
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            ItemStack itemStack = entityPlayer.getHeldItem(enumHand);
            try {
                flag = itemStack.getItem() == Items.NAME_TAG;
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            boolean flag2 = flag;
            try {
                if (flag2) {
                    itemStack.interactWithEntity(entityPlayer, (EntityLivingBase)this, enumHand);
                    return true;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            try {
                try {
                    if (!this.world.isRemote || this.canInteract(entityPlayer)) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
                this.a(I18n.format((String)"bia.dialogue.busy", (Object[])new Object[0]));
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
        return true;
    }

    @Override
    public boolean canInteract(EntityPlayer entityPlayer) {
        String[] stringArray = new String[]{"action.names.sex", "action.names.touchboobs", "action.names.headpat"};
        ItemStack[] itemStackArray = new ItemStack[]{new ItemStack(Items.FISH, 3, 0), new ItemStack(Items.FISH, 2, 1), null};
        LunaNpc.openActionMenuWithItems(entityPlayer, (GirlEntity)this, stringArray, itemStackArray);
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    protected static void openActionMenuWithItems(EntityPlayer entityPlayer, GirlEntity girl, String[] stringArray, ItemStack[] itemStackArray) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlCommandMenu(girl, entityPlayer, stringArray, itemStackArray, true));
    }

    public void b(ItemStack itemStack) {
        this.DataManager.set(HeldItemStackKey, itemStack);
    }

    @Override
    public void noop() {
        this.WanderAI = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.tasks.addTask(5, (EntityAIBase)this.WatchPlayerAI);
        this.tasks.addTask(5, (EntityAIBase)this.WanderAI);
    }

    @Override
    public void updateAITasks() {
        block32: {
            block34: {
                block31: {
                    block29: {
                        boolean flag;
                        DataParameter<Boolean> dataParameter;
                        EntityDataManager entityDataManager;
                        block28: {
                            block27: {
                                block26: {
                                    block25: {
                                        try {
                                            super.updateAITasks();
                                            if (this.J()) break block25;
                                            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0);
                                            break block26;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw LunaNpc.rethrow(runtimeException);
                                        }
                                    }
                                    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
                                }
                                try {
                                    try {
                                        this.void_m();
                                        this.getRenderLabelOffset();
                                        entityDataManager = this.DataManager;
                                        dataParameter = IsBoundKey;
                                        if (this.Familiar == null || this.DataManager.get(HeldItemStackKey) != ItemStack.EMPTY) break block27;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaNpc.rethrow(runtimeException);
                                    }
                                    flag = true;
                                    break block28;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw LunaNpc.rethrow(runtimeException);
                                }
                            }
                            flag = false;
                        }
                        try {
                            try {
                                entityDataManager.set(dataParameter, flag);
                                if (this.FamiliarCheckTime != this.world.getTotalWorldTime() || this.Familiar == null) break block29;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaNpc.rethrow(runtimeException);
                            }
                            this.world.removeEntity((Entity)this.Familiar);
                            this.Familiar = null;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                    }
                    if (this.ay) {
                        block30: {
                            double d2 = this.getTargetPos().distanceTo(this.getPositionVector());
                            try {
                                try {
                                    if (!(d2 < 0.5) && this.ak <= 200) break block30;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw LunaNpc.rethrow(runtimeException);
                                }
                                this.ay = false;
                                this.ak = 0;
                                this.DataManager.set(BusyKey, true);
                                this.noClip = true;
                                this.setNoGravity(true);
                                this.motionX = 0.0;
                                this.motionY = 0.0;
                                this.motionZ = 0.0;
                                this.setCurrentAction(GirlAnimationState.WAIT_CAT);
                                break block31;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaNpc.rethrow(runtimeException);
                            }
                        }
                        try {
                            try {
                                if (++this.ak != 60 && this.ak != 120) break block31;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaNpc.rethrow(runtimeException);
                            }
                            this.getNavigator().clearPath();
                            this.getNavigator().tryMoveToXYZ(this.getTargetPos().x, this.getTargetPos().y, this.getTargetPos().z, 0.2);
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                    }
                }
                try {
                    block33: {
                        try {
                            try {
                                if (!this.ac) break block32;
                                ++this.aw;
                                if (this.getPositionVector().equals((Object)this.getTargetPos())) break block33;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaNpc.rethrow(runtimeException);
                            }
                            if (this.aw <= 40) break block34;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                    }
                    this.ac = false;
                    this.aw = 0;
                    this.b(this.world.getMinecraftServer().getPlayerList().getPlayerByUUID((UUID)this.getSexPlayerUuid()).rotationYaw + 180.0f);
                    this.DataManager.set(BusyKey, true);
                    this.getNavigator().clearPath();
                    this.U();
                    break block32;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
            }
            this.rotationYaw = this.I().floatValue();
            this.setNoGravity(false);
            Vec3d vec3d = LerpMath.stepTowards(this.getPositionVector(), this.getTargetPos(), 40 - this.aw);
            this.setPosition(vec3d.x, vec3d.y, vec3d.z);
        }
        this.void_d();
        this.DataManager.set(ActiveItemStackKey, this.Inventory.getStackInSlot(6));
    }

    void void_d() {
        ItemStack itemStack = this.HeldRodStack;
        ItemStack itemStack2 = (ItemStack)this.DataManager.get(ActiveItemStackKey);
        try {
            if (itemStack2.equals(ItemStack.EMPTY)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        Map map = EnchantmentHelper.getEnchantments((ItemStack)itemStack2);
        EnchantmentHelper.setEnchantments((Map)map, (ItemStack)itemStack);
    }

    @Override
    public void onUpdate() {
        block3: {
            block2: {
                try {
                    super.onUpdate();
                    if (!GirlAnimationState.WAIT_CAT.equals((Object)this.getCurrentAction())) break block2;
                    this.getGirlUuid();
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
            }
            this.ab = 0;
        }
    }

    void f() {
        block12: {
            EntityPlayer entityPlayer;
            block11: {
                entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 10.0);
                try {
                    if (entityPlayer == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
                try {
                    if (entityPlayer.getDistance((Entity)this) > 1.25f) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
                try {
                    if (!this.world.isRemote) break block11;
                    this.a(entityPlayer, this.ab);
                    break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
            }
            try {
                if (this.ab == 25) {
                    this.handleGirlUuidEvent(entityPlayer.getPersistentID());
                    entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
                    entityPlayer.setPositionAndUpdate(this.getPositionVector().x, this.getPositionVector().y, this.getPositionVector().z);
                    this.setCurrentAction(GirlAnimationState.COWGIRL_SITTING_INTRO);
                    entityPlayer.setRotationYawHead(this.I().floatValue() + 180.0f);
                    entityPlayer.rotationYaw = this.I().floatValue() + 180.0f;
                    entityPlayer.prevRotationYaw = this.I().floatValue() + 180.0f;
                    this.AimYaw = this.I().floatValue() + 180.0f;
                    this.a(0.0, -0.075f, -0.7109375, 0.0f, 0.0f);
                    this.DataManager.set(OutfitIndexKey, 0);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
        ++this.ab;
    }

    @SideOnly(value=Side.CLIENT)
    void a(EntityPlayer entityPlayer, int i) {
        EntityPlayerSP entityPlayerSP;
        if (i == 0) {
            entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    GuiTransitionScreen.startTransition();
                    entityPlayerSP.setVelocity(0.0, 0.0, 0.0);
                    AnimationInputLock.setAnimationLocked(false);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
        if (i == 25) {
            entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    Minecraft.getMinecraft().gameSettings.thirdPersonView = 2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
    }

    @Override
    public void goToSexBed() {
        block9: {
            BlockPos blockPos;
            block8: {
                this.DataManager.set(BusyKey, false);
                this.setCurrentAction(GirlAnimationState.NULL);
                this.ar = true;
                blockPos = this.findNearbyBedPos(this.getPosition());
                try {
                    if (blockPos != null) break block8;
                    this.playRandomSound(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    NetworkHandler.channel.sendToAllAround((IMessage)new PacketSendChatMessage("<" + this.getGirlName() + "> Heh.. there is no bed nearby.. but I already ate the fish so nya~ hehe", this.dimension, this.getGirlUuid()), this.P());
                    break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
            }
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
                        throw LunaNpc.rethrow(runtimeException);
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
                    this.playRandomSound(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    this.a("Heh.. the bed is obscured.. but I already ate the fish so nya~ hehe");
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            Vec3d vec3d3 = vec3d.add(vec3dArrayArray[i][0]);
            this.b(nArray[i]);
            this.setTargetPos(new Vec3d(vec3d3.x, vec3d3.y, vec3d3.z));
            this.AimYaw = this.I().floatValue();
            this.getNavigator().clearPath();
            this.getNavigator().tryMoveToXYZ(vec3d3.x, vec3d3.y, vec3d3.z, 0.2);
            this.ay = true;
            this.ak = 0;
        }
    }

    public void j() {
        EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, (ItemStack)this.DataManager.get(HeldItemStackKey));
        Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, (double)0.2f + Math.random() * (double)0.1f, (double)-0.2f + Math.random() * (double)-0.1f), this.rotationYaw);
        entityItem.motionX = vec3d.x;
        entityItem.motionY = vec3d.y;
        entityItem.motionZ = vec3d.z;
        this.world.spawnEntity((Entity)entityItem);
        this.DataManager.set(HeldItemStackKey, ItemStack.EMPTY);
    }

    public void q() {
        try {
            this.MoveTargetPos = null;
            this.at = 0;
            this.as = 0;
            this.am = false;
            this.DataManager.set(BusyKey, false);
            this.DataManager.set(HeldItemStackKey, ItemStack.EMPTY);
            this.setSilent(false);
            this.setCurrentAction(GirlAnimationState.NULL);
            if (this.Familiar != null) {
                this.world.removeEntity((Entity)this.Familiar);
                this.Familiar = null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        try {
            if (this.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        try {
            this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
            this.tasks.addTask(5, (EntityAIBase)this.WatchPlayerAI);
            if (this.J()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        this.WanderAI = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.tasks.addTask(5, (EntityAIBase)this.WanderAI);
    }

    public void void_h() {
        try {
            this.getWalkState();
            if (++this.aq >= 3) {
                this.aq = 0;
                this.FamiliarAgeTicks = 0;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
    }

    void i() {
        block43: {
            ItemStack itemStack;
            block40: {
                block42: {
                    block41: {
                        block38: {
                            block39: {
                                try {
                                    try {
                                        block37: {
                                            try {
                                                try {
                                                    if (this.J() || this.getSexPlayerUuid() != null) break block37;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw LunaNpc.rethrow(runtimeException);
                                                }
                                                if (!this.ar) break block38;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw LunaNpc.rethrow(runtimeException);
                                            }
                                        }
                                        if (!((Boolean)this.DataManager.get(IsBoundKey)).booleanValue()) break block39;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaNpc.rethrow(runtimeException);
                                    }
                                    this.getWalkState();
                                }
                                catch (RuntimeException runtimeException) {
                                    throw LunaNpc.rethrow(runtimeException);
                                }
                            }
                            return;
                        }
                        try {
                            int i = this.FamiliarAgeTicks;
                            ++this.FamiliarAgeTicks;
                            if ((float)i < 1200.0f) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                        try {
                            if (this.Familiar == null || this.Familiar.DiveTicks != 15) break block40;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                        ((ItemLunaRod)this.HeldRodStack.getItem()).a(this.world, this, EnumHand.MAIN_HAND);
                        this.FamiliarCheckTime = this.world.getTotalWorldTime() + 20L;
                        itemStack = (ItemStack)this.DataManager.get(HeldItemStackKey);
                        try {
                            if (itemStack != ItemStack.EMPTY) break block41;
                            break block40;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                    }
                    try {
                        if (!(itemStack.getItem() instanceof ItemFood)) break block42;
                        this.setCurrentAction(GirlAnimationState.FISHING_EAT);
                        break block40;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaNpc.rethrow(runtimeException);
                    }
                }
                this.setCurrentAction(GirlAnimationState.FISHING_THROW_AWAY);
            }
            try {
                if (!this.getCurrentAction().toString().toLowerCase().contains("fishing")) {
                    this.isOwnedByLocalPlayer();
                    this.isLocalPlayerNearby();
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            try {
                try {
                    try {
                        try {
                            if (this.MoveTargetPos == null || this.CurrentPath != null) break block43;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                        if (this.getNavigator().getPath() != null) break block43;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaNpc.rethrow(runtimeException);
                    }
                    if (this.inWater) break block43;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
                if (!this.onGround) break block43;
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            RayTraceResult rayTraceResult = this.world.rayTraceBlocks(this.getPositionVector().add(0.0, (double)this.getEyeHeight(), 0.0), new Vec3d((double)this.MoveTargetPos.getX(), (double)this.MoveTargetPos.getY(), (double)this.MoveTargetPos.getZ()), true);
            try {
                this.setSilent(true);
                if (this.WanderAI != null) {
                    this.tasks.removeTask((EntityAIBase)this.WanderAI);
                    this.WanderAI = null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            try {
                if (this.WatchPlayerAI != null) {
                    this.tasks.removeTask((EntityAIBase)this.WatchPlayerAI);
                    this.WatchPlayerAI = null;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            try {
                if (this.getCurrentAction() == GirlAnimationState.NULL) {
                    this.setCurrentAction(GirlAnimationState.FISHING_START);
                    this.setTargetPos(this.getPositionVector());
                    this.DataManager.set(BusyKey, true);
                    this.b((float)Math.atan2(this.posZ - (double)this.MoveTargetPos.getZ(), this.posX - (double)this.MoveTargetPos.getX()) * 57.29578f + 90.0f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            return;
        }
        this.CurrentPath = this.getNavigator().getPath();
    }

    public void void_o() {
        this.VisitedPositions.add(this.MoveTargetPos);
        this.getWalkState();
    }

    void e() {
        try {
            if (this.MoveTargetPos == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        PathNavigate pathNavigate = this.getNavigator();
        pathNavigate.tryMoveToXYZ((double)this.MoveTargetPos.getX(), (double)this.MoveTargetPos.getY(), (double)this.MoveTargetPos.getZ(), (double)0.35f);
        Path path = pathNavigate.getPath();
        try {
            if (path == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        if (path.getCurrentPathLength() > path.getCurrentPathIndex() + 1) {
            PathPoint pathPoint = path.getPathPointFromIndex(path.getCurrentPathIndex() + 1);
            PathPoint pathPoint2 = path.getPathPointFromIndex(path.getCurrentPathLength() - 1);
            Vec3d vec3d = new Vec3d((double)pathPoint2.x, (double)pathPoint2.y, (double)pathPoint2.z);
            BlockPos blockPos = new BlockPos(pathPoint.x, pathPoint.y, pathPoint.z);
            try {
                if (this.getPositionVector().distanceTo(vec3d) < 0.75) {
                    pathNavigate.clearPath();
                    this.setPosition(vec3d.x, vec3d.y, vec3d.z);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            try {
                if (this.world.getBlockState(blockPos.add(0, 1, 0)).getBlock() == Blocks.WATER) {
                    pathNavigate.clearPath();
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            try {
                if (this.world.getBlockState(blockPos).getBlock() == Blocks.WATER) {
                    pathNavigate.clearPath();
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
            try {
                if (this.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() == Blocks.WATER) {
                    pathNavigate.clearPath();
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
    }

    void void_n() {
        block26: {
            int i;
            BlockPos blockPos;
            block25: {
                block24: {
                    int i2 = 0;
                    blockPos = null;
                    i = 0;
                    while (++i2 < 50) {
                        BlockPos blockPos2 = this.findBlockPos(this.getPosition(), i2 + 1, (Block)Blocks.WATER, 60, 10, new HashSet<Biome>(Arrays.asList(Biomes.RIVER, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.BEACH, Biomes.STONE_BEACH, Biomes.SWAMPLAND, Biomes.MUTATED_SWAMPLAND)));
                        try {
                            if (blockPos2 == null) {
                                break;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                        while (this.world.getBlockState(blockPos2.add(0, 1, 0)).getBlock() == Blocks.WATER) {
                            blockPos2 = blockPos2.add(0, 1, 0);
                        }
                        int i3 = 1;
                        BlockPos blockPos3 = blockPos2;
                        while (this.world.getBlockState(blockPos3.add(0, -1, 0)).getBlock() == Blocks.WATER) {
                            blockPos3 = blockPos3.add(0, -1, 0);
                            ++i3;
                        }
                        try {
                            if (this.VisitedPositions.contains(blockPos2)) {
                                continue;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                        if (blockPos == null) {
                            blockPos = blockPos2;
                            i = i3;
                            continue;
                        }
                        if (i3 <= i) continue;
                        blockPos = blockPos2;
                        i = i3;
                        try {
                            if (i < 6) continue;
                            break;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                    }
                    try {
                        if (blockPos == null) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaNpc.rethrow(runtimeException);
                    }
                    try {
                        try {
                            if (this.MoveTargetPos != null && this.at >= i) break block24;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaNpc.rethrow(runtimeException);
                        }
                        this.MoveTargetPos = blockPos;
                        this.at = i;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaNpc.rethrow(runtimeException);
                    }
                }
                try {
                    if (!this.MoveTargetPos.equals(blockPos)) break block25;
                    this.as = 0;
                    break block26;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
            }
            try {
                if (++this.as > 20) {
                    this.MoveTargetPos = blockPos;
                    this.at = i;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
    }

    void void_m() {
        Path path = this.getNavigator().getPath();
        try {
            if (path == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        PathPoint pathPoint = path.getFinalPathPoint();
        PathPoint pathPoint2 = new PathPoint(MathUtils.roundToInt(this.posX), MathUtils.roundToInt(this.posY), MathUtils.roundToInt(this.posZ));
        try {
            if (pathPoint == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        this.DataManager.set(Y, Float.valueOf(pathPoint.distanceTo(pathPoint2)));
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            super.a(string, uUID);
            if ("action.names.touchboobs".equals(string)) {
                this.handleGirlUuidEvent(uUID);
                this.a(true, true, uUID);
                this.a("animationFollowUp", "touch_boobs");
                this.a("currentModel", "0");
                AnimationInputLock.setAnimationLocked(false);
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        try {
            if ("action.names.sex".equals(string)) {
                this.handleGirlUuidEvent(uUID);
                this.a(true, true, uUID);
                this.a("animationFollowUp", "sex");
                AnimationInputLock.setAnimationLocked(false);
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        try {
            if ("action.names.headpat".equals(string)) {
                this.handleGirlUuidEvent(uUID);
                this.a(true, true, uUID);
                AnimationInputLock.setAnimationLocked(false);
                this.a("animationFollowUp", "headpat");
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
    }

    @Override
    protected GirlAnimationState getFollowUpAction(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.TOUCH_BOOBS_SLOW) {
                return GirlAnimationState.TOUCH_BOOBS_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.COWGIRL_SITTING_SLOW) {
                return GirlAnimationState.COWGIRL_SITTING_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState nextAnimationState(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.TOUCH_BOOBS_SLOW && girlAnimationState != GirlAnimationState.TOUCH_BOOBS_FAST) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaNpc.rethrow(runtimeException);
                    }
                    return GirlAnimationState.TOUCH_BOOBS_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.COWGIRL_SITTING_FAST && girlAnimationState != GirlAnimationState.COWGIRL_SITTING_SLOW) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaNpc.rethrow(runtimeException);
                }
                return GirlAnimationState.COWGIRL_SITTING_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw LunaNpc.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    protected void U() {
        switch ((String)this.DataManager.get(BlowjobStageKey)) {
            case "touch_boobs": {
                if (this.getCurrentAction() != GirlAnimationState.PAYMENT) {
                    this.setCurrentAction(GirlAnimationState.PAYMENT);
                    return;
                }
                this.setCurrentAction(GirlAnimationState.TOUCH_BOOBS_INTRO);
                break;
            }
            case "sex": {
                if (this.getCurrentAction() != GirlAnimationState.PAYMENT) {
                    this.setCurrentAction(GirlAnimationState.PAYMENT);
                } else {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendGirlToSex(this.getGirlUuid()));
                    NetworkHandler.channel.sendToServer((IMessage)new PacketResetGirl(this.getGirlUuid()));
                }
                return;
            }
            case "headpat": {
                this.setCurrentAction(GirlAnimationState.HEAD_PAT);
            }
        }
        if (this.world.isRemote) {
            this.a("animationFollowUp", "");
        } else {
            this.DataManager.set(BlowjobStageKey, "");
        }
    }

    protected void playHurtSound(DamageSource damageSource) {
        this.playRandomSound(ModSounds.GIRLS_LUNA_OUU, new int[0]);
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        try {
            if (this.getRNG().nextFloat() * 100.0f > 95.0f) {
                return ModSounds.GIRLS_ALLIE_SCAWY[2];
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        return ModSounds.GIRLS_LUNA_OUU[12];
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0);
    }

    protected float getJumpUpwardsMotion() {
        float f;
        try {
            f = this.isInWater() ? 1.0f : 0.5f;
        }
        catch (RuntimeException runtimeException) {
            throw LunaNpc.rethrow(runtimeException);
        }
        return f;
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.cat.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.cat.blink", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.cat.null", true, animEvent);
                    break;
                }
                if (this.isRiding()) {
                    this.createAnimationOnce("animation.cat.sit", true, animEvent);
                    break;
                }
                if (Math.abs(this.prevPosX - this.posX) + Math.abs(this.prevPosZ - this.posZ) > 0.0) {
                    if (this.onGround && Math.abs(Math.abs(this.prevPosY) - Math.abs(this.posY)) < (double)0.1f) {
                        this.createAnimationOnce(((Float)this.DataManager.get(Y)).floatValue() < 3.0f ? "animation.cat.walk" : "animation.cat.run", true, animEvent);
                    } else {
                        this.createAnimationOnce("animation.cat.fly", true, animEvent);
                    }
                    this.rotationYaw = this.rotationYawHead;
                    break;
                }
                this.createAnimationOnce("animation.cat.idle" + (this.ad ? "2" : ""), true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.createAnimationOnce("animation.cat.null", true, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.createAnimationOnce("animation.cat.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case RIDE:
                    case SIT: {
                        this.createAnimationOnce("animation.cat.sit", true, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.createAnimationOnce("animation.cat.bowcharge", false, animEvent);
                        break block5;
                    }
                    case THROW_PEARL: {
                        this.createAnimationOnce("animation.cat.throwpearl", true, animEvent);
                        break block5;
                    }
                    case DOWNED: {
                        this.createAnimationOnce("animation.cat.downed", true, animEvent);
                        break block5;
                    }
                    case FISHING_START: {
                        this.createAnimationOnce("animation.cat.start_fishing", false, animEvent);
                        break block5;
                    }
                    case FISHING_IDLE: {
                        this.createAnimationOnce("animation.cat.idle_fishing", true, animEvent);
                        break block5;
                    }
                    case FISHING_EAT: {
                        this.createAnimationOnce("animation.cat.eat_fishing", false, animEvent);
                        break block5;
                    }
                    case FISHING_THROW_AWAY: {
                        this.createAnimationOnce("animation.cat.throw_away", false, animEvent);
                        break block5;
                    }
                    case PAYMENT: {
                        this.createAnimationOnce("animation.cat.payment", false, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_INTRO: {
                        this.createAnimationOnce("animation.cat.touch_boobs_intro", false, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_SLOW: {
                        this.createAnimationOnce("animation.cat.touch_boobs_slow" + (this.ae ? "1" : ""), true, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_FAST: {
                        this.createAnimationOnce("animation.cat.touch_boobs_fast", true, animEvent);
                        break block5;
                    }
                    case TOUCH_BOOBS_CUM: {
                        this.createAnimationOnce("animation.cat.touch_boobs_cum", false, animEvent);
                        break block5;
                    }
                    case WAIT_CAT: {
                        this.createAnimationOnce("animation.cat.wait", false, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_INTRO: {
                        this.createAnimationOnce("animation.cat.sitting_intro", false, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_SLOW: {
                        this.createAnimationOnce("animation.cat.sitting_slow", true, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_FAST: {
                        this.createAnimationOnce("animation.cat.sitting_fast", true, animEvent);
                        break block5;
                    }
                    case COWGIRL_SITTING_CUM: {
                        this.createAnimationOnce("animation.cat.sitting_cum", false, animEvent);
                        break block5;
                    }
                    case HEAD_PAT: {
                        this.createAnimationOnce("animation.cat.head_pat", true, animEvent);
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
            throw LunaNpc.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackSound": {
                    this.playSoundEvent(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG);
                    break;
                }
                case "attackDone": {
                    this.setCurrentAction(GirlAnimationState.NULL);
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "idleDone": {
                    this.ad = this.getRNG().nextInt(10) == 0;
                    break;
                }
                case "idle2Done": {
                    this.ad = false;
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "start_fishingDone": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.setCurrentAction(GirlAnimationState.FISHING_IDLE);
                    break;
                }
                case "rod_shoot": {
                    if (!this.isLocalPlayerNearby()) break;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketCatActivateFishing(this.getGirlUuid()));
                    break;
                }
                case "eat": {
                    this.playSoundAt(ModSounds.pickRandomSound(ModSounds.MISC_EAT), 0.5f + 0.5f * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
                    this.aa -= 0.33333334f;
                    break;
                }
                case "eatPay": {
                    this.playSoundAt(ModSounds.pickRandomSound(ModSounds.MISC_EAT), 0.5f + 0.5f * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
                    this.n -= 0.33333334f;
                    break;
                }
                case "burp": {
                    this.playSoundAt(SoundEvents.ENTITY_PLAYER_BURP, 0.5f, this.rand.nextFloat() * 0.1f + 0.9f);
                    break;
                }
                case "eatingDone": {
                    if (this.isLocalPlayerNearby()) {
                        NetworkHandler.channel.sendToServer((IMessage)new PacketCatEatingDone(this.getGirlUuid()));
                        this.setCurrentAction(GirlAnimationState.NULL);
                    }
                    this.aa = 1.0f;
                    this.Z = 0.0f;
                    break;
                }
                case "throw_away": {
                    if (this.isLocalPlayerNearby()) {
                        NetworkHandler.channel.sendToServer((IMessage)new PacketCatThrowAwayItem(this.getGirlUuid()));
                    }
                    this.aa = 1.0f;
                    this.Z = 0.0f;
                    break;
                }
                case "renderItem": {
                    this.Z = 1.0f;
                    break;
                }
                case "paymentMSG1": {
                    this.a(this.getSexPlayerUuid(), "Here, I know u like fish and yea.. these are for you");
                    this.playSoundEvent(ModSounds.MISC_PLOB[0]);
                    break;
                }
                case "paymentMSG2": {
                    this.a("huh~?");
                    this.playRandomSound(ModSounds.GIRLS_LUNA_HUH, new int[0]);
                    break;
                }
                case "paymentMSG3": {
                    this.a("nyyyaaaa~ :D");
                    int[] nArray = new int[]{1, 7, 10, 11};
                    int i = nArray[this.getRNG().nextInt(nArray.length)];
                    this.playSoundEvent(ModSounds.GIRLS_LUNA_CUTENYA[i]);
                    break;
                }
                case "paymentMSG4": {
                    this.a("tankuuuu owowowo");
                    this.playRandomSound(ModSounds.GIRLS_LUNA_OWO, new int[0]);
                    break;
                }
                case "paymentDone": {
                    if (this.isLocalPlayerNearby()) {
                        this.U();
                    }
                    this.n = 1.0f;
                    break;
                }
                case "breath":
                case "rod_breath": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_LIGHTBREATHING, new int[0]);
                    break;
                }
                case "happyOh": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_HAPPYOH, new int[0]);
                    break;
                }
                case "cutenya3": {
                    this.playSoundEvent(ModSounds.GIRLS_LUNA_CUTENYA[3]);
                    break;
                }
                case "cutenya2": {
                    this.playSoundEvent(ModSounds.GIRLS_LUNA_CUTENYA[2]);
                    break;
                }
                case "huh": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_HUH, new int[0]);
                    break;
                }
                case "hmph": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_HMPH, new int[0]);
                    break;
                }
                case "hehe":
                case "giggle": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    break;
                }
                case "singing": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_SINGING, new int[0]);
                    break;
                }
                case "touch_boobsMSG1": {
                    this.a("comon~ touch me hihi~");
                    this.playRandomSound(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    break;
                }
                case "touch": {
                    this.playRandomSound(ModSounds.MISC_TOUCH, new int[0]);
                    break;
                }
                case "jump": {
                    this.playSoundAtVolume(ModSounds.MISC_JUMP[0], 0.2f);
                    break;
                }
                case "horninya": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_HORNINYA, new int[0]);
                    break;
                }
                case "horninya2":
                case "touch_boobs_cumMSG3":
                case "sitting_cumMSG1": {
                    this.playSoundEvent(ModSounds.GIRLS_LUNA_HORNINYA[1]);
                    this.playSoundAtVolume(ModSounds.MISC_CUMINFLATION[0], 5.0f);
                    break;
                }
                case "moan": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                    break;
                }
                case "touch_boobs_introDone": {
                    this.setCurrentAction(GirlAnimationState.TOUCH_BOOBS_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    GuiHud.showHud();
                    AnimationInputLock.setAnimationLocked(false);
                    break;
                }
                case "touch_boobs_slowDone": {
                    if (this.ae) {
                        this.ae = false;
                        break;
                    }
                    this.ae = Math.random() < 0.5;
                    break;
                }
                case "addCumSlow": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "addCumFast": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.TOUCH_BOOBS_SLOW);
                    break;
                }
                case "moanOrNya": {
                    if (Math.random() > 0.5) {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                        break;
                    }
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_HORNINYA));
                    break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "touch_boobs_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                    break;
                }
                case "resetGirl": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.resetAimTarget();
                    break;
                }
                case "touch_boobs_cumMSG1": {
                    this.playSoundEvent(ModSounds.GIRLS_LUNA_HORNINYA[3]);
                    break;
                }
                case "touch_boobs_cumMSG2": {
                    this.playSoundEvent(ModSounds.GIRLS_LUNA_HORNINYA[9]);
                    break;
                }
                case "call_playerMSG1": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    this.a("come here - big guy hehe~");
                    break;
                }
                case "pounding": {
                    this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING));
                    break;
                }
                case "sitting_introMSG1": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_GIGGLE, new int[0]);
                    this.a("hehe~");
                    break;
                }
                case "sitting_introDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.setCurrentAction(GirlAnimationState.COWGIRL_SITTING_SLOW);
                    GuiHud.resetProgress();
                    GuiHud.showHud();
                    break;
                }
                case "sitting_slowMSG1": {
                    if (this.getRNG().nextBoolean()) {
                        if (this.getRNG().nextBoolean()) {
                            this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_HORNINYA));
                            break;
                        }
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                    } else {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_LIGHTBREATHING));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02);
                    break;
                }
                case "sitting_fastMSG1": {
                    if (this.getRNG().nextBoolean()) {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_HORNINYA));
                    } else {
                        this.playSoundEvent(ModSounds.pickRandomSound(ModSounds.GIRLS_LUNA_MOAN));
                    }
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "sitting_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.COWGIRL_SITTING_SLOW);
                    Vec3d vec3d = new Vec3d(0.0, -0.075f, -0.7109375);
                    Vec3d vec3d2 = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    Minecraft.getMinecraft().player.setPosition(this.getTargetPos().x + vec3d2.x, this.getTargetPos().y + vec3d2.y, this.getTargetPos().z + vec3d2.z);
                    break;
                }
                case "sitting_fastTp": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    Vec3d vec3d = new Vec3d(0.0, -0.160625, -0.9925);
                    Vec3d vec3d3 = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    Minecraft.getMinecraft().player.setPosition(this.getTargetPos().x + vec3d3.x, this.getTargetPos().y + vec3d3.y, this.getTargetPos().z + vec3d3.z);
                    break;
                }
                case "headpatMSG1": {
                    this.a("huh?~");
                    this.playRandomSound(ModSounds.GIRLS_LUNA_HUH, new int[0]);
                    break;
                }
                case "headpatMSG2": {
                    this.playRandomSound(ModSounds.GIRLS_LUNA_MMM, new int[0]);
                    break;
                }
                case "headpatMSG3": {
                    this.a("nya~");
                    this.playSoundEvent(ModSounds.GIRLS_LUNA_HORNINYA[0]);
                }
            }
        };
        this.MovementController.transitionLengthTicks = 10.0;
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        super.readEntityFromNBT(nBTTagCompound);
        this.setNoGravity(false);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class EventHandler {
        @SubscribeEvent
        public void onEntityJoinWorld(EntityJoinWorldEvent entityJoinWorldEvent) {
            Entity entity = entityJoinWorldEvent.getEntity();
            if (entity instanceof EntityCreeper) {
                EntityCreeper entityCreeper = (EntityCreeper)entity;
                entityCreeper.tasks.addTask(3, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)entityCreeper, LunaNpc.class, 6.0f, 1.0, 1.2));
            }
        }
    }
}

