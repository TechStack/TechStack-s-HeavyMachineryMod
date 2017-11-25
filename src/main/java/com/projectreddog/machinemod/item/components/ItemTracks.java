package com.projectreddog.machinemod.item.components;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemTracks extends ItemMachineMod {
	public String registryName = "tracks";

	public ItemTracks() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
