package com.trolmastercard.sexmod;

import java.util.ConcurrentModificationException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandLocateGoblinLair extends CommandBase {
   public static final CommandLocateGoblinLair Instance = new CommandLocateGoblinLair();

   public String getName() {
      return "locatenearestgoblinlair";
   }

   public String getUsage(ICommandSender sender) {
      return "/locatenearestgoblinlair";
   }


   public void execute(MinecraftServer server, ICommandSender sender, String[] stringArray) throws CommandException {
        block19: {
            String string;
            StringBuilder stringBuilder;
            TextComponentString textComponentString;
            TextComponentString textComponentString2;
            ICommandSender iCommandSender2;
            block21: {
                block20: {
                    Entity entity;
                    block22: {
                        entity = sender.getCommandSenderEntity();
                        if (entity == null) break block19;
                        if (entity.dimension == 0) break block19;
                        break block22;
                        catch (ConcurrentModificationException concurrentModificationException) {
                            throw CommandLocateGoblinLair.rethrow(concurrentModificationException);
                        }
                    }
                    try {
                        block23: {
                            TextComponentString textComponentString3;
                            iCommandSender2 = sender;
                            textComponentString2 = textComponentString3;
                            textComponentString = textComponentString3;
                            stringBuilder = new StringBuilder().append(TextFormatting.YELLOW).append("goblin lairs don't exist in the ");
                            if (entity.dimension != -1) break block20;
                            break block23;
                            catch (ConcurrentModificationException concurrentModificationException) {
                                throw CommandLocateGoblinLair.rethrow(concurrentModificationException);
                            }
                        }
                        string = TextFormatting.RED + "Nether";
                        break block21;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw CommandLocateGoblinLair.rethrow(concurrentModificationException);
                    }
                }
                string = TextFormatting.DARK_PURPLE + "End";
            }
            textComponentString2(stringBuilder.append(string).toString());
            iCommandSender2.sendMessage((ITextComponent)textComponentString);
            return;
        }
        GoblinNpc goblin = null;
        try {
            for (GirlEntity girl : GirlEntity.getAllGirls()) {
                try {
                    if (!(girl instanceof GoblinNpc)) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw CommandLocateGoblinLair.rethrow(concurrentModificationException);
                }
                GoblinNpc goblin2 = (GoblinNpc)girl;
                try {
                    if (!goblin2.aX) {
                        continue;
                    }
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw CommandLocateGoblinLair.rethrow(concurrentModificationException);
                }
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
        try {
            if (goblin == null) {
                sender.sendMessage((ITextComponent)new TextComponentString(TextFormatting.RED + "No nearby goblin lair found uwu"));
                return;
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            throw CommandLocateGoblinLair.rethrow(concurrentModificationException);
        }
        BlockPos blockPos = goblin.getPosition();
        sender.sendMessage((ITextComponent)new TextComponentString(String.format("%sgoblin lair found at %s%s %s%s %s%s", TextFormatting.YELLOW, TextFormatting.RED, blockPos.getX(), TextFormatting.GREEN, blockPos.getY(), TextFormatting.BLUE, blockPos.getZ())));
    }

   private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
      return error;
   }
}
