package com.projectreddog.machinemod.utility;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockBlueprintHelper {

	public static boolean BuildBlocks(String fileName, World world, BlockPos pos1, Rotation rotation, boolean instant, int x, int y, int z) {
		// x first , then z , then y
		boolean result = true;
		int dx;
		int dy;
		int dz;

		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(fileName));
			dis = new DataInputStream(fis);

			dx = dis.readInt();// dx
			dy = dis.readInt();
			dz = dis.readInt();

			for (int j = pos1.getY(); j <= pos1.getY() + dy; j++) {
				for (int i = pos1.getX(); i <= pos1.getX() + dx; i++) {
					for (int k = pos1.getZ(); k <= pos1.getZ() + dz; k++) {

						// DEBUGGING line
						int i2 = dis.readInt();
						int j2 = dis.readInt();
						int l2 = dis.readInt();
						// DEBUGING STUFF;
						String BlockRegistryName = dis.readUTF();
						int metaValue = dis.readInt();
						BlockPos bp = new BlockPos(i, j, k);

						if (instant || (j == x && i == y && k == z)) {
							setBlockState(world, bp, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getStateFromMeta(metaValue));
						}

					}
				}
			}
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}

		return result;

	}

	public static ItemStack GetBuildBlockForBlueprintPosition(String fileName, World world, int x, int y, int z) {
		// x first , then z , then y

		int dx;
		int dy;
		int dz;

		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(fileName));
			dis = new DataInputStream(fis);

			dx = dis.readInt();// dx
			dy = dis.readInt();
			dz = dis.readInt();

			for (int j = 0; j <= dy; j++) {
				for (int i = 0; i <= +dx; i++) {
					for (int k = 0; k <= +dz; k++) {

						// DEBUGGING line
						int i2 = dis.readInt();
						int j2 = dis.readInt();
						int l2 = dis.readInt();
						// DEBUGING STUFF;
						String BlockRegistryName = dis.readUTF();
						int metaValue = dis.readInt();
						BlockPos bp = new BlockPos(i, j, k);

						// setBlockState(world, bp, );

						return new ItemStack(Item.getItemFromBlock(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName))), 1, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).damageDropped(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getStateFromMeta(metaValue)));

					}

				}
			}

			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return ItemStack.EMPTY;
		}
		return ItemStack.EMPTY;
	}

	private static boolean setBlockState(World world, BlockPos bp, IBlockState state) {
		boolean result = false;
		result = world.setBlockState(bp, state);
		if (result) {
			IBlockState state2 = world.getBlockState(bp);
			world.notifyBlockUpdate(bp, state, state2, 3);
		}
		return result;
	}
}
