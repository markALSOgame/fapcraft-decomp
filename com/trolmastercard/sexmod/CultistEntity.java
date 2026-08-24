package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CultistEntity extends EntityLiving {
   public static final long b = 60000L;
   public static final float g = 3.0F;
   static final float c = 30.0F;
   static final int WaypointTickLimit = 175;
   static final int WanderRange = 10;
   BlockPos TargetPos = null;
   int WaypointTicks = 0;
   boolean IsAggroed = false;
   public int HitTick = -1;

   public CultistEntity(World world) {
      super(world);
   }

   protected void updateAITasks() {
      super.updateAITasks();
      this.updateWanderTarget();
   }


   void updateWanderTarget() {
        block27: {
            block26: {
                block23: {
                    int i;
                    int i2;
                    int i3;
                    block25: {
                        block24: {
                            block21: {
                                try {
                                    if (this.IsAggroed) {
                                        this.getNavigator().clearPath();
                                        return;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw CultistEntity.rethrow(runtimeException);
                                }
                                EntityPlayer entityPlayer = this.world.getClosestPlayerToEntity((Entity)this, 15.0);
                                try {
                                    try {
                                        if (entityPlayer == null || !(entityPlayer.getDistance((Entity)this) < 3.0f)) break block21;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw CultistEntity.rethrow(runtimeException);
                                    }
                                    this.getNavigator().clearPath();
                                    return;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw CultistEntity.rethrow(runtimeException);
                                }
                            }
                            try {
                                try {
                                    block22: {
                                        try {
                                            try {
                                                if (this.TargetPos == null || this.getDistance(this.TargetPos.getX(), this.TargetPos.getY(), this.TargetPos.getZ()) > this.getMaxWanderDistance()) break block22;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw CultistEntity.rethrow(runtimeException);
                                            }
                                            if (this.WaypointTicks <= 175) break block23;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw CultistEntity.rethrow(runtimeException);
                                        }
                                    }
                                    if (!this.getRNG().nextBoolean()) break block24;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw CultistEntity.rethrow(runtimeException);
                                }
                                i3 = 1;
                                break block25;
                            }
                            catch (RuntimeException runtimeException) {
                                throw CultistEntity.rethrow(runtimeException);
                            }
                        }
                        i3 = -1;
                    }
                    int i4 = i3 * this.getRNG().nextInt(10);
                    try {
                        i2 = this.getRNG().nextBoolean() ? 1 : -1;
                    }
                    catch (RuntimeException runtimeException) {
                        throw CultistEntity.rethrow(runtimeException);
                    }
                    int i5 = i2 * this.getRNG().nextInt(10);
                    try {
                        i = this.world.provider.getDimensionType() == DimensionType.NETHER ? (int)Math.ceil(this.posY) : BedLogic.countNearbyBeds(this.world, this.getPosition().getX() + i4, this.getPosition().getZ() + i5);
                    }
                    catch (RuntimeException runtimeException) {
                        throw CultistEntity.rethrow(runtimeException);
                    }
                    int i6 = i;
                    this.TargetPos = new BlockPos(this.getPosition().getX() + i4, i6, this.getPosition().getZ() + i5);
                    this.WaypointTicks = 0;
                }
                try {
                    if (!(Math.sqrt(this.TargetPos.distanceSq((Vec3i)this.getPosition())) > 2.0)) break block26;
                    this.getNavigator().tryMoveToXYZ((double)this.TargetPos.getX(), (double)this.TargetPos.getY(), (double)this.TargetPos.getZ(), (double)0.35f);
                    this.applyPathVelocity();
                    break block27;
                }
                catch (RuntimeException runtimeException) {
                    throw CultistEntity.rethrow(runtimeException);
                }
            }
            ++this.WaypointTicks;
        }
    }


   protected void applyPathVelocity() {
        int i;
        Path path;
        block12: {
            block11: {
                path = this.getNavigator().getPath();
                try {
                    if (path == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw CultistEntity.rethrow(runtimeException);
                }
                try {
                    try {
                        if (!this.onGround && !this.isInWater()) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw CultistEntity.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw CultistEntity.rethrow(runtimeException);
                }
            }
            i = path.getCurrentPathIndex();
            int i2 = path.getCurrentPathLength();
            try {
                try {
                    if (i2 != i && i2 - 1 != i) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw CultistEntity.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw CultistEntity.rethrow(runtimeException);
            }
        }
        PathPoint pathPoint = path.getPathPointFromIndex(i);
        PathPoint pathPoint2 = path.getPathPointFromIndex(i + 1);
        Vec3d vec3d = new Vec3d((double)(pathPoint2.x - pathPoint.x), (double)(pathPoint2.y - pathPoint.y), (double)(pathPoint2.z - pathPoint.z));
        this.motionX = vec3d.x / 7.0;
        this.motionZ = vec3d.z / 7.0;
    }

   public boolean attackEntityFrom(DamageSource damage, float f) {
      try {
         if (damage == DamageSource.OUT_OF_WORLD) {
            this.world.removeEntity(this);
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!(damage.getTrueSource() instanceof EntityPlayer)) {
            return false;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (this.world.isRemote) {
            this.playWeowSound();
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      this.IsAggroed = true;
      MathUtils.runAfterDelay(6250, () -> this.world.removeEntity(this));
      return false;
   }

   @SideOnly(Side.CLIENT)
   void playWeowSound() {
      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
      this.HitTick = mcPlayer.ticksExisted;
      mcPlayer.playSound(ModSounds.MISC_WEOWEO[3], 1.0F, 1.0F);
   }

   double getMaxWanderDistance() {
      return Math.sqrt(1800.0);
   }


   public boolean getCanSpawnHere() {
        block4: {
            try {
                try {
                    if (this.getRNG().nextInt(100) >= 1 || this.getRNG().nextInt(100) >= 10) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw CultistEntity.rethrow(runtimeException);
                }
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw CultistEntity.rethrow(runtimeException);
            }
        }
        this.world.removeEntity((Entity)this);
        return false;
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
