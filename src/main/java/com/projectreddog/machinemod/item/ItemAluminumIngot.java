package com.projectreddog.machinemod.item;

public class ItemAluminumIngot extends ItemMachineMod {
	public String registryName = "aluminumingot";

	public ItemAluminumIngot() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
