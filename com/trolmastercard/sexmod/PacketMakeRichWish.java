package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketMakeRichWish implements IMessage {
   boolean Loaded;
   Vec3d Pos;

   public PacketMakeRichWish() {
   }

   public PacketMakeRichWish(Vec3d vec3d) {
      this.Pos = vec3d;
   }

   public void fromBytes(ByteBuf buf) {
      this.Pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeDouble(this.Pos.x);
      buf.writeDouble(this.Pos.y);
      buf.writeDouble(this.Pos.z);
   }

   public static class Handler implements IMessageHandler<PacketMakeRichWish, IMessage> {

      public IMessage onMessage(PacketMakeRichWish packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketMakeRichWish.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @MakeRichWish :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketMakeRichWish.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                World world = ctx.getServerHandler().player.world;
                EntityItem entityItem = new EntityItem(world, packet.Pos.x, packet.Pos.y, packet.Pos.z, new ItemStack(Items.DIAMOND, ModConstants.Random.nextInt(2) + 1));
                EntityItem entityItem2 = new EntityItem(world, packet.Pos.x, packet.Pos.y, packet.Pos.z, new ItemStack(Items.EMERALD, ModConstants.Random.nextInt(2) + 1));
                EntityItem entityItem3 = new EntityItem(world, packet.Pos.x, packet.Pos.y, packet.Pos.z, new ItemStack(Items.GOLD_INGOT, ModConstants.Random.nextInt(2) + 1));
                world.spawnEntity((Entity)entityItem);
                world.spawnEntity((Entity)entityItem2);
                world.spawnEntity((Entity)entityItem3);
            });
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
