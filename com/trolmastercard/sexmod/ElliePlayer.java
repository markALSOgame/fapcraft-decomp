/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
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

public class ElliePlayer
extends PlayerGirlEntity {
    boolean ar = false;
    boolean aq = false;
    int ap = 1;

    protected ElliePlayer(World world) {
        super(world);
    }

    public ElliePlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float getRenderLabelOffset() {
        return 2.05f;
    }

    public float getEyeHeight() {
        float f;
        try {
            f = this.a() ? 1.53f : 1.9f;
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        return f;
    }

    @Override
    public void sitDown() {
        this.b(GirlAnimationState.SITDOWN);
    }

    @Override
    public void startAction(String string, UUID uUID) {
        try {
            if ("Face fuck".equals(string)) {
                this.b(uUID);
                this.b(GirlAnimationState.CARRY_INTRO);
                this.a(this.getOutfitIndex(), GirlAnimationState.CARRY_INTRO);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
    }

    @Override
    public ModelPartProvider getHandModel(int i) {
        return new ModelCube6();
    }

    @Override
    public String getHandTexture(int i) {
        try {
            if (i == 0) {
                return "textures/entity/ellie/hand_nude.png";
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        return "textures/entity/ellie/hand.png";
    }

    @Override
    public boolean isCustomAnimatable() {
        return true;
    }

    @Override
    public void a(String string, UUID uUID) {
        try {
            if ("action.names.cowgirl".equals(string)) {
                this.a("animationFollowUp", "Cowgirl");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if ("action.names.missionary".equals(string)) {
                this.a("animationFollowUp", "Missionary");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if (!((Optional)this.DataManager.get(ai)).isPresent()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        NetworkHandler.channel.sendToServer((IMessage)new PacketSexPrompt(string, uUID, (UUID)((Optional)this.DataManager.get(ai)).get(), this.Accept));
        this.Accept = true;
    }

    @Override
    public boolean onPlayerInteract(EntityPlayer entityPlayer) {
        ElliePlayer.openActionMenu(entityPlayer, this, new String[]{"Face fuck"}, false);
        return true;
    }

    void openActionMenu(EntityPlayer entityPlayer) {
        ElliePlayer.openActionMenu(entityPlayer, this, new String[]{"action.names.cowgirl", "action.names.missionary"}, false);
    }

    @Override
    public boolean isMenuOpenable() {
        return false;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block14: {
            GirlAnimationState girlAnimationState2;
            block12: {
                girlAnimationState2 = this.getCurrentAction();
                try {
                    block13: {
                        try {
                            try {
                                if (girlAnimationState2 != GirlAnimationState.MISSIONARY_CUM) break block12;
                                if (girlAnimationState == GirlAnimationState.MISSIONARY_FAST) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ElliePlayer.rethrow(runtimeException);
                            }
                            if (girlAnimationState != GirlAnimationState.MISSIONARY_SLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
            }
            try {
                block15: {
                    try {
                        try {
                            if (girlAnimationState2 != GirlAnimationState.COWGIRLCUM) break block14;
                            if (girlAnimationState == GirlAnimationState.COWGIRLSLOW) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.COWGIRLFAST) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ElliePlayer.rethrow(runtimeException);
            }
        }
        super.b(girlAnimationState);
    }

    @Override
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        try {
            if (girlAnimationState == GirlAnimationState.COWGIRLSLOW) {
                return GirlAnimationState.COWGIRLFAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.MISSIONARY_SLOW) {
                return GirlAnimationState.MISSIONARY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            if (girlAnimationState == GirlAnimationState.CARRY_SLOW) {
                return GirlAnimationState.CARRY_FAST;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
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
                            if (girlAnimationState != GirlAnimationState.COWGIRLFAST && girlAnimationState != GirlAnimationState.COWGIRLSLOW) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                        return GirlAnimationState.COWGIRLCUM;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.MISSIONARY_FAST && girlAnimationState != GirlAnimationState.MISSIONARY_SLOW) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                    return GirlAnimationState.MISSIONARY_CUM;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.CARRY_SLOW && girlAnimationState != GirlAnimationState.CARRY_FAST) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
                return GirlAnimationState.CARRY_CUM;
            }
            catch (RuntimeException runtimeException) {
                throw ElliePlayer.rethrow(runtimeException);
            }
        }
        return null;
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        if (this.getCurrentAction() == GirlAnimationState.SITDOWNIDLE) {
            EntityPlayer entityPlayer;
            String string;
            block12: {
                block11: {
                    string = (String)this.DataManager.get(GirlEntity.BlowjobStageKey);
                    try {
                        try {
                            if ("Missionary".equals(string) || "Cowgirl".equals(string)) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ElliePlayer.rethrow(runtimeException);
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                }
                entityPlayer = this.getRenderPosition();
                try {
                    try {
                        if (entityPlayer != null && !(entityPlayer.getDistance(this.getCustomName().x, this.getCustomName().y, this.getCustomName().z) > 1.0)) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ElliePlayer.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw ElliePlayer.rethrow(runtimeException);
                }
            }
            this.DataManager.set(GirlEntity.BlowjobStageKey, (Object)"");
            this.DataManager.set(GirlEntity.OutfitIndexKey, (Object)0);
            this.hasGirl(entityPlayer.getPersistentID());
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.world.getPlayerEntityByUUID((UUID)((Optional)this.DataManager.get(ai)).get());
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer);
            NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), entityPlayerMP);
            entityPlayer.moveRelative(0.0f, 0.0f, 0.0f, 0.0f);
            entityPlayerMP.capabilities.isFlying = true;
            entityPlayer.capabilities.isFlying = true;
            entityPlayerMP.noClip = true;
            entityPlayer.noClip = true;
            entityPlayerMP.setNoGravity(true);
            entityPlayer.setNoGravity(true);
            if ("Missionary".equals(string)) {
                this.b(GirlAnimationState.MISSIONARY_START);
                Vec3d vec3d = this.getCustomName().subtract(0.0, 0.1, 0.0);
                entityPlayer.setPositionAndRotation(vec3d.x, vec3d.y, vec3d.z, this.I().floatValue(), 60.0f);
                entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
            } else {
                this.b(GirlAnimationState.COWGIRLSTART);
                Vec3d vec3d = this.getCustomName().add(new Vec3d(-Math.sin((double)this.I().floatValue() * (Math.PI / 180)) * 1.8, -0.65, Math.cos((double)this.I().floatValue() * (Math.PI / 180)) * 1.8));
                entityPlayer.setPositionAndRotation(vec3d.x, vec3d.y, vec3d.z, 180.0f + this.I().floatValue(), -30.0f);
                entityPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
            }
        }
    }

    boolean a() {
        boolean flag;
        EntityPlayer entityPlayer = this.getBoundPlayer();
        try {
            if (entityPlayer == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
        }
        try {
            flag = this.world.getBlockState(entityPlayer.getPosition().up().up()).getBlock() != Blocks.AIR;
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
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
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 20[SWITCH]
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
                this.isCustomAnimatable();
            }
        }
        catch (RuntimeException runtimeException) {
            throw ElliePlayer.rethrow(runtimeException);
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

