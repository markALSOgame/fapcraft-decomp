package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

public class GuiClothingList extends GuiListExtended {
   static final int LabelColor = 3809871;
   static final List<GirlBodySlot> BodySlots = Arrays.asList(GirlBodySlot.values());
   static final String WidthTemplate = "MMMMMMMMMM";
   protected static int ItemOffsetY = 5;
   protected static int HalfWidth = 200;
   private List<GuiClothingList.Entry> Entries = new ArrayList<>();
   GuiCustomizeGirl ParentGui;
   boolean h = false;
   float g = 0.0F;

   public GuiClothingList(Minecraft mc, GuiCustomizeGirl gui) {
      super(mc, gui.width / 2, gui.height, 0, gui.height, 30);
      HalfWidth = gui.width / 2;
      this.ParentGui = gui;
   }

   public IGuiListEntry getListEntry(int i) {
      return this.Entries.get(i);
   }

   protected int getSize() {
      return this.Entries.size();
   }

   protected int getScrollBarX() {
      return 0;
   }

   protected void drawContainerBackground(Tessellator tessellator) {
   }

   public void handleMouseInput() {
      try {
         if (!this.isMouseYWithinSlotBounds(this.mouseY)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      int i = Mouse.getEventDWheel();

      try {
         if (i == 0) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      byte bv;
      if (i > 0) {
         bv = -1;
      } else {
         bv = 1;
      }

      this.amountScrolled = this.amountScrolled + bv * this.slotHeight / 2;
   }

   protected void overlayBackground(int i, int i2, int i3, int i4) {
   }

   void a() {
      int i = this.Entries.size() * this.slotHeight;

      try {
         if (i > this.height) {
            this.top = 0;
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      int i2 = this.height - i;
      this.top = i2 / 2;
   }


   public void drawScreen(int i, int i2, float f) {
        boolean flag;
        GuiClothingList gui;
        Entry entry3;
        Entry entry4;
        List<WidthTemplate> list;
        this.Entries.clear();
        int i3 = 0;
        for (Map.Entry<GirlBodySlot, Map.Entry<List<String>, Integer>> entry : GuiCustomizeGirl.ClothingOptions) {
            GirlBodySlot girlBodySlot = entry.getKey();
            Map.Entry<List<String>, Integer> entry2 = entry.getValue();
            try {
                this.Entries.add(new Entry(girlBodySlot, entry2.getKey(), entry2.getValue()));
                if (!GirlBodySlot.CUSTOM_BONE.equals((Object)entry.getKey())) continue;
                ++i3;
            }
            catch (RuntimeException runtimeException) {
                throw GuiClothingList.rethrow(runtimeException);
            }
        }
        this.Entries.sort(Comparator.comparingInt(arg1 -> BodySlots.indexOf((Object)arg1.SelectedSlot)));
        List<String> list2 = FilePersistence.getModelNames(this.ParentGui.Girl).get((Object)GirlBodySlot.CUSTOM_BONE);
        try {
            Entry entry5;
            list2.add(0, "cross");
            list = this.Entries;
            entry4 = entry5;
            entry3 = entry5;
            gui = this;
            flag = i3 > 1;
        }
        catch (RuntimeException runtimeException) {
            throw GuiClothingList.rethrow(runtimeException);
        }
        entry4(flag);
        list.add(entry3);
        this.a();
        this.a(i, i2, f);
        if (!this.h) {
            return;
        }
        this.scrollBy(999999);
        this.h = false;
    }

   void a(int i, int i2, float f) {
      try {
         if (!this.visible) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.mouseX = i;
      this.mouseY = i2;
      this.drawBackground();
      int i3 = this.getScrollBarX();
      int i4 = i3 + 6;
      this.bindAmountScrolled();
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      this.drawContainerBackground(tessellator);
      int i5 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
      int i6 = this.top + 4 - (int)this.amountScrolled;

      try {
         if (this.hasListHeader) {
            this.drawListHeader(i5, i6, tessellator);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      this.drawSelectionBox(i5, i6, i, i2, f);
      GlStateManager.disableDepth();
      this.overlayBackground(0, this.top, 255, 255);
      this.overlayBackground(this.bottom, this.height, 255, 255);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ZERO, DestFactor.ONE);
      GlStateManager.disableAlpha();
      GlStateManager.shadeModel(7425);
      GlStateManager.disableTexture2D();
      int i7 = this.getMaxScroll();
      if (i7 > 0) {
         int i8 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
         i8 = MathHelper.clamp(i8, 32, this.bottom - this.top - 8);
         int i9 = (int)this.amountScrolled * (this.bottom - this.top - i8) / i7 + this.top;
         if (i9 < this.top) {
            i9 = this.top;
         }

         bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         bufferBuilder.pos(i3, this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
         bufferBuilder.pos(i4, this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
         bufferBuilder.pos(i4, this.top, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
         bufferBuilder.pos(i3, this.top, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
         tessellator.draw();
         bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         bufferBuilder.pos(i3, i9 + i8, 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
         bufferBuilder.pos(i4, i9 + i8, 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
         bufferBuilder.pos(i4, i9, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
         bufferBuilder.pos(i3, i9, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
         tessellator.draw();
         bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         bufferBuilder.pos(i3, i9 + i8 - 1, 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
         bufferBuilder.pos(i4 - 1, i9 + i8 - 1, 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
         bufferBuilder.pos(i4 - 1, i9, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
         bufferBuilder.pos(i3, i9, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
         tessellator.draw();
      }

      this.renderDecorations(i, i2);
      GlStateManager.enableTexture2D();
      GlStateManager.shadeModel(7424);
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
   }

   public boolean mouseClicked(int i, int i2, int i3) {
      this.a(i, i2, i3);
      return super.mouseClicked(i, i2, i3);
   }

   void a(int i, int i2, int i3) {
      try {
         if (i > this.width) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      int i4 = this.getAmountScrolled();
      float f = i4 + i2 - 5 - this.top;
      int i5 = Math.round((float)Math.floor(f / this.slotHeight));
      int i6 = (int)Math.round((f / this.slotHeight - Math.floor(f / this.slotHeight)) * this.slotHeight);

      try {
         if (i5 < 0) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (i5 < this.Entries.size()) {
            this.Entries.get(i5).a(i, i6, i3, i5);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   @SideOnly(Side.CLIENT)
   public class Entry implements IGuiListEntry {
      static final int g = 4;
      public GirlBodySlot BodySlot;
      public List<String> BoneList;
      public int RowY;
      FontRenderer FontRenderer;
      boolean a = false;
      boolean Selected = false;

      public Entry(GirlBodySlot girlBodySlot, List<String> list, int i) {
         this.BodySlot = girlBodySlot;
         this.BoneList = list;
         this.RowY = i;
         this.FontRenderer = GuiClothingList.this.mc.fontRenderer;
      }

      public Entry(boolean flag) {
         this.Selected = flag;
         this.WidthTemplate = true;
      }

      boolean b(int i2, int i3, int i4, int i5, int i6, int i7) {
         try {
            if (i2 < i4) {
               return false;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (i2 > i6) {
               return false;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         try {
            if (i3 < i5) {
               return false;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         try {
            return i3 <= i7;
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }
      }


      void b(int i8, int i9, int i10) {
            int i11;
            int i12;
            int i13;
            int i14;
            GuiCustomizeGirl gui;
            block9: {
                block8: {
                    int i15;
                    int i16;
                    int i17;
                    int i18;
                    GuiCustomizeGirl gui2;
                    int i19 = 30;
                    try {
                        ((GuiClothingList)GuiClothingList.this).mc.renderEngine.bindTexture(GuiCustomizeGirl.Texture);
                        gui2 = GuiClothingList.this.ParentGui;
                        i18 = i19;
                        i17 = i8 += 5;
                        i16 = 40;
                        i15 = this.b(i9, i10, i19, i8, i19 + 20, i8 + 20) ? 40 : 20;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                    try {
                        gui2.drawTexturedModalRect(i18, i17, i16, i15, 20, 20);
                        gui = GuiClothingList.this.ParentGui;
                        i14 = i19 += 40;
                        i13 = i8;
                        i12 = this.Selected ? 60 : 80;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                    try {
                        try {
                            if (!this.Selected || !this.b(i9, i10, i19, i8, i19 + 20, i8 + 20)) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiClothingList.Entry.rethrow(runtimeException);
                        }
                        i11 = 40;
                        break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                }
                i11 = 20;
            }
            gui.drawTexturedModalRect(i14, i13, i12, i11, 20, 20);
        }


      void a(int i20, int i21, int i22) {
        try {
            if (i20 > this.width) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GuiClothingList.rethrow(runtimeException);
        }
        int i23 = this.getAmountScrolled();
        float f = i23 + i21 - 5 - this.top;
        int i24 = Math.round((float)Math.floor(f / (float)this.slotHeight));
        int i25 = (int)Math.round(((double)(f / (float)this.slotHeight) - Math.floor(f / (float)this.slotHeight)) * (double)this.slotHeight);
        try {
            if (i24 < 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GuiClothingList.rethrow(runtimeException);
        }
        try {
            if (i24 < this.BoneList.size()) {
                this.BoneList.get(i24).a(i20, i25, i22, i24);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GuiClothingList.rethrow(runtimeException);
        }
    }

      int c(int i26, int i27, int i28, int i29) {
         GuiCustomizeGirl gui3;
         int i30;
         int i31;
         byte bv;
         byte bv2;
         byte bv3;
         label28: {
            try {
               gui3 = GuiClothingList.this.ParentGui;
               i30 = i26;
               i31 = i27;
               bv = 0;
               bv2 = 20;
               if (this.b(i28, i29, i26, i27, i26 + 20, i27 + 20)) {
                  bv3 = 2;
                  break label28;
               }
            } catch (RuntimeException error5) {
               throw rethrow(error5);
            }

            bv3 = 1;
         }

         label21: {
            try {
               gui3.a(i30, i31, bv, bv2 * bv3);
               i26 += 20;
               gui3 = GuiClothingList.this.ParentGui;
               i30 = i26;
               i31 = i27;
               bv = 20;
               bv2 = 20;
               if (this.b(i28, i29, i26, i27, i26 + 20, i27 + 20)) {
                  bv3 = 2;
                  break label21;
               }
            } catch (RuntimeException error6) {
               throw rethrow(error6);
            }

            bv3 = 1;
         }

         gui3.a(i30, i31, bv, bv2 * bv3);
         return i26 + 40;
      }

      void a(int i32, int i33, int i34, int i35, int i36) {
         GuiClothingList.this.ParentGui.drawTexturedModalRect(i32, i33, 140, 20, 79, 20);
         i32 += 4;
         int i37 = i32;
         int i38 = i32 + 71 - 4;
         float f2 = this.a(i33, i37, i38, i34, i35, i36);
         int i39 = (int)LerpMath.lerp(i37, i38, f2);

         GuiCustomizeGirl gui4;
         int i40;
         int i41;
         short sh;
         label17: {
            try {
               gui4 = GuiClothingList.this.ParentGui;
               i40 = i39;
               i41 = i33;
               if (this.b(i34, i35, i39, i33, i39 + 4, i33 + 20)) {
                  sh = 223;
                  break label17;
               }
            } catch (RuntimeException error7) {
               throw rethrow(error7);
            }

            sh = 219;
         }

         gui4.drawTexturedModalRect(i40, i41, sh, 20, 4, 20);
         GuiClothingList.this.ParentGui.Girl.a(i36, (int)(f2 * 100.0F));
      }


      float a(int i42, int i43, int i44, int i45, int i46, int i47) {
            block16: {
                try {
                    if (!GuiClothingList.this.ParentGui.MagicSlotsDirty) {
                        return this.a(i47);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GuiClothingList.Entry.rethrow(runtimeException);
                }
                try {
                    if ((float)i45 > 0.33333334f * (float)GuiClothingList.this.ParentGui.width) {
                        return this.a(i47);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GuiClothingList.Entry.rethrow(runtimeException);
                }
                try {
                    try {
                        if (i46 >= i42 && i46 <= i42 + 20) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                    return this.a(i47);
                }
                catch (RuntimeException runtimeException) {
                    throw GuiClothingList.Entry.rethrow(runtimeException);
                }
            }
            try {
                if (i45 < i43) {
                    return 0.0f;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GuiClothingList.Entry.rethrow(runtimeException);
            }
            try {
                if (i45 > i44) {
                    return 1.0f;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GuiClothingList.Entry.rethrow(runtimeException);
            }
            return (float)(i45 -= i43) / (float)(i44 -= i43);
        }

      float a(int i48) {
         Entry entry = GuiClothingList.this.ParentGui.Girl.d(GuiClothingList.this.ParentGui.GirlUuid).get(i48);
         return ((Integer)((Entry)entry.getValue()).getValue()).intValue() / 100.0F;
      }

      void b(int i49, int i50, int i51, int i52) {
         boolean flag2 = GuiClothingList.this.ParentGui.Girl.h(i52);

         label31: {
            try {
               GuiClothingList.this.mc.renderEngine.bindTexture(GuiCustomizeGirl.Texture);
               if (flag2) {
                  GuiClothingList.this.ParentGui.drawTexturedModalRect(GuiClothingList.ItemOffsetY, i49, 0, 60, 119, 30);
                  break label31;
               }
            } catch (RuntimeException error8) {
               throw rethrow(error8);
            }

            GuiClothingList.this.ParentGui.drawTexturedModalRect(GuiClothingList.ItemOffsetY, i49, 0, 90, 95, 30);
         }

         int i53 = GuiClothingList.ItemOffsetY + 10;

         try {
            i49 += 5;
            GuiClothingList.this.ParentGui.a(i53, i49, GuiClothingList.this.ParentGui.Girl.g(i52));
            i53 += 25;
            if (flag2) {
               this.a(i53, i49, i50, i51, i52);
               return;
            }
         } catch (RuntimeException error9) {
            throw rethrow(error9);
         }

         this.c(i53, i49, i50, i51);
      }

      public void drawEntry(int i54, int i55, int i56, int i57, int i58, int i59, int i60, boolean flag3, float f3) {
         try {
            if (this.WidthTemplate) {
               this.b(i56, i59, i60);
               return;
            }
         } catch (RuntimeException error10) {
            throw rethrow(error10);
         }

         try {
            if (this.BodySlot == GirlBodySlot.GIRL_SPECIFIC) {
               this.b(i56, i59, i60, i54);
               return;
            }
         } catch (RuntimeException error11) {
            throw rethrow(error11);
         }

         this.a(i56, i59, i60);
      }

      void a(String string, int i61, int i62) {
         this.FontRenderer.drawString(string, i61, i62, 3809871);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }


      void b(int i63, int i64) {
            block10: {
                int i65;
                block9: {
                    i65 = 30;
                    try {
                        if (i63 <= i65 || i63 >= i65 + 20) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                    GuiClothingList.this.h = true;
                    GuiClothingList.this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
                    ArrayList<String> arrayList = new ArrayList<String>();
                    arrayList.add("cross");
                    arrayList.addAll((Collection)FilePersistence.getModelNames(GuiClothingList.this.ParentGui.Girl).get((Object)GirlBodySlot.CUSTOM_BONE));
                    GuiCustomizeGirl.ClothingOptions.add(GuiCustomizeGirl.getClothingData(GuiClothingList.this.ParentGui.Girl));
                }
                try {
                    if (!this.Selected) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GuiClothingList.Entry.rethrow(runtimeException);
                }
                try {
                    try {
                        if (i63 <= (i65 += 40) || i63 >= i65 + 20) break block10;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                    GuiClothingList.this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
                    GuiCustomizeGirl.ClothingOptions.remove(GuiCustomizeGirl.ClothingOptions.size() - 1);
                }
                catch (RuntimeException runtimeException) {
                    throw GuiClothingList.Entry.rethrow(runtimeException);
                }
            }
        }


      void a(int i66, int i67) {
            block9: {
                block8: {
                    try {
                        try {
                            if (i66 <= 40 || i66 >= 60) break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GuiClothingList.Entry.rethrow(runtimeException);
                        }
                        GuiClothingList.this.ParentGui.a(this.BodySlot, false, i67);
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (i66 <= 60 || i66 >= 80) break block9;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GuiClothingList.Entry.rethrow(runtimeException);
                    }
                    GuiClothingList.this.ParentGui.a(this.BodySlot, true, i67);
                }
                catch (RuntimeException runtimeException) {
                    throw GuiClothingList.Entry.rethrow(runtimeException);
                }
            }
        }

      void c(int i68, int i69) {
         try {
            if (!GuiClothingList.this.ParentGui.Girl.h(i69)) {
               this.a(i68, i69);
            }
         } catch (RuntimeException error12) {
            throw rethrow(error12);
         }
      }

      public void a(int i70, int i71, int i72, int i73) {
         try {
            if (i72 != 0) {
               return;
            }
         } catch (RuntimeException error13) {
            throw rethrow(error13);
         }

         try {
            if (i71 < 5) {
               return;
            }
         } catch (RuntimeException error14) {
            throw rethrow(error14);
         }

         try {
            if (i71 > 25) {
               return;
            }
         } catch (RuntimeException error15) {
            throw rethrow(error15);
         }

         try {
            if (this.WidthTemplate) {
               this.b(i70, i71);
               return;
            }
         } catch (RuntimeException error16) {
            throw rethrow(error16);
         }

         try {
            if (this.BodySlot == GirlBodySlot.GIRL_SPECIFIC) {
               this.c(i70, i73);
               return;
            }
         } catch (RuntimeException error17) {
            throw rethrow(error17);
         }

         this.a(i70, i73);
      }

      public void updatePosition(int i74, int i75, int i76, float f4) {
      }

      public boolean mousePressed(int i77, int i78, int i79, int i80, int i81, int i82) {
         return false;
      }

      public void mouseReleased(int i83, int i84, int i85, int i86, int i87, int i88) {
      }

      private static RuntimeException rethrow(RuntimeException error18) {
         return error18;
      }
   }
}
