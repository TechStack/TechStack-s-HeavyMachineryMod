package com.projectreddog.machinemod.item;

public class ItemRawAsphalt extends ItemMachineMod {
	public String registryName = "rawasphalt";

	public ItemRawAsphalt() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
