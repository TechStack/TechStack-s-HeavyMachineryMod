package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineAssemblyTable extends BlockMachineMod {

	public BlockMachineAssemblyTable() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.METAL);
	}
}
