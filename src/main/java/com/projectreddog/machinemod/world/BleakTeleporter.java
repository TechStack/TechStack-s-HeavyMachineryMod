package com.projectreddog.machinemod.world;

import javax.annotation.Nonnull;

import com.projectreddog.machinemod.block.BlockMachineBleakPortal;
import com.projectreddog.machinemod.block.BlockMachineBleakPortalFrame;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModDimensions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntityMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class BleakTeleporter extends Teleporter {

	private final WorldServer worldServer;
	private double x;
	private double y;
	private double z;
	private BlockPos portalBlockPos;
	private Direction portalFacing;

	public BleakTeleporter(WorldServer world, double x, double y, double z, BlockPos portalBlockPos, Direction portalFacing) {
		super(world);
		this.worldServer = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.portalBlockPos = portalBlockPos;
		this.portalFacing = portalFacing;
	}

	@Override
	public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
		// TODO : ADD A PORATAL LATER LOOK AT THE SUPER CLASS to see how it handles this with a nether portal !
		// The main purpose of this function is to *not* create a nether portal

		if (this.world.provider.getDimensionType().getId() == ModDimensions.bleakDimID) {
			if (!this.placeInExistingPortal(entity, rotationYaw)) {
				this.makePortal(entity);
				// this.placeInExistingPortal(entity, rotationYaw);
			}
		}

		this.worldServer.getBlockState(new BlockPos((int) this.x, (int) this.y, (int) this.z));

		entity.setPosition(this.x, this.y, this.z);
		entity.motionX = 0.0f;
		entity.motionY = 0.0f;
		entity.motionZ = 0.0f;
	}

	public static void teleportToDimension(ServerPlayerPlayerEntity, int dimension, double x, double y, double z, BlockPos portalBlockPos, Direction portalFacing) {
		int oldDimension = player.getEntityWorld().provider.getDimension();
		ServerPlayerEntityServerPlayerEntity= (PlayerEntityMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);

		if (worldServer == null || worldServer.getMinecraftServer() == null) { // Dimension doesn't exist
			throw new IllegalArgumentException("Dimension: " + dimension + " doesn't exist!");
		}

		worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(PlayerEntityMP, dimension, new BleakTeleporter(worldServer, x, y, z, portalBlockPos, portalFacing));
		player.setPositionAndUpdate(x, y, z);
		if (oldDimension == 1) {
			// For some reason teleporting out of the end does weird things. Compensate for that
			player.setPositionAndUpdate(x, y, z);
			worldServer.spawnEntity(player);
			worldServer.updateEntityWithOptionalForce(player, false);
		}
	}

	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		boolean result = false;
		if (this.world.getBlockState(portalBlockPos).getBlock() == ModBlocks.machinebleakportal) {
			return true;
		}
		return result;

	}

	public boolean makePortal(Entity entityIn) {
		boolean result = DigHoleAround(portalBlockPos);
		result = result && PlacePortalBlocks(portalBlockPos);

		return result;
	}

	public boolean PlacePortalBlocks(BlockPos bp) {
		boolean result = true;
		BlockPos center = bp;
		BlockPos side1 = bp;
		BlockPos side2 = bp;
		Direction side1Facing;
		Direction side2Facing;

		if (portalFacing == Direction.EAST || portalFacing == Direction.WEST) {
			side1 = side1.offset(Direction.EAST);
			side1Facing = Direction.WEST;
			side2 = side2.offset(Direction.WEST);
			side2Facing = Direction.EAST;

		} else { // must be N or S ( No Up Or Down on the protal block)

			side1 = side1.offset(Direction.NORTH);
			side1Facing = Direction.SOUTH;
			side2 = side2.offset(Direction.SOUTH);
			side2Facing = Direction.NORTH;
		}

		for (int i = 0; i < 3; i++) {

			// use flag 2 to prevent block updates from breaking the portal as it is formed.
			world.setBlockState(center.add(0, i, 0), ModBlocks.machinebleakportal.getDefaultState().withProperty(BlockMachineBleakPortal.FACING, side1Facing), 2);
			world.setBlockState(side1.add(0, i, 0), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, side1Facing).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, true), 2);
			world.setBlockState(side2.add(0, i, 0), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, side2Facing).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, true), 2);

		}

		return result;
	}

	public boolean DigHoleAround(BlockPos bp) {
		boolean result = true;

		for (int x = -5; x < 6; x++) {
			for (int z = -5; z < 6; z++) {
				for (int y = 0; y < 11; y++) {

					this.world.setBlockToAir(bp.add(x, y, z));
				}
				y = 0;
				this.world.setBlockState((bp.add(x, y - 1, z)), ModBlocks.machinebleakstone.getDefaultState());

				this.world.setBlockState((bp.add(x, y + 11, z)), ModBlocks.machinebleakstone.getDefaultState());

			}

		}

		return result;
	}

}
