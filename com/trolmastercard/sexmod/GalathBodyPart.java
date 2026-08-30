package com.trolmastercard.sexmod;

import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.world.World;

public class GalathBodyPart extends MultiPartEntityPart {
   public boolean Collidable = false;

   public GalathBodyPart(World world) {
      super(null, "", 0.0F, 0.0F);
   }

   public GalathBodyPart(IEntityMultiPart iEntityMultiPart, String string, float f, float f2) {
      super(iEntityMultiPart, string, f, f2);
   }

   public boolean canBeCollidedWith() {
      return this.Collidable;
   }
}
