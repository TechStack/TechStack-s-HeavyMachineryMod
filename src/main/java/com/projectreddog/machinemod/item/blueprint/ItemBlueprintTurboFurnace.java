package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBlueprintTurboFurnace extends ItemBlueprint {
	public String registryName = "blueprintturbofurnace";

	public ItemBlueprintTurboFurnace() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 500;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machineturbofurnace.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(ModItems.steelingot.getRegistryName().toString(), 8));
		ingredents.add(new BlueprintIngredent(ModItems.turbo.getRegistryName().toString(), 1));
		ingredents.add(new BlueprintIngredent(Blocks.FURNACE.getRegistryName().toString(), 1));

		LogHelper.info(this);
	}
}
