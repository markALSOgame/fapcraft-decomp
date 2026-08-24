package software.bernie.geckolib3.model;

import java.util.Collections;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.animation.AnimationTicker;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.processor.AnimationProcessor;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.exception.GeoModelException;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.IAnimatableModelProvider;
import software.bernie.geckolib3.resource.GeckoLibCache;
import software.bernie.geckolib3.util.MolangUtils;
import software.bernie.shadowed.eliotlash.molang.MolangParser;

public abstract class AnimatedGeoModel<T extends IAnimatable> extends GeoModelProvider<T> implements IAnimatableModel<T>, IAnimatableModelProvider<T> {
   private final AnimationProcessor animationProcessor = new AnimationProcessor(this);
   private GeoModel currentModel;

   protected AnimatedGeoModel() {
   }

   public void registerBone(GeoBone bone) {
      this.registerModelRenderer(bone);

      for (GeoBone childBone : bone.childBones) {
         this.registerBone(childBone);
      }
   }

   public void setLivingAnimations(T entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
      AnimationData manager = entity.getFactory().getOrCreateAnimationData(uniqueID);
      if (manager.ticker == null) {
         AnimationTicker ticker = new AnimationTicker(manager);
         manager.ticker = ticker;
         MinecraftForge.EVENT_BUS.register(ticker);
      }

      if (Minecraft.func_71410_x().func_147113_T() && !manager.shouldPlayWhilePaused) {
         this.seekTime = manager.tick;
      } else {
         this.seekTime = manager.tick + Minecraft.func_71410_x().func_184121_ak();
      }

      AnimationEvent<T> predicate;
      if (customPredicate == null) {
         predicate = new AnimationEvent<>(entity, 0.0F, 0.0F, (float)(manager.tick - this.lastGameTickTime), false, Collections.emptyList());
      } else {
         predicate = customPredicate;
      }

      predicate.animationTick = this.seekTime;
      this.animationProcessor.preAnimationSetup(predicate.getAnimatable(), this.seekTime);
      if (!this.animationProcessor.getModelRendererList().isEmpty()) {
         this.animationProcessor.tickAnimation(entity, uniqueID, this.seekTime, predicate, GeckoLibCache.getInstance().parser, this.shouldCrashOnMissing);
      }
   }

   @Override
   public AnimationProcessor getAnimationProcessor() {
      return this.animationProcessor;
   }

   public void registerModelRenderer(IBone modelRenderer) {
      this.animationProcessor.registerModelRenderer(modelRenderer);
   }

   @Override
   public Animation getAnimation(String name, IAnimatable animatable) {
      return GeckoLibCache.getInstance().getAnimations().get(this.getAnimationFileLocation((T)animatable)).getAnimation(name);
   }

   @Override
   public GeoModel getModel(ResourceLocation location) {
      GeoModel model = super.getModel(location);
      if (model == null) {
         throw new GeoModelException(location, "Could not find model.");
      }

      if (model != this.currentModel) {
         this.animationProcessor.clearModelRendererList();

         for (GeoBone bone : model.topLevelBones) {
            this.registerBone(bone);
         }

         this.currentModel = model;
      }

      return model;
   }

   @Override
   public void setMolangQueries(IAnimatable animatable, double currentTick) {
      MolangParser parser = GeckoLibCache.getInstance().parser;
      Minecraft minecraftInstance = Minecraft.func_71410_x();
      float partialTick = minecraftInstance.func_184121_ak();
      parser.setValue("query.actor_count", minecraftInstance.field_71441_e.field_72996_f.size());
      parser.setValue("query.time_of_day", MolangUtils.normalizeTime(minecraftInstance.field_71441_e.func_82737_E()));
      parser.setValue("query.moon_phase", minecraftInstance.field_71441_e.func_72853_d());
      if (animatable instanceof Entity) {
         Entity entity = (Entity)animatable;
         Entity camera = minecraftInstance.func_175606_aa();
         Vec3d entityCamera = new Vec3d(
            camera.field_70169_q + (camera.field_70165_t - camera.field_70169_q) * partialTick,
            camera.field_70167_r + (camera.field_70163_u - camera.field_70167_r) * partialTick,
            camera.field_70166_s + (camera.field_70161_v - camera.field_70166_s) * partialTick
         );
         Vec3d entityPosition = new Vec3d(
            entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * partialTick,
            entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * partialTick,
            entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * partialTick
         );
         double distance = entityCamera.func_178787_e(ActiveRenderInfo.getCameraPosition()).func_72438_d(entityPosition);
         parser.setValue("query.distance_from_camera", distance);
         parser.setValue("query.is_on_ground", MolangUtils.booleanToFloat(((Entity)animatable).field_70122_E));
         parser.setValue("query.is_in_water", MolangUtils.booleanToFloat(((Entity)animatable).func_70090_H()));
         parser.setValue("query.is_in_water_or_rain", MolangUtils.booleanToFloat(((Entity)animatable).func_70026_G()));
         if (animatable instanceof EntityLivingBase) {
            EntityLivingBase livingEntity = (EntityLivingBase)animatable;
            parser.setValue("query.health", livingEntity.func_110143_aJ());
            parser.setValue("query.max_health", livingEntity.func_110138_aP());
            parser.setValue("query.is_on_fire", MolangUtils.booleanToFloat(livingEntity.func_70027_ad()));
            double dx = livingEntity.field_70159_w;
            double dz = livingEntity.field_70179_y;
            float groundSpeed = MathHelper.func_76133_a(dx * dx + dz * dz);
            parser.setValue("query.ground_speed", groundSpeed);
            float yawSpeed = this.getYaw(livingEntity, Minecraft.func_71410_x().func_184121_ak())
               - this.getYaw(livingEntity, (float)(Minecraft.func_71410_x().func_184121_ak() - 0.1));
            parser.setValue("query.yaw_speed", yawSpeed);
         }
      }
   }

   private float getYaw(EntityLivingBase entity, float tick) {
      return entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * tick;
   }

   @Override
   public double getCurrentTick() {
      return Minecraft.func_71386_F() / 50.0;
   }
}
