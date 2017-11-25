package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineModBlastedStoneBase extends BlockMachineModFalling {

	public BlockMachineModBlastedStoneBase() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setRegistryName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
	}

}
