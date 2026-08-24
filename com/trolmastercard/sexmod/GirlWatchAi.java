package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest2;

public class GirlWatchAi extends EntityAIWatchClosest2 {
   public boolean Active = true;

   public GirlWatchAi(EntityLiving living, Class<? extends Entity> cls, float f, float f2) {
      super(living, cls, f, f2);
   }

   public void updateTask() {
      try {
         if (this.Active) {
            super.updateTask();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
