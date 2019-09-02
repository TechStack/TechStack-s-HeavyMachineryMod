package com.projectreddog.machinemod.network;

import java.util.UUID;

import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.utility.BlockBlueprintHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageRequestFileListToServerHandler implements IMessageHandler<MachineModMessageRequestFileListToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageRequestFileListToServer message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageRequestFileListToServer message, MessageContext ctx) {

		// Send packet back to client with requested info.
		String[] filelisttmp = BlockBlueprintHelper.GetBlockBlueprintFileList();
		String[] owner = new String[filelisttmp.length];
		String[] displayName = new String[filelisttmp.length];

		for (int i = 0; i < filelisttmp.length; i++) {
			// TODO convert from UUID to player name here

			UUID uuid = UUID.fromString(filelisttmp[i].split("~")[0]);

			EntityPlayer ep = ctx.getServerHandler().player.world.getPlayerEntityByUUID(uuid);

			owner[i] = ctx.getServerHandler().player.mcServer.getPlayerProfileCache().getProfileByUUID(uuid).getName();
			displayName[i] = filelisttmp[i].split("~")[1].split("\\.")[0];
		}

		if (filelisttmp != null) {
			ModNetwork.simpleNetworkWrapper.sendTo(new MachineModMessageFileListToClient(filelisttmp, owner, displayName), ctx.getServerHandler().player);
		}

	}
}
