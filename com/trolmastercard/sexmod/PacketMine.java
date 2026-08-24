package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketMine implements IMessage {
   boolean Loaded = false;
   BlockPos Pos;
   EnumFacing Facing;

   public PacketMine() {
   }

   public PacketMine(BlockPos pos, EnumFacing facing) {
      this.Pos = pos;
      this.Facing = facing;
   }

   public void fromBytes(ByteBuf buf) {
      this.Pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
      this.Facing = EnumFacing.byName(ByteBufUtils.readUTF8String(buf));
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.Pos.getX());
      buf.writeInt(this.Pos.getY());
      buf.writeInt(this.Pos.getZ());
      ByteBufUtils.writeUTF8String(buf, this.Facing.getName());
   }

   public static class Handler implements IMessageHandler<PacketMine, IMessage> {

      public IMessage handle(PacketMine packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side.equals((Object)Side.SERVER)) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketMine.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid Message @Mine :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketMine.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
                UUID uUID = GirlHomeBuilder.findTribeUuid(entityPlayerMP.getPersistentID());
                try {
                    if (uUID == null) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketMine.Handler.rethrow(runtimeException);
                }
                int i = GirlHomeBuilder.getKoboldCount(uUID);
                int i2 = (int)Math.floor((double)GirlHomeBuilder.getSpawnPositions(uUID).size() / 2.0);
                try {
                    if (i > i2) {
                        entityPlayerMP.sendMessage((ITextComponent)new TextComponentString(String.format("sUr Tribe will only work for you, if %severyone%s of them has a %sbed", TextFormatting.RED, TextFormatting.WHITE, TextFormatting.RED)));
                        entityPlayerMP.sendMessage((ITextComponent)new TextComponentString(String.format("%s%d/%d Beds", TextFormatting.YELLOW, i2, i)));
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw PacketMine.Handler.rethrow(runtimeException);
                }
                HashSet<BlockPos> hashSet = this.handle(packet.Pos, packet.Facing);
                World world = ctx.getServerHandler().player.world;
                for (BlockPos blockPos : hashSet) {
                    IBlockState iBlockState = world.getBlockState(blockPos);
                    try {
                        if (!(iBlockState.getBlock().getBlockHardness(iBlockState, world, blockPos) < 0.0f)) continue;
                        entityPlayerMP.sendStatusMessage((ITextComponent)new TextComponentString("This area contains Bedrock and cannot be mined"), true);
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketMine.Handler.rethrow(runtimeException);
                    }
                }
                TreeCluster treeCluster = new TreeCluster(packet.Pos, TreeCluster.TaskType.MINE, hashSet, packet.Facing);
                GirlHomeBuilder.addAnchor(uUID, treeCluster);
                NetworkHandler.channel.sendTo((IMessage)new PacketSendBlocks(hashSet, true), ctx.getServerHandler().player);
            });
            return null;
        }

      HashSet<BlockPos> handle(BlockPos pos, EnumFacing facing) {
         HashSet set = new HashSet();
         BlockPos pos2 = pos;

         for (int i = 0; i < 30; i++) {
            set.add(pos2.subtract(this.handle(facing)));
            set.add(pos2.subtract(this.handle(facing)).up());
            set.add(pos2.subtract(this.handle(facing)).up().up());
            set.add(pos2);
            set.add(pos2.up());
            set.add(pos2.up().up());
            set.add(pos2.add(this.handle(facing)));
            set.add(pos2.add(this.handle(facing)).up());
            set.add(pos2.add(this.handle(facing)).up().up());
            pos2 = pos2.add(facing.getDirectionVec());
         }

         return set;
      }

      BlockPos handle(EnumFacing facing) {
         Vec3i vec3i = facing.getDirectionVec();
         return new BlockPos(vec3i.getZ(), vec3i.getY(), -vec3i.getX());
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
