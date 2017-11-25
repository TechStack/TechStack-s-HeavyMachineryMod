package com.projectreddog.machinemod.item.trailer;

public class ItemSemiTrailerFlatBed extends ItemSemiTrailer {
	public String registryName = "flatbedtrailer";

	public ItemSemiTrailerFlatBed() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
