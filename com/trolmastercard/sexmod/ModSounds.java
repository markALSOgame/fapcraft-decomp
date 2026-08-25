package com.trolmastercard.sexmod;

import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import java.util.Random;

public class ModSounds {
   public static final SoundEvent[] MISC_PLOB = new SoundEvent[1];
   public static final SoundEvent[] MISC_BELLJINGLE = new SoundEvent[1];
   public static final SoundEvent[] MISC_BEDRUSTLE = new SoundEvent[2];
   public static final SoundEvent[] MISC_SLAP = new SoundEvent[2];
   public static final SoundEvent[] MISC_TOUCH = new SoundEvent[2];
   public static final SoundEvent[] MISC_POUNDING = new SoundEvent[35];
   public static final SoundEvent[] MISC_SMALLINSERTS = new SoundEvent[5];
   public static final SoundEvent[] MISC_INSERTS = new SoundEvent[5];
   public static final SoundEvent[] MISC_CUMINFLATION = new SoundEvent[1];
   public static final SoundEvent[] MISC_SCREAM = new SoundEvent[2];
   public static final SoundEvent[] MISC_FART = new SoundEvent[3];
   public static final SoundEvent[] MISC_JUMP = new SoundEvent[1];
   public static final SoundEvent[] MISC_EAT = new SoundEvent[3];
   public static final SoundEvent[] MISC_SLIDE = new SoundEvent[7];
   public static final SoundEvent[] GIRLS_JENNY_AFTERSESSIONMOAN = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_JENNY_AHH = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_JENNY_BJMOAN = new SoundEvent[13];
   public static final SoundEvent[] GIRLS_JENNY_GIGGLE = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_JENNY_HAPPYOH = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_JENNY_HEAVYBREATHING = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_JENNY_HMPH = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_JENNY_HUH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_JENNY_LIGHTBREATHING = new SoundEvent[12];
   public static final SoundEvent[] GIRLS_JENNY_LIPSOUND = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_JENNY_MMM = new SoundEvent[9];
   public static final SoundEvent[] GIRLS_JENNY_MOAN = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_JENNY_SADOH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_JENNY_SIGH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ELLIE_AFTERSESSIONMOAN = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_ELLIE_AHH = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_ELLIE_BJMOAN = new SoundEvent[13];
   public static final SoundEvent[] GIRLS_ELLIE_GIGGLE = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_ELLIE_HAPPYOH = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_ELLIE_HEAVYBREATHING = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_ELLIE_HMPH = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_ELLIE_HUH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ELLIE_LIGHTBREATHING = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_ELLIE_LIPSOUND = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_ELLIE_MMM = new SoundEvent[9];
   public static final SoundEvent[] GIRLS_ELLIE_MOAN = new SoundEvent[9];
   public static final SoundEvent[] GIRLS_ELLIE_SADOH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ELLIE_SIGH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ELLIE_COMETOMOMMY = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ELLIE_GOODBOY = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ELLIE_MOMMYHORNY = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_BIA_AHH = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_BIA_BJMOAN = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_BIA_BREATH = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_BIA_GIGGLE = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_BIA_HEY = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_BIA_HUH = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_BIA_MMM = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_LUNA_AHH = new SoundEvent[18];
   public static final SoundEvent[] GIRLS_LUNA_CUTENYA = new SoundEvent[12];
   public static final SoundEvent[] GIRLS_LUNA_HAPPYOH = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_LUNA_HMPH = new SoundEvent[6];
   public static final SoundEvent[] GIRLS_LUNA_HORNINYA = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_LUNA_HUH = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_LUNA_LIGHTBREATHING = new SoundEvent[25];
   public static final SoundEvent[] GIRLS_LUNA_MMM = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_LUNA_MOAN = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_LUNA_SADOH = new SoundEvent[7];
   public static final SoundEvent[] GIRLS_LUNA_SIGH = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_LUNA_SINGING = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_LUNA_GIGGLE = new SoundEvent[15];
   public static final SoundEvent[] GIRLS_LUNA_OUU = new SoundEvent[13];
   public static final SoundEvent[] GIRLS_LUNA_OWO = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_ALLIE_AFTERSESSIONMOAN = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_ALLIE_AHH = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_ALLIE_BJMOAN = new SoundEvent[14];
   public static final SoundEvent[] GIRLS_ALLIE_GIGGLE = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_ALLIE_HAPPYOH = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_ALLIE_HEAVYBREATHING = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_ALLIE_HMPH = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_ALLIE_HUH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ALLIE_LIGHTBREATHING = new SoundEvent[11];
   public static final SoundEvent[] GIRLS_ALLIE_LIPSOUND = new SoundEvent[14];
   public static final SoundEvent[] GIRLS_ALLIE_MMM = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_ALLIE_MOAN = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_ALLIE_SADOH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ALLIE_SIGH = new SoundEvent[2];
   public static final SoundEvent[] GIRLS_ALLIE_SCAWY = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_KOBOLD_BJMOAN = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_KOBOLD_GIGGLE = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_KOBOLD_HAA = new SoundEvent[7];
   public static final SoundEvent[] GIRLS_KOBOLD_HEYMASTER = new SoundEvent[6];
   public static final SoundEvent[] GIRLS_KOBOLD_INTERESTED = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_KOBOLD_LIGHTBREATHING = new SoundEvent[12];
   public static final SoundEvent[] GIRLS_KOBOLD_MASTER = new SoundEvent[6];
   public static final SoundEvent[] GIRLS_KOBOLD_MOAN = new SoundEvent[10];
   public static final SoundEvent[] GIRLS_KOBOLD_ORGASM = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_KOBOLD_SAD = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_KOBOLD_YEP = new SoundEvent[7];
   public static final SoundEvent[] MISC_FLAP = new SoundEvent[4];
   public static final SoundEvent[] MISC_SHATTER = new SoundEvent[1];
   public static final SoundEvent[] MISC_WEOWEO = new SoundEvent[4];
   public static final SoundEvent[] MISC_BEEW = new SoundEvent[3];
   public static final SoundEvent[] MISC_CLAP = new SoundEvent[1];
   public static final SoundEvent[] GIRLS_GALATH_AHH = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_GALATH_BREATHING = new SoundEvent[7];
   public static final SoundEvent[] GIRLS_GALATH_DIALOG = new SoundEvent[6];
   public static final SoundEvent[] GIRLS_GALATH_GIGGLE = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_GALATH_HMPH = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_GALATH_HUH = new SoundEvent[3];
   public static final SoundEvent[] GIRLS_GALATH_LIGHTCHARGE = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_GALATH_MOAN = new SoundEvent[8];
   public static final SoundEvent[] GIRLS_GALATH_STRONGCHARGE = new SoundEvent[4];
   public static final SoundEvent[] GIRLS_GALATH_UUH = new SoundEvent[7];
   public static final SoundEvent[] GIRLS_GALATH_ORGASM = new SoundEvent[5];
   public static final SoundEvent[] GIRLS_GALATH_AAA = new SoundEvent[2];
   public static final SoundEvent[] MISC_PYRO = new SoundEvent[1];
   static HashMap<SoundEvent, Integer> lastRandomSound = new HashMap<>();


   public static void registerAllSounds() {
        for (java.lang.reflect.Field field : ModSounds.class.getDeclaredFields()) {
            Class<?> clazz = field.getType();
            if (!clazz.isArray() || clazz.getComponentType() != SoundEvent.class) continue;
            SoundEvent[] soundEventArray;
            try {
                soundEventArray = (SoundEvent[])field.get(null);
            }
            catch (Exception exception) {
                Main.LOGGER.error("Error registering sound: " + exception.getMessage());
                continue;
            }
            String string = field.getName().toLowerCase().replace("_", ".");
            String[] stringArray = string.split("\\.");
            String string2 = stringArray.length > 2 ? stringArray[2] : stringArray[1];
            for (int i = 0; i < soundEventArray.length; ++i) {
                soundEventArray[i] = ModSounds.registerSoundEvent(String.format("%s.%s%s", string, string2, i));
            }
        }
    }

   public static SoundEvent registerSoundEvent(String string) {
      ResourceLocation location = new ResourceLocation("sexmod", string);
      SoundEvent sound = new SoundEvent(location);
      sound.setRegistryName(string);
      ForgeRegistries.SOUND_EVENTS.register(sound);
      return sound;
   }

   public static SoundEvent pickRandomSound(SoundEvent[] soundArray) {
      lastRandomSound.putIfAbsent(soundArray[0], -69);
      int i = 0;

      int i2;
      do {
         i2 = ModConstants.Random.nextInt(soundArray.length);
      } while (++i < 10 && i2 == lastRandomSound.get(soundArray[0]));

      lastRandomSound.replace(soundArray[0], i2);
      return soundArray[i2];
   }
 static RuntimeException rethrow(RuntimeException error) {

      return error;

   }


   private static RuntimeException rethrow(Exception error) {
      return new RuntimeException(error);
   }
}
