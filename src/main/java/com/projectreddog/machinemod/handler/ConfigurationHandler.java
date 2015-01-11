package com.projectreddog.machinemod.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.projectreddog.machinemod.reference.Reference;

public class ConfigurationHandler {
	
    public static Configuration configuration;
	public static void init(File configFile)
	{

		// do everything related to loading the config etc..
		if (configuration == null ){ 
			configuration = new Configuration(configFile);

			loadConfiguration();
		}
		
		//
	}
	
	
	private static void loadConfiguration(){
		
			 Reference.enableBulldozer = configuration.get(Configuration.CATEGORY_GENERAL,"enableBulldozer" , true,"If true Bulldozer Enabled, if false BullDozer is disabled").getBoolean();
		
			//save the config if it did not exits
			if (configuration.hasChanged()){
				//only save it if it has been modified (may help keep from updating the time stamp (last modified))
				configuration.save();
			}
	
	}
	
	@SubscribeEvent
	public void onConfiguratoinChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			//resync configs
			loadConfiguration();
		}
	}
}
