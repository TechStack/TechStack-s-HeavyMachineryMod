package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.block.state.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemBleakCrystal extends ItemMachineMod implements IPlantable {
	public String registryName = "bleakcrystal";

	public ItemBleakCrystal() {

		// heal sat wolf fav
		super();
		// this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" + "cornseed");
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

	/**
	 * Called when a Block is right-clicked with this Item
	 * 
	 * @param pos
	 *            The block being right-clicked
	 * @param side
	 *            The side being right-clicked
	 */
	// public EnumActionResult onItemUse(PlayerEntity player, World worldIn, BlockPos pos, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)

	@Override
	public EnumActionResult onItemUse(PlayerEntity playerIn, World worldIn, BlockPos pos, EnumHand hand, Direction side, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (side != Direction.UP) {
			return EnumActionResult.FAIL;
		} else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
			return EnumActionResult.FAIL;
		} else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.machinebleakdirt && worldIn.isAirBlock(pos.up()) && worldIn.getLight(pos.up()) == 0) {
			worldIn.setBlockState(pos.up(), ModBlocks.machinebleakcrystal.getDefaultState());
			stack.setCount(stack.getCount() - 1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return net.minecraftforge.common.EnumPlantType.Crop;
	}

	@Override
	public BlockState getPlant(IBlockAccess world, BlockPos pos) {
		return ModBlocks.machinebleakcrystal.getDefaultState();
	}

}
