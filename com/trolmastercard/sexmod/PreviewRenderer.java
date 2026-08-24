package com.trolmastercard.sexmod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PreviewRenderer extends GeoEntityRenderer<PreviewEntity> {
   public static final float e = 1.876945F;
   public static final float i = 2.876945F;
   Minecraft Mc;
   PreviewEntity PreviewEntity = null;
   FilePersistence.WhitelistFile WhitelistEntry = null;
   HashMap<String, String> h = new HashMap<>();
   HashMap<String, String> f = new HashMap<>();
   HashMap<String, GirlFloatProvider> g = new HashMap<>();
   public static boolean IsRendering = false;
   Vec3d ModelScale = new Vec3d(1.0, 1.0, 1.0);
   Vec3d CachedPos;

   public PreviewRenderer(RenderManager renderManager, AnimatedGeoModel<PreviewEntity> animatedGeoModel) {
      super(renderManager, animatedGeoModel);
      this.Mc = Minecraft.getMinecraft();
      this.a();
   }

   void a() {
      this.h.put("customLegL", "legL");
      this.h.put("customShinL", "shinL");
      this.h.put("customLegR", "legR");
      this.h.put("customShinR", "shinR");
      this.f.put("top", "upperBody");
      this.f.put("customArmL", "armL");
      this.f.put("customLowerArmL", "lowerArmL");
      this.f.put("customArmR", "armR");
      this.f.put("customLowerArmR", "lowerArmR");
      this.g.put("lowerArmR", arg1 -> AngleMath.degToRadians(arg1.ai()));
      this.g.put("lowerArmL", arg1b -> AngleMath.degToRadians(arg1b.T()));
   }

   boolean d(PreviewEntity previewEntity) {
      String string = previewEntity.getModelName();

      try {
         if (previewEntity.f) {
            return false;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      try {
         if (FilePersistence.isModelRegistered(string)) {
            return false;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if (FilePersistence.getServerAddress() != null) {
            return true;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      UUID uuid = previewEntity.b();
      GirlEntity girl = GirlEntity.getClientSideByUuid(uuid);

      try {
         if (girl == null) {
            return true;
         }
      } catch (IllegalStateException error4) {
         throw rethrow(error4);
      }

      HashSet set = girl.Y();
      set.remove(string);
      String string2 = GirlEntity.joinModelNames(set);
      NetworkHandler.channel.sendToServer(new PacketUploadModelString(string2, previewEntity.b()));
      return true;
   }

   @SideOnly(Side.CLIENT)
   public static void rotateCameraToGirl(GirlEntity girl, float f) {
      try {
         if (girl.isDead) {
            return;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      try {
         if (!girl.world.isRemote) {
            return;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if (!girl.H()) {
            return;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

      for (String string : girl.Y()) {
         PreviewEntity previewEntity = new PreviewEntity(girl.world, girl.getGirlUuid(), string);
         IsRendering = true;
         renderManager.renderEntity(previewEntity, 0.0, 0.0, 0.0, 0.0F, f, false);
      }
   }

   public boolean a(PreviewEntity previewEntity, ICamera iCamera, double d, double d2, double d3) {
      return super.shouldRender(previewEntity, iCamera, d, d2, d3);
   }

   boolean a(float f) {
      try {
         if (f == 2.876945F) {
            return true;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      try {
         if (f == 1.876945F) {
            return true;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if (IsRendering) {
            IsRendering = false;
            return true;
         } else {
            return false;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }
   }


   void a(FilePersistence.WhitelistFile whitelist, PreviewEntity previewEntity, float f) {
        Vec3d vec3d;
        block6: {
            try {
                try {
                    if (whitelist != null && whitelist.i() != RenderMode.DEFAULT) break block6;
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
                this.CachedPos = null;
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
        }
        try {
            GL11.glDisable((int)2896);
            PreviewRenderer renderer = this;
            vec3d = whitelist.i() == RenderMode.SEXMOD ? BedLogic.getBedHeadPos(previewEntity, f) : null;
        }
        catch (IllegalStateException illegalStateException) {
            throw PreviewRenderer.rethrow(illegalStateException);
        }
        renderer.CachedPos = vec3d;
    }


   public void a(PreviewEntity previewEntity, double d2, double d3, double d4, float f, float f2) {
        EntityPlayer entityPlayer;
        UUID uUID;
        GirlEntity girl;
        GirlEntity girl2;
        block36: {
            FilePersistence.WhitelistFile whitelist;
            block35: {
                try {
                    if (!this.a(f2)) {
                        return;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
                try {
                    if (FilePersistence.ServerActive) {
                        return;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
                try {
                    if (this.d(previewEntity)) {
                        return;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
                previewEntity.RenderStack = new MatrixStack();
                whitelist = FilePersistence.getModelData(previewEntity.getModelName());
                try {
                    try {
                        this.PreviewEntity = previewEntity;
                        this.WhitelistEntry = whitelist;
                        this.a(whitelist, previewEntity, f2);
                        if (f2 != 1.876945f && f2 != 2.876945f) break block35;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw PreviewRenderer.rethrow(illegalStateException);
                    }
                    this.ModelScale = new Vec3d(1.0, 1.0, 1.0);
                    super.doRender(previewEntity, d2, d3, d4, f, f2);
                    GL11.glEnable((int)2896);
                    return;
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
            }
            UUID uUID2 = previewEntity.b();
            try {
                if (uUID2 == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
            girl2 = GirlEntity.getClientSideByUuid(uUID2);
            try {
                if (girl2 == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
            try {
                try {
                    try {
                        if (whitelist == null || whitelist.a()) break block36;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw PreviewRenderer.rethrow(illegalStateException);
                    }
                    if (girl2.getOutfitIndex() != 0) break block36;
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
        }
        if (!(girl2 instanceof PlayerGirlEntity)) {
            girl = girl2;
        } else {
            GirlEntity girl3;
            uUID = ((PlayerGirlEntity)girl2).m();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
            entityPlayer = previewEntity.world.getPlayerEntityByUUID(uUID);
            try {
                girl3 = entityPlayer == null ? girl2 : entityPlayer;
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
            girl = girl3;
        }
        uUID = girl2.a(this.Mc, previewEntity, (EntityLivingBase)girl, f2);
        entityPlayer = new BlockPos(Math.floor(((EntityLivingBase)girl).posX), Math.floor(((EntityLivingBase)girl).posY), Math.floor(((EntityLivingBase)girl).posZ));
        int i = ((EntityLivingBase)girl).world.getLight((BlockPos)entityPlayer, true);
        Vec3d vec3d = new Vec3d(1.0, 1.0, 1.0);
        float f3 = MathUtils.clamp(i, 10.0f, 15.0f) / 15.0f;
        try {
            this.ModelScale = new Vec3d(vec3d.x * (double)f3, vec3d.y * (double)f3, vec3d.z * (double)f3);
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)((Vec3d)uUID).x, (double)((Vec3d)uUID).y, (double)((Vec3d)uUID).z);
            if (girl2.Q()) {
                GlStateManager.rotate((float)girl2.I().floatValue(), (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw PreviewRenderer.rethrow(illegalStateException);
        }
        super.doRender(previewEntity, 0.0, 0.0, 0.0, f, f2);
        GlStateManager.popMatrix();
        GL11.glEnable((int)2896);
    }

   public static Vec3d getRenderPosition(Minecraft mc, PreviewEntity previewEntity, EntityLivingBase livingBase, GirlEntity girl, float f) {
      Vec3d vec3d;
      if (girl.Q()) {
         Vec3d vec3d2 = girl.getTargetPos();
         float f2 = girl.I();
         previewEntity.prevPosX = vec3d2.x;
         previewEntity.prevPosY = vec3d2.y;
         previewEntity.prevPosZ = vec3d2.z;
         previewEntity.lastTickPosX = vec3d2.x;
         previewEntity.lastTickPosY = vec3d2.y;
         previewEntity.lastTickPosZ = vec3d2.z;
         previewEntity.posX = vec3d2.x;
         previewEntity.posY = vec3d2.y;
         previewEntity.posZ = vec3d2.z;
         previewEntity.rotationYaw = f2;
         previewEntity.prevRotationYaw = f2;
         previewEntity.rotationYawHead = f2;
         previewEntity.prevRotationYawHead = f2;
         previewEntity.renderYawOffset = f2;
         previewEntity.prevRenderYawOffset = f2;
         previewEntity.rotationPitch = f2;
         previewEntity.prevRotationPitch = f2;
         vec3d = vec3d2;
      } else {
         previewEntity.rotationYaw = livingBase.rotationYaw;
         previewEntity.prevRotationYaw = livingBase.prevRotationYaw;
         previewEntity.rotationYawHead = livingBase.rotationYawHead;
         previewEntity.prevRotationYawHead = livingBase.prevRotationYawHead;
         previewEntity.renderYawOffset = livingBase.renderYawOffset;
         previewEntity.prevRenderYawOffset = livingBase.prevRenderYawOffset;
         previewEntity.rotationPitch = livingBase.rotationPitch;
         previewEntity.prevRotationPitch = livingBase.prevRotationPitch;
         previewEntity.prevPosX = livingBase.prevPosX;
         previewEntity.prevPosY = livingBase.prevPosY;
         previewEntity.prevPosZ = livingBase.prevPosZ;
         previewEntity.lastTickPosX = livingBase.lastTickPosX;
         previewEntity.lastTickPosY = livingBase.lastTickPosY;
         previewEntity.lastTickPosZ = livingBase.lastTickPosZ;
         previewEntity.posX = livingBase.posX;
         previewEntity.posY = livingBase.posY;
         previewEntity.posZ = livingBase.posZ;
         vec3d = LerpMath.lerpVec3d(new Vec3d(livingBase.lastTickPosX, livingBase.lastTickPosY, livingBase.lastTickPosZ), livingBase.getPositionVector(), f);
      }

      EntityPlayerSP mcPlayer = mc.player;
      Vec3d vec3d3 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f);
      return vec3d.subtract(vec3d3);
   }

   public void a(GeoModel model, PreviewEntity previewEntity, float f, float f2, float f3, float f4, float f5) {
      GlStateManager.disableCull();
      GlStateManager.enableRescaleNormal();
      BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
      bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

      for (GeoBone bone : model.topLevelBones) {
         try {
            if (f != 1.876945F) {
               this.a(previewEntity, bone, f);
            }
         } catch (IllegalStateException error) {
            throw rethrow(error);
         }

         previewEntity.RenderStack.translate(-bone.getPivotX() / 16.0F, -bone.getPivotY() / 16.0F, -bone.getPivotZ() / 16.0F);
         this.renderRecursively(bufferBuilder, bone, f2, f3, f4, f5);
      }

      Tessellator.getInstance().draw();
      GlStateManager.disableRescaleNormal();
      GlStateManager.enableCull();
   }

   EntityLivingBase c(PreviewEntity previewEntity) {
      GirlEntity girl = this.b(previewEntity);

      try {
         if (girl == null) {
            return null;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      Object obj;
      if (!(girl instanceof PlayerGirlEntity)) {
         obj = girl;
      } else {
         EntityPlayer player = previewEntity.world.getPlayerEntityByUUID(((PlayerGirlEntity)girl).m());

         Object obj2;
         label28: {
            try {
               if (player == null) {
                  obj2 = girl;
                  break label28;
               }
            } catch (IllegalStateException error2) {
               throw rethrow(error2);
            }

            obj2 = player;
         }

         obj = obj2;
      }

      return (EntityLivingBase)obj;
   }

   GirlEntity b(PreviewEntity previewEntity) {
      UUID uuid = previewEntity.b();
      GirlEntity girl = GirlTracker.getByUuid(uuid);

      try {
         if (girl != null) {
            return girl;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      return GirlEntity.getClientSideByUuid(uuid);
   }

   void a(PreviewEntity previewEntity, GeoBone bone, float f) {
      String string = this.a(previewEntity);

      try {
         if (string == null) {
            return;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      this.a(previewEntity, bone, f, string);
   }


   void a(PreviewEntity previewEntity, GeoBone bone, float f2, String string) {
        block4: {
            GirlEntity girl = this.b(previewEntity);
            EntityLivingBase entityLivingBase = this.c(previewEntity);
            try {
                try {
                    previewEntity.RenderStack = girl.a(string, false);
                    if (previewEntity.f && f2 == 2.876945f) break block4;
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
        }
        previewEntity.RenderStack.scale(0.5f, 0.5f, 0.5f);
        previewEntity.RenderStack.rotateY((float)Math.toRadians(-GuiCustomizeGirl.b));
    }

   String a(PreviewEntity previewEntity) {
      try {
         if (previewEntity.f) {
            return previewEntity.SlotFilter.boneName;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      FilePersistence.WhitelistFile whitelist = FilePersistence.getModelData(previewEntity.getModelName());

      try {
         if (whitelist == null) {
            return null;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if (GirlBodySlot.CUSTOM_BONE.equals(whitelist.j())) {
            return whitelist.b();
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      return whitelist.j().boneName;
   }

   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
      this.PreviewEntity.RenderStack.push();
      this.PreviewEntity.RenderStack.translate(bone);
      this.PreviewEntity.RenderStack.moveToPivot(bone);
      this.PreviewEntity.RenderStack.rotate(bone);
      this.PreviewEntity.RenderStack.scale(bone);
      this.PreviewEntity.RenderStack.moveBackFromPivot(bone);
      if (!bone.isHidden()) {
         for (GeoCube geoCube : bone.childCubes) {
            this.PreviewEntity.RenderStack.push();
            GlStateManager.pushMatrix();
            this.renderCube(bufferBuilder, geoCube, f, f2, f3, f4);
            GlStateManager.popMatrix();
            this.PreviewEntity.RenderStack.pop();
         }
      }

      if (!bone.childBonesAreHiddenToo()) {
         for (GeoBone bone2 : bone.childBones) {
            this.renderRecursively(bufferBuilder, bone2, f, f2, f3, f4);
         }
      }

      try {
         this.PreviewEntity.RenderStack.pop();
      } catch (IllegalStateException error) {
      }
   }


   public void renderCube(BufferBuilder bufferBuilder, GeoCube geoCube, float f, float f2, float f3, float f4) {
        this.PreviewEntity.RenderStack.moveToPivot(geoCube);
        this.PreviewEntity.RenderStack.rotate(geoCube);
        this.PreviewEntity.RenderStack.moveBackFromPivot(geoCube);
        for (GeoQuad geoQuad : geoCube.quads) {
            Vector3f vector3f;
            block28: {
                block27: {
                    block26: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw PreviewRenderer.rethrow(illegalStateException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.getX(), (float)geoQuad.normal.getY(), (float)geoQuad.normal.getZ());
                        try {
                            try {
                                try {
                                    this.PreviewEntity.RenderStack.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block26;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw PreviewRenderer.rethrow(illegalStateException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block26;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw PreviewRenderer.rethrow(illegalStateException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw PreviewRenderer.rethrow(illegalStateException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block27;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw PreviewRenderer.rethrow(illegalStateException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block27;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw PreviewRenderer.rethrow(illegalStateException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw PreviewRenderer.rethrow(illegalStateException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block28;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw PreviewRenderer.rethrow(illegalStateException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block28;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw PreviewRenderer.rethrow(illegalStateException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (IllegalStateException illegalStateException) {
                    throw PreviewRenderer.rethrow(illegalStateException);
                }
            }
            try {
                if (this.CachedPos != null) {
                    this.ModelScale = BoneColorHelper.tintColor(this.ModelScale, vector3f, this.CachedPos);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw PreviewRenderer.rethrow(illegalStateException);
            }
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                this.PreviewEntity.RenderStack.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.pos((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).tex((double)geoVertex.textureU, (double)geoVertex.textureV).color((float)this.ModelScale.x, (float)this.ModelScale.y, (float)this.ModelScale.z, f4).normal(vector3f.getX(), vector3f.getY(), vector3f.getZ()).endVertex();
            }
        }
    }

   private static IllegalStateException rethrow(IllegalStateException error) {
      return error;
   }
}
