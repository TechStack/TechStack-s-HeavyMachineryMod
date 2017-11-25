package com.projectreddog.machinemod.item;

public class ItemHose extends ItemMachineMod {
	public String registryName = "hose";

	public ItemHose() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
