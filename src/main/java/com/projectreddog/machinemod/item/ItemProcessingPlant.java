package com.projectreddog.machinemod.item;

public class ItemProcessingPlant extends ItemMachineMod {
	public String registryName = "processingplant";

	public ItemProcessingPlant() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
