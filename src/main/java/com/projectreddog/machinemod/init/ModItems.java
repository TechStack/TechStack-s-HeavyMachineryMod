package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.item.ItemANFO;
import com.projectreddog.machinemod.item.ItemAfterBurner;
import com.projectreddog.machinemod.item.ItemAirTank;
import com.projectreddog.machinemod.item.ItemAluminumIngot;
import com.projectreddog.machinemod.item.ItemBaggerBody;
import com.projectreddog.machinemod.item.ItemBaggerStorage;
import com.projectreddog.machinemod.item.ItemBleakCrystal;
import com.projectreddog.machinemod.item.ItemBoomArmSegment;
import com.projectreddog.machinemod.item.ItemBucketWheel;
import com.projectreddog.machinemod.item.ItemCarbonDust;
import com.projectreddog.machinemod.item.ItemCollapsedStar;
import com.projectreddog.machinemod.item.ItemConeCrusher;
import com.projectreddog.machinemod.item.ItemCornSeed;
import com.projectreddog.machinemod.item.ItemCutterBucket;
import com.projectreddog.machinemod.item.ItemDebug;
import com.projectreddog.machinemod.item.ItemDrillHead;
import com.projectreddog.machinemod.item.ItemDrillPipe;
import com.projectreddog.machinemod.item.ItemFuelCan;
import com.projectreddog.machinemod.item.ItemGoldDust;
import com.projectreddog.machinemod.item.ItemGraderBlade;
import com.projectreddog.machinemod.item.ItemHammer;
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
import com.projectreddog.machinemod.item.ItemSteelDust;
import com.projectreddog.machinemod.item.ItemSubBody;
import com.projectreddog.machinemod.item.ItemTrackSegment;
import com.projectreddog.machinemod.item.ItemTurboProp;
import com.projectreddog.machinemod.item.ItemWrench;
import com.projectreddog.machinemod.item.armor.ItemMachineModArmor;
import com.projectreddog.machinemod.item.armor.ItemMachineModCrashHelmet;
import com.projectreddog.machinemod.item.armor.ItemMachineModElytraJetLegs;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlanter;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlow;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentSprayer;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentTrencher;
import com.projectreddog.machinemod.item.blueprint.ItemBlueprintBatteryBank;
import com.projectreddog.machinemod.item.blueprint.ItemBlueprintConduit;
import com.projectreddog.machinemod.item.blueprint.ItemBlueprintContinuousMiner;
import com.projectreddog.machinemod.item.blueprint.ItemBlueprintFactory;
import com.projectreddog.machinemod.item.blueprint.ItemBlueprintGenerator;
import com.projectreddog.machinemod.item.chopperattachments.ItemChopperAttachmentSawBlades;
import com.projectreddog.machinemod.item.components.ItemCamshaft;
import com.projectreddog.machinemod.item.components.ItemDozerBlade;
import com.projectreddog.machinemod.item.components.ItemDumperBed;
import com.projectreddog.machinemod.item.components.ItemEngine;
import com.projectreddog.machinemod.item.components.ItemHarvesterHead;
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
import com.projectreddog.machinemod.item.ingots.ItemAzuriumLump;
import com.projectreddog.machinemod.item.ingots.ItemCitroniteIngot;
import com.projectreddog.machinemod.item.ingots.ItemCrimsonitePebble;
import com.projectreddog.machinemod.item.ingots.ItemIridoniumIngot;
import com.projectreddog.machinemod.item.ingots.ItemLimoniteumIngot;
import com.projectreddog.machinemod.item.ingots.ItemMagentiaIngot;
import com.projectreddog.machinemod.item.ingots.ItemSteelIngot;
import com.projectreddog.machinemod.item.ingots.ItemUnobtaniumGem;
import com.projectreddog.machinemod.item.machines.ItemBagger;
import com.projectreddog.machinemod.item.machines.ItemBulldozer;
import com.projectreddog.machinemod.item.machines.ItemChopper;
import com.projectreddog.machinemod.item.machines.ItemCombine;
import com.projectreddog.machinemod.item.machines.ItemContinuousMiner;
import com.projectreddog.machinemod.item.machines.ItemCrane;
import com.projectreddog.machinemod.item.machines.ItemDrillingRig;
import com.projectreddog.machinemod.item.machines.ItemDumpTruck;
import com.projectreddog.machinemod.item.machines.ItemExcavator;
import com.projectreddog.machinemod.item.machines.ItemGrader;
import com.projectreddog.machinemod.item.machines.ItemLaserMiner;
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
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
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
	public static final ItemMachineMod bleakcrystal = new ItemBleakCrystal();

	public static final ItemMachineMod carbondust = new ItemCarbonDust();
	public static final ItemMachineMod steeldust = new ItemSteelDust();
	public static final ItemMachineMod steelingot = new ItemSteelIngot();

	public static final ItemMachineMod citroniteingot = new ItemCitroniteIngot();
	public static final ItemMachineMod iridoniumingot = new ItemIridoniumIngot();
	public static final ItemMachineMod limoniteumingot = new ItemLimoniteumIngot();
	public static final ItemMachineMod magentiaingot = new ItemMagentiaIngot();

	public static final ItemMachineMod crimsonitepebble = new ItemCrimsonitePebble();
	public static final ItemMachineMod azuriumlump = new ItemAzuriumLump();
	public static final ItemMachineMod unobtaniumgem = new ItemUnobtaniumGem();

	public static final ItemMachineMod aluminumingot = new ItemAluminumIngot();

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
	public static final ItemMachineModElytraJetLegs elytrajetleg = new ItemMachineModElytraJetLegs(ItemMachineModArmor.MachineFuleConsumerMaterial, EntityEquipmentSlot.LEGS);
	public static final ItemMachineModCrashHelmet crashhelmet = new ItemMachineModCrashHelmet(ItemMachineModArmor.SteelMaterial, EntityEquipmentSlot.HEAD);
	public static final ItemMachineMod afterburner = new ItemAfterBurner();
	public static final ItemMachineMod collapsedstar = new ItemCollapsedStar();

	public static final ItemBlueprintContinuousMiner blueprintcontinuousminer = new ItemBlueprintContinuousMiner();

	public static final ItemBlueprintConduit blueprintconduit = new ItemBlueprintConduit();

	public static final ItemBlueprintFactory blueprintfactory = new ItemBlueprintFactory();

	public static final ItemBlueprintGenerator blueprintgenerator = new ItemBlueprintGenerator();

	public static final ItemBlueprintBatteryBank blueprintbatterybank = new ItemBlueprintBatteryBank();

	@ObjectHolder("bagger")
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

	public static final ItemMachineMod hammer = new ItemHammer();

	public static final ItemMachineMod debug = new ItemDebug();

	public static final ItemMachineMod boomarmsegment = new ItemBoomArmSegment();

	public static final ItemMachineMod drillingrig = new ItemDrillingRig();
	public static final ItemMachineMod dumptruck = new ItemDumpTruck();
	public static final ItemMachineMod loader = new ItemLoader();
	public static final ItemMachineMod laserminer = new ItemLaserMiner();

	public static final ItemMachineMod grader = new ItemGrader();

	public static final ItemMachineMod continuousminer = new ItemContinuousMiner();

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

	public static final ItemMachineMod choppersawblade = new ItemChopperAttachmentSawBlades();

	public static final ItemMachineMod dozerblade = new ItemDozerBlade();
	public static final ItemMachineMod tracks = new ItemTracks();
	public static final ItemMachineMod wheel = new ItemWheel();
	public static final ItemMachineMod loaderbucket = new ItemLoaderBucket();
	public static final ItemMachineMod dumperbed = new ItemDumperBed();
	public static final ItemMachineMod engine = new ItemEngine();
	public static final ItemMachineMod havesterhead = new ItemHarvesterHead();
	public static final ItemMachineMod fuelcan = new ItemFuelCan();

	public static final ItemMachineMod graderblade = new ItemGraderBlade();
	public static final ItemMachineMod wrench = new ItemWrench();

	/// buckets
	// public static final ItemBucket oilbucket = new ItemOilBucket(ModBlocks.oilFluidBlock);

	public static void init() {

		// machines

		if (Reference.enableBagger) {
			ForgeRegistries.ITEMS.register(bagger);// , "bagger");

		}
		if (Reference.enableBulldozer) {
			ForgeRegistries.ITEMS.register(bulldozer);// , "bulldozer");
		}

		if (Reference.enableCombine) {
			ForgeRegistries.ITEMS.register(combine);// , "combine");

		}
		if (Reference.enableCrane) {
			// ForgeRegistries.ITEMS.register(crane);// , "crane");

		}
		if (Reference.enableDrillingRig) {
			ForgeRegistries.ITEMS.register(drillingrig);// , "drillingrig");

		}

		if (Reference.enableDumptruck) {
			ForgeRegistries.ITEMS.register(dumptruck);// , "dumptruck");

		}

		if (Reference.enableExcavator) {
			ForgeRegistries.ITEMS.register(excavator);// , "excavator");

		}

		if (Reference.enableGrader) {
			ForgeRegistries.ITEMS.register(grader);// , "grader");

		}

		if (Reference.enableLawnmower) {
			ForgeRegistries.ITEMS.register(lawnmower);// , "lawnmower");

		}
		if (Reference.enableLoader) {
			ForgeRegistries.ITEMS.register(loader);// , "loader");

		}
		if (Reference.enableLaserMiner) {
			ForgeRegistries.ITEMS.register(laserminer);

		}
		if (Reference.enableContinuousMiner) {
			ForgeRegistries.ITEMS.register(continuousminer);

		}

		if (Reference.enableOilRig) {
			// ForgeRegistries.ITEMS.register(oilrig);// , "oilrig");

		}

		if (Reference.enablePaver) {
			ForgeRegistries.ITEMS.register(paver);// , "paver");

		}

		if (Reference.enablePumpJack) {
			// pump jack future use
		}
		if (Reference.enableRoadRoller) {
			ForgeRegistries.ITEMS.register(roadroller);// , "roadroller");

		}
		if (Reference.enableSemiTractor) {
			ForgeRegistries.ITEMS.register(semitractor);// , "semitractor");

		}
		if (Reference.enableSub) {
			ForgeRegistries.ITEMS.register(sub);// , "sub");

		}

		if (Reference.enableChopper) {
			ForgeRegistries.ITEMS.register(chopper);// , "chopper");

		}

		if (Reference.enableTractor) {
			ForgeRegistries.ITEMS.register(tractor);// , "tractor");

		}

		ForgeRegistries.ITEMS.register(bleakcrystal);// , "paverscreed");

		ForgeRegistries.ITEMS.register(paverscreed);// , "paverscreed");
		ForgeRegistries.ITEMS.register(rollerwheel);// , "rollerwheel");

		ForgeRegistries.ITEMS.register(liquidtanksegment);// , "liquidtanksegment");

		ForgeRegistries.ITEMS.register(prop);// , "prop");
		ForgeRegistries.ITEMS.register(propcage);// , "propcage");
		ForgeRegistries.ITEMS.register(turboprop);// , "turboprop");
		ForgeRegistries.ITEMS.register(operatorsbubble);// , "operatorsbubble");
		ForgeRegistries.ITEMS.register(subbody);// , "subbody");
		ForgeRegistries.ITEMS.register(airtank);// , "airtank");
		ForgeRegistries.ITEMS.register(powercell);// , "powercell");
		ForgeRegistries.ITEMS.register(light);// , "light");
		ForgeRegistries.ITEMS.register(lightmodule);// , "lightmodule");
		ForgeRegistries.ITEMS.register(elytrajetleg);
		ForgeRegistries.ITEMS.register(crashhelmet);
		ForgeRegistries.ITEMS.register(afterburner);
		ForgeRegistries.ITEMS.register(collapsedstar);

		ForgeRegistries.ITEMS.register(tracksegment);// , "tracksegment");

		ForgeRegistries.ITEMS.register(cutterbucket);// , "cutterbucket");
		ForgeRegistries.ITEMS.register(rigging);// , "rigging");
		ForgeRegistries.ITEMS.register(bucketwheel);// , "bucketwheel");
		ForgeRegistries.ITEMS.register(operatorsbooth);// , "operatorsbooth");
		ForgeRegistries.ITEMS.register(powerplant);// , "powerplant");
		ForgeRegistries.ITEMS.register(processingplant);// , "processingplant");
		ForgeRegistries.ITEMS.register(baggerstorage);// , "baggerstorage");
		ForgeRegistries.ITEMS.register(baggerbody);// , "baggerbody");

		ForgeRegistries.ITEMS.register(conecrusher);// , "conecrusher");

		ForgeRegistries.ITEMS.register(boomarmsegment);// , "boomarmsegment");

		ForgeRegistries.ITEMS.register(hose);// , "hose");
		ForgeRegistries.ITEMS.register(mowerdeck);// , "mowerdeck");
		ForgeRegistries.ITEMS.register(rawasphalt);// , "rawasphalt");

		ForgeRegistries.ITEMS.register(lidwithspout);// , "lidwithspout");
		ForgeRegistries.ITEMS.register(handdrill);// , "handdrill");
		ForgeRegistries.ITEMS.register(hammer);
		ForgeRegistries.ITEMS.register(debug);

		ForgeRegistries.ITEMS.register(anfo);// , "anfo");

		ForgeRegistries.ITEMS.register(cornseed);// , "cornseed");
		ForgeRegistries.ITEMS.register(trencher);// , "trencher");
		ForgeRegistries.ITEMS.register(irondust);// , "irondust");
		ForgeRegistries.ITEMS.register(golddust);// , "golddust");

		ForgeRegistries.ITEMS.register(drillhead);// , "drillhead");
		ForgeRegistries.ITEMS.register(drillpipe);// , "drillpipe");
		ForgeRegistries.ITEMS.register(plow);// , "plow");
		ForgeRegistries.ITEMS.register(planter);// , "planter");
		ForgeRegistries.ITEMS.register(sprayer);// , "sprayer");
		ForgeRegistries.ITEMS.register(woodengear);// , "woodengear");
		ForgeRegistries.ITEMS.register(stonegear);// , "stonegear");
		ForgeRegistries.ITEMS.register(irongear);// , "irongear");

		ForgeRegistries.ITEMS.register(transmission);// , "transmission");
		ForgeRegistries.ITEMS.register(turbofan);// , "turbofan");

		ForgeRegistries.ITEMS.register(camshaft);// , "camshaft");
		ForgeRegistries.ITEMS.register(piston);// , "piston");
		ForgeRegistries.ITEMS.register(turbo);// , "turbo");
		ForgeRegistries.ITEMS.register(turboengine);// , "turboengine");
		ForgeRegistries.ITEMS.register(flatbedtrailer);// , "flatbedtrailer");

		ForgeRegistries.ITEMS.register(livestocktrailer);// , "livestocktrailer");
		ForgeRegistries.ITEMS.register(tankertrailer);// , "tankertrailer");
		ForgeRegistries.ITEMS.register(cargotrailer);// , "cargotrailer");

		ForgeRegistries.ITEMS.register(choppersawblade);

		ForgeRegistries.ITEMS.register(dozerblade);// , "dozerblade");
		ForgeRegistries.ITEMS.register(tracks);// , "tracks");
		ForgeRegistries.ITEMS.register(wheel);// , "wheel");
		ForgeRegistries.ITEMS.register(loaderbucket);// , "loaderbucket");
		ForgeRegistries.ITEMS.register(dumperbed);// , "dumperbed");
		ForgeRegistries.ITEMS.register(engine);// , "engine");
		ForgeRegistries.ITEMS.register(havesterhead);// , "havesterhead");
		ForgeRegistries.ITEMS.register(fuelcan);// , "fuelcan");
		ForgeRegistries.ITEMS.register(graderblade);// , "graderblade");
		ForgeRegistries.ITEMS.register(wrench);// , "wrench");

		ForgeRegistries.ITEMS.register(carbondust);// , "carbondust");

		ForgeRegistries.ITEMS.register(steeldust);// , "steeldust");

		ForgeRegistries.ITEMS.register(steelingot);// , "steelingot");

		ForgeRegistries.ITEMS.register(citroniteingot);
		ForgeRegistries.ITEMS.register(iridoniumingot);
		ForgeRegistries.ITEMS.register(limoniteumingot);
		ForgeRegistries.ITEMS.register(magentiaingot);

		ForgeRegistries.ITEMS.register(crimsonitepebble);
		ForgeRegistries.ITEMS.register(azuriumlump);
		ForgeRegistries.ITEMS.register(unobtaniumgem);

		ForgeRegistries.ITEMS.register(aluminumingot);// , "aluminumingot");

		ForgeRegistries.ITEMS.register(blueprintcontinuousminer);

		ForgeRegistries.ITEMS.register(blueprintconduit);
		ForgeRegistries.ITEMS.register(blueprintfactory);
		ForgeRegistries.ITEMS.register(blueprintgenerator);
		ForgeRegistries.ITEMS.register(blueprintbatterybank);

		/// register ore dict
		OreDictionary.registerOre("gearWood", woodengear);
		OreDictionary.registerOre("gearStone", stonegear);
		OreDictionary.registerOre("gearIron", irongear);
		OreDictionary.registerOre("dustIron", irondust);
		OreDictionary.registerOre("dustGold", golddust);
		OreDictionary.registerOre("dustSteel", steeldust);
		OreDictionary.registerOre("ingotSteel", steelingot);

		OreDictionary.registerOre("ingotCitronite", citroniteingot);
		OreDictionary.registerOre("ingotIridonium", iridoniumingot);
		OreDictionary.registerOre("ingotLimoniteum", limoniteumingot);
		OreDictionary.registerOre("ingotMagentia", magentiaingot);

		OreDictionary.registerOre("ingotAluminum", aluminumingot);

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
			// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(crane, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "crane", "inventory"));

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

		if (Reference.enableLaserMiner) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(laserminer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "laserminer", "inventory"));
		}
		if (Reference.enableContinuousMiner) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(continuousminer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "continuousminer", "inventory"));

		}

		if (Reference.enableOilRig) {
			// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(oilrig, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "oilrig", "inventory"));

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

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bleakcrystal, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "bleakcrystal", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(carbondust, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "carbondust", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(steeldust, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "steeldust", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(steelingot, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "steelingot", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(citroniteingot, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "citroniteingot", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(iridoniumingot, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "iridoniumingot", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(limoniteumingot, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "limoniteumingot", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(magentiaingot, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "magentiaingot", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(crimsonitepebble, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "crimsonitepebble", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(azuriumlump, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "azuriumlump", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(unobtaniumgem, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "unobtaniumgem", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(aluminumingot, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "aluminumingot", "inventory"));

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
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(elytrajetleg, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "elytrajetleg", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(crashhelmet, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "crashhelmet", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(afterburner, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "afterburner", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(collapsedstar, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "collapsedstar", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blueprintcontinuousminer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "blueprintcontinuousminer", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blueprintconduit, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "blueprintconduit", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blueprintfactory, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "blueprintfactory", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blueprintgenerator, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "blueprintgenerator", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blueprintbatterybank, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "blueprintbatterybank", "inventory"));

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
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hammer, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "hammer", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(debug, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "debug", "inventory"));

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
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(choppersawblade, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "choppersawblade", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dozerblade, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dozerblade", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tracks, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "tracks", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(wheel, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "wheel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(loaderbucket, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "loaderbucket", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dumperbed, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "dumperbed", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(engine, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "engine", "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(havesterhead, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "harvesterhead", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(fuelcan, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "fuelcan", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(graderblade, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "graderblade", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(wrench, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + "wrench", "inventory"));

	}
}
