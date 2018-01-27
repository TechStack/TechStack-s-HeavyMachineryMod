package com.projectreddog.machinemod.item.ingots;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemCitroniteIngot extends ItemMachineMod {
	public String registryName = "citroniteingot";

	public ItemCitroniteIngot() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
