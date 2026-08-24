package com.trolmastercard.sexmod;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ItemWinchester extends Item implements IAnimatable {
   public static final ItemWinchester Instance = new ItemWinchester();
   private final AnimationFactory Factory = new AnimationFactory(this);

   public static void registerItem() {
      Instance.setRegistryName("sexmod", "winchester");
      Instance.setTranslationKey("winchester");
      MinecraftForge.EVENT_BUS.register(ItemWinchester.class);
   }

   public void registerControllers(AnimationData animationData) {
   }

   public AnimationFactory getFactory() {
      return this.Factory;
   }
}
