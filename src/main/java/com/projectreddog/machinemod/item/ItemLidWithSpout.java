package com.projectreddog.machinemod.item;

public class ItemLidWithSpout extends ItemMachineMod {
	public String registryName = "lidwithspout";

	public ItemLidWithSpout() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
