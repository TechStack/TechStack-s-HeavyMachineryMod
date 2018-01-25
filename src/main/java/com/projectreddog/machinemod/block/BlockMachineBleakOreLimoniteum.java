package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineBleakOreLimoniteum extends BlockMachineMod {

	public BlockMachineBleakOreLimoniteum() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_LIMONITEUM);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_LIMONITEUM);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setBlockUnbreakable();// not sure on the hardness
		this.setSoundType(SoundType.METAL);
	}

}
