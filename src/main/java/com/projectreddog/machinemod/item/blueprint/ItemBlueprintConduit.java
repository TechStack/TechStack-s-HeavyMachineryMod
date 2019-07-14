package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBlueprintConduit extends ItemBlueprint {
	public String registryName = "blueprintconduit";

	public ItemBlueprintConduit() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 100;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machineconduit.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(Items.GLOWSTONE_DUST.getRegistryName().toString(), 1));
		ingredents.add(new BlueprintIngredent(Items.REDSTONE.getRegistryName().toString(), 1));

		LogHelper.info(this);
	}
}
