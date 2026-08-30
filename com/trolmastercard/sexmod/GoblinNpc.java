/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  javax.vecmath.Vector2f
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
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
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerChangedDimensionEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Vector2f;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class GoblinNpc
extends GirlEffectEntity
implements GirlMaster {
    public static final GirlColor DefaultTribeColor = GirlColor.DARK_GREEN;
    public static final Vec3i SeatSearchBox = new Vec3i(11, 6, 11);
    public static final Vec3d GuardSeat180 = new Vec3d(5.0, 1.0, 9.0);
    public static final Vec3d ThroneSeat0 = new Vec3d(3.0, -1.0, 6.0);
    public static final Vec3d GuardSeat270 = new Vec3d(1.0, 1.0, 5.0);
    public static final Vec3d ThroneSeat90 = new Vec3d(-6.0, -1.0, 3.0);
    public static final Vec3d GuardSeat0 = new Vec3d(5.0, 1.0, 1.0);
    public static final Vec3d ThroneSeat180 = new Vec3d(-3.0, -1.0, -6.0);
    public static final Vec3d GuardSeat90 = new Vec3d(9.0, 1.0, 5.0);
    public static final Vec3d PartnerPosC = new Vec3d(0.0, -1.0, -4.0);
    public static final Vec3d PartnerPosA = new Vec3d(1.0, -1.0, -3.0);
    public static final Vec3d PartnerPosB = new Vec3d(-1.0, -1.0, -3.0);
    public static final Vec3d ThroneSeat270 = new Vec3d(6.0, -1.0, -3.0);
    public static final int aj = 39;
    public static final int ae = 15;
    public static final int aE = 8400;
    static final int aH = 45;
    static final int ad = 32000;
    static final int aw = 26;
    static final int V = 205;
    static final int aL = 100;
    static final int aA = 1200;
    static final int ak = 30;
    static final int aW = 37;
    static final float aU = 2.0f;
    static final int aI = 5;
    static final int S = 100;
    static final int aq = 20;
    static final float aG = 0.825f;
    static final Vector2f aS = new Vector2f(0.5f, 0.99f);
    static final HashSet<Item> ag = new HashSet<Item>(Arrays.asList(Items.GOLDEN_HOE, Items.GOLDEN_HORSE_ARMOR, Items.GOLD_INGOT, Items.GOLDEN_APPLE, Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_PICKAXE, Items.GOLDEN_SWORD, Items.GOLDEN_CARROT, Items.GOLDEN_HELMET, Items.GOLDEN_BOOTS, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLD_INGOT, Items.GOLD_NUGGET, Item.getItemFromBlock((Block)Blocks.GOLD_BLOCK), Item.getItemFromBlock((Block)Blocks.GOLD_ORE)));
    public static final DataParameter<String> BoundPlayerUuidKey = EntityDataManager.createKey(GoblinNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(122);
    public static final DataParameter<String> QueenNameKey = EntityDataManager.createKey(GoblinNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(123);
    public static final DataParameter<ItemStack> HeldItemKey = EntityDataManager.createKey(GoblinNpc.class, (DataSerializer)DataSerializers.ITEM_STACK).getSerializer().createKey(124);
    public static final DataParameter<Boolean> TamedKey = EntityDataManager.createKey(GoblinNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(125);
    public static final DataParameter<Boolean> PregnantKey = EntityDataManager.createKey(GoblinNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(126);
    public boolean aX = false;
    public float ThroneRot = 0.0f;
    public long ImpregnationTick = -1L;
    public Vec3d ThronePos = Vec3d.ZERO;
    List<UUID> T = new ArrayList<UUID>();
    int aO = 31520;
    int aQ = -1;
    public int aR = -1;
    boolean aZ = false;
    BlockPos R = null;
    int Y = 0;
    int aa = 0;
    int aJ = 0;
    int an = -1;
    int am = 0;
    long LastAmbientTicks = 0L;
    List<GoblinNpc> NearbyGoblins = new ArrayList<GoblinNpc>();
    int aY = -1;
    int az = -1;
    GirlAnimationState aN = null;
    public float FadeAlpha = 1.0f;
    int Z = -1;
    boolean aD = true;
    boolean aF = true;
    boolean X = false;
    String aP = "";
    boolean ay = false;

    public GoblinNpc(World world) {
        super(world);
        this.setSize(GoblinNpc.aS.x, GoblinNpc.aS.y);
    }

    public GoblinNpc(World world, @Nonnull String string, int i) {
        this(world);
        this.DataManager.set(QueenNameKey,string);
        this.DataManager.set(M,this.generateAppearanceKey(new StringBuilder(), i));
    }

    public GoblinNpc(World world, boolean flag, float f, Vec3d vec3d) {
        this(world);
        if (!flag) {
            return;
        }
        this.DataManager.set(M,this.generateRandomAppearanceKey(new StringBuilder()));
        this.ThroneRot = f;
        this.ThronePos = vec3d;
        this.aX = true;
        this.setTargetPos(vec3d);
        this.b(f);
        this.setCurrentAction(GirlAnimationState.SIT);
        this.setShouldBeAtTargetPos(true);
        this.setPosition(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public void noop() {
        super.noop();
        this.a((UUID)null);
        this.noClip = false;
        this.setNoGravity(false);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        TribeColor tribeColor = TribeColor.values()[this.getRNG().nextInt(TribeColor.values().length)];
        this.DataManager.register(K,new BlockPos(tribeColor.getColor()));
        this.DataManager.register(TribeColorKey,DefaultTribeColor.name());
        this.DataManager.register(BoundPlayerUuidKey,"");
        this.DataManager.register(QueenNameKey,"");
        this.DataManager.register(HeldItemKey,ItemStack.EMPTY);
        this.DataManager.register(TamedKey,false);
        this.DataManager.register(PregnantKey,false);
    }

    @Override
    protected void void_a() {
        GoblinNpcRenderer.clearColorCache();
    }

    public void setDead() {
        try {
            super.setDead();
            this.a((UUID)null);
            if (this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        ItemStack itemStack = (ItemStack)this.DataManager.get(HeldItemKey);
        try {
            if (itemStack == ItemStack.EMPTY) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, itemStack);
        this.world.spawnEntity((Entity)entityItem);
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            if ("take ur stuff back".equals(string)) {
                this.setCurrentAction(GirlAnimationState.START_THROWING);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if ("use her".equals(string)) {
                this.c(uUID);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    public void c(UUID uUID) {
        this.aY = 0;
        GuiTransitionScreen.startTransition();
        AnimationInputLock.setAnimationLocked(false);
        this.handleGirlUuidEvent(uUID);
    }

    public void b(UUID uUID) {
        this.az = 0;
        GuiTransitionScreen.startTransition();
        AnimationInputLock.setAnimationLocked(false);
        this.handleGirlUuidEvent(uUID);
    }

    @Override
    public String getGirlName() {
        return "Goblin";
    }

    public float getEyeHeight() {
        return 0.75f;
    }

    @Override
    public float getRenderLabelOffset() {
        return 0.1f;
    }

    @Override
    public void a(UUID uUID) {
        try {
            if (uUID == null) {
                this.DataManager.set(BoundPlayerUuidKey,"");
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.DataManager.set(BoundPlayerUuidKey,uUID.toString());
    }

    @Override
    @Nullable
    public UUID getGirlUuid() {
        String string = (String)this.DataManager.get(BoundPlayerUuidKey);
            if ("".equals(string)) {
                return null;
            }
        try {
            return UUID.fromString((String)this.DataManager.get(BoundPlayerUuidKey));
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public int getPickupCountdown() {
        return this.aQ;
    }

    @Override
    public void setPickupCountdown(int i) {
        this.aQ = i;
    }

    protected String generateRandomAppearanceKey(StringBuilder stringBuilder) {
        GirlEffectEntity.appendRandomNumber(stringBuilder, 3);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, 7);
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, 7);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 5);
        GirlEffectEntity.appendRandomNumber(stringBuilder, MarkColor.values().length - 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, GirlColor.values().length - 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, TribeColor.values().length - 1);
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, 1);
        return stringBuilder.toString();
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        GirlEffectEntity.appendRandomNumber(stringBuilder, 3);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 8);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 8);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 5);
        GirlEffectEntity.appendRandomNumber(stringBuilder, MarkColor.values().length - 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, GirlColor.values().length - 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, TribeColor.values().length - 1);
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, 0);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> D() {
        return new ArrayList<Integer>(){
            {
                this.add(4);
                this.add(3);
                this.add(3);
                this.add(16);
                this.add(16);
                this.add(6);
                this.add(MarkColor.values().length);
                this.add(GirlColor.values().length);
                this.add(TribeColor.values().length);
            }
        };
    }

    @Override
    public List<Integer> u() {
        return Collections.singletonList(2);
    }

    @Override
    public Vec2i getSlotColor(int i) {
        switch (i) {
            case 0: {
                return new Vec2i(40, 130);
            }
            case 1: {
                return new Vec2i(60, 130);
            }
            case 2: {
                return new Vec2i(80, 130);
            }
            case 3: {
                return new Vec2i(100, 130);
            }
            case 4: {
                return new Vec2i(120, 130);
            }
            case 5: {
                return new Vec2i(140, 130);
            }
            case 6: {
                return new Vec2i(160, 130);
            }
            case 7: {
                return new Vec2i(180, 130);
            }
            case 8: {
                return new Vec2i(200, 0);
            }
            case 9: {
                return new Vec2i(200, 130);
            }
        }
        return Vec2i.ZERO;
    }

    @Override
    public void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : list) {
            GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, i);
        }
        try {
            GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, Integer.parseInt(GirlEffectEntity.getAttributeStrings(this)[9]));
            this.DataManager.set(M,stringBuilder.toString());
            if (Main.proxy instanceof ClientProxy) {
                GoblinNpcRenderer.clearColorCache();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    void refreshOutfitModel() {
        try {
            if (this.OutfitData == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry entry : this.OutfitData) {
            int i = (Integer)((Map.Entry)entry.getValue()).getValue();
            GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, i);
        }
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, Integer.parseInt(GirlEffectEntity.getAttributeStrings(this)[9]));
        this.DataManager.set(M,stringBuilder.toString());
        GoblinNpcRenderer.clearColorCache();
    }

    protected String generateAppearanceKey(StringBuilder stringBuilder, int i) {
        GirlEffectEntity.appendRandomNumber(stringBuilder, 3);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 7);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 7);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 5);
        GirlEffectEntity.appendRandomNumber(stringBuilder, MarkColor.values().length - 1);
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, i);
        GirlEffectEntity.appendRandomNumber(stringBuilder, TribeColor.values().length - 1);
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, 0);
        return stringBuilder.toString();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        try {
            super.writeEntityToNBT(nBTTagCompound);
            nBTTagCompound.setString("bodyColor", (String)this.DataManager.get(TribeColorKey));
            nBTTagCompound.setInteger("eyeColorX", ((BlockPos)this.DataManager.get(K)).getX());
            nBTTagCompound.setInteger("eyeColorY", ((BlockPos)this.DataManager.get(K)).getY());
            nBTTagCompound.setInteger("eyeColorZ", ((BlockPos)this.DataManager.get(K)).getZ());
            nBTTagCompound.setString("model", (String)this.DataManager.get(M));
            nBTTagCompound.setString("girlID", (String)this.DataManager.get(GirlUuidKey));
            nBTTagCompound.setString("queen", (String)this.DataManager.get(QueenNameKey));
            nBTTagCompound.setBoolean("isQueen", this.aX);
            nBTTagCompound.setBoolean("isTamed", ((Boolean)this.DataManager.get(TamedKey)).booleanValue());
            nBTTagCompound.setInteger("robTicks", this.aO);
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        nBTTagCompound.setBoolean("preggo", ((Boolean)this.DataManager.get(PregnantKey)).booleanValue());
        nBTTagCompound.setFloat("throneRot", this.ThroneRot);
        nBTTagCompound.setDouble("thronePosX", this.ThronePos.x);
        nBTTagCompound.setDouble("thronePosY", this.ThronePos.y);
        nBTTagCompound.setDouble("thronePosZ", this.ThronePos.z);
        nBTTagCompound.setLong("impregnationTick", this.ImpregnationTick);
        try {
            for (int i = 0; i < this.T.size(); ++i) {
                nBTTagCompound.setString("guard" + i, this.T.get(i).toString());
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        block10: {
            super.readEntityFromNBT(nBTTagCompound);
            this.aX = nBTTagCompound.getBoolean("isQueen");
            this.DataManager.set(M,nBTTagCompound.getString("model"));
            this.DataManager.set(TribeColorKey,nBTTagCompound.getString("bodyColor"));
            String[] stringArray = GirlEffectEntity.getAttributeStrings(this);
            try {
                try {
                    if (Integer.parseInt(stringArray[3]) <= 7 && Integer.parseInt(stringArray[4]) <= 7) break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                this.DataManager.set(M,this.generateAppearanceKey(new StringBuilder(), this.getAppearanceIndex()));
                Main.LOGGER.log(Level.INFO, "updated an old Goblin");
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            this.DataManager.set(K,new BlockPos(nBTTagCompound.getInteger("eyeColorX"), nBTTagCompound.getInteger("eyeColorY"), nBTTagCompound.getInteger("eyeColorZ")));
            this.DataManager.set(GirlUuidKey,nBTTagCompound.getString("girlID"));
            this.DataManager.set(QueenNameKey,nBTTagCompound.getString("queen"));
            this.DataManager.set(TamedKey,nBTTagCompound.getBoolean("isTamed"));
            this.aO = nBTTagCompound.getInteger("robTicks");
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.ThroneRot = nBTTagCompound.getFloat("throneRot");
        this.ThronePos = new Vec3d(nBTTagCompound.getDouble("thronePosX"), nBTTagCompound.getDouble("thronePosY"), nBTTagCompound.getDouble("thronePosZ"));
        int i = 0;
        try {
            while (!"".equals(nBTTagCompound.getString("guard" + i))) {
                this.T.add(UUID.fromString(nBTTagCompound.getString("guard" + i)));
                ++i;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.DataManager.set(PregnantKey,nBTTagCompound.getBoolean("preggo"));
        this.ImpregnationTick = nBTTagCompound.getLong("impregnationTick");
    }

    protected boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        block16: {
            block15: {
                block12: {
                    block14: {
                        block13: {
                            try {
                                if (this.world.isRemote) {
                                    return true;
                                }
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GoblinNpc.rethrow(concurrentModificationException);
                            }
                            try {
                                if (this.aX) {
                                    return true;
                                }
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GoblinNpc.rethrow(concurrentModificationException);
                            }
                            try {
                                try {
                                    if (this.getCurrentAction() != GirlAnimationState.RUN) break block12;
                                    if (!((double)this.getDistance((Entity)entityPlayer) > 3.5)) break block13;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                                entityPlayer.sendStatusMessage((ITextComponent)new TextComponentString("get a bit closer..."), true);
                                break block14;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GoblinNpc.rethrow(concurrentModificationException);
                            }
                        }
                        this.setTargetPos(entityPlayer.getPositionVector());
                        this.b(entityPlayer.rotationYaw);
                        this.setCurrentAction(GirlAnimationState.CATCH);
                        this.DataManager.set(BlowjobStageKey,"bj");
                        this.a(entityPlayer.getPersistentID());
                        this.handleGirlUuidEvent(entityPlayer.getPersistentID());
                        this.getNavigator().clearPath();
                        this.motionX = 0.0;
                        this.motionY = 0.0;
                        this.motionZ = 0.0;
                    }
                    return true;
                }
                try {
                    if (!GoblinNpc.playerHasSingleGoblin(entityPlayer.getPersistentID())) break block15;
                    entityPlayer.sendStatusMessage((ITextComponent)new TextComponentString("you are already carrying a Goblin"), true);
                    break block16;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
            }
            this.a(entityPlayer.getPersistentID());
            this.setCurrentAction(GirlAnimationState.PICK_UP);
            this.aQ = 45;
            this.setShouldBeAtTargetPos(false);
            this.DataManager.set(TamedKey,true);
            this.getNavigator().clearPath();
        }
        return true;
    }

    public static boolean playerHasSingleGoblin(UUID uUID) {
        try {
            if (uUID == null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                try {
                    if (!(girl instanceof GirlMaster)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (girl.world.isRemote) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (girl.isDead) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                UUID uUID2 = ((GirlMaster)((Object)girl)).getGirlUuid();
                if (!uUID.equals(uUID2)) continue;
                return true;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        return false;
    }

    @Override
    protected void initEntityAI() {
        this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 2.0f, 1.0f);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this));
        this.tasks.addTask(5, (EntityAIBase)this.WatchPlayerAI);
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        this.f();
        GoblinNpc.handlePickUp(this);
        this.sitOnThrone();
        this.tickItemTheftTimer();
        this.fleeFromPlayers();
        this.tryStartThroneBreeding();
        this.tickThroneJump();
        this.w();
        this.tickBreedingReward();
        this.dismissTamedGuards();
        this.void_h();
        this.void_o();
        this.tickStandUp();
        this.void_n();
    }

    public boolean canBeCollidedWith() {
        GirlAnimationState girlAnimationState = this.getCurrentAction();
        try {
            if (girlAnimationState == GirlAnimationState.THROWN) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.RUN) {
                return super.canBeCollidedWith();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.AWAIT_PICK_UP) {
                return super.canBeCollidedWith();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getGirlUuid() != null) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (girlAnimationState != GirlAnimationState.NULL) {
                return false;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return super.canBeCollidedWith();
    }

    void facePlayer(EntityPlayer entityPlayer) {
        PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(entityPlayer.getPersistentID());
        Vec3d vec3d = new Vec3d(entityPlayer.posX, entityPlayer.posY + (double)(playerGirl == null ? entityPlayer.eyeHeight : playerGirl.getEyeHeight()), entityPlayer.posZ);
        Vec3d vec3d2 = new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
        double d = vec3d2.distanceTo(vec3d);
        double d2 = vec3d.y - vec3d2.y;
        this.rotationPitch = (float)(-(Math.sin(d2 / d) * 57.29577951308232));
    }

    void void_n() {
        block31: {
            block30: {
                block27: {
                    int i;
                    int i2;
                    block29: {
                        block28: {
                            block25: {
                                try {
                                    if (!((Boolean)this.DataManager.get(TamedKey)).booleanValue()) {
                                        return;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                                try {
                                    if (this.getSexPlayerUuid() != null) {
                                        return;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                                try {
                                    if (this.getCurrentAction() != GirlAnimationState.NULL) {
                                        return;
                                    }
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                                EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 15.0);
                                try {
                                    try {
                                        if (entityPlayer == null || !(entityPlayer.getDistance((Entity)this) < 2.0f)) break block25;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GoblinNpc.rethrow(concurrentModificationException);
                                    }
                                    this.facePlayer(entityPlayer);
                                    this.getNavigator().clearPath();
                                    return;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    block26: {
                                        try {
                                            try {
                                                if (this.R == null || this.getDistance(this.R.getX(), this.R.getY(), this.R.getZ()) > this.l()) break block26;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GoblinNpc.rethrow(concurrentModificationException);
                                            }
                                            if (this.Y <= 100) break block27;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GoblinNpc.rethrow(concurrentModificationException);
                                        }
                                    }
                                    if (!this.getRNG().nextBoolean()) break block28;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                                i2 = 1;
                                break block29;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GoblinNpc.rethrow(concurrentModificationException);
                            }
                        }
                        i2 = -1;
                    }
                    int i3 = i2 * this.getRNG().nextInt(5);
                    try {
                        i = this.getRNG().nextBoolean() ? 1 : -1;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.rethrow(concurrentModificationException);
                    }
                    int i4 = i * this.getRNG().nextInt(5);
                    int i5 = BedLogic.countNearbyBeds(this.world, this.getPosition().getX() + i3, this.getPosition().getZ() + i4);
                    this.R = new BlockPos(this.getPosition().getX() + i3, i5, this.getPosition().getZ() + i4);
                    this.Y = 0;
                }
                try {
                    if (!(Math.sqrt(this.R.distanceSq((Vec3i)this.getPosition())) > 2.0)) break block30;
                    this.getNavigator().tryMoveToXYZ((double)this.R.getX(), (double)this.R.getY(), (double)this.R.getZ(), (double)0.3f);
                    this.getAppearanceIndex();
                    break block31;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
            }
            ++this.Y;
        }
    }

    double l() {
        return Math.sqrt(800.0);
    }

    void tickStandUp() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.STAND_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.aa < 37) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.aa = 0;
        this.setCurrentAction(GirlAnimationState.NULL);
    }

    @Override
    public void a(int i) {
        this.aJ = i;
    }

    public int getThrowTicks() {
        return this.aJ;
    }

    void void_o() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.THROWN) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (!this.onGround) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        int i = this.getThrowTicks() + 1;
        try {
            this.a(i);
            if (i < 30) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.a(0);
        this.setCurrentAction(GirlAnimationState.STAND_UP);
    }

    void void_h() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (!((Boolean)this.DataManager.get(PregnantKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.ImpregnationTick + 8400L < this.world.getTotalWorldTime()) {
                this.DataManager.set(PregnantKey,false);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    void dismissTamedGuards() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.NearbyGoblins.isEmpty()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        boolean flag = false;
        for (GoblinNpc goblin : this.NearbyGoblins) {
            if (!((Boolean)goblin.getDataManager().get(TamedKey)).booleanValue()) continue;
            flag = true;
        }
        try {
            if (!flag) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.h("Farewell my knight. You are welcome once I am breedable again.");
        for (GoblinNpc goblin2 : this.NearbyGoblins) {
            try {
                if (((Boolean)goblin2.getDataManager().get(TamedKey)).booleanValue()) continue;
                goblin2.setCurrentAction(GirlAnimationState.VANISH);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        this.NearbyGoblins.clear();
        this.handleGirlUuidEvent((UUID)null);
    }

    void tickBreedingReward() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.Z == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.Z < 100) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.Z = -1;
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                this.resetAimTarget();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                this.resetAimTarget();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.handleGirlUuidEvent((UUID)null);
        for (GoblinNpc goblin : this.NearbyGoblins) {
            goblin.handleGirlUuidEvent((UUID)null);
        }
        List<GoblinNpc> list = this.getOrSpawnGuards();
        float f = this.ThroneRot + 180.0f;
        Vec3d vec3d = this.ThronePos.add(GoblinNpc.rotateVec3dYaw(PartnerPosA, f));
        Vec3d vec3d2 = this.ThronePos.add(GoblinNpc.rotateVec3dYaw(PartnerPosB, f));
        Vec3d vec3d3 = this.ThronePos.add(GoblinNpc.rotateVec3dYaw(PartnerPosC, f));
        GoblinNpc goblin2 = (GoblinNpc)list.get(0);
        GoblinNpc goblin3 = (GoblinNpc)list.get(1);
        goblin2.setTargetPos(vec3d);
        goblin3.setTargetPos(vec3d2);
        goblin2.b(0.0f);
        goblin3.b(0.0f);
        goblin2.setShouldBeAtTargetPos(true);
        goblin3.setShouldBeAtTargetPos(true);
        goblin2.setCurrentAction(GirlAnimationState.AWAIT_PICK_UP);
        goblin3.setCurrentAction(GirlAnimationState.AWAIT_PICK_UP);
        goblin2.setNoGravity(false);
        goblin3.setNoGravity(false);
        entityPlayer.setNoGravity(false);
        goblin2.noClip = false;
        goblin3.noClip = false;
        entityPlayer.noClip = false;
        entityPlayer.rotationYaw = f;
        entityPlayer.rotationPitch = 30.0f;
        entityPlayer.setPositionAndUpdate(vec3d3.x, vec3d3.y, vec3d3.z);
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), (EntityPlayerMP)entityPlayer);
        this.h("Thanks to you, my clan is soon going to get a few new members! In return I will bear of one of my guards to serve as your personal Onahole. Choose wisely~");
    }

    void w() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.an == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.an < 205) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.an = -1;
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = GoblinNpc.rotateVec3dYaw(new Vec3d(0.0, 0.15625 - (double)entityPlayer.getEyeHeight(), -0.8859375), this.ThroneRot - 180.0f);
        vec3d = vec3d.add(this.getTargetPos());
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    public static Vec3d rotateVec3dYaw(Vec3d vec3d, float f) {
        return GoblinNpc.rotateVec3d(vec3d, 0.0f, f);
    }

    public static Vec3d rotateVec3d(Vec3d vec3d, float f, float f2) {
        Vec3d vec3d2 = new Vec3d(vec3d.x, vec3d.y * Math.cos((double)f * (Math.PI / 180)) - vec3d.z * Math.sin((double)f * (Math.PI / 180)), vec3d.y * Math.sin((double)f * (Math.PI / 180)) + vec3d.z * Math.cos((double)f * (Math.PI / 180)));
        Vec3d vec3d3 = new Vec3d(-Math.sin((double)(f2 + 90.0f) * (Math.PI / 180)) * vec3d2.x - Math.sin((double)f2 * (Math.PI / 180)) * vec3d2.z, vec3d2.y, Math.cos((double)(f2 + 90.0f) * (Math.PI / 180)) * vec3d2.x + Math.cos((double)f2 * (Math.PI / 180)) * vec3d2.z);
        return vec3d3;
    }

    void tickThroneJump() {
        GoblinNpc goblin;
        Vec3d vec3d;
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.JUMP_0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.am < 26) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.am = 0;
        switch ((int)this.ThroneRot) {
            case 90: {
                vec3d = this.ThronePos.add(ThroneSeat90);
                break;
            }
            case 180: {
                vec3d = this.ThronePos.add(ThroneSeat180);
                break;
            }
            case -90: {
                vec3d = this.ThronePos.add(ThroneSeat270);
                break;
            }
            default: {
                vec3d = this.ThronePos.add(ThroneSeat0);
            }
        }
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.setTargetPos(vec3d);
        this.b(this.ThroneRot);
        this.setCurrentAction(GirlAnimationState.BREEDING_INTRO_0);
        this.noClip = true;
        this.setNoGravity(true);
        Vec3d vec3d2 = GoblinNpc.rotateVec3dYaw(new Vec3d(0.0, 0.44375 - (double)entityPlayer.eyeHeight, -0.7875), this.ThroneRot - 180.0f);
        entityPlayer.noClip = true;
        entityPlayer.setNoGravity(true);
        entityPlayer.setPositionAndUpdate(vec3d2.x + vec3d.x, vec3d2.y + vec3d.y, vec3d2.z + vec3d.z);
        List<GoblinNpc> list = this.getOrSpawnGuards();
        if (list.size() >= 1) {
            goblin = list.get(0);
            goblin.setTargetPos(vec3d);
            goblin.b(this.ThroneRot);
            goblin.setCurrentAction(GirlAnimationState.BREEDING_INTRO_1);
            goblin.noClip = true;
            goblin.setNoGravity(true);
        }
        if (list.size() >= 2) {
            goblin = list.get(1);
            goblin.setTargetPos(vec3d);
            goblin.b(this.ThroneRot);
            goblin.setCurrentAction(GirlAnimationState.BREEDING_INTRO_2);
            goblin.noClip = true;
            goblin.setNoGravity(true);
        }
        this.an = 0;
    }

    AxisAlignedBB makeBoundingBox(Vec3d vec3d, Vec3d vec3d2) {
        return new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d2.x, vec3d2.y, vec3d2.z);
    }

    void tryStartThroneBreeding() {
        EntityPlayer entityPlayer;
        block27: {
            block28: {
                try {
                    if (!this.aX) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                try {
                    if (this.getSexPlayerUuid() != null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                Vec3d vec3d = null;
                switch ((int)this.ThroneRot) {
                    case 0: {
                        vec3d = GuardSeat0;
                        break;
                    }
                    case 90: {
                        vec3d = GuardSeat90;
                        break;
                    }
                    case 180: {
                        vec3d = GuardSeat180;
                        break;
                    }
                    case -90: {
                        vec3d = GuardSeat270;
                    }
                }
                try {
                    if (vec3d == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                Vec3d vec3d2 = this.ThronePos.subtract(0.5, 0.0, 0.5).subtract(vec3d);
                AxisAlignedBB axisAlignedBB = this.makeBoundingBox(vec3d2, vec3d2.add((double)SeatSearchBox.getX(), (double)SeatSearchBox.getY(), (double)SeatSearchBox.getZ()));
                List list = this.world.getEntitiesWithinAABB(EntityPlayer.class, axisAlignedBB);
                try {
                    if (list.isEmpty()) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                entityPlayer = (EntityPlayer)list.get(0);
                try {
                    if (!entityPlayer.onGround) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                try {
                    try {
                        if (!((Boolean)this.DataManager.get(PregnantKey)).booleanValue()) break block27;
                        if (this.LastAmbientTicks + 1200L >= this.world.getTotalWorldTime()) break block28;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.rethrow(concurrentModificationException);
                    }
                    entityPlayer.sendStatusMessage((ITextComponent)new TextComponentString("The Queen is still pregnant - so no breeding for you uwu"), true);
                    this.LastAmbientTicks = this.world.getTotalWorldTime();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
            }
            return;
        }
        UUID uUID = entityPlayer.getPersistentID();
        Vec3d vec3d = entityPlayer.getPositionVector();
        float f = entityPlayer.rotationYaw + 180.0f;
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
        this.handleGirlUuidEvent(uUID);
        this.setCurrentAction(GirlAnimationState.JUMP_0);
        this.setTargetPos(vec3d);
        this.b(f);
        this.setShouldBeAtTargetPos(true);
        List<GoblinNpc> list = this.getOrSpawnGuards();
        if (list.size() > 0) {
            GoblinNpc goblin = list.get(0);
            goblin.handleGirlUuidEvent(uUID);
            goblin.setCurrentAction(GirlAnimationState.JUMP_1);
            goblin.setTargetPos(vec3d);
            goblin.b(f);
            goblin.setShouldBeAtTargetPos(true);
            if (list.size() > 1) {
                GoblinNpc goblin2 = list.get(1);
                goblin2.handleGirlUuidEvent(uUID);
                goblin2.setCurrentAction(GirlAnimationState.JUMP_2);
                goblin2.setTargetPos(vec3d);
                goblin2.b(f);
                goblin2.setShouldBeAtTargetPos(true);
            }
        }
    }

    List<GoblinNpc> getOrSpawnGuards() {
        GoblinNpc goblin;
        try {
            if (this.NearbyGoblins.size() > 1) {
                return this.NearbyGoblins;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        for (GoblinNpc goblin2 : this.NearbyGoblins) {
            this.world.removeEntity((Entity)goblin2);
        }
        this.NearbyGoblins.clear();
        GoblinNpc goblin3 = new GoblinNpc(this.world, this.getGirlUuid().toString(), this.getAppearanceIndex());
        goblin3.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)goblin3);
        this.NearbyGoblins.add(goblin3);
        GoblinNpc goblin2 = new GoblinNpc(this.world, this.getGirlUuid().toString(), this.getAppearanceIndex());
        goblin2.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)goblin2);
        this.NearbyGoblins.add(goblin2);
        return this.NearbyGoblins;
    }

    void f() {
        block11: {
            try {
                if (this.aZ) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                try {
                    try {
                        try {
                            this.noClip = false;
                            this.setNoGravity(false);
                            if (this.aX || ((Boolean)this.DataManager.get(TamedKey)).booleanValue()) break block11;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GoblinNpc.rethrow(concurrentModificationException);
                        }
                        if (((String)this.DataManager.get(QueenNameKey)).equals("")) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.rethrow(concurrentModificationException);
                    }
                    if (this.getCurrentAction() != GirlAnimationState.NULL) break block11;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                this.world.removeEntity((Entity)this);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        this.aZ = true;
    }

    void tickThrowSequence() {
        GoblinNpc goblin = this;
        int i = goblin.getThrowCounter();
        try {
            if (i == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        goblin.c(++i);
        if (i == 15) {
            Vec3d vec3d = GoblinNpc.getBoundPlayerPos(this);
            float f = GoblinNpc.getBoundPlayerPitch(this);
            float f2 = GoblinNpc.getBoundPlayerYaw(this);
            this.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
            Vec3d vec3d2 = GoblinNpc.rotateVec3d(new Vec3d(0.0, 0.0, 1.5), f, f2);
            try {
                this.motionX = vec3d2.x;
                this.motionY = vec3d2.y;
                this.motionZ = vec3d2.z;
                if (!this.world.isRemote) {
                    this.b(f2);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            this.noClip = false;
            this.setNoGravity(false);
            if (i == 39) {
                this.c(-1);
                this.setCurrentAction(GirlAnimationState.THROWN);
                this.handleGirlUuidEvent((UUID)null);
                this.a((UUID)null);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    public static Vec3d getBoundPlayerPos(GirlEntity girl) {
        GirlMaster girlMaster = (GirlMaster)((Object)girl);
        UUID uUID = girlMaster.getGirlUuid();
        try {
            if (uUID == null) {
                return girl.getPositionVector();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = girl.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return girl.getPositionVector();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return entityPlayer.getPositionVector().add(0.0, (double)entityPlayer.getEyeHeight(), 0.0).add(GoblinNpc.rotateVec3d(new Vec3d((double)0.4f, 0.0, 0.0), GoblinNpc.getBoundPlayerPitch(girl), GoblinNpc.getBoundPlayerYaw(girl)));
    }

    public static float getBoundPlayerYaw(GirlEntity girl) {
        GirlMaster girlMaster = (GirlMaster)((Object)girl);
        UUID uUID = girlMaster.getGirlUuid();
        try {
            if (uUID == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = girl.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return entityPlayer.rotationYawHead;
    }

    public static float getBoundPlayerPitch(GirlEntity girl) {
        GirlMaster girlMaster = (GirlMaster)((Object)girl);
        UUID uUID = girlMaster.getGirlUuid();
        try {
            if (uUID == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = girl.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return 0.0f;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return entityPlayer.rotationPitch;
    }

    void fleeFromPlayers() {
        boolean flag;
        try {
            if (!this.onGround) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.RUN) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 100.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        double d = 20.0;
        do {
            if (d <= 0.0) {
                return;
            }
            Vec3d vec3d = this.getPositionVector().subtract(entityPlayer.getPositionVector());
            Vec3d vec3d2 = new Vec3d(Math.abs(vec3d.x), Math.abs(vec3d.y), Math.abs(vec3d.z));
            double d2 = vec3d2.x / (vec3d2.x + vec3d2.z);
            double d3 = vec3d2.z / (vec3d2.x + vec3d2.z);
            Vec3d vec3d3 = this.getPositionVector().add(new Vec3d((double)(vec3d.x > 0.0 ? 1 : -1) * d2 * d, 0.0, (double)(vec3d.z > 0.0 ? 1 : -1) * d3 * d));
            PathNavigate pathNavigate = this.getNavigator();
            pathNavigate.clearPath();
            flag = pathNavigate.tryMoveToXYZ(vec3d3.x, vec3d3.y, vec3d3.z, (double)0.825f);
            d -= 1.0;
        } while (!flag);
    }

    protected void jump() {
        block4: {
            try {
                try {
                    if (this.getCurrentAction() != GirlAnimationState.RUN || this.j()) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        super.jump();
    }

    boolean j() {
        boolean flag;
        int i;
        Path path;
        block9: {
            PathNavigate pathNavigate = this.getNavigator();
            path = pathNavigate.getPath();
            try {
                if (path == null) {
                    return true;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            i = path.getCurrentPathIndex();
            int i2 = path.getCurrentPathLength();
            try {
                try {
                    if (i2 != i && i2 - 1 != i) break block9;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                return true;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        PathPoint pathPoint = path.getPathPointFromIndex(i);
        PathPoint pathPoint2 = path.getPathPointFromIndex(i + 1);
        try {
            flag = pathPoint2.y - pathPoint.y == 1;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return flag;
    }

    void tickItemTheftTimer() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (((Boolean)this.DataManager.get(TamedKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (((Boolean)this.DataManager.get(PregnantKey)).booleanValue()) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.SIT) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.aO < 32000) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 3000.0);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (!entityPlayer.onGround) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (entityPlayer.isAirBorne) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        Integer i = this.java_lang_Integer_c(entityPlayer);
        try {
            if (i == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = entityPlayer.getPositionVector();
        Vec3d vec3d2 = this.getPositionVector();
        Vec3d vec3d3 = vec3d.subtract(vec3d2);
        double d = Math.sqrt(vec3d3.x * vec3d3.x + vec3d3.z * vec3d3.z);
        try {
            if (d > 100.0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        ItemStack itemStack = entityPlayer.inventory.getStackInSlot(i.intValue()).copy();
        GoblinNpc goblin = new GoblinNpc(this.world, this.getGirlUuid().toString(), this.getAppearanceIndex());
        Vec3d vec3d4 = GoblinNpc.rotateVec3dYaw(new Vec3d(0.0, 0.0, (double)-0.2f), entityPlayer.rotationYawHead);
        goblin.setPosition(entityPlayer.posX + vec3d4.x, entityPlayer.posY, entityPlayer.posZ + vec3d4.z);
        goblin.setCurrentAction(GirlAnimationState.RUN);
        this.world.spawnEntity((Entity)goblin);
        goblin.DataManager.set(HeldItemKey,itemStack);
        entityPlayer.sendMessage((ITextComponent)new TextComponentString(String.format("<%s> I got your %s hehe~", goblin.getDisplayName(), itemStack.getDisplayName())));
        entityPlayer.inventory.removeStackFromSlot(i.intValue());
        this.aO = 0;
    }

    int getAppearanceIndex() {
        return Integer.parseInt(GirlEffectEntity.getAttributeStrings(this)[7]);
    }

    @Nullable
    Integer java_lang_Integer_c(EntityPlayer entityPlayer) {
        NonNullList nonNullList = entityPlayer.inventory.mainInventory;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < nonNullList.size(); ++i) {
            ItemStack itemStack = (ItemStack)nonNullList.get(i);
            try {
                if (itemStack == ItemStack.EMPTY) {
                    continue;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                if (!ag.contains(itemStack.getItem())) continue;
                arrayList.add(i);
                continue;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            if (arrayList.isEmpty()) {
                return null;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return (Integer)arrayList.get(this.getRNG().nextInt(arrayList.size()));
    }

    void sitOnThrone() {
        try {
            if (!this.aX) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.setTargetPos(this.ThronePos);
        this.b(this.ThroneRot);
        this.setShouldBeAtTargetPos(true);
        this.setNoGravity(true);
        this.setCurrentAction(GirlAnimationState.SIT);
    }

    @Override
    public void onUpdate() {
        block7: {
            try {
                this.getRenderLabelOffset();
                GoblinNpc.syncThrowState(this);
                this.tickThrowSequence();
                if (this.getGirlUuid() != null) {
                    this.inPortal = false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                try {
                    super.onUpdate();
                    this.getCurrentAction();
                    this.tickThrownCleanup();
                    this.tickVanishFade();
                    if (!this.world.isRemote) break block7;
                    this.getScale();
                    this.tickNelsonCountdown();
                    if (this.getGirlUuid() == null) break block7;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                this.noClip = true;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
    }

    public GirlAnimationState getShadowAction() {
        return this.aN;
    }

    @Override
    public void a(GirlAnimationState girlAnimationState) {
        this.aN = girlAnimationState;
    }

    @Override
    public void c(int i) {
        this.aR = i;
    }

    public int getThrowCounter() {
        return this.aR;
    }

    public static void syncThrowState(GirlEntity girl) {
        GirlMaster girlMaster;
        GirlAnimationState girlAnimationState;
        block4: {
            girlAnimationState = girl.getCurrentAction();
            girlMaster = (GirlMaster)((Object)girl);
            try {
                try {
                    GirlAnimationState shadowAction = girl instanceof GoblinNpc ? ((GoblinNpc)girl).getShadowAction() : ((GoblinPlayer)girl).getShadowAction();
                if (shadowAction == GirlAnimationState.START_THROWING || girlAnimationState != GirlAnimationState.START_THROWING) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                girlMaster.c(0);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        girlMaster.a(girlAnimationState);
    }

    public void setFire(int i) {
        try {
            if (this.getGirlUuid() == null) {
                super.setFire(i);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    void tickVanishFade() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.VANISH) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            this.FadeAlpha -= 0.05f;
            if (this.FadeAlpha > 0.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.world.removeEntity((Entity)this);
    }

    void tickThrownCleanup() {
        block16: {
            try {
                if (((Boolean)this.DataManager.get(TamedKey)).booleanValue()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                if (this.getCurrentAction() != GirlAnimationState.THROWN) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                try {
                    if (this.onGround || this.isInWater()) break block16;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            this.FadeAlpha = (float)((double)this.FadeAlpha - 0.05);
            if (this.FadeAlpha > 0.0f) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (this.world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.setCurrentAction(GirlAnimationState.NULL);
        this.handleGirlUuidEvent((UUID)null);
        this.a((UUID)null);
        this.world.removeEntity((Entity)this);
    }

    @SideOnly(value=Side.CLIENT)
    void void_v() {
        try {
            if (this.aY == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.aY != 15) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.aY = -1;
        this.setCurrentAction(GirlAnimationState.PAIZURI_START);
        Minecraft.getMinecraft().player.closeScreen();
    }

    @SideOnly(value=Side.CLIENT)
    void tickNelsonCountdown() {
        try {
            if (this.az == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            if (++this.az != 15) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.az = -1;
        this.setCurrentAction(GirlAnimationState.NELSON_INTRO);
        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.player.closeScreen();
        minecraft.gameSettings.thirdPersonView = 2;
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block59: {
            GirlAnimationState girlAnimationState2;
            block58: {
                block57: {
                    block56: {
                        block55: {
                            block53: {
                                block51: {
                                    block49: {
                                        girlAnimationState2 = this.getCurrentAction();
                                        try {
                                            block50: {
                                                try {
                                                    try {
                                                        if (girlAnimationState2 != GirlAnimationState.PAIZURI_CUM) break block49;
                                                        if (girlAnimationState == GirlAnimationState.PAIZURI_SLOW) break block50;
                                                    }
                                                    catch (ConcurrentModificationException concurrentModificationException) {
                                                        throw GoblinNpc.rethrow(concurrentModificationException);
                                                    }
                                                    if (girlAnimationState != GirlAnimationState.PAIZURI_FAST) break block49;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                                }
                                            }
                                            return;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GoblinNpc.rethrow(concurrentModificationException);
                                        }
                                    }
                                    try {
                                        block52: {
                                            try {
                                                try {
                                                    if (girlAnimationState2 != GirlAnimationState.NELSON_CUM) break block51;
                                                    if (girlAnimationState == GirlAnimationState.NELSON_SLOW) break block52;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                                }
                                                if (girlAnimationState != GirlAnimationState.NELSON_FAST) break block51;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GoblinNpc.rethrow(concurrentModificationException);
                                            }
                                        }
                                        return;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GoblinNpc.rethrow(concurrentModificationException);
                                    }
                                }
                                try {
                                    block54: {
                                        try {
                                            try {
                                                if (girlAnimationState2 != GirlAnimationState.BREEDING_CUM_0) break block53;
                                                if (girlAnimationState == GirlAnimationState.BREEDING_SLOW_0) break block54;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GoblinNpc.rethrow(concurrentModificationException);
                                            }
                                            if (girlAnimationState != GirlAnimationState.BREEDING_FAST_0) break block53;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GoblinNpc.rethrow(concurrentModificationException);
                                        }
                                    }
                                    return;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                try {
                                    if (girlAnimationState != GirlAnimationState.START_THROWING || this.world.isRemote) break block55;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GoblinNpc.rethrow(concurrentModificationException);
                                }
                                this.handleGirlUuidEvent(this.getGirlUuid());
                                this.dropHeldItem();
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GoblinNpc.rethrow(concurrentModificationException);
                            }
                        }
                        try {
                            try {
                                if (girlAnimationState != GirlAnimationState.PAIZURI_START || this.world.isRemote) break block56;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GoblinNpc.rethrow(concurrentModificationException);
                            }
                            this.z();
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GoblinNpc.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.NELSON_INTRO || this.world.isRemote) break block57;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GoblinNpc.rethrow(concurrentModificationException);
                        }
                        this.q();
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.rethrow(concurrentModificationException);
                    }
                }
                try {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.PAIZURI_CUM || girlAnimationState != GirlAnimationState.NULL) break block58;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GoblinNpc.rethrow(concurrentModificationException);
                        }
                        if (this.world.isRemote) break block58;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.rethrow(concurrentModificationException);
                    }
                    this.cleanupAfterUse();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
            }
            try {
                if (girlAnimationState == GirlAnimationState.BREEDING_CUM_0) {
                    this.DataManager.set(PregnantKey,true);
                    this.ImpregnationTick = this.world.getTotalWorldTime();
                    this.LastAmbientTicks = this.world.getTotalWorldTime();
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                if (girlAnimationState == GirlAnimationState.BREEDING_CUM_0) {
                    this.Z = 0;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                if (girlAnimationState == GirlAnimationState.NELSON_CUM) {
                    this.DataManager.set(PregnantKey,true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                try {
                    if (girlAnimationState2 != GirlAnimationState.NELSON_CUM || girlAnimationState == GirlAnimationState.NELSON_CUM) break block59;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                this.DataManager.set(PregnantKey,false);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    void cleanupAfterUse() {
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer != null) {
                PacketResetGirl.Handler.handle((EntityPlayerMP)entityPlayer);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            this.handleGirlUuidEvent((UUID)null);
            this.setShouldBeAtTargetPos(false);
            this.noClip = false;
            this.setNoGravity(false);
            this.DataManager.set(HeldItemKey,ItemStack.EMPTY);
            if (!((Boolean)this.DataManager.get(TamedKey)).booleanValue()) {
                this.setPositionAndUpdate(this.HomePos.x, this.HomePos.y, this.HomePos.z);
                this.world.removeEntity((Entity)this);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    void q() {
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.a((UUID)null);
        this.setTargetPos(entityPlayer.getPositionVector());
        this.b(entityPlayer.rotationYaw);
        this.setShouldBeAtTargetPos(true);
        this.noClip = true;
        this.setNoGravity(true);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        this.handleGirlUuidEvent(entityPlayer.getPersistentID());
    }

    void z() {
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.a((UUID)null);
        this.setTargetPos(entityPlayer.getPositionVector());
        this.b(entityPlayer.rotationYaw + 180.0f);
        this.setShouldBeAtTargetPos(true);
        this.noClip = true;
        this.setNoGravity(true);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        this.handleGirlUuidEvent(entityPlayer.getPersistentID());
        entityPlayer.setPositionAndUpdate(entityPlayer.posX, entityPlayer.posY - 0.5, entityPlayer.posZ);
        entityPlayer.rotationPitch = 70.0f;
        entityPlayer.prevRotationPitch = 70.0f;
    }

    void dropHeldItem() {
        ItemStack itemStack = (ItemStack)this.DataManager.get(HeldItemKey);
        try {
            if (itemStack == ItemStack.EMPTY) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        entityPlayer.inventory.addItemStackToInventory(itemStack.copy());
        this.DataManager.set(HeldItemKey,ItemStack.EMPTY);
    }

    public static void handlePickUp(GirlEntity girl) {
        try {
            if (girl.getCurrentAction() != GirlAnimationState.PICK_UP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        GirlMaster girlMaster = (GirlMaster)((Object)girl);
        UUID uUID = girlMaster.getGirlUuid();
        try {
            if (uUID == null) {
                girlMaster.setPickupCountdown(-1);
                girl.setCurrentAction(GirlAnimationState.NULL);
                girlMaster.a((UUID)null);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = girl.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                girlMaster.setPickupCountdown(-1);
                girl.setCurrentAction(GirlAnimationState.NULL);
                girlMaster.a((UUID)null);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            girl.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
            if (girl.getPositionVector().distanceTo(entityPlayer.getPositionVector()) > 10.0) {
                girlMaster.setPickupCountdown(-1);
                girl.setCurrentAction(GirlAnimationState.NULL);
                girlMaster.a((UUID)null);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        int i = ((GoblinNpc)girl).getPickupCountdown() - 1;
        try {
            girlMaster.setPickupCountdown(i);
            if (i != 0) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        girl.setCurrentAction(GirlAnimationState.SHOULDER_IDLE);
        girl.noClip = true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean t() {
        boolean flag;
        block12: {
            try {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                if (this.getGirlUuid() != null) {
                    return false;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            try {
                try {
                    if (((Boolean)this.DataManager.get(TamedKey)).booleanValue() || Minecraft.getMinecraft().player.canEntityBeSeen((Entity)this)) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                return false;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            flag = this.getGirlUuid() == null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return flag;
    }

    void void_y() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.SHOULDER_IDLE) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        UUID uUID = this.getGirlUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
        this.noClip = true;
        this.setNoGravity(true);
    }

    @Override
    protected GirlAnimationState nextAnimationState(GirlAnimationState girlAnimationState) {
        switch (girlAnimationState) {
            case PAIZURI_IDLE:
            case PAIZURI_SLOW: {
                return GirlAnimationState.PAIZURI_FAST;
            }
            case BREEDING_SLOW_0: {
                return GirlAnimationState.BREEDING_FAST_0;
            }
            case BREEDING_SLOW_2: {
                return GirlAnimationState.BREEDING_FAST_2;
            }
            case NELSON_SLOW: {
                return GirlAnimationState.NELSON_FAST;
            }
        }
        return null;
    }

    protected GirlAnimationState getFollowUpAction(GirlAnimationState girlAnimationState) {
        switch (girlAnimationState) {
            case PAIZURI_SLOW:
            case PAIZURI_FAST:
            case PAIZURI_FAST_CONTINUES: {
                return GirlAnimationState.PAIZURI_CUM;
            }
            case BREEDING_1: {
                return GirlAnimationState.BREEDING_CUM_1;
            }
            case BREEDING_SLOW_2:
            case BREEDING_FAST_2: {
                return GirlAnimationState.BREEDING_CUM_2;
            }
            case NELSON_SLOW:
            case NELSON_FAST: {
                return GirlAnimationState.NELSON_CUM;
            }
            case BREEDING_SLOW_0:
            case BREEDING_FAST_0: {
                for (GoblinNpc goblin : this.NearbyGoblins) {
                    goblin.getFollowUpAction(girlAnimationState);
                }
                return GirlAnimationState.BREEDING_CUM_0;
            }
        }
        return null;
    }

    public boolean isCeilingBlocked() {
        boolean flag;
        Block block = this.world.getBlockState(this.getPosition().add(0, 1, 0)).getBlock();
        try {
            flag = !block.isPassable((IBlockAccess)this.world, this.getPosition().add(0, 1, 0));
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return flag;
    }

    public void fall(float f, float f2) {
        block4: {
            GirlAnimationState girlAnimationState = this.getCurrentAction();
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.THROWN && girlAnimationState != GirlAnimationState.START_THROWING) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        super.fall(f, f2);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        if (this.ActionController == null) {
            this.initAnimationControllers();
        }
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.goblin.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.goblin.blink", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.goblin.null", true, animEvent);
                    break;
                }
                double d = Math.abs(this.prevPosX - this.posX) + Math.abs(this.prevPosZ - this.posZ);
                if (!((Boolean)this.DataManager.get(BusyKey)).booleanValue() && d > 0.0) {
                    if (this.onGround && Math.abs(Math.abs(this.prevPosY) - Math.abs(this.posY)) < (double)0.1f) {
                        if (d > (double)0.2f) {
                            this.createAnimationOnce("animation.goblin.walk", true, animEvent);
                        } else {
                            this.createAnimationOnce("animation.goblin.walk", true, animEvent);
                        }
                        this.rotationYaw = this.rotationYawHead;
                        break;
                    }
                    this.createAnimationOnce("animation.goblin.fly", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.goblin.idle", true, animEvent);
                break;
            }
            case "action": {
                Minecraft minecraft = Minecraft.getMinecraft();
                String string = minecraft.player.getPersistentID().equals(this.getGirlUuid()) && minecraft.gameSettings.thirdPersonView == 0 ? "1" : "3";
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.createAnimationOnce("animation.goblin.null", true, animEvent);
                        break block5;
                    }
                    case SHOULDER_IDLE: {
                        this.createAnimationOnce("animation.goblin.shoulder_idle", true, animEvent);
                        break block5;
                    }
                    case PICK_UP: {
                        this.createAnimationOnce(String.format("animation.goblin.pick_up_%sperson", string), true, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.createAnimationOnce("animation.goblin.sit", true, animEvent);
                        break block5;
                    }
                    case RUN: {
                        if (this.onGround) {
                            this.createAnimationOnce("animation.goblin.running", true, animEvent);
                            break block5;
                        }
                        this.createAnimationOnce("animation.goblin.fly", true, animEvent);
                        break block5;
                    }
                    case CATCH: {
                        this.createAnimationOnce(String.format("animation.goblin.catch_%sperson", string), true, animEvent);
                        break block5;
                    }
                    case CATCH_BJ: {
                        this.createAnimationOnce(String.format("animation.goblin.catch_%spersonBj", string), true, animEvent);
                        break block5;
                    }
                    case CATCH_BJ_IDLE: {
                        this.createAnimationOnce(String.format("animation.goblin.catch_%spersonBj_idle", string), true, animEvent);
                        break block5;
                    }
                    case START_THROWING: {
                        this.createAnimationOnce(String.format("animation.goblin.throw_%sperson", string), true, animEvent);
                        break block5;
                    }
                    case THROWN: {
                        this.createAnimationOnce("animation.goblin.thrown", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_START: {
                        this.createAnimationOnce("animation.goblin.paizuri_start", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_SLOW: {
                        this.createAnimationOnce("animation.goblin.paizuri_slow" + this.aP, true, animEvent);
                        break block5;
                    }
                    case PAIZURI_FAST: {
                        this.createAnimationOnce("animation.goblin.paizuri_fast", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_FAST_CONTINUES: {
                        this.createAnimationOnce("animation.goblin.paizuri_fast_countinues", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_IDLE: {
                        this.createAnimationOnce("animation.goblin.paizuri_idle", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_CUM: {
                        this.createAnimationOnce("animation.goblin.paizuri_cum", true, animEvent);
                        break block5;
                    }
                    case JUMP_0: {
                        this.createAnimationOnce("animation.goblin.jump_1", true, animEvent);
                        break block5;
                    }
                    case JUMP_1: {
                        this.createAnimationOnce("animation.goblin.jump_2", true, animEvent);
                        break block5;
                    }
                    case JUMP_2: {
                        this.createAnimationOnce("animation.goblin.jump_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_INTRO_0: {
                        this.createAnimationOnce("animation.goblin.breeding_intro_1", true, animEvent);
                        break block5;
                    }
                    case BREEDING_INTRO_1: {
                        this.createAnimationOnce("animation.goblin.breeding_intro_2", true, animEvent);
                        break block5;
                    }
                    case BREEDING_INTRO_2: {
                        this.createAnimationOnce("animation.goblin.breeding_intro_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_SLOW_0: {
                        this.createAnimationOnce("animation.goblin.breeding_slow_1" + (this.aD ? "l" : "r"), true, animEvent);
                        break block5;
                    }
                    case BREEDING_SLOW_2: {
                        this.createAnimationOnce("animation.goblin.breeding_slow_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_FAST_0: {
                        this.createAnimationOnce("animation.goblin.breeding_fast_1" + (this.ay ? "c" : "s"), true, animEvent);
                        break block5;
                    }
                    case BREEDING_FAST_2: {
                        this.createAnimationOnce("animation.goblin.breeding_fast_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_CUM_0: {
                        this.createAnimationOnce("animation.goblin.breeding_cum_1", true, animEvent);
                        break block5;
                    }
                    case BREEDING_CUM_1: {
                        this.createAnimationOnce("animation.goblin.breeding_cum_2", true, animEvent);
                        break block5;
                    }
                    case BREEDING_CUM_2: {
                        this.createAnimationOnce("animation.goblin.breeding_cum_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_1: {
                        this.createAnimationOnce("animation.goblin.breeding_2", true, animEvent);
                        break block5;
                    }
                    case VANISH:
                    case AWAIT_PICK_UP: {
                        this.createAnimationOnce("animation.goblin.await_pick_up", true, animEvent);
                        break block5;
                    }
                    case STAND_UP: {
                        this.createAnimationOnce("animation.goblin.stand_up", false, animEvent);
                        break block5;
                    }
                    case NELSON_INTRO: {
                        this.createAnimationOnce("animation.goblin.nelson_intro", true, animEvent);
                        break block5;
                    }
                    case NELSON_SLOW: {
                        this.createAnimationOnce("animation.goblin.nelson_slow" + (this.aF ? "" : "2"), true, animEvent);
                        break block5;
                    }
                    case NELSON_FAST: {
                        this.createAnimationOnce("animation.goblin.nelson_fast" + (this.X ? "c" : "s"), true, animEvent);
                        break block5;
                    }
                    case NELSON_CUM: {
                        this.createAnimationOnce("animation.goblin.nelson_cum", true, animEvent);
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
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "catchEh": {
                    this.a("ehh..");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchAkward": {
                    this.a("awkward..");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchWell": {
                    this.a("well...");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchRather": {
                    this.a("would you rather have this stupid... thing?");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchMe": {
                    this.a("...or use me?~");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchDone": {
                    if (!"bj".equals((String)this.DataManager.get(BlowjobStageKey))) break;
                    this.setCurrentAction(GirlAnimationState.CATCH_BJ);
                    break;
                }
                case "catchBjDone": {
                    this.setCurrentAction(GirlAnimationState.CATCH_BJ_IDLE);
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    GirlEntity.openActionMenuWithItems(entityPlayerSP, this, new String[]{"use her", "take ur stuff back"}, null, false);
                    break;
                }
                case "paizuriChoice": {
                    this.a("good choice!~");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "paizuriBoth": {
                    this.a("...for both of us!");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "paizruiUse": {
                    this.a("now use me like a fuck toy!~");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "paizuriSwitch": {
                    if (this.getRNG().nextBoolean()) break;
                    this.aP = "".equals(this.aP) ? "2" : "";
                    break;
                }
                case "touch": {
                    this.playRandomSoundWithChance(ModSounds.MISC_TOUCH, 3.0f);
                    break;
                }
                case "pound": {
                    this.playRandomSound(ModSounds.MISC_POUNDING, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "paizuri_startDone": {
                    this.setCurrentAction(GirlAnimationState.PAIZURI_IDLE);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "paizuriFastDone": {
                    this.setCurrentAction(GirlAnimationState.PAIZURI_SLOW);
                    break;
                }
                case "paizuriFastReady": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.PAIZURI_FAST_CONTINUES);
                    break;
                }
                case "paizuriFastContinuesReady": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "smallPound": {
                    this.playRandomSoundWithChance(ModSounds.MISC_POUNDING, 0.25f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "paizruiCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    entityPlayerSP.rotationPitch = 70.0f;
                    entityPlayerSP.prevRotationPitch = 70.0f;
                    break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "paizuriCumDone": {
                    this.setCurrentAction(GirlAnimationState.NULL);
                    break;
                }
                case "cumSound": {
                    this.playRandomSoundWithChance(ModSounds.MISC_SMALLINSERTS, 3.0f);
                    break;
                }
                case "jumpCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    Minecraft minecraft = Minecraft.getMinecraft();
                    minecraft.player.rotationYaw = this.I().floatValue() + 170.0f;
                    minecraft.player.rotationPitch = -20.0f;
                    minecraft.player.rotationYawHead = minecraft.player.rotationYaw;
                    minecraft.gameSettings.thirdPersonView = 2;
                    break;
                }
                case "breedingHmm": {
                    if (this.isOwnedByLocalPlayer()) {
                        Minecraft minecraft = Minecraft.getMinecraft();
                        minecraft.player.rotationYaw = this.I().floatValue() + 180.0f;
                        minecraft.player.rotationPitch = -15.0f;
                        minecraft.player.rotationYawHead = minecraft.player.rotationYaw;
                        minecraft.gameSettings.thirdPersonView = 0;
                    }
                    this.a("hmm...");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "breedingFound": {
                    this.a("guess we found a worthy breeding partner!");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "breedingEnough": {
                    this.a("Eh.. go pin him down, before he runs off!");
                    this.playRandomSound(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "breedingCam2": {
                    if (this.isOwnedByLocalPlayer()) {
                        Minecraft minecraft = Minecraft.getMinecraft();
                        minecraft.gameSettings.thirdPersonView = 2;
                        minecraft.player.rotationYaw = this.I().floatValue() - 120.0f;
                        minecraft.player.rotationPitch = -30.0f;
                    }
                }
                case "breedingIntroDone": {
                    this.setCurrentAction(GirlAnimationState.BREEDING_SLOW_0);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "breeding_slow1Done": {
                    if (this.getRNG().nextBoolean()) {
                        boolean bl = this.aD = !this.aD;
                    }
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.BREEDING_FAST_0);
                    this.ay = false;
                    break;
                }
                case "breeding_fast1Done": {
                    this.setCurrentAction(GirlAnimationState.BREEDING_SLOW_0);
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.ay = false;
                    break;
                }
                case "breeding_fast1Ready": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.ay = true;
                    this.N();
                    this.ActionController.tickOffset = 0.0;
                    break;
                }
                case "cum": {
                    this.playRandomSoundWithChance(ModSounds.MISC_SMALLINSERTS, 2.0f);
                    break;
                }
                case "breeding_intro_3Done": {
                    this.setCurrentAction(GirlAnimationState.BREEDING_SLOW_2);
                    break;
                }
                case "breeding_3_wiggle": {
                    if (!this.getRNG().nextBoolean()) break;
                    this.ActionController.tickOffset = 0.0;
                    break;
                }
                case "breeding_fast_3Done": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.BREEDING_SLOW_2);
                    break;
                }
                case "breeding_intro_2Done": {
                    this.setCurrentAction(GirlAnimationState.BREEDING_1);
                    break;
                }
                case "breeding_cumCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    Minecraft minecraft = Minecraft.getMinecraft();
                    minecraft.gameSettings.thirdPersonView = 0;
                    minecraft.player.rotationYaw = this.I().floatValue() + 180.0f;
                    minecraft.player.rotationPitch = -15.0f;
                    minecraft.player.rotationYawHead = minecraft.player.rotationYaw;
                    minecraft.gameSettings.thirdPersonView = 0;
                    break;
                }
                case "neslon_introDone": {
                    this.setCurrentAction(GirlAnimationState.NELSON_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "nelson_slowDone": {
                    if (!this.getRNG().nextBoolean()) break;
                    this.aF = !this.aF;
                    break;
                }
                case "neslon_fastSwitch": {
                    if (!this.isOwnedByLocalPlayer()) {
                        this.X = true;
                        return;
                    }
                    if (!AnimationInputLock.SneakPressed) break;
                    this.X = true;
                    break;
                }
                case "neslon_fastBackSwitch": {
                    if (!this.isOwnedByLocalPlayer()) {
                        this.ActionController.tickOffset = 0.0;
                        break;
                    }
                    if (!AnimationInputLock.SneakPressed) break;
                    this.ActionController.tickOffset = 0.0;
                    break;
                }
                case "nelsonFastDone": {
                    this.X = false;
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.setCurrentAction(GirlAnimationState.NELSON_SLOW);
                    break;
                }
                case "nelson_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    this.setCurrentAction(GirlAnimationState.NULL);
                }
            }
        };
        this.ActionController.registerSoundListener(iSoundListener);
        this.MovementController.transitionLengthTicks = 10.0;
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }
 static RuntimeException rethrow(RuntimeException error) {

       return error;

    }


    private static RuntimeException rethrow(Exception exception) {
        return new RuntimeException(exception);
    }

    public static class EventHandler {
        static Minecraft MinecraftInstance = null;

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
            if (clientTickEvent.phase == TickEvent.Phase.START) {
                return;
            }
            ArrayList<GoblinNpc> arrayList = new ArrayList<GoblinNpc>();
            try {
                for (GirlEntity girl : GirlEntity.getAllGirls()) {
                    EntityPlayer entityPlayer;
                    GoblinNpc goblin;
                    UUID uUID;
                    if (!girl.world.isRemote || !(girl instanceof GoblinNpc) || (uUID = (goblin = (GoblinNpc)girl).getGirlUuid()) == null || (entityPlayer = goblin.world.getPlayerEntityByUUID(uUID)) == null || entityPlayer.dimension == goblin.dimension) continue;
                    arrayList.add(goblin);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
            for (GoblinNpc goblin : arrayList) {
                goblin.a((UUID)null);
                goblin.handleGirlUuidEvent((UUID)null);
                goblin.setDead();
            }
        }

        @SubscribeEvent
        public void onChangedDimension(PlayerEvent.PlayerChangedDimensionEvent playerChangedDimensionEvent) {
            EntityPlayer entityPlayer = playerChangedDimensionEvent.player;
            UUID uUID = entityPlayer.getPersistentID();
            int i = playerChangedDimensionEvent.toDim;
            World world = entityPlayer.world;
            GoblinNpc goblin = null;
            try {
                for (GirlEntity girl : GirlEntity.getAllGirls()) {
                    try {
                        if (girl.world.isRemote) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (!(girl instanceof GoblinNpc)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    GoblinNpc goblin2 = (GoblinNpc)girl;
                    try {
                        if (!uUID.equals(goblin2.getGirlUuid())) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    String string = goblin2.C();
                    String string2 = goblin2.F();
                    goblin = goblin2;
                    goblin.a((UUID)null);
                    goblin.handleGirlUuidEvent((UUID)null);
                    goblin.setCurrentAction(GirlAnimationState.NULL);
                    GoblinNpc goblin3 = new GoblinNpc(world);
                    goblin3.dimension = i;
                    goblin3.forceSpawn = true;
                    goblin3.setCustomModel(string);
                    goblin3.DataManager.set(M,string2);
                    goblin3.DataManager.set(TamedKey,true);
                    world.spawnEntity((Entity)goblin3);
                    goblin3.setPositionAndUpdate(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
                    goblin3.a(uUID);
                    goblin3.setCurrentAction(GirlAnimationState.SHOULDER_IDLE);
                    break;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
            try {
                if (goblin == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            world.removeEntity(goblin);
            GirlEntity.getAllGirls().remove(goblin);
        }

        @SubscribeEvent
        public void a(LivingAttackEvent livingAttackEvent) {
            try {
                if (livingAttackEvent.getSource() == DamageSource.OUT_OF_WORLD) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            EntityLivingBase entityLivingBase = livingAttackEvent.getEntityLiving();
            try {
                if (!(entityLivingBase instanceof GoblinNpc)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            GoblinNpc goblin = (GoblinNpc)entityLivingBase;
            try {
                if (goblin.getGirlUuid() != null) {
                    livingAttackEvent.setCanceled(true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
        }

        @SubscribeEvent
        @SideOnly(value=Side.CLIENT)
        public void a(InputEvent.KeyInputEvent keyInputEvent) {
            try {
                if (MinecraftInstance == null) {
                    MinecraftInstance = Minecraft.getMinecraft();
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            try {
                if (GoblinNpc.EventHandler.MinecraftInstance.currentScreen instanceof GuiGirlPreview) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            try {
                if (!ClientProxy.keyBindings[0].isPressed()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            GirlEntity girl = null;
            UUID uUID = Minecraft.getMinecraft().player.getPersistentID();
            try {
                for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
                    if (!girl2.world.isRemote) continue;
                    try {
                        if (!(girl2 instanceof GirlMaster)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
                    }
                    GirlMaster girlMaster = (GirlMaster)((Object)girl2);
                    if (!uUID.equals(girlMaster.getGirlUuid())) continue;
                    girl = girl2;
                    break;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
            try {
                if (girl == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            try {
                if (girl.getCurrentAction() != GirlAnimationState.SHOULDER_IDLE) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.EventHandler.rethrow(concurrentModificationException);
            }
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlPreview(girl));
        }

        private static ConcurrentModificationException rethrow(ConcurrentModificationException concurrentModificationException) {
            return concurrentModificationException;
        }
    }
}

