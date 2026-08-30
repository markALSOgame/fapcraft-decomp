package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class LineOfSight {
   public static boolean canSeeEntity(Entity entity) {
      try {
         if (entity instanceof EntityCreeper) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (entity instanceof EntityPigZombie) {
            return false;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (entity instanceof EntityGuardian) {
            return false;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         return !(entity instanceof EntityEnderman);
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }
   }

   public static boolean hasLineOfSight(World world, Vec3d vec3d, Entity entity) {
      RayTraceResult hit = world.rayTraceBlocks(vec3d, entity.getPositionVector().add(0.0, entity.getEyeHeight(), 0.0), true, true, false);

      try {
         if (hit == null) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (hit.typeOfHit != Type.BLOCK) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return false;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
