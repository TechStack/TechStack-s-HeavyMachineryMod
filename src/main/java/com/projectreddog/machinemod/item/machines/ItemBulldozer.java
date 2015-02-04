package com.projectreddog.machinemod.item.machines;

import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.item.ItemTransportable;
import com.projectreddog.machinemod.model.ModelBulldozer;
import com.projectreddog.machinemod.model.ModelTransportable;

public class ItemBulldozer extends ItemTransportable {

	public ModelTransportable mt;

	public ItemBulldozer() {
		super();
		this.setUnlocalizedName("bulldozer");
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

			EntityBulldozer entityBulldozer = new EntityBulldozer(world);
			entityBulldozer.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityBulldozer.prevPosX = x + .5d;
			entityBulldozer.prevPosY = y + 1.0d;
			entityBulldozer.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityBulldozer);
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
			mt = new ModelBulldozer();
		}
		return mt;
	}

}
