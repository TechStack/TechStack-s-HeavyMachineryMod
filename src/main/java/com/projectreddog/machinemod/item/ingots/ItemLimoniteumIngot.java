package com.projectreddog.machinemod.item.ingots;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemLimoniteumIngot extends ItemMachineMod {
	public String registryName = "limoniteumingot";

	public ItemLimoniteumIngot() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
