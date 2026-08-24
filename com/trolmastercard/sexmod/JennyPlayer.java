/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class JennyPlayer
extends PlayerGirlEntity {
    boolean ap = false;
    boolean ar = false;
    int aq = 0;
    boolean as = false;

    protected JennyPlayer(World world) {
        super(world);
    }

    public JennyPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.75f;
    }

    @Override
    public float T() {
        return 35.0f;
    }

    @Override
    public float ai() {
        return 140.0f;
    }

    public float getEyeHeight() {
        return 1.64f;
    }

    @Override
    public void startDoggy() {
        this.b(GirlAnimationState.STARTDOGGY);
        this.DataManager.set(GirlEntity.OutfitIndexKey, (Object)0);
        this.AimYaw = ((Float)this.DataManager.get(GirlEntity.RotationYawKey)).floatValue();
    }

    @Override
    public boolean suppressInventoryStripButton() {
        return false;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube1();
    }

    @Override
    public String getHandTexture(int i) {
        try {
            if (i == 0) {
                return "textures/entity/jenny/hand_nude.png";
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyPlayer.rethrow(runtimeException);
        }
        return "textures/entity/jenny/hand.png";
    }

    @Override
    public void performAction(String string, UUID uUID) {
        try {
            if ("action.names.boobjob".equals(string)) {
                this.DataManager.set(GirlEntity.OutfitIndexKey, (Object)0);
                this.b(GirlAnimationState.PAIZURI_START);
                this.a(0, GirlAnimationState.PAIZURI_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyPlayer.rethrow(runtimeException);
        }
        try {
            if ("action.names.blowjob".equals(string)) {
                this.b(GirlAnimationState.STARTBLOWJOB);
                this.a(this.getOutfitIndex(), GirlAnimationState.PAIZURI_START);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw JennyPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void updateAITasks() {
        block7: {
            super.updateAITasks();
            if (this.getCurrentAction() == GirlAnimationState.WAITDOGGY) {
                EntityPlayer entityPlayer;
                block8: {
                    entityPlayer = this.getRenderPosition();
                    try {
                        try {
                            try {
                                if (entityPlayer == null || !(entityPlayer.getDistance(this.getCustomName().x, this.getCustomName().y, this.getCustomName().z) < 1.0)) break block7;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyPlayer.rethrow(runtimeException);
                            }
                            if (!this.c(entityPlayer.getPersistentID())) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                        entityPlayer.sendMessage((ITextComponent)new TextComponentString(TextFormatting.DARK_PURPLE + "sowy no lesbo action yet uwu"));
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                this.hasGirl(entityPlayer.getPersistentID());
                entityPlayer.setPositionAndUpdate(this.getPositionVector().x, this.getCustomName().y, this.getPositionVector().z);
                this.a((EntityPlayerMP)entityPlayer, false);
                entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
                entityPlayer.capabilities.isFlying = true;
                this.world.getPlayerEntityByUUID((UUID)this.getBoundPlayerUuid()).capabilities.isFlying = true;
                this.a(0.0, 0.0, 0.4, 0.0f, 60.0f);
                this.AimTarget = null;
                this.b(GirlAnimationState.DOGGYSTART);
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
            }
        }
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        GirlEntity.openActionMenu(entityPlayer, this, new String[]{"action.names.blowjob", "action.names.boobjob"}, false);
        return true;
    }

    /*
     * Exception decompiling
     */
    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
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

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB && girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                        this.a(0.0, 0.0, 0.0, 0.0f, 70.0f);
                        return GirlAnimationState.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.DOGGYSLOW && girlAnimationState != GirlAnimationState.DOGGYFAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.DOGGYCUM;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.PAIZURI_FAST && girlAnimationState != GirlAnimationState.PAIZURI_SLOW) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.PAIZURI_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw JennyPlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block22: {
            GirlAnimationState girlAnimationState2;
            block20: {
                block18: {
                    girlAnimationState2 = this.getCurrentAction();
                    try {
                        block19: {
                            try {
                                try {
                                    if (girlAnimationState2 != GirlAnimationState.DOGGYCUM) break block18;
                                    if (girlAnimationState == GirlAnimationState.DOGGYSLOW) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw JennyPlayer.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.DOGGYFAST) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyPlayer.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.CUMBLOWJOB) break block20;
                                if (girlAnimationState == GirlAnimationState.THRUSTBLOWJOB) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw JennyPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.SUCKBLOWJOB) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw JennyPlayer.rethrow(runtimeException);
                }
            }
            try {
                block23: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.PAIZURI_CUM) break block22;
                            if (girlAnimationState == GirlAnimationState.PAIZURI_SLOW) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw JennyPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.PAIZURI_FAST) break block22;
                    }
                    catch (RuntimeException runtimeException) {
                        throw JennyPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw JennyPlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
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
            throw JennyPlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 37[SWITCH]
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

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

