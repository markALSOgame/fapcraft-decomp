package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class InventoryGirlEntity extends GirlEntity {
   public int S = 1;
   public int P;
   public int O = 0;
   public int K;
   public Vec3d V = Vec3d.ZERO;
   public boolean IsDowned;
   public ItemStackHandler Inventory = new ItemStackHandler(7);
   public static final DataParameter<ItemStack> L = EntityDataManager.createKey(InventoryGirlEntity.class, DataSerializers.ITEM_STACK)
      .getSerializer()
      .createKey(117);
   public static final DataParameter<ItemStack> R = EntityDataManager.createKey(InventoryGirlEntity.class, DataSerializers.ITEM_STACK)
      .getSerializer()
      .createKey(116);
   public static final DataParameter<ItemStack> HelmetKey = EntityDataManager.createKey(InventoryGirlEntity.class, DataSerializers.ITEM_STACK)
      .getSerializer()
      .createKey(115);
   public static final DataParameter<ItemStack> ChestKey = EntityDataManager.createKey(InventoryGirlEntity.class, DataSerializers.ITEM_STACK)
      .getSerializer()
      .createKey(114);
   public static final DataParameter<ItemStack> PantsKey = EntityDataManager.createKey(InventoryGirlEntity.class, DataSerializers.ITEM_STACK)
      .getSerializer()
      .createKey(113);
   public static final DataParameter<ItemStack> BootsKey = EntityDataManager.createKey(InventoryGirlEntity.class, DataSerializers.ITEM_STACK)
      .getSerializer()
      .createKey(112);
   public static final DataParameter<Integer> ModeKey = EntityDataManager.createKey(InventoryGirlEntity.class, DataSerializers.VARINT).getSerializer().createKey(111);

   protected InventoryGirlEntity(World world) {
      super(world);
      if (this.Inventory.getStackInSlot(0) == ItemStack.EMPTY) {
         this.Inventory.setStackInSlot(0, new ItemStack(Items.IRON_SWORD));
      }

      try {
         if (this.Inventory.getStackInSlot(1) == ItemStack.EMPTY) {
            this.Inventory.setStackInSlot(1, new ItemStack(Items.BOW));
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.DataManager.register(ModeKey, 0);
      this.DataManager.register(L, ItemStack.EMPTY);
      this.DataManager.register(R, ItemStack.EMPTY);
      this.DataManager.register(HelmetKey, ItemStack.EMPTY);
      this.DataManager.register(ChestKey, ItemStack.EMPTY);
      this.DataManager.register(PantsKey, ItemStack.EMPTY);
      this.DataManager.register(BootsKey, ItemStack.EMPTY);
   }

   @Override
   protected void initEntityAI() {
      super.initEntityAI();
      this.tasks.addTask(1, new GirlCombatAi(this));
   }

   public void c() {
   }

   @Override

   public void updateAITasks() {
        block14: {
            block12: {
                int i;
                block13: {
                    try {
                        try {
                            try {
                                super.updateAITasks();
                                if (this.ticksExisted % 80 != 0 || this.getHealth() == this.getMaxHealth()) break block12;
                            }
                            catch (RuntimeException runtimeException) {
                                throw InventoryGirlEntity.rethrow(runtimeException);
                            }
                            if (this.J()) break block13;
                        }
                        catch (RuntimeException runtimeException) {
                            throw InventoryGirlEntity.rethrow(runtimeException);
                        }
                        this.heal(1.0f);
                        break block12;
                    }
                    catch (RuntimeException runtimeException) {
                        throw InventoryGirlEntity.rethrow(runtimeException);
                    }
                }
                List list = this.world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(new BlockPos(this.posX - 7.0, this.posY - 1.0, this.posZ - 7.0), new BlockPos(this.posX + 7.0, this.posY + 1.0, this.posZ + 7.0)));
                try {
                    i = list.isEmpty() ? 4 : 1;
                }
                catch (RuntimeException runtimeException) {
                    throw InventoryGirlEntity.rethrow(runtimeException);
                }
                int i2 = i;
                this.heal(i2);
                ((WorldServer)this.world).spawnParticle(EnumParticleTypes.HEART, false, this.posX, this.posY + 1.0 + ModConstants.Random.nextDouble(), this.posZ, i2, 1.0, 1.0, 1.0, ModConstants.Random.nextGaussian(), new int[0]);
            }
            try {
                try {
                    if (!this.IsDowned || this.J()) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw InventoryGirlEntity.rethrow(runtimeException);
                }
                this.IsDowned = false;
            }
            catch (RuntimeException runtimeException) {
                throw InventoryGirlEntity.rethrow(runtimeException);
            }
        }
        this.DataManager.set(HAND_STATES, (Object)Byte.valueOf("1"));
        this.DataManager.set(L, (Object)this.Inventory.getStackInSlot(0));
        this.DataManager.set(R, (Object)this.Inventory.getStackInSlot(1));
        this.DataManager.set(HelmetKey, (Object)this.Inventory.getStackInSlot(2));
        this.DataManager.set(ChestKey, (Object)this.Inventory.getStackInSlot(3));
        this.DataManager.set(PantsKey, (Object)this.Inventory.getStackInSlot(4));
        this.DataManager.set(BootsKey, (Object)this.Inventory.getStackInSlot(5));
    }

   @SideOnly(Side.CLIENT)
   @Override
   public void a(String string, UUID uuid) {
      try {
         if ("action.names.followme".equals(string)) {
            this.a("master", uuid.toString());
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if ("action.names.stopfollowme".equals(string)) {
            this.resetMasterAndWalkSpeed();
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      if ("action.names.equipment".equals(string)) {
         EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
         NetworkHandler.channel.sendToServer(new PacketOpenEquipment(this.getGirlUuid(), mcPlayer.getPersistentID()));
      } else {
         try {
            if ("action.names.gohome".equals(string)) {
               this.resetMasterAndWalkSpeed();
               NetworkHandler.channel.sendToServer(new PacketSendCompanionHome(this.getGirlUuid()));
               return;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         try {
            if ("action.names.setnewhome".equals(string)) {
               this.getDisplayName();
               NetworkHandler.channel.sendToServer(new PacketSetNewHome(this.getGirlUuid(), new Vec3d(this.getPosition())));
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      tagCompound.setTag("inventory", this.Inventory.serializeNBT());
      super.writeEntityToNBT(tagCompound);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      this.Inventory.deserializeNBT(tagCompound.getCompoundTag("inventory"));
   }


   public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        boolean flag;
        block5: {
            block4: {
                try {
                    try {
                        if (capability != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && !super.hasCapability(capability, facing)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw InventoryGirlEntity.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw InventoryGirlEntity.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

   public <ChestKey> ChestKey getCapability(Capability<ChestKey> capability, EnumFacing facing) {
      try {
         if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (ChestKey)this.Inventory;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return (ChestKey)super.getCapability(capability, facing);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
