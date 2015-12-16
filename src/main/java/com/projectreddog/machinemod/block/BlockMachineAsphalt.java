package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

public class BlockMachineAsphalt extends BlockMachineMod {

	public BlockMachineAsphalt() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_ASPHALT);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
	}

}
