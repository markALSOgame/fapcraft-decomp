package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SerializationUtils {
   public static <T extends Serializable> T clone(T object) {
      if (object == null) {
         return null;
      }

      byte[] objectData = serialize(object);
      ByteArrayInputStream bais = new ByteArrayInputStream(objectData);

      try (SerializationUtils.ClassLoaderAwareObjectInputStream in = new SerializationUtils.ClassLoaderAwareObjectInputStream(
            bais, object.getClass().getClassLoader()
         )) {
         return (T)in.readObject();
      } catch (ClassNotFoundException ex) {
         throw new SerializationException("ClassNotFoundException while reading cloned object data", ex);
      } catch (IOException ex) {
         throw new SerializationException("IOException while reading or closing cloned object data", ex);
      }
   }

   public static <T extends Serializable> T roundtrip(T msg) {
      return deserialize(serialize(msg));
   }

   public static void serialize(Serializable obj, OutputStream outputStream) {
      Validate.notNull(outputStream, "The OutputStream must not be null");

      try (ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
         out.writeObject(obj);
      } catch (IOException ex) {
         throw new SerializationException(ex);
      }
   }

   public static byte[] serialize(Serializable obj) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
      serialize(obj, baos);
      return baos.toByteArray();
   }

   public static <T> T deserialize(InputStream inputStream) {
      Validate.notNull(inputStream, "The InputStream must not be null");

      try (ObjectInputStream in = new ObjectInputStream(inputStream)) {
         return (T)in.readObject();
      } catch (ClassNotFoundException | IOException ex) {
         throw new SerializationException(ex);
      }
   }

   public static <T> T deserialize(byte[] objectData) {
      Validate.notNull(objectData, "The byte[] must not be null");
      return deserialize(new ByteArrayInputStream(objectData));
   }

   static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
      private static final Map<String, Class<?>> primitiveTypes = new HashMap<>();
      private final ClassLoader classLoader;

      ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
         super(in);
         this.classLoader = classLoader;
      }

      @Override
      protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
         String name = desc.getName();

         try {
            return Class.forName(name, false, this.classLoader);
         } catch (ClassNotFoundException ex) {
            try {
               return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException cnfe) {
               Class<?> cls = primitiveTypes.get(name);
               if (cls != null) {
                  return cls;
               } else {
                  throw cnfe;
               }
            }
         }
      }

      static {
         primitiveTypes.put("byte", byte.class);
         primitiveTypes.put("short", short.class);
         primitiveTypes.put("int", int.class);
         primitiveTypes.put("long", long.class);
         primitiveTypes.put("float", float.class);
         primitiveTypes.put("double", double.class);
         primitiveTypes.put("boolean", boolean.class);
         primitiveTypes.put("char", char.class);
         primitiveTypes.put("void", void.class);
      }
   }
}
