package com.trolmastercard.sexmod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoblinNpcRenderer extends ScaledGirlGeoRenderer<GoblinNpc> {
   static final Vec3i DefaultColor = new Vec3i(255, 255, 255);
   static final float K = -420.69F;
   static final float A = 8.0F;
   static final float L = 3.0F;
   static final Vec3d G = new Vec3d(10.0, -20.0, -10.0);
   static final float J = 0.1F;
   static final HashSet<String> MeatParts = new HashSet<>(
      Arrays.asList(
         "meatTorso",
         "meatCheekR",
         "meatCheekL",
         "meatFootR",
         "meatFootL",
         "meatShinR",
         "meatShinL",
         "meatLegL",
         "meatLegR",
         "nippleR",
         "nippleL",
         "preggy",
         "shoeL",
         "shoeR",
         "frontAndInside",
         "Lside",
         "Rside",
         "cheekR",
         "cheekL",
         "fuckhole",
         "head",
         "nose",
         "neck",
         "armL",
         "lowerArmL",
         "armR",
         "lowerArmR",
         "torso",
         "LegL",
         "LegR",
         "shinL",
         "shinR"
      )
   );
   static final HashSet<String> EyeBrowParts = new HashSet<>(Arrays.asList("lashR", "lashL", "closedR", "closedL", "browL", "browR", "closedL", "closedL"));
   static final HashSet<String> BoobLegParts = new HashSet<>(Arrays.asList("meatLegR", "meatShinR", "meatFootR", "boobR", "boobR1", "boobR2"));
   static Minecraft Mc;
   float LastPartialTicks = 0.0F;
   boolean Initialized = false;
   boolean F = false;
   static float B = 0.0F;
   float LightLevel = 0.0F;
   static float H = 0.0F;
   static float t = 0.0F;
   static float I = 0.0F;
   static float E = 0.0F;
   static float N = 0.0F;
   static float x = 0.0F;

   public GoblinNpcRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel, d);
      Mc = Minecraft.getMinecraft();
   }

   protected ResourceLocation d(GoblinNpc goblin) throws IOException {
      UUID uuid = goblin.getSexPlayerUuid();
      if (uuid == null) {
         uuid = goblin.getGirlUuid();
      }

      label43: {
         try {
            if (!(goblin.world instanceof PreviewWorld) && uuid != null) {
               break label43;
            }
         } catch (IOException error) {
            throw rethrow(error);
         }

         ResourceLocation location = BoyCamera.get(Mc.getSession().getProfile().getId());

         try {
            if (location == null) {
               return this.a(Mc.getSession().getProfile().getId(), goblin.world);
            }

            return location;
         } catch (IOException error2) {
            throw rethrow(error2);
         }
      }

      ResourceLocation location2 = BoyCamera.get(uuid);

      try {
         if (location2 == null) {
            return this.a(uuid, goblin.world);
         }
      } catch (IOException error3) {
         throw rethrow(error3);
      }

      return location2;
   }

   public static void renderGirlBox(GirlEntity girl, float f) {
      Mc.getRenderManager().renderEntity(girl, 0.0, 0.0, 0.0, -420.69F, f, false);
   }

   public static void rotateCameraToGirl(float f) {
      try {
         if (!(Mc.getRenderViewEntity() instanceof EntityPlayer)) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      EntityPlayer player2 = (EntityPlayer)Mc.getRenderViewEntity();
      float f2 = player2.distanceWalkedModified - player2.prevDistanceWalkedModified;
      float f3 = -(player2.distanceWalkedModified + f2 * f);
      float f4 = player2.prevCameraYaw + (player2.cameraYaw - player2.prevCameraYaw) * f;
      float f5 = MathHelper.sin(f3 * (float) Math.PI) * f4 * 0.5F;
      GlStateManager.translate(
         Math.cos(Mc.player.rotationYaw * (Math.PI / 180.0)) * f5,
         Math.abs(MathHelper.cos(f3 * (float) Math.PI) * f4),
         Math.sin(Mc.player.rotationYaw * (Math.PI / 180.0)) * f5
      );
   }

   public void render(GeoModel model, GoblinNpc goblin, float f, float f2, float f3, float f4, float f5) {
      super.render(model, goblin, f, f2, f3, f4, goblin.FadeAlpha);
   }


   public void doRenderShadowAndFire(Entity entity, double d, double d2, double d3, float f, float f2) {
        block7: {
            try {
                if (!(entity instanceof GoblinNpc)) {
                    super.doRenderShadowAndFire(entity, d, d2, d3, f, f2);
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
            GoblinNpc goblin = (GoblinNpc)entity;
            try {
                try {
                    if (goblin.getCurrentAction() != GirlAnimationState.PICK_UP && goblin.getCurrentAction() != GirlAnimationState.SHOULDER_IDLE) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        super.doRenderShadowAndFire(entity, d, d2, d3, f, f2);
    }

   public static Vec3d a(World world, GirlEntity girl, UUID uuid, double d, double d2, double d3) {
      try {
         if (world == null) {
            return new Vec3d(d, d2, d3);
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (uuid == null) {
            return new Vec3d(d, d2, d3);
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      try {
         if (girl == null) {
            return new Vec3d(d, d2, d3);
         }
      } catch (RuntimeException error3) {
         throw rethrow(error3);
      }

      EntityPlayer player2 = world.getPlayerEntityByUUID(uuid);

      try {
         if (player2 == null) {
            return new Vec3d(d, d2, d3);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }

      Vec3d vec3d = player2.getPositionVector();
      Vec3d vec3d2 = Mc.player.getPositionVector();
      girl.prevRenderYawOffset = player2.prevRotationYawHead;
      girl.renderYawOffset = player2.rotationYawHead;
      girl.setCurrentAction(GirlAnimationState.START_THROWING);
      return vec3d.subtract(vec3d2);
   }


   public void doRender(GoblinNpc goblin, double d, double d2, double d3, float f, float f2) {
      this.RenderEntity = goblin;
      this.Initialized = -420.69F == f && goblin.getCurrentAction() == GirlAnimationState.SHOULDER_IDLE;
      this.F = -420.69F == f && goblin.getCurrentAction() == GirlAnimationState.PICK_UP;
      this.LightLevel = goblin.world.getLight(goblin.getPosition(), true);
      this.LastPartialTicks = f2;
      B = f;
      GirlAnimationState girlAnimationState = goblin.getCurrentAction();
      UUID uuid = goblin.getGirlUuid();

      if (goblin.isTracked()) {
         Vec3d vec3d = GoblinNpcRenderer.a(goblin.world, goblin, uuid, d, d2, d3);
         d = vec3d.x;
         d2 = vec3d.y;
         d3 = vec3d.z;
      }

      if (girlAnimationState == GirlAnimationState.THROWN || girlAnimationState == GirlAnimationState.START_THROWING) {
         if (Mc.gameSettings.thirdPersonView == 0 && f == -420.69F && !goblin.isTracked()) {
            return;
         }

         if (!goblin.isTracked()) {
            float f3 = goblin.I().floatValue();
            goblin.prevRenderYawOffset = f3;
            goblin.renderYawOffset = f3;
         }
      }

      if (isInCatchThrowAnimation(goblin, girlAnimationState)) {
         if (Mc.player.getPersistentID().equals(uuid)) {
            if (-420.69F != f) {
               return;
            }

            goblin.renderYawOffset = Mc.player.rotationYaw + 180.0F;
            goblin.prevRenderYawOffset = Mc.player.rotationYaw + 180.0F;
            Vec3d lookVec = Mc.player.getLookVec();
            GlStateManager.pushMatrix();
            GlStateManager.translate(lookVec.x, lookVec.y + (double)Mc.player.getEyeHeight(), lookVec.z);
            Vec3d vec3d2 = GoblinNpc.rotateVec3dYaw(new Vec3d(-Math.abs(Mc.player.rotationPitch), 0.0, 0.0), Mc.player.rotationYaw);
            GlStateManager.rotate(Mc.player.rotationPitch, (float)vec3d2.x, 0.0F, (float)vec3d2.z);
            d = 0.0;
            d2 = 0.0;
            d3 = 0.0;
         } else {
            if (!goblin.isTracked() || uuid == null || Mc.player.getPersistentID().equals(uuid)) {
               if (uuid != null && !Mc.player.getPersistentID().equals(uuid)) {
                  EntityPlayer player2 = goblin.world.getPlayerEntityByUUID(uuid);
                  if (player2 != null) {
                     goblin.renderYawOffset = player2.rotationYaw;
                     goblin.prevRenderYawOffset = player2.rotationYaw;
                  }
               } else {
                  goblin.renderYawOffset = Mc.player.rotationYaw;
                  goblin.prevRenderYawOffset = Mc.player.rotationYaw;
               }
            }

            Vec3d vec3d3 = GoblinNpcRenderer.getThrowAimOffset(goblin, uuid, f2);
            d = vec3d3.x;
            d2 = vec3d3.y;
            d3 = vec3d3.z;
         }
      } else if (this.Initialized) {
         rotateCameraToGirl(f2);
         Vec3d vec3d4 = new Vec3d(LerpMath.lerp(-0.1F, 0.2F, Mc.gameSettings.fovSetting / 110.0F), 0.0, 0.0);
         vec3d4 = GoblinNpc.rotateVec3dYaw(vec3d4, Mc.player.rotationYaw);
         d = vec3d4.x;
         d2 = vec3d4.y;
         d3 = vec3d4.z;
         goblin.renderYawOffset = Mc.player.rotationYaw;
         goblin.prevRenderYawOffset = Mc.player.prevRotationYaw;

         if (Mc.player.isSneaking()) {
            d2 -= 0.075;
         }
      } else if (girlAnimationState == GirlAnimationState.SHOULDER_IDLE) {
         if (uuid == null) {
            return;
         }

         if (Mc.player.getPersistentID().equals(uuid) && Mc.gameSettings.thirdPersonView == 0) {
            return;
         }

         EntityPlayer player3 = goblin.world.getPlayerEntityByUUID(uuid);

         if (player3 == null) {
            return;
         }

         Vector4f vector4f = getShoulderOffset(player3, f2);
         d = vector4f.x;
         d2 = vector4f.y;
         d3 = vector4f.z;
         goblin.renderYawOffset = vector4f.w;

         if (player3.isSneaking()) {
            d2 -= 0.32;
         }
      } else if (girlAnimationState == GirlAnimationState.PICK_UP && uuid != null) {
         EntityPlayer player4 = goblin.world.getPlayerEntityByUUID(uuid);

         if (player4 != null) {
            goblin.prevRenderYawOffset = player4.prevRotationYawHead;
            goblin.renderYawOffset = player4.rotationYawHead;
         }
      }

      super.doRender(goblin, d, d2, d3, f, f2);

      if (isInCatchThrowAnimation(goblin, girlAnimationState) && Mc.gameSettings.thirdPersonView == 0 && Mc.player.getPersistentID().equals(uuid)) {
         GlStateManager.popMatrix();
      }
    }


   public static boolean isInCatchThrowAnimation(GirlEntity girl, GirlAnimationState girlAnimationState) {
        block16: {
            block15: {
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.START_THROWING || girl.isTracked()) break block15;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinNpcRenderer.rethrow(runtimeException);
                    }
                    return false;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
            }
            try {
                block17: {
                    try {
                        try {
                            if (GoblinNpcRenderer.Mc.gameSettings.thirdPersonView == 0) break block16;
                            if (girlAnimationState == GirlAnimationState.START_THROWING) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GoblinNpcRenderer.rethrow(runtimeException);
                        }
                        if (girlAnimationState != GirlAnimationState.PICK_UP) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinNpcRenderer.rethrow(runtimeException);
                    }
                }
                return false;
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        try {
            switch (girlAnimationState) {
                case PICK_UP: 
                case CATCH: 
                case CATCH_BJ: 
                case CATCH_BJ_IDLE: 
                case START_THROWING: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinNpcRenderer.rethrow(runtimeException);
        }
    }

   public static Vec3d getThrowAimOffset(GirlEntity girl, UUID uuid, float f) {
      try {
         if (uuid == null) {
            return Vec3d.ZERO;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      EntityPlayer player2 = girl.world.getPlayerEntityByUUID(uuid);

      try {
         if (player2 == null) {
            return Vec3d.ZERO;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      Vec3d vec3d = LerpMath.lerpVec3d(new Vec3d(player2.prevPosX, player2.prevPosY, player2.prevPosZ), player2.getPositionVector(), f);
      Vec3d vec3d2 = LerpMath.lerpVec3d(
         new Vec3d(Mc.player.prevPosX, Mc.player.prevPosY, Mc.player.prevPosZ), Mc.player.getPositionVector(), f
      );
      return vec3d.subtract(vec3d2);
   }

   public static Vector4f getShoulderOffset(EntityPlayer player2, float f) {
      EntityPlayerSP mcPlayer = Mc.player;
      float f2 = LerpMath.lerp(player2.prevRenderYawOffset, player2.renderYawOffset, f);
      Vec3d vec3d = LerpMath.lerpVec3d(new Vec3d(player2.lastTickPosX, player2.lastTickPosY, player2.lastTickPosZ), player2.getPositionVector(), f);
      Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f);
      Vec3d vec3d3 = vec3d.subtract(vec3d2);
      return new Vector4f((float)vec3d3.x, (float)vec3d3.y, (float)vec3d3.z, f2);
   }

   @Override

   protected Vec3i getBoneColor(String string) {
        String[] stringArray;
        block24: {
            block23: {
                stringArray = GirlEffectEntity.getAttributeStrings(this.RenderEntity);
                try {
                    if (stringArray.length < 8) {
                        return DefaultColor;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                try {
                    if (string.contains("band")) {
                        return DefaultColor;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                try {
                    try {
                        if (!string.contains("eyeColor") && !string.contains("eyeColor2")) break block23;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinNpcRenderer.rethrow(runtimeException);
                    }
                    return GoblinNpcRenderer.parseTribeColor(stringArray[8]);
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (!string.contains("variant") && !string.contains("boob")) break block24;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                return GoblinNpcRenderer.parseGirlColor(stringArray[7]);
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        try {
            if (string.contains("hair")) {
                return GoblinNpcRenderer.parseMarkColor(stringArray[6]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinNpcRenderer.rethrow(runtimeException);
        }
        try {
            if (MeatParts.contains(string)) {
                return GoblinNpcRenderer.parseGirlColor(stringArray[7]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinNpcRenderer.rethrow(runtimeException);
        }
        try {
            if (EyeBrowParts.contains(string)) {
                return GoblinNpcRenderer.parseMarkColor(stringArray[6]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinNpcRenderer.rethrow(runtimeException);
        }
        return DefaultColor;
    }

   public static Vec3i parseTribeColor(String string) {
      return TribeColor.values()[Integer.parseInt(string)].getColor();
   }

   public static Vec3i parseGirlColor(String string) {
      return GirlColor.values()[Integer.parseInt(string)].a();
   }

   public static Vec3i parseMarkColor(String string) {
      return MarkColor.values()[Integer.parseInt(string)].a();
   }

   @Override
   protected void a(BufferBuilder bufferBuilder, String string, GeoBone bone) {
      try {
         if (this.RenderEntity.world instanceof PreviewWorld) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String[] stringArray = GirlEffectEntity.getAttributeStrings(this.RenderEntity);

      try {
         if (stringArray.length < 8) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      String string2 = string;
      byte bv = -1;

      label115: {
         label114: {
            label113: {
               label112: {
                  label111: {
                     label110: {
                        label109: {
                           label108: {
                              try {
                                 switch (string2.hashCode()) {
                                    case -1383396097:
                                       break label112;
                                    case -1383396096:
                                       break label113;
                                    case 2364452:
                                       break label110;
                                    case 3029410:
                                       break label109;
                                    case 3105718:
                                       if (!string2.equals("earL")) {
                                          break label115;
                                       }
                                       break label114;
                                    case 3105724:
                                       break;
                                    case 3194850:
                                       break label108;
                                    case 93921650:
                                       break label111;
                                    default:
                                       break label115;
                                 }
                              } catch (RuntimeException error3) {
                                 throw rethrow(error3);
                              }

                              if (string2.equals("earR")) {
                                 bv = 1;
                              }
                              break label115;
                           }

                           if (string2.equals("hair")) {
                              bv = 2;
                           }
                           break label115;
                        }

                        if (string2.equals("body")) {
                           bv = 3;
                        }
                        break label115;
                     }

                     if (string2.equals("LegR")) {
                        bv = 4;
                     }
                     break label115;
                  }

                  if (string2.equals("boobR")) {
                     bv = 5;
                  }
                  break label115;
               }

               if (string2.equals("boobR1")) {
                  bv = 6;
               }
               break label115;
            }

            if (string2.equals("boobR2")) {
               bv = 7;
            }
            break label115;
         }

         bv = 0;
      }

      label86: {
         label85: {
            label84: {
               label83: {
                  label82: {
                     label81: {
                        label80: {
                           try {
                              switch (bv) {
                                 case 0:
                                    applyChildBoneVisibility(bone, stringArray[0], stringArray[1], stringArray[3]);
                                    break label86;
                                 case 1:
                                    break label85;
                                 case 2:
                                    break label84;
                                 case 3:
                                    break label83;
                                 case 4:
                                    break label82;
                                 case 5:
                                    break label81;
                                 case 6:
                                    break label80;
                                 case 7:
                                    break;
                                 default:
                                    break label86;
                              }
                           } catch (RuntimeException error4) {
                              throw rethrow(error4);
                           }

                           applyBoneWobble(this.Initialized, bone, 5.0F, 3.0F);
                           break label86;
                        }

                        applyBoneWobble(this.Initialized, bone, 10.0F, 15.0F);
                        break label86;
                     }

                     applyBoneWobble(this.Initialized, bone, 30.0F, 30.0F);
                     break label86;
                  }

                  applyBoneWobble(this.Initialized, bone, 25.0F, 25.0F);
                  break label86;
               }

               bone.setPivotY(-0.15F);
               applyShoulderIdlePose(this.RenderEntity, bone);
               break label86;
            }

            applyBoneState(bone, stringArray[5]);
            break label86;
         }

         applyChildBoneVisibility(bone, stringArray[0], stringArray[2], stringArray[4]);
      }

      try {
         if (string.contains("crown")) {
            applyBoneAppearanceState(this.RenderEntity, bone, stringArray[9]);
         }
      } catch (RuntimeException error5) {
         throw rethrow(error5);
      }
   }

   public static void applyBoneAppearanceState(GirlEntity girl, GeoBone bone, String string) {
      try {
         if (girl.isTracked()) {
            bone.setHidden(true);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      if (girl instanceof GoblinNpc) {
         int i = Integer.parseInt(string);

         GeoBone bone2;
         boolean flag;
         label30: {
            try {
               bone2 = bone;
               if (i == 0) {
                  flag = true;
                  break label30;
               }
            } catch (RuntimeException error2) {
               throw rethrow(error2);
            }

            flag = false;
         }

         bone2.setHidden(flag);
      } else {
         try {
            if (girl instanceof GoblinPlayer) {
               bone.setHidden(((ItemStack)girl.getDataManager().get(InventoryGirlEntity.HelmetKey)).isEmpty());
            }
         } catch (RuntimeException error3) {
            throw rethrow(error3);
         }
      }
   }

   public static void applyBoneWobble(boolean flag, GeoBone bone, float f, float f2) {
      try {
         if (Mc.isGamePaused()) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      try {
         if (!flag) {
            return;
         }
      } catch (RuntimeException error2) {
         throw rethrow(error2);
      }

      bone.setRotationX(bone.getRotationX() + AngleMath.degToRadians(MathUtils.clamp(x, -f, f)));
      bone.setRotationZ(bone.getRotationZ() + AngleMath.degToRadians(MathUtils.clamp(N, -f2, f2)));
   }


   public static void applyShoulderIdlePose(GirlEntity girl, GeoBone bone) {
        block7: {
            try {
                try {
                    if (B == -420.69f && girl.getCurrentAction() == GirlAnimationState.SHOULDER_IDLE) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        float f = -GoblinNpcRenderer.Mc.getRenderManager().playerViewX;
        try {
            bone.setPivotY(8.0f);
            if (Mc.isGamePaused()) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinNpcRenderer.rethrow(runtimeException);
        }
        bone.setRotationX(bone.getRotationX() + AngleMath.degToRadians(f));
    }

   public static void applyBoneState(GeoBone bone, String string) {
      int i = Integer.parseInt(string);
      a(bone, i);
   }

   static HashSet<Integer> rotateSubsetIndex(int i, String string) {
      int i2 = Integer.parseInt(string);
      int i3 = i - 1;
      ArrayList list = computeSubsets(i3);

      while (i2 >= list.size()) {
         i2 -= list.size();
      }

      return (HashSet<Integer>)list.get(i2);
   }

   static ArrayList<HashSet<Integer>> computeSubsets(int i) {
      ArrayList list = new ArrayList();
      collectSubsets(0, new HashSet<>(), i, list);
      return list;
   }

   static void collectSubsets(int i, HashSet<Integer> set, int i2, ArrayList<HashSet<Integer>> list) {
      try {
         if (i > i2) {
            list.add(set);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      HashSet set2 = new HashSet(set);
      collectSubsets(i + 1, set, i2, list);
      set2.add(i);
      collectSubsets(i + 1, set2, i2, list);
   }

   static HashSet<Integer> pickRandomSubset(int i, String string) {
      HashSet set = new HashSet();
      int i2 = Integer.parseInt(string);
      i2 = (int)(0.01F * i2 * i2);
      int i3 = Math.round(i2 / 100.0F * i);
      Random random = new Random(i2);

      for (int i4 = 0; i4 < i3; i4++) {
         int i5 = random.nextInt(i);

         try {
            if (!set.contains(i5)) {
               set.add(i5);
               continue;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         i4--;
      }

      return set;
   }

   static void applyChildBoneVisibility(GeoBone bone, String string, String string2, String string3) {
      GeoBone bone2 = a(bone, Integer.parseInt(string));
      GeoBone bone3 = a(bone2, Integer.parseInt(string2));
      List list = bone3.childBones;
      int i = list.size();
      HashSet<Integer> set = rotateSubsetIndex(i, string3);
      bone3.childBones.forEach(arg1 -> arg1.setHidden(true));
      set.forEach(arg1b -> b(bone3, arg1b));
   }

   @Override

   protected Vec3i a(Vec3i vec3i) {
        block4: {
            try {
                try {
                    if (this.Initialized || this.F) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                return vec3i;
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        float f = MathUtils.clamp(this.LightLevel, 2.0f, 15.0f) / 15.0f;
        return new Vec3i((double)((float)vec3i.getX() * f), (double)((float)vec3i.getY() * f), (double)((float)vec3i.getZ() * f));
    }

   @Override

   protected ItemStack a(@Nullable ItemStack stack) {
        block4: {
            GirlAnimationState girlAnimationState = ((GoblinNpc)this.RenderEntity).getCurrentAction();
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.RUN && girlAnimationState != GirlAnimationState.CATCH) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                return (ItemStack)((GoblinNpc)this.RenderEntity).getDataManager().get(GoblinNpc.HeldItemKey);
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        return stack;
    }

   @Override
   public HashSet<String> getFilteredBoneNames() {
      return new HashSet<String>() {
         {
            this.add("boobs");
            this.add("booty");
            this.add("vagina");
            this.add("fuckhole");
            this.add("preggy");
            this.add("LegL");
            this.add("LegR");
            this.add("cheekR");
            this.add("cheekL");
         }
      };
   }

   @Override
   protected float float_a() {
      try {
         return this.RenderEntity.getCurrentAction() == GirlAnimationState.CATCH ? 0.5F : 1.0F;
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @Override

   protected Vec3d getItemRenderRotation(ItemStack stack) {
        block7: {
            try {
                if (stack == null) {
                    return Vec3d.ZERO;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
            try {
                try {
                    if (!(stack.getItem() instanceof ItemBlock) && stack.getMaxStackSize() != 1) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                return super.a(stack);
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        return new Vec3d(180.0, 0.0, 0.0);
    }

   @Override

   public void a(BufferBuilder bufferBuilder, GeoCube geoCube, GeoBone bone, float f, float f2, float f3, float f4, double d) {
        block7: {
            try {
                try {
                    if (!this.Initialized || BoobLegParts.contains(bone.getName())) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinNpcRenderer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GoblinNpcRenderer.rethrow(runtimeException);
            }
        }
        try {
            if (this.ProcessedBones.contains(bone.getName())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinNpcRenderer.rethrow(runtimeException);
        }
        this.CachedBone = bone;
        super.a(bufferBuilder, geoCube, bone, f, f2, f3, f4, d);
    }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
