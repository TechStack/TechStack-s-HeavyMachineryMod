package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCombine extends ItemMachineModMachine {
	public String registryName = "combine";

	public ModelTransportable mt;

	public ItemCombine() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	@Override
	public EnumActionResult onItemUse(PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, PlayerEntity player, World world, BlockPos pos, EnumHand hand, Direction side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on loader!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityCombine entityCombine = new EntityCombine(world);
			entityCombine.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityCombine.prevPosX = x + .5d;
			entityCombine.prevPosY = y + 1.0d;
			entityCombine.prevPosZ = z + .5d;
			result = world.spawnEntity(entityCombine);
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

}
