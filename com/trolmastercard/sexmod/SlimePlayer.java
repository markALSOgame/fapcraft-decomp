/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class SlimePlayer
extends PlayerGirlEntity {
    boolean ap = false;
    int aq = 0;

    protected SlimePlayer(World world) {
        super(world);
    }

    public SlimePlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.6f;
    }

    public float getEyeHeight() {
        return 1.64f;
    }

    @Override
    public boolean allowsBedInteraction() {
        return false;
    }

    @Override
    public boolean suppressInventoryStripButton() {
        return false;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube5();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/slime/hand.png";
    }

    @Override
    public void performAction(String string, UUID uUID) {
        try {
            if ("action.names.blowjob".equals(string)) {
                this.a(0, GirlAnimationState.SUCKBLOWJOB);
                this.b(GirlAnimationState.SUCKBLOWJOB);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        SlimePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.blowjob"}, false);
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
                                if (this.getCurrentAction() != GirlAnimationState.CUMBLOWJOB) break block12;
                                if (girlAnimationState == GirlAnimationState.THRUSTBLOWJOB) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimePlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.DOGGYCUM) break block14;
                            if (girlAnimationState == GirlAnimationState.DOGGYFAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.DOGGYSLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw SlimePlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB) {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.DOGGYSLOW) {
                return GirlAnimationState.DOGGYFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB && girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimePlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.CUMBLOWJOB;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.DOGGYSLOW && girlAnimationState != GirlAnimationState.DOGGYFAST) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimePlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.DOGGYCUM;
            }
            catch (RuntimeException runtimeException) {
                throw SlimePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void updateAITasks() {
        try {
            super.updateAITasks();
            if (this.getCurrentAction() != GirlAnimationState.WAITDOGGY) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.getRenderPosition();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        try {
            if (entityPlayer.getPositionVector().distanceTo(this.getCustomName()) > 1.0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw SlimePlayer.rethrow(runtimeException);
        }
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
        this.hasGirl(entityPlayer.getPersistentID());
        entityPlayer.rotationYaw = this.I().floatValue();
        this.AimYaw = this.I().floatValue();
        entityPlayer.setPosition(this.getCustomName().x, this.getCustomName().y, this.getCustomName().z);
        entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
        this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
        this.b(GirlAnimationState.DOGGYSTART);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        EntityPlayer entityPlayer2 = this.world.getPlayerEntityByUUID(this.getBoundPlayerUuid());
        entityPlayer2.setNoGravity(true);
        entityPlayer.noClip = true;
        entityPlayer.capabilities.isFlying = true;
        entityPlayer2.capabilities.isFlying = true;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 17[SWITCH]
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
            throw SlimePlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 25[SWITCH]
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
        animationData.addAnimationController(this.EyesController);
        animationData.addAnimationController(this.MovementController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

