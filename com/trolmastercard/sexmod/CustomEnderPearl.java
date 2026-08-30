package com.trolmastercard.sexmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class CustomEnderPearl extends EntityEnderPearl {
   public CustomEnderPearl(World world) {
      super(world);
   }

   public CustomEnderPearl(World world, EntityLivingBase livingBase) {
      super(world, livingBase);
   }


   protected void onImpact(RayTraceResult hit) {
        block17: {
            block18: {
                BlockPos blockPos;
                TileEntity tileEntity;
                EntityLivingBase entityLivingBase = this.getThrower();
                if (hit.typeOfHit == RayTraceResult.Type.BLOCK && (tileEntity = this.world.getTileEntity(blockPos = hit.getBlockPos())) instanceof TileEntityEndGateway) {
                    TileEntityEndGateway tileEntityEndGateway;
                    block15: {
                        block16: {
                            tileEntityEndGateway = (TileEntityEndGateway)tileEntity;
                            try {
                                try {
                                    if (entityLivingBase == null) break block15;
                                    if (!(entityLivingBase instanceof EntityPlayerMP)) break block16;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw CustomEnderPearl.rethrow(runtimeException);
                                }
                                CriteriaTriggers.ENTER_BLOCK.trigger((EntityPlayerMP)entityLivingBase, this.world.getBlockState(blockPos));
                            }
                            catch (RuntimeException runtimeException) {
                                throw CustomEnderPearl.rethrow(runtimeException);
                            }
                        }
                        tileEntityEndGateway.teleportEntity((Entity)entityLivingBase);
                        this.setDead();
                        return;
                    }
                    tileEntityEndGateway.teleportEntity((Entity)this);
                    return;
                }
                try {
                    for (int i = 0; i < 32; ++i) {
                        this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0, this.posZ, this.rand.nextGaussian(), 0.0, this.rand.nextGaussian(), new int[0]);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw CustomEnderPearl.rethrow(runtimeException);
                }
                try {
                    if (this.world.isRemote) break block17;
                    if (entityLivingBase == null) break block18;
                }
                catch (RuntimeException runtimeException) {
                    throw CustomEnderPearl.rethrow(runtimeException);
                }
                GirlEntity girl = (GirlEntity)entityLivingBase;
                if (girl.HomePos.distanceTo(this.getPositionVector()) < 5.0) {
                    block19: {
                        EnderTeleportEvent enderTeleportEvent = new EnderTeleportEvent(entityLivingBase, this.posX, this.posY, this.posZ, 5.0f);
                        try {
                            try {
                                if (MinecraftForge.EVENT_BUS.post((Event)enderTeleportEvent)) break block18;
                                if (!entityLivingBase.isRiding()) break block19;
                            }
                            catch (RuntimeException runtimeException) {
                                throw CustomEnderPearl.rethrow(runtimeException);
                            }
                            entityLivingBase.dismountRidingEntity();
                        }
                        catch (RuntimeException runtimeException) {
                            throw CustomEnderPearl.rethrow(runtimeException);
                        }
                    }
                    entityLivingBase.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                    entityLivingBase.fallDistance = 0.0f;
                }
            }
            this.setDead();
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class EventHandler {
      @SubscribeEvent
      public void onEnderTeleport(EnderTeleportEvent enderTeleportEvent) {
         if (enderTeleportEvent.getEntityLiving() instanceof GirlEntity) {
            GirlEntity girl = (GirlEntity)enderTeleportEvent.getEntityLiving();
            girl.EnderPearl = null;
            girl.setCurrentAction(GirlAnimationState.NULL);
            girl.getDataManager().set(GirlEntity.BusyKey, false);
            girl.resetMasterAndWalkSpeed();
         }
      }
   }
}
