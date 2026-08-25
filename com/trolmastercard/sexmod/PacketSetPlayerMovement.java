package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

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

      public IMessage onMessage(PacketSetPlayerMovement packet, MessageContext ctx) {
            if (!packet.Loaded || ctx.side != Side.CLIENT) {
                System.out.println("received an invalid message @SetPlayerMovement :(");
                return null;
            }
            AnimationInputLock.setAnimationLocked(packet.MovementActive);
            try {
                Minecraft.getMinecraft().player.setVelocity(0.0, 0.0, 0.0);
            }
            catch (Exception error) {
                // empty catch block
            }
            if (packet.MovementActive) {
                GuiHud.forceShowHud();
            }
            return null;
        }

      private static Exception rethrow(Exception error) {
         return error;
      }
   }
}
