package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.item.ItemANFO;
import com.projectreddog.machinemod.item.ItemAirTank;
import com.projectreddog.machinemod.item.ItemBaggerBody;
import com.projectreddog.machinemod.item.ItemBaggerStorage;
import com.projectreddog.machinemod.item.ItemBoomArmSegment;
import com.projectreddog.machinemod.item.ItemBucketWheel;
import com.projectreddog.machinemod.item.ItemConeCrusher;
import com.projectreddog.machinemod.item.ItemCornSeed;
import com.projectreddog.machinemod.item.ItemCutterBucket;
import com.projectreddog.machinemod.item.ItemDrillHead;
import com.projectreddog.machinemod.item.ItemDrillPipe;
import com.projectreddog.machinemod.item.ItemFuelCan;
import com.projectreddog.machinemod.item.ItemGoldDust;
import com.projectreddog.machinemod.item.ItemGraderBlade;
import com.projectreddog.machinemod.item.ItemHandDrill;
import com.projectreddog.machinemod.item.ItemHose;
import com.projectreddog.machinemod.item.ItemIronDust;
import com.projectreddog.machinemod.item.ItemLidWithSpout;
import com.projectreddog.machinemod.item.ItemLight;
import com.projectreddog.machinemod.item.ItemLightModule;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.item.ItemMowerDeck;
import com.projectreddog.machinemod.item.ItemOperatorsBooth;
import com.projectreddog.machinemod.item.ItemOperatorsBubble;
import com.projectreddog.machinemod.item.ItemPowerCell;
import com.projectreddog.machinemod.item.ItemPowerPlant;
import com.projectreddog.machinemod.item.ItemProcessingPlant;
import com.projectreddog.machinemod.item.ItemProp;
import com.projectreddog.machinemod.item.ItemPropCage;
import com.projectreddog.machinemod.item.ItemRawAsphalt;
import com.projectreddog.machinemod.item.ItemRigging;
import com.projectreddog.machinemod.item.ItemSubBody;
import com.projectreddog.machinemod.item.ItemTrackSegment;
import com.projectreddog.machinemod.item.ItemTurboProp;
import com.projectreddog.machinemod.item.ItemWrench;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlanter;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlow;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentSprayer;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentTrencher;
import com.projectreddog.machinemod.item.components.ItemCamshaft;
import com.projectreddog.machinemod.item.components.ItemDozerBlade;
import com.projectreddog.machinemod.item.components.ItemDumperBed;
import com.projectreddog.machinemod.item.components.ItemEngine;
import com.projectreddog.machinemod.item.components.ItemHavesterHead;
import com.projectreddog.machinemod.item.components.ItemIronGear;
import com.projectreddog.machinemod.item.components.ItemLiquidTankSegment;
import com.projectreddog.machinemod.item.components.ItemLoaderBucket;
import com.projectreddog.machinemod.item.components.ItemPaverScreed;
import com.projectreddog.machinemod.item.components.ItemPiston;
import com.projectreddog.machinemod.item.components.ItemRollerWheel;
import com.projectreddog.machinemod.item.components.ItemStoneGear;
import com.projectreddog.machinemod.item.components.ItemTracks;
import com.projectreddog.machinemod.item.components.ItemTransmission;
import com.projectreddog.machinemod.item.components.ItemTruboFan;
import com.projectreddog.machinemod.item.components.ItemTurbo;
import com.projectreddog.machinemod.item.components.ItemTurboEngine;
import com.projectreddog.machinemod.item.components.ItemWheel;
import com.projectreddog.machinemod.item.components.ItemWoodenGear;
import com.projectreddog.machinemod.item.machines.ItemBagger;
import com.projectreddog.machinemod.item.machines.ItemBulldozer;
import com.projectreddog.machinemod.item.machines.ItemChopper;
import com.projectreddog.machinemod.item.machines.ItemCombine;
import com.projectreddog.machinemod.item.machines.ItemCrane;
import com.projectreddog.machinemod.item.machines.ItemDrillingRig;
import com.projectreddog.machinemod.item.machines.ItemDumpTruck;
import com.projectreddog.machinemod.item.machines.ItemExcavator;
import com.projectreddog.machinemod.item.machines.ItemGrader;
import com.projectreddog.machinemod.item.machines.ItemLawnmower;
import com.projectreddog.machinemod.item.machines.ItemLoader;
import com.projectreddog.machinemod.item.machines.ItemOilRig;
import com.projectreddog.machinemod.item.machines.ItemPaver;
import com.projectreddog.machinemod.item.machines.ItemRoadRoller;
import com.projectreddog.machinemod.item.machines.ItemSemiTractor;
import com.projectreddog.machinemod.item.machines.ItemSub;
import com.projectreddog.machinemod.item.machines.ItemTractor;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerCargo;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerFlatBed;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerLivestock;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerTanker;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

	public static final ItemMachineMod bulldozer = new ItemBulldozer();
	public static final ItemMachineMod tractor = new ItemTractor();
	public static final ItemMachineMod lawnmower = new ItemLawnmower();
	public static final ItemMachineMod anfo = new ItemANFO();
	public static final ItemMachineMod paver = new ItemPaver();
	public static final ItemMachineMod roadroller = new ItemRoadRoller();
	public static final ItemMachineMod paverscreed = new ItemPaverScreed();
	public static final ItemMachineMod rollerwheel = new ItemRollerWheel();
	public static final ItemMachineMod oilrig = new ItemOilRig();
	public static final ItemMachineMod sub = new ItemSub();
	public static final ItemMachineMod chopper = new ItemChopper();

	public static final ItemMachineMod liquidtanksegment = new ItemLiquidTankSegment();

	public static final ItemMachineMod prop = new ItemProp();
	public static final ItemMachineMod propcage = new ItemPropCage();
	public static final ItemMachineMod turboprop = new ItemTurboProp();
	public static final ItemMachineMod operatorsbubble = new ItemOperatorsBubble();
	public static final ItemMachineMod subbody = new ItemSubBody();
	public static final ItemMachineMod airtank = new ItemAirTank();
	public static final ItemMachineMod powercell = new ItemPowerCell();
	public static final ItemMachineMod light = new ItemLight();
	public static final ItemMachineMod lightmodule = new ItemLightModule();

	public static final ItemMachineMod bagger = new ItemBagger();

	public static final ItemMachineMod tracksegment = new ItemTrackSegment();

	public static final ItemMachineMod cutterbucket = new ItemCutterBucket();
	public static final ItemMachineMod rigging = new ItemRigging();
	public static final ItemMachineMod bucketwheel = new ItemBucketWheel();
	public static final ItemMachineMod operatorsbooth = new ItemOperatorsBooth();
	public static final ItemMachineMod powerplant = new ItemPowerPlant();
	public static final ItemMachineMod processingplant = new ItemProcessingPlant();
	public static final ItemMachineMod baggerstorage = new ItemBaggerStorage();
	public static final ItemMachineMod baggerbody = new ItemBaggerBody();

	public static final ItemMachineMod conecrusher = new ItemConeCrusher();
	public static final ItemMachineMod lidwithspout = new ItemLidWithSpout();
	public static final ItemMachineMod handdrill = new ItemHandDrill();

	public static final ItemMachineMod boomarmsegment = new ItemBoomArmSegment();

	public static final ItemMachineMod drillingrig = new ItemDrillingRig();
	public static final ItemMachineMod dumptruck = new ItemDumpTruck();
	public static final ItemMachineMod loader = new ItemLoader();
	public static final ItemMachineMod grader = new ItemGrader();

	public static final ItemMachineMod combine = new ItemCombine();
	public static final ItemMachineMod crane = new ItemCrane();
	public static final ItemMachineMod excavator = new ItemExcavator();
	public static final ItemMachineMod trencher = new ItemTractorAttachmentTrencher();
	public static final ItemMachineMod irondust = new ItemIronDust();
	public static final ItemMachineMod golddust = new ItemGoldDust();
	public static final ItemMachineMod hose = new ItemHose();
	public static final ItemMachineMod mowerdeck = new ItemMowerDeck();

	public static final ItemMachineMod rawasphalt = new ItemRawAsphalt();

	public static final ItemMachineMod drillhead = new ItemDrillHead();
	public static final ItemFood cornseed = new ItemCornSeed();

	public static final ItemMachineMod drillpipe = new ItemDrillPipe();
	public static final ItemMachineMod plow = new ItemTractorAttachmentPlow();
	public static final ItemMachineMod planter = new ItemTractorAttachmentPlanter();
	public static final ItemMachineMod sprayer = new ItemTractorAttachmentSprayer();
	public static final ItemMachineMod semitractor = new ItemSemiTractor();
	public static final ItemMachineMod woodengear = new ItemWoodenGear();
	public static final ItemMachineMod stonegear = new ItemStoneGear();
	public static final ItemMachineMod irongear = new ItemIronGear();
	public static final ItemMachineMod transmission = new ItemTransmission();
	public static final ItemMachineMod turbofan = new ItemTruboFan();

	public static final ItemMachineMod camshaft = new ItemCamshaft();
	public static final ItemMachineMod piston = new ItemPiston();
	public static final ItemMachineMod turbo = new ItemTurbo();
	public static final ItemMachineMod turboengine = new ItemTurboEngine();
	public static final ItemMachineMod flatbedtrailer = new ItemSemiTrailerFlatBed();

	public static final ItemMachineMod livestocktrailer = new ItemSemiTrailerLivestock();
	public static final ItemMachineMod tankertrailer = new ItemSemiTrailerTanker();
	public static final ItemMachineMod cargotrailer = new ItemSemiTrailerCargo();

	public static final ItemMachineMod dozerblade = new ItemDozerBlade();
	public static final ItemMachineMod tracks = new ItemTracks();
	public static final ItemMachineMod wheel = new ItemWheel();
	public static final ItemMachineMod loaderbucket = new ItemLoaderBucket();
	public static final ItemMachineMod dumperbed = new ItemDumperBed();
	public static final ItemMachineMod engine = new ItemEngine();
	public static final ItemMachineMod havesterhead = new ItemHavesterHead();
	public static final ItemMachineMod fuelcan = new ItemFuelCan();

	public static final ItemMachineMod graderblade = new ItemGraderBlade();
	public static final ItemMachineMod wrench = new ItemWrench();

	/// buckets
	// public static final ItemBucket oilbucket = new ItemOilBucket(ModBlocks.oilFluidBlock);

	public static void init() {

		// machines

		if (Reference.enableBagger) {
			GameRegistry.registerItem(bagger, "bagger");

		}
		if (Reference.enableBulldozer) {
			GameRegistry.registerItem(bulldozer, "bulldozer");
		}

		if (Reference.enableCombine) {
			GameRegistry.registerItem(combine, "combine");

		}
		if (Reference.enableCrane) {
			GameRegistry.registerItem(crane, "crane");

		}
		if (Reference.enableDrillingRig) {
			GameRegistry.registerItem(drillingrig, "drillingrig");

		}

		if (Reference.enableDumptruck) {
			GameRegistry.registerItem(dumptruck, "dumptruck");

		}

		if (Reference.enableExcavator) {
			GameRegistry.registerItem(excavator, "excavator");

		}

		if (Reference.enableGrader) {
			GameRegistry.registerItem(grader, "grader");

		}

		if (Reference.enableLawnmower) {
			GameRegistry.registerItem(lawnmower, "lawnmower");

		}
		if (Reference.enableLoader) {
			GameRegistry.registerItem(loader, "loader");

		}

		if (Reference.enableOilRig) {
			GameRegistry.registerItem(oilrig, "oilrig");

		}

		if (Reference.enablePaver) {
			GameRegistry.registerItem(paver, "paver");

		}

		if (Reference.enablePumpJack) {
			// pump jack future use
		}
		if (Reference.enableRoadRoller) {
			GameRegistry.registerItem(roadroller, "roadroller");

		}
		if (Reference.enableSemiTractor) {
			GameRegistry.registerItem(semitractor, "semitractor");

		}
		if (Reference.enableSub) {
			GameRegistry.registerItem(sub, "sub");

		}

		if (Reference.enableChopper) {
			GameRegistry.registerItem(chopper, "chopper");

		}
		if (Reference.enableTractor) {
			GameRegistry.registerItem(tractor, "tractor");

		}

		GameRegistry.registerItem(paverscreed, "paverscreed");
		GameRegistry.registerItem(rollerwheel, "rollerwheel");

		GameRegistry.registerItem(liquidtanksegment, "liquidtanksegment");

		GameRegistry.registerItem(prop, "prop");
		GameRegistry.registerItem(propcage, "propcage");
		GameRegistry.registerItem(turboprop, "turboprop");
		GameRegistry.registerItem(operatorsbubble, "operatorsbubble");
		GameRegistry.registerItem(subbody, "subbody");
		GameRegistry.registerItem(airtank, "airtank");
		GameRegistry.registerItem(powercell, "powercell");
		GameRegistry.registerItem(light, "light");
		GameRegistry.registerItem(lightmodule, "lightmodule");

		GameRegistry.registerItem(tracksegment, "tracksegment");

		GameRegistry.registerItem(cutterbucket, "cutterbucket");
		GameRegistry.registerItem(rigging, "rigging");
		GameRegistry.registerItem(bucketwheel, "bucketwheel");
		GameRegistry.registerItem(operatorsbooth, "operatorsbooth");
		GameRegistry.registerItem(powerplant, "powerplant");
		GameRegistry.registerItem(processingplant, "processingplant");
		GameRegistry.registerItem(baggerstorage, "baggerstorage");
		GameRegistry.registerItem(baggerbody, "baggerbody");

		GameRegistry.registerItem(conecrusher, "conecrusher");

		GameRegistry.registerItem(boomarmsegment, "boomarmsegment");

		GameRegistry.registerItem(hose, "hose");
		GameRegistry.registerItem(mowerdeck, "mowerdeck");
		GameRegistry.registerItem(rawasphalt, "rawasphalt");

		GameRegistry.registerItem(lidwithspout, "lidwithspout");
		GameRegistry.registerItem(handdrill, "handdrill");
		GameRegistry.registerItem(anfo, "anfo");

		GameRegistry.registerItem(cornseed, "cornseed");
		GameRegistry.registerItem(trencher, "trencher");
		GameRegistry.registerItem(irondust, "irondust");
		OreDictionary.registerOre("dustIron", irondust);
		GameRegistry.registerItem(golddust, "golddust");
		OreDictionary.registerOre("dustGold", golddust);

		GameRegistry.registerItem(drillhead, "drillhead");
		GameRegistry.registerItem(drillpipe, "drillpipe");
		GameRegistry.registerItem(plow, "plow");
		GameRegistry.registerItem(planter, "planter");
		GameRegistry.registerItem(sprayer, "sprayer");
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

		GameRegistry.registerItem(livestocktrailer, "livestocktrailer");
		GameRegistry.registerItem(tankertrailer, "tankertrailer");
		GameRegistry.registerItem(cargotrailer, "cargotrailer");

		GameRegistry.registerItem(dozerblade, "dozerblade");
		GameRegistry.registerItem(tracks, "tracks");
		GameRegistry.registerItem(wheel, "wheel");
		GameRegistry.registerItem(loaderbucket, "loaderbucket");
		GameRegistry.registerItem(dumperbed, "dumperbed");
		GameRegistry.registerItem(engine, "engine");
		GameRegistry.registerItem(havesterhead, "havesterhead");
		GameRegistry.registerItem(fuelcan, "fuelcan");
		GameRegistry.registerItem(graderblade, "graderblade");
		GameRegistry.registerItem(wrench, "wrench");

		// buckets
		// GameRegistry.registerItem(oilbucket, "oilbucket");
		// FluidContainerRegistry.registerFluidContainer(ModBlocks.fluidOil, new ItemStack(oilbucket), new ItemStack(Items.bucket));
		// EventHandler.buckets.put(ModBlocks.oilFluidBlock, ModItems.oilbucket);
	}

	public static void initItemRender() {

		/// machine
		if (Reference.enableBagger) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bagger, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "bagger", "inventory"));

		}
		if (Reference.enableBulldozer) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bulldozer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "bulldozer", "inventory"));

		}

		if (Reference.enableCombine) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(combine, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "combine", "inventory"));

		}
		if (Reference.enableCrane) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(crane, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "crane", "inventory"));

		}
		if (Reference.enableDrillingRig) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillingrig, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "drillingrig", "inventory"));

		}

		if (Reference.enableDumptruck) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dumptruck, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dumptruck", "inventory"));

		}

		if (Reference.enableExcavator) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(excavator, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "excavator", "inventory"));

		}

		if (Reference.enableGrader) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(grader, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "grader", "inventory"));

		}

		if (Reference.enableLawnmower) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(lawnmower, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "lawnmower", "inventory"));

		}
		if (Reference.enableLoader) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(loader, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "loader", "inventory"));

		}

		if (Reference.enableOilRig) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(oilrig, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "oilrig", "inventory"));

		}

		if (Reference.enablePaver) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(paver, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "paver", "inventory"));

		}

		if (Reference.enablePumpJack) {
			// pump jack future use

		}
		if (Reference.enableRoadRoller) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(roadroller, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "roadroller", "inventory"));

		}
		if (Reference.enableSemiTractor) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(semitractor, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "semitractor", "inventory"));

		}
		if (Reference.enableSub) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(sub, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "sub", "inventory"));

		}
		if (Reference.enableChopper) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(chopper, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "chopper", "inventory"));

		}
		if (Reference.enableTractor) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tractor, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tractor", "inventory"));

		}

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tracksegment, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tracksegment", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(paverscreed, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "paverscreed", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(rollerwheel, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "rollerwheel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(liquidtanksegment, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "liquidtanksegment", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(prop, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "prop", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(propcage, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "propcage", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(turboprop, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "turboprop", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(operatorsbubble, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "operatorsbubble", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(subbody, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "subbody", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(airtank, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "airtank", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(powercell, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "powercell", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(light, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "light", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(lightmodule, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "lightmodule", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cutterbucket, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "cutterbucket", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(rigging, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "rigging", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bucketwheel, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "bucketwheel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(operatorsbooth, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "operatorsbooth", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(powerplant, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "powerplant", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(processingplant, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "processingplant", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(baggerstorage, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "baggerstorage", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(baggerbody, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "baggerbody", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(rawasphalt, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "rawasphalt", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillhead, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "drillhead", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillpipe, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "drillpipe", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(plow, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "plow", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cornseed, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "cornseed", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(irondust, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "irondust", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(golddust, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "golddust", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(golddust, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "golddust", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(anfo, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "anfo", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(planter, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "planter", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(sprayer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "sprayer", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(woodengear, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "woodengear", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(stonegear, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "stonegear", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(irongear, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "irongear", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(transmission, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "transmission", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(turbofan, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "turbofan", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(trencher, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "trencher", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(conecrusher, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "conecrusher", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hose, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "hose", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(handdrill, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "handdrill", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(mowerdeck, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "mowerdeck", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(boomarmsegment, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "boomarmsegment", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(lidwithspout, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "lidwithspout", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(camshaft, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "camshaft", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(piston, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "piston", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(turbo, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "turbo", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(turboengine, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "turboengine", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(flatbedtrailer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "flatbedtrailer", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(livestocktrailer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "livestocktrailer", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tankertrailer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tankertrailer", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cargotrailer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "cargotrailer", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dozerblade, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dozerblade", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tracks, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tracks", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(wheel, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "wheel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(loaderbucket, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "loaderbucket", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dumperbed, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dumperbed", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(engine, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "engine", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(havesterhead, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "havesterhead", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(fuelcan, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "fuelcan", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(graderblade, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "graderblade", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(wrench, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "wrench", "inventory"));

	}
}
