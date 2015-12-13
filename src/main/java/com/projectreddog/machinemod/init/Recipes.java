package com.projectreddog.machinemod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
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

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tracks), "sis", "i i", "sis", 'i', "ingotIron", 's', Items.slime_ball));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wheel), " s ", "sis", " s ", 'i', "ingotIron", 's', Items.slime_ball));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wheel), " s ", "sis", " s ", 'i', "ingotIron", 's', Items.slime_ball));

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

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.hose), " s ", " s ", " s ", 's', Items.slime_ball));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinefuelpump), "ggh", "ic ", "ii ", 'g', Blocks.glass, 'h', ModItems.hose, 'i', "ingotIron", 'c', Items.cauldron));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinedistiller), "h  ", "bcb", "fff", 'h', ModItems.hose, 'c', Items.cauldron, 'b', Items.brewing_stand, 'f', Blocks.furnace));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinefermenter), "www", "wcw", "h  ", 'h', ModItems.hose, 'c', Blocks.chest, 'w', "plankWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lidwithspout), "s  ", "sii", "   ", 's', Items.slime_ball, 'i', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.fuelcan, 1, ModItems.fuelcan.getMaxDamage()), "   ", " l ", " b ", 'l', ModItems.lidwithspout, 'b', Items.bucket));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.boomarmsegment), "b  ", " b ", "  b", 'b', Blocks.iron_block));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.excavator), " bg", "bet", " rr", 'b', ModItems.boomarmsegment, 'e', ModItems.engine, 'g', Blocks.glass, 't', ModItems.transmission, 'r', ModItems.tracks));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.drillingrig), "pgg", "pet", "drr", 'p', ModItems.drillpipe, 'g', Blocks.glass, 'e', ModItems.engine, 't', ModItems.transmission, 'd', ModItems.drillhead, 'r', ModItems.tracks));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.trencher), "i  ", "i  ", " i ", 'i', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.handdrill, 1, ModItems.handdrill.getMaxDamage()), "   ", "dpi", "  i", 'i', "ingotIron", 'd', ModItems.drillhead, 'p', ModItems.drillpipe));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sprayer), " c ", " h ", "w w", 'c', Blocks.chest, 'h', Blocks.hopper, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.plow), "   ", "iii", "hhh", 'i', "ingotIron", 'h', Items.iron_hoe));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.planter), "ccc", "hhh", "ddd", 'c', Blocks.chest, 'h', Blocks.hopper, 'd', Blocks.dispenser));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.havesterhead), " s ", "sis", " s ", 's', Items.shears, 'i', "gearIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.combine), " cc", "het", " ww", 'c', Blocks.chest, 'h', ModItems.havesterhead, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.conecrusher), "   ", " i ", " b ", 'i', "ingotIron", 'b', Blocks.iron_block));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineconveyor, 4), "lll", "rir", "lll", 'l', Items.leather, 'r', Items.redstone, 'i', Items.iron_ingot));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineprimarycrhuser), "o o", "o o", "scs", 'o', Blocks.obsidian, 's', Blocks.sticky_piston, 'c', ModItems.conecrusher));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.anfo, 16), "bbb", "bfb", "bbb", 'b', new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeDamage()), 'f', ModItems.fuelcan.setContainerItem(ModItems.fuelcan)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinescreen, 1), "d d", "pbp", "iii", 'b', Blocks.iron_bars, 'd', Items.diamond, 'p', Blocks.piston, 'i', Items.iron_ingot));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mowerdeck, 1), "   ", "iii", "sss", 'i', "ingotIron", 's', Items.shears));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lawnmower, 1), "   ", " e ", "wmw", 'e', ModItems.engine, 'w', ModItems.wheel, 'm', ModItems.mowerdeck));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.graderblade, 1), " i ", " i ", "i  ", 'i', Items.iron_ingot));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.grader, 1), "  g", " et", "wbw", 'g', Blocks.glass, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel, 'b', ModItems.graderblade));

		// adding bagger related recipies
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tracksegment, 1), "   ", " i ", "ttt", 'i', Blocks.iron_block, 't', ModItems.tracks));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cutterbucket, 1), "e e", "i i", "iii", 'e', Blocks.emerald_block, 'i', Items.iron_ingot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rigging, 1), " b ", "bbb", "   ", 'b', ModItems.boomarmsegment));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bucketwheel, 1), "bbb", "bib", "bbb", 'b', ModItems.cutterbucket, 'i', Blocks.iron_block));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.operatorsbooth, 1), "gbb", "g i", "iii", 'g', Blocks.glass, 'b', Blocks.iron_bars, 'i', Items.iron_ingot));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.powerplant, 1), "   ", "ttt", "eee", 't', ModItems.turbo, 'e', ModItems.turboengine));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.processingplant, 1), "csc", "sps", "csc", 'c', ModBlocks.machineconveyor, 's', ModBlocks.machinescreen, 'p', ModBlocks.machineprimarycrhuser));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.baggerstorge, 1), "iii", "ici", "iii", 'i', Items.iron_ingot, 'c', Blocks.chest));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.baggerbody, 1), "cpc", "isi", "clc", 'c', ModBlocks.machineconveyor, 'p', ModBlocks.machineprimarycrhuser, 'i', Blocks.iron_block, 's', Blocks.chest, 'l', Items.lava_bucket));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bagger, 1), "br ", "obe", " tt", 'b', ModItems.bucketwheel, 'r', ModItems.rigging, 'o', ModItems.operatorsbooth, 'b', ModItems.baggerbody, 'e', ModItems.powerplant, 't', ModItems.tracksegment));

		;

		// D D
		// PBP
		// III

		SmeltingRecipes();
	}

	public static void SmeltingRecipes() {
		GameRegistry.addSmelting(ModItems.irondust, new ItemStack(Items.iron_ingot), 0);
		GameRegistry.addSmelting(ModItems.golddust, new ItemStack(Items.gold_ingot), 0);
	}
}
