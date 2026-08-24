package software.bernie.shadowed.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;
import software.bernie.shadowed.fasterxml.jackson.annotation.JsonTypeInfo;
import software.bernie.shadowed.fasterxml.jackson.core.JsonGenerator;
import software.bernie.shadowed.fasterxml.jackson.core.JsonToken;
import software.bernie.shadowed.fasterxml.jackson.core.type.WritableTypeId;
import software.bernie.shadowed.fasterxml.jackson.databind.BeanProperty;
import software.bernie.shadowed.fasterxml.jackson.databind.JavaType;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonMappingException;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonNode;
import software.bernie.shadowed.fasterxml.jackson.databind.JsonSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.MapperFeature;
import software.bernie.shadowed.fasterxml.jackson.databind.SerializerProvider;
import software.bernie.shadowed.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import software.bernie.shadowed.fasterxml.jackson.databind.introspect.AnnotatedMember;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonschema.JsonSchema;
import software.bernie.shadowed.fasterxml.jackson.databind.jsonschema.SchemaAware;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import software.bernie.shadowed.fasterxml.jackson.databind.jsontype.TypeSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.ser.ContextualSerializer;
import software.bernie.shadowed.fasterxml.jackson.databind.util.ClassUtil;

@JacksonStdImpl
public class JsonValueSerializer extends StdSerializer<Object> implements ContextualSerializer, JsonFormatVisitable, SchemaAware {
   protected final AnnotatedMember _accessor;
   protected final JsonSerializer<Object> _valueSerializer;
   protected final BeanProperty _property;
   protected final boolean _forceTypeInformation;

   public JsonValueSerializer(AnnotatedMember accessor, JsonSerializer<?> ser) {
      super(accessor.getType());
      this._accessor = accessor;
      this._valueSerializer = (JsonSerializer<Object>)ser;
      this._property = null;
      this._forceTypeInformation = true;
   }

   public JsonValueSerializer(JsonValueSerializer src, BeanProperty property, JsonSerializer<?> ser, boolean forceTypeInfo) {
      super(_notNullClass(src.handledType()));
      this._accessor = src._accessor;
      this._valueSerializer = (JsonSerializer<Object>)ser;
      this._property = property;
      this._forceTypeInformation = forceTypeInfo;
   }

   private static final Class<Object> _notNullClass(Class<?> cls) {
      return cls == null ? Object.class : cls;
   }

   public JsonValueSerializer withResolved(BeanProperty property, JsonSerializer<?> ser, boolean forceTypeInfo) {
      return this._property == property && this._valueSerializer == ser && forceTypeInfo == this._forceTypeInformation
         ? this
         : new JsonValueSerializer(this, property, ser, forceTypeInfo);
   }

   @Override
   public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      JsonSerializer<?> ser = this._valueSerializer;
      if (ser == null) {
         JavaType t = this._accessor.getType();
         if (!provider.isEnabled(MapperFeature.USE_STATIC_TYPING) && !t.isFinal()) {
            return this;
         }

         ser = provider.findPrimaryPropertySerializer(t, property);
         boolean forceTypeInformation = this.isNaturalTypeWithStdHandling(t.getRawClass(), ser);
         return this.withResolved(property, ser, forceTypeInformation);
      } else {
         ser = provider.handlePrimaryContextualization(ser, property);
         return this.withResolved(property, ser, this._forceTypeInformation);
      }
   }

   @Override
   public void serialize(Object bean, JsonGenerator gen, SerializerProvider prov) throws IOException {
      try {
         Object value = this._accessor.getValue(bean);
         if (value == null) {
            prov.defaultSerializeNull(gen);
            return;
         }

         JsonSerializer<Object> ser = this._valueSerializer;
         if (ser == null) {
            Class<?> c = value.getClass();
            ser = prov.findTypedValueSerializer(c, true, this._property);
         }

         ser.serialize(value, gen, prov);
      } catch (Exception e) {
         this.wrapAndThrow(prov, e, bean, this._accessor.getName() + "()");
      }
   }

   @Override
   public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer0) throws IOException {
      Object value = null;

      try {
         value = this._accessor.getValue(bean);
         if (value == null) {
            provider.defaultSerializeNull(gen);
            return;
         }

         JsonSerializer<Object> ser = this._valueSerializer;
         if (ser == null) {
            ser = provider.findValueSerializer(value.getClass(), this._property);
         } else if (this._forceTypeInformation) {
            WritableTypeId typeIdDef = typeSer0.writeTypePrefix(gen, typeSer0.typeId(bean, JsonToken.VALUE_STRING));
            ser.serialize(value, gen, provider);
            typeSer0.writeTypeSuffix(gen, typeIdDef);
            return;
         }

         JsonValueSerializer.TypeSerializerRerouter rr = new JsonValueSerializer.TypeSerializerRerouter(typeSer0, bean);
         ser.serializeWithType(value, gen, provider, rr);
      } catch (Exception e) {
         this.wrapAndThrow(provider, e, bean, this._accessor.getName() + "()");
      }
   }

   @Override
   public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
      return this._valueSerializer instanceof SchemaAware ? ((SchemaAware)this._valueSerializer).getSchema(provider, null) : JsonSchema.getDefaultSchemaNode();
   }

   @Override
   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      JavaType type = this._accessor.getType();
      Class<?> declaring = this._accessor.getDeclaringClass();
      if (declaring == null || !declaring.isEnum() || !this._acceptJsonFormatVisitorForEnum(visitor, typeHint, declaring)) {
         JsonSerializer<Object> ser = this._valueSerializer;
         if (ser == null) {
            ser = visitor.getProvider().findTypedValueSerializer(type, false, this._property);
            if (ser == null) {
               visitor.expectAnyFormat(typeHint);
               return;
            }
         }

         ser.acceptJsonFormatVisitor(visitor, null);
      }
   }

   protected boolean _acceptJsonFormatVisitorForEnum(JsonFormatVisitorWrapper visitor, JavaType typeHint, Class<?> enumType) throws JsonMappingException {
      JsonStringFormatVisitor stringVisitor = visitor.expectStringFormat(typeHint);
      if (stringVisitor != null) {
         Set<String> enums = new LinkedHashSet<>();

         for (Object en : enumType.getEnumConstants()) {
            try {
               enums.add(String.valueOf(this._accessor.getValue(en)));
            } catch (Exception e) {
               Throwable t = e;

               while (t instanceof InvocationTargetException && t.getCause() != null) {
                  t = t.getCause();
               }

               ClassUtil.throwIfError(t);
               throw JsonMappingException.wrapWithPath(t, en, this._accessor.getName() + "()");
            }
         }

         stringVisitor.enumTypes(enums);
      }

      return true;
   }

   protected boolean isNaturalTypeWithStdHandling(Class<?> rawType, JsonSerializer<?> ser) {
      if (rawType.isPrimitive()) {
         if (rawType != int.class && rawType != boolean.class && rawType != double.class) {
            return false;
         }
      } else if (rawType != String.class && rawType != Integer.class && rawType != Boolean.class && rawType != Double.class) {
         return false;
      }

      return this.isDefaultSerializer(ser);
   }

   @Override
   public String toString() {
      return "(@JsonValue serializer for method " + this._accessor.getDeclaringClass() + "#" + this._accessor.getName() + ")";
   }

   static class TypeSerializerRerouter extends TypeSerializer {
      protected final TypeSerializer _typeSerializer;
      protected final Object _forObject;

      public TypeSerializerRerouter(TypeSerializer ts, Object ob) {
         this._typeSerializer = ts;
         this._forObject = ob;
      }

      @Override
      public TypeSerializer forProperty(BeanProperty prop) {
         throw new UnsupportedOperationException();
      }

      @Override
      public JsonTypeInfo.As getTypeInclusion() {
         return this._typeSerializer.getTypeInclusion();
      }

      @Override
      public String getPropertyName() {
         return this._typeSerializer.getPropertyName();
      }

      @Override
      public TypeIdResolver getTypeIdResolver() {
         return this._typeSerializer.getTypeIdResolver();
      }

      @Override
      public WritableTypeId writeTypePrefix(JsonGenerator g, WritableTypeId typeId) throws IOException {
         typeId.forValue = this._forObject;
         return this._typeSerializer.writeTypePrefix(g, typeId);
      }

      @Override
      public WritableTypeId writeTypeSuffix(JsonGenerator g, WritableTypeId typeId) throws IOException {
         return this._typeSerializer.writeTypeSuffix(g, typeId);
      }

      @Deprecated
      @Override
      public void writeTypePrefixForScalar(Object value, JsonGenerator gen) throws IOException {
         this._typeSerializer.writeTypePrefixForScalar(this._forObject, gen);
      }

      @Deprecated
      @Override
      public void writeTypePrefixForObject(Object value, JsonGenerator gen) throws IOException {
         this._typeSerializer.writeTypePrefixForObject(this._forObject, gen);
      }

      @Deprecated
      @Override
      public void writeTypePrefixForArray(Object value, JsonGenerator gen) throws IOException {
         this._typeSerializer.writeTypePrefixForArray(this._forObject, gen);
      }

      @Deprecated
      @Override
      public void writeTypeSuffixForScalar(Object value, JsonGenerator gen) throws IOException {
         this._typeSerializer.writeTypeSuffixForScalar(this._forObject, gen);
      }

      @Deprecated
      @Override
      public void writeTypeSuffixForObject(Object value, JsonGenerator gen) throws IOException {
         this._typeSerializer.writeTypeSuffixForObject(this._forObject, gen);
      }

      @Deprecated
      @Override
      public void writeTypeSuffixForArray(Object value, JsonGenerator gen) throws IOException {
         this._typeSerializer.writeTypeSuffixForArray(this._forObject, gen);
      }

      @Deprecated
      @Override
      public void writeTypePrefixForScalar(Object value, JsonGenerator gen, Class<?> type) throws IOException {
         this._typeSerializer.writeTypePrefixForScalar(this._forObject, gen, type);
      }

      @Deprecated
      @Override
      public void writeTypePrefixForObject(Object value, JsonGenerator gen, Class<?> type) throws IOException {
         this._typeSerializer.writeTypePrefixForObject(this._forObject, gen, type);
      }

      @Deprecated
      @Override
      public void writeTypePrefixForArray(Object value, JsonGenerator gen, Class<?> type) throws IOException {
         this._typeSerializer.writeTypePrefixForArray(this._forObject, gen, type);
      }

      @Deprecated
      @Override
      public void writeCustomTypePrefixForScalar(Object value, JsonGenerator gen, String typeId) throws IOException {
         this._typeSerializer.writeCustomTypePrefixForScalar(this._forObject, gen, typeId);
      }

      @Deprecated
      @Override
      public void writeCustomTypePrefixForObject(Object value, JsonGenerator gen, String typeId) throws IOException {
         this._typeSerializer.writeCustomTypePrefixForObject(this._forObject, gen, typeId);
      }

      @Deprecated
      @Override
      public void writeCustomTypePrefixForArray(Object value, JsonGenerator gen, String typeId) throws IOException {
         this._typeSerializer.writeCustomTypePrefixForArray(this._forObject, gen, typeId);
      }

      @Deprecated
      @Override
      public void writeCustomTypeSuffixForScalar(Object value, JsonGenerator gen, String typeId) throws IOException {
         this._typeSerializer.writeCustomTypeSuffixForScalar(this._forObject, gen, typeId);
      }

      @Deprecated
      @Override
      public void writeCustomTypeSuffixForObject(Object value, JsonGenerator gen, String typeId) throws IOException {
         this._typeSerializer.writeCustomTypeSuffixForObject(this._forObject, gen, typeId);
      }

      @Deprecated
      @Override
      public void writeCustomTypeSuffixForArray(Object value, JsonGenerator gen, String typeId) throws IOException {
         this._typeSerializer.writeCustomTypeSuffixForArray(this._forObject, gen, typeId);
      }
   }
}
