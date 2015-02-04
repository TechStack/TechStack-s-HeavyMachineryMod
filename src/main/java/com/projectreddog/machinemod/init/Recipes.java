package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.item.ItemDrillHead;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {

	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.drillpipe), "i i", "i i", "i i", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.drillhead), " p ", "dpd", " d ", 'p', ModItems.drillpipe, 'd', "gemDiamond"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.woodengear), " s ", "s s", " s ", 's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.stonegear), " c ", "cwc", " c ", 'c', "cobblestone", 'w', "gearWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.irongear), " i ", "isi", " i ", 'i', "ingotIron", 's', "gearStone"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.transmission), "   ", "iii", "   ", 'i', "gearIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.turbofan), " b ", "bib", " b ", 'b', Blocks.iron_bars, 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.camshaft), "   ", " i ", "i i", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.piston), "iii", "iii", " i ", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.turbo), "iii", "fff", "iii", 'i', "ingotIron", 'f', ModItems.turbofan));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.turboengine), "   ", "tet", "   ", 't', ModItems.turbo, 'e', ModItems.engine));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.flatbedtrailer), " pp", "iii", " ww", 'p', "plankWood", 'i', "ingotIron", 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dozerblade), "  i", "  i", "  i", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tracks), "sis", "i i", "sis", 'i', "ingotIron", 's', Blocks.slime_block));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wheel), " s ", "sis", " s ", 'i', "ingotIron", 's', Blocks.slime_block));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wheel), " s ", "sis", " s ", 'i', "ingotIron", 's', Blocks.slime_block));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.loaderbucket), " i ", " hi", "iii", 'i', "ingotIron", 'h', Blocks.hopper));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dumperbed), "ici", "ici", " i ", 'i', "ingotIron", 'c', Blocks.chest));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.engine), "ppp", " c ", "ppp", 'p', ModItems.piston, 'c', ModItems.camshaft));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dumptruck), "  b", "et ", "w w", 'b', ModItems.dumperbed, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.loader), "   ", "bet", "w w", 'b', ModItems.loaderbucket, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tractor), "   ", " et", "w w", 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bulldozer), "   ", " et", "dkk", 'k', ModItems.tracks, 'e', ModItems.engine, 't', ModItems.transmission, 'd', ModItems.dozerblade));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.widebedtruck), "   ", "etf", "ww ", 'e', ModItems.turboengine, 't', ModItems.transmission, 'f', ModItems.flatbedtrailer, 'w', ModItems.wheel));

		// GameRegistry.addRecipe(new ShapedOreRecipe(new
		// ItemStack(ModItems.combine), " cc", "het", " ww", 'c' ,Blocks.chest
		// ,'' ));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sprayer), " c ", " h ", "w w", 'c', Blocks.chest, 'h', Blocks.hopper, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.plow), "   ", "iii", "hhh", 'i', "ingotIron", 'h', Items.iron_hoe));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.planter), "ccc", "hhh", "ddd", 'c', Blocks.chest, 'h', Blocks.hopper, 'd', Blocks.dispenser));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.havesterhead), " s ", "sis", " s ", 's', Items.shears, 'i', "gearIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.combine), " cc", "het", " ww", 'c', Blocks.chest, 'h', ModItems.havesterhead, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));

	}

}
