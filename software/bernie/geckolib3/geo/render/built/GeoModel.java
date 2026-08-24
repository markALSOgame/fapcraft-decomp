package software.bernie.geckolib3.geo.render.built;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import software.bernie.geckolib3.geo.raw.pojo.ModelProperties;

public class GeoModel {
   public List<GeoBone> topLevelBones = new ArrayList<>();
   public ModelProperties properties;

   public Optional<GeoBone> getBone(String name) {
      for (GeoBone bone : this.topLevelBones) {
         GeoBone optionalBone = this.getBoneRecursively(name, bone);
         if (optionalBone != null) {
            return Optional.of(optionalBone);
         }
      }

      return Optional.empty();
   }

   private GeoBone getBoneRecursively(String name, GeoBone bone) {
      if (bone.name.equals(name)) {
         return bone;
      }

      for (GeoBone childBone : bone.childBones) {
         if (childBone.name.equals(name)) {
            return childBone;
         }

         GeoBone optionalBone = this.getBoneRecursively(name, childBone);
         if (optionalBone != null) {
            return optionalBone;
         }
      }

      return null;
   }
}
