package com.projectreddog.machinemod.network;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.projectreddog.machinemod.entity.EntityExcavator;

public class MachineModMessageMouseInputToServerHandler implements IMessageHandler<MachineModMessageMouseInputToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageMouseInputToServer message, final MessageContext ctx) {

		ctx.getServerHandler().playerEntity.getServerForPlayer().addScheduledTask(new Runnable() {
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
				if (((EntityExcavator) entity).riddenByEntity == ctx.getServerHandler().playerEntity) {
					// its ridden by this player (avoid some hacks)
					((EntityExcavator) entity).targetBlockPos = new BlockPos(message.posX, message.posY, message.posZ);
				}
			}
		}
	}
}
