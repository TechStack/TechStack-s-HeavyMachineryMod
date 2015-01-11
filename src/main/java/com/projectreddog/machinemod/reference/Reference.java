package com.projectreddog.machinemod.reference;

public class Reference {
//common constants for our mod
	//example: public static final string MOD_ID ="Blah";
	public static final String MOD_ID ="machinemod";
	public static final String MOD_NAME ="MachineMod";
	public static final String VERSION = "1.7.10-1.0-Pre-ALPHA";
	public static final String CLIENT_PROXY_CLASS = "com.projectreddog.machinemod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.projectreddog.machinemod.proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = "com.projectreddog.machinemod.client.gui.GuiFactory";
	public static final String MODEL_BULLDOZER_TEXTURE_LOCATION = "models/modelbulldozer2.png";
	public static final String MODEL_DRILLINGRIG_TEXTURE_LOCATION = "models/drillingrig.png";
	public static final String MODEL_DUMPTRUCK_TEXTURE_LOCATION = "models/dumptruck.png";
	public static final String MODEL_LOADER_TEXTURE_LOCATION = "models/dumptruck.png";

	public static final int GUI_DUMP_TRUCK  = 0;
	public static final int GUI_LOADER  = 1;

	public static final String MODBLOCK_MACHINE_ASSEMBLY_TABLE ="machineassemblytable";
	public static final String MODBLOCK_MACHINE_DRILLED_STONE ="machinedrilledstone";
	public static final String MODBLOCK_MACHINE_BLASTED_STONE ="machineblastedstone";
	public static final String MODBLOCK_MACHINE_DRILLING_RIG ="machinedrillingrig";


	public static final String MODBLOCK_MACHINE_EXPLOSIVE_PACKED_DRILLED_STONE ="machineexplosivepackeddrilledstone";

	
	// config file settings
	public static	boolean enableBulldozer=true;
}
