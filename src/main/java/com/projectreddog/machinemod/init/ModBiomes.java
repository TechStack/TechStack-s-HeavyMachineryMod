package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.world.biome.BiomeBleak;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes {

	public static final Biome bleak = new BiomeBleak(new Biome.BiomeProperties("Bleak").setRainDisabled());

	// TODO add to the configs the DIM ID For bleak Dim
	public static int bleakBiomeId = Reference.BleakBiomeID;

	public static void init() {

		// TODO add to the configs the DIM ID For bleak Dim
		// set init value for bleakDimID based on configs
		ForgeRegistries.BIOMES.register(bleak);

	}

}
