package com.projectreddog.machinemod.reference;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

public class Reference {
	// common constants for our mod
	// example: public static final string MOD_ID ="Blah";

	public static final GameProfile gameProfile = new GameProfile(UUID.nameUUIDFromBytes("TECHSTACKS HEAVY MACHINERY MOD".getBytes()), "[TECHSTACKS HEAVY MACHINERY MOD]");
	public static final String MOD_ID = "machinemod";
	public static final String MOD_NAME = "MachineMod";
	// public static final String VERSION = "${version}";
	public static final String CLIENT_PROXY_CLASS = "com.projectreddog.machinemod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.projectreddog.machinemod.proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = "com.projectreddog.machinemod.client.gui.GuiFactory";
	// textures for Entities
	public static final String MODEL_BULLDOZER_TEXTURE_LOCATION = "models/bulldozer.png";
	public static final String MODEL_TRACK_LOADER_TEXTURE_LOCATION = "models/trackloader.png";

	public static final String MODEL_TRACTOR_TEXTURE_LOCATION = "models/modeltractor.png";
	public static final String MODEL_LAWNMOWER_TEXTURE_LOCATION = "models/lawnmower.png";
	public static final String MODEL_TESTCUBE_TEXTURE_LOCATION = "models/uv.png";

	public static final String MODEL_SUB_TEXTURE_LOCATION = "models/sub.png";
	public static final String MODEL_CHOPPER_TEXTURE_LOCATION = "models/chopper.png";

	public static final String MODEL_SEMI_TEXTURE_LOCATION = "models/semi.png";

	public static final String FLUID_OIL_STILL_TEXTURE_LOCATION = "textures/blocks/oil_still.png";
	public static final String FLUID_OIL_FLOWING_TEXTURE_LOCATION = "textures/blocks/oil_flowing.png";
	public static final String MODEL_FUEL_PUMP_TEXTURE_LOCATION = "models/fuelpump.png";
	public static final String MODEL_DISTILLER_TEXTURE_LOCATION = "models/distiller.png";
	public static final String MODEL_FACTORY_TEXTURE_LOCATION = "models/factory.png";

	public static final String MODEL_FRACTIONAL_DISTILATION_TEXTURE_LOCATION = "models/fractionaldistilation.png";
	public static final String MODEL_FERMENTER_TEXTURE_LOCATION = "models/fermenter.png";

	public static final String MODEL_WELL_HEAD_TEXTURE_LOCATION = "models/wellhead.png";
	public static final String MODEL_PIPE_TEXTURE_LOCATION = "models/pipe.png";
	public static final String MODEL_CRATE_TEXTURE_LOCATION = "models/crate.png";
	public static final String MODEL_HOLOSCANNER_TEXTURE_LOCATION = "models/holoscanner.png";

	public static final String MODEL_COMBINE_TEXTURE_LOCATION = "models/combine.png";
	public static final String MODEL_DRILLINGRIG_TEXTURE_LOCATION = "models/drillingrig.png";
	public static final String MODEL_DUMPTRUCK_TEXTURE_LOCATION = "models/dumptruck.png";
	public static final String MODEL_UNDERGROUND_DUMPTRUCK_TEXTURE_LOCATION = "models/undergrounddumptruck.png";

	public static final String MODEL_LOADER_TEXTURE_LOCATION = "models/modelloader.png";

	public static final String MODEL_UNDER_GROUND_LOADER_TEXTURE_LOCATION = "models/undergroundloader.png";
	public static final String MODEL_CONTINUOUSMINER_TEXTURE_LOCATION = "models/continuousminer.png";

	public static final String MODEL_BAGGER_TEXTURE_LOCATION = "models/bagger.png";
	public static final String MODEL_OIL_RIG_TEXTURE_LOCATION = "models/bagger.png";
	public static final String MODEL_PUMP_JACK_TEXTURE_LOCATION = "models/pumpjack.png";

	public static final String MODEL_GRADER_TEXTURE_LOCATION = "models/graderprototype.png";

	public static final String MODEL_PAVER_TEXTURE_LOCATION = "models/paver.png";
	public static final String MODEL_ROADROLLER_TEXTURE_LOCATION = "models/steamroller.png";

	public static final String MODEL_CRANE_TEXTURE_LOCATION = "models/crane.png";
	public static final String MODEL_BEAM_TEXTURE_LOCATION = "models/beam.png";
	public static final String MODEL_LASER_MINER_TEXTURE_LOCATION = "models/laserminer.png";

	public static final String MODEL_EXCAVATOR_TEXTURE_LOCATION = "models/excavator.png";

	public static final String MODEL_ElYTRA_JET_BELT_TEXTURE_LOCATION = "models/elytrajetbelt.png";

	public static final String GUI_FUEL_GUAGE_TEXTURE_LOCATION = "textures/gui/gui_overlay_fuel_gage.png";
	public static final String GUI_FUEL_LEVEL_TEXTURE_LOCATION = "textures/gui/gui_fuel_level_marker.png";
	public static final String GUI_WORK_PROGRESS_TEXTURE_LOCATION = "textures/gui/workprogress.png";
	public static final String GUI_SCROLL_BAR_MARKER_LOCATION = "textures/gui/scrollbarmarker.png";
	public static final String GUI_TURBO_FURNACE_ARROW_CUTOUT = "textures/gui/turbofurnacearrow.png";
	public static final String GUI_TURBO_FURNACE_ARROW_FILL = "textures/gui/arrow fill.png";
	public static final String GUI_TURBO_FURNACE_FLAME = "textures/gui/turbofurnaceflame.png";
	public static final String GUI_TURBO_FURNACE_FLAME_CUTOUT = "textures/gui/flamecutout.png";

	public static final String BLOCK_BLUEPRINT_TRANSPARENT_LOCATION = "textures/blocks/blockblueprinttransparent.png";
	// textures for Tile Entities
	public static final String MODEL_TILEENTITY_PRIMARY_CRUSHER_TEXTURE_LOCATION = "models/primarycrusher.png";
	public static final String MODEL_TILEENTITY_CENRIFUGE_TEXTURE_LOCATION = "models/centrifuge.png";

	public static final int GUI_DUMP_TRUCK = 0;
	public static final int GUI_LOADER = 1;
	public static final int GUI_TRACTOR = 2;
	public static final int GUI_WIDEBEDTRUCK = 3;
	public static final int GUI_COMBINE = 4;
	public static final int GUI_CANNER = 5;
	public static final int GUI_FERMENTER = 6;
	public static final int GUI_DISTILLER = 7;
	public static final int GUI_SCREEN = 8;
	public static final int GUI_CENTRIFUGE = 9;
	public static final int GUI_PAVER = 10;
	public static final int GUI_GRADER = 11;
	public static final int GUI_BAGGER = 12;
	public static final int GUI_FRACTIONALDISTILLATION = 13;
	public static final int GUI_EXCAVATOR = 14;
	public static final int GUI_PRIMARY_CRUSHER = 15;
	public static final int GUI_CHOPPER = 16;
	public static final int GUI_CONTINUOUSMINER = 17;
	public static final int GUI_ASSEMBLY_TABLE = 18;
	public static final int GUI_TURBO_FURNACE = 19;
	public static final int GUI_TRACK_LOADER = 20;
	public static final int GUI_LASAERMINER = 21;
	public static final int GUI_FEED_TROUGH = 22;
	public static final int GUI_TOWER_CRANE = 23;
	public static final int GUI_TOWER_CRANE_SETTINGS = 24;
	public static final int GUI_LASAER_LEVEL = 25;
	public static final int GUI_UNDERGROUND_LOADER = 26;

	public static final int MAX_TREE_DEPTH = 256;
	public static final int MAX_TREE_WIDTH = 4;
	public static final String MODBLOCK_MACHINE_ASSEMBLY_TABLE = "machineassemblytable";
	public static final String MODBLOCK_STEEL_BLOCK = "steelblock";

	public static final String MODBLOCK_MACHINE_ASPHALT = "machineasphalt";
	public static final String MODBLOCK_MACHINE_ASPHALT_SLAB = "machineasphaltslab";

	public static final String MODBLOCK_MACHINE_BLEAK_DIRT = "machinebleakdirt";

	public static final String MODBLOCK_MACHINE_BLEAK_CRYSTAL = "machinebleakcrystal";

	public static final String MODBLOCK_MACHINE_BLEAK_STONE = "machinebleakstone";
	public static final String MODBLOCK_MACHINE_BLEAK_ORE_IRIDONIUM = "machinebleakoreiridonium";
	public static final String MODBLOCK_MACHINE_BLEAK_ORE_MAGENTIA = "machinebleakoremagentia";

	public static final String MODBLOCK_MACHINE_BLEAK_ORE_LIMONITEUM = "machinebleakorelimoniteum";
	public static final String MODBLOCK_MACHINE_BLEAK_ORE_CRIMSONITE = "machinebleakorecrimsonite";
	public static final String MODBLOCK_MACHINE_BLEAK_ORE_AZURIUM = "machinebleakoreazurium";
	public static final String MODBLOCK_MACHINE_BLEAK_ORE_CITRONITE = "machinebleakorecitronite";
	public static final String MODBLOCK_MACHINE_BLEAK_ORE_UNOBTANIUM = "machinebleakoreunobtanium";

	public static final String MODBLOCK_MACHINE_CRATE = "machinecrate";

	public static final String MODBLOCK_MACHINE_CRUDE_OIL_STONE = "machinecrudeoilstone";
	public static final String MODBLOCK_MACHINE_FRACTIONAL_DISTILLATION = "machinefractionaldistillation";
	public static final String MODBLOCK_MACHINE_ASPHALT_MIXER = "machineasphaltmixer";

	public static final String MODBLOCK_MACHINE_COMPRESSED_ASPHALT = "machinecompressedasphalt";
	public static final String MODBLOCK_MACHINE_COMPRESSED_ASPHALT_SLAB = "machinecompressedasphaltslab";

	public static final String MODBLOCK_MACHINE_MOWED_GRASS = "machinemowedgrass";

	public static final String MODBLOCK_MACHINE_DRILLED_STONE = "machinedrilledstone";
	public static final String MODBLOCK_MACHINE_LIQUID_PIPE = "machineliquidpipe";

	public static final String MODBLOCK_MACHINE_DRILLED_ANDESITE = "machinedrilledandesite";
	public static final String MODBLOCK_MACHINE_DRILLED_DIORITE = "machinedrilleddiorite";
	public static final String MODBLOCK_MACHINE_DRILLED_GRANITE = "machinedrilledgranite";

	public static final String MODBLOCK_MACHINE_BLASTED_STONE = "machineblastedstone";
	public static final String MODBLOCK_MACHINE_BLASTED_GRANITE = "machineblastedgranite";
	public static final String MODBLOCK_MACHINE_BLASTED_DIORITE = "machineblasteddiorite";
	public static final String MODBLOCK_MACHINE_BLASTED_ANDESITE = "machineblastedandesite";
	public static final String MODBLOCK_MACHINE_BLASTED_GOLD = "machineblastedgold";
	public static final String MODBLOCK_MACHINE_BLASTED_IRON = "machineblastediron";
	public static final String MODBLOCK_MACHINE_BLASTED_COAL = "machineblastedcoal";
	public static final String MODBLOCK_MACHINE_BLASTED_LAPIS = "machineblastedlapis";
	public static final String MODBLOCK_MACHINE_BLASTED_DIAMOND = "machineblasteddiamond";
	public static final String MODBLOCK_MACHINE_BLASTED_REDSTONE = "machineblastedredstone";
	public static final String MODBLOCK_MACHINE_BLASTED_EMERALD = "machineblastedemerald";

	public static final String MODBLOCK_MACHINE_CORN = "corn";
	public static final String MODBLOCK_MACHINE_BLASTED_STONE2 = "machineblastedstone2";
	public static final String MODBLOCK_MACHINE_PRIMARY_CRUSHER = "machineprimarycrusher";
	public static final String MODBLOCK_MACHINE_CENTRIFUGE = "machinecentrifuge";
	public static final String MODBLOCK_MACHINE_DISTILLER = "machinedistiller";
	public static final String MODBLOCK_MACHINE_WELL_HEAD = "machinewellhead";

	public static final String MODBLOCK_MACHINE_FACTORY = "machinefactory";

	public static final String MODBLOCK_MACHINE_FERMENTER = "machinefermenter";
	public static final String MODBLOCK_MACHINE_FUEL_PUMP = "machinefuelpump";
	public static final String MODBLOCK_MACHINE_TOWER_CRANE = "machinetowercrane";

	public static final String MODBLOCK_MACHINE_CONVEYOR = "machineconveyor";
	public static final String MODBLOCK_MACHINE_FEED_TROUGH = "machinefeedtrough";
	public static final String MODBLOCK_MACHINE_HOLO_SCANNER = "machineholoscanner";

	public static final String MODBLOCK_MACHINE_GENERATOR = "machinegenerator";

	public static final String MODBLOCK_MACHINE_BATTERY_BANK = "machinebatterybank";
	public static final String MODBLOCK_MACHINE_CONDUIT = "machineconduit";

	public static final String MODBLOCK_MACHINE_TURBO_FURNACE = "machineturbofurnace";

	public static final String MODBLOCK_MACHINE_BLEAK_PORTAL_FRAME = "machinebleakportalframe";
	public static final String MODBLOCK_MACHINE_BLEAK_PORTAL = "machinebleakportal";

	public static final String MODBLOCK_MACHINE_SCREEN = "machinescreen";
	public static final String MODBLOCK_MACHINE_SHREDDER = "machineshredder";

	public static final String MODBLOCK_MACHINE_FLUID_BIOFUEL = "biofuel";

	public static final String MODBLOCK_MACHINE_FLUID_OIL = "machineoil";

	public static final String MODBLOCK_MACHINE_EXPLOSIVE_PACKED_DRILLED_STONE = "machineexplosivepackeddrilledstone";
	public static final String MACHINE_MOD_NBT_PREFIX = "MACHINE_MOD_";

	public static final String CONFIG_SECTION_BLEAK = "bleak";
	// config file settings
	public static int clientRemoveInactiveEntityTimer = 100;
	public static int updateConnectionTimer = 100;
	public static boolean enableDebugPipeCode = false;

	public static boolean enableBulldozer = true;
	public static boolean enableTrackLoader = true;
	public static boolean enableBagger = true;

	public static boolean enableCombine = true;
	public static boolean enableCrane = true;
	public static boolean enableDrillingRig = true;
	public static boolean enableDumptruck = true;
	public static boolean enableUnderGroundDumptruck = true;

	public static boolean enableExcavator = true;
	public static boolean enableGrader = true;
	public static boolean enableLawnmower = true;
	public static boolean enableLoader = true;
	public static boolean enableUnderGroundLoader = true;

	public static boolean enableLaserMiner = true;

	public static boolean enableContinuousMiner = true;

	public static boolean enableOilRig = true;
	public static boolean enablePaver = true;
	public static boolean enablePumpJack = true;
	public static boolean enableRoadRoller = true;
	public static boolean enableSemiTractor = true;
	public static boolean enableSub = true;
	public static boolean enableChopper = true;

	public static boolean enableTractor = true;

	public static boolean enablePlayerSkullsInWorldGen = true;

	public static int crudeOilStoneGenMinlevel = 5;
	public static int crudeOilStoneGenMaxlevel = 30;

	public static int bleakoreiridoniumGenMinlevel = 41;
	public static int bleakoreiridoniumGenMaxlevel = 75;
	public static int bleakoreiridoniumGenDepositSize = 8;

	public static int bleakoremagentiaGenMinlevel = 30;
	public static int bleakoremagentiaGenMaxlevel = 69;
	public static int bleakoremagentiaGenDepositSize = 8;

	public static int bleakorelimoniteumGenMinlevel = 61;
	public static int bleakorelimoniteumGenMaxlevel = 94;
	public static int bleakorelimoniteumGenDepositSize = 8;

	public static int bleakorecrimsoniteGenMinlevel = 4;
	public static int bleakorecrimsoniteGenMaxlevel = 31;
	public static int bleakorecrimsoniteGenDepositSize = 8;

	public static int bleakoreazuriumGenMinlevel = 4;
	public static int bleakoreazuriumGenMaxlevel = 22;
	public static int bleakoreazuriumGenDepositSize = 8;

	public static int bleakorecitroniteGenMinlevel = 20;
	public static int bleakorecitroniteGenMaxlevel = 54;
	public static int bleakorecitroniteGenDepositSize = 8;

	public static int bleakoreunobtaniumGenMinlevel = 4;
	public static int bleakoreunobtaniumGenMaxlevel = 20;
	public static int bleakoreunobtaniumGenDepositSize = 8;

	public static int wellHeadMaxRange = 15;

	public static int BleakBiomeID = 57;
	public static int BleakDimID = 57;

	// GUI BUTTONS by Screen
	// ASSEMBLY TABLE
	public static final int GUI_ASSEMBLY_TABLE_BUTTON_ASSEMBLE = 1;
	public static final int GUI_ASSEMBLY_TABLE_BUTTON_NEXT_PAGE = 2;
	public static final int GUI_ASSEMBLY_TABLE_BUTTON_PREV_PAGE = 3;

	public static final int GUI_TOWER_CRANE_BUTTON_SETTINGS = 1;

	public static final int GUI_HOLO_SCANNER_BUTTON_FRONT_PLUS = 2;
	public static final int GUI_HOLO_SCANNER_BUTTON_FRONT_MINUS = 3;
	public static final int GUI_HOLO_SCANNER_BUTTON_RIGHT_PLUS = 4;
	public static final int GUI_HOLO_SCANNER_BUTTON_RIGHT_MINUS = 5;
	public static final int GUI_HOLO_SCANNER_BUTTON_UP_PLUS = 6;
	public static final int GUI_HOLO_SCANNER_BUTTON_UP_MINUS = 7;

	public static final int GUI_TOWER_CRANE_BUTTON_BP1 = 1;
	public static final int GUI_TOWER_CRANE_BUTTON_BP2 = 2;
	public static final int GUI_TOWER_CRANE_BUTTON_BP3 = 3;
	public static final int GUI_TOWER_CRANE_BUTTON_BP4 = 4;
	public static final int GUI_TOWER_CRANE_BUTTON_BP5 = 5;
	public static final int GUI_TOWER_CRANE_BUTTON_BP6 = 6;
	public static final int GUI_TOWER_CRANE_BUTTON_BP7 = 7;
	public static final int GUI_TOWER_CRANE_BUTTON_BP8 = 8;

	public static final int GUI_TOWER_CRANE_BUTTON_INVENTORY = 9;

	public static final int GUI_TOWER_CRANE_SCROLL_UP = 10;
	public static final int GUI_TOWER_CRANE_SCROLL_DOWN = 11;

	public static final int GUI_HOLO_SCANNER_BUTTON_SAVE = 1;

	public static final String BLUEPRINTLOCATION = "config/Machinemod/Blueprints/";
	public static final String CONFIG_MACHINEMOD_LOCATION = "config/Machinemod";

}
