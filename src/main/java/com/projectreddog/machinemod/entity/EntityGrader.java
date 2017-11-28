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
import net.minecraftforge.items.ItemStackHandler;

public class EntityGrader extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityGrader(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];

		this.mountedOffsetY = 1D;
		this.mountedOffsetX = -0.9D;
		this.mountedOffsetZ = -0.9D;
		this.maxAngle = 15;
		this.minAngle = -90;
		this.droppedItem = ModItems.grader;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {

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
					if (world.getBlockState(bp).getBlock() == Blocks.SNOW_LAYER || world.getBlockState(bp).getBlock() == Blocks.SNOW || world.getBlockState(bp).getBlock() == Blocks.DIRT || world.getBlockState(bp).getBlock() == Blocks.SAND || world.getBlockState(bp).getBlock() == Blocks.GRAVEL || world.getBlockState(bp).getBlock() == Blocks.GRASS || world.getBlockState(bp).getBlock() == Blocks.CLAY
							|| world.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedgranite || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblasteddiorite || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedandesite
							|| this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedgold || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastediron || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedcoal || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedlapis
							|| this.world.getBlockState(bp).getBlock() == ModBlocks.machineblasteddiamond || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedredstone || this.world.getBlockState(bp).getBlock() == ModBlocks.machineblastedemerald || world.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone2 || world.getBlockState(bp).getBlock() == Blocks.SOUL_SAND
							|| world.getBlockState(bp).getBlock() == Blocks.TALLGRASS) {
						BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

					}

				}

				AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

				List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);

				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					bp = new BlockPos(posX + calcTwoOffsetX(2, angle, i), posY, posZ + calcTwoOffsetZ(2, angle, i));
					if (world.getBlockState(bp).getBlock() == Blocks.SNOW_LAYER || world.getBlockState(bp).getBlock() == Blocks.SNOW || world.getBlockState(bp).getBlock() == Blocks.DIRT || world.getBlockState(bp).getBlock() == Blocks.SAND || world.getBlockState(bp).getBlock() == Blocks.GRAVEL || world.getBlockState(bp).getBlock() == Blocks.GRASS || world.getBlockState(bp).getBlock() == Blocks.CLAY
							|| world.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || world.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone2 || world.getBlockState(bp).getBlock() == Blocks.SOUL_SAND || world.getBlockState(bp).getBlock() == Blocks.TALLGRASS) {
						BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

					}

				}

				bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

				list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);

			}
			if (this.isPlayerPushingSprintButton) {

				// Blade Down
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < SIZE; i++) {
					ItemStack item = this.inventory.getStackInSlot(i);
					int angle;
					if (item != null && item.getCount() > 0) {
						if (item.getItem() instanceof ItemBlock) {
							ItemBlock ib = (ItemBlock) item.getItem();
							if (ib.getBlock() == Blocks.DIRT) {

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

									if (world.getBlockState(bp).getBlock().isAir(world.getBlockState(bp), world, bp) || world.getBlockState(bp).getBlock() == Blocks.WATER || world.getBlockState(bp).getBlock() == Blocks.FLOWING_WATER || world.getBlockState(bp).getBlock() == Blocks.TALLGRASS || world.getBlockState(bp).getBlock() == Blocks.VINE || world.getBlockState(bp).getBlock() == Blocks.REEDS
											|| world.getBlockState(bp).getBlock() == Blocks.RED_FLOWER || world.getBlockState(bp).getBlock() == Blocks.YELLOW_FLOWER || world.getBlockState(bp).getBlock() == Blocks.WATERLILY || world.getBlockState(bp).getBlock() == Blocks.BROWN_MUSHROOM_BLOCK || world.getBlockState(bp).getBlock() == Blocks.RED_MUSHROOM_BLOCK) {
										bp = GetLowestBlockPos(bp);
										if (world.setBlockState(bp, ib.getBlock().getDefaultState())) {
											this.inventory.extractItem(i, 1, false);
										}
										return;

									}
									bp2 = new BlockPos(posX + calcTwoOffsetX(2, angle, j), posY - 1, posZ + calcTwoOffsetZ(2, angle, j));
									if (world.getBlockState(bp2).getBlock().isAir(world.getBlockState(bp2), world, bp2) || world.getBlockState(bp2).getBlock() == Blocks.WATER || world.getBlockState(bp2).getBlock() == Blocks.FLOWING_WATER || world.getBlockState(bp).getBlock() == Blocks.TALLGRASS || world.getBlockState(bp).getBlock() == Blocks.VINE
											|| world.getBlockState(bp).getBlock() == Blocks.REEDS || world.getBlockState(bp).getBlock() == Blocks.RED_FLOWER || world.getBlockState(bp).getBlock() == Blocks.YELLOW_FLOWER || world.getBlockState(bp).getBlock() == Blocks.WATERLILY || world.getBlockState(bp).getBlock() == Blocks.BROWN_MUSHROOM_BLOCK
											|| world.getBlockState(bp).getBlock() == Blocks.RED_MUSHROOM_BLOCK) {
										bp2 = GetLowestBlockPos(bp2);
										if (world.setBlockState(bp2, ib.getBlock().getDefaultState())) {
											this.inventory.extractItem(i, 1, false);
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
			if (world.getBlockState(bp.offset(EnumFacing.DOWN)).getBlock().isAir(world.getBlockState(bp.offset(EnumFacing.DOWN)), world, bp.offset(EnumFacing.DOWN)) || world.getBlockState(bp.offset(EnumFacing.DOWN)).getBlock() == Blocks.WATER || world.getBlockState(bp.offset(EnumFacing.DOWN)).getBlock() == Blocks.FLOWING_WATER) {
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
