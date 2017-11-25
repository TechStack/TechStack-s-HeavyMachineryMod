package com.projectreddog.machinemod.item.components;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemTurbo extends ItemMachineMod {
	public String registryName = "turbo";

	public ItemTurbo() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.maxStackSize = 64;

	}

}
