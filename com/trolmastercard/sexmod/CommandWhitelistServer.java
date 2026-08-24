package com.trolmastercard.sexmod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.IClientCommand;

public class CommandWhitelistServer extends CommandBase implements IClientCommand {
   public static final CommandWhitelistServer Instance = new CommandWhitelistServer();

   public String getName() {
      return "whitelistserver";
   }

   public String getUsage(ICommandSender sender) {
      return "/whitelistserver";
   }

   public boolean allowUsageWithoutPrefix(ICommandSender sender, String string) {
      return false;
   }

   public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
      return true;
   }


   public void execute(MinecraftServer server, ICommandSender sender, String[] stringArray) throws CommandException {
        boolean flag;
        String string;
        block14: {
            block13: {
                string = FilePersistence.getServerAddress();
                try {
                    if (string == null) {
                        sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.YELLOW + "This is a multiplayer feature only"));
                        return;
                    }
                }
                catch (CommandException commandException) {
                    throw CommandWhitelistServer.rethrow(commandException);
                }
                try {
                    if (FilePersistence.isWhitelistedByFile(string)) {
                        sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.GREEN + "Server is already whitelisted :)"));
                        return;
                    }
                }
                catch (CommandException commandException) {
                    throw CommandWhitelistServer.rethrow(commandException);
                }
                try {
                    try {
                        if (stringArray.length <= 0 || !"confirm".equals(stringArray[0])) break block13;
                    }
                    catch (CommandException commandException) {
                        throw CommandWhitelistServer.rethrow(commandException);
                    }
                    flag = true;
                    break block14;
                }
                catch (CommandException commandException) {
                    throw CommandWhitelistServer.rethrow(commandException);
                }
            }
            flag = false;
        }
        boolean flag2 = flag;
        try {
            if (!flag2) {
                sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.YELLOW + "By whitelisting this server, you allow the server to send you the custom models that are used on it"));
                sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.RED + "ONLY WHITELIST SERVERS, WHOSE SERVER OWNER YOU KNOW AND TRUST"));
                sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.YELLOW + "to confirm your decision type:"));
                sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.GREEN + "/whitelistserver confirm"));
                return;
            }
        }
        catch (CommandException commandException) {
            throw CommandWhitelistServer.rethrow(commandException);
        }
        FilePersistence.whitelistServer(string);
        sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.GREEN + "confirmed :)"));
        FilePersistence.requestModelChecksums();
    }

   private static CommandException rethrow(CommandException command) {
      return command;
   }
}
