package com.trolmastercard.sexmod;

import net.minecraft.block.BlockDoor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;

public class EntityAIOpenDoor extends EntityAIBase {
   protected EntityLiving entity;
   protected BlockPos doorPosition = BlockPos.ORIGIN;
   protected BlockDoor doorBlock;
   boolean isDoorOpen;
   float xDiff;
   float zDiff;
   int closeDelay = 10;

   public EntityAIOpenDoor(EntityLiving living) {
      this.entity = living;
      if (!(living.getNavigator() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }


   public boolean shouldExecute() {
        block20: {
            boolean flag;
            boolean flag2 = true;
            for (int i = -3; i < 5; ++i) {
                for (int i5 = -3; i5 < 5; ++i5) {
                    IBlockState iBlockState = this.entity.world.getBlockState(this.entity.getPosition().add(i, 0, i5));
                    try {
                        if (!(iBlockState.getBlock() instanceof BlockDoor) || iBlockState.getMaterial() != Material.WOOD) continue;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                    }
                    flag2 = false;
                    break;
                }
                try {
                    if (flag2) continue;
                    break;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                }
            }
            try {
                if (flag2) {
                    return false;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw EntityAIOpenDoor.rethrow(illegalArgumentException);
            }
            PathNavigateGround pathNavigateGround = (PathNavigateGround)this.entity.getNavigator();
            Path path = pathNavigateGround.getPath();
            try {
                try {
                    if (path == null || path.isFinished()) break block20;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                }
                if (!pathNavigateGround.getEnterDoors()) break block20;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw EntityAIOpenDoor.rethrow(illegalArgumentException);
            }
            for (int i6 = 0; i6 < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); ++i6) {
                PathPoint pathPoint = path.getPathPointFromIndex(i6);
                try {
                    try {
                        this.doorPosition = new BlockPos(pathPoint.x, pathPoint.y + 1, pathPoint.z);
                        if (!(this.entity.getDistanceSq((double)this.doorPosition.getX(), this.entity.posY, (double)this.doorPosition.getZ()) <= 2.25)) continue;
                        this.doorBlock = this.getDoorBlock(this.doorPosition);
                        if (this.doorBlock == null) continue;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                    }
                    return true;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                }
            }
            try {
                this.doorPosition = new BlockPos((Entity)this.entity).up();
                this.doorBlock = this.getDoorBlock(this.doorPosition);
                flag = this.doorBlock != null;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw EntityAIOpenDoor.rethrow(illegalArgumentException);
            }
            return flag;
        }
        return false;
    }

   public boolean shouldContinueExecuting() {
      try {
         if (this.closeDelay >= 0) {
            return true;
         }
      } catch (IllegalArgumentException error) {
         throw rethrow(error);
      }

      return false;
   }

   public void startExecuting() {
      this.isDoorOpen = false;
      this.xDiff = (float)(this.doorPosition.getX() + 0.5F - this.entity.posX);
      this.zDiff = (float)(this.doorPosition.getZ() + 0.5F - this.entity.posZ);
      this.doorBlock.toggleDoor(this.entity.world, this.doorPosition, true);
   }


   public void updateTask() {
        block4: {
            float f = (float)((double)((float)this.doorPosition.getX() + 0.5f) - this.entity.posX);
            float f2 = (float)((double)((float)this.doorPosition.getZ() + 0.5f) - this.entity.posZ);
            float f3 = this.xDiff * f + this.zDiff * f2;
            try {
                try {
                    if (!(f3 < 0.0f) || --this.closeDelay > 0) break block4;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                }
                this.doorBlock.toggleDoor(this.entity.world, this.doorPosition, false);
                this.isDoorOpen = true;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw EntityAIOpenDoor.rethrow(illegalArgumentException);
            }
        }
    }

   public void resetTask() {
      this.closeDelay = 10;
   }


   private BlockDoor getDoorBlock(BlockPos pos) {
        BlockDoor blockDoor;
        block5: {
            block4: {
                IBlockState iBlockState = this.entity.world.getBlockState(pos);
                Block block = iBlockState.getBlock();
                try {
                    try {
                        if (!(block instanceof BlockDoor) || iBlockState.getMaterial() != Material.WOOD) break block4;
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                    }
                    blockDoor = (BlockDoor)block;
                    break block5;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    throw EntityAIOpenDoor.rethrow(illegalArgumentException);
                }
            }
            blockDoor = null;
        }
        return blockDoor;
    }

   private static IllegalArgumentException rethrow(IllegalArgumentException error) {
      return error;
   }
}
