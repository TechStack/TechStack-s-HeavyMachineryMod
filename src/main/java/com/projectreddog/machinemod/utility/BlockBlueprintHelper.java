package com.projectreddog.machinemod.utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
		int fileformatVersion;

		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(Reference.BLUEPRINTLOCATION + fileName));
			dis = new DataInputStream(fis);
			fileformatVersion = dis.readInt();

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
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();

			return ItemStack.EMPTY;
		}
		return ItemStack.EMPTY;
	}

//	public HashMap<String, Integer> getMissingBlocks(IBlockState[][][] blockStateArray, IInventory inventory) {
//		HashMap<String, Integer> myMap = new HashMap<String, Integer>();
//		for (int x = 0; x < blockStateArray.length; x++) {
//			for (int y = 0; y < blockStateArray[x].length; y++) {
//				for (int z = 0; z < blockStateArray[x][y].length; z++) {
//
//					if (!blockStateArray[x][y][z].getBlock().isAir(blockStateArray[x][y][z], null, null)) {
//						Item item = blockStateArray[x][y][z].getBlock().getPickBlock(blockStateArray[x][y][z], null, null, null, null).getItem();
//						String itemName = item.getItemStackDisplayName(new ItemStack(item));
//						if (myMap.containsKey(itemName)) {
//							int count = myMap.get(itemName);
//							myMap.put(itemName, count + 1);
//
//						} else {
//							// not found in the list so add it
//							myMap.put(itemName, 1);
//						}
//					}
//				}
//
//			}
//		}
//
//		return myMap;
//	}
//
//	
	public static ItemStack getNextBlockNeeded(IBlockState[][][] blockStateArray, int currentX, int currentY, int currentZ, int state) {
		if (blockStateArray != null) {
			if (currentX >= blockStateArray.length) {
				currentX = 0;
				currentY++;
			}
			if (currentY >= blockStateArray[currentX].length) {
				currentY = 0;
				currentZ++;
			}
			if (currentZ >= blockStateArray[currentX][currentY].length) {
				currentZ = 0;
				// ERRORR
				return ItemStack.EMPTY;
			}
			if (state > 2) {

				currentX = currentX + 1;

				if (currentX >= blockStateArray.length) {
					currentX = 0;
					currentY++;
				}
				if (currentY >= blockStateArray[currentX].length) {
					currentY = 0;
					currentZ++;
				}
				if (currentZ >= blockStateArray[currentX][currentY].length) {
					currentZ = 0;
					// ERRORR
					return ItemStack.EMPTY;
				}
			}

			while (blockStateArray[currentX][currentY][currentZ].getBlock().isAir(blockStateArray[currentX][currentY][currentZ], null, null)) {
				currentX = currentX + 1;

				if (currentX >= blockStateArray.length) {
					currentX = 0;
					currentY++;
				}
				if (currentY >= blockStateArray[currentX].length) {
					currentY = 0;
					currentZ++;
				}
				if (currentZ >= blockStateArray[currentX][currentY].length) {
					currentZ = 0;
					// ERRORR
					return ItemStack.EMPTY;
				}
			}

			ItemStack item;
			if (blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.BLACK_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.BLUE_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.BROWN_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.CYAN_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.GRAY_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.GREEN_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.LIGHT_BLUE_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.LIME_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.MAGENTA_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.ORANGE_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.PINK_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.PURPLE_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.RED_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.WHITE_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.YELLOW_SHULKER_BOX || blockStateArray[currentX][currentY][currentZ].getBlock() == Blocks.SILVER_SHULKER_BOX) {

				item = new ItemStack(ItemBlock.getItemFromBlock(blockStateArray[currentX][currentY][currentZ].getBlock()));
			} else if (blockStateArray[currentX][currentY][currentZ].getBlock() instanceof BlockDoublePlant) {
				item = new ItemStack(ItemBlock.getItemFromBlock(blockStateArray[currentX][currentY][currentZ].getBlock()));
				item.setItemDamage(blockStateArray[currentX][currentY][currentZ].getBlock().getMetaFromState(blockStateArray[currentX][currentY][currentZ]));

			}

			else {
				item = blockStateArray[currentX][currentY][currentZ].getBlock().getPickBlock(blockStateArray[currentX][currentY][currentZ], null, null, null, null);
			}
			return item;
		} else {
			return ItemStack.EMPTY;

		}

	}

	public static List<ItemStack> getMissingBlocks(IBlockState[][][] blockStateArray, TileEntityTowerCrane inventory, int currentX, int currentY, int currentZ) {
		List<ItemStack> neededItems = new ArrayList<ItemStack>();
		int x = 0;
		int y = 0;
		int z = 0;
		int activeBlockCount = 1;
		if (blockStateArray != null) {
			if (blockStateArray.length > 0 && blockStateArray[0].length > 0 && blockStateArray[0][0].length > 0) {
				int maxX = blockStateArray.length;
				int maxY = blockStateArray[0].length;
				int maxZ = blockStateArray[0][0].length;

				activeBlockCount = (currentX + (maxX * currentZ) + (maxX * maxZ * currentY));

			}

			// add all required items
			for (x = 0; x < blockStateArray.length; x++) {
				for (y = 0; y < blockStateArray[x].length; y++) {

					for (z = 0; z < blockStateArray[x][y].length; z++) {

						int loopCount = (x + (blockStateArray.length * z) + (blockStateArray.length * blockStateArray[x][y].length * y));
						if (activeBlockCount <= loopCount) {
							// LogHelper.info("Loop Count: " + loopCount);

							if (!blockStateArray[x][y][z].getBlock().isAir(blockStateArray[x][y][z], null, null)) {
								ItemStack item;
								if (blockStateArray[x][y][z].getBlock() == Blocks.BLACK_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.BLUE_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.BROWN_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.CYAN_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.GRAY_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.GREEN_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.LIGHT_BLUE_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.LIME_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.MAGENTA_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.ORANGE_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.PINK_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.PURPLE_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.RED_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.WHITE_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.YELLOW_SHULKER_BOX || blockStateArray[x][y][z].getBlock() == Blocks.SILVER_SHULKER_BOX) {
									item = new ItemStack(ItemBlock.getItemFromBlock(blockStateArray[x][y][z].getBlock()));
								} else if (blockStateArray[x][y][z].getBlock() instanceof BlockDoublePlant) {
									item = new ItemStack(ItemBlock.getItemFromBlock(blockStateArray[x][y][z].getBlock()));
									item.setItemDamage(blockStateArray[x][y][z].getBlock().getMetaFromState(blockStateArray[x][y][z]));

								} else {

									item = blockStateArray[x][y][z].getBlock().getPickBlock(blockStateArray[x][y][z], null, null, null, null);
								}
								// String itemName = item.getItemStackDisplayName(new ItemStack(item));

								if (neededItems.size() == 0) {
									neededItems.add(item);
								} else {
									boolean found = false;
									for (Iterator iterator = neededItems.iterator(); iterator.hasNext();) {
										ItemStack is = (ItemStack) iterator.next();
										if (is.getItem() == item.getItem()) {
											// same items
											if (is.getMetadata() == item.getMetadata()) {
												is.setCount(is.getCount() + 1);

												// add one more to teh needed list

												found = true;
											} else {

											}
										} else {
										}
									}

									if (!found) {
										neededItems.add(item);
									}

								}
							}
						}
					}
				}
			}

			// remove built
//			for (int y = 0; y <= currentY; y++) {
//
//				for (int z = 0; z <= currentZ; z++) {
//					for (int x = 0; x <= currentX; x++) {
//						// REMOVE as needed
//
//						if (!blockStateArray[x][y][z].getBlock().isAir(blockStateArray[x][y][z], null, null)) {
//							Item item = blockStateArray[x][y][z].getBlock().getPickBlock(blockStateArray[x][y][z], null, null, null, null).getItem();
//							// String itemName = item.getItemStackDisplayName(new ItemStack(item));
//							for (int j = 0; j < neededItems.size(); j++) {
//								ItemStack is = neededItems.get(j);
//								if (is.getItem() == item) {
//									// same items
//									is.setCount(is.getCount() - 1);
//									// add one more to the needed list
//									if (is.getCount() <= 0) {
//										neededItems.remove(j);
//										j = neededItems.size();
//
//									}
//								}
//							}
//
//						}
//
//					}
//				}
//			}
		}

		// remove what was in invnentory
		for (int i = 0; i < inventory.getSizeInventory(); i++) {

			for (int j = 0; j < neededItems.size(); j++) {
				ItemStack is = neededItems.get(j);

				if (inventory.getStackInSlot(i) != ItemStack.EMPTY && inventory.getStackInSlot(i) != null) {

					if (is.getItem() == inventory.getStackInSlot(i).getItem()) {
						// same item check meta
						if (is.getItemDamage() == inventory.getStackInSlot(i).getItemDamage()) {
							// same meta /damage
							is.setCount(is.getCount() - inventory.getStackInSlot(i).getCount());
							if (is.getCount() <= 0) {
								is.setCount(0);

								neededItems.remove(j);
								// max out to end the loop please
								j = neededItems.size();

							}
						}

					}
				}
			}

		}
		// remove what was in claw invnentory
		for (int j = 0; j < neededItems.size(); j++) {
			ItemStack is = neededItems.get(j);

			if (inventory.getClawHolding() != ItemStack.EMPTY && inventory.getClawHolding() != null) {

				if (is.getItem() == inventory.getClawHolding().getItem()) {
					// same item check meta
					if (is.getItemDamage() == inventory.getClawHolding().getItemDamage()) {
						// same meta /damage
						is.setCount(is.getCount() - 1);
						if (is.getCount() <= 0) {
							is.setCount(0);

							neededItems.remove(j);
							// max out to end the loop please
							j = neededItems.size();

						}
					}

				}
			}
		}

		return neededItems;
	}

	public static BlockPos getBlueprintArea(String fileName) {
		boolean result = true;
		int dx;
		int dy;
		int dz;
		int fileformatVersion;
		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(Reference.BLUEPRINTLOCATION + fileName));
			dis = new DataInputStream(fis);
			fileformatVersion = dis.readInt();
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
		int fileformatVersion;
		DataInputStream dis = null;
		try {
			// String fileName = "TESTFILE";
			FileInputStream fis = new FileInputStream(new File(Reference.BLUEPRINTLOCATION + fileName));
			dis = new DataInputStream(fis);
			fileformatVersion = dis.readInt();

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

	public static void WriteBlockStateArrayToByteBuff(ByteBuf buf, IBlockState[][][] blockStateArray) {

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

	public static IBlockState[][][] ReadBlockStateArrayFromByteBuff(ByteBuf buf) {

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

	public static boolean ScanBlocks(World world, BlockPos pos1, BlockPos pos2, String FileName, EnumFacing enumFacing) {
		CreateBlueprintLocation();
		// GetBlockBlueprintFileList();
		boolean result = true;
		int dx;
		int dy;
		int dz;
		int fileformatVersion;

		int maxX;
		int maxY;
		int maxZ;
		int minX;
		int minY;
		int minZ;
		fileformatVersion = Reference.BLOCK_BLUEPRINT_FILE_FORMAT_VERSION;
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
			dos.writeInt(fileformatVersion);

			dos.writeInt(dx);
			dos.writeInt(dy);
			dos.writeInt(dz);
			for (int j = 0; j <= dy; j++) {
				for (int i = 0; i <= dx; i++) {
					for (int k = 0; k <= dz; k++) {

						int y = j + minY;
						int x = getScannX(i, k, dx, dz, enumFacing) + maxX;
						int z = getScannZ(i, k, dx, dz, enumFacing) + maxZ;

						LogHelper.info(" The block at cords X,Y,Z:" + x + "," + y + "," + z + ", is a block named:" + world.getBlockState(new BlockPos(x, y, z)).getBlock().getRegistryName() + " " + world.getBlockState(new BlockPos(x, y, z)).getBlock().getMetaFromState(world.getBlockState(new BlockPos(x, y, z)).getBlock().getBlockState().getBaseState()));
						LogHelper.info("Properties of this block are :");
						// DEBUGGING line
						dos.writeInt(x);
						dos.writeInt(y);
						dos.writeInt(z);
						String BlockRegistryName;
						int metaValue = 0;
						if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.BED || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.ACACIA_DOOR || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.BIRCH_DOOR || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.DARK_OAK_DOOR || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.IRON_DOOR || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.JUNGLE_DOOR || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.OAK_DOOR || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.SPRUCE_DOOR || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.PISTON_HEAD) {
							BlockRegistryName = Blocks.AIR.getRegistryName().toString();
							LogHelper.info("non Supported block scanned Beds and doors are two blocks Piston Heads are also not supported and wont scan correctly skipping ! ");

						} else if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.WATER || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.FLOWING_WATER) {
							LogHelper.info("Not scanning water for underwater base support!");
							BlockRegistryName = Blocks.AIR.getRegistryName().toString();

						} else {
							BlockRegistryName = world.getBlockState(new BlockPos(x, y, z)).getBlock().getRegistryName().toString();
							metaValue = world.getBlockState(new BlockPos(x, y, z)).getBlock().getMetaFromState(world.getBlockState(new BlockPos(x, y, z)));
						}
						// DEBUGGING line
						dos.writeUTF(BlockRegistryName);
						// HOW MANY TO READ

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

	public static int getScannX(int x, int z, int maxX, int maxZ, EnumFacing enumFacing) {

		switch (enumFacing) {
		case NORTH:
			return x - maxX;
		case SOUTH:
			return x - maxX;// return this.boundingBox.minX + x;
		case WEST:
			return x - maxX;
		case EAST:
			return x - maxX;
		default:
			return x;
		}
	}

	public static int getScannZ(int x, int z, int maxX, int maxZ, EnumFacing enumFacing) {

		switch (enumFacing) {
		case NORTH:
			return z - maxZ;
		case SOUTH:
			return z - maxZ;// return this.boundingBox.minX + x;
		case WEST:
			return z - maxZ;
		case EAST:
			return z - maxZ;
		default:
			return z;
		}
	}
}
