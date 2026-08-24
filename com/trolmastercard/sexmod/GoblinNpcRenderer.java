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

   protected ResourceLocation a(GoblinNpc goblin) throws IOException {
      UUID uuid = goblin.getSexPlayerUuid();
      if (uuid == null) {
         uuid = goblin.e();
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

   public void a(GeoModel model, GoblinNpc goblin, float f, float f2, float f3, float f4, float f5) {
      super.a(model, goblin, f, f2, f3, f4, goblin.FadeAlpha);
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


   public void a(GoblinNpc goblin, double d, double d2, double d3, float f, float f2) {
        block78: {
            block77: {
                block74: {
                    block68: {
                        block72: {
                            block73: {
                                block69: {
                                    block70: {
                                        block66: {
                                            block67: {
                                                block65: {
                                                    block64: {
                                                        block63: {
                                                            block62: {
                                                                try {
                                                                    try {
                                                                        this.RenderEntity = goblin;
                                                                        renderer = this;
                                                                        if (-420.69f != f || goblin.getCurrentAction() != GirlAnimationState.SHOULDER_IDLE) break block62;
                                                                    }
                                                                    catch (RuntimeException error) {
                                                                        throw GoblinNpcRenderer.rethrow(error);
                                                                    }
                                                                    flag = true;
                                                                    break block63;
                                                                }
                                                                catch (RuntimeException error2) {
                                                                    throw GoblinNpcRenderer.rethrow(error2);
                                                                }
                                                            }
                                                            flag = false;
                                                        }
                                                        try {
                                                            try {
                                                                renderer.u = flag;
                                                                renderer2 = this;
                                                                if (-420.69f != f || goblin.getCurrentAction() != GirlAnimationState.PICK_UP) break block64;
                                                            }
                                                            catch (RuntimeException error3) {
                                                                throw GoblinNpcRenderer.rethrow(error3);
                                                            }
                                                            flag2 = true;
                                                            break block65;
                                                        }
                                                        catch (RuntimeException error4) {
                                                            throw GoblinNpcRenderer.rethrow(error4);
                                                        }
                                                    }
                                                    flag2 = false;
                                                }
                                                renderer2.F = flag2;
                                                this.LightLevel = goblin.world.getLight(goblin.getPosition(), true);
                                                this.LastPartialTicks = f2;
                                                GoblinNpcRenderer.B = f;
                                                girlAnimationState = goblin.getCurrentAction();
                                                uuid = goblin.e();
                                                if (goblin.isTracked()) {
                                                    vec3d = GoblinNpcRenderer.a(goblin.world, goblin, uuid, d, d2, d3);
                                                    d = vec3d.x;
                                                    d2 = vec3d.y;
                                                    d3 = vec3d.z;
                                                }
                                                try {
                                                    try {
                                                        try {
                                                            try {
                                                                try {
                                                                    if (girlAnimationState != GirlAnimationState.THROWN && girlAnimationState != GirlAnimationState.START_THROWING) break block66;
                                                                }
                                                                catch (RuntimeException error5) {
                                                                    throw GoblinNpcRenderer.rethrow(error5);
                                                                }
                                                                if (GoblinNpcRenderer.Mc.gameSettings.thirdPersonView != 0) break block67;
                                                            }
                                                            catch (RuntimeException error6) {
                                                                throw GoblinNpcRenderer.rethrow(error6);
                                                            }
                                                            if (f != -420.69f) break block67;
                                                        }
                                                        catch (RuntimeException error7) {
                                                            throw GoblinNpcRenderer.rethrow(error7);
                                                        }
                                                        if (goblin.isTracked()) break block67;
                                                    }
                                                    catch (RuntimeException error8) {
                                                        throw GoblinNpcRenderer.rethrow(error8);
                                                    }
                                                    return;
                                                }
                                                catch (RuntimeException error9) {
                                                    throw GoblinNpcRenderer.rethrow(error9);
                                                }
                                            }
                                            if (!goblin.isTracked()) {
                                                goblin.prevRenderYawOffset = d4 = goblin.I().floatValue();
                                                goblin.renderYawOffset = d4;
                                            }
                                        }
                                        try {
                                            try {
                                                try {
                                                    if (!GoblinNpcRenderer.a((GirlEntity)goblin, girlAnimationState)) break block68;
                                                    if (!GoblinNpcRenderer.Mc.player.getPersistentID().equals(uuid)) break block69;
                                                }
                                                catch (RuntimeException error10) {
                                                    throw GoblinNpcRenderer.rethrow(error10);
                                                }
                                                if (-420.69f == f) break block70;
                                            }
                                            catch (RuntimeException error11) {
                                                throw GoblinNpcRenderer.rethrow(error11);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException error12) {
                                            throw GoblinNpcRenderer.rethrow(error12);
                                        }
                                    }
                                    goblin.renderYawOffset = GoblinNpcRenderer.Mc.player.rotationYaw + 180.0f;
                                    goblin.prevRenderYawOffset = GoblinNpcRenderer.Mc.player.rotationYaw + 180.0f;
                                    vec3d2 = GoblinNpcRenderer.Mc.player.getLookVec();
                                    GlStateManager.pushMatrix();
                                    GlStateManager.translate((double)vec3d2.x, (double)(vec3d2.y + (double)GoblinNpcRenderer.Mc.player.getEyeHeight()), (double)vec3d2.z);
                                    vec3d3 = GoblinNpc.rotateVec3dYaw(new Vec3d((double)(-Math.abs(GoblinNpcRenderer.Mc.player.rotationPitch)), 0.0, 0.0), GoblinNpcRenderer.Mc.player.rotationYaw);
                                    GlStateManager.rotate((float)GoblinNpcRenderer.Mc.player.rotationPitch, (float)((float)vec3d3.x), (float)0.0f, (float)((float)vec3d3.z));
                                    d = 0.0;
                                    d2 = 0.0;
                                    d3 = 0.0;
                                    break block77;
                                }
                                try {
                                    try {
                                        block71: {
                                            try {
                                                try {
                                                    if (!goblin.isTracked() || uuid == null) break block71;
                                                }
                                                catch (RuntimeException error13) {
                                                    throw GoblinNpcRenderer.rethrow(error13);
                                                }
                                                if (!GoblinNpcRenderer.Mc.player.getPersistentID().equals(uuid)) break block72;
                                            }
                                            catch (RuntimeException error14) {
                                                throw GoblinNpcRenderer.rethrow(error14);
                                            }
                                        }
                                        if (uuid == null) break block73;
                                    }
                                    catch (RuntimeException error15) {
                                        throw GoblinNpcRenderer.rethrow(error15);
                                    }
                                    if (GoblinNpcRenderer.Mc.player.getPersistentID().equals(uuid)) break block73;
                                }
                                catch (RuntimeException error16) {
                                    throw GoblinNpcRenderer.rethrow(error16);
                                }
                                player2 = goblin.world.getPlayerEntityByUUID(uuid);
                                try {
                                    if (player2 == null) ** GOTO lbl131
                                    goblin.renderYawOffset = player2.rotationYaw;
                                    goblin.prevRenderYawOffset = player2.rotationYaw;
                                }
                                catch (RuntimeException error17) {
                                    throw GoblinNpcRenderer.rethrow(error17);
                                }
                            }
                            goblin.renderYawOffset = GoblinNpcRenderer.Mc.player.rotationYaw;
                            goblin.prevRenderYawOffset = GoblinNpcRenderer.Mc.player.rotationYaw;
                        }
                        vec3d4 = GoblinNpcRenderer.getThrowAimOffset((GirlEntity)goblin, goblin.e(), f2);
                        d = vec3d4.x;
                        d2 = vec3d4.y;
                        d3 = vec3d4.z;
                        break block77;
                    }
                    if (this.Initialized) {
                        GoblinNpcRenderer.rotateCameraToGirl(f2);
                        vec3d5 = new Vec3d((double)LerpMath.a(-0.1f, 0.2f, GoblinNpcRenderer.Mc.gameSettings.fovSetting / 110.0f), 0.0, 0.0);
                        vec3d5 = GoblinNpc.rotateVec3dYaw(vec3d5, GoblinNpcRenderer.Mc.player.rotationYaw);
                        d = vec3d5.x;
                        d2 = vec3d5.y;
                        d3 = vec3d5.z;
                        goblin.renderYawOffset = GoblinNpcRenderer.Mc.player.rotationYaw;
                        goblin.prevRenderYawOffset = GoblinNpcRenderer.Mc.player.prevRotationYaw;
                        if (GoblinNpcRenderer.Mc.player.isSneaking()) {
                            d2 -= 0.075;
                        }
                    } else {
                        block76: {
                            block75: {
                                try {
                                    try {
                                        if (girlAnimationState != GirlAnimationState.SHOULDER_IDLE) break block74;
                                        if (uuid != null) break block75;
                                    }
                                    catch (RuntimeException error18) {
                                        throw GoblinNpcRenderer.rethrow(error18);
                                    }
                                    return;
                                }
                                catch (RuntimeException error19) {
                                    throw GoblinNpcRenderer.rethrow(error19);
                                }
                            }
                            try {
                                try {
                                    if (!GoblinNpcRenderer.Mc.player.getPersistentID().equals(uuid) || GoblinNpcRenderer.Mc.gameSettings.thirdPersonView != 0) break block76;
                                }
                                catch (RuntimeException error20) {
                                    throw GoblinNpcRenderer.rethrow(error20);
                                }
                                return;
                            }
                            catch (RuntimeException error21) {
                                throw GoblinNpcRenderer.rethrow(error21);
                            }
                        }
                        player3 = goblin.world.getPlayerEntityByUUID(uuid);
                        try {
                            if (player3 == null) {
                                return;
                            }
                        }
                        catch (RuntimeException error22) {
                            throw GoblinNpcRenderer.rethrow(error22);
                        }
                        vector4f = GoblinNpcRenderer.renderGirlBox(player3, f2);
                        d = vector4f.x;
                        d2 = vector4f.y;
                        d3 = vector4f.z;
                        goblin.renderYawOffset = vector4f.w;
                        if (player3.isSneaking()) {
                            d2 -= 0.32;
                        }
                    }
                    break block77;
                }
                try {
                    if (girlAnimationState != GirlAnimationState.PICK_UP || uuid == null) break block77;
                }
                catch (RuntimeException error23) {
                    throw GoblinNpcRenderer.rethrow(error23);
                }
                player4 = goblin.world.getPlayerEntityByUUID(uuid);
                try {
                    if (player4 != null) {
                        goblin.prevRenderYawOffset = player4.prevRotationYawHead;
                        goblin.renderYawOffset = player4.rotationYawHead;
                    }
                }
                catch (RuntimeException error24) {
                    throw GoblinNpcRenderer.rethrow(error24);
                }
            }
            try {
                try {
                    try {
                        super.a(goblin, d, d2, d3, f, f2);
                        if (!GoblinNpcRenderer.a((GirlEntity)goblin, girlAnimationState) || GoblinNpcRenderer.Mc.gameSettings.thirdPersonView != 0) break block78;
                    }
                    catch (RuntimeException error25) {
                        throw GoblinNpcRenderer.rethrow(error25);
                    }
                    if (!GoblinNpcRenderer.Mc.player.getPersistentID().equals(uuid)) break block78;
                }
                catch (RuntimeException error26) {
                    throw GoblinNpcRenderer.rethrow(error26);
                }
                GlStateManager.popMatrix();
            }
            catch (RuntimeException error27) {
                throw GoblinNpcRenderer.rethrow(error27);
            }
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

   public static Vector4f a(EntityPlayer player2, float f) {
      EntityPlayerSP mcPlayer = Mc.player;
      float f2 = LerpMath.lerp(player2.prevRenderYawOffset, player2.renderYawOffset, f);
      Vec3d vec3d = LerpMath.lerpVec3d(new Vec3d(player2.lastTickPosX, player2.lastTickPosY, player2.lastTickPosZ), player2.getPositionVector(), f);
      Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f);
      Vec3d vec3d3 = vec3d.subtract(vec3d2);
      return new Vector4f((float)vec3d3.x, (float)vec3d3.y, (float)vec3d3.z, f2);
   }

   @Override

   protected Vec3i a(String string) {
        String[] stringArray;
        block24: {
            block23: {
                stringArray = GirlEffectEntity.getAttributeStrings(this.RenderEntity);
                try {
                    if (stringArray.length < 8) {
                        return r;
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
        return r;
    }

   public static Vec3i parseTribeColor(String string) {
      return TribeColor.values()[Integer.parseInt(string)].a();
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

   static HashSet<Integer> b(int i, String string) {
      int i2 = Integer.parseInt(string);
      int i3 = i - 1;
      ArrayList list = rotateCameraToGirl(i3);

      while (i2 >= list.size()) {
         i2 -= list.size();
      }

      return (HashSet<Integer>)list.get(i2);
   }

   static ArrayList<HashSet<Integer>> a(int i) {
      ArrayList list = new ArrayList();
      a(0, new HashSet<>(), i, list);
      return list;
   }

   static void a(int i, HashSet<Integer> set, int i2, ArrayList<HashSet<Integer>> list) {
      try {
         if (i > i2) {
            list.add(set);
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      HashSet set2 = new HashSet(set);
      a(i + 1, set, i2, list);
      set2.add(i);
      a(i + 1, set2, i2, list);
   }

   static HashSet<Integer> a(int i, String string) {
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
      HashSet set = b(i, string3);
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
            GirlAnimationState girlAnimationState = ((GoblinNpc)this.RenderEntity).y();
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
   public HashSet<String> a() {
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
   protected float a() {
      try {
         return this.RenderEntity.getCurrentAction() == GirlAnimationState.CATCH ? 0.5F : 1.0F;
      } catch (RuntimeException error) {
         throw rethrow(error);
      }
   }

   @Override

   protected Vec3d a(ItemStack stack) {
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
