package software.bernie.shadowed.eliotlash.mclib.math.functions.utility;

import software.bernie.shadowed.eliotlash.mclib.math.IValue;
import software.bernie.shadowed.eliotlash.mclib.math.functions.Function;

public class DieRollInteger extends Function {
   public java.util.Random random = new java.util.Random();

   public DieRollInteger(IValue[] values, String name) throws Exception {
      super(values, name);
   }

   @Override
   public int getRequiredArguments() {
      return 3;
   }

   @Override
   public double get() {
      double i = 0.0;
      double total = 0.0;

      while (i < this.getArg(0)) {
         total += Math.round(this.getArg(1) + Math.random() * (this.getArg(2) - this.getArg(1)));
      }

      return total;
   }
}
