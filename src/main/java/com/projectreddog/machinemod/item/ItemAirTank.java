package com.projectreddog.machinemod.item;

public class ItemAirTank extends ItemMachineMod {
	public String registryName = "airtank";

	public ItemAirTank() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
