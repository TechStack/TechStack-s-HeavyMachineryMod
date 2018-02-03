package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachineCompressedAsphalt extends BlockMachineMod {

	protected static final AxisAlignedBB COMPRESSED_ASPHALT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

	public BlockMachineCompressedAsphalt() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_COMPRESSED_ASPHALT);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_COMPRESSED_ASPHALT);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return COMPRESSED_ASPHALT_AABB;
	}

	/**
	 * Called When an Entity Collided with the Block
	 */
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		double savedSpeed = Math.sqrt(entity.motionZ * entity.motionZ + entity.motionX * entity.motionX);
		if (!(entity instanceof EntityPlayer)) {
			// not a player
			if (!(entity instanceof EntityMachineModRideable)) {
				// not a machine mod rideable
				return;

			} else if (!((EntityMachineModRideable) entity).isPlayerAccelerating && !((EntityMachineModRideable) entity).isPlayerBreaking) {
				// is a machine mod rideable but no movment
				return;
			}

		} else if (((EntityPlayer) entity).moveForward < .8f && ((EntityPlayer) entity).moveStrafing < .8f) {
			return;
			// player no longer trying to move
		}

		if (savedSpeed != 0) {

			entity.motionZ = entity.motionZ * .4 / savedSpeed;
			entity.motionX = entity.motionX * .4 / savedSpeed;
		}
	}
}
