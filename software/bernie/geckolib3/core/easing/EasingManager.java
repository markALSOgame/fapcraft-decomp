package software.bernie.geckolib3.core.easing;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import software.bernie.geckolib3.core.util.Memoizer;

public class EasingManager {
   static Function<Double, Double> quart = poly(4.0);
   static Function<Double, Double> quint = poly(5.0);
   static Function<EasingManager.EasingFunctionArgs, Function<Double, Double>> getEasingFunction = Memoizer.memoize(EasingManager::getEasingFuncImpl);

   public static double ease(double number, EasingType easingType, List<Double> easingArgs) {
      Double firstArg = easingArgs != null && easingArgs.size() >= 1 ? easingArgs.get(0) : null;
      return getEasingFunction.apply(new EasingManager.EasingFunctionArgs(easingType, firstArg)).apply(number);
   }

   static Function<Double, Double> getEasingFuncImpl(EasingManager.EasingFunctionArgs args) {
      switch (args.easingType) {
         case Linear:
         default:
            return in(EasingManager::linear);
         case Step:
            return in(step(args.arg0));
         case EaseInSine:
            return in(EasingManager::sin);
         case EaseOutSine:
            return out(EasingManager::sin);
         case EaseInOutSine:
            return inOut(EasingManager::sin);
         case EaseInQuad:
            return in(EasingManager::quad);
         case EaseOutQuad:
            return out(EasingManager::quad);
         case EaseInOutQuad:
            return inOut(EasingManager::quad);
         case EaseInCubic:
            return in(EasingManager::cubic);
         case EaseOutCubic:
            return out(EasingManager::cubic);
         case EaseInOutCubic:
            return inOut(EasingManager::cubic);
         case EaseInExpo:
            return in(EasingManager::exp);
         case EaseOutExpo:
            return out(EasingManager::exp);
         case EaseInOutExpo:
            return inOut(EasingManager::exp);
         case EaseInCirc:
            return in(EasingManager::circle);
         case EaseOutCirc:
            return out(EasingManager::circle);
         case EaseInOutCirc:
            return inOut(EasingManager::circle);
         case EaseInQuart:
            return in(quart);
         case EaseOutQuart:
            return out(quart);
         case EaseInOutQuart:
            return inOut(quart);
         case EaseInQuint:
            return in(quint);
         case EaseOutQuint:
            return out(quint);
         case EaseInOutQuint:
            return inOut(quint);
         case EaseInBack:
            return in(back(args.arg0));
         case EaseOutBack:
            return out(back(args.arg0));
         case EaseInOutBack:
            return inOut(back(args.arg0));
         case EaseInElastic:
            return in(elastic(args.arg0));
         case EaseOutElastic:
            return out(elastic(args.arg0));
         case EaseInOutElastic:
            return inOut(elastic(args.arg0));
         case EaseInBounce:
            return in(bounce(args.arg0));
         case EaseOutBounce:
            return out(bounce(args.arg0));
         case EaseInOutBounce:
            return inOut(bounce(args.arg0));
      }
   }

   static Function<Double, Double> in(Function<Double, Double> easing) {
      return easing;
   }

   static Function<Double, Double> out(Function<Double, Double> easing) {
      return t -> 1.0 - easing.apply(1.0 - t);
   }

   static Function<Double, Double> inOut(Function<Double, Double> easing) {
      return t -> t < 0.5 ? easing.apply(t * 2.0) / 2.0 : 1.0 - easing.apply((1.0 - t) * 2.0) / 2.0;
   }

   static Function<Double, Double> step0() {
      return n -> n > 0.0 ? 1.0 : 0.0;
   }

   static Function<Double, Double> step1() {
      return n -> n >= 1.0 ? 1.0 : 0.0;
   }

   static double linear(double t) {
      return t;
   }

   static double quad(double t) {
      return t * t;
   }

   static double cubic(double t) {
      return t * t * t;
   }

   static Function<Double, Double> poly(double n) {
      return t -> Math.pow(t, n);
   }

   static double sin(double t) {
      return 1.0 - Math.cos((float)(t * Math.PI / 2.0));
   }

   static double circle(double t) {
      return 1.0 - Math.sqrt(1.0 - t * t);
   }

   static double exp(double t) {
      return Math.pow(2.0, 10.0 * (t - 1.0));
   }

   static Function<Double, Double> elastic(Double bounciness) {
      double p = (bounciness == null ? 1.0 : bounciness) * Math.PI;
      return t -> 1.0 - Math.pow(Math.cos((float)(t * Math.PI / 2.0)), 3.0) * Math.cos((float)(t * p));
   }

   static Function<Double, Double> back(Double s) {
      double p = s == null ? 1.70158 : s * 1.70158;
      return t -> t * t * ((p + 1.0) * t - p);
   }

   public static Function<Double, Double> bounce(Double s) {
      double k = s == null ? 0.5 : s;
      Function<Double, Double> q = x -> 7.5625 * x * x;
      Function<Double, Double> w = x -> 30.25 * k * Math.pow(x - 0.5454545454545454, 2.0) + 1.0 - k;
      Function<Double, Double> r = x -> 121.0 * k * k * Math.pow(x - 0.8181818181818182, 2.0) + 1.0 - k * k;
      Function<Double, Double> t = x -> 484.0 * k * k * k * Math.pow(x - 0.9545454545454546, 2.0) + 1.0 - k * k * k;
      return x -> min(q.apply(x), w.apply(x), r.apply(x), t.apply(x));
   }

   static Function<Double, Double> step(Double stepArg) {
      int steps = stepArg != null ? stepArg.intValue() : 2;
      double[] intervals = stepRange(steps);
      return t -> intervals[findIntervalBorderIndex(t, intervals, false)];
   }

   static double min(double a, double b, double c, double d) {
      return Math.min(Math.min(a, b), Math.min(c, d));
   }

   static int findIntervalBorderIndex(double point, double[] intervals, boolean useRightBorder) {
      if (point < intervals[0]) {
         return 0;
      }

      if (point > intervals[intervals.length - 1]) {
         return intervals.length - 1;
      }

      int indexOfNumberToCompare = 0;
      int leftBorderIndex = 0;
      int rightBorderIndex = intervals.length - 1;

      while (rightBorderIndex - leftBorderIndex != 1) {
         indexOfNumberToCompare = leftBorderIndex + (rightBorderIndex - leftBorderIndex) / 2;
         if (point >= intervals[indexOfNumberToCompare]) {
            leftBorderIndex = indexOfNumberToCompare;
         } else {
            rightBorderIndex = indexOfNumberToCompare;
         }
      }

      return useRightBorder ? rightBorderIndex : leftBorderIndex;
   }

   static double[] stepRange(int steps) {
      double stop = 1.0;
      if (steps < 2) {
         throw new IllegalArgumentException("steps must be > 2, got:" + steps);
      }

      double stepLength = 1.0 / steps;
      AtomicInteger i = new AtomicInteger();
      return DoubleStream.generate(() -> i.getAndIncrement() * stepLength).limit(steps).toArray();
   }

   static class EasingFunctionArgs {
      public final EasingType easingType;
      public final Double arg0;

      public EasingFunctionArgs(EasingType easingType, Double arg0) {
         this.easingType = easingType;
         this.arg0 = arg0;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            EasingManager.EasingFunctionArgs that = (EasingManager.EasingFunctionArgs)o;
            return this.easingType == that.easingType && Objects.equals(this.arg0, that.arg0);
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.easingType, this.arg0);
      }
   }
}
