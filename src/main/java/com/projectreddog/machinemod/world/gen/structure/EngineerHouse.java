package com.projectreddog.machinemod.world.gen.structure;

import java.util.List;
import java.util.Random;

import com.mojang.authlib.GameProfile;
import com.projectreddog.machinemod.block.BlockMachineModFactory;
import com.projectreddog.machinemod.block.BlockMachineModFuelPump;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModVillage;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.VillagePieces.Village;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class EngineerHouse extends Village {
	int villagersSpawned = 0;

	String[] TSCraftMembers = new String[] { "TechStack", "diegocob", "Chazmanm", "robbversion1", "MusicDiskMaster", "Alchao", "Czarified", "chanmaster99", "Griffen8280", "KingCam26", "SmashShock", "Dorff333", "scoote205", "drcobra", "FrozenDesign", "Doomthrak", "Pixule", "trayer3", "zenstic0", "samzataru", "XxDJ_DINOxX", "binaryactions", "tater_canon", "mwigby", "iJord4nn", "FallDownGuy", "geekpeek", "mcfly64321", "DePowah", "help_12_21_2012", "MrMouselab", "nickgodin10", "Coolbum67", "King_Me56", "malcomful", "Owenrocks11", "supak1154", "Me_Is_Jake27", "frostydeath108", "spykid8", "Lazsa", "shadowmage4513", "Aragorn006", "Golden_Tree_Ink", "fierykilljoy", "Airbrat", "frost11111", "Gazer29", "Lunesta210x2", "matthewl6970", "kreezxil", "ProRed" };

	public EngineerHouse(StructureVillagePieces.Start start, int type, StructureBoundingBox bounds, Direction facing) {
		super(start, type);
		this.setCoordBaseMode(facing);
		this.boundingBox = bounds;
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		if (this.averageGroundLvl < 0) {
			this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

			if (this.averageGroundLvl < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
		}

		BlockState cobble = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
		BlockState planks = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
		BlockState log = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
		BlockState stairs = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState());

		BlockState air = Blocks.AIR.getDefaultState();
		// BlockState BlockState3 = this.getBiomeSpecificBlockState(ModBlocks.steelblock.getDefaultState());
		// BlockState BlockState4 = this.getBiomeSpecificBlockState(ModBlocks.steelblock.getDefaultState());
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 1, 13, 2, 11, cobble, cobble, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 2, 13, 6, 11, planks, planks, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 3, 3, 12, 6, 10, air, air, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 3, 2, 13, 6, 11, planks, planks, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 3, 3, 12, 6, 10, air, air, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 2, 1, 6, 2, log, log, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 3, 11, 13, 6, 1, log, log, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 11, 1, 6, 11, log, log, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 3, 2, 13, 6, 2, log, log, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 3, 2, 9, 6, 2, log, log, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 3, 11, 9, 6, 11, log, log, false);

		// big grage door
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 3, 2, 7, 5, 10, air, air, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 2, 6, 6, 10, air, air, false);
		// mod blocks

		this.setBlockState(worldIn, ModBlocks.machinefuelpump.getDefaultState().withProperty(BlockMachineModFuelPump.FACING, Direction.EAST), 2, 3, 3, structureBoundingBoxIn);
		this.setBlockState(worldIn, ModBlocks.machineassemblytable.getDefaultState(), 5, 3, 6, structureBoundingBoxIn);
		this.setBlockState(worldIn, ModBlocks.machinefactory.getDefaultState().withProperty(BlockMachineModFactory.FACING, Direction.SOUTH), 5, 3, 9, structureBoundingBoxIn);

		this.setBlockState(worldIn, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.TOP), 12, 3, 9, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, EnumBlockHalf.TOP), 11, 3, 9, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.OAK_FENCE_GATE.getDefaultState(), 10, 3, 9, structureBoundingBoxIn);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 7, 2, 2, 7, 11, stairs.withProperty(StairsBlock.FACING, Direction.EAST), stairs.withProperty(StairsBlock.FACING, Direction.EAST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 7, 2, 8, 7, 11, stairs.withProperty(StairsBlock.FACING, Direction.WEST), stairs.withProperty(StairsBlock.FACING, Direction.WEST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 8, 2, 3, 8, 11, stairs.withProperty(StairsBlock.FACING, Direction.EAST), stairs.withProperty(StairsBlock.FACING, Direction.EAST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 8, 2, 7, 8, 11, stairs.withProperty(StairsBlock.FACING, Direction.WEST), stairs.withProperty(StairsBlock.FACING, Direction.WEST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 9, 2, 4, 9, 11, stairs.withProperty(StairsBlock.FACING, Direction.EAST), stairs.withProperty(StairsBlock.FACING, Direction.EAST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 9, 2, 6, 9, 11, stairs.withProperty(StairsBlock.FACING, Direction.WEST), stairs.withProperty(StairsBlock.FACING, Direction.WEST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 7, 2, 7, 7, 2, planks, planks, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 8, 2, 6, 8, 2, planks, planks, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 7, 11, 7, 7, 11, planks, planks, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 8, 11, 6, 8, 11, planks, planks, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 9, 2, 5, 9, 11, planks, planks, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 9, 2, 6, 9, 2, stairs.withProperty(StairsBlock.FACING, Direction.NORTH), stairs.withProperty(StairsBlock.FACING, Direction.NORTH), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 9, 11, 6, 9, 11, stairs.withProperty(StairsBlock.FACING, Direction.SOUTH), stairs.withProperty(StairsBlock.FACING, Direction.SOUTH), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 7, 3, 10, 7, 10, stairs.withProperty(StairsBlock.FACING, Direction.EAST), stairs.withProperty(StairsBlock.FACING, Direction.EAST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 7, 3, 12, 7, 10, stairs.withProperty(StairsBlock.FACING, Direction.WEST), stairs.withProperty(StairsBlock.FACING, Direction.WEST), false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 7, 3, 11, 7, 10, planks, planks, false);

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 7, 3, 11, 7, 3, stairs.withProperty(StairsBlock.FACING, Direction.NORTH), stairs.withProperty(StairsBlock.FACING, Direction.NORTH), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 7, 10, 11, 7, 10, stairs.withProperty(StairsBlock.FACING, Direction.SOUTH), stairs.withProperty(StairsBlock.FACING, Direction.SOUTH), false);

		this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 11, 3, 2, Direction.NORTH);

		this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 9, 3, 5, Direction.EAST);

		this.placeTorch(worldIn, Direction.NORTH, 2, 5, 3, structureBoundingBoxIn);

		this.placeTorch(worldIn, Direction.NORTH, 8, 5, 3, structureBoundingBoxIn);

		this.placeTorch(worldIn, Direction.SOUTH, 3, 5, 10, structureBoundingBoxIn);

		this.placeTorch(worldIn, Direction.SOUTH, 7, 5, 10, structureBoundingBoxIn);

		this.placeTorch(worldIn, Direction.NORTH, 11, 5, 3, structureBoundingBoxIn);

		this.placeTorch(worldIn, Direction.SOUTH, 11, 5, 10, structureBoundingBoxIn);

		String theChosenOne = TSCraftMembers[MathHelper.getInt(new Random(), 0, TSCraftMembers.length - 1)];
		this.setBlockState(worldIn, Blocks.WALL_SIGN.getDefaultState().withProperty(BlockWallSign.FACING, Direction.SOUTH), 5, 7, 1, structureBoundingBoxIn);
		BlockPos blockpos = new BlockPos(this.getXWithOffset(5, 1), this.getYWithOffset(7), this.getZWithOffset(5, 1));

		TileEntity te = worldIn.getTileEntity(blockpos);
		if (te != null) {
			if (te instanceof TileEntitySign) {
				TileEntitySign tes = (TileEntitySign) te;

				tes.signText[0] = new TextComponentString("");
				tes.signText[1] = new TextComponentString("");
				tes.signText[2] = new TextComponentString(theChosenOne + "'s");
				tes.signText[3] = new TextComponentString("Workshop");
			}
		}

		if (Reference.enablePlayerSkullsInWorldGen) {
			this.setBlockState(worldIn, Blocks.SKULL.getDefaultState().withProperty(BlockSkull.FACING, Direction.SOUTH), 5, 8, 1, structureBoundingBoxIn);
			blockpos = new BlockPos(this.getXWithOffset(5, 1), this.getYWithOffset(8), this.getZWithOffset(5, 1));

			te = worldIn.getTileEntity(blockpos);
			if (te != null) {
				if (te instanceof TileEntitySkull) {
					TileEntitySkull tes = (TileEntitySkull) te;

					tes.setPlayerProfile(new GameProfile(null, theChosenOne));
				}
			}

		}
		// this.setBlockState(worldIn, cobble, 0, 1, 0, structureBoundingBoxIn);

		// this.setBlockState(worldIn, cobble, 0, 3, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 4, 1, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 4, 2, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 4, 3, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 0, 1, 4, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 0, 2, 4, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 0, 3, 4, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 4, 1, 4, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 4, 2, 4, structureBoundingBoxIn);
		// this.setBlockState(worldIn, cobble, 4, 3, 4, structureBoundingBoxIn);
		// this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, planks, planks, false);
		// this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, planks, planks, false);
		// this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 3, 4, planks, planks, false);
		// this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
		// this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 4, structureBoundingBoxIn);
		// this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
		// this.setBlockState(worldIn, planks, 1, 1, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, planks, 1, 2, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, planks, 1, 3, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, planks, 2, 3, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, planks, 3, 3, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, planks, 3, 2, 0, structureBoundingBoxIn);
		// this.setBlockState(worldIn, planks, 3, 1, 0, structureBoundingBoxIn);
		//
		// if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR) {
		// this.setBlockState(worldIn, log, 2, 0, -1, structureBoundingBoxIn);
		//
		// if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH) {
		// this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
		// }
		// }
		//
		// this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
		//
		// this.placeTorch(worldIn, Direction.NORTH, 2, 3, 1, structureBoundingBoxIn);

		for (int j = 0; j < 14; ++j) {
			for (int i = 0; i < 11; ++i) {
				this.clearCurrentPositionBlocksUpwards(worldIn, i, 10, j, structureBoundingBoxIn);
				this.replaceAirAndLiquidDownwards(worldIn, cobble, i, -1, j, structureBoundingBoxIn);
			}
		}

		this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1);
		return true;

	}

	protected void createVillageDoor(World p_189927_1_, StructureBoundingBox p_189927_2_, Random p_189927_3_, int p_189927_4_, int p_189927_5_, int p_189927_6_, Direction p_189927_7_) {
		if (!this.isZombieInfested) {
			this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_, p_189927_7_, this.biomeDoor());
		}
	}

	/**
	 * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y offset, z offset, number of villagers
	 */
	protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count) {
		if (this.villagersSpawned < count) {
			for (int i = this.villagersSpawned; i < count; ++i) {
				int j = this.getXWithOffset(x + i, z) + 2;
				int k = this.getYWithOffset(y) + 1;
				int l = this.getZWithOffset(x + i, z) + 2;

				if (!structurebb.isVecInside(new BlockPos(j, k, l))) {
					break;
				}

				++this.villagersSpawned;

				if (this.isZombieInfested) {
					EntityZombieVillager entityzombievillager = new EntityZombieVillager(worldIn);
					entityzombievillager.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
					entityzombievillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityzombievillager)), (IEntityLivingData) null);
					entityzombievillager.enablePersistence();
					worldIn.spawnEntity(entityzombievillager);
				} else {
					EntityVillager entityvillager = new EntityVillager(worldIn);
					entityvillager.setLocationAndAngles((double) j + 0.5D, (double) k + 1, (double) l + 0.5D, 0.0F, 0.0F);
					entityvillager.setProfession(ModVillage.engineer);
					entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData) null, false);
					worldIn.spawnEntity(entityvillager);
				}
			}
		}
	}

	public static EngineerHouse buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, Direction facing, int type) {
		StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 11, 6, 11, facing);
		return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new EngineerHouse(villagePiece, type, box, facing) : null;
	}

}
