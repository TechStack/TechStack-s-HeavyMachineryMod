package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.model.ModelChopper;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemChopper extends ItemTransportable {
	public String registryName = "chopper";

	public ModelTransportable mt;

	public ItemChopper() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	@Override
	// public boolean onItemUse(ItemStack stack, PlayerEntity playerIn, World
	// worldIn, BlockPos pos, Direction side, float hitX, float hitY, float
	// hitZ)
	public EnumActionResult onItemUse(PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on Bulldozer!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityMachineModRideable entityChopper = getEntityToSpawn(world);
			entityChopper.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityChopper.prevPosX = x + .5d;
			entityChopper.prevPosY = y + 1.0d;
			entityChopper.prevPosZ = z + .5d;
			result = world.spawnEntity(entityChopper);
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

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityChopper(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelChopper();
		}
		return mt;
	}
}
