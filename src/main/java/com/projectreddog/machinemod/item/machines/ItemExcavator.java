package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemExcavator extends ItemMachineModMachine {

	public ModelTransportable mt;

	public ItemExcavator() {
		super();
		this.setUnlocalizedName("excavator");
		this.maxStackSize = 1;

	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float xOff, float yOff, float zOff) {
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on loader!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityExcavator entityExcavator = new EntityExcavator(world);
			entityExcavator.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityExcavator.prevPosX = x + .5d;
			entityExcavator.prevPosY = y + 1.0d;
			entityExcavator.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityExcavator);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

}
