package com.projectreddog.machinemod.item;

public class ItemInfusedCrystal extends ItemMachineMod {
	public String registryName = "infusedcrystal";

	public ItemInfusedCrystal() {

		// heal sat wolf fav
		super();
		// this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" + "cornseed");
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;
	}

}
