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
    public String getDisplayName() {
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
    public void setCurrentAction(GirlAnimationState girlAnimationState) {
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
                        this.DataManager.set(BlowjobStageKey, (Object)"");
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

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        if (this.FirstTick) {
            this.setNoGravity(false);
            this.noClip = false;
            this.FirstTick = false;
        }
        if (this.SeekingBed) {
            ++this.WalkTicks;
            if (this.getPositionVector().equals((Object)this.getTargetPos()) || this.WalkTicks > 40) {
                this.SeekingBed = false;
                this.WalkTicks = 0;
                this.b(this.world.getMinecraftServer().getPlayerList().getPlayerByUUID((UUID)this.getSexPlayerUuid()).rotationYaw + 180.0f);
                this.DataManager.set(BiaNpc.BusyKey, (Object)true);
                this.getNavigator().clearPath();
                this.U();
            } else {
                this.rotationYaw = this.I().floatValue();
                try {
                    BiaNpc.TargetPosKey.equals(null);
                }
                catch (NullPointerException error5) {
                    this.setTargetPos(this.getPlayerFrontPos());
                }
                this.setNoGravity(false);
                Vec3d vec3d = LerpMath.stepTowards(this.getPositionVector(), this.getTargetPos(), 40 - this.WalkTicks);
                this.setPosition(vec3d.x, vec3d.y, vec3d.z);
            }
        }
        if (this.ArrivedAtBed) {
            if (this.getPositionVector().distanceTo(this.getTargetPos()) < 0.6 || this.SitTicks > 200) {
                this.ArrivedAtBed = false;
                this.DataManager.set(BiaNpc.BusyKey, (Object)true);
                this.SitTicks = 0;
                this.noClip = true;
                this.setNoGravity(true);
                this.motionX = 0.0;
                this.motionY = 0.0;
                this.motionZ = 0.0;
                if ("anal".equals(this.DataManager.get(BiaNpc.BlowjobStageKey))) {
                    this.setCurrentAction(GirlAnimationState.ANAL_PREPARE);
                    this.setOutfitIndex(0);
                } else {
                    this.setCurrentAction(GirlAnimationState.SITDOWN);
                }
            } else {
                ++this.SitTicks;
                if (this.SitTicks == 60 || this.SitTicks == 120) {
                    this.getNavigator().clearPath();
                    this.getNavigator().tryMoveToXYZ(this.getTargetPos().x, this.getTargetPos().y, this.getTargetPos().z, 0.35);
                }
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
                    if (!this.world.isRemote || this.canInteract(entityPlayer)) break block15;
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
    public boolean canInteract(EntityPlayer entityPlayer) {
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
                                    if (!((String)this.DataManager.get(MasterUuidKey)).equals(Minecraft.getMinecraft().player.getPersistentID().toString())) break block8;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw BiaNpc.rethrow(nullPointerException);
                                }
                            }
                            String[] stringArray2 = new String[3];
                            String[] stringArray3 = stringArray2;
                            stringArray = stringArray2;
                            int i = 0;
                            if ((Integer)this.DataManager.get(OutfitIndexKey) != 1) break block10;
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
            stringArray3[0] = string;
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

    @Override
    public void a(String string, UUID uuid) {
        super.a(string, uuid);
        switch (string) {
            case "action.names.talk": {
                this.handleGirlUuidEvent(Minecraft.getMinecraft().player.getPersistentID());
                this.a("playerSheHasSexWith", Minecraft.getMinecraft().player.getPersistentID().toString());
                this.a("animationFollowUp", "talkHorny");
                this.acceptPlayer(uuid);
                break;
            }
            case "action.names.headpat": {
                this.handleGirlUuidEvent(Minecraft.getMinecraft().player.getPersistentID());
                this.a("playerSheHasSexWith", Minecraft.getMinecraft().player.getPersistentID().toString());
                this.a("animationFollowUp", "Headpat");
                this.acceptPlayer(uuid);
                break;
            }
            case "action.names.anal": {
                this.a("animationFollowUp", "anal");
                this.setCurrentAction(GirlAnimationState.TALK_RESPONSE);
                this.MenuPending = true;
                break;
            }
            case "doggy": {
                this.a("animationFollowUp", "doggy");
                this.setCurrentAction(GirlAnimationState.TALK_RESPONSE);
                this.MenuPending = true;
                break;
            }
            case "action.names.dressup": 
            case "action.names.strip": {
                this.setCurrentAction(GirlAnimationState.STRIP);
            }
        }
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
        String string = (String)this.DataManager.get(BlowjobStageKey);
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

    @Override
    protected void U() {
        switch ((String)this.DataManager.get(BlowjobStageKey)) {
            case "talkHorny": {
                this.setCurrentAction(GirlAnimationState.TALK_HORNY);
                break;
            }
            case "Headpat": {
                this.setCurrentAction(GirlAnimationState.HEAD_PAT);
                break;
            }
            case "doggy": 
            case "anal": {
                this.resetAimTarget();
                NetworkHandler.channel.sendToServer((IMessage)new PacketSendGirlToSex(this.getGirlUuid()));
                return;
            }
        }
        if (this.world.isRemote) {
            this.a("animationFollowUp", "");
        } else {
            this.DataManager.set(BlowjobStageKey, (Object)"");
        }
    }

    @Override
    public float T() {
        return 35.0f;
    }

    @Override
    public float ai() {
        return 140.0f;
    }

    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return null;
        }
        block5: switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.createAnimationOnce("animation.bia.null", true, animEvent);
                    break;
                }
                this.createAnimationOnce("animation.bia.fhappy", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.createAnimationOnce("animation.bia.null", true, animEvent);
                    break;
                }
                if (this.isRiding()) {
                    this.createAnimationOnce("animation.bia.sit", true, animEvent);
                    break;
                }
                if (Math.abs(this.prevPosX - this.posX) + Math.abs(this.prevPosZ - this.posZ) > 0.0) {
                    switch (this.getWalkState()) {
                        case RUN: {
                            this.createAnimationOnce("animation.bia.run", true, animEvent);
                            break;
                        }
                        case FAST_WALK: {
                            this.createAnimationOnce("animation.bia.fastwalk", true, animEvent);
                            break;
                        }
                        case WALK: {
                            this.createAnimationOnce("animation.bia.walk", true, animEvent);
                        }
                    }
                    this.rotationYaw = this.rotationYawHead;
                    break;
                }
                this.createAnimationOnce("animation.bia.idle", true, animEvent);
                break;
            }
            case "action": {
                switch (this.getCurrentAction()) {
                    case NULL: {
                        this.createAnimationOnce("animation.bia.null", true, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.createAnimationOnce("animation.bia.strip", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.createAnimationOnce("animation.bia.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.createAnimationOnce("animation.bia.bowcharge", false, animEvent);
                        break block5;
                    }
                    case RIDE: {
                        this.createAnimationOnce("animation.bia.ride", true, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.createAnimationOnce("animation.bia.sit", true, animEvent);
                        break block5;
                    }
                    case THROW_PEARL: {
                        this.createAnimationOnce("animation.bia.throwpearl", false, animEvent);
                        break block5;
                    }
                    case DOWNED: {
                        this.createAnimationOnce("animation.bia.downed", true, animEvent);
                        break block5;
                    }
                    case TALK_HORNY: {
                        this.createAnimationOnce("animation.bia.talk_horny2", true, animEvent);
                        break block5;
                    }
                    case TALK_IDLE: {
                        this.createAnimationOnce("animation.bia.talk_idle2", true, animEvent);
                        break block5;
                    }
                    case TALK_RESPONSE: {
                        this.createAnimationOnce("animation.bia.talk_response", true, animEvent);
                        break block5;
                    }
                    case ANAL_PREPARE: {
                        this.createAnimationOnce("animation.bia.anal_prepare", false, animEvent);
                        break block5;
                    }
                    case ANAL_WAIT: {
                        this.createAnimationOnce("animation.bia.anal_wait", false, animEvent);
                        break block5;
                    }
                    case ANAL_START: {
                        this.createAnimationOnce("animation.bia.anal_start", true, animEvent);
                        break block5;
                    }
                    case ANAL_SLOW: {
                        this.createAnimationOnce("animation.bia.anal_slow", true, animEvent);
                        break block5;
                    }
                    case ANAL_FAST: {
                        this.createAnimationOnce("animation.bia.anal_fast", true, animEvent);
                        break block5;
                    }
                    case ANAL_CUM: {
                        this.createAnimationOnce("animation.bia.anal_cum", false, animEvent);
                        break block5;
                    }
                    case HEAD_PAT: {
                        this.createAnimationOnce("animation.bia.headpat", false, animEvent);
                        break block5;
                    }
                    case SITDOWN: {
                        this.createAnimationOnce("animation.bia.sitdown", false, animEvent);
                        break block5;
                    }
                    case SITDOWNIDLE: {
                        this.createAnimationOnce("animation.bia.sitdownidle", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_INTRO: {
                        this.createAnimationOnce("animation.bia.prone_doggy_intro", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_INSERT: {
                        this.createAnimationOnce("animation.bia.prone_doggy_insert", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_SOFT: {
                        this.createAnimationOnce("animation.bia.prone_doggy_soft", true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_HARD: {
                        this.createAnimationOnce("animation.bia.prone_doggy_hard" + this.DanceStep, true, animEvent);
                        break block5;
                    }
                    case PRONE_DOGGY_CUM: {
                        this.createAnimationOnce("animation.bia.prone_doggy_cum", true, animEvent);
                        break block5;
                    }
                    case WAVE_IDLE: {
                        this.createAnimationOnce("animation.bia.wave_idle", true, animEvent);
                        break block5;
                    }
                    case WAVE: {
                        this.createAnimationOnce("animation.bia.wave", true, animEvent);
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
        catch (NullPointerException nullPointerException) {
            throw BiaNpc.rethrow(nullPointerException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    this.setCurrentAction(GirlAnimationState.NULL);
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "becomeNude": {
                    if (!this.isLocalPlayerNearby()) break;
                    this.a("currentModel", (Integer)this.DataManager.get(OutfitIndexKey) == 1 ? "0" : "1");
                    break;
                }
                case "stripDone": {
                    this.resetAimTarget();
                    this.U();
                    break;
                }
                case "stripMSG1": {
                    this.a(I18n.format("bia.dialogue.hihi", new Object[0]));
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_BIA_GIGGLE));
                    break;
                }
                case "sexUiOn": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "pearl": {
                    NetworkHandler.channel.sendToServer((IMessage)new PacketSendCompanionHome(this.getGirlUuid()));
                    break;
                }
                case "talk_hornyMSG1": {
                    this.a(I18n.format("bia.dialogue.heya", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_HEY, new int[0]);
                    break;
                }
                case "talk_hornyMSG2": {
                    this.a(I18n.format("bia.dialogue.horny", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_GIGGLE[2]);
                    break;
                }
                case "talk_hornyMSG3": {
                    this.a(I18n.format("bia.dialogue.so", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_BREATH[0]);
                    break;
                }
                case "talk_hornyMSG4": {
                    this.a(I18n.format("bia.dialogue.fun", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_HUH[0]);
                    break;
                }
                case "talk_hornyDone": {
                    this.setCurrentAction(GirlAnimationState.TALK_IDLE);
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.openActionMenu(Minecraft.getMinecraft().player);
                    break;
                }
                case "talk_responseMSG1": {
                    this.a(I18n.format("bia.dialogue.huh", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_HUH[2]);
                    break;
                }
                case "talk_responseMSG2": {
                    this.a(I18n.format("bia.dialogue.iuhm", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_BREATH[1]);
                    break;
                }
                case "talk_responseMSG3": {
                    this.a(I18n.format("bia.dialogue.yes", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_GIGGLE[0]);
                    break;
                }
                case "talk_responseDone": {
                    if (this.isOwnedByLocalPlayer()) {
                        this.s();
                    }
                    this.U();
                    break;
                }
                case "anal_prepareMSG1": {
                    this.a(ModSounds.MISC_PLOB[0]);
                    break;
                }
                case "anal_prepareMSG2": {
                    this.a(ModSounds.MISC_BEDRUSTLE[0]);
                    break;
                }
                case "anal_prepareDone": {
                    this.setCurrentAction(GirlAnimationState.ANAL_WAIT);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    break;
                }
                case "anal_startMSG1": {
                    this.a(ModSounds.GIRLS_BIA_MMM[3]);
                    this.a(ModSounds.MISC_POUNDING[34]);
                    break;
                }
                case "anal_fastMSG1": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                }
                case "anal_slowMSG1": 
                case "anal_startMSG2": {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.addProgress(0.02);
                    }
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_POUNDING), 0.5f);
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_BIA_AHH));
                    break;
                }
                case "anal_fastDone": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                }
                case "anal_startDone": {
                    this.setCurrentAction(GirlAnimationState.ANAL_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "anal_cumMSG2": {
                    this.a(ModSounds.pickRandomSound(ModSounds.GIRLS_BIA_AHH));
                    break;
                }
                case "blackScreen": 
                case "anal_cumBlackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "doggy_cumDone": 
                case "anal_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.resetProgress();
                    this.resetAimTarget();
                    break;
                }
                case "headpatMSG1": {
                    this.a(I18n.format("bia.dialogue.headpats", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_BREATH[0]);
                    break;
                }
                case "headpatMSG2": {
                    this.a(I18n.format("bia.dialogue.hmm", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_MMM[0]);
                    break;
                }
                case "headpatMSG3": {
                    this.a(I18n.format("bia.dialogue.huh2", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_HUH[0]);
                    break;
                }
                case "headpatMSG4": {
                    this.a(I18n.format("bia.dialogue.thankyou", new Object[0]));
                    this.a(ModSounds.GIRLS_BIA_GIGGLE[1]);
                    break;
                }
                case "headpatDone": {
                    this.resetAimTarget();
                    break;
                }
                case "sitdownMSG1": {
                    this.a("come here big boy~");
                    this.a(ModSounds.GIRLS_BIA_BREATH, new int[0]);
                    break;
                }
                case "sitdownDone": {
                    this.setCurrentAction(GirlAnimationState.SITDOWNIDLE);
                    break;
                }
                case "slide": {
                    this.a(ModSounds.pickRandomSound(ModSounds.MISC_SLIDE));
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.005);
                    break;
                }
                case "pound": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    break;
                }
                case "doggyMoan": {
                    this.a(this.getRNG().nextBoolean() ? ModSounds.GIRLS_BIA_AHH : ModSounds.GIRLS_BIA_MMM, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04);
                    break;
                }
                case "doggySwitch": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.setCurrentAction(GirlAnimationState.PRONE_DOGGY_HARD);
                    break;
                }
                case "doggyReset": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_INSERTS, 6.0f);
                    break;
                }
                case "orgasm1": {
                    this.a(ModSounds.GIRLS_BIA_MMM[6]);
                    break;
                }
                case "orgasm2": {
                    this.a(ModSounds.GIRLS_BIA_MMM[7]);
                }
            }
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

