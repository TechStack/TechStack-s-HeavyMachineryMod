package com.projectreddog.machinemod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.block.BlockMachineDrilledStone;
import com.projectreddog.machinemod.init.ModBlocks;

public class ItemHandDrill extends ItemMachineMod {

	public int currentDepth = 0;

	public ItemHandDrill() {
		super();
		this.setUnlocalizedName("handdrill");
		this.maxStackSize = 1;
		this.setMaxDamage(1000);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xOff, float yOff, float zOff) {
		boolean result = false;
		if (world.getBlockState(pos).getBlock() == Blocks.stone) {
			// facing constarined to a horizontal plane
			EnumFacing ef = player.func_174811_aO();
			// EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineDrilledStone.FACING);

			world.setBlockState(pos, ModBlocks.machinedrilledstone.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()));
			player.getHeldItem().setItemDamage(player.getHeldItem().getItemDamage() + 1);

		} else if (world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledstone) {
			EnumFacing ef = player.func_174811_aO();

			for (int i = 0; i < 8; i++) {
				if (world.getBlockState(pos.offset(ef, i)).getBlock() == Blocks.stone) {
					world.setBlockState(pos.offset(ef, i), ModBlocks.machinedrilledstone.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()));
					i = 8;
					player.getHeldItem().setItemDamage(player.getHeldItem().getItemDamage() + 1);
				}
			}
		}
		return result;
	}
}
