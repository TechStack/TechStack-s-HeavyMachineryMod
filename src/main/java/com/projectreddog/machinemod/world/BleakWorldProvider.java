package com.projectreddog.machinemod.world;

import com.projectreddog.machinemod.init.ModDimensions;
import com.projectreddog.machinemod.world.gen.BleakChunkGenerator;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class BleakWorldProvider extends WorldProvider {

	@Override
	public DimensionType getDimensionType() {

		return ModDimensions.bleakDimensionType;
	}

	@Override
	public String getSaveFolder() {
		return "BLEAK";
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new BleakChunkGenerator(world);
	}

}
