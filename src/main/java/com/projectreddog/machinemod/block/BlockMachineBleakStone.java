package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMachineBleakStone extends BlockMachineMod {

	public BlockMachineBleakStone() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 6000000.0F).sound(SoundType.STONE));
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_STONE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_STONE);

	}
// TODO  FIX BLEAK STONE SO It can be replaced by world gen
//	/**
//	 * Determines if the current block is replaceable by Ore veins during world
//	 * generation.
//	 *
//	 * @param state The current state
//	 * @param world The current world
//	 * @param pos Block position in world
//	 * @param target The generic target block the gen is looking for, Standards
//	 * define stone for overworld generation, and neatherack for the nether.
//	 * @return True to allow this block to be replaced by a ore
//	 */
//	public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, com.google.common.base.Predicate<IBlockState> target) {
//		return true;
//	}

}
