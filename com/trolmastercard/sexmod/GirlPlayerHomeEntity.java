package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class GirlPlayerHomeEntity extends PlayerGirlEntity {
   public static final DataParameter<String> TribeColorKey = EntityDataManager.createKey(GirlPlayerHomeEntity.class, DataSerializers.STRING).getSerializer().createKey(119);
   public static final DataParameter<BlockPos> HomePosKey = EntityDataManager.createKey(GirlPlayerHomeEntity.class, DataSerializers.BLOCK_POS)
      .getSerializer()
      .createKey(120);
   public static final DataParameter<String> AttributeStringKey = EntityDataManager.createKey(GirlPlayerHomeEntity.class, DataSerializers.STRING).getSerializer().createKey(121);
   boolean ar = true;
   String CachedTribeColor = null;
   String CachedAttributeString = null;
   BlockPos CachedHomePos = null;

   protected GirlPlayerHomeEntity(World world) {
      super(world);
   }

   protected GirlPlayerHomeEntity(World world, UUID uuid) {
      super(world, uuid);
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
                    throw GirlPlayerHomeEntity.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GirlPlayerHomeEntity.rethrow(runtimeException);
            }
        }
        this.DataManager.register(AttributeStringKey, (Object)this.generateAttributeString(new StringBuilder()));
    }

   protected abstract String generateAttributeString(StringBuilder sb);

   public static String[] getAttributeStrings(GirlEntity girl) {
      return ((String)girl.getDataManager().get(AttributeStringKey)).split("-");
   }

   @Override
   public void onUpdate() {
      try {
         super.onUpdate();
         this.checkHomeData();
         if (!this.ar) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (this.world.isRemote) {
            this.resetHomeData();
            this.ar = true;
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player = this.getBoundPlayer();

      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      String string = player.getEntityData().getString("sexmod:GirlSpecific" + GirlRegistry.getByEntity(this));

      try {
         this.ar = false;
         if (!"".equals(string)) {
            this.a(string);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }
   }


   void checkHomeData() {
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
                throw GirlPlayerHomeEntity.rethrow(runtimeException);
            }
            string2 = (String)this.DataManager.get(TribeColorKey);
            string = (String)this.DataManager.get(AttributeStringKey);
            blockPos = (BlockPos)this.DataManager.get(HomePosKey);
            try {
                if (this.CachedTribeColor == null) {
                    this.CachedTribeColor = string2;
                    this.CachedAttributeString = string;
                    this.CachedHomePos = blockPos;
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlPlayerHomeEntity.rethrow(runtimeException);
            }
            try {
                block12: {
                    try {
                        try {
                            if (!this.CachedAttributeString.equals(string) || !this.CachedTribeColor.equals(string2)) break block12;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlPlayerHomeEntity.rethrow(runtimeException);
                        }
                        if (this.CachedHomePos.equals((Object)blockPos)) break block13;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlPlayerHomeEntity.rethrow(runtimeException);
                    }
                }
                this.resetHomeData();
            }
            catch (RuntimeException runtimeException) {
                throw GirlPlayerHomeEntity.rethrow(runtimeException);
            }
        }
        this.CachedTribeColor = string2;
        this.CachedAttributeString = string;
        this.CachedHomePos = blockPos;
    }

   protected abstract void resetHomeData();

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
