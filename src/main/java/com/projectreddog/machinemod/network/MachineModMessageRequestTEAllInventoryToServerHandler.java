package com.projectreddog.machinemod.network;

import javax.xml.ws.handler.MessageContext;

import com.projectreddog.machinemod.tileentities.TileEntityCrate;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class MachineModMessageRequestTEAllInventoryToServerHandler implements IMessageHandler<MachineModMessageRequestTEAllInventoryToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageRequestTEAllInventoryToServer message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageRequestTEAllInventoryToServer message, MessageContext ctx) {

		TileEntity Tileentity = ctx.getServerHandler().player.world.getTileEntity(new BlockPos(message.x, message.y, message.z));

		if (Tileentity != null) {

			if (Tileentity instanceof TileEntityCrate) {

				// do function to send inventory to client

				((TileEntityCrate) Tileentity).sendAllInventoryToPlayer(ctx.getServerHandler().player);
			}
		}
	}
}
