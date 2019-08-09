package com.projectreddog.machinemod.item.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TruboFurnaceRecipes {

	private static final TruboFurnaceRecipe SMELTING_BASE = new TruboFurnaceRecipe();
	/** The list of smelting results. */
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	/** A list which contains how many experience points each recipe output will give. */
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	/**
	 * Returns an instance of FurnaceRecipe.
	 */
	public static TruboFurnaceRecipe instance() {
		return SMELTING_BASE;
	}

	private TruboFurnaceRecipe() {
		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakoremagentia, new ItemStack(ModItems.magentiaingot), 1.5F);
		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakorecitronite, new ItemStack(ModItems.citroniteingot), 1.5F);
		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakoreiridonium, new ItemStack(ModItems.iridoniumingot), 1.5F);
		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakorelimoniteum, new ItemStack(ModItems.limoniteumingot), 1.5F);
		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakoreunobtanium, new ItemStack(ModItems.unobtaniumgem), 1.5F);

		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakoreazurium, new ItemStack(ModItems.azuriumlump), 1.5F);

		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakorecrimsonite, new ItemStack(ModItems.crimsonitepebble), 1.5F);

		this.addSmeltingRecipeForBlock(ModBlocks.machinebleakstone, new ItemStack(ModItems.rawasphalt), 1.5F);

		// this.addSmeltingRecipeForBlock(Blocks.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 1.0F);

		// this.addSmeltingRecipeForBlock(Blocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND), 1.0F);
		// this.addSmeltingRecipeForBlock(Blocks.SAND, new ItemStack(Blocks.GLASS), 0.1F);
		// this.addSmelting(Items.PORKCHOP, new ItemStack(Items.COOKED_PORKCHOP), 0.35F);
		// this.addSmelting(Items.BEEF, new ItemStack(Items.COOKED_BEEF), 0.35F);

	}

	/**
	 * Adds a smelting recipe, where the input item is an instance of Block.
	 */
	public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience) {
		this.addSmelting(Item.getItemFromBlock(input), stack, experience);
	}

	/**
	 * Adds a smelting recipe using an Item as the input item.
	 */
	public void addSmelting(Item input, ItemStack stack, float experience) {
		this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
	}

	/**
	 * Adds a smelting recipe using an ItemStack as the input for the recipe.
	 */
	public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience) {
		if (getSmeltingResult(input) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack);
			return;
		}
		this.smeltingList.put(input, stack);
		this.experienceList.put(stack, Float.valueOf(experience));
	}

	/**
	 * Returns the smelting result of an item.
	 */
	public ItemStack getSmeltingResult(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
			if (this.compareItemStacks(stack, entry.getKey())) {
				return entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}

	/**
	 * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
	 */
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Map<ItemStack, ItemStack> getSmeltingList() {
		return this.smeltingList;
	}

	public float getSmeltingExperience(ItemStack stack) {
		float ret = stack.getItem().getSmeltingExperience(stack);
		if (ret != -1)
			return ret;

		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, entry.getKey())) {
				return ((Float) entry.getValue()).floatValue();
			}
		}

		return 0.0F;
	}
}
