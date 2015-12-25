package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityCrane;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemCrane extends ItemMachineModMachine {

	public ModelTransportable mt;

	public ItemCrane() {
		super();
		this.setUnlocalizedName("crane");
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

			EntityCrane entityCrane = new EntityCrane(world);
			entityCrane.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityCrane.prevPosX = x + .5d;
			entityCrane.prevPosY = y + 1.0d;
			entityCrane.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityCrane);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

}
