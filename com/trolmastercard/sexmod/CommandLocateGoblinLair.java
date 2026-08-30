package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandLocateGoblinLair extends CommandBase {
   public static final CommandLocateGoblinLair Instance = new CommandLocateGoblinLair();

   public String getName() {
      return "locatenearestgoblinlair";
   }

   public String getUsage(ICommandSender sender) {
      return "/locatenearestgoblinlair";
   }


   public void execute(MinecraftServer server, ICommandSender sender, String[] stringArray) throws CommandException {
        Entity entity = sender.getCommandSenderEntity();
        if (entity != null && entity.dimension != 0) {
            sender.sendMessage(new TextComponentString(TextFormatting.YELLOW + "goblin lairs don't exist in the " + (entity.dimension == -1 ? TextFormatting.RED + "Nether" : TextFormatting.DARK_PURPLE + "End")));
            return;
        }
        GoblinNpc goblin = null;
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                if (!(girl instanceof GoblinNpc)) continue;
                GoblinNpc goblin2 = (GoblinNpc)girl;
                if (!goblin2.aX) continue;
                if (goblin == null) {
                    goblin = goblin2;
                    continue;
                }
                if (!(goblin2.getDistanceSq(sender.getPosition()) < goblin.getDistanceSq(sender.getPosition()))) continue;
                goblin = goblin2;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        if (goblin == null) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "No nearby goblin lair found uwu"));
            return;
        }
        BlockPos blockPos = goblin.getPosition();
        sender.sendMessage(new TextComponentString(String.format("%sgoblin lair found at %s%s %s%s %s%s", TextFormatting.YELLOW, TextFormatting.RED, blockPos.getX(), TextFormatting.GREEN, blockPos.getY(), TextFormatting.BLUE, blockPos.getZ())));
    }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
