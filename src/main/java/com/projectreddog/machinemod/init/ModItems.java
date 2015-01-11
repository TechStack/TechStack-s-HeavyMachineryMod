package com.projectreddog.machinemod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.projectreddog.machinemod.item.ItemBulldozer;
import com.projectreddog.machinemod.item.ItemDrillHead;
import com.projectreddog.machinemod.item.ItemDrillPipe;
import com.projectreddog.machinemod.item.ItemDrillingRig;
import com.projectreddog.machinemod.item.ItemDumpTruck;
import com.projectreddog.machinemod.item.ItemLoader;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

	public static final ItemMachineMod bulldozer = new ItemBulldozer();

	public static final ItemMachineMod drillingrig = new ItemDrillingRig();
	public static final ItemMachineMod dumptruck= new ItemDumpTruck();
	public static final ItemMachineMod loader= new ItemLoader();
	public static final ItemMachineMod drillhead = new ItemDrillHead();
	public static final ItemMachineMod drillpipe = new ItemDrillPipe();

	public static void init()
	{
		GameRegistry.registerItem(bulldozer,"bulldozer");
		GameRegistry.registerItem(drillingrig,"drillingrig");
		GameRegistry.registerItem(dumptruck,"dumptruck");
		GameRegistry.registerItem(loader,"loader");
		GameRegistry.registerItem(drillhead,"drillhead");
		GameRegistry.registerItem(drillpipe,"drillpipe");


	}
	
	public static void initItemRender() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bulldozer, 0, new ModelResourceLocation (Reference.MOD_ID + ":"+ "bulldozer", "inventory")   );
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillingrig , 0, new ModelResourceLocation (Reference.MOD_ID + ":"+ "drillingrig", "inventory")   );
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(dumptruck, 0, new ModelResourceLocation (Reference.MOD_ID + ":"+ "dumptruck", "inventory")   );
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(loader, 0, new ModelResourceLocation (Reference.MOD_ID + ":"+ "loader", "inventory")   );
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillhead, 0, new ModelResourceLocation(Reference.MOD_ID + ":"+ "drillhead","inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(drillpipe, 0, new ModelResourceLocation(Reference.MOD_ID + ":"+ "drillpipe","inventory"));

	}
}
