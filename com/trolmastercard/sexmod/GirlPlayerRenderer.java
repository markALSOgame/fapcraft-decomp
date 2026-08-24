package com.trolmastercard.sexmod;

import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class GirlPlayerRenderer extends GeoGirlRenderer {
   public static boolean RenderTrigger = false;
   ItemStack MainHandStack = ItemStack.EMPTY;
   ItemStack OffHandStack = ItemStack.EMPTY;
   boolean r = false;
   boolean u = false;
   protected PlayerGirlEntity CurrentGirl;
   protected float RenderTick;
   float t = 0.0F;

   public GirlPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel, 0.0);
   }

   public void doRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2) {
   }

   boolean a(GirlEntity girl) {
      try {
         if (girl.isTracked()) {
            return true;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      boolean flag = RenderTrigger;
      RenderTrigger = false;
      return flag;
   }

   @Override
   public void a(GirlEntity girl, double d, double d2, double d3, float f, float f2) {
      try {
         if (!this.a(girl)) {
            return;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity playerGirl = (PlayerGirlEntity)girl;

      try {
         if (playerGirl.getBoundPlayerUuid() == null) {
            return;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player2 = Minecraft.getMinecraft().player.world.getPlayerEntityByUUID(playerGirl.getBoundPlayerUuid());

      try {
         if (player2 == null) {
            return;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      try {
         this.MainHandStack = player2.getHeldItemMainhand();
         this.OffHandStack = player2.getHeldItemOffhand();
         this.u = playerGirl.ah;
         this.r = playerGirl.ad;
         this.CurrentGirl = (PlayerGirlEntity)girl;
         this.RenderTick = f2;
         playerGirl.syncEquipment(player2);
         if (this.a(player2, girl)) {
            this.renderLivingLabel(girl, player2.getName(), d, d2 + playerGirl.getRenderLabelOffset(), d3, 300);
         }
      } catch (IllegalStateException error4) {
         throw rethrow(error4);
      }

      super.a(girl, d, d2, d3, f, f2);
   }

   @Override
   public Entity c(GirlEntity girl) {
      try {
         if (!(girl instanceof PlayerGirlEntity)) {
            return girl;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      PlayerGirlEntity playerGirl = (PlayerGirlEntity)girl;
      EntityPlayer player = playerGirl.getBoundPlayer();

      try {
         return (Entity)(player == null ? girl : player);
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }
   }

   boolean a(EntityPlayer player2, GirlEntity girl) {
      try {
         if (player2.getPersistentID().equals(Minecraft.getMinecraft().player.getPersistentID())) {
            return false;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      GirlAnimationState girlAnimationState = girl.getCurrentAction();

      try {
         if (girlAnimationState == null) {
            return true;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if (!girlAnimationState.hideNameTag) {
            return true;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      return false;
   }

   protected void a(String string, GeoBone bone) {
   }

   protected void a(String string, GeoBone bone, PlayerGirlEntity playerGirl, BufferBuilder bufferBuilder) {
   }

   @Override

   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
        block68: {
            block79: {
                block67: {
                    block66: {
                        block65: {
                            block63: {
                                block64: {
                                    block73: {
                                        block61: {
                                            block62: {
                                                block71: {
                                                    block70: {
                                                        block59: {
                                                            block60: {
                                                                string = bone.getName();
                                                                if (!this.r) break block59;
                                                                try {
                                                                    block69: {
                                                                        if (!string.equals("upperBody")) break block60;
                                                                        break block69;
                                                                        catch (IllegalStateException error) {
                                                                            throw GirlPlayerRenderer.rethrow(error);
                                                                        }
                                                                    }
                                                                    bone.setRotationX(bone.getRotationX() - 0.5f);
                                                                }
                                                                catch (IllegalStateException error2) {
                                                                    throw GirlPlayerRenderer.rethrow(error2);
                                                                }
                                                            }
                                                            try {
                                                                if (string.equals("head")) {
                                                                    bone.setRotationX(bone.getRotationX() + 0.5f);
                                                                }
                                                            }
                                                            catch (IllegalStateException error3) {
                                                                throw GirlPlayerRenderer.rethrow(error3);
                                                            }
                                                        }
                                                        try {
                                                            if (string.equals("head")) {
                                                                this.a(bufferBuilder, bone, Color.ofRGB(f, f2, f3));
                                                            }
                                                        }
                                                        catch (IllegalStateException error4) {
                                                            throw GirlPlayerRenderer.rethrow(error4);
                                                        }
                                                        this.a(string, bone);
                                                        this.a(string, bone, this.CurrentGirl, bufferBuilder);
                                                        if (!this.u) break block61;
                                                        if (this.MainHandStack.getItem() instanceof ItemBow) ** GOTO lbl43
                                                        break block70;
                                                        catch (IllegalStateException error5) {
                                                            throw GirlPlayerRenderer.rethrow(error5);
                                                        }
                                                    }
                                                    if (!(this.OffHandStack.getItem() instanceof ItemBow)) break block61;
                                                    break block71;
                                                    catch (IllegalStateException error6) {
                                                        throw GirlPlayerRenderer.rethrow(error6);
                                                    }
                                                }
                                                try {
                                                    block72: {
                                                        if (!string.equals("armR")) break block62;
                                                        break block72;
                                                        catch (IllegalStateException error7) {
                                                            throw GirlPlayerRenderer.rethrow(error7);
                                                        }
                                                    }
                                                    bone.setRotationX(bone.getRotationX() - this.RenderEntity.rotationPitch / 50.0f);
                                                }
                                                catch (IllegalStateException error8) {
                                                    throw GirlPlayerRenderer.rethrow(error8);
                                                }
                                            }
                                            try {
                                                if (string.equals("armL")) {
                                                    bone.setRotationY(bone.getRotationY() - this.RenderEntity.rotationPitch / 50.0f);
                                                }
                                            }
                                            catch (IllegalStateException error9) {
                                                throw GirlPlayerRenderer.rethrow(error9);
                                            }
                                            if (this.OffHandStack.getItem() instanceof ItemBow) {
                                                stack = this.OffHandStack;
                                                this.OffHandStack = this.MainHandStack;
                                                this.MainHandStack = stack;
                                            }
                                        }
                                        if (!this.u) break block63;
                                        if (!(this.MainHandStack.getItem() instanceof ItemShield)) break block63;
                                        break block73;
                                        catch (IllegalStateException error10) {
                                            throw GirlPlayerRenderer.rethrow(error10);
                                        }
                                    }
                                    try {
                                        block74: {
                                            if (!string.equals("armR")) break block64;
                                            break block74;
                                            catch (IllegalStateException error11) {
                                                throw GirlPlayerRenderer.rethrow(error11);
                                            }
                                        }
                                        bone.setRotationZ(0.0f);
                                        bone.setRotationX(0.5f);
                                        break block63;
                                    }
                                    catch (IllegalStateException error12) {
                                        throw GirlPlayerRenderer.rethrow(error12);
                                    }
                                }
                                if (!(this.OffHandStack.getItem() instanceof ItemShield)) break block63;
                                try {
                                    block75: {
                                        if (!string.equals("armL")) break block63;
                                        break block75;
                                        catch (IllegalStateException error13) {
                                            throw GirlPlayerRenderer.rethrow(error13);
                                        }
                                    }
                                    bone.setRotationZ(0.0f);
                                    bone.setRotationX(0.5f);
                                }
                                catch (IllegalStateException error14) {
                                    throw GirlPlayerRenderer.rethrow(error14);
                                }
                            }
                            if (!string.equals("weapon")) break block65;
                            try {
                                block76: {
                                    if (this.MainHandStack.isEmpty()) break block65;
                                    break block76;
                                    catch (IllegalStateException error15) {
                                        throw GirlPlayerRenderer.rethrow(error15);
                                    }
                                }
                                this.a(bufferBuilder, bone, false);
                            }
                            catch (IllegalStateException error16) {
                                throw GirlPlayerRenderer.rethrow(error16);
                            }
                        }
                        if (!string.equals("offhand")) break block66;
                        try {
                            block77: {
                                if (this.OffHandStack.isEmpty()) break block66;
                                break block77;
                                catch (IllegalStateException error17) {
                                    throw GirlPlayerRenderer.rethrow(error17);
                                }
                            }
                            this.a(bufferBuilder, bone, true);
                        }
                        catch (IllegalStateException error18) {
                            throw GirlPlayerRenderer.rethrow(error18);
                        }
                    }
                    GirlPlayerRenderer.MATRIX_STACK.push();
                    GirlPlayerRenderer.MATRIX_STACK.translate(bone);
                    GirlPlayerRenderer.MATRIX_STACK.moveToPivot(bone);
                    GirlPlayerRenderer.MATRIX_STACK.rotate(bone);
                    GirlPlayerRenderer.MATRIX_STACK.scale(bone);
                    GirlPlayerRenderer.MATRIX_STACK.moveBackFromPivot(bone);
                    if (!"Head2".equals(string)) break block67;
                    try {
                        block78: {
                            if (this.c()) break block67;
                            break block78;
                            catch (IllegalStateException error19) {
                                throw GirlPlayerRenderer.rethrow(error19);
                            }
                        }
                        GirlPlayerRenderer.MATRIX_STACK.pop();
                        return;
                    }
                    catch (IllegalStateException error20) {
                        throw GirlPlayerRenderer.rethrow(error20);
                    }
                }
                if ("neck".equals(string)) ** GOTO lbl152
                if (!"head".equals(string)) break block68;
                break block79;
                catch (IllegalStateException error21) {
                    throw GirlPlayerRenderer.rethrow(error21);
                }
            }
            try {
                block80: {
                    if (this.a()) break block68;
                    break block80;
                    catch (IllegalStateException error22) {
                        throw GirlPlayerRenderer.rethrow(error22);
                    }
                }
                GirlPlayerRenderer.MATRIX_STACK.pop();
                return;
            }
            catch (IllegalStateException error23) {
                throw GirlPlayerRenderer.rethrow(error23);
            }
        }
        if (!bone.isHidden) {
            stack = this.a(string, f, f2, f3);
            f = stack.x;
            f2 = stack.y;
            f3 = stack.z;
            d = stack.w;
            if (!this.ProcessedBones.contains(string)) {
                for (Object obj : bone.childCubes) {
                    GirlPlayerRenderer.MATRIX_STACK.push();
                    GlStateManager.pushMatrix();
                    this.CachedBone = bone;
                    this.a(bufferBuilder, (GeoCube)obj, f, f2, f3, f4, d);
                    GlStateManager.popMatrix();
                    GirlPlayerRenderer.MATRIX_STACK.pop();
                }
            }
            for (Object obj2 : bone.childBones) {
                try {
                    if (d == 0.0) {
                        this.renderRecursively(bufferBuilder, (GeoBone)obj2, f, f2, f3, f4);
                        continue;
                    }
                }
                catch (IllegalStateException error24) {
                    throw GirlPlayerRenderer.rethrow(error24);
                }
                this.a(bufferBuilder, (GeoBone)obj2, f, f2, f3, f4, d);
            }
        }
        try {
            GirlPlayerRenderer.MATRIX_STACK.pop();
        }
        catch (IllegalStateException error25) {
            // empty catch block
        }
    }


   boolean a() {
        boolean flag;
        block11: {
            block10: {
                try {
                    if (!((PlayerGirlEntity)this.RenderEntity).f()) {
                        return true;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
                try {
                    if (GirlPlayerRenderer.Mc.gameSettings.thirdPersonView != 0) {
                        return true;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
                try {
                    try {
                        if (!(GirlPlayerRenderer.Mc.currentScreen instanceof GuiInventory) && !(GirlPlayerRenderer.Mc.currentScreen instanceof GuiContainerCreative)) break block10;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GirlPlayerRenderer.rethrow(illegalStateException);
                    }
                    flag = true;
                    break block11;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
            }
            flag = false;
        }
        return flag;
    }

   void a(BufferBuilder bufferBuilder, GeoBone bone, Color color) {
      GlStateManager.pushMatrix();
      Tessellator.getInstance().draw();
      MatrixUtil.applyGeoBoneTransform(IGeoRenderer.MATRIX_STACK, bone);
      GL11.glEnable(2896);
      this.c();
      new GirlElytraRenderer(this).render(this.RenderEntity, this.RenderEntity.limbSwing, this.RenderEntity.limbSwingAmount, this.RenderTick, 0.0F, 0.0F, 0.0F, color);
      this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      GL11.glDisable(2896);
      GlStateManager.popMatrix();
   }

   protected void c() {
   }


   void a(BufferBuilder bufferBuilder, GeoBone bone, boolean flag) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 5[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

   protected void a(boolean flag, ItemStack stack) {
      float f;
      label16: {
         try {
            if (flag) {
               f = 200.0F;
               break label16;
            }
         } catch (IllegalStateException error) {
            throw rethrow(error);
         }

         f = 90.0F;
      }

      GlStateManager.rotate(f, 1.0F, 0.0F, 0.0F);
   }

   protected void a(boolean flag) {
      GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
   }


   protected void a(boolean flag, boolean flag2) {
        block8: {
            block7: {
                try {
                    try {
                        if (!flag) break block7;
                        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        if (!flag2) break block8;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GirlPlayerRenderer.rethrow(illegalStateException);
                    }
                    GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.rotate((float)35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.rotate((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.228f);
                    break block8;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GirlPlayerRenderer.rethrow(illegalStateException);
                }
            }
            try {
                if (flag2) {
                    GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.translate((float)0.0f, (float)0.165f, (float)0.0f);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw GirlPlayerRenderer.rethrow(illegalStateException);
            }
        }
    }

   private static IllegalStateException rethrow(IllegalStateException error) {
      return error;
   }
}
