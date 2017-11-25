package com.projectreddog.machinemod.item;

public class ItemIronDust extends ItemMachineMod {
	public String registryName = "irondust";

	public ItemIronDust() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
