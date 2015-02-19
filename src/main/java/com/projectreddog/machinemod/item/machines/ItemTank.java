package com.projectreddog.machinemod.item.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityTank;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.model.ModelTransportable;

public class ItemTank extends ItemMachineMod {

	public ModelTransportable mt;

	public ItemTank() {
		super();
		this.setUnlocalizedName("tank");
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

			EntityMachineModRideable entityTank = getEntityToSpawn(world);
			entityTank.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityTank.prevPosX = x + .5d;
			entityTank.prevPosY = y + 1.0d;
			entityTank.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityTank);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityTank(world);

	}

}
