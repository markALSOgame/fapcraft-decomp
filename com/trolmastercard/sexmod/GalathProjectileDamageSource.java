package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class GalathProjectileDamageSource extends DamageSource {
   GalathNpc Galath;
   Vec3d DamagePosition;

   public GalathProjectileDamageSource(GalathNpc galath) {
      super("galath");
      this.Galath = galath;
      this.DamagePosition = galath.getPositionVector();
   }

   public ITextComponent getDeathMessage(EntityLivingBase livingBase) {
      return new TextComponentString(livingBase.getName() + " was slain by Galath");
   }

   @Nullable
   public Entity getImmediateSource() {
      return this.Galath;
   }

   @Nullable
   public Entity getTrueSource() {
      return this.Galath;
   }

   @Nullable
   public Vec3d getDamageLocation() {
      return this.DamagePosition;
   }
}
