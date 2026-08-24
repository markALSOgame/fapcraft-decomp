/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class LunaPlayer
extends PlayerGirlEntity {
    int WaitTicks = 0;
    boolean aq = false;
    boolean ap = false;
    boolean as = false;

    protected LunaPlayer(World world) {
        super(world);
    }

    public LunaPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 1.6f;
    }

    public float getEyeHeight() {
        return 1.34f;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube4();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/cat/hand.png";
    }

    @Override
    public void performAction(String string, UUID uUID) {
        try {
            if ("action.names.touchboobs".equals(string)) {
                this.a(0, GirlAnimationState.TOUCH_BOOBS_INTRO);
                this.b(GirlAnimationState.TOUCH_BOOBS_INTRO);
                this.DataManager.set(D, (Object)0);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
        try {
            if ("action.names.headpat".equals(string)) {
                this.b(GirlAnimationState.HEAD_PAT);
                this.b(uUID);
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void setWaitState() {
        this.b(GirlAnimationState.WAIT_CAT);
    }

    @Override
    public boolean allowsBedInteraction() {
        return true;
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        LunaPlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.touchboobs", "action.names.headpat"}, false);
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
                                if (this.getCurrentAction() != GirlAnimationState.COWGIRL_SITTING_CUM) break block12;
                                if (girlAnimationState == GirlAnimationState.COWGIRL_SITTING_SLOW) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.COWGIRL_SITTING_FAST) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (this.getCurrentAction() != GirlAnimationState.TOUCH_BOOBS_CUM) break block14;
                            if (girlAnimationState == GirlAnimationState.TOUCH_BOOBS_FAST) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.TOUCH_BOOBS_SLOW) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    public void onUpdate() {
        block3: {
            block2: {
                try {
                    super.onUpdate();
                    if (!GirlAnimationState.WAIT_CAT.equals((Object)this.getCurrentAction())) break block2;
                    this.a();
                    break block3;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            this.WaitTicks = 0;
        }
    }

    void a() {
        block12: {
            EntityPlayer entityPlayer;
            block11: {
                entityPlayer = this.getRenderPosition();
                try {
                    if (entityPlayer == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
                try {
                    if (entityPlayer.getDistance(this.posX, this.getCustomName().y, this.posZ) > 1.25) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
                try {
                    if (!this.world.isRemote) break block11;
                    this.a(entityPlayer, this.WaitTicks);
                    break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            try {
                if (this.WaitTicks == 25) {
                    this.hasGirl(entityPlayer.getPersistentID());
                    entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
                    entityPlayer.setPositionAndUpdate(this.getPositionVector().x, this.getCustomName().y, this.getPositionVector().z);
                    this.b(GirlAnimationState.COWGIRL_SITTING_INTRO);
                    entityPlayer.setRotationYawHead(this.I().floatValue() + 180.0f);
                    entityPlayer.rotationYaw = this.I().floatValue() + 180.0f;
                    entityPlayer.prevRotationYaw = this.I().floatValue() + 180.0f;
                    this.AimYaw = this.I().floatValue() + 180.0f;
                    this.a(0.0, -0.075f, -0.7109375, 0.0f, 0.0f);
                    this.DataManager.set(D, (Object)0);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        ++this.WaitTicks;
    }

    @SideOnly(value=Side.CLIENT)
    void a(EntityPlayer entityPlayer, int i) {
        EntityPlayerSP entityPlayerSP;
        if (i == 0) {
            entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    GuiTransitionScreen.startTransition();
                    entityPlayerSP.setVelocity(0.0, 0.0, 0.0);
                    AnimationInputLock.setAnimationLocked(false);
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        if (i == 25) {
            entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                if (entityPlayerSP.getPersistentID().equals(entityPlayer.getPersistentID())) {
                    Minecraft.getMinecraft().gameSettings.thirdPersonView = 2;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.TOUCH_BOOBS_SLOW) {
                return GirlAnimationState.TOUCH_BOOBS_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.COWGIRL_SITTING_SLOW) {
                return GirlAnimationState.COWGIRL_SITTING_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaPlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block9: {
            block8: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.TOUCH_BOOBS_SLOW && girlAnimationState != GirlAnimationState.TOUCH_BOOBS_FAST) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.TOUCH_BOOBS_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.COWGIRL_SITTING_FAST && girlAnimationState != GirlAnimationState.COWGIRL_SITTING_SLOW) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.COWGIRL_SITTING_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw LunaPlayer.rethrow(runtimeException);
            }
        }
        return null;
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
            throw LunaPlayer.rethrow(runtimeException);
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
        this.MovementController.transitionLengthTicks = 10.0;
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

