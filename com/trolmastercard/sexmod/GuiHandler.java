package com.trolmastercard.sexmod;

import java.io.File;
import java.util.ConcurrentModificationException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {
   File b;
   File c;
   boolean a = false;

   public GuiHandler() {
   }

   public GuiHandler(boolean flag) {
      this.preloadContainers();
   }

   @SideOnly(Side.CLIENT)

   void preloadContainers() {
        block30: {
            int i;
            block25: {
                i = 2;
                if (i != 0) break block25;
                try {
                    for (GirlEntity girl : GirlEntity.getAllGirls()) {
                        block24: {
                            block28: {
                                block27: {
                                    block26: {
                                        if (girl.world.isRemote) continue;
                                        if (girl.getPosition().getX() != 5) continue;
                                        break block26;
                                        catch (ConcurrentModificationException concurrentModificationException) {
                                            throw GuiHandler.rethrow(concurrentModificationException);
                                        }
                                    }
                                    if (girl.getPosition().getY() != 7) continue;
                                    break block27;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GuiHandler.rethrow(concurrentModificationException);
                                    }
                                }
                                if (girl.getPosition().getZ() != 5) continue;
                                break block28;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GuiHandler.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                block29: {
                                    if (!(girl instanceof LunaNpc)) break block24;
                                    break block29;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GuiHandler.rethrow(concurrentModificationException);
                                    }
                                }
                                new ContainerLunaEquipment((LunaNpc)girl, Minecraft.getMinecraft().player.inventory, UUID.randomUUID());
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                        }
                        new ContainerGirlEquipment(girl, Minecraft.getMinecraft().player.inventory, UUID.randomUUID());
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    // empty catch block
                }
            }
            if (i != 1) break block30;
            try {
                for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
                    block32: {
                        block31: {
                            if (girl2.world.isRemote) continue;
                            if (!(girl2 instanceof IInventory)) continue;
                            break block31;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                        }
                        if (girl2.getPosition().getX() != 3) continue;
                        break block32;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GuiHandler.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        block33: {
                            if (girl2.getPosition().getY() != 1) continue;
                            break block33;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                        }
                        if (girl2.getPosition().getZ() != 7) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GuiHandler.rethrow(concurrentModificationException);
                    }
                    IInventory iInventory = (IInventory)girl2;
                    new ContainerChest((IInventory)Minecraft.getMinecraft().player.inventory, iInventory, (EntityPlayer)Minecraft.getMinecraft().player, UUID.randomUUID());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }
    }


   public Object getServerGuiElement(int i, EntityPlayer player, World world2, int i2, int i3, int i4) {
        block27: {
            block23: {
                if (i != 0) break block23;
                try {
                    for (GirlEntity girl : GirlEntity.getAllGirls()) {
                        block22: {
                            block25: {
                                block24: {
                                    if (girl.world.isRemote) continue;
                                    if (girl.getPosition().getX() != i2) continue;
                                    break block24;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GuiHandler.rethrow(concurrentModificationException);
                                    }
                                }
                                if (girl.getPosition().getY() != i3) continue;
                                break block25;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GuiHandler.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                block26: {
                                    if (girl.getPosition().getZ() != i4) continue;
                                    break block26;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GuiHandler.rethrow(concurrentModificationException);
                                    }
                                }
                                if (!(girl instanceof LunaNpc)) break block22;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                            return new ContainerLunaEquipment((LunaNpc)girl, player.inventory, UUID.randomUUID());
                        }
                        return new ContainerGirlEquipment(girl, player.inventory, UUID.randomUUID());
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    // empty catch block
                }
            }
            if (i != 1) break block27;
            try {
                for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
                    block29: {
                        block28: {
                            if (girl2.world.isRemote) continue;
                            if (!(girl2 instanceof IInventory)) continue;
                            break block28;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                        }
                        if (girl2.getPosition().getX() != i2) continue;
                        break block29;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GuiHandler.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        block30: {
                            if (girl2.getPosition().getY() != i3) continue;
                            break block30;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                        }
                        if (girl2.getPosition().getZ() != i4) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GuiHandler.rethrow(concurrentModificationException);
                    }
                    IInventory iInventory = (IInventory)girl2;
                    return new ContainerChest((IInventory)player.inventory, iInventory, player, UUID.randomUUID());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }
        return null;
    }


   public Object getClientGuiElement(int i, EntityPlayer player, World world2, int i2, int i3, int i4) {
        block27: {
            block23: {
                if (i != 0) break block23;
                try {
                    for (GirlEntity girl : GirlEntity.getAllGirls()) {
                        block22: {
                            block25: {
                                block24: {
                                    if (!girl.world.isRemote) continue;
                                    if (girl.getPosition().getX() != i2) continue;
                                    break block24;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GuiHandler.rethrow(concurrentModificationException);
                                    }
                                }
                                if (girl.getPosition().getY() != i3) continue;
                                break block25;
                                catch (ConcurrentModificationException concurrentModificationException) {
                                    throw GuiHandler.rethrow(concurrentModificationException);
                                }
                            }
                            try {
                                block26: {
                                    if (girl.getPosition().getZ() != i4) continue;
                                    break block26;
                                    catch (ConcurrentModificationException concurrentModificationException) {
                                        throw GuiHandler.rethrow(concurrentModificationException);
                                    }
                                }
                                if (!(girl instanceof LunaNpc)) break block22;
                            }
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                            return new GuiLunaEquipment((LunaNpc)girl, player.inventory, UUID.randomUUID());
                        }
                        return new GuiGirlEquipment(girl, player.inventory, UUID.randomUUID());
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    // empty catch block
                }
            }
            if (i != 1) break block27;
            try {
                for (GirlEntity girl2 : GirlEntity.getAllGirls()) {
                    block29: {
                        block28: {
                            if (!girl2.world.isRemote) continue;
                            if (!(girl2 instanceof IInventory)) continue;
                            break block28;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                        }
                        if (girl2.getPosition().getX() != i2) continue;
                        break block29;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw GuiHandler.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        block30: {
                            if (girl2.getPosition().getY() != i3) continue;
                            break block30;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw GuiHandler.rethrow(concurrentModificationException);
                            }
                        }
                        if (girl2.getPosition().getZ() != i4) continue;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw GuiHandler.rethrow(concurrentModificationException);
                    }
                    return new GuiChest(player, girl2, UUID.randomUUID());
                }
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                // empty catch block
            }
        }
        return null;
    }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
