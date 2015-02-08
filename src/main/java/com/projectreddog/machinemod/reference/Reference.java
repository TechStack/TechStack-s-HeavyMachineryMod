package com.projectreddog.machinemod.reference;

public class Reference {
	// common constants for our mod
	// example: public static final string MOD_ID ="Blah";
	public static final String MOD_ID = "machinemod";
	public static final String MOD_NAME = "MachineMod";
	// public static final String VERSION = "${version}";
	public static final String CLIENT_PROXY_CLASS = "com.projectreddog.machinemod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.projectreddog.machinemod.proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = "com.projectreddog.machinemod.client.gui.GuiFactory";
	// textures for Entities
	public static final String MODEL_BULLDOZER_TEXTURE_LOCATION = "models/bulldozer.png";
	public static final String MODEL_TRACTOR_TEXTURE_LOCATION = "models/modeltractor.png";
	public static final String MODEL_COMBINE_TEXTURE_LOCATION = "models/combine.png";
	public static final String MODEL_DRILLINGRIG_TEXTURE_LOCATION = "models/drillingrig.png";
	public static final String MODEL_DUMPTRUCK_TEXTURE_LOCATION = "models/dumptruck.png";
	public static final String MODEL_LOADER_TEXTURE_LOCATION = "models/modelloader.png";
	public static final String MODEL_CRANE_TEXTURE_LOCATION = "models/crane.png";
	public static final String MODEL_EXCAVATOR_TEXTURE_LOCATION = "models/excavator.png";

	// textures for Tile Entities
	public static final String MODEL_TILEENTITY_PRIMARY_CRUSHER_TEXTURE_LOCATION = "models/primarycrusher.png";

	public static final int GUI_DUMP_TRUCK = 0;
	public static final int GUI_LOADER = 1;
	public static final int GUI_TRACTOR = 2;
	public static final int GUI_WIDEBEDTRUCK = 3;
	public static final int GUI_COMBINE = 4;

	public static final int MAX_TREE_DEPTH = 256;
	public static final int MAX_TREE_WIDTH = 4;
	public static final String MODBLOCK_MACHINE_ASSEMBLY_TABLE = "machineassemblytable";
	public static final String MODBLOCK_MACHINE_DRILLED_STONE = "machinedrilledstone";
	public static final String MODBLOCK_MACHINE_BLASTED_STONE = "machineblastedstone";
	public static final String MODBLOCK_MACHINE_CORN = "corn";
	public static final String MODBLOCK_MACHINE_BLASTED_STONE2 = "machineblastedstone2";
	public static final String MODBLOCK_MACHINE_PRIMARY_CRUSHER = "machineprimarycrhuser";
	public static final String MODBLOCK_MACHINE_CONVEYOR = "machineconveyor";

	public static final String MODBLOCK_MACHINE_FLUID_BIOFUEL = "biofuel";

	public static final String MODBLOCK_MACHINE_EXPLOSIVE_PACKED_DRILLED_STONE = "machineexplosivepackeddrilledstone";
	public static final String MACHINE_MOD_NBT_PREFIX = "MACHINE_MOD_";

	// config file settings
	public static boolean enableBulldozer = true;
}
