package com.projectreddog.machinemod.item.ingots;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemAzuriumLump extends ItemMachineMod {
	public String registryName = "azuriumlump";

	public ItemAzuriumLump() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
