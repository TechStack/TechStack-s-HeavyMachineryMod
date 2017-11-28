package com.projectreddog.machinemod;

import com.projectreddog.machinemod.client.handler.InputEventHandler;
import com.projectreddog.machinemod.handler.ConfigurationHandler;
import com.projectreddog.machinemod.handler.events.EventHandler;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModEntities;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.init.ModWorldGen;
import com.projectreddog.machinemod.init.Recipes;
import com.projectreddog.machinemod.proxy.IProxy;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

//adding comment to test build on jenkins new server test6
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, guiFactory = Reference.GUI_FACTORY_CLASS, updateJSON = "https://raw.githubusercontent.com/TechStack/TechStack-s-HeavyMachineryMod/master/update.json")
public class MachineMod {

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.Instance(Reference.MOD_ID)
	public static MachineMod instance; // an instance back to this mod

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// net handling mod config init items & blocks

		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModBlocks.init();

		ModItems.init();
		ModNetwork.init();
		proxy.PreInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		// register gui, tile entites , crafting recipies (general event
		// hanlders)
		ModEntities.init(this);
		FMLCommonHandler.instance().bus().register(new InputEventHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		Recipes.init();
		ModWorldGen.init();

		proxy.registerRenderers();
		proxy.RegisterKeybinds();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// wrap things up .. runs after other mods do there init steps

		LogHelper.debug("Is Bulldozer Enabled: " + Reference.enableBulldozer);
	}

	// testing

}
