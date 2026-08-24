package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetPlayerMovement implements IMessage {
   boolean Loaded;
   boolean MovementActive;

   public PacketSetPlayerMovement(boolean flag) {
      this.MovementActive = flag;
      this.Loaded = true;
   }

   public PacketSetPlayerMovement() {
      this.Loaded = false;
   }

   public void fromBytes(ByteBuf buf) {
      this.MovementActive = buf.readBoolean();
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      buf.writeBoolean(this.MovementActive);
      this.Loaded = true;
   }

   public static class Handler implements IMessageHandler<PacketSetPlayerMovement, IMessage> {

      public IMessage handle(PacketSetPlayerMovement packet, MessageContext ctx) {
            block9: {
                if (!packet.Loaded) ** GOTO lbl8
                try {
                    block10: {
                        if (ctx.side == Side.CLIENT) break block9;
                        break block10;
                        catch (Exception error) {
                            throw PacketSetPlayerMovement.Handler.rethrow(error);
                        }
                    }
                    System.out.println("received an invalid message @SetPlayerMovement :(");
                    return null;
                }
                catch (Exception error2) {
                    throw PacketSetPlayerMovement.Handler.rethrow(error2);
                }
            }
            AnimationInputLock.setAnimationLocked(packet.MovementActive);
            try {
                Minecraft.getMinecraft().player.setVelocity(0.0, 0.0, 0.0);
            }
            catch (Exception error3) {
                // empty catch block
            }
            try {
                if (packet.MovementActive) {
                    GuiHud.forceShowHud();
                }
            }
            catch (Exception error4) {
                throw PacketSetPlayerMovement.Handler.rethrow(error4);
            }
            return null;
        }

      private static Exception rethrow(Exception error) {
         return error;
      }
   }
}
