package com.projectreddog.machinemod.block;

import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.google.common.collect.ImmutableMap;
import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;

public class BlockMoonshine extends BlockFluidClassic    {

	public BlockMoonshine(Fluid fluid, Material material) {
		super(fluid, material);
		// TODO Auto-generated constructor stub
		setCreativeTab(CreativeTabMachineMod.MACHINEMOD_TAB);
		this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" +Reference.MODBLOCK_MACHINE_FLUID_MOONSHINE);

	}
	
	

	 
    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos bp) {
            if (world.getBlockState(bp).getBlock().getMaterial().isLiquid()) return false;
            return super.canDisplace(world, bp);
    }
    
    @Override
    public boolean displaceIfPossible(World world, BlockPos bp) {
            if (world.getBlockState(bp).getBlock().getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, bp);
    }

   @Override
    protected void flowIntoBlock(World world, BlockPos pos, int meta)
    {
        if (meta < 0) return;
        if (displaceIfPossible(world, pos))
        {
        	world.setBlockState(pos, this.getBlockState().getBaseState().withProperty(LEVEL, meta), 3);
        			
        			
  
        	}
    }


}
