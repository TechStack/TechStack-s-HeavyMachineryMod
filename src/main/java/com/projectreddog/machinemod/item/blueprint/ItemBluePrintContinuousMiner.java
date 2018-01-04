package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.init.Items;

public class ItemBlueprintContinuousMiner extends ItemBlueprint {
	public String registryName = "blueprintcontinuousminer";

	public ItemBlueprintContinuousMiner() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 100000;

	}

	public void Init() {
		this.outputItemName = ModItems.continuousminer.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(Items.IRON_INGOT.getRegistryName().toString(), 100));
		ingredents.add(new BlueprintIngredent(Items.IRON_INGOT.getRegistryName().toString(), 100));

		LogHelper.info(this);
	}
}
