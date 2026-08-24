package software.bernie.geckolib3.core;

import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public interface IAnimatableModel<E> {
   default double getCurrentTick() {
      return System.nanoTime() / 1000000L / 50.0;
   }

   default void setLivingAnimations(E entity, Integer uniqueID) {
      this.setLivingAnimations(entity, uniqueID, null);
   }

   void setLivingAnimations(E var1, Integer var2, AnimationEvent var3);

   AnimationProcessor getAnimationProcessor();

   Animation getAnimation(String var1, IAnimatable var2);

   default IBone getBone(String boneName) {
      IBone bone = this.getAnimationProcessor().getBone(boneName);
      if (bone == null) {
         throw new RuntimeException("Could not find bone: " + boneName);
      } else {
         return bone;
      }
   }

   void setMolangQueries(IAnimatable var1, double var2);
}
