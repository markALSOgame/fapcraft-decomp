package com.trolmastercard.sexmod;

import com.mojang.realmsclient.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public abstract class GirlEntity extends EntityCreature implements IAnimatable {
   public static int j = 22;
   protected static final long t = 20L;
   private final AnimationFactory Factory;
   public EntityAIWanderAvoidWater WanderAI;
   public GirlWatchAi WatchPlayerAI;
   public static HashSet<GirlEntity> AllGirls = new HashSet<>();
   public Vec3d AimTarget;
   protected float AimYaw;
   protected EntityDataManager DataManager;
   public PathNavigate Navigation;
   public Vec3d HomePos;
   public EntityEnderPearl EnderPearl;
   public float n;
   public boolean F;
   private boolean Tracked;
   HashMap<String, Vec3d> AnchorPoints;
   public static final DataParameter<String> MasterUuidKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(110);
   public static final DataParameter<Boolean> BusyKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.BOOLEAN).getSerializer().createKey(109);
   public static final DataParameter<String> TargetPosKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(108);
   public static final DataParameter<Float> RotationYawKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.FLOAT).getSerializer().createKey(107);
   public static final DataParameter<String> GirlUuidKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(106);
   public static final DataParameter<Integer> OutfitIndexKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.VARINT).getSerializer().createKey(105);
   public static final DataParameter<String> CurrentActionKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(104);
   public static final DataParameter<String> BlowjobStageKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(103);
   public static final DataParameter<String> SexPlayerUuidKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(102);
   public static final DataParameter<String> WalkStateKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(101);
   public static final DataParameter<String> CustomModelKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(100);
   public static final DataParameter<String> CustomNameKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.STRING).getSerializer().createKey(99);
   protected static final List<Item> PaymentItems = Arrays.asList(Items.EMERALD, Items.DIAMOND, Items.GOLD_INGOT, Items.ENDER_PEARL);
   public AnimationController ActionController;
   public AnimationController MovementController;
   public AnimationController EyesController;
   HashMap<String, Pair<Integer, Integer>> AnimRanges;
   AnimationProcessor<?> BoneProcessor;
   public List<String> AnchorNames;
   protected List<Entry<GirlBodySlot, Entry<List<String>, Integer>>> OutfitData;

   public void a(GirlEntity.WalkState walkState) {
      this.DataManager.set(WalkStateKey, walkState.toString());
   }

   public GirlEntity.WalkState getWalkState() {
      return GirlEntity.WalkState.valueOf((String)this.DataManager.get(WalkStateKey));
   }

   @SideOnly(Side.CLIENT)
   protected void a(String string, String string2) {
      NetworkHandler.channel.sendToServer(new PacketUpdateGirl(this.getGirlUuid(), string, string2));
   }

   public UUID getGirlUuid() {
      try {
         return UUID.fromString((String)this.DataManager.get(GirlUuidKey));
      } catch (Exception error) {
         UUID uuid = UUID.randomUUID();
         this.DataManager.set(GirlUuidKey, uuid.toString());
         return uuid;
      }
   }

   public GirlAnimationState getCurrentAction() {
      return GirlAnimationState.valueOf((String)this.DataManager.get(CurrentActionKey));
   }


   public void setCurrentAction(GirlAnimationState girlAnimationState) {
        GirlAnimationState girlAnimationState2;
        block12: {
            GirlAnimationState girlAnimationState3 = this.getCurrentAction();
            try {
                if (girlAnimationState3 == girlAnimationState) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.ATTACK || girlAnimationState3 == GirlAnimationState.NULL) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        try {
            girlAnimationState2 = girlAnimationState == null ? GirlAnimationState.NULL : girlAnimationState;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GirlEntity.rethrow(concurrentModificationException);
        }
        girlAnimationState = girlAnimationState2;
        try {
            if (this.world.isRemote) {
                this.a("currentAction", girlAnimationState.toString());
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GirlEntity.rethrow(concurrentModificationException);
        }
        girlAnimationState3.ticksPlaying = new int[]{0, 0};
        this.DataManager.set(CurrentActionKey, (Object)girlAnimationState.toString());
    }

   public int getOutfitIndex() {
      return (Integer)this.DataManager.get(OutfitIndexKey);
   }

   public void setOutfitIndex(int i) {
      try {
         if (this.world.isRemote) {
            this.a("currentModel", "0");
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      this.DataManager.set(OutfitIndexKey, i);
   }

   public boolean m() {
      return false;
   }

   @Nullable
   public EntityPlayer S() {
      UUID uuid = this.getSexPlayerUuid();

      try {
         if (uuid == null) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return this.world.getPlayerEntityByUUID(uuid);
   }

   public static void sendMessageToNearbyPlayers(GirlEntity girl, String string) {
      for (EntityPlayer player : BedLogic.getNearbyNetworkPlayers(girl)) {
         player.sendMessage(new TextComponentString(string));
      }
   }

   public static void playSound(GirlEntity girl, SoundEvent sound, boolean flag) {
      Vec3d vec3d = girl.getPositionVector();

      for (EntityPlayer player : BedLogic.getNearbyNetworkPlayers(girl)) {
         Vec3d vec3d2;
         if (!flag) {
            vec3d2 = vec3d;
         } else {
            Vec3d vec3d3 = player.getPositionVector();
            Vec3d vec3d4 = vec3d.subtract(vec3d3).normalize();
            vec3d2 = vec3d3.add(vec3d4);
         }

         ((EntityPlayerMP)player)
            .connection
            .sendPacket(new SPacketSoundEffect(sound, SoundCategory.AMBIENT, vec3d2.x, vec3d2.y, vec3d2.z, 1.0F, 1.0F));
      }
   }

   public static void playSound(GirlEntity girl, SoundEvent sound) {
      playSound(girl, sound, false);
   }

   public static void playSoundRandom(GirlEntity girl, SoundEvent[] soundArray) {
      a(girl, ModSounds.pickRandomSound(soundArray));
   }

   public static void playSoundRandom(GirlEntity girl, SoundEvent[] soundArray, boolean flag) {
      a(girl, ModSounds.pickRandomSound(soundArray), flag);
   }

   @SideOnly(Side.CLIENT)
   public Vec3d A() {
      Vec3d vec3d = Minecraft.getMinecraft().player.getPositionVector();
      Vec3d vec3d2 = this.getPositionVector();
      Vec3d vec3d3 = vec3d2.subtract(vec3d).normalize();
      return vec3d.add(vec3d3);
   }

   @Nullable
   public UUID getSexPlayerUuid() {
      String string = (String)this.DataManager.get(SexPlayerUuidKey);

      try {
         if (string.equals("null")) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return UUID.fromString(string);
   }


   public void handleGirlUuidEvent(UUID uuid) {
        block10: {
            block9: {
                block6: {
                    block8: {
                        block7: {
                            try {
                                try {
                                    if (!this.world.isRemote) break block6;
                                    if (uuid != null) break block7;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GirlEntity.rethrow(concurrentModificationException);
                                }
                                this.a("playerSheHasSexWith", (String)null);
                                break block8;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GirlEntity.rethrow(concurrentModificationException);
                            }
                        }
                        this.a("playerSheHasSexWith", uuid.toString());
                    }
                    return;
                }
                try {
                    if (uuid != null) break block9;
                    this.DataManager.set(SexPlayerUuidKey, (Object)"null");
                    break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
            }
            this.DataManager.set(SexPlayerUuidKey, (Object)uuid.toString());
        }
    }

   public void a(@Nonnull EntityPlayer player) {
      this.handleGirlUuidEvent(player.getPersistentID());
   }

   public Vec3d getTargetPos() {
      String[] stringArray = ((String)this.DataManager.get(TargetPosKey)).split("\\|");
      return new Vec3d(Double.parseDouble(stringArray[0]), Double.parseDouble(stringArray[1]), Double.parseDouble(stringArray[2]));
   }

   public void setTargetPos(Vec3d vec3d) {
      if (this.world.isRemote) {
         String string = vec3d.x + "f" + vec3d.y + "f" + vec3d.z + "f";
         this.a("targetPos", string);
      } else {
         this.DataManager.set(TargetPosKey, vec3d.x + "|" + vec3d.y + "|" + vec3d.z);
      }
   }

   public void a(Vec3d vec3d) {
      this.DataManager.set(TargetPosKey, vec3d.x + "|" + vec3d.y + "|" + vec3d.z);
   }

   public Float I() {
      return (Float)this.DataManager.get(RotationYawKey);
   }

   public void b(float f) {
      this.DataManager.set(RotationYawKey, f);
   }

   public void setShouldBeAtTargetPos(boolean flag) {
      try {
         if (this.world.isRemote) {
            this.a("shouldbeattargetpos", String.valueOf(flag));
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      this.DataManager.set(BusyKey, flag);
   }

   public boolean Q() {
      return (Boolean)this.DataManager.get(BusyKey);
   }

   protected boolean canDespawn() {
      return false;
   }


   protected GirlEntity(World world) {
        block8: {
            super(world);
            this.Factory = new AnimationFactory(this);
            this.HomePos = Vec3d.ZERO;
            this.n = 1.0f;
            this.F = false;
            this.Tracked = false;
            this.AnchorPoints = new HashMap();
            this.AnimRanges = new HashMap();
            this.BoneProcessor = null;
            this.AnchorNames = new ArrayList<String>();
            this.OutfitData = null;
            if (world.isRemote) {
                this.initAnimationControllers();
            }
            try {
                try {
                    if (!world.isRemote || !(world instanceof PreviewWorld)) break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        PathNavigate pathNavigate = this.getNavigator();
        try {
            if (pathNavigate instanceof PathNavigateGround) {
                ((PathNavigateGround)pathNavigate).setBreakDoors(true);
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GirlEntity.rethrow(concurrentModificationException);
        }
    }

   @SideOnly(Side.CLIENT)
   protected void initAnimationControllers() {
      this.ActionController = new AnimationController(this, "action", 0.0F, this::a);
      this.MovementController = new AnimationController(this, "movement", 5.0F, this::a);
      this.EyesController = new AnimationController(this, "eyes", 10.0F, this::a);
   }

   protected void entityInit() {
      super.entityInit();
      this.Navigation = this.getNavigator();
      this.DataManager = this.getDataManager();
      this.DataManager.register(GirlUuidKey, UUID.randomUUID().toString());
      this.DataManager.register(OutfitIndexKey, 1);
      this.DataManager.register(CurrentActionKey, GirlAnimationState.NULL.toString());
      this.DataManager.register(BlowjobStageKey, "");
      this.DataManager.register(SexPlayerUuidKey, "null");
      this.DataManager.register(BusyKey, false);
      this.DataManager.register(RotationYawKey, 0.0F);
      this.DataManager.register(TargetPosKey, "0|0|0");
      this.DataManager.register(MasterUuidKey, "");
      this.DataManager.register(WalkStateKey, GirlEntity.WalkState.WALK.toString());
      this.DataManager.register(CustomModelKey, "");
      this.DataManager.register(CustomNameKey, "");
   }

   public void setTracked(boolean flag) {
      try {
         this.Tracked = flag;
         if (flag) {
            GirlTracker.trackGirl(this);
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      GirlTracker.untrackGirl(this);
   }

   public boolean isTracked() {
      return this.Tracked;
   }

   public static List<GirlEntity> getAllGirls() {
      try {
         if (!ServerThreadUtil.isServerThread()) {
            return Z();
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      WorldServer[] worldArray = FMLCommonHandler.instance().getMinecraftServerInstance().worlds;

      try {
         if (worldArray.length == 0) {
            return new ArrayList<>();
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      ArrayList list = new ArrayList();

      for (WorldServer world : worldArray) {
         list.addAll(world.getEntities(GirlEntity.class, arg1 -> true));
      }

      return list;
   }

   @SideOnly(Side.CLIENT)
   private static List<GirlEntity> Z() {
      WorldClient worldClient = Minecraft.getMinecraft().world;

      try {
         if (worldClient == null) {
            return new ArrayList<>();
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return worldClient.getEntities(GirlEntity.class, arg1 -> true);
   }

   public boolean B() {
      return true;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0);
   }

   protected void initEntityAI() {
      this.WanderAI = new EntityAIWanderAvoidWater(this, 0.35);
      this.WatchPlayerAI = new GirlWatchAi(this, EntityPlayer.class, 3.0F, 1.0F);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAITempt(this, 0.4, false, new HashSet<>(PaymentItems)));
      this.tasks.addTask(3, new EntityAIOpenDoor(this));
      this.tasks.addTask(5, this.WatchPlayerAI);
      this.tasks.addTask(5, this.WanderAI);
   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      tagCompound.setDouble("homeX", this.HomePos.x);
      tagCompound.setDouble("homeY", this.HomePos.y);
      tagCompound.setDouble("homeZ", this.HomePos.z);
      tagCompound.setString("girlID", (String)this.DataManager.get(GirlUuidKey));
      String string = this.getCustomName();

      try {
         if (!"".equals(string)) {
            tagCompound.setString("sexmod:customname", string);
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (this.X()) {
            tagCompound.setString("sexmod:customModel", this.C());
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      super.writeEntityToNBT(tagCompound);
   }

   protected boolean X() {
      return isGirlEntity(this);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      this.HomePos = new Vec3d(tagCompound.getDouble("homeX"), tagCompound.getDouble("homeY"), tagCompound.getDouble("homeZ"));
      String string = tagCompound.getString("sexmod:customname");

      try {
         if (!"".equals(string)) {
            this.setCustomName(string);
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      String string2 = tagCompound.getString("girlID");

      try {
         if ("".equals(string2)) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      UUID uuid = UUID.fromString(string2);
      boolean flag = false;

      for (GirlEntity girl : getGirlsByOwner(uuid)) {
         try {
            if (girl.world.isRemote) {
               continue;
            }
         } catch (ConcurrentModificationException error3) {
            throw rethrow(error3);
         }

         try {
            if (girl == this) {
               continue;
            }
         } catch (ConcurrentModificationException error4) {
            throw rethrow(error4);
         }

         try {
            if (girl.isDead) {
               continue;
            }
         } catch (ConcurrentModificationException error5) {
            throw rethrow(error5);
         }

         try {
            if (!girl.isAddedToWorld()) {
               continue;
            }
         } catch (ConcurrentModificationException error6) {
            throw rethrow(error6);
         }

         flag = true;
         break;
      }

      try {
         if (flag) {
            Main.LOGGER.log(Level.WARN, String.format("got a duped %s with id '%s'. Deleted her", this.getDisplayName(), uuid));
            this.world.removeEntity(this);
            return;
         }
      } catch (ConcurrentModificationException error7) {
         throw rethrow(error7);
      }

      try {
         this.DataManager.set(GirlUuidKey, uuid.toString());
         if (this.X()) {
            this.f(tagCompound.getString("sexmod:customModel"));
         }
      } catch (ConcurrentModificationException error8) {
         throw rethrow(error8);
      }
   }

   public boolean d() {
      return true;
   }

   public void setVelocity(double d, double d2, double d3) {
      this.motionX = d;
      this.motionY = d2;
      this.motionZ = d3;
   }

   public void b(Vec3d vec3d) {
      this.motionX = vec3d.x;
      this.motionY = vec3d.y;
      this.motionZ = vec3d.z;
   }

   public Vec3d getRenderPosition() {
      return new Vec3d(this.lastTickPosX, this.lastTickPosY, this.lastTickPosZ);
   }

   public void updateAITasks() {
      try {
         if ((Boolean)this.DataManager.get(BusyKey)) {
            this.setRotationYawHead(this.I());
            this.setPositionAndRotation(this.getTargetPos().x, this.getTargetPos().y, this.getTargetPos().z, this.I(), 0.0F);
            this.setRotation(this.I(), this.rotationPitch);
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (this.HomePos.equals(Vec3d.ZERO)) {
            this.HomePos = new Vec3d(this.getPosition());
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      this.G();
   }

   public void onUpdate() {
      super.onUpdate();
      this.advanceAnimationState();
   }

   protected void G() {
      try {
         if (!FilePersistence.ClientActive) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      HashSet set = this.Y();
      GirlRegistry girlType = GirlRegistry.getByEntity(this);
      HashSet set2 = new HashSet();
      String string = FilePersistence.getModelsPath();

      for (String string2 : set) {
         try {
            if (!"".equals(FilePersistence.validateModelFiles(string2, string))) {
               set2.add(string2);
               continue;
            }
         } catch (ConcurrentModificationException error2) {
            throw rethrow(error2);
         }

         HashSet set3 = FilePersistence.getAllowedGirls(string2);

         try {
            if (set3 == null) {
               set2.add(string2);
               continue;
            }
         } catch (ConcurrentModificationException error3) {
            throw rethrow(error3);
         }

         try {
            if (set3.isEmpty()) {
               continue;
            }
         } catch (ConcurrentModificationException error4) {
            throw rethrow(error4);
         }

         try {
            if (!set3.contains(girlType)) {
               set2.add(string2);
            }
         } catch (ConcurrentModificationException error5) {
            throw rethrow(error5);
         }
      }

      try {
         if (set2.isEmpty()) {
            return;
         }
      } catch (ConcurrentModificationException error6) {
         throw rethrow(error6);
      }

      set.removeAll(set2);
      this.f(joinModelNames(set));
   }

   protected void advanceAnimationState() {
      GirlAnimationState girlAnimationState = this.getCurrentAction();

      int[] ints;
      byte bv;
      label42: {
         try {
            ints = girlAnimationState.ticksPlaying;
            if (this.world.isRemote) {
               bv = 1;
               break label42;
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }

         bv = 0;
      }

      try {
         if (++ints[bv] < girlAnimationState.length) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      try {
         if (girlAnimationState.followUp == null) {
            return;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      try {
         if (!this.world.isRemote) {
            this.setCurrentAction(girlAnimationState.followUp);
         }
      } catch (ConcurrentModificationException error4) {
         throw rethrow(error4);
      }
   }


   protected void k() {
        int i;
        Path path;
        block12: {
            block11: {
                path = this.getNavigator().getPath();
                try {
                    if (path == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                try {
                    try {
                        if (!this.onGround && !this.isInWater()) break block11;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlEntity.rethrow(concurrentModificationException);
                    }
                    return;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
            }
            i = path.getCurrentPathIndex();
            int i2 = path.getCurrentPathLength();
            try {
                try {
                    if (i2 != i && i2 - 1 != i) break block12;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        PathPoint pathPoint = path.getPathPointFromIndex(i);
        PathPoint pathPoint2 = path.getPathPointFromIndex(i + 1);
        Vec3d vec3d = new Vec3d((double)(pathPoint2.x - pathPoint.x), (double)(pathPoint2.y - pathPoint.y), (double)(pathPoint2.z - pathPoint.z));
        this.motionX = vec3d.x / 7.0;
        this.motionZ = vec3d.z / 7.0;
    }

   public void noop() {
   }

   @SideOnly(Side.CLIENT)
   public boolean b(EntityPlayer player) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   protected static void openActionMenu(EntityPlayer player, GirlEntity girl) {
      Minecraft.getMinecraft().displayGuiScreen(new GuiGirlCommandMenu(girl, player));
   }

   @SideOnly(Side.CLIENT)
   protected static void openActionMenuWithItems(EntityPlayer player, GirlEntity girl, String[] stringArray, ItemStack[] stackArray, boolean flag) {
      Minecraft.getMinecraft().displayGuiScreen(new GuiGirlCommandMenu(girl, player, stringArray, stackArray, flag));
   }

   @SideOnly(Side.CLIENT)
   protected static void openActionMenu(EntityPlayer player, GirlEntity girl, String[] stringArray, boolean flag) {
      Minecraft.getMinecraft().displayGuiScreen(new GuiGirlCommandMenu(girl, player, stringArray, null, flag));
   }

   public void a(ItemStack stack) {
      this.activeItemStack = stack;
   }

   public void d(int i) {
      this.activeItemStackUseCount = i;
   }

   public Vec3d M() {
      return new Vec3d(this.prevPosX, this.prevPosY, this.prevPosZ);
   }

   protected static Vec3d a(GirlEntity girl) {
      return new Vec3d(girl.prevPosX, girl.prevPosY, girl.prevPosZ);
   }

   public GirlEntity getSelf() {
      return this;
   }

   public void resetMasterAndWalkSpeed() {
      try {
         if (this.world.isRemote) {
            this.a("master", "");
            this.a("walk speed", GirlEntity.WalkState.WALK.toString());
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      this.DataManager.set(MasterUuidKey, "");
      this.DataManager.set(WalkStateKey, GirlEntity.WalkState.WALK.toString());
   }

   protected void a(EntityPlayerMP serverPlayer, boolean flag) {
      serverPlayer.motionX = 0.0;
      serverPlayer.motionY = 0.0;
      serverPlayer.motionZ = 0.0;
      if (flag) {
         Vec3d vec3d = this.a(0.35);
         serverPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
      }
   }

   public void teleportPlayerInFront(UUID uuid) {
      EntityPlayer player = this.world.getPlayerEntityByUUID(uuid);
      player.motionX = 0.0;
      player.motionY = 0.0;
      player.motionZ = 0.0;
      Vec3d vec3d = this.a(0.35);
      player.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
      this.b(player.rotationYawHead + 180.0F);
   }

   protected void a(boolean flag, boolean flag2, UUID uuid) {
      try {
         if (this.world.isRemote) {
            NetworkHandler.channel.sendToServer(new PacketSexPromptReply(this.getGirlUuid(), uuid, flag, flag2));
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      PacketSexPromptReply.Handler.a(this.getGirlUuid(), uuid, flag, flag2);
   }

   public static GirlEntity getClientSideByUuid(UUID uuid) {
      try {
         if (uuid == null) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      for (GirlEntity girl : getGirlsByOwner(uuid)) {
         try {
            if (girl.world.isRemote) {
               return girl;
            }
         } catch (ConcurrentModificationException error2) {
            throw rethrow(error2);
         }
      }

      return null;
   }

   public static GirlEntity getServerSideByUuid(UUID uuid) {
      try {
         if (uuid == null) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      for (GirlEntity girl : getGirlsByOwner(uuid)) {
         try {
            if (!girl.world.isRemote) {
               return girl;
            }
         } catch (ConcurrentModificationException error2) {
            throw rethrow(error2);
         }
      }

      return null;
   }

   public static ArrayList<GirlEntity> getGirlsByOwner(UUID uuid) {
      ArrayList list = new ArrayList();

      try {
         for (GirlEntity girl : getAllGirls()) {
            try {
               if (girl == null) {
                  continue;
               }
            } catch (ConcurrentModificationException error) {
               throw rethrow(error);
            }

            try {
               if (girl.getGirlUuid().equals(uuid)) {
                  list.add(girl);
               }
            } catch (ConcurrentModificationException error2) {
               throw rethrow(error2);
            }
         }
      } catch (ConcurrentModificationException error3) {
         System.out.println("had a ConcurrentModificationException while cycling through the girl list... hopefully nothin borke owo");
         error3.printStackTrace();
      }

      return list;
   }

   protected BlockPos a(BlockPos pos) {
      return this.a(pos, 1);
   }

   public BlockPos a(BlockPos pos, int i) {
      return this.a(pos, i, Blocks.BED, 22, 3, null);
   }

   public void W() {
      this.DataManager.set(HAND_STATES, Byte.valueOf("1"));
   }

   public void K() {
      this.DataManager.set(HAND_STATES, Byte.valueOf("0"));
   }


   public BlockPos a(BlockPos pos, int i, Block block, int i3, int i4, @Nullable HashSet<Biome> set) {
        int i5 = 1;
        int i6 = -1;
        BlockPos blockPos2 = pos;
        int i7 = 0;
        while (i5 < i3) {
            for (int i8 = 0; i8 < 2; ++i8) {
                int i9;
                int i10;
                i6 *= -1;
                block22: for (i10 = 0; i10 < i5; ++i10) {
                    blockPos2 = blockPos2.add(0, 0, i6);
                    i9 = -i4;
                    while (true) {
                        block26: {
                            try {
                                block27: {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    if (i9 >= i4 + 1) continue block22;
                                                    if (this.world.getBlockState(blockPos2.add(0, i9, i6)).getBlock() != block) break block26;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw GirlEntity.rethrow(concurrentModificationException);
                                                }
                                                if (++i7 < i) break block26;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GirlEntity.rethrow(concurrentModificationException);
                                            }
                                            if (set == null) break block27;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GirlEntity.rethrow(concurrentModificationException);
                                        }
                                        if (!set.contains(this.world.getBiome(blockPos2.add(i6, i9, 0)))) break block26;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GirlEntity.rethrow(concurrentModificationException);
                                    }
                                }
                                return blockPos2.add(0, i9, i6);
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GirlEntity.rethrow(concurrentModificationException);
                            }
                        }
                        ++i9;
                    }
                }
                block24: for (i10 = 0; i10 < i5; ++i10) {
                    blockPos2 = blockPos2.add(i6, 0, 0);
                    i9 = -i4;
                    while (true) {
                        block28: {
                            try {
                                block29: {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    if (i9 >= i4 + 1) continue block24;
                                                    if (this.world.getBlockState(blockPos2.add(i6, i9, 0)).getBlock() != block) break block28;
                                                }
                                                catch (ConcurrentModificationException concurrentModificationException) {
                                                    throw GirlEntity.rethrow(concurrentModificationException);
                                                }
                                                if (++i7 < i) break block28;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GirlEntity.rethrow(concurrentModificationException);
                                            }
                                            if (set == null) break block29;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GirlEntity.rethrow(concurrentModificationException);
                                        }
                                        if (!set.contains(this.world.getBiome(blockPos2.add(i6, i9, 0)))) break block28;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GirlEntity.rethrow(concurrentModificationException);
                                    }
                                }
                                return blockPos2.add(i6, i9, 0);
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GirlEntity.rethrow(concurrentModificationException);
                            }
                        }
                        ++i9;
                    }
                }
                ++i5;
            }
        }
        return null;
    }


   protected List<BlockPos> a(BlockPos pos, Class cls, int i, int i3, @Nullable HashSet<Biome> set) {
        int i4 = 1;
        int i5 = -1;
        BlockPos blockPos2 = pos;
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        while (i4 < i) {
            for (int i6 = 0; i6 < 2; ++i6) {
                int i7;
                int i8;
                i5 *= -1;
                block18: for (i8 = 0; i8 < i4; ++i8) {
                    blockPos2 = blockPos2.add(0, 0, i5);
                    i7 = -i3;
                    while (true) {
                        block22: {
                            try {
                                block23: {
                                    try {
                                        try {
                                            try {
                                                if (i7 >= i3 + 1) continue block18;
                                                if (!cls.isInstance(this.world.getBlockState(blockPos2.add(0, i7, i5)).getBlock())) break block22;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GirlEntity.rethrow(concurrentModificationException);
                                            }
                                            if (set == null) break block23;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GirlEntity.rethrow(concurrentModificationException);
                                        }
                                        if (!set.contains(this.world.getBiome(blockPos2.add(i5, i7, 0)))) break block22;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GirlEntity.rethrow(concurrentModificationException);
                                    }
                                }
                                arrayList.add(blockPos2.add(0, i7, i5));
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GirlEntity.rethrow(concurrentModificationException);
                            }
                        }
                        ++i7;
                    }
                }
                block20: for (i8 = 0; i8 < i4; ++i8) {
                    blockPos2 = blockPos2.add(i5, 0, 0);
                    i7 = -i3;
                    while (true) {
                        block24: {
                            try {
                                block25: {
                                    try {
                                        try {
                                            try {
                                                if (i7 >= i3 + 1) continue block20;
                                                if (!cls.isInstance(this.world.getBlockState(blockPos2.add(i5, i7, 0)).getBlock())) break block24;
                                            }
                                            catch (ConcurrentModificationException concurrentModificationException) {
                                                throw GirlEntity.rethrow(concurrentModificationException);
                                            }
                                            if (set == null) break block25;
                                        }
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GirlEntity.rethrow(concurrentModificationException);
                                        }
                                        if (!set.contains(this.world.getBiome(blockPos2.add(i5, i7, 0)))) break block24;
                                    }
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GirlEntity.rethrow(concurrentModificationException);
                                    }
                                }
                                arrayList.add(blockPos2.add(i5, i7, 0));
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GirlEntity.rethrow(concurrentModificationException);
                            }
                        }
                        ++i7;
                    }
                }
                ++i4;
            }
        }
        return arrayList;
    }

   public boolean J() {
      try {
         if (!((String)this.DataManager.get(MasterUuidKey)).equals("")) {
            return true;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return false;
   }

   @Nullable
   public UUID O() {
      String string = (String)this.DataManager.get(MasterUuidKey);

      try {
         if ("".equals(string)) {
            return null;
         }
      } catch (IllegalArgumentException error) {
         throw rethrow(error);
      }

      try {
         return UUID.fromString(string);
      } catch (IllegalArgumentException error2) {
         return null;
      }
   }

   @Nullable
   public EntityPlayer getSexPlayer() {
      UUID uuid = this.O();

      try {
         if (uuid == null) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return this.world.getPlayerEntityByUUID(uuid);
   }

   protected ResourceLocation getLootTable() {
      return GirlLootTables.JennyLootTable;
   }

   @SideOnly(Side.CLIENT)
   public void a(String string, UUID uuid) {
   }

   @SideOnly(Side.CLIENT)
   protected abstract <MovementController extends IAnimatable> PlayState a(AnimationEvent<MovementController> animEvent);

   @SideOnly(Side.CLIENT)
   protected boolean a(GirlAnimationState girlAnimationState, String string, boolean flag, AnimationEvent animEvent) {
      return false;
   }

   @SideOnly(Side.CLIENT)

   protected void a(String string, boolean flag, AnimationEvent animEvent, boolean flag2) {
        ILoopType.EDefaultLoopTypes eDefaultLoopTypes;
        block8: {
            try {
                try {
                    try {
                        if (flag2 || !GirlAnimationState.isAnimationAtEnd(this, animEvent.getPartialTick())) break block8;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlEntity.rethrow(concurrentModificationException);
                    }
                    if (!this.a(this.getCurrentAction(), string, AnimationInputLock.SneakPressed, animEvent)) break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        try {
            eDefaultLoopTypes = flag ? ILoopType.EDefaultLoopTypes.LOOP : ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GirlEntity.rethrow(concurrentModificationException);
        }
        ILoopType.EDefaultLoopTypes eDefaultLoopTypes2 = eDefaultLoopTypes;
        animEvent.getController().setAnimation(new AnimationBuilder().addAnimation(string, eDefaultLoopTypes2));
        animEvent.getController().transitionLengthTicks = 0.0;
    }

   @SideOnly(Side.CLIENT)
   protected void a(String string, boolean flag, AnimationEvent animEvent) {
      this.a(string, flag, animEvent, false);
   }

   @SideOnly(Side.CLIENT)

   protected void a(String string, int i, float f, AnimationEvent animEvent, boolean flag) {
        int i2;
        Integer i3;
        String string2;
        HashMap<String, Pair<Integer, Integer>> hashMap;
        String string3;
        AnimationBuilder animationBuilder;
        AnimationController animationController;
        int i4;
        int i5;
        AnimationController animationController2;
        block16: {
            String string4;
            AnimationBuilder animationBuilder2;
            AnimationController animationController3;
            block18: {
                block17: {
                    block15: {
                        try {
                            try {
                                try {
                                    if (flag || !GirlAnimationState.isAnimationAtEnd(this, animEvent.getPartialTick())) break block15;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GirlEntity.rethrow(concurrentModificationException);
                                }
                                if (!this.a(this.getCurrentAction(), string, AnimationInputLock.SneakPressed, animEvent)) break block15;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GirlEntity.rethrow(concurrentModificationException);
                            }
                            return;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlEntity.rethrow(concurrentModificationException);
                        }
                    }
                    animationController2 = animEvent.getController();
                    Pair pair = this.AnimRanges.get(string);
                    if (pair == null) {
                        pair = Pair.of((Object)0, (Object)0);
                    }
                    i5 = (Integer)pair.first();
                    i4 = (Integer)pair.second();
                    try {
                        try {
                            if (GirlAnimationState.isAnimationAtEnd(this, animEvent.getPartialTick())) break block16;
                            animationController3 = animEvent.getController();
                            animationBuilder2 = new AnimationBuilder();
                            if (i5 != 0) break block17;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlEntity.rethrow(concurrentModificationException);
                        }
                        string4 = string;
                        break block18;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlEntity.rethrow(concurrentModificationException);
                    }
                }
                string4 = string + i5;
            }
            animationController3.setAnimation(animationBuilder2.addAnimation(string4, ILoopType.EDefaultLoopTypes.LOOP));
            animEvent.getController().transitionLengthTicks = 0.0;
            return;
        }
        int i6 = this.a(i5, i4, i, f);
        try {
            animationController = animationController2;
            animationBuilder = new AnimationBuilder();
            string3 = i6 == 0 ? string : string + i6;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GirlEntity.rethrow(concurrentModificationException);
        }
        try {
            animationController.setAnimation(animationBuilder.addAnimation(string3, ILoopType.EDefaultLoopTypes.LOOP));
            animationController2.transitionLengthTicks = 0.0;
            hashMap = this.AnimRanges;
            string2 = string;
            i3 = i6;
            i2 = i6 == 0 ? i4 : i6;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw GirlEntity.rethrow(concurrentModificationException);
        }
        hashMap.put(string2, (Pair<Integer, Integer>)Pair.of((Object)i3, (Object)i2));
    }

   @SideOnly(Side.CLIENT)
   protected void a(String string, int i, float f, AnimationEvent animEvent) {
      this.a(string, i, f, animEvent, false);
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   int a(int i, int i2, int i3, float f) {
      try {
         if (i != 0) {
            return 0;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      Random random = this.getRNG();

      try {
         if (random.nextFloat() > f) {
            return 0;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      int i4;
      while (true) {
         i4 = random.nextInt(i3);
         if (i4 != i2) {
            try {
               if (i4 != 0) {
                  break;
               }
            } catch (ConcurrentModificationException error3) {
               throw rethrow(error3);
            }
         }

         try {
            if (i3 <= 2) {
               break;
            }
         } catch (ConcurrentModificationException error4) {
            throw rethrow(error4);
         }
      }

      try {
         return i4;
      } catch (ConcurrentModificationException error5) {
         throw rethrow(error5);
      }
   }

   @SideOnly(Side.CLIENT)
   public abstract void registerControllers(AnimationData animationData);


   protected void s() {
        block8: {
            block7: {
                try {
                    try {
                        if (!this.world.isRemote || !this.isOwnedByLocalPlayer()) break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlEntity.rethrow(concurrentModificationException);
                    }
                    this.AimTarget = null;
                    NetworkHandler.channel.sendToServer((IMessage)new PacketResetGirl(this.getGirlUuid(), true));
                    break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
            }
            try {
                if (!this.world.isRemote) {
                    EyesController.Handler.a((EntityPlayerMP)this.world.getPlayerEntityByUUID(this.getSexPlayerUuid()));
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
    }

   public static GirlEntity getByPlayer(EntityPlayer player) {
      try {
         if (player == null) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return getGirlByUuid(player.getPersistentID());
   }

   @SideOnly(Side.CLIENT)
   public Vec3d a(Minecraft mc, PreviewEntity previewEntity, EntityLivingBase livingBase, float f) {
      return PreviewRenderer.getRenderPosition(mc, previewEntity, livingBase, this, f);
   }

   public static GirlEntity getGirlByUuid(@Nonnull UUID uuid) {
      return getByUuidForSide(uuid, (Boolean)null);
   }

   public static GirlEntity getByUuidForSide(@Nonnull UUID uuid, Boolean flag) {
      try {
         Iterator iterator2 = getAllGirls().iterator();

         while (true) {
            GirlEntity girl;
            while (true) {
               while (true) {
                  if (!iterator2.hasNext()) {
                     return null;
                  }

                  girl = (GirlEntity)iterator2.next();

                  try {
                     if (girl.isDead) {
                        continue;
                     }
                     break;
                  } catch (ConcurrentModificationException error) {
                     throw rethrow(error);
                  }
               }

               try {
                  if (!uuid.equals(girl.getSexPlayerUuid())) {
                     continue;
                  }
                  break;
               } catch (ConcurrentModificationException error2) {
                  throw rethrow(error2);
               }
            }

            if (flag == null) {
               return girl;
            }

            boolean flag2 = girl.world.isRemote;

            try {
               if (flag2 && !flag) {
                  return girl;
               }
            } catch (ConcurrentModificationException error3) {
               throw rethrow(error3);
            }

            try {
               if (flag2 || !flag) {
                  continue;
               }
            } catch (ConcurrentModificationException error4) {
               throw rethrow(error4);
            }

            return girl;
         }
      } catch (ConcurrentModificationException error5) {
         return null;
      }
   }

   @Nullable
   public static GirlEntity getByUuid(@Nonnull UUID uuid) {
      boolean flag;
      label55: {
         try {
            if (FMLCommonHandler.instance().getMinecraftServerInstance() == null) {
               flag = true;
               break label55;
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }

         flag = false;
      }

      boolean flag2 = flag;

      try {
         for (GirlEntity girl : getAllGirls()) {
            try {
               if (girl.isDead) {
                  continue;
               }
            } catch (ConcurrentModificationException error2) {
               throw rethrow(error2);
            }

            boolean flag3 = girl.world.isRemote;

            try {
               if (flag3 != flag2) {
                  continue;
               }
            } catch (ConcurrentModificationException error3) {
               throw rethrow(error3);
            }

            if (uuid.equals(girl.getSexPlayerUuid())) {
               return girl;
            }
         }
      } catch (ConcurrentModificationException error4) {
      }

      return null;
   }

   public static GirlEntity getByPlayerUuid(@Nonnull EntityPlayer player) {
      return getByUuid(player.getPersistentID());
   }

   @SideOnly(Side.CLIENT)
   public void ac() {
   }

   public void resetAimTarget() {
      try {
         this.AimTarget = null;
         this.setNoGravity(false);
         this.setCurrentAction((GirlAnimationState)null);
         if (this.world.isRemote) {
            this.V();
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   @SideOnly(Side.CLIENT)
   protected void V() {
      try {
         if (this.isOwnedByLocalPlayer()) {
            AnimationInputLock.setAnimationLocked(true);
            Minecraft.getMinecraft().player.setInvisible(false);
            NetworkHandler.channel.sendToServer(new PacketResetGirl(this.getGirlUuid()));
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   @SideOnly(Side.CLIENT)
   public static void advanceAnimationState(UUID uuid) {
      try {
         Iterator iterator2 = getAllGirls().iterator();

         GirlEntity girl;
         while (true) {
            UUID uuid2;
            while (true) {
               if (!iterator2.hasNext()) {
                  return;
               }

               girl = (GirlEntity)iterator2.next();
               uuid2 = girl.getSexPlayerUuid();

               try {
                  if (uuid2 == null) {
                     continue;
                  }
                  break;
               } catch (ConcurrentModificationException error) {
                  throw rethrow(error);
               }
            }

            try {
               if (!uuid2.equals(uuid)) {
                  continue;
               }
               break;
            } catch (ConcurrentModificationException error2) {
               throw rethrow(error2);
            }
         }

         GirlAnimationState girlAnimationState = girl.c(girl.getCurrentAction());
         if (girlAnimationState != null) {
            girl.setCurrentAction(girlAnimationState);
         }
      } catch (ConcurrentModificationException error3) {
      }
   }

   @SideOnly(Side.CLIENT)
   public static void unregisterRemote(UUID uuid) {
      try {
         for (GirlEntity girl : getAllGirls()) {
            try {
               if (girl.isDead) {
                  continue;
               }
            } catch (ConcurrentModificationException error) {
               throw rethrow(error);
            }

            try {
               if (!girl.world.isRemote) {
                  continue;
               }
            } catch (ConcurrentModificationException error2) {
               throw rethrow(error2);
            }

            UUID uuid2 = girl.getSexPlayerUuid();

            try {
               if (uuid2 == null) {
                  continue;
               }
            } catch (ConcurrentModificationException error3) {
               throw rethrow(error3);
            }

            try {
               if (!uuid2.equals(uuid)) {
                  continue;
               }
            } catch (ConcurrentModificationException error4) {
               throw rethrow(error4);
            }

            GirlAnimationState girlAnimationState = girl.a(girl.getCurrentAction());

            try {
               if (girlAnimationState == null) {
                  continue;
               }
            } catch (ConcurrentModificationException error5) {
               throw rethrow(error5);
            }

            girl.setCurrentAction(girlAnimationState);
         }
      } catch (ConcurrentModificationException error6) {
      }
   }

   public void N() {
      this.resetTickOffset();
      NetworkHandler.channel.sendToServer(new PacketResetController(this.getGirlUuid()));
   }

   @SideOnly(Side.CLIENT)
   public void resetTickOffset() {
      this.ActionController.tickOffset = 0.0;
   }

   @SideOnly(Side.CLIENT)
   @Nullable
   protected abstract GirlAnimationState c(GirlAnimationState girlAnimationState);

   @SideOnly(Side.CLIENT)
   protected abstract GirlAnimationState a(GirlAnimationState girlAnimationState2);

   public TargetPoint P() {
      return new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 50.0);
   }

   protected void a(double d, double d2, double d3, float f, float f2) {
      try {
         if (this.getSexPlayerUuid() == null) {
            System.out.println("couldnt move camera because the player isn't set");
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      EntityPlayer player = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());

      try {
         if (this.AimTarget == null) {
            this.AimTarget = player.getPositionVector();
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      Vec3d vec3d = this.AimTarget;
      vec3d = vec3d.add(-Math.sin((this.AimYaw + 90.0F) * (Math.PI / 180.0)) * d, 0.0, Math.cos((this.AimYaw + 90.0F) * (Math.PI / 180.0)) * d);
      vec3d = vec3d.add(0.0, d2, 0.0);
      vec3d = vec3d.add(-Math.sin(this.AimYaw * (Math.PI / 180.0)) * d3, 0.0, Math.cos(this.AimYaw * (Math.PI / 180.0)) * d3);

      try {
         if (this.world.isRemote) {
            NetworkHandler.channel.sendToServer(new PacketTeleportPlayer(player.getPersistentID().toString(), vec3d, this.AimYaw + f, f2));
            return;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      player.setPositionAndRotation(vec3d.x, vec3d.y, vec3d.z, this.AimYaw + f, f2);
      player.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
   }

   @SideOnly(Side.CLIENT)

   protected boolean isOwnedByLocalPlayer() {
        boolean flag;
        block8: {
            block7: {
                try {
                    if (!this.world.isRemote) {
                        return false;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                try {
                    try {
                        if (!entityPlayerSP.getPersistentID().equals(this.getSexPlayerUuid()) && !entityPlayerSP.getUniqueID().equals(this.getSexPlayerUuid())) break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlEntity.rethrow(concurrentModificationException);
                    }
                    flag = true;
                    break block8;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
            }
            flag = false;
        }
        return flag;
    }

   protected void U() {
   }

   public void setCustomName(String string) {
      this.DataManager.set(CustomNameKey, string);
   }

   public String getCustomName() {
      return (String)this.DataManager.get(CustomNameKey);
   }

   public abstract String getDisplayName();

   public String getDisplayName() {
      String string = (String)this.DataManager.get(CustomNameKey);

      try {
         if (!"".equals(string)) {
            return string;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return this.getDisplayName();
   }

   public abstract float getRenderLabelOffset();

   @SideOnly(Side.CLIENT)
   public boolean t() {
      return true;
   }

   public void h(String string) {
      try {
         if (!this.world.isRemote) {
            NetworkHandler.channel
               .sendToAllAround(
                  new PacketSendChatMessage(String.format("<%s> %s", this.getDisplayName(), string), this.dimension, this.getGirlUuid()),
                  new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 40.0)
               );
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (this.isOwnedByLocalPlayer()) {
            NetworkHandler.channel.sendToServer(new PacketSendChatMessage(String.format("<%s> %s", this.getDisplayName(), string), this.dimension, this.getGirlUuid()));
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }
   }

   protected void b(String string, boolean flag) {
      try {
         if (!flag) {
            this.h(string);
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (!this.world.isRemote) {
            NetworkHandler.channel
               .sendToAllAround(
                  new PacketSendChatMessage(string, this.dimension, this.getGirlUuid()),
                  new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 40.0)
               );
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      try {
         if (this.isOwnedByLocalPlayer()) {
            NetworkHandler.channel.sendToServer(new PacketSendChatMessage(string, this.dimension, this.getGirlUuid()));
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }
   }

   protected void a(String string) {
      try {
         if (this.world.isRemote) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(String.format("<%s> %s", this.getDisplayName(), string)));
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   protected void a(UUID uuid, String string) {
      EntityPlayer player2 = this.world.getPlayerEntityByUUID(uuid);

      try {
         if (player2 == null) {
            System.out.println("Player with UUID " + uuid.toString() + " not found");
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (this.world.isRemote) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("<" + player2.getName() + "> " + string));
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }
   }

   public void a(SoundEvent sound, float f, float f2) {
      this.world
         .playSound(
            this.getPosition().getX(),
            this.getPosition().getY(),
            this.getPosition().getZ(),
            sound,
            SoundCategory.NEUTRAL,
            f,
            f2,
            false
         );
   }

   public void a(SoundEvent sound) {
      this.a(sound, 1.0F, 1.0F);
   }

   public void a(SoundEvent[] soundArray, int... ints) {
      try {
         if (ints.length == 0) {
            this.a(soundArray[this.getRNG().nextInt(soundArray.length)]);
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      this.a(soundArray[ints[this.getRNG().nextInt(ints.length)]], 1.0F, 1.0F);
   }

   public void a(SoundEvent[] soundArray, float f) {
      this.a(soundArray[this.getRNG().nextInt(soundArray.length)], f, 1.0F);
   }

   public void a(SoundEvent sound, float f) {
      this.a(sound, f, 1.0F);
   }

   public static boolean isGirlEntity(Entity entity) {
      try {
         if (entity == null) {
            return false;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (!(entity instanceof GirlEntity)) {
            return false;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      try {
         if (!(entity instanceof PlayerGirlEntity)) {
            return true;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      return false;
   }

   @SideOnly(Side.CLIENT)
   public GirlEntity E() {
      return this;
   }

   @SideOnly(Side.CLIENT)
   public boolean isLocalPlayerNearby() {
      EntityPlayer player2 = this.world.getClosestPlayerToEntity(this, 50.0);

      try {
         if (player2 == null) {
            return false;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return player2.getPersistentID().equals(Minecraft.getMinecraft().player.getPersistentID());
   }

   public Vec3d getPlayerFrontPos() {
      return this.a(1.0);
   }

   public Vec3d a(double d) {
      EntityPlayer player = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
      float f = player.rotationYaw;
      return player.getPositionVector().add(-Math.sin(f * (Math.PI / 180.0)) * d, 0.0, Math.cos(f * (Math.PI / 180.0)) * d);
   }

   public Vec3d a(Vec3d vec3d, float f) {
      return vec3d;
   }

   public static void spawnParticles(EnumParticleTypes particle, GirlEntity girl) {
      double d = ModConstants.Random.nextGaussian() * 0.02;
      double d2 = ModConstants.Random.nextGaussian() * 0.02;
      double d3 = ModConstants.Random.nextGaussian() * 0.02;
      girl.world
         .spawnParticle(
            particle,
            girl.posX + ModConstants.Random.nextFloat() * girl.width * 2.0F - girl.width,
            girl.posY + 0.5 + ModConstants.Random.nextFloat() * girl.height,
            girl.posZ + ModConstants.Random.nextFloat() * girl.width * 2.0F - girl.width,
            d,
            d2,
            d3,
            new int[0]
         );
   }

   public static void spawnParticles(EnumParticleTypes particle, GirlEntity girl, int i) {
      int i2 = 0;

      try {
         while (i2 < i) {
            spawnParticles(particle, girl);
            i2++;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   public AnimationFactory getFactory() {
      return this.Factory;
   }

   public boolean canBePushed() {
      return false;
   }

   @SideOnly(Side.CLIENT)

   protected SoundEvent getAmbientSound() {
        block6: {
            block7: {
                try {
                    try {
                        try {
                            if (this.getRNG().nextInt(10000) != 0) break block6;
                            if (!this.world.isRemote) break block7;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlEntity.rethrow(concurrentModificationException);
                        }
                        if (!(Minecraft.getMinecraft().player.getPositionVector().distanceTo(this.getPositionVector()) < 10.0)) break block7;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GirlEntity.rethrow(concurrentModificationException);
                    }
                    this.a("whopa");
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
            }
            return ModSounds.pickRandomSound(ModSounds.MISC_FART);
        }
        return null;
    }

   public float T() {
      return 0.0F;
   }

   public float ai() {
      return 0.0F;
   }

   @SideOnly(Side.CLIENT)

   public MatrixStack a(String string, boolean flag) {
        Object object;
        ArrayList<GeoBone> arrayList;
        GeoBone geoBone;
        block17: {
            block16: {
                IBone iBone;
                block14: {
                    block15: {
                        try {
                            if (this.BoneProcessor == null) {
                                this.BoneProcessor = this.b();
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlEntity.rethrow(concurrentModificationException);
                        }
                        iBone = this.BoneProcessor.getBone(string);
                        try {
                            try {
                                if (iBone != null) break block14;
                                if (GirlGeoModel.CamBones.contains(string)) break block15;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GirlEntity.rethrow(concurrentModificationException);
                            }
                            Main.LOGGER.log(Level.WARN, String.format("The bone '%s' does not exist on %s. Bone model matrix couldn't be calculated", string, this.getDisplayName()));
                            this.AnchorNames.remove(string);
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GirlEntity.rethrow(concurrentModificationException);
                        }
                    }
                    return new MatrixStack();
                }
                geoBone = (GeoBone)iBone;
                arrayList = new ArrayList<GeoBone>();
                object = geoBone;
                while (((GeoBone)object).parent != null) {
                    GeoBone geoBone2 = ((GeoBone)object).parent;
                    arrayList.add(geoBone2);
                    object = geoBone2;
                }
                Collections.reverse(arrayList);
                object = new MatrixStack();
                try {
                    if (!this.Q()) break block16;
                    ((MatrixStack)object).rotateY((float)(-Math.toRadians(this.I().floatValue())));
                    break block17;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
            }
            try {
                if (flag) {
                    ((MatrixStack)object).rotateY((float)(-Math.toRadians(LerpMath.lerp(this.prevRenderYawOffset, this.renderYawOffset, Minecraft.getMinecraft().getRenderPartialTicks()))));
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        for (GeoBone geoBone3 : arrayList) {
            ((MatrixStack)object).translate(geoBone3);
            ((MatrixStack)object).moveToPivot(geoBone3);
            ((MatrixStack)object).rotate(geoBone3);
            ((MatrixStack)object).scale(geoBone3);
            ((MatrixStack)object).moveBackFromPivot(geoBone3);
        }
        ((MatrixStack)object).translate(geoBone);
        ((MatrixStack)object).moveToPivot(geoBone);
        ((MatrixStack)object).rotate(geoBone);
        ((MatrixStack)object).scale(geoBone);
        object = this.a((MatrixStack)object);
        return object;
    }

   protected MatrixStack a(MatrixStack matrixStack) {
      return matrixStack;
   }

   @SideOnly(Side.CLIENT)
   public Vec3d getModelBone(String string) {
      Vec3d vec3d = this.AnchorPoints.get(string);

      try {
         if (vec3d != null) {
            return vec3d;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (!this.AnchorNames.contains(string)) {
            this.AnchorNames.add(string);
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      return Vec3d.ZERO;
   }

   @SideOnly(Side.CLIENT)
   public Vec3d d(String string) {
      return this.getModelBone(string).add(this.getPositionVector());
   }

   public void a(String string, Vec3d vec3d) {
      this.AnchorPoints.put(string, vec3d);
   }

   @SideOnly(Side.CLIENT)
   public float R() {
      AnimationProcessor animationProcessor = this.b();
      IBone iBone = animationProcessor.getBone("girlCam");

      try {
         if (iBone == null) {
            return 0.0F;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      float f = iBone.getPivotY();
      f = this.a(f);
      return f / 16.0F;
   }

   @SideOnly(Side.CLIENT)
   public float getScale() {
      return 1.0F;
   }

   protected float a(float f) {
      return f;
   }

   public AnimatedGeoModel<? extends GirlEntity> a() {
      Minecraft mc = Minecraft.getMinecraft();
      Render render = mc.getRenderManager().getEntityRenderObject(this);

      try {
         if (render == null) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (!(render instanceof GeoGirlRenderer)) {
            return null;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      GeoEntityRenderer renderer = (GeoEntityRenderer)render;
      GeoModelProvider geoModelProvider = renderer.getGeoModelProvider();

      try {
         if (geoModelProvider == null) {
            return null;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      try {
         if (!(geoModelProvider instanceof AnimatedGeoModel)) {
            return null;
         }
      } catch (ConcurrentModificationException error4) {
         throw rethrow(error4);
      }

      return (AnimatedGeoModel<? extends GirlEntity>)geoModelProvider;
   }

   public AnimationProcessor<?> b() {
      return this.a().getAnimationProcessor();
   }

   public boolean h(int i) {
      ArrayList list = this.D();

      try {
         if (list.size() - 1 < i) {
            return false;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if ((Integer)list.get(i) == 101) {
            return true;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      return false;
   }

   public Vec2i getSlotColor(int i) {
      return Vec2i.ZERO;
   }


   public void a(List<Integer> list) {
        block5: {
            try {
                try {
                    if (this instanceof GirlEffectEntity || this instanceof GirlPlayerHomeEntity) break block5;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : list) {
            GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, i);
        }
        this.DataManager.set(GirlEffectEntity.M, (Object)stringBuilder.toString());
    }


   public String F() {
        block4: {
            try {
                try {
                    if (!(this instanceof GirlEffectEntity) && !(this instanceof GirlPlayerHomeEntity)) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                return (String)this.DataManager.get(GirlEffectEntity.M);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        return "";
    }

   public static String colorsToString(List<Integer> list) {
      StringBuilder sb = new StringBuilder();

      for (int i : list) {
         sb.append(i);
         sb.append("-");
      }

      return sb.toString();
   }

   public static List<Integer> stringToColors(String string) {
      ArrayList list = new ArrayList();
      String[] stringArray = string.split("-");

      for (String string2 : stringArray) {
         list.add(Integer.parseInt(string2));
      }

      return list;
   }


   public static List<Integer> getModelColors(UUID uuid) {
        ArrayList<Integer> arrayList;
        block6: {
            GirlEntity girl;
            try {
                girl = Main.proxy instanceof ClientProxy ? GirlEntity.getClientSideByUuid(uuid) : GirlEntity.getServerSideByUuid(uuid);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
            GirlEntity girl2 = girl;
            arrayList = new ArrayList<Integer>(girl2.L());
            try {
                try {
                    if (!(girl2 instanceof GirlEffectEntity) && !(girl2 instanceof GirlPlayerHomeEntity)) break block6;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                arrayList.addAll(GirlEntity.stringToColors((String)girl2.getDataManager().get(GirlEffectEntity.M)));
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
        return arrayList;
    }

   public ArrayList<Integer> L() {
      return new ArrayList<>();
   }

   public List<Entry<GirlBodySlot, Entry<List<String>, Integer>>> d(UUID uuid) {
      try {
         if (this.OutfitData != null) {
            return this.OutfitData;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      ArrayList list = this.D();

      try {
         if (list.isEmpty()) {
            this.OutfitData = new ArrayList<>();
            return this.OutfitData;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      ArrayList list2 = new ArrayList();
      List list3 = getModelColors(uuid);
      int i = 0;

      try {
         while (i < list.size()) {
            list2.add(new SimpleEntry<>(GirlBodySlot.GIRL_SPECIFIC, new SimpleEntry<>(this.e((Integer)list.get(i)), list3.get(i))));
            i++;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      this.OutfitData = list2;
      return list2;
   }

   public void b(List<Entry<GirlBodySlot, Entry<List<String>, Integer>>> uuid) {
      this.OutfitData = uuid;
   }

   public void a(int i, int i2) {
      try {
         if (this.OutfitData == null) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (this.OutfitData.size() - 1 < i) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      Entry entry = this.OutfitData.get(i);
      ((Entry)entry.getValue()).setValue(i2);
      this.OutfitData.set(i, entry);
   }


   public void handleModelCommand(String string) {
        block4: {
            try {
                try {
                    if (!(this instanceof GirlEffectEntity) && !(this instanceof GirlPlayerHomeEntity)) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw GirlEntity.rethrow(concurrentModificationException);
                }
                this.DataManager.set(GirlEffectEntity.M, (Object)string);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw GirlEntity.rethrow(concurrentModificationException);
            }
        }
    }

   private List<String> e(int i) {
      ArrayList list = new ArrayList();
      int i2 = 0;

      try {
         while (i2 < i) {
            list.add("");
            i2++;
         }

         return list;
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   public ArrayList<Integer> D() {
      return new ArrayList<>();
   }

   public List<Integer> u() {
      return new ArrayList<>();
   }

   public void setCustomModel(String string) {
      this.DataManager.set(CustomModelKey, string);
   }

   public String C() {
      return (String)this.DataManager.get(CustomModelKey);
   }

   public static String joinModelNames(HashSet<String> set) {
      try {
         if (set == null) {
            return "";
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (set.isEmpty()) {
            return "";
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      StringBuilder sb = new StringBuilder();

      for (String string : set) {
         sb.append(string);
         sb.append("#");
      }

      return sb.toString();
   }

   public HashSet<String> Y() {
      String string = this.C();
      String[] stringArray = string.split("#");
      HashSet set = new HashSet();

      for (String string2 : stringArray) {
         try {
            if ("".equals(string2)) {
               continue;
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }

         try {
            if ("cross".equals(string2)) {
               continue;
            }
         } catch (ConcurrentModificationException error2) {
            throw rethrow(error2);
         }

         set.add(string2);
      }

      return set;
   }

   @SideOnly(Side.CLIENT)
   public boolean H() {
      return true;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public enum WalkState {
      WALK,
      FAST_WALK,
      RUN;
   }
}
