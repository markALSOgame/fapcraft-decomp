package com.trolmastercard.sexmod;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class LunaFamiliarRenderer extends Render<LunaFamiliarEntity> {
   static final double OrbitRadius = 0.1896224320030116;
   static final double HeightOffset = -0.5;
   static final double BounceRadius = 0.08742380916962415;
   private static final ResourceLocation ParticlesTexture = new ResourceLocation("textures/particle/particles.png");

   public LunaFamiliarRenderer(RenderManager renderManager) {
      super(renderManager);
   }


   public void doRender(LunaFamiliarEntity familiar, double d, double d2, double d3, float f, float f2) {
        double d4;
        int i;
        int i3;
        Vec3d vec3d;
        BufferBuilder bufferBuilder;
        LunaNpc luna;
        block28: {
            luna = familiar.getOwnerNpcClient();
            try {
                block27: {
                    try {
                        try {
                            if (luna == null || this.renderOutlines) break block27;
                        }
                        catch (RuntimeException runtimeException) {
                            throw LunaFamiliarRenderer.rethrow(runtimeException);
                        }
                        if (luna.Z != 1.0f) break block28;
                    }
                    catch (RuntimeException runtimeException) {
                        throw LunaFamiliarRenderer.rethrow(runtimeException);
                    }
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw LunaFamiliarRenderer.rethrow(runtimeException);
            }
        }
        luna.Familiar = familiar;
        ItemStack itemStack = (ItemStack)luna.getDataManager().get(LunaNpc.HeldItemStackKey);
        if (!itemStack.getItem().equals(Items.AIR)) {
            float f3 = Minecraft.getDebugFPS();
            if (f3 == 0.0f) {
                f3 = 0.1f;
            }
            luna.Z += 60.0f / f3 * 0.01666f * 2.0f;
            luna.Z = Math.min(1.0f, luna.Z);
            EntityPlayerSP mcPlayer = Minecraft.getMinecraft().player;
            Vec3d vec3d2 = LerpMath.lerpVec3d(new Vec3d(mcPlayer.lastTickPosX, mcPlayer.lastTickPosY, mcPlayer.lastTickPosZ), mcPlayer.getPositionVector(), (double)f2);
            vec3d = new Vec3d(d, d2, d3);
            Vec3d vec3d3 = LerpMath.lerpVec3d(new Vec3d(luna.lastTickPosX, luna.lastTickPosY + 0.875, luna.lastTickPosZ), luna.getPositionVector().add(0.0, 0.875, 0.0), (double)f2);
            vec3d3 = vec3d3.subtract(vec3d2);
            vec3d = LerpMath.lerpVec3d(vec3d, vec3d3, (double)luna.Z);
            d = vec3d.x;
            d2 = vec3d.y;
            d3 = vec3d.z;
        } else {
            luna.Z = 0.0f;
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((float)d), (float)((float)d2), (float)((float)d3));
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale((float)0.5f, (float)0.5f, (float)0.5f);
        this.bindEntityTexture(familiar);
        Tessellator tessellator = Tessellator.getInstance();
        bufferBuilder = tessellator.getBuffer();
        try {
            GlStateManager.rotate((float)(180.0f - this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            i3 = this.renderManager.options.thirdPersonView == 2 ? -1 : 1;
        }
        catch (RuntimeException runtimeException) {
            throw LunaFamiliarRenderer.rethrow(runtimeException);
        }
        try {
            GlStateManager.rotate((float)((float)i3 * -this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
            if (this.renderOutlines) {
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode((int)this.getTeamColor(familiar));
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaFamiliarRenderer.rethrow(runtimeException);
        }
        try {
            if (!itemStack.getItem().equals(Items.AIR)) {
                GlStateManager.scale((float)2.0f, (float)2.0f, (float)2.0f);
                GlStateManager.translate((float)0.0f, (float)-0.2f, (float)0.0f);
                Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)luna, itemStack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
                GlStateManager.scale((float)0.5f, (float)0.5f, (float)0.5f);
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaFamiliarRenderer.rethrow(runtimeException);
        }
        try {
            this.bindEntityTexture(familiar);
            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
            bufferBuilder.pos(-0.5, -0.5, 0.0).tex(0.0625, 0.1875).normal(0.0f, 1.0f, 0.0f).endVertex();
            bufferBuilder.pos(0.5, -0.5, 0.0).tex(0.125, 0.1875).normal(0.0f, 1.0f, 0.0f).endVertex();
            bufferBuilder.pos(0.5, 0.5, 0.0).tex(0.125, 0.125).normal(0.0f, 1.0f, 0.0f).endVertex();
            bufferBuilder.pos(-0.5, 0.5, 0.0).tex(0.0625, 0.125).normal(0.0f, 1.0f, 0.0f).endVertex();
            tessellator.draw();
            if (this.renderOutlines) {
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
            }
        }
        catch (RuntimeException runtimeException) {
            throw LunaFamiliarRenderer.rethrow(runtimeException);
        }
        try {
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            i = luna.getPrimaryHand() == EnumHandSide.RIGHT ? 1 : -1;
        }
        catch (RuntimeException runtimeException) {
            throw LunaFamiliarRenderer.rethrow(runtimeException);
        }
        int i4 = i;
        ItemStack heldStack = luna.getHeldItemMainhand();
        if (!(heldStack.getItem() instanceof ItemFishingRod)) {
            i4 = -i4;
        }
        luna.rotationYaw = luna.I().floatValue();
        luna.renderYawOffset = luna.I().floatValue();
        luna.posX = luna.getTargetPos().x;
        luna.posY = luna.getTargetPos().y;
        luna.posZ = luna.getTargetPos().z;
        luna.prevPosX = luna.getTargetPos().x;
        luna.prevPosY = luna.getTargetPos().y;
        luna.prevPosZ = luna.getTargetPos().z;
        float f4 = (luna.prevRenderYawOffset + (luna.renderYawOffset - luna.prevRenderYawOffset) * f2) * ((float)Math.PI / 180);
        double d5 = MathHelper.sin((float)f4);
        double d6 = MathHelper.cos((float)f4);
        double d7 = (double)i4 * 0.35;
        double d8 = luna.prevPosX + (luna.posX - luna.prevPosX) * (double)f2 - d6 * d7 - d5 * 0.8;
        double d9 = luna.prevPosY + (double)luna.getEyeHeight() + (luna.posY - luna.prevPosY) * (double)f2 - 0.45;
        double d10 = luna.prevPosZ + (luna.posZ - luna.prevPosZ) * (double)f2 - d5 * d7 + d6 * 0.8;
        try {
            d4 = luna.isSneaking() ? -0.1875 : 0.0;
        }
        catch (RuntimeException runtimeException) {
            throw LunaFamiliarRenderer.rethrow(runtimeException);
        }
        double d11 = d4;
        double d12 = familiar.prevPosX + (familiar.posX - familiar.prevPosX) * (double)f2 - Math.sin((double)(luna.I().floatValue() + 90.0f) * (Math.PI / 180)) * 0.1896224320030116 - Math.sin((double)luna.I().floatValue() * (Math.PI / 180)) * 0.08742380916962415;
        double d27 = familiar.prevPosY + (familiar.posY - familiar.prevPosY) * (double)f2 + 0.25 + -0.5;
        double d28 = familiar.prevPosZ + (familiar.posZ - familiar.prevPosZ) * (double)f2 + Math.cos((double)(luna.I().floatValue() + 90.0f) * (Math.PI / 180)) * 0.1896224320030116 + Math.cos((double)luna.I().floatValue() * (Math.PI / 180)) * 0.08742380916962415;
        double d29 = (float)(d8 - d12);
        double d30 = (double)((float)(d9 - d27)) + d11;
        double d31 = (float)(d10 - d28);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        if (itemStack.getItem().equals(Items.AIR)) {
            bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for (int i5 = 0; i5 <= 16; ++i5) {
                float f5 = (float)i5 / 16.0f;
                bufferBuilder.pos(d + d29 * (double)f5, d2 + d30 * (double)(f5 * f5 + f5) * 0.5 + 0.25, d3 + d31 * (double)f5).color(0, 0, 0, 255).endVertex();
            }
            tessellator.draw();
        }
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        super.doRender(familiar, d, d2, d3, f, f2);
    }

   @Nullable
   protected ResourceLocation getEntityTexture(LunaFamiliarEntity familiar) {
      return ParticlesTexture;
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
