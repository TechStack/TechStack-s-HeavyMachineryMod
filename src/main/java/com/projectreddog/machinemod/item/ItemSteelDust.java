package com.projectreddog.machinemod.item;

public class ItemSteelDust extends ItemMachineMod {
	public String registryName = "steeldust";

	public ItemSteelDust() {
		super();

		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.maxStackSize = 64;

	}

}
