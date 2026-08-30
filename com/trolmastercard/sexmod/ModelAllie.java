package com.trolmastercard.sexmod;

import net.minecraft.util.ResourceLocation;

public class ModelAllie extends GirlGeoModel<GirlEntity> {
   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{
         new ResourceLocation("sexmod", "geo/allie/allie.geo.json"),
         new ResourceLocation("sexmod", "geo/allie/armored.geo.json"),
         new ResourceLocation("sexmod", "geo/allie/allie.geo.json")
      };
   }

   @Override
   public ResourceLocation getTextureLocation(GirlEntity girl) {
      try {
         if (girl.world instanceof PreviewWorld) {
            return this.TextureLayers[0];
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if ((Integer)girl.getDataManager().get(GirlEntity.OutfitIndexKey) > this.TextureLayers.length) {
            System.out.println("Girl doesn't have an outfit Nr." + girl.getDataManager().get(GirlEntity.OutfitIndexKey) + " so im just making her nude lol");
            return this.TextureLayers[0];
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (girl instanceof AlliePlayer) {
            return this.TextureLayers[girl.getDataManager().get(GirlEntity.OutfitIndexKey)];
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if ((Integer)girl.getDataManager().get(GirlEntity.OutfitIndexKey) == 1) {
            return this.TextureLayers[2];
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      return this.TextureLayers[0];
   }

   @Override
   public ResourceLocation getSkinLocation() {
      return new ResourceLocation("sexmod", "textures/entity/allie/allie.png");
   }

   @Override
   public ResourceLocation getAnimationFileLocation(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/allie/allie.animation.json");
   }

   @Override
   public String[] getHelmetBones() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getChestArmorBones() {
      return new String[]{"armorShoulderR", "armorShoulderL", "armorChest", "armorBoobs"};
   }

   @Override
   public String[] getFleshTorsoBones() {
      return new String[]{"boobsFlesh", "clothes", "clothesR", "clothesL"};
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
