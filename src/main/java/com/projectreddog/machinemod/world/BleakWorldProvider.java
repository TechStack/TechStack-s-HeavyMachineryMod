package com.projectreddog.machinemod.world;

import com.projectreddog.machinemod.init.ModDimensions;
import com.projectreddog.machinemod.world.gen.BleakChunkGenerator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BleakWorldProvider extends WorldProvider {

	@Override
	public void init() {
		super.init();
		this.hasSkyLight = false;

	}

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

	/* ======================================= Start Moved From World ========================================= */

	public Biome getBiomeForCoords(BlockPos pos) {
		return world.getBiomeForCoordsBody(pos);
	}

	/**
	 * Creates the light to brightness table
	 */
	@Override
	protected void generateLightBrightnessTable() {

		float f = 0.0F;

		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			if (i < 1) {
				this.lightBrightnessTable[i] = .05f;
			} else if (i == 15f) {
				this.lightBrightnessTable[i] = i + .05f / this.lightBrightnessTable[i - 1];
			} else {

				this.lightBrightnessTable[i] = 1f;
			}
			// n
			// this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;

		}
	}

	/**
	 * Returns true if the given X,Z coordinate should show environmental fog.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
	 */
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {

		return .5f;
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
	 */
	public boolean isSurfaceWorld() {
		return false;
	}

}
