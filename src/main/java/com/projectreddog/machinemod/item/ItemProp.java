package com.projectreddog.machinemod.item;

public class ItemProp extends ItemMachineMod {
	public String registryName = "prop";

	public ItemProp() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
