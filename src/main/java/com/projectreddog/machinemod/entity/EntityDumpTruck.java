package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityDumpTruck extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityDumpTruck(World world) {
		super(world);

		setSize(3, 2);
		inventory = new ItemStack[54];
		this.mountedOffsetY = 0.35D;
		this.mountedOffsetX = 1.5D;
		this.mountedOffsetZ = 1.5D;
		this.maxAngle = 0;
		this.minAngle = -60;
		this.droppedItem = ModItems.dumptruck;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {
			List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
			collidedEntitiesInList(list);
			if (this.Attribute1 == getMinAngle()) {
				// need
				for (int i = 0; i < this.getSizeInventory(); i++) {
					ItemStack item = this.getStackInSlot(i);

					if (item != null && item.getCount() > 0) {
						;

						EntityItem entityItem = new EntityItem(world, posX + calcOffsetX(-3.5), posY - .1f, posZ + calcOffsetZ(-3.5), item);

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
						this.setInventorySlotContents(i, null);
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
