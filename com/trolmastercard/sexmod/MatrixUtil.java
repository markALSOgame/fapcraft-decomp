package com.trolmastercard.sexmod;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import javax.vecmath.Matrix4f;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.util.MatrixStack;

public class MatrixUtil {
   public static final float[] MatrixArray = new float[16];
   public static final FloatBuffer FloatBuffer = BufferUtils.createFloatBuffer(16);
   private static final Matrix4f ScratchMatrix = new Matrix4f();

   public static void applyGeoBoneTransform(MatrixStack matrixStack, GeoBone bone) {
      ScratchMatrix.set(matrixStack.getModelMatrix());
      ScratchMatrix.transpose();
      copyMatrix4fToFloats(MatrixArray, ScratchMatrix);
      ((Buffer)FloatBuffer).clear();
      FloatBuffer.put(MatrixArray);
      ((Buffer)FloatBuffer).flip();
      GlStateManager.multMatrix(FloatBuffer);
      GlStateManager.translate(bone.rotationPointX / 16.0F, bone.rotationPointY / 16.0F, bone.rotationPointZ / 16.0F);
   }

   public static void copyMatrix4fToFloats(float[] floats, Matrix4f matrix4f) {
      floats[0] = matrix4f.m00;
      floats[1] = matrix4f.m01;
      floats[2] = matrix4f.m02;
      floats[3] = matrix4f.m03;
      floats[4] = matrix4f.m10;
      floats[5] = matrix4f.m11;
      floats[6] = matrix4f.m12;
      floats[7] = matrix4f.m13;
      floats[8] = matrix4f.m20;
      floats[9] = matrix4f.m21;
      floats[10] = matrix4f.m22;
      floats[11] = matrix4f.m23;
      floats[12] = matrix4f.m30;
      floats[13] = matrix4f.m31;
      floats[14] = matrix4f.m32;
      floats[15] = matrix4f.m33;
   }

   public static Matrix4f multiplyMatrix4f(Matrix4f matrix4f, Matrix4f matrix4f2) {
      Matrix4f matrix4f3 = (Matrix4f)matrix4f2.clone();
      matrix4f3.mul(matrix4f);
      return matrix4f3;
   }
}
