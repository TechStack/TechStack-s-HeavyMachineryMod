package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityLaserMiner extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityLaserMiner(World world) {
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
		this.droppedItem = ModItems.laserminer;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {

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
					if (!is.isEmpty() && is != null) {
						is.setItemDamage(((EntityItem) entity).getItem().getItemDamage());
						if (!entity.isDead) {
							if (is.getCount() > 0) {
								ItemStack is1 = addToinventory(is);

								if (!is1.isEmpty() && is1.getCount() != 0) {
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

}
