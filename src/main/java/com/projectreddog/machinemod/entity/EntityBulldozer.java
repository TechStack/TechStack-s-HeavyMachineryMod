package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityBulldozer extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntityBulldozer(World world) {
		super(world);
		setSize(4.2F, 2.5F);
		inventory = new ItemStack[0];
		this.mountedOffsetY = .8D;
		this.mountedOffsetX = -1.5D;
		this.mountedOffsetZ = -1.5D;
		this.maxAngle = 15;
		this.minAngle = -15;
		this.droppedItem = ModItems.bulldozer;

	}

	public void doParticleEffects() {
		if (this.currentFuelLevel > 0 && this.getControllingPassenger() != null) {
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + calcTwoOffsetX(2.1, -90, .7), this.posY + 3.5, this.posZ + calcTwoOffsetZ(2.1, -90, .7), 0, 0, 0, 0);
		}
	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			int bucketOffsetY = 0;
			if (this.Attribute1 > 7) {
				bucketOffsetY = -1;
			} else if (this.Attribute1 < -7) {
				bucketOffsetY = 1;
			}
			// bucket Down
			// break blocks first
			int angle;
			for (int j = 0; j < 2; j++) {
				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					bp = new BlockPos(posX + calcTwoOffsetX(3.5, angle, i), posY + j + bucketOffsetY, posZ + calcTwoOffsetZ(3.5, angle, i));
					if (worldObj.getBlockState(bp).getBlock() == Blocks.SNOW_layer || worldObj.getBlockState(bp).getBlock() == Blocks.SNOW || worldObj.getBlockState(bp).getBlock() == Blocks.dirt || worldObj.getBlockState(bp).getBlock() == Blocks.sand || worldObj.getBlockState(bp).getBlock() == Blocks.gravel || worldObj.getBlockState(bp).getBlock() == Blocks.GRASS
							|| worldObj.getBlockState(bp).getBlock() == Blocks.CLAY || worldObj.getBlockState(bp).getBlock() == Blocks.netherrack || worldObj.getBlockState(bp).getBlock() == Blocks.mycelium || worldObj.getBlockState(bp).getBlock() == Blocks.soul_sand || worldObj.getBlockState(bp).getBlock() == Blocks.TALLGRASS || worldObj.getBlockState(bp).getBlock() == Blocks.farmland) {
						BlockUtil.BreakBlock(worldObj, bp, this.getControllingPassenger());

					}
					toppleTree(bp, 0, 0, worldObj.getBlockState(bp).getBlock());

				}
			}

		}
	}

	public void digMethodA() {

		int yOffset = 0;
		double bladeOffsetX = (bladeOffset * MathHelper.cos((float) ((yaw + 90) * Math.PI / 180.0D)));
		double bladeOffsetZ = (bladeOffset * MathHelper.sin((float) ((yaw + 90) * Math.PI / 180.0D)));

		if (this.getControllingPassenger() != null && this.isPlayerPushingSprintButton) {
			yOffset = -1;
		}

		if (this.getControllingPassenger() != null && this.isPlayerPushingJumpButton) {
			yOffset = +1;
		}

		int x = (int) (this.posX + bladeOffsetX - .5d);
		int y = (int) Math.round(this.posY + yOffset);
		int z = (int) (this.posZ + bladeOffsetZ - .5d);
		BlockPos bp = new BlockPos(x, y, z);
		toppleTree(bp, 0, 0, worldObj.getBlockState(bp).getBlock());
		if (worldObj.getBlockState(bp).getBlock().getMaterial() == Material.grass || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.ground || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.sand) {

			worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
			worldObj.setBlockToAir(bp);
		}

		double bladeOffsetX2 = (1 * MathHelper.cos((float) ((yaw + 90 + 90) * Math.PI / 180.0D)));
		double bladeOffsetZ2 = (1 * MathHelper.sin((float) ((yaw + 90 + 90) * Math.PI / 180.0D)));

		x = (int) (this.posX + bladeOffsetX + bladeOffsetX2 - .5d);
		z = (int) (this.posZ + bladeOffsetZ + bladeOffsetZ2 - .5d);
		bp = new BlockPos(x, y, z);
		toppleTree(bp, 0, 0, worldObj.getBlockState(bp).getBlock());
		if (worldObj.getBlockState(bp).getBlock().getMaterial() == Material.grass || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.ground || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.sand) {
			worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
			worldObj.setBlockToAir(bp);
		}
		x = (int) (this.posX + bladeOffsetX - bladeOffsetX2 - .5d);
		z = (int) (this.posZ + bladeOffsetZ - bladeOffsetZ2 - .5d);
		bp = new BlockPos(x, y, z);
		toppleTree(bp, 0, 0, worldObj.getBlockState(bp).getBlock());

		if (worldObj.getBlockState(bp).getBlock().getMaterial() == Material.grass || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.ground || worldObj.getBlockState(bp).getBlock().getMaterial() == Material.sand) {
			worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
			worldObj.setBlockToAir(bp);
		}

	}

	public int BladePos1X;
	public int BladePos1Z;
	public int BladePos2X;
	public int BladePos2Z;
	public int BladePos3X;
	public int BladePos3Z;

	public void setBladePosFromYaw() {
		if ((yaw >= 0 && yaw < 23) || yaw > 337) {
			BladePos1X = (int) Math.round(posX);
			BladePos1Z = (int) Math.round(posZ + bladeOffset);
			BladePos2X = (int) Math.round(posX + 1);
			BladePos2Z = (int) Math.round(posZ + bladeOffset);
			BladePos3X = (int) Math.round(posX - 1);
			BladePos3Z = (int) Math.round(posZ + bladeOffset);
		} else if (yaw >= 23 && yaw < 69) {

			BladePos1X = (int) Math.round(posX + bladeOffset);
			BladePos1Z = (int) Math.round(posZ + bladeOffset);
			BladePos2X = (int) Math.round(posX + bladeOffset) + 1;
			BladePos2Z = (int) Math.round(posZ + bladeOffset) + 1;
			BladePos3X = (int) Math.round(posX + bladeOffset - 1);
			BladePos3Z = (int) Math.round(posZ + bladeOffset) - 1;

		}
	}

}
