package com.projectreddog.machinemod.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModItems;

public class EntityTractor extends EntityMachineModRideable {

	public double bladeOffset = 2.0d;

	public EntityTractor(World world) {
		super(world);
		setSize(1.5F, 2F);
		inventory = new ItemStack[1];
		this.mountedOffsetY = 0.55D;
		this.mountedOffsetX = 0.65d;
		this.mountedOffsetZ = 0.65d;
		this.maxAngle = 0;
		this.minAngle = -60;
		this.droppedItem = ModItems.tractor;
	}




	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			// digMethodA();
			BlockPos bp;
			int angle = 0;
			// this will calcl the offset for three positions behind the tractor
			// (3 wide)
			if (this.isPlayerPushingSprintButton) {
				for (int i = -1; i < 2; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					bp = new BlockPos(posX + calcTwoOffsetX(-3.5, angle, i), posY - 1, posZ + calcTwoOffsetZ(-3.5, angle, i));
					if (worldObj.getBlockState(bp).getBlock() == Blocks.dirt || worldObj.getBlockState(bp).getBlock() == Blocks.grass) {
						worldObj.setBlockState(bp, Blocks.farmland.getDefaultState());
					}
				}

			}

		}
	}

}
