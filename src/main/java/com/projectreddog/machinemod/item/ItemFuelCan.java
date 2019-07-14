package com.projectreddog.machinemod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFuelCan extends ItemMachineMod {
	public String registryName = "fuelcan";

	public ItemFuelCan() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;
		this.setMaxDamage(1000);
	}

	@Override
	public EnumActionResult onItemUse(PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getActiveItemStack(); // testing code
		boolean result = false;

		// this.setDamage(stack, this.getDamage(stack) + 1);

		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	/**
	 * ItemStack sensitive version of getContainerItem. Returns a full ItemStack instance of the result.
	 *
	 * @param itemStack
	 *            The current ItemStack
	 * @return The resulting ItemStack
	 */
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {

		if (!hasContainerItem(itemStack)) {
			return null;
		}
		ItemStack is = itemStack.copy();
		is.attemptDamageItem(getMaxDamage(), itemRand, null);
		return is;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
}
