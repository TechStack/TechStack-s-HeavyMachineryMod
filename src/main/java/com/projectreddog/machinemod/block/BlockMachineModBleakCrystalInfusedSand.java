package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;

public class BlockMachineModBleakCrystalInfusedSand extends BlockMachineModFalling {

	public BlockMachineModBleakCrystalInfusedSand() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_CRYSTAL_INFUSED_SAND);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_CRYSTAL_INFUSED_SAND);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.SAND);
		this.setHardness(0.5f);

	}

}
