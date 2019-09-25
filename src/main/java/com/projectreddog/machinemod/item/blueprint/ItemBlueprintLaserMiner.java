package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBlueprintLaserMiner extends ItemBlueprint {
	public String registryName = "blueprintlaserminer";

	public ItemBlueprintLaserMiner() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 100000;

	}

	public void Init() {
		this.outputItemName = ModItems.laserminer.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(ModItems.steelingot.getRegistryName().toString(), 128));
		ingredents.add(new BlueprintIngredent(ModItems.unobtaniumgem.getRegistryName().toString(), 10));
		ingredents.add(new BlueprintIngredent(ModItems.limoniteumingot.getRegistryName().toString(), 5));
		ingredents.add(new BlueprintIngredent(ModItems.azuriumlump.getRegistryName().toString(), 24));
		ingredents.add(new BlueprintIngredent(ModItems.citroniteingot.getRegistryName().toString(), 4));
		ingredents.add(new BlueprintIngredent(ModItems.crimsonitepebble.getRegistryName().toString(), 2));
		ingredents.add(new BlueprintIngredent(ModItems.magentiaingot.getRegistryName().toString(), 2));

		LogHelper.info(this);
	}
}
