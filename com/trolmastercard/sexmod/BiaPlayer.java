/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class BiaPlayer
extends PlayerGirlEntity {
    int ActionCountdown = -1;
    boolean ap = false;
    int MotionVariant = 1;

    public BiaPlayer(World world) {
        super(world);
    }

    public BiaPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.5f;
    }

    public float getEyeHeight() {
        return 1.5f;
    }

    @Override
    public void u() {
    }

    @Override
    public boolean startActionByKey(String string) {
        try {
            if ("anal".equals(string)) {
                this.b(GirlAnimationState.ANAL_PREPARE);
                this.f(0);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        try {
            if ("doggy".equals(string)) {
                this.b(GirlAnimationState.SITDOWN);
                this.f(0);
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void openActionMenu() {
        GirlEntity.openActionMenu((EntityPlayer)Minecraft.getMinecraft().player, this, new String[]{"anal", "doggy"}, false);
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("action.names.headpat".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.HEAD_PAT);
                this.a(this.getOutfitIndex(), GirlAnimationState.HEAD_PAT);
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube9();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/bia/hand.png";
    }

    @Override
    public float getCameraPitch() {
        return 35.0f;
    }

    @Override
    public float getCameraYaw() {
        return 140.0f;
    }

    @Override
    public boolean isMenuOpenable() {
        return false;
    }

    @Override
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        GirlEntity.openActionMenu(entityPlayer, this, new String[]{"action.names.headpat"}, false);
        return true;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block14: {
            block12: {
                try {
                    block13: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.ANAL_CUM) break block12;
                                if (girlAnimationState == GirlAnimationState.ANAL_FAST) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw BiaPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.ANAL_SLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BiaPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.PRONE_DOGGY_CUM) break block14;
                            if (girlAnimationState == GirlAnimationState.PRONE_DOGGY_HARD) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BiaPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.PRONE_DOGGY_SOFT) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw BiaPlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.ANAL_SLOW) {
                return GirlAnimationState.ANAL_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.PRONE_DOGGY_INTRO) {
                return GirlAnimationState.PRONE_DOGGY_INSERT;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
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
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.ANAL_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.PRONE_DOGGY_SOFT && girlAnimationState != GirlAnimationState.PRONE_DOGGY_HARD) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.PRONE_DOGGY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw BiaPlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.a();
    }

    @Override
    protected void resetAction() {
        super.resetAction();
        this.ActionCountdown = -1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isLocalPlayer(EntityPlayer entityPlayer) {
        return Minecraft.getMinecraft().player.getPersistentID().equals(entityPlayer.getPersistentID());
    }

    void a() {
        float f2;
        EntityPlayer entityPlayer;
        block35: {
            block37: {
                block36: {
                    GirlAnimationState girlAnimationState;
                    block32: {
                        block34: {
                            block33: {
                                block31: {
                                    block30: {
                                        girlAnimationState = this.getCurrentAction();
                                        try {
                                            try {
                                                if (girlAnimationState == GirlAnimationState.ANAL_WAIT || girlAnimationState == GirlAnimationState.SITDOWNIDLE) break block30;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw BiaPlayer.rethrow(runtimeException);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw BiaPlayer.rethrow(runtimeException);
                                        }
                                    }
                                    entityPlayer = this.getRenderPosition();
                                    try {
                                        if (entityPlayer == null) {
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                    try {
                                        if (entityPlayer.getDistance((Entity)this) > 1.0f) {
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                    try {
                                        try {
                                            if (!this.world.isRemote || this.isLocalPlayer(entityPlayer)) break block31;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw BiaPlayer.rethrow(runtimeException);
                                        }
                                        return;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (this.ActionCountdown != -1) break block32;
                                        if (!this.world.isRemote) break block33;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BiaPlayer.rethrow(runtimeException);
                                    }
                                    GuiTransitionScreen.startTransition();
                                    AnimationInputLock.setAnimationLocked(false);
                                    break block34;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw BiaPlayer.rethrow(runtimeException);
                                }
                            }
                            this.hasGirl(entityPlayer.getPersistentID());
                        }
                        this.ActionCountdown = GirlEntity.j;
                        return;
                    }
                    try {
                        if (--this.ActionCountdown > 0) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                    try {
                        this.ActionCountdown = -1;
                        entityPlayer.noClip = true;
                        entityPlayer.setNoGravity(true);
                        if (girlAnimationState != GirlAnimationState.ANAL_WAIT) break block35;
                        if (this.world.isRemote) break block36;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BiaPlayer.rethrow(runtimeException);
                    }
                    this.b(GirlAnimationState.ANAL_START);
                    Vec3d vec3d = this.getTargetPos().add(VectorMath.rotatePitch(-0.3, -1.0, -0.5, this.I().floatValue()));
                    entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
                    break block37;
                }
                try {
                    if (this.isOwnedByLocalPlayer()) {
                        GuiHud.showHud();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw BiaPlayer.rethrow(runtimeException);
                }
            }
            return;
        }
        entityPlayer.rotationYaw = f2 = this.I().floatValue();
        entityPlayer.rotationPitch = 60.0f;
        if (!this.world.isRemote) {
            this.f(0);
            this.b(GirlAnimationState.PRONE_DOGGY_INTRO);
            Vec3d vec3d = this.getTargetPos();
            Vec3d vec3d2 = vec3d.add(VectorMath.rotatePitch(0.0, 0.0, 1.0, f2));
            this.c(vec3d2);
            EntityPlayer entityPlayer2 = this.getBoundPlayer();
            try {
                if (entityPlayer2 != null) {
                    entityPlayer2.setPositionAndUpdate(vec3d2.x, vec3d2.y, vec3d2.z);
                }
            }
            catch (RuntimeException runtimeException) {
                throw BiaPlayer.rethrow(runtimeException);
            }
            Vec3d vec3d3 = vec3d.add(VectorMath.rotatePitch(0.0, 1.1875 - (double)entityPlayer.getEyeHeight(), 0.5, f2));
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
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
        int i = this.MotionVariant;
        try {
            do {
                this.MotionVariant = this.getRNG().nextInt(3) + 1;
            } while (i == this.MotionVariant);
            return;
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 16[SWITCH]
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
                this.canStartInteraction();
            }
        }
        catch (RuntimeException runtimeException) {
            throw BiaPlayer.rethrow(runtimeException);
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
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
        animationData.addAnimationController(this.ActionController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

