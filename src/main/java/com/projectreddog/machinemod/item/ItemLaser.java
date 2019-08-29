package com.projectreddog.machinemod.item;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLaser extends ItemMachineMod {
	public String registryName = "laser";
	public HashMap<UUID, BlockPos> Point1Map;
	public HashMap<UUID, BlockPos> Point2Map;

	public ItemLaser() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;
		Point1Map = new HashMap<UUID, BlockPos>();
		Point2Map = new HashMap<UUID, BlockPos>();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		// public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;
		if (!world.isRemote) {
			// Server
			UUID tmpUUID = player.getUniqueID();

			if (player.isSneaking()) {
				if (Point1Map.containsKey(tmpUUID)) {
					Point1Map.remove(tmpUUID);
				}
				if (Point2Map.containsKey(tmpUUID)) {
					Point2Map.remove(tmpUUID);
				}
			}
			if (Point1Map.containsKey(tmpUUID)) {
				Point2Map.put(tmpUUID, pos);
				// Both set do the scan now ?
				ScanBlocks(world, Point1Map.get(tmpUUID), Point2Map.get(tmpUUID));
			} else {
				// no map create new!
				Point1Map.put(tmpUUID, pos);
			}
		}
		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	private boolean ScanBlocks(World world, BlockPos pos1, BlockPos pos2) {
		boolean result = true;
		int dx;
		int dy;
		int dz;
		int maxX;
		int maxY;
		int maxZ;
		int minX;
		int minY;
		int minZ;
		minX = pos1.getX();
		maxX = pos1.getX();
		if (pos2.getX() < minX) {
			minX = pos2.getX();
		}
		if (pos2.getX() > maxX) {
			maxX = pos2.getX();
		}
		minY = pos1.getY();
		maxY = pos1.getY();
		if (pos2.getY() < minY) {
			minY = pos2.getY();
		}
		if (pos2.getY() > maxY) {
			maxY = pos2.getY();
		}
		minZ = pos1.getZ();
		maxZ = pos1.getZ();
		if (pos2.getZ() < minZ) {
			minZ = pos2.getZ();
		}
		if (pos2.getZ() > maxZ) {
			maxZ = pos2.getZ();
		}

		dx = maxX - minX;
		dy = maxY - minY;
		dz = maxZ - minZ;
		DataOutputStream dos = null;
		try {
			String fileName = "TESTFILE";
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			dos = new DataOutputStream(fos);

			dos.writeInt(dx);
			dos.writeInt(dy);
			dos.writeInt(dz);
			for (int j = minY; j <= maxY; j++) {
				for (int i = minX; i <= maxX; i++) {
					for (int k = minZ; k <= maxZ; k++) {
						LogHelper.info(" The block at cords X,Y,Z:" + i + "," + j + "," + k + ", is a block named:" + world.getBlockState(new BlockPos(i, j, k)).getBlock().getRegistryName() + " " + world.getBlockState(new BlockPos(i, j, k)).getBlock().getMetaFromState(world.getBlockState(new BlockPos(i, j, k)).getBlock().getBlockState().getBaseState()));
						LogHelper.info("Properties of this block are :");
						// DEBUGGING line
						dos.writeInt(i);
						dos.writeInt(j);
						dos.writeInt(k);

						String BlockRegistryName = world.getBlockState(new BlockPos(i, j, k)).getBlock().getRegistryName().toString();
						// DEBUGGING line
						dos.writeUTF(BlockRegistryName);
						// HOW MANY TO READ

						int metaValue = world.getBlockState(new BlockPos(i, j, k)).getBlock().getMetaFromState(world.getBlockState(new BlockPos(i, j, k)));
						dos.writeInt(metaValue);
						// for (IProperty p : world.getBlockState(new BlockPos(i, j, k)).getBlock().getBlockState().getProperties()) {
						// LogHelper.info("name : " + p.getName() + " VALUE= " + world.getBlockState(new BlockPos(i, j, k)).getValue(p));
						// LogHelper.info(" I tried to find the block by name and found : " + ForgeRegistries.BLOCKS.getValue(world.getBlockState(new BlockPos(i, j, k)).getBlock().getRegistryName()).getRegistryName());
						// String propertyName = p.getName();
						// String propertyValue = world.getBlockState(new BlockPos(i, j, k)).getValue(p).toString();
						// dos.writeUTF(propertyName);
						// dos.writeUTF(propertyValue);
						// }
					}
				}
			}
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}

		return result;

	}

}
