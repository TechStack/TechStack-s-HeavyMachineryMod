package com.projectreddog.machinemod.item.components;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemLoaderBucket extends ItemMachineMod {
	public String registryName = "loaderbucket";

	public ItemLoaderBucket() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
