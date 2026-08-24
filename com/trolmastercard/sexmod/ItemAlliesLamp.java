package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ItemAlliesLamp extends Item implements IAnimatable {
   static final String InUseTag = "sexmodAllieInUse";
   static final String InUseTicksTag = "sexmodAllieInUseTicks";
   public static final String UsesTag = "sexmodUses";
   public static final String AllieIdTag = "sexmodAllieID";
   static final Integer c = 95;
   static final Integer k = 50;
   public static final int a = 150;
   public static final float f = 0.75F;
   public static final ItemAlliesLamp Instance = new ItemAlliesLamp();
   private final AnimationFactory AnimFactory = new AnimationFactory(this);
   AnimationController<ItemAlliesLamp> AnimController;

   public ItemAlliesLamp() {
      this.setCreativeTab(CreativeTabs.MISC);
      this.maxStackSize = 1;
   }

   public static void registerAll() {
      Instance.setRegistryName("sexmod", "allies_lamp");
      Instance.setTranslationKey("allies_lamp");
      MinecraftForge.EVENT_BUS.register(ItemAlliesLamp.class);
   }

   @SubscribeEvent
   public static void registerItem(Register<Item> register2) {
      register2.getRegistry().register(Instance);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void registerItemModel(ModelRegistryEvent model) {
      ModelLoader.setCustomModelResourceLocation(Instance, 0, new ModelResourceLocation("sexmod:allies_lamp"));
      Instance.setTileEntityItemStackRenderer(new CustomSkinRenderer());
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(Pre pre) {
      NBTTagCompound tagCompound = Minecraft.getMinecraft().player.getEntityData();

      try {
         if (tagCompound.getBoolean("sexmodAllieInUse")) {
            pre.setCanceled(true);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @SubscribeEvent
   public void a(LootTableLoadEvent lootTableLoadEvent) {
      HashSet set = new HashSet();
      set.add(LootTableList.CHESTS_ABANDONED_MINESHAFT);
      set.add(LootTableList.CHESTS_DESERT_PYRAMID);
      set.add(LootTableList.CHESTS_SIMPLE_DUNGEON);
      set.add(LootTableList.CHESTS_WOODLAND_MANSION);
      if (set.contains(lootTableLoadEvent.getName())) {
         LootPool lootPool = lootTableLoadEvent.getTable().getPool("pool3");
         if (lootPool == null) {
            lootPool = lootTableLoadEvent.getTable().getPool("pool2");
         }

         try {
            if (lootPool != null) {
               lootPool.addEntry(new LootEntryItem(Instance, 5, 0, new LootFunction[0], new LootCondition[0], "sexmod:allies_lamp"));
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }
   }

   public void registerControllers(AnimationData animationData) {
      this.AnimController = new AnimationController(this, "controller", 2.0F, this::a);
      animationData.addAnimationController(this.AnimController);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag iTooltipFlag) {
      NBTTagCompound tagCompound = stack.getTagCompound();

      try {
         if (tagCompound == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      int i = 3 - stack.getTagCompound().getInteger("sexmodUses");

      label42: {
         try {
            switch (i) {
               case 0:
                  break;
               case 1:
                  break label42;
               case 2:
                  list.add("2 wishes left");
                  return;
               default:
                  return;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         list.add("no wishes left");
         return;
      }

      list.add("1 wish left");
   }

   @SideOnly(Side.CLIENT)
   protected <segs extends IAnimatable> PlayState a(AnimationEvent<segs> animEvent) {
      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
      NBTTagCompound tagCompound = mcPlayer.getEntityData();
      boolean flag = tagCompound.getBoolean("sexmodAllieInUse");

      try {
         if (!flag) {
            animEvent.getController().clearAnimationCache();
            return PlayState.STOP;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      animEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lamp.rub", EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
      return PlayState.CONTINUE;
   }


   public void onUpdate(ItemStack stack, World world2, Entity entity, int i, boolean flag) {
        NBTTagCompound nBTTagCompound;
        block26: {
            GirlAnimationState girlAnimationState;
            AllieNpc allie;
            AllieNpc allie2;
            block25: {
                Vec3d vec3d;
                int i2;
                NBTTagCompound nBTTagCompound2;
                EntityPlayer entityPlayer;
                block24: {
                    block23: {
                        try {
                            if (!(entity instanceof EntityPlayer)) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw ItemAlliesLamp.rethrow(runtimeException);
                        }
                        entityPlayer = (EntityPlayer)entity;
                        nBTTagCompound2 = entity.getEntityData();
                        try {
                            try {
                                if (stack.equals(entityPlayer.getHeldItemMainhand()) || stack.equals(entityPlayer.getHeldItemOffhand())) break block23;
                            }
                            catch (RuntimeException runtimeException) {
                                throw ItemAlliesLamp.rethrow(runtimeException);
                            }
                            return;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ItemAlliesLamp.rethrow(runtimeException);
                        }
                    }
                    boolean flag2 = nBTTagCompound2.getBoolean(InUseTag);
                    i2 = nBTTagCompound2.getInteger(InUseTicksTag);
                    try {
                        if (!flag2) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemAlliesLamp.rethrow(runtimeException);
                    }
                    try {
                        nBTTagCompound2.setInteger(InUseTicksTag, i2 + 1);
                        if (i2 <= k || i2 >= c) break block24;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemAlliesLamp.rethrow(runtimeException);
                    }
                    double d = (float)(i2 - k) / (float)(c - k);
                    d = LerpMath.EaseInOutSine(d);
                    vec3d = new Vec3d(0.0, (double)entityPlayer.eyeHeight * (1.0 - d), 0.0);
                    BedLogic.a(world2, EnumParticleTypes.CRIT_MAGIC, this.a(entityPlayer).add(vec3d), (int)(d * 150.0), d * 0.75, d);
                }
                try {
                    if (i2 < c) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ItemAlliesLamp.rethrow(runtimeException);
                }
                try {
                    BedLogic.spawnParticles(world2, EnumParticleTypes.CRIT_MAGIC, this.a(entityPlayer), 150, 0.75, 2.0);
                    nBTTagCompound2.setBoolean(InUseTag, false);
                    nBTTagCompound2.setInteger(InUseTicksTag, 0);
                    if (world2.isRemote) {
                        AnimationInputLock.setAnimationLocked(false);
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw ItemAlliesLamp.rethrow(runtimeException);
                }
                nBTTagCompound = stack.getTagCompound();
                if (nBTTagCompound == null) {
                    nBTTagCompound = new NBTTagCompound();
                }
                nBTTagCompound.setInteger(UsesTag, nBTTagCompound.getInteger(UsesTag) + 1);
                allie2 = new AllieNpc(entityPlayer.world, entityPlayer.getHeldItemMainhand());
                allie2.handleGirlUuidEvent(entityPlayer.getPersistentID());
                vec3d = this.a(entityPlayer);
                allie2.setPositionAndRotation(vec3d.x, vec3d.y, vec3d.z, entityPlayer.rotationYaw + 180.0f, entityPlayer.rotationPitch);
                allie2.setTargetPos(allie2.getPositionVector());
                allie2.b(entityPlayer.rotationYaw + 180.0f);
                allie2.a(true);
                allie2.setNoGravity(true);
                allie2.noClip = true;
                entityPlayer.world.spawnEntity((Entity)allie2);
                BlockPos blockPos = allie2.getPosition().add(0, -1, 0);
                try {
                    if (!allie2.world.getBlockState(blockPos).getBlock().equals(Blocks.SAND)) break block25;
                    allie2.b(GirlAnimationState.SUMMON_SAND);
                    break block26;
                }
                catch (RuntimeException runtimeException) {
                    throw ItemAlliesLamp.rethrow(runtimeException);
                }
            }
            try {
                allie = allie2;
                girlAnimationState = allie2.isFirstTimeWithItem() ? GirlAnimationState.SUMMON : GirlAnimationState.SUMMON_NORMAL;
            }
            catch (RuntimeException runtimeException) {
                throw ItemAlliesLamp.rethrow(runtimeException);
            }
            allie.b(girlAnimationState);
        }
        stack.setTagCompound(nBTTagCompound);
    }

   Vec3d a(EntityPlayer player) {
      return player.getPositionVector().add(VectorMath.rotateYaw(new Vec3d(0.0, 0.0, 2.0), player.rotationYawHead));
   }

   public AnimationFactory getFactory() {
      return this.AnimFactory;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class a {
      @SubscribeEvent
      public void a(PlayerLoggedOutEvent playerLoggedOutEvent) {
         playerLoggedOutEvent.player.getEntityData().setBoolean("sexmodAllieInUse", false);
      }

      @SubscribeEvent

      public void a(RightClickItem rightClickItem) {
         EntityPlayer entityPlayer = rightClickItem.getEntityPlayer();
         EnumHand enumHand = rightClickItem.getHand();
         ItemStack itemStack = entityPlayer.getHeldItem(enumHand);
         try {
            if (PlayerGirlEntity.isPlayerGirl(entityPlayer)) {
               return;
            }
         }
         catch (ConcurrentModificationException concurrentModificationException) {
            throw ItemAlliesLamp.a.rethrow(concurrentModificationException);
         }
         if (entityPlayer.world.isRemote && !AnimationInputLock.isAnimationLocked()) {
            return;
         }
         if (!entityPlayer.world.isRemote) {
            try {
               for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
                  AllieNpc allie;
                  ItemStack itemStack2;
                  if (girl2.isDead || !(girl2 instanceof AllieNpc) || !itemStack.equals(itemStack2 = (allie = (AllieNpc)girl2).getDataManager().get(AllieNpc.SpawnItemStack))) continue;
                  return;
               }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
               // empty catch block
            }
         }
         if (itemStack.getItem() != Instance) {
            return;
         }
         NBTTagCompound nBTTagCompound = itemStack.getTagCompound();
         if (nBTTagCompound != null && nBTTagCompound.getInteger(ItemAlliesLamp.UsesTag) >= 3) {
            return;
         }
         NBTTagCompound playerData = entityPlayer.getEntityData();
         boolean flag = playerData.getBoolean(ItemAlliesLamp.InUseTag);
         if (flag) {
            return;
         }
         playerData.setBoolean(ItemAlliesLamp.InUseTag, true);
         playerData.setInteger(ItemAlliesLamp.InUseTicksTag, 0);
      }

      private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
         return error;
      }
   }
}
