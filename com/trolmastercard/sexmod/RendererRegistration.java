package com.trolmastercard.sexmod;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RendererRegistration {
   public static void registerEntityRenderers() {
      RenderingRegistry.registerEntityRenderingHandler(KoboldNpc.class, arg1 -> new KoboldNpcRenderer(arg1, new ModelKobold(), -0.4));
      RenderingRegistry.registerEntityRenderingHandler(JennyNpc.class, arg1b -> new JennyNpcRenderer(arg1b, (AnimatedGeoModel)new ModelJenny(), -0.15));
      RenderingRegistry.registerEntityRenderingHandler(EllieNpc.class, arg1c -> new EllieNpcRenderer(arg1c, (AnimatedGeoModel)new ModelEllie(), 0.05));
      RenderingRegistry.registerEntityRenderingHandler(SlimeNpc.class, arg1d -> new SlimeNpcRenderer(arg1d, new ModelSlime(), -0.2));
      RenderingRegistry.registerEntityRenderingHandler(BiaNpc.class, arg1e -> new BiaNpcRenderer(arg1e, new ModelBia(), -0.4));
      RenderingRegistry.registerEntityRenderingHandler(AllieNpc.class, arg1f -> new AllieNpcRenderer(arg1f, new ModelAllie(), -0.4));
      RenderingRegistry.registerEntityRenderingHandler(BeeNpc.class, arg1g -> new BeeNpcRenderer(arg1g, (AnimatedGeoModel)new ModelBee(), -0.4));
      RenderingRegistry.registerEntityRenderingHandler(SlimeRainEntity.class, SlimeRainRenderer::new);
      RenderingRegistry.registerEntityRenderingHandler(LunaNpc.class, arg1h -> new LunaNpcRenderer(arg1h, new ModelLuna(), -0.4));
      RenderingRegistry.registerEntityRenderingHandler(GoblinNpc.class, arg1i -> new GoblinNpcRenderer(arg1i, new ModelGoblin(), -0.6));
      RenderingRegistry.registerEntityRenderingHandler(GalathNpc.class, arg1_var0 -> new GalathNpcRenderer(arg1_var0, new ModelGalath(), -0.05));
      RenderingRegistry.registerEntityRenderingHandler(KoboldEggEntity.class, arg1_var0 -> new KoboldEggRenderer(arg1_var0, new KoboldEggModel()));
      RenderingRegistry.registerEntityRenderingHandler(ManglelieNpc.class, arg1_var0 -> new ManglelieNpcRenderer(arg1_var0, new ModelManglelie(), -0.05));
      RenderingRegistry.registerEntityRenderingHandler(BiaPlayer.class, arg1_var0 -> new BiaPlayerRenderer(arg1_var0, new ModelBia()));
      RenderingRegistry.registerEntityRenderingHandler(JennyPlayer.class, arg1_var0 -> new JennyPlayerRenderer(arg1_var0, new ModelJenny()));
      RenderingRegistry.registerEntityRenderingHandler(ElliePlayer.class, arg1_var0 -> new ElliePlayerRenderer(arg1_var0, new ModelEllie()));
      RenderingRegistry.registerEntityRenderingHandler(SlimePlayer.class, arg1_var0 -> new SlimePlayerRenderer(arg1_var0, new ModelSlime()));
      RenderingRegistry.registerEntityRenderingHandler(AlliePlayer.class, arg1_var0 -> new AlliePlayerRenderer(arg1_var0, new ModelAllie()));
      RenderingRegistry.registerEntityRenderingHandler(BeePlayer.class, arg1_var0 -> new BeePlayerRenderer(arg1_var0, new ModelBee()));
      RenderingRegistry.registerEntityRenderingHandler(LunaPlayer.class, arg1_var0 -> new LunaPlayerRenderer(arg1_var0, new ModelLuna()));
      RenderingRegistry.registerEntityRenderingHandler(KoboldPlayer.class, arg1_var0 -> new KoboldPlayerRenderer(arg1_var0, new ModelKobold()));
      RenderingRegistry.registerEntityRenderingHandler(GoblinPlayer.class, arg1_var0 -> new GoblinPlayerRenderer(arg1_var0, new ModelGoblin()));
      RenderingRegistry.registerEntityRenderingHandler(GalathPlayer.class, arg1_var0 -> new GalathPlayerRenderer(arg1_var0, new ModelGalath()));
      RenderingRegistry.registerEntityRenderingHandler(LunaFamiliarEntity.class, LunaFamiliarRenderer::new);
      RenderingRegistry.registerEntityRenderingHandler(PreviewEntity.class, arg1_var0 -> new PreviewRenderer(arg1_var0, new PreviewModel()));
      RenderingRegistry.registerEntityRenderingHandler(EnergyBallEntity.class, EnergyBallRenderer::new);
      RenderingRegistry.registerEntityRenderingHandler(CultistEntity.class, CultistRenderer::new);
   }
}
