package com.projectreddog.machinemod.tileentities;

import java.util.List;
import java.util.Random;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class TileEntityShredder extends TileEntity implements ITickable {
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

	@Override
	public void update() {

		if (!world.isBlockPowered(this.pos)) {

			if (world.getBlockState(pos).getBlock() == ModBlocks.machineshredder) {

				boundingBox = new AxisAlignedBB(this.pos.up(), this.pos.up().add(1, 1, 1));
				List list = world.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);
				processEntitiesInList(list);

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
						elb.attackEntityFrom(new EntityDamageSource(Reference.MOD_ID + ":" + "SHREDDER" + (r.nextInt(5) + 1), FakePlayerFactory.get((WorldServer) this.world, Reference.gameProfile)), 50);
					}
					// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
					// }
				}
			}
		}
	}

}
