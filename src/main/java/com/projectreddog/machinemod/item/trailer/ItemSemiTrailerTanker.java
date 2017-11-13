package com.projectreddog.machinemod.item.trailer;

public class ItemSemiTrailerTanker extends ItemSemiTrailer {
	public String registryName = "tankertrailer";

	public ItemSemiTrailerTanker() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
