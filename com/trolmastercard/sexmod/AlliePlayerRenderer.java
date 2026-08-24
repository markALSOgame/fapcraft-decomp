package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec2f;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AlliePlayerRenderer extends GirlPlayerRenderer {
   static final float E = 8.0F;
   static final float K = 1.68F;
   static final float M = 5.0F;
   static Collection<AlliePlayerRenderer> Instances = new ArrayList<>();
   double PosX = 0.0;
   double PosZ = 0.0;
   double PrevPosX = 0.0;
   double PrevPosZ = 0.0;
   float PrevRotationOffsetX = 0.0F;
   float PrevRotationOffsetZ = 0.0F;
   float RotationOffsetX;
   float RotationOffsetZ;
   double PrevSwayAmount = 0.0;
   double SwayAmount = 0.0;

   public AlliePlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
      Instances.add(this);
   }

   @Override
   protected void applyScaleOffset() {
      GlStateManager.translate(0.0F, -1.1F, 0.0F);
      GlStateManager.scale(0.7F, 0.7F, 0.7F);
   }

   @Override
   protected void applyHeldItemTransform(boolean flag, ItemStack stack) {
      try {
         super.applyHeldItemTransform(flag, stack);
         switch (stack.getItem().getItemUseAction(stack)) {
            case BLOCK:
            case BOW:
               return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!flag) {
            GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      GlStateManager.translate(0.0, 0.05, 0.0);
   }

   @Override
   protected void applyHandOffset(boolean flag) {
      try {
         super.applyHandOffset(flag);
         if (flag) {
            GlStateManager.translate(0.15, 0.0, 0.0);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GlStateManager.translate(-0.05, 0.0, 0.0);
   }

   @Override

   protected void applyDualHandOffset(boolean flag, boolean flag2) {
        block9: {
            block8: {
                try {
                    try {
                        super.applyDualHandOffset(flag, flag2);
                        if (!flag || flag2) break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw AlliePlayerRenderer.rethrow(runtimeException);
                    }
                    GlStateManager.translate((double)-0.025, (double)-0.1, (double)-0.1);
                    GlStateManager.rotate((float)10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (flag || flag2) break block9;
                }
                catch (RuntimeException runtimeException) {
                    throw AlliePlayerRenderer.rethrow(runtimeException);
                }
                GlStateManager.translate((double)-0.05, (double)-0.125, (double)0.125);
                GlStateManager.rotate((float)50.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                return;
            }
            catch (RuntimeException runtimeException) {
                throw AlliePlayerRenderer.rethrow(runtimeException);
            }
        }
    }

   @Override
   protected void applyBoneTransform(String string, GeoBone bone) {
      try {
         if ((Boolean)this.CurrentGirl.getDataManager().get(GirlEntity.BusyKey)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if ("tail".equals(string)) {
            this.applySwayRotation(bone, 0.0F, 0.0F, 1.0F);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if ("body".equals(string)) {
            this.applySwayPosition(bone);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (this.CurrentGirl.getCurrentAction() == GirlAnimationState.BOW) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if ("armL".equals(string)) {
            this.applySwayRotation(bone, 0.0F, (float) (-Math.PI / 9), 0.15F);
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      try {
         if (this.CurrentGirl.getCurrentAction() == GirlAnimationState.ATTACK) {
            return;
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      try {
         if ("armR".equals(string)) {
            this.applySwayRotation(bone, 0.0F, (float) (Math.PI / 9), 0.15F);
         }
      } catch (RuntimeException error7) {
         throw rethrow(error7);
      }
   }

   void applySwayRotation(GeoBone bone, float f, float f2, float f3) {
      double d = this.PosX - this.PrevPosX;
      double d2 = this.PosZ - this.PrevPosZ;
      double d3 = (Math.PI / 180.0) * this.CurrentGirl.rotationYaw;
      Vec2f vec2f = new Vec2f((float)(d * Math.cos(d3) + d2 * Math.sin(d3)), (float)(-d * Math.sin(d3) + d2 * Math.cos(d3)));
      this.RotationOffsetX = vec2f.y * -8.0F;
      this.RotationOffsetZ = vec2f.x * 8.0F;
      this.RotationOffsetX = MathUtils.clamp(this.RotationOffsetX, -1.68F, 1.68F);
      this.RotationOffsetZ = MathUtils.clamp(this.RotationOffsetZ, -1.68F, 1.68F);
      this.RotationOffsetX = LerpMath.lerp(this.PrevRotationOffsetX, this.RotationOffsetX, this.RenderTick);
      this.RotationOffsetZ = LerpMath.lerp(this.PrevRotationOffsetZ, this.RotationOffsetZ, this.RenderTick);
      bone.setRotationX(f + this.RotationOffsetX * f3);
      bone.setRotationZ(f2 + this.RotationOffsetZ * f3);
   }

   void applySwayPosition(GeoBone bone) {
      double d = this.PosX - this.PrevPosX;
      double d2 = this.PosZ - this.PrevPosZ;

      try {
         this.SwayAmount = (Math.abs(d) + Math.abs(d2)) * 5.0;
         this.SwayAmount = MathUtils.clamp((float)this.SwayAmount, 0.0F, 1.0F);
         bone.setPositionY((float)LerpMath.cosineLerp(5.0, 0.0, LerpMath.lerp(this.PrevSwayAmount, this.SwayAmount, this.RenderTick)));
         if (this.CurrentGirl instanceof AlliePlayer) {
            ((AlliePlayer)this.CurrentGirl).aq = (float)LerpMath.cosineLerp(0.3F, 0.0, LerpMath.lerp(this.PrevSwayAmount, this.SwayAmount, this.RenderTick));
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   void updateTrackedPlayerPositions() {
      try {
         if (this.CurrentGirl == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         this.PrevRotationOffsetX = this.RotationOffsetX;
         this.PrevRotationOffsetZ = this.RotationOffsetZ;
         this.PrevSwayAmount = this.SwayAmount;
         if (this.CurrentGirl.getBoundPlayerUuid() == null) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player = this.RenderEntity.world.getPlayerEntityByUUID(this.CurrentGirl.getBoundPlayerUuid());

      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      this.PrevPosX = this.PosX;
      this.PrevPosZ = this.PosZ;
      this.PosX = player.posX;
      this.PosZ = player.posZ;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class EventHandler {
      @SubscribeEvent
      public void onClientTick(ClientTickEvent clientTickEvent) {
         for (AlliePlayerRenderer renderer : AlliePlayerRenderer.Instances) {
            renderer.updateTrackedPlayerPositions();
         }
      }
   }
}
