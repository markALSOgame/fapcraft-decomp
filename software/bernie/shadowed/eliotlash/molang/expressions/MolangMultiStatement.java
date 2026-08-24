package software.bernie.shadowed.eliotlash.molang.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import software.bernie.shadowed.eliotlash.mclib.math.Variable;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public class MolangMultiStatement extends MolangExpression {
   public List<MolangExpression> expressions = new ArrayList<>();
   public Map<String, Variable> locals = new HashMap<>();

   public MolangMultiStatement(MolangParser context) {
      super(context);
   }

   @Override
   public double get() {
      double value = 0.0;

      for (MolangExpression expression : this.expressions) {
         value = expression.get();
      }

      return value;
   }

   @Override
   public String toString() {
      StringJoiner builder = new StringJoiner("; ");

      for (MolangExpression expression : this.expressions) {
         builder.add(expression.toString());
         if (expression instanceof MolangValue && ((MolangValue)expression).returns) {
            break;
         }
      }

      return builder.toString();
   }
}
