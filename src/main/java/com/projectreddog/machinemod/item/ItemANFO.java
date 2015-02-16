package com.projectreddog.machinemod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.block.BlockMachineDrilledStone;
import com.projectreddog.machinemod.init.ModBlocks;

public class ItemANFO extends ItemMachineMod {

	public ItemANFO() {
		super();
		this.setUnlocalizedName("anfo");
		this.maxStackSize = 64;

	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xOff, float yOff, float zOff) {
		boolean result = false;
		if (world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledstone) {

			EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineDrilledStone.FACING);
			if (ef == EnumFacing.DOWN || ef == EnumFacing.UP) {

				BlockPos bottom = null;
				for (int i = 0; i < 17; i++) {
					if (world.getBlockState(pos.offsetDown(i)).getBlock() == ModBlocks.machinedrilledstone) {
						bottom = pos.offsetDown(i);
					}
				}
				if (bottom != null) {
					world.setBlockState(bottom, ModBlocks.machineexplosivepackeddrilledstone.getDefaultState());
					result = true;
				}
			} else if (ef == EnumFacing.EAST || ef == EnumFacing.WEST || ef == EnumFacing.NORTH || ef == EnumFacing.SOUTH) {

				BlockPos bottom = null;
				for (int i = 0; i < 9; i++) {
					if (world.getBlockState(pos.offset(ef.getOpposite(), i)).getBlock() == ModBlocks.machinedrilledstone) {
						bottom = pos.offset(ef.getOpposite(), i);
					}
				}
				if (bottom != null) {
					world.setBlockState(bottom, ModBlocks.machineexplosivepackeddrilledstone.getDefaultState());
					result = true;
				}
			}

		}

		if (result && !player.capabilities.isCreativeMode) {
			stack.stackSize--;
		}
		return result;
	}
}
