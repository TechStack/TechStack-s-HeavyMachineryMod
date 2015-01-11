package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

public class BlockMachineDrilledStone extends BlockMachineModManyTexture{

	public BlockMachineDrilledStone()
	{
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
//		this.setBlockName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
//		this.setBlockTextureName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		//this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
	}

}
