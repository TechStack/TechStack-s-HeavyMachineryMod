package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityRoadRoller;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachineCompressedAsphaltSlab extends BlockMachineMod {

	protected static final AxisAlignedBB COMPRESSED_ASPHALT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);

	public BlockMachineCompressedAsphaltSlab() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(15f).sound(SoundType.STONE));
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_COMPRESSED_ASPHALT_SLAB);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_COMPRESSED_ASPHALT_SLAB);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);

	}

	@Override
	public boolean isOpaqueCube(BlockState state) {
		return false;
	}

	/**
	 * Determines if an entity can path through this block
	 */
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;

	}

	@Override
	public boolean isFullCube(BlockState state) {
		return false;
	}

	@Override

	public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
		return COMPRESSED_ASPHALT_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(BlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return COMPRESSED_ASPHALT_AABB;
	}

	/**
	 * Called When an Entity Collided with the Block
	 */
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, BlockState state, Entity entity) {
		double savedSpeed = Math.sqrt(entity.motionZ * entity.motionZ + entity.motionX * entity.motionX);
		if (!(entity instanceof PlayerEntity)) {
			// not a player
			if (!(entity instanceof EntityMachineModRideable)) {
				// not a machine mod rideable
				return;

			} else if ((entity instanceof EntityMachineModRideable) && ((entity instanceof EntityRoadRoller) || (entity instanceof EntityPaver))) {
				return;

			} else if (!((EntityMachineModRideable) entity).isPlayerAccelerating && !((EntityMachineModRideable) entity).isPlayerBreaking) {
				// is a machine mod rideable but no movment
				return;
			}

		} else if (((PlayerEntity) entity).moveForward < .8f && ((PlayerEntity) entity).moveStrafing < .8f) {
			return;
			// player no longer trying to move
		}

		if (savedSpeed != 0) {

			entity.motionZ = entity.motionZ * .4 / savedSpeed;
			entity.motionX = entity.motionX * .4 / savedSpeed;
		}
	}
}
