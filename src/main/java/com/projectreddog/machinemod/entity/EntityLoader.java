package com.projectreddog.machinemod.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageEntityInventoryChangedToClient;
import com.projectreddog.machinemod.utility.LogHelper;

public class EntityLoader extends EntityMachineModRideable  {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);


	public EntityLoader(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		inventory = new ItemStack[9];
		
		this.mountedOffsetY = 0.6D;
		this.mountedOffsetX = 0.4D;
		this.mountedOffsetZ = 0.4D;
		this.maxAngle = 0;
		this.minAngle = -90;
		this.droppedItem = ModItems.loader;
		this.shouldSendClientInvetoryUpdates=true;
	
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (this.Attribute1 == this.getMaxAngle()) {
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
					bp = new BlockPos(posX + calcTwoOffsetX(3.5, angle, i), posY, posZ + calcTwoOffsetZ(3.5, angle, i));
					if ( worldObj.getBlockState(bp).getBlock() == Blocks.snow_layer
							||  worldObj.getBlockState(bp).getBlock() == Blocks.snow
							||  worldObj.getBlockState(bp).getBlock() == Blocks.dirt
							||  worldObj.getBlockState(bp).getBlock() == Blocks.sand
							||  worldObj.getBlockState(bp).getBlock() == Blocks.gravel
							||  worldObj.getBlockState(bp).getBlock() == Blocks.grass
							||  worldObj.getBlockState(bp).getBlock() == Blocks.clay
							||  worldObj.getBlockState(bp).getBlock() == ModBlocks.machinemodblastedstone
							||  worldObj.getBlockState(bp).getBlock() == ModBlocks.machinemodblastedstone2
							||  worldObj.getBlockState(bp).getBlock() == Blocks.soul_sand
							)
					{
						worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
						worldObj.setBlockToAir(bp);
					}

				}

				AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(3.5, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(3.5, 90, -1) + posZ - .5d, calcTwoOffsetX(3.5, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(3.5, 90, 1) + posZ + .5d);

				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
				collidedEntitiesInList(list);
			}
			if (this.Attribute1 == this.getMinAngle()) {
				// bucket up
				// Drop blocks
				// TODO needs something to pace it a bit more now it drops
				// everything way to fast.
				for (int i = 0; i < this.getSizeInventory(); i++) {
					ItemStack item = this.getStackInSlot(i);

					if (item != null && item.stackSize > 0) {
						;

						EntityItem entityItem = new EntityItem(worldObj, posX + calcOffsetX(3.5), posY + 4, posZ + calcOffsetZ(3.5), item);

						if (item.hasTagCompound()) {
							entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
						}

						float factor = 0.05F;
						// entityItem.motionX = rand.nextGaussian() * factor;
						entityItem.motionY = 0;
						// entityItem.motionZ = rand.nextGaussian() * factor;
						entityItem.forceSpawn = true;
						LogHelper.info(worldObj.spawnEntityInWorld(entityItem));
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
					ItemStack is = ((EntityItem) entity).getEntityItem().copy();
					is.setItemDamage(((EntityItem) entity).getEntityItem().getItemDamage());
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
