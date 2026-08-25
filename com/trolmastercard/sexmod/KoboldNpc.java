/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  javax.annotation.Nullable
 *  javax.vecmath.Vector4d
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.BlockLog
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumFacing$Axis
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.items.IItemHandler
 *  net.minecraftforge.items.ItemStackHandler
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.vecmath.Vector4d;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.resource.GeckoLibCache;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class KoboldNpc
extends GirlEffectEntity
implements VoidCallback,
IInventory,
dr {
    public static final EyeAndKoboldColor DefaultTribeColor = EyeAndKoboldColor.PURPLE;
    public static final float Y = 0.25f;
    static final int ar = 20;
    static final int ag = 2;
    static final int aG = 30;
    static final int ah = 84;
    static final int i = 32;
    static final int i5 = 5;
    static final float ae = 1.5f;
    static final float aW = 20.0f;
    static final double au = 10.0;
    static final double ay = 2.0;
    static final double al = 3.0;
    static final int aQ = 300;
    static final int aq = 5;
    static final int aO = 100;
    static final int aB = 100;
    static final int ac = 2;
    static final float am = 2.0f;
    static final int aw = 300;
    static final float aj = 0.2f;
    static final double aH = 0.7;
    static final int aa = 142;
    public static final DataParameter<Float> BodySizeKey = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.FLOAT).getSerializer().createKey(122);
    public static final DataParameter<String> CustomNameKey = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(123);
    public static final DataParameter<Boolean> aC = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(124);
    public static final DataParameter<Boolean> aZ = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(125);
    public static final DataParameter<String> TribeNameKey = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(126);
    public static final DataParameter<Boolean> HasTribeKey = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(127);
    public static final DataParameter<Boolean> at = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(128);
    public static final DataParameter<Optional<UUID>> BoundPlayerUuidKey = EntityDataManager.createKey(KoboldNpc.class, (DataSerializer)DataSerializers.OPTIONAL_UNIQUE_ID).getSerializer().createKey(129);
    public static final int av = 24;
    public static double HealthScale = 69.0;
    public static List<Vector4d> MemberData = new ArrayList<Vector4d>();
    ItemStackHandler Inventory = new ItemStackHandler(27);
    public String as = null;
    boolean az = false;
    int aP = 0;
    int U = 0;
    boolean flag = false;
    int aD = 0;
    int i6 = 0;
    float S = Float.MAX_VALUE;
    static long aV = Long.MIN_VALUE;
    String[] IdleChatLines = new String[]{"What the fuck did you just fucking say about me, you little bitch? I'll have you know I graduated top of my class in the Navy Seals, and I've been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and I'm the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. You're fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and that's just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little \"clever\" comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldn't, you didn't, and now you're paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. You're fucking dead, kiddo.", "suck my iron cock you worthless piece of shit!", "you'll die a fucking virgin!", "not even Johnny sins would wanna stick his cock up ur ass", "fuck you with ur borderline illegal fetishes!", "ur cum tastes terrible!", "I've always faked my orgasms when having sex with you!", "Not even Jenny would fuck you for 6 diamonds!", "U look like u'd use a shovel to mine diamonds, fucking idiot!", "Why tf does ur cock smell like my asshole???", "do all of us a favor and hit [ALT]+[F4]!", "I'm about to say the N word!", "you are under attack retard", "Eat my ass!", "my tongue is longer than ur fucking dick bitch!", "Ligma titties!", "touch some grass bitch!"};
    IBlockState R = null;
    IBlockState aX = null;
    BlockPos aF = null;
    boolean ao = true;
    Vec3d aS = Vec3d.ZERO;
    BlockPos aM = null;
    BlockPos aI = null;
    int ChatDelayTicks = 0;
    int Z = 0;
    int aK = 0;
    int i7 = 0;
    boolean ax = false;
    BlockPos WanderTargetPos = null;
    int ab = 0;
    int aR = 24;
    int W = 0;
    ItemStack CarriedItem = null;
    public boolean aA = false;
    int V = -1;
    boolean flag2 = true;
    boolean aT = false;
    public boolean Q = false;
    int aN = 0;

    public KoboldNpc(World world) {
        super(world);
        this.setSize(0.5f, 0.99f);
    }

    KoboldNpc(World world, UUID uUID, float f) {
        this(world);
        this.DataManager.set(BoundPlayerUuidKey, (Object)Optional.of((Object)uUID));
        this.DataManager.set(BodySizeKey, (Object)Float.valueOf(f));
    }

    public static KoboldNpc create(World world, UUID uUID) {
        float f = KoboldNpc.getRandomBodySize();
        return KoboldNpc.create(world, uUID, f);
    }

    public static KoboldNpc create(World world, UUID uUID, float f) {
        HealthScale = 10.0 - (double)f * 25.0;
        return new KoboldNpc(world, uUID, f);
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        KoboldNpc.appendRandomBelow(stringBuilder, 8);
        KoboldNpc.appendRandomBelow(stringBuilder, 3);
        KoboldNpc.appendRandomGauss(stringBuilder);
        KoboldNpc.appendRandomGauss(stringBuilder);
        KoboldNpc.appendRandomNumber(stringBuilder, 2);
        KoboldNpc.appendRandomNumber(stringBuilder, 2);
        KoboldNpc.appendRandomNumber(stringBuilder, 1);
        KoboldNpc.appendRandomNumber(stringBuilder, 1);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> D() {
        return new ArrayList<Integer>(){
            {
                this.add(101);
                this.add(EyeAndKoboldColor.values().length);
                this.add(EyeAndKoboldColor.values().length);
                this.add(8);
                this.add(3);
                this.add(101);
                this.add(101);
                this.add(3);
                this.add(3);
                this.add(4);
                this.add(2);
            }
        };
    }

    @Override
    public ArrayList<Integer> L() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(Math.round(((Float)this.DataManager.get(BodySizeKey)).floatValue() * 100.0f / 0.25f));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((String)this.DataManager.get(BodyColorKey))));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((Vec3i)this.DataManager.get(EyeColorKey))));
        return arrayList;
    }

    @Override
    public void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        block5: for (int i = 0; i < list.size(); ++i) {
            int i2 = list.get(i);
            switch (i) {
                case 0: {
                    this.DataManager.set(BodySizeKey, (Object)Float.valueOf((float)i2 / 100.0f * 0.25f));
                    continue block5;
                }
                case 1: {
                    String string = (String)this.DataManager.get(BodyColorKey);
                    String string2 = EyeAndKoboldColor.values()[i2].toString();
                    if (!string2.equals(string)) {
                        this.aA = true;
                    }
                    this.DataManager.set(BodyColorKey, (Object)string2);
                    continue block5;
                }
                case 2: {
                    this.DataManager.set(EyeColorKey, (Object)new BlockPos(EyeAndKoboldColor.values()[i2].getMainColor()));
                    continue block5;
                }
                default: {
                    GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, i2);
                }
            }
        }
        this.DataManager.set(M, (Object)stringBuilder.toString());
        KoboldNpcRenderer.clearColorCache();
    }

    void void_m() {
        if (this.OutfitData == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        block5: for (int i = 0; i < this.OutfitData.size(); ++i) {
            Map.Entry entry = (Map.Entry)this.OutfitData.get(i);
            int i2 = (Integer)((Map.Entry)entry.getValue()).getValue();
            switch (i) {
                case 0: {
                    this.DataManager.set(BodySizeKey, (Object)Float.valueOf((float)i2 / 100.0f * 0.25f));
                    continue block5;
                }
                case 1: {
                    this.DataManager.set(BodyColorKey, (Object)EyeAndKoboldColor.values()[i2].toString());
                    continue block5;
                }
                case 2: {
                    this.DataManager.set(EyeColorKey, (Object)new BlockPos(EyeAndKoboldColor.values()[i2].getMainColor()));
                    continue block5;
                }
                default: {
                    GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, i2);
                }
            }
        }
        this.DataManager.set(M, (Object)stringBuilder.toString());
        KoboldNpcRenderer.clearColorCache();
    }

    @Override
    public Vec2i g(int i) {
        switch (i) {
            case 0: {
                return new Vec2i(160, 0);
            }
            case 1: {
                return new Vec2i(180, 0);
            }
            case 2: {
                return new Vec2i(200, 0);
            }
            case 3: {
                return new Vec2i(220, 0);
            }
            case 4: {
                return new Vec2i(227, 20);
            }
            case 5: {
                return new Vec2i(140, 40);
            }
            case 6: {
                return new Vec2i(160, 40);
            }
            case 7: {
                return new Vec2i(180, 40);
            }
            case 8: {
                return new Vec2i(227, 40);
            }
            case 9: {
                return new Vec2i(0, 130);
            }
            case 10: {
                return new Vec2i(20, 130);
            }
        }
        return Vec2i.ZERO;
    }

    @Override
    public String getDisplayName() {
        return (String)this.DataManager.get(CustomNameKey);
    }

    @Override
    public float getScaleOffset() {
        return 0.2f - (0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue());
    }

    public float getEyeHeight() {
        return 0.94f;
    }

    public static float getRandomBodySize() {
        return (float)(Math.random() * 0.25);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.values()[this.getRNG().nextInt(EyeAndKoboldColor.values().length)];
        this.DataManager.register(EyeColorKey, (Object)new BlockPos(eyeAndKoboldColor.getMainColor()));
        this.DataManager.register(BodyColorKey, (Object)DefaultTribeColor.name());
        this.DataManager.register(BoundPlayerUuidKey, (Object)Optional.absent());
        this.DataManager.register(BodySizeKey, (Object)Float.valueOf(0.0f));
        this.DataManager.register(CustomNameKey, (Object)SexAnimation.values()[this.getRNG().nextInt(SexAnimation.values().length)].toString());
        this.DataManager.register(aC, (Object)false);
        this.DataManager.register(aZ, (Object)false);
        this.DataManager.register(TribeNameKey, (Object)"null");
        this.DataManager.register(HasTribeKey, (Object)false);
        this.DataManager.register(at, (Object)false);
    }

    @Override
    protected void initEntityAI() {
        this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.4, false, new HashSet(I)));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this));
        this.tasks.addTask(5, (EntityAIBase)this.WatchPlayerAI);
    }

    protected float getJumpUpwardsMotion() {
        return 0.45f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(HealthScale);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0);
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    protected boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        block45: {
            block43: {
                block44: {
                    block42: {
                        ItemStack itemStack;
                        block40: {
                            block41: {
                                block39: {
                                    try {
                                        if (this.getSexPlayerUuid() != null) {
                                            return false;
                                        }
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                    ItemStack itemStack2 = entityPlayer.getHeldItem(EnumHand.MAIN_HAND);
                                    if (!itemStack2.getItem().equals(Items.NAME_TAG)) {
                                        itemStack2 = entityPlayer.getHeldItem(EnumHand.OFF_HAND);
                                    }
                                    try {
                                        try {
                                            if (!itemStack2.getItem().equals(Items.NAME_TAG) || !entityPlayer.getPersistentID().toString().equals(this.DataManager.get(v))) break block39;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw KoboldNpc.rethrow(illegalArgumentException);
                                        }
                                        this.DataManager.set(CustomNameKey, (Object)itemStack2.getDisplayName());
                                        itemStack2.shrink(1);
                                        return true;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                }
                                try {
                                    if (((Boolean)this.DataManager.get(aC)).booleanValue()) {
                                        return false;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                try {
                                    if (this.getCurrentAction() == GirlAnimationState.SLEEP) {
                                        return false;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                itemStack = entityPlayer.getHeldItem(EnumHand.MAIN_HAND);
                                if (itemStack.getItem() != ItemDragonStaff.Instance) {
                                    itemStack = entityPlayer.getHeldItem(EnumHand.OFF_HAND);
                                }
                                try {
                                    try {
                                        try {
                                            if (this.J() || itemStack.getItem() != ItemDragonStaff.Instance) break block40;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw KoboldNpc.rethrow(illegalArgumentException);
                                        }
                                        if (this.world.isRemote) break block41;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                    return true;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                            }
                            Optional optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
                            try {
                                if (!optional.isPresent()) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            try {
                                if (!MemberData.isEmpty()) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            this.m((UUID)optional.get());
                            return true;
                        }
                        try {
                            try {
                                try {
                                    if (!this.J() || itemStack.getItem() != ItemDragonStaff.Instance) break block42;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                if (!((String)this.DataManager.get(v)).equals(entityPlayer.getPersistentID().toString())) break block42;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            entityPlayer.openGui((Object)Main.instance, 1, this.world, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
                            return true;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (!this.world.isRemote) break block43;
                                if (!this.J()) break block44;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            if (!((String)this.DataManager.get(v)).equals(entityPlayer.getPersistentID().toString())) break block44;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        this.a(ModSounds.GIRLS_KOBOLD_MASTER);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                this.canInteract(entityPlayer);
                break block45;
            }
            this.handleGirlUuidEvent(entityPlayer.getPersistentID());
            this.getNavigator().clearPath();
            this.b((float)(Math.atan2(this.posZ - entityPlayer.posZ, this.posX - entityPlayer.posX) * 57.29577951308232 + 90.0));
            this.setTargetPos(new Vec3d(this.posX, Math.floor(this.posY), this.posZ));
            this.DataManager.set(G, (Object)true);
            this.setCurrentAction(GirlAnimationState.NULL);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    void m(UUID uUID) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiNameTribe(uUID));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean canInteract(EntityPlayer entityPlayer) {
        block7: {
            try {
                try {
                    if (!this.J() || !entityPlayer.getPersistentID().toString().equals(this.DataManager.get(v))) break block7;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlCommandMenu(this, entityPlayer, new String[]{"anal", "oral", "mating"}, null, false));
                return true;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        try {
            if (this.getActivePotionEffect(PotionHandler.b) != null) {
                Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlCommandMenu(this, entityPlayer, new String[]{"anal", "oral"}, null, false));
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlCommandMenu(this, entityPlayer, new String[]{"anal", "oral"}, new ItemStack[]{new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(Items.IRON_PICKAXE)}, false));
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ac() {
        try {
            if (this.az) {
                this.az = false;
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        this.handleGirlUuidEvent((UUID)null);
        this.a("shouldbeattargetpos", "false");
    }

    @Override
    public void r() {
        this.Q = false;
        super.resetAimTarget();
    }

    protected void a(boolean flag, UUID uUID) {
        super.a(flag, true, uUID);
        AnimationInputLock.setAnimationLocked(false);
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            this.az = true;
            if ("oral".equals(string)) {
                this.a("animationFollowUp", GirlAnimationState.STARTBLOWJOB.toString());
                this.a(true, uUID);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if ("anal".equals(string)) {
                this.a("animationFollowUp", GirlAnimationState.KOBOLD_ANAL_START.toString());
                this.a(true, uUID);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if ("mating".equals(string)) {
                this.a("animationFollowUp", GirlAnimationState.MATING_PRESS_START.toString());
                this.a(true, uUID);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    @Override
    public void void_b() {
        this.a2 = true;
        this.DataManager.set(G, (Object)false);
    }

    @Override
    protected void void_a() {
        KoboldNpcRenderer.clearColorCache();
    }

    boolean g() {
        try {
            if (!this.a2) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        ++this.aD;
        this.noClip = false;
        this.setNoGravity(false);
        if (this.aD > 40) {
            this.a2 = false;
            this.aD = 0;
            EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
            this.b(entityPlayer.rotationYaw + 180.0f);
            this.DataManager.set(G, (Object)true);
            entityPlayer.noClip = true;
            entityPlayer.setNoGravity(true);
            this.noClip = true;
            this.setNoGravity(true);
            this.getNavigator().clearPath();
            this.U();
            return true;
        }
        this.rotationYaw = this.I().floatValue();
        this.setNoGravity(false);
        Vec3d vec3d = LerpMath.stepTowards(this.getPositionVector(), this.getTargetPos(), 40 - this.aD);
        this.setPosition(vec3d.x, vec3d.y, vec3d.z);
        this.setCurrentAction(GirlAnimationState.NULL);
        Optional optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
        try {
            if (!optional.isPresent()) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        Collection<TreeCluster> collection = GirlHomeBuilder.getTreeClusters((UUID)optional.get());
        try {
            if (collection == null) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        for (TreeCluster treeCluster : collection) {
            treeCluster.unassignKobold(this);
        }
        return true;
    }

    void o(UUID uUID) {
        try {
            if (this.V == -1) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (++this.V < 132) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            this.V = -1;
            if (this.getCurrentAction() != GirlAnimationState.MATING_PRESS_CUM) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        UUID uUID2 = this.getSexPlayerUuid();
        try {
            if (uUID2 == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID2);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        EyeAndKoboldColor eyeAndKoboldColor = GirlHomeBuilder.getTribeColor(uUID);
        ItemStack itemStack = new ItemStack((Item)ItemKoboldEgg.Instance, 1, eyeAndKoboldColor.getWoolMeta());
        NBTTagCompound nBTTagCompound = itemStack.getTagCompound();
        if (nBTTagCompound == null) {
            nBTTagCompound = new NBTTagCompound();
        }
        nBTTagCompound.setString("tribeID", uUID.toString());
        nBTTagCompound.setString("tribeColor", eyeAndKoboldColor.toString());
        itemStack.setTagCompound(nBTTagCompound);
        entityPlayer.inventory.addItemStackToInventory(itemStack);
    }

    @Override
    public void updateAITasks() {
        Optional optional;
        block39: {
            Object object;
            block40: {
                block38: {
                    block37: {
                        super.updateAITasks();
                        this.ax = false;
                        optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
                        if (optional.isPresent()) {
                            this.getTargetPos((UUID)optional.get());
                            GirlHomeBuilder.updateKoboldEntity((UUID)optional.get());
                            object = this.getSexPlayer();
                            try {
                                if (object != null) {
                                    GirlHomeBuilder.setOwnerUuid((UUID)optional.get(), object.getPersistentID());
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                        }
                        try {
                            if (this.noop()) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        try {
                            if (this.getSexPlayerUuid() != null) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        try {
                            try {
                                try {
                                    if (((Boolean)this.DataManager.get(aC)).booleanValue()) break block37;
                                    if (this.getHealth() == this.getMaxHealth()) break block38;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                if (++this.a5 < 100) break block38;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            this.setHealth(this.getHealth() + 2.0f);
                            this.a5 = 0;
                            NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnParticle(this.getGirlUuid(), EnumParticleTypes.HEART.getParticleName()), (Entity)this);
                            break block38;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    this.a5 = 0;
                }
                try {
                    if (!((Boolean)this.DataManager.get(G)).booleanValue()) {
                        this.setNoGravity(false);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                try {
                    if (!optional.isPresent()) {
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                try {
                    try {
                        --this.aP;
                        if (this.getCurrentAction() != GirlAnimationState.ATTACK) break block39;
                        this.getNavigator().clearPath();
                        this.rotationYaw = this.I().floatValue();
                        this.rotationYawHead = this.I().floatValue();
                        ++this.U;
                        if (22 != this.U) break block40;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    this.u();
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            if (32 == this.U) {
                object = GirlHomeBuilder.getNearbyEntities((UUID)optional.get());
                HashSet<EntityLivingBase> hashSet = new HashSet<EntityLivingBase>();
                Iterator iterator = ((HashSet)object).iterator();
                while (iterator.hasNext()) {
                    EntityLivingBase entityLivingBase = (EntityLivingBase)iterator.next();
                    try {
                        if (entityLivingBase.getDistance((Entity)this) > 2.0f) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        entityLivingBase.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 5.0f);
                        if (!entityLivingBase.isDead) continue;
                        hashSet.add(entityLivingBase);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                for (EntityLivingBase entityLivingBase : hashSet) {
                    GirlHomeBuilder.removeNearbyEntity((UUID)optional.get(), entityLivingBase);
                }
            }
            try {
                if (84 <= this.U) {
                    this.setCurrentAction(GirlAnimationState.NULL);
                    this.DataManager.set(G, (Object)false);
                    this.U = 0;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            return;
        }
        this.DataManager.set(aC, (Object)this.getDisplayName((UUID)optional.get(), false));
        this.DataManager.set(aZ, (Object)GirlHomeBuilder.isKoboldOfGirl((UUID)optional.get(), this));
        this.DataManager.set(HasTribeKey, (Object)GirlHomeBuilder.hasTribe((UUID)optional.get()));
        this.d();
        this.isTracked();
        this.WatchPlayerAI.Active = this.getTargetPos();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.t();
        this.getScale();
        this.getWalkState();
        this.getCustomName();
        this.m();
    }

    void w() {
        float f;
        block28: {
            try {
                if (!this.world.isRemote) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (this.world.getTotalWorldTime() - 300L < aV) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (!this.J()) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (!"".equals(this.DataManager.get(h))) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (((Boolean)this.DataManager.get(HasTribeKey)).booleanValue()) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            String string = (String)this.DataManager.get(v);
            EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 10.0);
            try {
                if (entityPlayer == null) {
                    this.S = Float.MAX_VALUE;
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (!entityPlayer.getPersistentID().toString().equals(string)) {
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            f = this.getDistance((Entity)entityPlayer);
            try {
                try {
                    if (!(f < 2.0f) || !(this.S > 2.0f)) break block28;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                this.b(ModSounds.pickRandomSound(ModSounds.GIRLS_KOBOLD_HEYMASTER));
                this.a("Hey master!");
                aV = this.world.getTotalWorldTime();
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        this.S = f;
    }

    void q() {
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.getCurrentAction() == GirlAnimationState.SLEEP) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (!((Boolean)this.DataManager.get(HasTribeKey)).booleanValue()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (!this.J()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(UUID.fromString((String)this.DataManager.get(v)));
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        this.canInteract(entityPlayer);
    }

    void void_t() {
        try {
            if (((Boolean)this.DataManager.get(aC)).booleanValue()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.J()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        Optional optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
        try {
            if (!optional.isPresent()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        for (EntityPlayer entityPlayer : this.world.playerEntities) {
            block24: {
                PathNavigate pathNavigate;
                double d;
                block23: {
                    block22: {
                        double d2 = d = entityPlayer.getPositionVector().distanceTo(this.getPositionVector());
                        if (!this.world.isRemote) {
                            for (KoboldNpc kobold : GirlHomeBuilder.getKobolds((UUID)optional.get())) {
                                double d3 = entityPlayer.getPositionVector().distanceTo(kobold.getPositionVector());
                                if (!(d3 < d2)) continue;
                                d2 = d3;
                            }
                        }
                        try {
                            if (d2 > 10.0) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        try {
                            try {
                                if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemDragonStaff.Instance || entityPlayer.getHeldItem(EnumHand.OFF_HAND).getItem() == ItemDragonStaff.Instance) break block22;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            return;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    pathNavigate = this.getNavigator();
                    try {
                        pathNavigate.clearPath();
                        if (!this.world.isRemote) break block23;
                        this.canInteract(entityPlayer);
                        break block24;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                if (d > 2.0) {
                    KoboldNpc kobold2;
                    kobold2 = this.getDisplayName(entityPlayer.getPosition());
                    pathNavigate.tryMoveToXYZ((double)kobold2.getX(), (double)kobold2.getY(), (double)kobold2.getZ(), (double)0.35f);
                }
            }
            return;
        }
    }

    @Override
    protected void U() {
        String string;
        block27: {
            block29: {
                boolean flag;
                block24: {
                    block26: {
                        boolean flag2;
                        block23: {
                            block22: {
                                boolean flag3;
                                string = (String)this.DataManager.get(GirlEntity.BlowjobStageKey);
                                try {
                                    flag3 = this.getActivePotionEffect(PotionHandler.b) != null;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                boolean flag4 = flag3;
                                boolean flag5 = false;
                                if (this.J()) {
                                    flag5 = ((String)this.DataManager.get(v)).equals(this.getSexPlayerUuid().toString());
                                }
                                try {
                                    try {
                                        if (flag4 || flag5) break block22;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                    flag2 = true;
                                    break block23;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                            }
                            flag2 = false;
                        }
                        flag = flag2;
                        try {
                            block25: {
                                try {
                                    try {
                                        if (!string.equals(GirlAnimationState.STARTBLOWJOB.toString())) break block24;
                                        if (!flag) break block25;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                    if (this.getCurrentAction() != GirlAnimationState.PAYMENT) break block26;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                            }
                            this.setCurrentAction(GirlAnimationState.STARTBLOWJOB);
                            break block24;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    this.setCurrentAction(GirlAnimationState.PAYMENT);
                }
                try {
                    block28: {
                        try {
                            try {
                                if (!string.equals(GirlAnimationState.KOBOLD_ANAL_START.toString())) break block27;
                                if (!flag) break block28;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            if (this.getCurrentAction() != GirlAnimationState.PAYMENT) break block29;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    this.setCurrentAction(GirlAnimationState.KOBOLD_ANAL_START);
                    break block27;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            this.setCurrentAction(GirlAnimationState.PAYMENT);
        }
        try {
            if (string.equals(GirlAnimationState.MATING_PRESS_START.toString())) {
                this.setCurrentAction(GirlAnimationState.MATING_PRESS_START);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    void v() {
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (!((Boolean)this.DataManager.get(G)).booleanValue()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.NULL) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        this.canInteract(entityPlayer);
    }

    void void_b(EntityPlayer entityPlayer) {
        float f;
        double d;
        double d2;
        double d3;
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
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        vec3d2(d2, d + (double)f, entityPlayer.posZ);
        Vec3d vec3d4 = vec3d;
        Vec3d vec3d5 = new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
        double d3 = vec3d5.distanceTo(vec3d4);
        double d4 = vec3d4.y - vec3d5.y;
        this.rotationPitch = (float)(-(Math.sin(d4 / d3) * 57.29577951308232));
    }

    void u_() {
    }

    boolean boolean_o() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.NULL) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (Math.abs(this.motionX) + Math.abs(this.motionZ) > 0.01) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.a()) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    void void_d() {
        UUID uUID;
        block23: {
            block21: {
                block22: {
                    Optional optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
                    try {
                        if (!optional.isPresent()) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    uUID = (UUID)optional.get();
                    try {
                        try {
                            try {
                                if (((Boolean)this.DataManager.get(aC)).booleanValue() || !GirlHomeBuilder.hasTribe(uUID)) break block21;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            if (this.J()) break block22;
                            return;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                EntityPlayer entityPlayer = this.getSexPlayer();
                try {
                    if (entityPlayer == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                for (TreeCluster treeCluster : GirlHomeBuilder.getTreeClusters(uUID)) {
                    try {
                        if (!treeCluster.isAssigned(this)) continue;
                        treeCluster.unassignKobold(this);
                        this.setCurrentAction(GirlAnimationState.NULL);
                        this.DataManager.set(G, (Object)false);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                this.noClip = false;
                this.setNoGravity(false);
                PathNavigate pathNavigate = this.getNavigator();
                double d = this.getPositionVector().distanceTo(entityPlayer.getPositionVector());
                try {
                    try {
                        if (!(d > 2.0)) return;
                        pathNavigate.tryMoveToEntityLiving((Entity)entityPlayer, this.a(entityPlayer, d));
                        this.k();
                        if (!(d > 15.0)) return;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    this.void_c(entityPlayer);
                    return;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            try {
                if (!GirlHomeBuilder.isKoboldOfGirl(uUID, this)) break block23;
                this.void_b(uUID);
                return;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        this.isOwnedByLocalPlayer(uUID);
    }

    protected double a(EntityPlayer entityPlayer, double d) {
        double d2 = entityPlayer.isSprinting() ? 0.7 : 0.35;
        double d3 = Math.floor(d / 5.0) * 0.3;
        d2 += d3;
        if (this.isInWater()) {
            d2 *= 60.0;
        }
        return d2;
    }

    void s(UUID uUID) {
        BlockPos blockPos = GirlHomeBuilder.getHomePos(uUID);
        try {
            if (blockPos == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.aX != null) {
                this.world.setBlockState(blockPos, this.aX);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.R != null) {
                this.world.setBlockState(blockPos.add(0, -1, 0), this.R);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    void void_b(UUID uuid) {
        ActivityState activityState;
        if (this.d_(uuid)) {
            return;
        }
        if (!this.J() && GirlHomeBuilder.hasKobold(uuid)) {
            this.getNavigator().clearPath();
            this.aM = null;
            return;
        }
        ActivityState activityState2 = GirlHomeBuilder.getActivityState(uuid);
        if (activityState2 != (activityState = this.p())) {
            GirlHomeBuilder.a(uuid, activityState);
            switch (activityState) {
                case REST: {
                    this.p(uuid);
                    GirlHomeBuilder.setHomePos(uuid, (BlockPos)null);
                    this.h("okay resting time owo");
                    break;
                }
                case ACTIVE: {
                    this.s(uuid);
                    this.q(uuid);
                }
            }
        }
        switch (activityState) {
            case ACTIVE: {
                this.aF = null;
                this.void_c(uuid);
                break;
            }
            case REST: {
                this.l(uuid);
            }
        }
    }

    void p(UUID uUID) {
        Collection<TreeCluster> collection = GirlHomeBuilder.getTreeClusters(uUID);
        try {
            if (collection == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        for (TreeCluster treeCluster : collection) {
            treeCluster.clearAssignees();
        }
    }

    void q(UUID uUID) {
        try {
            if (!this.J()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        List<KoboldNpc> list = GirlHomeBuilder.getKobolds(uUID);
        for (KoboldNpc kobold : list) {
            try {
                GirlHomeBuilder.clearBedHomePosition(kobold);
                if (kobold.getSexPlayerUuid() != null) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            kobold.noClip = false;
            kobold.setNoGravity(false);
            kobold.getDataManager().set(G, (Object)false);
            kobold.setCurrentAction(GirlAnimationState.NULL);
        }
    }

    void l(UUID uUID) {
        block5: {
            block4: {
                Collection<TreeCluster> collection = GirlHomeBuilder.getTreeClusters(uUID);
                if (collection != null) {
                    for (TreeCluster treeCluster : collection) {
                        treeCluster.unassignKobold(this);
                    }
                }
                try {
                    if (!this.J()) break block4;
                    this.getScaleOffset(uUID);
                    break block5;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            this.void_a(uUID);
        }
    }

    void getScaleOffset(UUID uUID) {
        BlockPos blockPos;
        block27: {
            block29: {
                block28: {
                    BlockPos[] blockPosArray = GirlHomeBuilder.getBedHomePositions(this);
                    if (blockPosArray != null) {
                        float f;
                        KoboldNpc kobold;
                        boolean flag;
                        Vec3d vec3d = new Vec3d((double)((float)blockPosArray[0].getX() + 0.5f), (double)blockPosArray[0].getY() + 0.5625, (double)((float)blockPosArray[0].getZ() + 0.5f));
                        Vec3d vec3d2 = new Vec3d((double)((float)blockPosArray[1].getX() + 0.5f), (double)blockPosArray[1].getY() + 0.5625, (double)((float)blockPosArray[1].getZ() + 0.5f));
                        try {
                            flag = vec3d.subtract((Vec3d)vec3d2).x == 0.0;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        boolean flag2 = flag;
                        Vec3d vec3d3 = LerpMath.lerpVec3d(vec3d, vec3d2, 0.5);
                        try {
                            this.DataManager.set(G, (Object)true);
                            this.setTargetPos(vec3d3);
                            kobold = this;
                            f = flag2 ? 0.0f : 90.0f;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        kobold.b(f);
                        this.noClip = true;
                        this.setNoGravity(true);
                        return;
                    }
                    HashSet<BlockPos> hashSet = GirlHomeBuilder.getSpawnPositions(uUID);
                    blockPos = null;
                    try {
                        if (hashSet == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    for (BlockPos blockPos2 : hashSet) {
                        IBlockState iBlockState = this.world.getBlockState(blockPos2);
                        boolean flag3 = false;
                        for (Map.Entry entry : iBlockState.getProperties().entrySet()) {
                            if (!(entry.getKey() instanceof PropertyBool)) continue;
                            flag3 = (Boolean)entry.getValue();
                            break;
                        }
                        try {
                            if (flag3) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        try {
                            if (GirlHomeBuilder.isBedBlock(blockPos2)) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        if (blockPos == null) {
                            blockPos = blockPos2;
                            continue;
                        }
                        if (!(this.getDistanceSq(blockPos) > this.getDistanceSq(blockPos2))) continue;
                        blockPos = blockPos2;
                    }
                    try {
                        if (blockPos == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        try {
                            if (!(blockPos.getDistance((int)this.posX, (int)this.posY, (int)this.posZ) > 2.0)) break block27;
                            if (Math.abs(blockPos.subtract((Vec3i)this.getPosition()).getY()) <= 4) break block28;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        this.appendRandomGauss(blockPos.add(0, 1, 0));
                        break block29;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                BlockPos blockPos3 = this.c(blockPos);
                try {
                    this.getNavigator().tryMoveToXYZ((double)blockPos3.getX(), (double)blockPos3.getY(), (double)blockPos3.getZ(), (double)0.35f);
                    if (this.getNavigator().getPath() == null) {
                        this.appendRandomGauss(blockPos.add(0, 1, 0));
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            return;
        }
        GirlHomeBuilder.setBedHomePosition(this, blockPos);
        this.setCurrentAction(GirlAnimationState.SLEEP);
    }

    void void_a(UUID uUID) {
        block12: {
            int i;
            int i2;
            int i3;
            int i4;
            BlockPos blockPos;
            block14: {
                block13: {
                    BlockPos blockPos2;
                    block11: {
                        blockPos2 = GirlHomeBuilder.getHomePos(uUID);
                        try {
                            if (blockPos2 != null || !GirlHomeBuilder.isKoboldOfGirl(uUID, this)) break block11;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        BlockPos blockPos3 = this.getPosition().add(1, 0, 0);
                        this.R = this.world.getBlockState(blockPos3.add(0, -1, 0));
                        this.aX = this.world.getBlockState(blockPos3);
                        this.world.setBlockState(blockPos3.add(0, -1, 0), Blocks.NETHERRACK.getDefaultState());
                        this.world.setBlockState(blockPos3, CustomFireBlock.a.getDefaultState());
                        GirlHomeBuilder.setHomePos(uUID, blockPos3);
                    }
                    try {
                        if (blockPos2 == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        try {
                            if (this.aF != null) break block12;
                            KoboldNpc kobold = this;
                            blockPos = blockPos2;
                            if (!this.getRNG().nextBoolean()) break block13;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        i4 = 1;
                        break block14;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                i4 = -1;
            }
            try {
                i3 = i4 * (this.getRNG().nextInt(2) + 1);
                i2 = 0;
                i = this.getRNG().nextBoolean() ? 1 : -1;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            kobold.aF = blockPos.add(i3, i2, i * (this.getRNG().nextInt(2) + 1));
        }
        this.getNavigator().tryMoveToXYZ((double)this.aF.getX(), (double)this.aF.getY(), (double)this.aF.getZ(), (double)0.35f);
        this.k();
    }

    void void_c(UUID uUID) {
        block9: {
            Collection<TreeCluster> collection;
            block8: {
                try {
                    if (this.J()) {
                        GirlHomeBuilder.b(uUID, null);
                        this.getGirlsByOwner(uUID);
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                collection = GirlHomeBuilder.getTreeClusters(uUID);
                try {
                    if (collection == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                try {
                    if (!this.ao) break block8;
                    this.aM = null;
                    this.b(uUID, collection);
                    break block9;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            this.a(uUID, collection);
        }
    }

    void b(UUID uUID, Collection<TreeCluster> collection) {
        try {
            if (collection.isEmpty()) {
                this.ao = false;
                this.resetAimTarget(uUID);
                this.h("Lets go somewhere else");
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    void a(UUID uUID, Collection<TreeCluster> collection) {
        BlockPos blockPos;
        block19: {
            block17: {
                block18: {
                    blockPos = GirlHomeBuilder.getHomePos(uUID);
                    try {
                        if (blockPos == null) {
                            this.resetAimTarget(uUID);
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        try {
                            if (this.ticksExisted % 40 != 0) break block17;
                            if (!this.aS.equals((Object)this.getPositionVector())) break block18;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        this.resetAimTarget(uUID);
                        this.aM = null;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                this.aS = this.getPositionVector();
            }
            try {
                try {
                    if (this.aM != null && !(this.aM.getDistance((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0)) break block19;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                this.aM = this.t(uUID);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        try {
            this.getNavigator().tryMoveToXYZ((double)this.aM.getX(), (double)this.aM.getY(), (double)this.aM.getZ(), (double)0.35f);
            this.k();
            if (Math.sqrt(this.getPosition().distanceSq((Vec3i)blockPos)) > 5.0) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        this.ao = true;
        this.h("Time to work bitches!");
        int i = GirlHomeBuilder.getKoboldCount(uUID);
        try {
            for (int i3 = 1; i3 < i; ++i3) {
                this.getDisplayName(uUID, collection);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        GirlHomeBuilder.b(uUID, null);
    }

    protected void void_c(EntityPlayer entityPlayer) {
        BlockPos blockPos;
        int i = 0;
        do {
            blockPos = entityPlayer.getPosition().add(ModConstants.Random.nextInt(10), 0, ModConstants.Random.nextInt(10));
        } while (++i < 20 && !this.attemptTeleport(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
        try {
            if (i == 20) {
                this.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
    }

    BlockPos t(UUID uUID) {
        BlockPos blockPos = GirlHomeBuilder.getHomePos(uUID);
        try {
            if (blockPos == null) {
                return BlockPos.ORIGIN;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return this.c(blockPos);
    }

    BlockPos c(BlockPos blockPos) {
        double d;
        double d2;
        int i;
        double d3;
        double d4;
        int i2;
        BlockPos blockPos2 = this.getPosition();
        BlockPos blockPos3 = blockPos.subtract((Vec3i)blockPos2);
        try {
            if (Math.abs(blockPos3.getX()) + Math.abs(blockPos3.getZ()) < 20) {
                return blockPos;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        double d5 = Math.min(Math.abs(blockPos3.getX()), Math.abs(blockPos3.getZ()));
        double d6 = Math.max(Math.abs(blockPos3.getX()), Math.abs(blockPos3.getZ()));
        double d7 = d5 / (d6 + d5);
        try {
            i2 = blockPos3.getX() > 0 ? 1 : -1;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            d4 = i2 * 20;
            d3 = d5 == (double)Math.abs(blockPos3.getX()) ? d7 : 1.0 - d7;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        int i3 = (int)(d4 * d3);
        try {
            i = blockPos3.getZ() > 0 ? 1 : -1;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            d2 = i * 20;
            d = d5 == (double)Math.abs(blockPos3.getZ()) ? d7 : 1.0 - d7;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        int i4 = (int)(d2 * d);
        BlockPos blockPos4 = this.getPosition().add(i3, 0, i4);
        blockPos4 = new BlockPos(blockPos4.getX(), BedLogic.countNearbyBeds(this.world, blockPos4.getX(), blockPos4.getZ()) + 1, blockPos4.getZ());
        return blockPos4;
    }

    void r(UUID uuid) {
        BlockPos blockPos;
        int i = 0;
        do {
            blockPos = this.getPosition();
            blockPos = blockPos.add((50 + this.getRNG().nextInt(50)) * (this.getRNG().nextBoolean() ? 1 : -1), 0, (50 + this.getRNG().nextInt(50)) * (this.getRNG().nextBoolean() ? 1 : -1));
        } while (((blockPos = new BlockPos(blockPos.getX(), BedLogic.countNearbyBeds(this.world, blockPos.getX(), blockPos.getZ()), blockPos.getZ())).getY() <= 0 || !this.getNavigator().canEntityStandOnPos(blockPos)) && ++i < 100);
        GirlHomeBuilder.setHomePos(uuid, blockPos);
    }

    void c(UUID uUID, Collection<TreeCluster> collection) {
        List<BlockPos> list = this.a(this.getPosition(), BlockLog.class, 30, 4, null);
        BlockPos blockPos = null;
        for (BlockPos blockPos2 : list) {
            Block block = this.world.getBlockState(blockPos2.down()).getBlock();
            if (block instanceof BlockLog) continue;
            try {
                if (block == Blocks.AIR) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            boolean flag = false;
            for (TreeCluster treeCluster : collection) {
                if (!treeCluster.containsLogPos(blockPos2)) continue;
                flag = true;
                break;
            }
            if (flag) continue;
            blockPos = blockPos2;
            break;
        }
        try {
            if (blockPos == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        TreeCluster.createFallTask(this.world, blockPos, uUID);
        this.h("Someone, go fall this tree!");
    }

    ActivityState p() {
        long l = this.world.getWorldTime();
        try {
            if (l < 12000L) {
                return ActivityState.ACTIVE;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return ActivityState.REST;
    }

    boolean d_(UUID uUID) {
        return this.c(uUID, true);
    }

    boolean c(UUID uUID, boolean flag) {
        Iterator<Object> iterator;
        Object object2;
        HashSet<EntityLivingBase> hashSet = GirlHomeBuilder.getNearbyEntities(uUID);
        KoboldNpc kobold = GirlHomeBuilder.getKoboldEntity(uUID);
        try {
            if (kobold == null) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        for (Object object2 : this.world.getEntitiesWithinAABB(KoboldNpc.class, new AxisAlignedBB(kobold.posX - 30.0, kobold.posY - 30.0, kobold.posZ - 30.0, kobold.posX + 30.0, kobold.posY + 30.0, kobold.posZ + 30.0))) {
            try {
                if (!this.canEntityBeSeen((Entity)object2)) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                try {
                    if (((GirlEntity)object2).J() && this.J()) {
                        continue;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            iterator = (Optional)object2.getDataManager().get(BoundPlayerUuidKey);
            try {
                if (!iterator.isPresent()) {
                    hashSet.add((EntityLivingBase)object2);
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (((UUID)iterator.get()).equals(uUID)) continue;
                hashSet.add((EntityLivingBase)object2);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        EntityLivingBase entityLivingBase = null;
        object2 = new ArrayList();
        for (EntityLivingBase entityLivingBase2 : hashSet) {
            try {
                if (entityLivingBase2.isDead) {
                    object2.add(entityLivingBase2);
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (kobold.getDistance((Entity)entityLivingBase2) > 30.0f) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (entityLivingBase != null && !(this.getDistance((Entity)entityLivingBase) > this.getDistance((Entity)entityLivingBase2))) continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            entityLivingBase = entityLivingBase2;
        }
        iterator = object2.iterator();
        while (iterator.hasNext()) {
            EntityLivingBase entityLivingBase2;
            entityLivingBase2 = (EntityLivingBase)iterator.next();
            GirlHomeBuilder.removeNearbyEntity(uUID, entityLivingBase2);
        }
        try {
            if (entityLivingBase == null) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (!flag) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.ATTACK) {
                this.DataManager.set(G, (Object)false);
                this.setCurrentAction(GirlAnimationState.NULL);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        iterator = this.getDisplayName(entityLivingBase.getPosition());
        try {
            this.getNavigator().tryMoveToXYZ((double)iterator.getX(), (double)iterator.getY(), (double)iterator.getZ(), 0.7);
            this.k();
            if (this.getDistance((Entity)entityLivingBase) > 1.5f) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (this.aP > 0) {
                return true;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        float f = (float)(Math.atan2(this.posZ - entityLivingBase.posZ, this.posX - entityLivingBase.posX) * 57.29577951308232 + 90.0);
        this.b(f);
        this.setCurrentAction(GirlAnimationState.ATTACK);
        this.aP = 84;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void n(UUID uUID) {
        try {
            if (this.d(uUID)) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        ActivityState activityState = GirlHomeBuilder.getActivityState(uUID);
        try {
            switch (activityState) {
                case REST: {
                    this.advanceAnimationState(uUID);
                    return;
                }
                case ACTIVE: {
                    this.aF = null;
                    this.getModelColors(uUID);
                    return;
                }
                default: {
                    return;
                }
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    void h_(UUID uUID) {
        block22: {
            block20: {
                block21: {
                    BlockPos blockPos = GirlHomeBuilder.getHomePos(uUID);
                    try {
                        if (blockPos == null) {
                            this.aM = null;
                            this.getGirlsByOwner(uUID);
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    KoboldNpc kobold = GirlHomeBuilder.getKoboldEntity(uUID);
                    try {
                        if (GirlHomeBuilder.hasKobold(uUID)) {
                            this.getNavigator().clearPath();
                            this.aM = null;
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        if (kobold == null) {
                            System.out.println("leader of tribe " + uUID + " is null");
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        if (kobold.getDistance((Entity)this) > 20.0f) {
                            this.setPosition(kobold.posX, kobold.posY, kobold.posZ);
                            this.aM = null;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        try {
                            if (this.ticksExisted % 40 != 0) break block20;
                            if (!this.aS.equals((Object)this.getPositionVector())) break block21;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        this.aM = this.t(uUID);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                this.aS = this.getPositionVector();
            }
            try {
                try {
                    if (this.aM != null && !(this.aM.getDistance((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0)) break block22;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                this.aM = this.t(uUID);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        this.getNavigator().tryMoveToXYZ((double)this.aM.getX(), (double)this.aM.getY(), (double)this.aM.getZ(), (double)0.35f);
        this.k();
    }

    void g_(UUID uUID) {
        try {
            if (this.getSexPlayerUuid() != null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        Collection<TreeCluster> collection = GirlHomeBuilder.getTreeClusters(uUID);
        try {
            if (collection == null) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        TreeCluster treeCluster = null;
        for (TreeCluster treeCluster2 : collection) {
            if (!treeCluster2.isAssigned(this)) continue;
            treeCluster = treeCluster2;
            break;
        }
        if (treeCluster == null) {
            for (TreeCluster treeCluster3 : collection) {
                try {
                    try {
                        if (this.J() && !this.getDisplayName(uUID, treeCluster3)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                try {
                    if (!this.a(treeCluster3)) {
                        this.ax = true;
                        continue;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                if (!treeCluster3.assignKobold(this)) continue;
                treeCluster = treeCluster3;
                try {
                    this.aI = null;
                    if (treeCluster3.getTaskType() == KoboldTask.FALL_TREE) {
                        this.h("Ima fall this tree owo");
                        break;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                this.h("Ima go mine uwu");
                this.b(treeCluster3.getAnchorPos());
                this.world.setBlockState(treeCluster3.getAnchorPos(), Blocks.AIR.getDefaultState());
                break;
            }
        }
        try {
            if (treeCluster == null) {
                this.u(uUID);
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (treeCluster.getTaskType() == KoboldTask.FALL_TREE) {
                this.a(uUID, treeCluster.getAnchorPos(), treeCluster);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (treeCluster.getTaskType() == KoboldTask.MINE) {
                this.b(uUID, treeCluster);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    void b(BlockPos blockPos) {
        NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnParticle(this.getGirlUuid(), EnumParticleTypes.PORTAL.getParticleName(), 30), new NetworkRegistry.TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 30.0));
        this.setPosition(0.5f + (float)blockPos.getX(), blockPos.getY(), 0.5f + (float)blockPos.getZ());
        NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnParticle(this.getGirlUuid(), EnumParticleTypes.PORTAL.getParticleName(), 30), new NetworkRegistry.TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 30.0));
    }

    void b(UUID uUID, TreeCluster treeCluster) {
        try {
            if (this.getCurrentAction() != GirlAnimationState.MINE) {
                this.a(uUID, treeCluster);
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        --this.Z;
        --this.ChatDelayTicks;
        if (this.ChatDelayTicks == 0) {
            IBlockState iBlockState;
            IBlockState iBlockState2 = this.world.getBlockState(this.aI.up());
            if (!(iBlockState2.getBlock() instanceof BlockFalling)) {
                treeCluster.removeLogPos(this.aI);
                iBlockState = this.getSexPlayer();
                try {
                    if (iBlockState != null) {
                        NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(this.aI, false), (EntityPlayerMP)iBlockState);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            iBlockState = this.world.getBlockState(this.aI);
            this.b(new ItemStack(iBlockState.getBlock().getItemDropped(iBlockState, this.getRNG(), 0), 1, iBlockState.getBlock().damageDropped(iBlockState)));
            this.world.destroyBlock(this.aI, false);
        }
        try {
            if (this.Z <= 0) {
                this.Z = 100;
                this.ChatDelayTicks = 24;
                this.setCurrentAction(GirlAnimationState.NULL);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    void a(UUID uUID, TreeCluster treeCluster) {
        PathNavigate pathNavigate;
        block28: {
            block27: {
                block25: {
                    BlockPos blockPos;
                    block26: {
                        pathNavigate = this.getNavigator();
                        try {
                            try {
                                if (this.aI != null && treeCluster.getLogPositions().contains(this.aI)) break block25;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            this.aI = this.a(treeCluster, uUID);
                            if (this.aI != null) break block26;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        boolean flag = treeCluster.getLogPositions().isEmpty();
                        HashSet<BlockPos> hashSet = GirlHomeBuilder.removeAnchorAndGetSpawnPositions(uUID, treeCluster);
                        UUID uUID2 = GirlHomeBuilder.getTribeMasterUuid(uUID);
                        try {
                            if (uUID2 == null) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID2);
                        try {
                            if (entityPlayer == null) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        try {
                            if (!flag) {
                                entityPlayer.sendMessage((ITextComponent)new TextComponentString(String.format("<%s> It's impossible to mine here...", this.getDisplayName())));
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet, false), (EntityPlayerMP)entityPlayer);
                        return;
                    }
                    if (Math.abs(this.getPosition().getY() - treeCluster.getAnchorPos().getY()) > 3) {
                        blockPos = treeCluster.getAnchorPos().add(treeCluster.getFacing().getOpposite().getDirectionVec());
                        this.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                        this.b(blockPos);
                    }
                    blockPos = this.aI.add(treeCluster.getFacing().getOpposite().getDirectionVec());
                    pathNavigate.tryMoveToXYZ((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)0.35f);
                    return;
                }
                IBlockState iBlockState = this.world.getBlockState(this.aI);
                try {
                    if (!this.boolean_a(new ItemStack(iBlockState.getBlock().getItemDropped(iBlockState, ModConstants.Random, 0)))) {
                        this.ax = true;
                        this.b(uUID, true);
                        return;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                try {
                    try {
                        try {
                            try {
                                if (this.motionX != 0.0 || this.motionZ != 0.0) break block27;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            if (!this.onGround) break block27;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        if (this.getDistance(this.aI.getX(), this.aI.getY(), this.aI.getZ()) > 3.0) break block27;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    if (++this.aK >= 10) break block28;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            BlockPos blockPos = this.aI.add(treeCluster.getFacing().getOpposite().getDirectionVec());
            pathNavigate.tryMoveToXYZ((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)0.35f);
            return;
        }
        pathNavigate.clearPath();
        this.aK = 0;
        this.setCurrentAction(GirlAnimationState.MINE);
        this.rotationYaw = this.rotationYawHead = (float)(Math.atan2(this.posZ - (double)this.aI.getZ(), this.posX - (double)this.aI.getX()) * 57.29577951308232 + 90.0);
        this.DataManager.set(at, (Object)false);
    }

    BlockPos a(TreeCluster treeCluster, UUID uuid) {
        HashSet<BlockPos> logPositions = treeCluster.getLogPositions();
        EnumFacing facing = treeCluster.getFacing();
        ArrayList<BlockPos> edgeCandidates = new ArrayList<BlockPos>();
        Integer n = null;
        if (logPositions.isEmpty()) {
            return null;
        }
        for (BlockPos blockPos : logPositions) {
            switch (facing) {
                case NORTH: {
                    if (n != null && blockPos.getZ() >= n) break;
                    n = blockPos.getZ();
                    edgeCandidates.add(blockPos);
                    break;
                }
                case SOUTH: {
                    if (n != null && blockPos.getZ() <= n) break;
                    n = blockPos.getZ();
                    edgeCandidates.add(blockPos);
                    break;
                }
                case EAST: {
                    if (n != null && blockPos.getX() <= n) break;
                    n = blockPos.getX();
                    edgeCandidates.add(blockPos);
                    break;
                }
                case WEST: {
                    if (n != null && blockPos.getX() >= n) break;
                    n = blockPos.getX();
                    edgeCandidates.add(blockPos);
                }
            }
        }
        if (edgeCandidates.isEmpty()) {
            return null;
        }
        ArrayList<BlockPos> workArea = new ArrayList<BlockPos>();
        EnumFacing enumFacing = treeCluster.getFacing();
        BlockPos anchorPos = treeCluster.getAnchorPos();
        BlockPos blockPos2;
        if (enumFacing.getAxis() == EnumFacing.Axis.Z) {
            blockPos2 = new BlockPos(anchorPos.getX(), anchorPos.getY(), edgeCandidates.get(0).getZ());
            blockPos2 = enumFacing == EnumFacing.NORTH ? blockPos2.north() : blockPos2.south();
            workArea.add(blockPos2.down());
            workArea.add(blockPos2.down().east());
            workArea.add(blockPos2.down().west());
            workArea.add(blockPos2);
            workArea.add(blockPos2.up());
            workArea.add(blockPos2.up().up());
            workArea.add(blockPos2.up().up().up());
            workArea.add(blockPos2.west());
            workArea.add(blockPos2.west().up());
            workArea.add(blockPos2.west().up().up());
            workArea.add(blockPos2.west().up().up().up());
            workArea.add(blockPos2.west().west());
            workArea.add(blockPos2.west().west().up());
            workArea.add(blockPos2.west().west().up().up());
            workArea.add(blockPos2.east());
            workArea.add(blockPos2.east().up());
            workArea.add(blockPos2.east().up().up());
            workArea.add(blockPos2.east().up().up().up());
            workArea.add(blockPos2.east().east());
            workArea.add(blockPos2.east().east().up());
            workArea.add(blockPos2.east().east().up().up());
        } else {
            blockPos2 = new BlockPos(edgeCandidates.get(0).getX(), anchorPos.getY(), anchorPos.getZ());
            blockPos2 = enumFacing == EnumFacing.EAST ? blockPos2.east() : blockPos2.west();
            workArea.add(blockPos2.down());
            workArea.add(blockPos2.down().north());
            workArea.add(blockPos2.down().south());
            workArea.add(blockPos2);
            workArea.add(blockPos2.up());
            workArea.add(blockPos2.up().up());
            workArea.add(blockPos2.up().up().up());
            workArea.add(blockPos2.south());
            workArea.add(blockPos2.south().up());
            workArea.add(blockPos2.south().up().up());
            workArea.add(blockPos2.south().up().up().up());
            workArea.add(blockPos2.south().south());
            workArea.add(blockPos2.south().south().up());
            workArea.add(blockPos2.south().south().up().up());
            workArea.add(blockPos2.north());
            workArea.add(blockPos2.north().up());
            workArea.add(blockPos2.north().up().up());
            workArea.add(blockPos2.north().up().up().up());
            workArea.add(blockPos2.north().north());
            workArea.add(blockPos2.north().north().up());
            workArea.add(blockPos2.north().north().up().up());
        }
        HashSet<BlockPos> flooded = new HashSet<BlockPos>();
        for (BlockPos blockPos3 : workArea) {
            if (!this.world.getBlockState(blockPos3).getMaterial().isLiquid()) continue;
            this.world.setBlockState(blockPos3, Blocks.COBBLESTONE.getDefaultState(), 2);
            if (!edgeCandidates.contains(blockPos3)) continue;
            flooded.add(blockPos3);
        }
        if (!flooded.isEmpty()) {
            treeCluster.addAllLogPos(flooded);
            EntityPlayer entityPlayer = this.getSexPlayer();
            if (entityPlayer != null) {
                NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(flooded, true), (EntityPlayerMP)entityPlayer);
            }
        }
        workArea.clear();
        workArea.add(blockPos2.down());
        if (enumFacing.getAxis() == EnumFacing.Axis.Z) {
            workArea.add(blockPos2.down().west());
            workArea.add(blockPos2.down().east());
        } else {
            workArea.add(blockPos2.down().north());
            workArea.add(blockPos2.down().south());
        }
        for (BlockPos blockPos4 : workArea) {
            if (!this.world.getBlockState(blockPos4).getBlock().isPassable(this.world, blockPos4)) continue;
            this.world.setBlockState(blockPos4, Blocks.COBBLESTONE.getDefaultState());
        }
        HashSet<BlockPos> airPositions = new HashSet<BlockPos>();
        for (BlockPos blockPos5 : edgeCandidates) {
            Block block = this.world.getBlockState(blockPos5).getBlock();
            if (block != Blocks.AIR) continue;
            airPositions.add(blockPos5);
        }
        if (!airPositions.isEmpty()) {
            edgeCandidates.removeAll(airPositions);
            treeCluster.removeAllLogPos(airPositions);
            UUID uuid2 = GirlHomeBuilder.getTribeMasterUuid(uuid);
            if (uuid2 != null) {
                EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uuid2);
                if (entityPlayer != null) {
                    NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(airPositions, false), (EntityPlayerMP)entityPlayer);
                }
            }
        }
        if (edgeCandidates.isEmpty()) {
            return this.a(treeCluster, uuid);
        }
        BlockPos result = null;
        List<KoboldNpc> assignees = treeCluster.getAssignees();
        for (int i = 0; i < assignees.size(); ++i) {
            if (assignees.get(i).getEntityId() != this.getEntityId()) continue;
            if (i == 0) {
                result = this.a(edgeCandidates, -1, treeCluster.getFacing(), treeCluster.getAnchorPos());
                if (result == null) {
                    result = this.a(edgeCandidates, 0, treeCluster.getFacing(), treeCluster.getAnchorPos());
                    if (result == null) {
                        result = this.a(edgeCandidates, 1, treeCluster.getFacing(), treeCluster.getAnchorPos());
                    }
                }
                break;
            }
            if (i == 1) {
                result = this.a(edgeCandidates, 1, treeCluster.getFacing(), treeCluster.getAnchorPos());
                if (result == null) {
                    result = this.a(edgeCandidates, 0, treeCluster.getFacing(), treeCluster.getAnchorPos());
                    if (result == null) {
                        result = this.a(edgeCandidates, -1, treeCluster.getFacing(), treeCluster.getAnchorPos());
                    }
                }
                break;
            }
            if (i != 2) continue;
            result = this.a(edgeCandidates, 0, treeCluster.getFacing(), treeCluster.getAnchorPos());
            if (result == null) {
                result = this.a(edgeCandidates, 1, treeCluster.getFacing(), treeCluster.getAnchorPos());
                if (result == null) {
                    result = this.a(edgeCandidates, -1, treeCluster.getFacing(), treeCluster.getAnchorPos());
                }
            }
            break;
        }
        return result;
    }

    @Nullable
    BlockPos a(List<BlockPos> list, int i, EnumFacing enumFacing, BlockPos blockPos) {
        BlockPos blockPos2;
        int i2;
        ArrayList<BlockPos> arrayList;
        ArrayList<BlockPos> arrayList2;
        ArrayList<BlockPos> arrayList3;
        block33: {
            int i3;
            block32: {
                try {
                    if (list.isEmpty()) {
                        return null;
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                arrayList3 = new ArrayList<BlockPos>();
                arrayList2 = new ArrayList<BlockPos>();
                arrayList = new ArrayList<BlockPos>();
                try {
                    try {
                        if (enumFacing != EnumFacing.SOUTH && enumFacing != EnumFacing.WEST) break block32;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    i3 = -1;
                    break block33;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            i3 = i2 = 1;
        }
        if (enumFacing.getAxis() == EnumFacing.Axis.Z) {
            blockPos2 = new BlockPos(blockPos.getX(), blockPos.getY(), list.get(0).getZ());
            arrayList.add(blockPos2);
            arrayList.add(blockPos2.up());
            arrayList.add(blockPos2.up().up());
            arrayList.add(blockPos2.west());
            arrayList.add(blockPos2.west().up());
            arrayList.add(blockPos2.west().up().up());
            arrayList.add(blockPos2.east());
            arrayList.add(blockPos2.east().up());
            arrayList.add(blockPos2.east().up().up());
            if (i == 0) {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.east(2));
                    arrayList2.add(blockPos3.east(-2));
                }
                for (BlockPos blockPos3 : list) {
                    try {
                        if (arrayList2.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
            } else {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.east(i2 * 2 * i));
                }
                for (BlockPos blockPos3 : arrayList2) {
                    try {
                        if (!list.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
            }
        }
        if (enumFacing.getAxis() == EnumFacing.Axis.X) {
            blockPos2 = new BlockPos(list.get(0).getX(), blockPos.getY(), blockPos.getZ());
            arrayList.add(blockPos2);
            arrayList.add(blockPos2.up());
            arrayList.add(blockPos2.up().up());
            arrayList.add(blockPos2.north());
            arrayList.add(blockPos2.north().up());
            arrayList.add(blockPos2.north().up().up());
            arrayList.add(blockPos2.south());
            arrayList.add(blockPos2.south().up());
            arrayList.add(blockPos2.south().up().up());
            if (i == 0) {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.south(2));
                    arrayList2.add(blockPos3.south(-2));
                }
                for (BlockPos blockPos3 : list) {
                    try {
                        if (arrayList2.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
            } else {
                for (BlockPos blockPos3 : arrayList) {
                    arrayList2.add(blockPos3.south(i2 * 2 * i));
                }
                for (BlockPos blockPos3 : arrayList2) {
                    try {
                        if (!list.contains(blockPos3)) continue;
                        arrayList3.add(blockPos3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
            }
        }
        try {
            if (arrayList3.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return (BlockPos)arrayList3.get(this.getRNG().nextInt(arrayList3.size()));
    }

    void u(UUID uUID) {
        try {
            if (this.b(uUID, false)) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        this.isLocalPlayerNearby();
    }

    void e() {
        block26: {
            block25: {
                block22: {
                    int i;
                    int i2;
                    block24: {
                        block23: {
                            block20: {
                                EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 15.0);
                                try {
                                    try {
                                        try {
                                            try {
                                                if (!this.J() || entityPlayer == null) break block20;
                                            }
                                            catch (IllegalArgumentException illegalArgumentException) {
                                                throw KoboldNpc.rethrow(illegalArgumentException);
                                            }
                                            if (!(entityPlayer.getDistance((Entity)this) < 2.0f)) break block20;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw KoboldNpc.rethrow(illegalArgumentException);
                                        }
                                        if (!((String)this.DataManager.get(v)).equals(entityPlayer.getPersistentID().toString())) break block20;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                    this.getNavigator().clearPath();
                                    return;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                            }
                            try {
                                try {
                                    block21: {
                                        try {
                                            try {
                                                if (this.WanderTargetPos == null || this.getDistance(this.WanderTargetPos.getX(), this.WanderTargetPos.getY(), this.WanderTargetPos.getZ()) > this.isOwnedByLocalPlayer()) break block21;
                                            }
                                            catch (IllegalArgumentException illegalArgumentException) {
                                                throw KoboldNpc.rethrow(illegalArgumentException);
                                            }
                                            if (this.ab <= 100) break block22;
                                        }
                                        catch (IllegalArgumentException illegalArgumentException) {
                                            throw KoboldNpc.rethrow(illegalArgumentException);
                                        }
                                    }
                                    if (!this.getRNG().nextBoolean()) break block23;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                i2 = 1;
                                break block24;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                        }
                        i2 = -1;
                    }
                    int i3 = i2 * this.getRNG().nextInt(5);
                    try {
                        i = this.getRNG().nextBoolean() ? 1 : -1;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    int i4 = i * this.getRNG().nextInt(5);
                    int i5 = BedLogic.countNearbyBeds(this.world, this.getPosition().getX() + i3, this.getPosition().getZ() + i4);
                    this.WanderTargetPos = new BlockPos(this.getPosition().getX() + i3, i5, this.getPosition().getZ() + i4);
                    this.ab = 0;
                }
                try {
                    if (!(Math.sqrt(this.WanderTargetPos.distanceSq((Vec3i)this.getPosition())) > 2.0)) break block25;
                    this.getNavigator().tryMoveToXYZ((double)this.WanderTargetPos.getX(), (double)this.WanderTargetPos.getY(), (double)this.WanderTargetPos.getZ(), (double)0.35f);
                    this.k();
                    break block26;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            ++this.ab;
        }
    }

    double n() {
        return Math.sqrt(800.0);
    }

    boolean b(UUID uUID, boolean flag) {
        block13: {
            try {
                if (this.getGirlUuid()) {
                    return false;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (this.a(uUID, flag)) {
                    this.a0 = 0;
                    return true;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                try {
                    if (--this.a0 < 0 && this.ax) break block13;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                return false;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        this.a0 = 300;
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(UUID.fromString((String)this.DataManager.get(v)));
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.valueOf((String)this.DataManager.get(BodyColorKey));
        try {
            if (entityPlayer != null) {
                entityPlayer.sendStatusMessage((ITextComponent)new TextComponentString(eyeAndKoboldColor.getTextColor() + this.getDisplayName() + "s " + TextFormatting.WHITE + "inventory is full and there are either no chests to put her items in or said chests are full as well"), false);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return false;
    }

    boolean a(UUID uUID, boolean flag) {
        block37: {
            block38: {
                TileEntityChest tileEntityChest;
                BlockPos blockPos3;
                BlockPos blockPos2;
                block35: {
                    block36: {
                        int i;
                        IItemHandler iItemHandler;
                        HashSet<BlockPos> hashSet = GirlHomeBuilder.getNearbyPositions(uUID);
                        try {
                            if (hashSet == null) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        blockPos2 = null;
                        for (BlockPos blockPos3 : hashSet) {
                            TileEntityChest tileEntityChest2 = (TileEntityChest)this.world.getTileEntity(blockPos3);
                            iItemHandler = tileEntityChest2.getSingleChestHandler();
                            i = 0;
                            for (int i5 = 0; i5 < this.Inventory.getSlots(); ++i5) {
                                ItemStack itemStack = this.Inventory.getStackInSlot(i5);
                                try {
                                    if (itemStack.isEmpty()) {
                                        continue;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                for (int i6 = 0; i6 < iItemHandler.getSlots(); ++i6) {
                                    ItemStack itemStack2 = iItemHandler.insertItem(i6, itemStack, true);
                                    if (itemStack2.getCount() == itemStack.getCount()) continue;
                                    i = 1;
                                    break;
                                }
                                try {
                                    if (i == 0) continue;
                                    break;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                            }
                            try {
                                if (i == 0) {
                                    continue;
                                }
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            if (blockPos2 == null) {
                                blockPos2 = blockPos3;
                                continue;
                            }
                            if (!(this.getDistanceSq(blockPos2) > this.getDistanceSq(blockPos3))) continue;
                            blockPos2 = blockPos3;
                        }
                        try {
                            if (blockPos2 == null) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        if (this.getDistance(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()) < 2.0) {
                            tileEntityChest = (TileEntityChest)this.world.getTileEntity(blockPos2);
                            blockPos3 = tileEntityChest.getSingleChestHandler();
                            block25: for (int i7 = 0; i7 < this.Inventory.getSlots(); ++i7) {
                                iItemHandler = this.Inventory.getStackInSlot(i7);
                                try {
                                    if (iItemHandler.isEmpty()) {
                                        continue;
                                    }
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                for (i = 0; i < blockPos3.getSlots(); ++i) {
                                    ItemStack itemStack = blockPos3.insertItem(i, (ItemStack)iItemHandler, false);
                                    try {
                                        if (itemStack.getCount() <= 0) {
                                            this.Inventory.setStackInSlot(i7, ItemStack.EMPTY);
                                            continue block25;
                                        }
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                    this.Inventory.setStackInSlot(i7, itemStack);
                                    iItemHandler = itemStack;
                                }
                            }
                            this.world.playSound(null, blockPos2, SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.BLOCKS, 1.0f, 1.0f);
                            return true;
                        }
                        try {
                            try {
                                if (Math.abs(blockPos2.getY() - this.getPosition().getY()) <= 4) break block35;
                                if (!flag) break block36;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            this.b(blockPos2);
                            break block37;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    return false;
                }
                tileEntityChest = this.getNavigator();
                blockPos3 = this.c(blockPos2);
                try {
                    try {
                        tileEntityChest.tryMoveToXYZ((double)blockPos3.getX(), (double)blockPos3.getY(), (double)blockPos3.getZ(), (double)0.35f);
                        if (tileEntityChest.getPath() != null) break block37;
                        if (!flag) break block38;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    this.b(blockPos2);
                    break block37;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            return false;
        }
        return true;
    }

    boolean getDisplayName(UUID uUID, TreeCluster treeCluster) {
        List<KoboldNpc> list = GirlHomeBuilder.getKobolds(uUID);
        Collection<TreeCluster> collection = GirlHomeBuilder.getTreeClusters(uUID);
        KoboldNpc kobold = null;
        Vec3d vec3d = new Vec3d((double)treeCluster.getAnchorPos().getX(), (double)treeCluster.getAnchorPos().getY(), (double)treeCluster.getAnchorPos().getZ());
        for (KoboldNpc kobold2 : list) {
            boolean flag = false;
            for (TreeCluster treeCluster2 : collection) {
                if (!treeCluster2.isAssigned(kobold2)) continue;
                flag = true;
                break;
            }
            try {
                if (flag) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            try {
                if (kobold2.getSexPlayerUuid() != null) {
                    continue;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            if (kobold == null) {
                kobold = kobold2;
                continue;
            }
            if (!(kobold.getPositionVector().distanceTo(vec3d) > kobold2.getPositionVector().distanceTo(vec3d))) continue;
            kobold = kobold2;
        }
        return this.equals(kobold);
    }

    void a(UUID uuid, TreeCluster treeCluster, BlockPos pos) {
        if (this.ad == null) {
            this.aR = 24;
            this.W = 0;
            this.setCurrentAction(GirlAnimationState.NULL);
            this.DataManager.set(G, (Object)false);
            EntityPlayer entityPlayer = this.getSexPlayer();
            HashSet<BlockPos> hashSet = treeCluster.getLogPositions();
            if (entityPlayer != null && !hashSet.isEmpty()) {
                NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet, false), (EntityPlayerMP)entityPlayer);
            }
            GirlHomeBuilder.updateKoboldEntity(uuid);
            return;
        }
        switch (this.ad.getMetadata()) {
            case 3:
            case 5: {
                this.world.setBlockState(pos, Blocks.SAPLING.getStateForPlacement(this.world, pos, EnumFacing.NORTH, pos.getX(), pos.getY(), pos.getZ(), this.ad.getMetadata(), this, EnumHand.MAIN_HAND));
                this.world.setBlockState(pos.north(), Blocks.SAPLING.getStateForPlacement(this.world, pos.north(), EnumFacing.NORTH, pos.getX(), pos.getY(), pos.getZ() + 1, this.ad.getMetadata(), this, EnumHand.MAIN_HAND));
                this.world.setBlockState(pos.west(), Blocks.SAPLING.getStateForPlacement(this.world, pos.west(), EnumFacing.NORTH, pos.getX() + 1, pos.getY(), pos.getZ(), this.ad.getMetadata(), this, EnumHand.MAIN_HAND));
                this.world.setBlockState(pos.north().west(), Blocks.SAPLING.getStateForPlacement(this.world, pos.north().west(), EnumFacing.NORTH, pos.getX() + 1, pos.getY(), pos.getZ() + 1, this.ad.getMetadata(), this, EnumHand.MAIN_HAND));
                break;
            }
            default: {
                this.world.setBlockState(pos, Blocks.SAPLING.getStateForPlacement(this.world, pos, EnumFacing.NORTH, pos.getX(), pos.getY(), pos.getZ(), this.ad.getMetadata(), this, EnumHand.MAIN_HAND));
            }
        }
        this.aR = 24;
        this.W = 0;
        this.ad = null;
        this.setCurrentAction(GirlAnimationState.NULL);
        this.setShouldBeAtTargetPos(false);
        EntityPlayer entityPlayer = this.getSexPlayer();
        HashSet<BlockPos> hashSet = treeCluster.getLogPositions();
        if (entityPlayer != null && !hashSet.isEmpty()) {
            NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet, false), (EntityPlayerMP)entityPlayer);
        }
        GirlHomeBuilder.updateKoboldEntity(uuid);
    }

    void a(UUID uUID, BlockPos blockPos, TreeCluster treeCluster) {
        if (this.getCurrentAction() != GirlAnimationState.MINE) {
            this.a(blockPos, uUID);
            return;
        }
        --this.W;
        if (this.W > 0) {
            return;
        }
        if (this.W == 0) {
            NetworkHandler.channel.sendToAllAround((IMessage)new PacketResetController(this.getGirlUuid()), this.P());
        }
        if (this.world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
            this.a(uUID, treeCluster, blockPos);
            return;
        }
        --this.aR;
        if (this.aR >= 0) {
            return;
        }
        this.aR = 24;
        this.W = 78;
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        EntityPlayer entityPlayer = this.getSexPlayer();
        for (BlockPos object2 : treeCluster.getLogPositions()) {
            if (this.world.getBlockState(object2).getBlock() == Blocks.AIR) {
                hashSet.add(object2);
                continue;
            }
            if (object2.getX() == blockPos.getX() && object2.getZ() == blockPos.getZ()) continue;
            try {
                ItemStack itemStack = this.world.getBlockState(object2).getBlock().getItem(this.world, blockPos, this.world.getBlockState(blockPos));
                if (itemStack.getItem() != Items.AIR) {
                    this.b(itemStack);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                Main.LOGGER.error("Couldn't get an item out of the block that a kobold just destroyed when falling a tree. As a result, the block wasn't added into the kobolds inventory. If you see this message, pls tell trol about it and send her the following stacktrace. Do you maybe remember what block the kobold just removed? Stacktrace follwing:");
                Main.LOGGER.warn("block in question: " + this.world.getBlockState(object2).getBlock().getTranslationKey());
                Main.LOGGER.error(illegalArgumentException.getMessage());
            }
            this.CarriedItem = this.net_minecraft_item_ItemStack_a(object2);
            this.world.destroyBlock(object2, false);
            treeCluster.removeLogPos(object2);
            treeCluster.removeAllLogPos(hashSet);
            hashSet.add(object2);
            if (entityPlayer != null) {
                NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet, false), (EntityPlayerMP)entityPlayer);
            }
            return;
        }
        try {
            ItemStack itemStack = this.world.getBlockState(blockPos).getBlock().getItem(this.world, blockPos, this.world.getBlockState(blockPos));
            if (itemStack.getItem() != Items.AIR) {
                this.b(itemStack);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            Main.LOGGER.error("Couldn't get an item out of the block that a kobold just destroyed when falling a tree. As a result, the block wasn't added into the kobolds inventory. If you see this message, pls tell trol about it and send her the following stacktrace. Do you maybe remember what block the kobold just removed? Stacktrace follwing:");
            Main.LOGGER.warn("block in question: " + this.world.getBlockState(blockPos).getBlock().getTranslationKey());
            Main.LOGGER.error(illegalArgumentException.getMessage());
        }
        this.CarriedItem = this.net_minecraft_item_ItemStack_a(blockPos);
        this.world.destroyBlock(blockPos, false);
        int i = 0;
        for (BlockPos blockPos2 : treeCluster.getLogPositions()) {
            if (!(this.world.getBlockState(blockPos2).getBlock() instanceof BlockLog)) continue;
            ++i;
        }
        HashSet<BlockPos> hashSet2 = new HashSet<BlockPos>();
        for (int i3 = 0; i3 < i; ++i3) {
            hashSet2.add(blockPos.add(0, i3, 0));
        }
        HashSet<BlockPos> hashSet3 = new HashSet<BlockPos>();
        for (BlockPos blockPos3 : treeCluster.getLogPositions()) {
            if (hashSet2.contains(blockPos3)) continue;
            hashSet3.add(blockPos3);
        }
        if (!hashSet3.isEmpty() && entityPlayer != null) {
            NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet3, false), (EntityPlayerMP)entityPlayer);
        }
        int i4 = 1;
        while (true) {
            BlockPos blockPos4 = blockPos.add(0, i4, 0);
            IBlockState iBlockState = this.world.getBlockState(blockPos4);
            if (this.world.getBlockState(blockPos4).getBlock() instanceof BlockLog) {
                this.world.destroyBlock(blockPos4, false);
                EntityFallingBlock entityFallingBlock = new EntityFallingBlock(this.world, (double)blockPos4.getX() + 0.5, (double)blockPos4.getY(), (double)blockPos4.getZ() + 0.5, iBlockState);
                entityFallingBlock.fallTime = 1;
                this.world.spawnEntity((Entity)entityFallingBlock);
            }
            if (!treeCluster.getLogPositions().contains(blockPos4)) break;
            ++i4;
        }
    }

    ItemStack net_minecraft_item_ItemStack_a(BlockPos blockPos) {
        block26: {
            int i;
            int i2;
            block25: {
                block24: {
                    block23: {
                        block22: {
                            ItemStack itemStack;
                            try {
                                itemStack = this.world.getBlockState(blockPos).getBlock().getItem(this.world, blockPos, this.world.getBlockState(blockPos));
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                Main.LOGGER.error("Couldn't turn a wooden block into an item to get its meta data. As a result the kobold is just gonna plant a oak saplinig instead. If you see this message, pls tell trol about it and send her the following stacktrace. Do you maybe remember what block the kobold just removed? Stacktrace follwing:");
                                Main.LOGGER.warn("block in question: " + this.world.getBlockState(blockPos).getBlock().getTranslationKey());
                                Main.LOGGER.error(illegalArgumentException.getMessage());
                                return new ItemStack(Blocks.SAPLING, 1, 0);
                            }
                            i2 = ItemBlock.getIdFromItem((Item)itemStack.getItem());
                            i = itemStack.getItem().getMetadata(itemStack);
                            try {
                                try {
                                    if (i2 != 17 || i != 1) break block22;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                return new ItemStack(Blocks.SAPLING, 1, 1);
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                        }
                        try {
                            try {
                                if (i2 != 17 || i != 2) break block23;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            return new ItemStack(Blocks.SAPLING, 1, 2);
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    try {
                        try {
                            if (i2 != 17 || i != 3) break block24;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        return new ItemStack(Blocks.SAPLING, 1, 3);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                try {
                    try {
                        if (i2 != 162 || i != 0) break block25;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    return new ItemStack(Blocks.SAPLING, 1, 4);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            try {
                try {
                    if (i2 != 162 || i != 1) break block26;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                return new ItemStack(Blocks.SAPLING, 1, 5);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        return new ItemStack(Blocks.SAPLING, 1, 0);
    }

    void a(BlockPos blockPos, UUID uUID) {
        BlockPos blockPos2;
        block34: {
            EntityPlayer entityPlayer;
            block35: {
                ArrayList<BlockPos> arrayList;
                block33: {
                    block32: {
                        block31: {
                            block30: {
                                blockPos2 = null;
                                arrayList = new ArrayList<BlockPos>();
                                try {
                                    try {
                                        if (!this.world.getBlockState(blockPos.north().down()).isFullCube() || this.world.getBlockState(blockPos.north()).isFullBlock()) break block30;
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        throw KoboldNpc.rethrow(illegalArgumentException);
                                    }
                                    arrayList.add(blockPos.north());
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                            }
                            try {
                                try {
                                    if (!this.world.getBlockState(blockPos.east().down()).isFullCube() || this.world.getBlockState(blockPos.east()).isFullBlock()) break block31;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                arrayList.add(blockPos.east());
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                        }
                        try {
                            try {
                                if (!this.world.getBlockState(blockPos.south().down()).isFullCube() || this.world.getBlockState(blockPos.south()).isFullBlock()) break block32;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            arrayList.add(blockPos.south());
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    try {
                        try {
                            if (!this.world.getBlockState(blockPos.west().down()).isFullCube() || this.world.getBlockState(blockPos.west()).isFullBlock()) break block33;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        arrayList.add(blockPos.west());
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                for (BlockPos blockPos3 : arrayList) {
                    if (blockPos2 == null) {
                        blockPos2 = blockPos3;
                        continue;
                    }
                    double d = new Vec3d((double)((float)blockPos2.getX() + 0.5f), (double)blockPos2.getY(), (double)((float)blockPos2.getZ() + 0.5f)).distanceTo(this.getPositionVector());
                    double d2 = new Vec3d((double)((float)blockPos3.getX() + 0.5f), (double)blockPos3.getY(), (double)((float)blockPos3.getZ() + 0.5f)).distanceTo(this.getPositionVector());
                    if (!(d2 < d)) continue;
                    blockPos2 = blockPos3;
                }
                if (blockPos2 == null) {
                    GirlHomeBuilder.removeWorkerAnchor(uUID, this);
                    entityPlayer = this.getSexPlayer();
                    try {
                        if (entityPlayer == null) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    entityPlayer.sendStatusMessage((ITextComponent)new TextComponentString("Your kobolds cannot fall this tree because it starts underground"), true);
                    return;
                }
                try {
                    try {
                        if (!(this.getPosition().getDistance(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()) > 1.0)) break block34;
                        if (Math.abs(this.getPosition().getY() - blockPos2.getY()) <= 4) break block35;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    this.b(blockPos2);
                    return;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            entityPlayer = this.c(blockPos2);
            this.getNavigator().tryMoveToXYZ((double)entityPlayer.getX() + 0.5, (double)entityPlayer.getY(), (double)entityPlayer.getZ() + 0.5, 0.35);
            this.k();
            return;
        }
        float f = 0.0f;
        if (blockPos2.subtract((Vec3i)blockPos).equals((Object)new BlockPos(0, 0, -1))) {
            f = 0.0f;
        }
        if (blockPos2.subtract((Vec3i)blockPos).equals((Object)new BlockPos(1, 0, 0))) {
            f = 90.0f;
        }
        if (blockPos2.subtract((Vec3i)blockPos).equals((Object)new BlockPos(0, 0, 1))) {
            f = 180.0f;
        }
        if (blockPos2.subtract((Vec3i)blockPos).equals((Object)new BlockPos(-1, 0, 0))) {
            f = -90.0f;
        }
        this.setTargetPos(new Vec3d((double)blockPos2.getX() + 0.5, (double)blockPos2.getY(), (double)blockPos2.getZ() + 0.5));
        this.b(f);
        this.DataManager.set(G, (Object)true);
        this.DataManager.set(at, (Object)true);
        this.setCurrentAction(GirlAnimationState.MINE);
        this.world.destroyBlock(blockPos2.up(), false);
    }

    void h() {
        try {
            if (this.aA) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        Optional optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
        try {
            if (!optional.isPresent()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        this.DataManager.set(BodyColorKey, (Object)GirlHomeBuilder.getTribeColor((UUID)optional.get()).toString());
    }

    @Override
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
        block25: {
            block23: {
                block21: {
                    try {
                        block22: {
                            try {
                                try {
                                    if (this.getCurrentAction() != GirlAnimationState.MATING_PRESS_CUM) break block21;
                                    if (girlAnimationState == GirlAnimationState.MATING_PRESS_SOFT) break block22;
                                }
                                catch (IllegalArgumentException illegalArgumentException) {
                                    throw KoboldNpc.rethrow(illegalArgumentException);
                                }
                                if (girlAnimationState != GirlAnimationState.MATING_PRESS_HARD) break block21;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                        }
                        return;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                try {
                    block24: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.KOBOLD_ANAL_CUM) break block23;
                                if (girlAnimationState == GirlAnimationState.KOBOLD_ANAL_SLOW) break block24;
                            }
                            catch (IllegalArgumentException illegalArgumentException) {
                                throw KoboldNpc.rethrow(illegalArgumentException);
                            }
                            if (girlAnimationState != GirlAnimationState.KOBOLD_ANAL_FAST) break block23;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                    }
                    return;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            try {
                block26: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.CUMBLOWJOB) break block25;
                            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB) break block26;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        if (girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block25;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                return;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        try {
            if (girlAnimationState == GirlAnimationState.MATING_PRESS_CUM) {
                this.V = 0;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        super.setCurrentAction(girlAnimationState);
    }

    public void onDeath(DamageSource damageSource) {
        try {
            super.onDeath(damageSource);
            if (this.world.isRemote) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        Optional optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
        try {
            if (!optional.isPresent()) {
                return;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        UUID uUID = (UUID)optional.get();
        GirlHomeBuilder.removeKobold(uUID, this);
        if (this.J()) {
            EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(UUID.fromString((String)this.getDataManager().get(v)));
            try {
                if (entityPlayer != null) {
                    entityPlayer.sendMessage((ITextComponent)new TextComponentString(String.format("%s%s%s has perished %suwu", TextFormatting.RED, this.getDisplayName(), TextFormatting.WHITE, TextFormatting.RED)));
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
    }

    @Override
    protected GirlAnimationState getFollowUpAction(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB_BLINK) {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.KOBOLD_ANAL_SLOW) {
                return GirlAnimationState.KOBOLD_ANAL_FAST;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState nextAnimationState(GirlAnimationState girlAnimationState) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.THRUSTBLOWJOB && girlAnimationState != GirlAnimationState.SUCKBLOWJOB_BLINK) break block12;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        return GirlAnimationState.CUMBLOWJOB;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.KOBOLD_ANAL_SLOW && girlAnimationState != GirlAnimationState.KOBOLD_ANAL_FAST) break block13;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    return GirlAnimationState.KOBOLD_ANAL_CUM;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.MATING_PRESS_HARD && girlAnimationState != GirlAnimationState.MATING_PRESS_SOFT) break block14;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                return GirlAnimationState.MATING_PRESS_CUM;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        return null;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nBTTagCompound) {
        super.writeEntityToNBT(nBTTagCompound);
        nBTTagCompound.setFloat("body_size", ((Float)this.DataManager.get(BodySizeKey)).floatValue());
        nBTTagCompound.setInteger("eyeColorX", ((BlockPos)this.DataManager.get(EyeColorKey)).getX());
        nBTTagCompound.setInteger("eyeColorY", ((BlockPos)this.DataManager.get(EyeColorKey)).getY());
        nBTTagCompound.setInteger("eyeColorZ", ((BlockPos)this.DataManager.get(EyeColorKey)).getZ());
        nBTTagCompound.setString("model", (String)this.DataManager.get(M));
        nBTTagCompound.setString("name", (String)this.DataManager.get(CustomNameKey));
        nBTTagCompound.setString("master", (String)this.DataManager.get(v));
        nBTTagCompound.setTag("inventory", (NBTBase)this.Inventory.serializeNBT());
        nBTTagCompound.setString("bodyColor", (String)this.DataManager.get(BodyColorKey));
        nBTTagCompound.setBoolean("editedColorManually", this.aA);
        Optional optional = (Optional)this.DataManager.get(BoundPlayerUuidKey);
        try {
            if (optional.isPresent()) {
                nBTTagCompound.setUniqueId("tribeId", (UUID)optional.get());
                nBTTagCompound.setBoolean("isLeader", GirlHomeBuilder.isKoboldOfGirl((UUID)optional.get(), this));
                nBTTagCompound.setString("tribeName", (String)this.DataManager.get(TribeNameKey));
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nBTTagCompound) {
        block18: {
            UUID uUID;
            block19: {
                super.readEntityFromNBT(nBTTagCompound);
                String string = nBTTagCompound.getString("model");
                try {
                    if (!"".equals(string)) {
                        this.DataManager.set(M, (Object)string);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                BlockPos blockPos = new BlockPos(nBTTagCompound.getInteger("eyeColorX"), nBTTagCompound.getInteger("eyeColorY"), nBTTagCompound.getInteger("eyeColorZ"));
                try {
                    if (!BlockPos.ORIGIN.equals((Object)blockPos)) {
                        this.DataManager.set(EyeColorKey, (Object)blockPos);
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                this.DataManager.set(BodySizeKey, (Object)Float.valueOf(nBTTagCompound.getFloat("body_size")));
                this.DataManager.set(CustomNameKey, (Object)nBTTagCompound.getString("name"));
                this.DataManager.set(v, (Object)nBTTagCompound.getString("master"));
                this.Inventory.deserializeNBT(nBTTagCompound.getCompoundTag("inventory"));
                String string2 = nBTTagCompound.getString("bodyColor");
                try {
                    if (!"".equals(string2)) {
                        this.DataManager.set(BodyColorKey, (Object)nBTTagCompound.getString("bodyColor"));
                    }
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
                this.aA = nBTTagCompound.getBoolean("editedColorManually");
                uUID = nBTTagCompound.getUniqueId("tribeId");
                try {
                    try {
                        try {
                            if (uUID == null || this.isDead) break block18;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        this.DataManager.set(BoundPlayerUuidKey, (Object)Optional.of((Object)uUID));
                        if (GirlHomeBuilder.hasHomeData(uUID)) break block19;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    GirlHomeBuilder.registerTribe(uUID, EyeAndKoboldColor.valueOf((String)this.DataManager.get(BodyColorKey)));
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            try {
                GirlHomeBuilder.registerKoboldInTribe(uUID, this);
                if (nBTTagCompound.getBoolean("isLeader")) {
                    GirlHomeBuilder.setKoboldEntity(uUID, this);
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
            this.DataManager.set(TribeNameKey, (Object)nBTTagCompound.getString("tribeName"));
        }
    }

    @Override
    public boolean boolean_a() {
        boolean flag;
        try {
            if (this.isTracked()) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        Block block = this.world.getBlockState(this.getPosition().add(0, 1, 0)).getBlock();
        try {
            flag = !block.isPassable((IBlockAccess)this.world, this.getPosition().add(0, 1, 0));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return flag;
    }

    boolean f() {
        int i = 0;
        while (true) {
            block5: {
                try {
                    try {
                        if (i >= this.Inventory.getSlots()) break;
                        if (this.Inventory.getStackInSlot(i).isEmpty()) break block5;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    return false;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            ++i;
        }
        return true;
    }

    boolean a(TreeCluster treeCluster) {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        for (BlockPos blockPos : treeCluster.getLogPositions()) {
            try {
                IBlockState iBlockState = this.world.getBlockState(blockPos);
                ItemStack itemStack = iBlockState.getBlock().getItem(this.world, blockPos, iBlockState);
                arrayList.add(itemStack);
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        return this.boolean_a((List<ItemStack>)arrayList);
    }

    boolean boolean_a(ItemStack itemStack) {
        return this.a(this.Inventory, itemStack, true, false);
    }

    boolean boolean_a(List<ItemStack> list) {
        ItemStackHandler itemStackHandler = new ItemStackHandler(this.Inventory.getSlots());
        try {
            for (int i = 0; i < itemStackHandler.getSlots(); ++i) {
                itemStackHandler.setStackInSlot(i, this.Inventory.getStackInSlot(i));
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        for (ItemStack itemStack : list) {
            try {
                if (this.a(itemStackHandler, itemStack, true, false)) continue;
                return false;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw KoboldNpc.rethrow(illegalArgumentException);
            }
        }
        return true;
    }

    boolean b(ItemStack itemStack) {
        return this.a(this.Inventory, itemStack, false, true);
    }

    boolean a(ItemStackHandler itemStackHandler, ItemStack itemStack, boolean flag, boolean flag2) {
        ItemStack itemStack2;
        int i;
        for (i = 0; i < itemStackHandler.getSlots(); ++i) {
            int i2;
            block22: {
                block23: {
                    itemStack2 = itemStackHandler.getStackInSlot(i);
                    try {
                        if (itemStack2.getItem() != itemStack.getItem()) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    try {
                        if (itemStack2.getMetadata() != itemStack.getMetadata()) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    i2 = itemStack2.getMaxStackSize();
                    try {
                        try {
                            if (i2 <= itemStack.getCount() + itemStack2.getCount()) break block22;
                            if (flag) break block23;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            throw KoboldNpc.rethrow(illegalArgumentException);
                        }
                        itemStack2.setCount(itemStack2.getCount() + itemStack.getCount());
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                }
                return true;
            }
            int i3 = i2 - itemStack2.getCount();
            itemStack2.setCount(i2);
            itemStack.setCount(itemStack.getCount() - i3);
        }
        for (i = 0; i < itemStackHandler.getSlots(); ++i) {
            block24: {
                itemStack2 = itemStackHandler.getStackInSlot(i);
                try {
                    try {
                        if (itemStack2.getItem() != Items.AIR) continue;
                        if (flag) break block24;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw KoboldNpc.rethrow(illegalArgumentException);
                    }
                    itemStackHandler.setStackInSlot(i, itemStack);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw KoboldNpc.rethrow(illegalArgumentException);
                }
            }
            return true;
        }
        try {
            if (flag) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        try {
            if (!flag2) {
                return false;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        EntityItem entityItem = new EntityItem(this.world);
        entityItem.setItem(itemStack);
        entityItem.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)entityItem);
        return false;
    }

    void b(SoundEvent soundEvent, float f) {
        float f2 = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        double d = f2 / 0.25f;
        float f3 = (float)LerpMath.lerp((double)0.9f, (double)1.1f, d);
        this.playSoundAt(soundEvent, f, f3);
    }

    void b(SoundEvent soundEvent) {
        this.b(soundEvent, 1.0f);
    }

    void a(SoundEvent[] soundEventArray) {
        this.b(soundEventArray, 1.0f);
    }

    void b(SoundEvent[] soundEventArray, float f) {
        this.b(soundEventArray[this.getRNG().nextInt(soundEventArray.length)], f);
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        if (this.ActionController == null) {
            this.initAnimationControllers();
        }
        float f = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        GeckoLibCache.getInstance().parser.setValue("size", f);
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.kobold.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.kobold.blink", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.kobold.null", true, animEvent);
                    break;
                }
                if (this.isRiding()) {
                    this.createAnimationOnce("animation.kobold.sit", true, animEvent);
                    break;
                }
                double d = Math.abs(this.prevPosX - this.posX) + Math.abs(this.prevPosZ - this.posZ);
                if (!((Boolean)this.DataManager.get(BusyKey)).booleanValue() && d > 0.0) {
                    if (this.onGround && Math.abs(Math.abs(this.prevPosY) - Math.abs(this.posY)) < (double)0.1f) {
                        this.rotationYaw = this.rotationYawHead;
                        double d2 = 1.0 + (double)(f * 2.0f);
                        this.MovementController.setAnimationSpeed(d2);
                        if (this.a()) {
                            this.createAnimationOnce("animation.kobold.crouch_walk", true, animEvent);
                            break;
                        }
                        if (((Boolean)this.DataManager.get(aC)).booleanValue()) {
                            this.createAnimationOnce("animation.kobold.run_armed", true, animEvent);
                            break;
                        }
                        if (d > (double)0.2f) {
                            this.createAnimationOnce("animation.kobold.run", true, animEvent);
                            break;
                        }
                        this.createAnimationOnce("animation.kobold.walk", true, animEvent);
                        break;
                    }
                    this.createAnimationOnce("animation.kobold.fly", true, animEvent);
                    break;
                }
                if (this.a()) {
                    this.createAnimationOnce("animation.kobold.crouch_idle", true, animEvent);
                    break;
                }
                this.createAnimationOnce((Boolean)this.DataManager.get(aC) != false ? "animation.kobold.idle_armed" : "animation.kobold.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.createAnimationOnce("animation.kobold.null", true, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.createAnimationOnce("animation.kobold.attack", false, animEvent);
                        break block5;
                    }
                    case RIDE:
                    case SIT: {
                        this.createAnimationOnce("animation.kobold.sit", true, animEvent);
                        break block5;
                    }
                    case MINE: {
                        this.createAnimationOnce("animation.kobold.fall_tree", true, animEvent);
                        break block5;
                    }
                    case PAYMENT: {
                        this.createAnimationOnce("animation.kobold.paymentBackpack", true, animEvent);
                        break block5;
                    }
                    case STARTBLOWJOB: {
                        this.createAnimationOnce("animation.kobold.blowjobStart", false, animEvent);
                        break block5;
                    }
                    case SUCKBLOWJOB_BLINK: {
                        String string = this.flag2 ? "R" : "L";
                        String string2 = this.aT ? "Switch" : "";
                        this.createAnimationOnce("animation.kobold.blowjobSlow" + string + string2, true, animEvent);
                        break block5;
                    }
                    case THRUSTBLOWJOB: {
                        this.createAnimationOnce("animation.kobold.blowjobFast", true, animEvent);
                        break block5;
                    }
                    case CUMBLOWJOB: {
                        this.createAnimationOnce("animation.kobold.blowjobCum", false, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_START: {
                        this.createAnimationOnce("animation.kobold.analStart", false, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_SLOW: {
                        this.createAnimationOnce("animation.kobold.analSoft", true, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_FAST: {
                        this.createAnimationOnce("animation.kobold.analHard", true, animEvent);
                        break block5;
                    }
                    case KOBOLD_ANAL_CUM: {
                        this.createAnimationOnce("animation.kobold.analCum", true, animEvent);
                        break block5;
                    }
                    case SLEEP: {
                        this.createAnimationOnce("animation.kobold.sleep", true, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_START: {
                        this.createAnimationOnce("animation.kobold.mating_press_start", false, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_SOFT: {
                        this.createAnimationOnce("animation.kobold.mating_press_soft", true, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_HARD: {
                        this.createAnimationOnce("animation.kobold.mating_press_hard", true, animEvent);
                        break block5;
                    }
                    case MATING_PRESS_CUM: {
                        this.createAnimationOnce("animation.kobold.mating_press_cum", true, animEvent);
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
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackSound": {
                    this.playSoundEvent(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG);
                    break;
                }
                case "paymentMSG1": {
                    this.a(this.getSexPlayerUuid(), "I'd like to use ur services owo");
                    this.playRandomSound(ModSounds.MISC_PLOB);
                    break;
                }
                case "plob": {
                    this.playRandomSound(ModSounds.MISC_PLOB);
                    break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "paymentDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.U();
                    break;
                }
                case "blowjobStartMSG1": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.625 - (double)entityPlayerSP.getEyeHeight(), -1.0), this.I().floatValue() + 180.0f);
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(this.getSexPlayerUuid().toString(), this.getTargetPos().add(vec3d), this.I().floatValue() + 180.0f, 0.0f));
                    break;
                }
                case "blowjobStartMSG2": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.5, 0.5 - (double)entityPlayerSP.getEyeHeight(), -0.6875), this.I().floatValue() + 180.0f);
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(this.getSexPlayerUuid().toString(), this.getTargetPos().add(vec3d), this.I().floatValue() + 180.0f - 40.0f, 0.0f));
                    break;
                }
                case "lipsound": {
                    if (this.getRNG().nextBoolean()) {
                        this.playRandomSoundWithChance(ModSounds.GIRLS_ALLIE_LIPSOUND, 1.5f);
                    } else {
                        this.playRandomSoundWithChance(ModSounds.GIRLS_JENNY_LIPSOUND, 1.5f);
                    }
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "touch": {
                    this.playRandomSound(ModSounds.MISC_TOUCH);
                    break;
                }
                case "blowjobStartDone": {
                    this.setCurrentAction(GirlAnimationState.SUCKBLOWJOB_BLINK);
                    this.aT = false;
                    this.flag2 = true;
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "switch": {
                    this.aT = this.getRNG().nextBoolean();
                    this.ActionController.clearAnimationCache();
                    break;
                }
                case "endSwitch": {
                    this.aT = false;
                    this.flag2 = !this.flag2;
                    this.ActionController.clearAnimationCache();
                    break;
                }
                case "blowjobFastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.SUCKBLOWJOB_BLINK);
                    break;
                }
                case "cumLoud": {
                    this.playRandomSoundWithChance(ModSounds.MISC_SMALLINSERTS, 3.0f);
                    break;
                }
                case "cumQuiet": {
                    this.playRandomSoundWithChance(ModSounds.MISC_SMALLINSERTS, 1.5f);
                    break;
                }
                case "analCumDone":
                case "blowjobCumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    GuiHud.forceShowHud();
                    break;
                }
                case "analStartDone": {
                    this.setCurrentAction(GirlAnimationState.KOBOLD_ANAL_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "analStartCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.5625 - (double)entityPlayerSP.getEyeHeight(), 0.5625), this.I().floatValue() + 180.0f);
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(this.getSexPlayerUuid().toString(), this.getTargetPos().add(vec3d), this.I().floatValue(), 0.0f));
                    break;
                }
                case "pounding": {
                    this.playRandomSound(ModSounds.MISC_POUNDING);
                    break;
                }
                case "analFastRapid": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    if (this.getCurrentAction() == GirlAnimationState.KOBOLD_ANAL_FAST) {
                        this.ActionController.tickOffset = 0.0;
                    }
                    this.setCurrentAction(GirlAnimationState.KOBOLD_ANAL_FAST);
                    break;
                }
                case "analDone": {
                    if (this.getCurrentAction() != GirlAnimationState.KOBOLD_ANAL_FAST) break;
                    this.setCurrentAction(GirlAnimationState.KOBOLD_ANAL_SLOW);
                    break;
                }
                case "analHard": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "analSoft": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "cum": {
                    this.playRandomSoundWithChance(ModSounds.MISC_SMALLINSERTS, 2.0f);
                    break;
                }
                case "giggle": {
                    this.a(ModSounds.GIRLS_KOBOLD_GIGGLE);
                    break;
                }
                case "moan": {
                    this.a(ModSounds.GIRLS_KOBOLD_MOAN);
                    break;
                }
                case "moanMating": {
                    --this.aN;
                    if (this.aN > 0) break;
                    this.aN = 3;
                    this.a(ModSounds.GIRLS_KOBOLD_MOAN);
                    break;
                }
                case "analHardMSG1": {
                    --this.aN;
                    if (this.aN > 0) break;
                    this.aN = 4;
                    this.a(ModSounds.GIRLS_KOBOLD_MOAN);
                    break;
                }
                case "orgasm": {
                    this.a(ModSounds.GIRLS_KOBOLD_ORGASM);
                    break;
                }
                case "breath": {
                    this.b(ModSounds.GIRLS_KOBOLD_LIGHTBREATHING, 0.5f);
                    break;
                }
                case "haa": {
                    this.b(ModSounds.GIRLS_KOBOLD_HAA, 0.7f);
                    break;
                }
                case "interested": {
                    this.a(ModSounds.GIRLS_KOBOLD_INTERESTED);
                    break;
                }
                case "yep": {
                    this.a(ModSounds.GIRLS_KOBOLD_YEP);
                    break;
                }
                case "bjmoan": {
                    this.b(ModSounds.pickRandomSound(ModSounds.GIRLS_KOBOLD_BJMOAN));
                    break;
                }
                case "blowjobStartbreath": {
                    int i = this.getRNG().nextInt(3);
                    this.b(ModSounds.GIRLS_KOBOLD_LIGHTBREATHING[i]);
                    break;
                }
                case "matingCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = new Vec3d(0.0, 0.4375 - (double)entityPlayerSP.eyeHeight, -0.6875);
                    vec3d = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    vec3d = vec3d.add(this.getTargetPos());
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(entityPlayerSP.getPersistentID().toString(), vec3d, this.I().floatValue() + 180.0f, 10.0f));
                    break;
                }
                case "mating_press_startDone": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.showHud();
                    }
                }
                case "mating_press_hardDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.setCurrentAction(GirlAnimationState.MATING_PRESS_SOFT);
                    break;
                }
                case "mating_press_softReady": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.04f);
                    }
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.MATING_PRESS_HARD);
                    break;
                }
                case "mating_press_hardReady": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.04f);
                    }
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "mating_cum_cam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    Vec3d vec3d = new Vec3d(0.0, 1.1875 - (double)entityPlayerSP.eyeHeight, 0.125);
                    vec3d = VectorMath.rotateYaw(vec3d, this.I().floatValue() + 180.0f);
                    vec3d = vec3d.add(this.getTargetPos());
                    NetworkHandler.channel.sendToServer((IMessage)new PacketTeleportPlayer(entityPlayerSP.getPersistentID().toString(), vec3d, this.I().floatValue() + 180.0f, 70.0f));
                    break;
                }
                case "cumMsg": {
                    this.a("I.. hope I am satisfying you sir");
                    this.b(ModSounds.GIRLS_KOBOLD_SAD[this.getRNG().nextInt(1)]);
                    break;
                }
                case "renderEgg": {
                    this.Q = true;
                    this.playRandomSoundWithChance(ModSounds.MISC_PLOB, 0.5f);
                    break;
                }
                case "mating_press_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                }
            }
        };
        this.MovementController.transitionLengthTicks = 10.0;
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    public int getSizeInventory() {
        return 27;
    }

    public boolean isEmpty() {
        return false;
    }

    public ItemStack getStackInSlot(int i) {
        try {
            if (i >= this.Inventory.getSlots()) {
                return ItemStack.EMPTY;
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw KoboldNpc.rethrow(illegalArgumentException);
        }
        return this.Inventory.getStackInSlot(i);
    }

    public ItemStack decrStackSize(int i, int i2) {
        return this.Inventory.extractItem(i, i2, false);
    }

    public ItemStack removeStackFromSlot(int i) {
        return this.Inventory.extractItem(i, this.Inventory.getStackInSlot(i).getCount(), false);
    }

    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.Inventory.setStackInSlot(i, itemStack);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void markDirty() {
    }

    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    public void openInventory(EntityPlayer entityPlayer) {
    }

    public void closeInventory(EntityPlayer entityPlayer) {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    public int getField(int i) {
        return i;
    }

    public void setField(int i, int i2) {
    }

    public int getFieldCount() {
        return 0;
    }

    public void clear() {
    }

    private static IllegalArgumentException rethrow(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    public static class DeathLootHandler {
        int DeathCount = 0;

        @SubscribeEvent
        public void onLivingDeath(LivingDeathEvent livingDeathEvent) {
            if (livingDeathEvent.getEntityLiving() instanceof KoboldNpc) {
                KoboldNpc kobold = (KoboldNpc)livingDeathEvent.getEntityLiving();
                try {
                    if (kobold.world.isRemote) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
                }
                for (int i = 0; i < kobold.Inventory.getSlots(); ++i) {
                    ItemStack itemStack = kobold.Inventory.getStackInSlot(i);
                    try {
                        if (itemStack.getItem() == Items.AIR) continue;
                        kobold.dropItem(itemStack.getItem(), itemStack.getCount());
                        continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
                    }
                }
            }
        }

        @SubscribeEvent
        public void onLivingHurt(LivingHurtEvent livingHurtEvent) {
            EntityPlayer entityPlayer;
            Entity entity = livingHurtEvent.getEntity();
            World world = entity.getEntityWorld();
            try {
                if (world.isRemote) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            try {
                if (!(entity instanceof KoboldNpc)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            KoboldNpc kobold = (KoboldNpc)entity;
            Optional optional = (Optional)kobold.getDataManager().get(aL);
            try {
                if (!optional.isPresent()) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            Entity entity2 = livingHurtEvent.getSource().getTrueSource();
            try {
                if (entity2 == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            try {
                if (!(entity2 instanceof EntityLivingBase)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            if (entity2 instanceof EntityPlayer) {
                entityPlayer = (EntityPlayer)entity2;
                try {
                    if (entityPlayer.capabilities.isCreativeMode) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
                }
                try {
                    if (entityPlayer.equals((Object)kobold.getSexPlayer())) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
                }
            }
            entityPlayer = kobold.getSexPlayer();
            try {
                if (entityPlayer != null) {
                    entityPlayer.sendStatusMessage((ITextComponent)new TextComponentString(TextFormatting.RED + "Your Tribe is under Attack!"), true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            GirlHomeBuilder.addNearbyEntity((UUID)optional.get(), (EntityLivingBase)entity2);
        }

        @SubscribeEvent
        public void a(WorldEvent.Unload unload) {
            try {
                for (GirlEntity girl : GirlEntity.getAllGirls()) {
                    try {
                        if (!(girl instanceof KoboldNpc)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
                    }
                    KoboldNpc kobold = (KoboldNpc)girl;
                    Optional optional = (Optional)kobold.getDataManager().get(aL);
                    try {
                        if (!optional.isPresent()) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
                    }
                    try {
                        if (!GirlHomeBuilder.isKoboldOfGirl((UUID)optional.get(), kobold)) {
                            continue;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
                    }
                    kobold.s((UUID)optional.get());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }

        @SubscribeEvent
        public void a(LivingHurtEvent livingHurtEvent) {
            try {
                if (livingHurtEvent.getSource() != DamageSource.IN_WALL) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            Entity entity = livingHurtEvent.getEntity();
            try {
                if (entity instanceof KoboldNpc) {
                    entity.setPosition(entity.posX, entity.posY + 1.0, entity.posZ);
                    livingHurtEvent.setCanceled(true);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(TickEvent.ClientTickEvent clientTickEvent) {
            WorldClient worldClient = Minecraft.getMinecraft().world;
            try {
                if (worldClient == null) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
            try {
                if (++this.DeathCount % 20 == 0) {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketGetTribeUIValues());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw KoboldNpc.DeathLootHandler.rethrow(concurrentModificationException);
            }
        }

        private static ConcurrentModificationException rethrow(ConcurrentModificationException concurrentModificationException) {
            return concurrentModificationException;
        }
    }
}

