package com.projectreddog.machinemod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFuelCan extends ItemMachineMod {

	public ItemFuelCan() {
		super();
		this.setUnlocalizedName("fuelcan");
		this.maxStackSize = 1;
		this.setMaxDamage(1000);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xOff, float yOff, float zOff) {
		// testing code
		boolean result = false;

		this.setDamage(stack, this.getDamage(stack) + 1);

		return true;

	}
}
