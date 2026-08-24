package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ItemGalathCoin extends Item implements IAnimatable {
   public static final ItemGalathCoin Instance = new ItemGalathCoin();
   public static final long ActivationMs = 4000L;
   public static final long DeactivationMs = 1000L;
   public static final long DesummonAnimMs = 3000L;
   public static final float q = 0.1F;
   public static final float p = -0.01F;
   public static final float e = 0.0015F;
   public static final float k = 2.0F;
   public static final float h = 1.5F;
   public static final float d = 0.03F;
   public static final float s = 100.0F;
   public static final float l = 0.2F;
   public static final float o = 1.5F;
   public static final String KeyActivationTime = "sexmod:galath_coin_activation_time";
   public static final String KeyDeactivationTime = "sexmod:galath_coin_deactivation_time";
   public static final String KeyDesummonAnimTime = "sexmod:galath_coin_de_summoning_animation_time";
   public static final String DefeatMessage = "Defeating a succubus makes her accept the victor as her master, granting him a coin to which her soul is bound. Using the coin summons her, offering services on demand. If her master uses the coin on her or goes too far, she returns to the coin";
   private final AnimationFactory AnimFactory = new AnimationFactory(this);
   AnimationController<ItemGalathCoin> AnimController;

   public ItemGalathCoin() {
      this.maxStackSize = 1;
   }

   public static void registerAll() {
      Instance.setRegistryName("sexmod", "galath_coin");
      Instance.setTranslationKey("galath_coin");
      MinecraftForge.EVENT_BUS.register(ItemGalathCoin.class);
   }

   @SubscribeEvent
   public static void registerItem(Register<Item> register2) {
      register2.getRegistry().register(Instance);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void registerItemModel(ModelRegistryEvent model) {
      ModelLoader.setCustomModelResourceLocation(Instance, 0, new ModelResourceLocation("sexmod:galath_coin"));
      Instance.setTileEntityItemStackRenderer(new GeoModelDrawer());
   }

   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      NBTTagCompound tagCompound = player.getEntityData();
      ActionResult actionResult = new ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand));

      try {
         if (tagCompound.getLong("sexmod:galath_coin_deactivation_time") != 0L) {
            return actionResult;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (tagCompound.getLong("sexmod:galath_coin_activation_time") != 0L) {
            return actionResult;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      try {
         if (!this.a(world, player)) {
            world.playSound(
               player.posX, player.posY, player.posZ, ModSounds.MISC_BEEW[0], SoundCategory.PLAYERS, 1.0F, 1.0F, false
            );
            return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      world.playSound(
         player.posX, player.posY, player.posZ, ModSounds.MISC_WEOWEO[1], SoundCategory.PLAYERS, 1.0F, 1.0F, false
      );
      tagCompound.setLong("sexmod:galath_coin_activation_time", System.currentTimeMillis());
      return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
   }


   boolean a(World world, EntityPlayer player) {
        boolean flag;
        block6: {
            boolean flag2;
            block8: {
                block7: {
                    try {
                        try {
                            if (world.isRemote) break block6;
                            if (GalathOwnershipData.hasOwnershipData(player.getPersistentID())) break block7;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw ItemGalathCoin.rethrow(concurrentModificationException);
                        }
                        flag2 = true;
                        break block8;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ItemGalathCoin.rethrow(concurrentModificationException);
                    }
                }
                flag2 = false;
            }
            return flag2;
        }
        try {
            flag = !GalathOwnershipData.f;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        return flag;
    }

   @SubscribeEvent
   public void a(EntityInteract entityInteract) {
      EntityPlayer player = entityInteract.getEntityPlayer();
      ItemStack stack = player.getHeldItem(entityInteract.getHand());

      try {
         if (!Instance.equals(stack.getItem())) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      Entity entity = entityInteract.getTarget();

      try {
         if (!(entity instanceof GalathNpc)) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      GalathNpc galath = (GalathNpc)entity;

      try {
         if (!player.getPersistentID().equals(galath.O())) {
            return;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      player.world
         .playSound(
            player.posX, player.posY, player.posZ, ModSounds.MISC_WEOWEO[0], SoundCategory.PLAYERS, 1.0F, 1.0F, false
         );
      player.getEntityData().setLong("sexmod:galath_coin_deactivation_time", System.currentTimeMillis());
      entityInteract.setCanceled(true);
   }


   public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean flag) {
        long l;
        long l5;
        long l6;
        EntityPlayer entityPlayer;
        block10: {
            try {
                super.onUpdate(stack, world, entity, i, flag);
                if (!(entity instanceof EntityPlayer)) {
                    return;
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ItemGalathCoin.rethrow(concurrentModificationException);
            }
            entityPlayer = (EntityPlayer)entity;
            NBTTagCompound nBTTagCompound = entityPlayer.getEntityData();
            l6 = nBTTagCompound.getLong(KeyActivationTime);
            l5 = nBTTagCompound.getLong(KeyDeactivationTime);
            l = System.currentTimeMillis();
            try {
                try {
                    this.b(entityPlayer, nBTTagCompound, l, l6);
                    this.a(entityPlayer, nBTTagCompound, l, l5);
                    if (l5 == 0L || l <= l5 + 4000L) break block10;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ItemGalathCoin.rethrow(concurrentModificationException);
                }
                nBTTagCompound.setLong(KeyDeactivationTime, 0L);
                nBTTagCompound.setBoolean(KeyDesummonAnimTime, false);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ItemGalathCoin.rethrow(concurrentModificationException);
            }
        }
        try {
            if (!world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        this.a(entityPlayer, l, l6);
        this.b(entityPlayer, l, l5);
    }

   @SideOnly(Side.CLIENT)

   void b(EntityPlayer player, long l, long l2) {
        try {
            if (l2 == 0L) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        if (l <= l2 + 1000L || l >= l2 + 3000L) return;
        GalathNpc galath = null;
        try {
            for (GirlEntity girl3 : GirlEntity.getAllGirls()) {
                if (girl3.isDead || !girl3.world.isRemote || !(girl3 instanceof GalathNpc) || !player.equals((Object)girl3.getSexPlayer())) continue;
                galath = (GalathNpc)girl3;
                break;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        try {
            if (galath == null) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        Vec3d vec3d = galath.getTargetPos().add(0.0, 1.5, 0.0);
        Vec3d vec3d2 = player.getPositionVector().add(0.0, (double)player.getEyeHeight(), 0.0);
        int i = player.getHeldItemMainhand().getItem().equals(Instance) ? 1 : -1;
        Vec3d vec3d3 = vec3d2.add(VectorMath.rotatePitch((float)i * 0.1f, (double)(-0.01f + player.rotationPitch * 0.0015f), 0.0, player.renderYawOffset));
        float f = (float)(l - l2 - 1000L) / 2000.0f;
        Vec3d vec3d4 = LerpMath.lerpVec3d(vec3d, vec3d3, (double)f);
        DragonBreathParticles.ParticleScale = 0.2f;
        Minecraft.getMinecraft().effectRenderer.addEffect((Particle)new DragonBreathParticles(player.world, vec3d4.x, vec3d4.y, vec3d4.z));
    }

   @SideOnly(Side.CLIENT)
   void a(EntityPlayer player2) {
      try {
         if (!Minecraft.getMinecraft().player.getPersistentID().equals(player2.getPersistentID())) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      GalathOwnershipData.f = true;
   }

   @SideOnly(Side.CLIENT)

   void a(EntityPlayer player, long l, long l2) {
        int i;
        Vec3d vec3d;
        block6: {
            try {
                try {
                    if (l > l2 + 1000L && l < l2 + 3000L) break block6;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ItemGalathCoin.rethrow(concurrentModificationException);
                }
                return;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ItemGalathCoin.rethrow(concurrentModificationException);
            }
        }
        Vec3d vec3d2 = player.getPositionVector().add(0.0, (double)player.getEyeHeight(), 0.0);
        try {
            vec3d = vec3d2;
            i = player.getHeldItemMainhand().getItem().equals(Instance) ? 1 : -1;
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        Vec3d vec3d3 = vec3d.add(VectorMath.rotatePitch((float)i * 0.1f, (double)(-0.01f + player.rotationPitch * 0.0015f), 0.0, player.renderYawOffset));
        Vec3d vec3d4 = vec3d2.add(player.getLookVec().normalize().scale(2.0));
        float f = (float)(l - l2 - 1000L) / 2000.0f;
        Vec3d vec3d5 = LerpMath.lerpVec3d(vec3d3, vec3d4, (double)f);
        DragonBreathParticles.ParticleScale = 0.2f;
        Minecraft.getMinecraft().effectRenderer.addEffect((Particle)new DragonBreathParticles(player.world, vec3d5.x, vec3d5.y, vec3d5.z));
    }

   @SubscribeEvent
   public void a(PlayerChangedDimensionEvent playerChangedDimensionEvent) {
      EntityPlayer player2 = playerChangedDimensionEvent.player;

      try {
         if (player2.world.isRemote) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      UUID uuid = GalathOwnershipData.getGalathByPlayer(player2);
      GirlEntity girl = GirlEntity.getServerSideByUuid(uuid);

      try {
         if (girl == null) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      GalathOwnershipData.releaseOwnedGalath((GalathNpc)girl);
      NetworkHandler.channel.sendTo(new PacketInformOfOwnership(false), (EntityPlayerMP)player2);
   }

   void b(EntityPlayer player, NBTTagCompound tagCompound, long l, long l2) {
      try {
         if (l2 == 0L) {
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      try {
         if (l - l2 <= 4000L) {
            return;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      tagCompound.setLong("sexmod:galath_coin_activation_time", 0L);
      Vec3d vec3d = player.getPositionVector().add(0.0, player.getEyeHeight(), 0.0);
      Vec3d vec3d2 = vec3d.add(player.getLookVec().normalize().scale(2.0));
      Random random = player.getRNG();
      int i = 0;

      try {
         while (i < 100.0F) {
            player.world
               .spawnParticle(
                  EnumParticleTypes.DRAGON_BREATH,
                  vec3d2.x,
                  vec3d2.y,
                  vec3d2.z,
                  (2.0F * random.nextFloat() - 1.0F) * 0.2F,
                  (2.0F * random.nextFloat() - 1.0F) * 0.2F,
                  (2.0F * random.nextFloat() - 1.0F) * 0.2F,
                  new int[0]
               );
            i++;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      World world2 = player.world;

      try {
         if (world2.isRemote) {
            this.a(player);
            return;
         }
      } catch (ConcurrentModificationException error4) {
         throw rethrow(error4);
      }

      GalathNpc galath = new GalathNpc(player.world, player, vec3d2);

      try {
         galath.setPositionAndUpdate(vec3d2.x, vec3d2.y, vec3d2.z);
         GalathOwnershipData.setOwnershipByPlayer(player, galath);
         player.world.spawnEntity(galath);
         if (GalathOwnershipData.isOwnerOnline(player.getPersistentID())) {
            galath.getScale();
         }
      } catch (ConcurrentModificationException error5) {
         throw rethrow(error5);
      }
   }

   void d(EntityPlayer player) {
      try {
         if (player.world.isRemote) {
            this.b(player);
            return;
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }

      this.c(player);
   }

   void c(EntityPlayer player) {
      UUID uuid = GalathOwnershipData.getGalathByPlayer(player);
      GirlEntity girl = GirlEntity.getServerSideByUuid(uuid);

      try {
         if (girl instanceof GalathNpc) {
            desummonGalath((GalathNpc)girl);
         }
      } catch (ConcurrentModificationException error) {
         throw rethrow(error);
      }
   }

   public static void desummonGalath(GalathNpc galath) {
      galath.setCurrentAction(GirlAnimationState.GALATH_DE_SUMMON);
      galath.aC();
      galath.getChildMangle(true);
      galath.setTargetPos(galath.getPositionVector());
      galath.b(galath.rotationYaw);
   }

   @SideOnly(Side.CLIENT)
   void b(EntityPlayer player) {
      GalathNpc galath = null;

      try {
         label69: {
            Iterator iterator2 = GirlEntity.getAllGirls().iterator();

            GirlEntity girl;
            while (true) {
               while (true) {
                  while (true) {
                     while (true) {
                        if (!iterator2.hasNext()) {
                           break label69;
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
                        if (!girl.world.isRemote) {
                           continue;
                        }
                        break;
                     } catch (ConcurrentModificationException error2) {
                        throw rethrow(error2);
                     }
                  }

                  try {
                     if (!(girl instanceof GalathNpc)) {
                        continue;
                     }
                     break;
                  } catch (ConcurrentModificationException error3) {
                     throw rethrow(error3);
                  }
               }

               try {
                  if (!player.equals(girl.getSexPlayer())) {
                     continue;
                  }
                  break;
               } catch (ConcurrentModificationException error4) {
                  throw rethrow(error4);
               }
            }

            galath = (GalathNpc)girl;
         }
      } catch (ConcurrentModificationException error5) {
      }

      try {
         if (galath == null) {
            return;
         }
      } catch (ConcurrentModificationException error6) {
         throw rethrow(error6);
      }

      spawnDesummonParticles(player, galath);
   }

   @SideOnly(Side.CLIENT)
   public static void spawnDesummonParticles(UUID uuid, GalathNpc galath) {
      World world2 = galath.world;

      Vec3d vec3d;
      label34: {
         try {
            if (galath.Q()) {
               vec3d = galath.getTargetPos();
               break label34;
            }
         } catch (ConcurrentModificationException error) {
            throw rethrow(error);
         }

         vec3d = galath.getPositionVector();
      }

      Vec3d vec3d2 = vec3d;
      Vec3d vec3d3 = vec3d2.add(0.0, 1.5, 0.0);
      Random random = galath.getRNG();

      for (int i = 0; i < 100.0F; i++) {
         Vec3d vec3d4 = new Vec3d((random.nextFloat() * 2.0F - 1.0F) * 1.5F, (random.nextFloat() * 2.0F - 1.0F) * 1.5F, (random.nextFloat() * 2.0F - 1.0F) * 1.5F);
         Vec3d vec3d5 = vec3d3.add(vec3d4);
         Vec3d vec3d6 = vec3d4.scale(-0.03F);
         world2.spawnParticle(
            EnumParticleTypes.DRAGON_BREATH,
            vec3d5.x,
            vec3d5.y,
            vec3d5.z,
            vec3d6.x,
            vec3d6.y,
            vec3d6.z,
            new int[0]
         );
      }

      try {
         if (Minecraft.getMinecraft().player.getPersistentID().equals(uuid)) {
            GalathOwnershipData.f = false;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }
   }

   public static void spawnDesummonParticles(EntityPlayer player, GalathNpc galath) {
      spawnDesummonParticles(player.getPersistentID(), galath);
   }


   void a(EntityPlayer player, NBTTagCompound tagCompound, long l, long l2) {
        World world;
        block19: {
            int i;
            long l3;
            long l7;
            block21: {
                block20: {
                    try {
                        if (l2 == 0L) {
                            return;
                        }
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ItemGalathCoin.rethrow(concurrentModificationException);
                    }
                    long l8 = l - l2;
                    world = player.world;
                    boolean flag = tagCompound.getBoolean(KeyDesummonAnimTime);
                    try {
                        try {
                            if (flag) break block19;
                            l7 = l8;
                            l3 = 1000L;
                            if (!world.isRemote) break block20;
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw ItemGalathCoin.rethrow(concurrentModificationException);
                        }
                        i = 0;
                        break block21;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw ItemGalathCoin.rethrow(concurrentModificationException);
                    }
                }
                i = 150;
            }
            try {
                if (l7 > l3 - (long)i) {
                    tagCompound.setBoolean(KeyDesummonAnimTime, true);
                    this.d(player);
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ItemGalathCoin.rethrow(concurrentModificationException);
            }
        }
        try {
            if (world.isRemote) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        try {
            if (l - l2 <= 3000L) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        UUID uUID = GalathOwnershipData.getGalathByPlayer(player);
        GirlEntity girl = GirlEntity.getServerSideByUuid(uUID);
        try {
            if (!(girl instanceof GalathNpc)) {
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemGalathCoin.rethrow(concurrentModificationException);
        }
        GalathOwnershipData.releaseOwnedGalath((GalathNpc)girl);
    }

   public void registerControllers(AnimationData animationData) {
      this.AnimController = new AnimationController(this, "controller", 0.0F, this::a);
      animationData.addAnimationController(this.AnimController);
   }

   @SideOnly(Side.CLIENT)

   protected <segs extends IAnimatable> PlayState a(AnimationEvent<segs> animEvent) {
        block4: {
            NBTTagCompound nBTTagCompound = Minecraft.getMinecraft().player.getEntityData();
            try {
                try {
                    if (nBTTagCompound.getLong(KeyActivationTime) != 0L || nBTTagCompound.getLong(KeyDeactivationTime) != 0L) break block4;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw ItemGalathCoin.rethrow(concurrentModificationException);
                }
                animEvent.getController().clearAnimationCache();
                return PlayState.STOP;
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                throw ItemGalathCoin.rethrow(concurrentModificationException);
            }
        }
        this.AnimController.setAnimation(new AnimationBuilder().addAnimation("animation.galath_coin.summon", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        return PlayState.CONTINUE;
    }

   public AnimationFactory getFactory() {
      return this.AnimFactory;
   }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
