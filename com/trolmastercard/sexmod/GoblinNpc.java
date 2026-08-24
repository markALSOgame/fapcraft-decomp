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
        this.DataManager.set(QueenNameKey, (Object)string);
        this.DataManager.set(M, (Object)this.generateAppearanceKey(new StringBuilder(), i));
    }

    public GoblinNpc(World world, boolean flag, float f, Vec3d vec3d) {
        this(world);
        if (!flag) {
            return;
        }
        this.DataManager.set(M, (Object)this.generateRandomAppearanceKey(new StringBuilder()));
        this.ThroneRot = f;
        this.ThronePos = vec3d;
        this.aX = true;
        this.c(vec3d);
        this.b(f);
        this.b(GirlAnimationState.SIT);
        this.a(true);
        this.setPosition(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public void g() {
        super.noop();
        this.a((UUID)null);
        this.noClip = false;
        this.setNoGravity(false);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        TribeColor tribeColor = TribeColor.values()[this.getRNG().nextInt(TribeColor.values().length)];
        this.DataManager.register(K, (Object)new BlockPos(tribeColor.getColor()));
        this.DataManager.register(N, (Object)DefaultTribeColor.name());
        this.DataManager.register(BoundPlayerUuidKey, (Object)"");
        this.DataManager.register(QueenNameKey, (Object)"");
        this.DataManager.register(HeldItemKey, (Object)ItemStack.EMPTY);
        this.DataManager.register(TamedKey, (Object)false);
        this.DataManager.register(PregnantKey, (Object)false);
    }

    @Override
    protected void a() {
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
                this.b(GirlAnimationState.START_THROWING);
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
        this.e(uUID);
    }

    public void b(UUID uUID) {
        this.az = 0;
        GuiTransitionScreen.startTransition();
        AnimationInputLock.setAnimationLocked(false);
        this.e(uUID);
    }

    @Override
    public String c() {
        return "Goblin";
    }

    public float getEyeHeight() {
        return 0.75f;
    }

    @Override
    public float i() {
        return 0.1f;
    }

    @Override
    public void a(UUID uUID) {
        try {
            if (uUID == null) {
                this.DataManager.set(BoundPlayerUuidKey, (Object)"");
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        this.DataManager.set(BoundPlayerUuidKey, (Object)uUID.toString());
    }

    @Override
    @Nullable
    public UUID e() {
        String string = (String)this.DataManager.get(BoundPlayerUuidKey);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (Exception exception) {
            throw GoblinNpc.rethrow(exception);
        }
        try {
            return UUID.fromString((String)this.DataManager.get(BoundPlayerUuidKey));
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public int c() {
        return this.aQ;
    }

    @Override
    public void b(int i) {
        this.aQ = i;
    }

    protected String generateRandomAppearanceKey(StringBuilder stringBuilder) {
        GoblinNpc.generateAppearanceKey(stringBuilder, 3);
        GoblinNpc.generateAppearanceKey(stringBuilder, 2);
        GoblinNpc.generateAppearanceKey(stringBuilder, 2);
        GoblinNpc.appendZeroPaddedNumber(stringBuilder, 7);
        GoblinNpc.appendZeroPaddedNumber(stringBuilder, 7);
        GoblinNpc.generateAppearanceKey(stringBuilder, 5);
        GoblinNpc.generateAppearanceKey(stringBuilder, MarkColor.values().length - 1);
        GoblinNpc.generateAppearanceKey(stringBuilder, GirlColor.values().length - 1);
        GoblinNpc.generateAppearanceKey(stringBuilder, TribeColor.values().length - 1);
        GoblinNpc.appendZeroPaddedNumber(stringBuilder, 1);
        return stringBuilder.toString();
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        GoblinNpc.generateAppearanceKey(stringBuilder, 3);
        GoblinNpc.generateAppearanceKey(stringBuilder, 2);
        GoblinNpc.generateAppearanceKey(stringBuilder, 2);
        GoblinNpc.generateAppearanceKey(stringBuilder, 8);
        GoblinNpc.generateAppearanceKey(stringBuilder, 8);
        GoblinNpc.generateAppearanceKey(stringBuilder, 5);
        GoblinNpc.generateAppearanceKey(stringBuilder, MarkColor.values().length - 1);
        GoblinNpc.generateAppearanceKey(stringBuilder, GirlColor.values().length - 1);
        GoblinNpc.generateAppearanceKey(stringBuilder, TribeColor.values().length - 1);
        GoblinNpc.appendZeroPaddedNumber(stringBuilder, 0);
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

    /*
     * Exception decompiling
     */
    @Override
    public Vec2i g(int i) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[SWITCH]
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
    public void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : list) {
            GoblinNpc.appendZeroPaddedNumber(stringBuilder, i);
        }
        try {
            GoblinNpc.appendZeroPaddedNumber(stringBuilder, Integer.parseInt(GoblinNpc.handlePickUp(this)[9]));
            this.DataManager.set(M, (Object)stringBuilder.toString());
            if (Main.proxy instanceof ClientProxy) {
                GoblinNpcRenderer.clearColorCache();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    void i() {
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
            GoblinNpc.appendZeroPaddedNumber(stringBuilder, i);
        }
        GoblinNpc.appendZeroPaddedNumber(stringBuilder, Integer.parseInt(GoblinNpc.handlePickUp(this)[9]));
        this.DataManager.set(M, (Object)stringBuilder.toString());
        GoblinNpcRenderer.clearColorCache();
    }

    protected String generateAppearanceKey(StringBuilder stringBuilder, int i) {
        GoblinNpc.generateAppearanceKey(stringBuilder, 3);
        GoblinNpc.generateAppearanceKey(stringBuilder, 2);
        GoblinNpc.generateAppearanceKey(stringBuilder, 2);
        GoblinNpc.generateAppearanceKey(stringBuilder, 7);
        GoblinNpc.generateAppearanceKey(stringBuilder, 7);
        GoblinNpc.generateAppearanceKey(stringBuilder, 5);
        GoblinNpc.generateAppearanceKey(stringBuilder, MarkColor.values().length - 1);
        GoblinNpc.appendZeroPaddedNumber(stringBuilder, i);
        GoblinNpc.generateAppearanceKey(stringBuilder, TribeColor.values().length - 1);
        GoblinNpc.appendZeroPaddedNumber(stringBuilder, 0);
        return stringBuilder.toString();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        try {
            super.writeEntityToNBT(nBTTagCompound);
            nBTTagCompound.setString("bodyColor", (String)this.DataManager.get(N));
            nBTTagCompound.setInteger("eyeColorX", ((BlockPos)this.DataManager.get(K)).getX());
            nBTTagCompound.setInteger("eyeColorY", ((BlockPos)this.DataManager.get(K)).getY());
            nBTTagCompound.setInteger("eyeColorZ", ((BlockPos)this.DataManager.get(K)).getZ());
            nBTTagCompound.setString("model", (String)this.DataManager.get(M));
            nBTTagCompound.setString("girlID", (String)this.DataManager.get(u));
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
            this.DataManager.set(M, (Object)nBTTagCompound.getString("model"));
            this.DataManager.set(N, (Object)nBTTagCompound.getString("bodyColor"));
            String[] stringArray = GoblinNpc.handlePickUp(this);
            try {
                try {
                    if (Integer.parseInt(stringArray[3]) <= 7 && Integer.parseInt(stringArray[4]) <= 7) break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
                this.DataManager.set(M, (Object)this.generateAppearanceKey(new StringBuilder(), this.k()));
                Main.LOGGER.log(Level.INFO, "updated an old Goblin");
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        try {
            this.DataManager.set(K, (Object)new BlockPos(nBTTagCompound.getInteger("eyeColorX"), nBTTagCompound.getInteger("eyeColorY"), nBTTagCompound.getInteger("eyeColorZ")));
            this.DataManager.set(u, (Object)nBTTagCompound.getString("girlID"));
            this.DataManager.set(QueenNameKey, (Object)nBTTagCompound.getString("queen"));
            this.DataManager.set(TamedKey, (Object)nBTTagCompound.getBoolean("isTamed"));
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
        this.DataManager.set(PregnantKey, (Object)nBTTagCompound.getBoolean("preggo"));
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
                        this.c(entityPlayer.getPositionVector());
                        this.b(entityPlayer.rotationYaw);
                        this.b(GirlAnimationState.CATCH);
                        this.DataManager.set(h, (Object)"bj");
                        this.a(entityPlayer.getPersistentID());
                        this.e(entityPlayer.getPersistentID());
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
            this.b(GirlAnimationState.PICK_UP);
            this.aQ = 45;
            this.a(false);
            this.DataManager.set(TamedKey, (Object)true);
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
                UUID uUID2 = ((LastAmbientTicks)((Object)girl)).e();
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
        this.getGirlUuid();
        GoblinNpc.handlePickUp(this);
        this.m();
        this.B();
        this.J();
        this.E();
        this.t();
        this.getCustomName();
        this.b();
        this.d();
        this.isTracked();
        this.getTargetPos();
        this.u();
        this.isOwnedByLocalPlayer();
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
            if (this.e() != null) {
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

    void b(EntityPlayer entityPlayer) {
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
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        vec3d2(d2, d + (double)f, entityPlayer.posZ);
        Vec3d vec3d4 = vec3d;
        Vec3d vec3d5 = new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
        double d3 = vec3d5.distanceTo(vec3d4);
        double d4 = vec3d4.y - vec3d5.y;
        this.rotationPitch = (float)(-(Math.sin(d4 / d3) * 57.29577951308232));
    }

    void n() {
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
                                    this.b(entityPlayer);
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
                                                if (this.R == null || this.getDistance(this.R.getX(), this.R.getY(), this.R.getZ()) > this.advanceAnimationState()) break block26;
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
                    this.k();
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

    void u() {
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
        this.b(GirlAnimationState.NULL);
    }

    @Override
    public void a(int i) {
        this.aJ = i;
    }

    @Override
    public int d() {
        return this.aJ;
    }

    void o() {
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
        int i = this.d() + 1;
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
        this.b(GirlAnimationState.STAND_UP);
    }

    void h() {
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
                this.DataManager.set(PregnantKey, (Object)false);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    void d() {
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
                goblin2.b(GirlAnimationState.VANISH);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        this.NearbyGoblins.clear();
        this.e((UUID)null);
    }

    void b() {
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
        this.e((UUID)null);
        for (GoblinNpc goblin : this.NearbyGoblins) {
            goblin.e((UUID)null);
        }
        List<GoblinNpc> list = this.I();
        float f = this.ThroneRot + 180.0f;
        Vec3d vec3d = this.ThronePos.add(GoblinNpc.rotateVec3dYaw(PartnerPosA, f));
        Vec3d vec3d2 = this.ThronePos.add(GoblinNpc.rotateVec3dYaw(PartnerPosB, f));
        Vec3d vec3d3 = this.ThronePos.add(GoblinNpc.rotateVec3dYaw(PartnerPosC, f));
        GoblinNpc goblin2 = (GoblinNpc)list.get(0);
        GoblinNpc goblin3 = (GoblinNpc)list.get(1);
        goblin2.c(vec3d);
        goblin3.c(vec3d2);
        goblin2.b(0.0f);
        goblin3.b(0.0f);
        goblin2.a(true);
        goblin3.a(true);
        goblin2.b(GirlAnimationState.AWAIT_PICK_UP);
        goblin3.b(GirlAnimationState.AWAIT_PICK_UP);
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

    void t() {
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
        this.c(vec3d);
        this.b(this.ThroneRot);
        this.b(GirlAnimationState.BREEDING_INTRO_0);
        this.noClip = true;
        this.setNoGravity(true);
        Vec3d vec3d2 = GoblinNpc.rotateVec3dYaw(new Vec3d(0.0, 0.44375 - (double)entityPlayer.eyeHeight, -0.7875), this.ThroneRot - 180.0f);
        entityPlayer.noClip = true;
        entityPlayer.setNoGravity(true);
        entityPlayer.setPositionAndUpdate(vec3d2.x + vec3d.x, vec3d2.y + vec3d.y, vec3d2.z + vec3d.z);
        List<GoblinNpc> list = this.I();
        if (list.size() >= 1) {
            goblin = list.get(0);
            goblin.c(vec3d);
            goblin.b(this.ThroneRot);
            goblin.b(GirlAnimationState.BREEDING_INTRO_1);
            goblin.noClip = true;
            goblin.setNoGravity(true);
        }
        if (list.size() >= 2) {
            goblin = list.get(1);
            goblin.c(vec3d);
            goblin.b(this.ThroneRot);
            goblin.b(GirlAnimationState.BREEDING_INTRO_2);
            goblin.noClip = true;
            goblin.setNoGravity(true);
        }
        this.an = 0;
    }

    AxisAlignedBB a(Vec3d vec3d, Vec3d vec3d2) {
        return new AxisAlignedBB(vec3d.x, vec3d.y, vec3d.z, vec3d2.x, vec3d2.y, vec3d2.z);
    }

    void E() {
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
                AxisAlignedBB axisAlignedBB = this.a(vec3d2, vec3d2.add((double)SeatSearchBox.getX(), (double)SeatSearchBox.getY(), (double)SeatSearchBox.getZ()));
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
        this.e(uUID);
        this.b(GirlAnimationState.JUMP_0);
        this.c(vec3d);
        this.b(f);
        this.a(true);
        List<GoblinNpc> list = this.I();
        if (list.size() > 0) {
            GoblinNpc goblin = list.get(0);
            goblin.e(uUID);
            goblin.b(GirlAnimationState.JUMP_1);
            goblin.c(vec3d);
            goblin.b(f);
            goblin.a(true);
            if (list.size() > 1) {
                GoblinNpc goblin2 = list.get(1);
                goblin2.e(uUID);
                goblin2.b(GirlAnimationState.JUMP_2);
                goblin2.c(vec3d);
                goblin2.b(f);
                goblin2.a(true);
            }
        }
    }

    List<GoblinNpc> I() {
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
        GoblinNpc goblin3 = new GoblinNpc(this.world, this.getGirlUuid().toString(), this.k());
        goblin3.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)goblin3);
        this.NearbyGoblins.add(goblin3);
        goblin2 = new GoblinNpc(this.world, this.getGirlUuid().toString(), this.k());
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

    void e() {
        GoblinNpc goblin = this;
        int i = goblin.a();
        try {
            if (i == -1) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        goblin.getBoundPlayerYaw(++i);
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
                this.b(GirlAnimationState.THROWN);
                this.e((UUID)null);
                this.a((UUID)null);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    public static Vec3d getBoundPlayerPos(GirlEntity girl) {
        GirlMaster girlMaster = (LastAmbientTicks)((Object)girl);
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
        GirlMaster girlMaster = (LastAmbientTicks)((Object)girl);
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
        GirlMaster girlMaster = (LastAmbientTicks)((Object)girl);
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

    void J() {
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
            int i;
            Vec3d vec3d;
            Vec3d vec3d2;
            Vec3d vec3d3;
            if (d <= 0.0) {
                return;
            }
            Vec3d vec3d4 = this.getPositionVector().subtract(entityPlayer.getPositionVector());
            Vec3d vec3d5 = new Vec3d(Math.abs(vec3d4.x), Math.abs(vec3d4.y), Math.abs(vec3d4.z));
            double d2 = vec3d5.x / (vec3d5.x + vec3d5.z);
            double d3 = vec3d5.z / (vec3d5.x + vec3d5.z);
            try {
                Vec3d vec3d6;
                vec3d3 = this.getPositionVector();
                vec3d2 = vec3d6;
                vec3d = vec3d6;
                i = vec3d4.x > 0.0 ? 1 : -1;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
            vec3d2((double)i * d2 * d, 0.0, (double)(vec3d4.z > 0.0 ? 1 : -1) * d3 * d);
            Vec3d vec3d7 = vec3d3.add(vec3d);
            PathNavigate pathNavigate = this.getNavigator();
            pathNavigate.clearPath();
            flag = pathNavigate.tryMoveToXYZ(vec3d7.x, vec3d7.y, vec3d7.z, (double)0.825f);
            d -= 1.0;
        } while (!flag);
    }

    protected void jump() {
        block4: {
            try {
                try {
                    if (this.getCurrentAction() != GirlAnimationState.RUN || this.getRenderPosition()) break block4;
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

    void B() {
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
        Integer i = this.c(entityPlayer);
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
        GoblinNpc goblin = new GoblinNpc(this.world, this.getGirlUuid().toString(), this.k());
        Vec3d vec3d4 = GoblinNpc.rotateVec3dYaw(new Vec3d(0.0, 0.0, (double)-0.2f), entityPlayer.rotationYawHead);
        goblin.setPosition(entityPlayer.posX + vec3d4.x, entityPlayer.posY, entityPlayer.posZ + vec3d4.z);
        goblin.b(GirlAnimationState.RUN);
        this.world.spawnEntity((Entity)goblin);
        goblin.DataManager.set(HeldItemKey, (Object)itemStack);
        entityPlayer.sendMessage((ITextComponent)new TextComponentString(String.format("<%s> I got your %s hehe~", goblin.c(), itemStack.getDisplayName())));
        entityPlayer.inventory.removeStackFromSlot(i.intValue());
        this.aO = 0;
    }

    int k() {
        return Integer.parseInt(GoblinNpc.handlePickUp(this)[7]);
    }

    @Nullable
    Integer c(EntityPlayer entityPlayer) {
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

    void m() {
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
        this.c(this.ThronePos);
        this.b(this.ThroneRot);
        this.a(true);
        this.setNoGravity(true);
        this.b(GirlAnimationState.SIT);
    }

    @Override
    public void onUpdate() {
        block7: {
            try {
                this.getRenderLabelOffset();
                GoblinNpc.syncThrowState(this);
                this.e();
                if (this.e() != null) {
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
                    this.H();
                    this.F();
                    if (!this.world.isRemote) break block7;
                    this.getScale();
                    this.A();
                    if (this.e() == null) break block7;
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

    @Override
    public GirlAnimationState b() {
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

    @Override
    public int a() {
        return this.aR;
    }

    public static void syncThrowState(GirlEntity girl) {
        GirlMaster girlMaster;
        GirlAnimationState girlAnimationState;
        block4: {
            girlAnimationState = girl.getCurrentAction();
            girlMaster = (LastAmbientTicks)((Object)girl);
            try {
                try {
                    if (girlMaster.b() == GirlAnimationState.START_THROWING || girlAnimationState != GirlAnimationState.START_THROWING) break block4;
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
            if (this.e() == null) {
                super.setFire(i);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
    }

    void F() {
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

    void H() {
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
        this.b(GirlAnimationState.NULL);
        this.e((UUID)null);
        this.a((UUID)null);
        this.world.removeEntity((Entity)this);
    }

    @SideOnly(value=Side.CLIENT)
    void v() {
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
        this.b(GirlAnimationState.PAIZURI_START);
        Minecraft.getMinecraft().player.closeScreen();
    }

    @SideOnly(value=Side.CLIENT)
    void A() {
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
        this.b(GirlAnimationState.NELSON_INTRO);
        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.player.closeScreen();
        minecraft.gameSettings.thirdPersonView = 2;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
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
                                this.e(this.e());
                                this.L();
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
                            this.getSexPlayer();
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
                        this.getWalkState();
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
                    this.D();
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GoblinNpc.rethrow(concurrentModificationException);
                }
            }
            try {
                if (girlAnimationState == GirlAnimationState.BREEDING_CUM_0) {
                    this.DataManager.set(PregnantKey, (Object)true);
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
                    this.DataManager.set(PregnantKey, (Object)true);
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
                this.DataManager.set(PregnantKey, (Object)false);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GoblinNpc.rethrow(concurrentModificationException);
            }
        }
        super.b(girlAnimationState);
    }

    void D() {
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer != null) {
                PacketResetGirl.Handler.a((EntityPlayerMP)entityPlayer);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        try {
            this.e((UUID)null);
            this.a(false);
            this.noClip = false;
            this.setNoGravity(false);
            this.DataManager.set(HeldItemKey, (Object)ItemStack.EMPTY);
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
        this.c(entityPlayer.getPositionVector());
        this.b(entityPlayer.rotationYaw);
        this.a(true);
        this.noClip = true;
        this.setNoGravity(true);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        this.e(entityPlayer.getPersistentID());
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
        this.c(entityPlayer.getPositionVector());
        this.b(entityPlayer.rotationYaw + 180.0f);
        this.a(true);
        this.noClip = true;
        this.setNoGravity(true);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        this.e(entityPlayer.getPersistentID());
        entityPlayer.setPositionAndUpdate(entityPlayer.posX, entityPlayer.posY - 0.5, entityPlayer.posZ);
        entityPlayer.rotationPitch = 70.0f;
        entityPlayer.prevRotationPitch = 70.0f;
    }

    void L() {
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
        this.DataManager.set(HeldItemKey, (Object)ItemStack.EMPTY);
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
        GirlMaster girlMaster = (LastAmbientTicks)((Object)girl);
        UUID uUID = girlMaster.getGirlUuid();
        try {
            if (uUID == null) {
                girlMaster.b(-1);
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
                girlMaster.b(-1);
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
                girlMaster.b(-1);
                girl.setCurrentAction(GirlAnimationState.NULL);
                girlMaster.a((UUID)null);
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        int i = girlMaster.c() - 1;
        try {
            girlMaster.b(i);
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
                if (this.e() != null) {
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
            flag = this.e() == null;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        return flag;
    }

    void y() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.SHOULDER_IDLE) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        UUID uUID = this.e();
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

    /*
     * Exception decompiling
     */
    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[SWITCH]
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
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[SWITCH]
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

    public boolean C() {
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

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 19[SWITCH]
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
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GoblinNpc.rethrow(concurrentModificationException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 34[SWITCH]
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
        this.MovementController.transitionLengthTicks = 10.0;
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    private static Exception rethrow(Exception exception) {
        return exception;
    }

    public static class EventHandler {
        static Minecraft MinecraftInstance = null;

        /*
         * Exception decompiling
         */
        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
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
                        if (!uUID.equals(goblin2.e())) {
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
                    goblin.e((UUID)null);
                    goblin.b(GirlAnimationState.NULL);
                    GoblinNpc goblin3 = new GoblinNpc(world);
                    goblin3.dimension = i;
                    goblin3.forceSpawn = true;
                    goblin3.setCustomModel(string);
                    goblin3.e(string2);
                    goblin3.DataManager.set(aC, (Object)true);
                    world.spawnEntity((Entity)goblin3);
                    goblin3.setPositionAndUpdate(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
                    goblin3.a(uUID);
                    goblin3.b(GirlAnimationState.SHOULDER_IDLE);
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
                if (goblin.e() != null) {
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
                    GirlMaster girlMaster = (ai)((Object)girl2);
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

