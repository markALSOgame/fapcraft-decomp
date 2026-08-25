package com.trolmastercard.sexmod;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Map;
import net.minecraftforge.client.event.InputEvent;

public class GuiCustomizeGirl extends GuiScreen {
   public static final ResourceLocation Texture = new ResourceLocation("sexmod", "textures/gui/clothing_icons.png");
   static final int r = 20;
   static final float j = 0.25F;
   int LayoutTopPx = 0;
   int LayoutBottomPx = 0;
   float PreviewYaw = 0.0F;
   public static float b = 0.0F;
   protected static List<Integer> a = new ArrayList<>();
   protected static int s = 0;
   protected static int h = 0;
   GirlEntity Girl;
   boolean RemovingCloth = false;
   GuiClothingList ClothingList;
   public static List<Entry<GirlBodySlot, Entry<List<String>, Integer>>> ClothingOptions = new ArrayList<>();
   final UUID GirlUuid;
   int OptionCount;
   int PrevScrollOffset;
   public boolean MagicSlotsDirty = false;
   int SelectedSlot = 0;
   int e = 1;

   public GuiCustomizeGirl(@Nonnull GirlEntity girl) {
      this.mc = Minecraft.getMinecraft();
      this.GirlUuid = girl.getGirlUuid();
      GirlRegistry girlType = GirlRegistry.getByEntity(girl);
      if (girlType == null) {
         girlType = GirlRegistry.JENNY;
      }

      try {
         Constructor constructor = girlType.npcClass.getConstructor(World.class);
         this.Girl = (GirlEntity)constructor.newInstance(this.mc.world);
         this.Girl.setTracked(true);
      } catch (Exception error) {
         error.printStackTrace();
      }

      this.e();
      String string = girl.C();
      this.Girl.getDataManager().set(GirlEntity.CustomModelKey, string);
      int i = 0;

      for (String string2 : this.Girl.Y()) {
         GirlBodySlot girlBodySlot = FilePersistence.getBodySlot(string2);

         try {
            if (GirlBodySlot.CUSTOM_BONE.equals(girlBodySlot)) {
               i++;
            }
         } catch (Exception error2) {
            throw rethrow(error2);
         }

         Entry entry = null;

         label65: {
            label79: {
               try {
                  if (GirlBodySlot.CUSTOM_BONE.equals(girlBodySlot) && i > 1) {
                     break label79;
                  }
               } catch (Exception error3) {
                  throw rethrow(error3);
               }

               Iterator iterator2 = ClothingOptions.iterator();

               while (true) {
                  if (!iterator2.hasNext()) {
                     break label65;
                  }

                  Entry entry2 = (Entry)iterator2.next();
                  if (((GirlBodySlot)entry2.getKey()).equals(girlBodySlot)) {
                     entry = entry2;
                  }
               }
            }

            entry = getClothingData(this.Girl);
         }

         try {
            if (entry == null) {
               continue;
            }
         } catch (Exception error4) {
            throw rethrow(error4);
         }

         ClothingOptions.remove(entry);
         int i2 = ((List)((Entry)entry.getValue()).getKey()).indexOf(string2);
         if (i2 == -1) {
            i2 = 0;
         }

         ((Entry)entry.getValue()).setValue(i2);
         ClothingOptions.add(entry);
      }
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.ClothingList.handleMouseInput();
   }

   public static HashSet<String> getSelectedClothingOptions() {
      HashSet set = new HashSet();

      for (Entry entry : ClothingOptions) {
         try {
            if (((List)((Entry)entry.getValue()).getKey()).size() == 1) {
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         Entry entry2 = (Entry)entry.getValue();
         List list = (List)entry2.getKey();
         Integer i = (Integer)entry2.getValue();
         set.add(list.get(i));
      }

      return set;
   }

   public static Entry<GirlBodySlot, Entry<List<String>, Integer>> getClothingData(GirlEntity girl) {
      ArrayList list = new ArrayList();
      list.add("cross");
      list.addAll(FilePersistence.getModelNames(girl).get(GirlBodySlot.CUSTOM_BONE));
      return new SimpleEntry<>(GirlBodySlot.CUSTOM_BONE, new SimpleEntry<>(list, 0));
   }

   void e() {
      ClothingOptions.clear();
      List list = this.Girl.d(this.GirlUuid);
      this.OptionCount = list.size();
      ClothingOptions.addAll(list);

      for (GirlBodySlot girlBodySlot : GirlBodySlot.values()) {
         try {
            if (girlBodySlot == GirlBodySlot.GIRL_SPECIFIC) {
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         ArrayList list2 = new ArrayList();
         list2.add("cross");
         ClothingOptions.add(new SimpleEntry<>(girlBodySlot, new SimpleEntry<>(list2, 0)));
      }

      for (Entry entry : FilePersistence.getModelNames(this.Girl).entrySet()) {
         Entry entry2 = null;

         for (Entry entry3 : ClothingOptions) {
            if (((GirlBodySlot)entry.getKey()).equals(entry3.getKey())) {
               entry2 = entry3;
            }
         }

         try {
            if (entry2 == null) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         int i = ClothingOptions.indexOf(entry2);
         ClothingOptions.remove(entry2);
         ((List)((Entry)entry2.getValue()).getKey()).addAll((Collection)entry.getValue());
         ClothingOptions.add(i, entry2);
      }
   }

   public void initGui() {
      this.ClothingList = new GuiClothingList(this.mc, this);
   }

   public void setWorldAndResolution(Minecraft mc, int i, int i2) {
      super.setWorldAndResolution(mc, i, i2);
      this.LayoutTopPx = this.a(76.0F);
      this.LayoutBottomPx = this.b(89.0F);
      this.PreviewYaw = 90.0F;
   }

   boolean a(int i, int i2, int i3, int i4, int i5, int i6) {
      try {
         if (i < i3) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (i > i5) {
            return false;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (i2 < i4) {
            return false;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         return i2 <= i6;
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }
   }

   public void drawScreen(int i, int i2, float f) {
      try {
         super.drawScreen(i, i2, f);
         if (this.RemovingCloth) {
            b = b + LerpMath.lerp(h, s, f);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.a();
      this.mc.renderEngine.bindTexture(Texture);
      int i3 = this.LayoutTopPx - this.a(15.0F);
      int i4 = this.LayoutBottomPx - 20;

      GuiCustomizeGirl gui;
      int i5;
      int i6;
      int bv;
      int bv2;
      label32: {
         try {
            gui = this;
            i5 = i3;
            i6 = i4;
            bv = 100;
            if (this.a(i, i2, i3, i4, i3 + 20, i4 + 20)) {
               bv2 = 40;
               break label32;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         bv2 = 20;
      }

      try {
         gui.drawTexturedModalRect(i5, i6, bv, bv2, 20, 20);
         if (FilePersistence.getServerAddress() == null) {
            this.b(i3, i, i2);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      this.a(this.LayoutTopPx, this.LayoutBottomPx, this.PreviewYaw, this.Girl, 1.2345679F);
      this.Girl.onUpdate();
      this.ClothingList.drawScreen(i, i2, f);
   }

   void b(int i, int i2, int i3) {
      int i4 = this.LayoutBottomPx - 40;

      GuiCustomizeGirl gui;
      int i5;
      int i6;
      int bv;
      int bv2;
      label41: {
         try {
            gui = this;
            i5 = i;
            i6 = i4;
            bv = 120;
            if (this.a(i2, i3, i, i4, i + 20, i4 + 20)) {
               bv2 = 40;
               break label41;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         bv2 = 20;
      }

      label34: {
         try {
            gui.drawTexturedModalRect(i5, i6, bv, bv2, 20, 20);
            i4 -= 20;
            gui = this;
            i5 = i;
            i6 = i4;
            bv = 20;
            if (this.a(i2, i3, i, i4, i + 20, i4 + 20)) {
               bv2 = 170;
               break label34;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         bv2 = 150;
      }

      label27: {
         try {
            gui.drawTexturedModalRect(i5, i6, bv, bv2, 20, 20);
            i4 -= 20;
            gui = this;
            i5 = i;
            i6 = i4;
            bv = 0;
            if (this.a(i2, i3, i, i4, i + 20, i4 + 20)) {
               bv2 = 170;
               break label27;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         bv2 = 150;
      }

      gui.drawTexturedModalRect(i5, i6, bv, bv2, 20, 20);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   void c() {
      this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
      HashSet set = new HashSet();
      ArrayList list = new ArrayList();

      for (Entry entry : ClothingOptions) {
         try {
            if (entry.getKey() == GirlBodySlot.GIRL_SPECIFIC) {
               list.add(((Entry)entry.getValue()).getValue());
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         Entry entry2 = (Entry)entry.getValue();
         Integer i = (Integer)entry2.getValue();

         try {
            if (i == 0) {
               continue;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         String string = (String)((List)entry2.getKey()).get(i);
         set.add(string);
      }

      NetworkHandler.channel.sendToServer(new PacketUploadModelString(GirlEntity.joinModelNames(set), this.GirlUuid, list));
      this.mc.player.closeScreen();
   }

   public void a(GirlBodySlot girlBodySlot, boolean flag, int i) {
      this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();
      int i2 = 0;

      for (Entry entry : ClothingOptions) {
         try {
            if (((GirlBodySlot)entry.getKey()).equals(girlBodySlot)) {
               list.add(entry);
               list2.add(i2);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         i2++;
      }

      try {
         if (list.size() == 0) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Entry entry2;
      int i3;
      if (list.size() == 1) {
         entry2 = (Entry)list.get(0);
         i3 = (Integer)list2.get(0);
      } else {
         int i4;
         label87: {
            label86: {
               try {
                  if (this.OptionCount == 0 || i > this.OptionCount - 1 + GirlBodySlot.getClothingSlotCount()) {
                     break label86;
                  }
               } catch (RuntimeException error3) {
                  throw rethrow(error3);
               }

               i4 = i;
               break label87;
            }

            i4 = i - (this.OptionCount + GirlBodySlot.getClothingSlotCount());
         }

         entry2 = (Entry)list.get(i4);
         i3 = (Integer)list2.get(i4);
      }

      try {
         if (entry2 == null) {
            return;
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      Entry entry3 = (Entry)entry2.getValue();
      int i5 = (Integer)entry3.getValue();
      int i6 = ((List)entry3.getKey()).size();

      label71: {
         label70: {
            try {
               if (flag) {
                  if (++i5 < i6) {
                     break label71;
                  }
                  break label70;
               }
            } catch (RuntimeException error5) {
               throw rethrow(error5);
            }

            if (--i5 < 0) {
               i5 = i6 - 1;
            }
            break label71;
         }

         i5 = 0;
      }

      ClothingOptions.set(i3, new SimpleEntry<>((GirlBodySlot)entry2.getKey(), new SimpleEntry<>((List<String>)((Entry)entry2.getValue()).getKey(), i5)));
      ArrayList list3 = new ArrayList();

      for (Entry entry4 : ClothingOptions) {
         try {
            if (entry4.getKey() == GirlBodySlot.GIRL_SPECIFIC) {
               list3.add(entry4);
            }
         } catch (RuntimeException error6) {
            throw rethrow(error6);
         }
      }

      this.Girl.b(list3);
   }

   public void a(int i, int i2, float f, PreviewEntity previewEntity) {
      this.a(i, i2, f, previewEntity, 1.876945F);
   }

   public void a(PreviewEntity previewEntity) {
      GuiCustomizeGirl gui;
      int i;
      int i2;
      float f2;
      PreviewEntity previewEntity2;
      float f3;
      byte bv;
      label16: {
         try {
            gui = this;
            i = this.LayoutTopPx;
            i2 = this.LayoutBottomPx;
            f2 = this.PreviewYaw;
            previewEntity2 = previewEntity;
            f3 = 2.876945F;
            if (previewEntity.f) {
               bv = 1;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         bv = 0;
      }

      gui.a(i, i2, f2, previewEntity2, f3, bv);
   }

   public void a(String string, int i, int i2) {
      this.drawHoveringText(string, i, i2);
   }

   protected void mouseClickMove(int i, int i2, int i3, long l) {
      try {
         super.mouseClickMove(i, i2, i3, l);
         if (i3 != 0) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (i < this.width / 2) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      int i4 = i - this.PrevScrollOffset;
      GuiCustomizeGirl.add(i4);
      this.PrevScrollOffset = i;
   }

   protected void mouseClicked(int i, int i2, int i3) throws IOException {
      try {
         super.mouseClicked(i, i2, i3);
         this.ClothingList.mouseClicked(i, i2, i3);
         if (i3 != 0) {
            return;
         }
      } catch (URISyntaxException error) {
         throw rethrow(error);
      }

      this.MagicSlotsDirty = true;
      this.RemovingCloth = true;
      this.PrevScrollOffset = i;
      int i4 = this.LayoutTopPx - this.a(15.0F);
      int i5 = this.LayoutBottomPx - 20;

      try {
         if (this.a(i, i2, i4, i5, i4 + 20, i5 + 20)) {
            this.c();
         }
      } catch (URISyntaxException error2) {
         throw rethrow(error2);
      }

      try {
         if (FilePersistence.getServerAddress() != null) {
            return;
         }
      } catch (URISyntaxException error3) {
         throw rethrow(error3);
      }

      i5 = this.LayoutBottomPx - 40;
      if (this.a(i, i2, i4, i5, i4 + 20, i5 + 20)) {
         this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
         this.mc.player.closeScreen();
         int i6 = FilePersistence.reloadModels(true);

         try {
            if (i6 != 0) {
               FilePersistence.ServerActive = true;
               return;
            }
         } catch (URISyntaxException error4) {
            throw rethrow(error4);
         }

         GirlEntity girl = GirlEntity.getClientSideByUuid(this.GirlUuid);

         try {
            if (girl != null) {
               GuiCustomizeGirl(girl);
            }
         } catch (URISyntaxException error5) {
            throw rethrow(error5);
         }
      } else {
         try {
            i5 -= 20;
            if (this.a(i, i2, i4, i5, i4 + 20, i5 + 20)) {
               Desktop.getDesktop().open(new File(FilePersistence.getClientModelsPath()));
               return;
            }
         } catch (URISyntaxException error6) {
            throw rethrow(error6);
         }

         try {
            i5 -= 20;
            if (this.a(i, i2, i4, i5, i4 + 20, i5 + 20)) {
               try {
                  Desktop.getDesktop().browse(new URI("http://fapcraft.org/assets/video/tutorial/girl_wand.mp4"));
               } catch (URISyntaxException error7) {
                  throw new RuntimeException(error7);
               }
            }
         } catch (IOException error8) {
            throw rethrow(error8);
         }
      }
   }

   protected void mouseReleased(int i, int i2, int i3) {
      try {
         super.mouseReleased(i, i2, i3);
         if (i3 == 0) {
            this.RemovingCloth = false;
            this.MagicSlotsDirty = false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.SelectedSlot = h;
   }

   int a(float f) {
      return Math.round(this.width * (f / 100.0F));
   }

   int b(float f) {
      return Math.round(this.height * (f / 100.0F));
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      this.Girl.world.removeEntityDangerously(this.Girl);
      GuiCustomizeGirl.clear();
      ClothingOptions.clear();
   }

   public GirlEntity d() {
      return this.Girl;
   }

   public void a(int i, int i2, int i3, int i4) {
      this.mc.renderEngine.bindTexture(Texture);
      this.drawTexturedModalRect(i, i2, i3, i4, 20, 20);
   }

   public void a(int i, int i2, int i3) {
      this.a(i, i2, i3, 0);
   }

   public void a(int i, int i2, Vec2i vec2i) {
      this.a(i, i2, vec2i.X, vec2i.Y);
   }

   void a(int i, int i2, float f, EntityLivingBase livingBase, float f2) {
      this.a(i, i2, f, livingBase, f2, 0);
   }

   void a(int i, int i2, float f, EntityLivingBase livingBase, float f2, int i3) {
      float f3 = livingBase.renderYawOffset;
      float f4 = livingBase.rotationYaw;
      float f5 = livingBase.rotationPitch;
      float f6 = livingBase.prevRotationYawHead;
      float f7 = livingBase.rotationYawHead;
      livingBase.renderYawOffset = 0.0F;
      livingBase.rotationYaw = 0.0F;
      livingBase.rotationPitch = 0.0F;
      livingBase.prevRotationYawHead = 0.0F;
      livingBase.rotationYawHead = 0.0F;
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate(i, i2, 50.0F);
      GlStateManager.scale(-f, f, f);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.0F, i3);
      GlStateManager.rotate(b, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(0.25F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      renderManager.setPlayerViewY(180.0F);
      renderManager.setRenderShadow(false);
      renderManager.renderEntity(livingBase, 0.0, 0.0, 0.0, 0.0F, f2, false);
      renderManager.setRenderShadow(true);
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
      livingBase.renderYawOffset = f3;
      livingBase.rotationYaw = f4;
      livingBase.rotationPitch = f5;
      livingBase.prevRotationYawHead = f6;
      livingBase.rotationYawHead = f7;
   }

   void a() {
      try {
         if (this.RemovingCloth) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      float f = Minecraft.getDebugFPS();
      if (f == 0.0F) {
         f = 0.1F;
      }

      try {
         if (this.SelectedSlot == 0) {
            b = b + this.e * 10 / f;
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         b = b + this.SelectedSlot / f;
         this.SelectedSlot = (int)(this.SelectedSlot * (1.0F - 0.25F / f));
         if (Math.abs(this.SelectedSlot) > 10) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      GuiCustomizeGirl gui;
      byte bv;
      label33: {
         try {
            gui = this;
            if (this.SelectedSlot > 0) {
               bv = 1;
               break label33;
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         bv = -1;
      }

      gui.e = bv;
      this.SelectedSlot = 0;
   }

   @SideOnly(Side.CLIENT)

   public static void openGui(@Nonnull GirlEntity girl) {
      Minecraft minecraft = Minecraft.getMinecraft();
      if (minecraft.currentScreen instanceof GuiCustomizeGirl) {
         return;
      }

      boolean flag = FilePersistence.getServerAddress() == null || FilePersistence.isServerWhitelisted();
      if (!flag) {
         minecraft.player.sendStatusMessage(new net.minecraft.util.text.TextComponentString("You have to whitelist the server to use its custom models. " + net.minecraft.util.text.TextFormatting.YELLOW + "/whitelistserver"), true);
         return;
      }

      minecraft.addScheduledTask(() -> minecraft.displayGuiScreen(new GuiCustomizeGirl(girl)));
   }

   private static Exception rethrow(Exception error) {
      return error;
   }

   @SideOnly(Side.CLIENT)
   public static class b {
      @SubscribeEvent
      @SideOnly(Side.CLIENT)

      public void a(KeyInputEvent keyInputEvent) {
            block13: {
                boolean flag;
                block15: {
                    block14: {
                        try {
                            if (!ClientProxy.keyBindings[1].isPressed()) {
                                return;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw rethrow(runtimeException);
                        }
                        try {
                            try {
                                if (!FilePersistence.ServerActive) break block13;
                                if (0 == FilePersistence.reloadModels(true)) break block14;
                            }
                            catch (RuntimeException runtimeException) {
                                throw rethrow(runtimeException);
                            }
                            flag = true;
                            break block15;
                        }
                        catch (RuntimeException runtimeException) {
                            throw rethrow(runtimeException);
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
                    throw rethrow(runtimeException);
                }
            }
            Minecraft minecraft = Minecraft.getMinecraft();
            PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(minecraft.player.getPersistentID());
            try {
                if (playerGirl == null) {
                    minecraft.player.sendStatusMessage((ITextComponent)new TextComponentString("You have to turn into the girl you want to customize"), true);
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw rethrow(runtimeException);
            }
            GuiCustomizeGirl.openGui(playerGirl);
        }

      @SubscribeEvent
      @SideOnly(Side.CLIENT)
      public void a(ClientTickEvent clientTickEvent) {
         GuiCustomizeGirl.h = GuiCustomizeGirl.s;
         GuiCustomizeGirl.s = 0;

         for (Integer i : GuiCustomizeGirl.a) {
            GuiCustomizeGirl.s = GuiCustomizeGirl.s + i;
         }

         GuiCustomizeGirl.a.clear();
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
