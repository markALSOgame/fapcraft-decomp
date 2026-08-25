package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.UUID;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;

public class PacketSendBlocks implements IMessage {
   boolean Loaded = false;
   HashSet<BlockPos> Blocks = new HashSet<>();
   boolean Remove;

   public PacketSendBlocks() {
   }

   public PacketSendBlocks(HashSet<BlockPos> set, boolean flag) {
      this.Blocks = set;
      this.Remove = flag;
   }

   public PacketSendBlocks(BlockPos pos, boolean flag) {
      this.Blocks.add(pos);
      this.Remove = flag;
   }

   public void fromBytes(ByteBuf buf) {
      this.Remove = buf.readBoolean();
      int i = buf.readInt();
      int i2 = 0;

      try {
         while (i2 < i) {
            this.Blocks.add(new BlockPos(buf.readInt(), buf.readInt(), buf.readInt()));
            i2++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeBoolean(this.Remove);
      buf.writeInt(this.Blocks.size());

      for (BlockPos pos : this.Blocks) {
         buf.writeInt(pos.getX());
         buf.writeInt(pos.getY());
         buf.writeInt(pos.getZ());
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketSendBlocks, IMessage> {

      public IMessage onMessage(PacketSendBlocks packet, MessageContext ctx) {
            block7: {
                block9: {
                    block8: {
                        try {
                            if (!packet.Loaded) {
                                System.out.println("received an invalid Message @SendBlocks :(");
                                return null;
                            }
                        }
                        catch (RuntimeException runtimeException) {
                            throw PacketSendBlocks.Handler.rethrow(runtimeException);
                        }
                        try {
                            try {
                                if (!ctx.side.isClient()) break block7;
                                if (!packet.Remove) break block8;
                            }
                            catch (RuntimeException runtimeException) {
                                throw PacketSendBlocks.Handler.rethrow(runtimeException);
                            }
                            GuiMark.addMarks(packet.Blocks);
                            break block9;
                        }
                        catch (RuntimeException runtimeException) {
                            throw PacketSendBlocks.Handler.rethrow(runtimeException);
                        }
                    }
                    GuiMark.removeMarks(packet.Blocks);
                }
                return null;
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                UUID uUID = ctx.getServerHandler().player.getPersistentID();
                UUID uUID2 = GirlHomeBuilder.findTribeUuid(uUID);
                try {
                    if (uUID2 == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSendBlocks.Handler.rethrow(runtimeException);
                }
                try {
                    if (packet.Blocks.size() != 1) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketSendBlocks.Handler.rethrow(runtimeException);
                }
                World world = ctx.getServerHandler().player.world;
                for (BlockPos blockPos : packet.Blocks) {
                    Object object;
                    BlockPos blockPos2;
                    block37: {
                        block38: {
                            IBlockState iBlockState;
                            block35: {
                                block36: {
                                    block34: {
                                        block33: {
                                            iBlockState = world.getBlockState(blockPos);
                                            blockPos2 = null;
                                            if (iBlockState.getBlock() instanceof BlockBed) {
                                                blockPos2 = BedLogic.getBedPosForState(blockPos, iBlockState);
                                            }
                                            if (iBlockState.getBlock() instanceof BlockChest) {
                                                block32: {
                                                    block31: {
                                                        block30: {
                                                            object = ((BlockChest)iBlockState.getBlock()).chestType;
                                                            try {
                                                                if (!(world.getBlockState(blockPos.north()).getBlock() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.getBlockState((BlockPos)blockPos.north()).getBlock()).chestType)) break block30;
                                                            }
                                                            catch (RuntimeException runtimeException) {
                                                                throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                                            }
                                                            blockPos2 = blockPos.north();
                                                        }
                                                        try {
                                                            if (!(world.getBlockState(blockPos.east()).getBlock() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.getBlockState((BlockPos)blockPos.east()).getBlock()).chestType)) break block31;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                                        }
                                                        blockPos2 = blockPos.east();
                                                    }
                                                    try {
                                                        if (!(world.getBlockState(blockPos.south()).getBlock() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.getBlockState((BlockPos)blockPos.south()).getBlock()).chestType)) break block32;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                                    }
                                                    blockPos2 = blockPos.south();
                                                }
                                                try {
                                                    if (!(world.getBlockState(blockPos.west()).getBlock() instanceof BlockChest) || !object.equals((Object)((BlockChest)world.getBlockState((BlockPos)blockPos.west()).getBlock()).chestType)) break block33;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                                }
                                                blockPos2 = blockPos.west();
                                            }
                                        }
                                        try {
                                            try {
                                                if (blockPos2 != null || !(iBlockState.getBlock() instanceof BlockBed)) break block34;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                        }
                                    }
                                    try {
                                        try {
                                            if (!packet.Remove) break block35;
                                            if (!(iBlockState.getBlock() instanceof BlockBed)) break block36;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                        }
                                        GirlHomeBuilder.addSpawnPosition(uUID2, blockPos);
                                        GirlHomeBuilder.addSpawnPosition(uUID2, blockPos2);
                                        break block37;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw PacketSendBlocks.Handler.rethrow(runtimeException);
                                    }
                                }
                                GirlHomeBuilder.addNearbyPosition(uUID2, blockPos);
                                GirlHomeBuilder.addNearbyPosition(uUID2, blockPos2);
                                break block37;
                            }
                            try {
                                if (!(iBlockState.getBlock() instanceof BlockBed)) break block38;
                                GirlHomeBuilder.removeSpawnPosition(uUID2, blockPos);
                                GirlHomeBuilder.removeSpawnPosition(uUID2, blockPos2);
                                break block37;
                            }
                            catch (RuntimeException runtimeException) {
                                throw PacketSendBlocks.Handler.rethrow(runtimeException);
                            }
                        }
                        GirlHomeBuilder.removeNearbyPosition(uUID2, blockPos);
                        GirlHomeBuilder.removeNearbyPosition(uUID2, blockPos2);
                    }
                    object = new HashSet();
                    try {
                        ((HashSet)object).add(blockPos);
                        if (blockPos2 != null) {
                            ((HashSet)object).add(blockPos2);
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketSendBlocks.Handler.rethrow(runtimeException);
                    }
                    NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks((HashSet<BlockPos>)object, packet.Remove), ctx.getServerHandler().player);
                }
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
