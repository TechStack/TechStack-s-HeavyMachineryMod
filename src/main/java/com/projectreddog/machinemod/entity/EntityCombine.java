package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityCombine extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityCombine(World world) {
		super(world);

		setSize(3.75f, 4f);
		SIZE = 54;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];

		this.mountedOffsetY = .7D;
		this.mountedOffsetX = 2.8D;
		this.mountedOffsetZ = 2.8D;
		this.maxAngle = 0;
		this.minAngle = 0;
		this.droppedItem = ModItems.combine;
		this.shouldSendClientInvetoryUpdates = true;
		this.ignoreFrustumCheck = true;

	}

	@Override
	public void onUpdate() {
		float forwardOffset = 4.5f;
		super.onUpdate();
		if (!world.isRemote) {
			// bucket Down
			// break blocks first
			int angle;
			if (this.getControllingPassenger() != null) {
				this.Attribute2 = Attribute2 + 5;
			}
			if (this.isPlayerPushingSprintButton) {
				for (int j = 0; j < 2; j++) {
					for (int i = -4; i < 5; i++) {
						if (i == 0) {
							angle = 0;
						} else {
							angle = 90;
						}
						BlockPos bp;
						bp = new BlockPos(posX + calcTwoOffsetX(forwardOffset, angle, i), posY + j, posZ + calcTwoOffsetZ(forwardOffset, angle, i));
						if (world.getBlockState(bp).getBlock() instanceof IGrowable) {

							IGrowable iGrowable = (IGrowable) world.getBlockState(bp).getBlock();

							if (!iGrowable.canGrow(world, bp, world.getBlockState(bp), world.isRemote)) {

								BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

							}
						}

					}
				}

				AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(forwardOffset - 1, 90, -5) + posX - 1d, posY, calcTwoOffsetZ(forwardOffset - 1, 90, -5) + posZ - 1d, calcTwoOffsetX(forwardOffset, 90, 5) + posX + 1d, posY + 1, calcTwoOffsetZ(forwardOffset, 90, 5) + posZ + 1d);

				List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);
			}
			if (this.isPlayerPushingJumpButton) {
				// bucket up
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < SIZE; i++) {
					ItemStack item = this.inventory.getStackInSlot(i);

					if (item != null && item.getCount() > 0) {
						;

						ItemEntity ItemEntity = new ItemEntity(world, posX + calcOffsetX(forwardOffset), posY + 4, posZ + calcOffsetZ(forwardOffset), item);

						if (item.hasTagCompound()) {
							ItemEntity.getItem().setTagCompound((CompoundNBT) item.getTagCompound().copy());
						}

						float factor = 0.05F;
						// ItemEntity.motionX = rand.nextGaussian() * factor;
						ItemEntity.motionY = 0;
						// ItemEntity.motionZ = rand.nextGaussian() * factor;
						ItemEntity.forceSpawn = true;
						world.spawnEntity(ItemEntity);
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
				if (entity instanceof ItemEntity) {
					ItemStack is = ((ItemEntity) entity).getItem().copy();
					is.setItemDamage(((ItemEntity) entity).getItem().getItemDamage());
					if (!entity.isDead) {
						if (is.getCount() > 0) {
							ItemStack is1 = addToinventory(is);

							if (is1 != null && is1.getCount() != 0) {
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
