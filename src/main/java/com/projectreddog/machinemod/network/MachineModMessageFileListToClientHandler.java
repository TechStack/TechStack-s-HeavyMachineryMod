package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.utility.BlockBlueprintHelper;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageFileListToClientHandler implements IMessageHandler<MachineModMessageFileListToClient, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageFileListToClient message, MessageContext ctx) {
		// LogHelper.info("in machineModMessageEntityToClient Handler");
		// LogHelper.info("Message data" + message);
		// LogHelper.info("on message MachineModMessageEntityToClientHandler");
		if (Minecraft.getMinecraft().world != null) {
			if (Minecraft.getMinecraft().world.isRemote) {

				Minecraft.getMinecraft().addScheduledTask(new Runnable() {
					public void run() {
						processMessage(message);
					}
				});
			}
		}
		return null;
	}

	public void processMessage(MachineModMessageFileListToClient message) {
		if (message != null) {
			// save blueprint list to static Blockblueprint Utility's local cache
			BlockBlueprintHelper.clientCacheBlueprintsFileName = message.blueprints;
			BlockBlueprintHelper.clientCacheBlueprintsOwner = message.Owners;
			BlockBlueprintHelper.clientCacheBlueprintsDisplayName = message.DisplayName;

		}
	}

}
