package com.projectreddog.machinemod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.projectreddog.machinemod.item.ItemBulldozer;
import com.projectreddog.machinemod.item.ItemCombine;
import com.projectreddog.machinemod.item.ItemDrillHead;
import com.projectreddog.machinemod.item.ItemDrillPipe;
import com.projectreddog.machinemod.item.ItemDumpTruck;
import com.projectreddog.machinemod.item.ItemLoader;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.item.ItemTractor;
import com.projectreddog.machinemod.item.ItemTractorAttachmentPlanter;
import com.projectreddog.machinemod.item.ItemTractorAttachmentPlow;
import com.projectreddog.machinemod.item.ItemTractorAttachmentSprayer;
import com.projectreddog.machinemod.item.ItemWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

	public static final ItemMachineMod bulldozer = new ItemBulldozer();
	public static final ItemMachineMod tractor = new ItemTractor();

	// public static final ItemMachineMod drillingrig = new ItemDrillingRig();
	public static final ItemMachineMod dumptruck = new ItemDumpTruck();
	public static final ItemMachineMod loader = new ItemLoader();
	public static final ItemMachineMod combine = new ItemCombine();
	public static final ItemMachineMod drillhead = new ItemDrillHead();
	public static final ItemMachineMod drillpipe = new ItemDrillPipe();
	public static final ItemMachineMod plow = new ItemTractorAttachmentPlow();
	public static final ItemMachineMod planter = new ItemTractorAttachmentPlanter();
	public static final ItemMachineMod sprayer= new ItemTractorAttachmentSprayer();
	public static final ItemMachineMod widebedtruck= new ItemWideBedTruck();

	
	
	public static void init() {
		GameRegistry.registerItem(bulldozer, "bulldozer");
		GameRegistry.registerItem(tractor, "tractor");

		// GameRegistry.registerItem(drillingrig, "drillingrig");
		GameRegistry.registerItem(dumptruck, "dumptruck");
		GameRegistry.registerItem(loader, "loader");
		GameRegistry.registerItem(combine, "combine");
		GameRegistry.registerItem(drillhead, "drillhead");
		GameRegistry.registerItem(drillpipe, "drillpipe");
		GameRegistry.registerItem(plow, "plow");
		GameRegistry.registerItem(planter, "planter");
		GameRegistry.registerItem(sprayer,"sprayer");
		GameRegistry.registerItem(widebedtruck,"widebedtruck");

	}

	public static void initItemRender() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bulldozer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "bulldozer", "inventory"));
		// Minecraft
		// .getMinecraft()
		// .getRenderItem()
		// .getItemModelMesher()
		// .register(
		// drillingrig,
		// 0,
		// new ModelResourceLocation(Reference.MOD_ID + ":"
		// + "drillingrig", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dumptruck, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dumptruck", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(loader, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "loader", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillhead, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "drillhead", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillpipe, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "drillpipe", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(plow, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "plow", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(planter, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "planter", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(sprayer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "sprayer", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tractor, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tractor", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(widebedtruck, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "widebedtruck", "inventory"));

	}
}
