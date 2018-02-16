package com.projectreddog.machinemod.world;

import java.util.Random;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MachineModWorldGenBleak implements IWorldGenerator {

	private WorldGenerator gen_machineMod_bleakoreiridonium;
	private WorldGenerator gen_machineMod_bleakoremagentia;
	private WorldGenerator gen_machineMod_bleakorelimoniteum;
	private WorldGenerator gen_machineMod_bleakorecrimsonite;
	private WorldGenerator gen_machineMod_bleakoreazurium;
	private WorldGenerator gen_machineMod_bleakorecitronite;
	private WorldGenerator gen_machineMod_bleakoreunobtanium;

	public MachineModWorldGenBleak() {
		this.gen_machineMod_bleakoreiridonium = new WorldGenMinable(ModBlocks.machinebleakoreiridonium.getDefaultState(), Reference.bleakoreiridoniumGenDepositSize);
		this.gen_machineMod_bleakoremagentia = new WorldGenMinable(ModBlocks.machinebleakoremagentia.getDefaultState(), Reference.bleakoremagentiaGenDepositSize);
		this.gen_machineMod_bleakorelimoniteum = new WorldGenMinable(ModBlocks.machinebleakorelimoniteum.getDefaultState(), Reference.bleakorelimoniteumGenDepositSize);
		this.gen_machineMod_bleakorecrimsonite = new WorldGenMinable(ModBlocks.machinebleakorecrimsonite.getDefaultState(), Reference.bleakorecrimsoniteGenDepositSize);
		this.gen_machineMod_bleakoreazurium = new WorldGenMinable(ModBlocks.machinebleakoreazurium.getDefaultState(), Reference.bleakoreazuriumGenDepositSize);
		this.gen_machineMod_bleakorecitronite = new WorldGenMinable(ModBlocks.machinebleakorecitronite.getDefaultState(), Reference.bleakorecitroniteGenDepositSize);
		this.gen_machineMod_bleakoreunobtanium = new WorldGenMinable(ModBlocks.machinebleakoreunobtanium.getDefaultState(), Reference.bleakoreunobtaniumGenDepositSize);

	}

	@Override
	// public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == Reference.BleakDimID) {// was get dim id

			this.runGenerator(this.gen_machineMod_bleakoreiridonium, world, random, chunkX, chunkZ, 5, Reference.bleakoreiridoniumGenMinlevel, Reference.bleakoreiridoniumGenMaxlevel);
			this.runGenerator(this.gen_machineMod_bleakoremagentia, world, random, chunkX, chunkZ, 5, Reference.bleakoremagentiaGenMinlevel, Reference.bleakoremagentiaGenMaxlevel);
			this.runGenerator(this.gen_machineMod_bleakorelimoniteum, world, random, chunkX, chunkZ, 5, Reference.bleakorelimoniteumGenMinlevel, Reference.bleakorelimoniteumGenMaxlevel);
			this.runGenerator(this.gen_machineMod_bleakorecrimsonite, world, random, chunkX, chunkZ, 5, Reference.bleakorecrimsoniteGenMinlevel, Reference.bleakorecrimsoniteGenMaxlevel);
			this.runGenerator(this.gen_machineMod_bleakoreazurium, world, random, chunkX, chunkZ, 5, Reference.bleakoreazuriumGenMinlevel, Reference.bleakoreazuriumGenMaxlevel);
			this.runGenerator(this.gen_machineMod_bleakorecitronite, world, random, chunkX, chunkZ, 5, Reference.bleakorecitroniteGenMinlevel, Reference.bleakorecitroniteGenMaxlevel);
			this.runGenerator(this.gen_machineMod_bleakoreunobtanium, world, random, chunkX, chunkZ, 2, Reference.bleakoreunobtaniumGenMinlevel, Reference.bleakoreunobtaniumGenMaxlevel);

		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			// if (BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(new BlockPos(x, y, z)), BiomeDictionary.Type.OCEAN)) {
			generator.generate(world, rand, new BlockPos(x, y, z));
			// }
		}
	}

}
