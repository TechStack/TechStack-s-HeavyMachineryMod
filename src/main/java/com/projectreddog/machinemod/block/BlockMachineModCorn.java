package com.projectreddog.machinemod.block;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.projectreddog.machinemod.reference.Reference;

public class BlockMachineModCorn extends BlockBush implements IGrowable {

	public BlockMachineModCorn() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_CORN);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		// this.setStepSound();

		// this.setHardness(1.5f);

	}

	@Override
	public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
		// TODO Auto-generated method stub

	}

}
