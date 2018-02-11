package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityRoadRoller extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityRoadRoller(World world) {
		super(world);

		setSize(4.5f, 5f);
		// inventory = new ItemStack[9];

		this.mountedOffsetY = .61D;
		// this.mountedOffsetX = -0.3D;
		// this.mountedOffsetZ = -.3D;
		//
		this.mountedOffsetX = .55D;
		this.mountedOffsetZ = .55D;
		this.maxAngle = 15;
		this.minAngle = -90;
		this.droppedItem = ModItems.roadroller;
		// this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {
			if (this.isPlayerPushingSprintButton) {
				// player trying

				int angle;
				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					bp = new BlockPos(posX + calcTwoOffsetX(3.5, angle, i), posY - 1, posZ + calcTwoOffsetZ(3.5, angle, i));
					if (world.getBlockState(bp).getBlock() == ModBlocks.machineasphalt) {
						world.setBlockState(bp, ModBlocks.machinecompressedasphalt.getDefaultState());

					}
				}
				return;

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
				if (entity instanceof EntityItem) {
					ItemStack is = ((EntityItem) entity).getItem().copy();
					is.setItemDamage(((EntityItem) entity).getItem().getItemDamage());
					if (!entity.isDead) {
						if (is.getCount() > 0) {
							ItemStack is1 = addToinventory(is);

							if (is1 != null && is1.getCount() != 0) {
								((EntityItem) entity).setItem(is1);
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
