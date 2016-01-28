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
		Reference.enableChopper = configuration.get(Configuration.CATEGORY_GENERAL, "enableChopper", true, "If true Chopper is Enabled, if false Chopper is disabled").getBoolean();
		Reference.enableCombine = configuration.get(Configuration.CATEGORY_GENERAL, "enableCombine", true, "If true Combine is Enabled, if false Combine is disabled").getBoolean();
		Reference.enableCrane = configuration.get(Configuration.CATEGORY_GENERAL, "enableCrane", true, "If true Crane is Enabled, if false Crane is disabled").getBoolean();
		Reference.enableDrillingRig = configuration.get(Configuration.CATEGORY_GENERAL, "enableDrillingRig", true, "If true DrillingRig is Enabled, if false DrillingRig is disabled").getBoolean();
		Reference.enableDumptruck = configuration.get(Configuration.CATEGORY_GENERAL, "enableDumptruck", true, "If true Dumptruck is Enabled, if false Dumptruck is disabled").getBoolean();
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

		// save the config if it did not exits
		if (configuration.hasChanged()) {
			// only save it if it has been modified (may help keep from updating
			// the time stamp (last modified))
			configuration.save();
		}

	}

	@SubscribeEvent
	public void onConfiguratoinChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			// resync configs
			loadConfiguration();
		}
	}
}
