package com.trolmastercard.sexmod;

import java.util.HashMap;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class BoneAnimationProcessor<T extends IAnimatable> extends AnimationProcessor<T> {
   HashMap<String, IBone> BoneCache = new HashMap<>();

   public BoneAnimationProcessor(IAnimatableModel iAnimatableModel) {
      super(iAnimatableModel);
   }

   public IBone getBone(String string) {
      return this.BoneCache.get(string);
   }

   public void registerModelRenderer(IBone iBone) {
      super.registerModelRenderer(iBone);
      this.BoneCache.put(iBone.getName(), iBone);
   }

   public void clearModelRendererList() {
      super.clearModelRendererList();
      this.BoneCache.clear();
   }
}
