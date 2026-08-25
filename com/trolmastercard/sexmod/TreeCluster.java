package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeCluster {
   public static final int MaxLogCapacity = 30;
   BlockPos AnchorPos;
   KoboldTask TaskType;
   HashSet<BlockPos> LogPositions;
   List<KoboldNpc> Assignees = new ArrayList<>();
   EnumFacing Facing = EnumFacing.NORTH;

   public TreeCluster(BlockPos pos, KoboldTask taskType, HashSet<BlockPos> set) {
      this.AnchorPos = pos;
      this.TaskType = taskType;
      this.LogPositions = set;
   }

   public TreeCluster(BlockPos pos, KoboldTask taskType, HashSet<BlockPos> set, EnumFacing facing) {
      this.AnchorPos = pos;
      this.TaskType = taskType;
      this.LogPositions = set;
      this.Facing = facing;
   }

   public EnumFacing getFacing() {
      return this.Facing;
   }

   public BlockPos getAnchorPos() {
      return this.AnchorPos;
   }

   public KoboldTask getTaskType() {
      return this.TaskType;
   }

   public HashSet<BlockPos> getLogPositions() {
      return this.LogPositions;
   }

   public void addLogPos(BlockPos pos) {
      this.LogPositions.add(pos);
   }

   public void addAllLogPos(HashSet<BlockPos> set) {
      this.LogPositions.addAll(set);
   }

   public void removeLogPos(BlockPos pos) {
      this.LogPositions.remove(pos);
   }

   public void removeAllLogPos(HashSet<BlockPos> set) {
      try {
         if (!set.isEmpty()) {
            this.LogPositions.removeAll(set);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   public boolean containsLogPos(BlockPos pos) {
      return this.LogPositions.contains(pos);
   }

   public boolean assignKobold(KoboldNpc kobold) {
      try {
         if (this.TaskType.Cost <= this.Assignees.size()) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.Assignees.add(kobold);
      return true;
   }

   public List<KoboldNpc> getAssignees() {
      return this.Assignees;
   }

   public void clearAssignees() {
      for (KoboldNpc kobold : this.Assignees) {
         try {
            if (kobold.getSexPlayerUuid() == null) {
               kobold.setNoGravity(false);
               kobold.noClip = false;
               kobold.setCurrentAction(GirlAnimationState.NULL);
               kobold.getDataManager().set(GirlEntity.BusyKey, false);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }

      this.Assignees.clear();
   }

   public void unassignKobold(KoboldNpc kobold) {
      this.Assignees.remove(kobold);
   }

   public boolean isFull() {
      try {
         if (this.TaskType.Cost <= this.Assignees.size()) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   public boolean isAssigned(KoboldNpc kobold) {
      return this.Assignees.contains(kobold);
   }


   public static HashSet<BlockPos> createFallTask(World world, BlockPos pos, UUID uuid) {
        BlockPos blockPos2 = pos;
        while (!TreeCluster.hasGroundBelow(world, blockPos2)) {
            blockPos2 = pos.down();
        }
        BlockPos blockPos3 = pos;
        while (!TreeCluster.isLogColumnTop(world, blockPos3)) {
            blockPos3 = blockPos3.up();
        }
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        int i = blockPos3.getY() - blockPos2.getY();
        try {
            for (int i3 = 0; i3 <= i; ++i3) {
                hashSet.add(blockPos2.add(0, i3, 0));
            }
        }
        catch (RuntimeException runtimeException) {
            throw TreeCluster.rethrow(runtimeException);
        }
        HashSet<BlockPos> hashSet2 = TreeCluster.floodFill(world, blockPos2);
        HashSet<BlockPos> hashSet3 = new HashSet<BlockPos>();
        for (BlockPos object2 : hashSet2) {
            try {
                try {
                    if (object2.getX() != blockPos2.getX() || object2.getZ() != blockPos2.getZ()) continue;
                }
                catch (RuntimeException runtimeException) {
                    throw TreeCluster.rethrow(runtimeException);
                }
                hashSet3.add(object2);
            }
            catch (RuntimeException runtimeException) {
                throw TreeCluster.rethrow(runtimeException);
            }
        }
        for (BlockPos blockPos4 : hashSet3) {
            hashSet2.remove(blockPos4);
        }
        hashSet.addAll(hashSet2);
        HashSet hashSet4 = new HashSet();
        block13: for (BlockPos blockPos5 : hashSet) {
            for (TreeCluster treeCluster : GirlHomeBuilder.getTreeClusters(uuid)) {
                HashSet<BlockPos> hashSet5 = treeCluster.getLogPositions();
                try {
                    if (!hashSet5.contains(blockPos5)) continue;
                    hashSet4.add(blockPos5);
                    continue block13;
                }
                catch (RuntimeException runtimeException) {
                    throw TreeCluster.rethrow(runtimeException);
                }
            }
        }
        hashSet.removeAll(hashSet4);
        TreeCluster treeCluster2 = new TreeCluster(blockPos2, KoboldTask.FALL_TREE, hashSet);
        GirlHomeBuilder.addAnchor(uuid, treeCluster2);
        return hashSet;
    }

   static boolean isLogColumnTop(World world, BlockPos pos) {
      Block block = world.getBlockState(pos.up()).getBlock();

      try {
         if (!(block instanceof BlockLog)) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }


   static boolean hasGroundBelow(World world, BlockPos pos) {
        boolean flag;
        block5: {
            block4: {
                IBlockState iBlockState = world.getBlockState(pos.down());
                try {
                    try {
                        if (iBlockState instanceof BlockLog || iBlockState.getMaterial() == Material.AIR) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw TreeCluster.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw TreeCluster.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

   static HashSet<BlockPos> floodFill(World world, BlockPos pos) {
      return floodFillRecursive(world, pos, new HashSet<>());
   }

   static HashSet<BlockPos> floodFillRecursive(World world, BlockPos pos, HashSet<BlockPos> set) {
      try {
         if (set.contains(pos)) {
            return new HashSet<>();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         set.add(pos);
         if (world.getBlockState(pos.add(1, 0, 0)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(1, 0, 0), set));
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (world.getBlockState(pos.add(-1, 0, 0)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(-1, 0, 0), set));
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (world.getBlockState(pos.add(0, 0, 1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(0, 0, 1), set));
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if (world.getBlockState(pos.add(0, 0, -1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(0, 0, -1), set));
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      try {
         if (world.getBlockState(pos.add(1, 0, 1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(1, 0, 1), set));
         }
      } catch (RuntimeException error6) {
         throw rethrow(error6);
      }

      try {
         if (world.getBlockState(pos.add(-1, 0, -1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(-1, 0, -1), set));
         }
      } catch (RuntimeException error7) {
         throw rethrow(error7);
      }

      try {
         if (world.getBlockState(pos.add(-1, 0, 1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(-1, 0, 1), set));
         }
      } catch (RuntimeException error8) {
         throw rethrow(error8);
      }

      try {
         if (world.getBlockState(pos.add(1, 0, -1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(1, 0, -1), set));
         }
      } catch (RuntimeException error9) {
         throw rethrow(error9);
      }

      try {
         if (world.getBlockState(pos.add(0, 1, 0)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(0, 1, 0), set));
         }
      } catch (RuntimeException error10) {
         throw rethrow(error10);
      }

      try {
         if (world.getBlockState(pos.add(1, 1, 0)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(1, 1, 0), set));
         }
      } catch (RuntimeException error11) {
         throw rethrow(error11);
      }

      try {
         if (world.getBlockState(pos.add(-1, 1, 0)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(-1, 1, 0), set));
         }
      } catch (RuntimeException error12) {
         throw rethrow(error12);
      }

      try {
         if (world.getBlockState(pos.add(0, 1, 1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(0, 1, 1), set));
         }
      } catch (RuntimeException error13) {
         throw rethrow(error13);
      }

      try {
         if (world.getBlockState(pos.add(0, 1, -1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(0, 1, -1), set));
         }
      } catch (RuntimeException error14) {
         throw rethrow(error14);
      }

      try {
         if (world.getBlockState(pos.add(1, 1, 1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(1, 1, 1), set));
         }
      } catch (RuntimeException error15) {
         throw rethrow(error15);
      }

      try {
         if (world.getBlockState(pos.add(-1, 1, -1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(-1, 1, -1), set));
         }
      } catch (RuntimeException error16) {
         throw rethrow(error16);
      }

      try {
         if (world.getBlockState(pos.add(-1, 1, 1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(-1, 1, 1), set));
         }
      } catch (RuntimeException error17) {
         throw rethrow(error17);
      }

      try {
         if (world.getBlockState(pos.add(1, 1, -1)).getBlock() instanceof BlockLog) {
            set.addAll(floodFillRecursive(world, pos.add(1, 1, -1), set));
         }

         return set;
      } catch (RuntimeException error18) {
         throw rethrow(error18);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public enum KoboldTask {
      FALL_TREE(1),
      MINE(3);

      int Cost;

      KoboldTask(int i) {
         this.Cost = i;
      }

      int getCost() {
         return this.Cost;
      }
   }
}
