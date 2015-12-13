package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBagger extends ItemMachineMod {

	public ModelTransportable mt;

	public ItemBagger() {
		super();
		this.setUnlocalizedName("bagger");
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

			EntityMachineModRideable entityBagger = getEntityToSpawn(world);
			entityBagger.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityBagger.prevPosX = x + .5d;
			entityBagger.prevPosY = y + 1.0d;
			entityBagger.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityBagger);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityBagger(world);

	}

}
