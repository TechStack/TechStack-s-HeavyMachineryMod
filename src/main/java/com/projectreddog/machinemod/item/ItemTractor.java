package com.projectreddog.machinemod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.model.ModelLoader;
import com.projectreddog.machinemod.model.ModelTractor;
import com.projectreddog.machinemod.model.ModelTransportable;

public class ItemTractor extends ItemTransportable {

	public ModelTransportable mt ;

	public ItemTractor() {
		super();
		this.setUnlocalizedName("tractor");
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

			EntityTractor entityTractor = new EntityTractor(world);
			entityTractor.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityTractor.prevPosX = x + .5d;
			entityTractor.prevPosY = y +  1.0d;
			entityTractor.prevPosZ = z + .5d;
			result = world.spawnEntityInWorld(entityTractor);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode){
				stack.stackSize --;
			}
		}
		return result;
	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null){
			mt = new ModelTractor();
		}
		return mt;
	}
}
