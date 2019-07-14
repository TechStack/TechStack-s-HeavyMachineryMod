package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityContinuousMiner extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private int direction = 0;

	public EntityContinuousMiner(World world) {
		super(world);
		setSize(3.5f, 4f);
		SIZE = 54;
		// inventory = new ItemStack[9];
		inventory = new ItemStackHandler(SIZE);

		this.mountedOffsetY = .5D;
		this.mountedOffsetX = -1.25D;
		this.mountedOffsetZ = -0.2D;
		this.maxAngle = 15;
		this.minAngle = -20;
		this.droppedItem = ModItems.continuousminer;
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
		if (!world.isRemote) {

			int bucketOffsetY = 0;

			if (this.Attribute1 < -13) {
				bucketOffsetY = 3;
			} else if (this.Attribute1 < -1) {
				bucketOffsetY = 2;

			} else if (this.Attribute1 < 11) {
				bucketOffsetY = 1;

			}

			// bucket Down
			// break blocks first
			int angle;
			if (isBeingRidden()) {
				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					for (int j = -1; j < 2; j++) {
						for (int k = -1; k < 2; k++) {
							bp = new BlockPos(posX + calcTwoOffsetX(7.5 + k, angle, i), posY + bucketOffsetY + j, posZ + calcTwoOffsetZ(7.5 + k, angle, i));

							if (!world.isAirBlock(bp) && world.getBlockState(bp).getBlock() != Blocks.BEDROCK && world.getBlockState(bp).getMaterial() != Material.WATER && world.getBlockState(bp).getMaterial() != Material.LAVA && world.getBlockState(bp).getBlock() != ModBlocks.machinebleakportal && world.getBlockState(bp).getBlock() != ModBlocks.machinebleakportalframe) {
								BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());
								i = 3;
								j = 2;
								k = 2;
							}
						}
					}
				}
			}

			AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(5, 90, -2) + posX - .5d, posY, calcTwoOffsetZ(5, 90, -2) + posZ - .5d, calcTwoOffsetX(5, 90, 2) + posX + .5d, posY + 1, calcTwoOffsetZ(5, 90, 2) + posZ + .5d);
			List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
			collidedEntitiesInList(list);

			if (this.isPlayerPushingUnloadButton) {
				// bucket up
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < SIZE; i++) {
					ItemStack item = inventory.getStackInSlot(i);

					if (!item.isEmpty() && item.getCount() > 0) {

						ItemEntity ItemEntity = new ItemEntity(world, posX + calcTwoOffsetX(-7.5, 90, 0), posY + 4, posZ + calcTwoOffsetZ(-7.5, 90, 0), item);

						if (item.hasTagCompound()) {
							ItemEntity.getItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
						}

						float factor = 0.05F;
						// ItemEntity.motionX = rand.nextGaussian() * factor;
						ItemEntity.motionY = 0;
						// ItemEntity.motionZ = rand.nextGaussian() * factor;
						ItemEntity.forceSpawn = true;
						world.spawnEntity(ItemEntity);
						// item.stackSize = 0;
						inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false);
						// inventory.insertItem(i, ItemStack.EMPTY, false);
					}
				}

			}

		}

	}

	@Override
	public void doParticleEffects() {

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
									entity.remove();
								}
							}
						}
					}
				}
			}
		}
	}

}
