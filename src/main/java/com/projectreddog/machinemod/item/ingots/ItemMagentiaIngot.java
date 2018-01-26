package com.projectreddog.machinemod.item.ingots;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemMagentiaIngot extends ItemMachineMod {
	public String registryName = "magentiaingot";

	public ItemMagentiaIngot() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
