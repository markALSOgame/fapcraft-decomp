package com.trolmastercard.sexmod;
import net.minecraft.entity.Entity;

public class GirlFollowAi extends GirlAiBase {
   int FollowTimer = 0;
   int StandbyTimer = 0;

   public GirlFollowAi(GirlEntity girl) {
      super(girl);
   }

   @Override
   public void resetTask() {
      super.resetTask();
      this.Girl.jumpMovementFactor = 0.02F;
   }

   @Override

   protected GirlAiBase.AiState getState() {
        boolean flag;
        block11: {
            block12: {
                boolean flag2;
                float f = this.Girl.getDistance((Entity)this.Player);
                try {
                    flag2 = f > 5.0f;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlFollowAi.rethrow(runtimeException);
                }
                flag = flag2;
                try {
                    try {
                        try {
                            if (this.Girl.getSexPlayerUuid() != null || flag) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlFollowAi.rethrow(runtimeException);
                        }
                        if (this.State != GirlAiBase.AiState.FOLLOW) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlFollowAi.rethrow(runtimeException);
                    }
                    if (++this.FollowTimer <= 60) break block12;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlFollowAi.rethrow(runtimeException);
                }
                flag = false;
                this.FollowTimer = 0;
                break block11;
            }
            flag = true;
        }
        try {
            if (flag) {
                return GirlAiBase.AiState.FOLLOW;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlFollowAi.rethrow(runtimeException);
        }
        return GirlAiBase.AiState.IDLE;
    }

   @Override
   protected void executeState(GirlAiBase.AiState state) {
      switch (state) {
         case FOLLOW:
            double d = this.Girl.getDistance(this.Player);

            label20: {
               try {
                  if (this.Navigation.getPathSearchRange() > d) {
                     this.Navigation.clearPath();
                     this.Navigation.tryMoveToEntityLiving(this.Player, 0.5);
                     break label20;
                  }
               } catch (RuntimeException error) {
                  throw rethrow(error);
               }

               this.moveToRandomNearbyPos();
            }

            this.StandbyTimer = 300;
            this.getMoveSpeed();
            break;
         case IDLE:
            this.getMoveSpeed();
      }
   }

   @Override
   protected double getMoveSpeed() {
      float f = this.Girl.getDistance(this.Player);
      float f2 = 0.02F;
      double d = Math.min(0.7, Math.floor(f / 3.0F) * 0.05);
      f2 = (float)(f2 + d);
      this.Girl.jumpMovementFactor = f2;
      return f2;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
