package com.projectreddog.machinemod.world;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ModExplosion extends Explosion {

	private World worldObj;
	private Random explosionRNG = new Random();
	private final double explosionX;
	private final double explosionY;
	private final double explosionZ;
	private final Entity exploder;
	private final float explosionSize;
	private List affectedBlockPositions;
	public boolean isSmoking;

	public ModExplosion(World world, Entity exploder, double explosionX, double explosionY, double explosionZ, float explosionSize) {
		super(world, exploder, explosionX, explosionY, explosionZ, explosionSize, false, true);

		worldObj = world;

		this.exploder = exploder;
		this.affectedBlockPositions = Lists.newArrayList();
		this.explosionX = explosionX;
		this.explosionY = explosionY;
		this.explosionZ = explosionZ;
		this.explosionSize = explosionSize;
		this.isSmoking = true;
	}

	/**
	 * Does the first part of the explosion (destroy blocks)
	 */
	@Override

	public void doExplosionA() {
		Set<BlockPos> set = Sets.<BlockPos> newHashSet();
		int i = 16;

		for (int j = 0; j < 16; ++j) {
			for (int k = 0; k < 16; ++k) {
				for (int l = 0; l < 16; ++l) {
					if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
						double d0 = (double) ((float) j / 15.0F * 2.0F - 1.0F);
						double d1 = (double) ((float) k / 15.0F * 2.0F - 1.0F);
						double d2 = (double) ((float) l / 15.0F * 2.0F - 1.0F);
						double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
						d0 = d0 / d3;
						d1 = d1 / d3;
						d2 = d2 / d3;
						float f = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
						double d4 = this.explosionX;
						double d6 = this.explosionY;
						double d8 = this.explosionZ;

						for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
							BlockPos blockpos = new BlockPos(d4, d6, d8);
							IBlockState iblockstate = this.worldObj.getBlockState(blockpos);

							if (iblockstate.getMaterial() != Material.AIR) {
								float f2 = this.exploder != null ? this.exploder.getExplosionResistance(this, this.worldObj, blockpos, iblockstate) : iblockstate.getBlock().getExplosionResistance(worldObj, blockpos, (Entity) null, this);
								f -= (f2 + 0.3F) * 0.3F;
							}

							if (f > 0.0F && (this.exploder == null || this.exploder.canExplosionDestroyBlock(this, this.worldObj, blockpos, iblockstate, f))) {
								set.add(blockpos);
							}

							d4 += d0 * 0.30000001192092896D;
							d6 += d1 * 0.30000001192092896D;
							d8 += d2 * 0.30000001192092896D;
						}
					}
				}
			}
		}

		this.affectedBlockPositions.addAll(set);
		float f3 = this.explosionSize * 2.0F;
		int k1 = MathHelper.floor(this.explosionX - (double) f3 - 1.0D);
		int l1 = MathHelper.floor(this.explosionX + (double) f3 + 1.0D);
		int i2 = MathHelper.floor(this.explosionY - (double) f3 - 1.0D);
		int i1 = MathHelper.floor(this.explosionY + (double) f3 + 1.0D);
		int j2 = MathHelper.floor(this.explosionZ - (double) f3 - 1.0D);
		int j1 = MathHelper.floor(this.explosionZ + (double) f3 + 1.0D);
		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double) k1, (double) i2, (double) j2, (double) l1, (double) i1, (double) j1));
		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, f3);
		Vec3d vec3d = new Vec3d(this.explosionX, this.explosionY, this.explosionZ);

		for (int k2 = 0; k2 < list.size(); ++k2) {
			Entity entity = list.get(k2);

			if (!entity.isImmuneToExplosions() && (!(entity instanceof EntityItem))) {
				double d12 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double) f3;

				if (d12 <= 1.0D) {
					double d5 = entity.posX - this.explosionX;
					double d7 = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
					double d9 = entity.posZ - this.explosionZ;
					double d13 = (double) MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);

					if (d13 != 0.0D) {
						d5 = d5 / d13;
						d7 = d7 / d13;
						d9 = d9 / d13;
						double d14 = (double) this.worldObj.getBlockDensity(vec3d, entity.getEntityBoundingBox());
						double d10 = (1.0D - d12) * d14;
						entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), (float) ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * (double) f3 + 1.0D)));
						double d11 = d10;

						if (entity instanceof EntityLivingBase) {
							d11 = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, d10);
						}

						entity.motionX += d5 * d11;
						entity.motionY += d7 * d11;
						entity.motionZ += d9 * d11;

						if (entity instanceof EntityPlayer) {
							EntityPlayer entityplayer = (EntityPlayer) entity;

							if (!entityplayer.isSpectator() && (!entityplayer.isCreative() || !entityplayer.capabilities.isFlying)) {
								this.getPlayerKnockbackMap().put(entityplayer, new Vec3d(d5 * d10, d7 * d10, d9 * d10));
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void doExplosionB(boolean p_77279_1_) {

		// this.affectedBlockPositions = this.getAffectedBlockPositions();

		this.worldObj.playSound(null, new BlockPos(this.explosionX, this.explosionY, this.explosionZ), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

		if (this.explosionSize >= 2.0F && this.isSmoking) {
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);

		} else {

			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
		}

		Iterator iterator;
		BlockPos blockpos;
		int i;
		int j;
		int k;
		Block block;

		if (this.isSmoking) {
			iterator = this.affectedBlockPositions.iterator();

			while (iterator.hasNext()) {
				blockpos = (BlockPos) iterator.next();
				i = blockpos.getX();
				j = blockpos.getY();
				k = blockpos.getZ();
				block = this.worldObj.getBlockState(blockpos).getBlock();

				if (p_77279_1_) {
					double d0 = (double) ((float) i + this.worldObj.rand.nextFloat());
					double d1 = (double) ((float) j + this.worldObj.rand.nextFloat());
					double d2 = (double) ((float) k + this.worldObj.rand.nextFloat());
					double d3 = d0 - this.explosionX;
					double d4 = d1 - this.explosionY;
					double d5 = d2 - this.explosionZ;
					double d6 = (double) MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
					d3 /= d6;
					d4 /= d6;
					d5 /= d6;
					double d7 = 0.5D / (d6 / (double) this.explosionSize + 0.1D);
					d7 *= (double) (this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
					d3 *= d7;
					d4 *= d7;
					d5 *= d7;
					this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.explosionX * 1.0D) / 2.0D, (d1 + this.explosionY * 1.0D) / 2.0D, (d2 + this.explosionZ * 1.0D) / 2.0D, d3, d4, d5);
					this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
				}

				if (block.getMaterial(this.worldObj.getBlockState(blockpos)) != Material.AIR) {

					// TS DO NOT DROP BLOCKS !
					// if (block.canDropFromExplosion(this))
					// {
					// block.dropBlockAsItemWithChance(this.worldObj, i, j, k,
					// this.worldObj.getBlockMetadata(i, j, k), 1.0F /
					// this.explosionSize, 0);
					// }
					// TS change next line where it actually breaks the block
					// instead do my call to set the block to the proper type
					// block.onBlockExploded(this.worldObj, i, j, k, this);

					changeBlockType(i, j, k, block);
				}
			}
		}

	}

	private void changeBlockType(int x, int y, int z, Block block) {

		if (block == ModBlocks.machineexplosivepackeddrilledstone) {
			// do the explosion! if it's blasted stone so we can propagate the
			// explosion on to the next block !
			block.onBlockExploded(this.worldObj, new BlockPos(x, y, z), this);
		} else {

			if (!this.worldObj.isRemote) {

				// Determine block to turn this block into
				BlockPos bp = new BlockPos(x, y, z);

				if (this.worldObj.getBlockState(bp).getBlock() == Blocks.STONE) {
					// its stone so get variant
					if (this.worldObj.getBlockState(bp).getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE) {
						this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedstone.getDefaultState());
					} else if (this.worldObj.getBlockState(bp).getValue(BlockStone.VARIANT) == BlockStone.EnumType.GRANITE) {
						this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedgranite.getDefaultState());
					} else if (this.worldObj.getBlockState(bp).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
						this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblasteddiorite.getDefaultState());
					} else if (this.worldObj.getBlockState(bp).getValue(BlockStone.VARIANT) == BlockStone.EnumType.ANDESITE) {
						this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedandesite.getDefaultState());
					}
				}
				// not stone
				else if (this.worldObj.getBlockState(bp).getBlock() == Blocks.GOLD_ORE) {
					this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedgold.getDefaultState());
				} else if (this.worldObj.getBlockState(bp).getBlock() == Blocks.IRON_ORE) {
					this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastediron.getDefaultState());
				} else if (this.worldObj.getBlockState(bp).getBlock() == Blocks.COAL_ORE) {
					this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedcoal.getDefaultState());
				} else if (this.worldObj.getBlockState(bp).getBlock() == Blocks.LAPIS_ORE) {
					this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedlapis.getDefaultState());
				} else if (this.worldObj.getBlockState(bp).getBlock() == Blocks.DIAMOND_ORE) {
					this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblasteddiamond.getDefaultState());
				} else if (this.worldObj.getBlockState(bp).getBlock() == Blocks.REDSTONE_ORE) {
					this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedredstone.getDefaultState());
				} else if (this.worldObj.getBlockState(bp).getBlock() == Blocks.EMERALD_ORE) {
					this.worldObj.setBlockState(new BlockPos(x, y, z), ModBlocks.machineblastedemerald.getDefaultState());
				} else if (this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedgranite || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblasteddiorite || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedandesite
						|| this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedgold || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastediron || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedcoal || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedlapis
						|| this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblasteddiamond || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedredstone || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedemerald || this.worldObj.getBlockState(bp).getBlock() == ModBlocks.machineblastedstone2) {
				} else {
					// check for mod blocks here using ore dictionary & set it
					// to ModBlocks.machinemodblastedstone2 ......

					block.dropBlockAsItem(this.worldObj, bp, this.worldObj.getBlockState(bp), 0);
					block.onBlockExploded(this.worldObj, bp, this);
					// this.worldObj.setBlockState(new BlockPos(x, y, z),
					// ModBlocks.machinemodblastedstone.getDefaultState());
				}
			}

		}

	}

}
