package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class BiMap<K, V> {
   private final HashMap<K, V> ForwardMap = new HashMap<>();
   private final HashMap<V, K> ReverseMap = new HashMap<>();

   public void put(K k, V v) {
      Object obj = this.ForwardMap.put((K)k, (V)v);
      this.ReverseMap.remove(obj);
      this.ReverseMap.put((V)v, (K)k);
   }

   public V get(K k) {
      return this.ForwardMap.get(k);
   }

   public K getByValue(V v) {
      return this.ReverseMap.get(v);
   }

   public int size() {
      return this.ForwardMap.size();
   }

   public void remove(K k) {
      Object obj = this.ForwardMap.get(k);

      try {
         if (obj != null) {
            this.ForwardMap.remove(k);
            this.ReverseMap.remove(obj);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   public Set<Entry<K, V>> entrySet() {
      return this.ForwardMap.entrySet();
   }

   public Set<K> a() {
      return this.ForwardMap.keySet();
   }

   public Set<V> values() {
      return this.ReverseMap.keySet();
   }

   public void clear() {
      this.ReverseMap.clear();
      this.ForwardMap.clear();
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
