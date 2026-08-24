package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class VillagerBreedAi extends EntityAIBase {
   private final EntityVillager Villager;
   private EntityVillager Mate;
   private final World WorldObj;
   private int BreedTimer;

   public VillagerBreedAi(EntityVillager entityVillager) {
      this.Villager = entityVillager;
      this.WorldObj = entityVillager.world;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      try {
         if (this.BreedTimer != 0) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Entity entity = this.WorldObj.findNearestEntityWithinAABB(EntityVillager.class, this.Villager.getEntityBoundingBox().grow(8.0, 3.0, 8.0), this.Villager);

      try {
         if (entity == null) {
            return false;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      this.Mate = (EntityVillager)entity;
      return true;
   }

   public void startExecuting() {
      this.BreedTimer = 300;
      this.Villager.setMating(true);
   }

   public void resetTask() {
   }

   public boolean shouldContinueExecuting() {
      return true;
   }

   public void updateTask() {
      try {
         this.BreedTimer--;
         this.Villager.getLookHelper().setLookPositionWithEntity(this.Mate, 10.0F, 30.0F);
         if (this.Villager.getDistanceSq(this.Mate) > 2.25) {
            this.Villager.getNavigator().tryMoveToEntityLiving(this.Mate, 0.25);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (this.BreedTimer <= 0) {
            this.breed();
            this.Villager.tasks.removeTask(this);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (this.Villager.getRNG().nextInt(35) == 0) {
            this.WorldObj.setEntityState(this.Villager, (byte)12);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }


   private void breed() {
        BabyEntitySpawnEvent babyEntitySpawnEvent;
        EntityVillager entityVillager;
        block4: {
            entityVillager = this.Villager.createChild((EntityAgeable)this.Mate);
            this.Mate.setGrowingAge(6000);
            this.Villager.setGrowingAge(6000);
            this.Mate.setIsWillingToMate(false);
            this.Villager.setIsWillingToMate(false);
            babyEntitySpawnEvent = new BabyEntitySpawnEvent((EntityLiving)this.Villager, (EntityLiving)this.Mate, (EntityAgeable)entityVillager);
            try {
                try {
                    if (!MinecraftForge.EVENT_BUS.post((Event)babyEntitySpawnEvent) && babyEntitySpawnEvent.getChild() != null) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw VillagerBreedAi.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw VillagerBreedAi.rethrow(runtimeException);
            }
        }
        entityVillager = babyEntitySpawnEvent.getChild();
        entityVillager.setGrowingAge(-24000);
        entityVillager.setLocationAndAngles(this.Villager.posX, this.Villager.posY, this.Villager.posZ, 0.0f, 0.0f);
        this.WorldObj.spawnEntity((Entity)entityVillager);
        this.WorldObj.setEntityState((Entity)entityVillager, (byte)12);
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
