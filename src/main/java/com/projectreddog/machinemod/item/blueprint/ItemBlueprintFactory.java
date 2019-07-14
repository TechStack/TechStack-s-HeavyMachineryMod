package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBlueprintFactory extends ItemBlueprint {
	public String registryName = "blueprintfactory";

	public ItemBlueprintFactory() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 1000;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machinefactory.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(ModItems.steelingot.getRegistryName().toString(), 20));
		ingredents.add(new BlueprintIngredent(Items.BLAZE_ROD.getRegistryName().toString(), 8));
		ingredents.add(new BlueprintIngredent(Items.REDSTONE.getRegistryName().toString(), 16));
		ingredents.add(new BlueprintIngredent(Items.QUARTZ.getRegistryName().toString(), 16));
		ingredents.add(new BlueprintIngredent(ModBlocks.machineconduit.getRegistryName().toString(), 5));

		LogHelper.info(this);
	}
}
