package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityUnderGroundDumpTruck extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityUnderGroundDumpTruck(World world) {
		super(world);

		setSize(3.5f, 2);
		SIZE = 54;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[54];
		this.mountedOffsetY = 1.50D;
		this.mountedOffsetX = 2.5D;
		this.mountedOffsetZ = 2.5D;
		this.maxAngle = 0;
		this.minAngle = -60;
		this.droppedItem = ModItems.undergrounddumptruck;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		float forwardOffset = -5f;
		if (!world.isRemote) {

			AxisAlignedBB bedboundingBox = new AxisAlignedBB(calcTwoOffsetX(forwardOffset + 5, 90, -2) + posX - .5d, (double) posY, calcTwoOffsetZ(forwardOffset + 5, 90, -2) + posZ - .5d, calcTwoOffsetX(forwardOffset, 90, 2) + posX + .5d, posY + 1, calcTwoOffsetZ(forwardOffset, 90, 2) + posZ + .5d);
			List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bedboundingBox);
			collidedEntitiesInList(list);
			if (this.Attribute1 == getMinAngle()) {
				// need
				for (int i = 0; i < SIZE; i++) {
					ItemStack item = this.inventory.getStackInSlot(i);

					if (item != null && item.getCount() > 0) {
						;

						EntityItem entityItem = new EntityItem(world, posX + calcOffsetX(forwardOffset + -1.5f), posY - .1f, posZ + calcOffsetZ(forwardOffset + -1.5f), item);

						if (item.hasTagCompound()) {
							entityItem.getItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
						}

						float factor = 0.05F;
						// entityItem.motionX = rand.nextGaussian() * factor;
						entityItem.motionY = 0;
						// entityItem.motionZ = rand.nextGaussian() * factor;
						entityItem.forceSpawn = true;
						world.spawnEntity(entityItem);
						// item.stackSize = 0;
						inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false);
						// this.inventory.insertItem(i, ItemStack.EMPTY, false);
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
