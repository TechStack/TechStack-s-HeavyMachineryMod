package com.projectreddog.machinemod.item;

public class ItemMowerDeck extends ItemMachineMod {
	public String registryName = "mowerdeck";

	public ItemMowerDeck() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

}
