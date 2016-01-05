package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityLiquidPipe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockMachineModLiquidPipe extends BlockContainer {

	public BlockMachineModLiquidPipe(Material material) {
		super(material);
		// 1.8
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_LIQUID_PIPE);
		// this.setBlockName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5f);

	}

	public BlockMachineModLiquidPipe() {
		// Generic constructor (set to rock by default)
		this(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityLiquidPipe();
	}

	@Override
	public int getRenderType() {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		return 2;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public int getRandomizedUpdateTime(World world) {
		// the following line may be needed ( config option perhaps) to slow the
		// update of the falling blocks
		// return this.tickRate(world)*5 + (1 + (int)(Math.random()*10));
		return this.tickRate(world);
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		onBlockAdded(worldIn, pos.getX(), pos.getY(), pos.getZ());
	}

	// / my 1.7 version
	public void onBlockAdded(World world, int x, int y, int z) {
		world.scheduleUpdate(new BlockPos(x, y, z), this, getRandomizedUpdateTime(world));

	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, neighbor Block
	 */

	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		onNeighborBlockChange(worldIn, pos.getX(), pos.getY(), pos.getZ(), neighborBlock);
	}

	// / my 1.7 version
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		world.scheduleUpdate(new BlockPos(x, y, z), this, getRandomizedUpdateTime(world));

		if (world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == this) {
			world.scheduleUpdate(new BlockPos(x, y + 1, z), this, getRandomizedUpdateTime(world));
		}
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		if (!worldIn.isRemote) {
			if (worldIn.getTileEntity(pos) instanceof TileEntityLiquidPipe) {
				((TileEntityLiquidPipe) worldIn.getTileEntity(pos)).updateConnections();
			}
		}

	}

	/**
	 * How many world ticks before ticking
	 */
	public int tickRate(World world) {
		return 2;
	}

}
