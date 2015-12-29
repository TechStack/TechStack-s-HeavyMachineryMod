package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityConveyor;
import com.projectreddog.machinemod.tileentities.TileEntityLiquidPipe;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineModLiquidPipe extends BlockContainer {

	public BlockMachineModLiquidPipe(Material material) {
		super( material);
		// 1.8
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_LIQUID_PIPE);
		// this.setBlockName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5f);

	}
	public BlockMachineModLiquidPipe() {
		// Generic constructor (set to rock by default)
		this(Material.rock);
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityLiquidPipe();
	}

	
	@Override
	public int getRenderType() {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		return 3;
	}
	
	
	
	}
