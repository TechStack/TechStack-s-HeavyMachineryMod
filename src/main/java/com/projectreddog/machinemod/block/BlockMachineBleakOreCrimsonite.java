package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockMachineBleakOreCrimsonite extends BlockMachineMod {

	public BlockMachineBleakOreCrimsonite() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_CRIMSONITE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_CRIMSONITE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setBlockUnbreakable();// not sure on the hardness
		this.setSoundType(SoundType.METAL);
		this.setResistance(6000000.0F);

	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random) {
		return random.nextInt(3) + 1;
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.crimsonitepebble;
	}

}
