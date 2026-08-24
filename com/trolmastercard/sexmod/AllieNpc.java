/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
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
public class AllieNpc
extends GirlEntity {
    public static final int PortalParticleCount = 300;
    public static final int TailBoneCount = 8;
    public static final Vec3d EntitySize = new Vec3d(0.5, 1.0, 0.0);
    float ConversionTimer = 1.0f;
    public boolean PlayIntroAnim = false;
    public static final DataParameter<ItemStack> SpawnItemStack = EntityDataManager.createKey(AllieNpc.class, (DataSerializer)DataSerializers.ITEM_STACK).getSerializer().createKey(111);
    boolean SpawnPortalsPending = true;
    int T = 1;
    int L = 1;
    boolean M = false;
    boolean MenuOpen = false;

    public AllieNpc(World world) {
        super(world);
        this.setSize((float)AllieNpc.EntitySize.x, (float)AllieNpc.EntitySize.y);
    }

    public AllieNpc(World world, ItemStack itemStack) {
        this(world);
        this.DataManager.set(SpawnItemStack, (Object)itemStack);
    }

    @Override
    public String c() {
        return "Allie";
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.0f;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.DataManager.register(SpawnItemStack, (Object)ItemStack.EMPTY);
    }

    public boolean isFirstTimeWithItem() {
        boolean flag;
        NBTTagCompound nBTTagCompound = ((ItemStack)this.DataManager.get(SpawnItemStack)).getTagCompound();
        try {
            if (nBTTagCompound == null) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            flag = nBTTagCompound.getInteger("sexmodUses") == 1;
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    public void updateAITasks() {
        try {
            super.updateAITasks();
            if (this.getCurrentAction() == GirlAnimationState.NULL) {
                this.world.removeEntity((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        UUID uUID = this.getSexPlayerUuid();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                this.world.removeEntity((Entity)this);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onActionGuiClosed() {
        try {
            if (!this.MenuOpen) {
                this.PlayIntroAnim = true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
    }

    @Override
    public void onUpdate() {
        block17: {
            block18: {
                try {
                    try {
                        try {
                            try {
                                super.onUpdate();
                                if (this.ConversionTimer == 1.0f || this.ConversionTimer == -69.0f) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AllieNpc.rethrow(runtimeException);
                            }
                            if (!(this.ConversionTimer <= 0.0f)) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                        if (!this.isOwnedByLocalPlayer()) break block18;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                    NetworkHandler.channel.sendToServer((IMessage)new PacketUploadInventory(this.isFirstTimeWithItem()));
                    AnimationInputLock.setAnimationLocked(true);
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
            }
            this.ConversionTimer = -69.0f;
        }
        try {
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.PlayIntroAnim) {
                this.getDisplayName();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            if (this.SpawnPortalsPending) {
                this.d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        this.spawnTailParticles();
    }

    void spawnTailParticles() {
        try {
            if (this.ticksExisted % 10 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        int i = this.getRNG().nextInt(8);
        Vec3d vec3d = this.b("tail" + i).add(this.getPositionVector());
        this.world.spawnParticle(EnumParticleTypes.PORTAL, vec3d.x, vec3d.y, vec3d.z, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, new int[0]);
    }

    @SideOnly(value=Side.CLIENT)
    void d() {
        this.SpawnPortalsPending = false;
        BedLogic.spawnParticles(this.world, EnumParticleTypes.PORTAL, this.getPositionVector(), 300, 0.75, 1.5);
    }

    @SideOnly(value=Side.CLIENT)
    void c() {
        this.openActionMenu((EntityPlayer)Minecraft.getMinecraft().player);
        this.PlayIntroAnim = false;
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        this.MenuOpen = false;
        String[] stringArray = new String[]{"action.names.makemerichallie", "action.names.deepthroat", "Reverse cowgirl"};
        AllieNpc.openActionMenu(entityPlayer, this, stringArray, false);
        return true;
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.DEEPTHROAT_SLOW) {
                return GirlAnimationState.DEEPTHROAT_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) {
                return GirlAnimationState.REVERSE_COWGIRL_FAST_START;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block12: {
            block10: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.DEEPTHROAT_FAST && girlAnimationState != GirlAnimationState.DEEPTHROAT_SLOW) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                    return GirlAnimationState.DEEPTHROAT_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
            }
            try {
                block11: {
                    try {
                        try {
                            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW || girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                }
                return GirlAnimationState.REVERSE_COWGIRL_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw AllieNpc.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block22: {
            block20: {
                block18: {
                    try {
                        block19: {
                            try {
                                try {
                                    if (this.getCurrentAction() != GirlAnimationState.DEEPTHROAT_CUM) break block18;
                                    if (girlAnimationState == GirlAnimationState.DEEPTHROAT_FAST) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw AllieNpc.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.DEEPTHROAT_SLOW) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AllieNpc.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                try {
                                    if (this.getCurrentAction() != GirlAnimationState.REVERSE_COWGIRL_CUM) break block20;
                                    if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) break block21;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw AllieNpc.rethrow(runtimeException);
                                }
                                if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AllieNpc.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (this.world.isRemote || girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_START) break block22;
                }
                catch (RuntimeException runtimeException) {
                    throw AllieNpc.rethrow(runtimeException);
                }
                this.a();
            }
            catch (RuntimeException runtimeException) {
                throw AllieNpc.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    void a() {
        EntityPlayer entityPlayer = this.S();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        Vec3d vec3d = this.getTargetPos();
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 6[SWITCH]
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
            throw AllieNpc.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 26[SWITCH]
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

    @Override
    public void performAction(String string, UUID uUID) {
        GirlAnimationState girlAnimationState;
        AllieNpc allie;
        String string2;
        String string3;
        AllieNpc allie2;
        block8: {
            GirlAnimationState girlAnimationState2;
            AllieNpc allie3;
            block10: {
                block9: {
                    try {
                        try {
                            this.MenuOpen = true;
                            if (!"action.names.makemerichallie".equals(string)) break block8;
                            allie3 = this;
                            if (!this.isFirstTimeWithItem()) break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AllieNpc.rethrow(runtimeException);
                        }
                        girlAnimationState2 = GirlAnimationState.RICH_FIRST_TIME;
                        break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AllieNpc.rethrow(runtimeException);
                    }
                }
                girlAnimationState2 = GirlAnimationState.RICH_NORMAL;
            }
            allie3.b(girlAnimationState2);
            return;
        }
        try {
            allie2 = this;
            string3 = "animationFollowUp";
            string2 = "action.names.deepthroat".equals(string) ? "deepthroat" : "reverse_cowgirl";
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        try {
            allie2.a(string3, string2);
            allie = this;
            girlAnimationState = this.isFirstTimeWithItem() ? GirlAnimationState.ALLIE_PREPARE_FIRST_TIME : GirlAnimationState.ALLIE_PREPARE_NORMAL;
        }
        catch (RuntimeException runtimeException) {
            throw AllieNpc.rethrow(runtimeException);
        }
        allie.b(girlAnimationState);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

