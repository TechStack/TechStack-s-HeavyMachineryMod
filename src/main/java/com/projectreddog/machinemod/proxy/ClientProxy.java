package com.projectreddog.machinemod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityCrane;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.render.machines.RenderBulldozer;
import com.projectreddog.machinemod.render.machines.RenderCombine;
import com.projectreddog.machinemod.render.machines.RenderCrane;
import com.projectreddog.machinemod.render.machines.RenderDumpTruck;
import com.projectreddog.machinemod.render.machines.RenderLoader;
import com.projectreddog.machinemod.render.machines.RenderTractor;
import com.projectreddog.machinemod.render.machines.RenderWideBedTruck;
import com.projectreddog.machinemod.render.tileentity.TileEntityPrimaryCrusherRenderer;
import com.projectreddog.machinemod.tileentities.TileEntityPrimaryCrusher;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {

		// LogHelper.info("in register Renderers");

		RenderingRegistry.registerEntityRenderingHandler(EntityBulldozer.class, new RenderBulldozer(Minecraft.getMinecraft().getRenderManager()));
		// RenderingRegistry.registerEntityRenderingHandler(
		// EntityDrillingRig.class, new RenderDrillingRig(Minecraft
		// .getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDumpTruck.class, new RenderDumpTruck(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.class, new RenderLoader(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTractor.class, new RenderTractor(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCombine.class, new RenderCombine(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityWideBedTruck.class, new RenderWideBedTruck(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrane.class, new RenderCrane(Minecraft.getMinecraft().getRenderManager()));

		Item ItemblockBlastedStone = GameRegistry.findItem(Reference.MOD_ID, Reference.MODBLOCK_MACHINE_BLASTED_STONE);

		ModelBakery.addVariantName(ItemblockBlastedStone, Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "stone", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "granite", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "diorite", Reference.MOD_ID + ":"
				+ Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "andesite", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "gold", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "iron", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "coal", Reference.MOD_ID + ":"
				+ Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "lapis", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "diamond", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "redstone", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "emerald");

		ModBlocks.initBlockRender();
		ModItems.initItemRender();

		// Register TESR (tile Entity Special renderes
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimaryCrusher.class, new TileEntityPrimaryCrusherRenderer());

	}

}
