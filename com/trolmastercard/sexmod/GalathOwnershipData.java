package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import org.apache.logging.log4j.Level;

public class GalathOwnershipData extends WorldSavedData {
   public static boolean f = true;
   public static final float c = 60.0F;
   public static final String NbtKeyGalathOwnership = "sexmod:galath_owner_ship";
   public static final String NbtKeyOwnership = "sexmod:ownershipdata";
   public static final String NbtKeyManglelieOwnership = "sexmod:mangownershipdata";
   static final long a = 0L;
   static BiMap<UUID, UUID> h = new BiMap<>();
   static HashMap<UUID, Long> b = new HashMap<>();
   static HashSet<UUID> OnlineOwners = new HashSet<>();

   public GalathOwnershipData() {
      super("sexmod:galath_owner_ship");
   }

   public GalathOwnershipData(String string) {
      super("sexmod:galath_owner_ship");
   }

   public static void clearOwnershipData() {
      OnlineOwners.clear();
      h.getMoveSpeed();
   }

   public static void markOwnerOnline(UUID uuid) {
      UUID uuid2 = getOwnerUuid(uuid);

      try {
         if (uuid2 == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      OnlineOwners.add(uuid2);
   }

   public static boolean isOwnerOnline(UUID uuid) {
      return OnlineOwners.contains(uuid);
   }

   public static boolean isPlayerNearOwnedGalath(GalathNpc galath) {
      UUID uuid = h.b(galath.getGirlUuid());

      try {
         if (uuid == null) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      World world2 = galath.world;
      EntityPlayer player = world2.getPlayerEntityByUUID(uuid);

      try {
         if (player == null) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (player.dimension != galath.dimension) {
            return false;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         return !(player.getDistance(galath) > 60.0F);
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }
   }

   public static boolean isGalathOwnedByPlayer(EntityPlayer player, GalathNpc galath) {
      return galath.getGirlUuid().equals(h.c(player.getPersistentID()));
   }

   public static void releaseOwnedGalath(GalathNpc galath) {
      ManglelieNpc manglelie = galath.getChildMangle(true);

      try {
         if (manglelie != null) {
            galath.world.removeEntity(manglelie);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      UUID uuid = h.b(galath.getGirlUuid());

      try {
         if (uuid == null) {
            galath.world.removeEntity(galath);
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      World world2 = galath.world;
      EntityPlayer player = world2.getPlayerEntityByUUID(uuid);

      try {
         galath.world.removeEntity(galath);
         h.a(uuid);
         if (player != null) {
            NetworkHandler.channel.sendTo(new PacketInformOfOwnership(false), (EntityPlayerMP)player);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }

   public static boolean hasOwnershipData(UUID uuid) {
      try {
         if (h.c(uuid) != null) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   public static UUID getOwnerUuid(UUID uuid) {
      return h.b(uuid);
   }

   public static UUID getGalathOwnerUuidByEntity(GalathNpc galath) {
      try {
         if (galath == null) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return getOwnerUuid(galath.getGirlUuid());
   }

   public static UUID getGalathByOwnerUuid(UUID uuid) {
      return h.c(uuid);
   }

   public static UUID getGalathByPlayer(EntityPlayer player) {
      try {
         if (player == null) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return getGalathByOwnerUuid(player.getPersistentID());
   }

   public static void setOwnership(UUID uuid, UUID uuid2) {
      h.a(uuid, uuid2);
   }

   public static void setOwnershipByPlayer(EntityPlayer player, GalathNpc galath) {
      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (galath == null) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      setOwnership(player.getPersistentID(), galath.getGirlUuid());
   }

   public static void removeOwnershipByGalathUuid(UUID uuid) {
      h.a(uuid);
   }

   public static void removeOwnershipByPlayer(EntityPlayer player) {
      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      removeOwnershipByGalathUuid(player.getPersistentID());
   }

   public static boolean isGalathRecent(UUID uuid, World world) {
      Long l = b.get(uuid);

      try {
         if (!isOwnerOnline(uuid)) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (l == null) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (world.getTotalWorldTime() - l > 0L) {
            return true;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      return false;
   }

   public static void setLastSeenTime(UUID uuid, Long l) {
      try {
         if (uuid == null) {
            Main.LOGGER.log(Level.WARN, "tried to save last cum dosage time on NULL player");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      b.put(uuid, l);
   }

   @SubscribeEvent
   public void a(ServerTickEvent serverTickEvent) {
      try {
         if (serverTickEvent.phase != Phase.END) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
      ArrayList list = new ArrayList();

      for (Entry entry : h.moveToRandomNearbyPos()) {
         UUID uuid = (UUID)entry.getKey();
         UUID uuid2 = (UUID)entry.getValue();
         EntityPlayer player = world.getPlayerEntityByUUID(uuid);

         try {
            if (player == null) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         try {
            if (GirlEntity.getServerSideByUuid(uuid2) == null) {
               list.add(player);
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }
      }

      for (EntityPlayer player2 : list) {
         h.a(player2.getPersistentID());
         NetworkHandler.channel.sendTo(new PacketInformOfOwnership(false), (EntityPlayerMP)player2);
      }
   }

   @SubscribeEvent
   public void a(Save save) {
      World world = save.getWorld();
      world.getMapStorage().setData("sexmod:galath_owner_ship", this);
      this.markDirty();
   }

   @SubscribeEvent
   public void a(Load load) {
      World world = load.getWorld();
      world.getMapStorage().getOrLoadData(GalathOwnershipData.class, "sexmod:galath_owner_ship");
   }


   public void readFromNBT(NBTTagCompound tagCompound) {
        NBTTagCompound nBTTagCompound2 = tagCompound.getCompoundTag(NbtKeyOwnership);
        int i = nBTTagCompound2.getInteger("amount");
        for (int i3 = 0; i3 < i; ++i3) {
            long l;
            UUID uUID;
            UUID uUID2;
            block9: {
                uUID2 = nBTTagCompound2.getUniqueId("master" + i3);
                uUID = nBTTagCompound2.getUniqueId("galath" + i3);
                l = nBTTagCompound2.getLong("lastcumdosage" + i3);
                try {
                    try {
                        if (uUID2 != null && uUID != null) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GalathOwnershipData.rethrow(runtimeException);
                    }
                    Main.LOGGER.fatal("OMFG WHOOP WHOOP SAVING DIDNT WORK CORRECTLY AAAAAAAAAAA");
                    continue;
                }
                catch (RuntimeException runtimeException) {
                    throw GalathOwnershipData.rethrow(runtimeException);
                }
            }
            h.a(uUID2, uUID);
            b.put(uUID2, l);
        }
        NBTTagCompound nBTTagCompound3 = tagCompound.getCompoundTag(NbtKeyManglelieOwnership);
        int i4 = 0;
        while (true) {
            try {
                if (!nBTTagCompound3.hasUniqueId("mang" + i4)) {
                    break;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GalathOwnershipData.rethrow(runtimeException);
            }
            OnlineOwners.add(nBTTagCompound3.getUniqueId("mang" + i4));
            ++i4;
        }
        tagCompound.setTag(NbtKeyManglelieOwnership, (NBTBase)new NBTTagCompound());
        tagCompound.setTag(NbtKeyOwnership, (NBTBase)new NBTTagCompound());
    }

   public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = new NBTTagCompound();
      tagCompound2.setInteger("amount", h.e());
      int i = 0;

      for (Entry entry : h.moveToRandomNearbyPos()) {
         UUID uuid = (UUID)entry.getKey();
         UUID uuid2 = (UUID)entry.getValue();
         Long l = b.get(uuid);
         if (l == null) {
            l = 0L;
         }

         tagCompound2.setUniqueId("galath" + i, uuid2);
         tagCompound2.setUniqueId("master" + i, uuid);
         tagCompound2.setLong("lastcumdosage" + i, l);
         i++;
      }

      NBTTagCompound tagCompound3 = new NBTTagCompound();
      i = 0;

      for (UUID uuid3 : OnlineOwners) {
         tagCompound3.setUniqueId("mang" + i++, uuid3);
      }

      tagCompound.setTag("sexmod:ownershipdata", tagCompound2);
      tagCompound.setTag("sexmod:mangownershipdata", tagCompound3);
      return tagCompound;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
