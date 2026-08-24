package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LunaFamiliarEntity extends Entity {
   public static final int m = 15;
   private static final DataParameter<Integer> HookedEntityIdKey = EntityDataManager.createKey(LunaFamiliarEntity.class, DataSerializers.VARINT).getSerializer().createKey(111);
   private static final DataParameter<Optional<UUID>> OwnerUuidKey = EntityDataManager.createKey(LunaFamiliarEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID)
      .getSerializer()
      .createKey(110);
   private boolean InWater;
   private int TicksInGround;
   private int TicksInAir;
   public int DiveTicks;
   private int RestTicks;
   private int ArcTicks;
   private float ArcAngle;
   public Entity HookedEntity;
   private LunaFamiliarEntity.HookState State = LunaFamiliarEntity.HookState.FLYING;
   private int Luck;
   private int LureSpeed;
   public static LunaNpc SummonerNpc = null;

   public LunaFamiliarEntity(World world, LunaNpc luna, double d) {
      super(world);
      this.bindToNpc(luna);
      this.launch(d);
   }

   public LunaFamiliarEntity(World world) {
      super(world);
   }

   private void bindToNpc(LunaNpc luna) {
      this.setSize(0.25F, 0.25F);
      this.ignoreFrustumCheck = true;
      luna.Familiar = this;
   }

   protected void entityInit() {
      this.getDataManager().register(HookedEntityIdKey, 0);
      this.getDataManager().register(OwnerUuidKey, Optional.of(SummonerNpc.f()));
   }

   public AxisAlignedBB getRenderBoundingBox() {
      return this.getEntityBoundingBox().grow(10.0);
   }

   LunaNpc getOwnerNpc() {
      Optional optional = (Optional)this.dataManager.get(OwnerUuidKey);

      try {
         if (!optional.isPresent()) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GirlEntity girl = GirlEntity.getServerSideByUuid((UUID)optional.get());

      try {
         if (girl == null) {
            return null;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (!(girl instanceof LunaNpc)) {
            return null;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      return (LunaNpc)girl;
   }

   LunaNpc getOwnerNpcClient() {
      Optional optional = (Optional)this.dataManager.get(OwnerUuidKey);

      try {
         if (!optional.isPresent()) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GirlEntity girl = GirlEntity.getClientSideByUuid((UUID)optional.get());

      try {
         if (!(girl instanceof LunaNpc)) {
            return null;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return (LunaNpc)girl;
   }

   public void setLureSpeed(int i) {
      this.LureSpeed = i;
   }

   public void setLuck(int i) {
      this.Luck = i;
   }


   public void onEntityUpdate() {
        block9: {
            try {
                super.onEntityUpdate();
                if (this.world.isRemote) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaFamiliarEntity.rethrow(runtimeException);
            }
            try {
                try {
                    try {
                        if (this.HookedEntity == null && !this.onGround) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarEntity.rethrow(runtimeException);
                    }
                    if (this.DiveTicks != 0) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaFamiliarEntity.rethrow(runtimeException);
                }
                this.getOwnerNpc().o();
            }
            catch (RuntimeException runtimeException) {
                throw LunaFamiliarEntity.rethrow(runtimeException);
            }
        }
    }

   public void launch(double d) {
      LunaNpc luna = this.getOwnerNpc();

      try {
         if (luna == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      BlockPos pos = luna.MoveTargetPos;
      float f = (float)Math.sqrt(luna.getPositionVector().squareDistanceTo(pos.getX(), pos.getY(), pos.getZ()));
      float f2 = -22.5F + 45.0F * (f / 7.0F);
      float f3 = luna.I();
      float f4 = MathHelper.cos(-f3 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f5 = MathHelper.sin(-f3 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float f6 = -MathHelper.cos(-f2 * (float) (Math.PI / 180.0));
      float f7 = MathHelper.sin(-f2 * (float) (Math.PI / 180.0));
      double d2 = luna.prevPosX + (luna.posX - luna.prevPosX) - f5 * 0.3;
      double d3 = luna.prevPosY + (luna.posY - luna.prevPosY) + luna.getEyeHeight();
      double d4 = luna.prevPosZ + (luna.posZ - luna.prevPosZ) - f4 * 0.3;
      this.setLocationAndAngles(d2, d3, d4, f3, f2);
      this.motionX = d * -f5;
      this.motionY = d * MathHelper.clamp(-(f7 / f6), -5.0F, 5.0F);
      this.motionZ = d * -f4;
      float f8 = MathHelper.sqrt(
         this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ
      );
      this.motionX = this.motionX * (0.6 / f8 + 0.5 + this.rand.nextGaussian() * 0.0045);
      this.motionY = this.motionY * (0.6 / f8 + 0.5 + this.rand.nextGaussian() * 0.0045);
      this.motionZ = this.motionZ * (0.6 / f8 + 0.5 + this.rand.nextGaussian() * 0.0045);
      float f9 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180.0 / Math.PI));
      this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f9) * (180.0 / Math.PI));
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
   }

   public void notifyDataManagerChange(DataParameter<?> dataParam) {
      if (HookedEntityIdKey.equals(dataParam)) {
         int i = (Integer)this.getDataManager().get(HookedEntityIdKey);

         LunaFamiliarEntity familiar;
         Entity entity;
         label19: {
            try {
               familiar = this;
               if (i > 0) {
                  entity = this.world.getEntityByID(i - 1);
                  break label19;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            entity = null;
         }

         familiar.HookedEntity = entity;
      }

      super.notifyDataManagerChange(dataParam);
   }

   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRenderDist(double d) {
      double d2 = 64.0;

      try {
         if (d < 4096.0) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   @SideOnly(Side.CLIENT)
   public void setPositionAndRotationDirect(double d, double d2, double d3, float f, float f2, int i, boolean flag) {
   }


   public void onUpdate() {
        block43: {
            double d;
            IBlockState iBlockState;
            block48: {
                BlockPos blockPos;
                float f2;
                block49: {
                    block50: {
                        block51: {
                            block45: {
                                block47: {
                                    block46: {
                                        block44: {
                                            block42: {
                                                try {
                                                    super.onUpdate();
                                                    if (this.getOwnerNpc() != null) break block42;
                                                    this.setDead();
                                                    break block43;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw LunaFamiliarEntity.rethrow(runtimeException);
                                                }
                                            }
                                            try {
                                                try {
                                                    try {
                                                        try {
                                                            if (!this.world.isRemote && this.f()) break block43;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw LunaFamiliarEntity.rethrow(runtimeException);
                                                        }
                                                        if (!this.InWater) break block44;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw LunaFamiliarEntity.rethrow(runtimeException);
                                                    }
                                                    ++this.TicksInGround;
                                                    if (this.TicksInGround < 1200) break block44;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw LunaFamiliarEntity.rethrow(runtimeException);
                                                }
                                                this.setDead();
                                                return;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw LunaFamiliarEntity.rethrow(runtimeException);
                                            }
                                        }
                                        f2 = 0.0f;
                                        blockPos = new BlockPos((Entity)this);
                                        iBlockState = this.world.getBlockState(blockPos);
                                        if (iBlockState.getMaterial() == Material.WATER) {
                                            f2 = BlockLiquid.getBlockLiquidHeight((IBlockState)iBlockState, (IBlockAccess)this.world, (BlockPos)blockPos);
                                        }
                                        try {
                                            try {
                                                if (this.State != LunaFamiliarEntity.HookState.FLYING) break block45;
                                                if (this.HookedEntity == null) break block46;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw LunaFamiliarEntity.rethrow(runtimeException);
                                            }
                                            this.motionX = 0.0;
                                            this.motionY = 0.0;
                                            this.motionZ = 0.0;
                                            this.State = LunaFamiliarEntity.HookState.HOOKED_IN_ENTITY;
                                            return;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw LunaFamiliarEntity.rethrow(runtimeException);
                                        }
                                    }
                                    try {
                                        if (f2 > 0.0f) {
                                            this.motionX *= 0.3;
                                            this.motionY *= 0.2;
                                            this.motionZ *= 0.3;
                                            this.State = LunaFamiliarEntity.HookState.BOBBING;
                                            return;
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaFamiliarEntity.rethrow(runtimeException);
                                    }
                                    try {
                                        if (!this.world.isRemote) {
                                            this.attemptHook();
                                        }
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaFamiliarEntity.rethrow(runtimeException);
                                    }
                                    try {
                                        try {
                                            try {
                                                if (this.InWater || this.onGround) break block47;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw LunaFamiliarEntity.rethrow(runtimeException);
                                            }
                                            if (this.collidedHorizontally) break block47;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw LunaFamiliarEntity.rethrow(runtimeException);
                                        }
                                        ++this.TicksInAir;
                                        break block48;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaFamiliarEntity.rethrow(runtimeException);
                                    }
                                }
                                this.TicksInAir = 0;
                                this.motionX = 0.0;
                                this.motionY = 0.0;
                                this.motionZ = 0.0;
                                break block48;
                            }
                            try {
                                try {
                                    try {
                                        if (this.State != LunaFamiliarEntity.HookState.HOOKED_IN_ENTITY) break block49;
                                        if (this.HookedEntity == null) break block50;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaFamiliarEntity.rethrow(runtimeException);
                                    }
                                    if (!this.HookedEntity.isDead) break block51;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw LunaFamiliarEntity.rethrow(runtimeException);
                                }
                                this.HookedEntity = null;
                                this.State = LunaFamiliarEntity.HookState.FLYING;
                                break block50;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaFamiliarEntity.rethrow(runtimeException);
                            }
                        }
                        this.posX = this.HookedEntity.posX;
                        double d2 = this.HookedEntity.height;
                        this.posY = this.HookedEntity.getEntityBoundingBox().minY + d2 * 0.8;
                        this.posZ = this.HookedEntity.posZ;
                        this.setPosition(this.posX, this.posY, this.posZ);
                    }
                    return;
                }
                if (this.State == LunaFamiliarEntity.HookState.BOBBING) {
                    this.motionX *= 0.9;
                    this.motionZ *= 0.9;
                    d = this.posY + this.motionY - (double)blockPos.getY() - (double)f2;
                    if (Math.abs(d) < 0.01) {
                        d += Math.signum(d) * 0.1;
                    }
                    try {
                        try {
                            this.motionY -= d * (double)this.rand.nextFloat() * 0.2;
                            if (this.world.isRemote || !(f2 > 0.0f)) break block48;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaFamiliarEntity.rethrow(runtimeException);
                        }
                        this.handleBobbing(blockPos);
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarEntity.rethrow(runtimeException);
                    }
                }
            }
            try {
                if (iBlockState.getMaterial() != Material.WATER) {
                    this.motionY -= 0.03;
                }
            }
            catch (RuntimeException runtimeException) {
                throw LunaFamiliarEntity.rethrow(runtimeException);
            }
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.smoothRotation();
            d = 0.92;
            this.motionX *= 0.92;
            this.motionY *= 0.92;
            this.motionZ *= 0.92;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

   private boolean f() {
      return false;
   }

   private void smoothRotation() {
      float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180.0 / Math.PI));
      this.rotationPitch = (float)(MathHelper.atan2(this.motionY, f) * (180.0 / Math.PI));

      try {
         while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
            this.prevRotationPitch -= 360.0F;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
      this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
   }


   private void attemptHook() {
        block16: {
            block17: {
                Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
                Vec3d vec3d2 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
                RayTraceResult rayTraceResult = this.world.rayTraceBlocks(vec3d, vec3d2, false, true, false);
                vec3d = new Vec3d(this.posX, this.posY, this.posZ);
                vec3d2 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
                if (rayTraceResult != null) {
                    vec3d2 = new Vec3d(rayTraceResult.hitVec.x, rayTraceResult.hitVec.y, rayTraceResult.hitVec.z);
                }
                Entity entity = null;
                List list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0));
                double d = 0.0;
                for (Entity entity2 : list) {
                    AxisAlignedBB axisAlignedBB;
                    RayTraceResult rayTraceResult2;
                    block15: {
                        try {
                            try {
                                if (!this.canHookEntity(entity2)) continue;
                                if (entity2 != this.getOwnerNpc()) break block15;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaFamiliarEntity.rethrow(runtimeException);
                            }
                            if (this.TicksInAir < 5) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaFamiliarEntity.rethrow(runtimeException);
                        }
                    }
                    if ((rayTraceResult2 = (axisAlignedBB = entity2.getEntityBoundingBox().grow((double)0.3f)).calculateIntercept(vec3d, vec3d2)) == null) continue;
                    double d2 = vec3d.squareDistanceTo(rayTraceResult2.hitVec);
                    try {
                        if (!(d2 < d) && d != 0.0) continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarEntity.rethrow(runtimeException);
                    }
                    entity = entity2;
                    d = d2;
                }
                if (entity != null) {
                    rayTraceResult = new RayTraceResult(entity);
                }
                try {
                    try {
                        try {
                            if (rayTraceResult == null || rayTraceResult.typeOfHit == RayTraceResult.Type.MISS) break block16;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaFamiliarEntity.rethrow(runtimeException);
                        }
                        if (rayTraceResult.typeOfHit != RayTraceResult.Type.ENTITY) break block17;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarEntity.rethrow(runtimeException);
                    }
                    this.HookedEntity = rayTraceResult.entityHit;
                    this.syncHookedEntity();
                    break block16;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaFamiliarEntity.rethrow(runtimeException);
                }
            }
            this.InWater = true;
        }
    }

   private void syncHookedEntity() {
      this.getDataManager().set(HookedEntityIdKey, this.HookedEntity.getEntityId() + 1);
   }


   private void handleBobbing(BlockPos pos) {
        int i;
        WorldServer worldServer;
        block34: {
            block35: {
                double d;
                double d2;
                double d3;
                float f;
                float f2;
                block36: {
                    block32: {
                        block33: {
                            block31: {
                                BlockPos blockPos2;
                                block30: {
                                    worldServer = (WorldServer)this.world;
                                    i = 1;
                                    blockPos2 = pos.up();
                                    try {
                                        try {
                                            if (!(this.rand.nextFloat() < 0.25f) || !this.world.isRainingAt(blockPos2)) break block30;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw LunaFamiliarEntity.rethrow(runtimeException);
                                        }
                                        ++i;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaFamiliarEntity.rethrow(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (!(this.rand.nextFloat() < 0.5f) || this.world.canSeeSky(blockPos2)) break block31;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw LunaFamiliarEntity.rethrow(runtimeException);
                                    }
                                    --i;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw LunaFamiliarEntity.rethrow(runtimeException);
                                }
                            }
                            try {
                                try {
                                    if (this.DiveTicks <= 0) break block32;
                                    --this.DiveTicks;
                                    if (this.DiveTicks > 0) break block33;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw LunaFamiliarEntity.rethrow(runtimeException);
                                }
                                this.RestTicks = 0;
                                this.ArcTicks = 0;
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaFamiliarEntity.rethrow(runtimeException);
                            }
                        }
                        this.motionY -= 0.2 * (double)this.rand.nextFloat() * (double)this.rand.nextFloat();
                        return;
                    }
                    try {
                        if (this.ArcTicks <= 0) break block34;
                        this.ArcTicks -= i;
                        if (this.ArcTicks <= 0) break block35;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarEntity.rethrow(runtimeException);
                    }
                    this.ArcAngle = (float)((double)this.ArcAngle + this.rand.nextGaussian() * 4.0);
                    float f3 = this.ArcAngle * ((float)Math.PI / 180);
                    f2 = MathHelper.sin((float)f3);
                    f = MathHelper.cos((float)f3);
                    d3 = this.posX + (double)(f2 * (float)this.ArcTicks * 0.1f);
                    d2 = (float)MathHelper.floor((double)this.getEntityBoundingBox().minY) + 1.0f;
                    d = this.posZ + (double)(f * (float)this.ArcTicks * 0.1f);
                    IBlockState iBlockState = worldServer.getBlockState(new BlockPos(d3, d2 - 1.0, d));
                    try {
                        try {
                            if (iBlockState.getMaterial() != Material.WATER) return;
                            if (!(this.rand.nextFloat() < 0.15f)) break block36;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaFamiliarEntity.rethrow(runtimeException);
                        }
                        worldServer.spawnParticle(EnumParticleTypes.WATER_BUBBLE, d3, d2 - (double)0.1f, d, 1, (double)f2, 0.1, (double)f, 0.0, new int[0]);
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarEntity.rethrow(runtimeException);
                    }
                }
                float f4 = f2 * 0.04f;
                float f5 = f * 0.04f;
                worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, d3, d2, d, 0, (double)f5, 0.01, (double)(-f4), 1.0, new int[0]);
                worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, d3, d2, d, 0, (double)(-f5), 0.01, (double)f4, 1.0, new int[0]);
                return;
            }
            this.motionY = -0.4f * MathHelper.nextFloat((Random)this.rand, (float)0.6f, (float)1.0f);
            this.playSound(SoundEvents.ENTITY_BOBBER_SPLASH, 0.25f, 1.0f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
            double d4 = this.getEntityBoundingBox().minY + 0.5;
            worldServer.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, d4, this.posZ, (int)(1.0f + this.width * 20.0f), (double)this.width, 0.0, (double)this.width, (double)0.2f, new int[0]);
            worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, this.posX, d4, this.posZ, (int)(1.0f + this.width * 20.0f), (double)this.width, 0.0, (double)this.width, (double)0.2f, new int[0]);
            this.DiveTicks = MathHelper.getInt((Random)this.rand, (int)20, (int)40);
            return;
        }
        if (this.RestTicks > 0) {
            this.RestTicks -= i;
            float f6 = 0.15f;
            if (this.RestTicks < 20) {
                f6 = (float)((double)f6 + (double)(20 - this.RestTicks) * 0.05);
            } else if (this.RestTicks < 40) {
                f6 = (float)((double)f6 + (double)(40 - this.RestTicks) * 0.02);
            } else if (this.RestTicks < 60) {
                f6 = (float)((double)f6 + (double)(60 - this.RestTicks) * 0.01);
            }
            if (this.rand.nextFloat() < f6) {
                float f7 = MathHelper.nextFloat((Random)this.rand, (float)0.0f, (float)360.0f) * ((float)Math.PI / 180);
                float f8 = MathHelper.nextFloat((Random)this.rand, (float)25.0f, (float)60.0f);
                double d5 = this.posX + (double)(MathHelper.sin((float)f7) * f8 * 0.1f);
                double d6 = (float)MathHelper.floor((double)this.getEntityBoundingBox().minY) + 1.0f;
                double d7 = this.posZ + (double)(MathHelper.cos((float)f7) * f8 * 0.1f);
                IBlockState iBlockState = worldServer.getBlockState(new BlockPos((int)d5, (int)d6 - 1, (int)d7));
                try {
                    if (iBlockState.getMaterial() == Material.WATER) {
                        worldServer.spawnParticle(EnumParticleTypes.WATER_SPLASH, d5, d6, d7, 2 + this.rand.nextInt(2), (double)0.1f, 0.0, (double)0.1f, 0.0, new int[0]);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw LunaFamiliarEntity.rethrow(runtimeException);
                }
            }
            try {
                if (this.RestTicks > 0) return;
                this.ArcAngle = MathHelper.nextFloat((Random)this.rand, (float)0.0f, (float)360.0f);
                this.ArcTicks = MathHelper.getInt((Random)this.rand, (int)20, (int)80);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw LunaFamiliarEntity.rethrow(runtimeException);
            }
        }
        this.RestTicks = MathHelper.getInt((Random)this.rand, (int)100, (int)600);
        this.RestTicks -= this.LureSpeed * 20 * 5;
    }


   protected boolean canHookEntity(Entity entity) {
        boolean flag;
        block5: {
            block4: {
                try {
                    try {
                        if (!entity.canBeCollidedWith() && !(entity instanceof EntityItem)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarEntity.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw LunaFamiliarEntity.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
   }


   public int getRodDamage() {
        block11: {
            int i;
            Object obj;
            int i2;
            block15: {
                block12: {
                    int i3;
                    block14: {
                        block13: {
                            try {
                                if (this.world.isRemote || this.getOwnerNpc() == null) break block11;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaFamiliarEntity.rethrow(runtimeException);
                            }
                            i2 = 0;
                            obj = null;
                            try {
                                try {
                                    if (this.HookedEntity == null) break block12;
                                    this.pullHookedEntity();
                                    this.world.setEntityState((Entity)this, (byte)31);
                                    if (!(this.HookedEntity instanceof EntityItem)) break block13;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw LunaFamiliarEntity.rethrow(runtimeException);
                                }
                                i3 = 3;
                                break block14;
                            }
                            catch (RuntimeException runtimeException) {
                                throw LunaFamiliarEntity.rethrow(runtimeException);
                            }
                        }
                        i3 = 5;
                    }
                    i2 = i3;
                    break block15;
                }
                if (this.DiveTicks > 0) {
                    LootContext.Builder builder = new LootContext.Builder((WorldServer)this.world);
                    List list = this.world.getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING).generateLootForPools(this.rand, builder.build());
                    for (ItemStack itemStack : list) {
                        LunaNpc luna = this.getOwnerNpc();
                        luna.b(itemStack);
                    }
                    this.DiveTicks = 9999;
                    i2 = 1;
                }
            }
            if (this.InWater) {
                i2 = 2;
            }
            try {
                i = obj == null ? i2 : obj.getRodDamage();
            }
            catch (RuntimeException runtimeException) {
                throw LunaFamiliarEntity.rethrow(runtimeException);
            }
            return i;
        }
        return 0;
    }

   protected void pullHookedEntity() {
      LunaNpc luna = this.getOwnerNpc();
      if (luna != null) {
         double d = luna.posX - this.posX;
         double d2 = luna.posY - this.posY;
         double d3 = luna.posZ - this.posZ;
         double d4 = 0.1;
         this.HookedEntity.motionX += d * 0.1;
         this.HookedEntity.motionY += d2 * 0.1;
         this.HookedEntity.motionZ += d3 * 0.1;
      }
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
   }

   public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
      return null;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   enum HookState {
      FLYING,
      HOOKED_IN_ENTITY,
      BOBBING;
   }
}
