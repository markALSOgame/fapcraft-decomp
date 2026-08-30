package com.trolmastercard.sexmod;

import net.minecraft.entity.Entity;

public enum GirlRegistry {
   JENNY(JennyNpc.class, 177013, JennyPlayer.class, 12388645),
   ELLIE(EllieNpc.class, 228922, ElliePlayer.class, 46348348),
   BIA(BiaNpc.class, 230053, BiaPlayer.class, 65456415),
   SLIME(SlimeNpc.class, 168597, SlimePlayer.class, 54816432),
   BEE(BeeNpc.class, 4663354, BeePlayer.class, 48648638),
   ALLIE(AllieNpc.class, 5614613, AlliePlayer.class, 64867483),
   LUNA(LunaNpc.class, 6816463, LunaPlayer.class, 81234824),
   KOBOLD(KoboldNpc.class, 5648456, KoboldPlayer.class, 62484851, true),
   GOBLIN(GoblinNpc.class, 4567275, GoblinPlayer.class, 6584344, true),
   GALATH(GalathNpc.class, 314351, GalathPlayer.class, 652535516),
   MANGLELIE(ManglelieNpc.class, 618151);

   public final int npcID;
   public final int playerID;
   public final Class<? extends GirlEntity> npcClass;
   public final Class<? extends PlayerGirlEntity> playerClass;
   public final boolean isNpcOnly;
   public final int editorID;
   public final boolean hasSpecifics;

   GirlRegistry(Class<? extends GirlEntity> cls, int i, Class<? extends PlayerGirlEntity> cls2, int i2, boolean flag) {
      this.npcID = i;
      this.playerID = i2;
      this.npcClass = cls;
      this.playerClass = cls2;
      this.isNpcOnly = false;
      this.hasSpecifics = flag;
      this.editorID = ModConstants.IdCounter++;
   }

   GirlRegistry(Class<? extends GirlEntity> cls, int i, Class<? extends PlayerGirlEntity> cls2, int i2) {
      this.npcID = i;
      this.playerID = i2;
      this.npcClass = cls;
      this.playerClass = cls2;
      this.isNpcOnly = false;
      this.hasSpecifics = false;
      this.editorID = ModConstants.IdCounter++;
   }

   GirlRegistry(Class<? extends GirlEntity> cls, int i) {
      this.npcID = i;
      this.npcClass = cls;
      this.isNpcOnly = true;
      this.hasSpecifics = false;
      this.editorID = ModConstants.IdCounter++;
      this.playerClass = null;
      this.playerID = 0;
   }

   public static GirlRegistry getByName(String string) {
      for (GirlRegistry girlType : values()) {
         try {
            if (girlType.toString().equalsIgnoreCase(string)) {
               return girlType;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }
      }

      return JENNY;
   }

   public static GirlRegistry getByEntity(Entity entity) {
      try {
         if (!(entity instanceof GirlEntity)) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GirlEntity girl = (GirlEntity)entity;
      Class cls = girl.getClass();

      for (GirlRegistry girlType : values()) {
         try {
            if (cls.equals(girlType.npcClass)) {
               return girlType;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         try {
            if (cls.equals(girlType.playerClass)) {
               return girlType;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }
      }

      return null;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
