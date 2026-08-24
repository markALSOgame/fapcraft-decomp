package com.trolmastercard.sexmod;

import net.minecraft.util.math.Vec3d;

@FunctionalInterface
public interface GirlAnchor {
   Vec3d getAnchorPos(GirlEntity girl);
}
