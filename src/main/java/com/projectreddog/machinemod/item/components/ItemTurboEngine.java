package com.projectreddog.machinemod.item.components;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemTurboEngine extends ItemMachineMod {

	public ItemTurboEngine() {
		super();
		this.setUnlocalizedName("turboengine");
		this.maxStackSize = 64;

	}

}
