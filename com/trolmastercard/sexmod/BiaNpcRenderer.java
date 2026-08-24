package com.trolmastercard.sexmod;

import java.util.HashSet;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BiaNpcRenderer extends GeoGirlRenderer {
   public BiaNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
   }

   @Override
   public HashSet<String> getFilteredBoneNames() {
      return new HashSet<String>() {
         {
            this.add("boobs");
            this.add("booty");
            this.add("vagina");
            this.add("fuckhole");
            this.add("leaf7");
            this.add("leaf8");
         }
      };
   }
}
