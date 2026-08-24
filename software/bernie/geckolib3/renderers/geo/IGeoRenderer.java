package software.bernie.geckolib3.renderers.geo;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoCube;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoQuad;
import software.bernie.geckolib3.geo.render.built.GeoVertex;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.util.MatrixStack;

public interface IGeoRenderer<T> {
   MatrixStack MATRIX_STACK = new MatrixStack();

   default void render(GeoModel model, T animatable, float partialTicks, float red, float green, float blue, float alpha) {
      GlStateManager.func_179129_p();
      GlStateManager.func_179091_B();
      this.renderEarly(animatable, partialTicks, red, green, blue, alpha);
      this.renderLate(animatable, partialTicks, red, green, blue, alpha);
      BufferBuilder builder = Tessellator.func_178181_a().func_178180_c();
      builder.func_181668_a(7, DefaultVertexFormats.field_181712_l);

      for (GeoBone group : model.topLevelBones) {
         this.renderRecursively(builder, group, red, green, blue, alpha);
      }

      Tessellator.func_178181_a().func_78381_a();
      this.renderAfter(animatable, partialTicks, red, green, blue, alpha);
      GlStateManager.func_179101_C();
      GlStateManager.func_179089_o();
   }

   default void renderRecursively(BufferBuilder builder, GeoBone bone, float red, float green, float blue, float alpha) {
      MATRIX_STACK.push();
      MATRIX_STACK.translate(bone);
      MATRIX_STACK.moveToPivot(bone);
      MATRIX_STACK.rotate(bone);
      MATRIX_STACK.scale(bone);
      MATRIX_STACK.moveBackFromPivot(bone);
      if (!bone.isHidden()) {
         for (GeoCube cube : bone.childCubes) {
            MATRIX_STACK.push();
            GlStateManager.func_179094_E();
            this.renderCube(builder, cube, red, green, blue, alpha);
            GlStateManager.func_179121_F();
            MATRIX_STACK.pop();
         }
      }

      if (!bone.childBonesAreHiddenToo()) {
         for (GeoBone childBone : bone.childBones) {
            this.renderRecursively(builder, childBone, red, green, blue, alpha);
         }
      }

      MATRIX_STACK.pop();
   }

   default void renderCube(BufferBuilder builder, GeoCube cube, float red, float green, float blue, float alpha) {
      MATRIX_STACK.moveToPivot(cube);
      MATRIX_STACK.rotate(cube);
      MATRIX_STACK.moveBackFromPivot(cube);

      for (GeoQuad quad : cube.quads) {
         Vector3f normal = new Vector3f(quad.normal.func_177958_n(), quad.normal.func_177956_o(), quad.normal.func_177952_p());
         MATRIX_STACK.getNormalMatrix().transform(normal);
         if ((cube.size.y == 0.0F || cube.size.z == 0.0F) && normal.getX() < 0.0F) {
            normal.x *= -1.0F;
         }

         if ((cube.size.x == 0.0F || cube.size.z == 0.0F) && normal.getY() < 0.0F) {
            normal.y *= -1.0F;
         }

         if ((cube.size.x == 0.0F || cube.size.y == 0.0F) && normal.getZ() < 0.0F) {
            normal.z *= -1.0F;
         }

         for (GeoVertex vertex : quad.vertices) {
            Vector4f vector4f = new Vector4f(vertex.position.getX(), vertex.position.getY(), vertex.position.getZ(), 1.0F);
            MATRIX_STACK.getModelMatrix().transform(vector4f);
            builder.func_181662_b(vector4f.getX(), vector4f.getY(), vector4f.getZ())
               .func_187315_a(vertex.textureU, vertex.textureV)
               .func_181666_a(red, green, blue, alpha)
               .func_181663_c(normal.getX(), normal.getY(), normal.getZ())
               .func_181675_d();
         }
      }
   }

   GeoModelProvider getGeoModelProvider();

   ResourceLocation getTextureLocation(T var1);

   default void renderEarly(T animatable, float ticks, float red, float green, float blue, float partialTicks) {
   }

   default void renderLate(T animatable, float ticks, float red, float green, float blue, float partialTicks) {
   }

   default void renderAfter(T animatable, float ticks, float red, float green, float blue, float partialTicks) {
   }

   default Color getRenderColor(T animatable, float partialTicks) {
      return Color.ofRGBA(255, 255, 255, 255);
   }

   default Integer getUniqueID(T animatable) {
      return animatable.hashCode();
   }
}
