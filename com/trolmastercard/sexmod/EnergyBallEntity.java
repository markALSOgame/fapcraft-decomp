package com.trolmastercard.sexmod;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnergyBallEntity extends EntityLiving {
   public static final float d = 0.4F;
   public static final float e = 0.3F;
   static final int b = 200;
   static final int k = 100;
   static final float a = 0.5F;
   static final float l = 0.15F;
   public static final float j = 0.75F;
   public double g = 1.0;
   Vec3d Velocity = Vec3d.ZERO;
   boolean Charged = false;
   boolean Detonated = true;
   GalathNpc OwnerGalath;

   public EnergyBallEntity(World world) {
      super(world);
      this.setSize(0.5F, 0.5F);
   }

   public EnergyBallEntity(World world, GalathNpc galath) {
      super(world);
      this.setSize(0.5F, 0.5F);
      this.OwnerGalath = galath;
   }

   public EnergyBallEntity(World world, GalathNpc galath, Vec3d vec3d) {
      this(world);
      this.Velocity = vec3d;
      this.OwnerGalath = galath;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void collideWithEntity(Entity entity) {
   }

   public void onUpdate() {
      try {
         if (this.isDead) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         this.noClip = true;
         this.setNoGravity(true);
         this.motionX = this.Velocity.x;
         this.motionY = this.Velocity.y;
         this.motionZ = this.Velocity.z;
         super.onUpdate();
         if (this.world.isRemote) {
            this.a();
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         this.c();
         if (this.world.isAirBlock(this.getPosition())) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      this.b();
      this.world.removeEntity(this);
   }

   void c() {
      try {
         if (this.world.isRemote) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!this.Charged) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Vec3d vec3d = this.getPositionVector();
      Vec3d vec3d2 = vec3d.subtract(0.75, 0.75, 0.75);
      Vec3d vec3d3 = vec3d.add(0.75, 0.75, 0.75);
      AxisAlignedBB bbox = new AxisAlignedBB(
         vec3d2.x, vec3d2.y, vec3d2.z, vec3d3.x, vec3d3.y, vec3d3.z
      );
      List<GalathNpc> list = this.world.getEntitiesWithinAABB(GalathNpc.class, bbox);

      try {
         if (list.isEmpty()) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true);

      for (GalathNpc galath : list) {
         galath.f(this.getPositionVector());
      }

      this.world.removeEntity(this);
   }

   void a() {
      this.a(
         LerpMath.lerp(this.lastTickPosX, this.posX, 0.5),
         LerpMath.lerp(this.lastTickPosY, this.posY, 0.5),
         LerpMath.lerp(this.lastTickPosZ, this.posZ, 0.5)
      );
      this.a(this.posX, this.posY, this.posZ);
   }

   void a(double d, double d2, double d3) {
      Random random = this.getRNG();
      this.world
         .spawnParticle(
            EnumParticleTypes.DRAGON_BREATH,
            d + random.nextDouble() * 0.3F,
            d2 + 0.25 + random.nextDouble() * 0.3F,
            d3 + random.nextDouble() * 0.3F,
            0.0,
            0.0,
            0.0,
            new int[0]
         );
   }

   void b() {
      try {
         if (this.world.isRemote) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (this.isDead) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (!this.Detonated) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      Vec3d vec3d = new Vec3d(this.posX, this.getPosition().getY() + 1, this.posZ);

      try {
         if (!this.b(vec3d)) {
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, true);
            this.Detonated = false;
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      EntityWitherSkeleton entityWitherSkeleton = new EntityWitherSkeleton(this.world);
      entityWitherSkeleton.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
      entityWitherSkeleton.setPositionAndUpdate(vec3d.x, vec3d.y, vec3d.z);
      this.world.spawnEntity(entityWitherSkeleton);
      NetworkHandler.channel.sendToAllTracking(new PacketSpawnEnergyBallParticles(vec3d, true), this);
      this.OwnerGalath.bI.add(entityWitherSkeleton);
   }

   boolean b(Vec3d vec3d) {
      try {
         if (this.OwnerGalath == null) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      EntityLivingBase livingBase = this.OwnerGalath.getTargetEntity();

      try {
         if (livingBase == null) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (livingBase.getDistance(vec3d.x, vec3d.y, vec3d.z) < 15.0) {
            return true;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      return false;
   }

   @SideOnly(Side.CLIENT)
   public static void spawnSmokeRingParticles(Vec3d vec3d) {
      WorldClient worldClient = Minecraft.getMinecraft().world;
      float f = AngleMath.degToRadians(1.8F);
      Random random = ModConstants.Random;

      for (float f2 = 0.0F; f2 < Math.PI * 2; f2 += f) {
         double d = Math.sin(f2);
         double d2 = Math.cos(f2);
         double d3 = vec3d.x + d * 0.5;
         double d4 = d * 0.15F;
         double d5 = vec3d.z + d2 * 0.5;
         double d6 = d2 * 0.15F;
         double d7 = vec3d.y;
         double d8 = random.nextDouble() * 0.15F;
         worldClient.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d3, d7, d5, d4, d8, d6, new int[0]);
      }
   }

   @SideOnly(Side.CLIENT)
   public static void spawnDragonBreathParticles(Vec3d vec3d) {
      WorldClient worldClient = Minecraft.getMinecraft().world;
      Random random = ModConstants.Random;
      int i = 0;

      try {
         while (i < 100) {
            worldClient.spawnParticle(
               EnumParticleTypes.DRAGON_BREATH,
               vec3d.x,
               vec3d.y,
               vec3d.z,
               random.nextDouble() * 0.15F,
               random.nextDouble() * 0.15F,
               random.nextDouble() * 0.15F,
               new int[0]
            );
            i++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      worldClient.playSound(
         vec3d.x, vec3d.y, vec3d.z, ModSounds.MISC_SHATTER[0], SoundCategory.AMBIENT, 0.7F, 1.0F, false
      );
   }

   public boolean attackEntityFrom(DamageSource damage, float f) {
      try {
         if (DamageSource.OUT_OF_WORLD.equals(damage)) {
            this.setHealth(0.0F);
            this.Detonated = false;
            this.world.removeEntity(this);
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      label53: {
         try {
            if (!this.world.isRemote && "arrow".equals(damage.damageType)) {
               break label53;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         Entity entity = damage.getTrueSource();

         try {
            if (!(entity instanceof EntityPlayer)) {
               return false;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         this.Velocity = entity.getLookVec();
         this.Charged = true;
         return true;
      }

      this.setHealth(0.0F);
      this.Detonated = false;
      NetworkHandler.channel.sendToAllTracking(new PacketSpawnEnergyBallParticles(this.getPositionVector(), false), this);
      Entity entity2 = damage.getImmediateSource();

      try {
         if (entity2 != null) {
            this.world.removeEntity(entity2);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      this.world.removeEntity(this);
      return true;
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      this.world.removeEntity(this);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
