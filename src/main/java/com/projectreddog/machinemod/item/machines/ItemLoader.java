package com.projectreddog.machinemod.item.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.item.ItemTransportable;
import com.projectreddog.machinemod.model.ModelDumpTruck;
import com.projectreddog.machinemod.model.ModelLoader;
import com.projectreddog.machinemod.model.ModelTransportable;

public class ItemLoader extends ItemTransportable {

	public ModelTransportable mt;

	public ItemLoader() {
		super();
		this.setUnlocalizedName("loader");
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

			EntityLoader entityLoader = new EntityLoader(world);
			entityLoader.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityLoader.prevPosX = x + .5d;
			entityLoader.prevPosY = y + 1.0d;
			entityLoader.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityLoader);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.stackSize--;
			}
		}
		return result;
	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelLoader();
		}
		return mt;
	}
}
