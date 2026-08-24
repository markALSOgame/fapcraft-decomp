/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector4d
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWanderAvoidWater
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import javax.vecmath.Vector4d;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
public class BiaNpc
extends InventoryGirlEntity
implements VoidCallback,
fg {
    static final int ae = 3;
    public boolean SeekingBed = false;
    int WalkTicks = 0;
    boolean ArrivedAtBed = false;
    int SitTicks = 0;
    boolean FirstTick = true;
    int ActionTimer = -1;
    boolean MenuPending = false;
    final int[] BedSideYaws = new int[]{0, 180, -90, 90};
    final Vec3d[][] BedSidePositions = new Vec3d[][]{{new Vec3d(0.5, 0.0, -0.5), new Vec3d(0.0, 0.0, -1.0)}, {new Vec3d(0.5, 0.0, 1.5), new Vec3d(0.0, 0.0, 1.0)}, {new Vec3d(-0.5, 0.0, 0.5), new Vec3d(-1.0, 0.0, 0.0)}, {new Vec3d(1.5, 0.0, 0.5), new Vec3d(1.0, 0.0, 0.0)}};
    int DanceStep = 1;

    public BiaNpc(World world) {
        super(world);
        this.setSize(0.49f, 1.65f);
        this.P = 140;
        this.O = 50;
        this.K = 140;
        this.V = new Vec3d(0.0, -0.029999997854232782, -0.2);
    }

    @Override
    public String c() {
        return "Bia";
    }

    @Override
    public float getRenderLabelOffset() {
        return -0.2f;
    }

    @Override
    public void c() {
        this.a("I am living here now nya~");
        this.a(ModSounds.GIRLS_BIA_BREATH, new int[0]);
    }

    @Override
    public void b() {
        this.SeekingBed = true;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block19: {
            GirlAnimationState girlAnimationState2;
            block17: {
                block16: {
                    girlAnimationState2 = this.getCurrentAction();
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.ANAL_CUM && girlAnimationState2 != GirlAnimationState.PRONE_DOGGY_CUM) break block16;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                        this.DataManager.set(h, (Object)"");
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                }
                try {
                    block18: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.ANAL_CUM) break block17;
                                if (girlAnimationState == GirlAnimationState.ANAL_FAST) break block18;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw BiaNpc.rethrow(nullPointerException);
                            }
                            if (girlAnimationState != GirlAnimationState.ANAL_SLOW) break block17;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                    }
                    return;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
            }
            try {
                block20: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.PRONE_DOGGY_CUM) break block19;
                            if (girlAnimationState == GirlAnimationState.PRONE_DOGGY_HARD) break block20;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                        if (girlAnimationState != GirlAnimationState.PRONE_DOGGY_SOFT) break block19;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                }
                return;
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
        }
        super.setCurrentAction(girlAnimationState);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return GirlLootTables.BiaLootTable;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void updateAITasks() {
        block25: {
            block27: {
                block28: {
                    block23: {
                        block24: {
                            block29: {
                                try {
                                    super.updateAITasks();
                                    if (this.FirstTick) {
                                        this.setNoGravity(false);
                                        this.noClip = false;
                                        this.FirstTick = false;
                                    }
                                }
                                catch (NullPointerException error) {
                                    throw BiaNpc.rethrow(error);
                                }
                                if (!this.SeekingBed) break block23;
                                ++this.WalkTicks;
                                if (this.getPositionVector().equals((Object)this.getTargetPos())) ** GOTO lbl24
                                break block29;
                                catch (NullPointerException error2) {
                                    throw BiaNpc.rethrow(error2);
                                }
                            }
                            try {
                                block30: {
                                    if (this.WalkTicks <= 40) break block24;
                                    break block30;
                                    catch (NullPointerException error3) {
                                        throw BiaNpc.rethrow(error3);
                                    }
                                }
                                this.SeekingBed = false;
                                this.WalkTicks = 0;
                                this.b(this.world.getMinecraftServer().getPlayerList().getPlayerByUUID((UUID)this.getSexPlayerUuid()).rotationYaw + 180.0f);
                                this.DataManager.set(BiaNpc.BusyKey, (Object)true);
                                this.getNavigator().clearPath();
                                this.U();
                                break block23;
                            }
                            catch (NullPointerException error4) {
                                throw BiaNpc.rethrow(error4);
                            }
                        }
                        this.rotationYaw = this.I().floatValue();
                        try {
                            BiaNpc.TargetPosKey.equals(null);
                        }
                        catch (NullPointerException error5) {
                            this.setTargetPos(this.getPlayerFrontPos());
                        }
                        this.setNoGravity(false);
                        vec3d = LerpMath.stepTowards(this.getPositionVector(), this.getTargetPos(), 40 - this.WalkTicks);
                        this.setPosition(vec3d.x, vec3d.y, vec3d.z);
                    }
                    try {
                        try {
                            block26: {
                                try {
                                    try {
                                        if (!this.ArrivedAtBed) break block25;
                                        if (this.getPositionVector().distanceTo(this.getTargetPos()) < 0.6) break block26;
                                    }
                                    catch (NullPointerException error6) {
                                        throw BiaNpc.rethrow(error6);
                                    }
                                    if (this.SitTicks <= 200) break block27;
                                }
                                catch (NullPointerException error7) {
                                    throw BiaNpc.rethrow(error7);
                                }
                            }
                            this.ArrivedAtBed = false;
                            this.DataManager.set(BiaNpc.BusyKey, (Object)true);
                            this.SitTicks = 0;
                            this.noClip = true;
                            this.setNoGravity(true);
                            this.motionX = 0.0;
                            this.motionY = 0.0;
                            this.motionZ = 0.0;
                            if (!"anal".equals(this.DataManager.get(BiaNpc.BlowjobStageKey))) break block28;
                        }
                        catch (NullPointerException error8) {
                            throw BiaNpc.rethrow(error8);
                        }
                        this.setCurrentAction(GirlAnimationState.ANAL_PREPARE);
                        this.setOutfitIndex(0);
                        break block25;
                    }
                    catch (NullPointerException error9) {
                        throw BiaNpc.rethrow(error9);
                    }
                }
                this.setCurrentAction(GirlAnimationState.SITDOWN);
                break block25;
            }
            try {
                try {
                    ++this.SitTicks;
                    if (this.SitTicks != 60 && this.SitTicks != 120) break block25;
                }
                catch (NullPointerException error10) {
                    throw BiaNpc.rethrow(error10);
                }
                this.getNavigator().clearPath();
                this.getNavigator().tryMoveToXYZ(this.getTargetPos().x, this.getTargetPos().y, this.getTargetPos().z, 0.35);
            }
            catch (NullPointerException error11) {
                throw BiaNpc.rethrow(error11);
            }
        }
    }

    public boolean processInteract(EntityPlayer entityPlayer, EnumHand enumHand) {
        block15: {
            boolean flag;
            try {
                if (super.processInteract(entityPlayer, enumHand)) {
                    return true;
                }
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
            try {
                if (this.getCurrentAction() == GirlAnimationState.SITDOWNIDLE) {
                    return true;
                }
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
            ItemStack itemStack = entityPlayer.getHeldItem(enumHand);
            try {
                flag = itemStack.getItem() == Items.NAME_TAG;
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
            boolean flag2 = flag;
            try {
                if (flag2) {
                    itemStack.interactWithEntity(entityPlayer, (EntityLivingBase)this, enumHand);
                    return true;
                }
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
            try {
                try {
                    if (!this.world.isRemote || this.b(entityPlayer)) break block15;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
                this.acceptPlayer(I18n.format((String)"bia.dialogue.busy", (Object[])new Object[0]));
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
        }
        return true;
    }

    @Override
    public boolean b(EntityPlayer entityPlayer) {
        block8: {
            String string;
            String[] stringArray;
            block11: {
                block10: {
                    try {
                        try {
                            block9: {
                                try {
                                    try {
                                        if (this.getSexPlayerUuid() != null) break block8;
                                        if (!this.J()) break block9;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw BiaNpc.rethrow(nullPointerException);
                                    }
                                    if (!((String)this.DataManager.get(v)).equals(Minecraft.getMinecraft().player.getPersistentID().toString())) break block8;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw BiaNpc.rethrow(nullPointerException);
                                }
                            }
                            String[] stringArray2 = new String[3];
                            String[] stringArray3 = stringArray2;
                            stringArray = stringArray2;
                            int i = 0;
                            if ((Integer)this.DataManager.get(D) != 1) break block10;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                        string = "action.names.strip";
                        break block11;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                }
                string = "action.names.dressup";
            }
            stringArray3[i] = string;
            stringArray[1] = "action.names.talk";
            stringArray[2] = "action.names.headpat";
            String[] stringArray4 = stringArray;
            BiaNpc.openActionMenu(entityPlayer, this, stringArray4, true);
            return true;
        }
        return false;
    }

    void b(EntityPlayer entityPlayer) {
        BiaNpc.openActionMenu(entityPlayer, this, new String[]{"action.names.anal", "doggy"}, false);
    }

    @Override
    public void ac() {
        block4: {
            try {
                try {
                    if (!this.Q() || this.MenuPending) break block4;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
                this.resetAimTarget();
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
        }
        this.MenuPending = false;
    }

    @Override
    public void onUpdate() {
        block8: {
            try {
                try {
                    try {
                        try {
                            super.onUpdate();
                            if (!this.world.isRemote || !this.isOwnedByLocalPlayer()) break block8;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                        if (this.getCurrentAction() != GirlAnimationState.PRONE_DOGGY_INTRO) break block8;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                    if (GuiTransitionScreen.isTransitionActive()) break block8;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
                GuiHud.showHud();
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
        }
        this.d();
    }

    @Override
    protected void resetActionTimer() {
        super.resetActionTimer();
        this.ActionTimer = -1;
    }

    void d() {
        float f;
        EntityPlayer entityPlayer;
        block27: {
            block29: {
                block28: {
                    GirlAnimationState girlAnimationState;
                    block24: {
                        block26: {
                            block25: {
                                block23: {
                                    girlAnimationState = this.getCurrentAction();
                                    try {
                                        try {
                                            if (girlAnimationState == GirlAnimationState.ANAL_WAIT || girlAnimationState == GirlAnimationState.SITDOWNIDLE) break block23;
                                        }
                                        catch (NullPointerException nullPointerException) {
                                            throw BiaNpc.rethrow(nullPointerException);
                                        }
                                        return;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw BiaNpc.rethrow(nullPointerException);
                                    }
                                }
                                entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 10.0);
                                try {
                                    if (entityPlayer == null) {
                                        return;
                                    }
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw BiaNpc.rethrow(nullPointerException);
                                }
                                try {
                                    if (entityPlayer.getDistance((Entity)this) > 1.0f) {
                                        return;
                                    }
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw BiaNpc.rethrow(nullPointerException);
                                }
                                try {
                                    try {
                                        if (this.ActionTimer != -1) break block24;
                                        if (!this.world.isRemote) break block25;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw BiaNpc.rethrow(nullPointerException);
                                    }
                                    GuiTransitionScreen.startTransition();
                                    AnimationInputLock.setAnimationLocked(false);
                                    break block26;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw BiaNpc.rethrow(nullPointerException);
                                }
                            }
                            this.handleGirlUuidEvent(entityPlayer.getPersistentID());
                        }
                        this.ActionTimer = j;
                        return;
                    }
                    try {
                        if (--this.ActionTimer > 0) {
                            return;
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                    try {
                        this.ActionTimer = -1;
                        entityPlayer.noClip = true;
                        entityPlayer.setNoGravity(true);
                        if (girlAnimationState != GirlAnimationState.ANAL_WAIT) break block27;
                        if (this.world.isRemote) break block28;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                    this.setCurrentAction(GirlAnimationState.ANAL_START);
                    Vec3d vec3d = this.getTargetPos().add(VectorMath.rotatePitch(-0.3, -1.0, -0.5, this.I().floatValue()));
                    entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
                    break block29;
                }
                try {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.showHud();
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
            }
            return;
        }
        entityPlayer.rotationYaw = f = this.I().floatValue();
        entityPlayer.rotationPitch = 60.0f;
        if (!this.world.isRemote) {
            this.setOutfitIndex(0);
            this.setCurrentAction(GirlAnimationState.PRONE_DOGGY_INTRO);
            Vec3d vec3d = this.getTargetPos();
            Vec3d vec3d2 = vec3d.add(VectorMath.rotatePitch(0.0, 0.0, 1.0, f));
            this.setTargetPos(vec3d2);
            Vec3d vec3d3 = vec3d.add(VectorMath.rotatePitch(0.0, 1.1875 - (double)entityPlayer.getEyeHeight(), 0.5, f));
            entityPlayer.setPositionAndUpdate(vec3d3.x, vec3d3.y, vec3d3.z);
            this.a(true);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void ag() {
        try {
            super.resetTickOffset();
            if (this.getCurrentAction() != GirlAnimationState.PRONE_DOGGY_HARD) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        int i = this.DanceStep;
        try {
            do {
                this.DanceStep = this.getRNG().nextInt(3) + 1;
            } while (i == this.DanceStep);
            return;
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
    }

    @Override
    public void initAITasks() {
        this.WanderAI = new EntityAIWanderAvoidWater((EntityCreature)this, 0.35);
        this.WatchPlayerAI = new GirlWatchAi((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f);
        this.tasks.addTask(5, (EntityAIBase)this.WatchPlayerAI);
        this.tasks.addTask(5, (EntityAIBase)this.WanderAI);
    }

    /*
     * Exception decompiling
     */
    @Override
    public void a(String string, UUID uuid) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[SWITCH]
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

    public void onDeath(DamageSource damageSource) {
        try {
            super.onDeath(damageSource);
            if (this.world.isRemote) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Blocks.WOOL, this.getRNG().nextInt(4), 12));
        this.world.spawnEntity((Entity)entityItem);
    }

    void acceptPlayer(UUID uUID) {
        this.a(true, true, uUID);
        AnimationInputLock.setAnimationLocked(false);
    }

    Vector4d a() {
        BlockPos blockPos;
        block15: {
            blockPos = null;
            int i = 0;
            while (!this.a(blockPos)) {
                blockPos = this.a(this.getPosition(), i);
                if (++i != 50) continue;
            }
            try {
                try {
                    if (blockPos != null && i != 50) break block15;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
                this.a(ModSounds.GIRLS_BIA_BREATH[2]);
                this.acceptPlayer(I18n.format((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                return null;
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
        }
        this.tasks.removeTask((EntityAIBase)this.WanderAI);
        this.tasks.removeTask((EntityAIBase)this.WatchPlayerAI);
        Vec3d vec3d = new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
        int i3 = -1;
        for (int i4 = 0; i4 < this.BedSidePositions.length; ++i4) {
            Vec3d vec3d2 = vec3d.add(this.BedSidePositions[i4][1]);
            Vec3d vec3d3 = vec3d.subtract(this.BedSidePositions[i4][1]);
            Block block = this.world.getBlockState(new BlockPos(vec3d2.x, vec3d2.y, vec3d2.z)).getBlock();
            try {
                try {
                    if (block != Blocks.AIR || !BedLogic.isBedBlock(this.world, new BlockPos(vec3d3))) {
                        continue;
                    }
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
            if (i3 == -1) {
                i3 = i4;
                continue;
            }
            double d = this.getPosition().distanceSq(vec3d.add((Vec3d)this.BedSidePositions[i3][0]).x, vec3d.add((Vec3d)this.BedSidePositions[i3][0]).y, vec3d.add((Vec3d)this.BedSidePositions[i3][0]).z);
            double d2 = this.getPosition().distanceSq(vec3d.add((Vec3d)this.BedSidePositions[i4][0]).x, vec3d.add((Vec3d)this.BedSidePositions[i4][0]).y, vec3d.add((Vec3d)this.BedSidePositions[i4][0]).z);
            if (!(d2 < d)) continue;
            i3 = i4;
        }
        try {
            if (i3 == -1) {
                this.a(ModSounds.GIRLS_BIA_BREATH[2]);
                this.acceptPlayer(I18n.format((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        Vec3d vec3d4 = vec3d.add(this.BedSidePositions[i3][0]);
        return new Vector4d(vec3d4.x, vec3d4.y, vec3d4.z, (double)this.BedSideYaws[i3]);
    }

    boolean a(BlockPos blockPos) {
        block22: {
            block21: {
                block20: {
                    block19: {
                        try {
                            if (blockPos == null) {
                                return false;
                            }
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                        try {
                            try {
                                if (!BedLogic.isBedBlock(this.world, blockPos.north()) || !this.world.isAirBlock(blockPos.south())) break block19;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw BiaNpc.rethrow(nullPointerException);
                            }
                            return true;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                    }
                    try {
                        try {
                            if (!BedLogic.isBedBlock(this.world, blockPos.east()) || !this.world.isAirBlock(blockPos.west())) break block20;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw BiaNpc.rethrow(nullPointerException);
                        }
                        return true;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                }
                try {
                    try {
                        if (!BedLogic.isBedBlock(this.world, blockPos.south()) || !this.world.isAirBlock(blockPos.north())) break block21;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                    return true;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
            }
            try {
                try {
                    if (!BedLogic.isBedBlock(this.world, blockPos.west()) || !this.world.isAirBlock(blockPos.east())) break block22;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
                return true;
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
        }
        return false;
    }

    Vector4d b() {
        BlockPos blockPos = this.acceptPlayer(this.getPosition());
        try {
            if (blockPos == null) {
                this.a(ModSounds.GIRLS_BIA_BREATH[2]);
                this.acceptPlayer(I18n.format((String)"jenny.dialogue.nobedinsight", (Object[])new Object[0]));
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        this.tasks.removeTask((EntityAIBase)this.WanderAI);
        this.tasks.removeTask((EntityAIBase)this.WatchPlayerAI);
        Vec3d vec3d = new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
        int i = -1;
        for (int i3 = 0; i3 < this.BedSidePositions.length; ++i3) {
            block9: {
                Vec3d vec3d2 = vec3d.add(this.BedSidePositions[i3][1]);
                try {
                    if (this.world.getBlockState(new BlockPos(vec3d2.x, vec3d2.y, vec3d2.z)).getBlock() != Blocks.AIR) continue;
                    if (i != -1) break block9;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
                i = i3;
                continue;
            }
            double d = this.getPosition().distanceSq(vec3d.add((Vec3d)this.BedSidePositions[i][0]).x, vec3d.add((Vec3d)this.BedSidePositions[i][0]).y, vec3d.add((Vec3d)this.BedSidePositions[i][0]).z);
            double d2 = this.getPosition().distanceSq(vec3d.add((Vec3d)this.BedSidePositions[i3][0]).x, vec3d.add((Vec3d)this.BedSidePositions[i3][0]).y, vec3d.add((Vec3d)this.BedSidePositions[i3][0]).z);
            if (!(d2 < d)) continue;
            i = i3;
        }
        try {
            if (i == -1) {
                this.a(ModSounds.GIRLS_BIA_BREATH[2]);
                this.acceptPlayer(I18n.format((String)"jenny.dialogue.bedobscured", (Object[])new Object[0]));
                return null;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        Vec3d vec3d3 = vec3d.add(this.BedSidePositions[i][0]);
        return new Vector4d(vec3d3.x, vec3d3.y, vec3d3.z, (double)this.BedSideYaws[i]);
    }

    @Override
    public void a() {
        Vector4d vector4d;
        String string = (String)this.DataManager.get(h);
        try {
            vector4d = string.equals("anal") ? this.b() : this.a();
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        Vector4d vector4d2 = vector4d;
        try {
            if (vector4d2 == null) {
                return;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        Vec3d vec3d = new Vec3d(vector4d2.getX(), vector4d2.getY(), vector4d2.getZ());
        this.b((float)vector4d2.getW());
        this.setTargetPos(vec3d);
        this.AimYaw = this.I().floatValue();
        this.getNavigator().clearPath();
        this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.35);
        this.ArrivedAtBed = true;
        this.SitTicks = 0;
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.ANAL_SLOW) {
                return GirlAnimationState.ANAL_FAST;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.PRONE_DOGGY_INTRO) {
                return GirlAnimationState.PRONE_DOGGY_INSERT;
            }
        }
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.ANAL_SLOW && girlAnimationState != GirlAnimationState.ANAL_FAST) break block8;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw BiaNpc.rethrow(nullPointerException);
                    }
                    return GirlAnimationState.ANAL_CUM;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.PRONE_DOGGY_SOFT && girlAnimationState != GirlAnimationState.PRONE_DOGGY_HARD) break block9;
                }
                catch (NullPointerException nullPointerException) {
                    throw BiaNpc.rethrow(nullPointerException);
                }
                return GirlAnimationState.PRONE_DOGGY_CUM;
            }
            catch (NullPointerException nullPointerException) {
                throw BiaNpc.rethrow(nullPointerException);
            }
        }
        return null;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void U() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 3[SWITCH]
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
    public float T() {
        return 35.0f;
    }

    @Override
    public float ai() {
        return 140.0f;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 10[SWITCH]
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
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 23[SWITCH]
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

    private static NullPointerException rethrow(NullPointerException nullPointerException) {
        return nullPointerException;
    }
}

