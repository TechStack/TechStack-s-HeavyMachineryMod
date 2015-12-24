package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.item.ItemTransportable;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySemiTractor extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private int carriedMachinesFuelStorage;
	private float bedRampBackOffset = -5f;

	public EntitySemiTractor(World world) {
		super(world);

		setSize(3, 2);
		inventory = new ItemStack[9];
		this.mountedOffsetY = 0.35D;
		this.mountedOffsetX = 0.0D;
		this.mountedOffsetZ = 0.0D;
		this.maxAngle = 10;
		this.minAngle = 0;
		this.droppedItem = ModItems.semitractor;
		this.shouldSendClientInvetoryUpdates = true;
		this.maxSpeed = 0.4d;
	}

	protected void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		carriedMachinesFuelStorage = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CMFULE");

	}

	protected void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CMFULE", carriedMachinesFuelStorage);

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {

			AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(bedRampBackOffset, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(bedRampBackOffset, 90, -1) + posZ - .5d, calcTwoOffsetX(bedRampBackOffset, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(bedRampBackOffset, 90, 1) + posZ + .5d);

			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
			collidedEntitiesInList(list);
			if (this.Attribute1 > 9) {

				if (getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemTransportable) {

					EntityMachineModRideable eMMR = ((ItemTransportable) getStackInSlot(0).getItem()).getEntityToSpawn(worldObj);

					eMMR.setPosition(calcTwoOffsetX(bedRampBackOffset + -3, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(bedRampBackOffset + -3, 90, -1) + posZ - .5d);
					eMMR.prevPosX = calcTwoOffsetX(bedRampBackOffset + -1, 90, -1) + posX - .5d;
					eMMR.prevPosY = posY + 1.0d;
					eMMR.prevPosZ = calcTwoOffsetZ(bedRampBackOffset + -1, 90, -1) + posZ - .5d;
					eMMR.currentFuelLevel = carriedMachinesFuelStorage;
					worldObj.spawnEntityInWorld(eMMR);
					carriedMachinesFuelStorage = 0;
					decrStackSize(0, 1);
				}
			}
		}
	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	private void collidedEntitiesInList(List par1List) {
		if (getStackInSlot(0) == null) {
			for (int i = 0; i < par1List.size(); ++i) {
				Entity entity = (Entity) par1List.get(i);
				if (entity != null) {
					if (entity instanceof EntityMachineModRideable && ((EntityMachineModRideable) entity).droppedItem instanceof ItemTransportable) {
						ItemStack is = new ItemStack(((EntityMachineModRideable) entity).getItemToBeDropped());
						if (!entity.isDead) {
							if (is.stackSize > 0) {
								ItemStack is1 = addToinventory(is);
								carriedMachinesFuelStorage = ((EntityMachineModRideable) entity).currentFuelLevel;
								entity.setDead();
								return; // only add 1 item
								// TODO way to store the contents of the machine's FUel level

							}
						}
					}
				}
			}
		}
	}
}
