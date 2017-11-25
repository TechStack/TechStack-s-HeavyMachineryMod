package com.projectreddog.machinemod.item;

public class ItemDrillHead extends ItemMachineMod {
	public String registryName = "drillhead";

	public ItemDrillHead() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

}
