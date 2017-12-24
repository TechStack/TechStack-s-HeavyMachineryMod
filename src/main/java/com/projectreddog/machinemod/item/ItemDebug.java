package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.init.ModDimensions;
import com.projectreddog.machinemod.world.BleakTeleporter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDebug extends ItemMachineMod {
	public String registryName = "debug";

	public ItemDebug() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		boolean result = false;

		// TP THE PLAYER TO THE DIM and set result true if it worked;

		if (!world.isRemote) {
			// server

			EntityPlayerMP emp = (EntityPlayerMP) player;
			if (player.isSneaking()) {
				BleakTeleporter.teleportToDimension(player, 0, pos.getX(), pos.getY(), pos.getZ());
			} else {
				BleakTeleporter.teleportToDimension(player, ModDimensions.bleakDimID, pos.getX(), pos.getY(), pos.getZ());
			}
		}

		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
}
