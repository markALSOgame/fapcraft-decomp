package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.UUID;

public class GirlTracker {
   static HashMap<UUID, GirlEntity> a = new HashMap<>();

   public static void trackGirl(GirlEntity girl) {
      a.put(girl.getGirlUuid(), girl);
   }

   public static void untrackGirl(GirlEntity girl) {
      a.remove(girl.getGirlUuid());
   }

   public static void clearAll() {
      a.clear();
   }

   public static GirlEntity getByUuid(UUID uuid) {
      return a.get(uuid);
   }
}
