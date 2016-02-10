package com.projectreddog.machinemod.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAdvancedMinecart extends EntityMinecart {
	public int linkedParent = -1;
	public int linkedChild = -1;

	public EntityAdvancedMinecart(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.setSize(0.98F, 0.7F);

	}

	public EntityAdvancedMinecart(World world) {
		super(world);
		this.setSize(0.98F, 0.7F);

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			// server side
			updateServer();

		} else {
			// client

			updateClient();
		}

	}

	public void updateServer() {
		if (linkedChild != -1) {
			EntityAdvancedMinecart eam = (EntityAdvancedMinecart) worldObj.getEntityByID(linkedChild);
			if (eam != null) {
				eam.motionX = this.motionX;
				eam.motionY = this.motionY;
				eam.motionZ = this.motionZ;

			}
		}
	}

	public void updateClient()

	{

	}

	@Override
	public boolean interactFirst(EntityPlayer player) // should be proper class
	{
		if (!worldObj.isRemote) {
			List list = worldObj.getEntitiesWithinAABB(EntityAdvancedMinecart.class, new AxisAlignedBB(this.posX - 1, this.posY, this.posZ - 1, this.posX + 1, this.posY + 1, this.posZ + 1));
			collidedEntitiesInList(list);
		}
		return true;
	}

	@Override
	public EnumMinecartType getMinecartType() {
		// TODO Auto-generated method stub
		return null;
	}

	private void collidedEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityAdvancedMinecart) {
					EntityAdvancedMinecart eam = (EntityAdvancedMinecart) entity;
					if (eam.getEntityId() != this.getEntityId()) {
						// assume good set child / parents & return

						this.linkedChild = eam.getEntityId();
						eam.linkedParent = this.getEntityId();
					}
				}
			}
		}
	}

}
