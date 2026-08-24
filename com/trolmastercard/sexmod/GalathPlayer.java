/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class GalathPlayer
extends PlayerGirlEntity
implements BoxSource {
    boolean BusyFlag = false;
    int ar = 0;
    boolean WasCorrupting = false;
    boolean aq = false;

    public GalathPlayer(World world) {
        super(world);
    }

    public GalathPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube2();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/galath/hand.png";
    }

    @Override
    @Nullable
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block7: {
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CORRUPT_FAST && girlAnimationState != GirlAnimationState.CORRUPT_SLOW) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.CORRUPT_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw GalathPlayer.rethrow(runtimeException);
            }
        }
        try {
            if (girlAnimationState == GirlAnimationState.RAPE_ON_GOING) {
                return GirlAnimationState.RAPE_CUM;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    public float getRenderLabelOffset() {
        return 2.3f;
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("cowgirl".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.RAPE_INTRO);
                this.a(this.getOutfitIndex(), GirlAnimationState.RAPE_INTRO);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        try {
            if ("mating press".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.CORRUPT_SLOW);
                this.a(this.getOutfitIndex(), GirlAnimationState.CORRUPT_SLOW);
                this.a();
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block20: {
            GirlAnimationState girlAnimationState2;
            block19: {
                block17: {
                    girlAnimationState2 = this.getCurrentAction();
                    try {
                        block18: {
                            try {
                                try {
                                    if (girlAnimationState2 != GirlAnimationState.CORRUPT_CUM) break block17;
                                    if (girlAnimationState == GirlAnimationState.CORRUPT_FAST) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GalathPlayer.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.CORRUPT_SLOW) break block17;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GalathPlayer.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState2 != GirlAnimationState.RAPE_CUM || girlAnimationState != GirlAnimationState.RAPE_ON_GOING) break block19;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayer.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState2 != GirlAnimationState.RAPE_CUM || girlAnimationState != GirlAnimationState.RAPE_CUM_IDLE) break block20;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GalathPlayer.rethrow(runtimeException);
            }
        }
        try {
            if (girlAnimationState == GirlAnimationState.CORRUPT_SLOW) {
                this.WasCorrupting = false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
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
            throw GalathPlayer.rethrow(runtimeException);
        }
        Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.5, (double)(0.5f - entityPlayer.getEyeHeight()), (double)0.4f), this.I().floatValue()).add(this.getTargetPos());
        entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean openActionMenu(EntityPlayer entityPlayer) {
        GalathPlayer.openActionMenu(entityPlayer, this, new String[]{"cowgirl", "mating press", "ride"}, false);
        return true;
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
    public Vec4d d() {
        return new Vec4d(0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean c() {
        boolean flag;
        block5: {
            block4: {
                try {
                    try {
                        if (this.getOutfitIndex() != 0 && !this.BusyFlag) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathPlayer.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathPlayer.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean a() {
        try {
            switch (this.getCurrentAction()) {
                case CORRUPT_CUM: 
                case CORRUPT_FAST: 
                case CORRUPT_SLOW: 
                case COWGIRLCUM: {
                    return false;
                }
                default: {
                    return true;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void B() {
        this.c(true);
    }

    @Override
    public void onUpdate() {
        try {
            super.onUpdate();
            this.b();
            if (this.world.isRemote) {
                this.d();
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
    }

    @SideOnly(value=Side.CLIENT)
    void d() {
        try {
            if (!this.isOwnedByLocalPlayer()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.RAPE_INTRO) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        GuiHud.showHudWithForce(false);
    }

    /*
     * Exception decompiling
     */
    void b() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous, and can't clone.
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:611)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:94)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:517)
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

    boolean g() {
        boolean flag;
        EntityPlayer entityPlayer = this.getBoundPlayer();
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        try {
            flag = this.world.getBlockState(entityPlayer.getPosition().up().up()).getBlock() != Blocks.AIR;
        }
        catch (RuntimeException runtimeException) {
            throw GalathPlayer.rethrow(runtimeException);
        }
        return flag;
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
        this.canStartInteraction();
        this.ActionController.registerSoundListener(arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 21[SWITCH]
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
        });
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.EyesController);
        animationData.addAnimationController(this.MovementController);
    }

    private static /* synthetic */ Vec3d lambda$null$3(GirlEntity girl) {
        return girl.getModelBone("creampiePos").add(girl.getTargetPos());
    }

    private /* synthetic */ Vec3d lambda$null$2(GirlEntity girl) {
        return VectorMath.rotateYaw(new Vec3d(0.0, 0.0, (double)0.6f), this.I().floatValue());
    }

    private static /* synthetic */ Vec3d lambda$null$1(GirlEntity girl) {
        return girl.getModelBone("futaCockTip").add(girl.getTargetPos());
    }

    private static /* synthetic */ Vec3d lambda$null$0(GirlEntity girl) {
        Vec3d vec3d = girl.d("futaCockTip");
        Vec3d vec3d2 = girl.d("futaCockTipDirHelp");
        return vec3d.subtract(vec3d2).normalize();
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

