package com.projectreddog.machinemod.item.components;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemPaverScreed extends ItemMachineMod {
	public String registryName = "paverscreed";

	public ItemPaverScreed() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
