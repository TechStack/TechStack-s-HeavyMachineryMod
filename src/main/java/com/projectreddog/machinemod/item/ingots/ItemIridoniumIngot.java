package com.projectreddog.machinemod.item.ingots;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemIridoniumIngot extends ItemMachineMod {
	public String registryName = "iridoniumingot";

	public ItemIridoniumIngot() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
