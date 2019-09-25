package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBlueprintHoloScanner extends ItemBlueprint {
	public String registryName = "blueprintholoscanner";

	public ItemBlueprintHoloScanner() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 20000;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machineholoscanner.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(ModItems.azuriumlump.getRegistryName().toString(), 24));
		ingredents.add(new BlueprintIngredent(ModBlocks.machinebleakglass.getRegistryName().toString(), 16));
		ingredents.add(new BlueprintIngredent(ModItems.unobtaniumgem.getRegistryName().toString(), 1));
		LogHelper.info(this);
	}
}
