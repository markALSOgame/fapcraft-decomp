package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTribeEgg extends Item {
   public static final ItemTribeEgg a = new ItemTribeEgg();

   public ItemTribeEgg() {
      this.setCreativeTab(CreativeTabs.MISC);
      this.maxStackSize = 1;
   }

   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      Vec3d vec3d = player.getPositionEyes(0.0F);
      Vec3d vec3d2 = player.getLook(0.0F);
      Vec3d vec3d3 = vec3d.add(vec3d2.x * 5.0, vec3d2.y * 5.0, vec3d2.z * 5.0);
      RayTraceResult hit = world.rayTraceBlocks(vec3d, vec3d3, false, false, true);

      try {
         if (hit == null) {
            return new ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand));
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (hit.typeOfHit == Type.MISS) {
            return new ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand));
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (!world.isRemote) {
            GirlHomeBuilder.createTribe(world, hit.hitVec);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
   }

   public static void registerAll() {
      a.setRegistryName("sexmod", "tribe_egg");
      a.setTranslationKey("tribe_egg");
      MinecraftForge.EVENT_BUS.register(ItemTribeEgg.class);
   }

   @SubscribeEvent
   public static void registerItem(Register<Item> register2) {
      register2.getRegistry().register(a);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void registerItemModel(ModelRegistryEvent model) {
      ModelLoader.setCustomModelResourceLocation(a, 0, new ModelResourceLocation("sexmod:tribe_egg"));
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
