package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockLog;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFallTree implements IMessage {
   Boolean Loaded = false;
   BlockPos Pos;

   public PacketFallTree() {
   }

   public PacketFallTree(BlockPos pos) {
      this.Pos = pos;
   }

   public void fromBytes(ByteBuf buf) {
      this.Pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.Pos.getX());
      buf.writeInt(this.Pos.getY());
      buf.writeInt(this.Pos.getZ());
   }

   public static class Handler implements IMessageHandler<PacketFallTree, IMessage> {

      public IMessage handle(PacketFallTree packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded.booleanValue() && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketFallTree.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid Message @FallTree :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketFallTree.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
                UUID uUID = GirlHomeBuilder.findTribeUuid(entityPlayerMP.getPersistentID());
                try {
                    if (uUID == null) {
                        System.out.println("not tribe for player");
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketFallTree.Handler.rethrow(runtimeException);
                }
                int i = GirlHomeBuilder.getKoboldCount(uUID);
                int i2 = (int)Math.floor((double)GirlHomeBuilder.getSpawnPositions(uUID).size() / 2.0);
                try {
                    if (i > i2) {
                        entityPlayerMP.sendMessage((ITextComponent)new TextComponentString(String.format("Ur Tribe will only work for you, if %severyone%s of them has a %sbed", TextFormatting.RED, TextFormatting.WHITE, TextFormatting.RED)));
                        entityPlayerMP.sendMessage((ITextComponent)new TextComponentString(String.format("%s%d/%d Beds", TextFormatting.YELLOW, i2, i)));
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketFallTree.Handler.rethrow(runtimeException);
                }
                World world = entityPlayerMP.world;
                BlockPos blockPos = this.handle(world, packet.Pos);
                HashSet<BlockPos> hashSet = TreeCluster.createFallTask(world, blockPos, uUID);
                NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet, true), ctx.getServerHandler().player);
            });
            return null;
        }

      BlockPos handle(World world, BlockPos pos) {
         try {
            if (world.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(0, -1, 0));
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (world.getBlockState(pos.add(1, -1, 0)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(1, -1, 0));
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         try {
            if (world.getBlockState(pos.add(-1, -1, 0)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(-1, -1, 0));
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         try {
            if (world.getBlockState(pos.add(0, -1, 1)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(0, -1, 1));
            }
         } catch (RuntimeException error4) {
            throw rethrow(error4);
         }

         try {
            if (world.getBlockState(pos.add(0, -1, -1)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(0, -1, -1));
            }
         } catch (RuntimeException error5) {
            throw rethrow(error5);
         }

         try {
            if (world.getBlockState(pos.add(-1, -1, -1)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(-1, -1, -1));
            }
         } catch (RuntimeException error6) {
            throw rethrow(error6);
         }

         try {
            if (world.getBlockState(pos.add(1, -1, 1)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(1, -1, 1));
            }
         } catch (RuntimeException error7) {
            throw rethrow(error7);
         }

         try {
            if (world.getBlockState(pos.add(-1, -1, 1)).getBlock() instanceof BlockLog) {
               return this.handle(world, pos.add(-1, -1, 1));
            }
         } catch (RuntimeException error8) {
            throw rethrow(error8);
         }

         try {
            return world.getBlockState(pos.add(1, -1, -1)).getBlock() instanceof BlockLog ? this.handle(world, pos.add(1, -1, -1)) : pos;
         } catch (RuntimeException error9) {
            throw rethrow(error9);
         }
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
