package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.block.BlockBioFuel;
import com.projectreddog.machinemod.block.BlockMachineAsphalt;
import com.projectreddog.machinemod.block.BlockMachineAsphaltSlab;
import com.projectreddog.machinemod.block.BlockMachineAssemblyTable;
import com.projectreddog.machinemod.block.BlockMachineBleakCrystal;
import com.projectreddog.machinemod.block.BlockMachineBleakDirt;
import com.projectreddog.machinemod.block.BlockMachineBleakGlass;
import com.projectreddog.machinemod.block.BlockMachineBleakOreAzurium;
import com.projectreddog.machinemod.block.BlockMachineBleakOreCitronite;
import com.projectreddog.machinemod.block.BlockMachineBleakOreCrimsonite;
import com.projectreddog.machinemod.block.BlockMachineBleakOreIridonium;
import com.projectreddog.machinemod.block.BlockMachineBleakOreLimoniteum;
import com.projectreddog.machinemod.block.BlockMachineBleakOreMagentia;
import com.projectreddog.machinemod.block.BlockMachineBleakOreUnobtanium;
import com.projectreddog.machinemod.block.BlockMachineBleakPortal;
import com.projectreddog.machinemod.block.BlockMachineBleakPortalFrame;
import com.projectreddog.machinemod.block.BlockMachineBleakStone;
import com.projectreddog.machinemod.block.BlockMachineCompressedAsphalt;
import com.projectreddog.machinemod.block.BlockMachineCompressedAsphaltSlab;
import com.projectreddog.machinemod.block.BlockMachineCrate;
import com.projectreddog.machinemod.block.BlockMachineCrudeOilStone;
import com.projectreddog.machinemod.block.BlockMachineDrilledAndesite;
import com.projectreddog.machinemod.block.BlockMachineDrilledDiorite;
import com.projectreddog.machinemod.block.BlockMachineDrilledGranite;
import com.projectreddog.machinemod.block.BlockMachineDrilledStone;
import com.projectreddog.machinemod.block.BlockMachineExplosivePackedDrilledStone;
import com.projectreddog.machinemod.block.BlockMachineMod;
import com.projectreddog.machinemod.block.BlockMachineModAsphaltMixer;
import com.projectreddog.machinemod.block.BlockMachineModBatteryBank;
import com.projectreddog.machinemod.block.BlockMachineModBlastedAndesite;
import com.projectreddog.machinemod.block.BlockMachineModBlastedCoal;
import com.projectreddog.machinemod.block.BlockMachineModBlastedDiamond;
import com.projectreddog.machinemod.block.BlockMachineModBlastedDiorite;
import com.projectreddog.machinemod.block.BlockMachineModBlastedEmerald;
import com.projectreddog.machinemod.block.BlockMachineModBlastedGold;
import com.projectreddog.machinemod.block.BlockMachineModBlastedGranite;
import com.projectreddog.machinemod.block.BlockMachineModBlastedIron;
import com.projectreddog.machinemod.block.BlockMachineModBlastedLapis;
import com.projectreddog.machinemod.block.BlockMachineModBlastedRedstone;
import com.projectreddog.machinemod.block.BlockMachineModBlastedStone;
import com.projectreddog.machinemod.block.BlockMachineModBleakCrystalInfusedSand;
import com.projectreddog.machinemod.block.BlockMachineModCentrifuge;
import com.projectreddog.machinemod.block.BlockMachineModConduit;
import com.projectreddog.machinemod.block.BlockMachineModConveyor;
import com.projectreddog.machinemod.block.BlockMachineModConveyorT2;
import com.projectreddog.machinemod.block.BlockMachineModCorn;
import com.projectreddog.machinemod.block.BlockMachineModDistiller;
import com.projectreddog.machinemod.block.BlockMachineModFactory;
import com.projectreddog.machinemod.block.BlockMachineModFeedTrough;
import com.projectreddog.machinemod.block.BlockMachineModFermenter;
import com.projectreddog.machinemod.block.BlockMachineModFractionalDistillation;
import com.projectreddog.machinemod.block.BlockMachineModFuelPump;
import com.projectreddog.machinemod.block.BlockMachineModGenerator;
import com.projectreddog.machinemod.block.BlockMachineModHoloScanner;
import com.projectreddog.machinemod.block.BlockMachineModLiquidPipe;
import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.block.BlockMachineModScreen;
import com.projectreddog.machinemod.block.BlockMachineModShredder;
import com.projectreddog.machinemod.block.BlockMachineModTowerCrane;
import com.projectreddog.machinemod.block.BlockMachineModTruboFurnace;
import com.projectreddog.machinemod.block.BlockMachineModWellHead;
import com.projectreddog.machinemod.block.BlockMachineMowedGrass;
import com.projectreddog.machinemod.block.BlockMachineSteelBlock;
import com.projectreddog.machinemod.block.BlockOilFluid;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityAsphaltMixer;
import com.projectreddog.machinemod.tileentities.TileEntityAssemblyTable;
import com.projectreddog.machinemod.tileentities.TileEntityBatteryBank;
import com.projectreddog.machinemod.tileentities.TileEntityCentrifuge;
import com.projectreddog.machinemod.tileentities.TileEntityConduit;
import com.projectreddog.machinemod.tileentities.TileEntityConveyor;
import com.projectreddog.machinemod.tileentities.TileEntityConveyorT2;
import com.projectreddog.machinemod.tileentities.TileEntityCrate;
import com.projectreddog.machinemod.tileentities.TileEntityDistiller;
import com.projectreddog.machinemod.tileentities.TileEntityFactory;
import com.projectreddog.machinemod.tileentities.TileEntityFeedTrough;
import com.projectreddog.machinemod.tileentities.TileEntityFermenter;
import com.projectreddog.machinemod.tileentities.TileEntityFractionalDistillation;
import com.projectreddog.machinemod.tileentities.TileEntityFuelPump;
import com.projectreddog.machinemod.tileentities.TileEntityGenerator;
import com.projectreddog.machinemod.tileentities.TileEntityHoloScanner;
import com.projectreddog.machinemod.tileentities.TileEntityLiquidPipe;
import com.projectreddog.machinemod.tileentities.TileEntityPrimaryCrusher;
import com.projectreddog.machinemod.tileentities.TileEntityScreen;
import com.projectreddog.machinemod.tileentities.TileEntityShredder;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;
import com.projectreddog.machinemod.tileentities.TileEntityTurboFurnace;
import com.projectreddog.machinemod.tileentities.TileEntityWellHead;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	public static final Block machineassemblytable = new BlockMachineAssemblyTable();
	public static final BlockMachineMod machineasphalt = new BlockMachineAsphalt();
	public static final BlockMachineMod machineasphaltslab = new BlockMachineAsphaltSlab();
	public static final BlockMachineMod machinebleakglass = new BlockMachineBleakGlass();

	public static final BlockMachineMod machinebleakdirt = new BlockMachineBleakDirt();

	public static final BlockMachineMod machinebleakcrystalinfusedsand = new BlockMachineModBleakCrystalInfusedSand();

	public static final BlockMachineMod machinebleakstone = new BlockMachineBleakStone();
	public static final BlockMachineMod machinebleakoreiridonium = new BlockMachineBleakOreIridonium();
	public static final BlockMachineMod machinebleakoremagentia = new BlockMachineBleakOreMagentia();

	public static final BlockMachineMod machinebleakorelimoniteum = new BlockMachineBleakOreLimoniteum();
	public static final BlockMachineMod machinebleakorecrimsonite = new BlockMachineBleakOreCrimsonite();
	public static final BlockMachineMod machinebleakoreazurium = new BlockMachineBleakOreAzurium();
	public static final BlockMachineMod machinebleakorecitronite = new BlockMachineBleakOreCitronite();
	public static final BlockMachineMod machinebleakoreunobtanium = new BlockMachineBleakOreUnobtanium();

	public static final BlockMachineMod steelblock = new BlockMachineSteelBlock();

	public static final Block machinegenerator = new BlockMachineModGenerator();

	public static final Block machinebatterybank = new BlockMachineModBatteryBank();

	public static final Block machineturbofurnace = new BlockMachineModTruboFurnace();

	public static final Block machineconduit = new BlockMachineModConduit();

	public static final BlockMachineMod machinecompressedasphalt = new BlockMachineCompressedAsphalt();
	public static final BlockMachineMod machinecompressedasphaltslab = new BlockMachineCompressedAsphaltSlab();

	public static final BlockMachineMod machinecrudeoilstone = new BlockMachineCrudeOilStone();

	public static final BlockMachineMod machinedrilledstone = new BlockMachineDrilledStone();
	public static final BlockMachineMod machinedrilledandesite = new BlockMachineDrilledAndesite();
	public static final BlockMachineMod machinedrilleddiorite = new BlockMachineDrilledDiorite();
	public static final BlockMachineMod machinedrilledgranite = new BlockMachineDrilledGranite();

	public static final BlockMachineMod machineexplosivepackeddrilledstone = new BlockMachineExplosivePackedDrilledStone();

	public static final BlockMachineMod machineblastedstone = new BlockMachineModBlastedStone();

	public static final BlockMachineMod machineblastedgranite = new BlockMachineModBlastedGranite();
	public static final BlockMachineMod machineblasteddiorite = new BlockMachineModBlastedDiorite();
	public static final BlockMachineMod machineblastedandesite = new BlockMachineModBlastedAndesite();
	public static final BlockMachineMod machineblastedgold = new BlockMachineModBlastedGold();
	public static final BlockMachineMod machineblastediron = new BlockMachineModBlastedIron();
	public static final BlockMachineMod machineblastedcoal = new BlockMachineModBlastedCoal();
	public static final BlockMachineMod machineblastedlapis = new BlockMachineModBlastedLapis();
	public static final BlockMachineMod machineblasteddiamond = new BlockMachineModBlastedDiamond();
	public static final BlockMachineMod machineblastedredstone = new BlockMachineModBlastedRedstone();
	public static final BlockMachineMod machineblastedemerald = new BlockMachineModBlastedEmerald();

	// public static final BlockMachineMod machineblastedstone2 = new BlockMachineModBlastedStone2();
	public static final Block corn = new BlockMachineModCorn();
	public static final Block machinebleakcrystal = new BlockMachineBleakCrystal();

	public static final Block machinedistiller = new BlockMachineModDistiller();
	public static final Block machinefactory = new BlockMachineModFactory();

	public static final Block machinefermenter = new BlockMachineModFermenter();
	public static final Block machinefuelpump = new BlockMachineModFuelPump();
	public static final Block machinetowercrane = new BlockMachineModTowerCrane();

	public static final Block machinewellhead = new BlockMachineModWellHead();

	public static final Block machineprimarycrhuser = new BlockMachineModPrimaryCrusher();
	public static final Block machinecentrifuge = new BlockMachineModCentrifuge();
	public static final Block machinecrate = new BlockMachineCrate();

	public static final Block machineconveyor = new BlockMachineModConveyor();
	public static final Block machineconveyort2 = new BlockMachineModConveyorT2();

	public static final Block machinefeedtrough = new BlockMachineModFeedTrough();

	public static final Block machineholoscanner = new BlockMachineModHoloScanner();

	public static final Block machineshredder = new BlockMachineModShredder();

	public static final Block machinebleakportalframe = new BlockMachineBleakPortalFrame();

	public static final Block machinebleakportal = new BlockMachineBleakPortal();

	public static final Block machinefractionaldistillation = new BlockMachineModFractionalDistillation();
	public static final Block machineliquidPipe = new BlockMachineModLiquidPipe();

	public static final Block machineasphaltmixer = new BlockMachineModAsphaltMixer();

	// public static Fluid fluidBioFuel = new Fluid("BioFuel");

	public static Fluid fluidOil = new Fluid("oil", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
	public static Fluid fluidDiesel = new Fluid("diesel", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
	public static Fluid fluidBitumen = new Fluid("bitumen", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
	public static Fluid fluidNaphtha = new Fluid("naphtha", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
	public static Fluid fluidJetFuel = new Fluid("jetfuel", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));

	public static final Block machinescreen = new BlockMachineModScreen();
	public static final Block machinemowedgrass = new BlockMachineMowedGrass();
	public static BlockBioFuel biofuel;
	public static Block oilFluidBlock;
	public static ItemBlock itemBlockBlastedStone;

	public static void init() {
		ForgeRegistries.BLOCKS.register(machineassemblytable);// Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE

		ForgeRegistries.ITEMS.register(new ItemBlock(machineassemblytable).setRegistryName(ModBlocks.machineassemblytable.getRegistryName()));

		// TODO need to register items for all blocks too!
		ForgeRegistries.BLOCKS.register(steelblock);
		ForgeRegistries.ITEMS.register(new ItemBlock(steelblock).setRegistryName(ModBlocks.steelblock.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinegenerator);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinegenerator).setRegistryName(ModBlocks.machinegenerator.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebatterybank);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebatterybank).setRegistryName(ModBlocks.machinebatterybank.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineturbofurnace);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineturbofurnace).setRegistryName(ModBlocks.machineturbofurnace.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineconduit);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineconduit).setRegistryName(ModBlocks.machineconduit.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineasphalt);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineasphalt).setRegistryName(ModBlocks.machineasphalt.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineasphaltslab);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineasphaltslab).setRegistryName(ModBlocks.machineasphaltslab.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebleakdirt);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakdirt).setRegistryName(ModBlocks.machinebleakdirt.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebleakcrystalinfusedsand);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakcrystalinfusedsand).setRegistryName(ModBlocks.machinebleakcrystalinfusedsand.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebleakglass);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakglass).setRegistryName(ModBlocks.machinebleakglass.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebleakcrystal);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakcrystal).setRegistryName(ModBlocks.machinebleakcrystal.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebleakstone);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakstone).setRegistryName(ModBlocks.machinebleakstone.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinebleakoreiridonium);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakoreiridonium).setRegistryName(ModBlocks.machinebleakoreiridonium.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinebleakoremagentia);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakoremagentia).setRegistryName(ModBlocks.machinebleakoremagentia.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebleakorelimoniteum);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakorelimoniteum).setRegistryName(ModBlocks.machinebleakorelimoniteum.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinebleakorecrimsonite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakorecrimsonite).setRegistryName(ModBlocks.machinebleakorecrimsonite.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinebleakoreazurium);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakoreazurium).setRegistryName(ModBlocks.machinebleakoreazurium.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinebleakorecitronite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakorecitronite).setRegistryName(ModBlocks.machinebleakorecitronite.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinebleakoreunobtanium);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakoreunobtanium).setRegistryName(ModBlocks.machinebleakoreunobtanium.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinecompressedasphalt);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinecompressedasphalt).setRegistryName(ModBlocks.machinecompressedasphalt.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinecompressedasphaltslab);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinecompressedasphaltslab).setRegistryName(ModBlocks.machinecompressedasphaltslab.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinecrudeoilstone);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinecrudeoilstone).setRegistryName(ModBlocks.machinecrudeoilstone.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinecrate);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinecrate).setRegistryName(ModBlocks.machinecrate.getRegistryName()));
		ForgeRegistries.BLOCKS.register(corn);
		ForgeRegistries.ITEMS.register(new ItemBlock(corn).setRegistryName(ModBlocks.corn.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinedrilledstone);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinedrilledstone).setRegistryName(ModBlocks.machinedrilledstone.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinedrilledandesite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinedrilledandesite).setRegistryName(ModBlocks.machinedrilledandesite.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinedrilleddiorite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinedrilleddiorite).setRegistryName(ModBlocks.machinedrilleddiorite.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinedrilledgranite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinedrilledgranite).setRegistryName(ModBlocks.machinedrilledgranite.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineexplosivepackeddrilledstone);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineexplosivepackeddrilledstone).setRegistryName(ModBlocks.machineexplosivepackeddrilledstone.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastedstone);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedstone).setRegistryName(ModBlocks.machineblastedstone.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineblastedgranite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedgranite).setRegistryName(ModBlocks.machineblastedgranite.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineblasteddiorite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblasteddiorite).setRegistryName(ModBlocks.machineblasteddiorite.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastedandesite);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedandesite).setRegistryName(ModBlocks.machineblastedandesite.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastedgold);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedgold).setRegistryName(ModBlocks.machineblastedgold.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastediron);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastediron).setRegistryName(ModBlocks.machineblastediron.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastedcoal);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedcoal).setRegistryName(ModBlocks.machineblastedcoal.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastedlapis);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedlapis).setRegistryName(ModBlocks.machineblastedlapis.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblasteddiamond);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblasteddiamond).setRegistryName(ModBlocks.machineblasteddiamond.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastedredstone);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedredstone).setRegistryName(ModBlocks.machineblastedredstone.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineblastedemerald);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedemerald).setRegistryName(ModBlocks.machineblastedemerald.getRegistryName()));

		// ForgeRegistries.BLOCKS.register(machineblastedstone2);
		// ForgeRegistries.ITEMS.register(new ItemBlock(machineblastedstone2).setRegistryName(ModBlocks.machineblastedstone2.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineprimarycrhuser);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineprimarycrhuser).setRegistryName(ModBlocks.machineprimarycrhuser.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinecentrifuge);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinecentrifuge).setRegistryName(ModBlocks.machinecentrifuge.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineconveyor);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineconveyor).setRegistryName(ModBlocks.machineconveyor.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineconveyort2);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineconveyort2).setRegistryName(ModBlocks.machineconveyort2.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinefeedtrough);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinefeedtrough).setRegistryName(ModBlocks.machinefeedtrough.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineholoscanner);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineholoscanner).setRegistryName(ModBlocks.machineholoscanner.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machineshredder);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineshredder).setRegistryName(ModBlocks.machineshredder.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinebleakportalframe);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakportalframe).setRegistryName(ModBlocks.machinebleakportalframe.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinebleakportal);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinebleakportal).setRegistryName(ModBlocks.machinebleakportal.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinefractionaldistillation);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinefractionaldistillation).setRegistryName(ModBlocks.machinefractionaldistillation.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineasphaltmixer);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineasphaltmixer).setRegistryName(ModBlocks.machineasphaltmixer.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machineliquidPipe);
		ForgeRegistries.ITEMS.register(new ItemBlock(machineliquidPipe).setRegistryName(ModBlocks.machineliquidPipe.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinescreen);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinescreen).setRegistryName(ModBlocks.machinescreen.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinemowedgrass);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinemowedgrass).setRegistryName(ModBlocks.machinemowedgrass.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinefuelpump);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinefuelpump).setRegistryName(ModBlocks.machinefuelpump.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinetowercrane);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinetowercrane).setRegistryName(ModBlocks.machinetowercrane.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinedistiller);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinedistiller).setRegistryName(ModBlocks.machinedistiller.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinefactory);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinefactory).setRegistryName(ModBlocks.machinefactory.getRegistryName()));

		ForgeRegistries.BLOCKS.register(machinefermenter);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinefermenter).setRegistryName(ModBlocks.machinefermenter.getRegistryName()));
		ForgeRegistries.BLOCKS.register(machinewellhead);
		ForgeRegistries.ITEMS.register(new ItemBlock(machinewellhead).setRegistryName(ModBlocks.machinewellhead.getRegistryName()));

		// next section for tile entities regsistration.
		GameRegistry.registerTileEntity(TileEntityPrimaryCrusher.class, Reference.MODBLOCK_MACHINE_PRIMARY_CRUSHER);
		GameRegistry.registerTileEntity(TileEntityCentrifuge.class, Reference.MODBLOCK_MACHINE_CENTRIFUGE);

		GameRegistry.registerTileEntity(TileEntityCrate.class, Reference.MODBLOCK_MACHINE_CRATE);

		GameRegistry.registerTileEntity(TileEntityConveyor.class, Reference.MODBLOCK_MACHINE_CONVEYOR);
		GameRegistry.registerTileEntity(TileEntityConveyorT2.class, Reference.MODBLOCK_MACHINE_CONVEYOR_T2);
		GameRegistry.registerTileEntity(TileEntityFeedTrough.class, Reference.MODBLOCK_MACHINE_FEED_TROUGH);

		GameRegistry.registerTileEntity(TileEntityHoloScanner.class, Reference.MODBLOCK_MACHINE_HOLO_SCANNER);

		GameRegistry.registerTileEntity(TileEntityScreen.class, Reference.MODBLOCK_MACHINE_SCREEN);
		GameRegistry.registerTileEntity(TileEntityFractionalDistillation.class, Reference.MODBLOCK_MACHINE_FRACTIONAL_DISTILLATION);
		GameRegistry.registerTileEntity(TileEntityAsphaltMixer.class, Reference.MODBLOCK_MACHINE_ASPHALT_MIXER);

		GameRegistry.registerTileEntity(TileEntityFuelPump.class, Reference.MODBLOCK_MACHINE_FUEL_PUMP);
		GameRegistry.registerTileEntity(TileEntityDistiller.class, Reference.MODBLOCK_MACHINE_DISTILLER);
		GameRegistry.registerTileEntity(TileEntityFermenter.class, Reference.MODBLOCK_MACHINE_FERMENTER);

		GameRegistry.registerTileEntity(TileEntityWellHead.class, Reference.MODBLOCK_MACHINE_WELL_HEAD);

		GameRegistry.registerTileEntity(TileEntityLiquidPipe.class, Reference.MODBLOCK_MACHINE_LIQUID_PIPE);
		GameRegistry.registerTileEntity(TileEntityFactory.class, Reference.MODBLOCK_MACHINE_FACTORY);

		GameRegistry.registerTileEntity(TileEntityAssemblyTable.class, Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);

		GameRegistry.registerTileEntity(TileEntityGenerator.class, Reference.MODBLOCK_MACHINE_GENERATOR);

		GameRegistry.registerTileEntity(TileEntityBatteryBank.class, Reference.MODBLOCK_MACHINE_BATTERY_BANK);

		GameRegistry.registerTileEntity(TileEntityConduit.class, Reference.MODBLOCK_MACHINE_CONDUIT);

		GameRegistry.registerTileEntity(TileEntityTurboFurnace.class, Reference.MODBLOCK_MACHINE_TURBO_FURNACE);

		GameRegistry.registerTileEntity(TileEntityShredder.class, Reference.MODBLOCK_MACHINE_SHREDDER);

		GameRegistry.registerTileEntity(TileEntityTowerCrane.class, Reference.MODBLOCK_MACHINE_TOWER_CRANE);

		// /Register Fluids
		FluidRegistry.registerFluid(fluidOil);

		FluidRegistry.registerFluid(fluidDiesel);
		FluidRegistry.registerFluid(fluidBitumen);
		FluidRegistry.registerFluid(fluidNaphtha);
		FluidRegistry.registerFluid(fluidJetFuel);

		fluidOil.setViscosity(6600);

		fluidDiesel.setViscosity(3000);
		fluidBitumen.setViscosity(5000);
		fluidNaphtha.setViscosity(2000);
		fluidJetFuel.setViscosity(2500);

		oilFluidBlock = new BlockOilFluid(fluidOil, Material.WATER);

		// public static Fluid fluidDiesel = new Fluid("diesel", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
		// public static Fluid fluidBitumen = new Fluid("bitumen", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
		// public static Fluid fluidNaphtha = new Fluid("naphtha", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
		// public static Fluid fluidJetFuel = new Fluid("jetfuel", new ResourceLocation("machinemod", Reference.FLUID_OIL_STILL_TEXTURE_LOCATION), new ResourceLocation("machinemod", Reference.FLUID_OIL_FLOWING_TEXTURE_LOCATION));
		//
		//

		ForgeRegistries.BLOCKS.register(oilFluidBlock);
		ForgeRegistries.ITEMS.register(new ItemBlock(oilFluidBlock).setRegistryName(ModBlocks.oilFluidBlock.getRegistryName()));

		// fluidBioFuel.setDensity(10);
		// FluidRegistry.registerFluid(fluidBioFuel);
		// biofuel = new BlockBioFuel(fluidBioFuel, Material.water) ;
		//
		// ForgeRegistries.BLOCKS.registerBlock(biofuel,
		// Reference.MODBLOCK_MACHINE_FLUID_BIOFUEL);
		// fluidBioFuel.setUnlocalizedName(biofuel.getUnlocalizedName());

		/// ore Dict Blocks
		OreDictionary.registerOre("blockSteel", steelblock);
	}

	public static void initBlockRender() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineassemblytable), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineasphalt), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_ASPHALT, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineasphaltslab), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_ASPHALT_SLAB, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakdirt), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_DIRT, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakcrystal), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_CRYSTAL, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakcrystalinfusedsand), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_CRYSTAL_INFUSED_SAND, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakglass), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_GLASS, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakstone), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_STONE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakoreiridonium), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_ORE_IRIDONIUM, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakoremagentia), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_ORE_MAGENTIA, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakorelimoniteum), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_ORE_LIMONITEUM, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakorecrimsonite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_ORE_CRIMSONITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakoreazurium), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_ORE_AZURIUM, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakorecitronite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_ORE_CITRONITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakoreunobtanium), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_ORE_UNOBTANIUM, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinecompressedasphalt), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_COMPRESSED_ASPHALT, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinecompressedasphaltslab), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_COMPRESSED_ASPHALT_SLAB, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(steelblock), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_STEEL_BLOCK, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinegenerator), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_GENERATOR, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebatterybank), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BATTERY_BANK, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineturbofurnace), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_TURBO_FURNACE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineconduit), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_CONDUIT, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinecrudeoilstone), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_CRUDE_OIL_STONE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinecrate), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_CRATE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinedrilledstone), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_DRILLED_STONE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinefractionaldistillation), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_FRACTIONAL_DISTILLATION, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineasphaltmixer), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_ASPHALT_MIXER, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineliquidPipe), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_LIQUID_PIPE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinedrilledandesite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_DRILLED_ANDESITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinedrilleddiorite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_DRILLED_DIORITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinedrilledgranite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_DRILLED_GRANITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineexplosivepackeddrilledstone), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_EXPLOSIVE_PACKED_DRILLED_STONE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedstone), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedgranite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_GRANITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblasteddiorite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_DIORITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedandesite), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_ANDESITE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedgold), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_GOLD, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastediron), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_IRON, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedcoal), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_COAL, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedlapis), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_LAPIS, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblasteddiamond), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_DIAMOND, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedredstone), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_REDSTONE, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineblastedemerald), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_EMERALD, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(corn), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_CORN, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineprimarycrhuser), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_PRIMARY_CRUSHER, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinecentrifuge), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_CENTRIFUGE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineconveyor), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_CONVEYOR, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineconveyort2), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_CONVEYOR_T2, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineshredder), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_SHREDDER, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinefeedtrough), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_FEED_TROUGH, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machineholoscanner), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_HOLO_SCANNER, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinescreen), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_SCREEN, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakportalframe), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_PORTAL_FRAME, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinebleakportal), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLEAK_PORTAL, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinemowedgrass), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_MOWED_GRASS, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinefuelpump), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_FUEL_PUMP, "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinetowercrane), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_TOWER_CRANE, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinedistiller), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_DISTILLER, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinefactory), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_FACTORY, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinefermenter), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_FERMENTER, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(machinewellhead), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_WELL_HEAD, "inventory"));

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(biofuel), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_FLUID_BIOFUEL, "inventory"));

	}
}
