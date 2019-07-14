package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityLaserMiner extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityLaserMiner(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		SIZE = 54;
		// inventory = new ItemStack[9];
		inventory = new ItemStackHandler(SIZE);

		this.mountedOffsetY = .8D;
		this.mountedOffsetX = -1.25D;
		this.mountedOffsetZ = -0.75D;
		this.maxAngle = 20;
		this.minAngle = -20;
		this.droppedItem = ModItems.laserminer;
		this.shouldSendClientInvetoryUpdates = true;
		this.ignoreFrustumCheck = true;

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
	// 20 + 3
	// 10 + 2
	// 0 --- +1
	// -10 --- Level of machine
	// -20 -- level down 1

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {

			int bucketOffsetY = 0;

			if (this.Attribute1 < -15) {
				bucketOffsetY = 3;
			} else if (this.Attribute1 < -5) {
				bucketOffsetY = 2;

			} else if (this.Attribute1 < 5) {
				bucketOffsetY = 1;

			} else if (this.Attribute1 < 15) {
				bucketOffsetY = 0;

			} else if (this.Attribute1 < 20) {
				bucketOffsetY = -1;

			}

			int angle;
			if (isBeingRidden() && this.currentFuelLevel > 0) {
				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					for (int k = -1; k < 11; k++) {
						bp = new BlockPos(posX + calcTwoOffsetX(7.5 + k, angle, i), posY + bucketOffsetY, posZ + calcTwoOffsetZ(7.5 + k, angle, i));
						if (!world.isAirBlock(bp) && world.getBlockState(bp).getBlock() != Blocks.BEDROCK && world.getBlockState(bp).getMaterial() != Material.WATER && world.getBlockState(bp).getMaterial() != Material.LAVA && world.getBlockState(bp).getBlock() != ModBlocks.machinebleakportal && world.getBlockState(bp).getBlock() != ModBlocks.machinebleakportalframe) {
							BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());
							// i = 3;

							k = 5;

						}
					}
				}
			}

			AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(5, 90, -2) + posX - .5d, posY, calcTwoOffsetZ(5, 90, -2) + posZ - .5d, calcTwoOffsetX(5, 90, 2) + posX + .5d, posY + 1, calcTwoOffsetZ(5, 90, 2) + posZ + .5d);
			List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
			collidedEntitiesInList(list);

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
					if (!is.isEmpty() && is != null) {
						is.setItemDamage(((ItemEntity) entity).getItem().getItemDamage());
						if (!entity.isDead) {
							if (is.getCount() > 0) {
								ItemStack is1 = addToinventory(is);

								if (!is1.isEmpty() && is1.getCount() != 0) {
									((ItemEntity) entity).setItem(is1);
								} else {
									entity.setDead();
								}
							}
						}
					}
				}
			}
		}
	}

}
