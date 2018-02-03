package com.projectreddog.machinemod.village;

import java.util.Random;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class TradeListEngineer implements ITradeList {

	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {

		recipeList.add(0, new MerchantRecipe(new ItemStack(Blocks.EMERALD_BLOCK, 5), new ItemStack(ModItems.blueprintcontinuousminer)));
	}

}
