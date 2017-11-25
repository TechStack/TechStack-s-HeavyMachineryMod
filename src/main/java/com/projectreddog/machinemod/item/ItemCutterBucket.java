package com.projectreddog.machinemod.item;

public class ItemCutterBucket extends ItemMachineMod {
	public String registryName = "cutterbucket";

	public ItemCutterBucket() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
