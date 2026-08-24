package com.trolmastercard.sexmod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNpcEditorWand extends Item {
   public static final ItemNpcEditorWand Instance = new ItemNpcEditorWand();

   public ItemNpcEditorWand() {
      this.setCreativeTab(CreativeTabs.TOOLS);
      this.maxStackSize = 1;
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean flag) {
      try {
         if (world.isRemote) {
            this.a(entity, stack);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      super.onUpdate(stack, world, entity, i, flag);
   }

   @SideOnly(Side.CLIENT)

   void a(Entity entity, ItemStack stack) {
        int i;
        ItemStack itemStack2;
        block13: {
            block12: {
                block11: {
                    try {
                        if (!(entity instanceof EntityPlayer)) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                    EntityPlayer entityPlayer = (EntityPlayer)entity;
                    try {
                        try {
                            if (stack.equals(entityPlayer.getHeldItemMainhand()) || stack.equals(entityPlayer.getHeldItemOffhand())) break block11;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ItemNpcEditorWand.rethrow(runtimeException);
                        }
                        stack.setItemDamage(0);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                }
                RayTraceResult rayTraceResult = Minecraft.getMinecraft().objectMouseOver;
                try {
                    try {
                        itemStack2 = stack;
                        if (rayTraceResult == null || !GirlEntity.isGirlEntity(rayTraceResult.entityHit)) break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                    i = 1;
                    break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw ItemNpcEditorWand.rethrow(runtimeException);
                }
            }
            i = 0;
        }
        itemStack2.setItemDamage(i);
    }

   @SubscribeEvent

   public void a(EntityInteract entityInteract) {
        Entity entity;
        block23: {
            boolean flag;
            block25: {
                block24: {
                    entity = entityInteract.getTarget();
                    try {
                        if (!(entity instanceof GirlEntity)) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                    try {
                        if (!GirlEntity.isGirlEntity(entity)) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                    EntityPlayer entityPlayer = entityInteract.getEntityPlayer();
                    try {
                        if (entityPlayer == null) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                    ItemStack itemStack = entityPlayer.getHeldItemMainhand();
                    if (itemStack.getItem() != Instance) {
                        itemStack = entityPlayer.getHeldItemOffhand();
                    }
                    try {
                        if (itemStack.getItem() != Instance) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                    try {
                        entityInteract.setCanceled(true);
                        if (!entityInteract.getWorld().isRemote) {
                            return;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                    try {
                        try {
                            if (!FilePersistence.ServerActive) break block23;
                            if (0 == FilePersistence.reloadModels(true)) break block24;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ItemNpcEditorWand.rethrow(runtimeException);
                        }
                        flag = true;
                        break block25;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ItemNpcEditorWand.rethrow(runtimeException);
                    }
                }
                flag = false;
            }
            try {
                FilePersistence.ServerActive = flag;
                if (FilePersistence.ServerActive) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ItemNpcEditorWand.rethrow(runtimeException);
            }
        }
        GuiCustomizeGirl.openGui(((GirlEntity)entity).E());
    }

   @SubscribeEvent
   public void a(AttackEntityEvent attackEntityEvent) {
      Entity entity = attackEntityEvent.getTarget();

      try {
         if (entity == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!(entity instanceof GirlEntity)) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player = attackEntityEvent.getEntityPlayer();

      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      ItemStack stack = player.getHeldItemMainhand();
      if (stack.getItem() != Instance) {
         stack = player.getHeldItemOffhand();
      }

      try {
         if (stack.getItem() != Instance) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      try {
         attackEntityEvent.setCanceled(true);
         if (!player.world.isRemote) {
            return;
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }

      GirlEntity girl = (GirlEntity)entity;
      String string = girl.C();
      String string2 = GirlEntity.colorsToString(GirlEntity.getModelColors(girl.getGirlUuid()));
      player.sendMessage(new TextComponentString(String.format("%s's model-code: %s%s$%s", girl.getDisplayName(), TextFormatting.YELLOW, string, string2)));
      player.sendMessage(new TextComponentString(TextFormatting.ITALIC + "copied to clipboard"));
      MathUtils.copyToClipboard(String.format("%s$%s", string, string2));
   }

   @SubscribeEvent
   public void a(LeftClickBlock leftClickBlock) {
      try {
         if (this.a(leftClickBlock.getEntityPlayer(), leftClickBlock.getWorld())) {
            leftClickBlock.setCanceled(true);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @SubscribeEvent
   public void a(LeftClickEmpty leftClickEmpty) {
      this.a(leftClickEmpty.getEntityPlayer(), leftClickEmpty.getWorld());
   }

   boolean a(EntityPlayer player, World world) {
      try {
         if (player == null) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      ItemStack stack = player.getHeldItemMainhand();
      if (stack.getItem() != Instance) {
         stack = player.getHeldItemOffhand();
      }

      try {
         if (stack.getItem() != Instance) {
            return false;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (!world.isRemote) {
            return true;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(player.getPersistentID());

      try {
         if (playerGirl == null) {
            player.sendStatusMessage(new TextComponentString("you gotta turn into the girl, you want to copy the model-code off"), true);
            return true;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      String string = playerGirl.C();
      String string2 = GirlEntity.colorsToString(GirlEntity.getModelColors(playerGirl.isBoundToLocalPlayer()));
      player.sendMessage(new TextComponentString(String.format("%s's model-code: %s%s$%s", MathUtils.capitalize(GirlRegistry.getByEntity(playerGirl).toString()), TextFormatting.YELLOW, string, string2)));
      player.sendMessage(new TextComponentString(TextFormatting.ITALIC + "copied to clipboard"));
      MathUtils.copyToClipboard(String.format("%s$%s", string, string2));
      return true;
   }

   public static void registerAll() {
      Instance.setRegistryName("sexmod", "npc_editor_wand");
      Instance.setTranslationKey("npc_editor_wand");
      MinecraftForge.EVENT_BUS.register(ItemNpcEditorWand.class);
   }

   @SubscribeEvent
   public static void registerItem(Register<Item> register2) {
      register2.getRegistry().register(Instance);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void registerItemModel(ModelRegistryEvent model) {
      ModelLoader.setCustomModelResourceLocation(Instance, 0, new ModelResourceLocation("sexmod:npc_editor_wand"));
      ModelLoader.setCustomModelResourceLocation(Instance, 1, new ModelResourceLocation("sexmod:npc_editor_wand_active"));
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
