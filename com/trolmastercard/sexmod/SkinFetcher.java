package com.trolmastercard.sexmod;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SkinFetcher {
   public static final int a = 3;

   @SideOnly(Side.CLIENT)
   public static BufferedImage fetchSkinImage(UUID uuid) throws IOException {
      try {
         URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString().replace("-", ""));
         BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
         String string = reader.lines().collect(Collectors.joining());
         int i = string.indexOf("\"value\" : ");
         int i2 = i + 11;
         StringBuilder sb = new StringBuilder();
         int i3 = 0;

         try {
            while (string.charAt(i2 + i3) != '"') {
               sb.append(string.charAt(i2 + i3));
               i3++;
            }
         } catch (Exception error) {
            throw rethrow(error);
         }

         String string2 = new String(Base64.getDecoder().decode(sb.toString()));
         int i4 = string2.indexOf("\"url\" : ");
         int i5 = i4 + 9;
         StringBuilder sb2 = new StringBuilder();
         int i6 = 0;

         try {
            while (string2.charAt(i5 + i6) != '"') {
               sb2.append(string2.charAt(i5 + i6));
               i6++;
            }
         } catch (Exception error2) {
            throw rethrow(error2);
         }

         URL url2 = new URL(sb2.toString());
         return ImageIO.read(url2);
      } catch (Exception error3) {
         return ImageIO.read(
            Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("sexmod", "textures/player/steve.png")).getInputStream()
         );
      }
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
