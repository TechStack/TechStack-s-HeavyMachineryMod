package com.projectreddog.machinemod.item;

public class ItemBaggerBody extends ItemMachineMod {
	public String registryName = "baggerbody";

	public ItemBaggerBody() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
