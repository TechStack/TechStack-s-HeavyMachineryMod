package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineAsphalt extends BlockMachineMod {

	public BlockMachineAsphalt() {
		super();
		// 1.8

		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_ASPHALT);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_ASPHALT);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
	}

}
