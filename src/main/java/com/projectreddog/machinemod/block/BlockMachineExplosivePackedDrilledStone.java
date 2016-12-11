package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.world.ModExplosion;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMachineExplosivePackedDrilledStone extends BlockMachineModManyTexture {
	public BlockMachineExplosivePackedDrilledStone() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_EXPLOSIVE_PACKED_DRILLED_STONE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_EXPLOSIVE_PACKED_DRILLED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5f);

	}

	@Override
	// public void onNeighborBlockChange(World worldIn, BlockPos pos,
	// IBlockState state, Block neighborBlock) {}
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)

	{
		// public void onNeighborChange(IBlockAccess world, BlockPos bp, BlockPos neighbor) {
		// public void onNeighborBlockChange(World world, BlockPos bp, IBlockState bs, Block neighborBlock) {

		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));

	}

	@Override
	// public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos,
	// Explosion explosionIn) {}
	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {

		detonate(world, pos.getX(), pos.getY(), pos.getZ());
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (worldIn.isBlockPowered(pos)) {
			detonate(worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	public void detonate(World world, int x, int y, int z) {

		// if (!world.isRemote){

		ModExplosion explosion = newExplosion(world, x, y, z, 8.0F, false, true);

		// world.createExplosion(p_72876_1_, p_72876_2_, p_72876_4_, p_72876_6_,
		// p_72876_8_, p_72876_9_)
		// explosion.affectedBlockPositions

		// world.setBlockToAir(x,y,z);
		// }

	}

	// (World p_i1948_1_, Entity p_i1948_2_, double p_i1948_3_, double
	// p_i1948_5_, double p_i1948_7_, float size)
	/**
	 * returns a new explosion. Does initiation (at time of writing Explosion is not finished)
	 */
	public ModExplosion newExplosion(World world, double x, double y, double z, float size, boolean flameing, boolean smoking) {

		ModExplosion explosion = new ModExplosion(world, (Entity) null, x, y, z, size);
		// explosion.isFlaming = flameing;
		explosion.isSmoking = smoking;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		return explosion;
	}

}
