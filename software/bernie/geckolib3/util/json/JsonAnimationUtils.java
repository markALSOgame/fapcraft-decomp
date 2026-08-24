package software.bernie.geckolib3.util.json;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import net.minecraft.client.util.JsonException;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.keyframe.BoneAnimation;
import software.bernie.geckolib3.core.keyframe.EventKeyFrame;
import software.bernie.geckolib3.core.keyframe.ParticleEventKeyFrame;
import software.bernie.geckolib3.core.keyframe.VectorKeyFrameList;
import software.bernie.geckolib3.util.AnimationUtils;
import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class JsonAnimationUtils {
   public static Set<Entry<String, JsonElement>> getAnimations(JsonObject json) {
      return getObjectListAsArray(json.getAsJsonObject("animations"));
   }

   public static List<Entry<String, JsonElement>> getBones(JsonObject json) {
      JsonObject bones = json.getAsJsonObject("bones");
      return bones == null ? new ArrayList<>() : new ArrayList<>(getObjectListAsArray(bones));
   }

   public static Set<Entry<String, JsonElement>> getRotationKeyFrames(JsonObject json) {
      JsonElement rotationObject = json.get("rotation");
      if (rotationObject.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", rotationObject.getAsJsonArray()));
      } else if (rotationObject.isJsonPrimitive()) {
         JsonPrimitive primitive = rotationObject.getAsJsonPrimitive();
         Gson gson = new Gson();
         JsonElement jsonElement = gson.toJsonTree(Arrays.asList(primitive, primitive, primitive));
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement));
      } else {
         return getObjectListAsArray(rotationObject.getAsJsonObject());
      }
   }

   public static Set<Entry<String, JsonElement>> getPositionKeyFrames(JsonObject json) {
      JsonElement positionObject = json.get("position");
      if (positionObject.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", positionObject.getAsJsonArray()));
      } else if (positionObject.isJsonPrimitive()) {
         JsonPrimitive primitive = positionObject.getAsJsonPrimitive();
         Gson gson = new Gson();
         JsonElement jsonElement = gson.toJsonTree(Arrays.asList(primitive, primitive, primitive));
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement));
      } else {
         return getObjectListAsArray(positionObject.getAsJsonObject());
      }
   }

   public static Set<Entry<String, JsonElement>> getScaleKeyFrames(JsonObject json) {
      JsonElement scaleObject = json.get("scale");
      if (scaleObject.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", scaleObject.getAsJsonArray()));
      } else if (scaleObject.isJsonPrimitive()) {
         JsonPrimitive primitive = scaleObject.getAsJsonPrimitive();
         Gson gson = new Gson();
         JsonElement jsonElement = gson.toJsonTree(Arrays.asList(primitive, primitive, primitive));
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement));
      } else {
         return getObjectListAsArray(scaleObject.getAsJsonObject());
      }
   }

   public static ArrayList<Entry<String, JsonElement>> getSoundEffectFrames(JsonObject json) {
      JsonObject sound_effects = json.getAsJsonObject("sound_effects");
      return sound_effects == null ? new ArrayList<>() : new ArrayList<>(getObjectListAsArray(sound_effects));
   }

   public static ArrayList<Entry<String, JsonElement>> getParticleEffectFrames(JsonObject json) {
      JsonObject particle_effects = json.getAsJsonObject("particle_effects");
      return particle_effects == null ? new ArrayList<>() : new ArrayList<>(getObjectListAsArray(particle_effects));
   }

   public static ArrayList<Entry<String, JsonElement>> getCustomInstructionKeyFrames(JsonObject json) {
      JsonObject custom_instructions = json.getAsJsonObject("timeline");
      return custom_instructions == null ? new ArrayList<>() : new ArrayList<>(getObjectListAsArray(custom_instructions));
   }

   private static JsonElement getObjectByKey(Set<Entry<String, JsonElement>> json, String key) throws JsonException {
      return json.stream().filter(x -> x.getKey().equals(key)).findFirst().orElseThrow(() -> new JsonException("Could not find key: " + key)).getValue();
   }

   public static Entry<String, JsonElement> getAnimation(JsonObject animationFile, String animationName) throws JsonException {
      return new SimpleEntry<>(animationName, getObjectByKey(getAnimations(animationFile), animationName));
   }

   public static Set<Entry<String, JsonElement>> getObjectListAsArray(JsonObject json) {
      return json.entrySet();
   }

   public static Animation deserializeJsonToAnimation(Entry<String, JsonElement> element, MolangParser parser) throws ClassCastException, IllegalStateException {
      Animation animation = new Animation();
      JsonObject animationJsonObject = element.getValue().getAsJsonObject();
      animation.animationName = element.getKey();
      JsonElement animation_length = animationJsonObject.get("animation_length");
      animation.animationLength = animation_length == null ? null : AnimationUtils.convertSecondsToTicks(animation_length.getAsDouble());
      animation.boneAnimations = new ArrayList<>();
      JsonElement loop = animationJsonObject.get("loop");
      animation.loop = loop != null && loop.getAsString() == "true" ? ILoopType.EDefaultLoopTypes.LOOP : ILoopType.EDefaultLoopTypes.PLAY_ONCE;
      ArrayList<Entry<String, JsonElement>> soundEffectFrames = getSoundEffectFrames(animationJsonObject);
      if (soundEffectFrames != null) {
         for (Entry<String, JsonElement> keyFrame : soundEffectFrames) {
            animation.soundKeyFrames
               .add(new EventKeyFrame<>(Double.parseDouble(keyFrame.getKey()) * 20.0, keyFrame.getValue().getAsJsonObject().get("effect").getAsString()));
         }
      }

      ArrayList<Entry<String, JsonElement>> particleKeyFrames = getParticleEffectFrames(animationJsonObject);
      if (particleKeyFrames != null) {
         for (Entry<String, JsonElement> keyFrame : particleKeyFrames) {
            JsonObject object = keyFrame.getValue().getAsJsonObject();
            JsonElement effect = object.get("effect");
            JsonElement locator = object.get("locator");
            JsonElement pre_effect_script = object.get("pre_effect_script");
            animation.particleKeyFrames
               .add(
                  new ParticleEventKeyFrame(
                     Double.parseDouble(keyFrame.getKey()) * 20.0,
                     effect == null ? "" : effect.getAsString(),
                     locator == null ? "" : locator.getAsString(),
                     pre_effect_script == null ? "" : pre_effect_script.getAsString()
                  )
               );
         }
      }

      ArrayList<Entry<String, JsonElement>> customInstructionKeyFrames = getCustomInstructionKeyFrames(animationJsonObject);
      if (customInstructionKeyFrames != null) {
         for (Entry<String, JsonElement> keyFrame : customInstructionKeyFrames) {
            animation.customInstructionKeyframes
               .add(
                  new EventKeyFrame<>(
                     Double.parseDouble(keyFrame.getKey()) * 20.0,
                     keyFrame.getValue() instanceof JsonArray
                        ? convertJsonArrayToList(keyFrame.getValue().getAsJsonArray()).toString()
                        : keyFrame.getValue().getAsString()
                  )
               );
         }
      }

      for (Entry<String, JsonElement> bone : getBones(animationJsonObject)) {
         BoneAnimation boneAnimation = new BoneAnimation();
         boneAnimation.boneName = bone.getKey();
         JsonObject boneJsonObj = bone.getValue().getAsJsonObject();

         try {
            Set<Entry<String, JsonElement>> scaleKeyFramesJson = getScaleKeyFrames(boneJsonObj);
            boneAnimation.scaleKeyFrames = JsonKeyFrameUtils.convertJsonToKeyFrames(new ArrayList<>(scaleKeyFramesJson), parser);
         } catch (Exception e) {
            boneAnimation.scaleKeyFrames = new VectorKeyFrameList<>();
         }

         try {
            Set<Entry<String, JsonElement>> positionKeyFramesJson = getPositionKeyFrames(boneJsonObj);
            boneAnimation.positionKeyFrames = JsonKeyFrameUtils.convertJsonToKeyFrames(new ArrayList<>(positionKeyFramesJson), parser);
         } catch (Exception e) {
            boneAnimation.positionKeyFrames = new VectorKeyFrameList<>();
         }

         try {
            Set<Entry<String, JsonElement>> rotationKeyFramesJson = getRotationKeyFrames(boneJsonObj);
            boneAnimation.rotationKeyFrames = JsonKeyFrameUtils.convertJsonToRotationKeyFrames(new ArrayList<>(rotationKeyFramesJson), parser);
         } catch (Exception e) {
            boneAnimation.rotationKeyFrames = new VectorKeyFrameList<>();
         }

         animation.boneAnimations.add(boneAnimation);
      }

      if (animation.animationLength == null) {
         animation.animationLength = calculateLength(animation.boneAnimations);
      }

      return animation;
   }

   private static double calculateLength(List<BoneAnimation> boneAnimations) {
      double longestLength = 0.0;

      for (BoneAnimation animation : boneAnimations) {
         double xKeyframeTime = animation.rotationKeyFrames.getLastKeyframeTime();
         double yKeyframeTime = animation.positionKeyFrames.getLastKeyframeTime();
         double zKeyframeTime = animation.scaleKeyFrames.getLastKeyframeTime();
         longestLength = maxAll(longestLength, xKeyframeTime, yKeyframeTime, zKeyframeTime);
      }

      return longestLength == 0.0 ? Double.MAX_VALUE : longestLength;
   }

   static List<IValue> convertJsonArrayToList(JsonArray array) {
      return (List<IValue>)new Gson().fromJson(array, ArrayList.class);
   }

   public static double maxAll(double... values) {
      double max = 0.0;

      for (double value : values) {
         max = Math.max(value, max);
      }

      return max;
   }
}
