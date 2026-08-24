package com.trolmastercard.sexmod;

import java.util.HashSet;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoblinPlayerRenderer extends CustomColorGirlRenderer {
   GoblinPlayer RenderedGoblin = null;
   boolean IsShoulderCarried = false;
   boolean IsPickingUp = false;
   boolean D = false;

   public GoblinPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
   }

   @Override

   protected Vec3i getBoneColor(String string) {
        String[] stringArray;
        block24: {
            block23: {
                stringArray = GirlEffectEntity.getAttributeStrings(this.RenderEntity);
                try {
                    if (stringArray.length < 8) {
                        return z;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayerRenderer.rethrow(runtimeException);
                }
                try {
                    if (string.contains("band")) {
                        return GoblinNpcRenderer.DefaultColor;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayerRenderer.rethrow(runtimeException);
                }
                try {
                    try {
                        if (!string.contains("eyeColor") && !string.contains("eyeColor2")) break block23;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayerRenderer.rethrow(runtimeException);
                    }
                    return GoblinNpcRenderer.parseTribeColor(stringArray[8]);
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayerRenderer.rethrow(runtimeException);
                }
            }
            try {
                try {
                    if (!string.contains("variant") && !string.contains("boob")) break block24;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayerRenderer.rethrow(runtimeException);
                }
                return GoblinNpcRenderer.parseGirlColor(stringArray[7]);
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayerRenderer.rethrow(runtimeException);
            }
        }
        try {
            if (string.contains("hair")) {
                return GoblinNpcRenderer.parseMarkColor(stringArray[6]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayerRenderer.rethrow(runtimeException);
        }
        try {
            if (GoblinNpcRenderer.MeatParts.contains(string)) {
                return GoblinNpcRenderer.parseGirlColor(stringArray[7]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayerRenderer.rethrow(runtimeException);
        }
        try {
            if (GoblinNpcRenderer.EyeBrowParts.contains(string)) {
                return GoblinNpcRenderer.parseMarkColor(stringArray[6]);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayerRenderer.rethrow(runtimeException);
        }
        return z;
    }

   @Override
   protected Vector4f getBoneTint(String string, float f, float f2, float f3) {
      if (string.startsWith("crown")) {
         ItemStack stack = (ItemStack)this.RenderEntity.getDataManager().get(InventoryGirlEntity.HelmetKey);

         try {
            if (stack.isEmpty()) {
               return super.getBoneTint(string, f, f2, f3);
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
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
               f = f5;
               f2 = f6;
               f3 = f7;
         }

         return new Vector4f(f, f2, f3, 72.0F * f4 / 4096.0F);
      } else {
         return super.getBoneTint(string, f, f2, f3);
      }
   }

   @Override
   protected boolean isBoneVisible(String string) {
      try {
         if (string.startsWith("crown")) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return super.isBoneVisible(string);
   }

   @Override
   public HashSet<String> getAdultParts() {
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
   protected void applyBoneState(String string, GeoBone bone) {
      String[] stringArray = GirlEffectEntity.getAttributeStrings(this.RenderEntity);

      try {
         if (stringArray.length < 8) {
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      String string2 = string;
      byte bv = -1;

      label111: {
         label110: {
            label109: {
               label108: {
                  label107: {
                     label106: {
                        label105: {
                           label104: {
                              try {
                                 switch (string2.hashCode()) {
                                    case -1383396097:
                                       break label108;
                                    case -1383396096:
                                       break label109;
                                    case 2364452:
                                       break label106;
                                    case 3029410:
                                       break label105;
                                    case 3105718:
                                       if (!string2.equals("earL")) {
                                          break label111;
                                       }
                                       break label110;
                                    case 3105724:
                                       break;
                                    case 3194850:
                                       break label104;
                                    case 93921650:
                                       break label107;
                                    default:
                                       break label111;
                                 }
                              } catch (RuntimeException error2) {
                                 throw rethrow(error2);
                              }

                              if (string2.equals("earR")) {
                                 bv = 1;
                              }
                              break label111;
                           }

                           if (string2.equals("hair")) {
                              bv = 2;
                           }
                           break label111;
                        }

                        if (string2.equals("body")) {
                           bv = 3;
                        }
                        break label111;
                     }

                     if (string2.equals("LegR")) {
                        bv = 4;
                     }
                     break label111;
                  }

                  if (string2.equals("boobR")) {
                     bv = 5;
                  }
                  break label111;
               }

               if (string2.equals("boobR1")) {
                  bv = 6;
               }
               break label111;
            }

            if (string2.equals("boobR2")) {
               bv = 7;
            }
            break label111;
         }

         bv = 0;
      }

      label82: {
         label81: {
            label80: {
               label79: {
                  label78: {
                     label77: {
                        label76: {
                           try {
                              switch (bv) {
                                 case 0:
                                    GoblinNpcRenderer.applyChildBoneVisibility(bone, stringArray[0], stringArray[1], stringArray[3]);
                                    break label82;
                                 case 1:
                                    break label81;
                                 case 2:
                                    break label80;
                                 case 3:
                                    break label79;
                                 case 4:
                                    break label78;
                                 case 5:
                                    break label77;
                                 case 6:
                                    break label76;
                                 case 7:
                                    break;
                                 default:
                                    break label82;
                              }
                           } catch (RuntimeException error3) {
                              throw rethrow(error3);
                           }

                           GoblinNpcRenderer.applyBoneWobble(this.IsShoulderCarried, bone, 5.0F, 3.0F);
                           break label82;
                        }

                        GoblinNpcRenderer.applyBoneWobble(this.IsShoulderCarried, bone, 10.0F, 15.0F);
                        break label82;
                     }

                     GoblinNpcRenderer.applyBoneWobble(this.IsShoulderCarried, bone, 30.0F, 30.0F);
                     break label82;
                  }

                  GoblinNpcRenderer.applyBoneWobble(this.IsShoulderCarried, bone, 25.0F, 25.0F);
                  break label82;
               }

               bone.setPivotY(-0.15F);
               GoblinNpcRenderer.applyShoulderIdlePose(this.RenderEntity, bone);
               break label82;
            }

            GoblinNpcRenderer.applyBoneState(bone, stringArray[5]);
            break label82;
         }

         GoblinNpcRenderer.applyChildBoneVisibility(bone, stringArray[0], stringArray[2], stringArray[4]);
      }

      try {
         if (string.contains("crown")) {
            GoblinNpcRenderer.applyBoneAppearanceState(this.RenderEntity, bone, stringArray[9]);
         }
      } catch (RuntimeException error4) {
         throw rethrow(error4);
      }
   }

   @Override

   public void render(GirlEntity girl, double d, double d2, double d3, float f, float f2) {
        block80: {
            block73: {
                block77: {
                    block70: {
                        block75: {
                            block76: {
                                block71: {
                                    block72: {
                                        block68: {
                                            block69: {
                                                block67: {
                                                    block66: {
                                                        block65: {
                                                            block64: {
                                                                try {
                                                                    try {
                                                                        this.D = GoblinPlayerRenderer.RenderTrigger;
                                                                        this.RenderedGoblin = (GoblinPlayer)girl;
                                                                        renderer = this;
                                                                        if (-420.69f != f || girl.getCurrentAction() != GirlAnimationState.SHOULDER_IDLE) break block64;
                                                                    }
                                                                    catch (RuntimeException error) {
                                                                        throw GoblinPlayerRenderer.rethrow(error);
                                                                    }
                                                                    flag = true;
                                                                    break block65;
                                                                }
                                                                catch (RuntimeException error2) {
                                                                    throw GoblinPlayerRenderer.rethrow(error2);
                                                                }
                                                            }
                                                            flag = false;
                                                        }
                                                        try {
                                                            try {
                                                                renderer.C = flag;
                                                                renderer2 = this;
                                                                if (-420.69f != f || girl.getCurrentAction() != GirlAnimationState.PICK_UP) break block66;
                                                            }
                                                            catch (RuntimeException error3) {
                                                                throw GoblinPlayerRenderer.rethrow(error3);
                                                            }
                                                            flag2 = true;
                                                            break block67;
                                                        }
                                                        catch (RuntimeException error4) {
                                                            throw GoblinPlayerRenderer.rethrow(error4);
                                                        }
                                                    }
                                                    flag2 = false;
                                                }
                                                renderer2.E = flag2;
                                                this.RenderTick = f2;
                                                GoblinNpcRenderer.B = f;
                                                girlAnimationState = girl.getCurrentAction();
                                                uuid = this.RenderedGoblin.e();
                                                if (girl.isTracked()) {
                                                    vec3d = GoblinNpcRenderer.a(girl.world, girl, uuid, d, d2, d3);
                                                    d = vec3d.x;
                                                    d2 = vec3d.y;
                                                    d3 = vec3d.z;
                                                }
                                                try {
                                                    try {
                                                        try {
                                                            try {
                                                                try {
                                                                    if (girlAnimationState != GirlAnimationState.THROWN && girlAnimationState != GirlAnimationState.START_THROWING) break block68;
                                                                }
                                                                catch (RuntimeException error5) {
                                                                    throw GoblinPlayerRenderer.rethrow(error5);
                                                                }
                                                                if (GoblinPlayerRenderer.Mc.gameSettings.thirdPersonView != 0) break block69;
                                                            }
                                                            catch (RuntimeException error6) {
                                                                throw GoblinPlayerRenderer.rethrow(error6);
                                                            }
                                                            if (f != -420.69f) break block69;
                                                        }
                                                        catch (RuntimeException error7) {
                                                            throw GoblinPlayerRenderer.rethrow(error7);
                                                        }
                                                        if (girl.isTracked()) break block69;
                                                    }
                                                    catch (RuntimeException error8) {
                                                        throw GoblinPlayerRenderer.rethrow(error8);
                                                    }
                                                    return;
                                                }
                                                catch (RuntimeException error9) {
                                                    throw GoblinPlayerRenderer.rethrow(error9);
                                                }
                                            }
                                            if (!girl.isTracked()) {
                                                girl.prevRenderYawOffset = f3 = girl.I().floatValue();
                                                girl.renderYawOffset = f3;
                                            }
                                        }
                                        try {
                                            try {
                                                try {
                                                    if (!GoblinNpcRenderer.a(girl, girlAnimationState)) break block70;
                                                    if (!GoblinPlayerRenderer.Mc.player.getPersistentID().equals(uuid)) break block71;
                                                }
                                                catch (RuntimeException error10) {
                                                    throw GoblinPlayerRenderer.rethrow(error10);
                                                }
                                                if (-420.69f == f) break block72;
                                            }
                                            catch (RuntimeException error11) {
                                                throw GoblinPlayerRenderer.rethrow(error11);
                                            }
                                            return;
                                        }
                                        catch (RuntimeException error12) {
                                            throw GoblinPlayerRenderer.rethrow(error12);
                                        }
                                    }
                                    girl.renderYawOffset = GoblinPlayerRenderer.Mc.player.rotationYaw + 180.0f;
                                    girl.prevRenderYawOffset = GoblinPlayerRenderer.Mc.player.rotationYaw + 180.0f;
                                    vec3d2 = GoblinPlayerRenderer.Mc.player.getLookVec();
                                    GlStateManager.pushMatrix();
                                    GlStateManager.translate((double)vec3d2.x, (double)(vec3d2.y + (double)GoblinPlayerRenderer.Mc.player.getEyeHeight()), (double)vec3d2.z);
                                    vec3d3 = GoblinNpc.rotateVec3dYaw(new Vec3d((double)(-Math.abs(GoblinPlayerRenderer.Mc.player.rotationPitch)), 0.0, 0.0), GoblinPlayerRenderer.Mc.player.rotationYaw);
                                    GlStateManager.rotate((float)GoblinPlayerRenderer.Mc.player.rotationPitch, (float)((float)vec3d3.x), (float)0.0f, (float)((float)vec3d3.z));
                                    d = 0.0;
                                    d2 = 0.0;
                                    d3 = 0.0;
                                    break block73;
                                }
                                try {
                                    try {
                                        block74: {
                                            try {
                                                try {
                                                    try {
                                                        if (this.RenderedGoblin.getBoundPlayerUuid().equals(GoblinPlayerRenderer.Mc.player.getPersistentID())) break block73;
                                                        if (!girl.isTracked()) break block74;
                                                    }
                                                    catch (RuntimeException error13) {
                                                        throw GoblinPlayerRenderer.rethrow(error13);
                                                    }
                                                    if (uuid == null) break block74;
                                                }
                                                catch (RuntimeException error14) {
                                                    throw GoblinPlayerRenderer.rethrow(error14);
                                                }
                                                if (!GoblinPlayerRenderer.Mc.player.getPersistentID().equals(uuid)) break block75;
                                            }
                                            catch (RuntimeException error15) {
                                                throw GoblinPlayerRenderer.rethrow(error15);
                                            }
                                        }
                                        if (uuid == null) break block76;
                                    }
                                    catch (RuntimeException error16) {
                                        throw GoblinPlayerRenderer.rethrow(error16);
                                    }
                                    if (GoblinPlayerRenderer.Mc.player.getPersistentID().equals(uuid)) break block76;
                                }
                                catch (RuntimeException error17) {
                                    throw GoblinPlayerRenderer.rethrow(error17);
                                }
                                player2 = girl.world.getPlayerEntityByUUID(uuid);
                                try {
                                    if (player2 == null) ** GOTO lbl137
                                    girl.renderYawOffset = player2.rotationYaw;
                                    girl.prevRenderYawOffset = player2.rotationYaw;
                                }
                                catch (RuntimeException error18) {
                                    throw GoblinPlayerRenderer.rethrow(error18);
                                }
                            }
                            girl.renderYawOffset = GoblinPlayerRenderer.Mc.player.rotationYaw;
                            girl.prevRenderYawOffset = GoblinPlayerRenderer.Mc.player.rotationYaw;
                        }
                        vec3d4 = GoblinNpcRenderer.getThrowAimOffset(girl, this.RenderedGoblin.e(), f2);
                        d = vec3d4.x;
                        d2 = vec3d4.y;
                        d3 = vec3d4.z;
                        break block73;
                    }
                    if (this.IsShoulderCarried) {
                        GoblinNpcRenderer.rotateCameraToGirl(f2);
                        vec3d5 = new Vec3d((double)LerpMath.a(-0.1f, 0.2f, GoblinPlayerRenderer.Mc.gameSettings.fovSetting / 110.0f), 0.0, 0.0);
                        vec3d5 = GoblinNpc.rotateVec3dYaw(vec3d5, GoblinPlayerRenderer.Mc.player.rotationYaw);
                        d = vec3d5.x;
                        d2 = vec3d5.y;
                        d3 = vec3d5.z;
                        girl.renderYawOffset = GoblinPlayerRenderer.Mc.player.rotationYaw;
                        girl.prevRenderYawOffset = GoblinPlayerRenderer.Mc.player.prevRotationYaw;
                        if (GoblinPlayerRenderer.Mc.player.isSneaking()) {
                            d2 -= 0.075;
                        }
                    } else {
                        block79: {
                            block78: {
                                try {
                                    try {
                                        if (girlAnimationState != GirlAnimationState.SHOULDER_IDLE) break block77;
                                        if (uuid != null) break block78;
                                    }
                                    catch (RuntimeException error19) {
                                        throw GoblinPlayerRenderer.rethrow(error19);
                                    }
                                    return;
                                }
                                catch (RuntimeException error20) {
                                    throw GoblinPlayerRenderer.rethrow(error20);
                                }
                            }
                            try {
                                try {
                                    if (!GoblinPlayerRenderer.Mc.player.getPersistentID().equals(uuid) || GoblinPlayerRenderer.Mc.gameSettings.thirdPersonView != 0) break block79;
                                }
                                catch (RuntimeException error21) {
                                    throw GoblinPlayerRenderer.rethrow(error21);
                                }
                                return;
                            }
                            catch (RuntimeException error22) {
                                throw GoblinPlayerRenderer.rethrow(error22);
                            }
                        }
                        player3 = girl.world.getPlayerEntityByUUID(uuid);
                        try {
                            if (player3 == null) {
                                return;
                            }
                        }
                        catch (RuntimeException error23) {
                            throw GoblinPlayerRenderer.rethrow(error23);
                        }
                        vector4f = GoblinNpcRenderer.renderGirlBox(player3, f2);
                        d = vector4f.x;
                        d2 = vector4f.y;
                        d3 = vector4f.z;
                        girl.renderYawOffset = vector4f.w;
                        if (player3.isSneaking()) {
                            d2 -= 0.32;
                        }
                    }
                    break block73;
                }
                try {
                    if (girlAnimationState != GirlAnimationState.PICK_UP || uuid == null) break block73;
                }
                catch (RuntimeException error24) {
                    throw GoblinPlayerRenderer.rethrow(error24);
                }
                player4 = girl.world.getPlayerEntityByUUID(uuid);
                try {
                    if (player4 != null) {
                        girl.prevRenderYawOffset = player4.prevRotationYawHead;
                        girl.renderYawOffset = player4.rotationYawHead;
                    }
                }
                catch (RuntimeException error25) {
                    throw GoblinPlayerRenderer.rethrow(error25);
                }
            }
            try {
                try {
                    try {
                        super.render(girl, d, d2, d3, f, f2);
                        if (!GoblinNpcRenderer.a(girl, girlAnimationState) || GoblinPlayerRenderer.Mc.gameSettings.thirdPersonView != 0) break block80;
                    }
                    catch (RuntimeException error26) {
                        throw GoblinPlayerRenderer.rethrow(error26);
                    }
                    if (!GoblinPlayerRenderer.Mc.player.getPersistentID().equals(uuid)) break block80;
                }
                catch (RuntimeException error27) {
                    throw GoblinPlayerRenderer.rethrow(error27);
                }
                GlStateManager.popMatrix();
            }
            catch (RuntimeException error28) {
                throw GoblinPlayerRenderer.rethrow(error28);
            }
        }
    }

   @Override
   protected void renderGeometry(Tessellator tessellator, BufferBuilder bufferBuilder, GirlEntity girl, Vec3f vec3f, float f) {
      a(tessellator, bufferBuilder, girl, vec3f, f);
   }

   @Nullable
   @Override

   protected Vec3f getTexture(GirlEntity girl) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [8[TRYBLOCK]], but top level block is 9[SWITCH]
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

   @Override
   protected void applyModelScale() {
      GlStateManager.translate(0.0, -0.77, -0.05);
      GlStateManager.scale(0.5, 0.5, 0.5);
   }

   @Override

   protected void applyItemPose(boolean flag, ItemStack stack) {
        float f;
        block6: {
            block8: {
                block7: {
                    try {
                        try {
                            super.applyItemPose(flag, stack);
                            if (stack.getItem().getItemUseAction(stack) != EnumAction.BOW) break block6;
                            if (!flag) break block7;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GoblinPlayerRenderer.rethrow(runtimeException);
                        }
                        GlStateManager.translate((float)0.1f, (float)0.0f, (float)0.0f);
                        GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        break block8;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayerRenderer.rethrow(runtimeException);
                    }
                }
                GlStateManager.rotate((float)170.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            }
            return;
        }
        try {
            f = flag ? 70.0f : 180.0f;
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayerRenderer.rethrow(runtimeException);
        }
        GlStateManager.rotate((float)f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.translate((double)0.0, (double)0.05, (double)-0.03);
    }

   @Override
   protected void a(boolean flag) {
   }

   @Override

   protected void applyArmPose(boolean flag, boolean flag2) {
        block8: {
            block9: {
                block6: {
                    block7: {
                        try {
                            try {
                                super.applyArmPose(flag, flag2);
                                if (!flag) break block6;
                                if (!flag2) break block7;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GoblinPlayerRenderer.rethrow(runtimeException);
                            }
                            GlStateManager.translate((double)0.0, (double)0.2, (double)-0.25);
                            GlStateManager.rotate((float)85.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            GlStateManager.rotate((float)38.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                            GlStateManager.rotate((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            break block8;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GoblinPlayerRenderer.rethrow(runtimeException);
                        }
                    }
                    GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.translate((double)0.0, (double)-0.265, (double)-0.04);
                    break block8;
                }
                try {
                    if (!flag2) break block9;
                    GlStateManager.rotate((float)0.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.rotate((float)150.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.rotate((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GlStateManager.translate((double)0.0, (double)-0.33, (double)-0.1);
                    break block8;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayerRenderer.rethrow(runtimeException);
                }
            }
            GlStateManager.translate((double)-0.02, (double)-0.05, (double)-0.05);
        }
    }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }
}
