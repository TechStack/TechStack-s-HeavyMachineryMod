package com.projectreddog.machinemod.item;

public class ItemPropCage extends ItemMachineMod {
	public String registryName = "propcage";

	public ItemPropCage() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
