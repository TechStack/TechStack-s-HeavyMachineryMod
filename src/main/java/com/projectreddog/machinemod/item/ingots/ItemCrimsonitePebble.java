package com.projectreddog.machinemod.item.ingots;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemCrimsonitePebble extends ItemMachineMod {
	public String registryName = "crimsonitepebble";

	public ItemCrimsonitePebble() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
