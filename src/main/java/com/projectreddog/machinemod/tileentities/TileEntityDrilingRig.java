package com.projectreddog.machinemod.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.utility.LogHelper;

public class TileEntityDrilingRig extends TileEntity  implements IUpdatePlayerListBox{

	private int currentDrillingLevel =1;
	private int EnergyLevel =0;
	private int startingLevel=0;

	public TileEntityDrilingRig(){

	}

	public TileEntityDrilingRig(int currentDrillingLevel){
		this.currentDrillingLevel = currentDrillingLevel;
		startingLevel=currentDrillingLevel;
	}

	@Override
	public void update(){

		LogHelper.info("TE update entity called");

		if (currentDrillingLevel < startingLevel +16){
			if (worldObj.getBlockState(this.getPos().offset(EnumFacing.DOWN, this.currentDrillingLevel)).getBlock() ==Blocks.stone
					)
			{

				//worldObj.setBlockState(this.getPos().offset(EnumFacing.DOWN, this.current_drilling_level),ModBlocks.machinedrilledstone.getDefaultState());

				worldObj.setBlockState(this.getPos().offset(EnumFacing.DOWN, this.currentDrillingLevel),ModBlocks.machineexplosivepackeddrilledstone.getDefaultState());


			}
			currentDrillingLevel=currentDrillingLevel+1;
		}
	}

}
