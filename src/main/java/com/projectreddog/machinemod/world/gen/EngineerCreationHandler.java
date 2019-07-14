package com.projectreddog.machinemod.world.gen;

import java.util.List;
import java.util.Random;

import com.projectreddog.machinemod.world.gen.structure.EngineerHouse;

import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class EngineerCreationHandler implements IVillageCreationHandler {
	public static final int WIDTH = 15;
	public static final int HEIGHT = 8;
	public static final int DEPTH = 11;
	//
	// public static final int OFFSET_X = -4;
	// public static final int OFFSET_Y = 0;
	// public static final int OFFSET_Z = -13;

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int size) {
		return new PieceWeight(EngineerHouse.class, 3, MathHelper.getInt(random, size, 1 + size));
	}

	@Override
	public Class<?> getComponentClass() {
		return EngineerHouse.class;
	}

	@Override
	public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int minX, int minY, int minZ, Direction facing, int componentType) {
		StructureBoundingBox bounds = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, 0, 0, 0, WIDTH, HEIGHT, DEPTH, facing);
		return StructureComponent.findIntersecting(pieces, bounds) == null ? new EngineerHouse(startPiece, componentType, bounds, facing) : null;
	}

}