package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMachineExpInfusedBlock extends BlockMachineMod {

	public BlockMachineExpInfusedBlock() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_EXP_INFUSED_BLOCK);
		this.setRegistryName(Reference.MODBLOCK_EXP_INFUSED_BLOCK);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.GLASS);

	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}

}
