package com.projectreddog.machinemod.item;

public class ItemBucketWheel extends ItemMachineMod {
	public String registryName = "bucketwheel";

	public ItemBucketWheel() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
