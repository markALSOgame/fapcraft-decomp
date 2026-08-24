package com.trolmastercard.sexmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommandSetModelCode extends CommandBase implements IClientCommand {
   public static final CommandSetModelCode Instance = new CommandSetModelCode();

   public boolean allowUsageWithoutPrefix(ICommandSender sender, String string) {
      return false;
   }

   public String getName() {
      return "setmodelcode";
   }

   public String getUsage(ICommandSender sender) {
      return "/setmodelcode";
   }

   public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
      return true;
   }

   public void execute(MinecraftServer server, ICommandSender sender, String[] stringArray) throws CommandException {
      Minecraft mc = Minecraft.getMinecraft();
      EntityPlayerSP mcPlayer = mc.player;
      String string = "";
      String string2 = "";
      if (stringArray.length > 0) {
         String[] stringArray2 = stringArray[0].split("\\$");
         string = stringArray2[0];
         if (stringArray2.length > 1) {
            string2 = stringArray2[1];
         }
      }

      RayTraceResult hit = Minecraft.getMinecraft().objectMouseOver;
      GirlEntity girl = this.a(hit);

      try {
         if (girl == null) {
            mcPlayer.sendStatusMessage(new TextComponentString("You gotta transform into the girl you want to apply the model-code to"), true);
            return;
         }
      } catch (CommandException command) {
         throw rethrow(command);
      }

      try {
         if ("".equals(string2)) {
            NetworkHandler.channel.sendToServer(new PacketUploadModelString(string, girl.getGirlUuid()));
            mcPlayer.sendStatusMessage(new TextComponentString(this.a(girl)), true);
            return;
         }
      } catch (CommandException command2) {
         throw rethrow(command2);
      }

      NetworkHandler.channel.sendToServer(new PacketUploadModelString(string, girl.getGirlUuid(), GirlEntity.stringToColors(string2)));
      mcPlayer.sendStatusMessage(new TextComponentString(this.a(girl)), true);
   }

   String a(GirlEntity girl) {
      try {
         if (girl instanceof PlayerGirlEntity) {
            return TextFormatting.YELLOW + "applied model code to your player-" + MathUtils.capitalize(GirlRegistry.getByEntity(girl).toString());
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return TextFormatting.YELLOW + "applied model code to this " + girl.getDisplayName();
   }

   @SideOnly(Side.CLIENT)
   GirlEntity a(RayTraceResult hit) {
      try {
         if (hit == null) {
            return PlayerGirlEntity.getByPlayer(Minecraft.getMinecraft().player);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (GirlEntity.isGirlEntity(hit.entityHit)) {
            return (GirlEntity)hit.entityHit;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return PlayerGirlEntity.getByPlayer(Minecraft.getMinecraft().player);
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
