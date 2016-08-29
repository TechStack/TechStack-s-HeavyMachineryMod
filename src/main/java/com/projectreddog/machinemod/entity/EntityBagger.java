package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBagger extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	int breakLocation = 14;

	public EntityBagger(World world) {
		super(world);

		setSize(13f, 7f);
		inventory = new ItemStack[54];

		this.mountedOffsetY = .40D;
		this.mountedOffsetX = 2.25D;
		this.mountedOffsetZ = 5.D;
		this.maxAngle = 24;
		this.minAngle = 0;
		this.droppedItem = ModItems.bagger;
		this.shouldSendClientInvetoryUpdates = true;
		this.ignoreFrustumCheck = true;
		this.maxFuelLevel = 4000;

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
		if (!worldObj.isRemote) {
			if (this.getControllingPassenger() != null && currentFuelLevel > 0) {
				this.Attribute2++;
				currentFuelLevel--;
			}
			if (this.Attribute2 > 360) {
				this.Attribute2 = 0;
			}

			int bucketOffsetY = 0;
			int hOffsetDuetoYoffset = 0;

			if (this.Attribute1 > 4) {
				bucketOffsetY--;
			}
			if (this.Attribute1 > 9) {
				bucketOffsetY--;
			}
			if (this.Attribute1 > 14) {
				bucketOffsetY--;
			}

			if (this.Attribute1 > 19) {
				bucketOffsetY--;
				hOffsetDuetoYoffset--;
			}
			if (this.Attribute1 > 24) {
				bucketOffsetY--;

			}

			// 6 th up for 5 more up
			// 10 in

			// bucket Down
			// break blocks first
			BlockPos bp;

			for (int h = 0; h < 9; h++) {
				for (int v = 0; v < 9; v++) {

					if (((h == 0 && (v == 0 || v == 1 || v == 7 || v == 8))) || (h == 1 && (v == 0 || v == 8)) || (h == 7 && (v == 0 || v == 8)) || ((h == 8 && (v == 0 || v == 1 || v == 7 || v == 8)))) {

					} else {

						bp = new BlockPos(posX + calcTwoOffsetX(10 + h + hOffsetDuetoYoffset, 0, 0), posY + bucketOffsetY + v + 3, posZ + calcTwoOffsetZ(10 + h + hOffsetDuetoYoffset, 0, 0));

						if (!(worldObj.getBlockState(bp).getBlock().isAir(worldObj, bp)) && !(worldObj.getBlockState(bp).getBlock() == Blocks.bedrock) && !(worldObj.getBlockState(bp).getBlock().getMaterial() == Material.water) && !(worldObj.getBlockState(bp).getBlock().getMaterial() == Material.lava) && !(worldObj.getBlockState(bp).getBlock() == Blocks.obsidian)) {

							// worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
							// worldObj.setBlockToAir(bp);
							BlockUtil.BreakBlock(worldObj, bp, this.getControllingPassenger());

						}

						AxisAlignedBB bucketboundingBox = new AxisAlignedBB(bp.getX(), bp.getY(), bp.getZ(), bp.getX() + 1, bp.getY() + 1, bp.getZ() + 1);

						List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
						collidedEntitiesInList(list);

						// if (this.Attribute1 == this.getMinAngle()) {
						// // bucket up
						// // Drop blocks
						// // TODO needs something to pace it a bit more now it drops
						// // everything way to fast.
						// for (int i = 0; i < this.getSizeInventory(); i++) {
						// ItemStack item = this.getStackInSlot(i);
						//
						// if (item != null && item.stackSize > 0) {
						// ;
						//
						// EntityItem entityItem = new EntityItem(worldObj, posX + calcOffsetX(3.5), posY + 4, posZ + calcOffsetZ(3.5), item);
						//
						// if (item.hasTagCompound()) {
						// entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
						// }
						//
						// float factor = 0.05F;
						// // entityItem.motionX = rand.nextGaussian() * factor;
						// entityItem.motionY = 0;
						// // entityItem.motionZ = rand.nextGaussian() * factor;
						// entityItem.forceSpawn = true;
						// worldObj.spawnEntityInWorld(entityItem);
						// // item.stackSize = 0;
						//
						// this.setInventorySlotContents(i, null);
						// }
						// }
						//
						// }

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
