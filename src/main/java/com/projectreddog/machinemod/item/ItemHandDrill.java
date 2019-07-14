package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.block.BlockMachineDrilledStone;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockStone;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHandDrill extends ItemMachineMod {
	public String registryName = "handdrill";

	public int currentDepth = 0;

	public ItemHandDrill() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;
		this.setMaxDamage(1000);
	}

	@Override
	public EnumActionResult onItemUse(PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getActiveItemStack();
		boolean result = false;
		if (world.getBlockState(pos).getBlock() == Blocks.STONE && world.getBlockState(pos).getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE) {
			// facing constarined to a horizontal plane
			Direction ef = player.getHorizontalFacing();
			// Direction ef = (Direction) world.getBlockState(pos).getValue(BlockMachineDrilledStone.FACING);

			world.setBlockState(pos, ModBlocks.machinedrilledstone.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
			BlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);

		} else if (world.getBlockState(pos).getBlock() == Blocks.STONE && world.getBlockState(pos).getValue(BlockStone.VARIANT) == BlockStone.EnumType.ANDESITE) {
			// facing constarined to a horizontal plane
			Direction ef = player.getHorizontalFacing();
			// Direction ef = (Direction) world.getBlockState(pos).getValue(BlockMachineDrilledStone.FACING);

			world.setBlockState(pos, ModBlocks.machinedrilledandesite.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
			BlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);

		} else if (world.getBlockState(pos).getBlock() == Blocks.STONE && world.getBlockState(pos).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
			// facing constarined to a horizontal plane
			Direction ef = player.getHorizontalFacing();
			// Direction ef = (Direction) world.getBlockState(pos).getValue(BlockMachineDrilledStone.FACING);

			world.setBlockState(pos, ModBlocks.machinedrilleddiorite.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
			BlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);

		} else if (world.getBlockState(pos).getBlock() == Blocks.STONE && world.getBlockState(pos).getValue(BlockStone.VARIANT) == BlockStone.EnumType.GRANITE) {
			// facing constarined to a horizontal plane
			Direction ef = player.getHorizontalFacing();
			// Direction ef = (Direction) world.getBlockState(pos).getValue(BlockMachineDrilledStone.FACING);

			world.setBlockState(pos, ModBlocks.machinedrilledgranite.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
			BlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);

		}

		else if (world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledstone || world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledandesite || world.getBlockState(pos).getBlock() == ModBlocks.machinedrilleddiorite || world.getBlockState(pos).getBlock() == ModBlocks.machinedrilledgranite) {
			Direction ef = player.getHorizontalFacing();

			for (int i = 0; i < 8; i++) {
				if (world.getBlockState(pos.offset(ef, i)).getBlock() == Blocks.STONE && world.getBlockState(pos.offset(ef, i)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE) {
					world.setBlockState(pos.offset(ef, i), ModBlocks.machinedrilledstone.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
					BlockState state = world.getBlockState(pos.offset(ef, i));
					world.notifyBlockUpdate(pos.offset(ef, i), state, state, 3);
					i = 8;

					player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);
				} else if (world.getBlockState(pos.offset(ef, i)).getBlock() == Blocks.STONE && world.getBlockState(pos.offset(ef, i)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.ANDESITE) {
					world.setBlockState(pos.offset(ef, i), ModBlocks.machinedrilledandesite.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
					BlockState state = world.getBlockState(pos.offset(ef, i));
					world.notifyBlockUpdate(pos.offset(ef, i), state, state, 3);
					i = 8;
					player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);
				} else if (world.getBlockState(pos.offset(ef, i)).getBlock() == Blocks.STONE && world.getBlockState(pos.offset(ef, i)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
					world.setBlockState(pos.offset(ef, i), ModBlocks.machinedrilleddiorite.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
					BlockState state = world.getBlockState(pos.offset(ef, i));
					world.notifyBlockUpdate(pos.offset(ef, i), state, state, 3);
					i = 8;
					player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);
				} else if (world.getBlockState(pos.offset(ef, i)).getBlock() == Blocks.STONE && world.getBlockState(pos.offset(ef, i)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.GRANITE) {
					world.setBlockState(pos.offset(ef, i), ModBlocks.machinedrilledgranite.getDefaultState().withProperty(BlockMachineDrilledStone.FACING, ef.getOpposite()), 3);
					BlockState state = world.getBlockState(pos.offset(ef, i));
					world.notifyBlockUpdate(pos.offset(ef, i), state, state, 3);
					i = 8;
					player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);
				}

			}
		}
		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
}
