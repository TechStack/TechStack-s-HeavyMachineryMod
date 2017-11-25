package com.projectreddog.machinemod.item;

public class ItemDrillPipe extends ItemMachineMod {
	public String registryName = "drillpipe";

	public ItemDrillPipe() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 16;

	}

}
