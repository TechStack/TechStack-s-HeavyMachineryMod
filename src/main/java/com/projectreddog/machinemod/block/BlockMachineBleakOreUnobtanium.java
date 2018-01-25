package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineBleakOreUnobtanium extends BlockMachineMod {

	public BlockMachineBleakOreUnobtanium() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_UNOBTANIUM);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_UNOBTANIUM);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setBlockUnbreakable();// not sure on the hardness
		this.setSoundType(SoundType.METAL);
	}

}
