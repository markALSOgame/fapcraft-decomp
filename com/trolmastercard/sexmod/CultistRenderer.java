package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class CultistRenderer extends Render<CultistEntity> {
   static final ResourceLocation StandingTexture = new ResourceLocation("sexmod", "textures/entity/pyrocinical/standing.png");
   static final ResourceLocation PraisingTexture = new ResourceLocation("sexmod", "textures/entity/pyrocinical/praising.png");
   static final ResourceLocation WalkingTexture1 = new ResourceLocation("sexmod", "textures/entity/pyrocinical/walking1.png");
   static final ResourceLocation WalkingTexture2 = new ResourceLocation("sexmod", "textures/entity/pyrocinical/walking2.png");
   static final String FatTextureDir = "textures/entity/pyrocinical/fat/";
   static final int FatFrameCount = 30;
   static final float BaseScale = 1.4F;
   static final float BobSpeedFactor = 0.75F;
   Minecraft Mc;
   ResourceLocation LastTexture = null;
   long LastSoundTime = 0L;

   public CultistRenderer(RenderManager renderManager) {
      super(renderManager);
      this.Mc = Minecraft.getMinecraft();
   }

   @Nullable
   protected ResourceLocation getEntityTexture(CultistEntity cultistEntity) {
      return null;
   }


   public void a(CultistEntity cultistEntity, double d, double d2, double d3, float f, float f2) {
        ResourceLocation resourceLocation;
        block6: {
            GL11.glDisable((int)2896);
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
            EntityPlayerSP entityPlayerSP = this.Mc.player;
            Vec3d vec3d = LerpMath.lerpVec3d(new Vec3d(cultistEntity.lastTickPosX, cultistEntity.lastTickPosY, cultistEntity.lastTickPosZ), cultistEntity.getPositionVector(), (double)f2);
            Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(entityPlayerSP.lastTickPosX, entityPlayerSP.lastTickPosY, entityPlayerSP.lastTickPosZ), entityPlayerSP.getPositionVector(), (double)f2);
            Vec3d vec3d3 = vec3d.subtract(vec3d2);
            resourceLocation = this.getTextureForState(cultistEntity, Math.abs(vec3d3.x) + Math.abs(vec3d3.y) + Math.abs(vec3d3.z));
            this.Mc.renderEngine.bindTexture(resourceLocation);
            GlStateManager.pushMatrix();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)this.getAlpha(cultistEntity, f2));
            GlStateManager.translate((double)vec3d3.x, (double)(vec3d3.y + this.getBobOffset(resourceLocation)), (double)vec3d3.z);
            GlStateManager.rotate((float)(180.0f - this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            float f3 = 1.4f + this.getFatProgress(cultistEntity, f2);
            GlStateManager.scale((float)f3, (float)f3, (float)f3);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferBuilder.pos(-1.0, 0.0, 0.0).tex(0.0, 1.0).endVertex();
            bufferBuilder.pos(1.0, 0.0, 0.0).tex(1.0, 1.0).endVertex();
            bufferBuilder.pos(1.0, 2.0, 0.0).tex(1.0, 0.0).endVertex();
            bufferBuilder.pos(-1.0, 2.0, 0.0).tex(0.0, 0.0).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
            GL11.glEnable((int)2896);
            GlStateManager.disableAlpha();
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)OpenGlHelper.lastBrightnessX, (float)OpenGlHelper.lastBrightnessY);
            long l = System.currentTimeMillis();
            try {
                try {
                    try {
                        if (this.LastTexture == PraisingTexture || resourceLocation != PraisingTexture) break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw CultistRenderer.rethrow(runtimeException);
                    }
                    if (l <= this.LastSoundTime + 60000L) break block6;
                }
                catch (RuntimeException runtimeException) {
                    throw CultistRenderer.rethrow(runtimeException);
                }
                this.Mc.player.playSound(ModSounds.MISC_PYRO[0], 1.0f, 1.0f);
                this.LastSoundTime = l;
            }
            catch (RuntimeException runtimeException) {
                throw CultistRenderer.rethrow(runtimeException);
            }
        }
        this.LastTexture = resourceLocation;
    }

   ResourceLocation getTextureForState(CultistEntity cultistEntity, double d) {
      try {
         if (cultistEntity.HitTick != -1) {
            return new ResourceLocation("sexmod", String.format("%s%s.png", "textures/entity/pyrocinical/fat/", this.getFatFrameIndex(cultistEntity)));
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (d < 3.0) {
            return PraisingTexture;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Vec3d vec3d = new Vec3d(cultistEntity.lastTickPosX, cultistEntity.lastTickPosY, cultistEntity.lastTickPosZ).subtract(cultistEntity.getPositionVector());

      try {
         if (Math.abs(vec3d.x) + Math.abs(vec3d.y) + Math.abs(vec3d.z) == 0.0) {
            return StandingTexture;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (Math.sin(this.Mc.player.ticksExisted * 0.75F) > 0.0) {
            return WalkingTexture1;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      return WalkingTexture2;
   }


   double getBobOffset(ResourceLocation location) {
        block4: {
            try {
                try {
                    if (WalkingTexture1.equals((Object)location) || WalkingTexture2.equals((Object)location)) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw CultistRenderer.rethrow(runtimeException);
                }
                return 0.0;
            }
            catch (RuntimeException runtimeException) {
                throw CultistRenderer.rethrow(runtimeException);
            }
        }
        return Math.sin((float)this.Mc.player.ticksExisted * 0.75f) * (double)0.1f;
    }

   int getFatFrameIndex(CultistEntity cultistEntity) {
      try {
         if (cultistEntity.HitTick == -1) {
            return 0;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return (int)MathUtils.clamp(this.Mc.player.ticksExisted - cultistEntity.HitTick, 1.0F, 30.0F);
   }

   float getFatProgress(CultistEntity cultistEntity, float f) {
      try {
         if (cultistEntity.HitTick == -1) {
            return 0.0F;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      int i = this.getFatFrameIndex(cultistEntity);

      try {
         if (i == 30) {
            return 1.0F;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return (i + f) / 30.0F;
   }

   float getAlpha(CultistEntity cultistEntity, float f) {
      try {
         if (cultistEntity.HitTick == -1) {
            return 1.0F;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (this.Mc.player.ticksExisted - cultistEntity.HitTick > 120) {
            return 0.0F;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      byte bv = 90;
      float f2 = MathUtils.clamp(this.Mc.player.ticksExisted - cultistEntity.HitTick, bv, 120.0F) - bv;
      float f3 = (f2 + f) / 30.0F;
      return 1.0F - f3;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
