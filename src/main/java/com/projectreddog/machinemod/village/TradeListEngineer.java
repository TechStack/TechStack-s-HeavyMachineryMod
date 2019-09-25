package com.projectreddog.machinemod.village;

import java.util.Random;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class TradeListEngineer implements ITradeList {

	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {

		recipeList.add(0, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 2), new ItemStack(ModItems.blueprintcontinuousminer)));
		recipeList.add(1, new MerchantRecipe(new ItemStack(Items.EMERALD, 3), new ItemStack(ModItems.blueprintconduit)));
		recipeList.add(2, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 1), new ItemStack(ModItems.blueprintfactory)));
		recipeList.add(3, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 1), new ItemStack(ModItems.blueprintgenerator)));
		recipeList.add(4, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 1), new ItemStack(ModItems.blueprintbatterybank)));
		recipeList.add(5, new MerchantRecipe(new ItemStack(Items.EMERALD, 5), new ItemStack(ModItems.blueprintturbofurnace)));
		recipeList.add(6, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 1), new ItemStack(ModItems.blueprintshredder)));
		recipeList.add(7, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 2), new ItemStack(ModItems.blueprintholoscanner)));
		recipeList.add(8, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 4), new ItemStack(ModItems.blueprintlaserminer)));
		recipeList.add(8, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 1), new ItemStack(ModItems.blueprinttowercrane)));

	}

}
