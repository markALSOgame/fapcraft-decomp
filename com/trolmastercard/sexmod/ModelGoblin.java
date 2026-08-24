package com.trolmastercard.sexmod;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;

public class ModelGoblin extends GirlGeoModel {
   final float g = 60.0F;
   Minecraft Mc = Minecraft.getMinecraft();

   @Override
   protected ResourceLocation[] a() {
      return new ResourceLocation[]{
         new ResourceLocation("sexmod", "geo/goblin/goblin.geo.json"), new ResourceLocation("sexmod", "geo/goblin/armored.geo.json")
      };
   }

   @Override
   public ResourceLocation b() {
      return new ResourceLocation("sexmod", "textures/entity/goblin/goblin.png");
   }

   @Override
   public ResourceLocation b(GirlEntity girl) {
      return new ResourceLocation("sexmod", "animations/goblin/goblin.animation.json");
   }

   @Override
   protected boolean isDefaultSkin(GirlEntity girl) {
      try {
         if (!(girl instanceof GoblinNpc)) {
            return super.isDefaultSkin(girl);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      GoblinNpc goblin = (GoblinNpc)girl;
      UUID uuid = goblin.getSexPlayerUuid();
      if (uuid == null) {
         uuid = goblin.e();
      }

      try {
         if (uuid == null) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      World world2 = goblin.world;
      AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)world2.getPlayerEntityByUUID(uuid);

      try {
         if (abstractClientPlayer == null) {
            return true;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      return "default".equals(abstractClientPlayer.getSkinType());
   }

   @Override

   public void handleAnimations(GirlEntity girl, Integer i, AnimationEvent animEvent) {
        boolean flag;
        AnimationProcessor animationProcessor;
        block48: {
            GirlMaster girlMaster;
            GirlAnimationState girlAnimationState;
            block47: {
                IBone iBone;
                block46: {
                    block45: {
                        block43: {
                            block44: {
                                block42: {
                                    IBone iBone2;
                                    block41: {
                                        block39: {
                                            boolean flag2;
                                            IBone iBone3;
                                            try {
                                                super.handleAnimations(girl, i, animEvent);
                                                if (girl.world instanceof PreviewWorld) {
                                                    return;
                                                }
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw ModelGoblin.rethrow(runtimeException);
                                            }
                                            animationProcessor = this.getAnimationProcessor();
                                            flag = girl instanceof GoblinNpc;
                                            IBone iBone4 = animationProcessor.getBone("preggy");
                                            try {
                                                iBone3 = iBone4;
                                                flag2 = (Boolean)girl.getDataManager().get(GoblinNpc.PregnantKey) == false;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw ModelGoblin.rethrow(runtimeException);
                                            }
                                            iBone3.setHidden(flag2);
                                            iBone = animationProcessor.getBone("body");
                                            iBone2 = animationProcessor.getBone("head");
                                            girlAnimationState = girl.getCurrentAction();
                                            try {
                                                try {
                                                    block38: {
                                                        try {
                                                            try {
                                                                if (girlAnimationState == GirlAnimationState.BREEDING_SLOW_2 || girlAnimationState == GirlAnimationState.BREEDING_FAST_2) break block38;
                                                            }
                                                            catch (RuntimeException runtimeException) {
                                                                throw ModelGoblin.rethrow(runtimeException);
                                                            }
                                                            if (girlAnimationState != GirlAnimationState.BREEDING_CUM_2) break block39;
                                                        }
                                                        catch (RuntimeException runtimeException) {
                                                            throw ModelGoblin.rethrow(runtimeException);
                                                        }
                                                    }
                                                    if (this.Mc.gameSettings.thirdPersonView != 0) break block39;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw ModelGoblin.rethrow(runtimeException);
                                                }
                                                iBone.setPositionY(iBone.getPositionY() + 1.5f);
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw ModelGoblin.rethrow(runtimeException);
                                            }
                                        }
                                        girlMaster = (GirlMaster)((Object)girl);
                                        try {
                                            block40: {
                                                try {
                                                    try {
                                                        if (flag && girlAnimationState == GirlAnimationState.AWAIT_PICK_UP) break block40;
                                                    }
                                                    catch (RuntimeException runtimeException) {
                                                        throw ModelGoblin.rethrow(runtimeException);
                                                    }
                                                    if (girlAnimationState != GirlAnimationState.VANISH) break block41;
                                                }
                                                catch (RuntimeException runtimeException) {
                                                    throw ModelGoblin.rethrow(runtimeException);
                                                }
                                            }
                                            this.faceNearestPlayer(girl, iBone, iBone2);
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw ModelGoblin.rethrow(runtimeException);
                                        }
                                    }
                                    try {
                                        try {
                                            if (!flag || girlAnimationState != GirlAnimationState.SIT) break block42;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw ModelGoblin.rethrow(runtimeException);
                                        }
                                        this.a(girl, iBone2);
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ModelGoblin.rethrow(runtimeException);
                                    }
                                }
                                try {
                                    try {
                                        if (girlAnimationState != GirlAnimationState.START_THROWING) break block43;
                                        if (!this.Mc.player.getPersistentID().equals(girlMaster.getGirlUuid())) break block44;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw ModelGoblin.rethrow(runtimeException);
                                    }
                                    this.applyBoneVisibilityForOwner(iBone, animationProcessor, girl, girlMaster);
                                    break block45;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw ModelGoblin.rethrow(runtimeException);
                                }
                            }
                            this.applyBoneVisibility(iBone, animationProcessor, girl);
                            break block45;
                        }
                        iBone.setHidden(false);
                    }
                    try {
                        try {
                            if (!iBone.isHidden() && girlAnimationState == GirlAnimationState.START_THROWING) break block46;
                        }
                        catch (RuntimeException runtimeException) {
                            throw ModelGoblin.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.THROWN) break block47;
                    }
                    catch (RuntimeException runtimeException) {
                        throw ModelGoblin.rethrow(runtimeException);
                    }
                }
                Vec3d vec3d = ModelGoblin.getMeatFootBoneNames(girl);
                iBone.setRotationX((float)vec3d.x);
                iBone.setPositionY((float)vec3d.y);
                iBone.setPositionZ((float)vec3d.z);
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.START_THROWING && girlAnimationState != GirlAnimationState.PICK_UP) break block48;
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGoblin.rethrow(runtimeException);
                }
                this.animateLegs(animationProcessor, girlMaster, girl);
            }
            catch (RuntimeException runtimeException) {
                throw ModelGoblin.rethrow(runtimeException);
            }
        }
        try {
            if (!flag) {
                this.lowerBodyForPickUp(animationProcessor, girl);
                this.hideBodyDuringThrow(animationProcessor, girl);
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGoblin.rethrow(runtimeException);
        }
    }


   void hideBodyDuringThrow(AnimationProcessor animationProcessor, GirlEntity girl) {
        block10: {
            try {
                if (girl.getCurrentAction() != GirlAnimationState.START_THROWING) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ModelGoblin.rethrow(runtimeException);
            }
            try {
                try {
                    if (this.Mc.gameSettings.thirdPersonView == 0 && this.Mc.player.getPersistentID().equals(((PlayerGirlEntity)girl).m())) break block10;
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGoblin.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ModelGoblin.rethrow(runtimeException);
            }
        }
        IBone iBone = animationProcessor.getBone("body");
        try {
            if (iBone == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGoblin.rethrow(runtimeException);
        }
        iBone.setHidden(true);
    }


   void lowerBodyForPickUp(AnimationProcessor animationProcessor, GirlEntity girl) {
        block13: {
            try {
                if (girl.getCurrentAction() != GirlAnimationState.PICK_UP) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw ModelGoblin.rethrow(runtimeException);
            }
            try {
                try {
                    if (this.Mc.gameSettings.thirdPersonView != 0 || !this.Mc.player.getPersistentID().equals(((GirlMaster)((Object)girl)).e())) break block13;
                }
                catch (RuntimeException runtimeException) {
                    throw ModelGoblin.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw ModelGoblin.rethrow(runtimeException);
            }
        }
        IBone iBone = animationProcessor.getBone("body");
        try {
            if (iBone == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGoblin.rethrow(runtimeException);
        }
        IBone iBone2 = animationProcessor.getBone("steve");
        try {
            if (iBone2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw ModelGoblin.rethrow(runtimeException);
        }
        iBone.setPositionY(iBone.getPositionY() - 32.0f);
        iBone2.setPositionY(iBone2.getPositionY() - 32.0f);
    }

   void animateLegs(AnimationProcessor animationProcessor, GirlMaster girlMaster, GirlEntity girl) {
      UUID uuid = girlMaster.getGirlUuid();

      try {
         if (uuid == null) {
            girl.getSexPlayerUuid();
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (uuid == null) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player = girl.world.getPlayerEntityByUUID(uuid);

      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      float f = LerpMath.lerp(player.prevLimbSwingAmount, player.limbSwingAmount, this.Mc.getRenderPartialTicks());
      float f2 = player.limbSwing;
      float f3 = (float)Math.sin(f2);
      IBone iBone = animationProcessor.getBone("LeftLeg");
      IBone iBone2 = animationProcessor.getBone("RightLeg");
      float f4 = AngleMath.degToRadians(60.0F * f3 * f);
      iBone.setRotationX(f4);
      iBone2.setRotationX(-f4);
   }


   void a(GirlEntity girl, IBone iBone) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 7[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

   void faceNearestPlayer(GirlEntity girl, IBone iBone, IBone iBone2) {
      EntityPlayer player = girl.world.getClosestPlayerToEntity(girl, 15.0);

      try {
         if (player == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      Vec3d vec3d = player.getPositionVector();
      Vec3d vec3d2 = girl.getPositionVector();
      Vec3d vec3d3 = vec3d.subtract(vec3d2);
      float f = (float)(-(Math.atan2(vec3d3.z, vec3d3.x) * (180.0 / Math.PI))) + 90.0F;
      float f2 = MathUtils.clamp((float)(player.getEyeHeight() + vec3d.y - (girl.getEyeHeight() + vec3d2.y)), -0.75F, 0.75F);
      iBone.setRotationY(AngleMath.degToRadians(f));
      iBone2.setRotationX(f2);
   }

   void applyBoneVisibility(IBone iBone, AnimationProcessor animationProcessor, GirlEntity girl) {
      try {
         if (girl.isTracked()) {
            iBone.setHidden(true);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      iBone.setHidden(false);
      animationProcessor.getBone("steve").setHidden(true);
   }

   void applyBoneVisibilityForOwner(IBone iBone, AnimationProcessor animationProcessor, GirlEntity girl, GirlMaster girlMaster) {
      label38: {
         try {
            if (girl.isTracked()) {
               iBone.setHidden(true);
               break label38;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         IBone iBone2;
         boolean flag;
         label29: {
            try {
               iBone2 = iBone;
               if (girlMaster.a() < 15) {
                  flag = true;
                  break label29;
               }
            } catch (RuntimeException error2) {
               throw rethrow(error2);
            }

            flag = false;
         }

         iBone2.setHidden(flag);
      }

      try {
         if (!girl.isTracked()) {
            animationProcessor.getBone("steve").setHidden(true);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }
   }

   @Override
   public String[] getHelmetBoneNames() {
      return new String[]{"armorHelmet"};
   }

   @Override
   public String[] getBoobArmorBoneNames() {
      return new String[]{"armorBoobL", "armorBoobR"};
   }

   @Override
   public String[] a() {
      return new String[]{"nippleL", "nippleR"};
   }

   @Override
   public String[] getLowerArmorBoneNames() {
      return new String[]{"armorCheekR", "armorCheekL", "armorLegL", "armorLegR", "armorShinL", "armorShinR", "armorTorso"};
   }

   @Override
   public String[] getMeatBoneNames() {
      return new String[]{"fuckhole", "vagina", "meatCheekR", "meatCheekL", "meatLegL", "meatLegR", "meatShinL", "meatShinR"};
   }

   @Override
   public String[] b() {
      return new String[]{"armorFootL", "armorFootR"};
   }

   @Override
   public String[] getMeatFootBoneNames() {
      return new String[]{"meatFootL", "meatFootR"};
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
