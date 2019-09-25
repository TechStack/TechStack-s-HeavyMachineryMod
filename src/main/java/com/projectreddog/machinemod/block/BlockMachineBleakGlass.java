package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineBleakGlass extends BlockMachineMod {

	public BlockMachineBleakGlass() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_GLASS);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_GLASS);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.GLASS);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (this == ModBlocks.machinebleakglass) {
			if (blockState != iblockstate) {
				return true;
			}

			if (block == this) {
				return false;
			}
		}

		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
}
