package com.projectreddog.machinemod.network;

import javax.xml.ws.handler.MessageContext;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class MachineModMessageRequestAllInventoryToServerHandler implements IMessageHandler<MachineModMessageRequestAllInventoryToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageRequestAllInventoryToServer message, final MessageContext ctx) {

		ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageRequestAllInventoryToServer message, MessageContext ctx) {

		Entity entity = ctx.getServerHandler().player.world.getEntityByID(message.entityid);

		if (entity != null) {

			if (entity instanceof EntityMachineModRideable) {

				// do function to send inventory to client

				((EntityMachineModRideable) entity).sendAllInventoryToPlayer(ctx.getServerHandler().player);
			}
		}
	}
}
