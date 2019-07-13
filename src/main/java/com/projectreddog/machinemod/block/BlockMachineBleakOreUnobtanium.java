package com.projectreddog.machinemod.block;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext;

public class BlockMachineBleakOreUnobtanium extends BlockMachineMod {

	public BlockMachineBleakOreUnobtanium() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 6000000.0F).sound(SoundType.METAL));
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_UNOBTANIUM);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_UNOBTANIUM);

	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random) {
		return random.nextInt(2) + 1;
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	@Deprecated
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(ModItems.unobtaniumgem));

		return list;
	}

}
