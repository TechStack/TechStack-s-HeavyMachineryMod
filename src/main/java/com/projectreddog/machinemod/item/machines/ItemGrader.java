package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.model.ModelGrader;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemGrader extends ItemTransportable {

	public ModelTransportable mt;

	public ItemGrader() {
		super();
		this.setUnlocalizedName("grader");
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

			EntityMachineModRideable entityGrader = getEntityToSpawn(world);
			entityGrader.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityGrader.prevPosX = x + .5d;
			entityGrader.prevPosY = y + 1.0d;
			entityGrader.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityGrader);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityGrader(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelGrader();
		}
		return mt;
	}
}
