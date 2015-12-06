package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityLawnmower;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.item.ItemTransportable;
import com.projectreddog.machinemod.model.ModelLawnmower;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemLawnmower extends ItemTransportable {

	public ModelTransportable mt;

	public ItemLawnmower() {
		super();
		this.setUnlocalizedName("lawnmower");
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

			EntityMachineModRideable entityLawnmower = getEntityToSpawn(world);
			entityLawnmower.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityLawnmower.prevPosX = x + .5d;
			entityLawnmower.prevPosY = y + 1.0d;
			entityLawnmower.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityLawnmower);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityLawnmower(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelLawnmower();
		}
		return mt;
	}
}
