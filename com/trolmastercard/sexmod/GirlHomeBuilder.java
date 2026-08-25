package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockChest.Type;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Map;
import java.util.Random;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GirlHomeBuilder {
   static final int HomeRadius = 4;
   private static final HashMap<UUID, GirlHomeBuilder.HomeData> GirlHomes = new HashMap<>();
   static final Vec3d[] BedOffsets = new Vec3d[]{
      new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.5, 0.0, 0.0), new Vec3d(-0.5, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.5), new Vec3d(0.0, 0.0, -0.5)
   };
   static HashMap<KoboldNpc, BlockPos[]> KoboldHomes = new HashMap<>();

   public static void clearAll() {
      GirlHomes.clear();
      KoboldHomes.clear();
   }

   public static void createTribe(World world, Vec3d vec3d) {
      UUID uuid = UUID.randomUUID();
      float[] floats = new float[4];
      floats[0] = 0.25F;
      int i = 1;

      try {
         while (i < floats.length) {
            floats[i] = KoboldNpc.getRandomBodySize();
            i++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      ArrayList<KoboldNpc> list = new ArrayList<KoboldNpc>();

      for (float f : floats) {
         KoboldNpc kobold = KoboldNpc.create(world, uuid, f);
         list.add(kobold);
      }

      EyeAndKoboldColor eyeColor = EyeAndKoboldColor.values()[ModConstants.Random.nextInt(EyeAndKoboldColor.values().length)];
      GirlHomeBuilder.HomeData homeData = new GirlHomeBuilder.HomeData(uuid, eyeColor, (KoboldNpc)list.get(0), list);
      GirlHomes.put(uuid, homeData);
      int i2 = 0;

      for (KoboldNpc kobold2 : list) {
         kobold2.setPosition(vec3d.x + BedOffsets[i2].x, vec3d.y, vec3d.z + BedOffsets[i2].z);
         world.spawnEntity(kobold2);
         i2++;
      }
   }

   public static boolean hasHomeData(UUID uuid) {
      try {
         if (GirlHomes.get(uuid) != null) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   public static void setOwnerUuid(UUID uuid, UUID uuid2) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.setOwnerUuid(uuid2);
   }

   public static void registerTribe(UUID uuid, EyeAndKoboldColor eyeColor) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData != null) {
            System.out.println("tribe of UUID " + uuid.toString() + " does already exist lol");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GirlHomes.put(uuid, new GirlHomeBuilder.HomeData(uuid, eyeColor));
   }

   public static boolean isBedBlock(BlockPos pos) {
      for (Entry entry : KoboldHomes.entrySet()) {
         BlockPos[] posArray = (BlockPos[])entry.getValue();

         try {
            if (posArray[0].equals(pos)) {
               return true;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (posArray[1].equals(pos)) {
               return true;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }
      }

      return false;
   }

   public static BlockPos[] getBedHomePositions(KoboldNpc kobold) {
      return KoboldHomes.get(kobold);
   }

   public static void setBedHomePosition(KoboldNpc kobold, BlockPos pos) {
      World world2 = kobold.world;
      BlockPos pos2 = null;
      if (world2.getBlockState(pos.north()).getBlock() instanceof BlockBed) {
         pos2 = pos.north();
      }

      if (world2.getBlockState(pos.east()).getBlock() instanceof BlockBed) {
         pos2 = pos.east();
      }

      if (world2.getBlockState(pos.south()).getBlock() instanceof BlockBed) {
         pos2 = pos.south();
      }

      if (world2.getBlockState(pos.west()).getBlock() instanceof BlockBed) {
         pos2 = pos.west();
      }

      try {
         if (pos2 == null) {
            System.out.println("bed @" + pos.toString() + " apparently doesn't have another half.. wtf");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      KoboldHomes.put(kobold, new BlockPos[]{pos, pos2});
   }

   public static void clearBedHomePosition(KoboldNpc kobold) {
      KoboldHomes.remove(kobold);
   }

   public static void setKoboldEntity(UUID uuid, KoboldNpc kobold) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.KoboldEntity = kobold;
   }

   public static void registerKoboldInTribe(UUID uuid, KoboldNpc kobold) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         homeData.addKobold(kobold);
         GirlHomes.replace(uuid, homeData);
         kobold.getDataManager().set(KoboldNpc.BoundPlayerUuidKey, Optional.of(uuid));
         if (!kobold.aA) {
            kobold.getDataManager().set(KoboldNpc.TribeColorKey, homeData.TribeColor.toString());
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }


   public static void updateKoboldEntity(UUID uuid) {
        block7: {
            HomeData homeData = GirlHomes.get(uuid);
            try {
                if (homeData == null) {
                    System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlHomeBuilder.rethrow(runtimeException);
            }
            KoboldNpc kobold = homeData.KoboldEntity;
            try {
                try {
                    if (kobold != null && !kobold.isDead) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlHomeBuilder.rethrow(runtimeException);
                }
                homeData.KoboldEntity = homeData.getNearestKobold();
            }
            catch (RuntimeException runtimeException) {
                throw GirlHomeBuilder.rethrow(runtimeException);
            }
        }
    }

   public static void removeKobold(UUID uuid, KoboldNpc kobold) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      label76: {
         try {
            homeData.removeKobold(kobold);
            homeData.removeHome(kobold.getGirlUuid());
            if (homeData.KoboldEntity == null || homeData.KoboldEntity.getEntityId() != kobold.getEntityId()) {
               break label76;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         KoboldNpc kobold2 = homeData.getNearestKobold();

         try {
            if (kobold2 != null) {
               homeData.KoboldEntity = kobold2;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }
      }

      for (TreeCluster treeCluster : homeData.AnchorEntities) {
         treeCluster.unassignKobold(kobold);
      }

      try {
         if (!homeData.Kobolds.isEmpty()) {
            GirlHomes.replace(uuid, homeData);
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if (!kobold.J()) {
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      EntityPlayer player = kobold.getSexPlayer();
      if (player != null) {
         HashSet set = new HashSet();
         set.addAll(homeData.NearbyPositions);
         set.addAll(homeData.SpawnPositions);

         for (TreeCluster treeCluster2 : homeData.AnchorEntities) {
            set.addAll(treeCluster2.LogPositions);
         }

         NetworkHandler.channel.sendTo(new PacketSendBlocks(set, false), (EntityPlayerMP)player);
         player.sendMessage(
            new TextComponentString(
               String.format("ur %stribe %shas been %seradicated %suwu", TextFormatting.RED, TextFormatting.WHITE, TextFormatting.RED, TextFormatting.WHITE)
            )
         );
      }
   }

   @Nullable
   public static KoboldNpc getKoboldEntity(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.KoboldEntity;
   }

   public static boolean isKoboldOfGirl(UUID uuid, KoboldNpc kobold) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (homeData.KoboldEntity == null) {
            return false;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (homeData.KoboldEntity.getEntityId() == kobold.getEntityId()) {
            return true;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      return false;
   }

   public static EyeAndKoboldColor getTribeColor(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return KoboldNpc.DefaultTribeColor;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.TribeColor;
   }

   public static HashSet<BlockPos> getSpawnPositions(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return new HashSet<>();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.SpawnPositions;
   }

   public static void addSpawnPosition(UUID uuid, BlockPos pos) {
      try {
         if (pos == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      homeData.SpawnPositions.add(pos);
   }

   public static void removeSpawnPosition(UUID uuid, BlockPos pos) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.SpawnPositions.remove(pos);
   }

   public static HashSet<BlockPos> getNearbyPositions(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.NearbyPositions;
   }

   public static void addNearbyPosition(UUID uuid, BlockPos pos) {
      try {
         if (pos == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      homeData.NearbyPositions.add(pos);
   }

   public static void removeNearbyPosition(UUID uuid, BlockPos pos) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.NearbyPositions.remove(pos);
   }

   public static HashSet<BlockPos> removeAnchorAndGetSpawnPositions(UUID uuid, TreeCluster treeCluster) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return new HashSet<>();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (treeCluster != null) {
            homeData.removeAnchor(treeCluster);
            return treeCluster.LogPositions;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return new HashSet<>();
   }

   public static HashSet<BlockPos> findAndRemoveAnchorAt(UUID uuid, BlockPos pos) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return new HashSet<>();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      TreeCluster treeCluster = null;

      for (TreeCluster treeCluster2 : homeData.AnchorEntities) {
         if (treeCluster2.LogPositions.contains(pos)) {
            treeCluster = treeCluster2;
            break;
         }
      }

      return removeAnchorAndGetSpawnPositions(uuid, treeCluster);
   }

   public static void addAnchor(UUID uuid, TreeCluster treeCluster) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.addAnchor(treeCluster);
   }

   public static void removeWorkerAnchor(UUID uuid, KoboldNpc kobold) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      TreeCluster treeCluster = null;

      for (TreeCluster treeCluster2 : homeData.AnchorEntities) {
         if (treeCluster2.isAssigned(kobold)) {
            treeCluster = treeCluster2;
         }
      }

      try {
         if (treeCluster == null) {
            System.out.println("task of worker " + kobold.getGirlUuid() + " not found uwu");
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      homeData.removeAnchor(treeCluster);
   }

   @Nullable
   public static Collection<TreeCluster> getTreeClusters(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.AnchorEntities;
   }

   public static ActivityState getActivityState(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return ActivityState.REST;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.getActivityState();
   }

   public static void a(UUID uuid, ActivityState activityState) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.setActivityState(activityState);
   }

   public static int getKoboldCount(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return 0;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.getOccupiedCount();
   }

   public static List<KoboldNpc> getKobolds(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return new ArrayList<>();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.Kobolds;
   }

   public static void setHomePos(UUID uuid, BlockPos pos) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.setHomePos(pos);
   }

   @Nullable
   public static BlockPos getHomePos(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.getHomePos();
   }

   public static HashSet<EntityLivingBase> getNearbyEntities(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return new HashSet<>();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.getNearbyEntities();
   }

   public static void addNearbyEntity(UUID uuid, EntityLivingBase livingBase) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.addNearbyEntity(livingBase);
   }

   public static void removeNearbyEntity(UUID uuid, EntityLivingBase livingBase) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.removeNearbyEntity(livingBase);
   }

   public static boolean hasKobold(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      for (KoboldNpc kobold : homeData.Kobolds) {
         try {
            if (kobold.getSexPlayerUuid() != null) {
               return true;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }
      }

      return false;
   }

   public static boolean hasTribe(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return homeData.HasTribe;
   }

   public static void setHasTribe(UUID uuid, boolean flag) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.HasTribe = flag;
   }

   @Nullable

   public static UUID findTribeUuid(UUID uuid) {
        try {
            if (uuid == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GirlHomeBuilder.rethrow(runtimeException);
        }
        for (Map.Entry<UUID, GirlHomeBuilder.HomeData> entry : GirlHomes.entrySet()) {
            HomeData homeData = entry.getValue();
            try {
                try {
                    if (homeData.Kobolds.size() == 0 && homeData.HomeMap.size() == 0) {
                        continue;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GirlHomeBuilder.rethrow(runtimeException);
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlHomeBuilder.rethrow(runtimeException);
            }
            try {
                if (!uuid.equals(entry.getValue().getOwnerUuid())) continue;
                return entry.getKey();
            }
            catch (RuntimeException runtimeException) {
                throw GirlHomeBuilder.rethrow(runtimeException);
            }
        }
        return null;
    }

   @Nullable
   public static UUID getTribeMasterUuid(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      List list = homeData.Kobolds;

      try {
         if (list.isEmpty()) {
            return null;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      KoboldNpc kobold = (KoboldNpc)list.get(0);

      try {
         if (!kobold.J()) {
            return null;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      String string = (String)((KoboldNpc)list.get(0)).getDataManager().get(GirlEntity.MasterUuidKey);
      return UUID.fromString(string);
   }

   public static HashSet<BlockPos> getTribeAreaPositions(UUID uuid) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);
      HashSet set = new HashSet();

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return set;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      for (TreeCluster treeCluster : homeData.AnchorEntities) {
         set.addAll(treeCluster.LogPositions);
      }

      set.addAll(homeData.NearbyPositions);
      set.addAll(homeData.SpawnPositions);
      return set;
   }

   public static HashMap<UUID, BlockPos> getLoadedHomes(UUID uuid, World world) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return new HashMap<>();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      HashMap<UUID, BlockPos> map = homeData.HomeMap;
      ArrayList<UUID> list = new ArrayList<UUID>();

      for (Entry entry : map.entrySet()) {
         BlockPos pos = (BlockPos)entry.getValue();
         UUID uuid2 = (UUID)entry.getKey();

         try {
            if (!world.isAreaLoaded(pos, 5)) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         AxisAlignedBB bbox = new AxisAlignedBB(pos.subtract(new Vec3i(-3, -3, -3)), pos.add(3, 3, 3));
         List list2 = world.getEntitiesWithinAABB(KoboldNpc.class, bbox);
         boolean flag = false;
         Iterator iterator2 = list2.iterator();

         while (true) {
            if (iterator2.hasNext()) {
               KoboldNpc kobold = (KoboldNpc)iterator2.next();
               if (!uuid2.equals(kobold.getGirlUuid())) {
                  continue;
               }

               flag = true;
            }

            try {
               if (!flag) {
                  list.add(uuid2);
               }
               break;
            } catch (RuntimeException error3) {
               throw rethrow(error3);
            }
         }
      }

      homeData.HomeMap = map;
      return map;
   }

   public static void putHome(UUID uuid, UUID uuid2, BlockPos pos) {
      GirlHomeBuilder.HomeData homeData = GirlHomes.get(uuid);

      try {
         if (homeData == null) {
            System.out.println("tribe of UUID " + uuid.toString() + " not found uwu");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      homeData.putHome(uuid2, pos);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class HomeData {
      UUID GirlUuid;
      UUID OwnerUuid;
      KoboldNpc KoboldEntity;
      List<KoboldNpc> Kobolds;
      EyeAndKoboldColor TribeColor;
      ActivityState State = ActivityState.REST;
      BlockPos HomePos = null;
      Collection<TreeCluster> AnchorEntities = new ArrayList<>();
      HashSet<EntityLivingBase> NearbyEntities = new HashSet<>();
      HashSet<BlockPos> NearbyPositions = new HashSet<>();
      HashSet<BlockPos> SpawnPositions = new HashSet<>();
      HashMap<UUID, BlockPos> HomeMap = new HashMap<>();
      boolean HasTribe = false;

      public HomeData(UUID uuid, EyeAndKoboldColor eyeColor, KoboldNpc kobold, List<KoboldNpc> list) {
         this.GirlUuid = uuid;
         this.TribeColor = eyeColor;
         this.KoboldEntity = kobold;
         this.Kobolds = list;
      }

      public HomeData(UUID uuid, EyeAndKoboldColor eyeColor) {
         this.GirlUuid = uuid;
         this.TribeColor = eyeColor;
         this.Kobolds = new ArrayList<>();
      }

      public void setOwnerUuid(UUID uuid) {
         this.OwnerUuid = uuid;
      }

      public UUID getOwnerUuid() {
         return this.OwnerUuid;
      }


      public void removeAnchor(TreeCluster treeCluster) {
            block11: {
                try {
                    if (!this.AnchorEntities.contains(treeCluster)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GirlHomeBuilder.HomeData.rethrow(runtimeException);
                }
                for (KoboldNpc kobold : treeCluster.Assignees) {
                    kobold.setCurrentAction(GirlAnimationState.NULL);
                    kobold.setNoGravity(false);
                    kobold.noClip = false;
                    kobold.getDataManager().set(GirlEntity.BusyKey, false);
                }
                try {
                    try {
                        this.AnchorEntities.remove(treeCluster);
                        if (!treeCluster.LogPositions.isEmpty() && this.OwnerUuid != null) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlHomeBuilder.HomeData.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlHomeBuilder.HomeData.rethrow(runtimeException);
                }
            }
            EntityPlayerMP entityPlayerMP = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(this.OwnerUuid);
            try {
                if (entityPlayerMP == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlHomeBuilder.HomeData.rethrow(runtimeException);
            }
            NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(treeCluster.LogPositions, false), entityPlayerMP);
        }

      public HashMap<UUID, BlockPos> getHomeMap() {
         return this.HomeMap;
      }

      public void putHome(UUID uuid, BlockPos pos) {
         this.HomeMap.put(uuid, pos);
      }

      public void removeHome(UUID uuid) {
         this.HomeMap.remove(uuid);
      }

      public void removeNearbyEntity(EntityLivingBase livingBase) {
         this.NearbyEntities.remove(livingBase);
      }

      public void addNearbyEntity(EntityLivingBase livingBase) {
         this.NearbyEntities.add(livingBase);
      }

      public HashSet<EntityLivingBase> getNearbyEntities() {
         return this.NearbyEntities;
      }

      public int getOccupiedCount() {
         HashSet set = new HashSet();

         for (KoboldNpc kobold : this.Kobolds) {
            set.add(kobold.getGirlUuid());
         }

         for (Entry entry : this.HomeMap.entrySet()) {
            set.add(entry.getKey());
         }

         return set.size();
      }

      public BlockPos getHomePos() {
         return this.HomePos;
      }

      public void setHomePos(BlockPos pos) {
         this.HomePos = pos;
      }

      public void addAnchor(TreeCluster treeCluster) {
         this.AnchorEntities.add(treeCluster);
      }

      public ActivityState getActivityState() {
         return this.State;
      }

      public void setActivityState(ActivityState activityState) {
         this.State = activityState;
      }

      public void addKobold(KoboldNpc kobold) {
         try {
            if (this.Kobolds.contains(kobold)) {
               return;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         UUID uuid = kobold.getGirlUuid();
         ArrayList<KoboldNpc> list = new ArrayList<KoboldNpc>();

         for (KoboldNpc kobold2 : this.Kobolds) {
            try {
               if (kobold2.getGirlUuid().equals(uuid)) {
                  list.add(kobold2);
               }
            } catch (RuntimeException error2) {
               throw rethrow(error2);
            }
         }

         for (KoboldNpc kobold3 : list) {
            Main.LOGGER.warn(String.format("Removed old entry of kobold called %s with UUID %s owned by %s", kobold3.getDisplayName(), kobold3.getGirlUuid(), this.OwnerUuid));
            this.removeKobold(kobold3);
         }

         this.Kobolds.add(kobold);
      }

      public void removeKobold(KoboldNpc kobold) {
         this.Kobolds.remove(kobold);
      }

      KoboldNpc getNearestKobold() {
         KoboldNpc kobold = null;

         for (KoboldNpc kobold2 : this.Kobolds) {
            try {
               if (kobold2.isDead) {
                  continue;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            if (kobold == null) {
               kobold = kobold2;
            } else {
               float f = (Float)kobold.getDataManager().get(KoboldNpc.BodySizeKey);
               float f2 = (Float)kobold2.getDataManager().get(KoboldNpc.BodySizeKey);
               if (f2 < f) {
                  kobold = kobold2;
               }
            }
         }

         return kobold;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }

   public static class TribeWorldData extends WorldSavedData {
      public TribeWorldData(String string) {
         super(string);
      }

      @SubscribeEvent
      public void a(Save save) {
         World world = save.getWorld();
         world.getMapStorage().setData("tribes", this);
         this.markDirty();
      }

      @SubscribeEvent
      public void a(Load load) {
         World world = load.getWorld();
         world.getMapStorage().getOrLoadData(GirlHomeBuilder.TribeWorldData.class, "tribes");
      }

      @SubscribeEvent
      public void a(PlayerSleepInBedEvent playerSleepInBedEvent) {
         try {
            if (GirlHomeBuilder.isBedBlock(playerSleepInBedEvent.getPos())) {
               playerSleepInBedEvent.setResult(SleepResult.OTHER_PROBLEM);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }

      @SubscribeEvent
      public void a(PlaceEvent placeEvent) {
         BlockPos pos = placeEvent.getPos();
         IBlockState state = placeEvent.getState();
         World world = placeEvent.getWorld();

         try {
            if (world.isRemote) {
               return;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (!(state.getBlock() instanceof BlockChest)) {
               return;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         Type type = ((BlockChest)world.getBlockState(pos).getBlock()).chestType;
         BlockPos pos2 = null;

         label115: {
            try {
               if (!(world.getBlockState(pos.north()).getBlock() instanceof BlockChest)
                  || !type.equals(((BlockChest)world.getBlockState(pos.north()).getBlock()).chestType)) {
                  break label115;
               }
            } catch (RuntimeException error3) {
               throw rethrow(error3);
            }

            pos2 = pos.north();
         }

         label108: {
            try {
               if (!(world.getBlockState(pos.east()).getBlock() instanceof BlockChest)
                  || !type.equals(((BlockChest)world.getBlockState(pos.east()).getBlock()).chestType)) {
                  break label108;
               }
            } catch (RuntimeException error4) {
               throw rethrow(error4);
            }

            pos2 = pos.east();
         }

         label101: {
            try {
               if (!(world.getBlockState(pos.south()).getBlock() instanceof BlockChest)
                  || !type.equals(((BlockChest)world.getBlockState(pos.south()).getBlock()).chestType)) {
                  break label101;
               }
            } catch (RuntimeException error5) {
               throw rethrow(error5);
            }

            pos2 = pos.south();
         }

         label94: {
            try {
               if (!(world.getBlockState(pos.west()).getBlock() instanceof BlockChest)
                  || !type.equals(((BlockChest)world.getBlockState(pos.west()).getBlock()).chestType)) {
                  break label94;
               }
            } catch (RuntimeException error6) {
               throw rethrow(error6);
            }

            pos2 = pos.west();
         }

         try {
            if (pos2 == null) {
               return;
            }
         } catch (RuntimeException error7) {
            throw rethrow(error7);
         }

         for (Entry entry : GirlHomeBuilder.GirlHomes.entrySet()) {
            GirlHomeBuilder.HomeData homeData = (GirlHomeBuilder.HomeData)entry.getValue();

            try {
               if (!homeData.NearbyPositions.contains(pos2)) {
                  continue;
               }
            } catch (RuntimeException error8) {
               throw rethrow(error8);
            }

            homeData.NearbyPositions.add(pos);
            UUID uuid = GirlHomeBuilder.getTribeMasterUuid((UUID)entry.getKey());

            try {
               if (uuid == null) {
                  continue;
               }
            } catch (RuntimeException error9) {
               throw rethrow(error9);
            }

            EntityPlayerMP serverPlayer = (EntityPlayerMP)world.getPlayerEntityByUUID(uuid);

            try {
               if (serverPlayer == null) {
                  continue;
               }
            } catch (RuntimeException error10) {
               throw rethrow(error10);
            }

            NetworkHandler.channel.sendTo(new PacketSendBlocks(pos, true), serverPlayer);
         }
      }

      @SubscribeEvent
      public void a(EntityJoinWorldEvent entityJoinWorldEvent) {
         Entity entity = entityJoinWorldEvent.getEntity();
         if (entity instanceof EntityZombie) {
            EntityZombie entityZombie = (EntityZombie)entity;
            entityZombie.targetTasks.addTask(3, new KoboldTargetAi(entityZombie, true, false));
         }

         if (entity instanceof AbstractSkeleton) {
            AbstractSkeleton abstractSkeleton = (AbstractSkeleton)entity;
            abstractSkeleton.targetTasks.addTask(3, new KoboldTargetAi(abstractSkeleton, true, false));
         }

         if (entity instanceof EntitySpider) {
            EntitySpider entitySpider = (EntitySpider)entity;
            entitySpider.targetTasks.addTask(3, new KoboldTargetAi(entitySpider, true, true));
         }
      }

      @SubscribeEvent
      public void a(BreakEvent breakEvent) {
         BlockPos pos = breakEvent.getPos();
         World world = breakEvent.getWorld();

         try {
            if (world.isRemote) {
               return;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         IBlockState state = world.getBlockState(pos);
         Block block = state.getBlock();
         if (block instanceof BlockChest) {
            for (Entry entry : GirlHomeBuilder.GirlHomes.entrySet()) {
               GirlHomeBuilder.HomeData homeData = (GirlHomeBuilder.HomeData)entry.getValue();

               try {
                  if (!homeData.NearbyPositions.contains(pos)) {
                     continue;
                  }
               } catch (RuntimeException error2) {
                  throw rethrow(error2);
               }

               homeData.NearbyPositions.remove(pos);
               UUID uuid = GirlHomeBuilder.getTribeMasterUuid((UUID)entry.getKey());

               try {
                  if (uuid == null) {
                     continue;
                  }
               } catch (RuntimeException error3) {
                  throw rethrow(error3);
               }

               EntityPlayerMP serverPlayer = (EntityPlayerMP)world.getPlayerEntityByUUID(uuid);

               try {
                  if (serverPlayer == null) {
                     continue;
                  }
               } catch (RuntimeException error4) {
                  throw rethrow(error4);
               }

               NetworkHandler.channel.sendTo(new PacketSendBlocks(pos, false), serverPlayer);
            }
         }

         if (block instanceof BlockBed) {
            for (Entry entry2 : GirlHomeBuilder.GirlHomes.entrySet()) {
               GirlHomeBuilder.HomeData homeData2 = (GirlHomeBuilder.HomeData)entry2.getValue();

               try {
                  if (!homeData2.SpawnPositions.contains(pos)) {
                     continue;
                  }
               } catch (RuntimeException error5) {
                  throw rethrow(error5);
               }

               BlockPos pos2 = BedLogic.getBedPosForState(pos, state);
               homeData2.SpawnPositions.remove(pos);
               homeData2.SpawnPositions.remove(pos2);
               UUID uuid2 = GirlHomeBuilder.getTribeMasterUuid((UUID)entry2.getKey());

               try {
                  if (uuid2 == null) {
                     continue;
                  }
               } catch (RuntimeException error6) {
                  throw rethrow(error6);
               }

               EntityPlayerMP serverPlayer2 = (EntityPlayerMP)world.getPlayerEntityByUUID(uuid2);

               try {
                  if (serverPlayer2 == null) {
                     continue;
                  }
               } catch (RuntimeException error7) {
                  throw rethrow(error7);
               }

               HashSet set = new HashSet();
               set.add(pos);
               set.add(pos2);
               NetworkHandler.channel.sendTo(new PacketSendBlocks(set, false), serverPlayer2);
            }
         }
      }

      String a(String string, NBTTagCompound tagCompound) {
         String string2 = tagCompound.getString(string);
         tagCompound.setString(string, "");
         return string2;
      }

      public void readFromNBT(NBTTagCompound tagCompound) {
         int i = 0;

         while (true) {
            String string = this.a("tribeId" + i, tagCompound);

            try {
               if ("".equals(string)) {
                  return;
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            UUID uuid = UUID.fromString(string);
            EyeAndKoboldColor eyeColor = EyeAndKoboldColor.valueOf(this.a("tribeColor" + i, tagCompound));
            GirlHomeBuilder.registerTribe(uuid, eyeColor);
            String string2 = this.a("tribeMaster" + i, tagCompound);

            try {
               if (!"".equals(string2)) {
                  GirlHomeBuilder.setOwnerUuid(uuid, UUID.fromString(string2));
               }
            } catch (RuntimeException error2) {
               throw rethrow(error2);
            }

            int i2 = 0;

            while (true) {
               String string3 = this.a(uuid.toString() + "member" + i2 + "pos", tagCompound);

               try {
                  if ("".equals(string3)) {
                     break;
                  }
               } catch (RuntimeException error3) {
                  throw rethrow(error3);
               }

               String string4 = this.a(uuid.toString() + "member" + i2 + "id", tagCompound);

               try {
                  if ("".equals(string4)) {
                     break;
                  }
               } catch (RuntimeException error4) {
                  throw rethrow(error4);
               }

               String[] stringArray = string3.split("\\|");
               BlockPos pos = new BlockPos(Integer.parseInt(stringArray[0]), Integer.parseInt(stringArray[1]), Integer.parseInt(stringArray[2]));
               UUID uuid2 = UUID.fromString(string4);
               GirlHomeBuilder.putHome(uuid, uuid2, pos);
               i2++;
            }

            int i3 = 0;

            while (true) {
               String string5 = this.a(uuid.toString() + "bed" + i3, tagCompound);

               try {
                  if ("".equals(string5)) {
                     break;
                  }
               } catch (RuntimeException error5) {
                  throw rethrow(error5);
               }

               String[] stringArray2 = string5.split("\\|");
               BlockPos pos2 = new BlockPos(Integer.parseInt(stringArray2[0]), Integer.parseInt(stringArray2[1]), Integer.parseInt(stringArray2[2]));
               GirlHomeBuilder.addSpawnPosition(uuid, pos2);
               i3++;
            }

            int i4 = 0;

            while (true) {
               String string6 = this.a(uuid.toString() + "chest" + i4, tagCompound);

               try {
                  if ("".equals(string6)) {
                     break;
                  }
               } catch (RuntimeException error6) {
                  throw rethrow(error6);
               }

               String[] stringArray3 = string6.split("\\|");
               BlockPos pos3 = new BlockPos(Integer.parseInt(stringArray3[0]), Integer.parseInt(stringArray3[1]), Integer.parseInt(stringArray3[2]));
               GirlHomeBuilder.addNearbyPosition(uuid, pos3);
               i4++;
            }

            int i5 = 0;

            label82:
            while (true) {
               String string7 = this.a(uuid.toString() + i5 + "taskKind", tagCompound);

               label132: {
                  try {
                     if ("".equals(string7)) {
                        break label132;
                     }
                  } catch (RuntimeException error7) {
                     throw rethrow(error7);
                  }

                  String string8 = this.a(uuid.toString() + i5 + "facing", tagCompound);
                  EnumFacing facing = EnumFacing.NORTH;
                  if (!"".equals(string8)) {
                     facing = EnumFacing.byName(string8);
                  }

                  String string9 = this.a(uuid.toString() + i5 + "pos", tagCompound);
                  String[] stringArray4 = string9.split("\\|");
                  BlockPos pos4 = new BlockPos(Integer.parseInt(stringArray4[0]), Integer.parseInt(stringArray4[1]), Integer.parseInt(stringArray4[2]));
                  HashSet set = new HashSet();
                  int i6 = 0;

                  while (true) {
                     String string10 = this.a(uuid.toString() + i5 + "block" + i6, tagCompound);

                     label70: {
                        try {
                           if (!"".equals(string10)) {
                              break label70;
                           }
                        } catch (RuntimeException error8) {
                           throw rethrow(error8);
                        }

                        GirlHomeBuilder.addAnchor(uuid, new TreeCluster(pos4, TreeCluster.KoboldTask.valueOf(string7), set, facing));
                        i5++;
                        continue label82;
                     }

                     String[] stringArray5 = string10.split("\\|");
                     BlockPos pos5 = new BlockPos(Integer.parseInt(stringArray5[0]), Integer.parseInt(stringArray5[1]), Integer.parseInt(stringArray5[2]));
                     set.add(pos5);
                     i6++;
                  }
               }

               i++;
               break;
            }
         }
      }

      public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
         int i = 0;

         for (Entry entry : GirlHomeBuilder.GirlHomes.entrySet()) {
            GirlHomeBuilder.HomeData homeData = (GirlHomeBuilder.HomeData)entry.getValue();
            UUID uuid = (UUID)entry.getKey();
            UUID uuid2 = homeData.getOwnerUuid();

            try {
               tagCompound.setString("tribeId" + i, uuid.toString());
               tagCompound.setString("tribeColor" + i, homeData.TribeColor.toString());
               if (uuid2 != null) {
                  tagCompound.setString("tribeMaster" + i, uuid2.toString());
               }
            } catch (RuntimeException error) {
               throw rethrow(error);
            }

            int i2 = 0;
            HashSet set = new HashSet();

            for (KoboldNpc kobold : homeData.Kobolds) {
               try {
                  if (kobold.isDead) {
                     continue;
                  }
               } catch (RuntimeException error2) {
                  throw rethrow(error2);
               }

               BlockPos pos = kobold.getPosition();
               UUID uuid3 = kobold.getGirlUuid();
               tagCompound.setString(uuid.toString() + "member" + i2 + "pos", pos.getX() + "|" + pos.getY() + "|" + pos.getZ());
               tagCompound.setString(uuid.toString() + "member" + i2 + "id", uuid3.toString());
               set.add(uuid3);
               i2++;
            }

            for (Entry entry2 : homeData.HomeMap.entrySet()) {
               UUID uuid4 = (UUID)entry2.getKey();
               BlockPos pos2 = (BlockPos)entry2.getValue();

               try {
                  if (set.contains(uuid4)) {
                     continue;
                  }
               } catch (RuntimeException error3) {
                  throw rethrow(error3);
               }

               tagCompound.setString(uuid.toString() + "member" + i2 + "pos", pos2.getX() + "|" + pos2.getY() + "|" + pos2.getZ());
               tagCompound.setString(uuid.toString() + "member" + i2 + "id", uuid4.toString());
               set.add(uuid4);
               i2++;
            }

            int i3 = 0;

            for (BlockPos pos3 : homeData.SpawnPositions) {
               tagCompound.setString(uuid.toString() + "bed" + i3, pos3.getX() + "|" + pos3.getY() + "|" + pos3.getZ());
               i3++;
            }

            int i4 = 0;

            for (BlockPos pos4 : homeData.NearbyPositions) {
               tagCompound.setString(uuid.toString() + "chest" + i4, pos4.getX() + "|" + pos4.getY() + "|" + pos4.getZ());
               i4++;
            }

            int i5 = 0;

            for (TreeCluster treeCluster : homeData.AnchorEntities) {
               tagCompound.setString(uuid.toString() + i5 + "taskKind", treeCluster.TaskType.toString());
               tagCompound.setString(uuid.toString() + i5 + "pos", treeCluster.AnchorPos.getX() + "|" + treeCluster.AnchorPos.getY() + "|" + treeCluster.AnchorPos.getZ());
               tagCompound.setString(uuid.toString() + i5 + "facing", treeCluster.Facing.getName());
               int i6 = 0;

               for (BlockPos pos5 : treeCluster.LogPositions) {
                  tagCompound.setString(
                     uuid.toString() + i5 + "block" + i6, pos5.getX() + "|" + pos5.getY() + "|" + pos5.getZ()
                  );
                  i6++;
               }

               i5++;
            }

            i++;
         }

         return tagCompound;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
