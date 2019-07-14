package com.projectreddog.machinemod.tileentities;

import java.util.List;
import java.util.Random;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class TileEntityShredder extends TileEntity implements ITickableTileEntity {
	public AxisAlignedBB boundingBox;
	public int coolDownAmount = 1;
	public int timeTillCoolDown = 0;

	// slot 0 = north
	// slot 1 = east
	// slot 2 = south
	// slot 3 = west

	public TileEntityShredder() {

	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
	}

	public boolean shouldDrop() {
		return this.world.isAirBlock(this.pos.down());
	}

	@Override
	public void tick() {

		if (!world.isRemote) {
			if (!world.isBlockPowered(this.pos)) {

				if (world.getBlockState(pos).getBlock() == ModBlocks.machineshredder) {

					boundingBox = new AxisAlignedBB(this.pos.up(), this.pos.up().add(1, 1, 1));
					List list = world.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);
					processEntitiesInList(list);

					if (shouldDrop()) {
						list = world.getEntitiesWithinAABB(EntityItem.class, boundingBox);
						processEntitiesInList2(list);

						list = world.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox);
						processEntitiesInList3(list);
					}

				}
			}
		}

	}

	private void processEntitiesInList3(List par1List) {
		Random r = new Random();
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityXPOrb) {
					EntityXPOrb eexp = (EntityXPOrb) entity;
					if (!eexp.isDead) {

						EntityXPOrb eexp2 = new EntityXPOrb(eexp.world, eexp.posX, eexp.posY - 2, eexp.posZ, eexp.getXpValue());
						eexp2.motionX = 0;
						eexp2.motionY = 0;
						eexp2.motionZ = 0;
						eexp2.lastTickPosX = eexp2.posX;
						eexp2.lastTickPosY = eexp2.posY;
						eexp2.lastTickPosZ = eexp2.posZ;

						eexp2.forceSpawn = true;
						this.world.spawnEntity(eexp2);
						eexp.setDead();
					}

				}
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
				// }
			}
		}

	}

	private void processEntitiesInList2(List par1List) {
		Random r = new Random();
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityItem) {
					EntityItem ei = (EntityItem) entity;
					if (!ei.isDead) {

						EntityItem ei2 = new EntityItem(ei.world, ei.posX, ei.posY - 2, ei.posZ, ei.getItem());
						ei2.motionX = 0;
						ei2.motionY = 0;
						ei2.motionZ = 0;
						ei2.lastTickPosX = ei2.posX;
						ei2.lastTickPosY = ei2.posY;
						ei2.lastTickPosZ = ei2.posZ;

						ei2.forceSpawn = true;
						this.world.spawnEntity(ei2);
						ei.setDead();
					}

				}
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
				// }
			}
		}

	}

	private void processEntitiesInList(List par1List) {
		Random r = new Random();
		if (!this.world.isRemote) {
			for (int i = 0; i < par1List.size(); ++i) {
				Entity entity = (Entity) par1List.get(i);
				if (entity != null) {
					if (entity instanceof EntityLivingBase) {
						EntityLivingBase elb = (EntityLivingBase) entity;
						elb.attackEntityFrom(new EntityDamageSource(Reference.MOD_ID + ":" + "SHREDDER" + (r.nextInt(6) + 1), FakePlayerFactory.get((WorldServer) this.world, Reference.gameProfile)), 50);
					}
					// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
					// }
				}
			}
		}
	}

}
