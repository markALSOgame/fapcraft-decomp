package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class KoboldEggEntity extends EntityLivingBase implements IAnimatable {
   static final int HatchDuration = 12000;
   private final AnimationFactory AnimationFactory = new AnimationFactory(this);
   public UUID TribeUuid = null;
   static AnimationController<KoboldEggEntity> AnimationController;
   public static final DataParameter<String> EggColorKey = EntityDataManager.createKey(KoboldEggEntity.class, DataSerializers.STRING).getSerializer().createKey(115);
   public static final DataParameter<Integer> EggAgeKey = EntityDataManager.createKey(KoboldEggEntity.class, DataSerializers.VARINT).getSerializer().createKey(116);

   public KoboldEggEntity(World world) {
      super(world);
      this.setSize(0.5F, 0.5F);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(EggColorKey, KoboldNpc.DefaultTribeColor.toString());
      this.dataManager.register(EggAgeKey, 0);
   }

   public void onUpdate() {
      super.onUpdate();
      int i = (Integer)this.dataManager.get(EggAgeKey);

      try {
         if (i >= 12000) {
            this.hatch();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!this.world.isRemote) {
            this.dataManager.set(EggAgeKey, i + 1);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   public boolean canTrample(World world, Block block, BlockPos pos, float f) {
      return false;
   }

   public boolean attackEntityFrom(DamageSource damage, float f) {
      boolean flag = super.attackEntityFrom(damage, f);

      try {
         if (!flag) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.setDead();
      return true;
   }


   void hatch() {
        int i = 0;
        while (true) {
            int i2;
            int i3;
            int i4;
            block23: {
                block22: {
                    try {
                        try {
                            if (i >= 30) break;
                            if (!ModConstants.Random.nextBoolean()) break block22;
                        }
                        catch (RuntimeException runtimeException) {
                            throw KoboldEggEntity.rethrow(runtimeException);
                        }
                        i4 = 1;
                        break block23;
                    }
                    catch (RuntimeException runtimeException) {
                        throw KoboldEggEntity.rethrow(runtimeException);
                    }
                }
                i4 = -1;
            }
            float f = (float)i4 * ModConstants.Random.nextFloat();
            try {
                i3 = ModConstants.Random.nextBoolean() ? 1 : -1;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldEggEntity.rethrow(runtimeException);
            }
            float f2 = (float)i3 * ModConstants.Random.nextFloat();
            try {
                i2 = ModConstants.Random.nextBoolean() ? 1 : -1;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldEggEntity.rethrow(runtimeException);
            }
            float f3 = (float)i2 * ModConstants.Random.nextFloat();
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, 0.5 + this.posX, 0.5 + this.posY, 0.5 + this.posZ, (double)f, (double)f2, (double)f3, new int[0]);
            ++i;
        }
        try {
            if (this.world.isRemote) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldEggEntity.rethrow(runtimeException);
        }
        try {
            if (this.TribeUuid == null) {
                this.TribeUuid = UUID.randomUUID();
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldEggEntity.rethrow(runtimeException);
        }
        KoboldNpc kobold = KoboldNpc.create(this.world, this.TribeUuid);
        GirlHomeBuilder.registerKoboldInTribe(this.TribeUuid, kobold);
        UUID uUID = GirlHomeBuilder.getTribeMasterUuid(this.TribeUuid);
        try {
            if (uUID != null) {
                kobold.getDataManager().set(GirlEntity.MasterUuidKey, (Object)uUID.toString());
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldEggEntity.rethrow(runtimeException);
        }
        List<KoboldNpc> list = GirlHomeBuilder.getKobolds(this.TribeUuid);
        String string = null;
        for (KoboldNpc kobold2 : list) {
            String string2 = (String)kobold2.getDataManager().get(KoboldNpc.TribeNameKey);
            if ("".equals(string2)) continue;
            string = string2;
            break;
        }
        try {
            if (string != null) {
                kobold.getDataManager().set(KoboldNpc.TribeNameKey, string);
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldEggEntity.rethrow(runtimeException);
        }
        kobold.setPosition(0.5 + this.posX, this.posY, 0.5 + this.posZ);
        this.world.spawnEntity((Entity)kobold);
        this.announceTribeJoin(kobold);
        this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 0.5f, 1.0f);
        this.world.removeEntity((Entity)this);
    }

   void announceTribeJoin(KoboldNpc kobold) {
      EntityPlayer player = kobold.getSexPlayer();

      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      EntityPlayerMP serverPlayer = (EntityPlayerMP)player;
      EyeAndKoboldColor eyeColor = GirlHomeBuilder.getTribeColor(this.TribeUuid);
      player.sendMessage(
         new TextComponentString(
            String.format(
               "%s%s %shas become a %snew tribe member%s!", eyeColor.getTextColor(), kobold.getDisplayName(), TextFormatting.WHITE, TextFormatting.RED, TextFormatting.WHITE
            )
         )
      );
      serverPlayer.connection
         .sendPacket(
            new SPacketSoundEffect(SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.NEUTRAL, player.posX, player.posY, player.posZ, 1.0F, 1.0F)
         );
      serverPlayer.connection
         .sendPacket(
            new SPacketSoundEffect(SoundEvents.ENTITY_FIREWORK_TWINKLE_FAR, SoundCategory.NEUTRAL, player.posX, player.posY, player.posZ, 1.0F, 1.0F)
         );
   }

   public void registerControllers(AnimationData animationData) {
      AnimationController = new AnimationController(this, "controller", 5.0F, this::selectAnimation);
      animationData.addAnimationController(AnimationController);
   }

   public AnimationFactory getFactory() {
      return this.AnimationFactory;
   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      try {
         if (this.TribeUuid != null) {
            tagCompound.setString("tribeID", this.TribeUuid.toString());
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      tagCompound.setString("egg_color", (String)this.dataManager.get(EggColorKey));
      tagCompound.setInteger("eggAge", (Integer)this.dataManager.get(EggAgeKey));
      super.writeEntityToNBT(tagCompound);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      String string = tagCompound.getString("tribeID");

      try {
         if (!"".equals(string)) {
            this.TribeUuid = UUID.fromString(string);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.dataManager.set(EggColorKey, tagCompound.getString("egg_color"));
      this.dataManager.set(EggAgeKey, tagCompound.getInteger("eggAge"));
   }

   protected <E extends IAnimatable> PlayState selectAnimation(AnimationEvent<E> animEvent) {
      int i = (Integer)this.dataManager.get(EggAgeKey);

      try {
         if (12000 - i < 20) {
            animEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.hatch", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      float f = i / 12000.0F;

      try {
         if (f > 0.98) {
            animEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.veryfast", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (f > 0.85) {
            animEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.fast", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (f > 0.75) {
            animEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.medium", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         if (f > 0.5) {
            animEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.slow", true));
            return PlayState.CONTINUE;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      return PlayState.CONTINUE;
   }

   public Iterable<ItemStack> getArmorInventoryList() {
      return new ArrayList<>();
   }

   public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityEquipmentSlot) {
      return ItemStack.EMPTY;
   }

   public void setItemStackToSlot(EntityEquipmentSlot entityEquipmentSlot, ItemStack stack) {
   }

   public EnumHandSide getPrimaryHand() {
      return EnumHandSide.LEFT;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
