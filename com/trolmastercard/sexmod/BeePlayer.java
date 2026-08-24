/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class BeePlayer
extends PlayerGirlEntity {
    protected BeePlayer(World world) {
        super(world);
    }

    public BeePlayer(World world, UUID uUID) {
        super(world, uUID);
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
    public float getRenderLabelOffset() {
        return 1.4f;
    }

    public float getEyeHeight() {
        return 1.3f;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube3();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/bee/hand.png";
    }

    @Override
    public void startAction(String string, UUID uUID) {
        this.a(0, GirlAnimationState.CITIZEN_START);
        this.f(0);
        this.b(GirlAnimationState.CITIZEN_START);
        this.b(uUID);
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeePlayer.rethrow(runtimeException);
        }
        Vec3d vec3d = this.a(-0.2);
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        BeePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.sex"}, false);
        return true;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block6: {
            try {
                block7: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.CITIZEN_CUM) break block6;
                            if (girlAnimationState == GirlAnimationState.CITIZEN_FAST) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BeePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.COWGIRLSLOW) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BeePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw BeePlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public boolean isCustomModel() {
        return false;
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.CITIZEN_SLOW) {
                return GirlAnimationState.CITIZEN_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeePlayer.rethrow(runtimeException);
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
                    throw BeePlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.CITIZEN_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw BeePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void onActionCleanup() {
        super.onActionCleanup();
        this.f(1);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[SWITCH]
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
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.canStartInteraction();
            }
        }
        catch (RuntimeException runtimeException) {
            throw BeePlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 15[SWITCH]
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

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

