package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.item.ItemDrillHead;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipies {

	public static void init()
	{
		
		GameRegistry.addRecipe(new ShapedOreRecipe(  new ItemStack(ModItems.drillhead)," p ","dpd"," d ",'p', "ingotIron",'d',"gemDiamond"));
	}
}
