package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.init.Items;

public class ItemBlueprintContinuousMiner extends ItemBlueprint {
	public String registryName = "blueprintcontinuousminer";

	public ItemBlueprintContinuousMiner() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.maxStackSize = 64;

	}

	public void Init() {
		this.outputItemName = "";

		ingredents.add(new BlueprintIngredent(Items.IRON_INGOT.getRegistryName().toString(), 100));
		ingredents.add(new BlueprintIngredent(Items.IRON_INGOT.getRegistryName().toString(), 100));

		LogHelper.info(ingredents.toString());
	}
}
