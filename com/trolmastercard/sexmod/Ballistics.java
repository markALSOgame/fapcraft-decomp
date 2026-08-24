package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Ballistics {
   public static final float Gravity = 9.81F;
   public static final float g = 0.05F;
   public static final float b = 0.05F;
   public static final float c = 0.03F;
   World WorldObj;
   Vec3d PrevPos;
   Vec3d CurrentPos;
   Vec3d Velocity;

   public Ballistics(World world, Vec3d vec3d, Vec3d vec3d2) {
      this.WorldObj = world;
      this.CurrentPos = vec3d;
      this.PrevPos = vec3d;
      this.Velocity = vec3d2;
   }


   public void advance() {
        int i;
        int i2;
        try {
            if (Vec3d.ZERO.equals((Object)this.Velocity)) {
                this.PrevPos = this.CurrentPos;
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw Ballistics.rethrow(runtimeException);
        }
        this.Velocity = new Vec3d(this.Velocity.x * (double)0.95f, (this.Velocity.y - 0.4905000329017639) * (double)0.95f, this.Velocity.z * (double)0.95f);
        this.PrevPos = this.CurrentPos;
        this.CurrentPos = new Vec3d(this.CurrentPos.x + this.Velocity.x * (double)0.05f, this.CurrentPos.y + this.Velocity.y * (double)0.05f, this.CurrentPos.z + this.Velocity.z * (double)0.05f);
        BlockPos blockPos = new BlockPos(this.PrevPos);
        BlockPos blockPos2 = null;
        for (BlockPos blockPos3 : Ballistics.getRayTracedBlocks(new BlockPos(this.PrevPos), new BlockPos(this.CurrentPos))) {
            if (this.WorldObj.getBlockState(blockPos3).getBlock() == Blocks.AIR) {
                blockPos = blockPos3;
                continue;
            }
            blockPos2 = blockPos3;
            break;
        }
        try {
            if (blockPos2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw Ballistics.rethrow(runtimeException);
        }
        int i3 = blockPos2.getX();
        int i4 = blockPos.getX();
        if (i3 - i4 != 0) {
            int i5;
            float f;
            double d;
            Vec3d vec3d;
            Vec3d vec3d2;
            double d2 = Math.max(i3, i4);
            double d3 = (this.PrevPos.y - this.CurrentPos.y) / (this.PrevPos.x - this.CurrentPos.x);
            double d4 = this.CurrentPos.y - d3 * this.CurrentPos.x;
            double d5 = d3 * d2 + d4;
            double d6 = (this.PrevPos.z - this.CurrentPos.z) / (this.PrevPos.x - this.CurrentPos.x);
            double d7 = this.CurrentPos.z - d6 * this.CurrentPos.x;
            double d8 = d6 * d2 + d7;
            try {
                Vec3d vec3d3;
                Ballistics ballistics = this;
                vec3d2 = vec3d3;
                vec3d = vec3d3;
                d = d2;
                f = 0.03f;
                i5 = i3 > i4 ? -1 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw Ballistics.rethrow(runtimeException);
            }
            vec3d2(d + (double)(f * (float)i5), d5, d8);
            ballistics.CurrentPos = vec3d;
            this.Velocity = new Vec3d(0.0, 0.0, 0.0);
            return;
        }
        int i6 = blockPos2.getY();
        if (i6 - (i2 = blockPos.getY()) != 0) {
            int i7;
            float f2;
            double d9;
            double d37;
            Vec3d vec3d;
            Vec3d vec3d4;
            double d38 = Math.max(i6, i2);
            double d39 = (this.PrevPos.x - this.CurrentPos.x) / (this.PrevPos.y - this.CurrentPos.y);
            double d40 = this.CurrentPos.x - d39 * this.CurrentPos.y;
            double d41 = d39 * d38 + d40;
            double d42 = (this.PrevPos.z - this.CurrentPos.z) / (this.PrevPos.y - this.CurrentPos.y);
            double d43 = this.CurrentPos.z - d42 * this.CurrentPos.y;
            double d44 = d42 * d38 + d43;
            try {
                Vec3d vec3d5;
                Ballistics ballistics2 = this;
                vec3d4 = vec3d5;
                vec3d = vec3d5;
                d37 = d41;
                d9 = d38;
                f2 = 0.03f;
                i7 = i6 > i2 ? -1 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw Ballistics.rethrow(runtimeException);
            }
            vec3d4(d37, d9 + (double)(f2 * (float)i7), d44);
            ballistics2.CurrentPos = vec3d;
            this.Velocity = new Vec3d(0.0, 0.0, 0.0);
            return;
        }
        int i8 = blockPos2.getZ();
        if (i8 - (i = blockPos.getZ()) != 0) {
            int i9;
            float f3;
            double d45;
            double d46;
            double d47;
            Vec3d vec3d;
            Vec3d vec3d6;
            double d48 = Math.max(i8, i);
            double d49 = (this.PrevPos.y - this.CurrentPos.y) / (this.PrevPos.z - this.CurrentPos.z);
            double d50 = this.CurrentPos.y - d49 * this.CurrentPos.z;
            double d51 = d49 * d48 + d50;
            double d52 = (this.PrevPos.x - this.CurrentPos.x) / (this.PrevPos.z - this.CurrentPos.z);
            double d53 = this.CurrentPos.x - d52 * this.CurrentPos.z;
            double d54 = d52 * d48 + d53;
            try {
                Vec3d vec3d7;
                Ballistics ballistics3 = this;
                vec3d6 = vec3d7;
                vec3d = vec3d7;
                d47 = d54;
                d46 = d51;
                d45 = d48;
                f3 = 0.03f;
                i9 = i8 > i ? -1 : 1;
            }
            catch (RuntimeException runtimeException) {
                throw Ballistics.rethrow(runtimeException);
            }
            vec3d6(d47, d46, d45 + (double)(f3 * (float)i9));
            ballistics3.CurrentPos = vec3d;
            this.Velocity = new Vec3d(0.0, 0.0, 0.0);
            return;
        }
    }

   static List<BlockPos> getRayTracedBlocks(BlockPos pos, BlockPos pos2) {
      ArrayList list = new ArrayList();
      list.add(pos);
      int i = pos.getX();
      int i2 = pos.getY();
      int i3 = pos.getZ();
      int i4 = pos2.getX();
      int i5 = pos2.getY();
      int i6 = pos2.getZ();
      int i7 = Math.abs(i4 - i);
      int i8 = Math.abs(i5 - i2);
      int i9 = Math.abs(i6 - i3);

      byte bv;
      label62: {
         try {
            if (i < i4) {
               bv = 1;
               break label62;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         bv = -1;
      }

      byte bv2 = bv;

      label54: {
         try {
            if (i2 < i5) {
               bv = 1;
               break label54;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         bv = -1;
      }

      byte bv3 = bv;

      label46: {
         try {
            if (i3 < i6) {
               bv = 1;
               break label46;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         bv = -1;
      }

      byte bv4 = bv;
      int i10 = Math.max(i7, Math.max(i8, i9));
      int i11 = i;
      int i12 = i2;
      int i13 = i3;
      int i14 = i10 / 2;
      int i15 = i10 / 2;
      int i16 = i10 / 2;

      for (int i17 = 0; i17 < i10; i17++) {
         list.add(new BlockPos(i11, i12, i13));
         i14 -= i7;
         i15 -= i8;
         i16 -= i9;
         if (i14 < 0) {
            i11 += bv2;
            i14 += i10;
         } else if (i15 < 0) {
            i12 += bv3;
            i15 += i10;
         } else if (i16 < 0) {
            i13 += bv4;
            i16 += i10;
         }
      }

      list.add(pos2);
      return list;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
