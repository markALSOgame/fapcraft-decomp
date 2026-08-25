package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class KoboldVillageGenerator extends WorldSavedData implements IWorldGenerator {
   static final String GenDataKey = "sexmod:generation";
   static final int h = 156;
   static final int a = 62;
   static final int b = 6;
   final double GenChance = 0.004F;
   public static boolean ShouldGenBuildings = true;
   final List<KoboldVillageGenerator.TribeConfig> TribeBiomes = new ArrayList<>();
   final List<KoboldVillageGenerator.TribeTemplate> QueuedTemplates = new ArrayList<>();
   private static KoboldVillageGenerator g = null;
   static boolean c = true;

   public static KoboldVillageGenerator getInstance() {
      try {
         if (g == null) {
            g = new KoboldVillageGenerator();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return g;
   }

   public KoboldVillageGenerator(String string) {
      this();
   }

   private KoboldVillageGenerator() {
      super("sexmod:generation");
      g = this;
      this.TribeBiomes
         .add(
            new KoboldVillageGenerator.TribeConfig(
               "ellie",
               new HashSet<>(Arrays.asList(Biomes.REDWOOD_TAIGA, Biomes.COLD_TAIGA, Biomes.TAIGA, Biomes.ROOFED_FOREST)),
               new Vec3i(30, 27, 26),
               9,
               true
            )
         );
      this.TribeBiomes.add(new KoboldVillageGenerator.TribeConfig("jenny", new HashSet<>(Arrays.asList(Biomes.PLAINS, Biomes.FOREST)), new Vec3i(9, 4, 9), 1, true));
      this.TribeBiomes
         .add(
            new KoboldVillageGenerator.TribeConfig(
               "ellie",
               new HashSet<>(Arrays.asList(Biomes.REDWOOD_TAIGA, Biomes.COLD_TAIGA, Biomes.TAIGA, Biomes.ROOFED_FOREST)),
               new Vec3i(30, 27, 26),
               9,
               true
            )
         );
      this.TribeBiomes.add(new KoboldVillageGenerator.TribeConfig("bia", new HashSet<>(Arrays.asList(Biomes.MUTATED_BIRCH_FOREST, Biomes.BIRCH_FOREST)), new Vec3i(11, 9, 15), 2, true));
      this.TribeBiomes.add(new KoboldVillageGenerator.TribeConfig("luna", new HashSet<>(Arrays.asList(Biomes.OCEAN, Biomes.DEEP_OCEAN)), new Vec3i(3, 7, 10), 0, false));
   }

   public void a() {
      this.QueuedTemplates.clear();
   }

   @SubscribeEvent
   public void a(Save save) {
      World world = save.getWorld();
      world.getMapStorage().setData("sexmod:generation", this);
      this.markDirty();
   }

   @SubscribeEvent
   public void a(Load load) {
      World world = load.getWorld();
      world.getMapStorage().getOrLoadData(KoboldVillageGenerator.class, "sexmod:generation");
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
      this.a();
      NBTTagCompound tagCompound2 = tagCompound.getCompoundTag("sexmod:generation");
      int i = 0;

      while (true) {
         String string = tagCompound2.getString("sexmod:name" + i);
         String string2 = tagCompound2.getString("sexmod:pos" + i);

         try {
            if ("".equals(string)) {
               break;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if ("".equals(string2)) {
               break;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         this.QueuedTemplates.add(new KoboldVillageGenerator.TribeTemplate(a(string2), string));
         i++;
      }
   }

   public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
      tagCompound.setTag("sexmod:generation", new NBTTagCompound());
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      int i = 0;

      for (KoboldVillageGenerator.TribeTemplate tribeTemplate : this.QueuedTemplates) {
         tagCompound2.setString("sexmod:name" + i, tribeTemplate.TemplateName);
         tagCompound2.setString("sexmod:pos" + i++, a(tribeTemplate.Offset));
      }

      tagCompound.setTag("sexmod:generation", tagCompound2);
      return tagCompound;
   }

   static String a(Vec2i vec2i) {
      return vec2i.X + "|" + vec2i.Y;
   }

   static Vec2i a(String string) {
      String[] stringArray = string.split("\\|");
      return new Vec2i(Integer.parseInt(stringArray[0]), Integer.parseInt(stringArray[1]));
   }

   public void generate(Random random, int i, int i2, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {
      try {
         if (!ShouldGenBuildings) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (world.getWorldType() == WorldType.FLAT) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      this.b(world, random, i, i2);
      this.a(world, random, i, i2);
      this.a(random, i, i2, world);
   }

   void a(Random random, int i, int i2, World world) {
      try {
         if (!c) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      c = false;

      for (KoboldVillageGenerator.TribeConfig tribeConfig : this.TribeBiomes) {
         this.a(tribeConfig, random, i, i2, world);
      }

      c = true;
   }


   void a(KoboldVillageGenerator.TribeConfig tribeConfig, Random random, int i, int i4, World world) {
        int i5;
        int i6;
        int i7;
        int i8;
        for (TribeTemplate tribeTemplate : this.QueuedTemplates) {
            int i9;
            try {
                i9 = tribeTemplate.a.equals(tribeConfig.TribeName) ? 156 : 62;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldVillageGenerator.rethrow(runtimeException);
            }
            i8 = i9;
            try {
                if (!(tribeTemplate.b.a(i, i4) < (float)i8)) continue;
                return;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldVillageGenerator.rethrow(runtimeException);
            }
        }
        int i10 = tribeConfig.CenterOffset.getX();
        int i11 = tribeConfig.CenterOffset.getZ();
        i8 = i * 16 + (16 - i10) / 2;
        int i12 = i4 * 16 + (16 - i11) / 2;
        Biome biome = world.provider.getBiomeForCoords(new BlockPos(i8, 80, i12));
        try {
            if (!tribeConfig.Biomes.contains(biome)) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldVillageGenerator.rethrow(runtimeException);
        }
        int i13 = Integer.MIN_VALUE;
        int i14 = Integer.MAX_VALUE;
        for (i7 = i8; i7 < i8 + i10; ++i7) {
            for (i6 = i12; i6 < i12 + i11; ++i6) {
                block29: {
                    i5 = BedLogic.countNearbyBeds(world, i7, i6);
                    try {
                        try {
                            if (!tribeConfig.Enabled || world.getBlockState(new BlockPos(i7, i5, i6)).getBlock() != Blocks.WATER) break block29;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldVillageGenerator.rethrow(runtimeException);
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldVillageGenerator.rethrow(runtimeException);
                    }
                }
                if (i5 > i13) {
                    i13 = i5;
                }
                if (i5 >= i14) continue;
                i14 = i5;
            }
        }
        try {
            if (i13 - i14 > tribeConfig.a) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldVillageGenerator.rethrow(runtimeException);
        }
        i7 = i13;
        try {
            this.QueuedTemplates.add(new TribeTemplate(new Vec2i(i, i4), tribeConfig.TribeName));
            tribeConfig.Generator.generate(world, random, new BlockPos(i8, i7, i12));
            if (!tribeConfig.Enabled) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldVillageGenerator.rethrow(runtimeException);
        }
        i6 = 1;
        i5 = i7 - 1;
        while (i6 != 0) {
            i6 = 0;
            Vec3i vec3i = new Vec3i(i10 + 2, 0, i11 + 2);
            --i12;
            for (int i15 = --i8; i15 < i8 + vec3i.getX(); ++i15) {
                for (int i16 = i12; i16 < i12 + vec3i.getZ(); ++i16) {
                    IBlockState iBlockState;
                    BlockPos blockPos = new BlockPos(i15, i5, i16);
                    IBlockState iBlockState2 = world.getBlockState(blockPos);
                    try {
                        if (!iBlockState2.getBlock().isPassable((IBlockAccess)world, blockPos)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldVillageGenerator.rethrow(runtimeException);
                    }
                    try {
                        iBlockState = world.canSeeSky(blockPos) ? Blocks.GRASS.getDefaultState() : Blocks.DIRT.getDefaultState();
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldVillageGenerator.rethrow(runtimeException);
                    }
                    iBlockState2 = iBlockState;
                    world.setBlockState(blockPos, iBlockState2);
                    i6 = 1;
                }
            }
            --i5;
        }
    }

   void b(World world, Random random, int i, int i2) {
      try {
         if (random.nextDouble() > 0.004F) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      int i3 = i * 16 + 8;
      int i4 = i2 * 16 + 8;
      int i5 = BedLogic.countNearbyBeds(world, i3, i4);

      try {
         if (world.getBlockState(new BlockPos(i3, i5, i4)).getMaterial().isLiquid()) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      GirlHomeBuilder.createTribe(world, new Vec3d(i3, i5, i4));
   }


   void a(World world, Random random, int i, int i5) {
        Vec3d vec3d;
        Material material;
        BlockPos blockPos;
        ArrayList<BlockPos> arrayList;
        BlockPos blockPos2;
        block49: {
            int i6 = 16 * i + 3;
            int i7 = 16 * i5 + 3;
            int i8 = random.nextInt(255);
            blockPos2 = new BlockPos(i6, i8, i7);
            arrayList = new ArrayList<BlockPos>();
            for (int i9 = 0; i9 <= GoblinNpc.SeatSearchBox.getX(); ++i9) {
                for (int i10 = -1; i10 <= GoblinNpc.SeatSearchBox.getY(); ++i10) {
                    for (int i11 = 0; i11 <= GoblinNpc.SeatSearchBox.getZ(); ++i11) {
                        block46: {
                            blockPos = blockPos2.add(i9, i10, i11);
                            material = world.getBlockState(blockPos).getMaterial();
                            boolean flag = material.isSolid();
                            try {
                                block47: {
                                    try {
                                        try {
                                            if (flag) break block46;
                                            if (i10 == -1) break block47;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw KoboldVillageGenerator.rethrow(runtimeException);
                                        }
                                        if (i10 != GoblinNpc.SeatSearchBox.getY()) break block46;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw KoboldVillageGenerator.rethrow(runtimeException);
                                    }
                                }
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw KoboldVillageGenerator.rethrow(runtimeException);
                            }
                        }
                        try {
                            try {
                                try {
                                    try {
                                        block48: {
                                            try {
                                                try {
                                                    try {
                                                        if (i9 == 0 || i9 == GoblinNpc.SeatSearchBox.getX()) break block48;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw KoboldVillageGenerator.rethrow(runtimeException);
                                                    }
                                                    if (i11 == 0) break block48;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw KoboldVillageGenerator.rethrow(runtimeException);
                                                }
                                                if (i11 != GoblinNpc.SeatSearchBox.getZ()) continue;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw KoboldVillageGenerator.rethrow(runtimeException);
                                            }
                                        }
                                        if (i10 != 0) continue;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw KoboldVillageGenerator.rethrow(runtimeException);
                                    }
                                    if (!world.isAirBlock(blockPos)) continue;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw KoboldVillageGenerator.rethrow(runtimeException);
                                }
                                if (!world.isAirBlock(blockPos.up())) continue;
                            }
                            catch (RuntimeException runtimeException) {
                                throw KoboldVillageGenerator.rethrow(runtimeException);
                            }
                            arrayList.add(blockPos);
                            continue;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldVillageGenerator.rethrow(runtimeException);
                        }
                    }
                }
            }
            try {
                try {
                    if (arrayList.size() != 0 && arrayList.size() <= 4) break block49;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldVillageGenerator.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldVillageGenerator.rethrow(runtimeException);
            }
        }
        BlockPos blockPos3 = null;
        Rotation rotation = arrayList.iterator();
        while (rotation.hasNext()) {
            BlockPos blockPos4;
            blockPos = blockPos4 = (BlockPos)rotation.next();
            material = blockPos2.add(6, 0, 6);
            blockPos = blockPos.subtract((Vec3i)material);
            try {
                if (Math.abs(blockPos.getX()) == Math.abs(blockPos.getZ())) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldVillageGenerator.rethrow(runtimeException);
            }
            try {
                if (Math.abs(blockPos.getX()) == Math.abs(blockPos.getZ()) - 1) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldVillageGenerator.rethrow(runtimeException);
            }
            try {
                if (Math.abs(blockPos.getX()) - 1 == Math.abs(blockPos.getZ())) {
                    continue;
                }
            }
            catch (RuntimeException runtimeException) {
                throw KoboldVillageGenerator.rethrow(runtimeException);
            }
            blockPos3 = blockPos;
            break;
        }
        try {
            if (blockPos3 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldVillageGenerator.rethrow(runtimeException);
        }
        blockPos = new Vec3i(0, 0, 0);
        float f = 0.0f;
        if (blockPos3.getZ() == -6) {
            rotation = Rotation.NONE;
            vec3d = GoblinNpc.GuardSeat180;
            f = 180.0f;
        } else if (blockPos3.getX() == 5) {
            rotation = Rotation.CLOCKWISE_90;
            vec3d = GoblinNpc.GuardSeat270;
            blockPos = new Vec3i(GoblinNpc.SeatSearchBox.getX() - 1, 0, 0);
            f = -90.0f;
        } else if (blockPos3.getZ() == 5) {
            rotation = Rotation.CLOCKWISE_180;
            vec3d = GoblinNpc.GuardSeat0;
            blockPos = new Vec3i(GoblinNpc.SeatSearchBox.getX() - 1, 0, GoblinNpc.SeatSearchBox.getZ() - 1);
        } else {
            rotation = Rotation.COUNTERCLOCKWISE_90;
            vec3d = GoblinNpc.GuardSeat90;
            blockPos = new Vec3i(0, 0, GoblinNpc.SeatSearchBox.getZ() - 1);
            f = 90.0f;
        }
        new StructureGenerator("goblin").a(world, blockPos2.add(0, -1, 0).add((Vec3i)blockPos), rotation);
        vec3d.add((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
        vec3d = new Vec3d((double)blockPos2.getX() + vec3d.x + 0.5, (double)blockPos2.getY() + vec3d.y, (double)blockPos2.getZ() + vec3d.z + 0.5);
        GoblinNpc goblin = new GoblinNpc(world, true, f, vec3d);
        goblin.forceSpawn = true;
        world.spawnEntity((Entity)goblin);
        world.getChunk(i, i5).markDirty();
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   static class TribeTemplate {
      Vec2i Offset;
      String TemplateName;

      public TribeTemplate(Vec2i vec2i, String string) {
         this.Offset = vec2i;
         this.TemplateName = string;
      }
   }

   static class TribeConfig {
      public final String TribeName;
      public final StructureGenerator Generator;
      public final HashSet<Biome> Biomes;
      public final Vec3i CenterOffset;
      public final boolean Enabled;
      public final int a;

      public TribeConfig(String string, HashSet<Biome> set, Vec3i vec3i, int i, boolean flag) {
         this.TribeName = string;
         this.Biomes = set;
         this.CenterOffset = vec3i;
         this.Enabled = flag;
         this.a = i;
         this.Generator = new StructureGenerator(string);
      }
   }
}
