package com.trolmastercard.sexmod;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.util.UUID;
import net.minecraft.util.math.Vec3d;

public class MathUtils {
   public static float angleDifference(double d, double d2) {
      d = (d + (Math.PI * 2)) % (Math.PI * 2);
      d2 = (d2 + (Math.PI * 2)) % (Math.PI * 2);
      double d3 = d2 - d;

      while (d3 < -Math.PI) {
         d3 += Math.PI * 2;
      }

      while (d3 >= Math.PI) {
         d3 -= Math.PI * 2;
      }

      return (float)d3;
   }

   public static Vec2f rotationBetween(Vec3d vec3d, Vec3d vec3d2) {
      Vec3d vec3d3 = vec3d2.subtract(vec3d).normalize();
      return new Vec2f(
         (float)Math.atan2(vec3d3.x, vec3d3.z),
         (float)Math.atan2(vec3d3.y, Math.sqrt(vec3d3.x * vec3d3.x + vec3d3.z * vec3d3.z))
      );
   }

   public static void copyToClipboard(String string) {
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      StringSelection stringSelection = new StringSelection(string);
      clipboard.setContents(stringSelection, null);
   }


   public static String capitalize(String string) {
        block4: {
            try {
                try {
                    if (string != null && !string.isEmpty()) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw MathUtils.rethrow(runtimeException);
                }
                return string;
            }
            catch (RuntimeException runtimeException) {
                throw MathUtils.rethrow(runtimeException);
            }
        }
        return Character.toUpperCase(string.charAt(0)) + string.substring(1).toLowerCase();
    }

   public static boolean isInRange(double d, double d2, double d3) {
      try {
         if (d < d2) {
            return false;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         return !(d >= d3);
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }
   }

   public static int weightedRandomIndex(int i) {
      try {
         if (i <= 0) {
            return i;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Random random = new Random();
      int i2 = 0;

      for (int i3 = 0; i3 <= i; i3++) {
         i2 += i3;
      }

      int i4 = random.nextInt(i2) + 1;
      int i5 = 0;

      for (int i6 = 0; i6 <= i; i6++) {
         i5 += i6;

         try {
            if (i5 >= i4) {
               return i6;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }
      }

      return i;
   }

   public static int randomSign() {
      try {
         if (ModConstants.Random.nextBoolean()) {
            return 1;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return -1;
   }

   public static float clamp(float f, float f2, float f3) {
      return Math.max(f2, Math.min(f3, f));
   }

   public static double clamp(double d, double d2, double d3) {
      return Math.max(d2, Math.min(d3, d));
   }


   public static float randomJitter(float f, boolean flag) {
        int i;
        float f2;
        block5: {
            block4: {
                Random random = new Random();
                try {
                    try {
                        f2 = random.nextFloat() * f;
                        if (!flag || !random.nextBoolean()) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw MathUtils.rethrow(runtimeException);
                    }
                    i = -1;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw MathUtils.rethrow(runtimeException);
                }
            }
            i = 1;
        }
        return f2 * (float)i;
    }


   public static float clampStep(float f, float f2, float f3) {
        block10: {
            block11: {
                try {
                    if (Math.abs(f - f2) <= f3) {
                        return f;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw MathUtils.rethrow(runtimeException);
                }
                try {
                    try {
                        if (!(Math.abs(f) < Math.abs(f2))) break block10;
                        if (!(f2 > 0.0f)) break block11;
                    }
                    catch (RuntimeException runtimeException) {
                        throw MathUtils.rethrow(runtimeException);
                    }
                    return f2 - f3;
                }
                catch (RuntimeException runtimeException) {
                    throw MathUtils.rethrow(runtimeException);
                }
            }
            return f2 + f3;
        }
        try {
            if (f > 0.0f) {
                return f - f3;
            }
        }
        catch (RuntimeException runtimeException) {
            throw MathUtils.rethrow(runtimeException);
        }
        return f + f3;
    }

   public static int roundToInt(double d) {
      return Math.round((float)d);
   }


   public static void runAfterDelay(int i, Runnable runnable) {
      String string = UUID.randomUUID().toString();
      new Thread(() -> {
         try {
            Thread.sleep(i);
         }
         catch (Exception exception) {
            exception.printStackTrace();
         }
         runnable.run();
      }, (ServerThreadUtil.isServerThread() ? "server sexmod thread " : "client sexmod thread ") + string).start();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
