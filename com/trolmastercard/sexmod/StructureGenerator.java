package com.trolmastercard.sexmod;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class StructureGenerator extends WorldGenerator implements TemplatePlacement {
   public String TemplateName;

   public StructureGenerator(String string) {
      this.TemplateName = string;
   }

   public void placeStructure(World world, BlockPos pos) {
      MinecraftServer server = world.getMinecraftServer();
      TemplateManager templateManager = PreviewRenderer.getStructureTemplateManager();
      ResourceLocation location = new ResourceLocation("sexmod", this.TemplateName);
      Template template = templateManager.get(server, location);
      if (template != null) {
         IBlockState state = world.getBlockState(pos);
         world.notifyBlockUpdate(pos, state, state, 3);
         template.addBlocksToWorld(world, pos, a);
      }
   }

   public void placeStructureRotated(World world, BlockPos pos, Rotation rotation) {
      MinecraftServer server = world.getMinecraftServer();
      TemplateManager templateManager = PreviewRenderer.getStructureTemplateManager();
      ResourceLocation location = new ResourceLocation("sexmod", this.TemplateName);
      Template template = templateManager.get(server, location);
      if (template != null) {
         IBlockState state = world.getBlockState(pos);
         world.notifyBlockUpdate(pos, state, state, 2);
         template.addBlocksToWorld(world, pos, a.setRotation(rotation));
      }
   }

   public boolean generate(World world, Random random, BlockPos pos) {
      this.placeStructure(world, pos);
      return true;
   }
}
