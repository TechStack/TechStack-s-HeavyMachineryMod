package com.projectreddog.machinemod.item;

import java.util.List;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.tileentities.TileEntityAssemblyTable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHammer extends ItemMachineMod {
	public String registryName = "hammer";

	public ItemHammer() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.maxStackSize = 1;
		this.setMaxDamage(1000);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Used to do work in the assembly table by hand");

	}

	@Override
	public EnumActionResult onItemUse(PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getActiveItemStack();
		boolean result = false;
		if (world.getBlockState(pos).getBlock() == ModBlocks.machineassemblytable) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityAssemblyTable) {
				TileEntityAssemblyTable teat = (TileEntityAssemblyTable) te;

				if (teat.isWorkNeeded()) {
					teat.appyWork(10);
					player.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(player.getHeldItem(EnumHand.MAIN_HAND).getItemDamage() + 1);
					result = true;
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, .5f, 1.0f);

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
