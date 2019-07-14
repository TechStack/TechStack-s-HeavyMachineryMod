package com.projectreddog.machinemod.client.gui;

import com.projectreddog.machinemod.handler.ConfigurationHandler;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen guiScreen) {

		super(guiScreen, new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, // world
																																										// restart
				false, // MC restart
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));

	}
}
