package com.projectreddog.machinemod.item.trailer;

public class ItemSemiTrailerLivestock extends ItemSemiTrailer {
	public String registryName = "livestocktrailer";

	public ItemSemiTrailerLivestock() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
