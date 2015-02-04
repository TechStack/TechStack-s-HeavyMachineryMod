package com.projectreddog.machinemod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.projectreddog.machinemod.item.ItemBulldozer;
import com.projectreddog.machinemod.item.ItemCamshaft;
import com.projectreddog.machinemod.item.ItemCombine;
import com.projectreddog.machinemod.item.ItemDozerBlade;
import com.projectreddog.machinemod.item.ItemDrillHead;
import com.projectreddog.machinemod.item.ItemDrillPipe;
import com.projectreddog.machinemod.item.ItemDumpTruck;
import com.projectreddog.machinemod.item.ItemDumperBed;
import com.projectreddog.machinemod.item.ItemEngine;
import com.projectreddog.machinemod.item.ItemFlatBedTrailer;
import com.projectreddog.machinemod.item.ItemHavesterHead;
import com.projectreddog.machinemod.item.ItemIronGear;
import com.projectreddog.machinemod.item.ItemLoader;
import com.projectreddog.machinemod.item.ItemLoaderBucket;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.item.ItemPiston;
import com.projectreddog.machinemod.item.ItemStoneGear;
import com.projectreddog.machinemod.item.ItemTracks;
import com.projectreddog.machinemod.item.ItemTractor;
import com.projectreddog.machinemod.item.ItemTractorAttachmentPlanter;
import com.projectreddog.machinemod.item.ItemTractorAttachmentPlow;
import com.projectreddog.machinemod.item.ItemTractorAttachmentSprayer;
import com.projectreddog.machinemod.item.ItemTransmission;
import com.projectreddog.machinemod.item.ItemTruboFan;
import com.projectreddog.machinemod.item.ItemTurbo;
import com.projectreddog.machinemod.item.ItemTurboEngine;
import com.projectreddog.machinemod.item.ItemWheel;
import com.projectreddog.machinemod.item.ItemWideBedTruck;
import com.projectreddog.machinemod.item.ItemWoodenGear;
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
	public static final ItemMachineMod sprayer = new ItemTractorAttachmentSprayer();
	public static final ItemMachineMod widebedtruck = new ItemWideBedTruck();
	public static final ItemMachineMod woodengear = new ItemWoodenGear();
	public static final ItemMachineMod stonegear = new ItemStoneGear();
	public static final ItemMachineMod irongear = new ItemIronGear();
	public static final ItemMachineMod transmission = new ItemTransmission();
	public static final ItemMachineMod turbofan = new ItemTruboFan();

	public static final ItemMachineMod camshaft = new ItemCamshaft();
	public static final ItemMachineMod piston = new ItemPiston();
	public static final ItemMachineMod turbo = new ItemTurbo();
	public static final ItemMachineMod turboengine = new ItemTurboEngine();
	public static final ItemMachineMod flatbedtrailer = new ItemFlatBedTrailer();
	public static final ItemMachineMod dozerblade = new ItemDozerBlade();
	public static final ItemMachineMod tracks = new ItemTracks();
	public static final ItemMachineMod wheel = new ItemWheel();
	public static final ItemMachineMod loaderbucket = new ItemLoaderBucket();
	public static final ItemMachineMod dumperbed = new ItemDumperBed();
	public static final ItemMachineMod engine = new ItemEngine();
	public static final ItemMachineMod havesterhead = new ItemHavesterHead();

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
		GameRegistry.registerItem(sprayer, "sprayer");
		GameRegistry.registerItem(widebedtruck, "widebedtruck");
		GameRegistry.registerItem(woodengear, "woodengear");
		OreDictionary.registerOre("gearWood", woodengear);
		GameRegistry.registerItem(stonegear, "stonegear");
		OreDictionary.registerOre("gearStone", stonegear);
		GameRegistry.registerItem(irongear, "irongear");
		OreDictionary.registerOre("gearIron", irongear);

		GameRegistry.registerItem(transmission, "transmission");
		GameRegistry.registerItem(turbofan, "turbofan");

		GameRegistry.registerItem(camshaft, "camshaft");
		GameRegistry.registerItem(piston, "piston");
		GameRegistry.registerItem(turbo, "turbo");
		GameRegistry.registerItem(turboengine, "turboengine");
		GameRegistry.registerItem(flatbedtrailer, "flatbedtrailer");
		GameRegistry.registerItem(dozerblade, "dozerblade");
		GameRegistry.registerItem(tracks, "tracks");
		GameRegistry.registerItem(wheel, "wheel");
		GameRegistry.registerItem(loaderbucket, "loaderbucket");
		GameRegistry.registerItem(dumperbed, "dumperbed");
		GameRegistry.registerItem(engine, "engine");
		GameRegistry.registerItem(havesterhead, "havesterhead");

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
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(woodengear, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "woodengear", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(stonegear, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "stonegear", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(irongear, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "irongear", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(transmission, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "transmission", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(turbofan, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "turbofan", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(combine, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "combine", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(camshaft, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "camshaft", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(piston, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "piston", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(turbo, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "turbo", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(turboengine, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "turboengine", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(flatbedtrailer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "flatbedtrailer", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dozerblade, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dozerblade", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tracks, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tracks", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(wheel, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "wheel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(loaderbucket, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "loaderbucket", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dumperbed, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dumperbed", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(engine, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "engine", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(havesterhead, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "havesterhead", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tractor, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tractor", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(widebedtruck, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "widebedtruck", "inventory"));

	}
}
