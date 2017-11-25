package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.block.BlockMachineModBlastedStone;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBlastedStone extends ItemBlock {

	public ItemBlockBlastedStone(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		BlockMachineModBlastedStone.EnumVanillaOres ore = BlockMachineModBlastedStone.EnumVanillaOres.byMetadata(stack.getMetadata());
		return super.getUnlocalizedName() + "." + ore.toString();
	}
}
