package com.trolmastercard.sexmod;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class EquipmentSlot extends SlotItemHandler {
   EquipmentSlot.SlotType Kind;

   public EquipmentSlot(EquipmentSlot.SlotType slotType, IItemHandler handler, int i, int i2, int i3) {
      super(handler, i, i2, i3);
      this.Kind = slotType;
   }

   public static boolean a(ItemStack stack, int i) {
      return a(stack, EquipmentSlot.SlotType.getBySlotIndex(i));
   }

   public boolean isItemValid(ItemStack stack) {
      return a(stack, this.Kind);
   }


   static boolean a(ItemStack stack, EquipmentSlot.SlotType slotType) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 11[SWITCH]
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

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public enum SlotType {
      WEAPON(0),
      BOW(1),
      HELMET(2),
      CHEST_PLATE(3),
      PANTS(4),
      SHOES(5),
      ROD(6);

      public int SlotIndex;

      public static EquipmentSlot.SlotType getBySlotIndex(int i) {
         try {
            switch (i) {
               case 0:
                  return WEAPON;
               case 1:
                  return BOW;
               case 2:
                  return HELMET;
               case 3:
                  return CHEST_PLATE;
               case 4:
                  return PANTS;
               case 5:
                  return SHOES;
               case 6:
                  return ROD;
            }
         } catch (NullPointerException error) {
            throw rethrow(error);
         }

         throw new NullPointerException("Girls don't have a slot nr. " + i);
      }

      SlotType(int i) {
         this.SlotIndex = i;
      }

      private static NullPointerException rethrow(NullPointerException error) {
         return error;
      }
   }
}
