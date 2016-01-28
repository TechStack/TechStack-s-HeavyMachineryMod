package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.model.ModelChopper;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemChopper extends ItemTransportable {

	public ModelTransportable mt;

	public ItemChopper() {
		super();
		this.setUnlocalizedName("chopper");
		this.maxStackSize = 1;

	}

	@Override
	// public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World
	// worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float
	// hitZ)
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xOff, float yOff, float zOff) {
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on Bulldozer!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityMachineModRideable entityChopper = getEntityToSpawn(world);
			entityChopper.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityChopper.prevPosX = x + .5d;
			entityChopper.prevPosY = y + 1.0d;
			entityChopper.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityChopper);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityChopper(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelChopper();
		}
		return mt;
	}
}
