package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityLoader extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityLoader(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		SIZE = 9;
		// inventory = new ItemStack[9];
		inventory = new ItemStackHandler(SIZE);

		this.mountedOffsetY = 1D;
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
				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					bp = new BlockPos(posX + calcTwoOffsetX(5, angle, i), posY + bucketOffsetY, posZ + calcTwoOffsetZ(5, angle, i));
					if (world.getBlockState(bp).getBlock() == Blocks.SNOW_LAYER || world.getBlockState(bp).getBlock() == Blocks.SNOW || world.getBlockState(bp).getBlock() == Blocks.DIRT || world.getBlockState(bp).getBlock() == Blocks.SAND || world.getBlockState(bp).getBlock() == Blocks.GRAVEL || world.getBlockState(bp).getBlock() == Blocks.GRASS || world.getBlockState(bp).getBlock() == Blocks.CLAY
							|| world.getBlockState(bp).getBlock() == Blocks.NETHERRACK || world.getBlockState(bp).getBlock() == Blocks.MYCELIUM || world.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedgranite || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblasteddiorite
							|| this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedandesite || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedgold || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastediron || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedcoal
							|| this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedlapis || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblasteddiamond || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedredstone || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedemerald || world.getBlockState(bp).getBlock() == Blocks.SOUL_SAND
							|| world.getBlockState(bp).getBlock() == Blocks.TALLGRASS) {
						BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

					}

				}

				AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(5, 90, -2) + posX - .5d, posY + bucketOffsetY, calcTwoOffsetZ(5, 90, -2) + posZ - .5d, calcTwoOffsetX(5, 90, 2) + posX + .5d, posY + 1, calcTwoOffsetZ(5, 90, 2) + posZ + .5d);

				List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);
			}
			if (this.Attribute1 == this.getMinAngle()) {
				// bucket up
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < SIZE; i++) {
					ItemStack item = inventory.getStackInSlot(i);

					if (!item.isEmpty() && item.getCount() > 0) {

						ItemEntity ItemEntity = new ItemEntity(world, posX + calcOffsetX(5), posY + 4, posZ + calcOffsetZ(5), item);

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
									entity.setDead();
								}
							}
						}
					}
				}
			}
		}
	}

}
