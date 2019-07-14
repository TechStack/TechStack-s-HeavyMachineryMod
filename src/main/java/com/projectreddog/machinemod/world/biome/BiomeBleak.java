package com.projectreddog.machinemod.world.biome;

import java.util.Random;

import com.projectreddog.machinemod.block.BlockMachineBleakCrystal;
import com.projectreddog.machinemod.entity.monster.EntityExpStalker;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeBleak extends Biome {

	public BiomeBleak(BiomeProperties properties) {
		super(properties);

		// TODO Auto-generated constructor stub
		this.topBlock = ModBlocks.machinebleakdirt.getDefaultState();
		this.fillerBlock = ModBlocks.machinebleakdirt.getDefaultState();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.modSpawnableLists.clear();
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityExpStalker.class, 10, 2, 4));
		this.setRegistryName("machinemodbleak");
	}

	/**
	 * takes temperature, returns color
	 */
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float currentTemperature) {
		return MathHelper.rgb(1f, 1f, 1f);
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateBiomeTerrain2(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	/**
	 * Given x, z coordinates, we count down all the y positions starting at 255 and working our way down. When we hit a non-air block, we replace it with this.topBlock (default grass, descendants may set otherwise), and then a relatively shallow layer of blocks of type this.fillerBlock (default dirt). A random set of blocks below y == 5 (but always including y == 0) is replaced with bedrock.
	 * 
	 * If we don't hit non-air until somewhat below sea level, we top with gravel and fill down with stone.
	 * 
	 * If this.fillerBlock is red sand, we replace some of that with red sandstone.
	 */
	public final void generateBiomeTerrain2(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		BlockState BlockState = this.topBlock;
		BlockState BlockState1 = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				BlockState BlockState2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (BlockState2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (BlockState2.getBlock() == ModBlocks.machinebleakstone) {
					if (j == -1) {
						if (k <= 0) {
							BlockState = AIR;
							BlockState1 = STONE;
						} else if (j1 >= i - 4 && j1 <= i + 1) {
							BlockState = this.topBlock;
							BlockState1 = this.fillerBlock;
						}

						j = k;

						chunkPrimerIn.setBlockState(i1, j1, l, BlockState1);

					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, BlockState1);

					}
				}
			}
		}
	}

	public void decorate(World worldIn, Random rand, BlockPos pos) {

		int chance = rand.nextInt(100);
		if (chance > 95) {
			for (int i = 0; i < 1; ++i) {
				int x = rand.nextInt(16) + 8;
				int z = rand.nextInt(16) + 8;
				int y = worldIn.getHeight(pos.getX() + x, pos.getZ() + z);
				worldIn.setBlockState(pos.add(x, y, z), ModBlocks.machinebleakcrystal.getDefaultState().withProperty(BlockMachineBleakCrystal.AGE, rand.nextInt(6)));
			}
		}
		super.decorate(worldIn, rand, pos);
	}

}
