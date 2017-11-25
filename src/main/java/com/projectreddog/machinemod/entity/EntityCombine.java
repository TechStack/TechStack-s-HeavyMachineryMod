package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityCombine extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityCombine(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];

		this.mountedOffsetY = 0.6D;
		this.mountedOffsetX = 0.4D;
		this.mountedOffsetZ = 0.4D;
		this.maxAngle = 0;
		this.minAngle = 0;
		this.droppedItem = ModItems.combine;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {
			// bucket Down
			// break blocks first
			int angle;
			if (this.getControllingPassenger() != null) {
				this.Attribute2++;
			}
			if (this.isPlayerPushingSprintButton) {
				for (int j = 0; j < 2; j++) {
					for (int i = -2; i < 3; i++) {
						if (i == 0) {
							angle = 0;
						} else {
							angle = 90;
						}
						BlockPos bp;
						bp = new BlockPos(posX + calcTwoOffsetX(3.5, angle, i), posY + j, posZ + calcTwoOffsetZ(3.5, angle, i));
						if (world.getBlockState(bp).getBlock() instanceof IGrowable) {

							IGrowable iGrowable = (IGrowable) world.getBlockState(bp).getBlock();

							if (!iGrowable.canGrow(world, bp, world.getBlockState(bp), world.isRemote)) {

								BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

							}
						}

					}
				}

				AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

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

						EntityItem entityItem = new EntityItem(world, posX + calcOffsetX(3.5), posY + 4, posZ + calcOffsetZ(3.5), item);

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
