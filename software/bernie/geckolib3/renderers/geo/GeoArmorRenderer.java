package software.bernie.geckolib3.renderers.geo;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.util.GeoUtils;

public abstract class GeoArmorRenderer<T extends ItemArmor & IAnimatable> extends ModelBiped implements IGeoRenderer<T> {
   private static Map<Class<? extends ItemArmor>, GeoArmorRenderer> renderers = new ConcurrentHashMap<>();
   private T currentArmorItem;
   private EntityLivingBase entityLiving;
   private ItemStack itemStack;
   private EntityEquipmentSlot armorSlot;
   public String headBone = "armorHead";
   public String bodyBone = "armorBody";
   public String rightArmBone = "armorRightArm";
   public String leftArmBone = "armorLeftArm";
   public String rightLegBone = "armorRightLeg";
   public String leftLegBone = "armorLeftLeg";
   public String rightBootBone = "armorRightBoot";
   public String leftBootBone = "armorLeftBoot";
   private final AnimatedGeoModel<T> modelProvider;

   public static void registerArmorRenderer(Class<? extends ItemArmor> itemClass, GeoArmorRenderer renderer) {
      renderers.put(itemClass, renderer);
   }

   public static GeoArmorRenderer getRenderer(Class<? extends ItemArmor> item) {
      return renderers.get(item);
   }

   public GeoArmorRenderer(AnimatedGeoModel<T> modelProvider) {
      super(1.0F);
      this.modelProvider = modelProvider;
   }

   public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.render(ageInTicks);
   }

   public void render(float partialTicks) {
      GlStateManager.func_179137_b(0.0, 1.501F, 0.0);
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(this.currentArmorItem));
      AnimationEvent itemEvent = new AnimationEvent(
         this.currentArmorItem, 0.0F, 0.0F, 0.0F, false, Arrays.asList(this.itemStack, this.entityLiving, this.armorSlot)
      );
      this.modelProvider.setLivingAnimations(this.currentArmorItem, this.getUniqueID(this.currentArmorItem), itemEvent);
      this.fitToBiped();
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(0.0F, 0.01F, 0.0F);
      IBone rightArmBone = this.modelProvider.getBone(this.rightArmBone);
      IBone leftArmBone = this.modelProvider.getBone(this.leftArmBone);
      if (this.field_78095_p > 0.0F) {
         rightArmBone.setScaleZ(1.25F);
         rightArmBone.setScaleX(1.25F);
         leftArmBone.setScaleZ(1.3F);
         leftArmBone.setScaleX(1.05F);
      }

      if (this.field_78117_n) {
         IBone headBone = this.modelProvider.getBone(this.headBone);
         IBone bodyBone = this.modelProvider.getBone(this.bodyBone);
         IBone rightLegBone = this.modelProvider.getBone(this.rightLegBone);
         IBone leftLegBone = this.modelProvider.getBone(this.leftLegBone);
         IBone rightBootBone = this.modelProvider.getBone(this.rightBootBone);
         IBone leftBootBone = this.modelProvider.getBone(this.leftBootBone);

         try {
            headBone.setPositionY(headBone.getPositionY() - 1.0F);
            bodyBone.setPositionZ(bodyBone.getPositionX() - 0.4F);
            bodyBone.setPositionY(bodyBone.getPositionX() - 3.5F);
            rightArmBone.setPositionY(bodyBone.getPositionX() - 3.0F);
            rightArmBone.setPositionX(bodyBone.getPositionX() + 0.35F);
            leftArmBone.setPositionY(bodyBone.getPositionX() - 3.0F);
            leftArmBone.setPositionX(bodyBone.getPositionX() - 0.35F);
            rightLegBone.setPositionZ(bodyBone.getPositionX() + 4.0F);
            leftLegBone.setPositionZ(bodyBone.getPositionX() + 4.0F);
            rightBootBone.setPositionZ(bodyBone.getPositionX() + 4.0F);
            leftBootBone.setPositionZ(bodyBone.getPositionX() + 4.0F);
         } catch (Exception e) {
            throw new RuntimeException("Could not find an armor bone.", e);
         }
      }

      Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getTextureLocation(this.currentArmorItem));
      Color renderColor = this.getRenderColor(this.currentArmorItem, partialTicks);
      this.render(
         model,
         this.currentArmorItem,
         partialTicks,
         renderColor.getRed() / 255.0F,
         renderColor.getGreen() / 255.0F,
         renderColor.getBlue() / 255.0F,
         renderColor.getAlpha() / 255.0F
      );
      GlStateManager.func_179121_F();
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      GlStateManager.func_179137_b(0.0, -1.501F, 0.0);
   }

   private void fitToBiped() {
      IBone headBone = this.modelProvider.getBone(this.headBone);
      IBone bodyBone = this.modelProvider.getBone(this.bodyBone);
      IBone rightArmBone = this.modelProvider.getBone(this.rightArmBone);
      IBone leftArmBone = this.modelProvider.getBone(this.leftArmBone);
      IBone rightLegBone = this.modelProvider.getBone(this.rightLegBone);
      IBone leftLegBone = this.modelProvider.getBone(this.leftLegBone);
      IBone rightBootBone = this.modelProvider.getBone(this.rightBootBone);
      IBone leftBootBone = this.modelProvider.getBone(this.leftBootBone);

      try {
         if (!(this.entityLiving instanceof EntityArmorStand)) {
            GeoUtils.copyRotations(this.field_78116_c, headBone);
            GeoUtils.copyRotations(this.field_78115_e, bodyBone);
            GeoUtils.copyRotations(this.field_178723_h, rightArmBone);
            GeoUtils.copyRotations(this.field_178724_i, leftArmBone);
            GeoUtils.copyRotations(this.field_178721_j, rightLegBone);
            GeoUtils.copyRotations(this.field_178722_k, leftLegBone);
            GeoUtils.copyRotations(this.field_178721_j, rightBootBone);
            GeoUtils.copyRotations(this.field_178722_k, leftBootBone);
         }
      } catch (Exception e) {
         throw new RuntimeException("Could not find an armor bone.", e);
      }
   }

   public AnimatedGeoModel<T> getGeoModelProvider() {
      return this.modelProvider;
   }

   public ResourceLocation getTextureLocation(T instance) {
      return this.modelProvider.getTextureLocation(instance);
   }

   public void setCurrentItem(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot) {
      this.entityLiving = entityLiving;
      this.itemStack = itemStack;
      this.armorSlot = armorSlot;
      this.currentArmorItem = (T)itemStack.func_77973_b();
   }

   public final GeoArmorRenderer applyEntityStats(ModelBiped defaultArmor) {
      this.field_78091_s = defaultArmor.field_78091_s;
      this.field_78117_n = defaultArmor.field_78117_n;
      this.field_78093_q = defaultArmor.field_78093_q;
      this.field_187076_m = defaultArmor.field_187076_m;
      this.field_187075_l = defaultArmor.field_187075_l;
      return this;
   }

   public GeoArmorRenderer applySlot(EntityEquipmentSlot slot) {
      this.modelProvider.getModel(this.modelProvider.getModelLocation(this.currentArmorItem));
      IBone headBone = this.modelProvider.getBone(this.headBone);
      IBone bodyBone = this.modelProvider.getBone(this.bodyBone);
      IBone rightArmBone = this.modelProvider.getBone(this.rightArmBone);
      IBone leftArmBone = this.modelProvider.getBone(this.leftArmBone);
      IBone rightLegBone = this.modelProvider.getBone(this.rightLegBone);
      IBone leftLegBone = this.modelProvider.getBone(this.leftLegBone);
      IBone rightBootBone = this.modelProvider.getBone(this.rightBootBone);
      IBone leftBootBone = this.modelProvider.getBone(this.leftBootBone);

      try {
         headBone.setHidden(true);
         bodyBone.setHidden(true);
         rightArmBone.setHidden(true);
         leftArmBone.setHidden(true);
         rightLegBone.setHidden(true);
         leftLegBone.setHidden(true);
         rightBootBone.setHidden(true);
         leftBootBone.setHidden(true);
         switch (slot) {
            case HEAD:
               headBone.setHidden(false);
               break;
            case CHEST:
               bodyBone.setHidden(false);
               rightArmBone.setHidden(false);
               leftArmBone.setHidden(false);
               break;
            case LEGS:
               rightLegBone.setHidden(false);
               leftLegBone.setHidden(false);
               break;
            case FEET:
               rightBootBone.setHidden(false);
               leftBootBone.setHidden(false);
         }

         return this;
      } catch (Exception e) {
         throw new RuntimeException("Could not find an armor bone.", e);
      }
   }

   public Integer getUniqueID(T animatable) {
      return Objects.hash(
         this.armorSlot,
         this.itemStack.func_77973_b(),
         this.itemStack.func_190916_E(),
         this.itemStack.func_77942_o() ? this.itemStack.func_77978_p().toString() : 1,
         this.entityLiving.func_110124_au().toString()
      );
   }

   static {
      AnimationController.addModelFetcher(object -> {
         if (object instanceof ItemArmor) {
            GeoArmorRenderer renderer = renderers.get(object.getClass());
            return renderer == null ? null : renderer.getGeoModelProvider();
         } else {
            return null;
         }
      });
   }
}
