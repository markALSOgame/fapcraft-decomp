package com.trolmastercard.sexmod;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GirlDeathHandler {
   @SubscribeEvent(priority = EventPriority.LOW)
   public void onGirlDeath(LivingDeathEvent livingDeathEvent) {
      if (livingDeathEvent.getEntity() instanceof GirlEntity) {
         GirlEntity girl = (GirlEntity)livingDeathEvent.getEntity();
         GirlEntity.getAllGirls().remove(girl);
      }
   }
}
