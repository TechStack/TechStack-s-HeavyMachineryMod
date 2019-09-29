package com.projectreddog.machinemod.item;

public class ItemStalkerHide extends ItemMachineMod {
	public String registryName = "stalkerhide";

	public ItemStalkerHide() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
