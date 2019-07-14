package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityPaver extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityPaver(World world) {
		super(world);

		setSize(4.5f, 4f);
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];

		this.mountedOffsetY = 0.83D;
		this.mountedOffsetX = -1.1D;
		this.mountedOffsetZ = -1.75D;
		this.maxAngle = 15;
		this.minAngle = -90;
		this.droppedItem = ModItems.paver;
		// this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public double getMountedXOffset() {
		// should be overridden in extended class if not default;

		return calcTwoOffsetX(this.mountedOffsetZ, 90, this.mountedOffsetX);
		// return calcOffsetX(mountedOffsetX);
	}

	@Override
	public double getMountedZOffset() {
		// should be overridden in extended class if not default;

		return calcTwoOffsetZ(this.mountedOffsetZ, 90, this.mountedOffsetX);
		// return calcOffsetX(mountedOffsetX);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {
			if (this.isPlayerPushingSprintButton) {
				// player trying
				for (int j = 0; j < SIZE; j++) {

					if (this.inventory.getStackInSlot(j) != null && this.inventory.getStackInSlot(j).getItem() == ModItems.rawasphalt && this.inventory.getStackInSlot(j).getCount() > 0) {
						int angle;
						for (int i = -2; i < 3; i++) {
							if (i == 0) {
								angle = 0;
							} else {
								angle = 90;
							}
							BlockPos bp;
							if (this.inventory.getStackInSlot(j) != null && this.inventory.getStackInSlot(j).getItem() == ModItems.rawasphalt && this.inventory.getStackInSlot(j).getCount() > 0) {
								bp = new BlockPos(posX + calcTwoOffsetX(-3.5, angle, i), posY, posZ + calcTwoOffsetZ(-3.5, angle, i));
								if (world.isAirBlock(bp)) {
									world.setBlockState(bp, ModBlocks.machineasphalt.getDefaultState());

									this.inventory.extractItem(j, 1, false);
								}
							}
						}
						return;

					}
				}
			}
		}
	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	private void collidedEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof ItemEntity) {
					ItemStack is = ((ItemEntity) entity).getItem().copy();
					is.setItemDamage(((ItemEntity) entity).getItem().getItemDamage());
					if (!entity.isDead) {
						if (is.getCount() > 0) {
							ItemStack is1 = addToinventory(is);

							if (is1 != null && is1.getCount() != 0) {
								((ItemEntity) entity).setItem(is1);
							} else {
								entity.remove();
							}
						}
					}
				}
			}
		}
	}

}
