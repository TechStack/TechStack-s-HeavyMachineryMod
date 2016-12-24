package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityGrader extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityGrader(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		inventory = new ItemStack[9];

		this.mountedOffsetY = 0.6D;
		this.mountedOffsetX = 0.4D;
		this.mountedOffsetZ = 0.4D;
		this.maxAngle = 15;
		this.minAngle = -90;
		this.droppedItem = ModItems.grader;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {

			if (this.isPlayerPushingSprintButton) {
				// blade Down
				// break blocks first
				int angle;
				for (int i = -1; i < 2; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					bp = new BlockPos(posX + calcTwoOffsetX(5.5, angle, i), posY, posZ + calcTwoOffsetZ(5.5, angle, i));
					if (worldObj.getBlockState(bp).getBlock() == Blocks.SNOW_LAYER || worldObj.getBlockState(bp).getBlock() == Blocks.SNOW || worldObj.getBlockState(bp).getBlock() == Blocks.DIRT || worldObj.getBlockState(bp).getBlock() == Blocks.SAND || worldObj.getBlockState(bp).getBlock() == Blocks.GRAVEL || worldObj.getBlockState(bp).getBlock() == Blocks.GRASS
							|| worldObj.getBlockState(bp).getBlock() == Blocks.CLAY || worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone2 || worldObj.getBlockState(bp).getBlock() == Blocks.SOUL_SAND || worldObj.getBlockState(bp).getBlock() == Blocks.TALLGRASS) {
						BlockUtil.BreakBlock(worldObj, bp, this.getControllingPassenger());

					}

				}

				AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);

				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					bp = new BlockPos(posX + calcTwoOffsetX(2, angle, i), posY, posZ + calcTwoOffsetZ(2, angle, i));
					if (worldObj.getBlockState(bp).getBlock() == Blocks.SNOW_LAYER || worldObj.getBlockState(bp).getBlock() == Blocks.SNOW || worldObj.getBlockState(bp).getBlock() == Blocks.DIRT || worldObj.getBlockState(bp).getBlock() == Blocks.SAND || worldObj.getBlockState(bp).getBlock() == Blocks.GRAVEL || worldObj.getBlockState(bp).getBlock() == Blocks.GRASS
							|| worldObj.getBlockState(bp).getBlock() == Blocks.CLAY || worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone2 || worldObj.getBlockState(bp).getBlock() == Blocks.SOUL_SAND || worldObj.getBlockState(bp).getBlock() == Blocks.TALLGRASS) {
						BlockUtil.BreakBlock(worldObj, bp, this.getControllingPassenger());

					}

				}

				bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

				list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);

			}
			if (this.isPlayerPushingSprintButton) {

				// Blade Down
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < this.getSizeInventory(); i++) {
					ItemStack item = this.getStackInSlot(i);
					int angle;
					if (item != null && item.stackSize > 0) {
						if (item.getItem() instanceof ItemBlock) {
							ItemBlock ib = (ItemBlock) item.getItem();
							if (ib.block == Blocks.DIRT) {

								// its dirt

								for (int j = -2; j < 3; j++) {
									if (j == 0) {
										angle = 0;
									} else {
										angle = 90;
									}
									BlockPos bp;
									BlockPos bp2;

									bp = new BlockPos(posX + calcTwoOffsetX(5.5, angle, j), posY - 1, posZ + calcTwoOffsetZ(5.5, angle, j));

									if (worldObj.getBlockState(bp).getBlock().isAir(worldObj.getBlockState(bp), worldObj, bp) || worldObj.getBlockState(bp).getBlock() == Blocks.WATER || worldObj.getBlockState(bp).getBlock() == Blocks.FLOWING_WATER || worldObj.getBlockState(bp).getBlock() == Blocks.TALLGRASS || worldObj.getBlockState(bp).getBlock() == Blocks.VINE
											|| worldObj.getBlockState(bp).getBlock() == Blocks.REEDS || worldObj.getBlockState(bp).getBlock() == Blocks.RED_FLOWER || worldObj.getBlockState(bp).getBlock() == Blocks.YELLOW_FLOWER || worldObj.getBlockState(bp).getBlock() == Blocks.WATERLILY || worldObj.getBlockState(bp).getBlock() == Blocks.BROWN_MUSHROOM_BLOCK
											|| worldObj.getBlockState(bp).getBlock() == Blocks.RED_MUSHROOM_BLOCK) {
										bp = GetLowestBlockPos(bp);
										if (worldObj.setBlockState(bp, ib.getBlock().getDefaultState())) {
											this.decrStackSize(i, 1);
										}
										return;

									}
									bp2 = new BlockPos(posX + calcTwoOffsetX(2, angle, j), posY - 1, posZ + calcTwoOffsetZ(2, angle, j));
									if (worldObj.getBlockState(bp2).getBlock().isAir(worldObj.getBlockState(bp2), worldObj, bp2) || worldObj.getBlockState(bp2).getBlock() == Blocks.WATER || worldObj.getBlockState(bp2).getBlock() == Blocks.FLOWING_WATER || worldObj.getBlockState(bp).getBlock() == Blocks.TALLGRASS || worldObj.getBlockState(bp).getBlock() == Blocks.VINE
											|| worldObj.getBlockState(bp).getBlock() == Blocks.REEDS || worldObj.getBlockState(bp).getBlock() == Blocks.RED_FLOWER || worldObj.getBlockState(bp).getBlock() == Blocks.YELLOW_FLOWER || worldObj.getBlockState(bp).getBlock() == Blocks.WATERLILY || worldObj.getBlockState(bp).getBlock() == Blocks.BROWN_MUSHROOM_BLOCK
											|| worldObj.getBlockState(bp).getBlock() == Blocks.RED_MUSHROOM_BLOCK) {
										bp2 = GetLowestBlockPos(bp2);
										if (worldObj.setBlockState(bp2, ib.getBlock().getDefaultState())) {
											this.decrStackSize(i, 1);
										}
										return;
									}
								}
							}
						}
					}

				}
			}
		}

	}

	public BlockPos GetLowestBlockPos(BlockPos bp) {
		BlockPos previous = bp;
		if (bp.getY() == 1) {
			return previous;
		} else {
			if (worldObj.getBlockState(bp.offset(EnumFacing.DOWN)).getBlock().isAir(worldObj.getBlockState(bp.offset(EnumFacing.DOWN)), worldObj, bp.offset(EnumFacing.DOWN)) || worldObj.getBlockState(bp.offset(EnumFacing.DOWN)).getBlock() == Blocks.WATER || worldObj.getBlockState(bp.offset(EnumFacing.DOWN)).getBlock() == Blocks.FLOWING_WATER) {
				return GetLowestBlockPos(bp.offset(EnumFacing.DOWN));
			} else {
				return previous;
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
