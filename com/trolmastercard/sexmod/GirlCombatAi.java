package com.trolmastercard.sexmod;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GirlCombatAi extends GirlAiBase {
   InventoryGirlEntity Girl;
   EntityLivingBase Target;
   Entity RideMount;
   double DistanceToPlayer = Float.MAX_VALUE;
   Vec3d PlayerPos = Vec3d.ZERO;
   int RandomMoveTimer = 0;
   int ForcedAttackTicks = 0;
   int AttackCooldownTicks = 0;
   int BowChargeTicks = 0;
   int FollowTimer = 0;

   public GirlCombatAi(InventoryGirlEntity inventoryGirl) {
      super(inventoryGirl);
      this.Girl = inventoryGirl;
   }

   @Override
   public void updateTask() {
      try {
         super.updateTask();
         this.DistanceToPlayer = this.Girl.getDistance(this.Player);
         this.PlayerPos = this.Player.getPositionVector();
         if (this.Girl.getCurrentAction() == GirlAnimationState.BOW) {
            this.Girl.setCurrentAction(GirlAnimationState.NULL);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }


   boolean isValidTarget(EntityLivingBase livingBase) {
        boolean flag;
        block19: {
            block18: {
                Vec3d vec3d = this.Girl.getPositionVector();
                try {
                    try {
                        try {
                            try {
                                try {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    if (livingBase instanceof GirlEntity || this.ForcedAttackTicks > 0) break block18;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw GirlCombatAi.rethrow(runtimeException);
                                                }
                                                if (livingBase == null) break block18;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw GirlCombatAi.rethrow(runtimeException);
                                            }
                                            if (livingBase.world == null) break block18;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GirlCombatAi.rethrow(runtimeException);
                                        }
                                        if (this.Girl.equals(livingBase)) break block18;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GirlCombatAi.rethrow(runtimeException);
                                    }
                                    if (!livingBase.isEntityAlive()) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GirlCombatAi.rethrow(runtimeException);
                                }
                                if (!(vec3d.distanceTo(this.Player.getPositionVector()) < 15.0)) break block18;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                            if (!(vec3d.distanceTo(livingBase.getPositionVector()) < 20.0)) break block18;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.rethrow(runtimeException);
                        }
                        if (livingBase.equals((Object)this.Player)) break block18;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCombatAi.rethrow(runtimeException);
                    }
                    flag = true;
                    break block19;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlCombatAi.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

   @Override
   protected void executeState(GirlAiBase.State state) {
      switch (state) {
         case ATTACK: {
            this.Girl.getLookHelper().setLookPositionWithEntity((Entity)this.Target, 30.0f, 30.0f);
            double d = this.Girl.getDistance((Entity)this.Target);
            this.Navigation.clearPath();
            if (d < 1.9 && --this.AttackCooldownTicks <= 0) {
               this.meleeAttack();
               break;
            }
            if (this.Girl.Inventory.getStackInSlot(1).getItem() instanceof ItemBow && this.Girl.getEntitySenses().canSee((Entity)this.Target) && ++this.BowChargeTicks > 0 && d > 6.0) {
               this.DataManager.set(InventoryGirlEntity.ModeKey, (Object)2);
               this.Girl.setCurrentAction(GirlAnimationState.BOW);
               if (++this.BowChargeTicks >= 32) {
                  this.BowChargeTicks = -20;
                  this.shootArrow();
                  this.Girl.setCurrentAction(GirlAnimationState.NULL);
               }
               this.DistanceToPlayer = this.Girl.getDistance((Entity)this.Player);
               this.PlayerPos = this.Player.getPositionVector();
               return;
            }
            if (d < 2.0) {
               this.DataManager.set(InventoryGirlEntity.ModeKey, (Object)1);
               this.Navigation.tryMoveToEntityLiving((Entity)this.Target, 0.5);
               this.Girl.a(GirlEntity.WalkState.WALK);
               break;
            }
            this.DataManager.set(InventoryGirlEntity.ModeKey, (Object)1);
            this.Navigation.tryMoveToEntityLiving((Entity)this.Target, 0.7);
            this.Girl.a(GirlEntity.WalkState.RUN);
            break;
         }
         case FOLLOW: {
            this.DataManager.set(InventoryGirlEntity.ModeKey, (Object)0);
            double d = this.Girl.getDistance((Entity)this.Player);
            if ((double)this.Navigation.getPathSearchRange() > d) {
               this.Navigation.clearPath();
               if (!this.Girl.IsDowned) {
                  this.Navigation.tryMoveToEntityLiving((Entity)this.Player, 0.5);
                  this.airDash();
               }
            } else {
               this.moveToRandomNearbyPos();
            }
            this.RandomMoveTimer = 300;
            this.getMoveSpeed();
            break;
         }
         case IDLE: {
            this.DataManager.set(InventoryGirlEntity.ModeKey, (Object)0);
            if (!this.Girl.IsDowned) {
               if (++this.RandomMoveTimer > 200 + ModConstants.Random.nextInt(100)) {
                  this.RandomMoveTimer = 0;
                  Vec3d vec3d = this.Player.getPositionVector();
                  Vec3d vec3d2 = new Vec3d(vec3d.x + 1.0 + (double)(ModConstants.Random.nextFloat() * 3.0f), vec3d.y, vec3d.z + 1.0 + (double)(ModConstants.Random.nextFloat() * 3.0f));
                  this.Navigation.clearPath();
                  this.Navigation.tryMoveToXYZ(vec3d2.x, vec3d2.y, vec3d2.z, 0.5);
               }
               this.getMoveSpeed();
               break;
            }
            if (!(this.Girl.getDistance((Entity)this.Player) > 10.0f)) break;
            this.moveToRandomNearbyPos();
            break;
         }
         case RIDE: {
            if (this.Girl.isRiding()) {
               this.Girl.setCurrentAction(GirlAnimationState.SIT);
               break;
            }
            this.Girl.setNoGravity(true);
            this.Girl.noClip = true;
            Vec3d vec3d3 = this.Player.getPositionVector().subtract(this.RideMount.getLookVec().x * 0.5, 0.0, this.RideMount.getLookVec().z * 0.5);
            this.Girl.setPositionAndRotation(vec3d3.x, vec3d3.y, vec3d3.z, 0.0f, 0.0f);
            this.Girl.motionX = 0.0;
            this.Girl.motionY = 0.0;
            this.Girl.motionZ = 0.0;
            this.Girl.setCurrentAction(GirlAnimationState.RIDE);
            break;
         }
         case DOWNED: {
            this.Navigation.clearPath();
         }
      }
   }

   @Override

   protected GirlAiBase.State getState() {
        boolean flag;
        block62: {
            block60: {
                block61: {
                    boolean flag2;
                    DamageSource damageSource;
                    Entity entity;
                    block59: {
                        block57: {
                            try {
                                try {
                                    --this.ForcedAttackTicks;
                                    if (this.Girl.IsDowned) return GirlAiBase.State.DOWNED;
                                    if (this.Girl.getSexPlayerUuid() != null) {
                                        return GirlAiBase.State.DOWNED;
                                    }
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GirlCombatAi.rethrow(runtimeException);
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                            if (this.Player.isRiding()) {
                                entity = this.Player.getRidingEntity();
                                try {
                                    block56: {
                                        try {
                                            try {
                                                try {
                                                    if (this.Girl.isRiding() || this.Girl.startRiding(entity)) break block56;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw GirlCombatAi.rethrow(runtimeException);
                                                }
                                                if (!(entity instanceof EntityHorse)) break block57;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw GirlCombatAi.rethrow(runtimeException);
                                            }
                                            if (!((EntityHorse)entity).isHorseSaddled()) break block57;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GirlCombatAi.rethrow(runtimeException);
                                        }
                                    }
                                    this.RideMount = entity;
                                    return GirlAiBase.State.RIDE;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GirlCombatAi.rethrow(runtimeException);
                                }
                            }
                            try {
                                block58: {
                                    try {
                                        try {
                                            try {
                                                if (!this.Player.isRiding() && this.Girl.isRiding()) break block58;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw GirlCombatAi.rethrow(runtimeException);
                                            }
                                            if (this.State != GirlAiBase.State.RIDE) break block57;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GirlCombatAi.rethrow(runtimeException);
                                        }
                                        if (this.Player.isRiding()) break block57;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GirlCombatAi.rethrow(runtimeException);
                                    }
                                }
                                this.Girl.setCurrentAction(GirlAnimationState.NULL);
                                this.Girl.dismountRidingEntity();
                                this.Girl.noClip = false;
                                this.Girl.setNoGravity(false);
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                        }
                        try {
                            if (this.isValidTarget(this.Target)) {
                                return GirlAiBase.State.ATTACK;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.rethrow(runtimeException);
                        }
                        damageSource = this.Girl.getLastDamageSource();
                        if (damageSource != null) {
                            entity = (EntityLivingBase)damageSource.getTrueSource();
                            try {
                                if (this.isValidTarget((EntityLivingBase)entity)) {
                                    this.Target = entity;
                                    return GirlAiBase.State.ATTACK;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                        }
                        entity = this.Player.getLastAttackedEntity();
                        try {
                            try {
                                if (this.Player.ticksExisted - this.Player.getLastAttackedEntityTime() >= 140 || !this.isValidTarget((EntityLivingBase)entity)) break block59;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                            this.Target = entity;
                            return GirlAiBase.State.ATTACK;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.rethrow(runtimeException);
                        }
                    }
                    if (this.State != GirlAiBase.State.FOLLOW) {
                        damageSource = this.Player.getLastDamageSource();
                        if (damageSource != null) {
                            entity = (EntityLivingBase)damageSource.getTrueSource();
                            try {
                                if (this.isValidTarget((EntityLivingBase)entity)) {
                                    this.Target = entity;
                                    return GirlAiBase.State.ATTACK;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                        }
                        Vec3d vec3d = this.Girl.getPositionVector();
                        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(vec3d.x - 5.0, vec3d.y - 2.0, vec3d.z - 5.0, vec3d.x + 5.0, vec3d.y + 2.0, vec3d.z + 5.0);
                        List list = this.Girl.world.getEntitiesWithinAABB(EntityMob.class, axisAlignedBB);
                        list.sort((entityMob, entityMob2) -> {
                            int i;
                            double d = entityMob.getDistance((Entity)this.Girl);
                            double d2 = entityMob2.getDistance((Entity)this.Girl);
                            try {
                                if (d == d2) {
                                    return 0;
                                }
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                            try {
                                i = d < d2 ? -1 : 1;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                            return i;
                        });
                        for (EntityMob entityMob3 : list) {
                            try {
                                try {
                                    if (!this.isValidTarget((EntityLivingBase)entityMob3) || entityMob3 instanceof EntityCreeper) continue;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GirlCombatAi.rethrow(runtimeException);
                                }
                                this.Target = entityMob3;
                                return GirlAiBase.State.ATTACK;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                        }
                    }
                    float f = this.Girl.getDistance((Entity)this.Player);
                    try {
                        flag2 = f > 5.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCombatAi.rethrow(runtimeException);
                    }
                    flag = flag2;
                    try {
                        try {
                            if (flag || this.State != GirlAiBase.State.FOLLOW) break block60;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.rethrow(runtimeException);
                        }
                        if (++this.FollowTimer <= 60) break block61;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCombatAi.rethrow(runtimeException);
                    }
                    flag = false;
                    this.FollowTimer = 0;
                    break block60;
                }
                flag = true;
            }
            try {
                try {
                    if (!flag || this.State != GirlAiBase.State.ATTACK) break block62;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlCombatAi.rethrow(runtimeException);
                }
                this.ForcedAttackTicks = 60;
            }
            catch (RuntimeException runtimeException) {
                throw GirlCombatAi.rethrow(runtimeException);
            }
        }
        try {
            if (!flag) return GirlAiBase.State.IDLE;
            return GirlAiBase.State.FOLLOW;
        }
        catch (RuntimeException runtimeException) {
            throw GirlCombatAi.rethrow(runtimeException);
        }
    }

   public void shootArrow() {
      EntityArrow entityArrow = this.b();
      double d = this.Target.posX - this.Girl.posX;
      double d2 = this.Target.getEntityBoundingBox().minY + this.Target.height / 3.0F - entityArrow.posY;
      double d3 = this.Target.posZ - this.Girl.posZ;
      double d4 = MathHelper.sqrt(d * d + d3 * d3);
      entityArrow.shoot(d, d2 + d4 * 0.2F, d3, 1.6F, 2.0F);
      this.Girl.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.Girl.getRNG().nextFloat() * 0.4F + 0.8F));
      this.Girl.world.spawnEntity(entityArrow);
      entityArrow.setDamage(4.5);
   }

   protected EntityArrow b() {
      EntityTippedArrow entityTippedArrow = new EntityTippedArrow(this.Girl.world, this.Girl);
      ItemStack stack = this.Girl.Inventory.getStackInSlot(1);
      double d = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
      int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
      int i2 = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack);

      try {
         if (d != 0.0) {
            entityTippedArrow.setDamage(entityTippedArrow.getDamage() + d * 0.5 + 0.5);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (i != 0) {
            entityTippedArrow.setKnockbackStrength(i);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (i2 != 0) {
            entityTippedArrow.setFire(100);
         }

         return entityTippedArrow;
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }


   void meleeAttack() {
        this.Girl.setCurrentAction(GirlAnimationState.ATTACK);
        this.DataManager.set(InventoryGirlEntity.ModeKey, (Object)1);
        ItemStack itemStack = this.Girl.Inventory.getStackInSlot(0);
        Multimap multimap = itemStack.getAttributeModifiers(EntityEquipmentSlot.MAINHAND);
        float f = 0.0f;
        float f2 = 0.0f;
        for (AttributeModifier attributeModifier : multimap.get((Object)SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
            f = (float)attributeModifier.getAmount();
        }
        for (AttributeModifier attributeModifier : multimap.get((Object)SharedMonsterAttributes.ATTACK_SPEED.getName())) {
            f2 = (float)attributeModifier.getAmount();
        }
        f2 = Math.max(f2, 0.5f);
        float f3 = EnchantmentHelper.getModifierForCreature((ItemStack)itemStack, (EnumCreatureAttribute)this.Target.getCreatureAttribute());
        int i = EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.KNOCKBACK, (ItemStack)itemStack);
        int i2 = EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.FIRE_ASPECT, (ItemStack)itemStack);
        int i3 = EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.SWEEPING, (ItemStack)itemStack);
        this.Target.knockBack((Entity)this.Girl, (float)i * 0.5f, (double)MathHelper.sin((float)(this.Girl.rotationYaw * ((float)Math.PI / 180))), (double)(-MathHelper.cos((float)(this.Girl.rotationYaw * ((float)Math.PI / 180)))));
        this.Target.setFire(i2 * 4);
        if (i3 != 0) {
            float f4 = 0.5f;
            if (i3 == 2) {
                f4 = 0.67f;
            } else if (i3 == 3) {
                f4 = 0.75f;
            }
            for (EntityLivingBase entityLivingBase : this.Girl.world.getEntitiesWithinAABB(EntityLivingBase.class, this.Target.getEntityBoundingBox().grow(1.0, 0.25, 1.0))) {
                try {
                    try {
                        try {
                            try {
                                try {
                                    if (entityLivingBase == this.Girl || entityLivingBase == this.Player) continue;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GirlCombatAi.rethrow(runtimeException);
                                }
                                if (entityLivingBase == this.Target) continue;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GirlCombatAi.rethrow(runtimeException);
                            }
                            if (this.Girl.isOnSameTeam((Entity)entityLivingBase)) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.rethrow(runtimeException);
                        }
                        if (!(this.Girl.getDistanceSq((Entity)entityLivingBase) < 9.0)) continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCombatAi.rethrow(runtimeException);
                    }
                    entityLivingBase.knockBack((Entity)this.Girl, 0.4f, (double)MathHelper.sin((float)(this.Girl.rotationYaw * ((float)Math.PI / 180))), (double)(-MathHelper.cos((float)(this.Girl.rotationYaw * ((float)Math.PI / 180)))));
                    entityLivingBase.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.Girl), (f + f3) * f4);
                }
                catch (RuntimeException runtimeException) {
                    throw GirlCombatAi.rethrow(runtimeException);
                }
            }
        }
        this.Target.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.Girl), f + f3);
        this.AttackCooldownTicks = Math.round(Math.abs(f2) / 3.373494f * 20.0f);
    }

   @Override
   protected double getMoveSpeed() {
      double d = super.getMoveSpeed();
      if (this.Girl.IsDowned) {
         d = 0.0;
      }

      this.Navigation.setSpeed(d);
      this.Girl.a(this.Girl.getWalkState());
      return d;
   }

   @Override
   public void resetTask() {
      super.resetTask();
      this.Girl.getDataManager().set(InventoryGirlEntity.ModeKey, 0);
   }


   void airDash() {
      if (this.Girl.onGround || this.Girl.isInWater() || this.Girl.motionX + this.Girl.motionZ != 0.0 || this.Girl.motionY <= 0.0) {
         return;
      }
      Vec3d vec3d = VectorMath.rotateYaw(new Vec3d(0.0, 0.0, (double)0.1f), this.Girl.rotationYaw);
      this.Girl.motionX = vec3d.x;
      this.Girl.motionZ = vec3d.z;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class EventHandler {
      @SubscribeEvent

      public void a(LivingHurtEvent livingHurtEvent) {
            block8: {
                if (livingHurtEvent.getEntityLiving() instanceof InventoryGirlEntity) {
                    InventoryGirlEntity inventoryGirl;
                    block7: {
                        inventoryGirl = (InventoryGirlEntity)livingHurtEvent.getEntityLiving();
                        try {
                            if (!inventoryGirl.IsDowned) break block7;
                            livingHurtEvent.setCanceled(true);
                            break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.EventHandler.rethrow(runtimeException);
                        }
                    }
                    try {
                        try {
                            if (!(inventoryGirl.getHealth() - livingHurtEvent.getAmount() < 0.0f) || ((String)inventoryGirl.getDataManager().get(InventoryGirlEntity.MasterUuidKey)).equals("")) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.EventHandler.rethrow(runtimeException);
                        }
                        inventoryGirl.IsDowned = true;
                        inventoryGirl.setCurrentAction(GirlAnimationState.DOWNED);
                        livingHurtEvent.setAmount(inventoryGirl.getHealth() - 1.0f);
                        inventoryGirl.getNavigator().clearPath();
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCombatAi.EventHandler.rethrow(runtimeException);
                    }
                }
            }
        }

      @SubscribeEvent

      public void a(LivingHealEvent livingHealEvent) {
            block5: {
                if (livingHealEvent.getEntityLiving() instanceof InventoryGirlEntity) {
                    InventoryGirlEntity inventoryGirl = (InventoryGirlEntity)livingHealEvent.getEntityLiving();
                    try {
                        try {
                            if (!inventoryGirl.IsDowned || !(inventoryGirl.getHealth() + livingHealEvent.getAmount() >= inventoryGirl.getMaxHealth())) break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlCombatAi.EventHandler.rethrow(runtimeException);
                        }
                        inventoryGirl.IsDowned = false;
                        inventoryGirl.setCurrentAction(GirlAnimationState.NULL);
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlCombatAi.EventHandler.rethrow(runtimeException);
                    }
                }
            }
        }

      @SubscribeEvent
      public void a(LivingDeathEvent livingDeathEvent) {
         if (livingDeathEvent.getEntityLiving() instanceof InventoryGirlEntity) {
            InventoryGirlEntity inventoryGirl = (InventoryGirlEntity)livingDeathEvent.getEntityLiving();

            try {
               if (inventoryGirl.world.isRemote) {
                  return;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            for (int i = 0; i < 6; i++) {
               Item item = inventoryGirl.Inventory.getStackInSlot(i).getItem();

               try {
                  if (item != Items.AIR) {
                     inventoryGirl.dropItem(item, 1);
                  }
               } catch (RuntimeException error2) {
                  throw rethrow(error2);
               }
            }
         }
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
