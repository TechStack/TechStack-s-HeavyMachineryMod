package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityBagger extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityBagger(World world) {
		super(world);

		setSize(12f, 7f);
		inventory = new ItemStack[9];

		this.mountedOffsetY = .65D;
		this.mountedOffsetX = 2D;
		this.mountedOffsetZ = 8.5D;
		this.maxAngle = 361;
		this.minAngle = -1;
		this.droppedItem = ModItems.bagger;
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

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (this.isPlayerPushingJumpButton) {
				this.Attribute2++;
			}
			if (this.Attribute2 > 360) {
				this.Attribute2 = 0;
			}

			if (this.Attribute1 == this.maxAngle) {
				this.Attribute1 = this.minAngle;
			} else if (this.Attribute1 == this.minAngle) {
				this.Attribute1 = this.maxAngle;

			}
			int bucketOffsetY = 0;

			// bucket Down
			// break blocks first
			int angle;
			for (int i = -1; i < 2; i++) {
				if (i == 0) {
					angle = 0;
				} else {
					angle = 90;
				}
				BlockPos bp;
				bp = new BlockPos(posX + calcTwoOffsetX(3.5, angle, i), posY + bucketOffsetY, posZ + calcTwoOffsetZ(3.5, angle, i));
				if (worldObj.getBlockState(bp).getBlock() == Blocks.snow_layer || worldObj.getBlockState(bp).getBlock() == Blocks.snow || worldObj.getBlockState(bp).getBlock() == Blocks.dirt || worldObj.getBlockState(bp).getBlock() == Blocks.sand || worldObj.getBlockState(bp).getBlock() == Blocks.gravel || worldObj.getBlockState(bp).getBlock() == Blocks.grass
						|| worldObj.getBlockState(bp).getBlock() == Blocks.clay || worldObj.getBlockState(bp).getBlock() == Blocks.netherrack || worldObj.getBlockState(bp).getBlock() == Blocks.mycelium || worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone2
						|| worldObj.getBlockState(bp).getBlock() == Blocks.soul_sand || worldObj.getBlockState(bp).getBlock() == Blocks.tallgrass) {
					worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
					worldObj.setBlockToAir(bp);
				}

			}

			AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY + bucketOffsetY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
			collidedEntitiesInList(list);

			if (this.Attribute1 == this.getMinAngle()) {
				// bucket up
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < this.getSizeInventory(); i++) {
					ItemStack item = this.getStackInSlot(i);

					if (item != null && item.stackSize > 0) {
						;

						EntityItem entityItem = new EntityItem(worldObj, posX + calcOffsetX(3.5), posY + 4, posZ + calcOffsetZ(3.5), item);

						if (item.hasTagCompound()) {
							entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
						}

						float factor = 0.05F;
						// entityItem.motionX = rand.nextGaussian() * factor;
						entityItem.motionY = 0;
						// entityItem.motionZ = rand.nextGaussian() * factor;
						entityItem.forceSpawn = true;
						worldObj.spawnEntityInWorld(entityItem);
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
