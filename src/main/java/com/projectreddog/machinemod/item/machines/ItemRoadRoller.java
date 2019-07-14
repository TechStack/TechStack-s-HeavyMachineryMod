package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityRoadRoller;
import com.projectreddog.machinemod.model.ModelRoadRoller;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRoadRoller extends ItemTransportable {
	public String registryName = "roadroller";

	public ModelTransportable mt;

	public ItemRoadRoller() {
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

			EntityMachineModRideable entityRoadRoller = getEntityToSpawn(world);
			entityRoadRoller.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityRoadRoller.prevPosX = x + .5d;
			entityRoadRoller.prevPosY = y + 1.0d;
			entityRoadRoller.prevPosZ = z + .5d;
			result = world.spawnEntity(entityRoadRoller);
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
		return new EntityRoadRoller(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelRoadRoller();
		}
		return mt;
	}
}
