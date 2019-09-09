package com.projectreddog.machinemod.utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;

import com.projectreddog.machinemod.reference.Reference;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockBlueprintHelper {

	public static HashMap<UUID, BlockPos> Point1Map;
	public static HashMap<UUID, BlockPos> Point2Map;

	public static String[] clientCacheBlueprintsFileName;

	public static String[] clientCacheBlueprintsOwner;

	public static String[] clientCacheBlueprintsDisplayName;

	//
	// public static boolean BuildBlocks(String fileName, World world, BlockPos pos1, Rotation rotation, boolean instant, int x, int y, int z) {
	// // x first , then z , then y
	// boolean result = true;
	// int dx;
	// int dy;
	// int dz;
	//
	// DataInputStream dis = null;
	// try {
	// // String fileName = "TESTFILE";
	// FileInputStream fis = new FileInputStream(new File(fileName));
	// dis = new DataInputStream(fis);
	//
	// dx = dis.readInt();// dx
	// dy = dis.readInt();
	// dz = dis.readInt();
	//
	// for (int j = pos1.getY(); j <= pos1.getY() + dy; j++) {
	// for (int i = pos1.getX(); i <= pos1.getX() + dx; i++) {
	// for (int k = pos1.getZ() - dz; k <= pos1.getZ(); k++) {
	//
	// // DEBUGGING line
	// int i2 = dis.readInt();
	// int j2 = dis.readInt();
	// int l2 = dis.readInt();
	// // DEBUGING STUFF;
	// String BlockRegistryName = dis.readUTF();
	// int metaValue = dis.readInt();
	//
	// if (instant || (i - pos1.getX() == x && j - pos1.getY() == y && pos1.getZ() - k == z)) {
	// BlockPos bp = new BlockPos(i, j, k - 1);
	// setBlockState(world, bp, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getStateFromMeta(metaValue), EnumFacing.NORTH);
	//
	// }
	//
	// }
	// }
	// }
	// dis.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// result = false;
	// }
	//
	// return result;
	//
	// }

	@Deprecated
	public static ItemStack GetBuildBlockForBlueprintPosition(String fileName, World world, int x, int y, int z) {
		// x first , then z , then y

		int dx;
		int dy;
		int dz;

		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(Reference.BLUEPRINTLOCATION + fileName));
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

						if ((i == x && j == y && k == z)) {
							BlockPos bp = new BlockPos(i, j, k);

							// setBlockState(world, bp, );

							return new ItemStack(Item.getItemFromBlock(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName))), 1, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).damageDropped(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getStateFromMeta(metaValue)));
						}
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

	public static BlockPos getBlueprintArea(String fileName) {
		boolean result = true;
		int dx;
		int dy;
		int dz;

		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(Reference.BLUEPRINTLOCATION + fileName));
			dis = new DataInputStream(fis);

			dx = dis.readInt();// dx
			dy = dis.readInt();
			dz = dis.readInt();

			dis.close();
			return new BlockPos(dx, dy, dz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static IBlockState[][][] getBlockStateArray(String fileName) {
		// x first , then z , then y
		boolean result = true;
		int dx;
		int dy;
		int dz;

		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(Reference.BLUEPRINTLOCATION + fileName));
			dis = new DataInputStream(fis);

			dx = dis.readInt();// dx
			dy = dis.readInt();
			dz = dis.readInt();
			IBlockState[][][] ibs = new IBlockState[dx + 1][dy + 1][dz + 1];
			for (int j = 0; j <= dy; j++) {
				for (int i = 0; i <= dx; i++) {
					for (int k = 0; k <= dz; k++) {

						// DEBUGGING line
						int i2 = dis.readInt();
						int j2 = dis.readInt();
						int l2 = dis.readInt();
						// DEBUGING STUFF;
						String BlockRegistryName = dis.readUTF();
						int metaValue = dis.readInt();
						// BlockPos bp = new BlockPos(i, j, k);
						// setBlockState(world, bp, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getStateFromMeta(metaValue));
						ibs[i][j][k] = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getStateFromMeta(metaValue);

						// new ItemStack( ibs[i][j][k].getBlock(), 1, ibs[i][j][k].getBlock().damageDropped(ibs[i][j][k]));

					}

				}
			}

			dis.close();
			return ibs;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void WriteBlockStateArrayToByteBuff(ByteBuf buf, IBlockState[][][] blockStateArray) {

		int x = blockStateArray.length;
		int y = blockStateArray[0].length;
		int z = blockStateArray[0][0].length;

		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

		for (int i = 0; i < blockStateArray.length; i++) {
			for (int j = 0; j < blockStateArray[i].length; j++) {
				for (int k = 0; k < blockStateArray[i][j].length; k++) {

					String registryName = blockStateArray[i][j][k].getBlock().getRegistryName().toString();
					buf.writeInt(registryName.length());

					buf.writeCharSequence(registryName, Charset.forName("UTF-8"));
					int metaValue = blockStateArray[i][j][k].getBlock().getMetaFromState(blockStateArray[i][j][k]);
					buf.writeInt(metaValue);

				}
			}
		}
	}

	public IBlockState[][][] ReadBlockStateArrayFromByteBuff(ByteBuf buf) {

		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		IBlockState[][][] blockStateArray = new IBlockState[x][y][z];

		for (int i = 0; i < blockStateArray.length; i++) {
			for (int j = 0; j < blockStateArray[i].length; j++) {
				for (int k = 0; k < blockStateArray[i][j].length; k++) {
					int lenght = buf.readInt();

					String RegisteryName = buf.readCharSequence(lenght, Charset.forName("UTF-8")).toString();
					int metaValue = buf.readInt();

					blockStateArray[i][j][k] = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(RegisteryName)).getStateFromMeta(metaValue);

					buf.writeInt(metaValue);

				}
			}
		}

		return blockStateArray;
	}

	public static boolean setBlockState(World world, BlockPos bp, IBlockState state, EnumFacing ef) {
		boolean result = false;
		if (ef == EnumFacing.NORTH) {
			// north Do nothing!
		} else if (ef == EnumFacing.NORTH) {
			// south = 180 so mirror

			state = state.withRotation(Rotation.CLOCKWISE_180);
		} else if (ef == EnumFacing.EAST) {
			// east = 90 so rotate 90
			state = state.withRotation(Rotation.CLOCKWISE_90);
		} else if (ef == EnumFacing.WEST) {
			// west = 270
			state = state.withRotation(Rotation.COUNTERCLOCKWISE_90);
		}

		result = world.setBlockState(bp, state);
		if (result) {
			IBlockState state2 = world.getBlockState(bp);
			world.notifyBlockUpdate(bp, state, state2, 2);
		}
		return result;
	}

	public static void CreateBlueprintLocation() {

		File f = new File(Reference.CONFIG_MACHINEMOD_LOCATION);
		LogHelper.info(f.mkdir());

		f = new File(Reference.BLUEPRINTLOCATION);
		LogHelper.info(f.mkdir());

	}

	public static String[] GetBlockBlueprintFileList() {

		CreateBlueprintLocation();

		File f = new File(Reference.BLUEPRINTLOCATION);

		return f.list();

	}

	public static boolean ScanBlocks(World world, BlockPos pos1, BlockPos pos2, String FileName) {
		CreateBlueprintLocation();
		// GetBlockBlueprintFileList();
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
			FileOutputStream fos = new FileOutputStream(new File(Reference.BLUEPRINTLOCATION + FileName));
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
