package com.projectreddog.machinemod.item;

import java.util.HashMap;
import java.util.UUID;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.BlockBlueprintHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLaser extends ItemMachineMod {
	public String registryName = "laser";

	public ItemLaser() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;
		BlockBlueprintHelper.Point1Map = new HashMap<UUID, BlockPos>();
		BlockBlueprintHelper.Point2Map = new HashMap<UUID, BlockPos>();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;
		if (!world.isRemote) {
			// Server
			UUID tmpUUID = player.getUniqueID();

			if (player.isSneaking()) {
				if (BlockBlueprintHelper.Point1Map.containsKey(tmpUUID)) {
					BlockBlueprintHelper.Point1Map.remove(tmpUUID);
				}
				if (BlockBlueprintHelper.Point2Map.containsKey(tmpUUID)) {
					BlockBlueprintHelper.Point2Map.remove(tmpUUID);
				}
			}
			if (BlockBlueprintHelper.Point1Map.containsKey(tmpUUID)) {
				BlockBlueprintHelper.Point2Map.put(tmpUUID, pos);
				// Both set do the scan now ?
				player.openGui(MachineMod.instance, Reference.GUI_LASAER_LEVEL, world, pos.getX(), pos.getY(), pos.getZ());

				// ScanBlocks(world, Point1Map.get(tmpUUID), Point2Map.get(tmpUUID));
			} else {
				// no map create new!
				BlockBlueprintHelper.Point1Map.put(tmpUUID, pos);
			}
		}
		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

}
