package com.trolmastercard.sexmod;

import net.minecraft.client.particle.ParticleDragonBreath;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DragonBreathParticles extends ParticleDragonBreath {
   public static final float a = 0.2F;
   public static final float c = 0.5F;
   public static float ParticleScale = 0.2F;

   public DragonBreathParticles(World world, double d, double d2, double d3) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
   }

   public void renderParticle(BufferBuilder bufferBuilder, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      this.particleScale = ParticleScale;
      float f7 = this.particleTextureIndexX / 16.0F;
      float f8 = f7 + 0.0624375F;
      float f9 = this.particleTextureIndexY / 16.0F;
      float f10 = f9 + 0.0624375F;
      float f11 = 0.1F * this.particleScale;
      if (this.particleTexture != null) {
         f7 = this.particleTexture.getMinU();
         f8 = this.particleTexture.getMaxU();
         f9 = this.particleTexture.getMinV();
         f10 = this.particleTexture.getMaxV();
      }

      float f12 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
      float f13 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
      float f14 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
      int i = this.getBrightnessForRender(f);
      int i2 = i >> 16 & 65535;
      int i3 = i & 65535;
      Vec3d[] vec3dArray = new Vec3d[]{
         new Vec3d(-f2 * f11 - f5 * f11, -f3 * f11, -f4 * f11 - f6 * f11),
         new Vec3d(-f2 * f11 + f5 * f11, f3 * f11, -f4 * f11 + f6 * f11),
         new Vec3d(f2 * f11 + f5 * f11, f3 * f11, f4 * f11 + f6 * f11),
         new Vec3d(f2 * f11 - f5 * f11, -f3 * f11, f4 * f11 - f6 * f11)
      };
      if (this.particleAngle != 0.0F) {
         float f15 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * f;
         float f16 = MathHelper.cos(f15 * 0.5F);
         float f17 = MathHelper.sin(f15 * 0.5F) * (float)cameraViewDir.x;
         float f18 = MathHelper.sin(f15 * 0.5F) * (float)cameraViewDir.y;
         float f19 = MathHelper.sin(f15 * 0.5F) * (float)cameraViewDir.z;
         Vec3d vec3d = new Vec3d(f17, f18, f19);
         int i4 = 0;

         try {
            while (i4 < 4) {
               vec3dArray[i4] = vec3d.scale(2.0 * vec3dArray[i4].dotProduct(vec3d))
                  .add(vec3dArray[i4].scale(f16 * f16 - vec3d.dotProduct(vec3d)))
                  .add(vec3d.crossProduct(vec3dArray[i4]).scale(2.0F * f16));
               i4++;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }

      bufferBuilder.pos(f12 + vec3dArray[0].x, f13 + vec3dArray[0].y, f14 + vec3dArray[0].z)
         .tex(f8, f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(i2, i3)
         .endVertex();
      bufferBuilder.pos(f12 + vec3dArray[1].x, f13 + vec3dArray[1].y, f14 + vec3dArray[1].z)
         .tex(f8, f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(i2, i3)
         .endVertex();
      bufferBuilder.pos(f12 + vec3dArray[2].x, f13 + vec3dArray[2].y, f14 + vec3dArray[2].z)
         .tex(f7, f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(i2, i3)
         .endVertex();
      bufferBuilder.pos(f12 + vec3dArray[3].x, f13 + vec3dArray[3].y, f14 + vec3dArray[3].z)
         .tex(f7, f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(i2, i3)
         .endVertex();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
