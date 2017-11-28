package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineModBlastedAndesite extends BlockMachineModBlastedStoneBase {

	public BlockMachineModBlastedAndesite() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_ANDESITE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLASTED_ANDESITE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5f);

	}

}
