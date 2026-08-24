package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class SlimeRainEntity extends EntityLiving {
   public static int RainDurationTicks = 8400;
   public static List<SlimeRainEntity> ActiveRains = new ArrayList<>();
   private static final DataParameter<Integer> SizeKey = EntityDataManager.createKey(SlimeRainEntity.class, DataSerializers.VARINT).getSerializer().createKey(111);
   private static final DataParameter<Integer> TimerKey = EntityDataManager.createKey(SlimeRainEntity.class, DataSerializers.VARINT).getSerializer().createKey(110);
   public float SquishTarget;
   public float SquishFactor;
   public float PrevSquishFactor;
   private boolean WasOnGround;

   public SlimeRainEntity(World world) {
      super(world);
      this.moveHelper = new SlimeRainEntity.SlimeMoveHelper(this);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new SlimeRainEntity.RandomHopAi(this));
      this.tasks.addTask(5, new SlimeRainEntity.MoveSlimeAi(this));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(TimerKey, 1);
      this.dataManager.register(SizeKey, 0);
   }

   public void fall(float f, float f2) {
   }

   protected boolean canDespawn() {
      return false;
   }

   protected void setSlimeSize(int i, boolean flag) {
      try {
         this.dataManager.set(TimerKey, i);
         this.setSize(0.51000005F * i, 0.51000005F * i);
         this.setPosition(this.posX, this.posY, this.posZ);
         this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(i * i);
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2F + 0.1F * i);
         if (flag) {
            this.setHealth(this.getMaxHealth());
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.experienceValue = i;
   }

   public int getSlimeSize() {
      return (Integer)this.dataManager.get(TimerKey);
   }

   public static void registerFixes(DataFixer dataFixer) {
      EntityLiving.registerFixesMob(dataFixer, SlimeRainEntity.class);
   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("Size", this.getSlimeSize() - 1);
      tagCompound.setBoolean("wasOnGround", this.WasOnGround);
      tagCompound.setInteger("ageInTicks", (Integer)this.dataManager.get(SizeKey));
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      int i = tagCompound.getInteger("Size");
      if (i < 0) {
         i = 0;
      }

      this.setSlimeSize(i + 1, false);
      this.WasOnGround = tagCompound.getBoolean("wasOnGround");
      this.dataManager.set(SizeKey, tagCompound.getInteger("ageInTicks"));
   }

   public boolean isSmallSlime() {
      try {
         if (this.getSlimeSize() <= 1) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   protected EnumParticleTypes getParticleType() {
      return EnumParticleTypes.SLIME;
   }

   public static ArrayList<SlimeRainEntity> getNearbySlimes(Vec3d vec3d) {
      ArrayList list = getNearbySlimesInRadius(vec3d, 0.1);
      if (list.isEmpty()) {
         list = getNearbySlimesInRadius(vec3d, 0.5);
      }

      return list;
   }


   private static ArrayList<SlimeRainEntity> getNearbySlimesInRadius(Vec3d vec3d, double d) {
        ArrayList<SlimeRainEntity> arrayList = new ArrayList<SlimeRainEntity>();
        try {
            for (SlimeRainEntity slimeRainEntity : ActiveRains) {
                try {
                    if (slimeRainEntity == null) {
                        continue;
                    }
                }
                catch (Exception exception) {
                    throw SlimeRainEntity.rethrow(exception);
                }
                double d2 = Math.abs(slimeRainEntity.prevPosX - vec3d.x) + Math.abs(slimeRainEntity.prevPosY - vec3d.y) + Math.abs(slimeRainEntity.prevPosZ - vec3d.z);
                if (slimeRainEntity.world == null) continue;
                try {
                    block10: {
                        if (!(d2 < d)) continue;
                        break block10;
                        catch (Exception exception) {
                            throw SlimeRainEntity.rethrow(exception);
                        }
                    }
                    arrayList.add(slimeRainEntity);
                }
                catch (Exception exception) {
                    throw SlimeRainEntity.rethrow(exception);
                    return arrayList;
                }
            }
        }
        catch (Exception exception) {
            System.out.println("couldnt find slimes at distance " + d);
        }
        return arrayList;
    }

   public Vec3d getPositionVec3d() {
      return new Vec3d(this.prevPosX, this.prevPosY, this.prevPosZ);
   }

   void spawnParticle(EnumParticleTypes particle) {
      double d = ModConstants.Random.nextGaussian() * 0.02;
      double d2 = ModConstants.Random.nextGaussian() * 0.02;
      double d3 = ModConstants.Random.nextGaussian() * 0.02;
      this.world
         .spawnParticle(
            particle,
            this.posX + ModConstants.Random.nextFloat() * this.width * 2.0F - this.width,
            this.posY + 0.15 + ModConstants.Random.nextFloat() * this.height,
            this.posZ + ModConstants.Random.nextFloat() * this.width * 2.0F - this.width,
            d,
            d2,
            d3,
            new int[0]
         );
   }


   public void onUpdate() {
        block21: {
            block20: {
                block19: {
                    block17: {
                        block18: {
                            try {
                                try {
                                    this.dataManager.set(SlimeRainEntity.RandomHopAi, (Object)((Integer)this.dataManager.get(SlimeRainEntity.RandomHopAi) + 1));
                                    if (!this.world.isRemote) break block17;
                                    if (!((double)((Integer)this.dataManager.get(SlimeRainEntity.RandomHopAi)).intValue() > (double)SlimeRainEntity.SlimeMoveHelper * 0.95)) break block18;
                                }
                                catch (RuntimeException error) {
                                    throw SlimeRainEntity.rethrow(error);
                                }
                                this.spawnParticle(EnumParticleTypes.CLOUD);
                                break block19;
                            }
                            catch (RuntimeException error2) {
                                throw SlimeRainEntity.rethrow(error2);
                            }
                        }
                        try {
                            try {
                                if (!((double)((Integer)this.dataManager.get(SlimeRainEntity.RandomHopAi)).intValue() > (double)SlimeRainEntity.SlimeMoveHelper * 0.7) || this.ticksExisted % 10 != 0) ** GOTO lbl32
                            }
                            catch (RuntimeException error3) {
                                throw SlimeRainEntity.rethrow(error3);
                            }
                            this.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY);
                        }
                        catch (RuntimeException error4) {
                            throw SlimeRainEntity.rethrow(error4);
                        }
                    }
                    if ((Integer)this.dataManager.get(SlimeRainEntity.RandomHopAi) > SlimeRainEntity.SlimeMoveHelper) {
                        slime = new SlimeNpc(this.world);
                        slime.setPositionAndRotation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
                        this.world.spawnEntity((Entity)slime);
                        slime.a(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
                        this.world.removeEntity((Entity)this);
                    }
                }
                try {
                    this.SquishFactor += (this.SquishTarget - this.SquishFactor) * 0.5f;
                    this.PrevSquishFactor = this.SquishFactor;
                    super.onUpdate();
                    if (!this.onGround || this.WasOnGround) break block20;
                }
                catch (RuntimeException error5) {
                    throw SlimeRainEntity.rethrow(error5);
                }
                i = this.getSlimeSize();
                if (this.isParticleSuppressed()) {
                    i = 0;
                }
                for (i2 = 0; i2 < i * 8; ++i2) {
                    f = this.rand.nextFloat() * 6.2831855f;
                    f2 = this.rand.nextFloat() * 0.5f + 0.5f;
                    f3 = MathHelper.sin((float)f) * (float)i * 0.5f * f2;
                    f4 = MathHelper.cos((float)f) * (float)i * 0.5f * f2;
                    world2 = this.world;
                    particle = this.getParticleType();
                    d = this.posX + (double)f3;
                    d2 = this.posZ + (double)f4;
                    world2.spawnParticle(particle, d, this.getEntityBoundingBox().minY, d2, 0.0, 0.0, 0.0, new int[0]);
                }
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) / 0.8f);
                this.SquishTarget = -0.5f;
                break block21;
            }
            try {
                try {
                    if (this.onGround || !this.WasOnGround) break block21;
                }
                catch (RuntimeException error6) {
                    throw SlimeRainEntity.rethrow(error6);
                }
                this.SquishTarget = 1.0f;
            }
            catch (RuntimeException error7) {
                throw SlimeRainEntity.rethrow(error7);
            }
        }
        this.WasOnGround = this.onGround;
        this.updateSquish();
    }

   protected void updateSquish() {
      this.SquishTarget *= 0.6F;
   }

   protected int getRandomJumpDelay() {
      return this.rand.nextInt(100) + 50;
   }

   protected SlimeRainEntity createInstance() {
      return new SlimeRainEntity(this.world);
   }


   public void notifyDataManagerChange(DataParameter<?> dataParam) {
        block5: {
            if (TimerKey.equals(dataParam)) {
                int i = this.getSlimeSize();
                try {
                    try {
                        this.setSize(0.51000005f * (float)i, 0.51000005f * (float)i);
                        this.rotationYaw = this.rotationYawHead;
                        this.renderYawOffset = this.rotationYawHead;
                        if (!this.isInWater() || this.rand.nextInt(20) != 0) break block5;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimeRainEntity.rethrow(runtimeException);
                    }
                    this.doWaterSplashEffect();
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeRainEntity.rethrow(runtimeException);
                }
            }
        }
        super.notifyDataManagerChange(dataParam);
    }


   public void setDead() {
        block11: {
            int i = this.getSlimeSize();
            try {
                try {
                    if (this.world.isRemote || i <= 1) break block11;
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeRainEntity.rethrow(runtimeException);
                }
                if (!(this.getHealth() <= 0.0f)) break block11;
            }
            catch (RuntimeException runtimeException) {
                throw SlimeRainEntity.rethrow(runtimeException);
            }
            int i3 = 2 + this.rand.nextInt(3);
            for (int i4 = 0; i4 < i3; ++i4) {
                float f = ((float)(i4 % 2) - 0.5f) * (float)i / 4.0f;
                float f2 = ((float)(i4 / 2) - 0.5f) * (float)i / 4.0f;
                SlimeRainEntity slimeRainEntity = this.createInstance();
                try {
                    if (this.hasCustomName()) {
                        slimeRainEntity.setCustomNameTag(this.getCustomNameTag());
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeRainEntity.rethrow(runtimeException);
                }
                try {
                    if (this.isNoDespawnRequired()) {
                        slimeRainEntity.enablePersistence();
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw SlimeRainEntity.rethrow(runtimeException);
                }
                slimeRainEntity.setSlimeSize(i / 2, true);
                slimeRainEntity.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5, this.posZ + (double)f2, this.rand.nextFloat() * 360.0f, 0.0f);
                this.world.spawnEntity((Entity)slimeRainEntity);
            }
        }
        super.setDead();
    }

   public float getEyeHeight() {
      return 0.625F * this.height;
   }

   protected SoundEvent getHurtSound(DamageSource damage) {
      try {
         if (this.isSmallSlime()) {
            return SoundEvents.ENTITY_SMALL_SLIME_HURT;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return SoundEvents.ENTITY_SLIME_HURT;
   }

   protected SoundEvent getDeathSound() {
      try {
         if (this.isSmallSlime()) {
            return SoundEvents.ENTITY_SMALL_SLIME_DEATH;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return SoundEvents.ENTITY_SLIME_DEATH;
   }

   protected SoundEvent getJumpSound() {
      try {
         if (this.isSmallSlime()) {
            return SoundEvents.ENTITY_SMALL_SLIME_SQUISH;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return SoundEvents.ENTITY_SLIME_SQUISH;
   }

   protected Item getDropItem() {
      try {
         if (this.getSlimeSize() == 1) {
            return Items.SLIME_BALL;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return null;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      try {
         if (this.getSlimeSize() == 1) {
            return LootTableList.ENTITIES_SLIME;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return LootTableList.EMPTY;
   }

   protected float getSoundVolume() {
      return 0.4F * this.getSlimeSize();
   }

   public int getVerticalFaceSpeed() {
      return 0;
   }

   protected boolean hasSlimeSize() {
      try {
         if (this.getSlimeSize() > 0) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   protected void jump() {
      this.motionY = 0.42F;
      this.isAirBorne = true;
   }

   @Nullable
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficultyInstance, @Nullable IEntityLivingData iEntityLivingData) {
      this.setSlimeSize(1, true);
      return super.onInitialSpawn(difficultyInstance, iEntityLivingData);
   }

   protected SoundEvent getSquishSound() {
      try {
         if (this.isSmallSlime()) {
            return SoundEvents.ENTITY_SMALL_SLIME_JUMP;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return SoundEvents.ENTITY_SLIME_JUMP;
   }

   protected boolean isParticleSuppressed() {
      return false;
   }

   private static Exception rethrow(Exception error) {
      return error;
   }

   static class RandomHopAi extends EntityAIBase {
      private final SlimeRainEntity RainDurationTicks;
      private float SquishTarget;
      private int TimerKey;

      public RandomHopAi(SlimeRainEntity slimeRainEntity) {
         this.RainDurationTicks = slimeRainEntity;
         this.setMutexBits(2);
      }


      public boolean shouldExecute() {
            return true;
        }

      public void updateTask() {
         try {
            if (--this.TimerKey <= 0) {
               this.TimerKey = 40 + this.RainDurationTicks.getRNG().nextInt(60);
               this.SquishTarget = this.RainDurationTicks.getRNG().nextInt(360);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         ((SlimeRainEntity.SlimeMoveHelper)this.RainDurationTicks.getMoveHelper()).a(this.SquishTarget, false);
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }

   static class SlimeMoveHelper extends EntityMoveHelper {
      private float RainDurationTicks;
      private int TimerKey;
      private final SlimeRainEntity SizeKey;
      private boolean SquishTarget;

      public SlimeMoveHelper(SlimeRainEntity slimeRainEntity) {
         super(slimeRainEntity);
         this.SizeKey = slimeRainEntity;
         this.RainDurationTicks = 180.0F * slimeRainEntity.rotationYaw / (float) Math.PI;
      }

      public void a(float f, boolean flag) {
         this.RainDurationTicks = f;
         this.SquishTarget = flag;
      }

      public void a(double d) {
         this.speed = d;
         this.action = Action.MOVE_TO;
      }


      public void onUpdateMoveHelper() {
            block11: {
                block12: {
                    block13: {
                        block10: {
                            try {
                                this.entity.rotationYawHead = this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.RainDurationTicks, 90.0f);
                                this.entity.renderYawOffset = this.entity.rotationYaw;
                                if (this.action == EntityMoveHelper.Action.MOVE_TO) break block10;
                                this.entity.setMoveForward(0.0f);
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimeRainEntity.SlimeMoveHelper.rethrow(runtimeException);
                            }
                        }
                        try {
                            try {
                                try {
                                    this.action = EntityMoveHelper.Action.WAIT;
                                    if (!this.entity.onGround) break block11;
                                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                                    if (this.TimerKey-- > 0) break block12;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw SlimeRainEntity.SlimeMoveHelper.rethrow(runtimeException);
                                }
                                this.TimerKey = this.SizeKey.a();
                                if (!this.SquishTarget) break block13;
                            }
                            catch (RuntimeException runtimeException) {
                                throw SlimeRainEntity.SlimeMoveHelper.rethrow(runtimeException);
                            }
                            this.TimerKey /= 3;
                        }
                        catch (RuntimeException runtimeException) {
                            throw SlimeRainEntity.SlimeMoveHelper.rethrow(runtimeException);
                        }
                    }
                    float f = ModConstants.Random.nextInt(360);
                    try {
                        ((RainDurationTicks)this.SizeKey.getMoveHelper()).a(f, false);
                        this.SizeKey.getJumpHelper().setJumping();
                        if (!this.SizeKey.i()) return;
                        this.SizeKey.playSound(this.SizeKey.c(), this.SizeKey.getSoundVolume(), ((this.SizeKey.getRNG().nextFloat() - this.SizeKey.getRNG().nextFloat()) * 0.2f + 1.0f) * 0.8f);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw SlimeRainEntity.SlimeMoveHelper.rethrow(runtimeException);
                    }
                }
                this.SizeKey.moveStrafing = 0.0f;
                this.SizeKey.moveForward = 0.0f;
                this.entity.setAIMoveSpeed(0.0f);
                return;
            }
            this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }

   static class MoveSlimeAi extends EntityAIBase {
      private final SlimeRainEntity SquishTarget;

      public MoveSlimeAi(SlimeRainEntity slimeRainEntity) {
         this.SquishTarget = slimeRainEntity;
         this.setMutexBits(5);
      }

      public boolean shouldExecute() {
         return true;
      }

      public void updateTask() {
         ((SlimeRainEntity.SlimeMoveHelper)this.SquishTarget.getMoveHelper()).a(1.0);
      }
   }

   static class RandomHopAi extends EntityAIBase {
      private final SlimeRainEntity SquishTarget;

      public RandomHopAi(SlimeRainEntity slimeRainEntity) {
         this.SquishTarget = slimeRainEntity;
         this.setMutexBits(5);
         ((PathNavigateGround)slimeRainEntity.getNavigator()).setCanSwim(true);
      }


      public boolean shouldExecute() {
            return true;
        }

      public void updateTask() {
         try {
            if (this.SquishTarget.getRNG().nextFloat() < 0.8F) {
               this.SquishTarget.getJumpHelper().setJumping();
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         ((SlimeRainEntity.SlimeMoveHelper)this.SquishTarget.getMoveHelper()).a(1.2);
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
