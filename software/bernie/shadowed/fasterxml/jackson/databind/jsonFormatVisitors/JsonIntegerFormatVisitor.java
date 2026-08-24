package software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors;

import software.bernie.shadowed.fasterxml.jackson.core.JsonParser;

public interface JsonIntegerFormatVisitor extends JsonValueFormatVisitor {
   void numberType(JsonParser.NumberType var1);

   class Base extends JsonValueFormatVisitor.Base implements JsonIntegerFormatVisitor {
      @Override
      public void numberType(JsonParser.NumberType type) {
      }
   }
}
