package com.projectreddog.machinemod.item.trailer;

public class ItemSemiTrailerCargo extends ItemSemiTrailer {
	public String registryName = "cargotrailer";

	public ItemSemiTrailerCargo() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
