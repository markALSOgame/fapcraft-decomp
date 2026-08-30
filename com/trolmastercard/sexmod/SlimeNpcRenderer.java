package com.trolmastercard.sexmod;

import java.util.HashSet;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SlimeNpcRenderer extends GeoGirlRenderer {
   public SlimeNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
   }

   @Override
   public HashSet<String> getFilteredBoneNames() {
      HashSet set = super.getFilteredBoneNames();
      set.add("figure");
      return set;
   }
}
