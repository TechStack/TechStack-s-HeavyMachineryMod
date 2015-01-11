




package com.projectreddog.machinemod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.utility.LogHelper;


public class ItemDrillingRig extends ItemMachineMod {







	public ItemDrillingRig(){
		super();
		this.setUnlocalizedName("drillingrig");
		this.maxStackSize =1;
		
	}



	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,float xOff, float yOff, float zOff)
	{
		boolean result = false;

		if (!world.isRemote)/// only run on server
		{
			//LogHelper.info("Item used on drillingrig!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityDrillingRig entityDrillingRig = new EntityDrillingRig(world);
			entityDrillingRig.setPosition(x+.5d,y+1.0d,z+.5d);
			entityDrillingRig.prevPosX= x+.5d;
			entityDrillingRig.prevPosY= y+.5d;
			entityDrillingRig.prevPosZ= z+.5d;
			result = world.spawnEntityInWorld(entityDrillingRig);
			//LogHelper.info("Spawn entity resutl:" + result );
		}
		return result;
	}
}

