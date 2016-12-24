package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.entity.EntityExcavator;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageMouseInputToServerHandler implements IMessageHandler<MachineModMessageMouseInputToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageMouseInputToServer message, final MessageContext ctx) {

		ctx.getServerHandler().playerEntity.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageMouseInputToServer message, MessageContext ctx) {

		Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityid);

		if (entity != null) {

			if (entity instanceof EntityExcavator) {
				if (((EntityExcavator) entity).getControllingPassenger() == ctx.getServerHandler().playerEntity) {
					// its ridden by this player (avoid some hacks)
					((EntityExcavator) entity).targetBlockPos = new BlockPos(message.posX, message.posY, message.posZ);
				}
			}
		}
	}
}
