package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityRoadRoller extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityRoadRoller(World world) {
		super(world);

		setSize(5.5f, 5f);
		// inventory = new ItemStack[9];

		this.mountedOffsetY = .6D;
		// this.mountedOffsetX = -0.3D;
		// this.mountedOffsetZ = -.3D;
		//
		this.mountedOffsetX = 1.0D;
		this.mountedOffsetZ = 1.0D;
		this.maxAngle = 15;
		this.minAngle = -90;
		this.droppedItem = ModItems.roadroller;
		// this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
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
					if (worldObj.getBlockState(bp).getBlock() == ModBlocks.machineasphalt) {
						worldObj.setBlockState(bp, ModBlocks.machinecompressedasphalt.getDefaultState());

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
					ItemStack is = ((EntityItem) entity).getEntityItem().copy();
					is.setItemDamage(((EntityItem) entity).getEntityItem().getItemDamage());
					if (!entity.isDead) {
						if (is.stackSize > 0) {
							ItemStack is1 = addToinventory(is);

							if (is1 != null && is1.stackSize != 0) {
								((EntityItem) entity).setEntityItemStack(is1);
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
