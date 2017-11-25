package com.projectreddog.machinemod.item;

public class ItemLight extends ItemMachineMod {
	public String registryName = "light";

	public ItemLight() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
