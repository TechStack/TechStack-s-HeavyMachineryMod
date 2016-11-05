package com.projectreddog.machinemod.proxy;

import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityCrane;
import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.entity.EntityLawnmower;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityOilRig;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityRoadRoller;
import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.entity.EntitySub;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.handler.events.RenderOverlayHandler;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.render.machines.RenderBagger;
import com.projectreddog.machinemod.render.machines.RenderBulldozer;
import com.projectreddog.machinemod.render.machines.RenderChopper;
import com.projectreddog.machinemod.render.machines.RenderCombine;
import com.projectreddog.machinemod.render.machines.RenderCrane;
import com.projectreddog.machinemod.render.machines.RenderDrillingRig;
import com.projectreddog.machinemod.render.machines.RenderDumpTruck;
import com.projectreddog.machinemod.render.machines.RenderExcavator;
import com.projectreddog.machinemod.render.machines.RenderGrader;
import com.projectreddog.machinemod.render.machines.RenderLawnmower;
import com.projectreddog.machinemod.render.machines.RenderLoader;
import com.projectreddog.machinemod.render.machines.RenderOilRig;
import com.projectreddog.machinemod.render.machines.RenderPaver;
import com.projectreddog.machinemod.render.machines.RenderRoadRoller;
import com.projectreddog.machinemod.render.machines.RenderSemiTractor;
import com.projectreddog.machinemod.render.machines.RenderSub;
import com.projectreddog.machinemod.render.machines.RenderTractor;
import com.projectreddog.machinemod.render.tileentity.TileEntityCentrifugeRenderer;
import com.projectreddog.machinemod.render.tileentity.TileEntityLiquidPipeRenderer;
import com.projectreddog.machinemod.tileentities.TileEntityCentrifuge;
import com.projectreddog.machinemod.tileentities.TileEntityLiquidPipe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {

		OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
		// LogHelper.info("in register Renderers");

		RenderingRegistry.registerEntityRenderingHandler(EntityBulldozer.class, new RenderBulldozer(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDrillingRig.class, new RenderDrillingRig(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDumpTruck.class, new RenderDumpTruck(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.class, new RenderLoader(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrader.class, new RenderGrader(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTractor.class, new RenderTractor(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLawnmower.class, new RenderLawnmower(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCombine.class, new RenderCombine(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySemiTractor.class, new RenderSemiTractor(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrane.class, new RenderCrane(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityExcavator.class, new RenderExcavator(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPaver.class, new RenderPaver(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityBagger.class, new RenderBagger(Minecraft.getMinecraft().getRenderManager()));

		RenderingRegistry.registerEntityRenderingHandler(EntityRoadRoller.class, new RenderRoadRoller(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityOilRig.class, new RenderOilRig(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySub.class, new RenderSub(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityChopper.class, new RenderChopper(Minecraft.getMinecraft().getRenderManager()));

		// RenderingRegistry.registerEntityRenderingHandler(EntityPumpJack.class, new RenderPumpJack(Minecraft.getMinecraft().getRenderManager()));

		Item ItemblockBlastedStone = GameRegistry.findItem(Reference.MOD_ID, Reference.MODBLOCK_MACHINE_BLASTED_STONE);

		ModelBakery.addVariantName(ItemblockBlastedStone, Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "stone", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "granite", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "diorite", Reference.MOD_ID + ":"
				+ Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "andesite", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "gold", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "iron", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "coal", Reference.MOD_ID + ":"
						+ Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "lapis", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "diamond", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "redstone", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "emerald");

		ModBlocks.initBlockRender();
		ModItems.initItemRender();

		// Register TESR (tile Entity Special renderes
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimaryCrusher.class, new TileEntityPrimaryCrusherRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCentrifuge.class, new TileEntityCentrifugeRenderer());

		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFuelPump.class, new TileEntityFuelPumpRenderer());
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDistiller.class, new TileEntityDistillerRenderer());
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFermenter.class, new TileEntityFermenterRenderer());

		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWellHead.class, new TileEntityWellHeadRenderer());

		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFractionalDistillation.class, new TileEntityFractionalDistillationRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLiquidPipe.class, new TileEntityLiquidPipeRenderer());

		// regsiter event for overlay
		MinecraftForge.EVENT_BUS.register(new RenderOverlayHandler());

	}

}
