package com.trolmastercard.sexmod;

public interface BoxSource {
   Vec4d getPoseOffsets();

   boolean shouldHideBody();

   boolean isVisible();

   boolean hasMangleCompanion();
}
