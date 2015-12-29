package com.projectreddog.machinemod.tileentities;

import com.mojang.authlib.properties.Property;
import com.projectreddog.machinemod.iface.ILiquidConnection;
import com.projectreddog.machinemod.iface.ILiquidPipe;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityLiquidPipe extends TileEntity implements IUpdatePlayerListBox,ILiquidPipe {


	public TileEntityLiquidPipe() {
		
	}

	@Override
	public void update() {
		for ( EnumFacing e : EnumFacing.VALUES){
			if(this.worldObj.getTileEntity(  this.pos.offset(e)) instanceof ILiquidConnection){
				LogHelper.info("Connection point found to the : " + e.toString());
			}
		}

	}

}