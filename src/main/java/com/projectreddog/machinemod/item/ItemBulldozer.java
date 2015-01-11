package com.projectreddog.machinemod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBulldozer extends ItemMachineMod {

	public ItemBulldozer(){
		super();
		this.setUnlocalizedName("bulldozer");
		this.maxStackSize =1;

	}

	

	@Override
	//    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,float xOff, float yOff, float zOff)
	{
		boolean result = false;
		
		if (!world.isRemote)/// only run on server
		{
		//LogHelper.info("Item used on Bulldozer!");
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		EntityBulldozer entityBulldozer = new EntityBulldozer(world);
		entityBulldozer.setPosition(x+.5d,y+1.0d,z+.5d);
		entityBulldozer.prevPosX= x+.5d;
		entityBulldozer.prevPosY= y+.5d;
		entityBulldozer.prevPosZ= z+.5d;
		result = world.spawnEntityInWorld(entityBulldozer);
		//LogHelper.info("Spawn entity resutl:" + result );
		}
		return result;
	}

}
