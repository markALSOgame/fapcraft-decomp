package com.trolmastercard.sexmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;

public class PotionHandler extends Potion {
   public static final Potion b = new PotionHandler("horny potion", false, 16736968, 0, 0);
   public static final PotionType a = (PotionType)new PotionType(
         "horny_potion", new PotionEffect[]{new PotionEffect(b, 3600), new PotionEffect(MobEffects.NAUSEA, 200, 1)}
      )
      .setRegistryName("horny_potion");

   public PotionHandler() {
      super(false, 0);
   }

   public PotionHandler(String string, boolean flag, int i, int i2, int i3) {
      super(flag, i);
      this.setPotionName(string);
      this.setIconIndex(i2, i3);
      this.setRegistryName(new ResourceLocation("sexmod:" + string));
   }

   public static void registerAll() {
      ForgeRegistries.POTIONS.register(b);
      ForgeRegistries.POTION_TYPES.register(a);
      PotionHelper.addMix(PotionTypes.MUNDANE, Item.getItemFromBlock(Blocks.RED_FLOWER), a);
   }

   @SubscribeEvent
   public void a(PlayerTickEvent playerTickEvent) {
      EntityPlayer player2 = playerTickEvent.player;
      PotionEffect effect = player2.getActivePotionEffect(b);

      try {
         if (player2.world.isRemote) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (effect == null) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (effect.getDuration() > 3500) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      player2.removePotionEffect(b);
      NetworkHandler.channel.sendTo(new PacketGirlSpecific(player2), (EntityPlayerMP)player2);
   }

   @SubscribeEvent

   public void a(LivingUpdateEvent livingUpdateEvent) {
        block11: {
            EntityVillager entityVillager;
            block12: {
                if (livingUpdateEvent.getEntity() instanceof EntityVillager) {
                    entityVillager = (EntityVillager)livingUpdateEvent.getEntity();
                    try {
                        if (entityVillager.isPotionActive(b)) {
                            entityVillager.tasks.addTask(2, (EntityAIBase)new VillagerBreedAi(entityVillager));
                            entityVillager.removePotionEffect(b);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PotionHandler.rethrow(runtimeException);
                    }
                }
                try {
                    if (!(livingUpdateEvent.getEntity() instanceof EntityAnimal)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PotionHandler.rethrow(runtimeException);
                }
                entityVillager = (EntityAnimal)livingUpdateEvent.getEntity();
                try {
                    try {
                        if (!entityVillager.isPotionActive(b)) break block11;
                        if (entityVillager.getGrowingAge() < 0) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PotionHandler.rethrow(runtimeException);
                    }
                    entityVillager.setGrowingAge(0);
                    entityVillager.resetInLove();
                    entityVillager.setInLove(entityVillager.world.getClosestPlayerToEntity((Entity)entityVillager, 30.0));
                }
                catch (RuntimeException runtimeException) {
                    throw PotionHandler.rethrow(runtimeException);
                }
            }
            entityVillager.removePotionEffect(b);
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
