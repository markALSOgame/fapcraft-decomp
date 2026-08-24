package com.trolmastercard.sexmod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandReloadCustomModels extends CommandBase {
   public static final CommandReloadCustomModels Instance = new CommandReloadCustomModels();

   public String getName() {
      return "reloadcustommodels";
   }

   public String getUsage(ICommandSender sender) {
      return "/reloadcustommodels";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public void execute(MinecraftServer server, ICommandSender sender, String[] stringArray) throws CommandException {
      FilePersistence.reloadModels(false);

      for (EntityPlayerMP serverPlayer : server.getPlayerList().getPlayers()) {
         server.addScheduledTask(() -> NetworkHandler.channel.sendTo(new PacketRequestServerModelAvailability(FilePersistence.getModelChecksums()), serverPlayer));
      }
   }
}
