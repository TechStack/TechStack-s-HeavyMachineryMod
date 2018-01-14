package com.projectreddog.machinemod.item;

public class ItemAfterBurner extends ItemMachineMod {
	public String registryName = "afterburner";

	public ItemAfterBurner() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
