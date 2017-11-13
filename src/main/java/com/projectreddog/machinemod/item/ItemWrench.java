package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.block.BlockMachineModConveyor;
import com.projectreddog.machinemod.block.BlockMachineModFractionalDistillation;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWrench extends ItemMachineMod {
	public String registryName = "wrench";

	public ItemWrench() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getActiveItemStack();
		boolean result = false;

		if (world.getBlockState(pos).getBlock() == ModBlocks.machineconveyor) {
			EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineModConveyor.FACING);
			switch (ef) {
			case NORTH:
				world.setBlockState(pos, ModBlocks.machineconveyor.getDefaultState().withProperty(BlockMachineModConveyor.FACING, EnumFacing.EAST), 3);
				break;
			case EAST:
				world.setBlockState(pos, ModBlocks.machineconveyor.getDefaultState().withProperty(BlockMachineModConveyor.FACING, EnumFacing.SOUTH), 3);
				break;
			case SOUTH:
				world.setBlockState(pos, ModBlocks.machineconveyor.getDefaultState().withProperty(BlockMachineModConveyor.FACING, EnumFacing.WEST), 3);
				break;
			case WEST:
				world.setBlockState(pos, ModBlocks.machineconveyor.getDefaultState().withProperty(BlockMachineModConveyor.FACING, EnumFacing.NORTH), 3);
				break;

			default:
				break;
			}
		} else if (world.getBlockState(pos).getBlock() == ModBlocks.machinefractionaldistillation) {
			EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineModFractionalDistillation.FACING);
			switch (ef) {
			case NORTH:
				world.setBlockState(pos, ModBlocks.machinefractionaldistillation.getDefaultState().withProperty(BlockMachineModFractionalDistillation.FACING, EnumFacing.EAST), 3);
				break;
			case EAST:
				world.setBlockState(pos, ModBlocks.machinefractionaldistillation.getDefaultState().withProperty(BlockMachineModFractionalDistillation.FACING, EnumFacing.SOUTH), 3);
				break;
			case SOUTH:
				world.setBlockState(pos, ModBlocks.machinefractionaldistillation.getDefaultState().withProperty(BlockMachineModFractionalDistillation.FACING, EnumFacing.WEST), 3);
				break;
			case WEST:
				world.setBlockState(pos, ModBlocks.machinefractionaldistillation.getDefaultState().withProperty(BlockMachineModFractionalDistillation.FACING, EnumFacing.NORTH), 3);
				break;

			default:
				break;
			}

		}

		// else if (world.getBlockState(pos).getBlock() == ModBlocks.machinedistiller) {
		// EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineModDistiller.FACING);
		// switch (ef) {
		// case NORTH:
		// world.setBlockState(pos, ModBlocks.machinedistiller.getDefaultState().withProperty(BlockMachineModDistiller.FACING, EnumFacing.EAST));
		// break;
		// case EAST:
		// world.setBlockState(pos, ModBlocks.machinedistiller.getDefaultState().withProperty(BlockMachineModDistiller.FACING, EnumFacing.SOUTH));
		// break;
		// case SOUTH:
		// world.setBlockState(pos, ModBlocks.machinedistiller.getDefaultState().withProperty(BlockMachineModDistiller.FACING, EnumFacing.WEST));
		// break;
		// case WEST:
		// world.setBlockState(pos, ModBlocks.machinedistiller.getDefaultState().withProperty(BlockMachineModDistiller.FACING, EnumFacing.NORTH));
		// break;
		//
		// default:
		// break;
		// }
		//
		// } else if (world.getBlockState(pos).getBlock() == ModBlocks.machinefermenter) {
		// EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineModFermenter.FACING);
		// switch (ef) {
		// case NORTH:
		// world.setBlockState(pos, ModBlocks.machinefermenter.getDefaultState().withProperty(BlockMachineModFermenter.FACING, EnumFacing.EAST));
		// break;
		// case EAST:
		// world.setBlockState(pos, ModBlocks.machinefermenter.getDefaultState().withProperty(BlockMachineModFermenter.FACING, EnumFacing.SOUTH));
		// break;
		// case SOUTH:
		// world.setBlockState(pos, ModBlocks.machinefermenter.getDefaultState().withProperty(BlockMachineModFermenter.FACING, EnumFacing.WEST));
		// break;
		// case WEST:
		// world.setBlockState(pos, ModBlocks.machinefermenter.getDefaultState().withProperty(BlockMachineModFermenter.FACING, EnumFacing.NORTH));
		// break;
		//
		// default:
		// break;
		// }
		//
		// } else if (world.getBlockState(pos).getBlock() == ModBlocks.machinefuelpump) {
		// EnumFacing ef = (EnumFacing) world.getBlockState(pos).getValue(BlockMachineModFuelPump.FACING);
		// switch (ef) {
		// case NORTH:
		//
		// world.setBlockState(pos, ModBlocks.machinefuelpump.getDefaultState().withProperty(BlockMachineModFuelPump.FACING, EnumFacing.EAST));
		// break;
		// case EAST:
		// world.setBlockState(pos, ModBlocks.machinefuelpump.getDefaultState().withProperty(BlockMachineModFuelPump.FACING, EnumFacing.SOUTH));
		// break;
		// case SOUTH:
		// world.setBlockState(pos, ModBlocks.machinefuelpump.getDefaultState().withProperty(BlockMachineModFuelPump.FACING, EnumFacing.WEST));
		// break;
		// case WEST:
		// world.setBlockState(pos, ModBlocks.machinefuelpump.getDefaultState().withProperty(BlockMachineModFuelPump.FACING, EnumFacing.NORTH));
		// break;
		//
		// default:
		// break;
		// }
		//
		// }

		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
}
