package com.trolmastercard.sexmod;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

public abstract class GirlEffectEntity extends GirlEntity {
   public static final DataParameter<String> TribeColorKey = EntityDataManager.createKey(GirlEffectEntity.class, DataSerializers.STRING).getSerializer().createKey(119);
   public static final DataParameter<BlockPos> K = EntityDataManager.createKey(GirlEffectEntity.class, DataSerializers.BLOCK_POS).getSerializer().createKey(120);
   public static final DataParameter<String> M = EntityDataManager.createKey(GirlEffectEntity.class, DataSerializers.STRING).getSerializer().createKey(121);
   String P = null;
   String O = null;
   BlockPos L = null;

   protected GirlEffectEntity(World world) {
      super(world);
   }

   @Override

   protected void entityInit() {
        block4: {
            try {
                try {
                    super.entityInit();
                    if (!this.world.isRemote || !(this.world instanceof PreviewWorld)) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlEffectEntity.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GirlEffectEntity.rethrow(runtimeException);
            }
        }
        this.DataManager.register(M, this.a(new StringBuilder()));
    }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.c();
   }


   void c() {
        BlockPos blockPos;
        String string;
        String string2;
        block13: {
            try {
                if (!this.world.isRemote) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlEffectEntity.rethrow(runtimeException);
            }
            string2 = (String)this.DataManager.get(TribeColorKey);
            string = (String)this.DataManager.get(M);
            blockPos = (BlockPos)this.DataManager.get(K);
            try {
                if (this.P == null) {
                    this.P = string2;
                    this.O = string;
                    this.L = blockPos;
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlEffectEntity.rethrow(runtimeException);
            }
            try {
                block12: {
                    try {
                        try {
                            if (!this.O.equals(string) || !this.P.equals(string2)) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlEffectEntity.rethrow(runtimeException);
                        }
                        if (this.L.equals((Object)blockPos)) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlEffectEntity.rethrow(runtimeException);
                    }
                }
                this.a();
            }
            catch (RuntimeException runtimeException) {
                throw GirlEffectEntity.rethrow(runtimeException);
            }
        }
        this.P = string2;
        this.O = string;
        this.L = blockPos;
    }

   protected abstract void void_a();

   protected abstract String a(StringBuilder sb);

   public static void appendZeroPaddedNumber(StringBuilder sb, int i) {
      try {
         if (i < 10) {
            sb.append(0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      sb.append(i);
      sb.append("-");
   }

   public static void appendRandomNumber(StringBuilder sb, int i) {
      int i2 = ModConstants.Random.nextInt(i + 1);

      try {
         if (i2 < 10) {
            sb.append(0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      sb.append(i2);
      sb.append("-");
   }

   public static void appendRandomGauss(StringBuilder sb) {
      double d = ModConstants.Random.nextDouble();
      double d2 = Math.pow(Math.E, -Math.pow(-2.5 + 5.0 * d, 2.0));
      String string = String.format("%.2f", d2);
      String[] stringArray = string.split("\\.");
      if (stringArray.length < 2) {
         stringArray = string.split(",");
      }

      string = stringArray[1];
      sb.append(string).append("-");
   }

   public static void appendRandomBelow(StringBuilder sb, int i) {
      int i2 = ModConstants.Random.nextInt(i);

      try {
         if (i2 < 10) {
            sb.append(0);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      sb.append(i2);
      sb.append("-");
   }

   public static String[] getAttributeStrings(GirlEntity girl) {
      return ((String)girl.getDataManager().get(M)).split("-");
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
