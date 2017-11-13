package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityLoader extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityLoader(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		inventory = new ItemStack[9];

		this.mountedOffsetY = 0.6D;
		this.mountedOffsetX = 0.4D;
		this.mountedOffsetZ = 0.4D;
		this.maxAngle = 15;
		this.minAngle = -90;
		this.droppedItem = ModItems.loader;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {

			int bucketOffsetY = 0;

			if (this.Attribute1 > 7) {
				bucketOffsetY = -1;
			} else if (this.Attribute1 < -7) {
				bucketOffsetY = 1;

			}
			if (this.Attribute1 > -20) {
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
					if (world.getBlockState(bp).getBlock() == Blocks.SNOW_LAYER || world.getBlockState(bp).getBlock() == Blocks.SNOW || world.getBlockState(bp).getBlock() == Blocks.DIRT || world.getBlockState(bp).getBlock() == Blocks.SAND || world.getBlockState(bp).getBlock() == Blocks.GRAVEL || world.getBlockState(bp).getBlock() == Blocks.GRASS || world.getBlockState(bp).getBlock() == Blocks.CLAY
							|| world.getBlockState(bp).getBlock() == Blocks.NETHERRACK || world.getBlockState(bp).getBlock() == Blocks.MYCELIUM || world.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || world.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone2 || world.getBlockState(bp).getBlock() == Blocks.SOUL_SAND
							|| world.getBlockState(bp).getBlock() == Blocks.TALLGRASS) {
						BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

					}

				}

				AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY + bucketOffsetY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

				List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);
			}
			if (this.Attribute1 == this.getMinAngle()) {
				// bucket up
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < this.getSizeInventory(); i++) {
					ItemStack item = this.getStackInSlot(i);

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
