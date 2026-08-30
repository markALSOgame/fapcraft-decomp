package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLunaRod extends ItemFishingRod {
   public static final ItemLunaRod Instance = new ItemLunaRod();

   public ItemLunaRod() {
      this.setMaxDamage(64);
      this.setMaxStackSize(1);
      this.addPropertyOverride(new ResourceLocation("cast"), new IItemPropertyGetter() {
         @SideOnly(Side.CLIENT)
         public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase livingBase) {
            try {
               if (livingBase == null) {
                  return 0.0F;
               }
            } catch (RuntimeException error) {
               throw error;
            }

            try {
               if (!(livingBase instanceof LunaNpc)) {
                  return 0.0F;
               }
            } catch (RuntimeException error2) {
               throw error2;
            }

            try {
               if ((Boolean)livingBase.getDataManager().get(LunaNpc.IsBoundKey)) {
                  return 1.0F;
               }
            } catch (RuntimeException error3) {
               throw error3;
            }

            return 0.0F;
         }

      });
   }

   public static void registerAll() {
      Instance.setRegistryName("sexmod", "luna_rod");
      Instance.setTranslationKey("luna_rod");
      MinecraftForge.EVENT_BUS.register(ItemLunaRod.class);
   }

   @SubscribeEvent
   public static void registerItem(Register<Item> register2) {
      register2.getRegistry().register(Instance);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void registerItemModel(ModelRegistryEvent model) {
      ModelLoader.setCustomModelResourceLocation(Instance, 0, new ModelResourceLocation("fishing_rod"));
   }

   public ActionResult<ItemStack> a(World world, LunaNpc luna, EnumHand hand) {
      ItemStack stack = luna.getHeldItem(hand);
      if (luna.Familiar != null) {
         int i = luna.Familiar.getRodDamage();
         stack.damageItem(i, luna);
         luna.swingArm(hand);
         world.playSound(
            (EntityPlayer)null,
            luna.posX,
            luna.posY,
            luna.posZ,
            SoundEvents.ENTITY_BOBBER_RETRIEVE,
            SoundCategory.NEUTRAL,
            1.0F,
            0.4F / (itemRand.nextFloat() * 0.4F + 0.8F)
         );
      } else {
         world.playSound(
            (EntityPlayer)null,
            luna.posX,
            luna.posY,
            luna.posZ,
            SoundEvents.ENTITY_BOBBER_THROW,
            SoundCategory.NEUTRAL,
            0.5F,
            0.4F / (itemRand.nextFloat() * 0.4F + 0.8F)
         );
         if (!world.isRemote) {
            LunaFamiliarEntity.SummonerNpc = luna;
            double d = luna.getPositionVector().distanceTo(new Vec3d(luna.MoveTargetPos.getX(), luna.MoveTargetPos.getY(), luna.MoveTargetPos.getZ()));
            LunaFamiliarEntity familiar = new LunaFamiliarEntity(world, luna, d * LunaNpc.FamiliarSpawnScale);
            int i2 = EnchantmentHelper.getFishingSpeedBonus(stack);

            try {
               if (i2 > 0) {
                  familiar.setLureSpeed(i2);
               }
            } catch (RuntimeException error) {
               throw error;
            }

            int i3 = EnchantmentHelper.getFishingLuckBonus(stack);

            try {
               if (i3 > 0) {
                  familiar.setLuck(i3);
               }
            } catch (RuntimeException error2) {
               throw error2;
            }

            world.spawnEntity(familiar);
         }

         luna.swingArm(hand);
      }

      return new ActionResult(EnumActionResult.SUCCESS, stack);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
