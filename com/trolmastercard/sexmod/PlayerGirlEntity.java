package com.trolmastercard.sexmod;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Vector2f;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class PlayerGirlEntity extends InventoryGirlEntity {
   public static final String CustomModelTag = "sexmod:CustomModel";
   public static final String GirlSpecificTag = "sexmod:GirlSpecific";
   public static final float ac = 0.0F;
   public static final int am = 100;
   public static final int Y = 65;
   public static boolean FeatureEnabled = true;
   public Vector2f ao = new Vector2f(0.0F, 0.0F);
   public boolean ad = false;
   public boolean aj = false;
   public boolean ak = false;
   public boolean af = true;
   public boolean ah = false;
   protected static final DataParameter<Optional<UUID>> BoundPlayerKey = EntityDataManager.createKey(GirlEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID)
      .getSerializer()
      .createKey(118);
   public static Hashtable<UUID, PlayerGirlEntity> PlayerGirls = new Hashtable<>();
   public static List<PlayerGirlEntity> AllPlayerGirls = new ArrayList<>();
   int JoinTickCounter = -1;
   public boolean Accept = true;

   protected PlayerGirlEntity(World world) {
      super(world);
      this.setSize(0.01F, 0.01F);
      AllPlayerGirls.add(this);
   }

   protected PlayerGirlEntity(World world, UUID uuid) {
      this(world);
      this.DataManager.set(BoundPlayerKey, Optional.of(uuid));
   }

   @Nullable
   public static PlayerGirlEntity getByUuid(UUID uuid) {
      return PlayerGirls.get(uuid);
   }

   @Nullable
   public static PlayerGirlEntity getByPlayer(@Nonnull EntityPlayer player) {
      return PlayerGirls.get(player.getPersistentID());
   }

   @Nullable
   public static PlayerGirlEntity getServerSideByUuid(UUID uuid) {
      try {
         for (GirlEntity girl : ad()) {
            try {
               if (girl.world.isRemote) {
                  continue;
               }
            } catch (ConcurrentModificationException error) {
               throw rethrow(error);
            }

            try {
               if (!(girl instanceof PlayerGirlEntity)) {
                  continue;
               }
            } catch (ConcurrentModificationException error2) {
               throw rethrow(error2);
            }

            PlayerGirlEntity playerGirl = (PlayerGirlEntity)girl;
            if (uuid.equals(playerGirl.getBoundPlayerUuid())) {
               return playerGirl;
            }
         }
      } catch (ConcurrentModificationException error3) {
      }

      return null;
   }

   @Override
   public TargetPoint P() {
      return new TargetPoint(this.dimension, this.posX, this.posY - 0.0, this.posZ, 50.0);
   }

   public void a(int i, GirlAnimationState girlAnimationState) {
      NetworkHandler.channel.sendToAllTracking(new PacketForcePlayerGirlUpdate(this.getBoundPlayerUuid(), i, girlAnimationState), this.P());
   }

   public EntityPlayer c(EntityPlayer player) {
      return player;
   }

   public boolean z() {
      return true;
   }

   public Vec3d c(Vec3d vec3d, float f) {
      return vec3d;
   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean v() {
      return true;
   }

   public boolean q() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void H() {
   }

   public boolean canStartInteraction() {
      return true;
   }

   public boolean a(String string) {
      return false;
   }

   public boolean A() {
      return true;
   }

   @Override
   public String c() {
      if (((Optional)this.DataManager.get(BoundPlayerKey)).isPresent()) {
         EntityPlayer player = this.world.getPlayerEntityByUUID((UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get());

         try {
            if (player != null) {
               return player.getName();
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }
      }

      return "anonymous horny girl";
   }

   public void u() {
   }

   public abstract void b(String string, UUID uuid);

   public abstract ModelPartProvider a(int i2);

   public abstract String c(int i3);

   public Vec3i b(int i) {
      return new Vec3i(255, 255, 255);
   }

   @Override
   public boolean canBePushed() {
      return false;
   }

   public boolean isNotColliding() {
      return true;
   }

   public boolean F() {
      return false;
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.DataManager.register(BoundPlayerKey, Optional.absent());
   }

   @SideOnly(Side.CLIENT)
   public static void i() {
      PlayerGirlEntity playerGirl = getByUuid(Minecraft.getMinecraft().player.getPersistentID());

      try {
         if (playerGirl == null) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      playerGirl.resetAimTarget();
   }

   @Override
   public void r() {
      try {
         this.AimTarget = null;
         this.setNoGravity(false);
         if (this.world.isRemote) {
            this.V();
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   protected void V() {
      try {
         if (!this.isOwnedByLocalPlayer() && !this.isBoundToLocalPlayer()) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      AnimationInputLock.setAnimationLocked(true);
      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
      mcPlayer.setInvisible(false);
      mcPlayer.setNoGravity(false);
      mcPlayer.noClip = false;
      this.DataManager.set(BusyKey, false);
      NetworkHandler.channel.sendToServer(new PacketResetGirl(this.isBoundToLocalPlayer()));
   }

   @SideOnly(Side.CLIENT)
   @Override

   public boolean H() {
    }

   protected void c(boolean flag) {
      try {
         if (!FeatureEnabled) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (this.getBoundPlayerUuid() == null) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player = this.world.getPlayerEntityByUUID(this.getBoundPlayerUuid());

      try {
         if (player == null) {
            return;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      try {
         player.capabilities.allowFlying = flag;
         if (!flag) {
            player.capabilities.isFlying = false;
         }
      } catch (ConcurrentModificationException error4) {
         throw rethrow(error4);
      }

      player.sendPlayerAbilities();
   }

   public static boolean hasGirl(UUID uuid) {
      C();

      for (Entry entry : PlayerGirls.entrySet()) {
         UUID uuid2 = (UUID)entry.getKey();

         try {
            if (uuid.equals(uuid2)) {
               return true;
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }
      }

      return false;
   }

   public static boolean isPlayerGirl(EntityPlayer player) {
      try {
         if (player == null) {
            return false;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return hasGirl(player.getPersistentID());
   }

   public AxisAlignedBB getEntityBoundingBox() {
      return super.getEntityBoundingBox().offset(0.0, 0.5, 0.0);
   }

   protected EntityPlayer j() {
      List list = this.world.playerEntities;
      EntityPlayer player = null;

      for (EntityPlayer player2 : list) {
         try {
            if (player2.getPersistentID().equals(((Optional)this.DataManager.get(BoundPlayerKey)).get())) {
               continue;
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }

         if (player == null) {
            player = player2;
         } else {
            double d = player.getDistanceSq(this.w().x, this.w().y, this.w().z);
            double d2 = player2.getDistanceSq(this.w().x, this.w().y, this.w().z);
            if (d2 < d) {
               player = player2;
            }
         }
      }

      return player;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public boolean e() {
      EntityPlayer player2 = this.getRenderPosition();

      try {
         if (player2 == null) {
            return false;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return player2.getPersistentID().equals(Minecraft.getMinecraft().player.getPersistentID());
   }

   public Vec3d w() {
      return new Vec3d(this.posX, this.posY - 0.0, this.posZ);
   }

   protected void b(UUID uuid) {
      EntityPlayerMP serverPlayer = (EntityPlayerMP)this.world.getPlayerEntityByUUID(uuid);
      EntityPlayerMP serverPlayer2 = (EntityPlayerMP)this.world.getPlayerEntityByUUID((UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get());
      NetworkHandler.channel.sendTo(new PacketSetPlayerMovement(false), serverPlayer);
      NetworkHandler.channel.sendTo(new PacketSetPlayerMovement(false), serverPlayer2);
      this.hasGirl(uuid);
      this.rotationYaw = 0.0F;
      this.rotationYawHead = 0.0F;
      serverPlayer.rotationYaw = 180.0F;
      serverPlayer.rotationYawHead = 180.0F;
      serverPlayer.setNoGravity(true);
      serverPlayer.noClip = true;
      Vec3d vec3d = this.getPositionVector();
      serverPlayer.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z + 1.0);
      serverPlayer.capabilities.isFlying = true;
      serverPlayer2.capabilities.isFlying = true;
      this.teleportPlayerInFront(uuid);
      this.DataManager.set(BusyKey, true);
      this.setTargetPos(vec3d);
      this.b(0.0F);
   }

   protected void playStepSound(BlockPos pos, Block block) {
      super.playStepSound(pos, block);
   }

   public AxisAlignedBB a(EntityPlayer player) {
      return player.getEntityBoundingBox();
   }

   @Override
   public void onUpdate() {
      try {
         this.noClip = true;
         this.setNoGravity(true);
         super.onUpdate();
         this.D();
         if (!this.world.isRemote) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (this.isBoundToLocalPlayer()) {
            ClientChatHandler.Entry.a();
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }
   }

   @SideOnly(Side.CLIENT)
   void h() {
      Minecraft.getMinecraft().player.eyeHeight = this.getEyeHeight();
   }

   @SideOnly(Side.CLIENT)
   public boolean isBoundToLocalPlayer() {
      try {
         if (!((Optional)this.DataManager.get(BoundPlayerKey)).isPresent()) {
            return false;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return ((UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get()).equals(Minecraft.getMinecraft().player.getPersistentID());
   }

   public boolean E() {
      return false;
   }

   void d(EntityPlayer player) {
      NBTTagCompound tagCompound = player.getEntityData();
      String string = tagCompound.getString("sexmod:CustomModel" + GirlRegistry.getByEntity(this));
      this.f(string);
   }

   @Override

   public void updateAITasks() {
        block17: {
            Object object;
            EntityPlayer entityPlayer;
            block16: {
                PlayerGirlEntity.C();
                this.isInteractionAllowed();
                this.G();
                UUID uUID = this.getBoundPlayerUuid();
                try {
                    if (uUID == null) {
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PlayerGirlEntity.rethrow(concurrentModificationException);
                }
                entityPlayer = this.world.getPlayerEntityByUUID(uUID);
                try {
                    if (entityPlayer == null) {
                        this.setPositionAndUpdate(this.posX, 0.0, this.posZ);
                        return;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PlayerGirlEntity.rethrow(concurrentModificationException);
                }
                this.d(entityPlayer);
                if (this.Q()) {
                    Vec3d vec3d = this.getTargetPos();
                    this.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
                } else {
                    this.setPositionAndUpdate(entityPlayer.posX, entityPlayer.posY + 0.0, entityPlayer.posZ);
                }
                object = this.getCurrentAction();
                try {
                    try {
                        if (object != GirlAnimationState.NULL || !entityPlayer.isSwingInProgress) break block16;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PlayerGirlEntity.rethrow(concurrentModificationException);
                    }
                    this.b(GirlAnimationState.ATTACK);
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PlayerGirlEntity.rethrow(concurrentModificationException);
                }
            }
            try {
                try {
                    if (object != GirlAnimationState.ATTACK || entityPlayer.isSwingInProgress) break block17;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PlayerGirlEntity.rethrow(concurrentModificationException);
                }
                this.b(GirlAnimationState.NULL);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw PlayerGirlEntity.rethrow(concurrentModificationException);
            }
        }
    }


   void D() {
        block18: {
            int i;
            PlayerGirlEntity playerGirl;
            block20: {
                block19: {
                    try {
                        if (this.JoinTickCounter == -1) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PlayerGirlEntity.rethrow(concurrentModificationException);
                    }
                    try {
                        try {
                            try {
                                ++this.JoinTickCounter;
                                if (this.world.isRemote || this.JoinTickCounter != 65) break block18;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw PlayerGirlEntity.rethrow(concurrentModificationException);
                            }
                            playerGirl = this;
                            if (this.getOutfitIndex() != 0) break block19;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw PlayerGirlEntity.rethrow(concurrentModificationException);
                        }
                        i = 1;
                        break block20;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PlayerGirlEntity.rethrow(concurrentModificationException);
                    }
                }
                i = 0;
            }
            playerGirl.f(i);
        }
        try {
            if (this.JoinTickCounter < 100) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw PlayerGirlEntity.rethrow(concurrentModificationException);
        }
        try {
            if (this.getCurrentAction() != GirlAnimationState.STRIP) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw PlayerGirlEntity.rethrow(concurrentModificationException);
        }
        try {
            if (this.world.isRemote) {
                this.isOwnedByLocalPlayer();
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw PlayerGirlEntity.rethrow(concurrentModificationException);
        }
        this.b(GirlAnimationState.NULL);
    }

   @SideOnly(Side.CLIENT)
   void n() {
      if (this.isBoundToLocalPlayer()) {
         Minecraft mc = Minecraft.getMinecraft();
         mc.gameSettings.thirdPersonView = 0;
         mc.entityRenderer.loadEntityShader(mc.getRenderViewEntity());
         AnimationInputLock.setAnimationLocked(true);
      }
   }

   public boolean o() {
      return this.Q();
   }

   public Vec3d b(Vec3d vec3d, float f) {
      return vec3d;
   }

   public boolean a(GirlAnimationState girlAnimationState, EntityPlayer player) {
      return false;
   }

   public boolean isInteractionAllowed() {
      return true;
   }

   public void startInteraction(EntityPlayer player) {
   }

   @Override

   public void b(GirlAnimationState girlAnimationState) {
        block11: {
            int i;
            block13: {
                block12: {
                    block10: {
                        try {
                            try {
                                try {
                                    if (this.world.isRemote || girlAnimationState != GirlAnimationState.NULL) break block10;
                                }
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw PlayerGirlEntity.rethrow(concurrentModificationException);
                                }
                                if (!this.Q()) break block10;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw PlayerGirlEntity.rethrow(concurrentModificationException);
                            }
                            System.out.println("prevented a potential animation break");
                            return;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw PlayerGirlEntity.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.STRIP) break block11;
                            PlayerGirlEntity playerGirl = this;
                            if (!this.world.isRemote) break block12;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw PlayerGirlEntity.rethrow(concurrentModificationException);
                        }
                        i = 5;
                        break block13;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PlayerGirlEntity.rethrow(concurrentModificationException);
                    }
                }
                i = 0;
            }
            playerGirl.JoinTickCounter = i;
        }
        super.b(girlAnimationState);
    }

   void syncEquipment(EntityPlayer player) {
      this.DataManager.set(HelmetKey, ItemStack.EMPTY);
      this.DataManager.set(ChestKey, ItemStack.EMPTY);
      this.DataManager.set(PantsKey, ItemStack.EMPTY);
      this.DataManager.set(BootsKey, ItemStack.EMPTY);

      for (ItemStack stack : player.getArmorInventoryList()) {
         try {
          if (stack.getItem() instanceof ItemElytra) {
             this.DataManager.set(ChestKey, stack);
               continue;
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }

         try {
            if (!(stack.getItem() instanceof ItemArmor)) {
               continue;
            }
         } catch (ConcurrentModificationException error2) {
            throw rethrow(error2);
         }

         ItemArmor armor = (ItemArmor)stack.getItem();

         label43: {
            label42: {
               try {
                  switch (armor.getEquipmentSlot()) {
                     case HEAD:
                        this.DataManager.set(HelmetKey, stack);
                        continue;
                     case CHEST:
                        break label43;
                     case LEGS:
                        break label42;
                     case FEET:
                        break;
                     default:
                        continue;
                  }
               } catch (ConcurrentModificationException error3) {
                  throw rethrow(error3);
               }

               this.DataManager.set(BootsKey, stack);
               continue;
            }

            this.DataManager.set(PantsKey, stack);
            continue;
         }

          this.DataManager.set(ChestKey, stack);
      }
   }

   public UUID getBoundPlayerUuid() {
      try {
         return ((Optional)this.DataManager.get(BoundPlayerKey)).isPresent() ? (UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get() : null;
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   @Nullable
   public EntityPlayer getBoundPlayer() {
      UUID uuid = this.getBoundPlayerUuid();

      try {
         if (uuid == null) {
            return null;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      return this.world.getPlayerEntityByUUID(uuid);
   }

   public void a(Optional<UUID> optional) {
      this.DataManager.set(BoundPlayerKey, optional);
   }

   public void y() {
   }

   public void B() {
   }

   public static void C() {
      ArrayList list = new ArrayList();

      try {
         for (PlayerGirlEntity playerGirl : AllPlayerGirls) {
            try {
               if (playerGirl.getBoundPlayerUuid() != null) {
                  PlayerGirls.put(playerGirl.getBoundPlayerUuid(), playerGirl);
                  list.add(playerGirl);
               }
            } catch (ConcurrentModificationException error) {
               throw rethrow(error);
            }
         }
      } catch (ConcurrentModificationException error2) {
      }

      for (PlayerGirlEntity playerGirl2 : list) {
         AllPlayerGirls.remove(playerGirl2);
      }

      t();
   }

   static void t() {
      ArrayList list = new ArrayList();

      for (Entry entry : PlayerGirls.entrySet()) {
         try {
            if (((PlayerGirlEntity)entry.getValue()).isDead) {
               list.add(entry.getKey());
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }
      }

      for (UUID uuid : list) {
         PlayerGirls.remove(uuid);
      }
   }

   protected boolean c(UUID uuid) {
      try {
         if (uuid == null) {
            return false;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity playerGirl = getByUuid(uuid);

      try {
         if (playerGirl != null) {
            return true;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      return false;
   }

   @Override
   public void a(String string, UUID uuid) {
      try {
         if (this.a(string)) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (!((Optional)this.DataManager.get(BoundPlayerKey)).isPresent()) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      NetworkHandler.channel.sendToServer(new PacketSexPrompt(string, uuid, (UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get(), this.Accept));
      this.Accept = true;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setString("owner", ((UUID)((Optional)this.DataManager.get(BoundPlayerKey)).get()).toString());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      this.DataManager.set(BoundPlayerKey, Optional.of(UUID.fromString(tagCompound.getString("owner"))));
      AllPlayerGirls.add(this);
   }

   @Override
   public void a(SoundEvent sound, float f, float f2) {
      Vec3d vec3d = this.getCustomName();

      try {
         if (this.world.isRemote) {
            this.world.playSound(vec3d.x, vec3d.y, vec3d.z, sound, SoundCategory.NEUTRAL, f, f2, false);
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      this.world.playSound(null, new BlockPos(vec3d.x, vec3d.y, vec3d.z), sound, SoundCategory.PLAYERS, f, f2);
   }

   @Override
   public void a(SoundEvent sound) {
      this.a(sound, 1.0F, 1.0F);
   }

   public void a(SoundEvent[] soundArray) {
      this.a(soundArray[this.getRNG().nextInt(soundArray.length)], 1.0F, 1.0F);
   }

   @Override
   public void a(SoundEvent sound, float f) {
      this.a(sound, f, 1.0F);
   }

   @Override
   protected void U() {
   }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
