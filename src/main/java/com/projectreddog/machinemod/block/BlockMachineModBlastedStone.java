package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;
public class BlockMachineModBlastedStone extends BlockMachineModFalling {




	public BlockMachineModBlastedStone()
	{
		super();
		//1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
//		this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		//this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
	}

}

