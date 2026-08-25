package com.trolmastercard.sexmod;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed.EnumPartType;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.BlockBed;

public class BedLogic {
   public static float angleDifference(float f, float f2) {
      f = AngleMath.normalizeDegrees(f);
      f2 = AngleMath.normalizeDegrees(f2);
      float f3 = Math.abs(f - f2);
      float f4 = 360.0F - f3;
      float f5 = Math.min(f3, f4);

      try {
         return f > f2 ? -f5 : f5;
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   public static Vec3d getBedHeadPos(EntityLivingBase livingBase, float f) {
      World world2 = livingBase.world;

      try {
         if (world2 instanceof PreviewWorld) {
            return new Vec3d(0.0, 1.0, 0.0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      BlockPos pos = new BlockPos(Math.floor(livingBase.posX), Math.floor(livingBase.posY), Math.floor(livingBase.posZ));
      HashMap map = new HashMap();
      int i = 0;

      for (int i2 = -1; i2 < 2; i2++) {
         for (int i3 = -1; i3 < 2; i3++) {
            for (int i4 = -1; i4 < 2; i4++) {
               int i5 = world2.getLight(pos.add(i2, i3, i4), false);
               map.put(new Vec3d(i2, i3, i4), i5);
               if (i5 > i) {
                  i = i5;
               }
            }
         }
      }

      Vec3d vec3d = null;

      for (Entry entry : map.entrySet()) {
         try {
            if ((Integer)entry.getValue() != i) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         if (vec3d != null) {
            vec3d = null;
            break;
         }

         vec3d = (Vec3d)entry.getKey();
      }

      if (vec3d == null) {
         vec3d = new Vec3d(0.2, 0.8, 0.0);
      } else {
         vec3d = new Vec3d(vec3d.x, vec3d.y, -vec3d.z);
         float f2 = -LerpMath.lerp(livingBase.prevRenderYawOffset, livingBase.renderYawOffset, f);
         vec3d = VectorMath.rotateYaw(vec3d, f2);
      }

      return vec3d.normalize();
   }

   public static int countNearbyBeds(World world, int i, int i2) {
      HashSet set = Sets.newHashSet(
         new Block[]{Blocks.GRASS, Blocks.SAND, Blocks.RED_SANDSTONE, Blocks.WATER, Blocks.STONE, Blocks.COBBLESTONE}
      );
      int i3 = world.getHeight();
      boolean flag = false;

      while (true) {
         try {
            if (flag || i3-- < 0) {
               return i3;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         Block block = world.getBlockState(new BlockPos(i, i3, i2)).getBlock();
         flag = set.contains(block);
      }
   }

   public static BlockPos isBedSpawnValid(World world, BlockPos pos) {
      return new BlockPos(pos.getX(), countNearbyBeds(world, pos.getX(), pos.getZ()), pos.getZ());
   }

   public static boolean isBedBlock(World world, BlockPos pos) {
      return a(world, pos, null, null, null);
   }


   public static boolean getBedRespawnPos(World world, BlockPos pos, Vec3d vec3d, EnumFacing facing, EntityPlayer player) {
        block21: {
            Object object;
            Block block;
            IBlockState iBlockState;
            block20: {
                block18: {
                    iBlockState = world.getBlockState(pos);
                    block = iBlockState.getBlock();
                    try {
                        if (block.isBed(iBlockState, (IBlockAccess)world, pos, null)) {
                            return true;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw BedLogic.rethrow(runtimeException);
                    }
                    TileEntity tileEntity = world.getTileEntity(pos);
                    if (tileEntity != null) {
                        object = tileEntity.getDisplayName();
                        try {
                            block19: {
                                try {
                                    try {
                                        if (object == null) break block18;
                                        if (object.toString().contains(" bed")) break block19;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw BedLogic.rethrow(runtimeException);
                                    }
                                    if (!object.toString().contains("bed ")) break block18;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw BedLogic.rethrow(runtimeException);
                                }
                            }
                            return true;
                        }
                        catch (RuntimeException runtimeException) {
                            throw BedLogic.rethrow(runtimeException);
                        }
                    }
                }
                try {
                    try {
                        if (facing != null && vec3d != null) break block20;
                    }
                    catch (RuntimeException runtimeException) {
                        throw BedLogic.rethrow(runtimeException);
                    }
                    return false;
                }
                catch (RuntimeException runtimeException) {
                    throw BedLogic.rethrow(runtimeException);
                }
            }
            object = block.getPickBlock(iBlockState, new RayTraceResult(vec3d, facing), world, pos, player).getDisplayName().toLowerCase();
            try {
                try {
                    if (!((String)object).contains(" bed") && !((String)object).contains("bed ")) break block21;
                }
                catch (RuntimeException runtimeException) {
                    throw BedLogic.rethrow(runtimeException);
                }
                return true;
            }
            catch (RuntimeException runtimeException) {
                throw BedLogic.rethrow(runtimeException);
            }
        }
        return false;
    }

   public static void spawnParticles(World world, EnumParticleTypes particle, Vec3d vec3d, int i, double d, double d2) {
      for (int i2 = 0; i2 < i; i2++) {
         float f = (float)i2 / i;
         double d3 = (Math.PI * 2) * f;
         double d4 = Math.sin(d3);
         double d5 = Math.cos(d3);
         d4 *= d;
         d5 *= d;
         world.spawnParticle(particle, vec3d.x + d4, vec3d.y, vec3d.z + d5, 0.0, ModConstants.Random.nextFloat() * d2, 0.0, new int[0]);
      }
   }

   public static BlockPos getBedPosForState(BlockPos pos, IBlockState state) {
      ImmutableMap immutableMap = state.getProperties();
      EnumFacing facing = null;
      EnumPartType enumPartType = null;
      UnmodifiableIterator unmodifiableIterator = immutableMap.entrySet().iterator();

      while (unmodifiableIterator.hasNext()) {
         Entry entry = (Entry)unmodifiableIterator.next();
         if (entry.getKey() instanceof PropertyDirection) {
            facing = (EnumFacing)entry.getValue();
         } else if (entry.getKey() instanceof PropertyEnum) {
            enumPartType = (EnumPartType)entry.getValue();
         }
      }

      try {
         if (facing == null) {
            System.out.println("bed is fucked up - it has no facing value");
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (enumPartType == null) {
            System.out.println("bed is fucked up - it has no partType value");
            return null;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      BlockPos pos2 = null;

      label91: {
         label92: {
            label72: {
               try {
                  if (enumPartType != EnumPartType.FOOT) {
                     break label92;
                  }

                  if (facing != EnumFacing.NORTH) {
                     break label72;
                  }
               } catch (RuntimeException error3) {
                  throw rethrow(error3);
               }

               pos2 = pos.north();
            }

            if (facing == EnumFacing.EAST) {
               pos2 = pos.east();
            }

            if (facing == EnumFacing.SOUTH) {
               pos2 = pos.south();
            }

            if (facing == EnumFacing.WEST) {
               pos2 = pos.west();
            }
            break label91;
         }

         if (facing == EnumFacing.NORTH) {
            pos2 = pos.south();
         }

         if (facing == EnumFacing.EAST) {
            pos2 = pos.west();
         }

         if (facing == EnumFacing.SOUTH) {
            pos2 = pos.north();
         }

         if (facing == EnumFacing.WEST) {
            pos2 = pos.east();
         }
      }

      try {
         if (pos2 == null) {
            System.out.println("bed is fucked up - it appears to be positioned vertically (wtf?)");
            return null;
         } else {
            return pos2;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }
   }

   public static Set<? extends EntityPlayer> getNearbyNetworkPlayers(Entity entity) {
      try {
         if (entity == null) {
            return Collections.emptySet();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(entity.dimension).getEntityTracker().getTrackingPlayers(entity);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
