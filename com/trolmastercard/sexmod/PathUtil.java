package com.trolmastercard.sexmod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;

public class PathUtil {
   public static BlockPos getPathEndPos(Path path) {
      try {
         if (path == null) {
            return BlockPos.ORIGIN;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      PathPoint pathPoint = path.getFinalPathPoint();

      try {
         if (pathPoint == null) {
            return BlockPos.ORIGIN;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return new BlockPos(pathPoint.x, pathPoint.y, pathPoint.z);
   }

   public static BlockPos getPathTargetPos(EntityLiving living) {
      PathNavigate navigator = living.getNavigator();
      Path path = navigator.getPath();
      return getPathEndPos(path);
   }


   public static boolean pathIntersectsPositions(Path path, BlockPos[] posArray) {
        int i = path.getCurrentPathLength();
        ArrayList<PathPoint> arrayList = new ArrayList<PathPoint>();
        try {
            for (int i3 = 0; i3 < i; ++i3) {
                arrayList.add(path.getPathPointFromIndex(i3));
            }
        }
        catch (RuntimeException runtimeException) {
            throw PathUtil.rethrow(runtimeException);
        }
        for (PathPoint pathPoint : arrayList) {
            for (BlockPos blockPos : posArray) {
                try {
                    try {
                        try {
                            if (pathPoint.x != blockPos.getX() || pathPoint.y != blockPos.getY()) continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw PathUtil.rethrow(runtimeException);
                        }
                        if (pathPoint.z != blockPos.getZ()) continue;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PathUtil.rethrow(runtimeException);
                    }
                    return true;
                }
                catch (RuntimeException runtimeException) {
                    throw PathUtil.rethrow(runtimeException);
                }
            }
        }
        return false;
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
