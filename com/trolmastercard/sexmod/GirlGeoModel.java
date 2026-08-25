package com.trolmastercard.sexmod;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.core.controller.AnimationController;

public abstract class GirlGeoModel<T extends GirlEntity> extends GirlAnimatedGeoModel<T> implements GirlBoneList {
   public static final List<String> BraStringBones = Arrays.asList(
      "braStringMidStartR",
      "braStringMidMid1R",
      "braStringMidMid2R",
      "braStringMidMid3R",
      "braStringMidEndR",
      "braStringBackR",
      "braStringRightEndR",
      "braStringRightStartR",
      "braStringRightL",
      "braStringMidMid1L",
      "braStringMidMid2L",
      "braStringMidMid3L",
      "braStringMidEndL",
      "braStringBackL",
      "braStringLeftEndL",
      "braStringLeftStartL",
      "braStringMidStartL",
      "braStringRightR"
   );
   public static final List<String> CamBones = Arrays.asList("boyCam", "girlCam");
   public static boolean RenderBraStrings = true;
   protected ResourceLocation[] TextureLayers = this.a();
   protected Minecraft Mc = Minecraft.getMinecraft();

   protected GirlGeoModel() {
   }

   protected abstract ResourceLocation[] a();

   public ResourceLocation getSkinLocation() {
      return null;
   }

   @Override
   public ResourceLocation getTextureLocation(T girl) {
      return this.a(girl);
   }

   @Override
   public ResourceLocation getModelLocation(T girl) {
      return this.TextureLayers[0];
   }

   public ResourceLocation a(GirlEntity girl) {
      try {
         if (girl.world instanceof PreviewWorld) {
            return this.TextureLayers[0];
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if ((Integer)girl.getDataManager().get(GirlEntity.OutfitIndexKey) > this.TextureLayers.length) {
            System.out.println("Girl doesn't have an outfit Nr." + girl.getDataManager().get(GirlEntity.OutfitIndexKey) + " so im just making her nude lol");
            return this.TextureLayers[0];
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return this.TextureLayers[girl.getDataManager().get(GirlEntity.OutfitIndexKey)];
   }

   public ResourceLocation g(GirlEntity girl) {
      return this.getSkinLocation();
   }

   public void setMolangQueries(IAnimatable iAnimatable, double d) {
      try {
         if (Minecraft.getMinecraft().world != null) {
            super.setMolangQueries(iAnimatable, d);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }


   @Override
   public void setLivingAnimations(T t, Integer i, AnimationEvent animEvent) {
        block24: {
            AnimationProcessor animationProcessor;
            block18: {
                double d;
                super.setLivingAnimations(t, i, animEvent);
                animationProcessor = this.getAnimationProcessor();
                try {
                    this.a(t, animationProcessor);
                    if (((GirlEntity)t).world instanceof PreviewWorld) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GirlGeoModel.rethrow(runtimeException);
                }
                try {
                    if (((Boolean)t.getDataManager().get(GirlEntity.BusyKey)).booleanValue()) {
                        t.setPositionAndRotationDirect(((GirlEntity)t).getTargetPos().x, ((GirlEntity)t).getTargetPos().y, ((GirlEntity)t).getTargetPos().z, ((GirlEntity)t).I().floatValue(), 0.0f, 3, true);
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GirlGeoModel.rethrow(runtimeException);
                }
                if (((GirlEntity)t).ActionController != null && !(((GirlEntity)t).world instanceof PreviewWorld)) {
                    GirlAnimationState girlAnimationState = ((GirlEntity)t).getCurrentAction();
                    d = girlAnimationState != null ? (double)girlAnimationState.transitionTick : 5.0;
                    ((GirlEntity)t).ActionController.transitionLengthTicks = d;
                }
            }
                try {
                    this.applyAnimationTransforms(t, animationProcessor, animEvent);
                }
                catch (RuntimeException runtimeException) {
                    throw GirlGeoModel.rethrow(runtimeException);
                }
                boolean flag = t instanceof InventoryGirlEntity && !((GirlEntity)t).isTracked();
                if (!flag || ((GirlEntity)t).getOutfitIndex() == 0) {
                    this.a(animationProcessor);
                    return;
                }
                this.a(animationProcessor, (ItemStack)((GirlEntity)t).DataManager.get(InventoryGirlEntity.HelmetKey), (ItemStack)((GirlEntity)t).DataManager.get(InventoryGirlEntity.ChestKey), (ItemStack)((GirlEntity)t).DataManager.get(InventoryGirlEntity.PantsKey), (ItemStack)((GirlEntity)t).DataManager.get(InventoryGirlEntity.BootsKey));
        }
    }

    public static Vec3d d(GirlEntity girl) {
      return a(new Vec3d(girl.lastTickPosX, girl.lastTickPosY, girl.lastTickPosZ), girl.getPositionVector());
   }

   public static Vec3d a(GirlEntity girl, Vec3d vec3d) {
      return a(vec3d, girl.getPositionVector());
   }


   public static Vec3d a(Vec3d vec3d, Vec3d vec3d2) {
        float f;
        float f2;
        int i;
        Vec3d vec3d3;
        Vec3d vec3d4;
        Vec3d vec3d5 = vec3d2.subtract(vec3d);
        Vec3d vec3d6 = new Vec3d(Math.abs(vec3d5.x), Math.abs(vec3d5.y), Math.abs(vec3d5.z));
        double d = vec3d6.x / (vec3d6.x + vec3d6.y + vec3d6.z);
        double d2 = vec3d6.y / (vec3d6.x + vec3d6.y + vec3d6.z);
        double d3 = vec3d6.z / (vec3d6.x + vec3d6.y + vec3d6.z);
        try {
            i = vec3d5.x > 0.0 ? 1 : -1;
        }
        catch (RuntimeException runtimeException) {
            throw GirlGeoModel.rethrow(runtimeException);
        }
        vec3d3 = new Vec3d((double)i * d, (double)(vec3d5.y > 0.0 ? 1 : -1) * d2, (double)(vec3d5.z > 0.0 ? 1 : -1) * d3);
        Vec3d vec3d8 = vec3d3;
        double d4 = vec3d8.y / 2.0 + 0.5;
        float f3 = (float)LerpMath.lerp(-180.0, 0.0, d4);
        if (Float.isNaN(f3)) {
            f3 = -90.0f;
        }
        try {
            float f4 = f2 = d4 < 0.5 ? 0.0f : (float)LerpMath.lerp(0.0, 16.0, -d4);
        }
        catch (RuntimeException runtimeException) {
            throw GirlGeoModel.rethrow(runtimeException);
        }
        if (Float.isNaN(f2)) {
            f2 = 0.0f;
        }
        if (Float.isNaN(f = (float)(4.0 - Math.sin(1.5707963267948966 + d4 * 2.0 * Math.PI) * 4.0))) {
            f = 8.0f;
        }
        return new Vec3d((double)AngleMath.degToRadians(f3), (double)f2, (double)f);
    }

   void a(AnimationProcessor<T> animationProcessor, ItemStack stack, ItemStack stack2, ItemStack stack3, ItemStack stack4) {
      GirlGeoModel girlGeoModel;
      AnimationProcessor animationProcessor2;
      boolean flag;
      label40: {
         try {
            girlGeoModel = this;
            animationProcessor2 = animationProcessor;
            if (!stack.isEmpty()) {
               flag = true;
               break label40;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag = false;
      }

      label33: {
         try {
            girlGeoModel.c(animationProcessor2, flag);
            this.b(animationProcessor, stack2.getItem() instanceof ItemArmor);
            girlGeoModel = this;
            animationProcessor2 = animationProcessor;
            if (!stack3.isEmpty()) {
               flag = true;
               break label33;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         flag = false;
      }

      label26: {
         try {
            girlGeoModel.d(animationProcessor2, flag);
            girlGeoModel = this;
            animationProcessor2 = animationProcessor;
            if (!stack4.isEmpty()) {
               flag = true;
               break label26;
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }

         flag = false;
      }

      girlGeoModel.a(animationProcessor2, flag);
   }

   protected void a(AnimationProcessor<T> animationProcessor) {
      this.c(animationProcessor, false);
      this.b(animationProcessor, false);
      this.d(animationProcessor, false);
      this.a(animationProcessor, false);
   }

   void c(AnimationProcessor animationProcessor, boolean flag) {
      GirlGeoModel girlGeoModel;
      String[] stringArray;
      boolean flag2;
      label16: {
         try {
            this.a(this.getHelmetBones(), flag, animationProcessor);
            girlGeoModel = this;
            stringArray = this.getHeadAccessoryBones();
            if (!flag) {
               flag2 = true;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag2 = false;
      }

      girlGeoModel.a(stringArray, flag2, animationProcessor);
   }

   void b(AnimationProcessor<T> animationProcessor, boolean flag) {
      GirlGeoModel girlGeoModel;
      String[] stringArray;
      boolean flag2;
      label16: {
         try {
            this.a(this.getChestArmorBones(), flag, animationProcessor);
            girlGeoModel = this;
            stringArray = this.getFleshTorsoBones();
            if (!flag) {
               flag2 = true;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag2 = false;
      }

      girlGeoModel.a(stringArray, flag2, animationProcessor);
   }

   void d(AnimationProcessor<T> animationProcessor, boolean flag) {
      GirlGeoModel girlGeoModel;
      String[] stringArray;
      boolean flag2;
      label16: {
         try {
            this.a(this.getPantsArmorBones(), flag, animationProcessor);
            girlGeoModel = this;
            stringArray = this.getFleshLegsBones();
            if (!flag) {
               flag2 = true;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag2 = false;
      }

      girlGeoModel.a(stringArray, flag2, animationProcessor);
   }

   void a(AnimationProcessor<T> animationProcessor, boolean flag) {
      GirlGeoModel girlGeoModel;
      String[] stringArray;
      boolean flag2;
      label16: {
         try {
            this.a(this.getBootsArmorBones(), flag, animationProcessor);
            girlGeoModel = this;
            stringArray = this.getFleshFeetBones();
            if (!flag) {
               flag2 = true;
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         flag2 = false;
      }

      girlGeoModel.a(stringArray, flag2, animationProcessor);
   }

   void a(String[] stringArray, boolean flag, AnimationProcessor<T> animationProcessor) {
      for (String string : stringArray) {
         this.a(string, flag, animationProcessor);
      }
   }

   void a(String string, boolean flag, AnimationProcessor<T> animationProcessor) {
      try {
         if (animationProcessor.getBone(string) == null) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      IBone iBone;
      boolean flag2;
      label22: {
         try {
            iBone = animationProcessor.getBone(string);
            if (!flag) {
               flag2 = true;
               break label22;
            }
         } catch (RuntimeException error2) {
            throw rethrow(error2);
         }

         flag2 = false;
      }

      iBone.setHidden(flag2);
   }

   protected boolean isDefaultSkin(T t) {
      UUID uuid = t.getSexPlayerUuid();

      try {
         if (uuid == null) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      World world2 = t.world;
      AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)world2.getPlayerEntityByUUID(uuid);

      try {
         if (abstractClientPlayer == null) {
            return true;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      return "default".equals(abstractClientPlayer.getSkinType());
   }


   void a(T t, AnimationProcessor<T> animationProcessor) {
        block12: {
            boolean flag;
            IBone iBone;
            block14: {
                block13: {
                    boolean flag2;
                    IBone iBone2;
                    boolean flag3;
                    IBone iBone3;
                    boolean flag4;
                    IBone iBone4;
                    boolean flag5;
                    IBone iBone5;
                    boolean flag6 = this.isDefaultSkin(t);
                    try {
                        animationProcessor.getBone("rightArmAlex").setHidden(flag6);
                        animationProcessor.getBone("rightLowerArmAlex").setHidden(flag6);
                        iBone5 = animationProcessor.getBone("rightArmSteve");
                        flag5 = !flag6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlGeoModel.rethrow(runtimeException);
                    }
                    try {
                        iBone5.setHidden(flag5);
                        iBone4 = animationProcessor.getBone("rightLowerArmSteve");
                        flag4 = !flag6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlGeoModel.rethrow(runtimeException);
                    }
                    try {
                        iBone4.setHidden(flag4);
                        animationProcessor.getBone("leftArmAlex").setHidden(flag6);
                        animationProcessor.getBone("leftLowerArmAlex").setHidden(flag6);
                        iBone3 = animationProcessor.getBone("leftArmSteve");
                        flag3 = !flag6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlGeoModel.rethrow(runtimeException);
                    }
                    try {
                        iBone3.setHidden(flag3);
                        iBone2 = animationProcessor.getBone("leftLowerArmSteve");
                        flag2 = !flag6;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlGeoModel.rethrow(runtimeException);
                    }
                    iBone2.setHidden(flag2);
                    IBone iBone6 = animationProcessor.getBone("steve");
                    try {
                        try {
                            if (iBone6 == null) break block12;
                            iBone = iBone6;
                            if (((GirlEntity)t).getCurrentAction().hasPlayer) break block13;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GirlGeoModel.rethrow(runtimeException);
                        }
                        flag = true;
                        break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlGeoModel.rethrow(runtimeException);
                    }
                }
                flag = false;
            }
            iBone.setHidden(flag);
        }
    }

   protected boolean e(T t) {
      return true;
   }


   protected void applyAnimationTransforms(T t, AnimationProcessor<T> animationProcessor, AnimationEvent animEvent) {
        IBone iBone;
        block14: {
            try {
                if (((GirlEntity)t).world instanceof PreviewWorld) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlGeoModel.rethrow(runtimeException);
            }
            try {
                if (!this.e(t)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GirlGeoModel.rethrow(runtimeException);
            }
            try {
                try {
                    try {
                        if (((GirlEntity)t).getCurrentAction() == GirlAnimationState.NULL || ((GirlEntity)t).getCurrentAction() == GirlAnimationState.ATTACK) break block14;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GirlGeoModel.rethrow(runtimeException);
                    }
                    if (((GirlEntity)t).getCurrentAction() == GirlAnimationState.BOW) break block14;
                }
                catch (RuntimeException runtimeException) {
                    throw GirlGeoModel.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GirlGeoModel.rethrow(runtimeException);
            }
        }
        EntityModelData entityModelData = (EntityModelData)animEvent.getExtraDataOfType(EntityModelData.class).get(0);
        IBone iBone2 = animationProcessor.getBone("neck");
        iBone2.setRotationY(entityModelData.netHeadYaw * 0.5f * ((float)Math.PI / 180));
        IBone iBone3 = animationProcessor.getBone("head");
        try {
            iBone3.setRotationY(entityModelData.netHeadYaw * ((float)Math.PI / 180));
            iBone3.setRotationX(entityModelData.headPitch * ((float)Math.PI / 180));
            iBone = animationProcessor.getBone("body") == null ? animationProcessor.getBone("dd") : animationProcessor.getBone("body");
        }
        catch (RuntimeException runtimeException) {
            throw GirlGeoModel.rethrow(runtimeException);
        }
        IBone iBone4 = iBone;
        iBone4.setRotationY(0.0f);
    }

   public ItemStack a(GirlEntity girl, String string) {
      try {
         if (Arrays.asList(this.getHelmetBones()).contains(string)) {
            return (ItemStack)girl.DataManager.get(InventoryGirlEntity.HelmetKey);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (Arrays.asList(this.getChestArmorBones()).contains(string)) {
            return (ItemStack)girl.DataManager.get(InventoryGirlEntity.ChestKey);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (Arrays.asList(this.getPantsArmorBones()).contains(string)) {
            return (ItemStack)girl.DataManager.get(InventoryGirlEntity.PantsKey);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      try {
         if (Arrays.asList(this.getBootsArmorBones()).contains(string)) {
            return (ItemStack)girl.DataManager.get(InventoryGirlEntity.BootsKey);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      return ItemStack.EMPTY;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
