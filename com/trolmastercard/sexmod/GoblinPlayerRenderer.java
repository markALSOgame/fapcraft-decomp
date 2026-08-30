package com.trolmastercard.sexmod;

import java.util.HashSet;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoblinPlayerRenderer extends CustomColorGirlRenderer {
   static Minecraft Mc;
   GoblinPlayer RenderedGoblin = null;
   boolean IsShoulderCarried = false;
   boolean IsPickingUp = false;
   boolean D = false;

   public GoblinPlayerRenderer(RenderManager renderManager, AnimatedGeoModel animatedGeoModel) {
      super(renderManager, animatedGeoModel);
      Mc = Minecraft.getMinecraft();
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
        return DefaultColor;
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

   public void doRender(GirlEntity girl, double d, double d2, double d3, float f, float f2) {
      this.D = GoblinPlayerRenderer.RenderTrigger;
      this.RenderedGoblin = (GoblinPlayer)girl;
      this.IsShoulderCarried = -420.69F == f && girl.getCurrentAction() == GirlAnimationState.SHOULDER_IDLE;
      this.IsPickingUp = -420.69F == f && girl.getCurrentAction() == GirlAnimationState.PICK_UP;
      this.RenderTick = f2;
      GoblinNpcRenderer.B = f;
      GirlAnimationState girlAnimationState = girl.getCurrentAction();
      UUID uuid = this.RenderedGoblin.getGirlUuid();

      if (girl.isTracked()) {
         Vec3d vec3d = GoblinNpcRenderer.a(girl.world, girl, uuid, d, d2, d3);
         d = vec3d.x;
         d2 = vec3d.y;
         d3 = vec3d.z;
      }

      if (girlAnimationState == GirlAnimationState.THROWN || girlAnimationState == GirlAnimationState.START_THROWING) {
         if (Mc.gameSettings.thirdPersonView == 0 && f == -420.69F && !girl.isTracked()) {
            return;
         }

         if (!girl.isTracked()) {
            float f3 = girl.I().floatValue();
            girl.prevRenderYawOffset = f3;
            girl.renderYawOffset = f3;
         }
      }

      if (GoblinNpcRenderer.isInCatchThrowAnimation(girl, girlAnimationState)) {
         if (Mc.player.getPersistentID().equals(uuid)) {
            if (-420.69F != f) {
               return;
            }

            girl.renderYawOffset = Mc.player.rotationYaw + 180.0F;
            girl.prevRenderYawOffset = Mc.player.rotationYaw + 180.0F;
            Vec3d lookVec = Mc.player.getLookVec();
            GlStateManager.pushMatrix();
            GlStateManager.translate(lookVec.x, lookVec.y + (double)Mc.player.getEyeHeight(), lookVec.z);
            Vec3d vec3d2 = GoblinNpc.rotateVec3dYaw(new Vec3d(-Math.abs(Mc.player.rotationPitch), 0.0, 0.0), Mc.player.rotationYaw);
            GlStateManager.rotate(Mc.player.rotationPitch, (float)vec3d2.x, 0.0F, (float)vec3d2.z);
            d = 0.0;
            d2 = 0.0;
            d3 = 0.0;
         } else if (!this.RenderedGoblin.getBoundPlayerUuid().equals(Mc.player.getPersistentID())) {
            if (!girl.isTracked() || uuid == null || Mc.player.getPersistentID().equals(uuid)) {
               if (uuid != null && !Mc.player.getPersistentID().equals(uuid)) {
                  EntityPlayer player2 = girl.world.getPlayerEntityByUUID(uuid);
                  if (player2 != null) {
                     girl.renderYawOffset = player2.rotationYaw;
                     girl.prevRenderYawOffset = player2.rotationYaw;
                  }
               } else {
                  girl.renderYawOffset = Mc.player.rotationYaw;
                  girl.prevRenderYawOffset = Mc.player.rotationYaw;
               }
            }

            Vec3d vec3d3 = GoblinNpcRenderer.getThrowAimOffset(girl, this.RenderedGoblin.getGirlUuid(), f2);
            d = vec3d3.x;
            d2 = vec3d3.y;
            d3 = vec3d3.z;
         }

      } else if (this.IsShoulderCarried) {
         GoblinNpcRenderer.rotateCameraToGirl(f2);
         Vec3d vec3d4 = new Vec3d(LerpMath.lerp(-0.1F, 0.2F, Mc.gameSettings.fovSetting / 110.0F), 0.0, 0.0);
         vec3d4 = GoblinNpc.rotateVec3dYaw(vec3d4, Mc.player.rotationYaw);
         d = vec3d4.x;
         d2 = vec3d4.y;
         d3 = vec3d4.z;
         girl.renderYawOffset = Mc.player.rotationYaw;
         girl.prevRenderYawOffset = Mc.player.prevRotationYaw;

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

         EntityPlayer player3 = girl.world.getPlayerEntityByUUID(uuid);

         if (player3 == null) {
            return;
         }

         Vector4f vector4f = GoblinNpcRenderer.getShoulderOffset(player3, f2);
         d = vector4f.x;
         d2 = vector4f.y;
         d3 = vector4f.z;
         girl.renderYawOffset = vector4f.w;

         if (player3.isSneaking()) {
            d2 -= 0.32;
         }
      } else if (girlAnimationState == GirlAnimationState.PICK_UP && uuid != null) {
         EntityPlayer player4 = girl.world.getPlayerEntityByUUID(uuid);

         if (player4 != null) {
            girl.prevRenderYawOffset = player4.prevRotationYawHead;
            girl.renderYawOffset = player4.rotationYawHead;
         }
      }

      super.doRender(girl, d, d2, d3, f, f2);

      if (GoblinNpcRenderer.isInCatchThrowAnimation(girl, girlAnimationState) && Mc.gameSettings.thirdPersonView == 0 && Mc.player.getPersistentID().equals(uuid)) {
         GlStateManager.popMatrix();
      }
    }

   @Override
   protected void b(Tessellator tessellator, BufferBuilder bufferBuilder, GirlEntity girl, Vec3f vec3f, float f) {
      a(tessellator, bufferBuilder, girl, vec3f, f);
   }

   @Nullable
   @Override

   protected Vec3f e(GirlEntity girl) {
      if (!this.D) {
         return null;
      }

      if (!(girl instanceof GoblinPlayer)) {
         return null;
      }

      GoblinPlayer goblin = (GoblinPlayer)girl;
      UUID uuid = goblin.getBoundPlayerUuid();
      EntityPlayerSP mcPlayer = Mc.player;
      if (uuid == null || Mc.gameSettings.thirdPersonView == 0 && mcPlayer.getPersistentID().equals(uuid)) {
         return null;
      }

      EntityPlayer player = goblin.getBoundPlayer();
      if (player == null) {
         return null;
      }

      ItemStack stack = goblin.getDataManager().get(InventoryGirlEntity.ChestKey);
      if (stack.isEmpty()) {
         return null;
      }

      if (!(stack.getItem() instanceof ItemArmor)) {
         return null;
      }

      ItemArmor armor = (ItemArmor)stack.getItem();

      label52: {
         switch (armor.getArmorMaterial()) {
            case GOLD:
               return new Vec3f(99.0F, 98.0F, 14.0F);
            case CHAIN:
            case IRON:
               return new Vec3f(85.0F, 85.0F, 85.0F);
            case LEATHER:
               break label52;
            default:
               return new Vec3f(23.0F, 100.0F, 93.0F);
         }
      }

      int colorValue = armor.getColor(stack);
      float f = colorValue >> 16 & 0xFF;
      float f2 = colorValue >> 8 & 0xFF;
      float f3 = colorValue & 0xFF;
      return new Vec3f(f, f2, f3);
   }

   @Override
   protected void applyScaleOffset() {
      GlStateManager.translate(0.0, -0.77, -0.05);
      GlStateManager.scale(0.5, 0.5, 0.5);
   }

   @Override

   protected void applyHeldItemTransform(boolean flag, ItemStack stack) {
        float f;
        block6: {
            block8: {
                block7: {
                    try {
                        try {
                            super.applyHeldItemTransform(flag, stack);
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
   protected void applyHandOffset(boolean flag) {
   }

   @Override

   protected void applyDualHandOffset(boolean flag, boolean flag2) {
        block8: {
            block9: {
                block6: {
                    block7: {
                        try {
                            try {
                                super.applyDualHandOffset(flag, flag2);
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
