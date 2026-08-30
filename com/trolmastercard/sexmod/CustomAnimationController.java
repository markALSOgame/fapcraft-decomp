package com.trolmastercard.sexmod;

import java.util.function.Function;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.controller.AnimationController.IAnimationPredicate;
import software.bernie.geckolib3.core.easing.EasingType;

public class CustomAnimationController<T extends IAnimatable> extends AnimationController<T> {
   public CustomAnimationController(T t, String string, float f, IAnimationPredicate<T> iAnimationPredicate) {
      super(t, string, f, iAnimationPredicate);
   }

   public CustomAnimationController(T t, String string, float f, EasingType easingType, IAnimationPredicate<T> iAnimationPredicate) {
      super(t, string, f, easingType, iAnimationPredicate);
   }

   public CustomAnimationController(T t, String string, float f, Function<Double, Double> function, IAnimationPredicate<T> iAnimationPredicate) {
      super(t, string, f, function, iAnimationPredicate);
   }
}
