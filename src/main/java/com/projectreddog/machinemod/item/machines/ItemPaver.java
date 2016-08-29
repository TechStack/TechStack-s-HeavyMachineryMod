package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.model.ModelPaver;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemPaver extends ItemTransportable {

	public ModelTransportable mt;

	public ItemPaver() {
		super();
		this.setUnlocalizedName("paver");
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

			EntityMachineModRideable entityPaver = getEntityToSpawn(world);
			entityPaver.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityPaver.prevPosX = x + .5d;
			entityPaver.prevPosY = y + 1.0d;
			entityPaver.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityPaver);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityPaver(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelPaver();
		}
		return mt;
	}
}
