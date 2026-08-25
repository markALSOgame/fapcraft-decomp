package com.trolmastercard.sexmod;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class GirlAttackHandler {
   @SubscribeEvent
   public void onGirlAttacked(LivingAttackEvent livingAttackEvent) {
      try {
         if (livingAttackEvent.getSource() == DamageSource.OUT_OF_WORLD) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!(livingAttackEvent.getEntity() instanceof GirlEntity)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      GirlEntity girl = (GirlEntity)livingAttackEvent.getEntity();

      try {
         if (girl instanceof PlayerGirlEntity) {
            livingAttackEvent.setCanceled(true);
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      LivingAttackEvent livingAttackEvent2;
      boolean flag;
      label32: {
         try {
            livingAttackEvent2 = livingAttackEvent;
            if (girl.getSexPlayerUuid() != null) {
               flag = true;
               break label32;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         flag = false;
      }

      livingAttackEvent2.setCanceled(flag);
   }

   @SubscribeEvent

   public void onPlayerAttackNearGirl(LivingAttackEvent livingAttackEvent) {
        block13: {
            DamageSource damageSource = livingAttackEvent.getSource();
            try {
                try {
                    if (damageSource != DamageSource.OUT_OF_WORLD && !(damageSource instanceof GalathMeleeDamageSource)) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlAttackHandler.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GirlAttackHandler.rethrow(runtimeException);
            }
        }
        try {
            if (!(livingAttackEvent.getEntity() instanceof EntityPlayer)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlAttackHandler.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = (EntityPlayer)livingAttackEvent.getEntity();
        GirlEntity girl = GirlEntity.getGirlByUuid(entityPlayer.getPersistentID());
        try {
            if (girl == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlAttackHandler.rethrow(runtimeException);
        }
        try {
            if (girl.getDistance((Entity)entityPlayer) < 1.0f) {
                livingAttackEvent.setCanceled(true);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlAttackHandler.rethrow(runtimeException);
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
