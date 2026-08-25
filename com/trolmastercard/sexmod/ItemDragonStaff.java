package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ItemDragonStaff extends Item implements IAnimatable {
   public static final ItemDragonStaff Instance = new ItemDragonStaff();
   private final AnimationFactory a = new AnimationFactory(this);

   public ItemDragonStaff() {
      this.setCreativeTab(CreativeTabs.TOOLS);
      this.maxStackSize = 1;
   }

   public static void registerAll() {
      Instance.setRegistryName("sexmod", "dragon_staff");
      Instance.setTranslationKey("dragon_staff");
      MinecraftForge.EVENT_BUS.register(ItemDragonStaff.class);
   }

   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      return new ActionResult(EnumActionResult.FAIL, player.getHeldItem(hand));
   }

   @SubscribeEvent
   public static void registerItem(Register<Item> register2) {
      register2.getRegistry().register(Instance);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void registerItemModel(ModelRegistryEvent model) {
      ModelLoader.setCustomModelResourceLocation(Instance, 0, new ModelResourceLocation("sexmod:dragon_staff"));
      Instance.setTileEntityItemStackRenderer(new ItemRenderUtil());
   }

   public void registerControllers(AnimationData animationData) {
   }

   public AnimationFactory getFactory() {
      return this.a;
   }

   public static class a {
      @SubscribeEvent

      public void a(RightClickItem rightClickItem) {
            block10: {
                World world = rightClickItem.getWorld();
                try {
                    if (!world.isRemote) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw rethrow(runtimeException);
                }
                EntityPlayer entityPlayer = rightClickItem.getEntityPlayer();
                try {
                    try {
                        if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == Instance || entityPlayer.getHeldItem(EnumHand.OFF_HAND).getItem() == Instance) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw rethrow(runtimeException);
                }
            }
            try {
                if (KoboldNpc.MemberData.isEmpty()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw rethrow(runtimeException);
            }
            this.registerAll();
        }

      @SideOnly(Side.CLIENT)
      void registerAll() {
         Minecraft.getMinecraft().displayGuiScreen(new GuiStructureBuilder());
         NetworkHandler.channel.sendToServer(new PacketGetTribeUIValues());
      }

      @SubscribeEvent

      public void a(RightClickBlock rightClickBlock) {
            block10: {
                EntityPlayer entityPlayer = rightClickBlock.getEntityPlayer();
                try {
                    try {
                        if (entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == Instance || entityPlayer.getHeldItem(EnumHand.OFF_HAND).getItem() == Instance) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw rethrow(runtimeException);
                }
            }
            Block block = rightClickBlock.getWorld().getBlockState(rightClickBlock.getPos()).getBlock();
            try {
                if (block instanceof BlockBed) {
                    rightClickBlock.setCancellationResult(EnumActionResult.FAIL);
                    rightClickBlock.setResult(Event.Result.DENY);
                    rightClickBlock.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw rethrow(runtimeException);
            }
            try {
                if (block instanceof BlockChest) {
                    rightClickBlock.setCancellationResult(EnumActionResult.FAIL);
                    rightClickBlock.setResult(Event.Result.DENY);
                    rightClickBlock.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw rethrow(runtimeException);
            }
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
