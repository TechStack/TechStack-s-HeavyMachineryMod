package com.projectreddog.machinemod;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.projectreddog.machinemod.client.handler.KeyInputEventHandler;
import com.projectreddog.machinemod.handler.ConfigurationHandler;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModEntities;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.proxy.IProxy;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

@Mod (modid= Reference.MOD_ID, name= Reference.MOD_NAME,version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class MachineMod {

	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS,serverSide=Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.Instance( Reference.MOD_ID)
	public static MachineMod instance; // an instance back to this mod
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
	//net handling mod config init items & blocks	
		
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModItems.init();
		ModBlocks.init();
		
	}
	@Mod.EventHandler 
	public void init(FMLInitializationEvent event){
		// register gui, tile entites , crafting recipies (general event hanlders)
		ModEntities.init(this);
		ModNetwork.init();
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
		proxy.registerRenderers();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		// wrap things up .. runs after other mods do there init steps
		
		LogHelper.debug("Is Bulldozer Enabled: " + Reference.enableBulldozer);
	}
	
	
	
	//testing
	
}
