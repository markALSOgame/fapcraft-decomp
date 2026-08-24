package com.trolmastercard.sexmod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;

public class CommandFuta extends CommandBase implements IClientCommand {
   static final String FilePath = "sexmod/futa";
   static final int TickDelay = 10;
   static final float Chance = 0.025F;
   public static boolean Enabled = true;
   public static final CommandFuta Instance = new CommandFuta();

   public CommandFuta() {
      String string = "";

      try {
         string = new BufferedReader(new FileReader("sexmod/futa")).readLine().toLowerCase();
      } catch (Exception error) {
      }

      try {
         if ("".equals(string)) {
            return;
         }
      } catch (Exception error2) {
         throw rethrow(error2);
      }

      try {
         if ("true".equals(string)) {
            Enabled = true;
         }
      } catch (Exception error3) {
         throw rethrow(error3);
      }

      try {
         if ("false".equals(string)) {
            Enabled = false;
         }
      } catch (Exception error4) {
         throw rethrow(error4);
      }
   }

   public String getName() {
      return "futa";
   }

   public String getUsage(ICommandSender sender) {
      return "/futa <true|false>";
   }

   public void execute(MinecraftServer server, ICommandSender sender, String[] stringArray) throws CommandException {
      try {
         if (stringArray.length < 1) {
            this.a(sender);
            return;
         }
      } catch (IOException error) {
         throw rethrow(error);
      }

      String string = stringArray[0].toLowerCase();

      label99: {
         label89: {
            try {
               if ("true".equals(string)) {
                  Enabled = true;
                  break label89;
               }
            } catch (IOException error2) {
               throw rethrow(error2);
            }

            try {
               if (!"false".equals(string)) {
                  break label99;
               }

               Enabled = false;
            } catch (IOException error3) {
               throw rethrow(error3);
            }
         }

         try {
            FileWriter fileWriter = new FileWriter("sexmod/futa");
            fileWriter.write(string);
            fileWriter.close();
         } catch (IOException error4) {
            error4.printStackTrace();
         }

         try {
            Iterator iterator2 = GirlEntity.getAllGirls().iterator();

            while (true) {
               GirlEntity girl;
               while (true) {
                  while (true) {
                     while (true) {
                        if (!iterator2.hasNext()) {
                           return;
                        }

                        girl = (GirlEntity)iterator2.next();

                        try {
                           if (girl.isDead) {
                              continue;
                           }
                           break;
                        } catch (IOException error5) {
                           throw rethrow(error5);
                        }
                     }

                     try {
                        if (!girl.world.isRemote) {
                           continue;
                        }
                        break;
                     } catch (IOException error6) {
                        throw rethrow(error6);
                     }
                  }

                  try {
                     if (!(girl instanceof GalathNpc)) {
                        continue;
                     }
                     break;
                  } catch (IOException error7) {
                     throw rethrow(error7);
                  }
               }

               Vec3d vec3d = girl.getModelBone("cockParticles").add(girl.getPositionVector());
               Random random = girl.getRNG();
               int i = 0;

               try {
                  while (i < 10) {
                     girl.world
                        .spawnParticle(
                           EnumParticleTypes.DRAGON_BREATH,
                           vec3d.x,
                           vec3d.y,
                           vec3d.z,
                           random.nextFloat() * 0.025F * MathUtils.randomSign(),
                           random.nextFloat() * 0.025F * MathUtils.randomSign(),
                           random.nextFloat() * 0.025F * MathUtils.randomSign(),
                           new int[0]
                        );
                     i++;
                  }
               } catch (IOException error8) {
                  throw rethrow(error8);
               }
            }
         } catch (ConcurrentModificationException error9) {
            return;
         }
      }

      this.a(sender);
   }

   void a(ICommandSender sender) {
      sender.sendMessage(
         new TextComponentString(
            String.format(
               "%sYou can either do %s/futa true %sor %s/futa false", TextFormatting.YELLOW, TextFormatting.GRAY, TextFormatting.YELLOW, TextFormatting.GRAY
            )
         )
      );
   }

   public boolean allowUsageWithoutPrefix(ICommandSender sender, String string) {
      return false;
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
