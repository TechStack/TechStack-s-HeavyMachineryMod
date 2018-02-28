package com.projectreddog.machinemod.handler;

import java.io.File;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static Configuration configuration;

	public static void init(File configFile) {

		// do everything related to loading the config etc..
		if (configuration == null) {
			configuration = new Configuration(configFile);

			loadConfiguration();
		}

		//
	}

	private static void loadConfiguration() {
		Reference.clientRemoveInactiveEntityTimer = configuration.get(Configuration.CATEGORY_GENERAL, "clientRemoveInactiveEntityTimer", 100, "Number of ticks that the client will wait without a message from the server before it will remove the client side entity (should help with desync) ").getInt();
		Reference.updateConnectionTimer = configuration.get(Configuration.CATEGORY_GENERAL, "updateConnectionTimer", 100, "Number of ticks before the Pipes will try to update connections to ensure they are still legit ").getInt();
		Reference.enableDebugPipeCode = configuration.get(Configuration.CATEGORY_GENERAL, "enableDebugPipeCode", Reference.enableDebugPipeCode, "If true Pipes will render color coded based on fulid in the pipes at the cost of addtional network packets, if false networking & color coded rendering is disabled").getBoolean();
		Reference.enableBagger = configuration.get(Configuration.CATEGORY_GENERAL, "enableBagger", true, "If true Bagger is Enabled, if false Bagger is disabled").getBoolean();
		Reference.enableBulldozer = configuration.get(Configuration.CATEGORY_GENERAL, "enableBulldozer", true, "If true Bulldozer is Enabled, if false BullDozer is disabled").getBoolean();
		Reference.enableTrackLoader = configuration.get(Configuration.CATEGORY_GENERAL, "enableTrackLoader", true, "If true Track Loader is Enabled, if false BullDozer is disabled").getBoolean();
		Reference.enableChopper = configuration.get(Configuration.CATEGORY_GENERAL, "enableChopper", true, "If true Chopper is Enabled, if false Chopper is disabled").getBoolean();
		Reference.enableCombine = configuration.get(Configuration.CATEGORY_GENERAL, "enableCombine", true, "If true Combine is Enabled, if false Combine is disabled").getBoolean();
		Reference.enableCrane = configuration.get(Configuration.CATEGORY_GENERAL, "enableCrane", true, "If true Crane is Enabled, if false Crane is disabled").getBoolean();
		Reference.enableDrillingRig = configuration.get(Configuration.CATEGORY_GENERAL, "enableDrillingRig", true, "If true DrillingRig is Enabled, if false DrillingRig is disabled").getBoolean();
		Reference.enableDumptruck = configuration.get(Configuration.CATEGORY_GENERAL, "enableDumptruck", true, "If true Dump truck is Enabled, if false Dump truck is disabled").getBoolean();
		Reference.enableUnderGroundDumptruck = configuration.get(Configuration.CATEGORY_GENERAL, "enableUnderGroundDumptruck", true, "If true Under Ground Dump truck is Enabled, if false Under Ground Dump Truck is disabled").getBoolean();
		Reference.enableExcavator = configuration.get(Configuration.CATEGORY_GENERAL, "enableExcavator", true, "If true Excavator is Enabled, if false Excavator is disabled").getBoolean();
		Reference.enableGrader = configuration.get(Configuration.CATEGORY_GENERAL, "enableGrader", true, "If true Grade is Enabled, if false Grade is disabled").getBoolean();
		Reference.enableLawnmower = configuration.get(Configuration.CATEGORY_GENERAL, "enableLawnmower", true, "If true Lawnmower is Enabled, if false Lawnmower is disabled").getBoolean();
		Reference.enableLoader = configuration.get(Configuration.CATEGORY_GENERAL, "enableLoader", true, "If true Loader is Enabled, if false Loader is disabled").getBoolean();
		Reference.enableOilRig = configuration.get(Configuration.CATEGORY_GENERAL, "enableOilRig", true, "If true OilRig is Enabled, if false OilRig is disabled").getBoolean();
		Reference.enablePaver = configuration.get(Configuration.CATEGORY_GENERAL, "enablePaver", true, "If true Paver is Enabled, if false Paver is disabled").getBoolean();
		Reference.enablePumpJack = configuration.get(Configuration.CATEGORY_GENERAL, "enablePumpJack", true, "If true PumpJack is Enabled, if false PumpJack is disabled").getBoolean();
		Reference.enableRoadRoller = configuration.get(Configuration.CATEGORY_GENERAL, "enableRoadRoller", true, "If true RoadRoller is Enabled, if false RoadRoller is disabled").getBoolean();
		Reference.enableSemiTractor = configuration.get(Configuration.CATEGORY_GENERAL, "enableSemiTractor", true, "If true SemiTractor is Enabled, if false SemiTractor is disabled").getBoolean();
		Reference.enableSub = configuration.get(Configuration.CATEGORY_GENERAL, "enableSub", true, "If true Sub is Enabled, if false Sub is disabled").getBoolean();
		Reference.enableChopper = configuration.get(Configuration.CATEGORY_GENERAL, "enableSub", true, "If true Sub is Enabled, if false Sub is disabled").getBoolean();
		Reference.enableTractor = configuration.get(Configuration.CATEGORY_GENERAL, "enableTractor", true, "If true Tractor is Enabled, if false Tractor is disabled").getBoolean();

		Reference.enablePlayerSkullsInWorldGen = configuration.get(Configuration.CATEGORY_GENERAL, "enablePlayerSkullsInWorldGen", Reference.enablePlayerSkullsInWorldGen, "If true player skulls above villager workshops is Enabled, if false player skulls above villager workshops will not spawn.").getBoolean();

		Reference.BleakBiomeID = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakbiomeid", 57, "set the BiomeId for the bleak biome").getInt();
		Reference.BleakDimID = configuration.get(Reference.CONFIG_SECTION_BLEAK, "Bleakdimid", 57, "set the Dim ID for the bleak dimension").getInt();

		Reference.bleakoreiridoniumGenMinlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreiridoniumGenMinlevel", Reference.bleakoreiridoniumGenMinlevel, "set min level for iridonium").getInt();
		Reference.bleakoreiridoniumGenMaxlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreiridoniumGenMaxlevel", Reference.bleakoreiridoniumGenMaxlevel, "set Max level for iridonium").getInt();
		Reference.bleakoreiridoniumGenDepositSize = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreiridoniumGenDepositSize", Reference.bleakoreiridoniumGenDepositSize, "set Deposit size for iridonium").getInt();

		Reference.bleakoremagentiaGenMinlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoremagentiaGenMinlevel", Reference.bleakoremagentiaGenMinlevel, "set min level for magentia").getInt();
		Reference.bleakoremagentiaGenMaxlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoremagentiaGenMaxlevel", Reference.bleakoremagentiaGenMaxlevel, "set Max level for magentia").getInt();
		Reference.bleakoremagentiaGenDepositSize = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoremagentiaGenDepositSize", Reference.bleakoremagentiaGenDepositSize, "set Deposit size for magentia").getInt();

		Reference.bleakorelimoniteumGenMinlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorelimoniteumGenMinlevel", Reference.bleakorelimoniteumGenMinlevel, "set min level for limoniteum").getInt();
		Reference.bleakorelimoniteumGenMaxlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorelimoniteumGenMaxlevel", Reference.bleakorelimoniteumGenMaxlevel, "set Max level for limoniteum").getInt();
		Reference.bleakorelimoniteumGenDepositSize = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorelimoniteumGenDepositSize", Reference.bleakorelimoniteumGenDepositSize, "set Deposit size for limoniteum").getInt();

		Reference.bleakorecrimsoniteGenMinlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorecrimsoniteGenMinlevel", Reference.bleakorecrimsoniteGenMinlevel, "set min level for crimsonite").getInt();
		Reference.bleakorecrimsoniteGenMaxlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorecrimsoniteGenMaxlevel", Reference.bleakorecrimsoniteGenMaxlevel, "set Max level for crimsonite").getInt();
		Reference.bleakorecrimsoniteGenDepositSize = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorecrimsoniteGenDepositSize", Reference.bleakorecrimsoniteGenDepositSize, "set Deposit size for crimsonite").getInt();
		;

		Reference.bleakoreazuriumGenMinlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreazuriumGenMinlevel", Reference.bleakoreazuriumGenMinlevel, "set min level for azurium").getInt();
		Reference.bleakoreazuriumGenMaxlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreazuriumGenMaxlevel", Reference.bleakoreazuriumGenMaxlevel, "set Max level for azurium").getInt();
		Reference.bleakoreazuriumGenDepositSize = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreazuriumGenDepositSize", Reference.bleakoreazuriumGenDepositSize, "set Deposit size for azurium").getInt();

		Reference.bleakorecitroniteGenMinlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorecitroniteGenMinlevel", Reference.bleakorecitroniteGenMinlevel, "set min level for citronite").getInt();
		Reference.bleakorecitroniteGenMaxlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorecitroniteGenMaxlevel", Reference.bleakorecitroniteGenMaxlevel, "set Max level for citronite").getInt();
		Reference.bleakorecitroniteGenDepositSize = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakorecitroniteGenDepositSize", Reference.bleakorecitroniteGenDepositSize, "set Deposit size for citronite").getInt();

		Reference.bleakoreunobtaniumGenMinlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreunobtaniumGenMinlevel", Reference.bleakoreunobtaniumGenMinlevel, "set min level for unobtanium").getInt();
		Reference.bleakoreunobtaniumGenMaxlevel = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreunobtaniumGenMaxlevel", Reference.bleakoreunobtaniumGenMaxlevel, "set Max level for unobtanium").getInt();
		Reference.bleakoreunobtaniumGenDepositSize = configuration.get(Reference.CONFIG_SECTION_BLEAK, "bleakoreunobtaniumGenDepositSize", Reference.bleakoreunobtaniumGenDepositSize, "set Deposit size for unobtanium").getInt();

		// save the config if it did not exits
		if (configuration.hasChanged()) {
			// only save it if it has been modified (may help keep from updating
			// the time stamp (last modified))
			configuration.save();
		}

	}

	@SubscribeEvent
	public void onConfiguratoinChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) {
			// resync configs
			loadConfiguration();
		}
	}
}
