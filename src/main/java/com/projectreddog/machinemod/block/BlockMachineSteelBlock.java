package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMachineSteelBlock extends BlockMachineMod {

	public BlockMachineSteelBlock() {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(15f).sound(SoundType.METAL)); // 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_STEEL_BLOCK);
		this.setRegistryName(Reference.MODBLOCK_STEEL_BLOCK);

	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}

}
