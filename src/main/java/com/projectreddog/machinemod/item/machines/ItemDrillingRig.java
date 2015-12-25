package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.model.ModelDrillingRig;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDrillingRig extends ItemTransportable {

	public ModelTransportable mt;

	public ItemDrillingRig() {
		super();
		this.setUnlocalizedName("drillingrig");
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

			EntityDrillingRig entityDrillingRig = new EntityDrillingRig(world);
			entityDrillingRig.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityDrillingRig.prevPosX = x + .5d;
			entityDrillingRig.prevPosY = y + 1.0d;
			entityDrillingRig.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityDrillingRig);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityDrillingRig(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelDrillingRig();
		}
		return mt;
	}

}
