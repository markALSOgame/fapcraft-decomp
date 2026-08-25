package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import javax.vecmath.Vector4d;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.Map;

public class PacketGetTribeUIValues implements IMessage {
   boolean Loaded = false;
   boolean Accept;
   List<Vector4d> MemberData;

   public PacketGetTribeUIValues() {
      this.Accept = false;
      this.MemberData = new ArrayList<>();
   }

   public PacketGetTribeUIValues(boolean flag, List<Vector4d> list) {
      this.Accept = flag;
      this.MemberData = list;
   }

   static PacketGetTribeUIValues handle() {
      return new PacketGetTribeUIValues(false, new ArrayList<>());
   }

   public void fromBytes(ByteBuf buf) {
      this.Accept = buf.readBoolean();
      int i = buf.readInt();
      int i2 = 0;

      try {
         while (i2 < i) {
            this.MemberData.add(new Vector4d(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()));
            i2++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeBoolean(this.Accept);
      buf.writeInt(this.MemberData.size());

      for (Vector4d vector4d : this.MemberData) {
         buf.writeInt((int)vector4d.getX());
         buf.writeInt((int)vector4d.getY());
         buf.writeInt((int)vector4d.getZ());
         buf.writeInt((int)vector4d.getW());
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketGetTribeUIValues, IMessage> {
      public IMessage onMessage(PacketGetTribeUIValues packet, MessageContext ctx) {
         try {
            if (!packet.Loaded) {
               System.out.println("received an invalid message @GetTribeUIValues :(");
               return null;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         try {
            if (ctx.side.isClient()) {
               GuiStructureBuilder.ExecuteBuild = packet.Accept;
               KoboldNpc.MemberData = packet.MemberData;
               return null;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
            UUID uuid = GirlHomeBuilder.findTribeUuid(ctx.getServerHandler().player.getPersistentID());

            try {
               if (uuid == null) {
                  NetworkHandler.channel.sendTo(PacketGetTribeUIValues.handle(), ctx.getServerHandler().player);
                  return;
               }
            } catch (RuntimeException error3) {
               throw rethrow(error3);
            }

            boolean flag = GirlHomeBuilder.hasTribe(uuid);
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            HashMap map = GirlHomeBuilder.getLoadedHomes(uuid, serverPlayer.world);
            List list = GirlHomeBuilder.getKobolds(uuid);
            ArrayList list2 = new ArrayList();
            int i = GirlHomeBuilder.getTribeColor(uuid).getWoolMeta();
            HashSet set = new HashSet();

            for (KoboldNpc kobold : list) {
               try {
                  if (kobold.isDead) {
                     continue;
                  }
               } catch (RuntimeException error4) {
                  throw rethrow(error4);
               }

               UUID uuid2 = kobold.getGirlUuid();

               try {
                  if (set.contains(uuid2)) {
                     continue;
                  }
               } catch (RuntimeException error5) {
                  throw rethrow(error5);
               }

               if (kobold.aA) {
                  i = EyeAndKoboldColor.safeValueOf((String)kobold.getDataManager().get(GirlEffectEntity.TribeColorKey)).getWoolMeta();
               }

               list2.add(new Vector4d(kobold.posX, kobold.posY, kobold.posZ, i));
               set.add(uuid2);
            }

            for (Entry entry : map.entrySet()) {
               try {
                  if (set.contains(entry.getKey())) {
                     continue;
                  }
               } catch (RuntimeException error6) {
                  throw rethrow(error6);
               }

               BlockPos pos = (BlockPos)entry.getValue();
               list2.add(new Vector4d(pos.getX(), pos.getY(), pos.getZ(), i));
            }

            NetworkHandler.channel.sendTo(new PacketGetTribeUIValues(flag, list2), serverPlayer);
         });
         return null;
      }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
