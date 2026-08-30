package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class VecMath {
   public static Vec3d getPlayerRelativeOffsetWithEyeHeight(Entity entity, EntityPlayer player, float f) {
      Vec3d vec3d = LerpMath.lerpVec3d(
         new Vec3d(entity.lastTickPosX, entity.lastTickPosY + player.getEyeHeight(), entity.lastTickPosZ),
         entity.getPositionVector().add(0.0, player.getEyeHeight(), 0.0),
         f
      );
      Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ), player.getPositionVector(), f);
      return vec3d.subtract(vec3d2);
   }

   public static Vec3d getPlayerRelativeOffset(Entity entity, EntityPlayer player, float f) {
      Vec3d vec3d = getPositionOffset(entity, f);

      try {
         if (player == null) {
            return vec3d;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Vec3d vec3d2 = getPositionOffset(player, f);
      return vec3d.subtract(vec3d2);
   }

   public static Vec3d getPositionOffset(Entity entity, float f) {
      try {
         if (!(entity instanceof GirlEntity)) {
            return b(entity, f);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GirlEntity girl = (GirlEntity)entity;

      try {
         if (!girl.Q()) {
            return b(entity, f);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return girl.getTargetPos();
   }

   static Vec3d b(Entity entity, float f) {
      return LerpMath.lerpVec3d(new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ), entity.getPositionVector(), f);
   }

   public static void reset() {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
