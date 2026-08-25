package com.trolmastercard.sexmod;

import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ManglelieFleeAi extends EntityAIAvoidEntity<EntityPlayer> {
   final ManglelieNpc Manglelie;
   final float SearchRadius;

   public ManglelieFleeAi(ManglelieNpc manglelie, float f, double d, double d2) {
      super(manglelie, EntityPlayer.class, f, d, d2);
      this.Manglelie = manglelie;
      this.SearchRadius = f;
   }

   boolean isFleeBlocked() {
      try {
         if (this.Manglelie.getMommyUuid() != null) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      BlockPos pos = this.Manglelie.getPosition();
      BlockPos pos2 = new BlockPos(this.SearchRadius, this.SearchRadius, this.SearchRadius);

      for (GalathNpc galath : this.Manglelie.world.getEntitiesWithinAABB(GalathNpc.class, new AxisAlignedBB(pos.add(pos2), pos.subtract(pos2)))) {
         try {
            if (galath.world.isRemote) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         try {
            if (galath.isDead) {
               continue;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         try {
            if (galath.maybeMountedByMangFn()) {
               return true;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }
      }

      return false;
   }

   public boolean shouldExecute() {
      try {
         if (this.isFleeBlocked()) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return super.shouldExecute();
   }

   public boolean shouldContinueExecuting() {
      try {
         if (this.isFleeBlocked()) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return super.shouldContinueExecuting();
   }

   public void startExecuting() {
      this.Manglelie.getDataManager().set(ManglelieNpc.FleeingKey, true);
      super.startExecuting();
   }

   public void resetTask() {
      this.Manglelie.getDataManager().set(ManglelieNpc.FleeingKey, false);
      super.resetTask();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
