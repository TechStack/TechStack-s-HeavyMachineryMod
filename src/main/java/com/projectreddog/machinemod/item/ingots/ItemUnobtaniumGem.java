package com.projectreddog.machinemod.item.ingots;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemUnobtaniumGem extends ItemMachineMod {
	public String registryName = "unobtaniumgem";

	public ItemUnobtaniumGem() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
