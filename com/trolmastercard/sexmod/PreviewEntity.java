package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.MatrixStack;

public class PreviewEntity extends EntityLivingBase implements IAnimatable {
   static final float e = 11000.0F;
   public static final DataParameter<String> ModelFolderKey = EntityDataManager.createKey(PreviewEntity.class, DataSerializers.STRING).getSerializer().createKey(101);
   public static final DataParameter<String> ModelNameKey = EntityDataManager.createKey(PreviewEntity.class, DataSerializers.STRING).getSerializer().createKey(102);
   AnimationFactory AnimFactory = new AnimationFactory(this);
   public boolean f = false;
   public MatrixStack RenderStack = new MatrixStack();
   GirlBodySlot SlotFilter = null;

   public PreviewEntity(World world) {
      super(world);
      this.width = 0.1F;
      this.height = 0.1F;
   }

   public PreviewEntity(World world, UUID uuid, String string) {
      this(world);
      this.dataManager.set(ModelFolderKey, uuid.toString());
      this.dataManager.set(ModelNameKey, string);
   }

   public static PreviewEntity a(World world, UUID uuid, GirlBodySlot girlBodySlot) {
      PreviewEntity previewEntity = new PreviewEntity(world);
      previewEntity.getDataManager().set(ModelFolderKey, uuid.toString());
      previewEntity.f = true;
      previewEntity.SlotFilter = girlBodySlot;
      return previewEntity;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(ModelFolderKey, "");
      this.dataManager.register(ModelNameKey, "");
   }

   public AxisAlignedBB getRenderBoundingBox() {
      BlockPos pos = this.getPosition();
      Vec3i vec3i = new Vec3i(0.5, 0.5, 0.5);
      return new AxisAlignedBB(pos.subtract(vec3i), pos.add(vec3i));
   }

   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRender3d(double d, double d2, double d3) {
      double d4 = this.posX - d;
      double d5 = this.posY - d2;
      double d6 = this.posZ - d3;
      double d7 = d4 * d4 + d5 * d5 + d6 * d6;
      return this.isInRangeToRenderDist(d7);
   }

   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRenderDist(double d) {
      try {
         if (d < 11000.0) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   @Nullable
   public UUID b() {
      String string = (String)this.dataManager.get(ModelFolderKey);

      try {
         if ("".equals(string)) {
            return null;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return UUID.fromString(string);
   }

   public boolean attackEntityFrom(DamageSource damage, float f) {
      try {
         if (damage != DamageSource.OUT_OF_WORLD) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return super.attackEntityFrom(damage, f);
   }

   @Nullable
   public String getModelName() {
      String string = (String)this.dataManager.get(ModelNameKey);

      try {
         return "".equals(string) ? null : string;
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   public boolean canBePushed() {
      return false;
   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public void onDeath(DamageSource damage) {
      super.onDeath(damage);
   }

   public AnimationFactory getFactory() {
      return this.AnimFactory;
   }

   public void registerControllers(AnimationData animationData) {
   }

   public Iterable<ItemStack> getArmorInventoryList() {
      return new ArrayList<>();
   }

   public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityEquipmentSlot) {
      return ItemStack.EMPTY;
   }

   public void setItemStackToSlot(EntityEquipmentSlot entityEquipmentSlot, ItemStack stack) {
   }

   public EnumHandSide getPrimaryHand() {
      return EnumHandSide.LEFT;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
