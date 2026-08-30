package com.trolmastercard.sexmod;

import java.io.PrintWriter;
import java.io.StringWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class HandItemRenderer {
   Minecraft Mc;
   float EquipProgress = 2.0F;
   boolean IsRendering = false;
   private static final ResourceLocation MapBackgroundTexture = new ResourceLocation("textures/map/map_background.png");
   ModelPartProvider Hand;
   ResourceLocation Texture;
   Vec3i Color;
   float a = 0.0F;

   @SubscribeEvent

   public void onRenderSpecificHand(RenderSpecificHandEvent renderSpecificHandEvent) {
        block26: {
            float f;
            block23: {
                block27: {
                    ItemStack itemStack;
                    ItemRenderer itemRenderer;
                    EntityPlayerSP mcPlayer;
                    float f2;
                    float f3;
                    block25: {
                        PlayerGirlEntity.C_();
                        PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(Minecraft.getMinecraft().player.getPersistentID());
                            if (playerGirl == null) {
                                return;
                            }
                        int i = playerGirl.getOutfitIndex();
                            this.Hand = playerGirl.getHandModel(i);
                            this.Texture = new ResourceLocation("sexmod", playerGirl.getHandTexture(i));
                            this.Color = playerGirl.b(i);
                            if (this.Hand == null) {
                                System.out.println("HAND IS NULL uwu did you forget to assign this girl a hand owo?");
                                return;
                            }
                        this.Mc = Minecraft.getMinecraft();
                        f3 = 0.0f;
                        f2 = 0.0f;
                        try {
                            itemRenderer = this.Mc.getItemRenderer();
                            if (DevConsole.isDeobfuscatedEnvironment()) {
                                f3 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, itemRenderer, "prevEquippedProgressMainHand")).floatValue();
                                f2 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, itemRenderer, "equippedProgressMainHand")).floatValue();
                            } else {
                                f3 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, itemRenderer, "prevEquippedProgressMainHand")).floatValue();
                                f2 = ((Float)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, itemRenderer, "equippedProgressMainHand")).floatValue();
                            }
                            this.EquipProgress = 2.0f - (f3 + (f2 - f3) * renderSpecificHandEvent.getPartialTicks());
                        }
                        catch (Exception exception) {
                            System.out.println("couldnt do the reflection thingy");
                            StringWriter stringWriter = new StringWriter();
                            exception.printStackTrace(new PrintWriter(stringWriter));
                            Minecraft.getMinecraft().player.sendChatMessage(stringWriter.toString());
                        }
                        mcPlayer = this.Mc.player;
                        f = mcPlayer.getSwingProgress(renderSpecificHandEvent.getPartialTicks());
                        itemStack = this.Mc.player.getHeldItemMainhand();
                            block24: {
                                try {
                                    try {
                                        GlStateManager.color((float)((float)this.Color.getX() / 255.0f), (float)((float)this.Color.getY() / 255.0f), (float)((float)this.Color.getZ() / 255.0f));
                                        if (renderSpecificHandEvent.getHand() != EnumHand.MAIN_HAND) break block23;
                                        if (itemStack.isEmpty()) break block24;
                                    }
                                    catch (Exception exception) {
                                        throw HandItemRenderer.rethrow(exception);
                                    }
                                    if (!(itemStack.getItem() instanceof ItemMap)) break block25;
                                }
                                catch (Exception exception) {
                                    throw HandItemRenderer.rethrow(exception);
                                }
                            }
                            renderSpecificHandEvent.setCanceled(true);
                            this.renderMainHandItem(itemStack, renderSpecificHandEvent.getPartialTicks(), (AbstractClientPlayer)mcPlayer, this.EquipProgress, f);
                            this.IsRendering = true;
                            break block26;
                    }
                        try {
                            if (!(f2 < f3)) break block27;
                            if (!this.IsRendering) break block26;
                        }
                        catch (Exception exception) {
                            throw HandItemRenderer.rethrow(exception);
                        }
                        renderSpecificHandEvent.setCanceled(true);
                        this.renderMainHandItem(itemStack, renderSpecificHandEvent.getPartialTicks(), (AbstractClientPlayer)mcPlayer, this.EquipProgress, f);
                        break block26;
                }
                this.IsRendering = false;
                break block26;
            }
                if (this.Mc.player.getHeldItemOffhand().getItem() instanceof ItemMap) {
                    renderSpecificHandEvent.setCanceled(true);
                    this.renderSideHandItem(EnumHandSide.LEFT, this.EquipProgress - 1.0f, f, this.Mc.player.getHeldItemOffhand());
                }
        }
        GlStateManager.resetColor();
    }


   void renderMainHandItem(ItemStack stack, float f, AbstractClientPlayer abstractClientPlayer, float f2, float f3) {
        block6: {
            block4: {
                block5: {
                    try {
                        try {
                            if (!(stack.getItem() instanceof ItemMap)) break block4;
                            if (!abstractClientPlayer.getHeldItemOffhand().isEmpty()) break block5;
                        }
                        catch (RuntimeException runtimeException) {
                            throw HandItemRenderer.rethrow(runtimeException);
                        }
                        this.renderGirlHandItem(stack, abstractClientPlayer, f3, f);
                        break block6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw HandItemRenderer.rethrow(runtimeException);
                    }
                }
                this.renderSideHandItem(EnumHandSide.RIGHT, f2 - 1.0f, f3, stack);
                break block6;
            }
            this.renderSecondHand(f3, f);
        }
    }


   void renderSideHandItem(EnumHandSide enumHandSide, float f, float f2, ItemStack stack) {
        float f3;
        block6: {
            block8: {
                block7: {
                    float f4;
                    try {
                        f4 = enumHandSide == EnumHandSide.RIGHT ? 1.0f : -1.0f;
                    }
                    catch (RuntimeException runtimeException) {
                        throw HandItemRenderer.rethrow(runtimeException);
                    }
                    f3 = f4;
                    try {
                        try {
                            GlStateManager.translate((float)(f3 * 0.125f), (float)-0.125f, (float)0.0f);
                            if (this.Mc.player.isInvisible()) break block6;
                            GlStateManager.pushMatrix();
                            GlStateManager.rotate((float)(f3 * 10.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                            this.transformSecondHand(f, f2, enumHandSide);
                            GlStateManager.translate((float)-0.5f, (float)-1.1f, (float)0.0f);
                            if (enumHandSide != EnumHandSide.RIGHT) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw HandItemRenderer.rethrow(runtimeException);
                        }
                        GlStateManager.translate((float)0.48f, (float)0.15f, (float)0.0f);
                        break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw HandItemRenderer.rethrow(runtimeException);
                    }
                }
                GlStateManager.translate((float)0.44f, (float)1.3f, (float)1.0f);
            }
            Minecraft.getMinecraft().getTextureManager().bindTexture(this.Texture);
            this.Hand.getRootModel().render(0.175f);
            GlStateManager.popMatrix();
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(f3 * 0.51f), (float)(-0.08f + f * -1.2f), (float)-0.75f);
        float f5 = MathHelper.sqrt((float)f2);
        float f6 = MathHelper.sin((float)(f5 * (float)Math.PI));
        float f7 = -0.5f * f6;
        float f8 = 0.4f * MathHelper.sin((float)(f5 * ((float)Math.PI * 2)));
        float f9 = -0.3f * MathHelper.sin((float)(f2 * (float)Math.PI));
        GlStateManager.translate((float)(f3 * f7), (float)(f8 - 0.3f * f6), (float)f9);
        GlStateManager.rotate((float)(f6 * -45.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.rotate((float)(f3 * f6 * -30.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        this.renderMapItem(stack);
        GlStateManager.popMatrix();
    }

   void renderGirlHandItem(ItemStack stack, AbstractClientPlayer abstractClientPlayer, float f, float f2) {
      float f3 = abstractClientPlayer.prevRotationPitch + (abstractClientPlayer.rotationPitch - abstractClientPlayer.prevRotationPitch) * f2;
      float f4 = MathHelper.sqrt(f);
      float f5 = -0.2F * MathHelper.sin(f * (float) Math.PI);
      float f6 = -0.4F * MathHelper.sin(f4 * (float) Math.PI);
      GlStateManager.translate(0.0F, -f5 / 2.0F, f6);
      float f7 = this.smoothCurve(f3);
      GlStateManager.translate(0.0F, 0.04F + (this.EquipProgress - 1.0F) * -1.2F + f7 * -0.5F, -0.72F);
      GlStateManager.rotate(f7 * -85.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.disableCull();
      GlStateManager.pushMatrix();
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      this.renderGirlHand(EnumHandSide.RIGHT);
      this.renderGirlHand(EnumHandSide.LEFT);
      GlStateManager.popMatrix();
      GlStateManager.enableCull();
      float f8 = MathHelper.sin(f4 * (float) Math.PI);
      GlStateManager.rotate(f8 * 20.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      this.renderMapItem(stack);
      GlStateManager.enableLighting();
   }

   void renderMapItem(ItemStack stack) {
      GlStateManager.resetColor();
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.scale(0.38F, 0.38F, 0.38F);
      GlStateManager.disableLighting();
      this.Mc.getTextureManager().bindTexture(MapBackgroundTexture);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      GlStateManager.translate(-0.5F, -0.5F, 0.0F);
      GlStateManager.scale(0.0078125F, 0.0078125F, 0.0078125F);
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
      bufferBuilder.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
      bufferBuilder.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
      bufferBuilder.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
      bufferBuilder.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
      tessellator.draw();
      MapData mapData = ((ItemMap)stack.getItem()).getMapData(stack, this.Mc.world);

      try {
         if (mapData != null) {
            this.Mc.entityRenderer.getMapItemRenderer().renderMap(mapData, false);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GlStateManager.color(this.Color.getX() / 255.0F, this.Color.getY() / 255.0F, this.Color.getZ() / 255.0F);
   }

   private void renderGirlHand(EnumHandSide enumHandSide) {
      float f;
      label30: {
         try {
            GlStateManager.pushMatrix();
            if (enumHandSide == EnumHandSide.RIGHT) {
               f = 1.0F;
               break label30;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         f = -1.0F;
      }

      float f2 = f;

      label22: {
         try {
            GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(f2 * -41.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(f2 * 0.3F, -1.1F, 0.45F);
            if (enumHandSide == EnumHandSide.RIGHT) {
               GlStateManager.translate(0.63F, 0.36F, 0.0F);
               break label22;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         GlStateManager.translate(1.6F, 0.35F, 0.0F);
      }

      Minecraft.getMinecraft().getTextureManager().bindTexture(this.Texture);
      this.Hand.getRootModel().render(0.175F);
      GlStateManager.popMatrix();
   }

   private float smoothCurve(float f) {
      float f2 = 1.0F - f / 45.0F + 0.1F;
      f2 = MathHelper.clamp(f2, 0.0F, 1.0F);
      return -MathHelper.cos(f2 * (float) Math.PI) * 0.5F + 0.5F;
   }

   void renderSecondHand(float f, float f2) {
      GlStateManager.disableCull();
      GlStateManager.pushMatrix();
      this.transformSecondHand(this.EquipProgress, f, EnumHandSide.RIGHT);
      Minecraft.getMinecraft().getTextureManager().bindTexture(this.Texture);
      this.Hand.getRootModel().render(0.175F);
      GlStateManager.disableBlend();
      GlStateManager.enableCull();
      GlStateManager.popMatrix();
   }

   private void transformSecondHand(float f, float f2, EnumHandSide enumHandSide) {
      boolean flag;
      label30: {
         try {
            if (enumHandSide != EnumHandSide.LEFT) {
               flag = true;
               break label30;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag = false;
      }

      boolean flag2 = flag;

      float f3;

      label22: {
         try {
            if (flag2) {
               f3 = 1.0F;
               break label22;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         f3 = -1.0F;
      }

      float f4 = f3;
      float f5 = MathHelper.sqrt(f2);
      float f6 = -0.3F * MathHelper.sin(f5 * (float) Math.PI);
      float f7 = 0.4F * MathHelper.sin(f5 * (float) (Math.PI * 2));
      float f8 = -0.4F * MathHelper.sin(f2 * (float) Math.PI);
      GlStateManager.translate(f4 * (f6 + 0.64000005F), f7 + -0.6F + f * -0.6F, f8 + -0.71999997F);
      GlStateManager.rotate(f4 * 45.0F, 0.0F, 1.0F, 0.0F);
      float f9 = MathHelper.sin(f2 * f2 * (float) Math.PI);
      float f10 = MathHelper.sin(f5 * (float) Math.PI);
      GlStateManager.rotate(f4 * f10 * 70.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f4 * f9 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.translate(f4 * -1.0F, 3.6F, 3.5F);
      GlStateManager.rotate(f4 * 120.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f4 * -135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(f4 * 5.6F, 0.0F, 0.0F);
      GlStateManager.translate(0.5F, 1.1F, 0.0F);
   }
 static RuntimeException rethrow(RuntimeException error) {

      return error;

   }


   private static RuntimeException rethrow(Exception error) {
      return new RuntimeException(error);
   }
}
