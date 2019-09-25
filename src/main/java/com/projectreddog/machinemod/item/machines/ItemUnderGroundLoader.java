package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityUnderGroundLoader;
import com.projectreddog.machinemod.model.ModelLoader;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemUnderGroundLoader extends ItemTransportable {
	public String registryName = "undergroundloader";

	public ModelTransportable mt;

	public ItemUnderGroundLoader() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{
			// LogHelper.info("Item used on loader!");
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			EntityMachineModRideable entityUnderGroundLoader = getEntityToSpawn(world);
			entityUnderGroundLoader.setPosition(x + .5d, y + 1.0d, z + .5d);
			entityUnderGroundLoader.prevPosX = x + .5d;
			entityUnderGroundLoader.prevPosY = y + 1.0d;
			entityUnderGroundLoader.prevPosZ = z + .5d;
			result = world.spawnEntity(entityUnderGroundLoader);
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
		return new EntityUnderGroundLoader(world);

	}

	@Override
	public ModelTransportable getModel() {
		if (mt == null) {
			mt = new ModelLoader();
		}
		return mt;
	}
}
