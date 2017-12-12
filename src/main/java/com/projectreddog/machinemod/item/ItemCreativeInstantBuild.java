package com.projectreddog.machinemod.item;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemCreativeInstantBuild extends ItemMachineMod {
	public String registryName = "creativeinstantbuild";
	public HashMap<UUID, BlockPos> Point1Map;
	public HashMap<UUID, BlockPos> Point2Map;

	public ItemCreativeInstantBuild() {
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
		if (world.isRemote) {
			// Server

			BuildBlocks(world, pos.up());

		}
		if (result) {
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	private boolean BuildBlocks(World world, BlockPos pos1) {
		boolean result = true;
		int dx;
		int dy;
		int dz;

		DataInputStream dis = null;
		try {
			String fileName = "TESTFILE";
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
						BlockPos bp = new BlockPos(i, j, k);

						// DEBUGGING line
						LogHelper.info(bp);
						LogHelper.info("Found block:" + ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)));
						LogHelper.info("State:" + ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getDefaultState());
						LogHelper.info(world.setBlockState(bp, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BlockRegistryName)).getDefaultState()));
						// HOW MANY TO READ
						int propSize = dis.readInt(); // dis.writeInt(world.getBlockState(new BlockPos(i, j, k)).getBlock().getBlockState().getProperties().size());

						for (int l = 0; l < propSize; l++) {

							String propertyName = dis.readUTF();
							String propertyValue = dis.readUTF();
							IProperty ip = (IProperty) world.getBlockState(bp).getProperties().get(propertyName);
							if (ip != null) {
								ip.parseValue(propertyValue);
								world.getBlockState(bp).withProperty(ip, ip.getValueClass());
							}

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

}
