package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateGirl implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   String Key;
   String Value;

   public PacketUpdateGirl() {
      this.Loaded = false;
   }

   public PacketUpdateGirl(UUID uuid, String string, String string2) {
      this.GirlUuid = uuid;
      this.Key = string;
      this.Value = string2;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Key = ByteBufUtils.readUTF8String(buf);
      this.Value = ByteBufUtils.readUTF8String(buf);
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBuf buf2;
      String string;
      label16: {
         try {
            ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
            ByteBufUtils.writeUTF8String(buf, this.Key);
            buf2 = buf;
            if (this.Value == null) {
               string = "null";
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         string = this.Value;
      }

      ByteBufUtils.writeUTF8String(buf2, string);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketUpdateGirl, IMessage> {
      public IMessage onMessage(PacketUpdateGirl packet, MessageContext ctx) {
         if (!packet.Loaded) {
            System.out.println("received an invalid message @ChangeDataParameter :(");
            return null;
         }

         FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
            GirlEntity girl = GirlEntity.getServerSideByUuid(packet.GirlUuid);
            if (girl == null) {
               return;
            }

            switch (packet.Key) {
               case "pregnant":
                  girl.getDataManager().set(SlimeNpc.TicksUntilBirthKey, Integer.valueOf(packet.Value));
                  break;
               case "currentModel":
                  girl.getDataManager().set(GirlEntity.OutfitIndexKey, Integer.valueOf(packet.Value));
                  break;
               case "currentAction":
                  if (GirlAnimationState.valueOf(packet.Value) == GirlAnimationState.ATTACK && girl.getCurrentAction() != GirlAnimationState.NULL) {
                     break;
                  }

                  girl.setCurrentAction(GirlAnimationState.valueOf(packet.Value));
                  break;
               case "animationFollowUp":
                  girl.getDataManager().set(GirlEntity.BlowjobStageKey, packet.Value);
                  break;
               case "playerSheHasSexWith":
                  if (packet.Value.equals("null")) {
                     girl.handleGirlUuidEvent((UUID)null);
                     break;
                  }

                  girl.handleGirlUuidEvent(UUID.fromString(packet.Value));
                  break;
               case "targetPos": {
                  String[] stringArray = packet.Value.split("f");
                  Vec3d vec3d = new Vec3d(Double.parseDouble(stringArray[0]), Double.parseDouble(stringArray[1]), Double.parseDouble(stringArray[2]));
                  girl.setTargetPos(vec3d);
                  break;
               }
               case "master":
                  girl.getDataManager().set(GirlEntity.MasterUuidKey, packet.Value);
                  break;
               case "walk speed":
                  girl.getDataManager().set(GirlEntity.WalkStateKey, packet.Value);
                  break;
               case "shouldbeattargetpos":
                  girl.getDataManager().set(GirlEntity.BusyKey, Boolean.valueOf(packet.Value));
            }
         });
         return null;
      }
   }
}
