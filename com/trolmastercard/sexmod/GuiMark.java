package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiMark {
   static final Vec3i Red = new Vec3i(255, 0, 0);
   static final Vec3i Green = new Vec3i(0, 255, 0);
   static final Vec3i Blue = new Vec3i(0, 0, 255);
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/mark.png");
   static HashSet<BlockPos> Marks = new HashSet<>();
   static Minecraft Mc = Minecraft.getMinecraft();
   static TextureManager TextureManager = Minecraft.getMinecraft().getTextureManager();

   public static void clearMarks() {
      Marks.clear();
   }

   public static boolean isMarked(BlockPos pos2) {
      return Marks.contains(pos2);
   }

   public static void b() {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      Vec3d vec3d = LerpMath.lerpVec3d(ModConstants.EndPos, ModConstants.StartPos, Mc.getRenderPartialTicks());
      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      GlStateManager.disableDepth();
      TextureManager.bindTexture(Texture);
      GlStateManager.translate(-vec3d.x, -vec3d.y, -vec3d.z);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

      try {
         for (BlockPos pos3 : Marks) {
            Vec3i vec3i = b(pos3);
            drawMark(bufferBuilder, pos3, vec3i.getX(), vec3i.getY(), vec3i.getZ());
         }
      } catch (ConcurrentModificationException error) {
      }

      tessellator.draw();
      GlStateManager.enableDepth();
      GlStateManager.enableCull();
      GlStateManager.popMatrix();
   }

   static Vec3i b(BlockPos pos4) {
      Block block = Minecraft.getMinecraft().world.getBlockState(pos4).getBlock();

      try {
         if (block instanceof BlockBed) {
            return Blue;
         }
      } catch (ConcurrentModificationException error2) {
         throw rethrow(error2);
      }

      try {
         if (block instanceof BlockChest) {
            return Green;
         }
      } catch (ConcurrentModificationException error3) {
         throw rethrow(error3);
      }

      return Red;
   }

   static void drawMark(BufferBuilder bufferBuilder2, BlockPos pos5, int i, int i2, int i3) {
      bufferBuilder2.pos(pos5.getX(), pos5.getY() + 1, pos5.getZ())
         .tex(0.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY() + 1, pos5.getZ())
         .tex(1.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY(), pos5.getZ())
         .tex(1.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY(), pos5.getZ())
         .tex(0.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY() + 1, 1 + pos5.getZ())
         .tex(0.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY() + 1, 1 + pos5.getZ())
         .tex(1.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY(), 1 + pos5.getZ())
         .tex(1.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY(), 1 + pos5.getZ())
         .tex(0.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY() + 1, pos5.getZ())
         .tex(0.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY() + 1, 1 + pos5.getZ())
         .tex(1.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY(), 1 + pos5.getZ())
         .tex(1.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY(), pos5.getZ())
         .tex(0.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY() + 1, pos5.getZ())
         .tex(0.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY() + 1, 1 + pos5.getZ())
         .tex(1.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY(), 1 + pos5.getZ())
         .tex(1.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY(), pos5.getZ())
         .tex(0.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY(), 1 + pos5.getZ())
         .tex(0.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY(), 1 + pos5.getZ())
         .tex(1.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY(), pos5.getZ())
         .tex(1.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY(), pos5.getZ())
         .tex(0.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY() + 1, 1 + pos5.getZ())
         .tex(0.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY() + 1, 1 + pos5.getZ())
         .tex(1.0, 1.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(1 + pos5.getX(), pos5.getY() + 1, pos5.getZ())
         .tex(1.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
      bufferBuilder2.pos(pos5.getX(), pos5.getY() + 1, pos5.getZ())
         .tex(0.0, 0.0)
         .color(i, i2, i3, 255)
         .endVertex();
   }

   public static void a(HashSet<BlockPos> set) {
      Marks.addAll(set);
   }

   public static void b(HashSet<BlockPos> set2) {
      Marks.removeAll(set2);
   }

   @SubscribeEvent
   public void a(RenderWorldLastEvent renderWorldLastEvent) {
      GlStateManager.enableColorMaterial();
      GL11.glDisable(2896);
      ItemStack stack = Mc.player.getHeldItem(EnumHand.MAIN_HAND);
      if (stack.getItem() != ItemDragonStaff.Instance) {
         stack = Mc.player.getHeldItem(EnumHand.OFF_HAND);
      }

      try {
         if (stack.getItem() == ItemDragonStaff.Instance) {
            b();
         }
      } catch (ConcurrentModificationException error4) {
         throw rethrow(error4);
      }

      GlStateManager.enableLighting();
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
      GL11.glEnable(2896);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void a(ClientTickEvent clientTickEvent) {
      try {
         if (clientTickEvent.phase == Phase.START) {
            return;
         }
      } catch (ConcurrentModificationException error5) {
         throw rethrow(error5);
      }

      EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;

      try {
         if (mcPlayer == null) {
            return;
         }
      } catch (ConcurrentModificationException error6) {
         throw rethrow(error6);
      }

      ModConstants.EndPos = ModConstants.StartPos;
      ModConstants.StartPos = mcPlayer.getPositionVector();
   }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error7) {
      return error7;
   }
}
