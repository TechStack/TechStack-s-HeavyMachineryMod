package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityUnderGroundDumpTruck;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemUnderGroundDumpTruck extends ItemMachineModMachine {
	public String registryName = "undergrounddumptruck";

	// public ModelTransportable mt ;

	public ItemUnderGroundDumpTruck() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on dumptruck!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityUnderGroundDumpTruck entityUnderGroundDumpTruck = new EntityUnderGroundDumpTruck(world);
			entityUnderGroundDumpTruck.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityUnderGroundDumpTruck.prevPosX = x + .5d;
			entityUnderGroundDumpTruck.prevPosY = y + 1.0d;
			entityUnderGroundDumpTruck.prevPosZ = z + .5d;
			result = world.spawnEntity(entityUnderGroundDumpTruck);
			// LogHelper.info("Spawn entity resutl:" + result );
			if (result && !player.capabilities.isCreativeMode) {
				stack.setCount(stack.getCount() - 1);
			}
		}
		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
	// @Override
	// public ModelTransportable getModel() {
	// if (mt == null){
	// mt = new ModelDumpTruck();
	// }
	// return mt;
	// }
}
