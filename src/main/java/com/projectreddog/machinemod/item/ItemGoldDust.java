package com.projectreddog.machinemod.item;

public class ItemGoldDust extends ItemMachineMod {
	public String registryName = "golddust";

	public ItemGoldDust() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
