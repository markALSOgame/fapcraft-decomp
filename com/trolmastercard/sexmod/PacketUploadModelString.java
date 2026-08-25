package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketUploadModelString implements IMessage {
   boolean Loaded = false;
   String ModelString;
   List<Integer> ColorIds = new ArrayList<>();
   UUID GirlUuid;

   public PacketUploadModelString() {
   }

   public PacketUploadModelString(String string, UUID uuid) {
      this.ModelString = string;
      this.GirlUuid = uuid;
   }

   public PacketUploadModelString(String string, UUID uuid, List<Integer> list) {
      this.ModelString = string;
      this.GirlUuid = uuid;
      this.ColorIds = list;
   }

   public void fromBytes(ByteBuf buf) {
      this.ModelString = ByteBufUtils.readUTF8String(buf);
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      int i = buf.readInt();
      int i2 = 0;

      try {
         while (i2 < i) {
            this.ColorIds.add(buf.readInt());
            i2++;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.ModelString);
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      buf.writeInt(this.ColorIds.size());

      for (int i : this.ColorIds) {
         buf.writeInt(i);
      }
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketUploadModelString, IMessage> {

      public IMessage onMessage(PacketUploadModelString packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                    }
                    System.out.println("received an invalid message @UploadModelString :(");
                    return null;
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                block16: {
                    boolean flag;
                    GirlEntity girl = GirlEntity.getServerSideByUuid(packet.GirlUuid);
                    try {
                        flag = packet.ColorIds.size() > 0;
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                    }
                    boolean flag2 = flag;
                    boolean flag3 = false;
                    if (flag2) {
                        flag3 = this.handle(girl, packet.ColorIds);
                        try {
                            if (flag3) {
                                girl.a(packet.ColorIds);
                            }
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                        }
                    }
                    try {
                        if (!(girl instanceof PlayerGirlEntity)) {
                            girl.setCustomModel(packet.ModelString);
                            return;
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                    }
                    EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
                    NBTTagCompound nBTTagCompound = entityPlayerMP.getEntityData();
                    PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer((EntityPlayer)entityPlayerMP);
                    try {
                        if (playerGirl == null) {
                            return;
                        }
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                    }
                    GirlRegistry girlType = GirlRegistry.getByEntity((Entity)playerGirl);
                    try {
                        try {
                            nBTTagCompound.setString("sexmod:CustomModel" + girlType.toString(), packet.ModelString);
                            if (!flag2 || !flag3) break block16;
                        }
                        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                        }
                        nBTTagCompound.setString("sexmod:GirlSpecific" + girlType.toString(), GirlEntity.colorsToString(packet.ColorIds));
                    }
                    catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        throw PacketUploadModelString.Handler.rethrow(indexOutOfBoundsException);
                    }
                }
            });
            return null;
        }

      boolean handle(GirlEntity girl, List<Integer> list) {
         ArrayList list2 = girl.D();

         try {
            int i = 0;

            while (true) {
               try {
                  if (i >= list2.size()) {
                     return true;
                  }

                  if ((Integer)list2.get(i) <= (Integer)list.get(i)) {
                     return false;
                  }
               } catch (IndexOutOfBoundsException error) {
                  throw rethrow(error);
               }

               i++;
            }
         } catch (IndexOutOfBoundsException error2) {
            return false;
         }
      }

      private static IndexOutOfBoundsException rethrow(IndexOutOfBoundsException error) {
         return error;
      }
   }
}
