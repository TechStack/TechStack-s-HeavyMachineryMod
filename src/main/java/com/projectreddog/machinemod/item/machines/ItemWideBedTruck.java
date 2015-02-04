package com.projectreddog.machinemod.item.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemWideBedTruck extends ItemMachineMod {

	public ItemWideBedTruck() {
		super();
		this.setUnlocalizedName("widebedtruck");
		this.maxStackSize = 1;

	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xOff, float yOff, float zOff) {
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on dumptruck!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityWideBedTruck entityWideBedTruck = new EntityWideBedTruck(world);
			entityWideBedTruck.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityWideBedTruck.prevPosX = x + .5d;
			entityWideBedTruck.prevPosY = y + 1.0d;
			entityWideBedTruck.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityWideBedTruck);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}
}
