package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class GalathMeleeDamageSource extends DamageSource {
   GalathNpc Galath;
   Vec3d DamagePos;

   public GalathMeleeDamageSource(GalathNpc galath) {
      super("galath");
      this.Galath = galath;
      this.DamagePos = galath.getPositionVector();
   }

   public ITextComponent getDeathMessage(EntityLivingBase livingBase) {
      return new TextComponentString(livingBase.getName() + " got his cum drained by a Succubus");
   }

   public boolean isUnblockable() {
      return true;
   }

   public boolean canHarmInCreative() {
      return true;
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
      return this.DamagePos;
   }
}
