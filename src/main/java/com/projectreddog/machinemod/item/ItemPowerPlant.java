package com.projectreddog.machinemod.item;

public class ItemPowerPlant extends ItemMachineMod {
	public String registryName = "powerplant";

	public ItemPowerPlant() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
