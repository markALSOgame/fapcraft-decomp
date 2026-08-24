/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.util.MatrixStack;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class KoboldPlayer
extends GirlPlayerHomeEntity
implements BooleanCheck {
    public static final EyeAndKoboldColor DefaultTribeColor = EyeAndKoboldColor.PURPLE;
    public static final DataParameter<Float> BodySizeKey = EntityDataManager.createKey(KoboldPlayer.class, (DataSerializer)DataSerializers.FLOAT).getSerializer().createKey(122);
    boolean aB = false;
    boolean az = true;
    boolean ay = false;
    int ax = 0;

    protected KoboldPlayer(World world) {
        super(world);
    }

    public KoboldPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        EyeAndKoboldColor eyeAndKoboldColor = EyeAndKoboldColor.values()[this.getRNG().nextInt(EyeAndKoboldColor.values().length)];
        this.DataManager.register(au, (Object)new BlockPos(eyeAndKoboldColor.getMainColor()));
        this.DataManager.register(as, (Object)DefaultTribeColor.name());
        this.DataManager.register(BodySizeKey, (Object)Float.valueOf(0.0f));
    }

    @Override
    public AxisAlignedBB getInteractBox(EntityPlayer entityPlayer) {
        float f = 0.6f;
        float f2 = 0.9f;
        float f3 = f / 2.0f;
        return new AxisAlignedBB(entityPlayer.posX - (double)f3, entityPlayer.posY, entityPlayer.posZ - (double)f3, entityPlayer.posX + (double)f3, entityPlayer.posY + (double)f2, entityPlayer.posZ + (double)f3);
    }

    /*
     * Exception decompiling
     */
    @Override
    public void a(List<Integer> list) {
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
    public ArrayList<Integer> getOutfitData() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(Math.round(((Float)this.DataManager.get(BodySizeKey)).floatValue() * 100.0f / 0.25f));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((String)this.DataManager.get(as))));
        arrayList.add(EyeAndKoboldColor.indexOf(EyeAndKoboldColor.safeValueOf((Vec3i)this.DataManager.get(au))));
        return arrayList;
    }

    @Override
    protected String serializeGirlSpecificData(StringBuilder stringBuilder) {
        GirlEffectEntity.appendRandomBelow(stringBuilder, 8);
        GirlEffectEntity.appendRandomBelow(stringBuilder, 3);
        GirlEffectEntity.appendRandomGauss(stringBuilder);
        GirlEffectEntity.appendRandomGauss(stringBuilder);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 1);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> getCustomizationSlots() {
        return new ArrayList<Integer>(){
            {
                this.add(101);
                this.add(EyeAndKoboldColor.values().length);
                this.add(EyeAndKoboldColor.values().length);
                this.add(8);
                this.add(3);
                this.add(101);
                this.add(101);
                this.add(3);
                this.add(3);
                this.add(4);
                this.add(2);
            }
        };
    }

    @Override
    protected void a() {
        KoboldPlayerRenderer.clearColorCache();
        KoboldNpcRenderer.clearColorCache();
    }

    @Override
    public float getRenderLabelOffset() {
        float f = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        return 1.4f - f;
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("anal".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.KOBOLD_ANAL_START);
                this.a(this.getOutfitIndex(), GirlAnimationState.KOBOLD_ANAL_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        try {
            if ("oral".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.STARTBLOWJOB);
                this.a(this.getOutfitIndex(), GirlAnimationState.STARTBLOWJOB);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        try {
            if ("mating".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.MATING_PRESS_START);
                this.a(this.getOutfitIndex(), GirlAnimationState.MATING_PRESS_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlCommandMenu(this, entityPlayer, new String[]{"anal", "oral", "mating"}, null, false));
        return true;
    }

    @Override
    public boolean a() {
        boolean flag;
        Block block = this.world.getBlockState(this.getPosition().add(0, 1, 0)).getBlock();
        try {
            flag = !block.isPassable((IBlockAccess)this.world, this.getPosition().add(0, 1, 0));
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    protected MatrixStack a(MatrixStack matrixStack) {
        float f = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        matrixStack.scale(1.0f - f, 1.0f - f, 1.0f - f);
        return matrixStack;
    }

    @Override
    protected float a(float f) {
        float f2 = 1.0f - (0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue());
        return f * f2;
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube8();
    }

    @Override
    public String getHandTexture(int i) {
        return "textures/entity/kobold/hand.png";
    }

    @Override
    public Vec3i getTribeColor(int i) {
        try {
            return EyeAndKoboldColor.valueOf((String)this.DataManager.get(as)).getMainColor();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return super.getTribeColor(i);
        }
    }

    @Override
    @Nullable
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB_BLINK) {
                return GirlAnimationState.THRUSTBLOWJOB;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.KOBOLD_ANAL_SLOW) {
                return GirlAnimationState.KOBOLD_ANAL_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldPlayer.rethrow(runtimeException);
        }
        return null;
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        block14: {
            block13: {
                block12: {
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.THRUSTBLOWJOB && girlAnimationState != GirlAnimationState.SUCKBLOWJOB_BLINK) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldPlayer.rethrow(runtimeException);
                        }
                        return GirlAnimationState.CUMBLOWJOB;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.KOBOLD_ANAL_SLOW && girlAnimationState != GirlAnimationState.KOBOLD_ANAL_FAST) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.KOBOLD_ANAL_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.MATING_PRESS_HARD && girlAnimationState != GirlAnimationState.MATING_PRESS_SOFT) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.MATING_PRESS_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayer.rethrow(runtimeException);
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
                                    if (girlAnimationState2 != GirlAnimationState.MATING_PRESS_CUM) break block18;
                                    if (girlAnimationState == GirlAnimationState.MATING_PRESS_SOFT) break block19;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw KoboldPlayer.rethrow(runtimeException);
                                }
                                if (girlAnimationState != GirlAnimationState.MATING_PRESS_HARD) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw KoboldPlayer.rethrow(runtimeException);
                            }
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    block21: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.KOBOLD_ANAL_CUM) break block20;
                                if (girlAnimationState == GirlAnimationState.KOBOLD_ANAL_SLOW) break block21;
                            }
                            catch (RuntimeException runtimeException) {
                                throw KoboldPlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.KOBOLD_ANAL_FAST) break block20;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldPlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldPlayer.rethrow(runtimeException);
                }
            }
            try {
                block23: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.CUMBLOWJOB) break block22;
                            if (girlAnimationState == GirlAnimationState.SUCKBLOWJOB) break block23;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldPlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.THRUSTBLOWJOB) break block22;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldPlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldPlayer.rethrow(runtimeException);
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
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 19[SWITCH]
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

    void b(SoundEvent soundEvent) {
        this.b(soundEvent, 1.0f);
    }

    void b(SoundEvent[] soundEventArray) {
        this.b(soundEventArray, 1.0f);
    }

    void b(SoundEvent[] soundEventArray, float f) {
        this.b(soundEventArray[this.getRNG().nextInt(soundEventArray.length)], f);
    }

    void b(SoundEvent soundEvent, float f) {
        float f2 = 0.25f - ((Float)this.DataManager.get(BodySizeKey)).floatValue();
        double d = f2 / 0.25f;
        float f3 = (float)LerpMath.lerp((double)0.9f, (double)1.1f, d);
        this.a(soundEvent, f, f3);
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
            throw KoboldPlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 34[SWITCH]
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
        this.MovementController.transitionLengthTicks = 3.0;
        this.ActionController.registerSoundListener(iSoundListener);
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

