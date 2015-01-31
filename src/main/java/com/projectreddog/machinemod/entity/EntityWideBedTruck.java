package com.projectreddog.machinemod.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageEntityInventoryChangedToClient;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

public class EntityWideBedTruck extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	

	public EntityWideBedTruck(World world) {
		super(world);

		setSize(3, 2);
		inventory = new ItemStack[9];
		this.mountedOffsetY = 0.35D;
		this.mountedOffsetX = 1.5D;
		this.mountedOffsetZ = 1.5D;
		this.maxAngle = 0;
		this.minAngle = 0;
		this.droppedItem = ModItems.widebedtruck;
		this.shouldSendClientInvetoryUpdates=true;
		this.maxSpeed = 0.4d;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	



}
