package com.trolmastercard.sexmod;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventManager {
   public static void registerEventHandlers(boolean flag) throws IOException {
      try {
         MinecraftForge.EVENT_BUS.register(new GirlAttackHandler());
         MinecraftForge.EVENT_BUS.register(new GirlDeathHandler());
         MinecraftForge.EVENT_BUS.register(new PlayerLoginHandler());
         MinecraftForge.EVENT_BUS.register(new PotionHandler());
         MinecraftForge.EVENT_BUS.register(new GirlDamageHandler());
         MinecraftForge.EVENT_BUS.register(new CustomEnderPearl.EventHandler());
         MinecraftForge.EVENT_BUS.register(new GirlCombatAi.EventHandler());
         MinecraftForge.EVENT_BUS.register(ItemAlliesLamp.Instance);
         MinecraftForge.EVENT_BUS.register(ItemDragonStaff.Instance);
         MinecraftForge.EVENT_BUS.register(ItemNpcEditorWand.Instance);
         MinecraftForge.EVENT_BUS.register(new ItemLunaRod());
         MinecraftForge.EVENT_BUS.register(new GirlInteractHandler());
         MinecraftForge.EVENT_BUS.register(new LunaNpc.EventHandler());
         MinecraftForge.EVENT_BUS.register(new FireSpreadHandler());
         MinecraftForge.EVENT_BUS.register(CustomFireBlock.a);
         MinecraftForge.EVENT_BUS.register(new KoboldNpc.DeathLootHandler());
         MinecraftForge.EVENT_BUS.register(new ItemDragonStaff.registerAll());
         MinecraftForge.EVENT_BUS.register(new GirlHomeBuilder.TribeWorldData("tribes"));
         MinecraftForge.EVENT_BUS.register(new ItemKoboldEgg());
         MinecraftForge.EVENT_BUS.register(new GirlRenderHandler());
         MinecraftForge.EVENT_BUS.register(new GoblinNpc.EventHandler());
         MinecraftForge.EVENT_BUS.register(new GoblinPlayer.EventHandler());
         MinecraftForge.EVENT_BUS.register(new ItemAlliesLamp.registerAll());
         MinecraftForge.EVENT_BUS.register(new DevConsole());
         MinecraftForge.EVENT_BUS.register(new GalathNpc.EventHandler());
         MinecraftForge.EVENT_BUS.register(new GalathOwnershipData());
         MinecraftForge.EVENT_BUS.register(ItemGalathCoin.Instance);
         MinecraftForge.EVENT_BUS.register(ItemWinchester.Instance);
         MinecraftForge.EVENT_BUS.register(new TribeVillageData());
         MinecraftForge.EVENT_BUS.register(new GirlHomeWorldData());
         MinecraftForge.EVENT_BUS.register(new CustomModelWorldData());
         MinecraftForge.EVENT_BUS.register(KoboldVillageGenerator.getInstance());
         MinecraftForge.EVENT_BUS.register(new ManglelieNpc.ArrowImpactHandler());
         MinecraftForge.EVENT_BUS.register(new GirlNameTagHandler());
         if (flag) {
            b();
         }
      } catch (IOException error) {
         throw rethrow(error);
      }
   }

   @SideOnly(Side.CLIENT)
   static void b() {
      label16: {
         try {
            if (a()) {
               MinecraftForge.EVENT_BUS.register(new PornWarningTrigger());
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         PornWarningHelper.a = false;
      }

      MinecraftForge.EVENT_BUS.register(new GuiHud());
      MinecraftForge.EVENT_BUS.register(new GuiTransitionScreen());
      MinecraftForge.EVENT_BUS.register(new AnimationInputLock());
      MinecraftForge.EVENT_BUS.register(new BoyCamera());
      MinecraftForge.EVENT_BUS.register(new GuiCloseResetHandler());
      MinecraftForge.EVENT_BUS.register(new HandItemRenderer());
      MinecraftForge.EVENT_BUS.register(new GirlCameraController());
      MinecraftForge.EVENT_BUS.register(new ClientChatHandler());
      MinecraftForge.EVENT_BUS.register(new AlliePlayerRenderer.EventHandler());
      MinecraftForge.EVENT_BUS.register(new GuiMark());
      MinecraftForge.EVENT_BUS.register(new VersionChecker());
      MinecraftForge.EVENT_BUS.register(new a.getSelectedClothingOptions());
      MinecraftForge.EVENT_BUS.register(new FilePersistence.ChatListener());
      MinecraftForge.EVENT_BUS.register(new GuiEscapeMinigame());
      MinecraftForge.EVENT_BUS.register(new GuiCumOverlay());
      MinecraftForge.EVENT_BUS.register(new GuiGalathFlight());
   }

   static boolean a() {
      File file = new File("sexmod/dontAskAgain");

      try {
         file.getParentFile().mkdirs();
         if (!file.exists()) {
            return true;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      return false;
   }

   private static Exception rethrow(Exception error) {
      return error;
   }
}
