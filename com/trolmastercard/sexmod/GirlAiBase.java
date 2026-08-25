package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;

public abstract class GirlAiBase extends EntityAIBase {
   public GirlEntity Girl;
   public EntityPlayer Player;
   public PathNavigate Navigation;
   public EntityDataManager DataManager;
   public GirlAiBase.AiState State = GirlAiBase.AiState.IDLE;
   public static final double WalkSpeed = 0.5;
   public static final double RunSpeed = 0.7;
   public static final int FollowDistance = 60;

   public GirlAiBase(GirlEntity girl) {
      this.Girl = girl;
      this.Navigation = girl.getNavigator();
      this.DataManager = girl.getDataManager();
   }

   protected void moveToRandomNearbyPos() {
      int i = 0;

      BlockPos pos;
      do {
         pos = this.Player.getPosition().add(ModConstants.Random.nextInt(10), 0, ModConstants.Random.nextInt(10));
      } while (++i < 20 && !this.Girl.attemptTeleport(pos.getX(), pos.getY(), pos.getZ()));

      try {
         if (i >= 20) {
            this.Girl.setPosition(this.Player.posX, this.Player.posY, this.Player.posZ);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.Girl.motionX = 0.0;
      this.Girl.motionY = 0.0;
      this.Girl.motionZ = 0.0;
   }

   protected double getMoveSpeed() {
      float f = this.Girl.getDistance(this.Player);
      double d;
      GirlEntity.WalkState walkState;
      if (this.Player.isSprinting()) {
         d = 0.7;
         walkState = GirlEntity.WalkState.RUN;
      } else {
         d = 0.5;
         walkState = GirlEntity.WalkState.WALK;
      }

      double d2 = Math.floor(f / 5.0F) * 0.2;
      d += d2;
      if (this.Girl.isInWater()) {
         d *= 60.0;
         walkState = GirlEntity.WalkState.WALK;
      }

      this.Navigation.setSpeed(d);
      this.Girl.a(walkState);
      return d;
   }

   public void resetTask() {
      this.Navigation.clearPath();
      this.State = GirlAiBase.AiState.IDLE;
      this.Girl.setCurrentAction(GirlAnimationState.NULL);
      this.DataManager.set(GirlEntity.MasterUuidKey, "");
      this.Navigation = null;
      this.DataManager = null;
      this.Player = null;
   }

   public boolean shouldExecute() {
      try {
         if (!((String)this.Girl.getDataManager().get(GirlEntity.MasterUuidKey)).equals("")) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }


   public boolean shouldContinueExecuting() {
        boolean flag;
        block5: {
            block4: {
                String string = (String)this.DataManager.get(GirlEntity.MasterUuidKey);
                try {
                    try {
                        if (string.equals("") || this.Girl.world.getPlayerEntityByUUID(UUID.fromString(string)) == null) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlAiBase.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlAiBase.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

   public void startExecuting() {
      this.Navigation = this.Girl.getNavigator();
      this.DataManager = this.Girl.getDataManager();
      this.Player = this.Girl.world.getPlayerEntityByUUID(UUID.fromString((String)this.DataManager.get(GirlEntity.MasterUuidKey)));
   }


   public void updateTask() {
        block4: {
            boolean flag;
            block6: {
                block5: {
                    try {
                        try {
                            this.State = this.getState();
                            if (this.Girl.WatchPlayerAI == null) break block4;
                            GirlWatchAi ai = this.Girl.WatchPlayerAI;
                            if (this.State != GirlAiBase.AiState.IDLE) break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlAiBase.rethrow(runtimeException);
                        }
                        flag = true;
                        break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlAiBase.rethrow(runtimeException);
                    }
                }
                flag = false;
            }
            ai.Active = flag;
        }
        this.executeState(this.State);
    }

   protected abstract GirlAiBase.AiState getState();

   protected abstract void executeState(GirlAiBase.AiState state);

   @SubscribeEvent
   public void onGirlDeath(LivingDeathEvent livingDeathEvent) {
      if (livingDeathEvent.getEntityLiving() instanceof GirlEntity) {
         GirlEntity girl = (GirlEntity)livingDeathEvent.getEntityLiving();

         try {
            if (!((String)girl.getDataManager().get(GirlEntity.MasterUuidKey)).equals("")) {
               livingDeathEvent.setCanceled(true);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

    public enum AiState {
      ATTACK,
      FOLLOW,
      IDLE,
      RIDE,
      DOWNED;
   }
}
