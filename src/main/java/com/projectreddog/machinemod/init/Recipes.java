package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {

	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.drillpipe), "i i", "i i", "i i", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.drillhead), " p ", "dpd", " d ", 'p', ModItems.drillpipe, 'd', "gemDiamond"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.woodengear), " s ", "s s", " s ", 's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.stonegear), " c ", "cwc", " c ", 'c', "cobblestone", 'w', "gearWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.irongear), " i ", "isi", " i ", 'i', "ingotIron", 's', "gearStone"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.transmission), "   ", "iii", "   ", 'i', "gearIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.turbofan), " b ", "bib", " b ", 'b', Blocks.IRON_BARS, 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.camshaft), "   ", " i ", "i i", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.piston), "iii", "iii", " i ", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.turbo), "iii", "fff", "iii", 'i', "ingotIron", 'f', ModItems.turbofan));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.turboengine), "   ", "tet", "   ", 't', ModItems.turbo, 'e', ModItems.engine));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.flatbedtrailer), " pp", "iii", " ww", 'p', "plankWood", 'i', "ingotIron", 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dozerblade), "  i", "  i", "  i", 'i', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tracks), "sis", "i i", "sis", 'i', "ingotIron", 's', "slimeball"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wheel), " s ", "sis", " s ", 'i', "ingotIron", 's', "slimeball"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wheel), " s ", "sis", " s ", 'i', "ingotIron", 's', "slimeball"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.loaderbucket), " i ", " hi", "iii", 'i', "ingotIron", 'h', Blocks.HOPPER));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dumperbed), "ici", "ici", " i ", 'i', "ingotIron", 'c', Blocks.CHEST));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.engine), "ppp", " c ", "ppp", 'p', ModItems.piston, 'c', ModItems.camshaft));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineliquidPipe), "ss ", "   ", "ss ", 's', "ingotSteel"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.steelblock), "sss", "sss", "sss", 's', "ingotSteel"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinefractionaldistillation), "ibi", "lbl", "isi", 's', Blocks.BREWING_STAND, 'i', Blocks.IRON_BLOCK, 'l', ModBlocks.machineliquidPipe, 'b', Blocks.IRON_BARS));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.steeldust), ModItems.carbondust, ModItems.irondust));

		if (Reference.enableDumptruck) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dumptruck), "  b", "et ", "w w", 'b', ModItems.dumperbed, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));
		}
		if (Reference.enableLoader) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.loader), "   ", "bet", "w w", 'b', ModItems.loaderbucket, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));
		}

		if (Reference.enableTractor) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tractor), "   ", " et", "w w", 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));
		}

		if (Reference.enableBulldozer) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bulldozer), "   ", " et", "dkk", 'k', ModItems.tracks, 'e', ModItems.engine, 't', ModItems.transmission, 'd', ModItems.dozerblade));
		}
		if (Reference.enableSemiTractor) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.semitractor), "o  ", "et ", "ww ", 'o', ModItems.operatorsbooth, 'e', ModItems.turboengine, 't', ModItems.transmission, 'w', ModItems.wheel));
		}
		// GameRegistry.addRecipe(new ShapedOreRecipe(new
		// ItemStack(ModItems.combine), " cc", "het", " ww", 'c' ,Blocks.chest
		// ,'' ));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.hose), " s ", " s ", " s ", 's', "slimeball"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinefuelpump), "ggh", "ic ", "ii ", 'g', Blocks.GLASS, 'h', ModItems.hose, 'i', "ingotIron", 'c', Items.CAULDRON));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinedistiller), "h  ", "bcb", "fff", 'h', ModItems.hose, 'c', Items.CAULDRON, 'b', Items.BREWING_STAND, 'f', Blocks.FURNACE));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinefermenter), "www", "wcw", "h  ", 'h', ModItems.hose, 'c', Blocks.CHEST, 'w', "plankWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lidwithspout), "s  ", "sii", "   ", 's', "slimeball", 'i', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.fuelcan, 1, ModItems.fuelcan.getMaxDamage()), "   ", " l ", " b ", 'l', ModItems.lidwithspout, 'b', Items.BUCKET));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.boomarmsegment), "b  ", " b ", "  b", 'b', Blocks.IRON_BLOCK));
		if (Reference.enableExcavator) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.excavator), " bg", "bet", " rr", 'b', ModItems.boomarmsegment, 'e', ModItems.engine, 'g', Blocks.GLASS, 't', ModItems.transmission, 'r', ModItems.tracks));
		}
		if (Reference.enableDrillingRig) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.drillingrig), "pgg", "pet", "drr", 'p', ModItems.drillpipe, 'g', Blocks.GLASS, 'e', ModItems.engine, 't', ModItems.transmission, 'd', ModItems.drillhead, 'r', ModItems.tracks));
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.trencher), "i  ", "i  ", " i ", 'i', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.handdrill, 1, ModItems.handdrill.getMaxDamage()), "   ", "dpi", "  i", 'i', "ingotIron", 'd', ModItems.drillhead, 'p', ModItems.drillpipe));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sprayer), " c ", " h ", "w w", 'c', Blocks.CHEST, 'h', Blocks.HOPPER, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.plow), "   ", "iii", "hhh", 'i', "ingotIron", 'h', Items.IRON_HOE));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.planter), "ccc", "hhh", "ddd", 'c', Blocks.CHEST, 'h', Blocks.HOPPER, 'd', Blocks.DISPENSER));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.havesterhead), " s ", "sis", " s ", 's', Items.SHEARS, 'i', "gearIron"));
		if (Reference.enableCombine) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.combine), " cc", "het", " ww", 'c', Blocks.CHEST, 'h', ModItems.havesterhead, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.conecrusher), "   ", " i ", " b ", 'i', "ingotIron", 'b', Blocks.IRON_BLOCK));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineconveyor, 4), "lll", "rir", "lll", 'l', Items.LEATHER, 'r', Items.REDSTONE, 'i', Items.IRON_INGOT));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineprimarycrhuser), "o o", "o o", "scs", 'o', Blocks.OBSIDIAN, 's', Blocks.STICKY_PISTON, 'c', ModItems.conecrusher));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.anfo, 16), "bbb", "bfb", "bbb", 'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()), 'f', ModItems.fuelcan.setContainerItem(ModItems.fuelcan)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinescreen, 1), "d d", "pbp", "iii", 'b', Blocks.IRON_BARS, 'd', Items.DIAMOND, 'p', Blocks.PISTON, 'i', Items.IRON_INGOT));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mowerdeck, 1), "   ", "iii", "sss", 'i', "ingotIron", 's', Items.SHEARS));

		if (Reference.enableLawnmower) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lawnmower, 1), "   ", " e ", "wmw", 'e', ModItems.engine, 'w', ModItems.wheel, 'm', ModItems.mowerdeck));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.graderblade, 1), " i ", " i ", "i  ", 'i', Items.IRON_INGOT));

		if (Reference.enableGrader) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.grader, 1), "  g", " et", "wbw", 'g', Blocks.GLASS, 'e', ModItems.engine, 't', ModItems.transmission, 'w', ModItems.wheel, 'b', ModItems.graderblade));
		}
		// adding bagger related recipies
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tracksegment, 1), "   ", " i ", "ttt", 'i', Blocks.IRON_BLOCK, 't', ModItems.tracks));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cutterbucket, 1), "e e", "i i", "iii", 'e', Blocks.EMERALD_BLOCK, 'i', Items.IRON_INGOT));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rigging, 1), " b ", "bbb", "   ", 'b', ModItems.boomarmsegment));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bucketwheel, 1), "bbb", "bib", "bbb", 'b', ModItems.cutterbucket, 'i', Blocks.IRON_BLOCK));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.operatorsbooth, 1), "gbb", "g i", "iii", 'g', Blocks.GLASS, 'b', Blocks.IRON_BARS, 'i', Items.IRON_INGOT));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.powerplant, 1), "   ", "ttt", "eee", 't', ModItems.turbo, 'e', ModItems.turboengine));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.processingplant, 1), "csc", "sps", "csc", 'c', ModBlocks.machineconveyor, 's', ModBlocks.machinescreen, 'p', ModBlocks.machineprimarycrhuser));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.baggerstorage, 1), "iii", "ici", "ici", 'i', Items.IRON_INGOT, 'c', Blocks.CHEST));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.baggerbody, 1), "cpc", "isi", "clc", 'c', ModBlocks.machineconveyor, 'p', ModBlocks.machineprimarycrhuser, 'i', Blocks.IRON_BLOCK, 's', Blocks.CHEST, 'l', Items.LAVA_BUCKET));
		if (Reference.enableBagger) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.bagger, 1), "wr ", "obe", " tt", 'w', ModItems.bucketwheel, 'r', ModItems.rigging, 'o', ModItems.operatorsbooth, 'b', ModItems.baggerbody, 'e', ModItems.powerplant, 't', ModItems.tracksegment));
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rollerwheel, 1), " i ", "ibi", " i ", 'i', Items.IRON_INGOT, 'b', Blocks.IRON_BLOCK));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.roadroller, 1), "   ", " e ", "wtw", 'e', ModItems.engine, 'w', ModItems.rollerwheel, 't', ModItems.transmission));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.paverscreed, 1), "   ", "  h", "iii", 'h', Blocks.HOPPER, 'i', Items.IRON_INGOT));
		if (Reference.enablePaver) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.paver, 1), "  c", " et", "sww", 'c', Blocks.CHEST, 'e', ModItems.engine, 't', ModItems.transmission, 's', ModItems.paverscreed, 'w', ModItems.wheel));
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.prop, 1), " g ", "gig", " g ", 'i', Items.IRON_INGOT, 'g', Items.GOLD_INGOT));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.propcage, 1), "ibi", "b b", "ibi", 'i', Items.IRON_INGOT, 'b', Blocks.IRON_BARS));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.turboprop, 1), " c ", "cpc", " c ", 'c', ModItems.propcage, 'p', ModItems.prop));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.operatorsbubble, 1), "bib", "igi", "bib", 'i', Items.IRON_INGOT, 'b', Blocks.IRON_BARS, 'g', Blocks.GLASS));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.subbody, 1), "gig", "ibi", "gig", 'i', Items.IRON_INGOT, 'g', Items.GOLD_INGOT, 'b', Items.BOAT));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.airtank, 1), " i ", "ibi", "iii", 'i', Items.IRON_INGOT, 'b', Items.GLASS_BOTTLE));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.powercell, 1), "tct", "rrr", "rrr", 't', Blocks.REDSTONE_TORCH, 'c', Items.COMPARATOR, 'r', Blocks.REDSTONE_BLOCK));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.light, 1), " r ", "rtr", " r ", 'r', Blocks.REDSTONE_LAMP, 't', Blocks.REDSTONE_TORCH));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lightmodule, 1), " l ", "lrl", " l ", 'r', ModItems.powercell, 'l', ModItems.light));
		if (Reference.enableSub) {

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sub, 1), "tlt", "ose", "tat", 't', ModItems.turboprop, 'l', ModItems.lightmodule, 'o', ModItems.operatorsbubble, 's', ModItems.subbody, 'e', ModItems.engine, 'a', ModItems.airtank));
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.liquidtanksegment, 1), "iii", "i i", "iii", 'i', Items.IRON_INGOT));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tankertrailer, 1), "   ", "sss", " ww", 's', ModItems.liquidtanksegment, 'w', ModItems.wheel));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineliquidPipe, 1), "ss ", "   ", "ss ", 's', "ingotSteel"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinefractionaldistillation, 1), "sbs", "lbl", "sts", 's', "blockSteel", 'b', Blocks.IRON_BARS, 't', Items.BREWING_STAND, 'l', ModBlocks.machineliquidPipe));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machinewellhead, 1), "   ", "lll", " l ", 'l', ModBlocks.machineliquidPipe));

		// D D
		// PBP
		// III

		SmeltingRecipes();
	}

	public static void SmeltingRecipes() {
		GameRegistry.addSmelting(ModItems.irondust, new ItemStack(Items.IRON_INGOT), 1);
		GameRegistry.addSmelting(ModItems.golddust, new ItemStack(Items.GOLD_INGOT), 2);
		GameRegistry.addSmelting(ModItems.steeldust, new ItemStack(ModItems.steelingot), 5);
		// GameRegistry.addSmelting(ModItems.golddust, new ItemStack(ModItems.aluminumingot), 0);
	}
}
