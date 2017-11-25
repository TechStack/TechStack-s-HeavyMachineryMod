package com.projectreddog.machinemod.item;

public class ItemBaggerStorage extends ItemMachineMod {
	public String registryName = "baggerstorage";

	public ItemBaggerStorage() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
