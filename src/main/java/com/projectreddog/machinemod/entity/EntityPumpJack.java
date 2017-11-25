package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityPumpJack extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	int breakLocation = 14;

	public EntityPumpJack(World world) {
		super(world);

		setSize(3f, 3f);
		SIZE = 54;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[54];

		this.mountedOffsetY = .40D;
		this.mountedOffsetX = 2.25D;
		this.mountedOffsetZ = 5.D;
		this.maxAngle = 24;
		this.minAngle = 0;
		this.droppedItem = ModItems.oilrig;
		this.shouldSendClientInvetoryUpdates = true;
		this.ignoreFrustumCheck = true;
		// this.maxSpeed = 0;
		// this.maxFuelLevel = 4000;

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
	public double getMaxVelocity() {
		// created as method so extending class can easily override to allow for
		// different speeds per machine
		return .02d;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {

			// TODO add oil rig logic

			this.Attribute1 = this.Attribute1 + 1;
			if (this.Attribute1 > 360) {
				this.Attribute1 = 0;
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
