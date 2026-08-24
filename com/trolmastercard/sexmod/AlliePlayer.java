/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class AlliePlayer
extends PlayerGirlEntity {
    static final double au = 4.0;
    static final double at = 4.0;
    public float HeightOffset = 0.0f;
    EntityPlayer BoundPlayer = null;
    boolean ap = false;
    int ar = 1;
    int av = 1;

    protected AlliePlayer(World world) {
        super(world);
    }

    public AlliePlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.9f + this.HeightOffset;
    }

    public float getEyeHeight() {
        return 1.63f;
    }

    @Override
    public boolean v() {
        return false;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube7();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/allie/hand.png";
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("action.names.deepthroat".equals(string)) {
                this.b(GirlAnimationState.DEEPTHROAT_START);
                this.a(this.getOutfitIndex(), GirlAnimationState.DEEPTHROAT_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        try {
            if ("Reverse cowgirl".equals(string)) {
                this.b(GirlAnimationState.REVERSE_COWGIRL_START);
                this.a(0, GirlAnimationState.REVERSE_COWGIRL_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        AlliePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.deepthroat", "Reverse cowgirl"}, false);
        return true;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block16: {
            block14: {
                try {
                    block15: {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.DEEPTHROAT_CUM) break block14;
                                if (girlAnimationState == GirlAnimationState.DEEPTHROAT_FAST) break block15;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AlliePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.DEEPTHROAT_SLOW) break block14;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AlliePlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayer.rethrow(runtimeException);
                }
            }
            try {
                block17: {
                    try {
                        try {
                            try {
                                if (this.getCurrentAction() != GirlAnimationState.REVERSE_COWGIRL_CUM) break block16;
                                if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw AlliePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AlliePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AlliePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    public boolean isInOralState() {
        try {
            switch (this.getCurrentAction()) {
                case ALLIE_PREPARE_NORMAL: 
                case DEEPTHROAT_START: 
                case DEEPTHROAT_CUM: 
                case DEEPTHROAT_FAST: 
                case ALLIE_PREPARE_FIRST_TIME: 
                case DEEPTHROAT_SLOW: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void updateAITasks() {
        EntityPlayer entityPlayer;
        block7: {
            try {
                super.updateAITasks();
                if (this.getBoundPlayerUuid() == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
            entityPlayer = this.world.getPlayerEntityByUUID(this.getBoundPlayerUuid());
            try {
                try {
                    if (entityPlayer == null || this.BoundPlayer != null) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayer.rethrow(runtimeException);
                }
                this.c(true);
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
        }
        this.BoundPlayer = entityPlayer;
    }

    @Override
    public void onUpdate() {
        try {
            super.onUpdate();
            if (this.world.isRemote) {
                this.a();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void a() {
        try {
            if (this.ticksExisted % 10 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        int i = this.getRNG().nextInt(8);
        Vec3d vec3d = this.b("tail" + i).add(this.getPositionVector());
        this.world.spawnParticle(EnumParticleTypes.PORTAL, vec3d.x, vec3d.y, vec3d.z, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, this.getRNG().nextGaussian() * (double)0.01f, new int[0]);
    }

    @Override
    public void B() {
        this.c(true);
    }

    @Override
    public void y() {
        this.c(false);
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.DEEPTHROAT_SLOW) {
                return GirlAnimationState.DEEPTHROAT_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW) {
                return GirlAnimationState.REVERSE_COWGIRL_FAST_START;
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
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
                        throw AlliePlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.DEEPTHROAT_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayer.rethrow(runtimeException);
                }
            }
            try {
                block11: {
                    try {
                        try {
                            if (girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_SLOW || girlAnimationState == GirlAnimationState.REVERSE_COWGIRL_FAST_START) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw AlliePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.REVERSE_COWGIRL_FAST_CONTINUES) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AlliePlayer.rethrow(runtimeException);
                    }
                }
                return GirlAnimationState.REVERSE_COWGIRL_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.canStartInteraction();
            }
        }
        catch (RuntimeException runtimeException) {
            throw AlliePlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 20[SWITCH]
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

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

