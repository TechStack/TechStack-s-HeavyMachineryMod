package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TileEntityExpCollector extends TileEntity implements ITickable {

	public AxisAlignedBB boundingBox;
	public AxisAlignedBB boundingBox2;

	public final double MoveSpeed = .1d;
	public int expAmount = 0;

	public TileEntityExpCollector() {

	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
	}

	public boolean isCenterBlock() {
		boolean ret = false;
		if (this.world.getBlockState(this.pos.up()).getBlock() == ModBlocks.machineexpcollector) {
			if (this.world.getBlockState(this.pos.down()).getBlock() == ModBlocks.machineexpcollector) {
				ret = true;
			}
		}
		return ret;
	}

	public boolean isBottomBlock() {
		boolean ret = false;

		if (this.world.getBlockState(this.pos.up().up()).getBlock() == ModBlocks.machineexpcollector) {
			if (this.world.getBlockState(this.pos.up()).getBlock() == ModBlocks.machineexpcollector) {
				ret = true;
			}
		}
		return ret;
	}

	public boolean isTopBlock() {
		boolean ret = false;
		if (this.world.getBlockState(this.pos.down().down()).getBlock() == ModBlocks.machineexpcollector) {
			if (this.world.getBlockState(this.pos.down()).getBlock() == ModBlocks.machineexpcollector) {
				ret = true;
			}
		}
		return ret;
	}

	@Override
	public void update() {
		if (this.world != null) {
			if (isCenterBlock()) {
				if (world.getBlockState(pos).getBlock() == ModBlocks.machineexpcollector) {
					BlockPos bp = this.pos;
					boundingBox = new AxisAlignedBB(bp).grow(2, 1, 2);
					bp = this.pos;

					boundingBox2 = new AxisAlignedBB(bp.up().up()).grow(1, 1, 1);
				}
				List list = world.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox);
				processEntitiesInList(list);

				list = world.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox2);
				processEntitiesInListTop(list);
			}
		}

	}

	public double getMoveSpeed() {
		return this.MoveSpeed;
	}

	private void processEntitiesInListTop(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
				double tx = this.getPos().getX();
				double ty = this.getPos().getY() + .5d;
				double tz = this.getPos().getZ() + 1.5d;

				double dx = tx - x;
				double dy = ty - y;
				double dz = tz - z;
				if (dx > 0) {
					dx = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dx);
				} else if (dx < 0) {
					dx = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dx);
				}

				if (dy > 0) {
					dy = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dy);
				} else if (dy < 0) {
					dy = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dy);
				}

				if (dz > 0) {
					dz = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dz);
				} else if (dz < 0) {
					dz = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dz);
				}

				entity.fallDistance = 0; // set so no damage to players going up long virt shafts
				entity.motionY = dy;
				entity.motionX = dx;
				entity.motionZ = dz;
				entity.lastTickPosX = entity.posX;
				entity.lastTickPosY = entity.posY;
				entity.lastTickPosZ = entity.posZ;
				entity.move(MoverType.SELF, dx, dy, dz);

			}
		}
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
				double tx = this.getPos().getX() + .5d;
				double ty = this.getPos().getY() + .5d;
				double tz = this.getPos().getZ() + .5d;

				double dx = tx - x;
				double dy = ty - y;
				double dz = tz - z;
				double distance = MathHelper.sqrt((dx * dx) + (dy * dy) + (dz * dz));
				if (!this.world.isRemote) {
					if (distance > 0 & distance < 1d || distance < 0 && distance > -1d) {

						if (entity instanceof EntityXPOrb) {
							EntityXPOrb expo = (EntityXPOrb) entity;
							this.expAmount = this.expAmount + expo.getXpValue();
						}

						entity.setDead();
					}
				}
				if (dx > 0) {
					dx = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dx);
				} else if (dx < 0) {
					dx = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dx);
				}

				if (dy > 0) {
					dy = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dy);
				} else if (dy < 0) {
					dy = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dy);
				}

				if (dz > 0) {
					dz = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dz);
				} else if (dz < 0) {
					dz = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dz);
				}

				entity.fallDistance = 0; // set so no damage to players going up long virt shafts
				entity.motionY = dy;
				entity.motionX = dx;
				entity.motionZ = dz;
				entity.lastTickPosX = entity.posX;
				entity.lastTickPosY = entity.posY;
				entity.lastTickPosZ = entity.posZ;
				entity.move(MoverType.SELF, dx, dy, dz);

			}
		}
	}

}
