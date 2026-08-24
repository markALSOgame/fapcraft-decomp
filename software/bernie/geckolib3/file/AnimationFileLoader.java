package software.bernie.geckolib3.file;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.util.json.JsonAnimationUtils;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class AnimationFileLoader {
   public AnimationFile loadAllAnimations(MolangParser parser, ResourceLocation location, IResourceManager manager) {
      AnimationFile animationFile = new AnimationFile();
      JsonObject jsonRepresentation = this.loadFile(location, manager);

      for (Entry<String, JsonElement> entry : JsonAnimationUtils.getAnimations(jsonRepresentation)) {
         String animationName = entry.getKey();

         try {
            Animation animation = JsonAnimationUtils.deserializeJsonToAnimation(JsonAnimationUtils.getAnimation(jsonRepresentation, animationName), parser);
            animationFile.putAnimation(animationName, animation);
         } catch (JsonException e) {
            GeckoLib.LOGGER.error("Could not load animation: {}", animationName, e);
            throw new RuntimeException(e);
         }
      }

      return animationFile;
   }

   private JsonObject loadFile(ResourceLocation location, IResourceManager manager) {
      String content = getResourceAsString(location, manager);
      Gson GSON = new Gson();
      return (JsonObject)JsonUtils.func_193839_a(GSON, new StringReader(content), JsonObject.class);
   }

   public static String getResourceAsString(ResourceLocation location, IResourceManager manager) {
      try (InputStream inputStream = manager.func_110536_a(location).func_110527_b()) {
         return IOUtils.toString(inputStream, Charset.defaultCharset());
      } catch (Exception e) {
         String message = "Couldn't load " + location;
         GeckoLib.LOGGER.error(message, e);
         throw new RuntimeException(new FileNotFoundException(location.toString()));
      }
   }
}
