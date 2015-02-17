package com.projectreddog.machinemod.init;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.client.gui.GuiHandler;
import com.projectreddog.machinemod.network.MachineModMessageEntityCurrentTargetPosToClient;
import com.projectreddog.machinemod.network.MachineModMessageEntityCurrentTargetPosToClientHandler;
import com.projectreddog.machinemod.network.MachineModMessageEntityInventoryChangedToClient;
import com.projectreddog.machinemod.network.MachineModMessageEntityInventoryChangedToClientHandler;
import com.projectreddog.machinemod.network.MachineModMessageEntityToClient;
import com.projectreddog.machinemod.network.MachineModMessageEntityToClientHandler;
import com.projectreddog.machinemod.network.MachineModMessageInputToServer;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerHandler;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerOpenGui;
import com.projectreddog.machinemod.network.MachineModMessageInputToServerOpenGuiHandler;
import com.projectreddog.machinemod.network.MachineModMessageMouseInputToServer;
import com.projectreddog.machinemod.network.MachineModMessageMouseInputToServerHandler;
import com.projectreddog.machinemod.network.MachineModMessageRequestAllInventoryToServer;
import com.projectreddog.machinemod.network.MachineModMessageRequestAllInventoryToServerHandler;
import com.projectreddog.machinemod.reference.Reference;

public class ModNetwork {

	public static SimpleNetworkWrapper simpleNetworkWrapper;

	public static void init() {
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		simpleNetworkWrapper.registerMessage(MachineModMessageInputToServerHandler.class, MachineModMessageInputToServer.class, 0, Side.SERVER);// message
																																				// to
																																				// server

		simpleNetworkWrapper.registerMessage(MachineModMessageEntityToClientHandler.class, MachineModMessageEntityToClient.class, 1, Side.CLIENT);// message
																																					// to
																																					// client
		simpleNetworkWrapper.registerMessage(MachineModMessageInputToServerOpenGuiHandler.class, MachineModMessageInputToServerOpenGui.class, 2, Side.SERVER);// message
																																								// to
																																								// server
		simpleNetworkWrapper.registerMessage(MachineModMessageEntityInventoryChangedToClientHandler.class, MachineModMessageEntityInventoryChangedToClient.class, 3, Side.CLIENT);// message
		simpleNetworkWrapper.registerMessage(MachineModMessageRequestAllInventoryToServerHandler.class, MachineModMessageRequestAllInventoryToServer.class, 4, Side.SERVER);// message // to
		// client

		simpleNetworkWrapper.registerMessage(MachineModMessageMouseInputToServerHandler.class, MachineModMessageMouseInputToServer.class, 5, Side.SERVER);// message to server

		simpleNetworkWrapper.registerMessage(MachineModMessageEntityCurrentTargetPosToClientHandler.class, MachineModMessageEntityCurrentTargetPosToClient.class, 6, Side.CLIENT);// message to server

		NetworkRegistry.INSTANCE.registerGuiHandler(MachineMod.instance, new GuiHandler());
	}

	// public static void sendPacketToAllAround(IMessage packet, TargetPoint tp) {
	// for (EntityPlayerMP player : (List<EntityPlayerMP>) FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList) {
	// if (player.dimension == tp.dimension) {
	// double d4 = tp.x - player.posX;
	// double d5 = tp.y - player.posY;
	// double d6 = tp.z - player.posZ;
	//
	// if (d4 * d4 + d5 * d5 + d6 * d6 < tp.range * tp.range) {
	//
	// ModNetwork.simpleNetworkWrapper.sendTo(packet, player);
	//
	// }
	// }
	// }
	// }
}
