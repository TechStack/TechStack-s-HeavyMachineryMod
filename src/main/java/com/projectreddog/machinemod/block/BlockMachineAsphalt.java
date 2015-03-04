package com.projectreddog.machinemod.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.reference.Reference;

public class BlockMachineAsphalt extends BlockMachineMod {

	public BlockMachineAsphalt() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_ASPHALT);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
	}

	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		float f = 0.125F;
		return new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), (double) (pos.getX() + 1), (double) ((float) (pos.getY() + 1) - f), (double) (pos.getZ() + 1));
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
