package com.projectreddog.machinemod.init;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.client.gui.GuiHandler;
import com.projectreddog.machinemod.network.MachineModMessageEntityToClient;
import com.projectreddog.machinemod.network.MachineModMessageEntityToClientHandler;
import com.projectreddog.machinemod.network.MachineModMessageInputToServer;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerHandler;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerOpenGui;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerOpenGuiHandler;
import com.projectreddog.machinemod.reference.Reference;

public class ModNetwork {

	public static SimpleNetworkWrapper simpleNetworkWrapper;
	
	public static void init(){
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		simpleNetworkWrapper.registerMessage(MachineModMessageInputToServerHandler.class, MachineModMessageInputToServer.class, 0, Side.SERVER);// message to server
		
		
		simpleNetworkWrapper.registerMessage(MachineModMessageEntityToClientHandler.class, MachineModMessageEntityToClient.class, 1, Side.CLIENT);// message to server
		simpleNetworkWrapper.registerMessage(MachineModMessageInputToServerOpenGuiHandler.class, MachineModMessageInputToServerOpenGui.class, 2, Side.SERVER);// message to server

		NetworkRegistry.INSTANCE.registerGuiHandler(MachineMod.instance, new GuiHandler());
	}
}
