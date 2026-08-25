/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityWitherSkeleton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.Packet
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.network.play.server.SPacketEntity$S16PacketEntityLook
 *  net.minecraft.network.play.server.SPacketEntityVelocity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketEntity;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public enum DeadClass {
    CHANGE_POSITION(f_2 -> {
        boolean flag;
        World world = f_2.world;
        BlockPos blockPos = f_2.getPosition();
        BlockPos blockPos2 = f_2.getTargetEntity().getPosition();
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        HashMap<BlockPos, Integer> hashMap = new HashMap<BlockPos, Integer>();
        int iMax = 0;
        try {
            flag = !world.isAirBlock(blockPos.down());
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        boolean flag2 = flag;
        for (int i = -10; i < 10; ++i) {
            block41: for (int i6 = -10; i6 < 10; ++i6) {
                int i7 = -10;
                while (true) {
                    block51: {
                        int i8;
                        BlockPos blockPos3;
                        block62: {
                            block60: {
                                block59: {
                                    block58: {
                                        block57: {
                                            block56: {
                                                block55: {
                                                    block54: {
                                                        block53: {
                                                            block52: {
                                                                block50: {
                                                                    try {
                                                                        try {
                                                                            try {
                                                                                try {
                                                                                    if (i7 >= 10) continue block41;
                                                                                    if (i != 0) break block50;
                                                                                }
                                                                                catch (RuntimeException runtimeException) {
                                                                                    throw DeadClass.rethrow(runtimeException);
                                                                                }
                                                                                if (i6 != 0) break block50;
                                                                            }
                                                                            catch (RuntimeException runtimeException) {
                                                                                throw DeadClass.rethrow(runtimeException);
                                                                            }
                                                                            if (i7 != 0) break block50;
                                                                            break block51;
                                                                        }
                                                                        catch (RuntimeException runtimeException) {
                                                                            throw DeadClass.rethrow(runtimeException);
                                                                        }
                                                                    }
                                                                    catch (RuntimeException runtimeException) {
                                                                        throw DeadClass.rethrow(runtimeException);
                                                                    }
                                                                }
                                                                blockPos3 = blockPos2.add((Vec3i)new BlockPos(i, i6, i7));
                                                                try {
                                                                    try {
                                                                        if (!flag2 || blockPos.getY() < blockPos3.getY()) break block52;
                                                                        break block51;
                                                                    }
                                                                    catch (RuntimeException runtimeException) {
                                                                        throw DeadClass.rethrow(runtimeException);
                                                                    }
                                                                }
                                                                catch (RuntimeException runtimeException) {
                                                                    throw DeadClass.rethrow(runtimeException);
                                                                }
                                                            }
                                                            try {
                                                                if (world.isAirBlock(blockPos3)) break block53;
                                                                break block51;
                                                            }
                                                            catch (RuntimeException runtimeException) {
                                                                throw DeadClass.rethrow(runtimeException);
                                                            }
                                                        }
                                                        try {
                                                            if (world.isAirBlock(blockPos3.up())) break block54;
                                                            break block51;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw DeadClass.rethrow(runtimeException);
                                                        }
                                                    }
                                                    try {
                                                        if (world.isAirBlock(blockPos3.up().up())) break block55;
                                                        break block51;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw DeadClass.rethrow(runtimeException);
                                                    }
                                                }
                                                RayTraceResult rayTraceResult = world.rayTraceBlocks(new Vec3d((Vec3i)blockPos), new Vec3d((Vec3i)blockPos3), true, true, true);
                                                try {
                                                    if (rayTraceResult == null) break block56;
                                                    break block51;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw DeadClass.rethrow(runtimeException);
                                                }
                                            }
                                            int i9 = blockPos3.getY();
                                            while (--i9 >= 0 && world.getBlockState(new BlockPos(blockPos3.getX(), i9, blockPos3.getZ())).getBlock() instanceof BlockAir) {
                                            }
                                            try {
                                                if (!(world.getBlockState(new BlockPos(blockPos3.getX(), i9, blockPos3.getZ())).getBlock() instanceof BlockLiquid)) break block57;
                                                break block51;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw DeadClass.rethrow(runtimeException);
                                            }
                                        }
                                        try {
                                            try {
                                                arrayList.add(blockPos3);
                                                if (world.isAirBlock(blockPos3.down()) && world.isAirBlock(blockPos3.down().down())) break block58;
                                                break block51;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw DeadClass.rethrow(runtimeException);
                                            }
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw DeadClass.rethrow(runtimeException);
                                        }
                                    }
                                    try {
                                        if (!(blockPos2.getDistance(blockPos3.getX(), blockPos3.getY(), blockPos3.getZ()) < 5.0)) break block59;
                                        break block51;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw DeadClass.rethrow(runtimeException);
                                    }
                                }
                                try {
                                    if (!(blockPos.getDistance(blockPos3.getX(), blockPos3.getY(), blockPos3.getZ()) < 3.0)) break block60;
                                    break block51;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw DeadClass.rethrow(runtimeException);
                                }
                            }
                            i8 = 0;
                            for (int i10 = -1; i10 < 2; ++i10) {
                                block45: for (int i11 = -1; i11 < 2; ++i11) {
                                    int i12 = -1;
                                    while (true) {
                                        block61: {
                                            try {
                                                try {
                                                    if (i12 >= 4) continue block45;
                                                    if (!world.isAirBlock(blockPos3.add(i10, i12, i11))) break block61;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw DeadClass.rethrow(runtimeException);
                                                }
                                                ++i8;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw DeadClass.rethrow(runtimeException);
                                            }
                                        }
                                        ++i12;
                                    }
                                }
                            }
                            try {
                                if (i8 >= 25) break block62;
                                break block51;
                            }
                            catch (RuntimeException runtimeException) {
                                throw DeadClass.rethrow(runtimeException);
                            }
                        }
                        hashMap.put(blockPos3, i8);
                        if (i8 > iMax) {
                            iMax = i8;
                        }
                    }
                    ++i7;
                }
            }
        }
        if (!hashMap.isEmpty()) {
            ArrayList<Map.Entry<BlockPos, Integer>> arrayList2 = new ArrayList<Map.Entry<BlockPos, Integer>>(hashMap.entrySet());
            arrayList2.sort((entry, entry2) -> ((Integer)entry2.getValue()).compareTo((Integer)entry.getValue()));
            f_2.O = new Vec3d((Vec3i)arrayList2.get(MathUtils.weightedRandomIndex(arrayList2.size() - 1)).getKey());
        } else {
            try {
                f_2.O = arrayList.isEmpty() ? new Vec3d((Vec3i)blockPos2.add((double)MathUtils.randomJitter(10.0f, true), (double)MathUtils.randomJitter(10.0f, false), (double)MathUtils.randomJitter(10.0f, true))) : new Vec3d((Vec3i)arrayList.get(ModConstants.Random.nextInt(arrayList.size())));
            }
            catch (RuntimeException runtimeException) {
                throw DeadClass.rethrow(runtimeException);
            }
        }
        f_2.bL = null;
        f_2.b(0);
        f_2.setCurrentAction(GirlAnimationState.FLY);
        NetworkHandler.channel.sendToAllTracking((IMessage)new PacketResetController(f_2.getGirlUuid()), (Entity)f_2);
    }, f_2 -> {
        Vec3d vec3d = f_2.getPositionVector();
        Vec3d vec3d2 = f_2.O;
        try {
            if (vec3d2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        f_2.bL = vec3d;
        int i6 = f_2.getCombatTargetId();
        try {
            f_2.b(i6 + 1);
            if (i6 != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        Vec3d vec3d3 = vec3d2.subtract(vec3d);
        Vec3d vec3d4 = vec3d3.normalize();
        f_2.motionX = vec3d4.x * (double)0.6f;
        f_2.motionZ = vec3d4.z * (double)0.6f;
        f_2.motionY = MathUtils.clamp(vec3d3.y * (double)0.6f, (double)-0.6f, (double)0.6f);
    }, f_2 -> {
        boolean flag3;
        try {
            flag3 = f_2.getCombatTargetId() > 23;
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        return flag3;
    }, f_2 -> {
        f_2.setMotionVector(Vec3d.ZERO);
        f_2.b(0);
        f_2.bL = null;
    }, false, f_2 -> true, false),
    SUMMON_SKELETON(f_2 -> {
        f_2.setCurrentAction(GirlAnimationState.SUMMON_SKELETON);
        f_2.ad = 0;
        EntityDataManager entityDataManager = f_2.getDataManager();
        entityDataManager.set(GalathNpc.RightBallActiveKey, true);
        entityDataManager.set(GalathNpc.LeftBallActiveKey, true);
        entityDataManager.set(GalathNpc.FlipSideKey, f_2.getRNG().nextBoolean());
        GirlEntity.playSoundRandom((GirlEntity)f_2, ModSounds.GIRLS_GALATH_STRONGCHARGE, true);
    }, f_2 -> {
        block14: {
            Vec3d vec3d;
            Vec3d vec3d2;
            EnergyBallEntity energyBall;
            Vec3d vec3d3;
            Vec3d vec3d4;
            Vec3d vec3d5;
            Random random;
            Vec3d vec3d6;
            block16: {
                block15: {
                    boolean flag4;
                    Vec3d vec3d7;
                    block11: {
                        Vec3d vec3d8;
                        Vec3d vec3d9;
                        block13: {
                            block12: {
                try {
                    f_2.setMotionVector(Vec3d.ZERO);
                    if ((float)f_2.ad != 30.0f) {
                        return;
                    }
                }
                                catch (RuntimeException runtimeException) {
                                    throw DeadClass.rethrow(runtimeException);
                                }
                                GalathNpc.rotateToTarget(f_2, 0.0f);
                                vec3d7 = f_2.getPositionVector();
                                vec3d6 = f_2.getTargetEntity().getPositionVector();
                                random = f_2.getRNG();
                                flag4 = (Boolean)f_2.getDataManager().get(GalathNpc.FlipSideKey);
                                try {
                                    try {
                                        if (!((Boolean)f_2.getDataManager().get(GalathNpc.RightBallActiveKey)).booleanValue()) break block11;
                                        vec3d9 = vec3d7;
                                        if (!flag4) break block12;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw DeadClass.rethrow(runtimeException);
                                    }
                                    vec3d8 = VectorMath.mirrorXZ(GalathNpc.RightBallOffset);
                                    break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw DeadClass.rethrow(runtimeException);
                                }
                            }
                            vec3d8 = GalathNpc.RightBallOffset;
                        }
                        vec3d5 = vec3d9.add(VectorMath.rotateYaw(vec3d8, 180.0f + f_2.renderYawOffset));
                        vec3d4 = vec3d6.subtract(vec3d5).normalize();
                        vec3d4 = new Vec3d(vec3d4.x + random.nextDouble() * (double)0.3f, vec3d4.y + random.nextDouble() * (double)0.3f, vec3d4.z + random.nextDouble() * (double)0.3f);
                        vec3d4 = vec3d4.normalize();
                        vec3d3 = new Vec3d(vec3d4.x * (double)0.4f, vec3d4.y * (double)0.4f, vec3d4.z * (double)0.4f);
                        energyBall = new EnergyBallEntity(f_2.world, f_2, vec3d3);
                        energyBall.setPositionAndUpdate(vec3d5.x, vec3d5.y, vec3d5.z);
                        f_2.world.spawnEntity((Entity)energyBall);
                    }
                    try {
                        try {
                            if (!((Boolean)f_2.getDataManager().get(GalathNpc.LeftBallActiveKey)).booleanValue()) break block14;
                            vec3d2 = vec3d7;
                            if (!flag4) break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw DeadClass.rethrow(runtimeException);
                        }
                        vec3d = VectorMath.mirrorXZ(GalathNpc.LeftBallOffset);
                        break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw DeadClass.rethrow(runtimeException);
                    }
                }
                vec3d = GalathNpc.LeftBallOffset;
            }
            vec3d5 = vec3d2.add(VectorMath.rotateYaw(vec3d, 180.0f + f_2.renderYawOffset));
            vec3d4 = vec3d6.subtract(vec3d5).normalize();
            vec3d4 = new Vec3d(vec3d4.x + random.nextDouble() * (double)0.3f, vec3d4.y + random.nextDouble() * (double)0.3f, vec3d4.z + random.nextDouble() * (double)0.3f);
            vec3d4 = vec3d4.normalize();
            vec3d3 = new Vec3d(vec3d4.x * (double)0.4f, vec3d4.y * (double)0.4f, vec3d4.z * (double)0.4f);
            energyBall = new EnergyBallEntity(f_2.world, f_2, vec3d3);
            energyBall.setPositionAndUpdate(vec3d5.x, vec3d5.y, vec3d5.z);
            f_2.world.spawnEntity((Entity)energyBall);
        }
    }, f_2 -> {
        boolean flag5;
        try {
            flag5 = f_2.ad >= 45;
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        return flag5;
    }, f_2 -> {
        f_2.ad = 0;
    }, true, f_2 -> {
        boolean flag6;
        try {
            flag6 = f_2.bI.size() < 2;
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        return flag6;
    }, true),
    ATTACK_SWORD(f_2 -> {
        f_2.a(0);
        f_2.setCurrentAction(GirlAnimationState.ATTACK_SWORD);
        f_2.setMotionVector(Vec3d.ZERO);
        Vec3d vec3d = f_2.getPositionVector();
        f_2.setManglePos(vec3d);
        Vec3d vec3d2 = f_2.getTargetEntity().getPositionVector();
        Vec2d vec2d = new Vec2d(vec3d2.x - vec3d.x, vec3d2.z - vec3d.z);
        double d = AngleMath.radToDegrees(Math.atan2(vec2d.Y, vec2d.X)) - 90.0;
        f_2.setShouldBeAtTargetPos(true);
        f_2.setTargetPos(vec3d);
        f_2.b((float)d);
        GirlEntity.playSoundRandom((GirlEntity)f_2, ModSounds.GIRLS_GALATH_STRONGCHARGE, true);
    }, f_2 -> {
        EntityLivingBase entityLivingBase = f_2.getTargetEntity();
        int i7 = f_2.getSpecialState() + 1;
        f_2.a(i7);
        if (MathUtils.isInRange((double)i7, 24.0, 32.0)) {
            Vec3d vec3d = entityLivingBase.getPositionVector().add(0.0, (double)entityLivingBase.getEyeHeight(), 0.0);
            Vec2d vec2d = new Vec2d(vec3d.x - f_2.posX, vec3d.z - f_2.posZ);
            double d = AngleMath.radToDegrees(Math.atan2(vec2d.Y, vec2d.X)) - 90.0;
            f_2.b((float)d);
            Vec3d vec3d2 = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 3.0), (float)(d + 180.0));
            Vec3d vec3d3 = f_2.getManglePos();
            Vec3d vec3d4 = vec3d.add(vec3d2);
            float f = (float)(i7 - 24) / 8.0f;
            Vec3d vec3d5 = LerpMath.lerpVec3d(vec3d3, vec3d4, (double)f);
            f_2.setTargetPos(vec3d5);
            return;
        }
        if (MathUtils.isInRange((double)i7, 32.0, 54.0)) {
            Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 1.5), f_2.I().floatValue() + 180.0f);
            Vec3d vec3d6 = entityLivingBase.getPositionVector().add(vec3d);
            f_2.setTargetPos(vec3d6);
            GalathProjectileDamageSource galathProjectileDamageSource = new GalathProjectileDamageSource(f_2);
            try {
                entityLivingBase.hurtTime = 0;
                entityLivingBase.hurtResistantTime = 0;
                if (i7 == 36) {
                    entityLivingBase.attackEntityFrom((DamageSource)galathProjectileDamageSource, 5.0f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw DeadClass.rethrow(runtimeException);
            }
            try {
                if (i7 != 40) return;
                entityLivingBase.attackEntityFrom((DamageSource)galathProjectileDamageSource, 5.0f);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw DeadClass.rethrow(runtimeException);
            }
        }
        if (i7 == 54) {
            f_2.setShouldBeAtTargetPos(false);
            f_2.setCurrentAction(GirlAnimationState.FLY);
            Vec3d vec3d = f_2.getManglePos().subtract(f_2.getPositionVector()).normalize();
            f_2.motionX = vec3d.x * (double)0.6f;
            f_2.motionY = vec3d.y * (double)0.6f;
            f_2.motionZ = vec3d.z * (double)0.6f;
            f_2.b(1);
            return;
        } else {
            f_2.b(f_2.getCombatTargetId() + 1);
        }
    }, f_2 -> {
        boolean flag7;
        try {
            flag7 = f_2.getCombatTargetId() > 23;
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        return flag7;
    }, f_2 -> {
        f_2.b(0);
        f_2.setMotionVector(Vec3d.ZERO);
        f_2.a(-1);
        f_2.setShouldBeAtTargetPos(false);
    }, true, f_2 -> true, false),
    RAPE(f_2 -> {
        f_2.setCurrentAction(GirlAnimationState.RAPE_PREPARE);
        f_2.aF = 0;
        f_2.bd = null;
        f_2.O = null;
        f_2.getDataManager().set(GalathNpc.bO, Float.valueOf(0.0f));
    }, f_2 -> {
        boolean flag8;
        Vec3d vec3d;
        double d2;
        Vec3d vec3d2;
        Vec3d vec3dO;
        Vec3d vec3d3;
        block29: {
            double d3;
            Vec3d vec3d4;
            try {
                if (++f_2.aF < 48) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw DeadClass.rethrow(runtimeException);
            }
            f_2.setCurrentAction(GirlAnimationState.RAPE_CHARGE);
            EntityLivingBase entityLivingBase = f_2.getTargetEntity();
            if (f_2.bd == null) {
                f_2.O = entityLivingBase.getPositionVector().add(0.0, (double)(entityLivingBase.getEyeHeight() / 2.0f), 0.0);
                f_2.bd = f_2.getPositionVector();
                vec3d3 = entityLivingBase.getPositionVector().subtract(f_2.getPositionVector()).normalize();
                f_2.b((float)(AngleMath.radToDegrees(Math.atan2(vec3d3.z, vec3d3.x)) - 90.0));
            }
            vec3d3 = f_2.getPositionVector();
            Vec3d vec3d5 = vec3d3.subtract((double)0.65f, (double)0.65f, (double)0.65f);
            Vec3d vec3d6 = vec3d3.add((double)0.65f, (double)0.65f, (double)0.65f);
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d5.x, vec3d5.y, vec3d5.z, vec3d6.x, vec3d6.y, vec3d6.z);
            List<EntityPlayer> list = f_2.world.getEntitiesWithinAABB(EntityPlayer.class, axisAlignedBB);
            for (EntityPlayer entityPlayer2 : list) {
                try {
                    if (entityPlayer2.isDead) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw DeadClass.rethrow(runtimeException);
                }
                try {
                    if (!entityPlayer2.onGround) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw DeadClass.rethrow(runtimeException);
                }
                try {
                    if (GirlEntity.getByUuidForSide(entityPlayer2.getPersistentID(), true) != null) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw DeadClass.rethrow(runtimeException);
                }
                vec3d4 = entityPlayer2.getPositionVector();
                vec3d2 = vec3d3.subtract(vec3d4);
                Vec3d vec3d7 = VectorMath.rotateYaw(vec3d2, f_2.I().floatValue());
                d2 = Math.abs(vec3d7.x);
                try {
                    if (d2 > (double)0.65f) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw DeadClass.rethrow(runtimeException);
                }
                for (EntityWitherSkeleton entityWitherSkeleton : f_2.bI) {
                    Vec3d vec3d8 = entityWitherSkeleton.getPositionVector();
                    entityWitherSkeleton.world.removeEntity((Entity)entityWitherSkeleton);
                    NetworkHandler.channel.sendToAllTracking((IMessage)new PacketSpawnEnergyBallParticles(vec3d8, true), new NetworkRegistry.TargetPoint(entityWitherSkeleton.dimension, vec3d8.x, vec3d8.y, vec3d8.z, 50.0));
                }
                f_2.bI.clear();
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entityPlayer2;
                f_2.setTargetPos(entityPlayer2.getPositionVector());
                f_2.handleGirlUuidEvent(entityPlayer2.getPersistentID());
                f_2.setShouldBeAtTargetPos(true);
                f_2.setCurrentAction(GirlAnimationState.RAPE_INTRO);
                byte bv = (byte)MathHelper.floor((float)((f_2.I().floatValue() + 180.0f) * 256.0f / 360.0f));
                NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), entityPlayerMP);
                entityPlayerMP.connection.sendPacket((Packet)new SPacketEntityVelocity(entityPlayerMP.getEntityId(), 0.0, 0.0, 0.0));
                entityPlayerMP.connection.sendPacket((Packet)new SPacketEntity.S16PacketEntityLook(entityPlayerMP.getEntityId(), bv, (byte)-14, true));
                return;
            }
            vec3d = f_2.bd;
            vec3dO = f_2.O;
            vec3d4 = vec3dO.subtract(vec3d);
            vec3d2 = vec3dO.add(vec3d4);
            vec3d2 = new Vec3d(vec3d2.x, vec3d.y, vec3d2.z);
            try {
                boolean flag9 = flag8 = vec3d3.distanceTo(new Vec3d(vec3d.x, vec3d3.y, vec3d.z)) > vec3d3.distanceTo(new Vec3d(vec3d2.x, vec3d3.y, vec3d2.z));
            }
            catch (RuntimeException runtimeException) {
                throw DeadClass.rethrow(runtimeException);
            }
            if (flag8) {
                d2 = VectorMath.inverseLerpComponent(vec3dO, vec3d2, vec3d3);
                d3 = vec3dO.distanceTo(vec3d2);
            } else {
                d2 = VectorMath.inverseLerpComponent(vec3d, vec3dO, vec3d3);
                d3 = vec3d.distanceTo(vec3dO);
            }
            double d4 = d3 / (double)0.05f;
            double d5 = 1.0 / d4 * 20.0;
            d2 += d5;
            try {
                try {
                    if (flag8 || !(d2 < (double)0.9f)) break block29;
                }
                catch (RuntimeException runtimeException) {
                    throw DeadClass.rethrow(runtimeException);
                }
                f_2.O = entityLivingBase.getPositionVector().add(0.0, (double)(entityLivingBase.getEyeHeight() / 2.0f), 0.0);
            }
            catch (RuntimeException runtimeException) {
                throw DeadClass.rethrow(runtimeException);
            }
        }
        vec3d3 = flag8 ? new Vec3d(LerpMath.lerp(vec3dO.x, vec3d2.x, Math.min(1.0, d2)), LerpMath.lerp(vec3dO.y, vec3d2.y, Math.min(1.0, LerpMath.easeInCubic(d2))), LerpMath.lerp(vec3dO.z, vec3d2.z, Math.min(1.0, d2))) : new Vec3d(LerpMath.lerp(vec3d.x, vec3dO.x, d2), LerpMath.lerp(vec3d.y, vec3dO.y, LerpMath.EaseOutCubic(d2)), LerpMath.lerp(vec3d.z, vec3dO.z, d2));
        try {
            f_2.setPosition(vec3d3.x, vec3d3.y, vec3d3.z);
            if (flag8) {
                f_2.getDataManager().set(GalathNpc.bO, Float.valueOf((float)d2));
            }
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
    }, f_2 -> {
        boolean flag10;
        try {
            if (f_2.getCurrentAction() == GirlAnimationState.RAPE_INTRO) {
                return true;
            }
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        Vec3d vec3d = f_2.bd;
        Vec3d vec3d2 = f_2.O;
        try {
            if (vec3d == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        Vec3d vec3d3 = vec3d2.subtract(vec3d);
        Vec3d vec3d4 = vec3d2.add(vec3d3);
        vec3d4 = new Vec3d(vec3d4.x, vec3d.y, vec3d4.z);
        try {
            flag10 = f_2.getDistance(vec3d4.x, vec3d4.y, vec3d4.z) < (double)0.1f;
        }
        catch (RuntimeException runtimeException) {
            throw DeadClass.rethrow(runtimeException);
        }
        return flag10;
    }, f_2 -> {
        f_2.O = null;
        f_2.bd = null;
        f_2.aF = 0;
        f_2.getDataManager().set(GalathNpc.bO, Float.valueOf(0.0f));
    }, true, f_2 -> true, true);

    final GirlPredicate CanDoPredicate;
    final GirlAction2 StartAction;
    final GirlAction3 ApplyAction;
    final GirlAction TickAction;
    final GirlPredicate2 ConditionalPredicate;
    public final boolean applyAttackCoolDown;
    public final boolean onlyDoThisOnPlayers;

    private DeadClass(GirlAction2 girlAction2, GirlAction3 girlAction3, GirlPredicate h_2, GirlAction girlAction, boolean flag, GirlPredicate2 girlPredicate2, boolean flag2) {
        this.CanDoPredicate = h_2;
        this.StartAction = girlAction2;
        this.ApplyAction = girlAction3;
        this.TickAction = girlAction;
        this.applyAttackCoolDown = flag;
        this.ConditionalPredicate = girlPredicate2;
        this.onlyDoThisOnPlayers = flag2;
    }

    public void start(GalathNpc f_2) {
        this.StartAction.a(f_2);
    }

    public boolean isDone(GalathNpc f_2) {
        return this.CanDoPredicate.a(f_2);
    }

    public void apply(GalathNpc f_2) {
        this.ApplyAction.a(f_2);
    }

    public void tick(GalathNpc f_2) {
        this.TickAction.a(f_2);
    }

    public boolean canDo(GalathNpc f_2) {
        return this.ConditionalPredicate.a(f_2);
    }

    private static RuntimeException rethrow(RuntimeException runtimeException) {
        return runtimeException;
    }
}

