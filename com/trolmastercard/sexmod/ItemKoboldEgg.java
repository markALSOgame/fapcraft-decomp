package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ItemKoboldEgg extends Item implements IAnimatable {
   private final AnimationFactory b = new AnimationFactory(this);
   public static ItemKoboldEgg Instance = new ItemKoboldEgg();

   public ItemKoboldEgg() {
      this.setMaxStackSize(1);
   }

   public static void registerAll() {
      Instance.setRegistryName("sexmod", "kobold_egg_item");
      Instance.setTranslationKey("kobold_egg_item");
      MinecraftForge.EVENT_BUS.register(ItemKoboldEgg.class);
   }

   public void registerControllers(AnimationData animationData) {
   }

   public AnimationFactory getFactory() {
      return this.b;
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void registerItemModel(ModelRegistryEvent model) {
      ModelResourceLocation model2 = new ModelResourceLocation("sexmod:kobold_egg_item");
      ModelLoader.setCustomMeshDefinition(Instance, arg1 -> model2);
      ModelBakery.registerItemVariants(Instance, new ResourceLocation[]{model2});
      Instance.setTileEntityItemStackRenderer(new KoboldEggItemRenderer());
   }

   @SubscribeEvent
   public static void registerItem(Register<Item> register2) {
      register2.getRegistry().register(Instance);
   }

   @SubscribeEvent
   public static void a(RightClickBlock rightClickBlock) {
      World world = rightClickBlock.getWorld();
      ItemStack stack = rightClickBlock.getItemStack();
      Vec3d vec3d = rightClickBlock.getHitVec();

      try {
         if (world.isRemote) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (stack.getItem() != Instance) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      KoboldEggEntity egg = new KoboldEggEntity(world);
      egg.setPosition(vec3d.x, vec3d.y, vec3d.z);
      egg.getDataManager().set(KoboldEggEntity.EggColorKey, EyeAndKoboldColor.getColorByWoolId(stack.getMetadata()).toString());
      NBTTagCompound tagCompound = stack.getTagCompound();

      try {
         if (tagCompound != null) {
            egg.TribeUuid = UUID.fromString(tagCompound.getString("tribeID"));
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      world.spawnEntity(egg);
      stack.shrink(1);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
