package com.projectreddog.machinemod.item.machines;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.entity.EntityOilRig;
import com.projectreddog.machinemod.model.ModelTransportable;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

public class ItemOilRig extends ItemMachineModMachine {
	public String registryName = "oilrig";

	public ModelTransportable mt;

	public ItemOilRig() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, PlayerEntity
	 */
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, PlayerEntity playerIn) {
		boolean flag = true;
		RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, flag);

		if (movingobjectposition == null) {
			return itemStackIn;
		} else {

			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos blockpos = movingobjectposition.getBlockPos();

				if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
					return itemStackIn;
				}

				if (flag) {
					if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStackIn)) {
						return itemStackIn;
					}

					BlockState BlockState = worldIn.getBlockState(blockpos);
					Material material = BlockState.getBlock().getMaterial(BlockState);

					if (material == Material.WATER && ((Integer) BlockState.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
						if (SpawnOilRig(itemStackIn, playerIn, worldIn, blockpos)) {
							return null;
						} else {
							return itemStackIn;
						}
					}

				}

			}

			return itemStackIn;
		}
	}

	public boolean SpawnOilRig(ItemStack stack, PlayerEntity player, World world, BlockPos pos) {
		boolean result = false;

		if (!world.isRemote)// / only run on server
		{

			if (BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.OCEAN) && pos.getY() > 60 && world.isAirBlock(pos.up()) && world.getBlockState(pos).getBlock() == Blocks.WATER) {

				// LogHelper.info("Item used on loader!");
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				EntityMachineModRideable entityOilRig = getEntityToSpawn(world);
				entityOilRig.setPosition(x + .5d, y + 1.0d, z + .5d);
				entityOilRig.prevPosX = x + .5d;
				entityOilRig.prevPosY = y + 1.0d;
				entityOilRig.prevPosZ = z + .5d;
				result = world.spawnEntity(entityOilRig);
				// LogHelper.info("Spawn entity resutl:" + result );
				if (result && !player.capabilities.isCreativeMode) {
					stack.setCount(stack.getCount() - 1);
				}
			} else {
				player.sendStatusMessage(new TextComponentTranslation("You can only place an oil rig on an ocean's surface!"), false);

			}
		}
		return result;
	}

	public EntityMachineModRideable getEntityToSpawn(World world) {
		return new EntityOilRig(world);

	}

}
