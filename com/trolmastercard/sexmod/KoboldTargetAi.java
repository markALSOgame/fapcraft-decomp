package com.trolmastercard.sexmod;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;

public class KoboldTargetAi extends EntityAINearestAttackableTarget<KoboldNpc> {
   private final int TargetInterval;
   private final boolean RequiresDarkness;

   public KoboldTargetAi(EntityCreature creature, boolean flag, boolean flag2) {
      this(creature, flag, false, flag2);
   }

   public KoboldTargetAi(EntityCreature creature, boolean flag, boolean flag2, boolean flag3) {
      this(creature, 10, flag, flag2, null, flag3);
   }

   public KoboldTargetAi(EntityCreature creature, int i, boolean flag, boolean flag2, @Nullable Predicate predicate, boolean flag3) {
      super(creature, KoboldNpc.class, i, flag, flag2, predicate);
      this.TargetInterval = i;
      this.RequiresDarkness = flag3;
   }


   public boolean shouldExecute() {
        block17: {
            if (this.RequiresDarkness) {
                float f = this.taskOwner.getBrightness();
                try {
                    if (f >= 0.5f) {
                        return false;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldTargetAi.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (this.TargetInterval <= 0 || this.taskOwner.getRNG().nextInt(this.TargetInterval) == 0) break block17;
                }
                catch (RuntimeException runtimeException) {
                    throw KoboldTargetAi.rethrow(runtimeException);
                }
                return false;
            }
            catch (RuntimeException runtimeException) {
                throw KoboldTargetAi.rethrow(runtimeException);
            }
        }
        List<KoboldNpc> list = this.taskOwner.world.getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);
        try {
            if (list.isEmpty()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldTargetAi.rethrow(runtimeException);
        }
        ArrayList<KoboldNpc> arrayList = new ArrayList<KoboldNpc>();
        for (KoboldNpc kobold : list) {
            try {
                if (!kobold.J()) continue;
                arrayList.add(kobold);
            }
            catch (RuntimeException runtimeException) {
                throw KoboldTargetAi.rethrow(runtimeException);
            }
        }
        try {
            if (arrayList.isEmpty()) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw KoboldTargetAi.rethrow(runtimeException);
        }
        arrayList.sort(this.sorter);
        this.targetEntity = arrayList.get(0);
        return true;
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
