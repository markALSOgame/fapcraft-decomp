package com.trolmastercard.sexmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomFireBlock extends BlockFire {
   public static final Block a = new CustomFireBlock();

   public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
   }

   public static void registerAll() {
      a.setRegistryName("sexmod", "fire");
      a.setTranslationKey("fire");
      MinecraftForge.EVENT_BUS.register(CustomFireBlock.class);
   }

   @SubscribeEvent
   public static void registerBlock(Register<Block> register2) {
      register2.getRegistry().register(a);
   }
}
