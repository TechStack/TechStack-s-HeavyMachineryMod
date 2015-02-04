package com.projectreddog.machinemod.item.components;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemTruboFan extends ItemMachineMod {

	public ItemTruboFan() {
		super();
		this.setUnlocalizedName("turbofan");
		this.maxStackSize = 64;

	}

}
