package com.projectreddog.machinemod.item;

public class ItemLightModule extends ItemMachineMod {
	public String registryName = "lightmodule";

	public ItemLightModule() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
