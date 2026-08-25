package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.opengl.GL11;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;

public class GuiStructureBuilder extends GuiScreen {
   static final float f = 100.0F;
   static final float g = 15.0F;
   static final float j = 0.5F;
   static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/command.png");
   static final HashSet<Material> SupportedMaterials = new HashSet<>(
      Arrays.asList(Material.CLAY, Material.ROCK, Material.SAND, Material.GROUND)
   );
   public static boolean ExecuteBuild = false;
   float OpenProgress = 0.0F;
   float CornerOffsetA = 0.0F;
   float CornerOffsetB = 0.0F;
   float CornerOffsetC = 0.0F;
   float CornerOffsetD = 0.0F;
   IBlockState TargetState;
   BlockPos Pos;
   EnumFacing Facing;

   public GuiStructureBuilder() {
      Minecraft mc = Minecraft.getMinecraft();

      label27: {
         try {
            this.Pos = mc.objectMouseOver.getBlockPos();
            if (mc.objectMouseOver.sideHit == null) {
               this.Facing = EnumFacing.NORTH;
               break label27;
            }
         } catch (NullPointerException error) {
            throw rethrow(error);
         }

         this.Facing = mc.objectMouseOver.sideHit.getOpposite();
      }

      try {
         if (this.Pos == null) {
            this.Pos = BlockPos.ORIGIN;
         }
      } catch (NullPointerException error2) {
         throw rethrow(error2);
      }

      this.TargetState = mc.world.getBlockState(this.Pos);
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      List list = Arrays.asList(this.CornerOffsetA, this.CornerOffsetB, this.CornerOffsetC, this.CornerOffsetD);
      float f = Collections.max(list);

      try {
         if (f == 0.0F) {
            return;
         }
      } catch (NullPointerException error) {
         throw rethrow(error);
      }

      try {
         if (this.CornerOffsetA == f) {
            this.b();
         }
      } catch (NullPointerException error2) {
         throw rethrow(error2);
      }

      try {
         if (this.CornerOffsetB == f) {
            this.d();
         }
      } catch (NullPointerException error3) {
         throw rethrow(error3);
      }

      try {
         if (this.CornerOffsetC == f) {
            this.c();
         }
      } catch (NullPointerException error4) {
         throw rethrow(error4);
      }

      try {
         if (this.CornerOffsetD == f) {
            this.a();
         }
      } catch (NullPointerException error5) {
         throw rethrow(error5);
      }
   }


   void b() {
        block6: {
            boolean flag;
            BlockPos blockPos;
            PacketSendBlocks packet;
            PacketSendBlocks packet2;
            SimpleNetworkWrapper simpleNetworkWrapper;
            block8: {
                block7: {
                    IBlockState iBlockState = this.mc.world.getBlockState(this.Pos);
                    try {
                        try {
                            PacketSendBlocks packet3;
                            try {
                                if (!(iBlockState.getBlock() instanceof BlockBed) && !(iBlockState.getBlock() instanceof BlockChest)) break block6;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw GuiStructureBuilder.rethrow(nullPointerException);
                            }
                            simpleNetworkWrapper = NetworkHandler.channel;
                            packet2 = packet3;
                            packet = packet3;
                            blockPos = this.Pos;
                            if (GuiMark.isMarked(this.Pos)) break block7;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw GuiStructureBuilder.rethrow(nullPointerException);
                        }
                        flag = true;
                        break block8;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw GuiStructureBuilder.rethrow(nullPointerException);
                    }
                }
                flag = false;
            }
            packet2(blockPos, flag);
            simpleNetworkWrapper.sendToServer((IMessage)packet);
        }
    }


   void d() {
        boolean flag;
        PacketSetTribeFollowMode packet;
        PacketSetTribeFollowMode packet2;
        SimpleNetworkWrapper simpleNetworkWrapper;
        try {
            PacketSetTribeFollowMode packet3;
            simpleNetworkWrapper = NetworkHandler.channel;
            packet2 = packet3;
            packet = packet3;
            flag = !ExecuteBuild;
        }
        catch (NullPointerException nullPointerException) {
            throw GuiStructureBuilder.rethrow(nullPointerException);
        }
        packet2(flag);
        simpleNetworkWrapper.sendToServer((IMessage)packet);
    }

   void c() {
      ItemRenderUtil.toggleAnimated();
   }


   void a() {
        block10: {
            Object[] objectArray;
            block11: {
                block8: {
                    block9: {
                        Block block = this.TargetState.getBlock();
                        try {
                            try {
                                if (!(block instanceof BlockLog)) break block8;
                                if (!GuiMark.isMarked(this.Pos)) break block9;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw GuiStructureBuilder.rethrow(nullPointerException);
                            }
                            NetworkHandler.channel.sendToServer((IMessage)new PacketCancelTask(this.Pos));
                            return;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw GuiStructureBuilder.rethrow(nullPointerException);
                        }
                    }
                    NetworkHandler.channel.sendToServer((IMessage)new PacketFallTree(this.Pos));
                }
                objectArray = this.e();
                try {
                    try {
                        if (objectArray == null) break block10;
                        if (!GuiMark.isMarked(this.Pos)) break block11;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw GuiStructureBuilder.rethrow(nullPointerException);
                    }
                    NetworkHandler.channel.sendToServer((IMessage)new PacketCancelTask(this.Pos));
                    return;
                }
                catch (NullPointerException nullPointerException) {
                    throw GuiStructureBuilder.rethrow(nullPointerException);
                }
            }
            NetworkHandler.channel.sendToServer((IMessage)new PacketMine((BlockPos)objectArray[0], (EnumFacing)objectArray[1]));
        }
    }

   @Nullable
   Object[] e() {
      Material material = this.mc.world.getBlockState(this.Pos).getMaterial();
      EntityPlayerSP mcPlayer = this.mc.player;

      try {
         if (!SupportedMaterials.contains(material)) {
            return null;
         }
      } catch (NullPointerException error) {
         throw rethrow(error);
      }

      try {
         if (mcPlayer.getPosition().getY() > this.Pos.getY()) {
            return null;
         }
      } catch (NullPointerException error2) {
         throw rethrow(error2);
      }

      BlockPos pos = this.Pos;

      while (
         this.mc.world.getBlockState(pos.down().add(this.Facing.getOpposite().getDirectionVec())).getBlock()
            == Blocks.AIR
      ) {
         pos = pos.down();
      }

      try {
         if (this.Pos.getY() - pos.getY() > 3) {
            return null;
         }
      } catch (NullPointerException error3) {
         throw rethrow(error3);
      }

      return new Object[]{pos, this.Facing};
   }


   public void drawScreen(int i, int i2, float f2) {
        block60: {
            boolean flag;
            float f3;
            block61: {
                boolean flag2;
                Block block;
                block58: {
                    boolean flag3;
                    block59: {
                        int i3;
                        float f4;
                        float f5;
                        block57: {
                            block56: {
                                int i4;
                                float f6;
                                block55: {
                                    block54: {
                                        int i5;
                                        float f7;
                                        block53: {
                                            block52: {
                                                int i6;
                                                float f8;
                                                block51: {
                                                    block50: {
                                                        super.drawScreen(i, i2, f2);
                                                        GL11.glEnable((int)3042);
                                                        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
                                                        GL11.glBlendFunc((int)770, (int)771);
                                                        try {
                                                            this.OpenProgress = Math.min(1.0f, this.OpenProgress + this.mc.getTickLength() / 5.0f);
                                                        }
                                                        catch (NullPointerException nullPointerException) {
                                                            // empty catch block
                                                        }
                                                        f5 = (float)this.a((double)this.OpenProgress);
                                                        f3 = (1.0f - f5) * 100.0f;
                                                        try {
                                                            try {
                                                                GuiStructureBuilder gui = this;
                                                                GuiStructureBuilder gui2 = gui;
                                                                f8 = gui.CornerOffsetA;
                                                                if (i >= this.width / 2 || i2 <= this.height / 2) break block50;
                                                            }
                                                            catch (NullPointerException nullPointerException) {
                                                                throw GuiStructureBuilder.rethrow(nullPointerException);
                                                            }
                                                            i6 = 1;
                                                            break block51;
                                                        }
                                                        catch (NullPointerException nullPointerException) {
                                                            throw GuiStructureBuilder.rethrow(nullPointerException);
                                                        }
                                                    }
                                                    i6 = -1;
                                                }
                                                try {
                                                    try {
                                                        gui2.CornerOffsetA = f8 + (float)i6 * this.mc.getTickLength();
                                                        GuiStructureBuilder gui3 = this;
                                                        GuiStructureBuilder gui4 = gui3;
                                                        f7 = gui3.CornerOffsetB;
                                                        if (i >= this.width / 2 || i2 >= this.height / 2) break block52;
                                                    }
                                                    catch (NullPointerException nullPointerException) {
                                                        throw GuiStructureBuilder.rethrow(nullPointerException);
                                                    }
                                                    i5 = 1;
                                                    break block53;
                                                }
                                                catch (NullPointerException nullPointerException) {
                                                    throw GuiStructureBuilder.rethrow(nullPointerException);
                                                }
                                            }
                                            i5 = -1;
                                        }
                                        try {
                                            try {
                                                gui4.CornerOffsetB = f7 + (float)i5 * this.mc.getTickLength();
                                                GuiStructureBuilder gui5 = this;
                                                GuiStructureBuilder gui6 = gui5;
                                                f6 = gui5.CornerOffsetC;
                                                if (i <= this.width / 2 || i2 <= this.height / 2) break block54;
                                            }
                                            catch (NullPointerException nullPointerException) {
                                                throw GuiStructureBuilder.rethrow(nullPointerException);
                                            }
                                            i4 = 1;
                                            break block55;
                                        }
                                        catch (NullPointerException nullPointerException) {
                                            throw GuiStructureBuilder.rethrow(nullPointerException);
                                        }
                                    }
                                    i4 = -1;
                                }
                                try {
                                    try {
                                        gui6.CornerOffsetC = f6 + (float)i4 * this.mc.getTickLength();
                                        GuiStructureBuilder gui7 = this;
                                        GuiStructureBuilder gui8 = gui7;
                                        f4 = gui7.CornerOffsetD;
                                        if (i <= this.width / 2 || i2 >= this.height / 2) break block56;
                                    }
                                    catch (NullPointerException nullPointerException) {
                                        throw GuiStructureBuilder.rethrow(nullPointerException);
                                    }
                                    i3 = 1;
                                    break block57;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw GuiStructureBuilder.rethrow(nullPointerException);
                                }
                            }
                            i3 = -1;
                        }
                        try {
                            gui8.CornerOffsetD = f4 + (float)i3 * this.mc.getTickLength();
                            this.CornerOffsetA = MathUtils.clamp(this.CornerOffsetA, 0.0f, 1.0f);
                            this.CornerOffsetB = MathUtils.clamp(this.CornerOffsetB, 0.0f, 1.0f);
                            this.CornerOffsetC = MathUtils.clamp(this.CornerOffsetC, 0.0f, 1.0f);
                            this.CornerOffsetD = MathUtils.clamp(this.CornerOffsetD, 0.0f, 1.0f);
                            GlStateManager.pushMatrix();
                            GlStateManager.translate((float)((float)this.width / 2.0f), (float)((float)this.height / 2.0f), (float)0.0f);
                            GlStateManager.scale((float)f5, (float)f5, (float)f5);
                            this.mc.renderEngine.bindTexture(Texture);
                            GlStateManager.pushMatrix();
                            GlStateManager.scale((float)(1.0f + this.CornerOffsetB * 0.5f), (float)(1.0f + this.CornerOffsetB * 0.5f), (float)1.0f);
                            this.drawTexturedModalRect(-62.0f + f3 - this.CornerOffsetB * 15.0f, -62.0f + f3 - this.CornerOffsetB * 15.0f, 0, 0, 64, 64);
                            this.c(f3);
                            if (ExecuteBuild) {
                                this.drawTexturedModalRect(-62.0f + f3 - this.CornerOffsetB * 15.0f, -62.0f + f3 - this.CornerOffsetB * 15.0f, 128, 64, 64, 64);
                            }
                        }
                        catch (NullPointerException nullPointerException) {
                            throw GuiStructureBuilder.rethrow(nullPointerException);
                        }
                        try {
                            GlStateManager.popMatrix();
                            GlStateManager.pushMatrix();
                            GlStateManager.scale((float)(1.0f + this.CornerOffsetC * 0.5f), (float)(1.0f + this.CornerOffsetC * 0.5f), (float)1.0f);
                            this.drawTexturedModalRect(-2.0f - f3 + this.CornerOffsetC * 15.0f, -2.0f - f3 + this.CornerOffsetC * 15.0f, 0, 0, 64, 64);
                            this.a(f3);
                            if (ItemRenderUtil.isAnimated()) {
                                this.drawTexturedModalRect(-2.0f - f3 + this.CornerOffsetC * 15.0f, -2.0f - f3 + this.CornerOffsetC * 15.0f, 128, 64, 64, 64);
                            }
                        }
                        catch (NullPointerException nullPointerException) {
                            throw GuiStructureBuilder.rethrow(nullPointerException);
                        }
                        GlStateManager.popMatrix();
                        block = this.TargetState.getBlock();
                        boolean flag4 = block instanceof BlockChest;
                        flag3 = block instanceof BlockBed;
                        try {
                            try {
                                try {
                                    if (!flag4 && !flag3) break block58;
                                }
                                catch (NullPointerException nullPointerException) {
                                    throw GuiStructureBuilder.rethrow(nullPointerException);
                                }
                                GlStateManager.pushMatrix();
                                GlStateManager.scale((float)(1.0f + this.CornerOffsetA * 0.5f), (float)(1.0f + this.CornerOffsetA * 0.5f), (float)1.0f);
                                this.drawTexturedModalRect(-62.0f + f3 - this.CornerOffsetA * 15.0f, -2.0f - f3 + this.CornerOffsetA * 15.0f, 0, 0, 64, 64);
                                if (!flag4) break block59;
                            }
                            catch (NullPointerException nullPointerException) {
                                throw GuiStructureBuilder.rethrow(nullPointerException);
                            }
                            this.d(f3);
                        }
                        catch (NullPointerException nullPointerException) {
                            throw GuiStructureBuilder.rethrow(nullPointerException);
                        }
                    }
                    try {
                        if (flag3) {
                            this.f(f3);
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw GuiStructureBuilder.rethrow(nullPointerException);
                    }
                    try {
                        if (GuiMark.isMarked(this.Pos)) {
                            this.drawTexturedModalRect(-62.0f + f3 - this.CornerOffsetA * 15.0f, -2.0f - f3 + this.CornerOffsetA * 15.0f, 128, 64, 64, 64);
                        }
                    }
                    catch (NullPointerException nullPointerException) {
                        throw GuiStructureBuilder.rethrow(nullPointerException);
                    }
                    GlStateManager.popMatrix();
                }
                boolean flag5 = block instanceof BlockLog;
                try {
                    flag2 = this.e() != null;
                }
                catch (NullPointerException nullPointerException) {
                    throw GuiStructureBuilder.rethrow(nullPointerException);
                }
                flag = flag2;
                try {
                    try {
                        try {
                            if (!flag5 && !flag) break block60;
                        }
                        catch (NullPointerException nullPointerException) {
                            throw GuiStructureBuilder.rethrow(nullPointerException);
                        }
                        GlStateManager.pushMatrix();
                        GlStateManager.scale((float)(1.0f + this.CornerOffsetD * 0.5f), (float)(1.0f + this.CornerOffsetD * 0.5f), (float)1.0f);
                        this.drawTexturedModalRect(-2.0f - f3 + this.CornerOffsetD * 15.0f, -62.0f + f3 - this.CornerOffsetD * 15.0f, 0, 0, 64, 64);
                        if (!flag5) break block61;
                    }
                    catch (NullPointerException nullPointerException) {
                        throw GuiStructureBuilder.rethrow(nullPointerException);
                    }
                    this.e(f3);
                }
                catch (NullPointerException nullPointerException) {
                    throw GuiStructureBuilder.rethrow(nullPointerException);
                }
            }
            try {
                if (flag) {
                    this.b(f3);
                }
            }
            catch (NullPointerException nullPointerException) {
                throw GuiStructureBuilder.rethrow(nullPointerException);
            }
            try {
                if (GuiMark.isMarked(this.Pos)) {
                    this.drawTexturedModalRect(-2.0f - f3 + this.CornerOffsetD * 15.0f, -62.0f + f3 - this.CornerOffsetD * 15.0f, 128, 64, 64, 64);
                }
            }
            catch (NullPointerException nullPointerException) {
                throw GuiStructureBuilder.rethrow(nullPointerException);
            }
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
        GL11.glDisable((int)3042);
    }

   void a(float f) {
      this.drawTexturedModalRect(-2.0F - f + this.CornerOffsetC * 15.0F, -2.0F - f + this.CornerOffsetC * 15.0F, 192, 64, 64, 64);
   }

   void c(float f) {
      this.drawTexturedModalRect(-62.0F + f - this.CornerOffsetB * 15.0F, -62.0F + f - this.CornerOffsetB * 15.0F, 64, 64, 64, 64);
   }

   void e(float f) {
      this.drawTexturedModalRect(-2.0F - f + this.CornerOffsetD * 15.0F, -62.0F + f - this.CornerOffsetD * 15.0F, 64, 0, 64, 64);
   }

   void b(float f) {
      this.drawTexturedModalRect(-2.0F - f + this.CornerOffsetD * 15.0F, -62.0F + f - this.CornerOffsetD * 15.0F, 128, 0, 64, 64);
   }

   void f(float f) {
      this.drawTexturedModalRect(-62.0F + f - this.CornerOffsetA * 15.0F, -2.0F - f + this.CornerOffsetA * 15.0F, 0, 64, 64, 64);
   }

   void d(float f) {
      this.drawTexturedModalRect(-62.0F + f - this.CornerOffsetA * 15.0F, -2.0F - f + this.CornerOffsetA * 15.0F, 192, 0, 64, 64);
   }

   double a(double d) {
      double d2 = 1.70158;
      double d3 = d2 + 1.0;
      return 1.0 + d3 * Math.pow(d - 1.0, 3.0) + d2 * Math.pow(d - 1.0, 2.0);
   }

   protected void mouseReleased(int i, int i2, int i3) {
      this.mc.player.closeScreen();
      super.mouseReleased(i, i2, i3);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   private static NullPointerException rethrow(NullPointerException error) {
      return error;
   }
}
