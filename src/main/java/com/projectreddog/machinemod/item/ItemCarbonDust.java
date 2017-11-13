package com.projectreddog.machinemod.item;

public class ItemCarbonDust extends ItemMachineMod {
	public String registryName = "carbondust";

	public ItemCarbonDust() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
