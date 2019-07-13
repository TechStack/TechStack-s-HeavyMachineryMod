package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineModBlastedLapis extends BlockMachineModBlastedStoneBase {

	public BlockMachineModBlastedLapis() {
		super();
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_LAPIS);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLASTED_LAPIS);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5f);

	}

}
