package com.projectreddog.machinemod.item;

public class ItemRigging extends ItemMachineMod {
	public String registryName = "rigging";

	public ItemRigging() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
