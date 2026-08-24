package com.trolmastercard.sexmod;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public abstract class GeoGirlRenderer<T extends GirlEntity & IAnimatable> extends GeoEntityRenderer<T> implements GirlBoneFilter {
   protected static final ResourceLocation LineTexture = new ResourceLocation("sexmod", "textures/line.png");
   static final float GlowScale = 1.5F;
   protected double c;
   protected T RenderEntity;
   protected static Minecraft Mc;
   protected static HashMap<UUID, ResourceLocation> TextureCache = new HashMap<>();
   Color BodyColor = new Color(245, 199, 165);
   Color AccentColor = new Color(245, 157, 169);
   boolean Initialized = false;
   protected HashSet<String> ProcessedBones = new HashSet<>();
   Integer k = null;
   Integer b = null;
   Integer d = null;
   float a = 0.0F;
   public static BufferBuilder n;
   Matrix4f ModelMatrix = null;
   protected GeoBone CachedBone = null;

   public GeoGirlRenderer(RenderManager renderManager, AnimatedGeoModel<T> animatedGeoModel, double d) {
      super(renderManager, animatedGeoModel);
      this.c = d;
      Mc = Minecraft.getMinecraft();
      this.shadowSize = 0.2F;
   }

   protected ResourceLocation d(T t) throws IOException {
      label38: {
         try {
            if (!(t.world instanceof PreviewWorld) && t.ae() != null) {
               break label38;
            }
         } catch (IOException error) {
            throw rethrow(error);
         }

         ResourceLocation location = TextureCache.get(Mc.getSession().getProfile().getId());

         try {
            if (location == null) {
               return this.a(Mc.getSession().getProfile().getId(), t.world);
            }

            return location;
         } catch (IOException error2) {
            throw rethrow(error2);
         }
      }

      ResourceLocation location2 = TextureCache.get(t.ae());

      try {
         if (location2 == null) {
            return this.a(t.ae(), t.world);
         }
      } catch (IOException error3) {
         throw rethrow(error3);
      }

      return location2;
   }

   protected ResourceLocation a(UUID uuid, World world) throws IOException {
      BufferedImage bufferedImage;
      try {
         bufferedImage = SkinFetcher.fetchSkinImage(uuid);
         Graphics graphics = bufferedImage.getGraphics();
         graphics.setColor(this.BodyColor);
         graphics.fillRect(0, 0, 4, 3);
         graphics.setColor(this.AccentColor);
         graphics.fillRect(4, 0, 3, 3);
      } catch (Exception error) {
         try {
            if (!this.Initialized) {
               this.Initialized = true;
            }
         } catch (Exception error2) {
            throw rethrow(error2);
         }

         bufferedImage = ImageIO.read(Mc.getResourceManager().getResource(new ResourceLocation("sexmod", "textures/player/steve.png")).getInputStream());
      }

      TextureCache.put(uuid, this.renderManager.renderEngine.getDynamicTextureLocation("player" + uuid, new DynamicTexture(bufferedImage)));
      return TextureCache.get(uuid);
   }

   protected static float a(GirlEntity girl, float f) {
      try {
         if (girl.Q()) {
            return girl.I();
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      return LerpMath.lerp(girl.prevRenderYawOffset, girl.renderYawOffset, f);
   }

   protected void d() {
   }

   protected void b() {
   }

   float a(World world, Vec3d vec3d, float f, float f2) {
      RayTraceResult hit = this.a(vec3d, vec3d.add(VectorMath.rotatePitchYaw(new Vec3d(0.0, 0.0, -4.0), f, f2)), world);

      try {
         if (hit == null) {
            return 4.0F;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      Vec3d vec3d2 = hit.hitVec;

      try {
         if (vec3d2 == null) {
            return 4.0F;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      return (float)vec3d.distanceTo(vec3d2);
   }

   boolean a(T t, EntityPlayer player) {
      try {
         if (t instanceof PlayerGirlEntity) {
            return true;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      World world2 = t.world;
      Vec3d vec3d = t.getPositionVector();
      float f = t.width * 1.5F;
      float f2 = t.height * 1.5F;
      Vec3d vec3d2 = player.getPositionVector().add(0.0, player.getEyeHeight(), 0.0);
      int i = Mc.gameSettings.thirdPersonView;

      try {
         if (i != 0) {
            return true;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      if (i > 0) {
         float f3 = player.rotationYaw;
         float f4 = player.rotationPitch;
         if (i == 2) {
            f4 += 180.0F;
         }

         float f5 = 4.0F;
         Vec3d vec3d3 = vec3d2.add(
            MathHelper.sin(f3 * (float) (Math.PI / 180.0)) * MathHelper.cos(f4 * (float) (Math.PI / 180.0)) * f5,
            MathHelper.sin(f4 * (float) (Math.PI / 180.0)) * f5,
            -MathHelper.cos(f3 * (float) (Math.PI / 180.0)) * MathHelper.cos(f4 * (float) (Math.PI / 180.0)) * f5
         );
         BlockPos pos = new BlockPos(vec3d3);
         boolean flag = world2.isAirBlock(pos);
         if (!flag) {
            vec3d2 = vec3d3;
         } else if (world2.isAirBlock(pos.add(0, 1, 0))) {
            vec3d2 = new Vec3d(vec3d3.x, pos.getY() + 1, vec3d3.z);
         }
      }

      Vec3d[] vec3dArray = new Vec3d[]{
         vec3d.add(-f / 2.0F, 0.0, -f / 2.0F),
         vec3d.add(-f / 2.0F, 0.0, f / 2.0F),
         vec3d.add(f / 2.0F, 0.0, -f / 2.0F),
         vec3d.add(f / 2.0F, 0.0, f / 2.0F),
         vec3d.add(-f / 2.0F, f2, -f / 2.0F),
         vec3d.add(-f / 2.0F, f2, f / 2.0F),
         vec3d.add(f / 2.0F, f2, -f / 2.0F),
         vec3d.add(f / 2.0F, f2, f / 2.0F)
      };

      for (Vec3d vec3d4 : vec3dArray) {
         RayTraceResult hit = this.a(vec3d2, vec3d4, world2);

         try {
            if (hit == null) {
               return true;
            }
         } catch (IllegalStateException error3) {
            throw rethrow(error3);
         }

         IBlockState state = world2.getBlockState(hit.getBlockPos());

         try {
            if (state.isTranslucent()) {
               return true;
            }
         } catch (IllegalStateException error4) {
            throw rethrow(error4);
         }

         try {
            if (state.getBlock().getRenderLayer() != BlockRenderLayer.SOLID) {
               return true;
            }
         } catch (IllegalStateException error5) {
            throw rethrow(error5);
         }
      }

      return false;
   }


   HashSet<String> a(Boolean flag, boolean flag2) {
        try {
            if (ClientProxy.IS_PRELOADING) {
                return new HashSet<String>();
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw GeoGirlRenderer.rethrow(illegalStateException);
        }
        HashSet<String> hashSet = flag != false ? GuiCustomizeGirl.getSelectedClothingOptions() : ((GirlEntity)this.RenderEntity).Y();
        HashSet<String> hashSet2 = new HashSet<String>();
        for (String string : hashSet) {
            FilePersistence.WhitelistFile whitelist = FilePersistence.getModelData(string);
            try {
                if (whitelist == null) {
                    continue;
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw GeoGirlRenderer.rethrow(illegalStateException);
            }
            try {
                try {
                    if (!whitelist.a() && flag2) {
                        continue;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
            }
            catch (IllegalStateException illegalStateException) {
                throw GeoGirlRenderer.rethrow(illegalStateException);
            }
            hashSet2.addAll(whitelist.h());
        }
        return hashSet2;
    }


   public void a(GeoModel model, T t, float f, float f2, float f3, float f4, float f5) {
        boolean flag;
        Boolean flag2;
        GeoGirlRenderer d_2;
        block10: {
            try {
                try {
                    try {
                        try {
                            if (GeoGirlRenderer.Mc.player == null || ((GirlEntity)t).h()) break block10;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        if (!((GirlEntity)t).d()) break block10;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                    if (this.a(t, (EntityPlayer)GeoGirlRenderer.Mc.player)) break block10;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw GeoGirlRenderer.rethrow(illegalStateException);
            }
        }
        GlStateManager.enableRescaleNormal();
        this.a(t, f, f2, f3, f4, f5);
        this.renderLate(t, f, f2, f3, f4, f5);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        try {
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
            this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
            this.ProcessedBones.clear();
            GeoGirlRenderer d_3 = this;
            d_2 = this;
            flag2 = ((GirlEntity)t).h();
            flag = ((GirlEntity)t).ah() == 0;
        }
        catch (IllegalStateException illegalStateException) {
            throw GeoGirlRenderer.rethrow(illegalStateException);
        }
        d_3.ProcessedBones = d_2.a(flag2, flag);
        this.d();
        BoneColorHelper.a(((GirlEntity)t).b().getModelRendererList(), this.a(), this);
        BoneColorHelper.setSkinColor(t, f);
        this.a(model, bufferBuilder, t, f2, f3, f4, f5, f);
        this.renderAfter(t, f, f2, f3, f4, f5);
        GlStateManager.disableRescaleNormal();
        GlStateManager.enableCull();
        GL20.glUseProgram((int)0);
    }

   protected void a(GeoModel model, BufferBuilder bufferBuilder, T t, float f, float f2, float f3, float f4, float f5) {
      GeoBone bone = null;

      for (GeoBone bone2 : model.topLevelBones) {
         if (bone2.getName().equals("steve")) {
            bone = bone2;
         } else {
            this.renderRecursively(bufferBuilder, bone2, f, f2, f3, f4);
         }
      }

      label30: {
         IOException error;
         try {
            Tessellator.getInstance().draw();
            this.b();
            if (bone == null) {
               return;
            }

            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

            try {
               Minecraft.getMinecraft().renderEngine.bindTexture(this.d(this.RenderEntity));
               break label30;
            } catch (IOException error2) {
               error = error2;
            }
         } catch (IllegalStateException error3) {
            throw rethrow(error3);
         }

         error.printStackTrace();
      }

      this.renderRecursively(bufferBuilder, bone, f, f2, f3, this.RenderEntity.v());
      Tessellator.getInstance().draw();
   }

   String a(String string) {
      StringBuilder sb = new StringBuilder();

      try {
         BufferedReader reader = new BufferedReader(new FileReader(string));

         while (true) {
            String string2;
            String string3 = string2 = reader.readLine();

            try {
               if (string3 == null) {
                  break;
               }

               sb.append(string2).append("//\n");
            } catch (IOException error) {
               throw rethrow(error);
            }
         }

         reader.close();
      } catch (IOException error2) {
         error2.printStackTrace();
      }

      return sb.toString();
   }

   protected void a(double d, double d2, double d3) {
      try {
         if (this.RenderEntity.h()) {
            return;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      try {
         if (this.RenderEntity.y().hideNameTag) {
            return;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if (Mc.getRenderManager().renderViewEntity == null) {
            return;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      this.renderLivingLabel(this.RenderEntity, this.RenderEntity.ab(), d, d2 + this.RenderEntity.i(), d3, 300);
   }

   Vec3d a(EntityPlayer player2, float f) {
      EntityLiving living = (EntityLiving)player2.getRidingEntity();
      EntityPlayerSP mcPlayer = Mc.player;
      Vec3d vec3d = living.getLookVec();
      Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(player2.lastTickPosX, player2.lastTickPosY, player2.lastTickPosZ), player2.getPositionVector(), f);
      Vec3d vec3d3 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f);
      vec3d3 = vec3d2.subtract(vec3d3);
      this.RenderEntity.renderYawOffset = living.renderYawOffset;
      return new Vec3d(vec3d3.x + vec3d.x * -0.5, vec3d3.y + 0.15F, vec3d3.z + vec3d.z * -0.5);
   }

   protected Vec3d a(T t, float f, Vec3d vec3d) {
      return vec3d;
   }


   Vec3d a(T t2, float f2, double d, double d2, double d3) {
        float f3;
        Vec3d vec3d;
        block28: {
            block27: {
                block26: {
                    block24: {
                        vec3d = new Vec3d(d, d2, d3);
                        try {
                            if (((GirlEntity)t2).world instanceof PreviewWorld) {
                                return vec3d;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        try {
                            block25: {
                                try {
                                    try {
                                        if (!((GirlEntity)t2).t()) break block24;
                                        if (!(t2 instanceof PlayerGirlEntity)) break block25;
                                    }
                                    catch (IllegalStateException illegalStateException) {
                                        throw GeoGirlRenderer.rethrow(illegalStateException);
                                    }
                                    if (GeoGirlRenderer.Mc.gameSettings.thirdPersonView == 0) break block24;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw GeoGirlRenderer.rethrow(illegalStateException);
                                }
                            }
                            this.a(d, d2, d3);
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                    }
                    EntityPlayer entityPlayer = ((GirlEntity)t2).z();
                    try {
                        try {
                            try {
                                try {
                                    if (entityPlayer == null || !entityPlayer.isRiding()) break block26;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw GeoGirlRenderer.rethrow(illegalStateException);
                                }
                                if (!(entityPlayer.getRidingEntity() instanceof EntityHorse)) break block26;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                            if (!((EntityHorse)entityPlayer.getRidingEntity()).isHorseSaddled()) break block26;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        return this.a(entityPlayer, f2);
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                }
                try {
                    if (!((GirlEntity)t2).Q()) {
                        return vec3d;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
                try {
                    try {
                        if (!(t2 instanceof PlayerGirlEntity) || !((PlayerGirlEntity)t2).f()) break block27;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                    if (GeoGirlRenderer.Mc.gameSettings.thirdPersonView != 0) break block28;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
            }
            Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(GeoGirlRenderer.Mc.player.lastTickPosX, GeoGirlRenderer.Mc.player.lastTickPosY, GeoGirlRenderer.Mc.player.lastTickPosZ), GeoGirlRenderer.Mc.player.getPositionVector(), (double)f2);
            vec3d = ((GirlEntity)t2).o().subtract(vec3d2);
        }
        ((GirlEntity)t2).rotationYaw = f3 = ((GirlEntity)t2).I().floatValue();
        ((GirlEntity)t2).prevRenderYawOffset = f3;
        ((GirlEntity)t2).renderYawOffset = f3;
        ((GirlEntity)t2).prevRotationYawHead = f3;
        ((GirlEntity)t2).rotationYawHead = f3;
        return vec3d;
    }

   protected void b(T t) {
   }


   public void a(T t, double d, double d2, double d3, float f, float f2) {
        boolean flag;
        float f3;
        float f4;
        float f5;
        T t2;
        AnimationEvent<T> animationEvent;
        AnimationEvent<T> animationEvent2;
        float f6;
        float f7;
        float f8;
        float f9;
        EntityModelData entityModelData;
        block33: {
            block32: {
                block31: {
                    float f10;
                    float f11;
                    boolean flag2;
                    block30: {
                        boolean flag3;
                        block29: {
                            block28: {
                                this.RenderEntity = t;
                                Vec3d vec3d = this.a(t, f2, d, d2, d3);
                                vec3d = this.a(t, f2, vec3d);
                                d = vec3d.x;
                                d2 = vec3d.y;
                                d3 = vec3d.z;
                                try {
                                    this.b(t);
                                    if (t.getLeashed()) {
                                        this.a((GirlEntity)t, d, d2 + this.c, d3, f2);
                                    }
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw GeoGirlRenderer.rethrow(illegalStateException);
                                }
                                try {
                                    try {
                                        GlStateManager.pushMatrix();
                                        GlStateManager.translate((double)d, (double)d2, (double)d3);
                                        GL11.glDisable((int)2896);
                                        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
                                        GlStateManager.enableNormalize();
                                        GlStateManager.enableBlend();
                                        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                                        if (t.getRidingEntity() == null || !t.getRidingEntity().shouldRiderSit()) break block28;
                                    }
                                    catch (IllegalStateException illegalStateException) {
                                        throw GeoGirlRenderer.rethrow(illegalStateException);
                                    }
                                    flag3 = true;
                                    break block29;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw GeoGirlRenderer.rethrow(illegalStateException);
                                }
                            }
                            flag3 = false;
                        }
                        flag2 = flag3;
                        entityModelData = new EntityModelData();
                        entityModelData.isSitting = flag2;
                        entityModelData.isChild = t.isChild();
                        f11 = Interpolations.lerpYaw(((GirlEntity)t).prevRenderYawOffset, ((GirlEntity)t).renderYawOffset, f2);
                        float f22 = Interpolations.lerpYaw(((GirlEntity)t).prevRotationYawHead, ((GirlEntity)t).rotationYawHead, f2);
                        f9 = f22 - f11;
                        try {
                            if (!flag2 || !(t.getRidingEntity() instanceof EntityLivingBase)) break block30;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        EntityLivingBase entityLivingBase = (EntityLivingBase)t.getRidingEntity();
                        f11 = Interpolations.lerpYaw(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, f2);
                        f9 = f22 - f11;
                        f10 = MathHelper.wrapDegrees((float)f9);
                        if (f10 < -85.0f) {
                            f10 = -85.0f;
                        }
                        if (f10 >= 85.0f) {
                            f10 = 85.0f;
                        }
                        f11 = f22 - f10;
                        if (f10 * f10 > 2500.0f) {
                            f11 += f10 * 0.2f;
                        }
                        f9 = f22 - f11;
                    }
                    f8 = Interpolations.lerp(((GirlEntity)t).prevRotationPitch, ((GirlEntity)t).rotationPitch, f2);
                    f10 = this.handleRotationFloat(t, f2);
                    this.b(t, f10, f11, f2);
                    f7 = 0.0f;
                    f6 = 0.0f;
                    try {
                        if (flag2 || !t.isEntityAlive()) break block31;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                    f7 = Interpolations.lerp(((GirlEntity)t).prevLimbSwingAmount, ((GirlEntity)t).limbSwingAmount, f2);
                    f6 = ((GirlEntity)t).limbSwing - ((GirlEntity)t).limbSwingAmount * (1.0f - f2);
                    if (t.isChild()) {
                        f6 *= 3.0f;
                    }
                    if (f7 > 1.0f) {
                        f7 = 1.0f;
                    }
                }
                try {
                    AnimationEvent<T> animationEvent3;
                    entityModelData.headPitch = -f8;
                    entityModelData.netHeadYaw = -f9;
                    animationEvent2 = animationEvent3;
                    animationEvent = animationEvent3;
                    t2 = t;
                    f5 = f6;
                    f4 = f7;
                    f3 = f2;
                    if (f7 > -0.15f && f7 < 0.15f) break block32;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
                flag = true;
                break block33;
            }
            flag = false;
        }
        animationEvent2(t2, f5, f4, f3, flag, Collections.singletonList(entityModelData));
        AnimationEvent<T> animationEvent4 = animationEvent;
        GeoModelProvider geoModelProvider = super.getGeoModelProvider();
        ResourceLocation resourceLocation = geoModelProvider.getModelLocation(t);
        GeoModel geoModel = geoModelProvider.getModel(resourceLocation);
        try {
            if (geoModelProvider instanceof IAnimatableModel) {
                ((IAnimatableModel)((Object)geoModelProvider)).setLivingAnimations(t, t.getUniqueID().hashCode(), animationEvent4);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw GeoGirlRenderer.rethrow(illegalStateException);
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)0.0f, (float)0.01f, (float)0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.getEntityTexture(t));
        software.bernie.geckolib3.core.util.Color color = this.getRenderColor(t, f2);
        boolean flag4 = this.setDoRenderBrightness(t, f2);
        try {
            this.a(geoModel, t, f2, (float)color.getRed() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getAlpha() / 255.0f);
            if (flag4) {
                RenderHurtColor.unset();
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw GeoGirlRenderer.rethrow(illegalStateException);
        }
        for (GeoLayerRenderer geoLayerRenderer : this.layerRenderers) {
            geoLayerRenderer.render(t, f6, f7, f2, f6, f9, f8, color);
        }
        GL11.glEnable((int)2896);
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        this.a(t);
        PreviewRenderer.rotateCameraToGirl(t, f2);
        Vec3f vec3f = this.e(t);
        try {
            if (vec3f != null) {
                this.a((GirlEntity)t, f2, vec3f);
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw GeoGirlRenderer.rethrow(illegalStateException);
        }
    }

   void a(T t) {
      ArrayList list = new ArrayList<>(GirlGeoModel.CamBones);
      list.addAll(t.p);

      for (String string : list) {
         GirlEntity girl;
         String string2;
         boolean flag;
         label23: {
            try {
               girl = t;
               string2 = string;
               if (!t.h()) {
                  flag = true;
                  break label23;
               }
            } catch (IllegalStateException error) {
               throw rethrow(error);
            }

            flag = false;
         }

         MatrixStack matrixStack = girl.a(string2, flag);
         Matrix4f matrix4f = matrixStack.getModelMatrix();
         Vec3d vec3d = new Vec3d(-matrix4f.m03, matrix4f.m13, -matrix4f.m23);
         t.a(string, vec3d);
      }
   }

   @Nullable
   protected Vec3f e(T t) {
      return null;
   }

   public Entity c(GirlEntity girl) {
      return girl;
   }

   void a(GirlEntity girl, float f, Vec3f vec3f) {
      EntityPlayerSP mcPlayer = Mc.player;
      vec3f = new Vec3f(vec3f.X / 255.0F, vec3f.Y / 255.0F, vec3f.Z / 255.0F);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0, 0.01, 0.0);
      Entity entity = this.c(girl);

      Vec3d vec3d;
      label17: {
         try {
            if (girl.Q()) {
               vec3d = girl.getTargetPos();
               break label17;
            }
         } catch (IllegalStateException error) {
            throw rethrow(error);
         }

         vec3d = LerpMath.lerpVec3d(new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ), entity.getPositionVector(), f);
      }

      Vec3d vec3d2 = vec3d;
      Vec3d vec3d3 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f);
      Vec3d vec3d4 = vec3d2.subtract(vec3d3);
      GlStateManager.translate(vec3d4.x, vec3d4.y, vec3d4.z);
      Mc.getTextureManager().bindTexture(LineTexture);
      float f2 = a(girl, f, 1.0F, 5.0F);
      this.b(tessellator, bufferBuilder, girl, vec3f, f2);
      GlStateManager.popMatrix();
   }

   protected static float a(GirlEntity girl, float f, float f2, float f3) {
      EntityPlayerSP mcPlayer = Mc.player;
      Entity entity = ((GeoGirlRenderer)Mc.getRenderManager().getEntityRenderObject(girl)).c(girl);

      Vec3d vec3d;
      label17: {
         try {
            if (girl.Q()) {
               vec3d = girl.getTargetPos();
               break label17;
            }
         } catch (IllegalStateException error) {
            throw rethrow(error);
         }

         vec3d = LerpMath.lerpVec3d(new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ), entity.getPositionVector(), f);
      }

      Vec3d vec3d2 = vec3d;
      Vec3d vec3d3 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), f);
      Vec3d vec3d4 = ActiveRenderInfo.getCameraPosition().add(vec3d3);
      float f4 = (float)vec3d4.distanceTo(vec3d2);
      float f5 = Math.abs(f4) / 5.0F;
      return LerpMath.lerp(f3, f2, MathUtils.clamp(f5, 0.0F, 1.0F));
   }

   protected void b(Tessellator tessellator, BufferBuilder bufferBuilder, GirlEntity girl, Vec3f vec3f, float f) {
   }

   protected static void a(BufferBuilder bufferBuilder, Tessellator tessellator, GirlEntity girl, String string, String string2, float f, float f2, float f3, float f4) {
      bufferBuilder.begin(1, DefaultVertexFormats.POSITION_TEX_COLOR);
      GlStateManager.glLineWidth(f4);
      Vec3d vec3d = girl.getModelBone(string);
      Vec3d vec3d2 = girl.getModelBone(string2);
      bufferBuilder.pos(vec3d.x, vec3d.y, vec3d.z)
         .tex(0.0, 0.0)
         .color(f, f2, f3, 1.0F)
         .endVertex();
      bufferBuilder.pos(vec3d2.x, vec3d2.y, vec3d2.z)
         .tex(0.0, 0.0)
         .color(f, f2, f3, 1.0F)
         .endVertex();
      tessellator.draw();
   }

   protected static void a(Tessellator tessellator, BufferBuilder bufferBuilder, GirlEntity girl, Vec3f vec3f, float f) {
      a(bufferBuilder, tessellator, girl, "braStringMidStartR", "braStringMidMid1R", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidMid1R", "braStringMidMid2R", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidMid2R", "braStringMidMid3R", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidMid3R", "braStringMidEndR", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidEndR", "braStringBackR", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringBackR", "braStringRightEndR", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringRightEndR", "braStringRightStartR", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringRightR", "braStringRightL", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidStartL", "braStringMidMid1L", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidMid1L", "braStringMidMid2L", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidMid2L", "braStringMidMid3L", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidMid3L", "braStringMidEndL", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringMidEndL", "braStringBackL", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringBackL", "braStringLeftEndL", vec3f.X, vec3f.Y, vec3f.Z, f);
      a(bufferBuilder, tessellator, girl, "braStringLeftEndL", "braStringLeftStartL", vec3f.X, vec3f.Y, vec3f.Z, f);
   }

   protected void b(T t, float f, float f2, float f3) {
      try {
         super.applyRotations(t, f, f2, f3);
         if (!(t instanceof PlayerGirlEntity)) {
            return;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      UUID uuid = ((PlayerGirlEntity)t).m();

      try {
         if (uuid == null) {
            return;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      EntityPlayer player = t.world.getPlayerEntityByUUID(uuid);

      try {
         if (player == null) {
            return;
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      try {
         if (!player.isElytraFlying()) {
            return;
         }
      } catch (IllegalStateException error4) {
         throw rethrow(error4);
      }

      float f4 = player.getTicksElytraFlying() + f3;
      float f5 = MathHelper.clamp(f4 * f4 / 100.0F, 0.0F, 1.0F);
      GlStateManager.rotate(f5 * (-90.0F - player.rotationPitch), 1.0F, 0.0F, 0.0F);
      Vec3d vec3d = player.getLook(f3);
      double d = player.motionX * player.motionX + player.motionZ * player.motionZ;
      double d2 = vec3d.x * vec3d.x + vec3d.z * vec3d.z;

      try {
         if (!(d > 0.0) || !(d2 > 0.0)) {
            return;
         }
      } catch (IllegalStateException error5) {
         throw rethrow(error5);
      }

      double d3 = (player.motionX * vec3d.x + player.motionZ * vec3d.z) / (Math.sqrt(d) * Math.sqrt(d2));
      double d4 = player.motionX * vec3d.z - player.motionZ * vec3d.x;
      GlStateManager.rotate((float)(Math.signum(d4) * Math.acos(d3)) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
   }

   protected void a(BufferBuilder bufferBuilder, String string, GeoBone bone) {
   }

   protected void a(GirlEntity girl, double d, double d2, double d3, float f) {
      Entity entity = girl.getLeashHolder();
      d2 -= (1.6 - girl.height) * 0.5;
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder bufferBuilder = tessellator.getBuffer();
      double d4 = LerpMath.lerp(entity.prevRotationYaw, entity.rotationYaw, f * 0.5F) * (float) (Math.PI / 180.0);
      double d5 = LerpMath.lerp(entity.prevRotationPitch, entity.rotationPitch, f * 0.5F) * (float) (Math.PI / 180.0);
      double d6 = Math.cos(d4);
      double d7 = Math.sin(d4);
      double d8 = Math.sin(d5);
      if (entity instanceof EntityHanging) {
         d6 = 0.0;
         d7 = 0.0;
         d8 = -1.0;
      }

      double d9 = Math.cos(d5);
      double d10 = LerpMath.lerp(entity.prevPosX, entity.posX, f) - d6 * 0.7 - d7 * 0.5 * d9;
      double d11 = LerpMath.lerp(entity.prevPosY + entity.getEyeHeight() * 0.7, entity.posY + entity.getEyeHeight() * 0.7, f) - d8 * 0.5 - 0.25;
      double d12 = LerpMath.lerp(entity.prevPosZ, entity.posZ, f) - d7 * 0.7 + d6 * 0.5 * d9;
      double d13 = LerpMath.lerp(girl.prevRenderYawOffset, girl.renderYawOffset, f) * (float) (Math.PI / 180.0) + (Math.PI / 2);
      d6 = Math.cos(d13) * girl.width * 0.4;
      d7 = Math.sin(d13) * girl.width * 0.4;
      double d14 = LerpMath.lerp(girl.prevPosX, girl.posX, f) + d6;
      double d15 = LerpMath.lerp(girl.prevPosY, girl.posY, f);
      double d16 = LerpMath.lerp(girl.prevPosZ, girl.posZ, f) + d7;
      d += d6;
      d3 += d7;
      double d17 = (float)(d10 - d14);
      double d18 = (float)(d11 - d15);
      double d19 = (float)(d12 - d16);
      GlStateManager.disableTexture2D();
      GlStateManager.disableLighting();
      GlStateManager.disableCull();
      bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);

      for (int i = 0; i <= 24; i++) {
         float f2 = 0.5F;
         float f3 = 0.4F;
         float f4 = 0.3F;
         if (i % 2 == 0) {
            f2 *= 0.7F;
            f3 *= 0.7F;
            f4 *= 0.7F;
         }

         float f5 = i / 24.0F;
         bufferBuilder.pos(
               d + d17 * f5 + 0.0, d2 + d18 * (f5 * f5 + f5) * 0.5 + ((24.0F - i) / 18.0F + 0.125F), d3 + d19 * f5
            )
            .color(f2, f3, f4, 1.0F)
            .endVertex();
         bufferBuilder.pos(
               d + d17 * f5 + 0.025, d2 + d18 * (f5 * f5 + f5) * 0.5 + ((24.0F - i) / 18.0F + 0.125F) + 0.025, d3 + d19 * f5
            )
            .color(f2, f3, f4, 1.0F)
            .endVertex();
      }

      tessellator.draw();
      bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);

      for (int i2 = 0; i2 <= 24; i2++) {
         float f6 = 0.5F;
         float f7 = 0.4F;
         float f8 = 0.3F;
         if (i2 % 2 == 0) {
            f6 *= 0.7F;
            f7 *= 0.7F;
            f8 *= 0.7F;
         }

         float f9 = i2 / 24.0F;
         bufferBuilder.pos(
               d + d17 * f9 + 0.0, d2 + d18 * (f9 * f9 + f9) * 0.5 + ((24.0F - i2) / 18.0F + 0.125F) + 0.025, d3 + d19 * f9
            )
            .color(f6, f7, f8, 1.0F)
            .endVertex();
         bufferBuilder.pos(
               d + d17 * f9 + 0.025, d2 + d18 * (f9 * f9 + f9) * 0.5 + ((24.0F - i2) / 18.0F + 0.125F), d3 + d19 * f9 + 0.025
            )
            .color(f6, f7, f8, 1.0F)
            .endVertex();
      }

      tessellator.draw();
      GlStateManager.enableLighting();
      GlStateManager.enableTexture2D();
      GlStateManager.enableCull();
   }


   public void renderRecursively(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4) {
        String string;
        block35: {
            block34: {
                block33: {
                    block32: {
                        block31: {
                            try {
                                if (((GirlEntity)this.RenderEntity).world instanceof PreviewWorld) {
                                    return;
                                }
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                            string = bone.getName();
                            if (!string.equals("weapon")) break block31;
                            try {
                                block36: {
                                    if (!(this.RenderEntity instanceof InventoryGirlEntity)) break block31;
                                    break block36;
                                    catch (IllegalStateException illegalStateException) {
                                        throw GeoGirlRenderer.rethrow(illegalStateException);
                                    }
                                }
                                this.a(bufferBuilder, bone);
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                        }
                        if (!string.equals("itemRenderer")) break block32;
                        try {
                            block37: {
                                if (((GirlEntity)this.RenderEntity).y() != GirlAnimationState.PAYMENT) break block32;
                                break block37;
                                catch (IllegalStateException illegalStateException) {
                                    throw GeoGirlRenderer.rethrow(illegalStateException);
                                }
                            }
                            this.b(bufferBuilder, bone);
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                    }
                    if (string.equals("ballL")) break block33;
                    try {
                        block38: {
                            if (string.equals("ballR")) break block33;
                            break block38;
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                        }
                        if (!string.equals("cock")) break block34;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                }
                f4 = 1.0f;
            }
            n = bufferBuilder;
            this.a(bufferBuilder, string, bone);
            MATRIX_STACK.push();
            MATRIX_STACK.translate(bone);
            MATRIX_STACK.moveToPivot(bone);
            MATRIX_STACK.rotate(bone);
            MATRIX_STACK.scale(bone);
            MATRIX_STACK.moveBackFromPivot(bone);
            if (!"Head2".equals(string)) break block35;
            try {
                block39: {
                    if (this.c()) break block35;
                    break block39;
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                }
                MATRIX_STACK.pop();
                return;
            }
            catch (IllegalStateException illegalStateException) {
                throw GeoGirlRenderer.rethrow(illegalStateException);
            }
        }
        try {
            if (!this.b(string)) {
                MATRIX_STACK.pop();
                return;
            }
        }
        catch (IllegalStateException illegalStateException) {
            throw GeoGirlRenderer.rethrow(illegalStateException);
        }
        if (!bone.isHidden) {
            Vector4f vector4f = this.a(string, f, f2, f3);
            f = vector4f.x;
            f2 = vector4f.y;
            f3 = vector4f.z;
            double d = vector4f.w;
            if (!this.ProcessedBones.contains(string)) {
                for (Object object : bone.childCubes) {
                    MATRIX_STACK.push();
                    this.CachedBone = bone;
                    this.a(bufferBuilder, (GeoCube)object, f, f2, f3, f4, d);
                    MATRIX_STACK.pop();
                }
            }
            for (Object object : bone.childBones) {
                try {
                    if (d == 0.0) {
                        this.renderRecursively(bufferBuilder, (GeoBone)object, f, f2, f3, f4);
                        continue;
                    }
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
                this.a(bufferBuilder, (GeoBone)object, f, f2, f3, f4, d);
            }
        }
        try {
            MATRIX_STACK.pop();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
    }

   protected Vector4f a(float f, float f2, float f3) {
      return new Vector4f(f, f2, f3, 0.0F);
   }

   boolean b(String string) {
      try {
         if (!string.startsWith("armor")) {
            return true;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      return this.RenderEntity instanceof InventoryGirlEntity;
   }

   protected Vector4f a(String string, float f, float f2, float f3) {
      try {
         if (!string.startsWith("armor")) {
            return this.a(f, f2, f3);
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      try {
         if (!(this.RenderEntity instanceof InventoryGirlEntity)) {
            return this.a(f, f2, f3);
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      try {
         if ((Integer)this.RenderEntity.OpenProgress.get(GirlEntity.OutfitIndexKey) == 0) {
            return this.a(f, f2, f3);
         }
      } catch (IllegalStateException error3) {
         throw rethrow(error3);
      }

      GeoModelProvider geoModelProvider = this.getGeoModelProvider();

      try {
         if (!(geoModelProvider instanceof GirlGeoModel)) {
            return this.a(f, f2, f3);
         }
      } catch (IllegalStateException error4) {
         throw rethrow(error4);
      }

      GirlGeoModel girlGeoModel = (GirlGeoModel)geoModelProvider;
      ItemStack stack = girlGeoModel.a(this.RenderEntity, string);

      try {
         if (!(stack.getItem() instanceof ItemArmor)) {
            return this.a(f, f2, f3);
         }
      } catch (IllegalStateException error5) {
         throw rethrow(error5);
      }

      ItemArmor armor = (ItemArmor)stack.getItem();
      ArmorMaterial armorMaterial = armor.getArmorMaterial();
      float f4 = 0.0F;
      switch (armorMaterial) {
         case GOLD:
            f4 = 1.0F;
            break;
         case CHAIN:
         case IRON:
            f4 = 2.0F;
            break;
         case LEATHER:
            f4 = 4.0F;
            int i = armor.getColor(stack);
            float f5 = (i >> 16 & 0xFF) / 255.0F;
            float f6 = (i >> 8 & 0xFF) / 255.0F;
            float f7 = (i & 0xFF) / 255.0F;
            f *= f5;
            f2 *= f6;
            f3 *= f7;
      }

      return new Vector4f(f, f2, f3, 72.0F * f4 / 4096.0F);
   }

   public void a(T t, float f, float f2, float f3, float f4, float f5) {
      this.ModelMatrix = (Matrix4f)MATRIX_STACK.getModelMatrix().clone();
   }


   public void a(BufferBuilder bufferBuilder, GeoBone bone, float f, float f2, float f3, float f4, double d) {
        block16: {
            block17: {
                String string;
                block15: {
                    block14: {
                        try {
                            if (((GirlEntity)this.RenderEntity).world instanceof PreviewWorld) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        string = bone.getName();
                        try {
                            if (string.equals("weapon")) {
                                this.a(bufferBuilder, bone);
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        try {
                            try {
                                if (string.equals("ballL") || string.equals("ballR")) break block14;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                            if (!string.equals("cock")) break block15;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                    }
                    f4 = 1.0f;
                }
                try {
                    this.a(bufferBuilder, bone.getName(), bone);
                    MATRIX_STACK.push();
                    MATRIX_STACK.translate(bone);
                    MATRIX_STACK.moveToPivot(bone);
                    MATRIX_STACK.rotate(bone);
                    MATRIX_STACK.scale(bone);
                    MATRIX_STACK.moveBackFromPivot(bone);
                    if (bone.isHidden) break block16;
                    if (this.ProcessedBones.contains(string)) break block17;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
                for (GeoCube object : bone.childCubes) {
                    MATRIX_STACK.push();
                    GlStateManager.pushMatrix();
                    this.CachedBone = bone;
                    this.a(bufferBuilder, object, f, f2, f3, f4, d);
                    GlStateManager.popMatrix();
                    MATRIX_STACK.pop();
                }
            }
            for (GeoBone geoBone2 : bone.childBones) {
                this.a(bufferBuilder, geoBone2, f, f2, f3, f4, d);
            }
        }
        MATRIX_STACK.pop();
    }

   protected boolean c() {
      try {
         if (!this.RenderEntity.n()) {
            return true;
         }
      } catch (IllegalStateException error) {
         throw rethrow(error);
      }

      try {
         if (Mc.gameSettings.thirdPersonView != 0) {
            return true;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }

      return false;
   }


   public void a(BufferBuilder bufferBuilder, GeoCube geoCube, float f, float f2, float f3, float f4, double d) {
        MATRIX_STACK.moveToPivot(geoCube);
        MATRIX_STACK.rotate(geoCube);
        MATRIX_STACK.moveBackFromPivot(geoCube);
        for (GeoQuad geoQuad : geoCube.quads) {
            Vector3f vector3f;
            block25: {
                block24: {
                    block23: {
                        try {
                            if (geoQuad == null) {
                                continue;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        vector3f = new Vector3f((float)geoQuad.normal.getX(), (float)geoQuad.normal.getY(), (float)geoQuad.normal.getZ());
                        try {
                            try {
                                try {
                                    MATRIX_STACK.getNormalMatrix().transform((Tuple3f)vector3f);
                                    if (geoCube.size.y != 0.0f && geoCube.size.z != 0.0f) break block23;
                                }
                                catch (IllegalStateException illegalStateException) {
                                    throw GeoGirlRenderer.rethrow(illegalStateException);
                                }
                                if (!(vector3f.getX() < 0.0f)) break block23;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                            vector3f.x *= -1.0f;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                    }
                    try {
                        try {
                            try {
                                if (geoCube.size.x != 0.0f && geoCube.size.z != 0.0f) break block24;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                            if (!(vector3f.getY() < 0.0f)) break block24;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        vector3f.y *= -1.0f;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                }
                try {
                    try {
                        try {
                            if (geoCube.size.x != 0.0f && geoCube.size.y != 0.0f) break block25;
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        if (!(vector3f.getZ() < 0.0f)) break block25;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                    vector3f.z *= -1.0f;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
            }
            Vec3d vec3d = BoneColorHelper.applyBoneColor(this, this.CachedBone, new Vec3d((double)f, (double)f2, (double)f3), vector3f);
            for (GeoVertex geoVertex : geoQuad.vertices) {
                Vector4f vector4f = new Vector4f(geoVertex.position.getX(), geoVertex.position.getY(), geoVertex.position.getZ(), 1.0f);
                MATRIX_STACK.getModelMatrix().transform((Tuple4f)vector4f);
                bufferBuilder.pos((double)vector4f.getX(), (double)vector4f.getY(), (double)vector4f.getZ()).tex((double)geoVertex.textureU + d, (double)geoVertex.textureV).color((float)vec3d.x, (float)vec3d.y, (float)vec3d.z, f4).normal(vector3f.getX(), vector3f.getY(), vector3f.getZ()).endVertex();
            }
        }
    }

   protected ItemStack a() {
      String string = (String)this.RenderEntity.OpenProgress.get(GirlEntity.BlowjobStageKey);
      byte bv = -1;

      label76: {
         label75: {
            label74: {
               label73: {
                  label72: {
                     label71: {
                        try {
                           switch (string.hashCode()) {
                              case -20842805:
                                 break;
                              case 113766:
                                 break label74;
                              case 64419037:
                                 break label72;
                              case 95761198:
                                 if (!string.equals("doggy")) {
                                    break label76;
                                 }
                                 break label75;
                              case 109773592:
                                 break label71;
                              case 2014427283:
                                 break label73;
                              default:
                                 break label76;
                           }
                        } catch (IllegalStateException error) {
                           throw rethrow(error);
                        }

                        if (string.equals("blowjob")) {
                           bv = 1;
                        }
                        break label76;
                     }

                     if (string.equals("strip")) {
                        bv = 2;
                     }
                     break label76;
                  }

                  if (string.equals("boobjob")) {
                     bv = 3;
                  }
                  break label76;
               }

               if (string.equals("touch_boobs")) {
                  bv = 4;
               }
               break label76;
            }

            if (string.equals("sex")) {
               bv = 5;
            }
            break label76;
         }

         bv = 0;
      }

      try {
         switch (bv) {
            case 0:
               return new ItemStack(Items.DIAMOND, 2);
            case 1:
               return new ItemStack(Items.EMERALD, 3);
            case 2:
               return new ItemStack(Items.GOLD_INGOT, 1);
            case 3:
               return new ItemStack(Items.ENDER_PEARL, 2);
            case 4:
               return new ItemStack(Items.FISH, 2, 1);
            case 5:
               return new ItemStack(Items.FISH, 3, 0);
            default:
               return null;
         }
      } catch (IllegalStateException error2) {
         throw rethrow(error2);
      }
   }


   protected void b(BufferBuilder bufferBuilder, GeoBone bone) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 3[SWITCH]
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

   protected ItemStack a(@Nullable ItemStack stack) {
      return stack;
   }


   protected void a(BufferBuilder bufferBuilder, GeoBone bone) {
        ItemStack itemStack;
        block27: {
            InventoryGirlEntity inventoryGirl;
            block28: {
                block26: {
                    block25: {
                        try {
                            if (this.RenderEntity == null) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        try {
                            if (!(this.RenderEntity instanceof InventoryGirlEntity)) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        EntityDataManager entityDataManager = this.RenderEntity.getDataManager();
                        inventoryGirl = (InventoryGirlEntity)this.RenderEntity;
                        int i = (Integer)entityDataManager.get(InventoryGirlEntity.ModeKey);
                        try {
                            if (inventoryGirl.getCurrentAction() != GirlAnimationState.BOW) {
                                this.a = 0.0f;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        itemStack = null;
                        if (i == 1) {
                            itemStack = (ItemStack)entityDataManager.get(InventoryGirlEntity.L);
                        } else if (i == 2) {
                            itemStack = (ItemStack)entityDataManager.get(InventoryGirlEntity.R);
                        }
                        itemStack = this.a(itemStack);
                        try {
                            if (itemStack == null) {
                                return;
                            }
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                        try {
                            try {
                                if (!itemStack.getItem().equals(Items.BOW) || inventoryGirl.getCurrentAction() != GirlAnimationState.BOW) break block25;
                            }
                            catch (IllegalStateException illegalStateException) {
                                throw GeoGirlRenderer.rethrow(illegalStateException);
                            }
                            this.a += 0.015f;
                            inventoryGirl.getByPlayerUuid(Math.round(-this.a * 20.0f + (float)itemStack.getMaxItemUseDuration()));
                            inventoryGirl.a(itemStack);
                        }
                        catch (IllegalStateException illegalStateException) {
                            throw GeoGirlRenderer.rethrow(illegalStateException);
                        }
                    }
                    try {
                        GlStateManager.pushMatrix();
                        Tessellator.getInstance().draw();
                        MatrixUtil.applyGeoBoneTransform(MATRIX_STACK, bone);
                        GL11.glEnable((int)2896);
                        if (!(itemStack.getItem() instanceof ItemBow)) break block26;
                        GL11.glRotatef((float)inventoryGirl.K, (float)1.0f, (float)0.0f, (float)0.0f);
                        break block27;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                }
                try {
                    try {
                        if (inventoryGirl.getCurrentAction() != GirlAnimationState.ATTACK || inventoryGirl.S != 0) break block28;
                    }
                    catch (IllegalStateException illegalStateException) {
                        throw GeoGirlRenderer.rethrow(illegalStateException);
                    }
                    GlStateManager.translate((double)inventoryGirl.V.x, (double)inventoryGirl.V.y, (double)inventoryGirl.V.z);
                    GL11.glRotatef((float)inventoryGirl.O, (float)1.0f, (float)0.0f, (float)0.0f);
                    break block27;
                }
                catch (IllegalStateException illegalStateException) {
                    throw GeoGirlRenderer.rethrow(illegalStateException);
                }
            }
            GL11.glRotatef((float)inventoryGirl.P, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        Minecraft.getMinecraft().getItemRenderer().renderItem(this.RenderEntity, itemStack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
        this.bindTexture(Objects.requireNonNull(this.getEntityTexture(this.RenderEntity)));
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
        GL11.glDisable((int)2896);
        GlStateManager.popMatrix();
    }


   RayTraceResult a(Vec3d vec3d, Vec3d vec3d2, World world) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [54[UNCONDITIONALDOLOOP]], but top level block is 25[TRYBLOCK]
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

   private static Exception rethrow(Exception error) {
      return error;
   }
}
