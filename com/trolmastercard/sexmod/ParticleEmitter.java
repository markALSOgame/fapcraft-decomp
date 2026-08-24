package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ParticleEmitter {
   static final int c = 30;
   static final int k = 6;
   static final int f = 6;
   static final float b = 0.15F;
   List<Ballistics> Particles = new ArrayList<>();
   final int MaxParticles;
   final GirlAnchor StartAnchor;
   final GirlAnchor2 EndAnchor;
   final GirlEntity Girl;
   final float SpreadRadius;
   final float SegmentBreakDistance;

   public ParticleEmitter(int i, GirlAnchor girlAnchor, GirlAnchor2 girlAnchor2, GirlEntity girl, float f, float f2) {
      this.MaxParticles = i;
      this.StartAnchor = girlAnchor;
      this.EndAnchor = girlAnchor2;
      this.Girl = girl;
      this.SpreadRadius = f;
      this.SegmentBreakDistance = f2;
   }

   void draw(Minecraft mc, Tessellator tessellator, BufferBuilder bufferBuilder, float f) {
      if (this.Particles.size() < this.MaxParticles) {
         for (int i = 0; i < 6; i++) {
            Vec3d vec3d = this.StartAnchor.getAnchorPos(this.Girl);
            this.Particles
               .add(
                  new Ballistics(
                     mc.world,
                     this.EndAnchor.canSeeEntity(this.Girl),
                     new Vec3d(
                        vec3d.x + (ModConstants.Random.nextFloat() * 2.0F - 1.0F) * this.SpreadRadius,
                        vec3d.y + (ModConstants.Random.nextFloat() * 2.0F - 1.0F) * this.SpreadRadius,
                        vec3d.z + (ModConstants.Random.nextFloat() * 2.0F - 1.0F) * this.SpreadRadius
                     )
                  )
               );
         }
      }

      GlStateManager.disableCull();
      GlStateManager.disableAlpha();
      Vec3d vec3d2 = LerpMath.lerpVec3d(
         new Vec3d(mc.player.lastTickPosX, mc.player.lastTickPosY, mc.player.lastTickPosZ),
         mc.player.getPositionVector(),
         f
      );
      bufferBuilder.begin(9, DefaultVertexFormats.POSITION_COLOR);
      this.sortByDistance();
      Vec3d vec3d3 = null;

      for (Ballistics ballistics : this.Particles) {
         Vec3d vec3d4 = LerpMath.lerpVec3d(ballistics.PrevPos, ballistics.CurrentPos, f);
         if (vec3d3 == null) {
            vec3d3 = vec3d4;
         }

         try {
            if (vec3d3.distanceTo(vec3d4) > this.SegmentBreakDistance) {
               tessellator.draw();
               bufferBuilder.begin(9, DefaultVertexFormats.POSITION_COLOR);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         bufferBuilder.pos(vec3d4.x - vec3d2.x, vec3d4.y - vec3d2.y, vec3d4.z - vec3d2.z)
            .color(255, 255, 255, 255)
            .endVertex();
         vec3d3 = vec3d4;
      }

      tessellator.draw();
      GlStateManager.enableCull();
   }

   void a() {
      for (Ballistics ballistics : this.Particles) {
         ballistics.advance();
      }
   }


   void sortByDistance() {
        block10: {
            try {
                try {
                    if (!this.Particles.isEmpty() && this.Particles.size() > 1) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw ParticleEmitter.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ParticleEmitter.rethrow(runtimeException);
            }
        }
        for (int i = 1; i < this.Particles.size(); ++i) {
            Ballistics ballistics = this.Particles.get(i);
            Vec3d vec3d = ballistics.CurrentPos;
            int i3 = i - 1;
            try {
                while (true) {
                    try {
                        if (i3 < 0 || !(vec3d.distanceTo(this.Particles.get((int)i3).f) < vec3d.distanceTo(this.Particles.get((int)(i3 + 1)).f))) break;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ParticleEmitter.rethrow(runtimeException);
                    }
                    this.Particles.set(i3 + 1, this.Particles.get(i3));
                    --i3;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ParticleEmitter.rethrow(runtimeException);
            }
            this.Particles.set(i3 + 1, ballistics);
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
