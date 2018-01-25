package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineBleakOreMagentia extends BlockMachineMod {

	public BlockMachineBleakOreMagentia() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_MAGENTIA);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_MAGENTIA);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setBlockUnbreakable();// not sure on the hardness
		this.setSoundType(SoundType.METAL);
	}

}
