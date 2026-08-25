package com.trolmastercard.sexmod;

import java.util.UUID;
import javax.annotation.Nullable;

public interface GirlMaster {
   @Nullable
   UUID getGirlUuid();

   void a(UUID uuid);

   int getPickupCountdown();

   void c(int i);

   int getThrowCounter();

   void a(int i2);

   int getThrowTicks();

   void a(GirlAnimationState girlAnimationState);

   GirlAnimationState getShadowAction();

   void setPickupCountdown(int i3);
}
