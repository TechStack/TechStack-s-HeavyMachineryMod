package com.projectreddog.machinemod.proxy;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityContinuousMiner;
import com.projectreddog.machinemod.entity.EntityCrane;
import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.entity.EntityLaserMiner;
import com.projectreddog.machinemod.entity.EntityLawnmower;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityOilRig;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityRoadRoller;
import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.entity.EntitySub;
import com.projectreddog.machinemod.entity.EntityTrackLoader;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityUnderGroundDumpTruck;
import com.projectreddog.machinemod.entity.EntityUnderGroundLoader;
import com.projectreddog.machinemod.entity.monster.EntityExpStalker;
import com.projectreddog.machinemod.handler.events.RenderOverlayHandler;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModKeyBindings;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.render.machines.RenderBagger;
import com.projectreddog.machinemod.render.machines.RenderBulldozer;
import com.projectreddog.machinemod.render.machines.RenderChopper;
import com.projectreddog.machinemod.render.machines.RenderCombine;
import com.projectreddog.machinemod.render.machines.RenderContinuousMiner;
import com.projectreddog.machinemod.render.machines.RenderCrane;
import com.projectreddog.machinemod.render.machines.RenderDrillingRig;
import com.projectreddog.machinemod.render.machines.RenderDumpTruck;
import com.projectreddog.machinemod.render.machines.RenderExcavator;
import com.projectreddog.machinemod.render.machines.RenderGrader;
import com.projectreddog.machinemod.render.machines.RenderLaserMiner;
import com.projectreddog.machinemod.render.machines.RenderLawnmower;
import com.projectreddog.machinemod.render.machines.RenderLoader;
import com.projectreddog.machinemod.render.machines.RenderOilRig;
import com.projectreddog.machinemod.render.machines.RenderPaver;
import com.projectreddog.machinemod.render.machines.RenderRoadRoller;
import com.projectreddog.machinemod.render.machines.RenderSemiTractor;
import com.projectreddog.machinemod.render.machines.RenderSub;
import com.projectreddog.machinemod.render.machines.RenderTrackLoader;
import com.projectreddog.machinemod.render.machines.RenderTractor;
import com.projectreddog.machinemod.render.machines.RenderUnderGroundDumpTruck;
import com.projectreddog.machinemod.render.machines.RenderUnderGroundLoader;
import com.projectreddog.machinemod.render.mob.RenderExpStalker;
import com.projectreddog.machinemod.render.tileentity.TileEntityCentrifugeRenderer;
import com.projectreddog.machinemod.render.tileentity.TileEntityCrateRenderer;
import com.projectreddog.machinemod.render.tileentity.TileEntityDistillerRenderer;
import com.projectreddog.machinemod.render.tileentity.TileEntityFactoryRenderer;
import com.projectreddog.machinemod.render.tileentity.TileEntityFermenterRenderer;
import com.projectreddog.machinemod.render.tileentity.TileEntityFuelPumpRenderer;
import com.projectreddog.machinemod.render.tileentity.TileEntityLiquidPipeRenderer;
import com.projectreddog.machinemod.tileentities.TileEntityCentrifuge;
import com.projectreddog.machinemod.tileentities.TileEntityCrate;
import com.projectreddog.machinemod.tileentities.TileEntityDistiller;
import com.projectreddog.machinemod.tileentities.TileEntityFactory;
import com.projectreddog.machinemod.tileentities.TileEntityFermenter;
import com.projectreddog.machinemod.tileentities.TileEntityFuelPump;
import com.projectreddog.machinemod.tileentities.TileEntityLiquidPipe;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void PreInit() {

		OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
	}

	@Override
	public void Init() {
		final BlockColors blockcolors = Minecraft.getMinecraft().getBlockColors();
		blockcolors.registerBlockColorHandler(new IBlockColor() {
			public int colorMultiplier(BlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
				return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
			}
		}, ModBlocks.machinemowedgrass);
	}

	@Override
	public void registerRenderers() {

		// LogHelper.info("in register Renderers");
		MachineModModelHelper.setupVertexFormat();
		RenderingRegistry.registerEntityRenderingHandler(EntityBulldozer.class, new RenderBulldozer(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDrillingRig.class, new RenderDrillingRig(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDumpTruck.class, new RenderDumpTruck(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLoader.class, new RenderLoader(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrader.class, new RenderGrader(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTractor.class, new RenderTractor(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLawnmower.class, new RenderLawnmower(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCombine.class, new RenderCombine(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySemiTractor.class, new RenderSemiTractor(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrane.class, new RenderCrane(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityExcavator.class, new RenderExcavator(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPaver.class, new RenderPaver(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityBagger.class, new RenderBagger(Minecraft.getMinecraft().getRenderManager()));

		RenderingRegistry.registerEntityRenderingHandler(EntityRoadRoller.class, new RenderRoadRoller(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityOilRig.class, new RenderOilRig(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySub.class, new RenderSub(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityChopper.class, new RenderChopper(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityContinuousMiner.class, new RenderContinuousMiner(Minecraft.getMinecraft().getRenderManager()));

		RenderingRegistry.registerEntityRenderingHandler(EntityLaserMiner.class, new RenderLaserMiner(Minecraft.getMinecraft().getRenderManager()));

		RenderingRegistry.registerEntityRenderingHandler(EntityExpStalker.class, new RenderExpStalker(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTrackLoader.class, new RenderTrackLoader(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityUnderGroundDumpTruck.class, new RenderUnderGroundDumpTruck(Minecraft.getMinecraft().getRenderManager()));

		RenderingRegistry.registerEntityRenderingHandler(EntityUnderGroundLoader.class, new RenderUnderGroundLoader(Minecraft.getMinecraft().getRenderManager()));

		// RenderingRegistry.registerEntityRenderingHandler(EntityPumpJack.class, new RenderPumpJack(Minecraft.getMinecraft().getRenderManager()));
		// TODO fix post JSON
		// Item ItemblockBlastedStone = GameRegistry.findItem(Reference.MOD_ID, Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// TODO Fix post Json
		// ModelBakery.registerItemVariants(ItemblockBlastedStone, new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "stone"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "granite"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_"
		// + "diorite"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "andesite"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "gold"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_"
		// + "iron"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "coal"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_"
		// + "lapis"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "diamond"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "redstone"), new ResourceLocation(Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "emerald"));

		// ModelBakery.addVariantName(ItemblockBlastedStone, Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "stone", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "granite", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "diorite", Reference.MOD_ID + ":"
		// + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "andesite", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "gold", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "iron", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "coal", Reference.MOD_ID + ":"
		// + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "lapis", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "diamond", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "redstone", Reference.MOD_ID + ":" + Reference.MODBLOCK_MACHINE_BLASTED_STONE + "_variants_" + "emerald");

		ModBlocks.initBlockRender();
		ModItems.initItemRender();

		// Register TESR (tile Entity Special renderes
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimaryCrusher.class, new TileEntityPrimaryCrusherRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCentrifuge.class, new TileEntityCentrifugeRenderer());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFuelPump.class, new TileEntityFuelPumpRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDistiller.class, new TileEntityDistillerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFermenter.class, new TileEntityFermenterRenderer());

		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWellHead.class, new TileEntityWellHeadRenderer());

		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFractionalDistillation.class, new TileEntityFractionalDistillationRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLiquidPipe.class, new TileEntityLiquidPipeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrate.class, new TileEntityCrateRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFactory.class, new TileEntityFactoryRenderer());

		// regsiter event for overlay
		MinecraftForge.EVENT_BUS.register(new RenderOverlayHandler());

	}

	@Override
	public void RegisterKeybinds() {
		ModKeyBindings.init();
	}

}
