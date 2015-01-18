package com.projectreddog.machinemod.init;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
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

		NetworkRegistry.INSTANCE.registerGuiHandler(MachineMod.instance, new GuiHandler());
	}

	public static void sendPacketToAllAround(IMessage packet, TargetPoint tp) {
		for (EntityPlayerMP player : (List<EntityPlayerMP>) FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList) {
			if (player.dimension == tp.dimension) {
				double d4 = tp.x - player.posX;
				double d5 = tp.y - player.posY;
				double d6 = tp.z - player.posZ;

				if (d4 * d4 + d5 * d5 + d6 * d6 < tp.range * tp.range) {

					ModNetwork.simpleNetworkWrapper.sendTo(packet, player);

				}
			}
		}
	}
}
