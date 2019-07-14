package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;

public class BlockMachineCrudeOilStone extends BlockMachineMod {

	public BlockMachineCrudeOilStone() {
		super();
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_CRUDE_OIL_STONE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_CRUDE_OIL_STONE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(5f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 * 
	 * @param fortune the level of the Fortune enchantment on the player's tool
	 */
	public Item getItemDropped(BlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.COBBLESTONE);
	}

}
