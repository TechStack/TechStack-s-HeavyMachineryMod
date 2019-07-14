package com.projectreddog.machinemod.world.gen;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.projectreddog.machinemod.entity.monster.EntityExpStalker;
import com.projectreddog.machinemod.init.ModBiomes;
import com.projectreddog.machinemod.world.BleakTerrainGenerator;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BleakChunkGenerator implements IChunkGenerator {

	private final World worldObj;
	private Random random;
	private Biome[] biomesForGeneration;

	private List<Biome.SpawnListEntry> mobs = Lists.newArrayList(new Biome.SpawnListEntry(EntityExpStalker.class, 100, 2, 2));

	private MapGenBase caveGenerator = new BleakMapGenCaves();
	private BleakTerrainGenerator terraingen = new BleakTerrainGenerator();

	public BleakChunkGenerator(World worldObj) {
		this.worldObj = worldObj;
		long seed = worldObj.getSeed();
		this.random = new Random(((seed + 356) * 317));
		terraingen.setup(worldObj, random);
		caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, EventType.CAVE);

	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();

		// setup biomes for terraingen
		this.biomesForGeneration = new Biome[256];
		// sets the biomes for each x * Z cord
		for (int i = 0; i < 256; i++) {

			this.biomesForGeneration[i] = ModBiomes.bleak;
		}
		terraingen.setBiomesForGeneration(biomesForGeneration);
		terraingen.generate(x, z, chunkprimer);

		// setup biomes again for actual biome decoration
		// this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		// this will replace stone for the biome specific stones
		terraingen.replaceBiomeBlocks(z, z, chunkprimer, this, biomesForGeneration);

		// generate caves
		this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);

		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);

		byte[] biomeArray = chunk.getBiomeArray();
		for (int i = 0; i < biomeArray.length; ++i) {
			biomeArray[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}

		chunk.generateSkylightMap();
		return chunk;

	}

	@Override
	public void populate(int x, int z) {

		int i = x * 16;
		int j = z * 16;

		BlockPos blockpos = new BlockPos(i, 0, j);
		Biome biome = this.worldObj.getBiome(blockpos.add(16, 0, 16));

		// add biome decorations (flowers, grass trees etc....)
		biome.decorate(this.worldObj, this.random, blockpos);

		// make sure animals apporpiate biome spawn here when the chunk is gen...

		WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome, i + 8, j + 8, 16, 16, this.random);
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		// If you want normal creatures appropriate for this biome then uncomment the
		// following two lines:
		// Biome biome = this.worldObj.getBiome(pos);
		// return biome.getSpawnableList(creatureType);

		if (creatureType == EnumCreatureType.MONSTER) {
			return mobs;
		}
		return ImmutableList.of();

	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {

	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		return false;
	}

}
