package com.projectreddog.machinemod.network;

import com.projectreddog.machinemod.entity.EntityMachineModRideable;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineModMessageInputToServerHandler implements IMessageHandler<MachineModMessageInputToServer, IMessage> {

	@Override
	public IMessage onMessage(final MachineModMessageInputToServer message, final MessageContext ctx) {

		ctx.getServerHandler().playerEntity.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, ctx);
			}
		});
		return null;
	}

	public void processMessage(MachineModMessageInputToServer message, MessageContext ctx) {

		Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityid);

		if (entity != null) {

			if (entity instanceof EntityMachineModRideable) {
				if (((EntityMachineModRideable) entity).getControllingPassenger() == ctx.getServerHandler().playerEntity) {
					// its ridden by this player (avoid some hacks)
					((EntityMachineModRideable) entity).isPlayerAccelerating = message.isPlayerAccelerating;
					((EntityMachineModRideable) entity).isPlayerBreaking = message.isPlayerBreaking;
					((EntityMachineModRideable) entity).isPlayerTurningLeft = message.isPlayerTurningLeft;
					((EntityMachineModRideable) entity).isPlayerTurningRight = message.isPlayerTurningRight;
					((EntityMachineModRideable) entity).isPlayerPushingSprintButton = message.isPlayerPushingSprintButton;
					((EntityMachineModRideable) entity).isPlayerPushingJumpButton = message.isPlayerPushingJumpButton;
				}
			}
		}
	}
}
