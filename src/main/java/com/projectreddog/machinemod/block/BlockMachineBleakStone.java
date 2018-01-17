package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMachineBleakStone extends BlockMachineMod {

	public BlockMachineBleakStone() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_STONE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_STONE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setBlockUnbreakable();// not sure on the hardness
		this.setSoundType(SoundType.STONE);
	}

	/**
	 * Determines if the current block is replaceable by Ore veins during world generation.
	 *
	 * @param state
	 *            The current state
	 * @param world
	 *            The current world
	 * @param pos
	 *            Block position in world
	 * @param target
	 *            The generic target block the gen is looking for, Standards define stone for overworld generation, and neatherack for the nether.
	 * @return True to allow this block to be replaced by a ore
	 */
	public boolean isReplaceableOreGen(IBlockState state, IBlockAccess world, BlockPos pos, com.google.common.base.Predicate<IBlockState> target) {
		return true;
	}

}
